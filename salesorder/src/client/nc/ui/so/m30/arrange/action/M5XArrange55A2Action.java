package nc.ui.so.m30.arrange.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.uif2.NCAction;

/**
 * ��ʱ��ť��������ȷ�ϣ��˹����������������Žڵ��ת����������ť�ûҡ�
 * �Ժ���Ҫ��������ʵ�ְ�ť����,��m30arrangeA2Action
 * 
 * @since 6.0
 * @version 2011-9-16 ����10:56:06
 * @author ��־ΰ
 */
public class M5XArrange55A2Action extends NCAction {

  private static final long serialVersionUID = -5639805229892958698L;

  public M5XArrange55A2Action() {
    super();
    this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0428")/*@res "��������"*/);
    this.setCode("m5XArrange55A2");
    this.putValue(Action.SHORT_DESCRIPTION, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0428"));
    this.enabled = false;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    //
  }

  @Override
  protected boolean isActionEnable() {
    return false;// ����������������Բ˵����û�
  }
}
