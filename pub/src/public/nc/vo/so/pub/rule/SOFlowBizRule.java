package nc.vo.so.pub.rule;

import nc.itf.uap.pf.metadata.IFlowBizItf;
import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.pf.IPfRetCheckInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۹�����ƽ̨������״̬��ҵ�񵥾�״̬ӳ����
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-4-30 ����10:29:50
 */
public class SOFlowBizRule extends FlowBizImpl {
  /**
   * SOFlowBizRule �Ĺ�����
   * 
   * @param ncobject
   */
  public SOFlowBizRule(NCObject ncobject) {
    super(ncobject);
  }

  @Override
  public void setApproveDate(UFDateTime approveDate) {
    UFDate date = null;
    if (null != approveDate) {
      date = approveDate.getDate();
    }
    this.setAttributeValue(IFlowBizItf.ATTRIBUTE_APPROVEDATE, date);
  }

  @Override
  public Integer getApproveStatus() {

    Integer pfstatus = super.getApproveStatus();

    if (null == pfstatus) {
      return null;
    }
    Integer newicheckState = null;
    switch (pfstatus.intValue()) {
      case BillStatus.I_FREE:
        newicheckState = (Integer) IPfRetCheckInfo.NOSTATE;
        break;
      case BillStatus.I_AUDIT:
        newicheckState = IPfRetCheckInfo.PASSING;
        break;
      case BillStatus.I_NOPASS:
        newicheckState = IPfRetCheckInfo.NOPASS;
        break;
      case BillStatus.I_AUDITING:
        newicheckState = IPfRetCheckInfo.GOINGON;
        break;
      default:
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006004_0", "04006004-0009")
        /*@res "������ƽ̨״̬תΪ����״̬ʱ����"*/);

    }
    return newicheckState;
  }

  @Override
  public void setApproveStatus(Integer icheckState) {

    Integer newicheckState = null;

    switch (icheckState.intValue()) {
      case IPfRetCheckInfo.NOSTATE:
        newicheckState = (Integer) BillStatus.FREE.value();
        break;
      case IPfRetCheckInfo.COMMIT:
        newicheckState = (Integer) BillStatus.AUDITING.value();
        break;
      case IPfRetCheckInfo.PASSING:
        newicheckState = (Integer) BillStatus.AUDIT.value();
        break;
      case IPfRetCheckInfo.NOPASS:
        newicheckState = (Integer) BillStatus.NOPASS.value();
        break;
      case IPfRetCheckInfo.GOINGON:
        newicheckState = (Integer) BillStatus.AUDITING.value();
        break;
      default:
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006004_0", "04006004-0009")
        /*@res "������ƽ̨״̬תΪ����״̬ʱ����"*/);
    }
    super.setApproveStatus(newicheckState);
  }
}
