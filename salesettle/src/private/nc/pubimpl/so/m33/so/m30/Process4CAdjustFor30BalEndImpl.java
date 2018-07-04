package nc.pubimpl.so.m33.so.m30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelARRushIncomeFor4CBP;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia.CancelIARegisterCreditFor4COrderEndBP;
import nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CBP;
import nc.bs.so.m33.biz.m4c.bp.square.ia.SquareIARegisterCreditFor4COrderEndBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBizBP;
import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.itf.so.m33.ref.ia.mi5.IAI5For4CServicesUtil;
import nc.pubimpl.so.m33.self.pub.Square434CQueryImpl;
import nc.pubitf.so.m33.so.m30.IProcess4CAdjustFor30BalEnd;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.util.SquareCalculatorForVO;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

public class Process4CAdjustFor30BalEndImpl implements
    IProcess4CAdjustFor30BalEnd {

  @Override
  public void process4CAdjust(String[] ordBids) {
    // ��ѯ��Ӧ�����۳�����㵥
    SquareOutViewVO[] sovvos =
        new Square4CQryFor30SqEndImpl()
            .queryETViewVOByOrdBIDForOrderEnd(ordBids);
    if (VOChecker.isEmpty(sovvos)) {
      return;
    }

    // �����ݹ�δ�س���ȫ�����۳�������㵥��ͬʱ����Ӧ�س�����
    sovvos = this.filterET(sovvos);

    if (!VOChecker.isEmpty(sovvos)) {
      SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(sovvos);
      // ���س�Ӧ�մ���
      new SquareARRushIncomeFor4CBP().square(sqvos);
    }
  }

  @Override
  public void process4CReg(String[] ordBids) {
    // ��ѯ��Ӧ�����۳�����㵥
    SquareOutViewVO[] sovvos =
        new Square4CQryFor30SqEndImpl()
            .queryREGViewVOByOrdBIDForOrderEnd(ordBids);
    if (VOChecker.isEmpty(sovvos)) {
      return;
    }

    // ���˷�����Ʒ�跽δ��ȫ�����س�����۳�������㵥��ͬʱ����Ӧ��������Ʒ��������
    sovvos = this.filterREG(sovvos);

    if (!VOChecker.isEmpty(sovvos)) {
      SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(sovvos);
      // ������������Ʒ
      new SquareIARegisterCreditFor4COrderEndBP().square(sqvos);
    }

  }

  @Override
  public void unProcess4CAdjust(String[] ordBids) {
    QuerySquare4CVOBP qry = new QuerySquare4CVOBP();
    String[] sqbids = qry.querySquareOutVOPKsBy30BID(ordBids);
    if (sqbids == null || sqbids.length == 0) {
      return;
    }
    SquareOutViewVO[] soviewvos = qry.querySquareOutViewVOByBID(sqbids);
    SquareOutVO[] soutvos =
        SquareOutVOUtils.getInstance().combineBill(soviewvos);
    SquareOutDetailVO[] rushVOs =
        new Square434CQueryImpl().queryRushIncomeSquareOutDetailVOByBID(sqbids);
    if (rushVOs != null && rushVOs.length > 0) {
      // ���ñ���ȡ����������
      SquareOutVO[] nsqvos =
          SquareOutVOUtils.getInstance().setNthisnumForCancelSquare(rushVOs,
              soutvos);
      // �����۳�������㵥����
      BSContext.getInstance().setSession(SquareOutVO.class.getName(), nsqvos);
      // ȡ���س�
      new CancelARRushIncomeFor4CBP().cancelSquare(rushVOs);
      // �ͷŻ���
      BSContext.getInstance().removeSession(SquareOutVO.class.getName());
    }
  }

  @Override
  public void unProcess4CReg(String[] ordBids) {
    QuerySquare4CVOBP qry = new QuerySquare4CVOBP();
    String[] sqbids = qry.querySquareOutVOPKsBy30BID(ordBids);
    // �������޳���
    if (VOChecker.isEmpty(sqbids)) {
      return;
    }
    SquareOutViewVO[] soviewvos = qry.querySquareOutViewVOByBID(sqbids);
    SquareOutVO[] soutvos =
        SquareOutVOUtils.getInstance().combineBill(soviewvos);
    SquareOutDetailVO[] regVOs =
        new Square434CQueryImpl().queryREGCreditSquareOutDetailVOByBID(sqbids);
    if (regVOs != null && regVOs.length > 0) {
      // ���ñ���ȡ����������
      SquareOutVO[] nsqvos =
          SquareOutVOUtils.getInstance().setNthisnumForCancelSquare(regVOs,
              soutvos);
      // �����۳�������㵥����
      BSContext.getInstance().setSession(SquareOutVO.class.getName(), nsqvos);
      // ȡ���س�
      new CancelIARegisterCreditFor4COrderEndBP().cancelSquare(regVOs);
      // �ͷŻ���
      BSContext.getInstance().removeSession(SquareOutVO.class.getName());
    }
  }

  /**
   * �����ݹ�δ�س���ȫ�����۳�������㵥��ͬʱ����Ӧ�س�����
   * 
   * @param sovvos -- �ݹ�δ�س���ȫ�����۳�������㵥
   * @return
   */
  private SquareOutViewVO[] filterET(SquareOutViewVO[] sovvos) {
    String[] bids =
        SoVoTools.getVOsOnlyValues(sovvos, SquareOutBVO.CSALESQUAREBID);
    // �ݹ������۳�����㵥
    SquareOutDetailVO[] dvos =
        new QuerySquare4CVOBizBP().queryETSquareOutDetailVOBySQBID(bids);
    String[] sqdids =
        SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSALESQUAREDID);
    // <did,�س�����>
    Map<String, UFDouble> mdidarrushnum =
        ArapServicesForSOUtil.querySaleOutETNotAllSquare(sqdids);
    if (null == mdidarrushnum || mdidarrushnum.size() == 0) {
      return null;
    }
    // <bid,�س�����>
    Map<String, UFDouble> mbidarrushnum = new HashMap<String, UFDouble>();
    // <bid,did>
    Map<String, String> mbiddid = new HashMap<String, String>();
    for (SquareOutDetailVO dvo : dvos) {
      String did = dvo.getCsalesquaredid();
      mbidarrushnum.put(dvo.getCsalesquarebid(), mdidarrushnum.get(did));
      mbiddid.put(dvo.getCsalesquarebid(), dvo.getCsalesquaredid());
    }

    // ���ûس�����
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO view : sovvos) {
      String bid = view.getItem().getCsalesquarebid();
      UFDouble arrushnum = mbidarrushnum.get(bid);
      // �Ѿ���ȫ�س�
      if (MathTool.isZero(arrushnum)) {
        continue;
      }
      // �洢�ݹ����۳�����㵥id��Ϊ�����س�ʹ��
      view.getItem().setProcesseid(mbiddid.get(bid));
      // Ӧ��Ӧ��Ҫ���������ź��ݹ�����һ��,�����۽���洢�������ݹ������෴,����ȡ��
      view.getItem().setNthisnum(MathTool.oppose(arrushnum));
      list.add(view);
    }

    SquareOutViewVO[] retvos = null;
    if (list.size() > 0) {
      retvos = list.toArray(new SquareOutViewVO[list.size()]);
    }
    return retvos;
  }

  /**
   * ���˷�����Ʒδ��ȫ������������Ʒ�����۳�������㵥��ͬʱ���ô�����������Ʒ����
   * 
   * @param sovvos -- ������Ʒδ��ȫ������������Ʒ�����۳�������㵥
   * @return
   */
  private SquareOutViewVO[] filterREG(SquareOutViewVO[] sovvos) {
    String[] bids =
        SoVoTools.getVOsOnlyValues(sovvos, SquareOutBVO.CSALESQUAREBID);
    // ������Ʒ�����۳�����㵥
    SquareOutDetailVO[] dvos =
        new QuerySquare4CVOBizBP().queryREGDebitSquareOutDetailVOBySQBID(bids);
    String[] sqdids =
        SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSALESQUAREDID);
    String[] outhids =
        SoVoTools.getVOsOnlyValues(sovvos, SquareOutBVO.CSQUAREBILLID);
    // <did,����������Ʒ>
    Map<String, UFDouble> mdidregnum =
        IAI5For4CServicesUtil.querySaleOutRegNotAllSquare(outhids, sqdids);
    if (null == mdidregnum || mdidregnum.size() == 0) {
      return null;
    }
    // <bid,����������Ʒ>
    Map<String, UFDouble> mbidregnum = new HashMap<String, UFDouble>();
    for (SquareOutDetailVO dvo : dvos) {
      String did = dvo.getCsalesquaredid();
      mbidregnum.put(dvo.getCsalesquarebid(), mdidregnum.get(did));
    }

    // ���ô���������Ʒ����
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO view : sovvos) {
      String bid = view.getItem().getCsalesquarebid();
      UFDouble regnum = mbidregnum.get(bid);
      // �Ѿ���ȫ������������Ʒ
      if (MathTool.isZero(regnum)) {
        continue;
      }
      view.getItem().setNthisnum(regnum);
      list.add(view);
    }

    SquareOutViewVO[] retvos = null;
    if (list.size() > 0) {
      retvos = list.toArray(new SquareOutViewVO[list.size()]);
      SquareOutBVO[] bvos =
          SquareOutVOUtils.getInstance().getSquareOutBVO(retvos);
      new SquareCalculatorForVO().calculate(bvos, SquareOutBVO.NTHISNUM);
    }
    return retvos;
  }

}
