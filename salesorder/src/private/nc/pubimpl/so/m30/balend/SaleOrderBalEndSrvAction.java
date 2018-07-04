package nc.pubimpl.so.m30.balend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.so.m30.state.SaleOrderStateMachine;
import nc.bs.so.m30.state.row.ArSettleCloseState;
import nc.bs.so.m30.state.row.ArSettleOpenState;
import nc.bs.so.m30.state.row.CostSettleCloseState;
import nc.bs.so.m30.state.row.CostSettleOpenState;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.balend.BalEndPara;
import nc.pubitf.so.m30.balend.BalOpenPara;
import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.fi.MaterialFiVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.balend.entity.BalCheckPara;
import nc.vo.so.m30.balend.entity.InvoiceBalVO;
import nc.vo.so.m30.balend.entity.SaleOutBalVO;
import nc.vo.so.m30.balend.enumeration.BalBillType;
import nc.vo.so.m30.balend.util.TriggerJudgeUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.trade.checkrule.VOChecker;

public class SaleOrderBalEndSrvAction {

  // Ӱ��ɱ����㶩������ID
  Set<String> m_costBalEnd;

  // �����㲻Ӱ��ɱ����㶩������ID
  Set<String> m_costNoAffect;

  // Ӱ��Ӧ�ս��㶩������ID
  Set<String> m_incomeBalEnd;

  // �����㲻Ӱ��Ӧ�ս��㶩������ID
  Set<String> m_incomeNoAffect;

  public UFBoolean[] isCostBalEnd(String[] orderbids) {
    BalEndDataAccess dataacc = new BalEndDataAccess();
    Map<String, UFBoolean> map =
        dataacc.queryOrderEndFlag(orderbids, SaleOrderBVO.BBCOSTSETTLEFLAG);
    UFBoolean[] flag = new UFBoolean[orderbids.length];
    for (int i = 0; i < orderbids.length; i++) {
      UFBoolean tflag = map.get(orderbids[i]);
      flag[i] = tflag;
    }
    return flag;
  }

  public UFBoolean[] isIncomeBalEnd(String[] orderbids) {
    BalEndDataAccess dataacc = new BalEndDataAccess();
    Map<String, UFBoolean> map =
        dataacc.queryOrderEndFlag(orderbids, SaleOrderBVO.BBARSETTLEFLAG);
    UFBoolean[] flag = new UFBoolean[orderbids.length];
    for (int i = 0; i < orderbids.length; i++) {
      UFBoolean tflag = map.get(orderbids[i]);
      flag[i] = tflag;
    }
    return flag;

  }

  public void processAutoBalEnd(BalEndPara para) {
    // Ҫ��������۶�����ID
    String[] orderbids = para.getOrderbids();
    // ������Ϊ�գ�����
    if (VOChecker.isEmpty(orderbids)) {
      return;
    }
    // ����
    new VOConcurrentTool().lock(SaleOrderBVO.class, orderbids);

    BalEndDataAccess dataacc = new BalEndDataAccess();
    // ��ѯ�����н���رձ�־λ��Ϣ
    Map<String, UFBoolean[]> mapBalEnd = dataacc.queryBalEndFlag(orderbids);
    // Ӧ�ս���رյĶ����ӱ�ID
    this.m_incomeBalEnd = new HashSet<String>();
    this.m_incomeNoAffect = new HashSet<String>();
    // �ɱ�����رյĶ����ӱ�ID
    this.m_costBalEnd = new HashSet<String>();
    this.m_costNoAffect = new HashSet<String>();

    // �������ж�
    String trigger = para.getTrigger().getCode();
    // �������Ƿ�Ӱ��Ӧ�ս���ر�
    boolean isIncomebal =
        TriggerJudgeUtil.getInstance().isAffectIncome(trigger);
    // �������Ƿ�Ӱ��ɱ�����ر�
    boolean isCostbal = TriggerJudgeUtil.getInstance().isAffectCost(trigger);

    for (String bid : orderbids) {
      UFBoolean[] balend = mapBalEnd.get(bid);

      // Ӱ��Ӧ�ս���ر���δӦ�ս���ر�
      if (isIncomebal && (null == balend[0] || !balend[0].booleanValue())) {
        this.m_incomeBalEnd.add(bid);
      }
      // Ӱ��ɱ�����ر���δ�ɱ�����ر�
      if (isCostbal && (null == balend[1] || !balend[1].booleanValue())) {
        this.m_costBalEnd.add(bid);
      }
    }
    // �����ж����������Ƿ����ر�
    this.checkBalEndEnable(trigger);

    // ����ҪӦ�ջ�ɱ�����رյ���
    if (this.m_incomeBalEnd.size() > 0 || this.m_costBalEnd.size() > 0) {
      this.processAfterBalEnd();
    }
  }

  /**
   * 
   * @param orderbids
   * @return <����bid,[0]�Ƿ���Ʒ��[1]�Ƿ�����ۿ��С����ϼ�ֵ����ģʽ���Ǵ��������>
   */
  private Map<String, UFBoolean[]> querySaleOrderEndInfo(String[] orderbids) {
    SaleOrderViewVO[] views = null;
    try {
      views =
          SOSaleOrderServicesUtil.querySaleOrderViewVOs(orderbids,
              new String[] {
              SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.CSETTLEORGID,
              SaleOrderBVO.CMATERIALVID, SaleOrderBVO.BDISCOUNTFLAG,
              SaleOrderBVO.BLABORFLAG, SaleOrderBVO.BLARGESSFLAG
          });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (views==null||views.length==0) {
      ExceptionUtils.wrappBusinessException("����bids��ѯ���۶���ViewVOsָ��ֵʧ�ܣ�");/*-=notranslate=-*/
    }
    Map<String, Set<String>> mfin_mater = this.getmMaterialvid(views);
    // <������֯oid+����vid,�Ƿ����ϼ�ֵ����ģʽ�Ǵ��������Y|N>
    Map<String, String> result = new HashMap<String, String>();
    if (!VOChecker.isEmpty(mfin_mater)) {
      for (Entry<String, Set<String>> entry : mfin_mater.entrySet()) {
        Set<String> lmvid = entry.getValue();
        String[] mvids = lmvid.toArray(new String[lmvid.size()]);
        String finoid = entry.getKey();
        MaterialFiVO[] mfvos =
            MaterialPubService.queryMaterialFinanceInfoByPks(mvids, finoid,
                new String[] {
                MaterialFiVO.MATERIALVALUEMGT
            });
        for (MaterialFiVO mfvo : mfvos) {
          String flag = "N";
          if (IMaterialEnumConst.MATERIALVALUEMGT_INVCOSTING == mfvo
              .getMaterialvaluemgt().intValue()) {
            flag = "Y";
          }
          result.put(finoid + mfvo.getPk_material(), flag);
        }
      }
    }

    Map<String, UFBoolean[]> map = new HashMap<String, UFBoolean[]>();
    for (SaleOrderViewVO view : views) {
      SaleOrderBVO bvo = view.getBody();
      UFBoolean blargessflag = ValueUtils.getUFBoolean(bvo.getBlargessflag());
      UFBoolean bdiscountflag = ValueUtils.getUFBoolean(bvo.getBdiscountflag());
      UFBoolean blaborflag = ValueUtils.getUFBoolean(bvo.getBlaborflag());
      UFBoolean binvcosting =
          ValueUtils.getUFBoolean(result.get(bvo.getCsettleorgid()
              + bvo.getCmaterialvid()));
      UFBoolean bcostflag =
          ValueUtils.getUFBoolean(bdiscountflag.booleanValue()
              || blaborflag.booleanValue() || !binvcosting.booleanValue());
      UFBoolean[] flag = new UFBoolean[] {
          blargessflag, bcostflag
      };
      map.put(bvo.getCsaleorderbid(), flag);
    }

    return map;
  }

  /**
   * @return <������֯oid,Set ����vid>
   */
  private Map<String, Set<String>> getmMaterialvid(SaleOrderViewVO[] views) {
    Map<String, Set<String>> mfin_mater = new HashMap<String, Set<String>>();
    for (SaleOrderViewVO view : views) {
      String finoid = view.getBody().getCsettleorgid();
      String materialvid = view.getBody().getCmaterialvid();
      Set<String> set = mfin_mater.get(finoid);
      if (VOChecker.isEmpty(set)) {
        set = new HashSet<String>();
        mfin_mater.put(finoid, set);
      }
      set.add(materialvid);
    }
    return mfin_mater;
  }

  public void processAutoBalOpen(BalOpenPara para) {
    // Ҫ��������۶�����ID
    String[] orderbids = para.getOrderbids();
    // ������Ϊ�գ�����
    if (VOChecker.isEmpty(orderbids)) {
      return;
    }
    // ����
    new VOConcurrentTool().lock(SaleOrderBVO.class, orderbids);

    BalEndDataAccess dataacc = new BalEndDataAccess();
    // ��ѯ�����н���رձ�־λ��Ϣ
    Map<String, UFBoolean[]> mapBalEnd = dataacc.queryBalEndFlag(orderbids);
    // ����Ӧ�ս����־�жϵĶ����ӱ�ID
    this.m_incomeBalEnd = new HashSet<String>();
    this.m_incomeNoAffect = new HashSet<String>();
    // ���гɱ������־�жϵĶ����ӱ�ID
    this.m_costBalEnd = new HashSet<String>();
    this.m_costNoAffect = new HashSet<String>();
    // �������ж�
    String trigger = para.getTrigger().getCode();
    boolean isIncomebal =
        TriggerJudgeUtil.getInstance().isAffectIncome(trigger);
    boolean isCostbal = TriggerJudgeUtil.getInstance().isAffectCost(trigger);

    List<String> incomeendids = new ArrayList<String>();
    List<String> costendids = new ArrayList<String>();
    // ��ѯ�������Ƿ���Ʒ�������ۿ��С����ϼ�ֵ����ģʽ���Ǵ��������ı�־����Щ�в����Զ������
    Map<String, UFBoolean[]> map = this.querySaleOrderEndInfo(orderbids);
    for (String bid : orderbids) {
      UFBoolean[] balend = mapBalEnd.get(bid);
      UFBoolean[] flag = map.get(bid);
      UFBoolean barflag = flag[0];
      UFBoolean biaflag = flag[1];
      // Ӱ��Ӧ�ս���ر����ѹر�
      if (isIncomebal && null != balend[0] && balend[0].booleanValue()
          && !barflag.booleanValue()) {
        this.m_incomeBalEnd.add(bid);
        incomeendids.add(bid);
      }
      // Ӱ��ɱ�����ر����ѹر�
      if (isCostbal && null != balend[1] && balend[1].booleanValue()
          && !biaflag.booleanValue()) {
        this.m_costBalEnd.add(bid);
        costendids.add(bid);
      }
    }
    // �����ж����������Ƿ����ر�
    this.checkBalEndEnable(trigger);

    // ����ҪӦ�ջ�ɱ�����رյ���
    if (incomeendids.size() > 0 || costendids.size() > 0) {
      this.processAfterBalOpen(incomeendids, costendids);
    }

  }

  private void checkAllApprove(Map<String, BalCheckPara> mapCheckParas,
      String billtypecode) {
    Set<String> orderids = new HashSet<String>();
    String[] orderbids =
        this.getFilteOrderbids(mapCheckParas, billtypecode, orderids);
    if (orderbids.length > 0) {
      BalEndDataAccess dataacc = new BalEndDataAccess();
      UFBoolean[] isAllApprove =
          dataacc.queryBalBillApprove(billtypecode,
              orderids.toArray(new String[orderids.size()]), orderbids);
      int iloop = orderbids.length;
      for (int i = 0; i < iloop; i++) {
        if (null == isAllApprove[i] || !isAllApprove[i].booleanValue()) {
          this.m_incomeBalEnd.remove(orderbids[i]);
          this.m_costBalEnd.remove(orderbids[i]);
        }
      }
    }
  }

  /**
   * �����������������������������г��ⵥ/��Ʊȫ��ǩ��/������
   * <p>
   * <b>����˵��</b>
   * 
   * @param mapCheckParas
   *          <p>
   * @author fengjb
   * @time 2010-7-20 ����09:41:46
   */
  private void checkBalBillApprove(Map<String, BalCheckPara> mapCheckParas) {
    this.checkAllApprove(mapCheckParas, SOBillType.Invoice.getCode());
    this.checkAllApprove(mapCheckParas, ICBillType.SaleOut.getCode());
  }

  /**
   * ���������������������г��ⵥ/��Ʊ�н���رա�
   * <p>
   * <b>����˵��</b>
   * 
   * @param mapCheckParas
   *          <p>
   * @author fengjb
   * @time 2010-7-20 ����10:21:12
   */
  private void checkBalBillBalClose(Map<String, BalCheckPara> mapCheckParas) {
    this.checkBillBalClose(mapCheckParas, SOBillType.Invoice.getCode());
    this.checkBillBalClose(mapCheckParas, ICBillType.SaleOut.getCode());
  }

  /**
   * ���������������������Ѿ�����/��Ʊ�رա�
   * <p>
   * <b>����˵��</b>
   * 
   * @param mapCheckParas
   *          <p>
   * @author fengjb
   * @time 2010-7-20 ����09:42:28
   */
  private void checkBalBillOrderClose(Map<String, BalCheckPara> mapCheckParas) {
    this.checkOrderClose(mapCheckParas, SOBillType.Invoice.getCode());
    this.checkOrderClose(mapCheckParas, ICBillType.SaleOut.getCode());
  }

  /**
   * 
   * ����������������鴫�����۶�������ID��Ӧ���Ƿ��ܹ��Զ�Ӧ��/�ɱ�����رա�
   * <p>
   * <b>����˵��</b>
   * 
   * @param trigger
   *          <p>
   * @author fengjb
   * @time 2010-9-13 ����02:29:37
   */
  private void checkBalEndEnable(String trigger) {
    // �ϲ�Ӧ�պͳɱ��رղ�ѯ���������ݿ����Ӵ���
    Set<String> hsAllBids = new HashSet<String>();
    hsAllBids.addAll(this.m_incomeBalEnd);
    hsAllBids.addAll(this.m_costBalEnd);
    // û��Ҫ���Ķ���BID������
    if (hsAllBids.size() == 0) {
      return;
    }
    // ��ѯ���������ε��ݽ�����ϸ��Ϣ
    String[] filterbids = hsAllBids.toArray(new String[0]);
    BalEndDataAccess dataacc = new BalEndDataAccess();
    Map<String, BalCheckPara> mapCheckParas = dataacc.querySquare(filterbids);
    // ��������ϸ���ݣ����ĳ����BIDû����ϸ��¼�����ܹر�
    for (String bid : filterbids) {
      if (!mapCheckParas.containsKey(bid)) {
        this.m_incomeBalEnd.remove(bid);
        this.m_costBalEnd.remove(bid);
      }
    }
    if (this.m_incomeBalEnd.size() == 0 && this.m_costBalEnd.size() == 0) {
      return;
    }

    // ���ý�������
    this.setBalType(mapCheckParas, trigger);
    // ������ͽ��㵥�������ж�
    this.checkTrigger(mapCheckParas, trigger);
    // ���г��ⵥ/��Ʊȫ��ǩ��/����
    this.checkBalBillApprove(mapCheckParas);
    // �������Ѿ�����/��Ʊ�ر�
    this.checkBalBillOrderClose(mapCheckParas);
    // ���г��ⵥ/��Ʊ�н���ر�
    this.checkBalBillBalClose(mapCheckParas);
    // ������������
    this.checkOthercon(mapCheckParas);
  }

  private void checkBillBalClose(Map<String, BalCheckPara> mapCheckParas,
      String billtype) {
    Set<String> orderids = new HashSet<String>();
    String[] orderbids =
        this.getFilteOrderbids(mapCheckParas, billtype, orderids);
    if (orderbids.length > 0) {
      BalEndDataAccess dataacc = new BalEndDataAccess();

      Map<String, UFBoolean[]> mapbalclose =
          dataacc.queryBillBalClose(billtype, orderbids);

      for (String orderbid : orderbids) {
        BalCheckPara para = mapCheckParas.get(orderbid);
        UFBoolean[] isbalclose = mapbalclose.get(orderbid);
        if (null == isbalclose) {
          continue;
        }
        // ���ν��㵥�ݲ�Ӱ��Ӧ�ս���||���ν��㵥����δӦ�ս���ر�
        if (!para.isAffectIncomeBal(billtype) || !isbalclose[0].booleanValue()) {
          this.m_incomeBalEnd.remove(orderbid);
        }
        // ���ν��㵥�ݲ�Ӱ��ɱ�����||���ν��㵥���гɱ�����ر�
        if (!para.isAffectCostBal(billtype) || !isbalclose[1].booleanValue()) {
          this.m_costBalEnd.remove(orderbid);
        }
      }
    }
  }

  /**
   * �����������������������Ƿ�Ӱ��ɱ�����رա�
   * <p>
   * <b>����˵��</b>
   * 
   * @param para
   * @param trigger
   *          <p>
   * @author fengjb
   * @time 2010-7-20 ����02:39:26
   */
  private void checkCostTrigger(BalCheckPara para, String trigger) {
    String orderbid = para.getOrderbid();
    if (!this.m_costBalEnd.contains(orderbid)) {
      return;
    }
    BalBillType costbaltype = para.getCostbaltype();
    if (null == costbaltype) {
      return;
    }

    // �ɱ����㵥��ֻ�з�Ʊ��������ֻӰ����ⵥ ���� �ɱ����㵥��ֻ�г��ⵥ��������ֻӰ�췢Ʊ
    if (BalBillType.VOICECOST.equalsValue(costbaltype) && TriggerJudgeUtil
        .getInstance().isOnlyAffectOut(trigger)
        || BalBillType.OUTCOST.equalsValue(costbaltype) && TriggerJudgeUtil
        .getInstance().isOnlyAffectVoice(trigger)
        || BalBillType.OnlyVOICECOST.equalsValue(costbaltype) && TriggerJudgeUtil
        .getInstance().isOnlyAffectOutNoWas(trigger)) {
      this.m_costBalEnd.remove(orderbid);
      this.m_costNoAffect.add(orderbid);
    }

  }

  /**
   * �����������������������Ƿ�Ӱ��Ӧ�ճɱ�����رա�
   * <p>
   * <b>����˵��</b>
   * 
   * @param para
   * @param trigger
   *          <p>
   * @author fengjb
   * @time 2010-7-20 ����02:39:51
   */
  private void checkIncomeTrigger(BalCheckPara para, String trigger) {
    String orderbid = para.getOrderbid();
    if (!this.m_incomeBalEnd.contains(orderbid)) {
      return;
    }
    BalBillType incomebaltype = para.getIncomebaltype();
    // Ӧ�ս��㵥��ֻ�з�Ʊ��������ȴֻӰ�����۳��ⵥ ���� Ӧ�ս��㵥��ֻ�г��ⵥ��������ֻӰ�췢Ʊ
    if (BalBillType.VOICEINCOME.equalsValue(incomebaltype) && TriggerJudgeUtil
        .getInstance().isOnlyAffectOut(trigger)
        || BalBillType.OUTINCOME.equalsValue(incomebaltype) && TriggerJudgeUtil
        .getInstance().isOnlyAffectVoice(trigger)
        || BalBillType.OnlyVOICEINCOME.equalsValue(incomebaltype) && TriggerJudgeUtil
        .getInstance().isOnlyAffectOutNoWas(trigger)) {
      this.m_incomeBalEnd.remove(orderbid);
      this.m_incomeNoAffect.add(orderbid);
    }
  }

  private void checkOrderClose(Map<String, BalCheckPara> mapCheckParas,
      String billtypecode) {
    Set<String> orderids = new HashSet<String>();
    String[] orderbids =
        this.getFilteOrderbids(mapCheckParas, billtypecode, orderids);
    if (orderbids.length > 0) {
      String key = null;
      if (SOBillType.Invoice.isEqual(billtypecode)) {
        key = SaleOrderBVO.BBINVOICENDFLAG;
      }
      else if (ICBillType.SaleOut.isEqual(billtypecode)) {
        key = SaleOrderBVO.BBOUTENDFLAG;
      }
      BalEndDataAccess dataacc = new BalEndDataAccess();
      Map<String, UFBoolean> map = dataacc.queryOrderEndFlag(orderbids, key);
      int iloop = orderbids.length;
      for (int i = 0; i < iloop; i++) {
        UFBoolean isorderclose = map.get(orderbids[i]);
        if (null == isorderclose || !isorderclose.booleanValue()) {
          this.m_incomeBalEnd.remove(orderbids[i]);
          this.m_costBalEnd.remove(orderbids[i]);
        }
      }
    }
  }

  /**
   * ����������������������У��������Ŀǰ��;��ȫ��ǩ�֡���Ȩ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param mapCheckParas
   *          <p>
   * @author fengjb
   * @time 2010-7-20 ����02:40:55
   */
  private void checkOthercon(Map<String, BalCheckPara> mapCheckParas) {
    Set<String> orderids = new HashSet<String>();
    String[] orderbids =
        this.getFilteOrderbids(mapCheckParas, ICBillType.WastageBill.getCode(),
            orderids);
    if (orderbids.length > 0) {
      // ����;���ṩ�ӿ�
      BalEndDataAccess dataacc = new BalEndDataAccess();
      UFBoolean[] allapprove =
          dataacc.queryBalBillApprove(ICBillType.WastageBill.getCode(),
              orderids.toArray(new String[orderids.size()]), orderbids);
      int iloop = orderbids.length;
      for (int i = 0; i < iloop; i++) {
        BalCheckPara para = mapCheckParas.get(orderbids[i]);
        if (!allapprove[i].booleanValue()
            && para.isAffectIncomeBal(ICBillType.WastageBill.getCode())) {
          this.m_incomeBalEnd.remove(orderbids[i]);
        }
        if (!allapprove[i].booleanValue()

            && para.isAffectCostBal(ICBillType.WastageBill.getCode())) {
          this.m_costBalEnd.remove(orderbids[i]);
        }
      }
    }
  }

  /**
   * ��������������������ͽ���رյĹ�ϵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param mapCheckParas
   * @param trigger
   *          <p>
   * @author fengjb
   * @time 2010-7-19 ����03:05:25
   */
  private void checkTrigger(Map<String, BalCheckPara> mapCheckParas,
      String trigger) {

    for (Entry<String, BalCheckPara> entry : mapCheckParas.entrySet()) {
      BalCheckPara para = entry.getValue();
      // ����Ӧ�ս��㵥������
      this.checkIncomeTrigger(para, trigger);
      // ����ɱ����㵥������
      this.checkCostTrigger(para, trigger);
    }

  }

  /**
   * ����������������������Ӱ���������۶���ID���顣
   * <p>
   * <b>����˵��</b>
   * 
   * @param mapCheckParas
   * @param billtype
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 ����02:38:53
   */
  private String[] getFilteOrderbids(Map<String, BalCheckPara> mapCheckParas,
      String billtype, Set<String> orderids) {

    List<String> alorderbids = new ArrayList<String>();
    for (Entry<String, BalCheckPara> entry : mapCheckParas.entrySet()) {
      String orderbid = entry.getKey();
      BalCheckPara para = entry.getValue();
      if ((this.m_incomeBalEnd.contains(orderbid) || this.m_costBalEnd
          .contains(orderbid)) && para.isAffectBal(billtype)) {
        alorderbids.add(orderbid);
        orderids.add(para.getOrderid());
      }
    }
    return alorderbids.toArray(new String[0]);
  }

  private void processAfterBalEnd() {
    // Ҫ�����Զ�����رյ����۶�������VO
    Set<String> hsupdate = new HashSet<String>();
    hsupdate.addAll(this.m_incomeBalEnd);
    hsupdate.addAll(this.m_costBalEnd);

    ViewQuery<SaleOrderViewVO> viewquery =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    SaleOrderViewVO[] viewvos =
        viewquery.query(hsupdate.toArray(new String[0]));

    List<SaleOrderViewVO> arclosviews = new ArrayList<SaleOrderViewVO>();
    List<SaleOrderViewVO> costcloseviews = new ArrayList<SaleOrderViewVO>();

    for (SaleOrderViewVO view : viewvos) {
      String bid = view.getBody().getCsaleorderbid();
      if (this.m_incomeBalEnd.contains(bid)) {
        arclosviews.add(view);
      }
      if (this.m_costBalEnd.contains(bid)) {
        costcloseviews.add(view);
      }
    }
    // ʹ�����۶���״̬��ͳһ������״̬����д����״̬��Ӱ������ͳһ��һ��
    SaleOrderStateMachine statemachine = new SaleOrderStateMachine();
    if (arclosviews.size() > 0) {
      ArSettleCloseState arclosestate = new ArSettleCloseState();
      SaleOrderViewVO[] arclosevos =
          arclosviews.toArray(new SaleOrderViewVO[0]);
      statemachine.setState(arclosestate, arclosevos);
    }

    if (costcloseviews.size() > 0) {
      CostSettleCloseState costclose = new CostSettleCloseState();
      SaleOrderViewVO[] costclosevos =
          costcloseviews.toArray(new SaleOrderViewVO[0]);
      statemachine.setState(costclose, costclosevos);
    }

  }

  /**
   * 
   * ���������������Զ�Ӧ��/�ɱ�����򿪺�����������״̬���¡����ô���ɾ���س�Ӧ�ա�
   * <p>
   * <b>����˵��</b>
   * 
   * @param incomeendids
   * @param costendids
   *          <p>
   * @author fengjb
   * @time 2010-9-13 ����02:31:32
   */
  private void processAfterBalOpen(List<String> incomeendids,
      List<String> costendids) {

    Set<String> setAropenId = new HashSet<String>();
    Set<String> setCostopenId = new HashSet<String>();
    for (String bid : incomeendids) {
      if (!this.m_incomeBalEnd.contains(bid)
          && !this.m_incomeNoAffect.contains(bid)) {
        setAropenId.add(bid);
      }
    }
    for (String bid : costendids) {
      if (!this.m_costBalEnd.contains(bid)
          && !this.m_costNoAffect.contains(bid)) {
        setCostopenId.add(bid);
      }
    }
    Set<String> hsupdate = new HashSet<String>();
    hsupdate.addAll(setAropenId);
    hsupdate.addAll(setCostopenId);
    String[] openids = hsupdate.toArray(new String[0]);

    ViewQuery<SaleOrderViewVO> viewquery =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    SaleOrderViewVO[] viewvos = viewquery.query(openids);

    List<SaleOrderViewVO> aropenviews = new ArrayList<SaleOrderViewVO>();
    List<SaleOrderViewVO> costopenviews = new ArrayList<SaleOrderViewVO>();

    for (SaleOrderViewVO view : viewvos) {
      String bid = view.getBody().getCsaleorderbid();
      if (setAropenId.contains(bid)) {
        aropenviews.add(view);
      }
      if (setCostopenId.contains(bid)) {
        costopenviews.add(view);
      }
    }
    // ʹ�����۶���״̬��ͳһ������״̬����д����״̬��Ӱ������ͳһ��һ��
    SaleOrderStateMachine statemachine = new SaleOrderStateMachine();
    if (aropenviews.size() > 0) {
      ArSettleOpenState arclosestate = new ArSettleOpenState();
      SaleOrderViewVO[] arclosevos =
          aropenviews.toArray(new SaleOrderViewVO[0]);
      statemachine.setState(arclosestate, arclosevos);
    }

    if (costopenviews.size() > 0) {
      CostSettleOpenState costclose = new CostSettleOpenState();
      SaleOrderViewVO[] costclosevos =
          costopenviews.toArray(new SaleOrderViewVO[0]);
      statemachine.setState(costclose, costclosevos);
    }
    // �ݹ�Ӧ�ս���رպ����ɻس�Ӧ�յ�
    /*    IProcess4CAdjustFor30BalEnd adjustsrv =
            NCLocator.getInstance().lookup(IProcess4CAdjustFor30BalEnd.class);
        String[] incomebalendids = this.m_incomeBalEnd.toArray(new String[0]);
        adjustsrv.process4CAdjust(incomebalendids);*/
  }

  /**
   * ������������������Ӧ�պͳɱ����㵥�����͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param mapCheckParas
   *          <p>
   * @author fengjb
   * @time 2010-7-19 ����02:41:34
   */
  private void setBalType(Map<String, BalCheckPara> mapCheckParas,
      String trigger) {

    for (Entry<String, BalCheckPara> entry : mapCheckParas.entrySet()) {
      BalCheckPara para = entry.getValue();
      // ����Ӧ�ս��㵥������
      this.setIncomeBalType(para, trigger);
      // ����ɱ����㵥������
      this.setCostBalType(para, trigger);
    }
  }

  /**
   * ������������������ɱ����㵥�����͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param para
   *          <p>
   * @author fengjb
   * @time 2010-7-19 ����02:34:06
   */
  private void setCostBalType(BalCheckPara para, String trigger) {
    String orderbid = para.getOrderbid();
    // ���û����Ӱ��ɱ�����رյ�set�в��ô���
    if (!this.m_costBalEnd.contains(orderbid)) {
      return;
    }
    SaleOutBalVO outbal = para.getOutbal();
    InvoiceBalVO voicebal = para.getInvoicebal();
    // ���ⵥ�ֹ����뷢����Ʒ
    if (null != outbal && outbal.isManualReg()) {
      if (!TriggerJudgeUtil.getInstance().isVoiceCloseTrigger(trigger)
          && (null == voicebal || !voicebal.isCostbal())) {
        this.m_costBalEnd.remove(orderbid);
      }
      else {
        para.setCostbaltype(BalBillType.OnlyVOICECOST);
      }
    }
    // ���ⵥ�Զ����뷢����Ʒ
    else if (null != outbal && outbal.isAutoReg()) {
      // ���û�з�Ʊ���ݣ���ɱ������޷��ر�
      if (!TriggerJudgeUtil.getInstance().isVoiceCloseTrigger(trigger)
          && (null == voicebal || !voicebal.isCostbal())) {
        this.m_costBalEnd.remove(orderbid);
      }
      else {
        para.setCostbaltype(BalBillType.BOTHCOST);
      }
    }

    // ���ⵥ����ɱ�����
    else if (null != outbal && outbal.isCostbal()) {
      if (null != voicebal && voicebal.isCostbal()) {
        para.setCostbaltype(BalBillType.BOTHCOST);
      }
      else {
        para.setCostbaltype(BalBillType.OUTCOST);
      }
    } // ���ⵥ��������㶯��
    else {
      if (null != voicebal && voicebal.isCostbal()) {
        para.setCostbaltype(BalBillType.VOICECOST);
      }
      else {
        para.setCostbaltype(BalBillType.NONEBAL);
        this.m_costBalEnd.remove(orderbid);
      }
    }
  }

  /**
   * ������������������Ӧ�ս��㵥�����͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param para
   *          <p>
   * @author fengjb
   * @time 2010-7-19 ����02:16:29
   */
  private void setIncomeBalType(BalCheckPara para, String trigger) {
    String orderbid = para.getOrderbid();
    // ���û����Ӱ��Ӧ�ս���رյ�set�в��ô���
    if (!this.m_incomeBalEnd.contains(orderbid)) {
      return;
    }
    SaleOutBalVO outbal = para.getOutbal();
    InvoiceBalVO voicebal = para.getInvoicebal();

    // ���ⵥ�ֹ��ݹ�
    if (null != outbal && outbal.isManualEt()) {
      // ���û�з�Ʊ���ݣ���Ӧ�ս����޷��ر�
      if (!TriggerJudgeUtil.getInstance().isVoiceCloseTrigger(trigger)
          && (null == voicebal || !voicebal.isIncomebal())) {
        this.m_incomeBalEnd.remove(orderbid);
      }
      else {
        para.setIncomebaltype(BalBillType.OnlyVOICEINCOME);
      }
    } // ���ⵥ�Զ��ݹ�
    else if (null != outbal && outbal.isAutoEt()) {
      // ���û�з�Ʊ���ݣ���Ӧ�ս����޷��ر�
      if (!TriggerJudgeUtil.getInstance().isVoiceCloseTrigger(trigger)
          && (null == voicebal || !voicebal.isIncomebal())) {
        this.m_incomeBalEnd.remove(orderbid);
      }
      else {
        para.setIncomebaltype(BalBillType.BOTHINCOME);
      }
    } // ���ⵥ����Ӧ�ս���
    else if (null != outbal && outbal.isIncomebal()) {
      if (null != voicebal && voicebal.isIncomebal()) {
        para.setIncomebaltype(BalBillType.BOTHINCOME);
      }
      else {
        para.setIncomebaltype(BalBillType.OUTINCOME);
      }
    } // ���ⵥ������Ӧ�ս���
    else {
      if (null != voicebal && voicebal.isIncomebal()) {
        para.setIncomebaltype(BalBillType.VOICEINCOME);
      }
      else {
        para.setIncomebaltype(BalBillType.NONEBAL);
        this.m_incomeBalEnd.remove(orderbid);
      }
    }
  }

}
