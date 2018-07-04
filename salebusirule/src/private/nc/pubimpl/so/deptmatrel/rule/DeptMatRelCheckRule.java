package nc.pubimpl.so.deptmatrel.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.base.entity.AllowSale;
import nc.vo.so.depmatrel.entity.DepMatRelHVO;

/**
 * ���š�ҵ��Ա������֮���ϵ���壺���ͻ��������ʼ�Ĺ�ϵ
 * 
 * @since 6.0
 * @version 2011-4-20 ����11:38:05
 * @author ף����
 */
public class DeptMatRelCheckRule {
  private StringBuffer errMsg = new StringBuffer();

  /**
   * ���ͻ��������ʼ�Ĺ�ϵ
   * 
   * @param voMap
   * @param paravos
   */
  public void checkDeptMatRel(Map<Integer, DeptMatRelParaVO> voMap,
      DeptMatRelParaVO[] paravos) {

    Set<DeptMatRelParaVO> tempSet = new HashSet<DeptMatRelParaVO>();
    for (DeptMatRelParaVO vo : paravos) {
      Integer index = vo.getParaindex();
      if (!voMap.containsKey(index)) {
        tempSet.add(vo);
        continue;
      }
      DeptMatRelParaVO temp_vo = voMap.get(index);
      Integer allowsale = temp_vo.getAllowsale();
      UFBoolean exclude = temp_vo.getExclude();
      if (AllowSale.ALLOW_SALE.equalsValue(allowsale)) {
        if ((null == exclude) || !exclude.booleanValue()) {
          continue;
        }
        this.errMsg.append(NCLangResOnserver.getInstance().getStrByID(
            "4006007_0", "04006007-0019", null, new String[] {
              vo.getCrowno()
            })/*�к�Ϊ��{0}�Ķ����в����㲿�š�ҵ��Ա�����Ϲ�ϵ���壬���ʧ�ܡ�*/);
      }
      else if (AllowSale.FORBID_SALE.equalsValue(allowsale)) {
        if ((null == exclude) || !exclude.booleanValue()) {
          this.errMsg.append(NCLangResOnserver.getInstance().getStrByID(
              "4006007_0", "04006007-0019", null, new String[] {
                vo.getCrowno()
              })/*�к�Ϊ��{0}�Ķ����в����㲿�š�ҵ��Ա�����Ϲ�ϵ���壬���ʧ�ܡ�*/);
        }
      }
    }
    if (tempSet.size() > 0) {
      this.dealOther(tempSet);
    }
    if (this.errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(this.errMsg.toString());
    }
  }

  private void dealOther(Set<DeptMatRelParaVO> tempSet) {
    Set<String> orgSet = new HashSet<String>();
    DeptMatRelParaVO[] paravos = new DeptMatRelParaVO[tempSet.size()];
    tempSet.toArray(paravos);
    for (DeptMatRelParaVO paravo : paravos) {
      orgSet.add(paravo.getPk_org());
    }
    DepMatRelHVO[] hvos = this.queryHvos(orgSet);
    if (null == hvos || hvos.length == 0) {
      return;
    }
    Map<String, DepMatRelHVO> map = new HashMap<String, DepMatRelHVO>();
    for (DepMatRelHVO hvo : hvos) {
      map.put(hvo.getPk_org(), hvo);
    }
    for (DeptMatRelParaVO paravo : paravos) {
      String pk_org = paravo.getPk_org();
      if (!map.containsKey(pk_org)) {
        continue;
      }
      DepMatRelHVO hvo = map.get(pk_org);
      Integer allowsale = hvo.getAllowsale();
      if (AllowSale.ALLOW_SALE.equalsValue(allowsale)) {
        this.errMsg.append(NCLangResOnserver.getInstance().getStrByID(
            "4006007_0", "04006007-0019", null, new String[] {
              paravo.getCrowno()
            })/*�к�Ϊ��{0}�Ķ����в����㲿�š�ҵ��Ա�����Ϲ�ϵ���壬���ʧ�ܡ�*/);
      }
      else if (AllowSale.FORBID_SALE.equalsValue(allowsale)) {
        continue;
      }
    }
  }

  private DepMatRelHVO[] queryHvos(Set<String> orgSet) {
    String[] orgs = new String[orgSet.size()];
    orgSet.toArray(orgs);
    StringBuffer sql = new StringBuffer();
    sql.append(" and ");
    SqlBuilder sqlbuilder = new SqlBuilder();
    sqlbuilder.append(DepMatRelHVO.PK_ORG, orgs);
    sql.append(sqlbuilder.toString());
    VOQuery<DepMatRelHVO> query = new VOQuery<DepMatRelHVO>(DepMatRelHVO.class);
    return query.query(sql.toString(), null);
  }
}
