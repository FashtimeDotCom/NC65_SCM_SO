package nc.impl.so.multipleprofit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.so.multipleprofit.temp.MultipleProfitTempTable;
import nc.impl.so.pub.summary.util.SQLCreateUtil;
import nc.itf.so.multipleprofit.IMultipleProfitMaintain;
import nc.pubitf.arap.receivable.IArapReceivableBillPubServiceForSCM;
import nc.pubitf.ia.mi5.so.IIAI5ForSOReportAnalyse;
import nc.ui.querytemplate.filter.DefaultFilter;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.report.ReportQueryConUtil;
import nc.vo.pubapp.util.DefaultVOMerger;
import nc.vo.scmpub.report.SCMReportTempTableUtil;
import nc.vo.so.report.multipleprofit.MultProfitSourceSystem;
import nc.vo.so.report.multipleprofit.MultipleProfitViewMeta;
import nc.vo.so.report.multipleprofit.MultipleProfitViewVO;
import nc.vo.so.report.reportpub.ReportContext;
import nc.vo.so.report.reportpub.ReportLevelProcesser;
import nc.vo.so.report.reportpub.ReportUserObject;

import com.ufida.dataset.IContext;

/**
 * ʵ���ۺ�ë��������ѯ�Ľӿ�12
 * 
 * @since 6.3
 * @version 2012-10-18 14:22:05
 * @author zhangkai4
 */
public class MultipleProfitMaintainImpl implements IMultipleProfitMaintain {

  @Override
  public String queryMultipleProfit(IContext context) throws BusinessException {
    // 1.������ʱ��
    String temptable = this.createTempTable();

    // 2.�жϲ�ѯ�����Ƿ�Ϊ��
    ReportQueryConUtil qryconutil = new ReportQueryConUtil(context);

    if (qryconutil.isNull()) {
      return "select * from " + temptable;
    }
    ReportUserObject userObject = (ReportUserObject) qryconutil.getUserObject();
    // 3.�������
    MultipleProfitViewVO[] viewVO = this.getData(userObject);

    if (null == viewVO || viewVO.length == 0) {
      return this.getSelectSql(temptable, userObject);
    }

    // 4.��������
    MultipleProfitViewVO[] combinviews = this.processData(viewVO, userObject);

    // 5.������ʱ��
    this.insertIntoTmpTable(temptable, combinviews);

    // 6.������ʱ������
    return this.getSelectSql(temptable, userObject);
  }

  private void insertIntoTmpTable(String temptable,
      MultipleProfitViewVO[] combinviews) {

    String[] fieldnames =
        new MultipleProfitTempTable().getMetaData().getFieldNames();
    SCMReportTempTableUtil tmptableutil = new SCMReportTempTableUtil();
    tmptableutil.insertIntoTempTable(temptable, fieldnames, combinviews);

  }

  /**
   * ��������
   * 
   * @param viewVO
   * @param userObject
   * @return
   */
  private MultipleProfitViewVO[] processData(MultipleProfitViewVO[] viewVO,
      ReportUserObject userObject) {
    // ������֯���κ����Ϸ��༶�δ���
    int marLevel = 0;
    int saleorgLevel = 0;
    IQueryScheme queryScheme = userObject.getIQueryScheme();
    ConditionVO[] conds =
        (ConditionVO[]) queryScheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals(ReportContext.CMATERIALMARBASCLASSLEVEL)) {
        marLevel = Integer.valueOf(cond.getValue()).intValue();
      }
      else if (cond.getFieldCode().equals(ReportContext.SALEORGLEVEL)) {
        saleorgLevel = Integer.valueOf(cond.getValue()).intValue();
      }
    }
    ReportLevelProcesser levelProcesser = ReportLevelProcesser.getInstance();
    levelProcesser.processMarLevel(viewVO, MultipleProfitViewVO.CMATERIALID,
        MultipleProfitViewVO.PK_MARBASCLASS, marLevel);
    levelProcesser.processSaleorgLevel(viewVO, MultipleProfitViewVO.CSALEORGID,
        saleorgLevel);

    // ���л���
    // MultipleProfitViewVO[] combinviews =
    // this.fillSumInfo(viewVO, userObject.getSummaryKeys());
    this.calMnyField(viewVO);
    return viewVO;
  }

  /**
   * ���ݲ�ѯ���������ŵ���Ӧ�յ����ɱ���ת�����������ⵥ����ȡ���ݲ�����
   * 
   * @param userObject
   * @return ����֮�������
   */
  private MultipleProfitViewVO[] getData(ReportUserObject userObject) {
    ConditionVO[] conditions =
        (ConditionVO[]) userObject.getIQueryScheme().get(
            IQueryScheme.KEY_LOGICAL_CONDITION);
    MultProfitSourceSystem sourSys = this.getSourceSystem(userObject);
    String[] sumKeys = this.getSummaryKeys(userObject);

    // ��Ӧ�յ���ȡ��Ϣ
    MultipleProfitViewVO[] obtainViewVO =
        this.queryObtainViewVO(conditions, sourSys, sumKeys);
    // �ӳɱ���ת�����������ⵥ��ȡ��Ϣ
    MultipleProfitViewVO[] costViewVO =
        this.queryCostViewVO(conditions, sourSys, sumKeys);

    // ���ܣ�Ӧ�յ����ɱ���ת�����������ⵥ������Ϣ
    MultipleProfitViewVO[] viewVO = this.getView(obtainViewVO, costViewVO);

    for (MultipleProfitViewVO view : viewVO) {
      if (view.getSourcesystem().equals("0")) {
        view.setSourcesystem(NCLangResOnserver.getInstance().getStrByID(
            "40060906", "1400609060028")/* ���� */);
      }
      else if (view.getSourcesystem().equals("1")) {
        view.setSourcesystem(NCLangResOnserver.getInstance().getStrByID(
            "40060906", "1400609060029")/* �ڲ����� */);
      }
    }
    return viewVO;
  }

  private String[] getSummaryKeys(ReportUserObject userObject) {
    Set<String> summkeySet = userObject.getSummaryKeys();
    // ���������֯������
    summkeySet.add(MultipleProfitViewVO.CSALEORGID);
    summkeySet.add(MultipleProfitViewVO.CMATERIALID);
    // summkeySet.add(MultipleProfitViewVO.CUNITID);
    summkeySet.add(MultipleProfitViewVO.CORIGCURRENCYID);
    String[] sumKeys = new String[summkeySet.size()];
    summkeySet.toArray(sumKeys);
    return sumKeys;
  }

  /**
   * ��д��Ӧ����˰���ۡ������ɱ����ۡ�����ë��������ë���ʡ�<br>
   * ���㹫ʽ��<br>
   * <ol>
   * <li>Ӧ����˰����=Ӧ����˰��� /Ӧ��������</li>
   * <li>�ɱ�����=�ɱ���� /�������������</li>
   * <li>ë��=Ӧ����˰���-�ɱ����</li>
   * <li>ë����=ë��/��˰���</li>
   * </ol>
   * 
   * @param viewVO
   */
  private void calMnyField(MultipleProfitViewVO[] viewVOs) {
    for (MultipleProfitViewVO viewVO : viewVOs) {
      // Ӧ����˰���
      UFDouble totalReceivMny = viewVO.getNtotalreceivmny();
      // �ɱ����
      UFDouble totalCostMny = viewVO.getNtotalcostmny();
      // ����ë��
      viewVO.setNprofitmny(MathTool.sub(totalReceivMny, totalCostMny));

    }
  }

  /**
   * ���л��ܣ�������֯�������ͻ�����Ʒ�ߡ��ͻ����۷��ࡢ���ϻ������ࣩ
   * 
   * @param viewVO
   *          ����ǰ��VO
   * @return ����֮���VO
   */
  private MultipleProfitViewVO[] fillSumInfo(MultipleProfitViewVO[] views,
      Set<String> groupSet) {
    DefaultVOMerger mergertool = new DefaultVOMerger();
    String[] groupkeys = new String[groupSet.size()];
    groupSet.toArray(groupkeys); // �����ֶ�
    String[] sumkeys = MultipleProfitViewMeta.AGGKEYS;

    mergertool.setGroupingAttr(groupkeys);
    mergertool.setSummingAttr(sumkeys);
    try {
      MultipleProfitViewVO[] conbinviews =
          (MultipleProfitViewVO[]) mergertool.mergeByGroup1(views);
      return conbinviews;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * ���ܣ�Ӧ�յ�,�ɱ���ת�����������ⵥ������Ϣ
   * 
   * @param allotViewVO
   *          Ӧ�յ�
   * @param costViewVO
   *          �ɱ���ת��
   * 
   * @return
   */
  private MultipleProfitViewVO[] getView(MultipleProfitViewVO[] obtainViewVO,
      MultipleProfitViewVO[] costViewVO) {
    // int length = obtainViewVO.length + costViewVO.length +
    // allotViewVO.length;
    List<MultipleProfitViewVO> listviews =
        new ArrayList<MultipleProfitViewVO>();
    // Ӧ�յ�
    for (MultipleProfitViewVO viewVO : obtainViewVO) {
      listviews.add(viewVO);
    }
    // �ɱ���ת��\�������ⵥ
    for (MultipleProfitViewVO viewVO : costViewVO) {
      listviews.add(viewVO);
    }

    return listviews.toArray(new MultipleProfitViewVO[listviews.size()]);
  }

  /**
   * �ӳɱ���ȡ��Ϣ
   * 
   * @param summkey2
   * @param soursys2
   * @param condition
   * @return
   */
  private MultipleProfitViewVO[] queryCostViewVO(ConditionVO[] conditions,
      MultProfitSourceSystem sourSys, String[] sumKeys) {
    MultipleProfitViewVO[] viewVO = null;
    IIAI5ForSOReportAnalyse iasrv =
        NCLocator.getInstance().lookup(IIAI5ForSOReportAnalyse.class);
    try {
      viewVO =
          iasrv.queryCostDataForMultipleProfitForSO(conditions, sourSys,
              sumKeys);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return viewVO;

  }

  /**
   * ��Ӧ�յ���ȡ��Ϣ
   * 
   * @param summkey2
   * @param soursys2
   * @param condition
   * 
   * @return
   */
  private MultipleProfitViewVO[] queryObtainViewVO(ConditionVO[] conditions,
      MultProfitSourceSystem sourSys, String[] sumKeys) {
    // ����Ӧ�յ���Ϣ
    IArapReceivableBillPubServiceForSCM iArapForMulProfit =
        NCLocator.getInstance().lookup(
            IArapReceivableBillPubServiceForSCM.class);
    MultipleProfitViewVO[] obtainViewVO = null;
    try {
      obtainViewVO =
          iArapForMulProfit.queryMultProfit(conditions, sourSys, sumKeys);
      for (MultipleProfitViewVO viw : obtainViewVO) {
        if (viw.getSourcesystem().equals("3")) {
          viw.setSourcesystem("0");
        }
        else if (viw.getSourcesystem().equals("16")) {
          viw.setSourcesystem("1");
        }
      }
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return obtainViewVO;

  }

  private MultProfitSourceSystem getSourceSystem(ReportUserObject userObject) {
    // ��Դϵͳ�Ļ�ȡ
    IFilter[] filters =
        (IFilter[]) userObject.getIQueryScheme().get(IQueryScheme.KEY_FILTERS);

    int sourcesystem = 0;// ϵͳ��Դ(1:������֯��2���ڲ����ס�3��������֯+�ڲ�����)
    for (IFilter filter : filters) {
      DefaultFilter deflilter = (DefaultFilter) filter;
      String fieldCode = deflilter.getFilterMeta().getFieldCode();
      if (fieldCode.equals("sourcesystem")) {// ��Դϵͳ
        List<IFieldValueElement> valueList =
            deflilter.getFieldValue().getFieldValues();
        for (IFieldValueElement value : valueList) {
          if (value.getSqlString().equals("0")) {
            sourcesystem += 1;
          }
          else if (value.getSqlString().equals("1")) {
            sourcesystem += 2;
          }
        }
      }
    }
    switch (sourcesystem) {
      case 1:
        return MultProfitSourceSystem.RESOURCE_SALE;
      case 2:
        return MultProfitSourceSystem.RESOURCE_INNER;
      default:
        return MultProfitSourceSystem.RESOURCE_SALE_INNER;
    }
  }

  /**
   * 
   * ������ʱ��
   * 
   * @return ��ʱ��ı���
   */
  private String createTempTable() {
    // ������ʱ��
    MultipleProfitTempTable table = new MultipleProfitTempTable();
    String temptable = table.createMulProfitTemptable();
    return temptable;
  }

  /**
   * ������ʱ�������ò�ѯ��sql���
   * 
   * @param temptable
   *          ��ʱ�����
   * @param userObject
   * @return ��ѯ��sql���
   */
  private String getSelectSql(String temptable, ReportUserObject userObject) {
    QuerySchemeProcessor qsp =
        new QuerySchemeProcessor(userObject.getIQueryScheme());
    QueryCondition qc = qsp.getQueryCondition(MultipleProfitViewVO.PK_ORG);
    Object[] orgs = qc.getValues();
    SqlBuilder colmsql = new SqlBuilder();
    String[] groupkeys = this.getGroupKeys(userObject);
    colmsql.append("select ");
    boolean isorgfalg = false;
    for (String key : groupkeys) {
      colmsql.append(key + ",");
      if (MultipleProfitViewVO.PK_ORG.equals(key)) {
        isorgfalg = true;
      }
    }
    if (!isorgfalg) {
      colmsql.append("'" + orgs[0] + "' " + MultipleProfitViewVO.PK_ORG + ",");
    }

    for (String key : MultipleProfitViewMeta.AGGKEYS) {
      colmsql.append("sum(" + key + ") " + key + ",");
    }

    String ntotalreceivprice =
        SQLCreateUtil.getSumDivsql(MultipleProfitViewVO.NTOTALRECEIVMNY,
            MultipleProfitViewVO.NSHOULDRECEIVNUM);
    colmsql.appendCaseWhen("sum(nshouldreceivnum)<>0", ntotalreceivprice, "0");
    colmsql.append(" ntotalreceivprice,");

    String ncostprice =
        SQLCreateUtil.getSumDivsql(MultipleProfitViewVO.NTOTALCOSTMNY,
            MultipleProfitViewVO.NMAINNUM);
    colmsql.appendCaseWhen("sum(nmainnum)<>0", ncostprice, "0");
    colmsql.append(" ncostprice,");

    SqlBuilder nprofitrate = new SqlBuilder();
    nprofitrate.append(SQLCreateUtil.getSumDivsql(
        MultipleProfitViewVO.NPROFITMNY, MultipleProfitViewVO.NTOTALRECEIVMNY));
    colmsql.appendCaseWhen("sum(ntotalreceivmny)<>0", nprofitrate.toString(),
        "0");
    colmsql.append(" nprofitrate ");

    colmsql.append(" from ");
    colmsql.append(temptable);
    colmsql.append(getSumGroupbySQL(groupkeys));
    return colmsql.toString();
  }

  private String getSumGroupbySQL(String[] groupkeys) {
    SqlBuilder sumsql = new SqlBuilder();
    sumsql.append(" group by  ");
    for (String sumkey : groupkeys) {
      sumsql.append(sumkey);
      sumsql.append(",");
    }
    sumsql.deleteLastChar();
    return sumsql.toString();
  }

  /**
   * ��÷����ֶ�
   * 
   * @param userobject
   * @return
   */
  private String[] getGroupKeys(ReportUserObject userobject) {
    Set<String> listgroup = new HashSet<String>();
    Set<String> selgroups = userobject.getSummaryKeys();
    for (String key : selgroups) {
      listgroup.add(key);
      if (MultipleProfitViewVO.CMATERIALID.equals(key)) {
        listgroup.add(MultipleProfitViewVO.CUNITID);
      }
    }
    listgroup.add(MultipleProfitViewVO.CORIGCURRENCYID);
    String[] groupkeys = new String[listgroup.size()];
    listgroup.toArray(groupkeys);
    return groupkeys;
  }

}
