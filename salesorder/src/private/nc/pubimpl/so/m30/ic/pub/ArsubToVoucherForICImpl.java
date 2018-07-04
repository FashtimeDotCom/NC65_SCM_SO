package nc.pubimpl.so.m30.ic.pub;

import nc.bs.so.pub.isolation.me.MEForSOInterfaceIsolate;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m30.ic.pub.ArsubToVoucherData;
import nc.pubitf.so.m30.ic.pub.IArsubToVoucherForIC;
import nc.vo.pub.BusinessException;

/**
 * ���۶����ṩ������������۳��ⵥ��ǩ��;������������ʱӰ��ͻ����õ���ƾ֤ʵ��
 * 
 * @since 6.3
 * @version 2014-06-30 19:14:52
 * @author ����
 */
public class ArsubToVoucherForICImpl implements IArsubToVoucherForIC {

  @Override
  public void onSaleOutSign(ArsubToVoucherData[] data) throws BusinessException {
    if (!SysInitGroupQuery.isMeEnabled()) {
      return;
    }
    MEForSOInterfaceIsolate.onSaleOutSign(data);
  }

  @Override
  public void onSaleOutUnSign(ArsubToVoucherData[] data)
      throws BusinessException {
    if (!SysInitGroupQuery.isMeEnabled()) {
      return;
    }
    MEForSOInterfaceIsolate.onSaleOutUnSign(data);
  }

  @Override
  public void onWastageSign(ArsubToVoucherData[] data) throws BusinessException {
    if (!SysInitGroupQuery.isMeEnabled()) {
      return;
    }
    MEForSOInterfaceIsolate.onWastageSign(data);
  }

  @Override
  public void onWastageUnSign(ArsubToVoucherData[] data)
      throws BusinessException {
    if (!SysInitGroupQuery.isMeEnabled()) {
      return;
    }
    MEForSOInterfaceIsolate.onWastageUnSign(data);
  }

}
