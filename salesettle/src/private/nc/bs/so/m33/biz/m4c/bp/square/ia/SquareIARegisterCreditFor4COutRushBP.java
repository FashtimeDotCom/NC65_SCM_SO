package nc.bs.so.m33.biz.m4c.bp.square.ia;

import nc.itf.so.m33.ref.ia.mi5.IAI5For4CServicesUtil;
import nc.vo.ia.mi5.entity.I5BillVO;

/**
 * ����Գ崫������Ʒ����
 * 
 * @since 6.0
 * @version 2011-8-3 ����10:40:04
 * @author zhangcheng
 */
public class SquareIARegisterCreditFor4COutRushBP extends
    AbstractSquareIARegisterCreditFor4CBP {

  @Override
  protected void toIA(I5BillVO[] i5vos) {
    // ����I5�����۳��ⵥ������Ʒ�ӿ�
    IAI5For4CServicesUtil.insertI5ForSO4CIntransitForOutrush(i5vos);
  }

}
