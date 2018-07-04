package nc.ui.so.m30.arrange.listener;

import java.util.ArrayList;
import java.util.List;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.IBillPush;
import nc.ui.uif2.IFuncNodeInitDataListener;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * ����/ֱ���ṩ�����۶����Ʋ���/ֱ�˵ļ���
 * <p>
 * <b>�����ť����/ֱ�˰��ź󣬵�������/ֱ�˽��桪����ʼ���ݼ���</b>
 * </p>
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

  @Override
  public void initData(FuncletInitData data) {
    Object object = data.getInitData();
    if (object == null) {
      return;
    }
    Object[] newObjects = (Object[]) object;
    // ���������Žڵ��ѯ����vo
    SaleOrderVO[] vos = this.filterVOs(newObjects);
    if (vos.length < 1) {
      return;
    }
    try {
      this.getBillContext().getModel().initModel(vos);
    }
    catch (Exception e) {

      // ���淶�׳��쳣(EJB�߽�����marsh����)
      ExceptionUtils.wrappException(e);
    }
  }

  @Override
  public void setBillContext(BillContext newContext) {
    this.context = newContext;
  }

  /**
   * ���˿��Բ������ŵĵ���
   * <p>
   * <b>������/ֱ�����ò�ѯ�������,�����Ľ�������/ֱ�˽ڵ��������ȥ��</b>
   * 
   * <ul>
   * <li>������
   * <li>δ���Źر�
   * <li>...
   * </ul>
   * </p>
   * 
   * @version 6.0
   * @author ��־ΰ
   * @time 2010-9-14 ����11:25:27
   */
  private SaleOrderVO[] filterVOs(Object[] objects) {
    List<SaleOrderVO> alViews = new ArrayList<SaleOrderVO>();
    for (Object object : objects) {
      SaleOrderVO vo = (SaleOrderVO) object;
      boolean isBZArrange = false;
      if (BillStatus.AUDIT.equalsValue(vo.getParentVO().getFstatusflag())) {
        SaleOrderBVO[] bodys = vo.getChildrenVO();
        for (SaleOrderBVO body : bodys) {
          if (!body.getBarrangedflag().booleanValue()) {
            isBZArrange = true;
            break;
          }
        }
        if (isBZArrange) {
          alViews.add(vo);
        }
      }
    }
    return alViews.toArray(new SaleOrderVO[0]);
  }

}
