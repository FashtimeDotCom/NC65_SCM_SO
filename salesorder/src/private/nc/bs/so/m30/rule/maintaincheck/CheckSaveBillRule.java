package nc.bs.so.m30.rule.maintaincheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.pub.rule.MaterielDistributeCheck;
import nc.bs.so.pub.rule.SOProfitCenterUtil;
import nc.bs.so.pub.rule.rowno.SORowNoUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.ct.saledaily.ISaledailyMaintain;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.vo.ct.saledaily.entity.AggCtSaleVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pfflow01.BillbusinessVO;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.enumeration.Largesstype;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.m30trantype.enumeration.SaleMode;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 *              ���ݱ���ǰ������ 1����鵥�ݱ�����ǿ� 2��������� 3������˻�����־ 4���������ģʽ 5���˻�����ϵ���
 * @scene
 *        ���۶����������޸ı���ǰ
 * @param m_transType ���۶�����������<���۶�����������code,���۶�����������vo>
 *          tranTypeService ���۶����������ͷ���ӿ�
 * @author gdsjw
 */
public class CheckSaveBillRule implements IRule<SaleOrderVO> {

  // ���۶�����������<���۶�����������code,���۶�����������vo>
  private Map<String, M30TranTypeVO> m_transType =
      new HashMap<String, M30TranTypeVO>();

  private IM30TranTypeService tranTypeService;

  public M30TranTypeVO getTransType(String ctrantypeid) {
    M30TranTypeVO tranType = this.m_transType.get(ctrantypeid);
    // ������û�����۶����������ʹӺ�̨��ѯ
    if (VOChecker.isEmpty(tranType)) {
      try {
        tranType = this.getTranTypeService().queryTranTypeVO(ctrantypeid);
        m_transType.put(ctrantypeid, tranType);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }
    }
    return tranType;
  }

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO vo : vos) {
      // ����ǲ�ȫVO��У��ʱ������Ҫ������״̬
      this.checkNotNull(vo);
      this.checkDirectType(vo);
      this.checkFretexchange(vo);
      this.checkSaleMode(vo);
      this.checbodyJustNegative(vo);
      this.checkRowCountLimit(vo);
      // У�����ϺͿ����֯�ķ����ϵ
      this.checkMaterielDistribute(vo);
      this.checkMaterielMutil(vo);
      this.checkFreeCust(vo);
      this.checkOrderPay(vo);

      SORowNoUtil.checkRowNo(vo);
      SOProfitCenterUtil.checkProfitCenterValue(vo);
    }
    // �����ѡ�����Ϻ�û���뿪���㣬ֱ��ѡ����֡����ʱ����ʾ�������ۺ�ͬ������ӳ٣�����ڴ���У�顣
    checkCTCurrency(vos);
  }

  private void checkCTCurrency(SaleOrderVO[] vos) {
    Map<String, String> currencymap = new HashMap<String, String>();
    Set<String> ctmanageset = new HashSet<String>();
    for (SaleOrderVO vo : vos) {
      String corigcurrencyid = vo.getParentVO().getCorigcurrencyid();
      for (SaleOrderBVO bvo : vo.getChildrenVO()) {
        if (VOStatus.DELETED == bvo.getStatus()
            || VOStatus.UNCHANGED == bvo.getStatus()) {
          continue;
        }

        if (bvo.getCctmanageid() != null) {
          currencymap.put(bvo.getCctmanageid(), corigcurrencyid);
          ctmanageset.add(bvo.getCctmanageid());
        }
      }
    }
    if (ctmanageset.size() == 0) {
      return;
    }
    ISaledailyMaintain ct =
        NCLocator.getInstance().lookup(ISaledailyMaintain.class);
    String[] cthids = ctmanageset.toArray(new String[ctmanageset.size()]);
    try {
      AggCtSaleVO[] ctvos = ct.queryCtApVoByIds(cthids);
      Map<String, String> ctcurrencymap = new HashMap<String, String>();
      for (AggCtSaleVO ctvo : ctvos) {
        ctcurrencymap.put(ctvo.getParentVO().getPk_ct_sale(), ctvo
            .getParentVO().getCorigcurrencyid());
      }
      for (SaleOrderVO vo : vos) {
        for (SaleOrderBVO bvo : vo.getChildrenVO()) {
          if (VOStatus.DELETED == bvo.getStatus()
              || VOStatus.UNCHANGED == bvo.getStatus()) {
            continue;
          }
          String cthid = bvo.getCctmanageid();

          if (cthid == null) {
            continue;
          }
          String corigcurrencyid = currencymap.get(cthid);
          String ctcorigcurrencyid = ctcurrencymap.get(cthid);

          if (!PubAppTool.isEqual(corigcurrencyid, ctcorigcurrencyid)) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006011_0", "04006011-0524")/*
                                                                         * ���۶��������ۺ�ͬ���ֲ���ͬ
                                                                         * ������!
                                                                         */);
          }
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.toString());
    }
  }

  private void checkBodyValidity(SaleOrderHVO head, SaleOrderBVO[] bodys) {
    // �Ƿ��������з�����
    boolean isHaveDelivery = false;
    String vtrantypecode = head.getVtrantypecode();
    String cbiztypeid = head.getCbiztypeid();
    BillbusinessVO[] businessvos =
        PfServiceScmUtil.queryBillDest(vtrantypecode, cbiztypeid);
    if (businessvos != null && businessvos.length > 0) {
      for (int i = 0; i < businessvos.length; i++) {
        if (SOBillType.Delivery.getCode().equals(
            businessvos[i].getPk_billtype())) {
          isHaveDelivery = true;
        }
      }
    }
    StringBuilder errMsg = new StringBuilder();
    SaleOrderVO aggvo = new SaleOrderVO();
    aggvo.setParentVO(head);
    for (SaleOrderBVO bvo : bodys) {
      aggvo.setChildrenVO(new SaleOrderBVO[] {
        bvo
      });
      // ����ɾ���� ע�͵�unchanged
      if (VOStatus.DELETED == bvo.getStatus()
          || VOStatus.UNCHANGED == bvo.getStatus()) {
        continue;
      }
      // ���Ϸ��ֶ�
      List<String> listValiField = new ArrayList<String>();

      String cmaterialid = bvo.getCmaterialvid();
      if (PubAppTool.isNull(cmaterialid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0307")/* ���� */);
      }

      String castunitid = bvo.getCastunitid();
      if (PubAppTool.isNull(castunitid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0308")/* ��λ */);
      }

      UFDouble ndiscountrate = bvo.getNdiscountrate();
      if (null == ndiscountrate) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0309")/* �����ۿ� */);
      }
      UFDouble nitemdiscount = bvo.getNitemdiscountrate();
      if (null == nitemdiscount) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0310")/* ��Ʒ�ۿ� */);
      }
      UFDouble ntaxrate = bvo.getNtaxrate();
      if (null == ntaxrate) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0311")/* ˰�� */);
      }

      if (null == bvo.getCtaxcodeid()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0439")/* ˰�� */);
      }

      if (null == bvo.getFtaxtypeflag()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0440")/* ��˰��� */);
      }

      if (null == bvo.getNcaltaxmny()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0441")/* ��˰��� */);
      }

      if (null == bvo.getCrececountryid()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0442")/* �ջ�����/���� */);
      }

      if (null == bvo.getCsendcountryid()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0443")/* ��������/���� */);
      }

      if (null == bvo.getCtaxcountryid()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0444")/* ��˰����/���� */);
      }

      if (null == bvo.getFbuysellflag()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0445")/* �������� */);
      }

      if (null == bvo.getBtriatradeflag()) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0446")/* ����ó�� */);
      }

      UFDate dsenddate = bvo.getDsenddate();
      if (null == dsenddate) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0319")/* �ƻ��������� */);
      }
      UFDate dreceivedate = bvo.getDreceivedate();
      if (null == dreceivedate) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0320")/* Ҫ�󵽻����� */);
      }
      String creceivicustid = bvo.getCreceivecustid();
      if (PubAppTool.isNull(creceivicustid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0321")/* �ջ��ͻ� */);
      }
      String csendstockorgvid = bvo.getCsendstockorgid();
      if (PubAppTool.isNull(csendstockorgvid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0322")/* ���������֯ */);
      }
      // ���з�����������������֯���ɿ�
      String ctrafficorgvid = bvo.getCtrafficorgvid();
      if (isHaveDelivery && PubAppTool.isNull(ctrafficorgvid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0323")/* ������֯ */);
      }
      String csettleorgvid = bvo.getCsettleorgvid();
      if (PubAppTool.isNull(csettleorgvid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0324")/* ���������֯ */);
      }
      String carorgvid = bvo.getCarorgvid();
      if (PubAppTool.isNull(carorgvid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0429")/* Ӧ����֯ */);
      }
      String ccurrencyid = bvo.getCcurrencyid();
      if (PubAppTool.isNull(ccurrencyid)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0325")/* ��λ�� */);
      }

      UFDouble nexchangerate = bvo.getNexchangerate();
      if (null == nexchangerate
          || nexchangerate.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0326")/* �۱����� */);
      }
      String ctrantypeid = head.getCtrantypeid();
      M30TranTypeVO trantypevo = this.getTransType(ctrantypeid);
      UFDouble naccprice = bvo.getNaccprice();
      if (null != trantypevo.getBlrgcashflag()
          && trantypevo.getBlrgcashflag().booleanValue()) {
        if (naccprice == null || MathTool.isZero(naccprice)) {
          listValiField.add(NCLangResOnserver.getInstance().getStrByID(
              "4006011_0", "04006011-0489")/* �����˵��� */);
        }
      }
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(aggvo);
      SOBuysellTriaRule buyselrule = new SOBuysellTriaRule(keyValue);
      boolean isInternational = buyselrule.isBuysellFlagOut(0);
      // ��ʱ��ȥ�����ҵ��û�д˹�ϵ
      if (!isInternational) {
        UFDouble ntaxmny = bvo.getNtaxmny();
        UFDouble naddtaxmny = MathTool.add(bvo.getNtax(), bvo.getNmny());
        if (!MathTool.equals(ntaxmny, naddtaxmny)) {
          listValiField.add(NCLangResOnserver.getInstance().getStrByID(
              "4006011_0",
              "04006011-0453",
              null,
              new String[] {
                ValueUtils.getString(ntaxmny),
                ValueUtils.getString(bvo.getNmny()),
                ValueUtils.getString(bvo.getNtax())
              })/* ���Ҽ�˰�ϼ�({0})�����ڱ�����˰���({1})��˰��({2}) */);
        }
      }
      if (listValiField.size() > 0) {
        String crowno = bvo.getCrowno();
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0327", null, new String[] {
              crowno
            })/* ��[{0}]�У� */);
        for (String field : listValiField) {
          errMsg
              .append("[")
              .append(field)
              .append("]")
              .append(
                  NCLangResOnserver.getInstance().getStrByID("4006011_0",
                      "04006011-0284")/* �� */);
        }
        errMsg.deleteCharAt(errMsg.length() - 1);
        errMsg.append("\n");
      }
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0328", null, new String[] {
            errMsg.toString()
          })/* �����ֶ�ֵ����Ϊ�ջ�Ϊ0:\n{0} */);
    }
  }

  private void checkDirectType(SaleOrderVO newbill) {
    SaleOrderHVO head = newbill.getParentVO();
    // �Ż���add by zhangby5 �ӻ�����ȡ�������͵�ֱ������
    M30TranTypeVO trantypevo = this.getTransType(head.getCtrantypeid());
    Integer directType =
        trantypevo.getFdirecttype() == null ? DirectType.DIRECTTRAN_NO
            .getIntegerValue() : trantypevo.getFdirecttype();
    if (DirectType.DIRECTTRAN_TO.equalsValue(directType)) {
      for (SaleOrderBVO bvo : newbill.getChildrenVO()) {
        if (VOStatus.DELETED == bvo.getStatus()
            || VOStatus.UNCHANGED == bvo.getStatus()) {
          continue;
        }
        if (StringUtil.isEmpty(bvo.getCsendstordocid())) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0090")/*
                                                                       * @res
                                                                       * "ֱ�˵������ͣ������ֿⲻ����Ϊ��"
                                                                       */);
        }
      }
    }
  }

  private void checbodyJustNegative(SaleOrderVO bill) {
    // ��Ʒ�Ҹ������۶���������¼����ֶ�����
    SaleOrderBVO[] saleorders = bill.getChildrenVO();
    for (SaleOrderBVO saleorder : saleorders) {
      if (MathTool.compareTo(saleorder.getNastnum(), UFDouble.ZERO_DBL) < 0
          && !PubAppTool.isNull(bill.getParentVO().getCarsubtypeid())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006011_0", "04006011-0517")/* ��Ʒ�Ҹ������۶���������¼�븺���У� */);
      }
    }
  }

  private void checkFreeCust(SaleOrderVO bill) {
    SaleOrderHVO header = bill.getParentVO();
    if (null != header.getBfreecustflag()
        && header.getBfreecustflag().booleanValue()) {
      if (PubAppTool.isNull(header.getCfreecustid())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006011_0", "04006011-0091")/*
                                                                     * @res
                                                                     * "���۶����ͻ���ɢ�����ʣ�ɢ������!"
                                                                     */);
      }
    }
  }

  private void checkFretexchange(SaleOrderVO bill) {
    SaleOrderBVO[] items = bill.getChildrenVO();
    for (SaleOrderBVO item : items) {
      int vostatus = item.getStatus();
      if (vostatus == VOStatus.DELETED) {
        // �����ɾ������
        continue;
      }
      Integer fretexchange = item.getFretexchange();
      UFDouble nnum = item.getNastnum();
      if (Fretexchange.COMMON.value().equals(fretexchange)) {
        if (null != nnum && nnum.compareTo(UFDouble.ZERO_DBL) < 0) {
          if (item.getBdiscountflag() != null
              && item.getBdiscountflag().booleanValue()) {
            // ��ͨ����������������Ϊ�ۿ�ʱ����ϸ����������Ϊ����
          }
          else {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006011_0", "04006011-0092")/*
                                                                         * @res
                                                                         * "���˻��Ķ���������������Ϊ������"
                                                                         */);
          }
        }
      }
      else if (Fretexchange.WITHDRAW.value().equals(fretexchange)) {
        if (item.getBdiscountflag() != null
            && item.getBdiscountflag().booleanValue()) {
          // �˻�����������������Ϊ�ۿ�ʱ����ϸ����������Ϊ���� v636
          if (null != nnum && nnum.compareTo(UFDouble.ZERO_DBL) < 0) {
            ExceptionUtils.wrappBusinessException(NCLangResOnserver
                .getInstance().getStrByID("4006011_0", "04006011-0490")/*
                                                                        * �˻��Ķ����У�
                                                                        * �ۿ�������
                                                                        * ��
                                                                        * ����������Ϊ����
                                                                        * ��
                                                                        */);
          }
        }
        else {
          if (null != nnum && nnum.compareTo(UFDouble.ZERO_DBL) > 0) {
            ExceptionUtils.wrappBusinessException(NCLangResOnserver
                .getInstance().getStrByID("4006011_0", "04006011-0491")/*
                                                                        * �˻��Ķ����У�
                                                                        * ���ۿ�������
                                                                        * ��
                                                                        * ����������Ϊ����
                                                                        * ��
                                                                        */);
          }
        }
      }
      else if (Fretexchange.EXCHANGE.value().equals(fretexchange)) {
        if (null != nnum && nnum.compareTo(UFDouble.ZERO_DBL) < 0) {

          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0094")/*
                                                                       * @res
                                                                       * "�����Ķ���������������Ϊ������"
                                                                       */);
        }
      }
    }

  }

  private void checkHeadValidity(SaleOrderHVO header) {

    List<String> errField = new ArrayList<String>();

    String pk_org = header.getPk_org();
    if (PubAppTool.isNull(pk_org)) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0329")/* ������֯ */);
    }

    String ctrantypeid = header.getCtrantypeid();
    if (PubAppTool.isNull(ctrantypeid)) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0330")/* �������� */);
    }

    String cbiztypeid = header.getCbiztypeid();
    if (PubAppTool.isNull(cbiztypeid)) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0331")/* ҵ������ */);
    }

    UFDate dbilldate = header.getDbilldate();
    if (null == dbilldate) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0332")/* �������� */);
    }
    String ccustomerid = header.getCcustomerid();
    if (PubAppTool.isNull(ccustomerid)) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0333")/* �ͻ� */);
    }
    String cinvoicecustid = header.getCinvoicecustid();
    if (PubAppTool.isNull(cinvoicecustid)) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0334")/* ��Ʊ�ͻ� */);
    }
    String corigcurrencyid = header.getCorigcurrencyid();
    if (PubAppTool.isNull(corigcurrencyid)) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0335")/* ���� */);
    }
    String cdeptid = header.getCdeptid();
    if (PubAppTool.isNull(cdeptid)) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0336")/* ���۲��� */);
    }

    if (errField.size() > 0) {
      StringBuilder errMsg =
          new StringBuilder(NCLangResOnserver.getInstance().getStrByID(
              "4006011_0", "04006011-0337")/* �������Ե�ֵ����Ϊ�գ� */);
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0338")/* \n ��ͷ */);
      for (String field : errField) {
        errMsg
            .append("[")
            .append(field)
            .append("]")
            .append(
                NCLangResOnserver.getInstance().getStrByID("4006011_0",
                    "04006011-0284")/* �� */);
      }
      errMsg.deleteCharAt(errMsg.length() - 1);

      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

  private void checkMaterielDistribute(SaleOrderVO bill) {
    int len = bill.getChildrenVO().length;
    String[][] materIDStoreIDs = new String[len][2];
    int i = 0;
    for (SaleOrderBVO bvo : bill.getChildrenVO()) {
      materIDStoreIDs[i] = new String[2];
      materIDStoreIDs[i][0] = bvo.getCmaterialvid();
      materIDStoreIDs[i][1] = bvo.getCsendstockorgid();
      i++;
    }
    new MaterielDistributeCheck().check(materIDStoreIDs);
  }

  /**
   * ���������Ƿ���ظ�
   * 
   * @param bill
   */
  private void checkMaterielMutil(SaleOrderVO bill) {
    SaleOrderHVO header = bill.getParentVO();
    M30TranTypeVO trantype = this.getTransType(header.getCtrantypeid());
    if (!trantype.getBmorerows().booleanValue()) {
      Set<String> sinvo = new HashSet<String>();
      Set<String> sinvv = new HashSet<String>();
      for (SaleOrderBVO bvo : bill.getChildrenVO()) {
        // ɾ����
        if (VOStatus.DELETED == bvo.getStatus()) {
          continue;
        }
        // ��Ʒ��
        if (null != bvo.getBlargessflag()
            && bvo.getBlargessflag().booleanValue()) {
          continue;
        }
        // �����в�����
        int fretexchage =
            bvo.getFretexchange() == null ? Fretexchange.COMMON.getIntValue()
                : bvo.getFretexchange().intValue();
        if (Fretexchange.EXCHANGE.getIntValue() == fretexchage) {
          continue;
        }
        // ��̯��Ʒ��
        Integer larstflag = bvo.getFlargesstypeflag();
        if (Largesstype.APPORTIONLARGESS.equalsValue(larstflag)) {
          continue;
        }
        String materieloid = bvo.getCmaterialid();
        String materielvid = bvo.getCmaterialvid();
        if (sinvo.contains(materieloid) && sinvv.contains(materielvid)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0095")/*
                                                                       * @res
                                                                       * "�������Ϳ���ͬһ���ﲻ���ж��У�"
                                                                       */);
        }
        else {
          sinvo.add(materieloid);
          sinvv.add(materielvid);
        }
      }
    }
  }

  private void checkNotNull(SaleOrderVO bill) {
    SaleOrderHVO head = bill.getParentVO();
    this.checkHeadValidity(head);

    SaleOrderBVO[] bodys = bill.getChildrenVO();
    if (null == bodys || this.getVORowCount(bill) == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006011_0", "04006011-0096")/*
                                                                   * @res
                                                                   * "�������岻��Ϊ�ա�"
                                                                   */);
    }
    this.checkBodyValidity(head, bodys);
  }

  /**
   * ����������˰�ϼƺͶ���ʵ���տ������ͬ��
   * 
   * @param bill
   */
  private void checkOrderPay(SaleOrderVO bill) {
    UFDouble nsummny = bill.getParentVO().getNtotalorigmny();
    UFDouble npaymny = bill.getParentVO().getNreceivedmny();
    if (MathTool.isDiffSign(nsummny, npaymny)) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0413")/*
                                                    * @res
                                                    * "����������˰�ϼƺͶ���ʵ���տ������ͬ�ţ�"
                                                    */);
    }

  }

  private void checkRowCountLimit(SaleOrderVO vo) {
    Object pk_org = vo.getParentVO().getPk_org();

    int rowlimit = 0;

    rowlimit =
        SOSysParaInitUtil.getSO01(pk_org.toString()) == null ? 0
            : SOSysParaInitUtil.getSO01(pk_org.toString()).intValue();

    int rowCount = this.getVORowCount(vo);
    if (rowlimit > 0 && rowCount > rowlimit) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006011_0", "04006011-0101")/*
                                                                   * @res
                                                                   * "��������������![����SO01��������:"
                                                                   */
          + rowlimit + "]");
    }
  }

  // ��ȡVO�б����з�ɾ��״̬������ zhangby5 2014.7.29
  private int getVORowCount(SaleOrderVO vo) {

    int count = 0;
    for (SaleOrderBVO bvo : vo.getChildrenVO()) {

      if (bvo.getStatus() != VOStatus.DELETED) {
        count++;
      }
    }
    return count;
  }

  private void checkSaleMode(SaleOrderVO bill) {
    SaleOrderHVO header = bill.getParentVO();
    M30TranTypeVO trantype = this.getTransType(header.getCtrantypeid());
    if (trantype == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006011_0", "04006011-0102")/*
                                                                   * @res
                                                                   * "��ѯ�������ͳ���"
                                                                   */);
    }
    Integer fsalemode = trantype.getFsalemode();
    if (fsalemode == null) {
      fsalemode = (Integer) SaleMode.MODE_COMMON.value();
    }
    boolean isExistCommon = false;
    boolean isExistWithdraw = false;
    boolean isExistExchange = false;

    SaleOrderBVO[] items = bill.getChildrenVO();
    for (SaleOrderBVO item : items) {
      int vostatus = item.getStatus();
      if (vostatus == VOStatus.DELETED) {
        // �����ɾ������
        continue;
      }
      Integer fretexchange = item.getFretexchange();
      // �ۿ�����������Ϊ�յ��Ǽ�˰�ϼ�Ϊ��(�˻���)��Ҫ����Ա���ͨ������˰�ϼ�Ϊ������ͨ��
      if (Fretexchange.COMMON.value().equals(fretexchange)
          || null == fretexchange) {
        if (!MathTool.equals(item.getNnum(), new UFDouble(0))) {
          isExistCommon = true;
        }
        else {
          if (item.getBdiscountflag().booleanValue()
              && MathTool.lessThan(item.getNorigtaxmny(), new UFDouble(0))) {
            isExistCommon = true;
          }
        }
        if (item.getBdiscountflag().booleanValue()
            && MathTool.equals(item.getNnum(), new UFDouble(0))
            && MathTool.greaterThan(item.getNorigtaxmny(), new UFDouble(0))) {
          isExistWithdraw = true;
        }
      }
      else if (Fretexchange.WITHDRAW.value().equals(fretexchange)) {
        isExistWithdraw = true;
      }
      else if (Fretexchange.EXCHANGE.value().equals(fretexchange)) {
        isExistExchange = true;
      }

      /*  // ����������ͨ��¼���������ȷ���˻�����������ͨ(FretexchangeRule)�������˻��Ѿ���ȷ�����˻������Բ��ܸ���¼����������ж�����ģʽ�����������ǿ��У���¡�
      // ��Դ�ڶ����ͳ��ⵥ�Ķ���Ϊ�˻������Բ���Ϊ������
      if (SOBillType.Order.getCode().equals(item.getVsrctype())
          || ICBillType.SaleOut.getCode().equals(item.getVsrctype())) {
        boolean discountflag = item.getBdiscountflag().booleanValue();
        // ���ۿ��������˻�Ӧ��Ϊ����
        if (!discountflag
            && MathTool.greaterThan(item.getNorigtaxmny(), new UFDouble(0))) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0104")
                                                                       * @res
                                                                       * "����ģʽΪ�˻������۶���ֻ�����˻���"
                                                                       );
        }
        // �ۿ��������˻�Ӧ��Ϊ����
        if (discountflag
            && MathTool.lessThan(item.getNorigtaxmny(), new UFDouble(0))) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0104")
                                                                       * @res
                                                                       * "����ģʽΪ�˻������۶���ֻ�����˻���"
                                                                       );
        }
      }*/

      if (fsalemode.equals(SaleMode.MODE_COMMON.value())) {
        if (isExistWithdraw || isExistExchange) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4006011_0", "04006011-0492")/*
                                                        * ����ģʽΪ��ͨ�����۶����������˻�����
                                                        * ���ۿ��������в�����Ϊ����
                                                        * ���ۿ��������в���Ϊ������
                                                        */);
        }
      }
      else if (fsalemode.equals(SaleMode.MODE_RETURN.value())) {
        if (isExistExchange || isExistCommon) {

          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0104")/*
                                                                       * @res
                                                                       * "����ģʽΪ�˻������۶���ֻ�����˻���"
                                                                       */);
        }
      }
      else if (fsalemode.equals(SaleMode.MODE_RETURNCHANGE.value())) {
        if (isExistCommon) {
          // ��������ۿ������ϣ���˰�ϼ�С��0�������� add by quyt 20150318
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID(
                  "4006011_0",
                  "04006011-0493",
                  null,
                  new String[] {
                    nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                        "4006011_0", "04006011-0105")
                  })/* {0}�����ۿ��������в�����Ϊ�������ۿ��������в���Ϊ������ */);
        }
      }
      else if (fsalemode.equals(SaleMode.MODE_COMMONRETURN.value())) {
        if (isExistExchange) {

          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0106")/*
                                                                       * @res
                                                                       * "����ģʽΪ��ͨ+�˻������۶�������������"
                                                                       */);
        }
      }
    }
  }

  private IM30TranTypeService getTranTypeService() {
    if (this.tranTypeService == null) {
      this.tranTypeService =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
    }
    return this.tranTypeService;
  }

}
