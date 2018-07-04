package nc.ui.so.pub.findprice;

import nc.vo.pub.AggregatedValueObject;


public interface IFindPriceConfig {
  /**
   * ѯ�۳ɹ��Ƿ������޸ĵ���
   * 
   * @return
   */
  boolean isModifyAskSucess();

  /**
   * ѯ��ʧ���Ƿ������޸ĵ���
   * 
   * @return
   */
  boolean isModifyAskFail();

  /**
   * ѯ��ʧ���Ƿ���ʾ
   * 
   * @return
   */
  boolean isShowMsgAskFail();

  /**
   * ��Ʒ�Ƿ�ѯ��
   * 
   * @return
   */
  boolean isLargessAskPrice();

  /**
   * ѯ�۹���
   * 
   * @return
   */
  Integer getAskPriceRule();

  /**
   * ѯ�۳ɹ��к�������
   * 
   * @param sucessrows
   */
  void processAskSucessRows(int[] sucessrows, String chgkey);

  /**
   * ѯ��ʧ���к�������
   * 
   * @param failrows
   */
  void processAskFailRows(int[] failrows);
  
  /**
   * ��������VO����
   * @return
   */
  AggregatedValueObject getBillVO();
  
  
  /**
   * �Ƿ���Ʒ�Ҹ�
   * 
   * @return
   */
  boolean isblrgcashflag();
  
  
  /**
   * ������������ģʽ
   * 
   * @return
   */
  int getSalemode();

}
