package nc.pubimpl.so.sobalance.arap.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
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
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;

import nc.vo.scmpub.res.billtype.SOBillType;
/**
 * ��������-�����տ��������
 * 
 * <p>ע�᣺����ƽ̨-�������ù���-ҵ����ע��-Ӧ�չ���-������ǰ-��������</p>
 * <p>��Ӧ��pub_eventlistener</p>
 * 
 * @since 6.0
 * @version 2011-3-31 ����04:37:49
 * @author ��־ΰ
 */
public class UnVerifyAfterListener implements IBusinessListener {

  /**
   * �����з�������¼�����۶���ID���飬����������ӦSoBalanceVO[]���и���
   */
  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
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
    SoBalanceVO[] vos =
        ArListenerUtils.getInstance().querySoBalanceVOByIDs(csaleorderids);

    // 4.ά�������տ������ϵ
    if (dataMap.size() > 0) {
      this.updateVO(dataMap, vos);
    }
    //begin-ncm-qiwang-�����д�ģ�����Ѳ�����Դ�����Ĺ��˵��ˣ�����ֲ�������
    datas = this.organizeDataMap(datas);
    // 5.��д���۶����������տ���
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
 
//    // 5.��д���۶����������տ���
//    this.rewrite30RowTotalPayMny(datas);
//  }
  //end-ncm-qiwang-�����д�ģ�����Ѳ�����Դ�����Ĺ��˵��ˣ�����ֲ�������   
  
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
    // Map<payBillRowID,SoBalanceBVO> ������SoBalanceBVO
    Map<String, SoBalanceBVO> allBodyMap = new HashMap<String, SoBalanceBVO>();
    // datas��paybillrowid������ͬ,ֻ����˫��forѭ��
    for (IDataFromVerifyForM30 data : datas) {
      String payBillRowID = data.getPayBillRowID();
      if (oldBodyMap.containsKey(payBillRowID)) {
        SoBalanceBVO oldBody = oldBodyMap.get(payBillRowID);
        oldBody = (SoBalanceBVO) oldBody.clone();
        UFDouble oldBalMny = oldBody.getNorigaccbalmny();
        UFDouble chgBalMny = data.getPayBillmny();
        // �Ѻ������ = ��������� && ������ʽΪ���������ɾ��������¼
        if (MathTool.absCompareTo(oldBalMny, chgBalMny) == 0
            && oldBody.getFibaltype().equals(
                SoBalanceType.SOBALANCE_FINBAL.getIntegerValue())) {
          oldBody.setStatus(VOStatus.DELETED);
        }
        // --�Ѻ������ >= ��������� �����³�(�Ѻ������ - ���������)
        else if (MathTool.absCompareTo(oldBalMny, chgBalMny) >= 0) {
          oldBody.setNorigaccbalmny(MathTool.sub(oldBody.getNorigaccbalmny(),
              data.getPayBillmny()));
          oldBody.setStatus(VOStatus.UPDATED);
        }
        allBodyMap.put(payBillRowID, oldBody);
        // ����oldBodyMap��oldBody
        oldBodyMap.put(payBillRowID, oldBody);
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
      String ordbid = bids[i];
      List<IDataFromVerifyForM30> dataList = dataMapList.get(ordbid);
      UFDouble sumPayBillMny = UFDouble.ZERO_DBL;
      for (IDataFromVerifyForM30 data : dataList) {
        sumPayBillMny =
            MathTool.add(sumPayBillMny,
                MathTool.sub(UFDouble.ZERO_DBL, data.getPayBillmny()));
      }
      paras[i] = new RewriteVerifyPara(ordbid, sumPayBillMny);
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

  private void updateVO(Map<String, Collection<IDataFromVerifyForM30>> dataMap,
      SoBalanceVO[] oldSoBalanceVOs) throws BusinessException {
    // --��ѯ���۶���VO
    String[] csaleorderids =
        dataMap.keySet().toArray(new String[dataMap.keySet().size()]);
    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] saleOrderVOs = query.query(csaleorderids);
    if (csaleorderids.length != saleOrderVOs.length) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0117")/*@res "���۶����ѱ�ɾ������ˢ����������ҵ��"*/);
    }

    SoBalanceVO[] newSoBalanceVOs = new SoBalanceVO[oldSoBalanceVOs.length];
    for (int i = 0; i < oldSoBalanceVOs.length; i++) {
      String csaleorderid = oldSoBalanceVOs[i].getParentVO().getCsaleorderid();
      // --�����տ����VO�����۶���ID = data��Ӧ�յ�Դͷ���۶���ID
      List<IDataFromVerifyForM30> dataList =
          (List<IDataFromVerifyForM30>) dataMap.get(csaleorderid);
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
