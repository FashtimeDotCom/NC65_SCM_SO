package nc.pubimpl.so;

import nc.bs.bank_cvp.formulainterface.RefCompilerBS;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.pubitf.so.m30.ICustomFunction;
import nc.pubitf.so.m30.ReturnAssignMatchVO;
import nc.vo.bank_cvp.compile.datastruct.ArrayValue;
import nc.vo.bd.material.MaterialVO;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.ic.m4c.entity.SaleOutViewVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.CarrierRuntimeException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.mreturncondition.entity.ReturnConditionVO;
import nc.vo.so.mreturnreason.entity.ReturnReasonVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * �˴���������˵����
 * �������ڣ�(2004-3-10 11:05:57)
 * 
 * @author����С��
 */
// import nc.vo.bank_cvp.compile.datastruct.IContext;
public class CustomFunctionImpl implements ICustomFunction {
  /**
   * CustomFunction ������ע�⡣
   */
  public CustomFunctionImpl() {
    super();
  }

  /**
   * �������ƣ������˻����ݽ��
   * �������������ͷ�Ŀͻ�����(����ʱ�ı���)
   * ����ֵ����ǰ�˻����뵥�Ŀͻ��ĵ�ǰ���ݸ��м�˰�ϼƽ��֮�͡�
   * Ӧ��ʵ����ѡ��ҵ����"�����˻����ݽ��"����ʾ����Ϊ"�����ǰ�˻����뵥�Ŀͻ��������˻��ܽ��"��
   */
  @Override
  public double getAppRetBillMny(ReturnAssignMatchVO paravo) {
    if (VOChecker.isEmpty(paravo)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006006_0", "04006006-0131")/*��ָ���ĵ�ǰ���˻����뵥�����¼��Ч��*/);
    }
    if (paravo.getNorigtaxmny() == null) {
      return 0;
    }
    return paravo.getNorigtaxmny().doubleValue();
  }

  /**
   * �������ƣ������˻�����
   * ��������������еĴ�����룫������(����ʱ�ı���)
   * ����ֵ����ǰ�д���������˻�����������
   * Ӧ��ʵ����ѡ��ҵ����"�����˻�����"����ʾ����Ϊ"�����ǰ�˻����뵥�Ŀͻ���ǰ�д���������˻�����"
   */
  @Override
  public double getAppRetNum(ReturnAssignMatchVO paravo) {
    if (VOChecker.isEmpty(paravo)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006006_0", "04006006-0131")/*��ָ���ĵ�ǰ���˻����뵥�����¼��Ч��*/);
    }
    if (paravo.getNnum() == null) {
      return 0;
    }
    return paravo.getNnum().doubleValue();
  }

  /*
  * �������ƣ��������
  * ��������������еĴ������ (����ʱ�ı���)
  * ����ֵ��������롣
  * Ӧ��ʵ����ѡ��ҵ����"���"��ѡ��"��"�����ַ���������"1876A"��ʾ����Ϊ"�����ǰ�д������Ϊ'1876A'��"��һ��������ĳ�ִ��׼�˻�׼�˵ȡ�
  */
  @Override
  public String getInvCode(ReturnAssignMatchVO paravo) {
    if (VOChecker.isEmpty(paravo)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006006_0", "04006006-0131")/*��ָ���ĵ�ǰ���˻����뵥�����¼��Ч��*/);
    }
    if (paravo.getPk_material() == null) {
      ExceptionUtils
          .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
              "4006006_0", "04006006-0132")/*��ָ���ĵ�ǰ���˻����뵥�����¼δָ�������Ϣ��*/);
    }
    String strPk = paravo.getPk_material();
    try {
      MaterialVO[] vos =
          MaterialPubService.queryMaterialBaseInfoByPks(new String[] {
            strPk
          }, new String[] {
            MaterialVO.CODE
          });
      String strCode = vos[0].getCode();
      return strCode;
    }
    catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * �������ƣ������������
   * ��������������еĴ�����룫������(����ʱ�ı���)
   * ����ֵ������������еĴ���������ڣ�0��1��2��3����
   * Ӧ��ʵ����ѡ��ҵ����"�����������"��ѡ��"��"�����ַ���������"0"��ʾ����Ϊ"�����ǰ�д������������Ϊ0(��������)"��
   */
  @Override
  public int getInvLifePrd(ReturnAssignMatchVO paravo) throws BusinessException {
    String pk_material = paravo.getPk_material();
    MaterialVO[] vos =
        MaterialPubService.queryMaterialBaseInfoByPks(new String[] {
          pk_material
        }, new String[] {
          MaterialVO.PROLIFEPERIOD
        });
    if (null != vos && vos.length > 0) {
      Integer life = vos[0].getProlifeperiod();
      if (null == life) {
        return 0;
      }
      return life.intValue();
    }
    return 0;
  }

  /**
   * �������ƣ����۶�����ʱ�����䣩
   * �������������ͷ�Ŀͻ�����(����ʱ�ı���) ������ͷ�ĵ�������(����ʱ�ı���)��ʱ�����䣨��������ʱ�ĳ�����
   * ����ֵ������������"��������"��"�������ڣ�ʱ������"���������۶����ı��Ҽ�˰�ϼ�֮�͡�
   * Ӧ��ʵ����ѡ��ҵ����"���۶������"��ѡ��"��"�������ִ�����"10"��ѡ��"��"�����ɺ���"���۶�����10��"��ʾ����Ϊ
   * "�����ǰ���ݵĿͻ��ڵ���������ǰ����10���ڵ�ʵ�����۶������֮��"���γ�where�Ӿ��between (today-10) and
   * today����
   */
  @Override
  public double getOrderMny(int iDays, ReturnAssignMatchVO paravo) {
    UFDouble dblMny = null;
    String cust = paravo.getPk_customer();
    String material = paravo.getPk_material();
    UFDate date = paravo.getDbilldate();
    UFDate startDate = date.getDateBefore(iDays);
    StringBuffer sbfSql =
        new StringBuffer("select sum(so_saleorder_b.norigmny) ");
    sbfSql.append("from so_saleorder_b left join so_saleorder ");
    sbfSql
        .append("on so_saleorder.csaleorderid = so_saleorder_b.csaleorderid ");
    sbfSql.append("where so_saleorder.dbilldate<='" + date.toString() + "' ");
    sbfSql
        .append("and so_saleorder.dbilldate>='" + startDate.toString() + "' ");
    sbfSql.append("and so_saleorder.ccustomerid ='" + cust + "' ");
    sbfSql.append("and so_saleorder_b.cmaterialvid ='" + material + "' ");
    sbfSql.append("and so_saleorder.fstatusflag=2 ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sbfSql.toString());
    while (rowset.next()) {
      dblMny = rowset.getUFDouble(0);
    }
    if (dblMny == null) {
      return 0;
    }
    return dblMny.getDouble();
  }

  /**
   * �������ƣ�ʵ�ʳ���������ʱ�����䣩
   * ��������������еĴ�����룫������(����ʱ�ı���)������ͷ�Ŀͻ�����(����ʱ�ı���)������ͷ�ĵ�������(����ʱ�ı���)��ʱ�����䣨��������ʱ�ĳ�������
   * ��
   * ����ֵ������������"��������"��"�������ڣ�ʱ������"���������۳��ⵥ��ʵ�ʳ��������֮�͡�
   * Ӧ��ʵ����ѡ��ҵ����"ʵ�ʳ�������"��ѡ��"��"�������ִ�����"10"��ѡ��"��"�����ɺ���"ʵ�ʳ���������10��"��ʾ����Ϊ
   * "�����ǰ���ݵĿͻ���ǰ�д�����ڵ���������ǰ����10���ڵ�ʵ�ʳ�������֮��"���γ�where�Ӿ��between (today-10) and
   * today����
   */
  @Override
  public double getOutNumber(int iDays, ReturnAssignMatchVO paravo) {
    UFDate date = paravo.getDbilldate();
    UFDate startDate = date.getDateBefore(iDays);
    String cust = paravo.getPk_customer();
    String cmaterialid1 = paravo.getPk_material();
    StringBuffer sql = new StringBuffer();
    sql.append("select " + MetaNameConst.CGENERALBID
        + " from ic_saleout_h h,ic_saleout_b b");
    sql.append(" where h.dr =0 and b.dr = 0 ");
    sql.append(" and h." + MetaNameConst.CCUSTOMERID + " ='" + cust + "'");
    sql.append(" and b." + MetaNameConst.DBIZDATE + " >='" + startDate + "'");
    sql.append(" and b." + MetaNameConst.DBIZDATE + " <='" + date + "'");
    sql.append(" and b." + ICPubMetaNameConst.CMATERIALVID + "='"
        + cmaterialid1 + "'");
    DataAccessUtils utils = new DataAccessUtils();
    String[] bids = utils.query(sql.toString()).toOneDimensionStringArray();
    SaleOutViewVO[] views =
        new ViewQuery<SaleOutViewVO>(SaleOutViewVO.class).query(bids);
    UFDouble value = new UFDouble(0.0);
    for (SaleOutViewVO view : views) {
      UFDouble num = view.getItem().getNnum();
      value = MathTool.add(num, value);
    }
    return value.doubleValue();
  }

  /**
   * �������ƣ��˻����뵥���ڡ�
   */
  @Override
  public String getResBillDate(ReturnAssignMatchVO paravo) {
    return this.strtostr(paravo.getDbilldate().toString());
  }

  /**
   * �������ƣ��˻�ԭ������
   * �����������������˻�ԭ�����
   * ����ֵ���˻�ԭ�򵵰��е��˻�ԭ������ȡֵ��
   * Ӧ��ʵ����ѡ��ҵ����"�˻�ԭ������"��ѡ��"��"�����ַ���������"0"
   * (�����˻�)����ʾ����Ϊ"�����ǰ�˻����뵥�ĵ�ǰ�д�����˻�ԭ����˻�ԭ������Ϊ"0"�������˻�����һ�������ж��Ƿ�������ԭ���˻صȡ�
   */
  @Override
  public int getRetRsnType(ReturnAssignMatchVO paravo) {
    String strPk_ReturnReason = paravo.getCretreasonid();
    if (strPk_ReturnReason == null) {
      throw new CarrierRuntimeException(NCLangResOnserver.getInstance()
          .getStrByID("4006006_0", "04006006-0133")/*��¼���˻�ԭ��*/);
    }
    VOQuery<ReturnReasonVO> query =
        new VOQuery<ReturnReasonVO>(ReturnReasonVO.class);
    ReturnReasonVO[] vos = query.query(new String[] {
      strPk_ReturnReason
    });
    if (VOChecker.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006006_0", "04006006-0134", null, new String[] {
            strPk_ReturnReason
          })/*���˻����뵥��ָ�����˻�ԭ������{0}�����ڣ�*/);
    }
    return vos[0].getFreasontype().intValue();
  }

  /**
   * �������ƣ�Դ��Ʊ����
   */
  @Override
  public String getSaleInvoiceBillDate(ReturnAssignMatchVO paravo) {
    String strBillID = this.getHidFor32(paravo);
    if (strBillID != null) {
      return this.strtostr(this.getDateFor32(strBillID));
    }
    return this.getResBillDate(paravo);
  }

  /**
   * �������ƣ�Դ��������
   */
  @Override
  public String getSaleOrderBillDate(ReturnAssignMatchVO paravo) {
    String strBillID = this.getHidFor30(paravo);
    if (strBillID != null) {
      return this.strtostr(this.getDateFor30(strBillID));
    }
    return this.getResBillDate(paravo);
  }

  /**
   * �������ƣ�Դ���ⵥ����
   */
  @Override
  public String getSaleOutBillDate(ReturnAssignMatchVO paravo) {
    String strBillID = this.getHidFor4C(paravo);
    if (strBillID != null) {
      return this.strtostr(this.getDateFor4C(strBillID));

    }
    return this.getResBillDate(paravo);
  }

  /**
   * �������ƣ�Դ��Ʊ����
   */
  @Override
  public int getSourceInvoiceDays(ReturnAssignMatchVO paravo) {
    String sourceInvoice = this.getSaleInvoiceBillDate1(paravo);
    if (sourceInvoice == null) {
      return 0;
    }
    return new UFDate(this.getResBillDate1(paravo)).getDaysAfter(new UFDate(
        sourceInvoice));
  }

  /**
   * �������ƣ�Դ��Ʊ����
   */
  @Override
  public int getSourceOrderDays(ReturnAssignMatchVO paravo) {
    String sourceInvoice = this.getSaleOrderBillDate1(paravo);
    if (sourceInvoice == null) {
      return 0;
    }
    return new nc.vo.pub.lang.UFDate(this.getResBillDate1(paravo))
        .getDaysAfter(new nc.vo.pub.lang.UFDate(sourceInvoice));
  }

  /**
   * �������ƣ�Դ��Ʊ����
   */
  @Override
  public int getSourceOutDays(ReturnAssignMatchVO paravo) {
    String sourceInvoice = this.getSaleOutBillDate1(paravo);
    if (sourceInvoice == null) {
      return 0;
    }
    return new nc.vo.pub.lang.UFDate(this.getResBillDate1(paravo))
        .getDaysAfter(new nc.vo.pub.lang.UFDate(sourceInvoice));
  }

  /**
   * �������ƣ���Ʒ
   * ���������������Ĵ������(����ʱ�ı���)���������е�"��Ʒ"���
   * ����ֵ��"��"��"��"��
   * Ӧ��ʵ����ѡ��ҵ����"��Ʒ"��ѡ��"��"�����ַ���������"��"����ʾ����Ϊ"�����ǰ�˻����뵥�ĵ�ǰ�д������Ʒ��־Ϊ"��
   * "��һ�������ж��Ƿ���Ʒ���۵��˻أ��ڲ������۶��������˻����뵥���������Ч����
   */
  @Override
  public boolean isLargessFlag(ReturnAssignMatchVO paravo) {
    return paravo.getBlargessflag() != null
        && paravo.getBlargessflag().booleanValue();
  }

  @Override
  public boolean judge(String strConditionCode, ReturnAssignMatchVO paravo) {
    FunctionContex context = new FunctionContex(paravo);
    String pk_org = paravo.getPk_saleorg();
    StringBuffer where = new StringBuffer();
    where.append(" and " + ReturnConditionVO.PK_ORG + " ='" + pk_org + "'");
    where.append(" and " + ReturnConditionVO.VCONDITIONCODE + " = '"
        + strConditionCode + "'");
    VOQuery<ReturnConditionVO> query =
        new VOQuery<ReturnConditionVO>(ReturnConditionVO.class);
    ReturnConditionVO[] vos = query.query(where.toString(), null);
    if (VOChecker.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006006_0", "04006006-0135", null, new String[] {
            pk_org, strConditionCode
          })/*������Ч������֯{0}�У������ڱ���Ϊ{1}���˻�������*/);
    }
    try {
      String strExpress = vos[0].getVexpressname();
      ArrayValue v = RefCompilerBS.getExpressionResult(strExpress, context);
      Object objTemp = v.getValue();
      if (objTemp instanceof Boolean) {
        Boolean bln = (Boolean) objTemp;
        if (bln.booleanValue()) {
          return true;
        }
      }
      else if (objTemp instanceof String) {
        if (objTemp.equals("true")) {
          return true;
        }
        else if (objTemp.equals("false")) {
          return false;
        }
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006006_0", "04006006-0136")/*����˻���������Ӧ���˻�����û������*/);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  private String getDateFor30(String strBillID) {
    VOQuery<SaleOrderHVO> query = new VOQuery<SaleOrderHVO>(SaleOrderHVO.class);
    SaleOrderHVO[] hvos = query.query(new String[] {
      strBillID
    });
    return hvos[0].getDbilldate().toString();
  }

  private String getDateFor32(String strBillID) {
    VOQuery<SaleInvoiceHVO> query =
        new VOQuery<SaleInvoiceHVO>(SaleInvoiceHVO.class);
    SaleInvoiceHVO[] hvos = query.query(new String[] {
      strBillID
    });
    return hvos[0].getDbilldate().toString();
  }

  private String getDateFor4C(String strBillID) {
    VOQuery<SaleOutHeadVO> query =
        new VOQuery<SaleOutHeadVO>(SaleOutHeadVO.class);
    SaleOutHeadVO[] hvos = query.query(new String[] {
      strBillID
    });
    return hvos[0].getDbilldate().toString();
  }

  private String getHidFor30(ReturnAssignMatchVO paravo) {
    String strBillID = null;
    if (paravo.getVsrctype() != null && paravo.getVsrctype().equals("30")) {
      strBillID = paravo.getCsrcid();
      if (strBillID.trim().length() == 0) {
        strBillID = null;
      }
    }
    if (strBillID == null && paravo.getVfirsttype() != null
        && paravo.getVfirsttype().equals("30")) {
      strBillID = paravo.getCfirstid();
      if (strBillID.trim().length() == 0) {
        strBillID = null;
      }
    }
    return strBillID;
  }

  private String getHidFor32(ReturnAssignMatchVO paravo) {
    String strBillID = null;
    if (paravo.getVsrctype() != null
        && paravo.getVsrctype().equals(SOBillType.Invoice.getCode())) {
      strBillID = paravo.getCsrcid();
      if (strBillID.trim().length() == 0) {
        strBillID = null;
      }
    }
    if (strBillID == null && paravo.getVfirsttype() != null
        && paravo.getVfirsttype().equals(SOBillType.Invoice.getCode())) {
      strBillID = paravo.getCfirstid();
      if (strBillID.trim().length() == 0) {
        strBillID = null;
      }
    }
    return strBillID;
  }

  private String getHidFor4C(ReturnAssignMatchVO paravo) {
    String strBillID = null;
    if (paravo.getVsrctype() != null && paravo.getVsrctype().equals("4C")) {
      strBillID = paravo.getCsrcid();
      if (strBillID.trim().length() == 0) {
        strBillID = null;
      }
    }
    if (strBillID == null && paravo.getVfirsttype() != null
        && paravo.getVfirsttype().equals("4C")) {
      strBillID = paravo.getCfirstid();
      if (strBillID.trim().length() == 0) {
        strBillID = null;
      }
    }
    return strBillID;
  }

  /**
   * �������ƣ��˻����뵥���ڡ�
   */
  private String getResBillDate1(ReturnAssignMatchVO paravo) {
    return paravo.getDbilldate().toString();
  }

  /**
   * �������ƣ�Դ��Ʊ����
   */
  private String getSaleInvoiceBillDate1(ReturnAssignMatchVO paravo) {
    String strBillID = this.getHidFor32(paravo);
    if (strBillID != null) {
      return this.getDateFor32(strBillID);
    }
    return this.getResBillDate1(paravo);
  }

  /**
   * �������ƣ�Դ��������
   */
  private String getSaleOrderBillDate1(ReturnAssignMatchVO paravo) {
    String strBillID = this.getHidFor30(paravo);
    if (strBillID != null) {
      this.getDateFor30(strBillID);
    }
    return this.getResBillDate1(paravo);
  }

  /**
   * �������ƣ�Դ���ⵥ����
   */
  private String getSaleOutBillDate1(ReturnAssignMatchVO paravo) {
    String strBillID = this.getHidFor4C(paravo);
    if (strBillID != null) {
      try {

        return this.getDateFor4C(strBillID);
      }
      catch (Exception e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }

    }
    return this.getResBillDate1(paravo);
  }

  /**
   * ֻ��ȡ�����ַ�����Ϊ��ʽ�༭������ʹ��������
   * ?user>
   * ���ܣ�
   * ������
   * ���أ�
   * ���⣺
   * ���ڣ�(2004-7-14 15:34:58)
   * �޸����ڣ��޸��ˣ��޸�ԭ��ע�ͱ�־��
   * 
   * @return java.lang.String
   * @param s java.lang.String
   */
  private String strtostr(String s) {
    String s1 = s;
    StringBuffer sb = new StringBuffer();
    int index = 0;
    while ((index = s1.indexOf("-")) >= 0) {
      sb.append(s1.substring(0, index));
      s1 = s1.substring(index + 1);
    }
    sb.append(s1);
    return sb.toString().substring(0, 8);
  }
}
