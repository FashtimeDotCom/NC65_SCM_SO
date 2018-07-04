package nc.bs.so.m30.state;

import java.util.HashMap;
import java.util.Map;

import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.util.biz.SOBusiMDEnum;
import nc.vo.so.pub.util.biz.SOBusiPara;
import nc.vo.so.pub.util.biz.SOBusiUtil;

import nc.itf.so.m30trantype.IM30TranTypeService;

import nc.pubitf.so.m30.ic.m4453.Rewrite4453Para;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.env.BSContext;

import nc.pubimpl.so.m30.ic.m4453.Rewrite30For4453Impl;
import nc.pubimpl.so.m30.ic.m4c.Rewrite30For4CImpl;

public class StateCalculateUtil {

  private Map<SOBusiPara, SOBusiMDEnum> busiTypeMap;

  private SOBusiUtil busiUtil;

  private Map<String, Rewrite32Para> m32Paras;

  private Map<String, Rewrite33Para> m33Paras;

  private Map<String, Rewrite4331Para> m4331Paras;

  private Map<String, Rewrite4453Para> m4453Paras;

  private Map<String, Rewrite4CPara> m4CParas;

  private Map<String, M30TranTypeVO> tranTypeMap;

  private IM30TranTypeService tranTypeService;

  /**
   * �йر�״̬ԾǨ�������ر�״̬����
   */
  public boolean isAutoTransitArSettleClose(SaleOrderViewVO view) {
    // �йرջᵼ���������ر�
    return BillStatus.CLOSED.equalsValue(view.getBody().getFrowstatus());
  }

  /**
   * �д�״̬ԾǨ��������״̬����
   */
  public boolean isAutoTransitArSettleOpen(SaleOrderViewVO view) {
    SaleOrderBVO bvo = view.getBody();
    boolean baudit =
        BillStatus.AUDIT.equalsValue(view.getBody().getFrowstatus());
    boolean boutopen = !ValueUtils.getBoolean(bvo.getBboutendflag());
    boolean binvoiceopen = !ValueUtils.getBoolean(bvo.getBbinvoicendflag());

    // �д򿪻ᵼ�³ɱ���
    return baudit || boutopen || binvoiceopen;
  }

  /**
   * ������״̬�ж��Ƿ���������رա�
   * <ul>
   * <li>�����йرգ��������ر�(��ͷת�ر�״̬)
   * </ul>
   */
  public boolean isAutoTransitBillClose(SaleOrderVO view) {
    SaleOrderBVO[] items = view.getChildrenVO();
    for (SaleOrderBVO item : items) {
      Integer frowstatus = item.getFrowstatus();
      if (!BillStatus.CLOSED.equalsValue(frowstatus)) {
        return false;
      }
    }
    return true;
  }

  /**
   * ������״̬�ж��Ƿ���������򿪡�
   * <ul>
   * <li>��һ�д򿪣���������(��ͷת����״̬)
   * </ul>
   */
  public boolean isAutoTransitBillOpen(SaleOrderVO view) {
    return !this.isAutoTransitBillClose(view);
  }

  /**
   * �йر�״̬ԾǨ�ɱ�����ر�״̬����
   */
  public boolean isAutoTransitCostSettleClose(SaleOrderViewVO view) {
    // �йرջᵼ�³ɱ�����ر�
    return BillStatus.CLOSED.equalsValue(view.getBody().getFrowstatus());
  }

  /**
   * �д�״̬ԾǨ�ɱ������״̬����
   */
  public boolean isAutoTransitCostSettleOpen(SaleOrderViewVO view) {
    SaleOrderBVO bvo = view.getBody();
    boolean baudit =
        BillStatus.AUDIT.equalsValue(view.getBody().getFrowstatus());
    boolean boutopen = !ValueUtils.getBoolean(bvo.getBboutendflag());
    boolean binvoiceopen = !ValueUtils.getBoolean(bvo.getBbinvoicendflag());

    // �д򿪻ᵼ�³ɱ���
    return baudit || boutopen || binvoiceopen;
  }

  /**
   * �йر�״̬ԾǨ��Ʊ�ر�״̬����
   */
  public boolean isAutoTransitInvoiceClose(SaleOrderViewVO view) {
    // �йرջᵼ�³���ر�
    return BillStatus.CLOSED.equalsValue(view.getBody().getFrowstatus());
  }

  /**
   * �д�״̬ԾǨ��Ʊ��״̬����
   */
  public boolean isAutoTransitInvoiceOpen(SaleOrderViewVO view) {
    SaleOrderHVO head = view.getHead();
    SOBusiMDEnum busiType =
        this.getBusiTypeByID(head.getVtrantypecode(), head.getCbiztypeid());
    boolean larflag = this.isLargessCanInvoice(view);
    // ��Ʒ�и��ݲ����жϿ�Ʊ��
    if(larflag){
      return false;
    }
    // --�Ȼ���Ʊ���������򿪣���Ʊ��Ҫ��,
    if (SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)) {
      if (!ValueUtils.getBoolean(view.getBody().getBboutendflag())) {
        return true;
      }

      // ��Rewrite30For4453Impl.addRule����Session˵��
      Object flagRewrite30For4453 =
          BSContext.getInstance().getSession(
              Rewrite30For4453Impl.class.getName());
      // ��Rewrite30For4453Impl.addRule����Session˵��
      Object flagRewrite30For4C =
          BSContext.getInstance()
              .getSession(Rewrite30For4CImpl.class.getName());
      // ���ֹ��д򿪲���
      if (!SOVOChecker.isEmpty(flagRewrite30For4453)
          || !SOVOChecker.isEmpty(flagRewrite30For4C)) {
        return false;
      }

    }
    // ��Ʊ���С���Ʊ������ܴ�
    else if (SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL.equals(busiType)
        || SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST.equals(busiType)) {
      return false;
    }
    // �д򿪻ᵼ�³����
    return BillStatus.AUDIT.equalsValue(view.getBody().getFrowstatus());
  }

  /**
   * �йر�״̬ԾǨ����ر�״̬����
   */
  public boolean isAutoTransitOutClose(SaleOrderViewVO view) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    boolean flag = false;
    // �йرջᵼ�³���ر�
    flag = BillStatus.CLOSED.equalsValue(body.getFrowstatus());
    if (flag) {
      return flag;
    }
    // �Ȼ���Ʊ����Ʊ�رջᵼ�³���رգ���Ʒ����
    boolean larflag = ValueUtils.getBoolean(body.getBlargessflag());
    SOBusiMDEnum busiType =
        this.getBusiTypeByID(head.getVtrantypecode(), head.getCbiztypeid());
    if (!larflag && SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)
        && body.getBbinvoicendflag().booleanValue()) {
      flag = true;
    }
    return flag;
  }

  /**
   * �д�״̬ԾǨ�����״̬����
   */
  public boolean isAutoTransitOutOpen(SaleOrderViewVO view) {
    SaleOrderBVO body = view.getBody();
    boolean flag = false;
    // �����򿪣�������������
    flag = !body.getBbsendendflag().booleanValue();
    if (flag) {
      return flag;
    }
    // �д򿪻ᵼ�³����
    return BillStatus.AUDIT.equalsValue(view.getBody().getFrowstatus());
  }

  /**
   * ����״̬�����������⡢��Ʊ������״̬�ر�ԾǨ�йرյ���
   */
  public boolean isAutoTransitRowClose(SaleOrderViewVO view) {
    // �����رգ����йر�
    if (BillStatus.CLOSED.equalsValue(view.getHead().getFstatusflag())) {
      return true;
    }
    return this.isAutoTransitRowCloseByOtherState(view);
  }

  /**
   * ����״̬�����������⡢��Ʊ������״̬��ԾǨ�д򿪵���
   */
  public boolean isAutoTransitRowOpen(SaleOrderViewVO view) {
    // �����򿪣����д�
    if (BillStatus.AUDIT.equalsValue(view.getHead().getFstatusflag())) {
      return true;
    }
    return !this.isAutoTransitRowCloseByOtherState(view);
  }

  /**
   * �йر�״̬&&����ر�״̬ԾǨ�����ر�״̬����
   */
  public boolean isAutoTransitSendClose(SaleOrderViewVO view) {
    // �йرջᵼ�·����ر�
    if (BillStatus.CLOSED.equalsValue(view.getBody().getFrowstatus())) {
      return true;
    }

    // ����رջᵼ�·����ر�
    return view.getBody().getBboutendflag() == null ? false : view.getBody()
        .getBboutendflag().booleanValue();
  }

  /**
   * �д�״̬ԾǨ������״̬����
   */
  public boolean isAutoTransitSendOpen(SaleOrderViewVO view) {
    // �д򿪻ᵼ�·�����
    return BillStatus.AUDIT.equalsValue(view.getBody().getFrowstatus());
  }

  /**
   * �Ƿ���Կ�Ʊ�ر�
   * <ul>
   * <li>���ݹ�ʽ�����ۼƿ�Ʊ����+�ۼƳ���Գ����� >= �����������ж�
   * </ul>
   * 
   * @param views
   * @return �ܷ�ر�
   * @author ��־ΰ
   */
  public boolean isInvoiceClose(SaleOrderViewVO view) {
    SaleOrderBVO body = view.getBody();
    Rewrite32Para para = this.getRewrite32Paras().get(body.getCsaleorderbid());
    UFDouble curchangenum = para.getNchangenum();
    return this.isInvoiceClose(view, curchangenum);
  }

  /**
   * �����޶��Ƿ�Ʊ�ر�
   * 
   * @param view
   * @return
   */
  public boolean isReviseInvoiceClose(SaleOrderViewVO view,
      SaleOrderViewVO originView) {
    // ����û�иı�
    if (!isNumChange(view, originView)) {
      return false;
    }
    return this.isInvoiceClose(view, null);
  }

  /**
   * �����޶��Ƿ�Ʊ��
   * 
   * @param view
   * @return
   */
  public boolean isReviseInvoiceOpen(SaleOrderViewVO view,
      SaleOrderViewVO originView) {
    // ����û�иı�
    if (!isNumChange(view, originView)) {
      return false;
    }
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    // 1.�ǹرյ��ݲ��ܴ�
    if (!body.getBbinvoicendflag().booleanValue()) {
      return false;
    }
    // 2.���ݹ�ʽ�ж��Ƿ��ܹ���Ʊ��
    UFDouble totalinvnum = body.getNtotalinvoicenum();
    UFDouble totalrushnum = body.getNtotalrushnum();
    UFDouble totaloutnum = body.getNtotaloutnum();
    UFDouble translossnum = body.getNtranslossnum();
    UFDouble num = body.getNnum();

    SOBusiMDEnum busiType =
        this.getBusiTypeByID(head.getVtrantypecode(), head.getCbiztypeid());
    // TODO �޶��޷��ж��Զ��رջ����ֹ�
    /*  // ���۶���֮ǰ�Ƿ��Զ���Ʊ�ر�
      boolean bautoClose = this.isAutoInvoiceColse(view, null, busiType);*/

    boolean ret = false;
    boolean laborflag = body.getBlaborflag() == null ? false
        : body.getBlaborflag().booleanValue();
    boolean discountflag = body.getBdiscountflag() == null ? false
        : body.getBdiscountflag().booleanValue();
    // --�����ۿ���:�ۼƿ�Ʊ���� >= ��������
    if (laborflag || discountflag) {
      // �ۿ�������¼�������������಻��������Ϊ�գ������ۿ�������Ϊ�յ��������ʵֻ����ת��һ��
      if (discountflag && MathTool.isZero(num)) {
        ret = true;
      }
      else {
        ret = MathTool.absCompareTo(totalinvnum, num) < 0;
      }
    }
    // --�Ȼ���Ʊ���ۼƿ�Ʊ���� + �ۼƳ���Գ����� != �ۼƳ������� - �ۼ�;������
    else if (SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)) {
      // �������δ�رգ���Ʊ���Զ��ر�
      if (!ValueUtils.getBoolean(view.getBody().getBboutendflag())) {
        return false;
      }
      ret =
          /*bautoClose
              &&*/MathTool.absCompareTo(MathTool.add(totalinvnum, totalrushnum),
              MathTool.sub(totaloutnum, translossnum)) != 0;
    }
    // --��Ʊ��� || Ʊ�����У��ۼƿ�Ʊ���� < ��������
    else if (SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST.equals(busiType)
        || SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL.equals(busiType)) {
      ret = /*bautoClose &&*/MathTool.absCompareTo(totalinvnum, num) < 0;
    }
    return ret;
  }

  /**
   * �Ƿ���Կ�Ʊ�ر�4;��
   * <ul>
   * <li>���ݹ�ʽ�����ۼƿ�Ʊ����+�ۼƳ���Գ����� >= �����������ж�
   * </ul>
   * 
   * @param views
   * @return �ܷ�ر�
   * @author ��־ΰ
   */
  public boolean isInvoiceCloseFor4453(SaleOrderViewVO view) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();

    // .���ݹ�ʽ�߼��ж��Ƿ�Ʊ�ر�
    UFDouble totalinvnum = body.getNtotalinvoicenum();
    UFDouble totalrushnum = body.getNtotalrushnum();
    UFDouble totaloutnum = body.getNtotaloutnum();
    UFDouble translossnum = body.getNtranslossnum();
    boolean ret = false;
    SOBusiMDEnum busiType =
        this.getBusiTypeByID(head.getVtrantypecode(), head.getCbiztypeid());
    // --�Ȼ���Ʊ���ۼƿ�Ʊ���� + �ۼƳ���Գ����� == �ۼƳ������� - �ۼ�;������ ���ҳ���ر�
    if (SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)) {
      UFBoolean boutend = ValueUtils.getUFBoolean(body.getBboutendflag());
      ret =
          boutend.booleanValue()
              && MathTool.compareTo(MathTool.add(totalinvnum, totalrushnum),
                  MathTool.sub(totaloutnum, translossnum)) == 0;
    }
    return ret;
  }

  public boolean isInvoiceCloseForOutRush(SaleOrderViewVO view) {
    Rewrite33Para para =
        this.getRewrite33Paras().get(view.getBody().getCsaleorderbid());
    UFDouble curchangenum = para.getNarnum();
    return this.isInvoiceClose(view, curchangenum);
  }

  /**
   * �Ƿ���Կ�Ʊ��
   * <p>
   * �Զ��ر� && (��ǰ�ۼƿ�Ʊ���� < ������) ��Ʊ�����Զ���
   * 
   * @param views SaleOrderViewVO
   * @return boolean �ܷ��
   * @author ��־ΰ
   */
  public boolean isInvoiceOpen(SaleOrderViewVO view) {
    Rewrite32Para para =
        this.getRewrite32Paras().get(view.getBody().getCsaleorderbid());
    UFDouble curchangenum = para.getNchangenum();
    return this.isInvoiceOpen(view, curchangenum);
  }

  /**
   * �Ƿ���Կ�Ʊ��4;��
   * 
   * @param views SaleOrderViewVO
   * @return boolean �ܷ��
   * @author ��־ΰ
   */
  public boolean isInvoiceOpenFor4453(SaleOrderViewVO view) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    Rewrite4453Para para =
        this.getRewrite4453Paras().get(body.getCsaleorderbid());

    // .���ݹ�ʽ�ж��Ƿ��ܹ���Ʊ��
    UFDouble translossnum = para.getNtranslossnum();
    boolean ret = false;
    SOBusiMDEnum busiType =
        this.getBusiTypeByID(head.getVtrantypecode(), head.getCbiztypeid());

    boolean larflag = this.isLargessCanInvoice(view);
    // --�Ȼ���Ʊ����Ʒ����Ʊ����
    if (!larflag && SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)) {
      // ;��Ӱ�췢Ʊ���߼�,�򻯹�ʽֻҪ��;�������ʹ�Ʊ,ϸ�����£�
      // A.isLossReNew=false����ر�ʱ����Ʊ��Ҫ��������Ʊ
      // B.isLossReNew=true�����ʱ����Ʊ��Ҫ������Ʊ
      if (!MathTool.isZero(translossnum)) {
        ret = true;
      }
    }
    return ret;
  }

  public boolean isInvoiceOpenForOutRush(SaleOrderViewVO view) {
    Rewrite33Para para =
        this.getRewrite33Paras().get(view.getBody().getCsaleorderbid());
    UFDouble curchangenum = para.getNarnum();
    return this.isInvoiceOpen(view, curchangenum);
  }

  /**
   * �������Ƿ������ֹ�����򿪵�����
   * 
   * @param view
   * @return
   */
  public boolean isManualOutOpen(SaleOrderViewVO view) {
    return this.isManualOutSendOpen(view);
  }

  /**
   * �������Ƿ������ֹ������򿪵�����
   * 
   * @param view
   * @return
   */
  public boolean isManualSendOpen(SaleOrderViewVO view) {
    return this.isManualOutSendOpen(view);
  }

  /**
   * �Ƿ���Գ���ر�
   * <ul>
   * <li>���ݳ��ⵥ�رձ���ж�
   * <li>���ݽ�������һ�γ���رձ��+�����ж�
   * <li>��ʽ�жϣ���|�ۼƳ�������+�ۼ�Ӧ��δ��������-is;�𲹻�(�ۼ�;������,0)| >= |��������* (1- ����ر����� /100)|��
   * </ul>
   * <p>
   * ����:�����ǰ��д�����������Ѿ��ֹ��ر��ҵ�ǰ�ۼ�����>=������ʱ������Ϊ�ֹ��ر�״̬�Զ�����Ϊ�Զ��ر�״̬(�˲���д�������)��
   * �����´λ�д����ʱ��Ҫ�Զ��򿪵ġ�
   * 
   * @param views
   * @return �ܷ�ر�
   * @author ��־ΰ
   */
  public boolean isOutClose(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    Rewrite4CPara para = this.getRewrite4CParas().get(body.getCsaleorderbid());
    // ���ݻ�д�����ж��Ƿ���Ҫ�رմ���
    if (!this.isNeedCloseByChangeNum(body, para.getNchangenum())) {
      return false;
    }

    // 1.���۳��ⵥ����رձ��ǿ�ƹر����α��
    if (para.getBclosed().booleanValue()) {
      return true;
    }

    // 2.һ���Գ���رգ�(|Ӧ������| > 0) �͹ر�
    boolean isOnceOutClose = this.getIsOnceOutClose(head.getCtrantypeid());
    if (isOnceOutClose) {
      if (MathTool.absCompareTo(body.getNtotaloutnum(),
          UFDouble.ZERO_DBL) > 0) {
        return true;
      }
    }

    /*
     * 3. ;�𲻲���:|�ۼƳ�������+�ۼ�Ӧ��δ��������| >= |������|,�����ر� ;�𲹻�: |�ۼƳ�������|
     * =0,ֻ��|�ۼ�Ӧ��δ��������|���ܶ���,���ⶼ���ر� ;�𲹻�:
     * |�ۼƳ�������+�ۼ�Ӧ��δ��������|-|;������|>=|��������|*��1-����ر�����%��,�����ر�
     */
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    boolean isOutClose = false;
    UFDouble nnum = this.getOrderOutCloseNum(view, materrialmaps);
    UFDouble ntotaloutnum = body.getNtotaloutnum();

    if (MathTool.absCompareTo(ntotaloutnum, UFDouble.ZERO_DBL) == 0) {
      return false;
    }
    // ;�𲻲���
    if (!isLossReNew) {
      isOutClose = MathTool.absCompareTo(body.getNtotaloutnum(), nnum) >= 0;
    }
    // ;�𲹻�
    else {
      UFDouble ntotallossnum = body.getNtranslossnum();
      isOutClose =
          MathTool
              .absCompareTo(MathTool.sub(ntotaloutnum, ntotallossnum), nnum) >= 0;
    }

    return isOutClose;
  }

  /**
   * �Ƿ���Գ���ر�4;��
   * 
   * @param views
   * @return �ܷ�ر�
   * @author ��־ΰ
   */
  public boolean isOutCloseFor4453(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    // Rewrite4453Para para =
    // this.getRewrite4453Paras().get(body.getCsaleorderbid());

    // |�ۼƳ������� + �ۼ�Ӧ��δ�������� - is;�𲹻�(�ۼ�;������,0)| >= |��������* (1- ����ر�����/100)|;
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
    UFDouble range = UFDouble.ONE_DBL.sub(closeLowerLimit.multiply(0.01));
    UFDouble nnum = body.getNnum().multiply(range);
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    UFDouble factOutNum =
        MathTool.sub(
            MathTool.add(body.getNtotaloutnum(), body.getNtotalnotoutnum()),
            isLossReNew ? body.getNtranslossnum() : UFDouble.ZERO_DBL);
    return MathTool.absCompareTo(factOutNum, nnum) >= 0;
  }

  /**
   * �Ƿ���Գ����
   * <p>
   * �Զ��ر� && (|��ǰ�ۼƳ������� + �ۼ�Ӧ��δ�������� - is;�𲹻�(�ۼ�;������,0)| < |������|)
   * 
   * @param views SaleOrderViewVO
   * @return boolean �ܷ��
   * @author ��־ΰ
   */
  public boolean isOutOpen(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    Rewrite4CPara para = this.getRewrite4CParas().get(body.getCsaleorderbid());
    // �ǹرյ��ݲ��ܴ�
    if (!body.getBboutendflag().booleanValue()) {
      return false;
    }
    // ���ݻ�д�����ж��Ƿ���Ҫ�򿪴����ۼƳ��⡢�ۼ�Ӧ��
    if (!this.isNeedOpenByChangeNum(body, para.getNchangenum())) {
      if (!this.isNeedOpenByChangeNum(body, para.getNchangenotoutnum())) {
        return false;
      }
    }
    // 1.һ���Գ���رգ�(|Ӧ������| == 0 && |ʵ������| == 0) ��;ֻҪ������һ���ǹرյ�
    boolean isOnceOutClose = this.getIsOnceOutClose(head.getCtrantypeid());
    if (isOnceOutClose) {
      UFDouble outnum = body.getNtotaloutnum();
      return MathTool.absCompareTo(outnum, UFDouble.ZERO_DBL) == 0? true
          : false;
    }

    // ;���Ƿ񲹻�
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
    // ���۶���֮ǰ�Ƿ��Զ�����ر�
    /*
     * 2013��5��21�պͳ¶������ۣ����۶�������ر�Ӱ�췢��������رգ������������Ӱ�����۶�������򿪡� boolean bautoClose
     * = this.isAutoOutColse(view, para.getNchangenum(),
     * para.getNchangenotoutnum(), isLossReNew, closeLowerLimit);
     */

    // ��ǰ�ۼƳ������� + �ۼ�Ӧ��δ�������� - is;�𲹻�(�ۼ�;������,0)<0
    UFDouble nowFactOutNum =
        MathTool.sub(body.getNtotaloutnum(),
            isLossReNew ? body.getNtranslossnum() : UFDouble.ZERO_DBL);
    UFDouble nnum = this.getOrderOutCloseNum(view, closeLowerLimit);
    // ȥ�� bautoClose�Զ�����ر�
    return MathTool.absCompareTo(nowFactOutNum, nnum) < 0;

  }

  /**
   * �Ƿ���Գ����4;��
   * <p>
   * �Զ��ر� && (|��ǰ�ۼƳ������� + �ۼ�Ӧ��δ�������� - is;�𲹻�(�ۼ�;������,0)| < |������|) ��������Զ���
   * 
   * @param views SaleOrderViewVO
   * @return boolean �ܷ��
   * @author ��־ΰ
   */
  public boolean isOutOpenFor4453(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    Rewrite4453Para para =
        this.getRewrite4453Paras().get(body.getCsaleorderbid());
    // �ǹرյ��ݲ��ܴ�
    if (!body.getBboutendflag().booleanValue()) {
      return false;
    }

    // .��ʽ�ж��Ƿ��
    // ԭ�ۼ�;������ = ��ǰ�ۼ�;������ - ��ǰ;������
    UFDouble origTranslossnum =
        MathTool.sub(body.getNtranslossnum(), para.getNtranslossnum());
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    // �ۼƳ������� + �ۼ�Ӧ��δ�������� - is;�𲹻�(ԭ�ۼ�;������,0)
    UFDouble origFactOutNum =
        MathTool.sub(
            MathTool.add(body.getNtotaloutnum(), body.getNtotalnotoutnum()),
            isLossReNew ? origTranslossnum : UFDouble.ZERO_DBL);
    // ������������
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
    UFDouble range = UFDouble.ONE_DBL.sub(closeLowerLimit.multiply(0.01));
    UFDouble lowerLimitNum = body.getNnum().multiply(range);
    // �Ƿ��Զ��ر�
    boolean bautoClose =
        MathTool.absCompareTo(origFactOutNum, lowerLimitNum) >= 0 ? true
            : false;
    // ʵ�ʵ�ǰ�ۼƳ������� + �ۼ�Ӧ��δ�������� - is;�𲹻�(�ۼ�;������,0)
    UFDouble nowFactOutNum =
        MathTool.sub(
            MathTool.add(body.getNtotaloutnum(), body.getNtotalnotoutnum()),
            isLossReNew ? body.getNtranslossnum() : UFDouble.ZERO_DBL);
    // ��ǰ�ۼƳ������� + �ۼ�Ӧ��δ��������
    UFDouble nowTotalOutAndNotNum =
        MathTool.add(body.getNtotaloutnum(), body.getNtotalnotoutnum());
    // �Զ��ر� &&(|��ǰ�ۼƳ�������+�ۼ�Ӧ��δ��������-is;�𲹻�(�ۼ�;������,0)|<|��������*(1- ����ر����� /100)|)
    // ���� (��ǰ�ۼƳ������� + �ۼ�Ӧ��δ�������� = 0,��Ϊ���γ��ⵥȫɾ��) ��������Զ���
    return bautoClose
        && MathTool.absCompareTo(nowFactOutNum, lowerLimitNum) < 0
        || MathTool.compareTo(nowTotalOutAndNotNum, UFDouble.ZERO_DBL) == 0;
  }

  /**
   * �Ƿ���Գ����4�޶�����
   * <p>
   * �Զ��ر� &&(|������|>|ԭ������|)
   * 
   * @param views SaleOrderViewVO
   * @return boolean �ܷ��
   * @author ��־ΰ
   */
  public boolean isOutOpenForRevise(SaleOrderViewVO view,
      SaleOrderViewVO originView, Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();

    // ����û�иı�
    if (!isNumChange(view, originView)) {
      return false;
    }

    // �ǹرյ��ݲ��ܴ�
    if (!body.getBboutendflag().booleanValue()) {
      return false;
    }

    // �����ۿ۳��ⲻ�ܴ�
    if (!isManualOutSendOpen(view)) {
      return false;
    }
    // 1.һ���Գ���رգ�(|Ӧ������| == 0 && |ʵ������| == 0) ��;ֻҪ������һ���ǹرյ�
    boolean isOnceOutClose = this.getIsOnceOutClose(head.getCtrantypeid());
    if (isOnceOutClose) {
      UFDouble outnum = body.getNtotaloutnum();
      return MathTool.absCompareTo(outnum, UFDouble.ZERO_DBL) == 0 ? true
          : false;
    }

    // ;���Ƿ񲹻�
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
    // ���۶���֮ǰ�Ƿ��Զ������ر�
    boolean bautoClose =
        this.isAutoOutColse(view, UFDouble.ZERO_DBL, UFDouble.ZERO_DBL,
            isLossReNew, closeLowerLimit);

    /*    // ��ǰ�ۼƳ������� + �ۼ�Ӧ��δ�������� - is;�𲹻�(�ۼ�;������,0)<0
        UFDouble nnum = view.getBody().getNnum();
        UFDouble orignNnum = originView.getBody().getNnum();*/
    return !bautoClose /*&& MathTool.absCompareTo(nnum, orignNnum) > 0*/;

  }

  /**
   * �Ƿ���Գ���ر�4�޶�����
   * <p>
   * �Զ��ر� &&(|������|>|ԭ������|)
   * 
   * @param views SaleOrderViewVO
   * @return boolean �ܷ��
   * @author ��־ΰ
   */
  public boolean isOutCloseForRevise(SaleOrderViewVO view,
      SaleOrderViewVO originView, Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();

    // ����û�иı�
    if (!isNumChange(view, originView)) {
      return false;
    }

    // �ǹرյ��ݲ��ܴ�
    if (body.getBboutendflag().booleanValue()) {
      return false;
    }

    // 1.һ���Գ���رգ�(|Ӧ������| == 0 && |ʵ������| == 0) ��;ֻҪ������һ���ǹرյ�
    boolean isOnceOutClose = this.getIsOnceOutClose(head.getCtrantypeid());
    if (isOnceOutClose) {
      UFDouble outnum = body.getNtotaloutnum();
      return MathTool.absCompareTo(outnum, UFDouble.ZERO_DBL) == 0 ? false
          : true;
    }

    // ;���Ƿ񲹻�
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
    // ���۶���֮ǰ�Ƿ��Զ������ر�
    boolean bautoClose =
        this.isAutoOutColse(view, UFDouble.ZERO_DBL, UFDouble.ZERO_DBL,
            isLossReNew, closeLowerLimit);

    /*  // ��ǰ�ۼƳ������� + �ۼ�Ӧ��δ�������� - is;�𲹻�(�ۼ�;������,0)<0
      UFDouble nnum = view.getBody().getNnum();
      UFDouble orignNnum = originView.getBody().getNnum();*/
    return bautoClose/* && MathTool.absCompareTo(nnum, orignNnum) < 0*/;

  }

  /**
   * �Ƿ���Է����ر�
   * <ul>
   * <li>���ݷ������رձ���ж�
   * <li>���ݽ�������һ�η����رձ��+�����ж�
   * <li>���ݹ�ʽ����|�ۼƷ�������| >= |������|���ж�
   * </ul>
   * <p>
   * ����:�����ǰ��д�����������Ѿ��ֹ��ر��ҵ�ǰ�ۼ�����>=������ʱ������Ϊ�ֹ��ر�״̬�Զ�����Ϊ�Զ��ر�״̬(�˲���д�������)��
   * �����´λ�д����ʱ��Ҫ�Զ��򿪵ġ�
   * 
   * @param views
   * @return �ܷ�ر�
   * @author ��־ΰ
   */
  public boolean isSendClose(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    Rewrite4331Para para =
        this.getRewrite4331Paras().get(body.getCsaleorderbid());
    // ���ݻ�д�����ж��Ƿ���Ҫ�رմ���
    if (!this.isNeedCloseByChangeNum(body, para.getNchangenum())) {
      return false;
    }

    // 1.������ǿ�ƹر����α��
    if (para.getBclosed().booleanValue()) {
      return true;
    }

    // 2.һ���Է����ر� && |����|> 0 �͹ر�
    boolean isOnceSendClose = this.getIsOnceSendClose(head.getCtrantypeid());
    if (isOnceSendClose
        && MathTool.absCompareTo(body.getNtotalsendnum(), UFDouble.ZERO_DBL) > 0) {
      return true;
    }

    /*
     * 3. ;�𲻲���:|�ۼƷ�������| >= |������|*��1-����ر�����%��,�򷢻��ر� ;�𲹻�:
     * |��������|-|;������|>=|��������|*��1-����ر�����%��,�򷢻��ر�
     */
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    boolean isSendClose = false;
    UFDouble nnum = this.getOrderOutCloseNum(view, materrialmaps);
    if (!isLossReNew) {
      isSendClose = MathTool.absCompareTo(body.getNtotalsendnum(), nnum) >= 0;
    }
    else {
      UFDouble ntotalsendnum = body.getNtotalsendnum();
      UFDouble ntotallossnum = body.getNtranslossnum();
      isSendClose =
          MathTool.absCompareTo(MathTool.sub(ntotalsendnum, ntotallossnum),
              nnum) >= 0;
    }
    return isSendClose;
  }

  /**
   * �Ƿ���Է�����
   * <p>
   * �Զ��ر� && (��ǰ�ۼƷ������� < ������) ���������Զ���
   * 
   * @param views SaleOrderViewVO
   * @return boolean �ܷ��
   * @author ��־ΰ
   */
  public boolean isSendOpen(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    Rewrite4331Para para =
        this.getRewrite4331Paras().get(body.getCsaleorderbid());
    UFDouble nchangenum = para.getNchangenum();
    // �ǹرյ��ݲ��ܴ�
    if (!body.getBbsendendflag().booleanValue()) {
      return false;
    }
    // �йرջ����رշ������ܴ�
    if (BillStatus.CLOSED.equalsValue(view.getBody().getFrowstatus())
        || body.getBboutendflag().booleanValue()) {
      return false;
    }
    // ���ݻ�д�����ж��Ƿ���Ҫ�򿪴���
    if (!this.isNeedOpenByChangeNum(body, nchangenum)) {
      return false;
    }

    // 1.һ���Է����ر� && |����|== 0 ��,;ֻҪ������һ���ǹرյ�
    boolean isOnceSendClose = this.getIsOnceSendClose(head.getCtrantypeid());
    if (isOnceSendClose) {
      UFDouble sendnum = body.getNtotalsendnum();
      return MathTool.absCompareTo(sendnum, UFDouble.ZERO_DBL) == 0 ? true
          : false;
    }

    // ;���Ƿ񲹻�
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
    // ���۶���֮ǰ�Ƿ��Զ������ر�
    boolean bautoClose =
        this.isAutoSendColse(view, nchangenum, isLossReNew, closeLowerLimit);
    /*
     * 3. ;�𲻲���:|�ۼƷ�������| < |��������|*��1-����ر�����%��,�򷢻��ر� ;�𲹻�: |��������|-|;������| <
     * |��������|*��1-����ر�����%��,�򷢻��ر�
     */
    boolean isSendOpen = false;
    UFDouble nnum = this.getOrderOutCloseNum(view, closeLowerLimit);
    // ;�𲻲���
    if (!isLossReNew) {
      isSendOpen =
          bautoClose
              && MathTool.absCompareTo(body.getNtotalsendnum(), nnum) < 0;
    }
    // ;�𲹻�
    else {
      UFDouble ntotalsendnum = body.getNtotalsendnum();
      UFDouble ntotallossnum = body.getNtranslossnum();
      isSendOpen =
          bautoClose
              && MathTool.absCompareTo(
                  MathTool.sub(ntotalsendnum, ntotallossnum), nnum) < 0;
    }
    return isSendOpen;
  }

  public boolean isSendOpenFor4453(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    boolean isSendOpen = false;
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    // ;���Ƿ񲹻�
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    if (isLossReNew) {
      Rewrite4453Para para =
          this.getRewrite4453Paras().get(body.getCsaleorderbid());
      UFDouble nchangenum = para.getNtranslossnum();
      UFDouble closeLowerLimit =
          this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
      // ���۶���֮ǰ�Ƿ��Զ������ر�
      boolean bautoClose =
          this.isAutoSendColseFor4453(view, nchangenum, isLossReNew,
              closeLowerLimit);
      if (bautoClose) {
        isSendOpen = MathTool.greaterThan(nchangenum, UFDouble.ZERO_DBL);
      }
    }
    return isSendOpen;
  }

  /**
   * �Ƿ���Է�����
   * <p>
   * �Զ��ر� &&(|������|>|ԭ������|)
   * 
   * @param views SaleOrderViewVO
   * @return boolean �ܷ��
   * @author ��־ΰ
   */
  public boolean isSendOpenForRevise(SaleOrderViewVO view,
      SaleOrderViewVO originView, Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();

    // ����û�иı�
    if (!isNumChange(view, originView)) {
      return false;
    }

    // �ǹرյ��ݲ��ܴ�
    if (!body.getBbsendendflag().booleanValue()) {
      return false;
    }
    // �йرջ����رշ������ܴ�
    if (BillStatus.CLOSED.equalsValue(view.getBody().getFrowstatus())
        || body.getBboutendflag().booleanValue()) {
      return false;
    }
    // �����ۿ۳��ⲻ�ܴ�
    if (!isManualOutSendOpen(view)) {
      return false;
    }

    // 1.һ���Է����ر� && |����|== 0 ��,;ֻҪ������һ���ǹرյ�
    boolean isOnceSendClose = this.getIsOnceSendClose(head.getCtrantypeid());
    if (isOnceSendClose) {
      UFDouble sendnum = body.getNtotalsendnum();
      return MathTool.absCompareTo(sendnum, UFDouble.ZERO_DBL) == 0 ? true
          : false;
    }

    // ;���Ƿ񲹻�
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);

    /*  UFDouble nnum = view.getBody().getNnum();
      UFDouble originNnum = UFDouble.ZERO_DBL;
      if (null != originView) {
        originNnum = originView.getBody().getNnum();
      }*/
    // ���۶���֮ǰ�Ƿ��Զ������ر�
    boolean bautoClose =
        this.isAutoSendColse(view, UFDouble.ZERO_DBL, isLossReNew,
            closeLowerLimit);
    return !bautoClose;
  }

  /**
   * �Ƿ���Է����ر��޶�
   * <p>
   * �Զ��ر� &&(|������|>|ԭ������|)
   * 
   * @param views SaleOrderViewVO
   * @return boolean �ܷ��
   * @author ��־ΰ
   */
  public boolean isSendColseForRevise(SaleOrderViewVO view,
      SaleOrderViewVO originView, Map<String, MaterialVO> materrialmaps) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();

    // ����û�иı�
    if (!isNumChange(view, originView)) {
      return false;
    }

    // �Ǵ򿪵��ݲ��ܹر�
    if (body.getBbsendendflag().booleanValue()) {
      return false;
    }
    /* // �йرջ����رշ������ܴ�
     if (BillStatus.CLOSED.equalsValue(view.getBody().getFrowstatus())
         || body.getBboutendflag().booleanValue()) {
       return false;
     }*/

    // 1.һ���Է����ر� && |����|== 0 ��,;ֻҪ������һ���ǹرյ�
    boolean isOnceSendClose = this.getIsOnceSendClose(head.getCtrantypeid());
    if (isOnceSendClose) {
      UFDouble sendnum = body.getNtotalsendnum();
      return MathTool.absCompareTo(sendnum, UFDouble.ZERO_DBL) == 0 ? true
          : false;
    }

    // ;���Ƿ񲹻�
    boolean isLossReNew = this.isLossReNew(head.getCtrantypeid());
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);

    /* UFDouble nnum = view.getBody().getNnum();
     UFDouble originNnum = UFDouble.ZERO_DBL;
     if (null != originView) {
       originNnum = originView.getBody().getNnum();
     }*/
    // ���۶���֮ǰ�Ƿ��Զ������ر�
    boolean bautoClose =
        this.isAutoSendColse(view, UFDouble.ZERO_DBL, isLossReNew,
            closeLowerLimit);
    return bautoClose/* && MathTool.absCompareTo(nnum, originNnum) > 0*/;
  }

  private SOBusiMDEnum getBusiTypeByID(String m30transTypeCode, String busitype) {
    SOBusiPara[] paras = new SOBusiPara[1];
    paras[0] = new SOBusiPara(m30transTypeCode, busitype);
    if (this.busiTypeMap == null) {
      this.busiTypeMap = new HashMap<SOBusiPara, SOBusiMDEnum>();
    }
    if (this.busiTypeMap.get(paras[0]) == null) {
      if (this.busiUtil == null) {
        this.busiUtil = new SOBusiUtil();
      }
      Map<SOBusiPara, SOBusiMDEnum> map = this.busiUtil.querySOBusiType(paras);
      this.busiTypeMap.putAll(map);
    }
    return this.busiTypeMap.get(paras[0]);
  }

  /**
   * ������ϵ����ϵĻ�����Ϣ��������
   */
  private UFDouble getCloseLowerLimit(String materialvid,
      Map<String, MaterialVO> materrialmaps) {
    UFDouble closeLowerLimit = UFDouble.ZERO_DBL;
    // ����
    if (materrialmaps != null && materrialmaps.size() > 0) {
      MaterialVO materialvo = materrialmaps.get(materialvid);
      UFDouble limit = materialvo.getOutcloselowerlimit();
      if (limit != null) {
        closeLowerLimit = limit;
      }
    }
    return closeLowerLimit;
  }

  /**
   * �������͡����Ƿ�һ�γ���ر�
   * 
   * @author ��־ΰ
   * @time 2010-01-28 ����13:49:07
   */
  private boolean getIsOnceOutClose(String ctrantypeid) {
    M30TranTypeVO tranTypeVO = this.getTranTypeVOByID(ctrantypeid);
    return null == tranTypeVO.getBarrangeout() ? false : tranTypeVO
        .getBarrangeout().booleanValue();
  }

  /**
   * �������͡����Ƿ�һ�η����ر�
   * 
   * @author ��־ΰ
   * @time 2010-01-28 ����13:49:07
   */
  private boolean getIsOnceSendClose(String ctrantypeid) {
    M30TranTypeVO tranTypeVO = this.getTranTypeVOByID(ctrantypeid);
    return null == tranTypeVO.getBarrangeinv() ? false : tranTypeVO
        .getBarrangeinv().booleanValue();
  }

  /**
   * |��������|*��1-����ر�����%��
   * 
   * @param view
   * @return
   */
  private UFDouble getOrderOutCloseNum(SaleOrderViewVO view,
      Map<String, MaterialVO> materrialmaps) {
    SaleOrderBVO body = view.getBody();
    UFDouble closeLowerLimit =
        this.getCloseLowerLimit(body.getCmaterialvid(), materrialmaps);
    return this.getOrderOutCloseNum(view, closeLowerLimit);
  }

  /**
   * |��������|*��1-����ر�����%��
   * 
   * @param view
   * @return
   */
  private UFDouble getOrderOutCloseNum(SaleOrderViewVO view,
      UFDouble closeLowerLimit) {
    SaleOrderBVO body = view.getBody();
    UFDouble range = UFDouble.ONE_DBL.sub(closeLowerLimit.multiply(0.01));
    UFDouble nnum = MathTool.nvl(body.getNnum()).multiply(range);
    return nnum;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Rewrite32Para> getRewrite32Paras() {
    if (this.m32Paras == null) {
      this.m32Paras =
          (Map<String, Rewrite32Para>) BSContext.getInstance().getSession(
              Rewrite32Para.class.getName());
    }
    return this.m32Paras;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Rewrite33Para> getRewrite33Paras() {
    if (this.m33Paras == null) {
      this.m33Paras =
          (Map<String, Rewrite33Para>) BSContext.getInstance().getSession(
              Rewrite33Para.class.getName());
    }
    return this.m33Paras;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Rewrite4331Para> getRewrite4331Paras() {
    if (this.m4331Paras == null) {
      this.m4331Paras =
          (Map<String, Rewrite4331Para>) BSContext.getInstance().getSession(
              Rewrite4331Para.class.getName());
    }
    return this.m4331Paras;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Rewrite4453Para> getRewrite4453Paras() {
    if (this.m4453Paras == null) {
      this.m4453Paras =
          (Map<String, Rewrite4453Para>) BSContext.getInstance().getSession(
              Rewrite4453Para.class.getName());
    }
    return this.m4453Paras;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Rewrite4CPara> getRewrite4CParas() {
    if (this.m4CParas == null) {
      this.m4CParas =
          (Map<String, Rewrite4CPara>) BSContext.getInstance().getSession(
              Rewrite4CPara.class.getName());
    }
    return this.m4CParas;
  }

  private IM30TranTypeService getTranTypeService() {
    if (this.tranTypeService == null) {
      this.tranTypeService =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
    }
    return this.tranTypeService;
  }

  private M30TranTypeVO getTranTypeVOByID(String ctrantypeid) {
    if (this.tranTypeMap == null) {
      this.tranTypeMap = new HashMap<String, M30TranTypeVO>();
    }
    if (this.tranTypeMap.get(ctrantypeid) == null) {
      M30TranTypeVO[] tranTypeVOs = null;
      try {
        tranTypeVOs = this.getTranTypeService().queryTranTypeVOs(new String[] {
          ctrantypeid
        });
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      if (tranTypeVOs != null && tranTypeVOs.length > 0) {
        this.tranTypeMap.put(tranTypeVOs[0].getCtrantypeid(), tranTypeVOs[0]);
      }
    }
    return this.tranTypeMap.get(ctrantypeid);
  }

  /**
   * ���۶���֮ǰ�Ƿ��Զ���Ʊ�ر�
   * 
   * @param view
   * @param curchangenum -- ��ǰ��Ʊ�仯��
   * @param busiType
   * @return
   */
  private boolean isAutoInvoiceColse(SaleOrderViewVO view,
      UFDouble curchangenum, SOBusiMDEnum busiType) {
    SaleOrderBVO body = view.getBody();
    // ԭ�ۼƿ�Ʊ���� = ��ǰ�ۼƿ�Ʊ���� - ��ǰ��Ʊ����
    UFDouble totalinvnum =
        MathTool.sub(body.getNtotalinvoicenum(), curchangenum);
    UFDouble totalrushnum = body.getNtotalrushnum();
    UFDouble totaloutnum = body.getNtotaloutnum();
    UFDouble translossnum = body.getNtranslossnum();
    UFDouble num = body.getNnum();
    // �Ƿ��Զ��ر�
    boolean bautoClose = false;
    // --�Ȼ���Ʊ���ۼƿ�Ʊ���� + �ۼƳ���Գ����� == �ۼƳ������� - �ۼ�;������
    if (SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)) {
      // �������δ�رգ���Ʊ���Զ��ر�
      if (!ValueUtils.getBoolean(view.getBody().getBboutendflag())) {
        return false;
      }
      bautoClose =
          MathTool.compareTo(MathTool.add(totalinvnum, totalrushnum),
              MathTool.sub(totaloutnum, translossnum)) == 0;
    }
    // --��Ʊ��� || Ʊ�����У��ۼƿ�Ʊ���� >= ��������
    else if (SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST.equals(busiType)
        || SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL.equals(busiType)) {
      bautoClose = MathTool.compareTo(totalinvnum, num) >= 0;
    }
    return bautoClose;
  }

  /**
   * ���۶���֮ǰ�Ƿ��Զ�����ر�
   * 
   * @param view
   * @param para
   * @param isLossReNew
   * @param closeLowerLimit
   * @return
   */
  private boolean isAutoOutColse(SaleOrderViewVO view, UFDouble changenum,
      UFDouble changenotoutnum, boolean isLossReNew, UFDouble closeLowerLimit) {
    SaleOrderBVO body = view.getBody();
    // ԭ�ۼƳ������� = ��ǰ�ۼƳ������� - ��ǰ��������
    UFDouble origTotalOutnum = MathTool.sub(body.getNtotaloutnum(), changenum);
    // ԭ�ۼ�Ӧ��δ�������� = ��ǰ�ۼ�Ӧ��δ�������� - ��ǰӦ��δ��������
    UFDouble origTotalnotoutnum =
        MathTool.sub(body.getNtotalnotoutnum(), changenotoutnum);
    // ԭ�ۼƳ������� + ԭ�ۼ�Ӧ��δ�������� - is;�𲹻�(�ۼ�;������,0)
    UFDouble origFactOutNum =
        MathTool.sub(MathTool.add(origTotalOutnum, origTotalnotoutnum),
            isLossReNew ? body.getNtranslossnum() : UFDouble.ZERO_DBL);
    // ������������
    UFDouble nnum = this.getOrderOutCloseNum(view, closeLowerLimit);
    // �Ƿ��Զ��ر�
    boolean bautoClose =
        MathTool.absCompareTo(origFactOutNum, nnum) >= 0 ? true : false;
    return bautoClose;
  }

  /**
   * ���۶���֮ǰ�Ƿ��Զ������ر�
   * 
   * @param view
   * @param nchangenum ��ǰ;��仯��
   * @param isLossReNew
   * @return
   */
  private boolean isAutoSendColse(SaleOrderViewVO view, UFDouble nchangenum,
      boolean isLossReNew, UFDouble closeLowerLimit) {
    if (null == view) {
      return false;
    }
    SaleOrderBVO body = view.getBody();
    // ԭ�ۼƷ������� = ��ǰ�ۼƷ������� - ��ǰ��������
    UFDouble origTotalSendnum =
        MathTool.sub(body.getNtotalsendnum(), nchangenum);
    UFDouble nnum = this.getOrderOutCloseNum(view, closeLowerLimit);

    boolean bautoClose = true;
    if (!isLossReNew) {
      // �Ƿ��Զ��ر� (|ԭ�ۼƷ�������| >= |������|)
      bautoClose =
          MathTool.absCompareTo(origTotalSendnum, nnum) >= 0 ? true : false;
    }
    else {
      // �Ƿ��Զ��ر� (|ԭ�ۼƷ�������| >= |������|)
      UFDouble ntotallossnum = body.getNtranslossnum();
      bautoClose =
          MathTool.absCompareTo(MathTool.sub(origTotalSendnum, ntotallossnum),
              nnum) >= 0 ? true : false;
    }
    return bautoClose;
  }

  private boolean isAutoSendColseFor4453(SaleOrderViewVO view,
      UFDouble nchangenum, boolean isLossReNew, UFDouble closeLowerLimit) {
    SaleOrderBVO body = view.getBody();
    // ԭ�ۼƷ�������
    UFDouble origTotalSendnum = body.getNtotalsendnum();
    UFDouble nnum = this.getOrderOutCloseNum(view, closeLowerLimit);
    boolean bautoClose = true;
    if (!isLossReNew) {
      // �Ƿ��Զ��ر� (|ԭ�ۼƷ�������| >= |������|)
      bautoClose =
          MathTool.absCompareTo(origTotalSendnum, nnum) >= 0 ? true : false;
    }
    else {
      // �Ƿ��Զ��ر� (|ԭ�ۼƷ�������-ԭ�ۼ�;������| >= |������|)
      UFDouble origTotallossnum =
          MathTool.sub(body.getNtranslossnum(), nchangenum);
      bautoClose =
          MathTool.absCompareTo(
              MathTool.sub(origTotalSendnum, origTotallossnum), nnum) >= 0 ? true
              : false;
    }
    return bautoClose;

  }

  private boolean isAutoTransitRowCloseByOtherState(SaleOrderViewVO view) {
    // �����رա�����رա���Ʊ�رա�����رյ����Զ��ر�
    SaleOrderBVO body = view.getBody();
    return (body.getBbsendendflag() == null ? false : body.getBbsendendflag()
        .booleanValue())
        && (body.getBboutendflag() == null ? false : body.getBboutendflag()
            .booleanValue())
        && (body.getBbinvoicendflag() == null ? false : body
            .getBbinvoicendflag().booleanValue())
        && (body.getBbarsettleflag() == null ? false : body.getBbarsettleflag()
            .booleanValue())
        && (body.getBbcostsettleflag() == null ? false : body
            .getBbcostsettleflag().booleanValue());
  }

  private boolean isInvoiceClose(SaleOrderViewVO view, UFDouble curchangenum) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();

    // ����ҵ����������
    SOBusiMDEnum busiType =
        this.getBusiTypeByID(head.getVtrantypecode(), head.getCbiztypeid());

    // ���ݹ�ʽ�߼��ж��Ƿ�Ʊ�ر�
    UFDouble totalinvnum = body.getNtotalinvoicenum();
    UFDouble totalrushnum = body.getNtotalrushnum();
    UFDouble totaloutnum = body.getNtotaloutnum();
    UFDouble translossnum = body.getNtranslossnum();
    UFDouble num = body.getNnum();
    boolean laborflag =
        body.getBlaborflag() == null ? false : body.getBlaborflag()
            .booleanValue();
    boolean discountflag =
        body.getBdiscountflag() == null ? false : body.getBdiscountflag()
            .booleanValue();
    boolean ret = false;
    // --�����ۿ���:�ۼƿ�Ʊ���� >= ��������
    if (laborflag || discountflag) {
      ret = MathTool.absCompareTo(totalinvnum, num) >= 0;
    }
    // --�Ȼ���Ʊ���ۼƿ�Ʊ���� + �ۼƳ���Գ����� == �ۼƳ������� - �ۼ�;������
    else if (SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)) {
      // �������δ�رգ���Ʊ���Զ��ر�
      if (!ValueUtils.getBoolean(view.getBody().getBboutendflag())) {
        return false;
      }
      ret =
          MathTool.absCompareTo(MathTool.add(totalinvnum, totalrushnum),
              MathTool.sub(totaloutnum, translossnum)) == 0;
    }
    // --��Ʊ��� || Ʊ�����У��ۼƿ�Ʊ���� >= ��������
    else if (SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST.equals(busiType)
        || SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL.equals(busiType)) {
      ret = MathTool.absCompareTo(totalinvnum, num) >= 0;
    }
    return ret;
  }

  /**
   * 
   * @param view
   * @param curchangenum -- ��ǰ��Ʊ�仯��
   * @return
   */
  private boolean isInvoiceOpen(SaleOrderViewVO view, UFDouble curchangenum) {
    SaleOrderHVO head = view.getHead();
    SaleOrderBVO body = view.getBody();
    // 1.�ǹرյ��ݲ��ܴ�
    if (!body.getBbinvoicendflag().booleanValue()) {
      return false;
    }
    // 2.���ݹ�ʽ�ж��Ƿ��ܹ���Ʊ��
    UFDouble totalinvnum = body.getNtotalinvoicenum();
    UFDouble totalrushnum = body.getNtotalrushnum();
    UFDouble totaloutnum = body.getNtotaloutnum();
    UFDouble translossnum = body.getNtranslossnum();
    UFDouble num = body.getNnum();

    SOBusiMDEnum busiType =
        this.getBusiTypeByID(head.getVtrantypecode(), head.getCbiztypeid());
    // ���۶���֮ǰ�Ƿ��Զ���Ʊ�ر�
    boolean bautoClose = this.isAutoInvoiceColse(view, curchangenum, busiType);

    boolean ret = false;
    boolean laborflag =
        body.getBlaborflag() == null ? false : body.getBlaborflag()
            .booleanValue();
    boolean discountflag =
        body.getBdiscountflag() == null ? false : body.getBdiscountflag()
            .booleanValue();
    // --�����ۿ���:�ۼƿ�Ʊ���� >= ��������
    if (laborflag || discountflag) {
      // �ۿ�������¼�������������಻��������Ϊ�գ������ۿ�������Ϊ�յ��������ʵֻ����ת��һ��
      if (discountflag && MathTool.isZero(num)) {
        ret = true;
      }
      else {
        ret = MathTool.absCompareTo(totalinvnum, num) < 0;
      }
    }
    // --�Ȼ���Ʊ���ۼƿ�Ʊ���� + �ۼƳ���Գ����� != �ۼƳ������� - �ۼ�;������
    else if (SOBusiMDEnum.SOBUSIMDENUM_OUTFIRST.equals(busiType)) {
      // �������δ�رգ���Ʊ���Զ��ر�
      if (!ValueUtils.getBoolean(view.getBody().getBboutendflag())) {
        return false;
      }
      ret =
          bautoClose
              && MathTool.absCompareTo(MathTool.add(totalinvnum, totalrushnum),
                  MathTool.sub(totaloutnum, translossnum)) != 0;
    }
    // --��Ʊ��� || Ʊ�����У��ۼƿ�Ʊ���� < ��������
    else if (SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST.equals(busiType)
        || SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL.equals(busiType)) {
      ret = bautoClose && MathTool.absCompareTo(totalinvnum, num) < 0;
    }
    return ret;
  }

  /**
   * ��Ʒ�Ƿ�Ʊ
   * 
   * @param view
   */
  private boolean isLargessCanInvoice(SaleOrderViewVO view) {
    SaleOrderBVO body = view.getBody();
    boolean larflag = ValueUtils.getBoolean(body.getBlargessflag());
    String settleorgid = body.getCsettleorgid();
    boolean bSO20 =
        SOSysParaInitUtil.getSO20(settleorgid) == null ? false
            : SOSysParaInitUtil.getSO20(settleorgid).booleanValue();
    return larflag && !bSO20;
  }

  /**
   * �������͡����Ƿ�;�𲹻�
   * 
   * @author ��־ΰ
   * @time 2010-01-28 ����13:49:07
   */
  private boolean isLossReNew(String ctrantypeid) {
    M30TranTypeVO tranTypeVO = this.getTranTypeVOByID(ctrantypeid);
    return tranTypeVO.getBlossrenew() == null ? false : tranTypeVO
        .getBlossrenew().booleanValue();
  }

  private boolean isManualOutSendOpen(SaleOrderViewVO view) {
    SaleOrderBVO body = view.getBody();
    boolean flag = true;
    // �����ۿ��з����������ֹ���
    boolean laborflag =
        body.getBlaborflag() == null ? false : body.getBlaborflag()
            .booleanValue();
    boolean discountflag =
        body.getBdiscountflag() == null ? false : body.getBdiscountflag()
            .booleanValue();
    if (laborflag || discountflag) {
      flag = false;
    }
    return flag;

  }

  private boolean isNeedCloseByChangeNum(SaleOrderBVO body, UFDouble nchangenum) {
    // �����л�д������Զ���ر�
    if (this.isRedRow(body)) {
      if (MathTool.compareTo(nchangenum, UFDouble.ZERO_DBL) > 0) {
        return false;
      }
    }
    // �����л�д������Զ���ر�
    else {
      if (MathTool.compareTo(nchangenum, UFDouble.ZERO_DBL) < 0) {
        return false;
      }
    }
    return true;
  }

  private boolean isNeedOpenByChangeNum(SaleOrderBVO body, UFDouble nchangenum) {
    // �����л�д����(����0)��Զ����
    if (this.isRedRow(body)) {
      if (MathTool.compareTo(nchangenum, UFDouble.ZERO_DBL) <= 0) {
        return false;
      }
    }
    // �����л�д����(����0)��Զ����
    else {
      if (MathTool.compareTo(nchangenum, UFDouble.ZERO_DBL) >= 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * �Ƿ���ֶ�����(��������)
   */
  private boolean isRedRow(SaleOrderBVO body) {
    return MathTool.compareTo(body.getNnum(), UFDouble.ZERO_DBL) < 0;
  }

  private boolean isNumChange(SaleOrderViewVO vo, SaleOrderViewVO originVO) {
    if (originVO == null) {
      return true;
    }

    UFDouble nnum = vo.getBody().getNnum();

    UFDouble oldnnum = originVO.getBody().getNnum();

    if (MathTool.equals(nnum, oldnnum)) {
      return false;
    }
    else {
      return true;
    }

  }
}
