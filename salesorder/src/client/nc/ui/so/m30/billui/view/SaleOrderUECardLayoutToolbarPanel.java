package nc.ui.so.m30.billui.view;

import nc.md.MDBaseQueryFacade;
import nc.md.model.IAttribute;
import nc.md.model.IBean;
import nc.md.model.MetaDataException;
import nc.md.model.type.IEnumType;
import nc.md.util.MDUtil;
import nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.model.AppEventConst;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * ������״̬Ӱ��UE��������ʾ����ʱ����һ�¡����������ǣ����۶�����������״̬Ӧ������ö�١�
 * 
 * @since 6.3
 * @version 2013-3-28 ����01:42:45
 * @author tianft
 */
public class SaleOrderUECardLayoutToolbarPanel extends UECardLayoutToolbarPanel {

  @Override
  public void handleEvent(AppEvent event) {
    super.handleEvent(event);
    if (AppEventConst.SELECTION_CHANGED.equals(event.getType())
        || AppEventConst.SELECTED_DATE_CHANGED.equals(event.getType())
        || AppEventConst.DATA_INSERTED.equals(event.getType())
        || AppEventConst.DATA_UPDATED.equals(event.getType())
        || AppEventConst.DATA_REFRESHED.equals(event.getType())) {
      this.setRightText(this.getBillStatusStr());
    }
  }

  private String getBillStatusStr() {
    Object data = this.getModel().getSelectedData();
    if (data == null) {
      return null;
    }
    try {
      IBean bean =
          MDBaseQueryFacade.getInstance().getBeanByFullClassName(
              data.getClass().getName());
      if (null != bean && MDUtil.isEntityType(bean)) {
        String billStatusName = SaleOrderHVO.FSTATUSFLAG;// ����״̬���棬������Ҫȷ������
        SaleOrderVO order = (SaleOrderVO) data;
        Integer billStatus = order.getParentVO().getFstatusflag();
        if (billStatus == null) {
          return null;
        }
        IAttribute attribute = bean.getAttributeByName(billStatusName);
        if (attribute != null && attribute.getDataType() instanceof IEnumType) {
          return ((IEnumType) attribute.getDataType()).getConstEnum(billStatus)
              .getName();
        }
      }
    }
    catch (MetaDataException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
