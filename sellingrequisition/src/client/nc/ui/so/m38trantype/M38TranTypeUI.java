package nc.ui.so.m38trantype;

import java.awt.BorderLayout;
import java.awt.Component;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38trantype.IM38TranTypeSelfService;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.transtype.EditorContext;
import nc.ui.pub.transtype.ITranstypeEditor;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;
import nc.vo.so.pub.enumeration.AskPriceRule;

/**
 * <b> Ԥ������������UI��</b>
 * 
 * @since 6.0
 * @version 2010-03-30 ����09:21:05
 * @author ��־ΰ
 */
public class M38TranTypeUI implements ITranstypeEditor {

  private BillCardPanel billCardPanel;

  private UIPanel ui = new UIPanel();

  @Override
  public void doAction(EditorContext ec) throws BusinessException {
    switch (ec.getEventtype()) {
      case EditorContext.TYPE_BROWSE:
        M38TranTypeVO vo = this.queryTranstypeExtProp(ec);
        this.showTranstypeExtObj(vo);
        // ���ɱ༭
        this.setEditable(false);
        break;
      case EditorContext.TYPE_NEW:
        // ����ʱ���༭��������գ�״̬Ϊ�ɱ༭
        this.newTranstypeExtProp();
        this.setEditable4AskedqtRule();
        break;
      case EditorContext.TYPE_EDIT:
        this.setEditable(true);
        this.setEditable4AskedqtRule();
        break;
      case EditorContext.TYPE_CLEAR:
        this.clearEditorPane();
        this.setEditable(false);
        break;
      case EditorContext.TYPE_CANCEL:
        this.setEditable(false);
        break;
      default:
        break;
    }

  }

  @Override
  public void doButtonAction(ButtonObject bo) throws BusinessException {
    // TODO Auto-generated method stub

  }

  @Override
  public Component getEditorPane() {
    this.init();
    return this.ui;
  }

  @Override
  public ButtonObject[] getExtButtonObjects() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * <b> ���ؽ���������չ���Զ��� </b>
   * 
   * @param EditorContext
   * @author ��־ΰ
   */
  @Override
  public Object getTransTypeExtObj(EditorContext context)
      throws BusinessException {
    M38TranTypeVO vo =
        (M38TranTypeVO) this.billCardPanel.getBillData().getHeaderValueVO(
            M38TranTypeVO.class.getName());
    vo.setCtrantypeid(context.getTranstype().getPk_billtypeid());
    return vo;
  }

  /**
   * �������������չ����ֵ
   * 
   * @author ��־ΰ
   */
  private void clearEditorPane() {
    this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYDISCOUNT).setValue(
        Boolean.FALSE);
  }

  private void init() {
    if (this.billCardPanel != null) {
      return;
    }
    this.billCardPanel = new BillCardPanel();
    this.billCardPanel.loadTemplet("38trantype", null, null, "@@@@");
    this.billCardPanel.setEnabled(false);
    this.billCardPanel.addBillEditListenerHeadTail(new BillEditListener() {
      @Override
      public void afterEdit(BillEditEvent e) {
        // �༭"ѯ�۹��� "
        if (e.getKey().equals(M38TranTypeVO.FASKQTRULE)) {
          M38TranTypeUI.this.setEditable4AskedqtRule();
        }
      }

      @Override
      public void bodyRowChange(BillEditEvent e) {
        // �иı��¼�.

      }

    });
    this.ui.setLayout(new BorderLayout());
    this.ui.add(this.billCardPanel);
  }

  /**
   * <b> ����Ԥ������������ </b>
   * 
   * @author ��־ΰ
   */
  private void newTranstypeExtProp() {
    this.billCardPanel.addNew();
    this.billCardPanel.updateValue();
    this.setEditable(true);
    this.setDefaultVaule();
  }

  /**
   * <b> ��ѯ����������չ����VO </b>
   * 
   * @param EditorContext
   * @author ��־ΰ
   */
  private M38TranTypeVO queryTranstypeExtProp(EditorContext ec) {
    if (ec.getTranstype() == null
        || ec.getTranstype().getPk_billtypeid() == null) {
      return new M38TranTypeVO();
    }
    String billtypeid = ec.getTranstype().getPk_billtypeid();
    M38TranTypeVO returnVos = null;
    try {
      IM38TranTypeSelfService service =
          NCLocator.getInstance().lookup(IM38TranTypeSelfService.class);
      returnVos = service.queryTranTypeVO(billtypeid);
    }
    catch (Exception e) {
      Log.info(e);
      ExceptionUtils.wrappException(e);
    }
    return returnVos;
  }

  /**
   * <b> ����Ԥ������������Ĭ��ֵ </b>
   * 
   * @author ��־ΰ
   */
  private void setDefaultVaule() {
    // �����޸��ۿ�(Ĭ����)
    this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYDISCOUNT).setValue(
        Boolean.TRUE);
  }

  /**
   * <b> ���õ������ݱ༭״̬. </b>
   * 
   * @param boolean
   * @author ��־ΰ
   */
  private void setEditable(boolean isEdit) {
    this.billCardPanel.setEnabled(isEdit);
  }

  /**
   * <b> ���ý������ͱ�ͷ,��β���� </b>
   * 
   * @param M38TranTypeVO
   * @author ��־ΰ
   */
  private void showTranstypeExtObj(M38TranTypeVO vo) {
    this.billCardPanel.getBillData().setHeaderValueVO(vo);
  }

  /**
   * ����ѯ�۹������á�ѯ���۸��Ƿ�ɸġ�δѯ���۸��Ƿ�ɸġ��Ŀɱ༭��
   * 
   * @author ��־ΰ
   */
  protected void setEditable4AskedqtRule() {
    Integer value =
        (Integer) this.billCardPanel.getHeadItem(M38TranTypeVO.FASKQTRULE)
            .getValueObject();
    // "null����ѯ��"ʱ����>"ѯ���۸��Ƿ�ɸġ�δѯ���۸��Ƿ�ɸ�"�����Ա༭
    if (null == value || AskPriceRule.ASKPRICE_NO.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYASKEDQT).setEnabled(
          false);
      this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYUNASKEDQT)
          .setEnabled(false);
      // ȷ��ֵΪnull
      this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYASKEDQT).setValue(
          null);
      this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYUNASKEDQT).setValue(
          null);
    }
    // "����ѯ�ۡ�ѯ�Ǵ�����"ʱ����>"ѯ���۸��Ƿ�ɸġ�δѯ���۸��Ƿ�ɸ�"���Ա༭
    else if (AskPriceRule.ASKPRICE_NORMAL.equalsValue(value)
        || AskPriceRule.ASKPRICE_UNSPROMOTION.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYASKEDQT).setEnabled(
          true);
      this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYUNASKEDQT)
          .setEnabled(true);
    }
  }

}
