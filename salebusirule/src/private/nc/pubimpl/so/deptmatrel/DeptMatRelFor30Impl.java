package nc.pubimpl.so.deptmatrel;

import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelCheckRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelDeptExtendRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelEmpliyerExtendRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelFillIndexRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelMatExtendRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelMatchResultRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelNullValueChgRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelQueryRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelTableCreateRule;
import nc.pubimpl.so.deptmatrel.rule.DeptMatRelValidateRule;
import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.pubitf.so.deptmatrel.IDeptMatRelFor30;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.pub.util.BaseSaleClassUtil;

/**
 * ���š�ҵ��Ա�����Ϲ�ϵ����
 * 
 * @since 6.0
 * @version 2011-4-20 ����01:43:11
 * @author ף����
 */
public class DeptMatRelFor30Impl implements IDeptMatRelFor30 {
  @Override
  public UFBoolean checkDeptMatRel(DeptMatRelParaVO[] paravos)
      throws BusinessException {
    if (null == paravos) {
      return UFBoolean.TRUE;
    }
    this.addRule(paravos);
    return UFBoolean.TRUE;
  }

  private void addRule(DeptMatRelParaVO[] paravos) {
    // 1.���ƥ������Ϸ���
    new DeptMatRelValidateRule().validate(paravos);
    // 2.������ݣ���ÿ���������һ��Ψһ��ʶ
    new DeptMatRelFillIndexRule().fillIndex(paravos);
    // 3.��չƥ�����
    DeptMatRelParaVO[] extendparas = this.extendParas(paravos);
    // 4.��ֵת��
    new DeptMatRelNullValueChgRule().changeNullValue(extendparas);
    // ������ʱ��
    String tempTable =
        new DeptMatRelTableCreateRule().createParaTempTable(extendparas);
    // ��ѯ���š�ҵ��Ա�����Ϲ�ϵ�Ľ��
    DeptMatRelParaVO[] results =
        new DeptMatRelQueryRule().queryDeptMatRelParaVO(tempTable);
    // ƥ�����Ž��
    Map<Integer, DeptMatRelParaVO> voMap =
        new DeptMatRelMatchResultRule().match(results);
    // ���ƥ����
    new DeptMatRelCheckRule().checkDeptMatRel(voMap, paravos);
  }

  /**
   * ��չ������Ϣ
   * 
   * @param paravos
   * 
   * @param csaleorgid
   * @param paravos
   * @return
   */
  private DeptMatRelParaVO[] extendParas(DeptMatRelParaVO[] paravos) {
    List<DeptMatRelParaVO> extendpara = null;
    String pk_group = BSContext.getInstance().getGroupID();
    // ��չ����
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      extendpara = new DeptMatRelMatExtendRule().extendBaseClass(paravos);
    }
    else {
      extendpara = new DeptMatRelMatExtendRule().extendMarSaleClass(paravos);
    }
    DeptMatRelEmpliyerExtendRule employer = new DeptMatRelEmpliyerExtendRule();
    employer.extendEmployer(extendpara);
    DeptMatRelDeptExtendRule dept = new DeptMatRelDeptExtendRule();
    dept.extendDept(extendpara);
    DeptMatRelParaVO[] extendparas = new DeptMatRelParaVO[extendpara.size()];
    extendpara.toArray(extendparas);
    return extendparas;
  }
}
