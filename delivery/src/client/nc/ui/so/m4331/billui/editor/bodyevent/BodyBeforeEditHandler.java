package nc.ui.so.m4331.billui.editor.bodyevent;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.vo.so.m4331.entity.DeliveryBVO;

public class BodyBeforeEditHandler implements
    IAppEventHandler<CardBodyBeforeEditEvent> {

  private DeliveryEditor editor;

  @Override
  public void handleAppEvent(CardBodyBeforeEditEvent e) {

    String editkey = e.getKey();
    // ����
    if (DeliveryBVO.CMATERIALVID.equals(editkey)) {
      MateriaEditHandler handler = new MateriaEditHandler();
      // modify by jilu for EHP1���̵�633 20140703
      handler.setEditor(this.editor);
      handler.beforeEdit(e);
    }
    // ���������֯
    else if (DeliveryBVO.CSENDSTOCKORGVID.equals(editkey)) {
      SendStockOrgHandler handler = new SendStockOrgHandler();
      handler.beforeEdit(e);
    }
    // �ջ������֯
    else if (DeliveryBVO.CINSTOCKORGID.equals(editkey)) {
      InstockHandler handler = new InstockHandler();
      handler.beforeEdit(e);
    }
    // �����ֿ�
    else if (DeliveryBVO.CSENDSTORDOCID.equals(editkey)) {
      SendStordocHandler handler = new SendStordocHandler();
      handler.beforeEdit(e);
    }
    // �ջ��ֿ�
    else if (DeliveryBVO.CINSTORDOCID.equals(editkey)) {
      InstordocHandler handler = new InstordocHandler();
      handler.beforeEdit(e);
    }
    // ���κ�
    else if (DeliveryBVO.VBATCHCODE.equals(editkey)) {
      BatchCodeEditHandler handler = new BatchCodeEditHandler();
      handler.setEditor(this.editor);
      handler.beforeEdit(e);
    }
    // ��λ
    else if (DeliveryBVO.CSPACEID.equals(editkey)) {
      SpaceEditHandler handler = new SpaceEditHandler();
      handler.beforeEdit(e);
    }
    // ˾��
    else if (DeliveryBVO.CCHAUFFEURID.equals(editkey)) {
      ChauffeurEditHandler handler = new ChauffeurEditHandler();
      handler.beforeEdit(e);
    }
    // Ѻ��Ա
    else if (DeliveryBVO.CSUPERCARGOID.equals(editkey)) {
      SupercargoEditHandler handler = new SupercargoEditHandler();
      handler.beforeEdit(e);
    }
    // ����
    else if (DeliveryBVO.CVEHICLEID.equals(editkey)) {
      VehicleEditHandler handler = new VehicleEditHandler();
      handler.beforeEdit(e);
    }
    // ����
    else if (DeliveryBVO.CVEHICLETYPEID.equals(editkey)) {
      VehicleTypeEditHandler handler = new VehicleTypeEditHandler();
      handler.beforeEdit(e);
    }
    // ������
    else if (DeliveryBVO.CTRANSCUSTID.equals(editkey)) {
      TransCustEditHandler handler = new TransCustEditHandler();
      handler.beforeEdit(e);
    }
    // �ջ���ϵ��
    else if (DeliveryBVO.CRECEIVEPERSONID.equals(editkey)) {
      ReceivePersonidEditHandler handler = new ReceivePersonidEditHandler();
      handler.beforeEdit(e);
    }
    // ��λ
    else if (DeliveryBVO.CASTUNITID.equals(editkey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.beforeEdit(e);
    }
    // ������ϵ��
    else if (DeliveryBVO.CSENDPERSONID.equals(editkey)) {
      SendpersonidEditHandler handler = new SendpersonidEditHandler();
      handler.beforeEdit(e);
    }
    // ԭ������
    else if (DeliveryBVO.CORIGAREAID.equals(editkey)) {
      OrigAreaEditHandler handler = new OrigAreaEditHandler();
      handler.beforeEdit(e);
    }
    else if(DeliveryBVO.CDEPTVID.equals(editkey)){
      DeptEditHandler handler=new DeptEditHandler();
      handler.beforeEdit(e);
    }
    else if(DeliveryBVO.CEMPLOYEEID.equals(editkey)){
      EmployeeEditHandler handler = new EmployeeEditHandler();
      handler.beforeEmployeeEdit(e);
    }
    else if(DeliveryBVO.CORDERCUSTID.equals(editkey)){
      OrderCustEditHandler handler=new OrderCustEditHandler();
      handler.beforeEdit(e);
    }
  }

  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }
}
