package nc.bs.so.m30.state.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30.ref.so.m33.SOm33ServicesUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * ���۶����ɱ�����رմ�������Ʒ�����ɴ���������Ʒ 
 * @scene
 * ���۶������γ��ⵥ����������Ʒ�ɱ�����رպ�
 * @param 
 * ��
 */
public class IACloseProcessRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {
    String[] ordBids =
        SoVoTools.getVOsOnlyValues(vos, SaleOrderBVO.CSALEORDERBID);
    SOm33ServicesUtil.process4CReg(ordBids);
  }

}
