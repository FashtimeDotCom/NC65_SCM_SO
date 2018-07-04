package nc.bs.so.m32.maintain.rule.insert;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.bs.so.pub.rule.rowno.SORowNoUtil;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.AppContext;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.rule.BodyUpdateByHead;
import nc.vo.so.m32.util.HeadTotalCalcUtil;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * ���۷�Ʊ����ǰ���Ĭ��ֵ
 * @scene
 * ���۷�Ʊ��������ǰ
 * @param
 * ��
 * @since 6.3
 * @version 2012-12-21 ����08:59:00
 * @author yaogj
 */
public class FillNewDefaultRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] invoices) {

    for (SaleInvoiceVO invoicevo : invoices) {
      // �������ʱĬ��ֵ
      this.setHeadDefault(invoicevo);
      this.setBodyDefault(invoicevo);

      // ����к�
      SORowNoUtil.fillupRowNo(invoicevo);
    }
    // ��䵥�ݺ�
    this.setBillCode(invoices);
    // ��䵥��������Ϊ���ܴ������۷�Ʊ�Զ����ó�ֻ�д��
    this.setBillIDs(invoices);

  }

  /**
   * ������������������ʱ��䷢Ʊ���ݺš�
   * <p>
   * <b>����˵��</b>
   * 
   * @param invoicevo
   *          <p>
   * @author ��ӱ�
   * @time 2010-1-21 ����02:43:32
   */
  private void setBillCode(SaleInvoiceVO[] invoices) {

    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.Invoice.getCode(),
            SaleInvoiceHVO.VBILLCODE, SaleInvoiceHVO.PK_GROUP,
            SaleInvoiceHVO.PK_ORG, SaleInvoiceHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.createBillCode(invoices);

  }

  /**
   * ���䵥�ݵı�ͷID ���Զ���ֵ�ʱ��ʹ��
   * 
   * @param invoices ��Ʊvos
   */
  private void setBillIDs(SaleInvoiceVO[] invoices) {
    for (SaleInvoiceVO vo : invoices) {
      SaleInvoiceHVO hvo = vo.getParentVO();
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      if (null != hvo.getPrimaryKey()) {
        continue;
      }
      int len = bvos.length;
      DBTool dao = new DBTool();
      String[] hid = dao.getOIDs(1);
      hvo.setCsaleinvoiceid(hid[0]);
      for (int i = 0; i < len; i++) {
        bvos[i].setCsaleinvoiceid(hid[0]);
      }
    }
  }

  /**
   * �����������������÷�Ʊ�ӱ�����Ĭ��ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param invoice
   *          <p>
   * @author ��ӱ�
   * @time 2010-1-21 ����02:18:23
   */
  private void setBodyDefault(SaleInvoiceVO invoice) {
    // ʹ�ñ�ͷ��Ʊ��֯����������������
    VOKeyValue<SaleInvoiceVO> keyValue = new VOKeyValue<SaleInvoiceVO>(invoice);
    BodyUpdateByHead rule = new BodyUpdateByHead(keyValue);
    String[] redunKeys = new String[] {
      SaleInvoiceBVO.PK_ORG, SaleInvoiceBVO.DBILLDATE, SaleInvoiceBVO.PK_GROUP
    };
    rule.updateAllBodyRedunValue(redunKeys);
  }

  /**
   * �����������������÷�Ʊ��������Ĭ��ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param parent
   *          <p>
   * @author fengjb
   * @time 2010-1-20 ����09:52:18
   */
  private void setHeadDefault(SaleInvoiceVO invoice) {

    SaleInvoiceHVO head = invoice.getParentVO();
    String groupid = AppContext.getInstance().getPkGroup();

    // ����
    if (VOChecker.isEmpty(head.getPk_group())) {
      head.setPk_group(groupid);
    }
    // ״̬
    if (null == head.getFstatusflag()) {
      head.setFstatusflag(BillStatus.FREE.getIntegerValue());
    }
    // �ϼ����� �����
    HeadTotalCalcUtil.getInstance().calcHeadTotalValue(new SaleInvoiceVO[] {
      invoice
    });

  }
}
