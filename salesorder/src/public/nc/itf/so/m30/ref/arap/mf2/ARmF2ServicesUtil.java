package nc.itf.so.m30.ref.arap.mf2;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.arap.gathering.IArapGatheringBillPubQueryService;
import nc.pubitf.arap.gathering.IArapGatheringBillPubService;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.GatheringBillItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ����arap�ķ��񹤾���
 * 
 * @since 6.0
 * @version 2011-5-26 ����09:35:46
 * @author ��־ΰ
 */
public class ARmF2ServicesUtil {

  /**
   * �����տVO
   * 
   * @param AggGatheringBillVO[]
   */
  public static void insertGatheringBill(AggGatheringBillVO[] arg0)
      throws BusinessException {
    IArapGatheringBillPubService service =
        NCLocator.getInstance().lookup(IArapGatheringBillPubService.class);
    try {
      service.save(arg0);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * ��ѯ���Խ��к������տ��
   * 
   * @param pk_org ������֯
   * @param corigcurrencyid ԭ�ұ���
   * @return GatheringBillItemVO[]
   */
  public static GatheringBillItemVO[] queryGatheringBillItemCanVerify(
      String wherePartSql) throws BusinessException {
    IArapGatheringBillPubQueryService service =
        NCLocator.getInstance().lookup(IArapGatheringBillPubQueryService.class);
    GatheringBillItemVO[] gatheringItemVOs = null;
    try {
      gatheringItemVOs = service.queryGatheringBillItemCanVerify(wherePartSql);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
    return gatheringItemVOs;
  }

  /**
   * ��ѯ���Խ��к������տ���
   * 
   * @param pk_org ������֯
   * @param corigcurrencyid ԭ�ұ���
   * @return GatheringBillItemVO[]
   */
  public static UFDouble queryGatheringBillMoneyItemCanVerify(
      String wherePartSql) throws BusinessException {
    IArapGatheringBillPubQueryService service =
        NCLocator.getInstance().lookup(IArapGatheringBillPubQueryService.class);
    UFDouble ret = null;
    try {
      ret = service.queryGatheringBillMoneyItemCanVerify(wherePartSql);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
    return ret;
  }
}
