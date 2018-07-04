package nc.bs.so.m33.biz.m32.action.ia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m33.biz.m32.bp.square.toia.SquareIACostFor32BP;
import nc.bs.so.m33.biz.m32.bp.square.toia.SquareIARegisterCreditFor32BP;
import nc.pubitf.so.m33.self.pub.ISquare434CQuery;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.pub.util.AggVOUtil;

public class IACostFor32Action {

  /**
   * �ɱ����㣨������ⵥ����������Ʒ����Ҫ��Ʊ������Ʒ��
   * 
   * @param vos
   */
  public void execCost(SquareInvVO[] vos) {

    // �������۽��㵥��������
    SquareInvVO[][] svos = this.filterSquareInvVO(vos);

    // ���γ��ⵥ����������Ʒ����Ʊ��������Ʒ����
    if (svos[0] != null && svos[0].length > 0) {
      new SquareIARegisterCreditFor32BP().square(svos[0]);
    }

    // ���γ��ⵥû������������Ʒ����Ʊ�ɱ�����
    if (svos[1] != null && svos[1].length > 0) {
      new SquareIACostFor32BP().square(svos[1]);
    }

  }

  /**
   * �������γ��ⵥ���Ƿ������������Ʒ��������VO�ֳɷ�����Ʒ����ͳɱ�����
   * 
   * @param vos
   * @return SquareInvVO[0] -- ������Ʒ
   *         SquareInvVO[1] -- �ɱ�����
   */
  private SquareInvVO[][] filterSquareInvVO(SquareInvVO[] vos) {

    // ���۳�������ѯ�ӿ�
    ISquare434CQuery square4cQry =
        NCLocator.getInstance().lookup(ISquare434CQuery.class);

    // ��ѯ���γ��ⵥ������Ʒ��¼
    String[] regOutBids =
        square4cQry.queryREGCostBidBy4CBID(AggVOUtil.getDistinctItemFieldArray(
            vos, SquareInvBVO.CSRCBID, String.class));

    // [0] -- ������Ʒ [1] -- �ɱ�����
    SquareInvVO[][] svos = new SquareInvVO[2][];
    // ���۷�Ʊȫ���ɱ�����
    if (regOutBids == null || regOutBids.length == 0) {
      svos[0] = null;
      svos[1] = vos;
    }
    else {
      Set<String> set_etOutBids =
          new HashSet<String>(Arrays.asList(regOutBids));

      // �ɱ�����VO
      List<SquareInvViewVO> l_viewvo_co = new ArrayList<SquareInvViewVO>();
      // ������ƷVO
      List<SquareInvViewVO> l_viewvo_re = new ArrayList<SquareInvViewVO>();
      SquareInvViewVO[] sviewvos =
          SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(vos);
      for (SquareInvViewVO svo : sviewvos) {
        // ��������Ʒ����vo
        if (set_etOutBids.contains(svo.getItem().getCsrcbid())) {
          svo.getItem().setFpreiatype(
              SquareType.SQUARETYPE_REG_CREDIT.getIntegerValue());
          l_viewvo_re.add(svo);
        }
        // ���ɱ�����vo
        else {
          svo.getItem().setFpreiatype(
              SquareType.SQUARETYPE_IA.getIntegerValue());
          l_viewvo_co.add(svo);
        }
      }
      svos[0] =
          SquareInvVOUtils.getInstance().combineBill(
              l_viewvo_re.toArray(new SquareInvViewVO[0]));
      svos[1] =
          SquareInvVOUtils.getInstance().combineBill(
              l_viewvo_co.toArray(new SquareInvViewVO[0]));
    }

    return svos;
  }

}
