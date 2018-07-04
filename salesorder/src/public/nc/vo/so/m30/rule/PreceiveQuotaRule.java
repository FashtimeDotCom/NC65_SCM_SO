package nc.vo.so.m30.rule;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * �տ��޶�/�տ�����������
 * 
 * @since 6.0
 * @version 2011-7-27 ����03:08:46
 * @author ��־ΰ
 */
public class PreceiveQuotaRule {
  private IKeyValue keyValue;
  
  public PreceiveQuotaRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ��˰�ϼ�Ӱ���տ��޶�ʱҪ�߲���SO03
   */
  public void calculateByPara(String sSO03) {
    // ����SO03���ȹ���Ϊ�տ��޶�:���ֹ��༭����������Ӱ���տ��޶�ֵ
    // �տ��޶��,�տ�����仯
    if (sSO03 != null && "�տ��޶�".equals(sSO03)) {/*-=notranslate=-*/
      this.calculatePreceiveRate();
    }
    // �տ��������,�տ��޶�仯
    else {
      this.calculatePreceiveQuoTa();
    }
  }

  /**
   * �տ����Ӱ���տ��޶�
   */
  public void calculatePreceiveQuoTa() {
    UFDouble rate =
        this.keyValue.getHeadUFDoubleValue(SaleOrderHVO.NPRECEIVERATE);
    UFDouble totalorigmny =
        this.keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);

    String corigcurrency =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    // rate������Դģ��Ĭ��:�����տ��޶�
    if (rate != null && totalorigmny != null) {
      UFDouble npreceivequota = totalorigmny.multiply(rate).multiply(0.01);
      ScaleUtils scaleutil =
          new ScaleUtils(AppContext.getInstance().getPkGroup());
      npreceivequota = scaleutil.adjustMnyScale(npreceivequota, corigcurrency);

      this.keyValue.setHeadValue(SaleOrderHVO.NPRECEIVEQUOTA, npreceivequota);
    }
    else {
      this.keyValue.setHeadValue(SaleOrderHVO.NPRECEIVEQUOTA, null);
    }
  }

  /**
   * �տ��޶�Ӱ���տ����
   */
  public void calculatePreceiveRate() {
    UFDouble totalorigmny =
        this.keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);
    totalorigmny = totalorigmny == null ? UFDouble.ZERO_DBL : totalorigmny;
    UFDouble npreceivequota =
        this.keyValue.getHeadUFDoubleValue(SaleOrderHVO.NPRECEIVEQUOTA);
    npreceivequota =
        npreceivequota == null ? UFDouble.ZERO_DBL : npreceivequota;

    if (MathTool.compareTo(totalorigmny, UFDouble.ZERO_DBL) != 0) {
      // �����տ��޶�ͼ�˰�ϼ�,�����տ����
      this.keyValue.setHeadValue(SaleOrderHVO.NPRECEIVERATE, npreceivequota
          .div(totalorigmny).multiply(100));
    }
  }
}
