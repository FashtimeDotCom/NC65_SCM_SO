package nc.pubimpl.so.custmatrel.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.base.entity.AllowSale;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;

/**
 * ���ͻ��������ʼ�Ĺ�ϵ
 * 
 * @since 6.0
 * @version 2011-4-20 ����11:38:05
 * @author ף����
 */
public class CustMatRelCheckRule {

  /**
   * ���ͻ��������ʼ�Ĺ�ϵ
   * 
   * @param voMap
   * @param paravos
   */
  public UFBoolean[] getCustMatRelCheckResult(
      Map<Integer, CustMatRelParaVO> voMap, CustMatRelParaVO[] paravos) {
    // �ͻ��������Ƿ�Ϸ�����
    UFBoolean[] matchCstMtrl = new UFBoolean[paravos.length];
    Set<CustMatRelParaVO> tempSet = new HashSet<CustMatRelParaVO>();
    for (CustMatRelParaVO vo : paravos) {
      Integer index = vo.getParaindex();
      int intvalue = index.intValue();
      if (!voMap.containsKey(index)) {
        tempSet.add(vo);
        continue;
      }
      CustMatRelParaVO temp_vo = voMap.get(index);
      Integer allowsale = temp_vo.getAllowsale();
      UFBoolean exclude = temp_vo.getExclude();
      if (AllowSale.ALLOW_SALE.equalsValue(allowsale)) {
        if (null == exclude || !exclude.booleanValue()) {
          matchCstMtrl[intvalue] = UFBoolean.TRUE;
        }
        else {
          matchCstMtrl[intvalue] = UFBoolean.FALSE;
        }
      }
      else if (AllowSale.FORBID_SALE.equalsValue(allowsale)) {
        if (null == exclude || !exclude.booleanValue()) {
          matchCstMtrl[intvalue] = UFBoolean.FALSE;
        }
        else {
          matchCstMtrl[intvalue] = UFBoolean.TRUE;
        }
      }
    }
    if (tempSet.size() > 0) {
      this.dealOther(tempSet, matchCstMtrl);
    }
    return matchCstMtrl;
  }

  public void checkCustMatRel(Map<Integer, CustMatRelParaVO> voMap,
      CustMatRelParaVO[] paravos) {

    Set<CustMatRelParaVO> tempSet = new HashSet<CustMatRelParaVO>();
    StringBuilder errMsg = new StringBuilder();
    for (CustMatRelParaVO vo : paravos) {
      Integer index = vo.getParaindex();

      if (!voMap.containsKey(index)) {
        tempSet.add(vo);
        continue;
      }
      CustMatRelParaVO temp_vo = voMap.get(index);
      Integer allowsale = temp_vo.getAllowsale();
      UFBoolean exclude = temp_vo.getExclude();
      if (AllowSale.ALLOW_SALE.equalsValue(allowsale)) {
        if ((null == exclude) || !exclude.booleanValue()) {
          continue;
        }
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0017", null, new String[] {
              vo.getCrowno()
            })/*�к�Ϊ��{0}�Ķ����в�����ͻ������Ϲ�ϵ���壬���ʧ�ܡ�*/);
      }
      else if (AllowSale.FORBID_SALE.equalsValue(allowsale)) {
        if ((null == exclude) || !exclude.booleanValue()) {
          errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
              "04006007-0017", null, new String[] {
                vo.getCrowno()
              })/*�к�Ϊ��{0}�Ķ����в�����ͻ������Ϲ�ϵ���壬���ʧ�ܡ�*/);
        }
      }
    }
    if (tempSet.size() > 0) {
      this.checkOther(tempSet, errMsg);
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }

  }

  private void checkOther(Set<CustMatRelParaVO> tempSet, StringBuilder errMsg) {
    Set<String> orgSet = new HashSet<String>();
    CustMatRelParaVO[] paravos = new CustMatRelParaVO[tempSet.size()];
    tempSet.toArray(paravos);
    for (CustMatRelParaVO paravo : paravos) {
      orgSet.add(paravo.getPk_org());
    }
    CustMatRelHVO[] hvos = this.queryHvos(orgSet);
    if (null == hvos || hvos.length == 0) {
      return;
    }
    Map<String, CustMatRelHVO> map = new HashMap<String, CustMatRelHVO>();
    for (CustMatRelHVO hvo : hvos) {
      map.put(hvo.getPk_org(), hvo);
    }
    for (CustMatRelParaVO paravo : paravos) {

      String pk_org = paravo.getPk_org();
      if (!map.containsKey(pk_org)) {
        continue;
      }
      CustMatRelHVO hvo = map.get(pk_org);
      Integer allowsale = hvo.getAllowsale();
      if (AllowSale.ALLOW_SALE.equalsValue(allowsale)) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006007_0",
            "04006007-0017", null, new String[] {
              paravo.getCrowno()
            })/*�к�Ϊ��{0}�Ķ����в�����ͻ������Ϲ�ϵ���壬���ʧ�ܡ�*/);
      }
      else if (AllowSale.FORBID_SALE.equalsValue(allowsale)) {
        continue;
      }
    }
  }

  private void dealOther(Set<CustMatRelParaVO> tempSet, UFBoolean[] matchCstMtrl) {
    Set<String> orgSet = new HashSet<String>();
    CustMatRelParaVO[] paravos = new CustMatRelParaVO[tempSet.size()];
    tempSet.toArray(paravos);
    for (CustMatRelParaVO paravo : paravos) {
      orgSet.add(paravo.getPk_org());
    }
    CustMatRelHVO[] hvos = this.queryHvos(orgSet);
    if (null == hvos || hvos.length == 0) {
      return;
    }
    Map<String, CustMatRelHVO> map = new HashMap<String, CustMatRelHVO>();
    for (CustMatRelHVO hvo : hvos) {
      map.put(hvo.getPk_org(), hvo);
    }
    for (CustMatRelParaVO paravo : paravos) {
      int intvalue = paravo.getParaindex().intValue();
      String pk_org = paravo.getPk_org();
      if (!map.containsKey(pk_org)) {
        continue;
      }
      CustMatRelHVO hvo = map.get(pk_org);
      Integer allowsale = hvo.getAllowsale();
      if (AllowSale.ALLOW_SALE.equalsValue(allowsale)) {
        matchCstMtrl[intvalue] = UFBoolean.FALSE;
      }
      else if (AllowSale.FORBID_SALE.equalsValue(allowsale)) {
        // ���������¼֮ǰЧ��ĺϷ���Ϊfalse,��ô����Ϊ�ü�¼���Ϸ�,������true����
        matchCstMtrl[intvalue] = matchCstMtrl[intvalue];
      }
    }
  }

  private CustMatRelHVO[] queryHvos(Set<String> orgSet) {
    String[] orgs = new String[orgSet.size()];
    orgSet.toArray(orgs);
    StringBuffer sql = new StringBuffer();
    sql.append(" and ");
    SqlBuilder sqlbuilder = new SqlBuilder();
    sqlbuilder.append(CustMatRelHVO.PK_ORG, orgs);
    sql.append(sqlbuilder.toString());
    VOQuery<CustMatRelHVO> query =
        new VOQuery<CustMatRelHVO>(CustMatRelHVO.class);
    return query.query(sql.toString(), null);
  }
}
