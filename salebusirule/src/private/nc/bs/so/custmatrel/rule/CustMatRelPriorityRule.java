package nc.bs.so.custmatrel.rule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.para.IPriorityCode;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;
import nc.vo.so.pub.para.CustBaseclPriorityCode;
import nc.vo.so.pub.para.CustSaleclPriorityCode;
import nc.vo.so.pub.para.MarBaseclPriorityCode;
import nc.vo.so.pub.para.MarSaleclPriorityCode;
import nc.vo.so.pub.para.SinglePriorityCode;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.so.pub.util.PriorityCodeGenUtil;

/**
 * @description
 * ���ۿͻ����Ϲ�ϵ����ǰ����������(������֯ (00) + ����(0) + ���Ϸ���(00) + �ͻ�(0) + �ͻ�����)
 * @scene
 * ���ۿͻ����Ϲ�ϵ�������޸ı���ǰ
 * @param
 * ��
 */
public class CustMatRelPriorityRule implements IRule<CustMatRelVO> {

  @Override
  public void process(CustMatRelVO[] vos) {
    for (CustMatRelVO vo : vos) {
      this.setPriority(vo);
    }
  }

  private IPriorityCode[] getPriorityCodeItems(CustMatRelBVO bvo,
      boolean ismarbase, boolean iscustbase) {
    String pk_org = bvo.getPk_org();
    if (null == pk_org) {
      pk_org = BSContext.getInstance().getGroupID();
    }
    IPriorityCode[] codeitems = new IPriorityCode[4];
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
    // �ͻ�
    codeitems[2] = new SinglePriorityCode(bvo.getPk_customer());
    // �ͻ�����
    if (iscustbase) {
      codeitems[3] =
          new CustBaseclPriorityCode(bvo.getPk_custbaseclass(), pk_org);
    }
    else {
      codeitems[3] =
          new CustSaleclPriorityCode(bvo.getPk_custsaleclass(), pk_org);
    }
    return codeitems;
  }

  /**
   * ����������
   * 
   * @param vo
   */
  private void setPriority(CustMatRelVO vo) {
    // ���������ɹ��� ������֯ (00) + ����(0) + ���Ϸ���(00) + �ͻ�(0) + �ͻ�����
    String pk_group = BSContext.getInstance().getGroupID();
    boolean ismarbase = BaseSaleClassUtil.isMarBaseClass(pk_group);
    boolean iscustbase = BaseSaleClassUtil.isCustBaseClass(pk_group);
    CustMatRelBVO[] bvos = vo.getChildrenVO();
    if (null == bvos) {
      return;
    }
    for (CustMatRelBVO bvo : bvos) {
      IPriorityCode[] pricodeitems =
          this.getPriorityCodeItems(bvo, ismarbase, iscustbase);
      String pricode = PriorityCodeGenUtil.genPriorityCode(pricodeitems);
      bvo.setCprioritycode(pricode);
    }
  }

}
