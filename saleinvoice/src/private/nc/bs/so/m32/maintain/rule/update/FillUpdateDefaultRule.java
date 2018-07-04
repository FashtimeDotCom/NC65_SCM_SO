package nc.bs.so.m32.maintain.rule.update;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.bs.so.pub.rule.rowno.SORowNoUtil;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.HeadTotalCalcUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * ���۷�Ʊ�޸ı������ǰ�޸ı���ʱ���Ĭ��ֵ
 * @scene
 * ���۷�Ʊ�޸ı���ǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-8-15 ����09:07:02
 * @author ô��
 */
public class FillUpdateDefaultRule implements ICompareRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos, SaleInvoiceVO[] originVOs) {

    for (SaleInvoiceVO invoicevo : vos) {
      // ����޸ı���ʱĬ��ֵ
      this.setHeadDefault(invoicevo);
      this.setBodyDefault(invoicevo);
      // ����к�
      SORowNoUtil.fillupRowNo(invoicevo);
    }
    // ��䵥�ݺ�
    this.setBillCode(vos, originVOs);

  }

  /**
   * ���������������޸ı���ǰ���ݺŴ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param originVOs
   *          <p>
   * @author ��ӱ�
   * @time 2010-1-21 ����06:59:14
   */
  private void setBillCode(SaleInvoiceVO[] vos, SaleInvoiceVO[] originVOs) {
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.Invoice.getCode(),
            SaleInvoiceHVO.VBILLCODE, SaleInvoiceHVO.PK_GROUP,
            SaleInvoiceHVO.PK_ORG, SaleInvoiceHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);

    util.upadteBillCode(vos, originVOs);

  }

  /**
   * ���������������޸ı���ǰ����Ĭ��ֵ��䡣
   * <p>
   * <b>����˵��</b>
   * 
   * @param invoicevo
   *          <p>
   * @author ��ӱ�
   * @time 2010-1-21 ����06:59:37
   */
  private void setBodyDefault(SaleInvoiceVO invoicevo) {
    if (VOChecker.isEmpty(invoicevo.getChildrenVO())) {
      return;
    }

    // ʹ�ñ�ͷ��Ʊ��֯����������������
    String invoiceorg = invoicevo.getParentVO().getPk_org();
    String pk_group = invoicevo.getParentVO().getPk_group();
    UFDate billdate = invoicevo.getParentVO().getDbilldate();
    for (SaleInvoiceBVO bvo : invoicevo.getChildrenVO()) {
      if (bvo.getStatus() == VOStatus.NEW) {
        bvo.setPk_org(invoiceorg);
        bvo.setPk_group(pk_group);
        bvo.setDbilldate(billdate);
      }
    }
  }

  /**
   * ���������������޸ı���ǰ��ͷĬ��ֵ��䡣
   * <p>
   * <b>����˵��</b>
   * 
   * @param invoicevo
   *          <p>
   * @author ��ӱ�
   * @time 2010-1-21 ����07:00:02
   */
  private void setHeadDefault(SaleInvoiceVO voInvoice) {

    // SaleInvoiceHVO head = voInvoice.getParentVO();

    // AuditInfoUtils.setUpdateAuditInfo(head);

    // �ϼ����� �����
    HeadTotalCalcUtil.getInstance().calcHeadTotalValue(new SaleInvoiceVO[] {
      voInvoice
    });

  }

}
