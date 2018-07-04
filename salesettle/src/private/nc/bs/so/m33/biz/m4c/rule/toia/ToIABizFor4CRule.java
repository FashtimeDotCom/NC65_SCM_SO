package nc.bs.so.m33.biz.m4c.rule.toia;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.pub.util.AggVOUtil;

/**
 * @description
 * ���۳�������㵥��������Ʒ����ǰִ��ҵ����򣬿���֯��������㣬��շ��������֯�Ͳֿ�
 * @scene
 * ���۳�������㵥��������Ʒ���������۳��ⵥ���ɱ����㡢���۳��ⵥ��������Ʒ
 * @param 
 * ��
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-5-28 ����10:17:59
 */
public class ToIABizFor4CRule implements IRule<SquareOutVO> {

  @Override
  public void process(SquareOutVO[] vos) {

    // ����֯��������㣬��շ��������֯�Ͳֿ�
    this.clearStockAndStordoc(vos);

  }

  /**
   * ����֯���������:���������֯����������֯�ͽ��������֯��һ��
   * ��շ��������֯�Ͳֿ�
   * 
   * @param vos
   */
  private void clearStockAndStordoc(SquareOutVO[] vos) {
    // �������ݿ����֯ID��ȡ��Ӧ�Ĳ�����֯ID
    String[] stockorgids =
        AggVOUtil.getDistinctHeadFieldArray(vos, SquareOutHVO.CSENDSTOCKORGID,
            String.class);

    Map<String, String> m_st_fin =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(stockorgids);

    SquareOutHVO hvo = null;
    for (SquareOutVO svo : vos) {
      hvo = svo.getParentVO();
      if (!hvo.getPk_org().equals(m_st_fin.get(hvo.getCsendstockorgid()))) {
        hvo.setCsendstockorgid(null);
        hvo.setCsendstockorgvid(null);
        hvo.setCsendstordocid(null);
      }
    }

  }

}
