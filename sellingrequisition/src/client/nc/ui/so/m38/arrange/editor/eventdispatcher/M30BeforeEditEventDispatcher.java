package nc.ui.so.m38.arrange.editor.eventdispatcher;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.so.m38.arrange.editor.eidthandler.AstUnitEditHanlder;
import nc.ui.so.m38.arrange.editor.eidthandler.BatchCodeEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.CalTaxMnyEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.ChangeRateEditHanlder;
import nc.ui.so.m38.arrange.editor.eidthandler.DeptEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.ExchangerateEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.FreeCustEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.GlobalExchgRateEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.GroupExchgRateEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.OrigAreaEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.QtUnitEditHanlder;
import nc.ui.so.m38.arrange.editor.eidthandler.QtUnitRateEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.SaleOrgEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.SendStockOrgEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.SendStordocEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.SettleOrgEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.TaxCodeEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.TrafficOrgEditHandler;
import nc.ui.so.m38.arrange.editor.eidthandler.TrantypeEditHandler;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m38.entity.PreOrderBVO;

/**
 * Ԥ�������Ž������۶����༭ǰ�¼��ɷ���
 * 
 * @since 6.0
 * @version 2012-4-6 ����03:50:28
 * @author fengjb
 */
public class M30BeforeEditEventDispatcher {

  private BillListPanel listPanel;

  private BillContext billcontext;

  public M30BeforeEditEventDispatcher(BillListPanel billListPanel,
      BillContext billcontext) {
    this.listPanel = billListPanel;
    this.billcontext = billcontext;
  }

  public void handleEvent(PushBillEvent e) {

    String editKey = e.getEditEvent().getKey();
    // ������֯
    if (SaleOrderHVO.PK_ORG.equals(editKey)) {
      SaleOrgEditHandler handler = new SaleOrgEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // ��������
    if (SaleOrderHVO.CTRANTYPEID.equals(editKey)) {
      TrantypeEditHandler handler = new TrantypeEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // ����
    else if (SaleOrderHVO.CDEPTVID.equals(editKey)) {
      DeptEditHandler handler = new DeptEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // ɢ��
    else if (SaleOrderHVO.CFREECUSTID.equals(editKey)) {
      FreeCustEditHandler handler = new FreeCustEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // ------����------
    // ��λ
    else if (SaleOrderBVO.CASTUNITID.equals(editKey)) {
      AstUnitEditHanlder handler = new AstUnitEditHanlder();
      handler.beforeEdit(this.listPanel, e);
    }
    // ���۵�λ
    else if (SaleOrderBVO.CQTUNITID.equals(editKey)) {
      QtUnitEditHanlder handler = new QtUnitEditHanlder();
      handler.beforeEdit(this.listPanel, e);
    }
    // ������
    else if (SaleOrderBVO.VCHANGERATE.equals(editKey)) {
      ChangeRateEditHanlder handler = new ChangeRateEditHanlder();
      handler.beforeEdit(this.listPanel, e);
    }
    // ���۵�λ������
    else if (PreOrderBVO.VQTUNITRATE.equals(editKey)) {
      QtUnitRateEditHandler handler = new QtUnitRateEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // ���������֯
    else if (SaleOrderBVO.CSENDSTOCKORGVID.equals(editKey)) {
      SendStockOrgEditHandler handler = new SendStockOrgEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // �����ֿ�
    else if (SaleOrderBVO.CSENDSTORDOCID.equals(editKey)) {
      SendStordocEditHandler handler = new SendStordocEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // ������֯
    else if (SaleOrderBVO.CTRAFFICORGVID.equals(editKey)) {
      TrafficOrgEditHandler handler = new TrafficOrgEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // ���������֯
    else if (SaleOrderBVO.CSETTLEORGVID.equals(editKey)) {
      SettleOrgEditHandler handler = new SettleOrgEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // �۱�����
    else if (SaleOrderBVO.NEXCHANGERATE.equals(editKey)) {
      ExchangerateEditHandler handler = new ExchangerateEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // ȫ���۱�����
    else if (SaleOrderBVO.NGLOBALEXCHGRATE.equals(editKey)) {
      GlobalExchgRateEditHandler handler = new GlobalExchgRateEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // �����۱�����
    else if (SaleOrderBVO.NGROUPEXCHGRATE.equals(editKey)) {
      GroupExchgRateEditHandler handler = new GroupExchgRateEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // ��˰���
    else if (SaleOrderBVO.NCALTAXMNY.equals(editKey)) {
      CalTaxMnyEditHandler handler = new CalTaxMnyEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // ˰��
    else if (SaleOrderBVO.CTAXCODEID.equals(editKey)) {
      TaxCodeEditHandler handler = new TaxCodeEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // ԭ������
    else if (SaleOrderBVO.CORIGAREAID.equals(editKey)) {
      OrigAreaEditHandler handler = new OrigAreaEditHandler();
      handler.beforeEdit(this.listPanel, e);
    }
    // ���κ�
    else if (SaleOrderBVO.VBATCHCODE.equals(editKey)) {
      BatchCodeEditHandler handler = new BatchCodeEditHandler();
      handler.beforeEdit(this.listPanel, e, this.billcontext);
    }
  }
}
