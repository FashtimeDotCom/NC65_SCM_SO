package nc.bs.so.m33.biz.m4c.bp.square.ia;

import nc.itf.so.m33.ref.ia.mi5.IAI5For4CServicesUtil;
import nc.vo.ia.mi5.entity.I5BillVO;

/**
 * ����رմ�������Ʒ����
 * 
 * @since 6.0
 * @version 2011-8-3 ����10:39:48
 * @author zhangcheng
 */
public class SquareIARegisterCreditFor4COrderEndBP extends
    AbstractSquareIARegisterCreditFor4CBP {

  @Override
  protected void toIA(I5BillVO[] i5vos) {
    // ����I5�����۳��ⵥ������Ʒ�ӿ�
    IAI5For4CServicesUtil.insertI5ForSOSquareEnd(i5vos);
  }

}
