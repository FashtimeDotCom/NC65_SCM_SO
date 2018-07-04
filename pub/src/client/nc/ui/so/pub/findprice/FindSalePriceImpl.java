package nc.ui.so.pub.findprice;

import nc.itf.so.pub.findprice.ISOFindPrice;
import nc.itf.so.pub.ref.price.PriceServicesUtil;
import nc.vo.price.pub.entity.FindPriceParaVO;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.pub.BusinessException;

/**
 * ѯ�۲������ʵ����:û��Ӧ��ɾ������
 * 
 */
public class FindSalePriceImpl implements ISOFindPrice {
  @Override
  public FindPriceResultVO[] findPrice(FindPriceParaVO[] findPriceParas,
      String saleOrg) throws BusinessException {
    // �ͻ����������Զ���ѯ�۷�ʽ
    return PriceServicesUtil.findPrice(findPriceParas, saleOrg);
  }
}
