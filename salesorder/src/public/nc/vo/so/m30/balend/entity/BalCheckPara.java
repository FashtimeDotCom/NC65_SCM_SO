package nc.vo.so.m30.balend.entity;

import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.balend.enumeration.BalBillType;

public class BalCheckPara {

  private BalBillType costbaltype;

  private BalBillType incomebaltype;

  private final String orderbid;

  private String orderid;

  private SaleOutBalVO outbal;

  private boolean virneedcheck;

  private InvoiceBalVO voicebal;

  /**
   * BalCheckPara �Ĺ�����
   * 
   * @param orderbid
   */
  public BalCheckPara(String orderbid) {
    this.orderbid = orderbid;
  }

  /**
   * �����������������سɱ��������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 ����02:22:46
   */
  public BalBillType getCostbaltype() {
    return this.costbaltype;
  }

  /**
   * ����������������������������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 ����02:22:22
   */
  public BalBillType getIncomebaltype() {
    return this.incomebaltype;
  }

  /**
   * �����������������سɱ�����رռ��VO��
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-16 ����01:56:33
   */
  public InvoiceBalVO getInvoicebal() {
    return this.voicebal;
  }

  /**
   * ���������������������۶����ӱ�ID��
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 ����02:21:58
   */
  public String getOrderbid() {
    return this.orderbid;
  }

  public String getOrderid() {
    return this.orderid;
  }

  /**
   * �����������������ط�Ʊ����رռ��VO��
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-16 ����01:56:15
   */
  public SaleOutBalVO getOutbal() {
    return this.outbal;
  }

  /**
   * �����������������뵥�������Ƿ�Ӱ����㣬�������н������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param billtype
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 ����02:33:43
   */
  public boolean isAffectBal(String billtype) {
    if (SOBillType.Invoice.isEqual(billtype)) {
      return this.isVoiceAffectBal();
    }
    else if (ICBillType.SaleOut.isEqual(billtype)) {
      return this.isOutAffectBal();
    }
    else if (ICBillType.WastageBill.isEqual(billtype)) {
      return this.isWasAffectBal();
    }
    else {
      return false;
    }
  }

  private boolean isWasAffectBal() {

    if ((null != this.incomebaltype && (BalBillType.OUTINCOME
        .equalsValue(this.incomebaltype)
        || BalBillType.BOTHINCOME.equalsValue(this.incomebaltype) || BalBillType.OnlyVOICEINCOME
        .equalsValue(this.incomebaltype)))

        || (null != this.costbaltype && (BalBillType.OUTCOST
            .equalsValue(this.costbaltype)
            || BalBillType.OUTCOST.equalsValue(this.costbaltype) || BalBillType.OnlyVOICECOST
            .equalsValue(this.costbaltype)))) {
      return true;
    }

    return false;

  }

  /**
   * �����������������뵥�������Ƿ�Ӱ��ɱ����㡣
   * <p>
   * <b>����˵��</b>
   * 
   * @param billtype
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 ����02:35:42
   */
  public boolean isAffectCostBal(String billtype) {
    if (SOBillType.Invoice.isEqual(billtype)) {
      return this.isVoiceAffectCostBal();
    }
    else if (ICBillType.SaleOut.isEqual(billtype)) {
      return this.isOutAffectCostBal();
    }
    else if (ICBillType.WastageBill.isEqual(billtype)) {
      return this.isWasAffectCostBal();
    }
    else {
      return false;
    }
  }

  private boolean isWasAffectCostBal() {

    if (null != this.costbaltype
        && (this.virneedcheck
            || BalBillType.BOTHCOST.equalsValue(this.costbaltype)
            || BalBillType.OUTCOST.equalsValue(this.costbaltype) || BalBillType.OnlyVOICECOST
            .equalsValue(this.costbaltype))) {
      return true;
    }

    return false;

  }

  /**
   * �����������������뵥�������Ƿ�Ӱ��������㡣
   * <p>
   * <b>����˵��</b>
   * 
   * @param billtype
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 ����02:35:20
   */
  public boolean isAffectIncomeBal(String billtype) {
    if (SOBillType.Invoice.isEqual(billtype)) {
      return this.isVoiceAffectIncomeBal();
    }
    else if (ICBillType.SaleOut.isEqual(billtype)) {
      return this.isOutAffectIncomeBal();
    }
    else if (ICBillType.WastageBill.isEqual(billtype)) {
      return this.isWasAffectIncomeBal();
    }
    else {
      return false;
    }
  }

  private boolean isWasAffectIncomeBal() {
    if (null != this.incomebaltype
        && (this.virneedcheck
            || BalBillType.BOTHINCOME.equalsValue(this.incomebaltype) || BalBillType.OUTINCOME
            .equalsValue(this.incomebaltype))
        || BalBillType.OnlyVOICEINCOME.equalsValue(this.incomebaltype)) {
      return true;
    }

    return false;

  }

  /**
   * �����������������óɱ��������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param costbaltype
   *          <p>
   * @author fengjb
   * @time 2010-7-16 ����01:55:40
   */
  public void setCostbaltype(BalBillType costbaltype) {
    this.costbaltype = costbaltype;
  }

  /**
   * ����������������������������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param incomebaltype
   *          <p>
   * @author fengjb
   * @time 2010-7-16 ����01:55:21
   */
  public void setIncomebaltype(BalBillType incomebaltype) {
    this.incomebaltype = incomebaltype;
  }

  /**
   * �����������������ý�����VO��
   * <p>
   * <b>����˵��</b>
   * 
   * @param balvo
   *          <p>
   * @author fengjb
   * @time 2010-7-16 ����01:55:53
   */
  public void setInvoicebal(InvoiceBalVO voicebal1) {
    this.voicebal = voicebal1;
  }

  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }

  public void setOutbal(SaleOutBalVO outbal) {
    this.outbal = outbal;
  }

  public void setVirtualCheck(boolean virneedcheck1) {
    this.virneedcheck = virneedcheck1;
  }

  /**
   * �����������������ⵥ�Ƿ�Ӱ����㡣
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 ����02:34:37
   */
  private boolean isOutAffectBal() {
    if ((null != this.incomebaltype && (BalBillType.OUTINCOME
        .equalsValue(this.incomebaltype) || BalBillType.BOTHINCOME
        .equalsValue(this.incomebaltype)))

        || (null != this.costbaltype && (BalBillType.OUTCOST
            .equalsValue(this.costbaltype) || BalBillType.OUTCOST
            .equalsValue(this.costbaltype)))) {
      return true;
    }

    return false;

  }

  /**
   * �����������������ⵥ�Ƿ�Ӱ��ɱ����㡣
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 ����02:36:17
   */
  private boolean isOutAffectCostBal() {
    if (null != this.costbaltype
        && (this.virneedcheck
            || BalBillType.BOTHCOST.equalsValue(this.costbaltype) || BalBillType.OUTCOST
            .equalsValue(this.costbaltype))) {
      return true;
    }

    return false;

  }

  /**
   * �����������������ⵥ�Ƿ�Ӱ��������㡣
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 ����02:36:44
   */
  private boolean isOutAffectIncomeBal() {
    if (null != this.incomebaltype
        && (this.virneedcheck
            || BalBillType.BOTHINCOME.equalsValue(this.incomebaltype) || BalBillType.OUTINCOME
            .equalsValue(this.incomebaltype))) {
      return true;
    }

    return false;

  }

  /**
   * ����������������Ʊ�Ƿ�Ӱ����㡣
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 ����02:34:22
   */
  private boolean isVoiceAffectBal() {
    if ((null != this.incomebaltype && (BalBillType.VOICEINCOME
        .equalsValue(this.incomebaltype)
        || BalBillType.BOTHINCOME.equalsValue(this.incomebaltype) || BalBillType.OnlyVOICEINCOME
        .equalsValue(this.incomebaltype)))

        || (null != this.costbaltype && (BalBillType.VOICECOST
            .equalsValue(this.costbaltype)
            || BalBillType.BOTHCOST.equalsValue(this.costbaltype) || BalBillType.OnlyVOICECOST
            .equalsValue(this.costbaltype)))) {
      return true;
    }
    return false;

  }

  /**
   * ����������������Ʊ�Ƿ�Ӱ��ɱ����㡣
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 ����02:36:00
   */
  private boolean isVoiceAffectCostBal() {
    if (null != this.costbaltype
        && (BalBillType.BOTHCOST.equalsValue(this.costbaltype)
            || BalBillType.VOICECOST.equalsValue(this.costbaltype) || BalBillType.OnlyVOICECOST
            .equalsValue(this.costbaltype))) {
      return true;
    }

    return false;

  }

  /**
   * ����������������Ʊ�Ƿ�Ӱ��������㡣
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 ����02:36:32
   */
  private boolean isVoiceAffectIncomeBal() {
    if (null != this.incomebaltype
        && (BalBillType.BOTHINCOME.equalsValue(this.incomebaltype)
            || BalBillType.VOICEINCOME.equalsValue(this.incomebaltype) || BalBillType.OnlyVOICEINCOME
            .equalsValue(this.incomebaltype))) {
      return true;
    }

    return false;

  }
}
