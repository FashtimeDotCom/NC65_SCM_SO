package nc.bs.so.m33.biz.m4453.rule.push;

import java.util.Map;

import nc.bs.so.m33.pub.QuerySaleOrderEndInfoBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.util.SOVOChecker;

/**
 * @description
 * ;�𵥳ɱ�����ʱ�ж��Ƿ�Դͷ�����гɱ�����ر�
 * @scene
 * ���۳��ⵥ��������Ʒǰ;�𵥳ɱ�����
 * @param
 * ��
 * @since 6.0
 * @version 2011-12-6 ����10:12:59
 * @author zhangcheng
 */
public class CheckBeforeCostSquareWasRule implements IRule<SquareWasVO> {

  @Override
  public void process(SquareWasVO[] vos) {

    // �������۶����Ѿ�����رգ�������;������
    String[] ordbids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareWasBVO.CFIRSTBID,
            String.class);

    // ;��Դͷ�����۶���
    if (SOVOChecker.isEmpty(ordbids)) {
      return;
    }

    Map<String, UFBoolean[]> map =
        new QuerySaleOrderEndInfoBP().querySaleOrderEndInfo(ordbids);

    if (SOVOChecker.isEmpty(map)) {
      return;
    }

    for (SquareWasVO wasvo : vos) {
      for (SquareWasBVO bvo : wasvo.getChildrenVO()) {
        String ordbid = bvo.getCfirstbid();
        UFBoolean[] flag = map.get(ordbid);
        if (flag[1].booleanValue()) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006010_0", "04006010-0145")/*@res "�������۶����Ѿ�����رգ�������;������!"*/);

        }
      }
    }

  }

}
