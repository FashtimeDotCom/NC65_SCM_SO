package nc.bs.so.m33.biz.m4c.rule.toar;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.pub.rule.CustDistributeCheck;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;

/**
 * @description
 * ���۳��ⵥ��Ӧ��ǰ ������򣨷ǿ����⡢���ͻ��Ƿ�����ڼ��Ż��ߵ�ǰӦ����֯��
 * @scene
 * ���۳��ⵥ��Ӧ�ա����س�Ӧ�ա�����Գ崫�س�Ӧ��ǰ 
 * @param 
 * ��
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-5-28 ����10:17:23
 */
public class ToARCheckFor4CRule implements IRule<SquareOutVO> {

  @Override
  public void process(SquareOutVO[] vos) {

    // �ǿ�����
    this.checkNullField(vos);

    // ���ͻ��Ƿ�����ڼ��Ż��ߵ�ǰӦ����֯
    this.checkCustDistribute(vos);
  }

  private void checkCustDistribute(SquareOutVO[] vos) {
    Map<String, String> map = new HashMap<String, String>();
    for (SquareOutVO svo : vos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        map.put(bvo.getCinvoicecustid(), bvo.getCarorgid());
      }
    }
    new CustDistributeCheck().check(map);
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
