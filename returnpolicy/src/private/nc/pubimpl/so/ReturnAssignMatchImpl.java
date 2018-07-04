package nc.pubimpl.so;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.pubimpl.so.rule.ChangeNullValueRule;
import nc.pubimpl.so.rule.CustParaExtendRule;
import nc.pubimpl.so.rule.MatchParaTableCreateRule;
import nc.pubimpl.so.rule.MatchResultRule;
import nc.pubimpl.so.rule.MatchReturnPoliceRule;
import nc.pubimpl.so.rule.MaterialParaExtendRule;
import nc.pubimpl.so.rule.ParaDataValidateRule;
import nc.pubimpl.so.rule.ParaFillIndexRule;
import nc.pubimpl.so.rule.ReturnAssignQueryRule;
import nc.pubitf.so.m30.IReturnAssignMatch;
import nc.pubitf.so.m30.ReturnAssignMatchVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.pub.util.BaseSaleClassUtil;

public class ReturnAssignMatchImpl implements IReturnAssignMatch {

  @Override
  public Map<String, String> matchReturnAssign(ReturnAssignMatchVO[] matchvos)
      throws BusinessException {
    Map<String, String> map = new HashMap<String, String>();
    if (null == matchvos || matchvos.length == 0) {
      return map;
    }
    String pk_org = matchvos[0].getPk_saleorg();
    map = this.addRule(pk_org,UFBoolean.TRUE, matchvos);
    return map;
  }
  
  @Override
  public Map<String, String> matchReturnPolicy(ReturnAssignMatchVO[] matchvos)
      throws BusinessException {
    Map<String, String> map = new HashMap<String, String>();
    if (null == matchvos || matchvos.length == 0) {
      return map;
    }
    String pk_org = matchvos[0].getPk_saleorg();
    map = this.addRule(pk_org,UFBoolean.FALSE, matchvos);
    return map;
  }

  private Map<String, String> addRule(String csaleorgid,UFBoolean check,
      ReturnAssignMatchVO[] matchvos) {
    // 1.���ƥ������Ϸ���
    new ParaDataValidateRule().validate(csaleorgid, matchvos);
    // 2.������ݣ���ÿ���������һ��Ψһ��ʶ
    new ParaFillIndexRule().fillIndex(matchvos);
    // 3.��չƥ�����
    ReturnAssignMatchVO[] extendparas = this.extendParas(csaleorgid, matchvos);
    // 4.��ֵת��
    new ChangeNullValueRule().changeNullValue(extendparas);
    // ������ʱ��
    String tempTable =
        new MatchParaTableCreateRule().createParaTempTable(extendparas);
    // ��ѯ�˻����߷�����
    ReturnAssignMatchVO[] results =
        new ReturnAssignQueryRule().queryReturnAssignMatchVO(tempTable);
    // ƥ�������˻����߷���
    Map<Integer, String> map = new MatchResultRule().match(results);
    // ƥ���˻��������ã�������˻����ʽ
    Map<String, String> reMap =
        new MatchReturnPoliceRule().matchReturnPolice(map, matchvos,check);
    return reMap;
  }

  /**
   * ��չ������Ϣ
   * 
   * @param csaleorgid
   * @param matchvos
   * @return
   */
  private ReturnAssignMatchVO[] extendParas(String csaleorgid,
      ReturnAssignMatchVO[] matchvos) {
    List<ReturnAssignMatchVO> extendpara = null;
    String pk_group = BSContext.getInstance().getGroupID();
    // ��չ����
    if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
      extendpara = new MaterialParaExtendRule().extendBaseClass(matchvos);
    }
    else {
      extendpara = new MaterialParaExtendRule().extendMarSaleClass(matchvos);
    }
    // ��չ�ͻ�
    if (BaseSaleClassUtil.isCustBaseClass(pk_group)) {
      new CustParaExtendRule().extendCustBaseClass(csaleorgid, extendpara);
    }
    else {
      new CustParaExtendRule().extendCustSaleClass(csaleorgid, extendpara);
    }
    ReturnAssignMatchVO[] extendparas =
        new ReturnAssignMatchVO[extendpara.size()];
    extendpara.toArray(extendparas);
    return extendparas;
  }

  
}
