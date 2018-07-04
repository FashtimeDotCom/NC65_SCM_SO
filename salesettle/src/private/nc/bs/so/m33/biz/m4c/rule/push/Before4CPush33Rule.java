package nc.bs.so.m33.biz.m4c.rule.push;

import nc.bs.so.m33.maintain.m4c.UpdateSquare4CFlagBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m33.m4c.entity.SquareOutVO;

/**
 * @description
 * ���۳��ⵥ��ʽ�������۽��㵥ʱ��ҵ���������Ĭ�ϵĽ����־
 * @scene
 * ���۳��ⵥ��ʽ�������۽��㵥ʱ
 * @param 
 * ��
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-5-28 ����10:12:52
 */
public class Before4CPush33Rule implements IRule<SquareOutVO> {

  @Override
  public void process(SquareOutVO[] vos) {
    // ����Ĭ�ϵĽ����־
    this.setDefaultSquareFlag(vos);
  }

  /**
   * ����Ĭ�ϵĽ����־�����۳��ⵥ��ʽ���ɽ��㵥��ʱ��
   * 
   * @param vos
   */
  private void setDefaultSquareFlag(SquareOutVO[] vos) {
    // ���۳��ⵥ��ʽ���ɽ��㵥��ʱ����±�������־λ
    new UpdateSquare4CFlagBP().updateSquareBFlagFor4CPush33(vos);
  }

}
