package nc.vo.so.salequotation.util;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.pub.util.ProfitCaculateUtil;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequotationVOUtil {

  /**
   * ë��������Ҫ������ȡ�ɱ���ͳɱ�����
   * 
   * @param vos ���۶���VO����
   * @return ����õ�ë��VO
   * @throws BusinessException
   * @see
   */
  public ProfitVO[] changeToProfitVO(AggSalequotationHVO[] vos) {
    List<ProfitVO> vievowlist = new ArrayList<ProfitVO>();

    for (AggSalequotationHVO vo : vos) {
      SalequotationHVO hvo = vo.getParentVO();
      SalequotationBVO[] bvos = vo.getChildrenVO();
      for (SalequotationBVO bvo : bvos) {
        if (bvo.getBlargessflag().booleanValue()) {
          continue;
        }
        ProfitVO profitvo = new ProfitVO();
        // profitvo.setCstockorgid(bvo.getCsendstockorgid());
        // profitvo.setCstordocid(bvo.getCsendstordocid());
        profitvo.setCmaterialid(bvo.getPk_material());
        profitvo.setNastnum(bvo.getNnum());
        // profitvo.setVbatchcode(bvo.getVbatchcode());
        profitvo.setNqtorignetprice(bvo.getNqtorignetprice());
        profitvo.setCastunitid(bvo.getPk_unit());
        profitvo.setCorigcurrencyid(hvo.getPk_currtype());
        UFDouble totalincome = bvo.getNorigmny();
        profitvo.setNtotalincome(totalincome);
        profitvo.setPk_org(bvo.getPk_org());
        // profitvo.setCfinanceorg(bvo.getCsettleorgid());
        vievowlist.add(profitvo);
      }
    }
    // ��������ȡ�ɱ���ͳɱ����۽ӿڼ���ë��
    ProfitCaculateUtil cacProfigUtil = new ProfitCaculateUtil(vievowlist);
    cacProfigUtil.caculateProfit();

    ProfitVO[] profitvos = vievowlist.toArray(new ProfitVO[vievowlist.size()]);
    return profitvos;

  }

}
