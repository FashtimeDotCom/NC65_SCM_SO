package nc.ui.so.m32.billui.model;

import nc.bs.uif2.validation.ValidationException;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.so.pub.model.SOValidationService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.paravo.CombinCacheVO;

/**
 * ���۷�Ʊ�ǿ�У�����(����ϲ��༭�����)
 * 
 * @since 6.0
 * @version 2011-8-15 ����09:34:28
 * @author ô��
 */
public class SaleInvoiceValidationService extends SOValidationService {

  private SaleInvoiceManageModel model;

  public SaleInvoiceManageModel getModel() {
    return this.model;
  }

  public void setModel(SaleInvoiceManageModel model) {
    this.model = model;
  }

  @Override
  public void validate(Object arg0) throws ValidationException {
    try {
      if (super.getEditor() != null && super.getEditor() instanceof BillForm) {
        CombinCacheVO cachevo = this.getModel().getCombinCacheVO();
        if (!cachevo.getBcombinflag()) {
          ((BillForm) super.getEditor()).validateValue();
        }
      }
    }
    catch (Exception e) {

      ExceptionUtils.wrappException(e);
    }
  }

}
