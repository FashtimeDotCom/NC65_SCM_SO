package nc.bs.so.m38.maintain.rule.delete;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * @description
 * Ԥ����ɾ�����˻ص��ݺ�
 * @scene
 * ����Ԥ����ɾ����
 * @param
 * ��
 * @author ��־ΰ
 * @version v6.0
 */
public class DeleteBillAfterRule implements IRule<PreOrderVO> {

  @Override
  public void process(PreOrderVO[] vos) {

    this.returnBillCode(vos);

    // for(PreOrderVO vo : vos) {
    //
    // }
  }

  private void returnBillCode(PreOrderVO[] vos) {
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.PreOrder.getCode(), PreOrderHVO.VBILLCODE,
            PreOrderHVO.PK_GROUP, PreOrderHVO.PK_ORG, PreOrderHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.returnBillCode(vos);
  }

}
