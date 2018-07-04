package nc.pubimpl.so.sobalance.arap.verify;

import java.util.Collection;
import java.util.HashMap;

import nc.pubitf.arap.pub.IArap4VerifyQryBill;
import nc.vo.arap.pfflow.ArapBillMapVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * IArap4VerifyQryBill�ӿ�ʵ�֣��ṩ������߶����տ������ϵ��ѯ����Arap������
 * <p>����Map<�տ��Ϣ,List<Ӧ�յ���Ϣ>></p>
 * <p>����Map<Ӧ�յ���Ϣ,List<�տ��Ϣ>></p>
 * 
 * @since 6.0
 * @version 2011-4-11 ����10:14:47
 * @author ��־ΰ
 */
public class SoBalance4VerifyQryBillImpl implements IArap4VerifyQryBill {

  /**
   * 1.processSK->processRush+processVerify
   * 2.processYS->processRush+processVerify
   * <p>ע�������������۶���������Ƽ�ע��;��ͨ��,�������̺���Ӧ�յ��Ĺ��̣�
   * �������,�������൱��ע�͵�����,�����൱��ע�͵ĺ���;</p>
   */
  @Override
  public HashMap<ArapBillMapVO, Collection<ArapBillMapVO>> queryArapBillmap(
      ArapBillMapVO[] arVOs) throws BusinessException {
    HashMap<ArapBillMapVO, Collection<ArapBillMapVO>> map = null;
    try {
      map = new SoBalance4VerifyQryBillAction().queryArapBillmap(arVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return map;
  }
}
