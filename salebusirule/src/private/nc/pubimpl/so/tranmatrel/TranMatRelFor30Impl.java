package nc.pubimpl.so.tranmatrel;

import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelCheckRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelFillIndexRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelMatExtendRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelMatchResultRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelNullValueChgRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelOrgExtendRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelQueryRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelTableCreateRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelTranExtendRule;
import nc.pubimpl.so.tranmatrel.rule.TranMatRelValidateRule;
import nc.pubitf.so.tanmatrel.ITranMatRelFor30;
import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
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
public class TranMatRelFor30Impl implements ITranMatRelFor30 {
  @Override
  public UFBoolean checkTranMatRel(TranMatRelParaVO[] paravos)
      throws BusinessException {
    if (null == paravos) {
      return UFBoolean.TRUE;
    }
    this.addRule(paravos);
    return UFBoolean.TRUE;
  }

  private void addRule(TranMatRelParaVO[] paravos) {
    // 1.���ƥ������Ϸ���
    new TranMatRelValidateRule().validate(paravos);
    // 2.������ݣ���ÿ���������һ��Ψһ��ʶ
    new TranMatRelFillIndexRule().fillIndex(paravos);
    // 3.��չƥ�����
    TranMatRelParaVO[] extendparas = this.extendParas(paravos);
    // 4.��ֵת��
    new TranMatRelNullValueChgRule().changeNullValue(extendparas);
    // ������ʱ��
    String tempTable =
        new TranMatRelTableCreateRule().createParaTempTable(extendparas);
    // ��ѯ�������������Ϲ�ϵ�Ľ��
    TranMatRelParaVO[] results =
        new TranMatRelQueryRule().queryTranMatRelParaVO(tempTable);
    // ƥ�����Ž��
    Map<Integer, TranMatRelParaVO> voMap =
        new TranMatRelMatchResultRule().match(results);
    // ���ƥ����
    new TranMatRelCheckRule().checkTranMatRel(voMap, paravos);
  }

  /**
   * ��չ������Ϣ
   * 
   * @param csaleorgid
   * @param paravos
   * @return
   */
  private TranMatRelParaVO[] extendParas(TranMatRelParaVO[] paravos) {
    List<TranMatRelParaVO> extendpara = null;
    String pk_group = BSContext.getInstance().getGroupID();
    // ��չ����
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      extendpara = new TranMatRelMatExtendRule().extendBaseClass(paravos);
    }
    else {
      extendpara = new TranMatRelMatExtendRule().extendMarSaleClass(paravos);
    }
    // ��չ������֯
    String pk_org = paravos[0].getPk_org();
    new TranMatRelOrgExtendRule().extendSaleOrg(pk_org, extendpara);
    new TranMatRelTranExtendRule().extendTrans(extendpara);
    TranMatRelParaVO[] extendparas = new TranMatRelParaVO[extendpara.size()];
    extendpara.toArray(extendparas);
    return extendparas;
  }
}
