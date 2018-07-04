package nc.bs.so.pub.rule;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.uap.pf.IPFWorkflowQry;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 *              ���۶�������Ʊ������֧���޸ģ�У�鵱ǰ�������Ƿ���������
 * @scene
 *        ���۶�������Ʊ������֧���޸� �޸�ǰ�����鵱ǰ�������Ƿ��������ˣ�ֻ�������˲ſ����޸�
 * @param ��
 * 
 * @since 6.3
 * @version 2015-1-19 ����3:12:18
 * @author wangshu6
 */
public class CheckApproverRule implements IRule {

  @Override
  public void process(Object[] vos) {
    for (Object vo : vos) {
      Integer status =
          (Integer) ((AbstractBill) vo).getParentVO().getAttributeValue(
              SOItemKey.FSTATUSFLAG);
      // ֻ�е������вŽ��� ��ǰ�������Ƿ��������˵�У��
      if (BillStatus.AUDITING.getIntegerValue().equals(status)) {
        boolean isApprover = false;
        try {
          
          IPFWorkflowQry queryService = NCLocator.getInstance().lookup(IPFWorkflowQry.class);
          // ��ȡ����ID
          String billID = ((AbstractBill) vo).getPrimaryKey();
          // ��ȡ�������ͱ���
          String vtrantypecode =
              (String) ((AbstractBill) vo).getParentVO().getAttributeValue(
                  SOItemKey.VTRANTYPECODE);
          // ��ȡ��ǰ��¼��
          String user = AppContext.getInstance().getPkUser();
          // ��ѯ�������Ƿ���������
          isApprover = queryService.isCheckman(billID, vtrantypecode, user);
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
        if (!isApprover) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006004_0", "04006004-0238")/*��ǰ�����˲��������ˣ������е��ݽ�ֹ�޸ģ�*/);
        }
      }
    }
  }

}
