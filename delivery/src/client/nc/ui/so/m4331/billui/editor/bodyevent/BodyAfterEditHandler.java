package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m4331.billui.pub.calculator.DeliveryCardCalculator;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.vo.so.m4331.entity.DeliveryBVO;

public class BodyAfterEditHandler implements
IAppEventHandler<CardBodyAfterEditEvent> {
  private DeliveryEditor editor;
  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {
    String editKey = e.getKey();
    // ����
    if (DeliveryBVO.CMATERIALVID.equals(editKey)) {
      MateriaEditHandler handler = new MateriaEditHandler();
      handler.afterEdit(e);
    }
    // ���������������
    else if (DeliveryBVO.NWEIGHT.equals(editKey)
        || DeliveryBVO.NVOLUME.equals(editKey)
        || DeliveryBVO.NPIECE.equals(editKey)) {
      HeadTotalCalEditHandler handler = new HeadTotalCalEditHandler();
      handler.afterEdit(e);
    }
    // ��λ
    else if (DeliveryBVO.CASTUNITID.equals(editKey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.afterEdit(e);
      editKey = DeliveryBVO.VCHANGERATE;
    }
    // ���������֯
    else if (DeliveryBVO.CSENDSTOCKORGVID.equals(editKey)) {
      SendStockOrgHandler handler = new SendStockOrgHandler();
      handler.afterEdit(e);
    }
    // �ջ������֯
    else if (DeliveryBVO.CINSTOCKORGVID.equals(editKey)) {
      InstockHandler handler = new InstockHandler();
      handler.afterEdit(e);
    }
    // �ջ���ַ
    else if (DeliveryBVO.CRECEIVEADDRID.equals(editKey)) {
      ReceAddrEditHandler handler = new ReceAddrEditHandler();
      handler.afterEdit(e);
    }
    // �ջ��ͻ�
    else if (DeliveryBVO.CRECEIVECUSTID.equals(editKey)) {
      ReceCustEditHandler handler = new ReceCustEditHandler();
      handler.afterEdit(e);
    }
    // ���κ�
    else if (DeliveryBVO.VBATCHCODE.equals(editKey)) {
      BatchCodeEditHandler handler = new BatchCodeEditHandler();
      handler.setEditor(this.getEditor());
      handler.afterEdit(e);
    }// �ջ��ֿ�
    else if (DeliveryBVO.CINSTORDOCID.equals(editKey)) {
      CinstordocEditHandler handler = new CinstordocEditHandler();
      handler.afterEdit(e);
    }// �����ֿ�
    else if (DeliveryBVO.CSENDSTORDOCID.equals(editKey)) {
      CsendstordocEditHandler handler = new CsendstordocEditHandler();
      handler.afterEdit(e);
    }// ������������
    else if (DeliveryBVO.CSPROFITCENTERVID.equals(editKey)) {
      CsprofitcenterEditHandler handler = new CsprofitcenterEditHandler();
      handler.afterEdit(e);
    }// �ջ��������� 
    else if (DeliveryBVO.CRPROFITCENTERVID.equals(editKey)) {
      CrprofitcenterEditHandler handler = new CrprofitcenterEditHandler();
      handler.afterEdit(e);
    }
    
    DeliveryCardCalculator calculator =
        new DeliveryCardCalculator(e.getBillCardPanel());
    int[] rows = new int[] {
        e.getRow()
    };
    calculator.calculate(rows, editKey);
  }
  
  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }
}
