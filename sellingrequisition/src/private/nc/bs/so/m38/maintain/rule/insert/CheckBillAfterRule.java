package nc.bs.so.m38.maintain.rule.insert;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * @description
 * Ԥ����������鵥�ݺ�Ψһ��
 * @scene
 * ����Ԥ�����������޸ı����
 * @param
 * ��
 */
public class CheckBillAfterRule implements IRule<PreOrderVO> {

  @Override
  public void process(PreOrderVO[] vos) {
    this.checkUniqueBillCode(vos);

  }

  /**
   * ��鵥�ݺ�Ψһ��
   * 
   * @param vos
   */
  private void checkUniqueBillCode(PreOrderVO[] vos) {
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.PreOrder.getCode(), PreOrderHVO.VBILLCODE,
            PreOrderHVO.PK_GROUP, PreOrderHVO.PK_ORG, PreOrderHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.checkUnique(vos);
  }

}
