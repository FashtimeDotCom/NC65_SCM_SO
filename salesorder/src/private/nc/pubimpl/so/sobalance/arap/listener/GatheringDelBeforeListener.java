package nc.pubimpl.so.sobalance.arap.listener;

import java.util.HashMap;
import java.util.Map;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.ml.NCLangResOnserver;
import nc.itf.arap.forso.IDataFromF2ForM30;
import nc.pubitf.arap.pub.GetSODataByArapUtils;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;

/**
 * �տ-ɾ��ǰ-�����տ��������
 * 
 * <p>ע�᣺����ƽ̨-�������ù���-ҵ����ע��-Ӧ�չ���-�ͻ��տ-ɾ��ǰ</p>
 * <p>��Ӧ��pub_eventlistener</p>
 * 
 * @since 6.0
 * @version 2011-3-31 ����04:37:49
 * @author ��־ΰ
 */
public class GatheringDelBeforeListener implements IBusinessListener {

  /**
   * �ѽ��������տ������ϵ���տ������ɾ��
   */
  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    // 1.�����տ����
    IDataFromF2ForM30[] datas =
        new GetSODataByArapUtils().getIDataFromF2ForM30(event);

    // 2.����ͬ��Դ���۶���ID��֯����(���˵�Դͷ�������۶�����)
    Map<String, IDataFromF2ForM30> dataMap = this.organizeDataMap(datas);

    // 3.����Դͷ����ID��ѯ�տ�������еĹ�ϵVO
    String[] payBillRowIDs =
        dataMap.keySet().toArray(new String[dataMap.keySet().size()]);
    if (payBillRowIDs == null || payBillRowIDs.length == 0) {
      return;
    }
    SoBalanceViewVO[] views =
        ArListenerUtils.getInstance().querySoBalanceViewByGatheringBillBodyIDs(
            payBillRowIDs);

    // 4. ��֯�տ�������۶�����ϵ��Map<�տbid, ���۶�����>
    Map<String, SoBalanceViewVO> balanceMap = this.organizeBalanceMap(views);

    // 5.��齨�����տ������ϵ�Ĳ�����ɾ��
    this.checkDatas(datas, balanceMap);
  }

  private void checkDatas(IDataFromF2ForM30[] datas,
      Map<String, SoBalanceViewVO> balanceMap) throws BusinessException {
    StringBuffer errMsg = new StringBuffer();
    for (IDataFromF2ForM30 data : datas) {
      if (balanceMap.containsKey(data.getPayBillRowID())) {
        String payBillNo = data.getPayBillNo();
        String billRowID =
            balanceMap.get(data.getPayBillRowID()).getHead().getVbillcode();
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0363", null, new String[] {
              payBillNo, billRowID
            })/*�տ[{0}]�����۶���[{1}]���ѽ��������տ������ϵ,������ɾ����*/);
        errMsg.append("\n");
      }
    }
    if (errMsg.length() > 0) {
      throw new BusinessException(errMsg.toString());
    }
  }

  private Map<String, SoBalanceViewVO> organizeBalanceMap(
      SoBalanceViewVO[] views) {
    Map<String, SoBalanceViewVO> balanceMap =
        new HashMap<String, SoBalanceViewVO>();
    if (views != null && views.length > 0) {
      for (SoBalanceViewVO view : views) {
        SoBalanceBVO body = view.getBody();
        balanceMap.put(body.getCpaybillrowid(), view);
      }
    }
    return balanceMap;
  }

  private Map<String, IDataFromF2ForM30> organizeDataMap(
      IDataFromF2ForM30[] datas) {
    Map<String, IDataFromF2ForM30> retMap =
        new HashMap<String, IDataFromF2ForM30>();
    for (IDataFromF2ForM30 data : datas) {
      retMap.put(data.getPayBillRowID(), data);
    }
    return retMap;
  }
}
