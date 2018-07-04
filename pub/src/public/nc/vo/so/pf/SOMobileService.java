package nc.vo.so.pf;

import nc.bs.pub.pf.IPrintDataGetter;
import nc.ui.pub.print.IDataSource;
import nc.vo.pub.BusinessException;

/**
 * ���۹������ʼ�����ʵ����
 * 
 * @since 6.0
 * @version 2011-12-23 ����12:53:34
 * @author fengjb
 */
public class SOMobileService implements IPrintDataGetter {

  @Override
  public IDataSource getPrintDs(String billId, String billtype, String checkman)
      throws BusinessException {
    return new SOPrintDataSourceForMailAudit(billtype, billId);
  }

}
