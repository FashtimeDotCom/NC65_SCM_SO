package nc.ui.so.m38.billui.editor.orgevent;

import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.so.m38.billui.pub.PreOrderPrecision;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.UIState;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.uif2.LoginContext;

public class OrgEditHandler implements IAppEventHandler<OrgChangedEvent> {

  private static final int DEFDABATEDATE = 3;

  private BillCardPanel billCardPanel;

  private BillForm billfrom;

  private LoginContext context;

  private CardKeyValue keyValue;

  public OrgEditHandler(BillForm bill, LoginContext context) {
    this.billfrom = bill;
    this.billCardPanel = bill.getBillCardPanel();
    this.context = context;
  }

  @Override
  public void handleAppEvent(OrgChangedEvent e) {
    this.keyValue = new CardKeyValue(this.billCardPanel);
    if (this.billfrom.isEditable()) {
      // �ڱ༭״̬�£�����֯�л�ʱ����ս������ݣ��Զ��������У��������кţ�songhy
      this.billfrom.addNew();
    }

    // ���ý��澫��
    PreOrderPrecision.getInstance().setCardPrecision(
        this.context.getPk_group(), this.billCardPanel);

    // �򿪽ڵ�ֱ�Ӳ�ѯold��֯Ϊ�ջ����������ж�������
    if (this.billfrom.getModel().getUiState().equals(UIState.ADD)) {

      // ��յ������
      this.clearPanel();

      if (e.getNewPkOrg() == null) {
        return;
      }

      // ����Ĭ��ֵ
      this.setDefValue(e);

      // ���ݲ���Ա����ҵ��Ա
      this.setDefaultPsnValue();
    }
    BillPanelUtils.setOrgForAllRef(this.billfrom.getBillCardPanel(),
        this.billfrom.getModel().getContext());
  }

  private void clearPanel() {
    BillItem[] headItems = this.billCardPanel.getHeadItems();
    for (BillItem item : headItems) {
      if (item.getDefaultValue() != null) {
        item.setValue(item.getDefaultValue());
      }
      else {
        item.setValue(null);
      }
    }

    BillItem[] bodyItems = this.billCardPanel.getBodyItems();
    int irowcount = this.billCardPanel.getRowCount();
    for (BillItem item : bodyItems) {
      if (item.getKey().equals(PreOrderBVO.CROWNO)) {
        continue;
      }
      for (int i = 0; i < irowcount; i++) {
        if (item.getDefaultValue() != null) {
          this.keyValue.setBodyValue(i, item.getKey(), item.getDefaultValue());
        }
        else {
          this.keyValue.setBodyValue(i, item.getKey(), null);
        }
      }
    }

    BillItem[] tailItems = this.billCardPanel.getTailItems();
    for (BillItem item : tailItems) {
      if (item.getDefaultValue() != null) {
        item.setValue(item.getDefaultValue());
      }
      else {
        item.setValue(null);
      }
    }
  }

  private void setDefaultPsnValue() {
    // ģ�������������ҵ��Ա��Ĭ��ֵ���򲻽�������Ĵ���
    if (this.keyValue.getHeadStringValue(PreOrderHVO.CEMPLOYEEID) != null) {
      return;
    }
    // TODO ���ҵ��Ա��Ϣ
    String pk_psndoc =
        UserManageQuery.queryPsndocByUserid(this.context.getPk_loginUser());
    this.keyValue.setHeadValue(PreOrderHVO.CEMPLOYEEID, pk_psndoc);
  }

  private void setDefValue(OrgChangedEvent e) {
    int irowcount = this.billCardPanel.getRowCount();
    // -- ��ͷ ----------
    // ������֯
    String pk_org = e.getNewPkOrg();
    this.keyValue.setHeadValue(PreOrderHVO.PK_ORG, pk_org);
    this.billCardPanel.getBillData().loadEditHeadRelation(PreOrderHVO.PK_ORG);
    // ����
    String pk_group = AppUiContext.getInstance().getPkGroup();
    this.keyValue.setHeadValue(PreOrderHVO.PK_GROUP, pk_group);
    // ��������
    UFDate busidate = AppUiContext.getInstance().getBusiDate();
    this.keyValue.setHeadValue(PreOrderHVO.DBILLDATE, busidate);
    // ʧЧ����
    this.keyValue.setHeadValue(PreOrderHVO.DABATEDATE, busidate.getDateAfter(DEFDABATEDATE).asLocalEnd());
    
    // �������ջ�����
    UFDate localend = busidate.asLocalEnd();
    // -- ���� ----------
    for (int i = 0; i < irowcount; i++) {
      // ����
      this.keyValue.setBodyValue(i, PreOrderBVO.PK_GROUP, pk_group);
      // ������֯
      this.keyValue.setBodyValue(i, PreOrderBVO.PK_ORG, pk_org);
      // ��������
      this.keyValue.setBodyValue(i, PreOrderBVO.DBILLDATE, busidate);
      // ��Ʒ�ۿ�
      this.keyValue.setBodyValue(i, PreOrderBVO.NITEMDISCOUNTRATE,
          new UFDouble(100));
      // �ƻ��������ڡ�Ҫ���ջ�����
      this.keyValue.setBodyValue(i, PreOrderBVO.DSENDDATE, localend);
      this.keyValue.setBodyValue(i, PreOrderBVO.DRECEIVEDATE, localend);
    }
  }
}
