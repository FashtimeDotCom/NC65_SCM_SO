package nc.impl.so.m38.profit;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.util.ProfitCaculateUtil;

import nc.itf.so.m38.profit.IPreOrderProfitCal;

import nc.impl.pubapp.pattern.data.bill.BillQuery;

/**
 * ���۷�Ʊë��Ԥ����������/�㷨����
 * <ol>
 * <li>���ݴ�������۷�ƱͷID�ļ��ϼ������ж����н���ë��Ԥ��
 * </ol>
 * 
 * @since 6.0
 * @version 2011-7-13 ����16:05:00
 * @author ������
 * @see
 */
public class PreOrderProfitCalImpl implements IPreOrderProfitCal {

  /**
   * ë��������Ҫ������ȡ�ɱ���ͳɱ�����
   * 
   * @param hids Ԥ����ͷID����
   * @return ����õ�ë��VO
   * @throws BusinessException
   * @see
   */
  @Override
  public ProfitVO[] caculate38Profit(String[] hids) throws BusinessException {
    // ��ѯ���۷�ƱVO
    PreOrderVO[] bills = null;
    BillQuery<PreOrderVO> query = new BillQuery<PreOrderVO>(PreOrderVO.class);
    bills = query.query(hids);
    // ��ʼ������ë��VO
    List<ProfitVO> vievowlist = new ArrayList<ProfitVO>();

    // ����ë��VO��������
    for (PreOrderVO vo : bills) {
      PreOrderBVO[] bvos = vo.getChildrenVO();
      for (PreOrderBVO bvo : bvos) {
        ProfitVO profitvo = new ProfitVO();
        profitvo.setCstockorgid(bvo.getCsendstockorgid());
        profitvo.setCstordocid(bvo.getCsendstordocid());
        profitvo.setCmaterialid(bvo.getCmaterialid());
        profitvo.setCmaterialvid(bvo.getCmaterialvid());
        profitvo.setNastnum(bvo.getNnum());
        profitvo.setVbatchcode(bvo.getVbatchcode());
        profitvo.setNqtorignetprice(bvo.getNnetprice());
        profitvo.setCastunitid(bvo.getCunitid());
        profitvo.setCorigcurrencyid(bvo.getCcurrencyid());
        UFDouble totalincome = bvo.getNmny();
        if (bvo.getBlargessflag().booleanValue()) {
          totalincome = UFDouble.ZERO_DBL;
        }
        profitvo.setNtotalincome(totalincome);
        profitvo.setPk_org(bvo.getPk_org());
        profitvo.setCfinanceorg(bvo.getCsettleorgid());
        profitvo.setBlargessflag(bvo.getBlargessflag());
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
