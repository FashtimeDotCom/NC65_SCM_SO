package nc.vo.so.m30.sobalance.util;

import nc.vo.pub.lang.UFDouble;

/**
 * �տȡֵkeyValue
 * 
 * @since 6.0
 * @version 2011-7-23 ����12:52:11
 * @author ��־ΰ
 */
public abstract class AbstractGatheringKeyValue {

  /** �տ����֯ */
  public abstract String getPk_org();

  /** ԭ�ұ��� */
  public abstract String getPk_currtype();

  /** �ͻ� */
  public abstract String getCustomer();

  /** ���۶�������֯ */
  public abstract String getSo_org();

  /** ���۶����������� */
  public abstract String getSo_ordertype();

  /** ������֯ */
  public abstract String getSett_org();

  /** ���۲��� */
  public abstract String getSo_deptid();

  /** ����ҵ��Ա */
  public abstract String getSo_psndoc();

  /** ������������ */
  public abstract String getSo_transtype();

  /** ���۶����ͻ� */
  public abstract String getOrdercubasdoc();

  /** ��Ʒ�� */
  public abstract String[] getProductlines();

  /** ԭ�ҽ�� */
  public abstract UFDouble getMoney();
}
