package nc.vo.so.m30.rule;

import nc.itf.uap.pf.metadata.IFlowBizItf;
import nc.md.data.access.NCObject;
import nc.uap.pf.metadata.FlowBizImpl;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;

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
public class SaleOrderFlowBizRule extends FlowBizImpl {
  /**
   * SOFlowBizRule �Ĺ�����
   * 
   * @param ncobject
   */
  public SaleOrderFlowBizRule(NCObject ncobject) {
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
}
