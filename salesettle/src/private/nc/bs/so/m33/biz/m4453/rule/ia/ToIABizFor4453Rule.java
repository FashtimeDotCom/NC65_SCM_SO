package nc.bs.so.m33.biz.m4453.rule.ia;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.so.m33.m4453.entity.SquareWasHVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.pub.util.AggVOUtil;

/**
 * @description
 * ���۳��ⵥ���ɱ�����ǰ����������Ʒ�������������ҵ����
 * @scene
 * ���۳��ⵥ���ɱ����㡢��������Ʒǰ���������
 * @param
 * ��
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-5-28 ����10:17:59
 */
public class ToIABizFor4453Rule implements IRule<SquareWasVO> {

  @Override
  public void process(SquareWasVO[] vos) {

    // ����֯��������㣬��շ��������֯�Ͳֿ�
    this.clearStockAndStordoc(vos);

  }

  /**
   * ����֯���������:���������֯����������֯�ͽ��������֯��һ��
   * ��շ��������֯�Ͳֿ�
   * 
   * @param vos
   */
  private void clearStockAndStordoc(SquareWasVO[] vos) {
    // �������ݿ����֯ID��ȡ��Ӧ�Ĳ�����֯ID
    String[] stockorgids =
        AggVOUtil.getDistinctHeadFieldArray(vos, SquareWasHVO.CSENDSTOCKORGID,
            String.class);

    Map<String, String> m_st_fin =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(stockorgids);

    SquareWasHVO hvo = null;
    for (SquareWasVO svo : vos) {
      hvo = svo.getParentVO();
      if (!hvo.getPk_org().equals(m_st_fin.get(hvo.getCsendstockorgid()))) {
        hvo.setCsendstockorgid(null);
        hvo.setCsendstockorgvid(null);
        hvo.setCsendstordocid(null);
      }
    }

  }

}
