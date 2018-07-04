package nc.ui.so.m33.service.ic.m4c.impl;

import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.so.m33.m4c.linkqryoutrush.entity.OutRushExeInfoVO;

public class OutRushInfoPrecision {

  // ������
  private static final String[] NUMKEYS = new String[] {
    OutRushExeInfoVO.NNUM, OutRushExeInfoVO.NOUTRUSHNUM
  };

  /**
   * ���ý��澫��
   * 
   * @param pk_group
   * @param model
   */
  public void setModelPrecision(String pk_group, BillModel model) {
    BillModelScaleProcessor scaleprocess =
        new BillModelScaleProcessor(pk_group, model);
    this.setTablePrecision(scaleprocess);
  }

  /**
   * ���ñ�񾫶�
   * 
   * @param scaleprocess
   */
  private void setTablePrecision(TableScaleProcessor scaleprocess) {
    // ������
    scaleprocess.setNumCtlInfo(OutRushInfoPrecision.NUMKEYS,
        OutRushExeInfoVO.CUNITID);
    scaleprocess.process();
  }

}
