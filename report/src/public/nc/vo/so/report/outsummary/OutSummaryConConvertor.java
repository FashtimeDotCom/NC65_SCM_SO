package nc.vo.so.report.outsummary;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nc.bs.scmpub.report.ReportQueryCondition;
import nc.bs.scmpub.report.ReportScaleProcess;
import nc.bs.scmpub.report.ReportScaleProcess2;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.iufo.freereport.extend.ISubscribeConditionConvertor;
import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.so.report.reportpub.ReportUIAdjust;
import nc.vo.so.report.reportpub.ReportUserObject;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseSubscribeCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * ���۳���ִ�л��ܱ����Ĵ�����
 * 
 * @since 6.3
 * @version 2012-10-18 13:35:44
 * @author ������
 */
public class OutSummaryConConvertor implements ISubscribeConditionConvertor {

  /**
   * ���з����ֶ�
   */
  private static final String[] ALLKEY = new String[] {
    OutSummaryViewVO.CSALEORGOID, OutSummaryViewVO.PK_ORG,
    OutSummaryViewVO.CBIZID, OutSummaryViewVO.CDPTID,
    OutSummaryViewVO.PK_CUSTCLASS, OutSummaryViewVO.PK_CUSTSALECLASS,
    OutSummaryViewVO.PK_AREACL, OutSummaryViewVO.CCUSTOMERID,
    OutSummaryViewVO.CINVOICECUSTID, OutSummaryViewVO.CRECEIEVEID,
    OutSummaryViewVO.PK_MARBASCLASS, OutSummaryViewVO.PK_MARSALECLASS,
    OutSummaryViewVO.VBATCHCODE, OutSummaryViewVO.CMATERIALOID,
    OutSummaryViewVO.FLARGESS, OutSummaryViewVO.CCHANNELTYPEID
  };

  /**
   * ���л����ֶ�
   */
  public static final String[] AGGKEYS = new String[] {
    OutSummaryViewVO.NNUM, OutSummaryViewVO.NACCUMOUTSIGNNUM,
    OutSummaryViewVO.NACCUMOUTBACKNUM, OutSummaryViewVO.NACCUMWASTNUM,
    OutSummaryViewVO.NSIGNNUM, OutSummaryViewVO.NARNUM,
    OutSummaryViewVO.NORIGTAXMNY, OutSummaryViewVO.NINVOICEMNY,
    OutSummaryViewVO.NARMNY, OutSummaryViewVO.NARREMAINMNY,

  };

  /**
   * �����ֶ�
   */
  private static final String[] NUMKEY = new String[] {
    OutSummaryViewVO.NNUM, OutSummaryViewVO.NACCUMOUTSIGNNUM,
    OutSummaryViewVO.NACCUMOUTBACKNUM, OutSummaryViewVO.NACCUMWASTNUM,
    OutSummaryViewVO.NSIGNNUM, OutSummaryViewVO.NARNUM
  };

  /**
   * ����ֶ�
   */
  private static final String[] MNYKEY = new String[] {
    OutSummaryViewVO.NORIGTAXMNY, OutSummaryViewVO.NINVOICEMNY,
    OutSummaryViewVO.NARMNY, OutSummaryViewVO.NARREMAINMNY,
    OutSummaryViewVO.NPAYMNY
  };

  @Override
  public IQueryCondition getQueryCondition(
      ISubscribeQueryCondition subscribCondition, IContext context,
      AbsAnaReportModel reportModel) {
    if (null == subscribCondition) {
      return null;
    }

    BaseSubscribeCondition base = (BaseSubscribeCondition) subscribCondition;
    QueryScheme scheme = (QueryScheme) base.getScheme();

    // 1.���ñ���ɸѡ������Ϊ��
    ReportQueryCondition result = new ReportQueryCondition(true);
    // 2.���ȴ���
    ReportScaleProcess process = this.scaleProcess();
    result.setBusiFormat(process);

    // ��ʽ����
    ReportUIAdjust adjust = new ReportUIAdjust(OutSummaryConConvertor.ALLKEY);
    adjust.addSpecialHideRela(OutSummaryViewVO.CMATERIALOID,
        OutSummaryViewVO.CUNITID);
    result.setRoportAdjustor(adjust);

    ReportUserObject userobject = new ReportUserObject(scheme);
    result.setUserObject(userobject);

    // ���顢�����ֶ�
//    String[] groupkeys = this.getGroupKeys(userobject);
//    ReportAggDes descriptor =
//        new ReportAggDes(groupkeys, OutSummaryConConvertor.AGGKEYS);
//    result.setDescriptors(new Descriptor[] {
//      descriptor
//    });
    return result;
  }

  private ReportScaleProcess scaleProcess() {
    ReportScaleProcess2 process = new ReportScaleProcess2();

    // ����
    process.setMnyDigits(OutSummaryViewVO.CORIGCURRENCYID,
        OutSummaryConConvertor.MNYKEY);
    // �ϼƴ���
    process.setTotalFields(OutSummaryConConvertor.MNYKEY);
    process.setNumTotalFields(OutSummaryConConvertor.NUMKEY);
    return process;
  }

  private String[] getGroupKeys(ReportUserObject userobject) {
    List<String> listgroup = new ArrayList<String>();
    Set<String> selgroups = userobject.getSummaryKeys();
    for (String key : selgroups) {
      listgroup.add(key);
      if (OutSummaryViewVO.CMATERIALOID.equals(key)) {
        listgroup.add(OutSummaryViewVO.CUNITID);
      }
    }
    listgroup.add(OutSummaryViewVO.CORIGCURRENCYID);
    String[] groupkeys = new String[listgroup.size()];
    listgroup.toArray(groupkeys);
    return groupkeys;
  }
}
