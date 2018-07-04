package nc.ui.so.m4331.arrange.listener;

import java.util.ArrayList;
import java.util.List;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.uif2.IFuncNodeInitDataListener;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * ���۶��������ť�������ź󣬵����������Ž����ʼ���ݼ���
 * 
 * @version 6.0
 * @author ��־ΰ
 * @time 2010-9-14 ����01:49:04
 */
public class M30InitDataListener implements IFuncNodeInitDataListener,
    IBillPush {

  private BillContext context;

  @Override
  public BillContext getBillContext() {
    return this.context;
  }

  /**
   * ���෽����д
   * 
   */
  @Override
  public void initData(FuncletInitData data) {
    Object object = data.getInitData();
    if (object == null) {
      return;
    }
    SaleOrderViewVO[] views = (SaleOrderViewVO[]) object;
    // ���������Žڵ��ѯ����Views
    views = this.filterViews(views);
    if (views.length < 1) {
      return;
    }
    try {
      this.getBillContext().getModel().initModel(views);
    }
    catch (Exception e) {
      // ���淶�׳��쳣(EJB�߽�����marsh����)
      ExceptionUtils.wrappException(e);
    }
  }

  @Override
  public void setBillContext(BillContext context1) {
    this.context = context1;
  }

  /**
   * ���˿��Է������ŵ�������
   * <ul>
   * <li>������
   * <li>������֯�ǿ�
   * <li>δ�����ر�
   * <li>...
   * </ul>
   * 
   * @version 6.0
   * @author ��־ΰ
   * @time 2010-9-14 ����11:25:27
   */
  private SaleOrderViewVO[] filterViews(SaleOrderViewVO[] views) {
    List<SaleOrderViewVO> alViews = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      if (BillStatus.AUDIT.equalsValue(view.getHead().getFstatusflag())
          && view.getBody().getCtrafficorgid() != null
          && !view.getBody().getBbsendendflag().booleanValue()) {
        alViews.add(view);
      }
    }
    return alViews.toArray(new SaleOrderViewVO[0]);
  }

}
