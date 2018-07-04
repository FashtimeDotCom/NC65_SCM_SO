package nc.pubimpl.so.sobalance.arap.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.ml.NCLangResOnserver;
import nc.itf.arap.forso.IDataFromF2ForM30;
import nc.pubitf.arap.pub.GetSODataByArapUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;

/**
 * �տ-�޸�ǰ-�����տ��������
 * 
 * <p>ע�᣺����ƽ̨-�������ù���-ҵ����ע��-Ӧ�չ���-�ͻ��տ-�޸�ǰ</p>
 * <p>��Ӧ��pub_eventlistener</p>
 * 
 * @since 6.0
 * @version 2011-3-31 ����04:37:49
 * @author ��־ΰ
 */
public class GatheringUpdateBeforeListener implements IBusinessListener {

  /**
   * �ѽ��������տ������ϵ���տ�������޸�
   */
  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
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
    SoBalanceVO[] vos =
        ArListenerUtils.getInstance().querySoBalanceVOByIDs(csaleorderids);

    // 4. ��֯�տ�������۶�����ϵ��Map<�տbid, SoBalanceVO>
    Map<String, SoBalanceVO> balanceMap =
        ArListenerUtils.getInstance().organizeBalanceMap(vos);

    // 5.��齨�����տ������ϵ�Ĳ������޸�
    this.checkDatas(datas, balanceMap);
  }

  private void checkDatas(IDataFromF2ForM30[] datas,
      Map<String, SoBalanceVO> balanceMap) throws BusinessException {
    StringBuffer errMsg = new StringBuffer();
    List<IDataFromF2ForM30> deleteList = new ArrayList<IDataFromF2ForM30>();
    List<IDataFromF2ForM30> updateList = new ArrayList<IDataFromF2ForM30>();
    for (IDataFromF2ForM30 data : datas) {
      if (VOStatus.DELETED == data.getRowStatus()) {
        deleteList.add(data);
      }
      else if (VOStatus.UPDATED == data.getRowStatus()) {
        updateList.add(data);
      }
    }
    if (deleteList.size() > 0) {
      IDataFromF2ForM30[] deleteDatas =
          deleteList.toArray(new IDataFromF2ForM30[deleteList.size()]);
      for (IDataFromF2ForM30 data : deleteDatas) {
        if (balanceMap.containsKey(data.getPayBillRowID())) {
          String payBillNo = data.getPayBillNo();
          String billRowID =
              balanceMap.get(data.getPayBillRowID()).getParentVO()
                  .getVbillcode();
          errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
              "04006011-0363", null, new String[] {
                payBillNo, billRowID
              })/*�տ[{0}]�����۶���[{1}]���ѽ��������տ������ϵ,������ɾ����*/);
          errMsg.append("\n");
        }
      }
    }
    if (updateList.size() > 0) {
      IDataFromF2ForM30[] updateDatas =
          updateList.toArray(new IDataFromF2ForM30[updateList.size()]);
      for (IDataFromF2ForM30 data : updateDatas) {
        if (balanceMap.containsKey(data.getPayBillRowID())) {
          SoBalanceVO sbVO = balanceMap.get(data.getPayBillRowID());
          this.checkGatheringbillAndSoBalanceConsistent(data, sbVO);
        }
      }
    }
    if (errMsg.length() > 0) {
      throw new BusinessException(errMsg.toString());
    }
  }

  private void checkGatheringbillAndSoBalanceConsistent(IDataFromF2ForM30 data,
      SoBalanceVO sbVO) throws BusinessException {
    SoBalanceHVO head = sbVO.getParentVO();
    String ccustomerid = head.getCcustomerid();
    String cinvoicecustid = head.getCinvoicecustid();
    String corigcurrencyid = head.getCorigcurrencyid();
    String carorgid = head.getCarorgid();
    SoBalanceBVO[] bodys = sbVO.getChildrenVO();
    int fibaltype = 1;
    for (SoBalanceBVO body : bodys) {
      if (body.getCpaybillrowid().equals(data.getPayBillRowID())) {
        fibaltype = body.getFibaltype().intValue();
        break;
      }
    }

    if (SoBalanceType.SOBALANCE_ORDERBAL.getIntValue() == fibaltype) {
      // TODO �����ͻ��Ƿ������޸Ĵ��飬�Ȱ������ϵ���,��IDataFromF2ForM30
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

    }
  }

}
