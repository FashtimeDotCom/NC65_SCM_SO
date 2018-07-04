package nc.ui.so.m38.billui.pub;

import nc.ui.pubapp.AppUiContext;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class BodyDefaultRule {

  private IKeyValue keyValue;

  public BodyDefaultRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setBodyDefaultValue(int[] rows) {
    // ��ͷ����
    String headorigcur =
        this.keyValue.getHeadStringValue(PreOrderHVO.CORIGCURRENCYID);
    // ��������(���൥������)
    UFDate billdate = this.keyValue.getHeadUFDateValue(PreOrderHVO.DBILLDATE);
    // �����ۿ�
    UFDouble discountrate =
        this.keyValue.getHeadUFDoubleValue(PreOrderHVO.NDISCOUNTRATE);
    if (null == discountrate) {
      discountrate = SOConstant.ONEHUNDRED;
    }
    // ҵ������
    UFDate busidate = AppUiContext.getInstance().getBusiDate();
    busidate = busidate.asLocalEnd();

    for (int row : rows) {

      // ����
      this.keyValue.setBodyValue(row, SOItemKey.CORIGCURRENCYID, headorigcur);

      this.keyValue.setBodyValue(row, PreOrderBVO.DBILLDATE, billdate);

      // �����ۿ�
      this.keyValue.setBodyValue(row, PreOrderBVO.NDISCOUNTRATE, discountrate);
      // ��Ʒ�ۿ�
      this.keyValue.setBodyValue(row, PreOrderBVO.NITEMDISCOUNTRATE,
          SOConstant.ONEHUNDRED);
      // �ƻ��������ڡ�Ҫ���ջ�����
      this.keyValue.setBodyValue(row, PreOrderBVO.DSENDDATE, busidate);
      this.keyValue.setBodyValue(row, PreOrderBVO.DRECEIVEDATE, busidate);

      // ����
      this.keyValue.setBodyValue(row, PreOrderBVO.NNUM, null);
      this.keyValue.setBodyValue(row, PreOrderBVO.NASTNUM, null);
      this.keyValue.setBodyValue(row, PreOrderBVO.NQTUNITNUM, null);
    }

  }
}
