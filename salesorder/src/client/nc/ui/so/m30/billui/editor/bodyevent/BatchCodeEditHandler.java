package nc.ui.so.m30.billui.editor.bodyevent;

import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.ic.batchcode.ref.BatchRefDlg;
import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.SOFillParamForICUtil;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ic.batch.BatchRefViewVO;
import nc.vo.ic.batchcode.BatchDlgParam;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmf.ic.mbatchcode.BatchcodeVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ���۶������κű༭�¼�
 * 
 * @since 6.0
 * @version 2011-6-23 ����11:42:18
 * @author feng j b
 */
@SuppressWarnings("restriction")
public class BatchCodeEditHandler {

  /**
   * 
   * @param e
   * @param billform
   */
  public void beforeEdit(CardBodyBeforeEditEvent e, SaleOrderBillForm billform) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    int row = e.getRow();
    String materialvid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
    String sendstock =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CSENDSTOCKORGID);
    if (PubAppTool.isNull(materialvid) || PubAppTool.isNull(sendstock)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    String[] wholeflag = new String[] {
      MaterialStockVO.WHOLEMANAFLAG
    };
    Map<String, MaterialStockVO> map =
        MaterialPubService.queryMaterialStockInfo(new String[] {
          materialvid
        }, sendstock, wholeflag);
    MaterialStockVO marstockvo = map.get(materialvid);
    UFBoolean flag = null;
    if (null != marstockvo) {
      flag = marstockvo.getWholemanaflag();
    }
    if (null == flag || !flag.booleanValue()) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    BillItem batchcodeitem = cardPanel.getBodyItem(SaleOrderBVO.VBATCHCODE);
    BatchRefPane batchref = (BatchRefPane) batchcodeitem.getComponent();
    BatchDlgParam param =
        SOFillParamForICUtil.getBatchDlgParam(billform, keyValue, row);
    batchref.setParam(param);
    batchref.setMultiSelectedEnabled(false);
  }

  /**
   * 
   * @param e
   * @param billform
   */
  public void afterEdit(CardBodyAfterEditEvent e, SaleOrderBillForm billform) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    String editvalue = (String) e.getValue();
    int row = e.getRow();
    BillItem batchcodeitem = cardPanel.getBodyItem(SaleOrderBVO.VBATCHCODE);
    BatchRefPane batchref = (BatchRefPane) batchcodeitem.getComponent();
    BatchRefDlg refdlg = batchref.getBatchRefDlg();
    String cmaterialvid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);

    // �Ƿ��ֹ��༭���ֹ��༭�����û�е������ȷ����ť����ȷ����ť���ڽ��㲻�뿪ʱ�ֶ��༭�˵�Ԫ���ı���
    UFBoolean isByHand = UFBoolean.FALSE;
    if (!batchref.isClicked()) {
      isByHand = UFBoolean.TRUE;
    }
    else {
      String batchCode =
          (String) batchref.getRefVOs().get(0).getAttributeValue("vbatchcode");
      if (PubAppTool.isNull(editvalue) || !editvalue.equals(batchCode + ".")) {
        isByHand = UFBoolean.TRUE;
      }
    }
    BatchRefViewVO batchvo = null;
    if (UFBoolean.TRUE.equals(isByHand)) {
      // �ֹ�¼��ʱ��
      BatchDlgParam param =
          SOFillParamForICUtil.getBatchDlgParam(billform, keyValue, row);
      param.setIsQueryZeroLot(UFBoolean.TRUE);
      refdlg.setParam(param);
      batchvo = refdlg.getRefVO(cmaterialvid, editvalue);
    }
    else {
      // ѡ�����ʱ��
      List<BatchRefViewVO> batchvoList = batchref.getRefVOs();
      if (batchvoList.size() > 0) {
        batchvo = batchvoList.get(0);
      }
    }
    if (PubAppTool.isNull(editvalue) || null == batchvo) {
      keyValue.setBodyValue(row, SaleOrderBVO.VBATCHCODE, null);
      keyValue.setBodyValue(row, SaleOrderBVO.PK_BATCHCODE, null);
    }
    else {
      keyValue.setBodyValue(row, SaleOrderBVO.VBATCHCODE,
          batchvo.getAttributeValue(BatchcodeVO.VBATCHCODE));
      keyValue.setBodyValue(row, SaleOrderBVO.PK_BATCHCODE,
          batchvo.getAttributeValue(BatchcodeVO.PK_BATCHCODE));
    }
    if (batchref.isClicked()) {
      batchref.setClicked(false);
    }
  }
}
