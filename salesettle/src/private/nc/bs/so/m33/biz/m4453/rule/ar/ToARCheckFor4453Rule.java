package nc.bs.so.m33.biz.m4453.rule.ar;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.pub.rule.CustDistributeCheck;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;

/**
 * @description
 * ����;��Ӧ�ս���ǰ����У�飨�ǿ����⡢���ͻ��Ƿ�����ڼ��Ż��ߵ�ǰӦ����֯��
 * @scene
 * ����;��Ӧ�ս��㡢�س�Ӧ�ս���ǰ
 * @param
 * ��
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-5-28 ����10:17:23
 */
public class ToARCheckFor4453Rule implements IRule<SquareWasVO> {

  @Override
  public void process(SquareWasVO[] vos) {

    // �ǿ�����
    this.checkNullField(vos);

    // ���ͻ��Ƿ�����ڼ��Ż��ߵ�ǰӦ����֯
    this.checkCustDistribute(vos);
  }

  private void checkCustDistribute(SquareWasVO[] vos) {
    Map<String, String> map = new HashMap<String, String>();
    for (SquareWasVO svo : vos) {
      for (SquareWasBVO bvo : svo.getChildrenVO()) {
        map.put(bvo.getCinvoicecustid(), bvo.getCarorgid());
      }
    }
    new CustDistributeCheck().check(map);
  }

  private void checkNullField(SquareWasVO[] vos) {
    String errorMsg = null;
    for (SquareWasVO svo : vos) {
      for (SquareWasBVO bvo : svo.getChildrenVO()) {
        // ���������֯
        if (PubAppTool.isNull(bvo.getPk_org())
            || PubAppTool.isNull(svo.getParentVO().getPk_org())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0014")/*@res "���ɴ����㵥�Ľ��������֯Ϊ�գ�"*/;
          break;
        }
        // Ӧ����֯
        if (PubAppTool.isNull(bvo.getCarorgid())) {
          errorMsg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006010_0", "04006010-0015")/*@res "���ɴ����㵥��Ӧ����֯Ϊ�գ�"*/;
          break;
        }
      }
    }
    if (errorMsg != null) {
      ExceptionUtils.wrappBusinessException(errorMsg);
    }
  }

}
