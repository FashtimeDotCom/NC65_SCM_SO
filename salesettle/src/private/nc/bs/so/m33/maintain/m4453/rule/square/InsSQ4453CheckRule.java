package nc.bs.so.m33.maintain.m4453.rule.square;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.pub.biz.vocheck.NumPriceMnyPubCheck;

/**
 * @description
 * ��ʽ����;������㵥����ǰУ�����
 * @scene
 * ��ʽ����;������㵥����ǰ
 * @param
 * ��
 */
public class InsSQ4453CheckRule implements IRule<SquareWasVO> {

  @Override
  public void process(SquareWasVO[] vos) {

    // �ǿ�����
    this.checkNullField(vos);

    // ���۽��ƽ���Լ���
    new NumPriceMnyPubCheck<SquareWasVO>().checkData(vos);
  }

  private void checkNullField(SquareWasVO[] vos) {
    String errorMsg = null;
    for (SquareWasVO svo : vos) {
      for (SquareWasBVO bvo : svo.getChildrenVO()) {
        // ���������֯
        if (PubAppTool.isNull(bvo.getPk_org())
            || PubAppTool.isNull(svo.getParentVO().getPk_org())) {
          errorMsg =
              NCLangResOnserver.getInstance().getStrByID("4006010_0",
                  "04006010-0097")/*����;������㵥�Ľ��������֯Ϊ�գ�*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCcostorgid())) {
          errorMsg =
              NCLangResOnserver.getInstance().getStrByID("4006010_0",
                  "04006010-0098")/*����;������㵥�ĳɱ�����Ϊ�գ�*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCcurrencyid())) {
          errorMsg =
              NCLangResOnserver.getInstance().getStrByID("4006010_0",
                  "04006010-0099")/*����;������㵥�ı��Ҳ���Ϊ�գ�*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCorigcurrencyid())) {
          errorMsg =
              NCLangResOnserver.getInstance().getStrByID("4006010_0",
                  "04006010-0100")/*����;������㵥��ԭ�Ҳ���Ϊ�գ�*/;
          break;
        }
        if (PubAppTool.isNull(bvo.getCorigcurrencyid())) {
          errorMsg =
              NCLangResOnserver.getInstance().getStrByID("4006010_0",
                  "04006010-0100")/*����;������㵥��ԭ�Ҳ���Ϊ�գ�*/;
          break;
        }
      }
    }

    if (errorMsg != null) {
      ExceptionUtils.wrappBusinessException(errorMsg);
    }
  }

}
