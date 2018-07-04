package nc.ui.so.m30trantype;

import java.awt.BorderLayout;
import java.awt.Component;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeSelfService;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.transtype.EditorContext;
import nc.ui.pub.transtype.ITranstypeEditor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.LargessDisType;
import nc.vo.so.m30trantype.enumeration.SaleMode;
import nc.vo.so.pub.enumeration.AskPriceRule;

/**
 * ���۶�����������UI��
 * 
 * @since 6.0
 * @version 2011-12-2 ����08:49:49
 * @author fengjb
 */
public class M30TranTypeUI implements ITranstypeEditor {

  private BillCardPanel billCardPanel;

  private UIPanel ui = new UIPanel();

  private M30TranTypeVO vo;

  @Override
  public void doAction(EditorContext ec) throws BusinessException {

    switch (ec.getEventtype()) {
      case EditorContext.TYPE_BROWSE:
        vo = this.queryTranstypeExtProp(ec);
        this.showTranstypeExtObj(vo);
        // ���ɱ༭
        this.setEditable(false);
        break;
      case EditorContext.TYPE_NEW:
        // ����ʱ���༭��������գ�״̬Ϊ�ɱ༭
        this.newTranstypeExtProp();
        this.setEditable4AskedqtRule();
        this.setEditable4BlrgcashRule();
        break;
      case EditorContext.TYPE_EDIT:
        this.setEditable(true);
        this.setEditable4AskedqtRule();
        this.setEditable4BlrgcashRule();
        this.setEditable4ChgOutRule();
        this.setEditable4NaccPriRule();
        this.setEditable4BlrgessPriNORule();
        break;
      case EditorContext.TYPE_CLEAR:
        this.clearEditorPane();
        this.setEditable(false);
        break;
      case EditorContext.TYPE_CANCEL:
        this.setEditable(false);
        this.showTranstypeExtObj(vo);
        break;
      default:
        break;
    }

  }

  @Override
  public void doButtonAction(ButtonObject bo) throws BusinessException {
    // TODO
  }

  @Override
  public Component getEditorPane() {
    this.init();
    return this.ui;
  }

  @Override
  public ButtonObject[] getExtButtonObjects() {
    return null;
  }

  @Override
  public Object getTransTypeExtObj(EditorContext context)
      throws BusinessException {
    M30TranTypeVO vo =
        (M30TranTypeVO) this.billCardPanel.getBillData().getHeaderValueVO(
            M30TranTypeVO.class.getName());
    vo.setVtrantypecode(context.getTranstype().getPk_billtypecode());
    String pk_group = AppContext.getInstance().getPkGroup();
    vo.setPk_group(pk_group);
    return vo;
  }

  /**
   * ����ѯ�۹������á�ѯ���۸��Ƿ�ɸġ�δѯ���۸��Ƿ�ɸġ��Ŀɱ༭��
   * 
   * @version 6.0
   * @author ��־ΰ
   */
  public void setEditable4AskedqtRule() {

    Integer value =
        (Integer) this.billCardPanel.getHeadItem(M30TranTypeVO.FASKQTRULE)
            .getValueObject();
    // "�ա���ѯ��"ʱ"ѯ���۸��Ƿ�ɸġ�δѯ���۸��Ƿ�ɸġ�δѯ���۸��Ƿ���ʾ"�����Ա༭
    if (null == value || AskPriceRule.ASKPRICE_NO.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYASKEDQT).setEnabled(
          false);
      this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYUNASKEDQT)
          .setEnabled(false);
      this.billCardPanel.getHeadItem(M30TranTypeVO.BNOFINDPRICEHIT).setEnabled(
          false);
      // "����ѯ�ۡ�ѯ�Ǵ�����"�л�"�ա���ѯ��"ȷ��ֵΪ��
      this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYASKEDQT).setValue(
          UFBoolean.FALSE);
      this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYUNASKEDQT).setValue(
          UFBoolean.FALSE);
      this.billCardPanel.getHeadItem(M30TranTypeVO.BNOFINDPRICEHIT).setValue(
          UFBoolean.FALSE);
    }
    // "����ѯ�ۡ�ѯ�Ǵ�����"ʱ"ѯ���۸��Ƿ�ɸġ�δѯ���۸��Ƿ�ɸ�"���Ա༭
    else if (AskPriceRule.ASKPRICE_NORMAL.equalsValue(value)
        || AskPriceRule.ASKPRICE_UNSPROMOTION.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYASKEDQT).setEnabled(
          true);
      this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYUNASKEDQT)
          .setEnabled(true);
      this.billCardPanel.getHeadItem(M30TranTypeVO.BNOFINDPRICEHIT).setEnabled(
          true);
    }
  }

  /**
   * ��������ģʽ���á���Ʒ�Ҹ����ֶεĿɱ༭��
   * 
   * @version 6.35
   * @author dongli2
   */
  public void setEditable4BlrgcashRule() {
    Integer value =
        (Integer) this.billCardPanel.getHeadItem(M30TranTypeVO.FSALEMODE)
            .getValueObject();
    // ����ģʽΪ���˻����˻�������ͨ+�˻�����ͨ+�˻���������������Ʒ�Ҹ�����
    if (null == value || SaleMode.MODE_COMMONRETURN.equalsValue(value)
        || SaleMode.MODE_COMMONRETURNCHANGE.equalsValue(value)
        || SaleMode.MODE_RETURN.equalsValue(value)
        || SaleMode.MODE_RETURNCHANGE.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BLRGCASHFLAG).setEnabled(
          false);
      // ����ģʽΪ���˻����˻�������ͨ+�˻�����ͨ+�˻�����,ȷ������Ʒ�Ҹ���ֵΪ��
      this.billCardPanel.getHeadItem(M30TranTypeVO.BLRGCASHFLAG).setValue(
          UFBoolean.FALSE);
    }
    // ����ģʽΪ����ͨ����������Ʒ�Ҹ�����
    if (SaleMode.MODE_COMMON.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BLRGCASHFLAG).setEnabled(
          true);
    }

  }

  /**
   * �����˻���ģʽ���á��˻����֮����ܻ������⡱�Ŀɱ༭��
   * 
   * @version 6.36
   * @author wangshu6
   */
  public void setEditable4ChgOutRule() {
    Integer value =
        (Integer) this.billCardPanel.getHeadItem(M30TranTypeVO.FSALEMODE)
            .getValueObject();
    // ����ģʽΪ���˻����˻�������ͨ+�˻�����ͨ+�˻����������˻����֮����ܻ������⡱�ſ���
    if (null == value || SaleMode.MODE_COMMONRETURN.equalsValue(value)
        || SaleMode.MODE_COMMONRETURNCHANGE.equalsValue(value)
        || SaleMode.MODE_RETURN.equalsValue(value)
        || SaleMode.MODE_RETURNCHANGE.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BCANCHANGEOUT).setEnabled(
          true);
    }
    // ����ģʽΪ����ͨ�������˻����֮����ܻ������⡱������
    if (SaleMode.MODE_COMMON.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BCANCHANGEOUT).setEnabled(
          false);
    }
  }

  /**
   * ����"�����˵���ȡ�۹���"�Ŀɱ༭��
   * 
   * @version 6.36
   * @author wangshu6
   */
  public void setEditable4NaccPriRule() {
    Boolean flag =
         (Boolean) this.billCardPanel.getHeadItem(M30TranTypeVO.BLRGCASHFLAG)
        .getValueObject();
    // ������Ʒ�Ҹ��ſ�ά�� �������˵���ȡ�۹��򡱲ſ���
    if (UFBoolean.TRUE.equals(UFBoolean.valueOf(flag))) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.NACCPRICERULE).setEnabled(
          true);
    }else{
      this.billCardPanel.getHeadItem(M30TranTypeVO.NACCPRICERULE).setEnabled(
          false);
    }
  }

  /**
   * ������Ʒ�۸��̯��ʽ���á���Ʒ�м۸񱣳ֲ��䡱�Ŀɱ༭��
   * 
   * @version 6.36
   * @author wangshu6
   */
  public void setEditable4BlrgessPriNORule() {
    Integer value =
        (Integer) this.billCardPanel.getHeadItem(M30TranTypeVO.FLARGESSDISTYPE)
            .getValueObject();
    // ��Ʒ�۸��̯��ʽΪ�����������÷�̯��ͬ���Ϸ�̯��������̯��������Ʒ�м۸񱣳ֲ��䡱�ſ���
    if (null == value || LargessDisType.DISPARTBYLARGESS.equalsValue(value)
        || LargessDisType.DISPARTBYINV.equalsValue(value)
        || LargessDisType.DISPARTONE.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BLARGESSPRICENO).setEnabled(
          true);
    }
    // ��Ʒ�۸��̯��ʽΪ������̯��������Ʒ�м۸񱣳ֲ��䡱������
    if (LargessDisType.NODISPART.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BLARGESSPRICENO).setEnabled(
          false);
    }
  }

  private void clearEditorPane() {
    this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYDISCOUNT).setValue(
        Boolean.FALSE);

  }

  private void init() {
    if (this.billCardPanel != null) {
      return;
    }
    this.billCardPanel = new BillCardPanel();
    this.billCardPanel.loadTemplet("30trantype", null, null, "@@@@");
    this.billCardPanel.setEnabled(false);
    this.billCardPanel.addBillEditListenerHeadTail(new BillEditListener() {

      @Override
      public void afterEdit(BillEditEvent e) {
        if (e.getKey().equals(M30TranTypeVO.FASKQTRULE)) {
          M30TranTypeUI.this.setEditable4AskedqtRule();
        }
        if (e.getKey().equals(M30TranTypeVO.FSALEMODE)) {
          M30TranTypeUI.this.setEditable4BlrgcashRule();
          M30TranTypeUI.this.setEditable4ChgOutRule();
        }if(e.getKey().equals(M30TranTypeVO.BLRGCASHFLAG)){
          M30TranTypeUI.this.setEditable4NaccPriRule();
        }if(e.getKey().equals(M30TranTypeVO.FLARGESSDISTYPE)){
          M30TranTypeUI.this.setEditable4BlrgessPriNORule();
        }
      }

      @Override
      public void bodyRowChange(BillEditEvent e) {
        // �����б仯
      }

    });
    this.ui.setLayout(new BorderLayout());
    this.ui.add(this.billCardPanel);

  }

  private void newTranstypeExtProp() {
    this.billCardPanel.addNew();
    this.billCardPanel.updateValue();
    this.setEditable(true);
    this.setDefaultVaule();
  }

  private M30TranTypeVO queryTranstypeExtProp(EditorContext ec) {

    if (ec.getTranstype() == null
        || ec.getTranstype().getPk_billtypeid() == null) {
      return new M30TranTypeVO();
    }

    String ctrantypeid = ec.getTranstype().getPk_billtypeid();
    M30TranTypeVO returnVos = null;
    try {
      IM30TranTypeSelfService service =
          NCLocator.getInstance().lookup(IM30TranTypeSelfService.class);
      returnVos = service.queryTranTypeVO(ctrantypeid);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return returnVos;
  }

  private void setDefaultVaule() {
    this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYDISCOUNT).setValue(
        Boolean.TRUE);
  }

  private void setEditable(boolean isEdit) {
    this.billCardPanel.setEnabled(isEdit);
  }

  private void showTranstypeExtObj(M30TranTypeVO vo) {
    this.billCardPanel.getBillData().setHeaderValueVO(vo);
  }
}
