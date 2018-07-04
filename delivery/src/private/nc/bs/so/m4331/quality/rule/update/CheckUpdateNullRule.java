package nc.bs.so.m4331.quality.rule.update;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

/**
 * @description
 * ���۷������ʼ���Ϣ�޸ı���ǰУ���ܷ�ɾ��
 * @scene
 * ���۷������ʼ���Ϣ�޸ı���ǰ
 * @param
 * ��
 */
public class CheckUpdateNullRule implements IRule<DeliveryCheckVO> {

  @Override
  public void process(DeliveryCheckVO[] vos) {
    for (DeliveryCheckVO vo : vos) {
      UFDouble nqtorignetprice = vo.getNqtorignetprice();
      if (null == nqtorignetprice
          || nqtorignetprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0058")/*@res "��˰���۲���Ϊ�ջ���Ϊ0��"*/);
      }
      UFDouble taxnetprice = vo.getNqtorigtaxnetprc();
      if (null == taxnetprice || taxnetprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0059")/*@res "��˰���۲���Ϊ�ջ���Ϊ0��"*/);
      }
    }
  }
}