package nc.ui.so.m33.service.ic.m4c.impl;

import java.awt.Container;

import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.bill.BillCardPanel;
import nc.vo.so.m33.m4c.linkqryoutrush.entity.OutRushExeInfoVO;

public class SquareOutRushLinkQueryDialog extends UIDialog {

  private static final int HEIGTH = 530;

  private static final int LENGTH = 800;

  private static final long serialVersionUID = 1L;

  private OutRushExeInfoVO[] infovos;

  public SquareOutRushLinkQueryDialog(Container parent, OutRushExeInfoVO[] vos) {
    super(parent);
    this.infovos = vos;
    this.initialize();
  }

  /**
   * ��ʼ������
   */
  private void initialize() {
    this.setSize(SquareOutRushLinkQueryDialog.LENGTH,
        SquareOutRushLinkQueryDialog.HEIGTH);
    this.setTitle("�������Գ�");/*-=notranslate=-*/
    BillCardPanel cardPanel = new BillCardPanel();
    // ����ģ�����--OutRush
    cardPanel.loadTemplet("0001Z81000000001FOCQ");
    this.add(cardPanel);
    new OutRushInfoPrecision().setModelPrecision(AppUiContext.getInstance()
        .getPkGroup(), cardPanel.getBillModel());
    cardPanel.getBillModel().setBodyDataVO(this.infovos);
    cardPanel.getBillModel().loadLoadRelationItemValue();
    cardPanel.setEnabled(false);
  }

}
