package nc.ui.so.custmatrel.action;

import nc.vo.so.custmatrel.entity.CustMatRelHVO;

import nc.ui.trade.excelimport.ExportTempItemSeleStrategy;
import nc.ui.trade.excelimport.InputItem;

/**
 * ��չ����ģ���ֶ�
 * 
 * @since 6.1
 * @version 2013-12-10 14:39:59
 * @author liujingn
 */
public class CustMaterExportDataItemSeleStrategy extends
    ExportTempItemSeleStrategy {

  @Override
  public boolean accept(InputItem item) {
    // ����ģ���������ʾ������֯
    if (item.getItemKey().equals(CustMatRelHVO.PK_ORG)) {
      return true;
    }
    return super.accept(item);
  }

}
