package nc.pubimpl.so.tranmatrel.rule;

import java.util.List;

import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.org.SaleOrgPubService;
import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
import nc.vo.bd.accessor.IBDData;
import nc.vo.so.pub.util.ObjectUtil;

/**
 * �������������Ϲ�ϵ����չ������֯��Ϣ
 * 
 * @since 6.0
 * @version 2011-4-14 ����06:38:55
 * @author ף����
 */
public class TranMatRelOrgExtendRule {
  /**
   * ��չ������֯��Ϣ
   * 
   * @param csaleorgid
   * @param matchparas
   * @return
   */
  public List<TranMatRelParaVO> extendSaleOrg(String csaleorgid,
      List<TranMatRelParaVO> listpara) {
    String pk_group = BSContext.getInstance().getGroupID();
    List<IBDData> fathersaleorgs =
        SaleOrgPubService.queryFatherSale(pk_group, csaleorgid, false);

    if (null == fathersaleorgs || fathersaleorgs.size() == 0) {
      return listpara;
    }
    TranMatRelParaVO[] vos = new TranMatRelParaVO[listpara.size()];
    listpara.toArray(vos);
    for (IBDData bddata : fathersaleorgs) {
      String fatherorg = bddata.getPk();
      for (TranMatRelParaVO vo : vos) {
        TranMatRelParaVO fatherpara =
            (TranMatRelParaVO) ObjectUtil.serializableClone(vo);
        vo.setPk_org(null);
        vo.setPk_fatherorg(fatherorg);
        listpara.add(fatherpara);
      }
    }
    return listpara;
  }
}
