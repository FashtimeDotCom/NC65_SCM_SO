package nc.bs.so.m33.maintain.m32.rule.square;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.pub.biz.vocheck.NumPriceMnyPubCheck;

/**
 * @description
 * ���۷�Ʊ���㱣��ǰУ�飺�ǿ����⡢���۽��ƽ���Լ���
 * @scene
 * ���۷�Ʊ����ǰ
 * @param
 * ��
 */
public class InsSQ32CheckRule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {
    // �ǿ�����
    this.checkNullField(vos);
    // ���۽��ƽ���Լ���
    new NumPriceMnyPubCheck<SquareInvVO>().checkData(vos);
  }

  private void checkNullField(SquareInvVO[] vos) {
    String errorMsg = null;
    for (SquareInvVO svo : vos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {
        // ���������֯
        if (PubAppTool.isNull(bvo.getPk_org())
            || PubAppTool.isNull(svo.getParentVO().getPk_org())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0033")/*@res "�������۷�Ʊ�����㵥�Ľ��������֯Ϊ�գ�"*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCcurrencyid())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0034")/*@res "�������۷�Ʊ�����㵥�ı��Ҳ���Ϊ�գ�"*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCorigcurrencyid())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0035")/*@res "�������۷�Ʊ�����㵥��ԭ�Ҳ���Ϊ�գ�"*/;
          break;
        }
      }
    }
    if (errorMsg != null) {
      ExceptionUtils.wrappBusinessException(errorMsg);
    }
  }

}
