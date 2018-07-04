package nc.bs.so.m33.maintain.m4c.rule.detail;

import java.util.Map;

import nc.bs.so.m33.maintain.m4c.UpdateSquare4CFieldsBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * ���۳�������㵥������Ʒ������д�ۼƷ�����Ʒ����
 * @scene
 * ȡ�����۳�������㵥������Ʒ����������Ʒ���������۳��ⵥ��������Ʒ
 * @param 
 * ��
 */
public class RewriteIARegsiterFor4CRule implements IRule<SquareOutDetailVO> {

  /**
   * ���෽����д
   * ��Ϊ�����ʱ���һ�����ǲ�ѯ���㵥�����Դ�ʱ��д��ʱ����Ҫ���²�ѯ
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#setCustRelaDefValue(E[])
   */
  @Override
  public void process(SquareOutDetailVO[] dvos) {
    String[] sqbids =
        SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSALESQUAREBID);
    Map<String, SquareOutViewVO> map =
        new QuerySquare4CVOBP().queryMapSquareOutViewVOByBID(sqbids);

    for (SquareOutDetailVO dvo : dvos) {
      String bid = dvo.getCsalesquarebid();
      SquareOutViewVO view = map.get(bid);
      SquareOutBVO bvo = view.getItem();
      UFDouble oldvalue = bvo.getNsquareregnum();
      UFDouble newvalue = dvo.getNsquarenum();
      bvo.setNsquareregnum(MathTool.add(oldvalue, newvalue));
      if (bvo.getNnum().compareTo(bvo.getNsquareregnum()) == 0) {
        bvo.setBsquareiafinish(UFBoolean.TRUE);
      }
      else {
        bvo.setBsquareiafinish(UFBoolean.FALSE);
      }
    }

    int size = map.values().size();
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().combineBill(
            map.values().toArray(new SquareOutViewVO[size]));

    // ��д���㵥�ۼƳɱ���������
    new UpdateSquare4CFieldsBP().updateFields(sqvos, null, new String[] {
      SquareOutBVO.NSQUAREREGNUM, SquareOutBVO.BSQUAREIAFINISH
    });

  }

}
