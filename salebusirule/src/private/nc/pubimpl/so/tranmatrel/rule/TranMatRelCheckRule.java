package nc.pubimpl.so.tranmatrel.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.base.entity.AllowSale;
import nc.vo.so.tranmatrel.entity.TranMatRelHVO;

/**
 * �������������Ϲ�ϵ���壺��鵥���������ʼ�Ĺ�ϵ
 * 
 * @since 6.0
 * @version 2011-4-20 ����11:38:05
 * @author ף����
 */
public class TranMatRelCheckRule {
  private StringBuffer errMsg = new StringBuffer();

  /**
   * ��鵥���������ʼ�Ĺ�ϵ
   * 
   * @param voMap
   * @param paravos
   */
  public void checkTranMatRel(Map<Integer, TranMatRelParaVO> voMap,
      TranMatRelParaVO[] paravos) {
    Set<TranMatRelParaVO> tempSet = new HashSet<TranMatRelParaVO>();
    for (TranMatRelParaVO vo : paravos) {
      Integer index = vo.getParaindex();
      if (!voMap.containsKey(index)) {
        tempSet.add(vo);
        continue;
      }
      TranMatRelParaVO temp_vo = voMap.get(index);
      Integer allowsale = temp_vo.getAllowsale();
      UFBoolean exclude = temp_vo.getExclude();
      if (AllowSale.ALLOW_SALE.equalsValue(allowsale)) {
        if ((null == exclude) || !exclude.booleanValue()) {
          continue;
        }
        this.errMsg.append(NCLangResOnserver.getInstance().getStrByID(
            "4006007_0", "04006007-0020", null, new String[] {
              vo.getCrowno()
            })/*�к�Ϊ��{0}�Ķ����в����㵥�����������Ϲ�ϵ���壬���ʧ�ܡ�*/);
      }
      else if (AllowSale.FORBID_SALE.equalsValue(allowsale)) {
        if ((null == exclude) || !exclude.booleanValue()) {
          this.errMsg.append(NCLangResOnserver.getInstance().getStrByID(
              "4006007_0", "04006007-0020", null, new String[] {
                vo.getCrowno()
              })/*�к�Ϊ��{0}�Ķ����в����㵥�����������Ϲ�ϵ���壬���ʧ�ܡ�*/);
        }
      }
    }
    if (tempSet.size() > 0) {
      this.dealOther(tempSet);
      //begin-ncm-shenjzh-�����¼�δ����
      this.dealOtherP(tempSet);
      //end-ncm-shenjzh-�����¼�δ����
    }
    if (this.errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(this.errMsg.toString());
    }
  }

  private void dealOther(Set<TranMatRelParaVO> tempSet) {
    Set<String> orgSet = new HashSet<String>();
    TranMatRelParaVO[] paravos = new TranMatRelParaVO[tempSet.size()];
    tempSet.toArray(paravos);
    for (TranMatRelParaVO paravo : paravos) {
      orgSet.add(paravo.getPk_org());
    }
    TranMatRelHVO[] hvos = this.queryHvos(orgSet);
    if (null == hvos || hvos.length == 0) {
      return;
    }
    Map<String, TranMatRelHVO> map = new HashMap<String, TranMatRelHVO>();
    for (TranMatRelHVO hvo : hvos) {
      map.put(hvo.getPk_org(), hvo);
    }
    for (TranMatRelParaVO paravo : paravos) {
      String pk_org = paravo.getPk_org();
      if (!map.containsKey(pk_org)) {
        continue;
      }
      TranMatRelHVO hvo = map.get(pk_org);
      Integer allowsale = hvo.getAllowsale();
      if (AllowSale.ALLOW_SALE.equalsValue(allowsale)) {
        this.errMsg.append(NCLangResOnserver.getInstance().getStrByID(
            "4006007_0", "04006007-0020", null, new String[] {
              paravo.getCrowno()
            })/*�к�Ϊ��{0}�Ķ����в����㵥�����������Ϲ�ϵ���壬���ʧ�ܡ�*/);
      }
      else if (AllowSale.FORBID_SALE.equalsValue(allowsale)) {
        continue;
      }
    }
  }
  
  //begin-ncm-shenjzh-�����¼�δ����
  private void dealOtherP(Set<TranMatRelParaVO> tempSet) {
      Set<String> orgpSet = new HashSet<String>();
      TranMatRelParaVO[] paravos = new TranMatRelParaVO[tempSet.size()];
      tempSet.toArray(paravos);
      for (TranMatRelParaVO paravo : paravos) {
        orgpSet.add(paravo.getPk_fatherorg());
      }
      TranMatRelHVO[] hpvos = this.queryHvos(orgpSet);
      if ((null == hpvos || hpvos.length == 0)) {
        return;
      }
      Map<String, TranMatRelHVO> mpap = new HashMap<String, TranMatRelHVO>();
      for (TranMatRelHVO hvo : hpvos) {
        mpap.put(hvo.getPk_org(), hvo);
      }
      for (TranMatRelParaVO paravo : paravos) {
          String pk_fatherorg = paravo.getPk_fatherorg();
          if (!mpap.containsKey(pk_fatherorg)) {
            continue;
          }
          TranMatRelHVO hvo = mpap.get(pk_fatherorg);
          Integer allowsale = hvo.getAllowsale();
          UFBoolean applylower = hvo.getApplylower();
          if (AllowSale.ALLOW_SALE.equalsValue(allowsale)&&applylower.booleanValue()) {
            this.errMsg.append(NCLangResOnserver.getInstance().getStrByID(
                "4006007_0", "04006007-0020", null, new String[] {
                  paravo.getCrowno()
                })/*�к�Ϊ��{0}�Ķ����в����㵥�����������Ϲ�ϵ���壬���ʧ�ܡ�*/);
          }
          else if (AllowSale.FORBID_SALE.equalsValue(allowsale)) {
            continue;
          }
        }
    }
  //end-ncm-shenjzh-�����¼�δ����

  private TranMatRelHVO[] queryHvos(Set<String> orgSet) {
    String[] orgs = new String[orgSet.size()];
    orgSet.toArray(orgs);
    StringBuffer sql = new StringBuffer();
    sql.append(" and ");
    SqlBuilder sqlbuilder = new SqlBuilder();
    sqlbuilder.append(TranMatRelHVO.PK_ORG, orgs);
    sql.append(sqlbuilder.toString());
    VOQuery<TranMatRelHVO> query =
        new VOQuery<TranMatRelHVO>(TranMatRelHVO.class);
    return query.query(sql.toString(), null);
  }
  
}
