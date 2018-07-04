package nc.bs.so.m33.biz.m32.rule.toia;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * <li>���㴫�ɱ�ǰִ�е�ҵ�����
 * <li>������Ŀ2 �������������ҵ����
 * @scene
 * ���㴫�ɱ�ǰ�������������
 * @param
 * ��
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-5-28 ����10:17:59
 */
public class ToIABizFor32Rule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {

    // ����֯��������㣬��շ��������֯�Ͳֿ�
    this.clearStockAndStordoc(vos);

  }

  /**
   * ����֯���������:���������֯����������֯�ͽ��������֯��һ��
   * ��շ��������֯�Ͳֿ�
   * 
   * @param vos
   */
  private void clearStockAndStordoc(SquareInvVO[] vos) {
    // �������ݿ����֯ID��ȡ��Ӧ�Ĳ�����֯ID
    String[] stockorgids =
        SoVoTools.getVOsOnlyValues(SquareInvVOUtils.getInstance()
            .getSquareInvBVO(vos), SquareInvBVO.CSENDSTOCKORGID);
    Map<String, String> m_st_fin =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(stockorgids);

    for (SquareInvVO svo : vos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {
        if (!bvo.getPk_org().equals(m_st_fin.get(bvo.getCsendstockorgid()))) {
          bvo.setCsendstockorgid(null);
          bvo.setCsendstockorgvid(null);
          bvo.setCsendstordocid(null);
        }
      }
    }

  }

}
