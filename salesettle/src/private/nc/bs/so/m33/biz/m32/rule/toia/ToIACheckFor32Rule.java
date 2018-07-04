package nc.bs.so.m33.biz.m32.rule.toia;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;

/**
 * @description
 * ���γ��ⵥ����������Ʒ����Ʊ��������Ʒ���� ,���㴫�ɱ�ǰУ����ɱ������
 * @scene
 * ���γ��ⵥ����������Ʒ����Ʊ��������Ʒ�ɱ�����ǰ
 * @param
 * ��
 *
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-5-28 ����10:19:52
 */
public class ToIACheckFor32Rule implements IRule<SquareInvVO> {

  private void checkNullField(SquareInvVO[] vos) {

    String errorMsg = null;

    for (SquareInvVO svo : vos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {



        // ���������֯
        if (PubAppTool.isNull(bvo.getPk_org())
            || PubAppTool.isNull(svo.getParentVO().getPk_org())) {
          errorMsg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0014")/*@res "���ɴ����㵥�Ľ��������֯Ϊ�գ�"*/;
          break;
        }

        // Ӧ����֯
        /*
         * if (PubAppTool.getInstance().isNull(bvo.getCarorgid())){ errorMsg =
         * "���ɴ����㵥��Ӧ����֯Ϊ�գ�"; break; }
         */


        // �ɱ���
        if (PubAppTool.isNull(bvo.getCcostorgid())) {
          errorMsg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0016")/*@res "���ɴ����㵥�ĳɱ���Ϊ�գ�"*/;
          break;
        }

      }
    }

    if (errorMsg != null) {

      ExceptionUtils.wrappBusinessException(errorMsg);
    }
  }

  @Override
  public void process(SquareInvVO[] vos) {

    // �ǿ�����
    this.checkNullField(vos);

    // ���۽��ƽ���Լ���

  }

}