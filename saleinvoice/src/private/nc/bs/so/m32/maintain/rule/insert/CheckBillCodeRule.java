package nc.bs.so.m32.maintain.rule.insert;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * @description
 * ���۷�Ʊ�����
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ��
 *  ���ݺ��ظ���У��
 * </ul>
 * <p>
 * @scene
 * ���۷�Ʊ�������޸ı����
 * @param
 * ��
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-1-21 ����05:14:11
 */
public class CheckBillCodeRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.Invoice.getCode(),
            SaleInvoiceHVO.VBILLCODE, SaleInvoiceHVO.PK_GROUP,
            SaleInvoiceHVO.PK_ORG, SaleInvoiceHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.checkUnique(vos);

  }

}
