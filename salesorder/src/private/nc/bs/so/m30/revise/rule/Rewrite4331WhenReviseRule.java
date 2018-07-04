package nc.bs.so.m30.revise.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m30.so.m4331.PriceParaFor4331;
import nc.pubitf.so.m4331.so.m30.IDeliveryFor30;
import nc.pubitf.so.m4331.so.m30.IDeliveryPriceParaFor30;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * ���۶����޶�������д�������ļ۹���
 * @scene
 * ���۶����޶������
 * @param
 * map key�����۶����ӱ�id value�����۶����޶�ʱ�����ķ������ļ۸���ز���
 * @since 6.0
 * @version 2011-6-13 ����08:48:42
 * @author ��־ΰ
 */
public class Rewrite4331WhenReviseRule implements IRule<SaleOrderVO> {

  private Map<String, IDeliveryPriceParaFor30> map;

  @Override
  public void process(SaleOrderVO[] vos) {
    this.map = new HashMap<String, IDeliveryPriceParaFor30>();
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        String bid = bvo.getCsaleorderbid();
        IDeliveryPriceParaFor30 para = new PriceParaFor4331(bvo);
        this.map.put(bid, para);
      }
    }

    if (this.map.size() > 0) {
      IDeliveryFor30 service =
          NCLocator.getInstance().lookup(IDeliveryFor30.class);
      try {
        service.renovatePrice(this.map);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

}
