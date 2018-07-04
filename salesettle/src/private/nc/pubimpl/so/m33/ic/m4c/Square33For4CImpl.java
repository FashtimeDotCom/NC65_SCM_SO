package nc.pubimpl.so.m33.ic.m4c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.util.CombineViewToAggUtil;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;

import nc.pubitf.so.m33.ic.m4c.ISquare33For4C;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m33.biz.m4c.action.ar.ETIncomeFor4CAction;
import nc.bs.so.m33.biz.m4c.action.ia.IARegisterFor4CAction;
import nc.bs.so.m33.biz.m4c.bp.cancelsquare.CancelSquareFor4CPubBP;
import nc.bs.so.m33.biz.m4c.rule.push.Before4CPush33Rule;
import nc.bs.so.m33.maintain.m4c.DeleteSquare4CBP;
import nc.bs.so.m33.maintain.m4c.InsertSquare4CBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.bs.so.m33.plugin.ServicePlugInPoint;

import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

import nc.pubimpl.so.m33.self.pub.Square434CQueryImpl;

/**
 * ���۽���Ϊ���۳��ⵥ�ṩ�ķ�����
 * 
 * @since 6.1
 * @version 2012-11-29 10:17:00
 * @author ��ӱ�
 */
public class Square33For4CImpl implements ISquare33For4C {

  @Override
  public void cancelSquareSrv(SaleOutVO[] saleOutVOs) throws BusinessException {
    // �����γ��ⵥ����
    BillConcurrentTool tool = new BillConcurrentTool();
    tool.lockBill(saleOutVOs);

    // ��ѯ�����㵥����
    SquareOutVO[] vos =
        new QuerySquare4CVOBP().querySquareOutVOBy4CID(SoVoTools
            .getVOPKValues(saleOutVOs));
    if (vos == null || vos.length == 0) {
      return;
    }

    // �Գ�������㵥����
    tool.lockBill(vos);

    // ������ϸ����
    SquareOutDetailVO[] sqdvos =
        new QuerySquare4CVOBP().querySquareOutDetailVOBySQBID(AggVOUtil
            .getDistinctItemFieldArray(vos, SquareOutDetailVO.CSALESQUAREBID,
                String.class));

    try {
      if (!VOChecker.isEmpty(sqdvos)) {
        // ��������ֹ�������ϸ���ݣ�������ȡ���ֹ�����
        this.checkManualDetailVO(sqdvos, vos);

        // ���ڽ��㵥(��ϸ����)
        new CancelSquareFor4CPubBP().cancelSquare(sqdvos, vos);
      }

      // �Զ�����ɾ�����㵥
      new DeleteSquare4CBP().delete(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void pushSquareSrv(SaleOutVO[] saleOutVOs) throws BusinessException {
    try {
      // �������۳��ⵥ����
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(saleOutVOs);

      // ����4C��334C��VO����
      SquareOutVO[] chsqvos =
          PfServiceScmUtil.executeVOChange(ICBillType.SaleOut.getCode(),
              SOBillType.SquareOut.getCode(), saleOutVOs);

      // ��������Ϊ0����
      SquareOutVO[] sqvos = this.fillterZeroNum(chsqvos);
      if (VOChecker.isEmpty(sqvos)) {
        return;
      }

      // ������Դ��Ϣ
      this.initSrcInfo(saleOutVOs, sqvos);

      AroundProcesser<SquareOutVO> processer =
          new AroundProcesser<SquareOutVO>(ServicePlugInPoint.Push33For4C);

      // ����ִ��ǰҵ�����
      this.addBeforePushSquareRule(processer);

      TimeLog.logStart();
      processer.before(sqvos);
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006010_0", "04006010-0017")/*@res "���ý��㵥����BPǰ����"*/);

      TimeLog.logStart();
      SquareOutVO[] vos = new InsertSquare4CBP().insert(sqvos);
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006010_0", "04006010-0018")/*@res "���ý��㵥��������BP"*/);

      TimeLog.logStart();
      processer.after(vos);
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006010_0", "04006010-0019")/*@res "���ý��㵥����BP�����"*/);

      // ����ǩ�տ�Ʊ�˻ص����۳��ⵥ���Զ�����
      this.processAutoSquareForReturn(vos);

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void squareOutRush(SaleOutVO blueOutVO, SaleOutVO[] redOutVOs)
      throws BusinessException {
    SaleOutVO vos = blueOutVO;
    vos.getBodys();
  }

  /**
   * ���ⵥ��ʽ���ɽ��㵥֮ǰҵ����
   * 
   * @param processer
   */
  private void addBeforePushSquareRule(AroundProcesser<SquareOutVO> processer) {
    IRule<SquareOutVO> rule = new Before4CPush33Rule();
    processer.addBeforeRule(rule);
  }

  private void checkManualDetailVO(SquareOutDetailVO[] sqdvos, SquareOutVO[] vos) {
    if (VOChecker.isEmpty(sqdvos)) {
      return;
    }

    // <���۳��ⵥ�����㵥ID���Ƿ�ǩ�տ�Ʊ�˻صĳ��ⵥ>
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    for (SquareOutVO svo : vos) {
      map.put(svo.getParentVO().getCsalesquareid(), svo.getParentVO()
          .getBreturnoutstock());
    }

    for (SquareOutDetailVO sdvo : sqdvos) {
      UFBoolean breturnoutstock = map.get(sdvo.getCsalesquareid());
      if (!ValueUtils.getBoolean(breturnoutstock)
          && !sdvo.getBautosquareflag().booleanValue()) {
        String msg =
            NCLangResOnserver.getInstance().getStrByID("4006010_0",
                "04006010-0101")/*����*/;
        if (SquareType.SQUARETYPE_AR.getIntValue() == sdvo.getFsquaretype()
            .intValue()) {
          msg =
              NCLangResOnserver.getInstance().getStrByID("4006010_0",
                  "04006010-0102")/*Ӧ�ս���*/;
        }
        else if (SquareType.SQUARETYPE_IA.getIntValue() == sdvo
            .getFsquaretype().intValue()) {
          msg =
              NCLangResOnserver.getInstance().getStrByID("4006010_0",
                  "04006010-0103")/*�ɱ�����*/;
        }
        else if (SquareType.SQUARETYPE_ET.getIntValue() == sdvo
            .getFsquaretype().intValue()) {
          msg =
              NCLangResOnserver.getInstance().getStrByID("4006010_0",
                  "04006010-0104")/*�ݹ�Ӧ��*/;
        }
        else if (SquareType.SQUARETYPE_REG_DEBIT.getIntValue() == sdvo
            .getFsquaretype().intValue()) {
          msg =
              NCLangResOnserver.getInstance().getStrByID("4006010_0",
                  "04006010-0105")/*���뷢����Ʒ*/;
        }
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006010_0", "04006010-0106", null, new String[] {
              msg
            })/*���۳��ⵥ�����ֹ�{0}���ݣ�����ȡ����*/);
      }
    }
  }

  /**
   * ��������Ϊ0����
   * 
   * @param sqvos
   * @return
   */
  private SquareOutVO[] fillterZeroNum(SquareOutVO[] sqvos) {
    // ��������Ϊ0����
    SquareOutViewVO[] views =
        SquareOutVOUtils.getInstance().changeToSaleSquareViewVO(sqvos);
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO view : views) {
      if (!MathTool.isZero(view.getItem().getNnum())) {
        list.add(view);
      }
    }
    SquareOutVO[] rets = null;
    int size = list.size();
    if (size > 0) {
      SquareOutViewVO[] retviews = list.toArray(new SquareOutViewVO[size]);
      CombineViewToAggUtil<SquareOutVO> comutil =
          new CombineViewToAggUtil<SquareOutVO>(SquareOutVO.class,
              SquareOutHVO.class, SquareOutBVO.class);
      rets = comutil.combineViewToAgg(retviews, SquareOutHVO.CSQUAREBILLID);
    }
    return rets;
  }

  /**
   * ����ҵ�񳡾��������ó��ⵥ��Դ��Ϣ��������˻صĳ��ⵥ����Դ��Ϣ����Դ�����ų��ⵥ
   * ��ΪIC�˻صĳ��ⵥ��׼��Դ��Ϣ�ֶμǵ������۶���
   * 
   * @param saleOutVOs
   * @param sqvos
   */
  private void initSrcInfo(SaleOutVO[] saleOutVOs, SquareOutVO[] sqvos) {
    // <4Cbid,SaleOutBodyVO>
    Map<String, SaleOutBodyVO> map = new HashMap<String, SaleOutBodyVO>();
    for (SaleOutVO svo : saleOutVOs) {
      for (SaleOutBodyVO bvo : svo.getBodys()) {
        map.put(bvo.getCgeneralbid(), bvo);
      }
    }
    for (SquareOutVO svo : sqvos) {
      SquareOutHVO hvo = svo.getParentVO();
      UFBoolean isReturnflag =
          ValueUtils.getUFBoolean(hvo.getBreturnoutstock());
      if (isReturnflag.booleanValue()) {
        for (SquareOutBVO bvo : svo.getChildrenVO()) {
          SaleOutBodyVO outbvo = map.get(bvo.getCsquarebillbid());
          bvo.setVsrccode(outbvo.getVsignwastcode());

          // ���ⵥֻ�������ε������ͣ��������Ҷ���֮
          bvo.setVsrctype(outbvo.getCsignwasttype());
          bvo.setVsrctrantype(outbvo.getCsignwasttype());
          // ���ⵥֻ�������ε������ͣ��������Ҷ���֮

          bvo.setCsrcid(outbvo.getCsignwasthid());
          bvo.setCsrcbid(outbvo.getCsignwastbid());
          bvo.setVsrcrowno(outbvo.getVsignwastrowno());
        }
      }
    }
  }

  /**
   * ����ǩ�տ�Ʊ�˻ص����۳��ⵥ,������γ��ⵥ(�������ֹ������Զ�)�Ѿ��ݹ�Ӧ��
   * ��ǩ�տ�Ʊ�˻ص����۳��ⵥ�Զ�����Ӧ�Ĵ������ٸ���������Զ����ֹ������־
   * 
   * @param vos
   */
  private void processAutoSquareForReturn(SquareOutVO[] vos) {
    // ���˻���ǩ�տ�Ʊ�˻ص����۳��ⵥ
    List<SquareOutVO> list = new ArrayList<SquareOutVO>();
    for (SquareOutVO svo : vos) {
      UFBoolean breturnoutstock = svo.getParentVO().getBreturnoutstock();
      if (ValueUtils.getBoolean(breturnoutstock)) {
        list.add(svo);
      }
    }

    if (list.size() > 0) {
      SquareOutVO[] svos = list.toArray(new SquareOutVO[list.size()]);
      // ��ѯ���γ��ⵥ�ݹ�Ӧ�ռ�¼
      String[] outBids =
          AggVOUtil.getDistinctItemFieldArray(svos, SquareOutBVO.CSRCBID,
              String.class);
      SquareOutViewVO[] outETREGviews =
          new Square434CQueryImpl().queryETIncomeREGCostBidBy4CBID(outBids);
      if (!VOChecker.isEmpty(outETREGviews)) {
        // <���γ��ⵥbid,������㵥>
        Map<String, SquareOutViewVO> moutbiddvo =
            new HashMap<String, SquareOutViewVO>();
        for (SquareOutViewVO view : outETREGviews) {
          // �������γ��ⵥ�Զ�����
          if (!view.getHead().getBautosquarecost().booleanValue()
              && !view.getHead().getBautosquareincome().booleanValue()) {
            moutbiddvo.put(view.getItem().getCsquarebillbid(), view);
          }
        }
        if (moutbiddvo.size() == 0) {
          return;
        }
        this.processAutoSquareForReturn(svos, moutbiddvo);
      }
    }

  }

  private void processAutoSquareForReturn(SquareOutVO[] svos,
      Map<String, SquareOutViewVO> moutbiddvo) {
    // ��Ҫ���س�Ӧ�յ�
    List<SquareOutViewVO> listRush = new ArrayList<SquareOutViewVO>();
    // ��Ҫ��������Ʒ��
    List<SquareOutViewVO> listREG = new ArrayList<SquareOutViewVO>();
    SquareOutViewVO[] views =
        SquareOutVOUtils.getInstance().changeToSaleSquareViewVO(svos);
    for (SquareOutViewVO view : views) {
      String srcoutbid = view.getItem().getCsrcbid();
      SquareOutViewVO srcoutview = moutbiddvo.get(srcoutbid);
      // ��Ҫ�س�
      int outartype = srcoutview.getItem().getFpreartype().intValue();
      if (SquareType.SQUARETYPE_ET.getIntValue() == outartype
          && !MathTool.isZero(srcoutview.getItem().getNsquareestnum())) {
        listRush.add((SquareOutViewVO) view.clone());
      }
      // ��Ҫ������Ʒ
      int outiatype = srcoutview.getItem().getFpreiatype().intValue();
      if (SquareType.SQUARETYPE_REG_DEBIT.getIntValue() == outiatype
          && !MathTool.isZero(srcoutview.getItem().getNsquareregnum())) {
        listREG.add((SquareOutViewVO) view.clone());
      }
    }

    // �Զ����س�
    if (listRush.size() > 0) {
      SquareOutViewVO[] sviews =
          listRush.toArray(new SquareOutViewVO[listRush.size()]);
      SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(sviews);
      // ����
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(sqvos);
      // ���ݹ�Ӧ�մ���
      new ETIncomeFor4CAction().execIncome(sqvos);
    }

    // �Զ���������Ʒ
    if (listREG.size() > 0) {
      SquareOutViewVO[] sviews =
          listREG.toArray(new SquareOutViewVO[listREG.size()]);
      SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(sviews);
      // ����
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(sqvos);
      // ���ݹ�Ӧ�մ���
      new IARegisterFor4CAction().execCost(sqvos);
    }

  }

  @Override
  public Map<String, String> queryOutSquareDetail(String[] outhids,
      String[] outbids) throws BusinessException {
    String[] keys = new String[] {
      SquareOutDetailVO.CSQUAREBILLBID, SquareOutDetailVO.CSALESQUAREDID
    };
    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(" and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareOutDetailVO.CSQUAREBILLID, outhids);
    wheresql.append(where);

    idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    where = idqb.buildSQL(SquareOutDetailVO.CSQUAREBILLBID, outbids);
    wheresql.append("and " + where);

    VOQuery<SquareOutDetailVO> qrysrv =
        new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class, keys);
    SquareOutDetailVO[] detailvos = qrysrv.query(wheresql.toString(), null);
    if (null == detailvos || detailvos.length == 0) {
      return new HashMap<String, String>();
    }
    Map<String, String> mapdet = new HashMap<String, String>();
    for (SquareOutDetailVO detvo : detailvos) {
      mapdet.put(detvo.getCsalesquaredid(), detvo.getCsquarebillbid());
    }
    return mapdet;
  }

}
