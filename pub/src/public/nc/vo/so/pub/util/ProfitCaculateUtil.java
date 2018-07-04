package nc.vo.so.pub.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.parameter.AskCostPriceParaVO;
import nc.vo.scmpub.parameter.SCMParameterUtils;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.pub.rule.CostRegionPara;
import nc.vo.so.pub.rule.SOCostRegionRule;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ë��VO�������ݼ���ë��
 * <li>������ȡ�ɱ���
 * <li>������ȡ�ɱ�����
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author ������
 * @time 2011-7-12 ����18:54:31
 */

public class ProfitCaculateUtil {

  /*
   * ��Ҫ����ë����ProfitVO
   */
  private List<ProfitVO> m_vievowlist;

  /**
   * �����������������캯����������Ҫ�����ë��VO��
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param lstProfitVO
   * @return
   *         <p>
   * @since 6.0
   * @author ������
   * @time 2011-7-12 ����18:54:31
   */
  public ProfitCaculateUtil(List<ProfitVO> lstProfitVO) {
    this.m_vievowlist = lstProfitVO;
  }

  /**
   * ������������������ë��VO������ȡ�ɱ���ͳɱ����ۣ�������ë��
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param
   * @return
   *         <p>
   * @since 6.0
   * @author ������
   * @time 2011-7-12 ����18:54:31
   */
  public void caculateProfit() {

    // ��ȡ�ɱ�����
    Map<String, UFDouble> costpricemap = this.getCostPrice();

    // ����ë��
    for (ProfitVO profitvo : this.m_vievowlist) {

      UFDouble totalincome = profitvo.getNtotalincome();
      String key =
          profitvo.getPk_org() + "|" + profitvo.getCfinanceorg() + "|"+profitvo.getPk_costregion()+"|"
              + profitvo.getCstockorgid() + "|" + profitvo.getCstordocid()
              + "|" + profitvo.getCmaterialid() + "|"
              + profitvo.getVbatchcode();
      // ��ȡ�ɱ�����
      UFDouble icprice = costpricemap.get(key);
      UFDouble costprice = null == icprice ? new UFDouble(0) : icprice;
      UFDouble totalcost = UFDouble.ZERO_DBL;
      if (null != profitvo.getNastnum()) {
        totalcost = profitvo.getNastnum().multiply(costprice);
      }
      profitvo.setNtotalcost(totalcost);
      // Ԥ������˰�ϼƿ���Ϊnull
      if (SOMathUtil.isZero(totalincome)) {
        totalincome = UFDouble.ZERO_DBL;
      }
      UFDouble totalprofi = totalincome.sub(totalcost);
      profitvo.setNtotalprofit(totalprofi);

      if (SOMathUtil.isZero(totalincome)) {
        if (!SOMathUtil.isZero(totalprofi)) {
          UFDouble totalprofitrate =
              totalprofi.div(totalprofi.abs()).multiply(100.0);
          profitvo.setNtotalprofitrate(totalprofitrate);
        }
      }
      else {
        UFDouble totalprofitrate = totalprofi.div(totalincome).multiply(100.0);
        profitvo.setNtotalprofitrate(totalprofitrate);
      }

    }

  }

  /**
   * ������������������ë��VO������ȡ�ɱ�����
   * 
   * @param Map<Key���ֿ���֯+�ֿ�, Value���ɱ���> costmap
   * @return Map<Key���ɱ���+����+���Σ�Value���ɱ�����>
   * @since 6.0
   * @author ������
   * @throws BusinessException
   * @time 2011-7-12 ����18:54:31
   */
  private Map<String, UFDouble> getCostPrice() {
    // ��ȡ��ʼ�����鳤��
    int intCount = this.m_vievowlist.size();
    // ��ʼ�������֯��֯�Ͳֿ�������顢���Ϻ����β�������
    List<AskCostPriceParaVO> costpriceparas =
        new ArrayList<AskCostPriceParaVO>();
    for (int i = 0; i < intCount; i++) {
      ProfitVO profitvo = this.m_vievowlist.get(i);
      if (profitvo.getPk_org() == null)
        continue;
      AskCostPriceParaVO paravo = new AskCostPriceParaVO();
      paravo.setPk_org(profitvo.getPk_org());
      paravo.setPk_financeorg(profitvo.getCfinanceorg());
      paravo.setCinventoryid(profitvo.getCmaterialid());
      paravo.setPk_stockorg(profitvo.getCstockorgid());
      paravo.setPk_stordoc(profitvo.getCstordocid());
      paravo.setVbatch(profitvo.getVbatchcode());
      paravo.setPk_costregion(profitvo.getPk_costregion());
      costpriceparas.add(paravo);
    }

    SCMParameterUtils scmutils = new SCMParameterUtils();
    Map<String, UFDouble> costpricemap = null;
    try {
      costpricemap =
          scmutils.getPriceBySCM02ForTO(costpriceparas
              .toArray(new AskCostPriceParaVO[costpriceparas.size()]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return costpricemap;

  }

  private Map<String, UFBoolean> getNeedCostPrice() {

    List<String> cmaterialvids = new ArrayList<String>();
    int intCount = this.m_vievowlist.size();
    for (int i = 0; i < intCount; i++) {
      ProfitVO profitvo = this.m_vievowlist.get(i);

      cmaterialvids.add(profitvo.getCmaterialvid());

    }

    MaterialVO[] mvos =
        MaterialPubService.queryMaterialBaseInfoByPks(
            cmaterialvids.toArray(new String[cmaterialvids.size()]),
            new String[] {
              MaterialVO.PK_SOURCE, MaterialVO.DISCOUNTFLAG, MaterialVO.FEE
            });

    Map<String, UFBoolean> isneedcostmap = new HashMap<String, UFBoolean>();
    for (MaterialVO vo : mvos) {
      if (vo.getFee().booleanValue() || vo.getDiscountflag().booleanValue()) {
        isneedcostmap.put(vo.getPk_source(), UFBoolean.FALSE);
      }
      else {
        isneedcostmap.put(vo.getPk_source(), UFBoolean.TRUE);
      }

    }
    return isneedcostmap;
  }
}
