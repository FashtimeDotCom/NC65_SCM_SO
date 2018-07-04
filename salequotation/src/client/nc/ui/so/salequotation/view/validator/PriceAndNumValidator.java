package nc.ui.so.salequotation.view.validator;

import nc.bs.uif2.validation.ValidationFailure;
import nc.bs.uif2.validation.Validator;
import nc.ui.ml.NCLangRes;
import nc.ui.so.salequotation.view.SalequoBillForm;
import nc.vo.scmpub.util.VOFieldLengthChecker;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;

public class PriceAndNumValidator implements Validator {
  private SalequoBillForm editor;

  public SalequoBillForm getEditor() {
    return this.editor;
  }

  public void setEditor(SalequoBillForm editor) {
    this.editor = editor;
  }

  @Override
  public ValidationFailure validate(Object obj) {
    return this.checkPriceAndNum();
  }

  private ValidationFailure checkPriceAndNum() {
    ValidationFailure failure = null;
    AggSalequotationHVO aggVO =
        (AggSalequotationHVO) this.getEditor().getValue();
    VOFieldLengthChecker.checkVOFieldsLength(aggVO);
    if (aggVO.getChildrenVO() != null && aggVO.getChildrenVO().length != 0) {
      SalequotationBVO[] bvos = aggVO.getChildrenVO();
      if (bvos == null) {
        return null;
      }
      for (int i = 0; i < bvos.length; i++) {
        failure = this.validateNonNegative(bvos[i]);
        if (failure != null) {
          break;
        }
      }
    }
    return failure;
  }

  private ValidationFailure validateNonNegative(SalequotationBVO bvo) {
    ValidationFailure failure = null;
    if (bvo == null) {
      return failure;
    }
    if (bvo.getNnum() != null && bvo.getNnum().doubleValue() < 0) {
      failure = new ValidationFailure(NCLangRes.getInstance().getStrByID("4006009_0", "04006009-0047", null, new String[]{bvo.getCrowno()})/*�кţ�{0}����������С����!*/);
    }
    if (bvo.getNassistnum() != null && bvo.getNassistnum().doubleValue() < 0) {
      failure = new ValidationFailure(NCLangRes.getInstance().getStrByID("4006009_0", "04006009-0048", null, new String[]{bvo.getCrowno()})/*�кţ�{0}��������С����!*/);
    }

    if (bvo.getNqtorigprice() != null
        && bvo.getNqtorigprice().doubleValue() < 0) {
      failure = new ValidationFailure(NCLangRes.getInstance().getStrByID("4006009_0", "04006009-0049", null, new String[]{bvo.getCrowno()})/*�кţ�{0}��˰���۲���С����!*/);
    }
    if (bvo.getNqtorigtaxprice() != null
        && bvo.getNqtorigtaxprice().doubleValue() < 0) {
      failure = new ValidationFailure(NCLangRes.getInstance().getStrByID("4006009_0", "04006009-0050", null, new String[]{bvo.getCrowno()})/*�кţ�{0}��˰���۲���С����!*/);
    }
    if (bvo.getNqtorignetprice() != null
        && bvo.getNqtorignetprice().doubleValue() < 0) {
      failure = new ValidationFailure(NCLangRes.getInstance().getStrByID("4006009_0", "04006009-0051", null, new String[]{bvo.getCrowno()})/*�кţ�{0}��˰���۲���С����!*/);
    }
    if (bvo.getNqtorigtaxnetprc() != null
        && bvo.getNqtorigtaxnetprc().doubleValue() < 0) {
      failure = new ValidationFailure(NCLangRes.getInstance().getStrByID("4006009_0", "04006009-0052", null, new String[]{bvo.getCrowno()})/*�кţ�{0}��˰���۲���С����!*/);
    }

    if (bvo.getNorigprice() != null && bvo.getNorigprice().doubleValue() < 0) {
      failure = new ValidationFailure(NCLangRes.getInstance().getStrByID("4006009_0", "04006009-0053", null, new String[]{bvo.getCrowno()})/*�кţ�{0}����˰���۲���С����!*/);
    }

    if (bvo.getNorigtaxprice() != null
        && bvo.getNorigtaxprice().doubleValue() < 0) {
      failure = new ValidationFailure(NCLangRes.getInstance().getStrByID("4006009_0", "04006009-0054", null, new String[]{bvo.getCrowno()})/*�кţ�{0}����˰���۲���С����!*/);
    }
    if (bvo.getNorignetprice() != null
        && bvo.getNorignetprice().doubleValue() < 0) {
      failure = new ValidationFailure(NCLangRes.getInstance().getStrByID("4006009_0", "04006009-0055", null, new String[]{bvo.getCrowno()})/*�кţ�{0}����˰���۲���С����!*/);
    }
    if (bvo.getNorigtaxnetprice() != null
        && bvo.getNorigtaxnetprice().doubleValue() < 0) {
      failure = new ValidationFailure(NCLangRes.getInstance().getStrByID("4006009_0", "04006009-0056", null, new String[]{bvo.getCrowno()})/*�кţ�{0}����˰���۲���С����!*/);
    }
    if (bvo.getNtaxrate() != null && bvo.getNtaxrate().doubleValue() < 0) {
      failure = new ValidationFailure(NCLangRes.getInstance().getStrByID("4006009_0", "04006009-0057", null, new String[]{bvo.getCrowno()})/*�кţ�{0}˰�ʲ���Ϊ����!*/);
    }
    if (bvo.getNitemdiscountrate() != null
        && bvo.getNitemdiscountrate().doubleValue() < 0) {
      failure = new ValidationFailure(NCLangRes.getInstance().getStrByID("4006009_0", "04006009-0058", null, new String[]{bvo.getCrowno()})/*�кţ�{0}��Ʒ�ۿ۲���Ϊ����!*/);
    }
    if (bvo.getNqtnum() != null && bvo.getNqtnum().doubleValue() < 0) {
      failure = new ValidationFailure(NCLangRes.getInstance().getStrByID("4006009_0", "04006009-0059", null, new String[]{bvo.getCrowno()})/*�кţ�{0}������������Ϊ����!*/);
    }

    return failure;
  }
}
