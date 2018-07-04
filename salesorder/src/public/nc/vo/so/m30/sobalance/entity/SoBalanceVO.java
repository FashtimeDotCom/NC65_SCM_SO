package nc.vo.so.m30.sobalance.entity;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = 
    "nc.vo.so.m30.sobalance.entity.SoBalanceHVO")
public class SoBalanceVO extends AbstractBill {

  /**
   *������־���������տ�ʱ���տ���Ϣͨ������Ӧ�յ�����ȡ��GatheringAddAfterListenerAction����
   *��ʱͨ��listenerflag����ʶSoBananceVO�Ƿ�������Ӧ�գ��������������Ӧ�գ���ô�޸ı����տ
   *ʱ�Ͳ��ý��ж����տ������˰�ϼ�֮��ıȽϣ���������տ�ʱ���ĵ��ǽ����Ϣ����ҵ�����ǲ������޸�
   *�����Ϣ�ģ���ô��Ӧ����߻����У����ƣ�������޸ĵĲ��ǽ����Ϣ����ô�����տ�ʱ��������ġ�
   */
  private UFBoolean listenerflag;
  
  public static final String ENTITYNAME = "so.so_balance";

  private static final long serialVersionUID = 3914562600056273535L;

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(SoBalanceVO.ENTITYNAME);
    return billMeta;
  }

  @Override
  public SoBalanceHVO getParentVO() {
    return (SoBalanceHVO) super.getParentVO();
  }

  public void setParentVO(SoBalanceHVO headVO) {
    this.setParent(headVO);
  }

  @Override
  public SoBalanceBVO[] getChildrenVO() {
    return (SoBalanceBVO[]) super.getChildrenVO();
  }

  public void setChildrenVO(SoBalanceBVO[] bodyVO) {
    super.setChildrenVO(bodyVO);
  }
  
  public UFBoolean getListenerflag() {
    return listenerflag;
  }

  public void setListenerflag(UFBoolean listenerflag) {
    this.listenerflag = listenerflag;
  }

}
