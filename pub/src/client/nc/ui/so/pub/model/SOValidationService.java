package nc.ui.so.pub.model;

import nc.bs.uif2.validation.IValidationService;
import nc.bs.uif2.validation.ValidationException;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.editor.IEditor;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ����ģ��ǿ�У�����
 * 
 * @since 6.0
 * @version 2011-6-24 ����10:31:43
 * @author ô��
 */
public class SOValidationService implements IValidationService {

  private IEditor editor;

  public IEditor getEditor() {
    return this.editor;
  }

  public void setEditor(IEditor editor) {
    this.editor = editor;
  }

  @Override
  public void validate(Object arg0) throws ValidationException {
    try {
      if (this.editor != null && this.editor instanceof BillForm) {
        ((BillForm) this.editor).validateValue();
      }
    }
    catch (Exception e) {

      ExceptionUtils.wrappException(e);
    }
  }

}
