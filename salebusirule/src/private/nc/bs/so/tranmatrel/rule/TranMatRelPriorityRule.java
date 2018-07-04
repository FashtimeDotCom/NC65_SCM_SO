package nc.bs.so.tranmatrel.rule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.para.IPriorityCode;
import nc.vo.so.pub.para.MarBaseclPriorityCode;
import nc.vo.so.pub.para.MarSaleclPriorityCode;
import nc.vo.so.pub.para.SinglePriorityCode;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.so.pub.util.PriorityCodeGenUtil;
import nc.vo.so.tranmatrel.entity.TranMatRelBVO;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;

/**
 * @description
 * ���۶������Ϲ�ϵ����ǰ����������(������֯ (00) + ����(0) + ���Ϸ���(00) + ����(0) + ��������)
 * @scene
 * ���۶������Ϲ�ϵ�������޸ı���ǰ
 * @param
 * ��
 */
public class TranMatRelPriorityRule implements IRule<TranMatRelVO> {

  @Override
  public void process(TranMatRelVO[] vos) {
    for (TranMatRelVO vo : vos) {
      this.setPriority(vo);
    }
  }

  private IPriorityCode[] getPriorityCodeItems(TranMatRelBVO bvo,
      boolean ismarbase) {
    String pk_org = bvo.getPk_org();
    if (null == pk_org) {
      pk_org = BSContext.getInstance().getGroupID();
    }
    IPriorityCode[] codeitems = new IPriorityCode[3];
    // ����
    codeitems[0] = new SinglePriorityCode(bvo.getPk_material());
    // ���Ϸ���
    if (ismarbase) {
      codeitems[1] =
          new MarBaseclPriorityCode(bvo.getPk_materialbaseclass(), pk_org);
    }
    else {
      codeitems[1] =
          new MarSaleclPriorityCode(bvo.getPk_materialsaleclass(), pk_org);
    }
    codeitems[2] = new SinglePriorityCode(bvo.getTrantype());
    return codeitems;
  }

  /**
   * ����������
   * 
   * @param vo
   */
  private void setPriority(TranMatRelVO vo) {
    // ���������ɹ��� ������֯ (00) + ����(0) + ���Ϸ���(00) + �ͻ�(0) + �ͻ�����
    String pk_group = BSContext.getInstance().getGroupID();
    boolean ismarbase = BaseSaleClassUtil.isMarBaseClass(pk_group);
    TranMatRelBVO[] bvos = vo.getChildrenVO();
    if (null == bvos) {
      return;
    }
    for (TranMatRelBVO bvo : bvos) {
      IPriorityCode[] pricodeitems = this.getPriorityCodeItems(bvo, ismarbase);
      String pricode = PriorityCodeGenUtil.genPriorityCode(pricodeitems);
      bvo.setCprioritycode(pricode);
    }
  }

}
