package nc.vo.so.m33.pub.exchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.pf.PfBillItfDefUtil;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.scmpub.reference.uap.pf.TransTypeMapping;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.pub.util.SettleVOUtils;
import nc.vo.so.pub.SOItemKey;
import nc.vo.trade.checkrule.VOChecker;

/**
 * ���۽��㴫Ӧ��Ӧ��vo���չ���������
 * 
 * E ���۴����㵥
 * T ���ε���
 * 
 * @since 6.0
 * @version 2011-6-25 ����01:49:28
 * @author zhangcheng
 */
public class ExchangeBillUtils<E extends AbstractBill, T extends AggregatedValueObject> {

  private Class<E> clazz;

  public ExchangeBillUtils(Class<E> clazz) {
    this.clazz = clazz;
  }

  /**
   * ���������νӿڹ�ϵ�������vo����
   * 
   * @param vos
   * @param squareBillType ���۴����㵥�������� (434C,4332,4353)
   * @param srcBillType ���㵥�ݵ������� ��4C,32,4453��
   * @param destBillType Ŀ�ĵ��ݵ�������
   * @return
   */
  public T[] exchangeBill(E[] vos, String squareBillType, String srcBillType,
      String destBillType) {
    Map<String, List<E>> mTransVO =
        new SettleVOUtils().splitVOByHeadItem(vos, SOItemKey.VTRANTYPECODE);
    String pk_group = BSContext.getInstance().getGroupID();
    List<ExchangeSourceVo> listexvo = new ArrayList<ExchangeSourceVo>();
    for (Entry<String, List<E>> entry : mTransVO.entrySet()) {
      String srcBillTransType = entry.getKey();
      ExchangeSourceVo exvo = new ExchangeSourceVo();
      exvo.setSrcBillType(srcBillType);
      exvo.setSrcBillTransType(srcBillTransType);
      exvo.setDestBillType(destBillType);
      exvo.setPk_group(pk_group);
      List<E> list = entry.getValue();
      E[] instances = Constructor.construct(this.clazz, list.size());
      instances = list.toArray(instances);
      exvo.setBills(instances);
      listexvo.add(exvo);
    }
    ExchangeSourceVo[] exvos =
        listexvo.toArray(new ExchangeSourceVo[listexvo.size()]);
    return this.exchange(squareBillType, exvos);
  }

  private T[] exchange(String squareBillType, ExchangeSourceVo[] exvos) {
    List<T> retBills = new ArrayList<T>();
    for (ExchangeSourceVo evo : exvos) {
      // ��ȡ�����νӿڹ�ϵ����
      String srcBillType = evo.getSrcBillType();
      String srcTransType = evo.getSrcBillTransType();
      TransTypeMapping mapping = new TransTypeMapping();
      mapping.setSrcBillType(srcBillType);
      mapping.setSrcTransTypeCode(srcTransType);
      mapping.setDestBillType(evo.getDestBillType());
      TransTypeMapping destBill =
          PfBillItfDefUtil.queryTransTypeMapping(evo.getPk_group(), mapping);

      // vo����
      String destTransType = evo.getDestBillType();
      String destBillType = evo.getDestBillType();
      if (!VOChecker.isEmpty(destBill)) {
        destTransType = destBill.getDestTransTypeCode();
        destBillType = destBill.getDestBillType();
      }
      // �����������Ϊ�գ����õ������ͽ��н���
      if (PubAppTool.isNull(destTransType)) {
        destTransType = destBillType;
      }
      @SuppressWarnings("unchecked")
      T[] tempDestBills =
          (T[]) PfServiceScmUtil.executeVOChange(squareBillType, destTransType,
              evo.getBills());
      retBills.addAll(Arrays.asList(tempDestBills));
    }

    T[] bills = null;
    if (retBills.size() > 0) {
      bills = new ListToArrayTool<T>().convertToArray(retBills);
    }
    return bills;
  }

}
