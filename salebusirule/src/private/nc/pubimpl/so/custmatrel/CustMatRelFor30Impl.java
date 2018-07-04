package nc.pubimpl.so.custmatrel;

import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.pubimpl.so.custmatrel.rule.CustMatRelCheckRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelCustExtendRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelFillIndexRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelMatExtendRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelMatchResultRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelNullValueChgRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelOtherExtendRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelQueryRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelTableCreateRule;
import nc.pubimpl.so.custmatrel.rule.CustMatRelValidateRule;
import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.pubitf.so.custmatrel.ICustMatRelFor30;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.pub.util.BaseSaleClassUtil;

public class CustMatRelFor30Impl implements ICustMatRelFor30 {

  @Override
  public void checkCustMatRel(CustMatRelParaVO[] paravos)
      throws BusinessException {

    // ������ݣ���ÿ���������һ��Ψһ��ʶ
    new CustMatRelFillIndexRule().fillIndex(paravos);
    // ���ƥ������Ϸ��ԣ���¼�Ϸ���־
    new CustMatRelValidateRule().validate(paravos);
    // ��չƥ�����
    CustMatRelParaVO[] extendparas = this.extendParas(paravos);
    // ��ֵת��
    new CustMatRelNullValueChgRule().changeNullValue(extendparas);
    // ������ʱ��
    String tempTable =
        new CustMatRelTableCreateRule().createParaTempTable(extendparas);
    // ��ѯ�ͻ������Ϲ�ϵ�Ľ��
    CustMatRelParaVO[] results =
        new CustMatRelQueryRule().queryCustMatRelParaVO(tempTable);
    // ƥ�����Ž��
    Map<Integer, CustMatRelParaVO> voMap =
        new CustMatRelMatchResultRule().match(results);
    // ���ƥ����
    CustMatRelCheckRule checkrule = new CustMatRelCheckRule();
    checkrule.checkCustMatRel(voMap, paravos);

  }

  /**
   * ���ͻ������Ϲ�ϵ,��¼�Ƿ��������ۿͻ�������
   * 
   * @param paravos �ͻ������ϼ���
   * @return UFBoolean[] �ͻ��������Ƿ���������
   */
  @Override
  public UFBoolean[] getCustMatRelSaleFlag(CustMatRelParaVO[] paravos) {

    // ������ݣ���ÿ���������һ��Ψһ��ʶ
    new CustMatRelFillIndexRule().fillIndex(paravos);
    // ���ƥ������Ϸ��ԣ���¼�Ϸ���־
    UFBoolean[] validateNull = new CustMatRelValidateRule().validate(paravos);
    // ��չƥ�����
    CustMatRelParaVO[] extendparas = this.extendParas(paravos);
    // ��ֵת��
    new CustMatRelNullValueChgRule().changeNullValue(extendparas);
    // ������ʱ��
    String tempTable =
        new CustMatRelTableCreateRule().createParaTempTable(extendparas);
    // ��ѯ�ͻ������Ϲ�ϵ�Ľ��
    CustMatRelParaVO[] results =
        new CustMatRelQueryRule().queryCustMatRelParaVO(tempTable);
    // ƥ�����Ž��
    Map<Integer, CustMatRelParaVO> voMap =
        new CustMatRelMatchResultRule().match(results);
    // ���ƥ��������¼�Ϸ���־
    CustMatRelCheckRule checkrule = new CustMatRelCheckRule();
    UFBoolean[] matchCstMtrl =
        checkrule.getCustMatRelCheckResult(voMap, paravos);
    // �ϲ����ƥ����,�Ϳ�ֵЧ����
    for (int i = 0; i < validateNull.length; i++) {
      if (null == matchCstMtrl[i]) {
        matchCstMtrl[i] = UFBoolean.TRUE;
      }
      if (!validateNull[i].booleanValue() || !matchCstMtrl[i].booleanValue()) {
        matchCstMtrl[i] = UFBoolean.FALSE;
      }
      else {
        matchCstMtrl[i] = UFBoolean.TRUE;
      }
    }
    return matchCstMtrl;
  }

  /**
   * ��չ������Ϣ
   * 
   * @param csaleorgid
   * @param paravos
   * @return
   */
  private CustMatRelParaVO[] extendParas(CustMatRelParaVO[] paravos) {
    List<CustMatRelParaVO> extendpara = null;
    String pk_group = BSContext.getInstance().getGroupID();
    String pk_org = paravos[0].getPk_org();
    // ��չ����
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      extendpara = new CustMatRelMatExtendRule().extendBaseClass(paravos);
    }
    else {
      extendpara = new CustMatRelMatExtendRule().extendMarSaleClass(paravos);
    }
    // ��չ�ͻ�
    if (BaseSaleClassUtil.isCustBaseClass(pk_group)) {
      new CustMatRelCustExtendRule().extendCustBaseClass(pk_org, extendpara);
    }
    else {
      new CustMatRelCustExtendRule().extendCustSaleClass(pk_org, extendpara);
    }
    new CustMatRelOtherExtendRule().extendOther(extendpara);
    CustMatRelParaVO[] extendparas = new CustMatRelParaVO[extendpara.size()];
    extendpara.toArray(extendparas);
    return extendparas;
  }
}
