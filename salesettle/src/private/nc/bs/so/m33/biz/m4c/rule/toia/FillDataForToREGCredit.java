package nc.bs.so.m33.biz.m4c.rule.toia;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;

/**
 * @description
 * ���۳�������㵥��������Ʒ������־
 * @scene
 * ���۳�������㵥��������Ʒ����
 * @param 
 * ��
 * @since 6.0
 * @version 2011-8-8 ����09:51:33
 * @author zhangcheng
 */
public class FillDataForToREGCredit implements IRule<SquareOutVO> {

  @Override
  public void process(SquareOutVO[] vos) {

    // ���ô�����־
    for (SquareOutVO svo : vos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        bvo.setFpreiatype(SquareType.SQUARETYPE_REG_CREDIT.getIntegerValue());
      }
    }

  }

}
