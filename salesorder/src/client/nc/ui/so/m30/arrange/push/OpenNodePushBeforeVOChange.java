package nc.ui.so.m30.arrange.push;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.pubitf.so.m30.pub.M30TranTypeUtil;
import nc.ui.pubapp.billref.push.IOpenNodePushBeforeVOChange;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30trantype.enumeration.DirectType;

/**
 * ����ֱ��VO����ǰ������
 * 
 * @since 6.0
 * @version 2011-3-4 ����01:26:58
 * @author ��־ΰ
 */
public class OpenNodePushBeforeVOChange implements IOpenNodePushBeforeVOChange {

  private String zyarrange;

  public String getZyarrange() {
    return this.zyarrange;
  }

  public void setZyarrange(String zyarrange) {
    this.zyarrange = zyarrange;
  }

  @Override
  public AggregatedValueObject[] processBeforeVOChange(
      AggregatedValueObject[] srcVos, String pk_org, String destBillType) {
    // ���˳��ɰ��ŵ���Դ���۶���
    AggregatedValueObject[] returnVOs = this.filterSrcVOs(srcVos);
    if (returnVOs == null || returnVOs.length == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006011_0", "04006011-0001")
      /*@res "��ǰû�пɰ��ŵĵ��ݻ����㰲����������ѡ�񵥾ݣ�"*/);
    }
    // ������������֯
    this.setDestPkOrg(srcVos, pk_org);

    // �빺�����ÿ����֯���ɹ��������òɹ���֯
    if (POBillType.PrayBill.getCode().equals(destBillType)) {
      this.setKeyValue(returnVOs, SaleOrderBVO.CSENDSTOCKORGVID, pk_org);
    }
    else if (POBillType.Order.getCode().equals(destBillType)) {
      this.setKeyValue(returnVOs, SaleOrderHVO.DEST_PK_ORG, pk_org);
    }
    else if (TOBillType.TransOrder.getCode().equals(destBillType)) {
    	 // ѡ�����֯�����뵥����֯һ��
//      ncm_changjr3_633_�Ĵ�ʡ��ɽ�и���ͨ��ũҩ�Ƽ����޹�˾���������������������֯�ڵĵ������ſ����ơ�
//      new ArrangeCheckOrg().checkInOutOrg(pk_org, srcVos);
//      ncm_changjr3
    }

    return returnVOs;
  }

  private void setDestPkOrg(AggregatedValueObject[] srcVos, String pk_org) {
    SaleOrderVO[] bills = (SaleOrderVO[]) srcVos;
    for (SaleOrderVO bill : bills) {
      SaleOrderHVO head = bill.getParentVO();
      head.setDest_pk_org(pk_org);
    }
  }

  private AggregatedValueObject[] filterSrcVOs(AggregatedValueObject[] srcVos) {
    SaleOrderVO[] vos = (SaleOrderVO[]) srcVos;
    List<AggregatedValueObject> rtnVOList =
        new ArrayList<AggregatedValueObject>();
    List<String> idList = new ArrayList<String>();
    for (SaleOrderVO vo : vos) {
      if (null != vo) {
        idList.add(vo.getParentVO().getCtrantypeid());
      }
    }
    if (idList.size() == 0) {
      return null;
    }
    String[] ctrantypeids = idList.toArray(new String[idList.size()]);
    Map<String, Integer> directMap =
        M30TranTypeUtil.getInstance().queryDirectType(ctrantypeids);
    if (directMap != null && directMap.size() > 0) {
      for (SaleOrderVO vo : vos) {
        String ctrantypeid = vo.getParentVO().getCtrantypeid();
        Integer directtype = directMap.get(ctrantypeid);
        // ��ֱ�˿ɲ�������
        if ("N".equals(this.zyarrange)
            && DirectType.DIRECTTRAN_NO.equalsValue(directtype)) {
          rtnVOList.add(vo);
        }
        // ֱ�����Ͳſ�ֱ�˰���
        else if ("ZYPO".equals(this.zyarrange)
            && DirectType.DIRECTTRAN_PO.equalsValue(directtype)) {
          rtnVOList.add(vo);
        }
        else if ("ZYTO".equals(this.zyarrange)
            && DirectType.DIRECTTRAN_TO.equalsValue(directtype)) {
          rtnVOList.add(vo);
        }
      }
    }
    return rtnVOList.toArray(new SaleOrderVO[rtnVOList.size()]);
  }

  private void setKeyValue(AggregatedValueObject[] srcVos, String key,
      String pk_org) {
    for (AggregatedValueObject vo : srcVos) {
      vo.getParentVO().setAttributeValue(key, pk_org);
    }
  }

}
