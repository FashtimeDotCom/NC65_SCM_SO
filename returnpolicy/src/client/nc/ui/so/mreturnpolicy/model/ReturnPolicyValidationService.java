package nc.ui.so.mreturnpolicy.model;

import java.util.ArrayList;
import java.util.List;

import nc.bs.uif2.validation.ValidationException;
import nc.bs.uif2.validation.ValidationFailure;
import nc.ui.ml.NCLangRes;
import nc.ui.uif2.model.DefaultBatchValidationService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.mreturnpolicy.entity.ReturnPolicyVO;

public class ReturnPolicyValidationService extends
    DefaultBatchValidationService {
  @Override
  public int[] unNecessaryData(List<Object> rows) {
    if (this.getEditor().isBodyAutoAddLine()) {
      int size = rows.size();
      List<Integer> list = new ArrayList<Integer>();

      for (int i = 0; i < size; i++) {
        ReturnPolicyVO vo = (ReturnPolicyVO) rows.get(i);
        if ((vo.getVpolicycode() == null) && (vo.getVpolicyname() == null)) {
          list.add(Integer.valueOf(i));
        }
      }
      int[] del = new int[list.size()];
      if (list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
          Object obj = list.get(i);
          del[i] = ValueUtils.getInt(obj);
        }
      }
      return del;
    }
    return null;
  }

  @Override
  protected void modelValidate(Object obj) throws ValidationException {
    BatchOperateVO batchVO = (BatchOperateVO) obj;
    Object[] addVOs = batchVO.getAddObjs();
    Object[] updateVOs = batchVO.getUpdObjs();
    Object[] newVOs = new Object[updateVOs.length + addVOs.length];
    System.arraycopy(addVOs, 0, newVOs, 0, addVOs.length);
    System.arraycopy(updateVOs, 0, newVOs, addVOs.length, updateVOs.length);

    List<ValidationFailure> list = new ArrayList<ValidationFailure>();
    for (int i = 0; i < newVOs.length; i++) {
      ReturnPolicyVO rtnpolicyVO = (ReturnPolicyVO) newVOs[i];
      this.checkVOData(rtnpolicyVO);
    }
    if (list.size() > 0) {
      throw new ValidationException(list);
    }
  }

  private void checkVOData(ReturnPolicyVO rtnpolicyVO) {
    String code = rtnpolicyVO.getVpolicycode();
    String name = rtnpolicyVO.getVpolicyname();
    String expressname = rtnpolicyVO.getVexpressname();
    UFDate startdate = rtnpolicyVO.getDstartdate();
    UFDate enddate = rtnpolicyVO.getDenddate();
    if (code == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0025")/*@res "�˻����߱��벻��Ϊ�գ����飡"*/);
    }
    else if (name == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0026")/*@res "�˻��������Ʋ���Ϊ�գ����飡"*/);
    }
    else if (expressname == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0027")/*@res "�˻������߼����ʽ����Ϊ�գ����飡"*/);
    }
    else if (startdate == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0028")/*@res "ִ�п�ʼ���ڲ���Ϊ�գ����飡"*/);
    }
    else if (enddate == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0029")/*@res "ִ�н������ڲ���Ϊ�գ����飡"*/);
    }
    else if (startdate.after(enddate)) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0109", null, new String[]{code})/*�˻����߱���[{0}]�У���ʼʱ�䲻�ܴ��ڽ�������*/);
    }
  }
}