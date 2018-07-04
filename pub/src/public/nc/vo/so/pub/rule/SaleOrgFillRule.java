package nc.vo.so.pub.rule;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.psn.PsndocPubService;
import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.SOKeyRela;
import nc.vo.so.pub.keyvalue.VOKeyValue;

/**
 * @description
 * ������֯����Ա������Ĭ��������
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-10-20 ����8:54:34
 * @author ����
 */
public class SaleOrgFillRule<E extends IBill> {

  private IKeyRela keyRela;

  private E[] vos;

  /**
   * ���췽��
   * @param e
   */
  public SaleOrgFillRule(
      E[] e) {
    this.keyRela = new SOKeyRela();
    this.vos = e;
  }

  /**
   * ���췽��
   * @param e
   * @param keyRela
   */
  public SaleOrgFillRule(
      E[] e, IKeyRela keyRela) {
    this.keyRela = keyRela;
    this.vos = e;
  }

  /**
   * �����֯����Ա������
   */
  public void setOrgEmplyDept() {

    setSaleOrg();

    setEmplyDept();
  }

  /**
   * ����������֯old����vid
   */
  public void setSaleOrg() {
    Set<String> orgset = new HashSet<>();
    for (E vo : vos) {
      IKeyValue keyValue = new VOKeyValue<E>(vo);
      String pk_org = keyValue.getHeadStringValue(keyRela.getPk_orgKey());
      String pk_org_v = keyValue.getHeadStringValue(keyRela.getPk_org_vKey());
      if (pk_org_v == null) {
        orgset.add(pk_org);
      }
    }
    if (orgset.size() > 0) {
      Map<String, String> orgvidmap =
          OrgUnitPubService
              .getOrgVid(orgset.toArray(new String[orgset.size()]));
      for (E vo : vos) {
        IKeyValue keyValue = new VOKeyValue<E>(vo);
        String pk_org_v = keyValue.getHeadStringValue(keyRela.getPk_org_vKey());
        String pk_org = keyValue.getHeadStringValue(keyRela.getPk_orgKey());
        if (pk_org_v == null) {
          keyValue
              .setHeadValue(keyRela.getPk_org_vKey(), orgvidmap.get(pk_org));
        }
      }
    }
  }

  /**
   * ����Ĭ����Ա����
   */
  public void setEmplyDept() {
    Set<String> setemploy = new HashSet<String>();
    for (E vo : vos) {
      IKeyValue keyValue = new VOKeyValue<E>(vo);
      String cemployeeid =
          keyValue.getHeadStringValue(keyRela.getCemployeeidKey());
      if (PubAppTool.isNull(cemployeeid)) {
        cemployeeid =
            UserManageQuery.queryPsndocByUserid(AppContext.getInstance()
                .getPkUser());
        if (cemployeeid != null) {
          keyValue.setHeadValue(keyRela.getCemployeeidKey(), cemployeeid);
          setemploy.add(cemployeeid);
        }
      }
    }
    
    if (setemploy.size() > 0) {
      Map<String, List<String>> deptoldid =
          PsndocPubService.queryDeptIDByPsndocIDs(setemploy
              .toArray(new String[setemploy.size()]));
      for (E vo : vos) {
        IKeyValue keyValue = new VOKeyValue<E>(vo);
        String cemployeeid =
            keyValue.getHeadStringValue(keyRela.getCemployeeidKey());
        String tmpeolddep = deptoldid.get(cemployeeid).get(0);
        keyValue.setHeadValue(keyRela.getCdeptidKey(), tmpeolddep);

      }
    }

    Set<String> depold = new HashSet<String>();
    for (E vo : vos) {
      IKeyValue keyValue = new VOKeyValue<E>(vo);
      String tmpeolddep = keyValue.getHeadStringValue(keyRela.getCdeptidKey());
      depold.add(tmpeolddep);
    }
    Map<String, String> depvid =
        DeptPubService.getLastVIDSByDeptIDS(depold.toArray(new String[depold
            .size()]));
    for (E vo : vos) {
      IKeyValue keyValue = new VOKeyValue<E>(vo);
      String cdeptid = keyValue.getHeadStringValue(keyRela.getCdeptidKey());
      keyValue.setHeadValue(keyRela.getCdeptvidKey(), depvid.get(cdeptid));
    }
  }
}
