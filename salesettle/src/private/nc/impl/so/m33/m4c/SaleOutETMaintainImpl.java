package nc.impl.so.m33.m4c;

import nc.bs.so.m33.biz.m4c.action.manual.SaleOutManualEstimateAction;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBizBP;
import nc.impl.pubapp.pattern.data.view.tool.ViewConcurrentTool;
import nc.itf.so.m33.maintain.m4c.ISaleOutETMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.view.IDataView;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

public class SaleOutETMaintainImpl implements ISaleOutETMaintain {

  @Override
  public void manualEstimate(SquareOutViewVO[] vos) throws BusinessException {
    try {
      this.setHGroupByBGroup(vos);
      this.concurrentCheck(vos);
      new SaleOutManualEstimateAction().manualEstimate(vos);

    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public void manualUnEstimate(SquareOutViewVO[] vos) throws BusinessException {
    try {
      this.setHGroupByBGroup(vos);
      this.concurrentCheck(vos);
      new SaleOutManualEstimateAction().manualUnEstimate(vos);

    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public SquareOutViewVO[] querySquareOutFor4CManualET(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return new QuerySquare4CVOBizBP().querySquareOutFor4CSquare(queryScheme);

    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  /**
   * ��������
   * 
   * @param views ��Ҫ�������Ƶ���ͼvo
   */
  private void concurrentCheck(IDataView[] views) {
    if (views == null || views.length == 0) {
      return;
    }
    ViewConcurrentTool tool = new ViewConcurrentTool();
    tool.checkConcurrent(views);
  }

  /**
   * �ñ���Group���ñ�ͷGroup����Ϊǰ̨��ƽ����ֻ�����Group
   * 
   * @param vos
   */
  private void setHGroupByBGroup(SquareOutViewVO[] vos) {
    for (SquareOutViewVO view : vos) {
      view.getHead().setPk_group(view.getItem().getPk_group());
    }
  }

}
