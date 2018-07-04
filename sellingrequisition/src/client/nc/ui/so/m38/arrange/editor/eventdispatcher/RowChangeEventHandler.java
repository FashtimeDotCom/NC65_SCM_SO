package nc.ui.so.m38.arrange.editor.eventdispatcher;

import nc.ui.ic.onhand.pane.QueryOnHandInfoPanel;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.BillTabPanel;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.pubapp.billref.src.view.RefListPanel;
import nc.ui.pubapp.util.RefListPanelValueUtils;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * Ԥ�������а�����ѡ�иı��¼�
 * 
 * @since 6.0
 * @version 2011-7-27 ����02:26:06
 * @author fengjb
 */
public class RowChangeEventHandler {

  public void handleEvent(PushBillEvent e, BillContext context) {
    BillTabPanel billtabpanel = context.getBillTabPanel();
    if (null != billtabpanel.getExtendedBottomPanel()) {
      int row = e.getEditEvent().getRow();
      try {
        // ��ȡ�ִ���ά��vo
        OnhandDimVO dimVo = this.getDimVo(context, row);
        // ˢ��
        ((QueryOnHandInfoPanel) billtabpanel.getExtendedBottomPanel())
            .queryOnhand(dimVo);
      }
      catch (Exception ex) {
        ExceptionUtils.wrappException(ex);
      }
    }
  }

  private OnhandDimVO getDimVo(BillContext context, int row) throws Exception {

    RefListPanel reflist = context.getListPanel();
    RefListPanelValueUtils valueUtil = new RefListPanelValueUtils(reflist);
    OnhandDimVO paraVO = new OnhandDimVO();
    // ����
    String pk_group = AppContext.getInstance().getPkGroup();
    paraVO.setPk_group(pk_group);
    // ����
    String cmaterialoid =
        valueUtil.getStringValueAt(row, SaleOrderBVO.CMATERIALID);
    paraVO.setCmaterialoid(cmaterialoid);

    String cmaterialvid =
        valueUtil.getStringValueAt(row, SaleOrderBVO.CMATERIALVID);
    paraVO.setCmaterialvid(cmaterialvid);
    // ҵ��λ
    String castunitid =
        valueUtil.getStringValueAt(row, SaleOrderBVO.CASTUNITID);
    paraVO.setCastunitid(castunitid);
    // ������
    String vchangerate =
        valueUtil.getStringValueAt(row, SaleOrderBVO.VCHANGERATE);
    paraVO.setVchangerate(vchangerate);
    // ��������
    String cproductorid =
        valueUtil.getStringValueAt(row, SaleOrderBVO.CPRODUCTORID);
    paraVO.setCproductorid(cproductorid);

    // ��Ŀ
    String cprojectid =
        valueUtil.getStringValueAt(row, SaleOrderBVO.CPROJECTID);
    paraVO.setCprojectid(cprojectid);

    // ��Ӧ��
    String cvendorid = valueUtil.getStringValueAt(row, SaleOrderBVO.CVENDORID);
    paraVO.setCvendorid(cvendorid);

    // �����֯
    String csendstockorgid =
        valueUtil.getStringValueAt(row, SaleOrderBVO.CSENDSTOCKORGID);
    paraVO.setPk_org(csendstockorgid);

    // �ֿ�
    String csendstordocid =
        valueUtil.getStringValueAt(row, SaleOrderBVO.CSENDSTORDOCID);
    paraVO.setCwarehouseid(csendstordocid);
    // ���κ�
    String pk_batchcode =
        valueUtil.getStringValueAt(row, SaleOrderBVO.PK_BATCHCODE);
    paraVO.setPk_batchcode(pk_batchcode);

    String vbatchcode =
        valueUtil.getStringValueAt(row, SaleOrderBVO.VBATCHCODE);
    paraVO.setVbatchcode(vbatchcode);
    // ������
    paraVO.setVfree1(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE1));
    paraVO.setVfree2(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE2));
    paraVO.setVfree3(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE3));
    paraVO.setVfree4(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE4));
    paraVO.setVfree5(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE5));
    paraVO.setVfree6(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE6));
    paraVO.setVfree7(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE7));
    paraVO.setVfree8(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE8));
    paraVO.setVfree9(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE9));
    paraVO.setVfree10(valueUtil.getStringValueAt(row, SaleOrderBVO.VFREE10));
    return paraVO;
  }

}
