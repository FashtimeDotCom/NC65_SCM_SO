package nc.pubimpl.so.sobalance.arap.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;

import nc.itf.arap.forso.IDataFromF2ForM30;

import nc.pubitf.arap.pub.GetSODataByArapUtils;

import nc.bs.businessevent.IBusinessEvent;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.so.m30.sobalance.maintain.IInsertBP;
import nc.impl.so.m30.sobalance.maintain.IUpdateBP;
import nc.impl.so.m30.sobalance.maintain.SobalanceBPFactoryForSoAuto;

/**
 * �����տ�ʱ�տ�ı������
 * 
 * @since 6.3
 * @version 2013-03-14 13:43:45
 * @author yixl
 */
public class GatheringAddAfterListenerAction {

  /**
   * ��������Map
   */
  Map<String, Collection<IDataFromF2ForM30>> insertDataMap;

  /**
   * ��������Map
   */
  Map<String, Collection<IDataFromF2ForM30>> updateDataMap;

  /**
   * �������տ��¼�����۶���ID���飬����������ӦSoBalanceVO[]���в�������
   * 
   * @param event
   */
  public void doAction(IBusinessEvent event) {
    // 1.�����տ����
    IDataFromF2ForM30[] datas =
        new GetSODataByArapUtils().getIDataFromF2ForM30(event);

    // 2.����ͬ��Դ���۶���ID��֯����(���˵�Դͷ�������۶�����)
    Map<String, Collection<IDataFromF2ForM30>> dataMap =
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

    // 4.���ݶ����տ��ϵVO�������տ������Ҫupdate����insert
    this.splitDataMap(vos, dataMap);

    // 5.ά�������տ������ϵ
    try {
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
  }

  private void checkGatheringAndSaleOrderConsistent(IDataFromF2ForM30[] datas,
      SaleOrderVO saleOrderVO) throws BusinessException {
    // �����޸ģ������ͻ�����Ʊ�ͻ������֡�Ӧ����֯
    SaleOrderHVO head = saleOrderVO.getParentVO();
    String ccustomerid = head.getCcustomerid();
    String corigcurrencyid = head.getCorigcurrencyid();
    String cinvoicecustid = head.getCinvoicecustid();
    String carorgid = saleOrderVO.getChildrenVO()[0].getCarorgid();
    UFDouble ntotalorigmny = head.getNtotalorigmny();
    // �ۼ�ʵ��Ԥ�տ���
    UFDouble npreceivemny = head.getNpreceivemny();
    boolean bpreceiveflag =
        head.getBpreceiveflag() == null ? false : head.getBpreceiveflag()
            .booleanValue();
    UFDouble npreceivequota = head.getNpreceivequota();
    for (IDataFromF2ForM30 data : datas) {
      // �����ͻ��Ƿ������޸Ĵ��飬�Ȱ������ϵ���,��IDataFromF2ForM30
      if (!ccustomerid.equals(data.getPayBillOrderCust())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0193")/*@res "�������޸Ķ����ͻ���"*/);
      }
      if (!cinvoicecustid.equals(data.getPayBillCustomer())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0194")/*@res "�������޸Ŀ�Ʊ�ͻ���"*/);
      }
      if (!corigcurrencyid.equals(data.getPayBillCurID())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0195")/*@res "�������޸ı��֡�"*/);
      }
      if (!carorgid.equals(data.getPayBillOrg())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0196")/*@res "�������޸�Ӧ����֯��"*/);
      }
      if (MathTool.isDiffSign(ntotalorigmny, data.getPayBillmny())) {
        throw new BusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0412")/*@res "���۶�����˰�ϼ����տ����������Ų�һ�£�"*/);
      }
      // --dongli2 2013.8.23������У�飬�����У��ᵼ���޸��ѱ�����տ�󣨲���ʵ���޸ģ����汨��
      // if (data.isPrePay()) {
      // npreceivemny = MathTool.add(npreceivemny, data.getPayBillmny());
      // }
    }
    // --dongli2 2013.8.23������У�飬�����У��ᵼ���޸��ѱ�����տ�󣨲���ʵ���޸ģ����汨��
    // ʵ��Ԥ�տ���+�����տ��� >�����տ��޶�
    // if (bpreceiveflag
    // && MathTool.absCompareTo(npreceivemny, npreceivequota) > 0) {
    // throw new BusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
    // "4006011_0", "04006011-0423")/*@res
    // "�տ��޶����Ԥ�յ����۶���,ʵ��Ԥ�տ���ܴ��ڶ����տ��޶"*/);
    // }
  }

  /**
   * �������۶���VO�����½�һ�������տ��ϵ��¼
   * 
   * @param datas
   * @param SaleOrderVO
   */

  private SoBalanceVO createInsertSoBalanceVO(IDataFromF2ForM30[] datas,
      SaleOrderVO saleOrderVO) {
    SoBalanceHVO headVO =
        ArListenerUtils.getInstance().createSoBalanceHVO(saleOrderVO);
    // ���ж����տ�ж�Ӧͬһ���۶����𣿿������еĻ���������֯��ʽ����
    UFDouble ntotalorigmny = saleOrderVO.getParentVO().getNtotalorigmny();
    SoBalanceBVO[] bodyVOs =
        this.createSoBalanceBVOs(datas, ntotalorigmny, UFDouble.ZERO_DBL);
    SoBalanceVO retVO = new SoBalanceVO();
    retVO.setParentVO(headVO);
    retVO.setChildrenVO(bodyVOs);
    return retVO;
  }

  private SoBalanceBVO[] createSoBalanceBVOs(IDataFromF2ForM30[] datas,
      UFDouble ntotalorigmny, UFDouble ntotalpaymny) {
    SoBalanceBVO[] retBodyVOs = new SoBalanceBVO[datas.length];
    for (int i = 0; i < datas.length; i++) {
      retBodyVOs[i] = new SoBalanceBVO();
      retBodyVOs[i].setCpaybillid(datas[i].getPayBillID());
      retBodyVOs[i].setCpaybillrowid(datas[i].getPayBillRowID());
      retBodyVOs[i].setVarbillcode(datas[i].getPayBillNo());
      retBodyVOs[i].setDarbilldate(datas[i].getPayBillDate());
      retBodyVOs[i].setDarbalancedate(datas[i].getPayBillDate());
      retBodyVOs[i].setCarorigcurrencyid(datas[i].getPayBillCurID());
      retBodyVOs[i].setCprodlineid(datas[i].getCprodlineID());
      retBodyVOs[i].setNorigarmny(datas[i].getPayBillmny());
      // �������������=min(|��˰�ϼ�-�Ѻ������|,|�տ���|)
      UFDouble canPayMny = MathTool.sub(ntotalorigmny, ntotalpaymny);
      if (MathTool.absCompareTo(canPayMny, datas[i].getPayBillmny()) > 0) {
        retBodyVOs[i].setNorigordbalmny(datas[i].getPayBillmny());
      }
      else {
        retBodyVOs[i].setNorigordbalmny(canPayMny);
      }
      retBodyVOs[i].setFibaltype(Integer
          .valueOf(SoBalanceType.SOBALANCE_ORDERBAL.getIntValue()));
      retBodyVOs[i].setBpreceiveflag(UFBoolean.valueOf(datas[i].isPrePay()));
      retBodyVOs[i].setDr(Integer.valueOf(0));
      retBodyVOs[i].setStatus(VOStatus.NEW);
    }
    return retBodyVOs;
  }

  /**
   * ���ݶ����տ����VO�����齨һ�������տ��ϵ��¼����������
   * <ol>
   * <li>û�б仯���տ��</li>
   * <li>�޸������տ</li>
   * <li>�½�����ϵ�Ķ����տ���</li>
   * </ol>
   * 
   * @param datas
   * @param SoBalanceVO
   */
  private SoBalanceVO createUpdateSoBalanceVO(IDataFromF2ForM30[] datas,
      SoBalanceVO oldSoBalanceVO) {
    // ������µĹ�ϵheadVO
    SoBalanceHVO newHeadVO =
        (SoBalanceHVO) oldSoBalanceVO.getParentVO().clone();
    newHeadVO.setStatus(VOStatus.UNCHANGED);
    // �ϲ���ϵ�ӱ�
    UFDouble ntotalorigtaxmny = newHeadVO.getNtotalorigtaxmny();
    UFDouble ntotalpaymny = newHeadVO.getNtotalpaymny();
    SoBalanceBVO[] oldBodyVOs = oldSoBalanceVO.getChildrenVO();

    Map<String, SoBalanceBVO> oldBodyMap = new HashMap<String, SoBalanceBVO>();
    for (SoBalanceBVO oldBodyVO : oldBodyVOs) {
      oldBodyMap.put(oldBodyVO.getCpaybillrowid(), oldBodyVO);
    }
    Map<String, IDataFromF2ForM30> updateMap =
        new HashMap<String, IDataFromF2ForM30>();
    List<IDataFromF2ForM30> newList = new ArrayList<IDataFromF2ForM30>();
    for (IDataFromF2ForM30 data : datas) {
      // ��Ҫ�޸Ķ���������
      String paybillrowid = data.getPayBillRowID();
      if (oldBodyMap.containsKey(paybillrowid)) {
        updateMap.put(data.getPayBillRowID(), data);
      }
      else {
        // ��Ҫ�½�����������
        newList.add(data);
      }
    }

    List<SoBalanceBVO> bodyVOList = new ArrayList<SoBalanceBVO>();
    // �����µĶ���������
    SoBalanceBVO[] newBodyVOs =
        this.createSoBalanceBVOs(
            newList.toArray(new IDataFromF2ForM30[newList.size()]),
            ntotalorigtaxmny, ntotalpaymny);
    if (newBodyVOs.length > 0) {
      for (SoBalanceBVO bodyvo : newBodyVOs) {
        bodyvo.setDr(Integer.valueOf(0));
        bodyvo.setStatus(VOStatus.NEW);
        bodyvo.setCsobalanceid(newHeadVO.getPrimaryKey());
        bodyVOList.add(bodyvo);
      }
    }
    // ������º�Ķ��������к�δ�ı���տ��
    for (SoBalanceBVO oldBody : oldBodyVOs) {
      String paybillrowid = oldBody.getCpaybillrowid();
      if (updateMap.containsKey(paybillrowid)) {
        SoBalanceBVO newbodyvo = (SoBalanceBVO) oldBody.clone();
        newbodyvo
            .setNorigordbalmny(updateMap.get(paybillrowid).getPayBillmny());
        newbodyvo.setStatus(VOStatus.UPDATED);
        bodyVOList.add(newbodyvo);
      }
      else {
        SoBalanceBVO newbodyvo = (SoBalanceBVO) oldBody.clone();
        newbodyvo.setStatus(VOStatus.UNCHANGED);
        bodyVOList.add(newbodyvo);
      }
    }

    SoBalanceVO retSobalanceVO = new SoBalanceVO();
    retSobalanceVO.setParentVO(newHeadVO);
    retSobalanceVO.setChildrenVO(bodyVOList.toArray(new SoBalanceBVO[bodyVOList
        .size()]));
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
          .getStrByID("4006011_0", "04006011-0117")/*@res "���۶����ѱ�ɾ������ˢ����������ҵ��"*/);
    }

    SoBalanceVO[] newSoBalanceVOs = new SoBalanceVO[this.insertDataMap.size()];
    for (int i = 0; i < saleOrderVOs.length; i++) {
      String csaleorderid = saleOrderVOs[i].getParentVO().getCsaleorderid();
      List<IDataFromF2ForM30> dataList =
          (List<IDataFromF2ForM30>) this.insertDataMap.get(csaleorderid);
      IDataFromF2ForM30[] datas =
          dataList.toArray(new IDataFromF2ForM30[dataList.size()]);
      // --��鶩�����տҪ���ֶ�һ����
      this.checkGatheringAndSaleOrderConsistent(datas, saleOrderVOs[i]);
      // --�������۶���VO���տ�data����SoBalanceVO
      newSoBalanceVOs[i] = this.createInsertSoBalanceVO(datas, saleOrderVOs[i]);
    }
    // --�����������������ϵ
    IInsertBP service = SobalanceBPFactoryForSoAuto.getInstance().getInsertBP();
    service.insert(newSoBalanceVOs);
  }

  /**
   * ������Map����ɣ���Ҫ����Map || ��Ҫ����Map
   * <p>
   * �����龰�� ����100����һ���տ�50���ڶ����տ�50����һ�Ŷ����տ�������б����Ӧ��ͬ�տ�š����ڶ����Ǹ�������һ��
   * </p>
   * 
   * Map<���۶���id,collection<IDataFromF2ForM30>>
   */
  private void splitDataMap(SoBalanceVO[] vos,
      Map<String, Collection<IDataFromF2ForM30>> dataMap) {
    Set<String> csaleorderidSet = new HashSet<String>();
    // --�������տ������ϵ��Data���ݷֳɣ���Ҫ����Map || ��Ҫ����Map
    if (vos != null && vos.length > 0) {
      for (SoBalanceVO vo : vos) {
        csaleorderidSet.add(vo.getParentVO().getCsaleorderid());
      }
    }
    this.insertDataMap = new HashMap<String, Collection<IDataFromF2ForM30>>();
    this.updateDataMap = new HashMap<String, Collection<IDataFromF2ForM30>>();
    for (Map.Entry<String, Collection<IDataFromF2ForM30>> entry : dataMap
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
          .getStrByID("4006011_0", "04006011-0117")/*@res "���۶����ѱ�ɾ������ˢ����������ҵ��"*/);
    }

    SoBalanceVO[] newSoBalanceVOs = new SoBalanceVO[this.updateDataMap.size()];
    for (int i = 0; i < oldSoBalanceVOs.length; i++) {
      String csaleorderid = oldSoBalanceVOs[i].getParentVO().getCsaleorderid();
      List<IDataFromF2ForM30> dataList =
          (List<IDataFromF2ForM30>) this.updateDataMap.get(csaleorderid);
      IDataFromF2ForM30[] datas =
          dataList.toArray(new IDataFromF2ForM30[dataList.size()]);
      // --��鶩�����տҪ���ֶ�һ����
      this.checkGatheringAndSaleOrderConsistent(datas, saleOrderVOs[i]);
      // --���ݶ����տ����VO���տ�data����SoBalanceVO
      newSoBalanceVOs[i] =
          this.createUpdateSoBalanceVO(datas, oldSoBalanceVOs[i]);
      newSoBalanceVOs[i].setListenerflag(UFBoolean.TRUE);
    }
    // �����޸ı��������ϵ
    IUpdateBP mainservice =
        SobalanceBPFactoryForSoAuto.getInstance().getUpdateBP();
    mainservice.update(newSoBalanceVOs, oldSoBalanceVOs);
  }

}
