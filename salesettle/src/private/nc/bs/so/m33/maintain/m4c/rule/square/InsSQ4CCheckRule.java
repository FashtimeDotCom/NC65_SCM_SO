package nc.bs.so.m33.maintain.m4c.rule.square;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.pub.biz.vocheck.NumPriceMnyPubCheck;

/**
 * @description
 * ���۽��㵥����У�����
 * @scene
 * ���۽��㵥����ǰ
 * @param 
 * ��
 */
public class InsSQ4CCheckRule implements IRule<SquareOutVO> {

  @Override
  public void process(SquareOutVO[] vos) {

    // �ǿ�����
    this.checkNullField(vos);

    // ���۽��ƽ���Լ���
    new NumPriceMnyPubCheck<SquareOutVO>().checkData(vos);
  }

  private void checkNullField(SquareOutVO[] vos) {
    String errorMsg = null;
    for (SquareOutVO svo : vos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        // ���������֯
        if (PubAppTool.isNull(bvo.getPk_org())
            || PubAppTool.isNull(svo.getParentVO().getPk_org())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0042")/*@res "�������۳�������㵥�Ľ��������֯Ϊ�գ�"*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCcostorgid())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0043")/*@res "�������۳�������㵥�ĳɱ�����Ϊ�գ�"*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCcurrencyid())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0044")/*@res "�������۳�������㵥�ı��Ҳ���Ϊ�գ�"*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCorigcurrencyid())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0045")/*@res "�������۳�������㵥��ԭ�Ҳ���Ϊ�գ�"*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCorigcurrencyid())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0045")/*@res "�������۳�������㵥��ԭ�Ҳ���Ϊ�գ�"*/;
          break;
        }
      }
    }

    if (errorMsg != null) {
      ExceptionUtils.wrappBusinessException(errorMsg);
    }
  }

}
