package nc.bs.so.m33.biz.m32.rule.toar;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.pub.rule.CustDistributeCheck;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;

/**
 * @description
 * ��Ӧ��ǰ��ҵ��У�飨�ǿ����⡢���ͻ��Ƿ�����ڼ��Ż��ߵ�ǰӦ����֯��
 * @scene
 * ��Ӧ�ս���ǰ
 * @param
 * ��
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-5-28 ����10:17:23
 */
public class ToARCheckFor32Rule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {

    // �ǿ�����
    this.checkNullField(vos);

    // ���ͻ��Ƿ�����ڼ��Ż��ߵ�ǰӦ����֯
    this.checkCustDistribute(vos);

  }

  private void checkCustDistribute(SquareInvVO[] vos) {
    Map<String, String> map = new HashMap<String, String>();
    for (SquareInvVO svo : vos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {
        map.put(svo.getParentVO().getCinvoicecustid(), bvo.getCarorgid());
      }
    }
    new CustDistributeCheck().check(map);
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
