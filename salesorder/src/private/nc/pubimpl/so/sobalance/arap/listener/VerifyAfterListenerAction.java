package nc.pubimpl.so.sobalance.arap.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.so.m30.sobalance.maintain.IInsertBP;
import nc.impl.so.m30.sobalance.maintain.IUpdateBP;
import nc.impl.so.m30.sobalance.maintain.SobalanceBPFactoryForAcc;
import nc.itf.arap.forso.IDataFromVerifyForM30;
import nc.pubitf.arap.pub.GetSODataByArapUtils;
import nc.pubitf.so.m30.so.balance.IRewrite30ForVerify;
import nc.pubitf.so.m30.so.balance.RewriteVerifyPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.pub.util.SOVOChecker;

public class VerifyAfterListenerAction {

  Map<String, Collection<IDataFromVerifyForM30>> insertDataMap;

  Map<String, Collection<IDataFromVerifyForM30>> updateDataMap;

  /**
   * �����к�����¼�����۶���ID���飬����������ӦSoBalanceVO[]���в�������
   */
  public void doAction(IBusinessEvent event) {
    // 1.�����տ����
    // --��ȷ��IDataFromVerifyForM30�е�������Ӧ�յ����տ�ĺ�����
    // ���������Լ�ҪУ�飬��ҪIDataFromVerifyForM30���ж���������
    IDataFromVerifyForM30[] datas =
        new GetSODataByArapUtils().getIDataFromVerifyForM30(event);
    if (datas.length == 0) {
      return;
    }
    // 2.����ͬ��Դ���۶���ID��֯����(���˵�Դͷ�������۶�����)
    Map<String, Collection<IDataFromVerifyForM30>> dataMap =
        ArListenerUtils.getInstance().organizeDataMap(datas);

    // 3.����Դͷ����ID��ѯ�տ�������еĹ�ϵVO
    String[] csaleorderids =
        dataMap.keySet().toArray(new String[dataMap.keySet().size()]);
    if (csaleorderids == null || csaleorderids.length == 0) {
      return;
    }
    SoBalanceVO[] vos = null;
    try {
      vos = ArListenerUtils.getInstance().querySoBalanceVOByIDs(csaleorderids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    // 4.���ݶ����տ��ϵVO�����ֺ��������Ҫupdate����insert
    this.splitDataMap(vos, dataMap);

    try {
      // 5.ά�������տ������ϵ
      if (this.insertDataMap.size() > 0) {
        this.insertVO();
      }
      if (this.updateDataMap.size() > 0) {
        this.updateVO(vos);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  //begin-ncm-shenjzh-�����д�ģ�����Ѳ�����Դ�����Ĺ��˵��ˣ�����ֲ�������
    datas = this.organizeDataMap(datas);
    // 6.��д���۶����������տ���
    this.rewrite30RowTotalPayMny(datas);
  }
  private IDataFromVerifyForM30[] organizeDataMap(IDataFromVerifyForM30[] datas) {
	    // Map<���۶���id,collection<IDataFromVerifyForM30>>
	  List<IDataFromVerifyForM30> dataList = new ArrayList<IDataFromVerifyForM30>();   dataList = new ArrayList<IDataFromVerifyForM30>();
	  for (IDataFromVerifyForM30 data : datas) {
	      String csaleorderid = data.getFirstID();
	      String firstBillType = data.getFirstBillType();
	      if (firstBillType == null
	          || !SOBillType.Order.getCode().equals(firstBillType)
	          || csaleorderid == null) {
	        continue;
	      }
	      dataList.add(data);
	    }
	  	IDataFromVerifyForM30[] datas2 = new IDataFromVerifyForM30[dataList.size()]; 
	    return dataList.toArray(datas2);
  }
  //end-ncm-shenjzh-�����д�ģ�����Ѳ�����Դ�����Ĺ��˵��ˣ�����ֲ�������
  private SoBalanceVO createInsertSoBalanceVO(IDataFromVerifyForM30[] datas,
      SaleOrderVO saleOrderVO) {
    // ����������ϵ��ͷ
    SoBalanceHVO headVO =
        ArListenerUtils.getInstance().createSoBalanceHVO(saleOrderVO);
    // ����������ϵ����
    SoBalanceBVO[] bodyVOs = this.createSoBalanceBVOs(datas);
    SoBalanceVO retVO = new SoBalanceVO();
    retVO.setParentVO(headVO);
    retVO.setChildrenVO(bodyVOs);
    return retVO;
  }

  private SoBalanceBVO createSoBalanceBVO(IDataFromVerifyForM30 data) {
    SoBalanceBVO retBodyVO = new SoBalanceBVO();
    retBodyVO.setCpaybillid(data.getPayBillID());
    retBodyVO.setCpaybillrowid(data.getPayBillRowID());
    retBodyVO.setVarbillcode(data.getPayBillNo());
    retBodyVO.setDarbilldate(data.getPayBillDate());
    retBodyVO.setDarbalancedate(data.getVerifyDate());
    retBodyVO.setCarorigcurrencyid(data.getPayBillCurID());
    retBodyVO.setCprodlineid(data.getCprodlineID());
    // ����������
    retBodyVO.setNorigaccbalmny(data.getPayBillmny());
    // �����տ���
    retBodyVO.setNorigordbalmny(data.getPayBillmny());
    retBodyVO.setFibaltype(Integer.valueOf(SoBalanceType.SOBALANCE_FINBAL
        .getIntValue()));
    retBodyVO.setDr(Integer.valueOf(0));
    retBodyVO.setStatus(VOStatus.NEW);
    return retBodyVO;
  }

  private SoBalanceBVO[] createSoBalanceBVOs(IDataFromVerifyForM30[] datas) {
    // <PayBillRowID,SoBalanceBVO>
    Map<String, SoBalanceBVO> map = new HashMap<String, SoBalanceBVO>();
    for (int i = 0; i < datas.length; i++) {
      String payBillRowID = datas[i].getPayBillRowID();
      SoBalanceBVO sob = map.get(payBillRowID);
      if (SOVOChecker.isEmpty(sob)) {
        sob = new SoBalanceBVO();
        // ����������
        sob.setNorigaccbalmny(datas[i].getPayBillmny());
        // �����տ���
        sob.setNorigordbalmny(datas[i].getPayBillmny());
        sob.setCpaybillid(datas[i].getPayBillID());
        sob.setCpaybillrowid(payBillRowID);
        sob.setVarbillcode(datas[i].getPayBillNo());
        sob.setDarbilldate(datas[i].getPayBillDate());
        sob.setDarbalancedate(datas[i].getVerifyDate());
        sob.setCarorigcurrencyid(datas[i].getPayBillCurID());
        sob.setCprodlineid(datas[i].getCprodlineID());
        sob.setFibaltype(Integer.valueOf(SoBalanceType.SOBALANCE_FINBAL
            .getIntValue()));
        sob.setDr(Integer.valueOf(0));
        sob.setStatus(VOStatus.NEW);
        map.put(payBillRowID, sob);
      }
      else {
        // ����������
        sob.setNorigaccbalmny(MathTool.add(sob.getNorigaccbalmny(),
            datas[i].getPayBillmny()));
        // �����տ���
        sob.setNorigordbalmny(MathTool.add(sob.getNorigordbalmny(),
            datas[i].getPayBillmny()));
      }
    }
    SoBalanceBVO[] retBodyVOs =
        map.values().toArray(new SoBalanceBVO[map.size()]);
    return retBodyVOs;
  }

  private SoBalanceVO createUpdateSoBalanceVO(IDataFromVerifyForM30[] datas,
      SoBalanceVO oldSoBalanceVO) {
    // ������µ�SoBalanceHVO
    SoBalanceHVO newHeadVO =
        (SoBalanceHVO) oldSoBalanceVO.getParentVO().clone();
    newHeadVO.setStatus(VOStatus.UNCHANGED);
    // ������µ�SoBalanceBVO[]...
    SoBalanceBVO[] oldBodyVOs = oldSoBalanceVO.getChildrenVO();
    Map<String, SoBalanceBVO> oldBodyMap = new HashMap<String, SoBalanceBVO>();
    for (SoBalanceBVO oldBodyVO : oldBodyVOs) {
      oldBodyMap.put(oldBodyVO.getCpaybillrowid(), oldBodyVO);
    }
    // �ж��ٲ��������¼����Ҫ�ж��ٶ���������ϵ��¼
    // Map<payBillRowID,SoBalanceBVO> ������SoBalanceBVO
    Map<String, SoBalanceBVO> allBodyMap = new HashMap<String, SoBalanceBVO>();
    // datas��paybillrowid������ͬ,ֻ����˫��forѭ��
    for (IDataFromVerifyForM30 data : datas) {
      String payBillRowID = data.getPayBillRowID();
      if (oldBodyMap.containsKey(payBillRowID)) {
        SoBalanceBVO oldBody = oldBodyMap.get(payBillRowID);
        oldBody = (SoBalanceBVO) oldBody.clone();
        oldBody.setDarbalancedate(data.getVerifyDate());
        // ����տ���=�����������ֻ������ڣ���ͬ�տ�󣬶����ִγ��⣬����Ӧ�յ����տ�����󣬻�д�Ķ����տ������ϵ���ݴ���
        if (MathTool.equals(oldBody.getNorigordbalmny(),
            oldBody.getNorigaccbalmny())) {
          oldBody.setNorigordbalmny(MathTool.add(data.getPayBillmny(),
              oldBody.getNorigordbalmny()));
        }
        oldBody.setNorigaccbalmny(MathTool.add(data.getPayBillmny(),
            oldBody.getNorigaccbalmny()));
        oldBody.setStatus(VOStatus.UPDATED);
        allBodyMap.put(payBillRowID, oldBody);
        // ����oldBodyMap��oldBody
        oldBodyMap.put(payBillRowID, oldBody);
      }
      // -- û�н�������ϵ�����½�һ���տ������ϵBVO
      else {
        SoBalanceBVO newBody = this.createSoBalanceBVO(data);
        newBody.setCsobalanceid(newHeadVO.getCsobalanceid());
        String cpaybillrowid = newBody.getCpaybillrowid();
        SoBalanceBVO oldBody = allBodyMap.get(cpaybillrowid);
        if (SOVOChecker.isEmpty(oldBody)) {
          allBodyMap.put(newBody.getCpaybillrowid(), newBody);
        }
        else {
          // ����������
          oldBody.setNorigaccbalmny(MathTool.add(newBody.getNorigaccbalmny(),
              oldBody.getNorigaccbalmny()));
          // �����տ���
          oldBody.setNorigordbalmny(MathTool.add(newBody.getNorigordbalmny(),
              oldBody.getNorigordbalmny()));
        }
      }
    }

    // ���δ�ı���
    Set<Map.Entry<String, SoBalanceBVO>> oldBodyEntrySet =
        oldBodyMap.entrySet();
    for (Entry<String, SoBalanceBVO> oldBodyEntry : oldBodyEntrySet) {
      SoBalanceBVO oldBody = oldBodyEntry.getValue();
      if (oldBody.getStatus() == VOStatus.UNCHANGED) {
        allBodyMap.put(oldBody.getCpaybillrowid(), oldBody);
      }
    }

    SoBalanceVO retSobalanceVO = new SoBalanceVO();
    retSobalanceVO.setParentVO(newHeadVO);
    retSobalanceVO.setChildrenVO(allBodyMap.values().toArray(
        new SoBalanceBVO[allBodyMap.size()]));
    return retSobalanceVO;
  }

  private void insertVO() throws BusinessException {
    // --��ѯ���۶���VO
    String[] csaleorderids =
        this.insertDataMap.keySet().toArray(
            new String[this.insertDataMap.keySet().size()]);
    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] saleOrderVOs = query.query(csaleorderids);
    if (this.insertDataMap.size() != saleOrderVOs.length) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0117")/* @res "���۶����ѱ�ɾ������ˢ����������ҵ��" */);
    }

    SoBalanceVO[] newSoBalanceVOs = new SoBalanceVO[this.insertDataMap.size()];
    for (int i = 0; i < saleOrderVOs.length; i++) {
      String csaleorderid = saleOrderVOs[i].getParentVO().getCsaleorderid();
      List<IDataFromVerifyForM30> dataList =
          (List<IDataFromVerifyForM30>) this.insertDataMap.get(csaleorderid);
      IDataFromVerifyForM30[] datas =
          dataList.toArray(new IDataFromVerifyForM30[dataList.size()]);
      // --�������۶���VO���տ�data����SoBalanceVO
      newSoBalanceVOs[i] = this.createInsertSoBalanceVO(datas, saleOrderVOs[i]);
    }
    // --�����������������ϵ
    IInsertBP mainservice =
        SobalanceBPFactoryForAcc.getInstance().getInsertBP();
    mainservice.insert(newSoBalanceVOs);
  }

  private void rewrite30RowTotalPayMny(IDataFromVerifyForM30[] datas) {
    // ���з��飬���л�д
    MapList<String, IDataFromVerifyForM30> dataMapList =
        new MapList<String, IDataFromVerifyForM30>();
    for (IDataFromVerifyForM30 data : datas) {
      dataMapList.put(data.getFirstBID(), data);
    }
    String[] bids =
        dataMapList.keySet().toArray(new String[dataMapList.size()]);
    RewriteVerifyPara[] paras = new RewriteVerifyPara[bids.length];
    for (int i = 0; i < paras.length; i++) {
      List<IDataFromVerifyForM30> dataList = dataMapList.get(bids[i]);
      UFDouble sumPayBillMny = UFDouble.ZERO_DBL;
      for (IDataFromVerifyForM30 data : dataList) {
        sumPayBillMny = MathTool.add(sumPayBillMny, data.getPayBillmny());
      }
      paras[i] = new RewriteVerifyPara(bids[i], sumPayBillMny);
    }
    IRewrite30ForVerify api =
        NCLocator.getInstance().lookup(IRewrite30ForVerify.class);
    try {
      api.rewrite30TotalPayMnyVerifyListener(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private void splitDataMap(SoBalanceVO[] vos,
      Map<String, Collection<IDataFromVerifyForM30>> dataMap) {
    Set<String> csaleorderidSet = new HashSet<String>();
    // --�������տ������ϵ��Data���ݷֳɣ���Ҫ����Map || ��Ҫ����Map
    if (vos != null && vos.length > 0) {
      for (SoBalanceVO vo : vos) {
        csaleorderidSet.add(vo.getParentVO().getCsaleorderid());
      }
    }
    this.insertDataMap =
        new HashMap<String, Collection<IDataFromVerifyForM30>>();
    this.updateDataMap =
        new HashMap<String, Collection<IDataFromVerifyForM30>>();
    for (Map.Entry<String, Collection<IDataFromVerifyForM30>> entry : dataMap
        .entrySet()) {
      // ��Ҫ����
      if (csaleorderidSet.contains(entry.getKey())) {
        this.updateDataMap.put(entry.getKey(), entry.getValue());
      }
      // ��Ҫ����
      else {
        this.insertDataMap.put(entry.getKey(), entry.getValue());
      }
    }
  }

  private void updateVO(SoBalanceVO[] oldSoBalanceVOs) throws BusinessException {
    // --��ѯ���۶���VO
    String[] csaleorderids =
        this.updateDataMap.keySet().toArray(
            new String[this.updateDataMap.keySet().size()]);
    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] saleOrderVOs = query.query(csaleorderids);
    if (this.updateDataMap.size() != saleOrderVOs.length) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0117")/* @res "���۶����ѱ�ɾ������ˢ����������ҵ��" */);
    }

    // ���ɷ��ٴ��տ�
    SaleOrderHVO[] heads = new SaleOrderHVO[saleOrderVOs.length];
    for (int i = 0; i < saleOrderVOs.length; i++) {
      heads[i] = saleOrderVOs[i].getParentVO();
    }

    // ��֯����SoBalanceVOs
    SoBalanceVO[] newSoBalanceVOs = new SoBalanceVO[this.updateDataMap.size()];
    for (int i = 0; i < oldSoBalanceVOs.length; i++) {
      String csaleorderid = oldSoBalanceVOs[i].getParentVO().getCsaleorderid();
      // --�����տ����VO�����۶���ID = data��Ӧ�յ�Դͷ���۶���ID
      List<IDataFromVerifyForM30> dataList =
          (List<IDataFromVerifyForM30>) this.updateDataMap.get(csaleorderid);
      IDataFromVerifyForM30[] datas =
          dataList.toArray(new IDataFromVerifyForM30[dataList.size()]);
      // --���ݶ����տ����VO�ͺ���data����SoBalanceVO
      newSoBalanceVOs[i] =
          this.createUpdateSoBalanceVO(datas, oldSoBalanceVOs[i]);
    }

    // �����޸ı��������ϵ
    IUpdateBP mainservice =
        SobalanceBPFactoryForAcc.getInstance().getUpdateBP();
    mainservice.update(newSoBalanceVOs, oldSoBalanceVOs);
  }

}
