package nc.bs.so.m30.rule.send;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * ���۶����ջ�ǰ��鵥���Ƿ���ջ�
 * @scene
 * ���۶����ջ�ǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-2-22 ����10:58:10
 * @author ô��
 */
public class CheckUnSendEnableRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO vo : vos) {
      SaleOrderHVO header = vo.getParentVO();
      // ������״̬����������Ϊ�յĵ��������ջ�
      if (!BillStatus.AUDITING.equalsValue(header.getFstatusflag())
          || null != header.getApprover()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0116")/*@res "��ǰ���ݵ���״̬�����ɽ����ջء�"*/);
      }

    }
  }

}