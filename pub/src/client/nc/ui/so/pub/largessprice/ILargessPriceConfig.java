package nc.ui.so.pub.largessprice;

import nc.ui.so.pub.findprice.IFindPriceConfig;

public interface ILargessPriceConfig {
  /**
   * ȡ�۷�ʽ
   * 
   * @return
   */
  Integer getLargessPriceMode();

  /**
   * ѯ�����ù���
   * 
   * @return
   */
  IFindPriceConfig getFindPriceConfig();

  /**
   * ��Ʒȡ�۳ɹ��к�������
   * 
   * @param sucessrows
   */
  void processAfterGetPrice(int[] rows, String chgkey);
}
