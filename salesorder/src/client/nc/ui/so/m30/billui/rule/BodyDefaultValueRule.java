package nc.ui.so.m30.billui.rule;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.pubapp.AppUiContext;

public class BodyDefaultValueRule {

  private IKeyValue keyValue;

  public BodyDefaultValueRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ���ݴ����reservOrigValue�ж��Ƿ���Ҫ���¸�ֵ����Ա༭��ʽ��
   * 
   * @since 2013.07.17
   * @author dongli2
   * @param rows
   * @param reservOrigValue
   */
  public void setBodyDefValue(int[] rows, boolean reservOrigValue) {

    // ����ID
    String orderid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CSALEORDERID);
    // ����֯
    String pk_org = this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    // ����
    String pk_group = AppUiContext.getInstance().getPkGroup();
    // ��������
    UFDate dbilldate = this.keyValue.getHeadUFDateValue(SaleOrderHVO.DBILLDATE);
    // ҵ������
    UFDate busidate = AppUiContext.getInstance().getBusiDate();
    busidate = busidate.asLocalEnd();
    // �����ۿ�
    UFDouble ndiscountrate =
        this.keyValue.getHeadUFDoubleValue(SaleOrderHVO.NDISCOUNTRATE);
    if (null == ndiscountrate) {
      ndiscountrate = SOConstant.ONEHUNDRED;
    }
    for (int row : rows) {
      // ���ݴ����reservOrigValue�������ֶ��Ƿ�Ϊ�� �ж��Ƿ���Ҫ���¸�ֵ dongli2 2013.07.17
      if (!reservOrigValue
          || this.keyValue.getBodyValue(row, SaleOrderBVO.CSALEORDERID) == null) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.CSALEORDERID, orderid);
      }
      if (!reservOrigValue
          || this.keyValue.getBodyValue(row, SaleOrderBVO.PK_ORG) == null) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.PK_ORG, pk_org);
      }
      if (!reservOrigValue
          || this.keyValue.getBodyValue(row, SaleOrderBVO.PK_GROUP) == null) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.PK_GROUP, pk_group);
      }

      // �շ�������
      if (!reservOrigValue
          || this.keyValue.getBodyValue(row, SaleOrderBVO.DBILLDATE) == null) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.DBILLDATE, dbilldate);
      }
      /*      if (!reservOrigValue
                || this.keyValue.getBodyValue(row, SaleOrderBVO.DSENDDATE) == null) {
              this.keyValue.setBodyValue(row, SaleOrderBVO.DSENDDATE, busidate);
            }
            if (!reservOrigValue
                || this.keyValue.getBodyValue(row, SaleOrderBVO.DRECEIVEDATE) == null) {
              this.keyValue.setBodyValue(row, SaleOrderBVO.DRECEIVEDATE, busidate);
            }*/
      // ��Ʒ�ۿ�
      if (!reservOrigValue
          || this.keyValue.getBodyValue(row, SaleOrderBVO.NITEMDISCOUNTRATE) == null) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.NITEMDISCOUNTRATE,
            SOConstant.ONEHUNDRED);
      }
      // �����ۿ�
      if (!reservOrigValue
          || this.keyValue.getBodyValue(row, SaleOrderBVO.NDISCOUNTRATE) == null) {
        this.keyValue.setBodyValue(row, SaleOrderBVO.NDISCOUNTRATE,
            ndiscountrate);
      }
    }
  }

  public void setBodyDefValue(int[] rows) {
    this.setBodyDefValue(rows, false);
  }
}
