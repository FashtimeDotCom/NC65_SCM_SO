package nc.vo.so.m30.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.ISaleOrgPubService;
import nc.itf.so.m30.ref.arap.mf2.ARmF2ServicesUtil;
import nc.itf.so.m30.sobalance.ISOBalanceQuery;
import nc.pubitf.so.m35.so.m30.IArsubToSaleorder;
import nc.vo.arap.basebill.IArapItemFieldVO;
import nc.vo.arap.gathering.GatheringBillItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.util.VOSortUtils;
import nc.vo.so.m30.entity.SaleOrderUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.m30.sobalance.util.AbstractGatheringKeyValue;
import nc.vo.so.m30.sobalance.util.GatherbillUtil;
import nc.vo.so.m30.sobalance.util.GatheringKeyValueForSaleOrder;
import nc.vo.so.m30.sobalance.util.SoBalanceUtil;
import nc.vo.so.m35.entity.ArsubViewVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;

/**
 * ������������
 * 
 * @since 6.0
 * @version 2011-7-22 ����10:30:19
 * @author ��־ΰ
 */
public class CashSaleUtil {

  public SoBalanceBVO[] createSoBalanceBVOs(SoBalanceHVO head,
      GatheringBillItemVO[] gatheringItemVOs, UFDouble canGatheringVerifyMny) {
    UFDouble canVerifyMnyTemp = canGatheringVerifyMny;
    List<GatheringBillItemVO> gitemvolist =
        new ArrayList<GatheringBillItemVO>();
    List<String> gitemBidList = new ArrayList<String>();
    for (GatheringBillItemVO itemVO : gatheringItemVOs) {
      gitemvolist.add(itemVO);
      gitemBidList.add(itemVO.getPk_gatheritem());
    }
    VOSortUtils.ascSort(gitemvolist, new String[] {
      IArapItemFieldVO.BILLDATE
    });
    Map<String, SoBalanceViewVO> balanceViewMap =
        this.querySoBalanceViewByGatheringBillBodyIDs(gitemBidList
            .toArray(new String[gitemBidList.size()]));
    List<SoBalanceBVO> resultlist = new ArrayList<SoBalanceBVO>();
    ISaleOrgPubService service =
        NCLocator.getInstance().lookup(ISaleOrgPubService.class);
    try {
      String[] oids = service.getOIDArray(gitemvolist.size());
      GatheringBillItemVO[] gitemvos =
          gitemvolist.toArray(new GatheringBillItemVO[gitemvolist.size()]);
      for (int i = 0; i < gitemvolist.size(); i++) {
        SoBalanceBVO sobalancebvo = new SoBalanceBVO();
        resultlist.add(sobalancebvo);
        sobalancebvo.setCpaybillid(gitemvos[i].getPk_gatherbill());
        sobalancebvo.setCpaybillrowid(gitemvos[i].getPk_gatheritem());
        sobalancebvo.setVarbillcode(gitemvos[i].getBillno());
        sobalancebvo.setDarbilldate(gitemvos[i].getBilldate());
        sobalancebvo.setDarbalancedate(gitemvos[i].getBilldate());
        sobalancebvo.setCarorigcurrencyid(gitemvos[i].getPk_currtype());
        sobalancebvo.setCprodlineid(gitemvos[i].getProductline());
        sobalancebvo.setFibaltype(Integer
            .valueOf(SoBalanceType.SOBALANCE_ORDERBAL.getIntValue()));
        sobalancebvo.setBpreceiveflag(Integer.valueOf(1).equals(
            gitemvos[i].getPrepay()) ? UFBoolean.TRUE : UFBoolean.FALSE);
        // ----------
        sobalancebvo.setDr(Integer.valueOf(0));
        sobalancebvo.setStatus(VOStatus.NEW);
        sobalancebvo.setCsobalancebid(oids[i]);

        // �����н��(�տԭ�ҽ��)
        UFDouble norigarmny = gitemvos[i].getMoney_cr();
        sobalancebvo.setNorigarmny(norigarmny);
        // (Ԥռ��ԭ�����)
        UFDouble occupationmny = gitemvos[i].getOccupationmny();
        // �����������(�Ѷ����������)
        UFDouble totalorigordbalmny = MathTool.sub(norigarmny, occupationmny);
        // �������������
        SoBalanceViewVO view =
            balanceViewMap.get(gitemvos[i].getPk_gatheritem());
        UFDouble norigordbalmny = null;
        if (view != null
            && view.getHead().getCsaleorderid().equals(head.getCsaleorderid())) {
          norigordbalmny = view.getBody().getNorigordbalmny();
        }
        sobalancebvo.setNorigordbalmny(norigordbalmny);
        // �����������
        UFDouble norigotherbalmny =
            MathTool.sub(totalorigordbalmny, norigordbalmny);
        sobalancebvo.setNorigotherbalmny(norigotherbalmny);
        // �����տ�������
        if (norigordbalmny != null) {
          // �Ѻ͵�ǰ�տ�н�����������ϵ,�����ٴν�����ϵ���˴�Ĭ��ֵΪ0
          sobalancebvo.setNorigthisbalmny(UFDouble.ZERO_DBL);
        }
        else if (MathTool.absCompareTo(canVerifyMnyTemp, occupationmny) > 0) {
          sobalancebvo.setNorigthisbalmny(occupationmny);
          canVerifyMnyTemp = canVerifyMnyTemp.sub(occupationmny);
        }
        else {
          sobalancebvo.setNorigthisbalmny(canVerifyMnyTemp);
          // �����տ�����Ľ������������
          break;
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return resultlist.toArray(new SoBalanceBVO[resultlist.size()]);
  }

  /**
   * ����������SO30 "�������Ƿ񵯳���ϸ��������"Ϊ��ʱ���񷽷�
   * 
   * @param offparamap
   * @param bill ���۶���VO
   * @return SaleOrderUserObject
   */
  public SaleOrderUserObject processCashSaleKeepIn(
      Map<Integer, OffsetParaVO> offsetparamap, SaleOrderVO bill) {
    SaleOrderUserObject retUserObject = new SaleOrderUserObject();
    UFDouble totalOrigMny = bill.getParentVO().getNtotalorigmny();

    // 1.����ɳ�ֽ��
    String pk_group = bill.getParentVO().getPk_group();
    UFDouble sumSubMny =
        this.processOffset(pk_group, offsetparamap, retUserObject);

    // 2.������տ�������
    UFDouble canGatheringVerifyMny = MathTool.sub(totalOrigMny, sumSubMny);
    if (MathTool.compareTo(canGatheringVerifyMny, UFDouble.ZERO_DBL) == 0) {
      return retUserObject;
    }
    UFDouble sumGatheringVerifyMny =
        this.processGatheringVerify(canGatheringVerifyMny, bill, retUserObject);

    // 3.ʣ�����Ϊ���ζ����տ���
    UFDouble canOrderGatheringMny =
        MathTool.sub(canGatheringVerifyMny, sumGatheringVerifyMny);
    retUserObject.setThisGatheringMny(canOrderGatheringMny);
    return retUserObject;
  }

  /**
   * ���������������տ��������
   * 
   * @param canGatheringVerifyMny �ɺ�������
   * @param ordervo ���۶���VO
   * @param userObject �洢����VO
   * @return sumGatheringVerifyMny ��������
   */
  public UFDouble processGatheringVerify(UFDouble canGatheringVerifyMny,
      SaleOrderVO ordervo, SaleOrderUserObject userObject) {
    UFDouble sumGatheringVerifyMny = UFDouble.ZERO_DBL;
    IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(ordervo);
    AbstractGatheringKeyValue gatheringKeyValue =
        new GatheringKeyValueForSaleOrder(keyValue);
    String wherePart =
        GatherbillUtil.getInstance()
            .getWherePartSqlCanVerify(gatheringKeyValue);
    // --�����տ�����տ�в�ѯ
    GatheringBillItemVO[] gatheringItemVOs = null;
    try {
      gatheringItemVOs =
          ARmF2ServicesUtil.queryGatheringBillItemCanVerify(wherePart);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (gatheringItemVOs == null || gatheringItemVOs.length == 0) {
      return sumGatheringVerifyMny;
    }
    // --���������ϵ��ͷ
    SoBalanceHVO head =
        SoBalanceUtil.getInstance().createSoBalanceHVOBySaleOrderVO(ordervo);
    head.setDr(Integer.valueOf(0));
    head.setStatus(VOStatus.NEW);
    SoBalanceVO newsobalancevo = new SoBalanceVO();
    newsobalancevo.setParentVO(head);
    // --���������ϵ����
    SoBalanceBVO[] bodys =
        this.createSoBalanceBVOs(head, gatheringItemVOs, canGatheringVerifyMny);
    newsobalancevo.setChildrenVO(bodys);
    // --�洢��ϵVO
    userObject.setSoBalanceVO(newsobalancevo);
    // ���ؽ�����ϵ���
    sumGatheringVerifyMny =
        SoBalanceUtil.getInstance().caculateSoBalanceTotalBodymny(
            newsobalancevo, SoBalanceBVO.NORIGTHISBALMNY);

    return sumGatheringVerifyMny;
  }

  /**
   * ���������������տ��������
   * 
   * @param pk_group ����
   * @param offparamap
   * @param userObject �洢���ó��VO
   * @return sumSubMny ���ó�ֽ��
   */
  public UFDouble processOffset(String pk_group,
      Map<Integer, OffsetParaVO> offparamap, SaleOrderUserObject userObject) {
    IArsubToSaleorder arsubquery =
        NCLocator.getInstance().lookup(IArsubToSaleorder.class);
    ArsubViewVO[] arsubviewvos = null;
    if (offparamap.size() > 0) {
      try {
        arsubviewvos = arsubquery.getAutoOffsetArSubVO(pk_group, offparamap);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }

    // ������ó��VO
    userObject.setArsubViews(arsubviewvos);

    // --�����ۼƳ�ֽ��
    UFDouble sumSubMny = UFDouble.ZERO_DBL;
    if (arsubviewvos != null && arsubviewvos.length > 0) {
      for (ArsubViewVO viewvo : arsubviewvos) {
        sumSubMny = MathTool.add(sumSubMny, viewvo.getItem().getNthissubmny());
      }
    }

    return sumSubMny;
  }

  public Map<String, SoBalanceViewVO> querySoBalanceViewByGatheringBillBodyIDs(
      String[] paybillrowids) {
    Map<String, SoBalanceViewVO> retMap =
        new HashMap<String, SoBalanceViewVO>();
    if (paybillrowids == null || paybillrowids.length == 0) {
      return retMap;
    }
    ISOBalanceQuery queryservice =
        NCLocator.getInstance().lookup(ISOBalanceQuery.class);
    SoBalanceViewVO[] views = null;
    try {
      views =
          queryservice.querySoBalanceViewByGatheringBillBodyIDs(paybillrowids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (views != null && views.length > 0) {
      for (SoBalanceViewVO view : views) {
        retMap.put(view.getBody().getCpaybillrowid(), view);
      }
    }
    return retMap;
  }
}
