package nc.bs.so.m33.biz.m4453.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARIncomeFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARRushIncomeFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIACostFor4453BP;
import nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIARegisterFor4453BP;
import nc.bs.so.m33.maintain.m4453.InsertSquare4453BP;
import nc.bs.so.m33.plugin.ServicePlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.md.model.impl.MDEnum;
import nc.pubitf.so.m30.balend.BalEndPara;
import nc.pubitf.so.m33.self.pub.ISquare434CQuery;
import nc.vo.ic.m4453.entity.WastageVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.balend.enumeration.BalEndTrigger;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.trade.checkrule.VOChecker;

public class SquareFor4453Action {

  public void soSquare(WastageVO[] wasvos) {
    // 1.��ʽ����;������㵥
    SquareWasVO[] swvos = this.pushSquareWasVO(wasvos);

    // 2.����;�𵥲�ѯ�������۳�����㵥���ѽ�������
    SquareOutDetailVO[] soutdvos = this.getSquareOutDetail(swvos);

    // 3.�����������۳�����㵥��������;������㵥�Ķ���
    SquareWasViewVO[] swvvos =
        SquareWasVOUtils.getInstance().changeToSaleSquareViewVO(swvos);
    Map<SquareType, List<SquareWasViewVO>> msvo =
        this.setSquareFlagFrom334C(soutdvos, swvvos);

    // 4.;������㵥���ݽ����־����
    this.execSquare(msvo);

    // 5.;������Ӱ�충������ر�
    this.processOrderSquareClose(swvos);
  }

  /**
   * ��������������;������㵥���ݽ����־����
   * 
   * @param swvvos
   *          <p>
   * @author zhangcheng
   * @time 2010-8-30 ����10:51:40
   */
  private void execSquare(Map<SquareType, List<SquareWasViewVO>> msvo) {
    if (SysInitGroupQuery.isAREnabled()) {
      // ��ȷ��Ӧ��
      new SquareARIncomeFor4453BP().square(msvo.get(SquareType.SQUARETYPE_AR));

      // ���س�Ӧ��
      new SquareARRushIncomeFor4453BP().square(msvo
          .get(SquareType.SQUARETYPE_ARRUSH));
    }

    if (SysInitGroupQuery.isIAEnabled()) {
      // ���ɱ�
      new SquareIACostFor4453BP().square(msvo.get(SquareType.SQUARETYPE_IA));

      // ��������Ʒ
      new SquareIARegisterFor4453BP().square(msvo
          .get(SquareType.SQUARETYPE_REG_DEBIT));
    }

  }

  /**
   * ����;�𵥲�ѯ�������۳�����㵥���ѽ�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param svos
   *          <p>
   * @author zhangcheng
   * @time 2010-8-30 ����09:05:01
   */
  private SquareOutDetailVO[] getSquareOutDetail(SquareWasVO[] svos) {
    // ���γ��ⵥ��id
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(svos, SquareWasBVO.CSRCBID,
            String.class);

    // ���۳�������ѯ�ӿ�,��ѯ���۳�����㵥
    ISquare434CQuery square4cQry =
        NCLocator.getInstance().lookup(ISquare434CQuery.class);
    SquareOutDetailVO[] sodvos =
        square4cQry.querySquareOutDetailVOBy4CBID(outBids);

    return sodvos;
  }

  /**
   * ;������Ӱ�충������ر�
   * 
   * @param swvos
   */
  private void processOrderSquareClose(SquareWasVO[] swvos) {
    // Դͷ���۶�������id
    String[] orderbids =
        AggVOUtil.getDistinctItemFieldArray(swvos, SquareWasBVO.CFIRSTBID,
            String.class);
    BalEndTrigger trigger = BalEndTrigger.WAST_AUDIT;
    BalEndPara para = new BalEndPara(orderbids, trigger);
    try {
      SOSaleOrderServicesUtil.processAutoBalEnd(para);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ����������������ʽ����;������㵥
   * <p>
   * <b>����˵��</b>
   * 
   * @param wasvos
   *          <p>
   * @author zhangcheng
   * @time 2010-8-30 ����09:05:01
   */
  private SquareWasVO[] pushSquareWasVO(WastageVO[] wasvos) {
    // ����;�𵥼���
    BillConcurrentTool tool = new BillConcurrentTool();
    tool.lockBill(wasvos);

    // ����4453��3353��VO����
    SquareWasVO[] sqwvos =
        PfServiceScmUtil.executeVOChange(ICBillType.WastageBill.getCode(),
            SOBillType.SquareWas.getCode(), wasvos);

    AroundProcesser<SquareWasVO> processer =
        new AroundProcesser<SquareWasVO>(ServicePlugInPoint.Push33For4453);
    processer.before(sqwvos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006010_0", "04006010-0017")/*@res "���ý��㵥����BPǰ����"*/);

    TimeLog.logStart();
    SquareWasVO[] vos = new InsertSquare4453BP().insert(sqwvos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006010_0", "04006010-0018")/*@res "���ý��㵥��������BP"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006010_0", "04006010-0019")/*@res "���ý��㵥����BP�����"*/);

    return vos;
  }

  private void setARData(SquareWasViewVO svvo, SquareOutDetailVO tempVo,
      Map<SquareType, List<SquareWasViewVO>> map) {
    // �������
    Integer artype = svvo.getItem().getFpreartype();
    if (!VOChecker.isEmpty(artype)) {
      // ;�𵥴��س�Ӧ�ձ������γ��ⵥ�����ݹ�Ӧ��
      if (SquareType.SQUARETYPE_ARRUSH.getIntValue() == artype.intValue()) {
        if (VOChecker.isEmpty(tempVo)
            || SquareType.SQUARETYPE_ET.getIntValue() != tempVo
                .getFsquaretype().intValue()) {
          return;
        }
        // ��processeid��ʱ�洢���ⵥ���㵥id(���洫�س�Ӧ��Ӧ����)
        svvo.getItem().setProcesseid(tempVo.getCsalesquaredid());

        // �س�������;��ɻس������ͳ��ⵥ�ݹ���������Сֵ
        UFDouble netnum = tempVo.getNnum();
        SquareWasVOUtils.getInstance().setNthisETRushNumUseMinNum(svvo, netnum);

        // ���ݽ������ͷ���洢
        this.setMapList(artype, map, svvo);
      }
    }
  }

  private void setIAData(SquareWasViewVO iasvvo, SquareOutDetailVO tempVo,
      Map<SquareType, List<SquareWasViewVO>> map) {
    // �ɱ�����
    Integer iatype = iasvvo.getItem().getFpreiatype();
    if (!VOChecker.isEmpty(iatype)) {
      // ;�𵥴����෢����Ʒ�������γ��ⵥ����������Ʒ
      if (SquareType.SQUARETYPE_REG_DEBIT.getIntValue() == iatype.intValue()) {
        if (VOChecker.isEmpty(tempVo)
            || SquareType.SQUARETYPE_REG_DEBIT.getIntValue() != tempVo
                .getFsquaretype().intValue()) {
          return;
        }

        // ������Ʒ������;����������ͳ��ⵥ������Ʒ��������Сֵ
        UFDouble nregnum = tempVo.getNnum();
        SquareWasVOUtils.getInstance().setNthisREGNumUseMinNum(iasvvo, nregnum);

        // ���ݽ������ͷ���洢
        this.setMapList(iatype, map, iasvvo);
      }
    }
  }

  private void setMapList(Integer iKey,
      Map<SquareType, List<SquareWasViewVO>> map, SquareWasViewVO svvo) {
    SquareType key = MDEnum.valueOf(SquareType.class, iKey);
    List<SquareWasViewVO> list = map.get(key);
    if (list == null) {
      list = new ArrayList<SquareWasViewVO>();
      map.put(key, list);
    }
    list.add(svvo);
  }

  /**
   * �������������������������۳�����㵥��������;������㵥�Ķ���
   * 1.���;�𵥴�Ӧ�ջ�ɱ���ֱ�Ӵ������ٿ����γ��ⵥ
   * 2.���;�𵥴��س�ͷ�����Ʒ����Ҫ�����γ��ⵥ�Ƿ񴫹��ݹ����߷�����Ʒ
   * 
   * @param soutdvos
   * @param swvos
   *          <p>
   * @author zhangcheng
   * @time 2010-8-30 ����10:51:37
   */
  private Map<SquareType, List<SquareWasViewVO>> setSquareFlagFrom334C(
      SquareOutDetailVO[] soutdvos, SquareWasViewVO[] swvos) {
    // <���ⵥ��id,List<SquareOutDetailVO>>��ֻ��¼�ݹ��ͷ�����Ʒ�������͵�
    Map<String, List<SquareOutDetailVO>> mOutBidSvo =
        new HashMap<String, List<SquareOutDetailVO>>();
    if (!VOChecker.isEmpty(soutdvos)) {
      for (SquareOutDetailVO sdvo : soutdvos) {
        String outBid = sdvo.getCsquarebillbid();
        int outsqtype = sdvo.getFsquaretype().intValue();
        if (SquareType.SQUARETYPE_ET.getIntValue() == outsqtype
            || SquareType.SQUARETYPE_REG_DEBIT.getIntValue() == outsqtype) {
          List<SquareOutDetailVO> list = mOutBidSvo.get(outBid);
          if (VOChecker.isEmpty(list)) {
            list = new ArrayList<SquareOutDetailVO>();
            mOutBidSvo.put(outBid, list);
          }
          list.add(sdvo);
        }
      }
    }

    // ׼��;������㵥����
    Map<SquareType, List<SquareWasViewVO>> map =
        new HashMap<SquareType, List<SquareWasViewVO>>();
    for (SquareWasViewVO svvo : swvos) {
      List<SquareOutDetailVO> listsdvo =
          mOutBidSvo.get(svvo.getItem().getCsrcbid());
      // ˵�����γ��ⵥ������ɱ�����
      if (SquareType.SQUARETYPE_AR.equalsValue(svvo.getItem().getFpreartype())
          || SquareType.SQUARETYPE_IA.equalsValue(svvo.getItem()
              .getFpreiatype())) {
        SquareWasViewVO iasvvo = (SquareWasViewVO) svvo.clone();
        if (SquareType.SQUARETYPE_AR
            .equalsValue(svvo.getItem().getFpreartype())) {
          // ����Ӧ�ս�������
          this.setMapList(SquareType.SQUARETYPE_AR.getIntegerValue(), map, svvo);
        }
        if (SquareType.SQUARETYPE_IA
            .equalsValue(svvo.getItem().getFpreiatype())) {
          // ���óɱ��������ݣ���Ҫ��¡һ����vo
          this.setMapList(SquareType.SQUARETYPE_IA.getIntegerValue(), map,
              iasvvo);
        }
      }

      if (!VOChecker.isEmpty(listsdvo)) {
        for (SquareOutDetailVO tempVo : listsdvo) {
          SquareWasViewVO iasvvo = (SquareWasViewVO) svvo.clone();
          // ����Ӧ�ս�������
          this.setARData(svvo, tempVo, map);
          // ���óɱ��������ݣ���Ҫ��¡һ����vo
          this.setIAData(iasvvo, tempVo, map);
        }
      }
    } // end for

    return map;
  }
}
