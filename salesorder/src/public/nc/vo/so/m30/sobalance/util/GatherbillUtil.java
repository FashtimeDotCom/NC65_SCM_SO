package nc.vo.so.m30.sobalance.util;

import java.util.HashSet;
import java.util.Set;

import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.GatheringBillItemVO;
import nc.vo.arap.gathering.GatheringBillVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

import nc.itf.so.m30trantype.IM30TranTypeService;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 
 * @author gdsjw
 */
public class GatherbillUtil {

  private static GatherbillUtil instance = new GatherbillUtil();

  private Set<String> paraSet;

  private GatherbillUtil() {
    // ˽�й�����
  }

  public static GatherbillUtil getInstance() {
    return GatherbillUtil.instance;
  }

  public void checkBeforeGathering(SaleOrderVO ordvo) throws BusinessException {
    // �رղ����տ�
    SaleOrderHVO head = ordvo.getParentVO();
    Integer fstatus = head.getFstatusflag();
    if (fstatus != null
        && fstatus.intValue() == BillStatus.CLOSED.getIntValue()) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0228")/* @res "�����ѹرգ���������ж����տ" */);
    }

    SaleOrderBVO[] bodyVOs = ordvo.getChildrenVO();
    if (bodyVOs.length <= 0) {
      return;
    }
    // ��������ͬ��Ӧ����֯�����տ�
    String carorg = null;
    for (SaleOrderBVO bodyvo : bodyVOs) {
      // ȥ����
      if (bodyvo.getCmaterialid() == null) {
        continue;
      }
      // ��¼��һ�в�����֯
      if (carorg == null) {
        carorg = bodyvo.getCarorgid();
      }
      // �Ա�
      else if (!carorg.equals(bodyvo.getCarorgid())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0107")/* @res
                                                      * "���۶���������ڶ��Ӧ����֯�����������տ" */);
      }
    }

    // ���ֲ����տ�
    UFDouble ntotalorigmny = head.getNtotalorigmny();
    if (MathTool.compareTo(ntotalorigmny, UFDouble.ZERO_DBL) < 0) {
      String pk_group = head.getPk_group();
      String vtrantypecode = head.getVtrantypecode();
      M30TranTypeVO trantype = null;
      IM30TranTypeService service =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      trantype = service.queryTranType(pk_group, vtrantypecode);
      UFBoolean bredorderpay = trantype.getBredorderpay();
      if (bredorderpay == null || !bredorderpay.booleanValue()) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0229")/* @res
                                                      * "���۶�����֧�ֺ����տ��������ж����տ" */);
      }
    }

    this.checkCanGathering(new SaleOrderHVO[] {
      head
    });
  }

  public void checkBeforeGathering(SaleOrderVO ordvo, UFDouble thisGatheringMny)
      throws BusinessException {
    // ���Ų�ͬ�����տ�
    UFDouble ntotalorigmny = ordvo.getParentVO().getNtotalorigmny();
    if (MathTool.isDiffSign(ntotalorigmny, thisGatheringMny)) {
      throw new BusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006011_0", "04006011-0424")/* @res "���۶�����˰�ϼ����տ����������Ų�һ�£�" */);
    }
    // ���������
    this.checkBeforeGathering(ordvo);
  }

  /**
   * �������տ����ܷ��ٴ��տ�
   */
  public void checkCanGathering(SaleOrderHVO[] heads) {
    /* �տ��޶����Ԥ�ա��򹴡�ʱ��������ͷ�����տ��޶�=ʵ��Ԥ�տ���ʱ�����ٴ��տ�
     * �տ��޶����Ԥ�ա����򹴡�ʱ��������ͷ�ļ�˰�ϼ� = ʵ���տ���ʱ�����ٴ��տ� */
    StringBuilder errMsg = new StringBuilder();
    for (SaleOrderHVO head : heads) {
      boolean bpreceiveflag =
          head.getBpreceiveflag() == null ? false : head.getBpreceiveflag()
              .booleanValue();
      UFDouble npreceivequota = head.getNpreceivequota();
      UFDouble npreceivemny = head.getNpreceivemny();
      UFDouble nthisreceivemny = head.getNthisreceivemny();
      UFDouble ntotalorigmny = head.getNtotalorigmny();
      if (bpreceiveflag
          && MathTool.absCompareTo(npreceivequota, npreceivemny) == 0) {
//        errMsg.append(
//            NCLangResOnserver.getInstance().getStrByID("4006011_0",
//                "04006011-0283")/* ���ݺţ� */).append(head.getVbillcode());
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0425")/* ʵ��Ԥ�տ����Ѵﵽ�����տ��޶�,���������տ */);
        errMsg.append("\n");
      }
      UFDouble npreceivemny_new = MathTool.add(npreceivemny, nthisreceivemny);
      if (bpreceiveflag
          && MathTool.absCompareTo(npreceivemny_new, npreceivequota) > 0) {
//        errMsg.append(
//            NCLangResOnserver.getInstance().getStrByID("4006011_0",
//                "04006011-0283")/* ���ݺţ� */).append(head.getVbillcode());
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0426")/* �տ��޶����Ԥ��,ʵ��Ԥ�տ���ܴ��ڶ����տ��޶ */);
        errMsg.append("\n");
      }
      UFDouble nreceivedmny = head.getNreceivedmny();
      if (!bpreceiveflag
          && MathTool.absCompareTo(ntotalorigmny, nreceivedmny) == 0) {
//        errMsg.append(
//            NCLangResOnserver.getInstance().getStrByID("4006011_0",
//                "04006011-0283")/* ���ݺţ� */).append(head.getVbillcode());
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0427")/* ʵ���տ����Ѵﵽ������˰�ϼ�,���������տ */);
        errMsg.append("\n");
      }
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

  public void checkGatheringbillAndSaleorderConsistent(SaleOrderVO saleordervo,
      AggGatheringBillVO gatheringbillvo) throws BusinessException {
    // ������༭���ֶΣ���Ʊ�ͻ������֡�Ӧ����֯
    String ccustomerid = saleordervo.getParentVO().getCcustomerid();
    String corigcurrencyid = saleordervo.getParentVO().getCorigcurrencyid();
    String cinvoicecustid = saleordervo.getParentVO().getCinvoicecustid();
    String carorgid = saleordervo.getChildrenVO()[0].getCarorgid();
    GatheringBillVO parent = (GatheringBillVO) gatheringbillvo.getParentVO();
    GatheringBillItemVO[] items =
        (GatheringBillItemVO[]) gatheringbillvo.getChildrenVO();
    if (!parent.getPk_org().equals(carorgid)) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0196")/* @res "�������޸�Ӧ����֯��" */);
    }
    for (int i = 0; i < items.length; i++) {
      // TODO �����ͻ��Ƿ������޸Ĵ��飬�Ȱ������ϵ���
      if (!ccustomerid.equals(items[i].getOrdercubasdoc())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0193")/* @res "�������޸Ķ����ͻ���" */);
      }
      if (!corigcurrencyid.equals(items[i].getPk_currtype())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0195")/* @res "�������޸ı��֡�" */);
      }
      if (!cinvoicecustid.equals(items[i].getCustomer())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0194")/* @res "�������޸Ŀ�Ʊ�ͻ���" */);
      }
    }

  }

  public void checkGatheringbillAndSoBalanceConsistent(
      SoBalanceViewVO[] sobalanceview, AggGatheringBillVO gatheringbillvo,
      String paybillrow) throws BusinessException {
    // ������༭���ֶΣ���Ʊ�ͻ������֡�Ӧ����֯
    // �������ʱ�����Ķ������������ϵ���տ�붩�������ֶο��ܲ�һ�£����������Ҫ���ݹ�ϵ���ͽ��������ж�
    GatheringBillVO parent = (GatheringBillVO) gatheringbillvo.getParentVO();
    GatheringBillItemVO[] items =
        (GatheringBillItemVO[]) gatheringbillvo.getChildrenVO();
    for (SoBalanceViewVO viewvo : sobalanceview) {
      SoBalanceHVO sobalancehvo = viewvo.getHead();
      // SoBalanceBVO sobalancebvo = viewvo.getBody();
      String ccustomerid = sobalancehvo.getCcustomerid();
      String cinvoicecustid = sobalancehvo.getCinvoicecustid();
      String corigcurrencyid = sobalancehvo.getCorigcurrencyid();
      String carorgid = sobalancehvo.getCarorgid();
      SoBalanceBVO sobalancebvo = viewvo.getBody();
      int fibaltype = sobalancebvo.getFibaltype().intValue();
      if (SoBalanceType.SOBALANCE_ORDERBAL.getIntValue() == fibaltype) {
        if (!parent.getPk_org().equals(carorgid)) {
          throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0196")/* @res
                                                                       * "�������޸�Ӧ����֯��" */);
        }
        for (int i = 0; i < items.length; i++) {
          if (!items[i].getPk_gatheritem().equals(paybillrow)) {
            continue;
          }
          if (!cinvoicecustid.equals(items[i].getCustomer())) {
            throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006011_0", "04006011-0194")/* @res
                                                                         * "�������޸Ŀ�Ʊ�ͻ���" */);
          }
          if (!corigcurrencyid.equals(items[i].getPk_currtype())) {
            throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006011_0", "04006011-0195")/* @res
                                                                         * "�������޸ı��֡�" */);
          }
          if (!ccustomerid.equals(items[i].getOrdercubasdoc())) {
            throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006011_0", "04006011-0193")/* @res
                                                                         * "�������޸Ķ����ͻ���" */);
          }
        }
      }
    }
  }

  public void checkSoBalanceAndSaleorderConsistent(SoBalanceVO bill,
      SaleOrderVO saleordervo) {
    SoBalanceHVO sobalancehvo = bill.getParentVO();
    String cinvoicecustid = sobalancehvo.getCinvoicecustid();
    String corigcurrencyid = sobalancehvo.getCorigcurrencyid();
    UFDouble ntotalorigtaxmny = sobalancehvo.getNtotalorigtaxmny();

    if (!corigcurrencyid.equals(saleordervo.getParentVO().getCorigcurrencyid())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0108")/* @res
                                                        * "�����տ������ϵ�붩�����ݲ�һ�£���ˢ����������ҵ��" */);
    }
    if (!cinvoicecustid.equals(saleordervo.getParentVO().getCinvoicecustid())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0108")/* @res
                                                        * "�����տ������ϵ�붩�����ݲ�һ�£���ˢ����������ҵ��" */);
    }
    if (MathTool.compareTo(ntotalorigtaxmny, saleordervo.getParentVO()
        .getNtotalorigmny()) != 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0108")/* @res
                                                        * "�����տ������ϵ�붩�����ݲ�һ�£���ˢ����������ҵ��" */);
    }

  }

  public SoBalanceBVO createBalanceBVOByGatheringBillItemForManual(
      GatheringBillItemVO gatheringbillitemvo, SoBalanceVO sobalancevo) {
    UFDouble ntotalorigtaxmny = sobalancevo.getParentVO().getNtotalorigtaxmny();
    UFDouble totalbodymny =
        SoBalanceUtil.getInstance().caculateSoBalanceTotalBodymnyForManual(
            sobalancevo);

    UFDouble diffmny = MathTool.sub(ntotalorigtaxmny, totalbodymny);
    if (MathTool.compareTo(ntotalorigtaxmny, UFDouble.ZERO_DBL) > 0) {
      if (MathTool.compareTo(diffmny, UFDouble.ZERO_DBL) <= 0) {
        diffmny = UFDouble.ZERO_DBL;
      }
    }
    else {
      if (MathTool.compareTo(ntotalorigtaxmny, UFDouble.ZERO_DBL) < 0) {
        if (MathTool.compareTo(diffmny, UFDouble.ZERO_DBL) >= 0) {
          diffmny = UFDouble.ZERO_DBL;
        }
      }
      else {
        diffmny = UFDouble.ZERO_DBL;
      }
    }
    SoBalanceBVO bodyvo = null;

    if (MathTool.absCompareTo(diffmny, UFDouble.ZERO_DBL) > 0) {
      bodyvo = new SoBalanceBVO();
      bodyvo.setCpaybillid(gatheringbillitemvo.getPk_gatherbill());
      bodyvo.setCpaybillrowid(gatheringbillitemvo.getPk_gatheritem());
      bodyvo.setVarbillcode(gatheringbillitemvo.getBillno());
      bodyvo.setDarbilldate(gatheringbillitemvo.getBilldate());
      bodyvo.setDarbalancedate(gatheringbillitemvo.getBilldate());
      bodyvo.setCarorigcurrencyid(gatheringbillitemvo.getPk_currtype());
      bodyvo.setCprodlineid(gatheringbillitemvo.getProductline());
      bodyvo.setNorigarmny(gatheringbillitemvo.getMoney_cr());
      // �տ��"Ԥռ��ԭ�����"Ϊר��Ϊ���۶����ɽ���������ϵ�������ģ�
      // ��������Ͷ�����������Ӱ��,ʵ��ֵ = �տԭ�ҽ�� - ���������� - �����������
      if (MathTool
          .absCompareTo(diffmny, gatheringbillitemvo.getOccupationmny()) > 0) {
        bodyvo.setNorigthisbalmny(gatheringbillitemvo.getOccupationmny());
      }
      else {
        bodyvo.setNorigthisbalmny(diffmny);
      }
      bodyvo.setFibaltype(Integer.valueOf(SoBalanceType.SOBALANCE_ORDERBAL
          .getIntValue()));
      bodyvo.setBpreceiveflag(Integer.valueOf(1).equals(
          gatheringbillitemvo.getPrepay()) ? UFBoolean.TRUE : UFBoolean.FALSE);
    }

    return bodyvo;
  }

  /**
   * ��õ�ǰĬ���տ�Ľ��
   */
  public UFDouble getDefaultGatheringMny(SaleOrderVO ordvo) {

    /* �տ��޶����Ԥ�ա��򹴡�ʱ��Ĭ���տ���=������ͷ�����տ��޶�-ʵ��Ԥ�տ��
     * �տ��޶����Ԥ�ա����򹴡�ʱ��
     * �����տ��޶�Ϊ�ջ��ߵ���0��Ĭ���տ���=������ͷ�ļ�˰�ϼ�-ʵ���տ��
     * �����տ��޶Ϊ���Ҳ�����0��Ĭ���տ���=������ͷ�Ķ����տ��޶�-ʵ���տ�� */
    SaleOrderHVO head = ordvo.getParentVO();
    // �տ��޶����Ԥ��
    UFBoolean preceiveflag = head.getBpreceiveflag();
    // �����տ��޶�
    UFDouble preceivequota = head.getNpreceivequota();
    // ʵ��Ԥ�տ���
    UFDouble preceivemny = head.getNpreceivemny();
    // ʵ���տ���
    UFDouble receivedmny = head.getNreceivedmny();
    // ��˰�ϼ�
    UFDouble totalorigmny = head.getNtotalorigmny();
    UFDouble ret = UFDouble.ZERO_DBL;
    if (preceiveflag != null && preceiveflag.booleanValue()) {
      ret = MathTool.sub(preceivequota, preceivemny);
    }
    else if (MathTool.compareTo(preceivequota, UFDouble.ZERO_DBL) == 0) {
      ret = MathTool.sub(totalorigmny, receivedmny);
    }
    else {
      // ʵ���տ����Ѵ����տ��޶��ôĬ��ֵΪ0
      if (MathTool.compareTo(MathTool.abs(receivedmny),
          MathTool.abs(preceivequota)) > 0) {
        ret = UFDouble.ZERO_DBL;
      }
      else {
        ret = MathTool.sub(preceivequota, receivedmny);
      }
    }

    return ret;
  }

  public String getWherePartSqlCanVerify(AbstractGatheringKeyValue keyValue) {
    if (keyValue == null) {
      return "";
    }
    // keyValue.get
    // ��ʼ��SO11����
    this.initParaSO11(keyValue);
    StringBuilder sb = new StringBuilder();
    sb.append("and dr = 0");
    UFDouble money = keyValue.getMoney();
    if (MathTool.compareTo(money, UFDouble.ZERO_DBL) > 0) {
      sb.append(" and isnull(occupationmny,0) > 0");
    }
    else {
      sb.append(" and isnull(occupationmny,0) < 0");
    }
    // �̶���������������֯
    String carorgid = keyValue.getPk_org();
    if (carorgid == null) {
      sb.append(" and");
      sb.append(" pk_org = '~'");
    }
    else {
      sb.append(" and");
      sb.append(" pk_org = '");
      sb.append(carorgid);
      sb.append("'");
    }
    // �̶�������������
    String corigcurrencyid = keyValue.getPk_currtype();
    if (corigcurrencyid == null) {
      sb.append(" and");
      sb.append(" pk_currtype = '~'");
    }
    else {
      sb.append(" and");
      sb.append(" pk_currtype = '");
      sb.append(corigcurrencyid);
      sb.append("'");
    }
    // �̶�����������Ʊ�ͻ�
    String cinvoicecustid = keyValue.getCustomer();
    if (this.paraSet.contains(SoBalanceHVO.CINVOICECUSTID)) {
      if (cinvoicecustid == null) {
        sb.append(" and");
        sb.append(" customer = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" customer = '");
        sb.append(cinvoicecustid);
        sb.append("'");
      }
    }
    String pk_org = keyValue.getSo_org();
    if (this.paraSet.contains(SoBalanceHVO.PK_ORG)) {
      if (pk_org == null) {
        sb.append(" and");
        sb.append(" so_org = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" so_org = '");
        sb.append(pk_org);
        sb.append("'");
      }
    }
    String vtrantypecode = keyValue.getSo_ordertype();
    if (this.paraSet.contains(SoBalanceHVO.VTRANTYPECODE)) {
      if (vtrantypecode == null) {
        sb.append(" and");
        sb.append(" so_ordertype = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" so_ordertype = '");
        sb.append(vtrantypecode);
        sb.append("'");
      }
    }
    String csettleorgid = keyValue.getSett_org();
    if (this.paraSet.contains(SaleOrderBVO.CSETTLEORGID)) {
      if (csettleorgid == null) {
        sb.append(" and");
        sb.append(" sett_org = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" sett_org = '");
        sb.append(csettleorgid);
        sb.append("'");
      }
    }
    String cdeptid = keyValue.getSo_deptid();
    if (this.paraSet.contains(SoBalanceHVO.CDEPTID)) {
      if (cdeptid == null) {
        sb.append(" and");
        sb.append(" so_deptid  = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" so_deptid = '");
        sb.append(cdeptid);
        sb.append("'");
      }
    }
    String cemployeeid = keyValue.getSo_psndoc();
    if (this.paraSet.contains(SoBalanceHVO.CEMPLOYEEID)) {
      if (cemployeeid == null) {
        sb.append(" and");
        sb.append(" so_psndoc = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" so_psndoc = '");
        sb.append(cemployeeid);
        sb.append("'");
      }
    }
    String cchanneltypeid = keyValue.getSo_transtype();
    if (this.paraSet.contains(SoBalanceHVO.CCHANNELTYPEID)) {
      if (cchanneltypeid == null) {
        sb.append(" and");
        sb.append(" so_transtype = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" so_transtype = '");
        sb.append(cchanneltypeid);
        sb.append("'");
      }
    }
    String ccusttomerid = keyValue.getOrdercubasdoc();
    if (this.paraSet.contains(SoBalanceHVO.CCUSTOMERID)) {
      if (ccusttomerid == null) {
        sb.append(" and");
        sb.append(" ordercubasdoc = '~'");
      }
      else {
        sb.append(" and");
        sb.append(" ordercubasdoc = '");
        sb.append(ccusttomerid);
        sb.append("'");
      }
    }
    // ���۶�������һ�еĲ�Ʒ����ƥ���տ��Ʒ�ߣ���ô�����������տ����
    String[] cprodlineids = keyValue.getProductlines();
    if (this.paraSet.contains(SoBalanceBVO.CPRODLINEID)) {
      Set<String> notNullSet = new HashSet<String>();
      boolean isNull = false;
      for (String cprodlineid : cprodlineids) {
        if (cprodlineid == null) {
          isNull = true;
        }
        else {
          notNullSet.add(cprodlineid);
        }
      }
      sb.append("and (");
      StringBuilder sbProductline = new StringBuilder();
      if (notNullSet.size() > 0) {
        IDExQueryBuilder iq =
            new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
        sbProductline.append(iq.buildSQL("productline",
            notNullSet.toArray(new String[notNullSet.size()])));
      }
      if (isNull) {
        if (sbProductline.length() > 0) {
          sbProductline.append(" or");
        }
        sbProductline.append(" productline = '~' ");
      }
      sb.append(sbProductline.toString());
      sb.append(")");
    }
    return sb.toString();
  }

  /**
   * VO����ǰ׼��SaleOrderVO�������տ�ֻ����һ���տ��
   */
  public SaleOrderVO prepareOrderBeforeChangeData(SaleOrderVO ordvo) {
    return this.prepareOrderBeforeChangeData(ordvo, null);
  }

  /**
   * VO����ǰ׼��SaleOrderVO������¼����տ�������տ�ֻ����һ���տ��
   */
  public SaleOrderVO prepareOrderByGatherMny(SaleOrderVO ordvo,
      UFDouble thisgathermny) {
    return this.prepareOrderBeforeChangeData(ordvo, thisgathermny);
  }

  private void initParaSO11(AbstractGatheringKeyValue keyValue) {
    this.paraSet = new HashSet<String>();
    String[] paras = null;

    String pk_saleorg = keyValue.getSo_org();
    if (pk_saleorg != null) {
      paras = SOSysParaInitUtil.getSO11(pk_saleorg);
    }

    if (paras != null && paras.length > 0) {
      for (String para : paras) {
        this.paraSet.add(para);
      }
    }
  }

  private SaleOrderVO prepareOrderBeforeChangeData(SaleOrderVO ordvo,
      UFDouble thisgathermny) {

    SaleOrderVO retVO = new SaleOrderVO();
    SaleOrderHVO newHead = (SaleOrderHVO) ordvo.getParentVO().clone();
    SaleOrderBVO newBody = (SaleOrderBVO) ordvo.getChildrenVO()[0].clone();
    /** ���������Ͳ����õ�һ���տ�� */

    // ԭ����˰���
    UFDouble sumOrigmny = UFDouble.ZERO_DBL;
    // ԭ�Ҽ�˰�ϼ�
    UFDouble sumOrigtaxmny = UFDouble.ZERO_DBL;
    // ����˰��
    UFDouble sumtax = UFDouble.ZERO_DBL;
    // ���Ҽ�˰�ϼ�
    UFDouble sumtaxmny = UFDouble.ZERO_DBL;
    // ������˰���
    UFDouble summny = UFDouble.ZERO_DBL;
    // ���ű��Ҽ�˰�ϼ�
    UFDouble groupsumtaxmny = UFDouble.ZERO_DBL;
    // ���ű�����˰���
    UFDouble groupsummny = UFDouble.ZERO_DBL;
    // ȫ�ֱ��Ҽ�˰�ϼ�
    UFDouble globalsumtaxmny = UFDouble.ZERO_DBL;
    // ȫ�ֱ�����˰���
    UFDouble globalsummny = UFDouble.ZERO_DBL;

    SaleOrderBVO[] bodys = ordvo.getChildrenVO();
    for (SaleOrderBVO body : bodys) {
      // ��Ʒ����Ӧ��
      if (body.getBlargessflag().booleanValue()) {
        continue;
      }
      // sumOrigtax = MathTool.add(sumOrigtax, body.getNorigtax());
      sumOrigmny = MathTool.add(sumOrigmny, body.getNorigmny());
      sumOrigtaxmny = MathTool.add(sumOrigtaxmny, body.getNorigtaxmny());
      sumtaxmny = MathTool.add(sumtaxmny, body.getNtaxmny());
      sumtax = MathTool.add(sumtax, body.getNtax());
      summny = MathTool.add(summny, body.getNmny());
      groupsumtaxmny = MathTool.add(groupsumtaxmny, body.getNgrouptaxmny());
      groupsummny = MathTool.add(groupsummny, body.getNgroupmny());
      globalsumtaxmny = MathTool.add(globalsumtaxmny, body.getNglobaltaxmny());
      globalsummny = MathTool.add(globalsummny, body.getNglobalmny());
    }

    // ԭ��
    newBody.setNorigtaxmny(sumOrigtaxmny);
    // newBody.setNorigtax(sumOrigtax);
    newBody.setNorigmny(sumOrigmny);

    // ����
    newBody.setNtaxmny(sumtaxmny);
    newBody.setNtax(sumtax);
    newBody.setNmny(summny);

    // ���š�ȫ�ֱ���
    newBody.setNgrouptaxmny(groupsumtaxmny);
    newBody.setNgroupmny(groupsummny);
    newBody.setNglobaltaxmny(globalsumtaxmny);
    newBody.setNglobalmny(globalsummny);

    // ��װSaleOrderVO
    retVO.setParentVO(newHead);
    retVO.setChildrenVO(new SaleOrderBVO[] {
      newBody
    });

    // ȡ�����۱�����
    UFDouble oldexchgrate = newBody.getNexchangerate();
    IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(retVO);
    SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
    int[] rows = new int[] {
      0
    };
    exraterule.calcCurrentBodyExchangeRates(rows);
    UFDouble newexchgrate = retVO.getChildrenVO()[0].getNexchangerate();
    if (MathTool.isZero(newexchgrate)) {
      retVO.getChildrenVO()[0].setNexchangerate(oldexchgrate);
    }

    SaleOrderVOCalculator calculator = new SaleOrderVOCalculator(retVO);
    // Ĭ���տ��� = �����տ��˰�ϼ� - ���տ�
    UFDouble nsumtaxmny = newHead.getNtotalorigmny();
    if (null == thisgathermny) {
      thisgathermny = this.getDefaultGatheringMny(retVO);
    }
    retVO.getParentVO().setNtotalorigmny(thisgathermny);
    retVO.getChildrenVO()[0].setNorigtaxmny(thisgathermny);
    // �ǵ�һ���տ�����¼���
    if (MathTool.compareTo(thisgathermny, nsumtaxmny) != 0) {
      calculator.calculate(rows, SaleOrderBVO.NORIGTAXMNY);
    }
    // ������ʲ�һ��ʱȡ�����۱����ʼ���
    else if (!MathTool.isZero(newexchgrate)
        && MathTool.compareTo(oldexchgrate, newexchgrate) != 0) {
      calculator.calculate(rows, SaleOrderBVO.NEXCHANGERATE);
    }

    return retVO;

  }
}
