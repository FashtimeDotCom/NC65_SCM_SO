package nc.bs.so.m33.biz.m4c.action.outrush;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.CirVOUtil;
import nc.vo.so.pub.util.ViewVOUtil;
import nc.vo.so.pub.votools.SoVoTools;

import nc.bs.so.m33.biz.m4c.bp.cancelsquare.CancelSquareFor4CPubBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBizBP;

public class CancelOutRushFor4CAction {

  /**
   * ���ֳ��ⵥȡ������Գ壺���ֺͶ�Ӧ�ĺ��ֳ��ⵥȫ��ȡ������Գ�
   * 
   * @param bluevos
   */
  public void cancelBlueOutRush(SquareOutViewVO[] blueview) {
    String[] rushbatchid =
        ViewVOUtil.getDistinctFieldArray(blueview, SquareOutBVO.class,
            SquareOutBVO.CRUSHOUTBATCHID, String.class);

    // ��ѯ���ֶԳ���ⵥ��Ӧ�ĶԳ���ϸ��¼
    String[] blueOutBids =
        ViewVOUtil.getDistinctFieldArray(blueview, SquareOutBVO.class,
            SquareOutBVO.CSQUAREBILLBID, String.class);
    QuerySquare4CVOBizBP bizqry = new QuerySquare4CVOBizBP();
    SquareOutDetailVO[] bluedvos =
        bizqry.queryOutRushSquareOutDetailVOBy4CBID(blueOutBids, rushbatchid);

    // ��ѯ���ֶԳ���ⵥ��Ӧ�ĺ��ֶԳ���ⵥ
    String[] redOutBids =
        SoVoTools.getVOsOnlyValues(bluedvos, SquareOutDetailVO.CRUSHGENERALBID);
    SquareOutViewVO[] redview =
        new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(redOutBids);

    // ��ѯ���ֶԳ���ⵥ�öԳ���ϸ��¼
    SquareOutDetailVO[] reddvos =
        bizqry.queryOutRushSquareOutDetailVOBy4CBID(redOutBids, rushbatchid);

    // �ϲ�VO
    SquareOutVO[] sqvos = this.combineSquareOutViewVO(blueview, redview);
    SquareOutDetailVO[] sqdvos =
        this.combineSquareOutDetailVO(bluedvos, reddvos);

    // ȡ��������ϸ
    new CancelSquareFor4CPubBP().cancelSquare(sqdvos, sqvos);
  }

  /**
   * �����ֳ��ⵥȡ������Գ壺���ֳ��ⵥȫ��ȡ������Գ壬��Ӧ�����ֳ��ⵥȡ����Ӧ�����ĳ���Գ�
   * 
   * @param redvos
   */
  public void cancelRedOutRush(SquareOutViewVO[] redview) {
    QuerySquare4CVOBizBP bizqry = new QuerySquare4CVOBizBP();
    String[] rushbatchid =
        ViewVOUtil.getDistinctFieldArray(redview, SquareOutBVO.class,
            SquareOutBVO.CRUSHOUTBATCHID, String.class);

    // ��ѯ���ֶԳ���ⵥ��Ӧ�ĶԳ���ϸ��¼
    String[] redOutBids =
        ViewVOUtil.getDistinctFieldArray(redview, SquareOutBVO.class,
            SquareOutBVO.CSQUAREBILLBID, String.class);
    SquareOutDetailVO[] reddvos =
        bizqry.queryOutRushSquareOutDetailVOBy4CBID(redOutBids, rushbatchid);

    // ��ѯ���ֶԳ���ⵥ��Ӧ�����ֶԳ���ⵥ
    String[] blueOutBids =
        SoVoTools.getVOsOnlyValues(reddvos, SquareOutDetailVO.CRUSHGENERALBID);
    SquareOutViewVO[] blueview =
        new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(blueOutBids);

    // ��ѯ���ֶԳ���ⵥ��Ӧ���ź��ֳ��ⵥ�ĶԳ���ϸ��¼
    SquareOutDetailVO[] bluedvos =
        bizqry.queryOutRushSquareOutDetailVOBy4CBID(blueOutBids, rushbatchid);
    List<SquareOutDetailVO> bluevosList = new ArrayList<SquareOutDetailVO>();
    Set<String> redbidSet = new HashSet<String>();
    // ��Ҫ���ݺ���id����һ��
    if (!ArrayUtils.isEmpty(redOutBids) && !ArrayUtils.isEmpty(bluedvos)) {
      for (String key : redOutBids) {
        redbidSet.add(key);
      }
      for (SquareOutDetailVO vo : bluedvos) {
        String rushgeneralbid = vo.getCrushgeneralbid();
        if (PubAppTool.isNull(rushgeneralbid)
            || redbidSet.contains(rushgeneralbid)) {
          bluevosList.add(vo);
        }
      }
    }
    bluedvos = bluevosList.toArray(new SquareOutDetailVO[bluevosList.size()]);
    // �ϲ�VO
    SquareOutVO[] sqvos = this.combineSquareOutViewVO(redview, blueview);
    SquareOutDetailVO[] sqdvos =
        this.combineSquareOutDetailVO(reddvos, bluedvos);

    // ȡ��������ϸ
    new CancelSquareFor4CPubBP().cancelSquare(sqdvos, sqvos);

  }

  private SquareOutDetailVO[] combineSquareOutDetailVO(SquareOutDetailVO[] voa,
      SquareOutDetailVO[] vob) {
    List<SquareOutDetailVO> lsdvo = CirVOUtil.combineBill(voa, vob);
    SquareOutDetailVO[] sqdvos = lsdvo.toArray(new SquareOutDetailVO[0]);
    return sqdvos;
  }

  private SquareOutVO[] combineSquareOutViewVO(SquareOutViewVO[] voa,
      SquareOutViewVO[] vob) {
    List<SquareOutViewVO> lview = CirVOUtil.combineBill(voa, vob);
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().combineBill(
            lview.toArray(new SquareOutViewVO[0]));
    return sqvos;
  }

}
