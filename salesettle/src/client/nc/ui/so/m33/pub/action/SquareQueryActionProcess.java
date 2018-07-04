package nc.ui.so.m33.pub.action;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.so.m33.pub.SquareUIUtils;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.ui.uif2.model.AppEventConst;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

/**
 * �����ѯˢ�¹���������
 * 
 * @since 6.0
 * @version 2011-8-2 ����08:48:49
 * @author zhangcheng
 */
public class SquareQueryActionProcess {

  public void processQuery(IQueryScheme queryScheme, IQueryService service,
      ShowUpableBillListView listView, AbstractUIAppModel model) {
    if (queryScheme == null || service == null || listView == null
        || model == null) {
      return;
    }
    Object[] selectedData = null;
    try {
      selectedData = service.queryByQueryScheme(queryScheme);
      if (selectedData != null) {
        // �����ѽ���������
        SquareOutVOUtils.getInstance().setNtotalsquarenum(
            (SquareOutViewVO[]) selectedData);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    if (selectedData == null) {
      SquareUIUtils.clearUIData(listView);
      model.fireEvent(new AppEvent(AppEventConst.DATA_REFRESHED));
      return;
    }

    // �������水ť״̬�ı�
    model.fireEvent(new AppEvent(AppEventConst.SELECTION_CHANGED));
    SquareUIUtils.setVOInList(listView,
        (CircularlyAccessibleValueObject[]) selectedData,
        (BillManageModel) model);
  }

}
