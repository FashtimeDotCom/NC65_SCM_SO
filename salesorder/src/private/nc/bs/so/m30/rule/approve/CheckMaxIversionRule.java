package nc.bs.so.m30.rule.approve;

import nc.vo.so.m30.util.Transfer30and30RVOTool;
import nc.bs.so.m30.maintain.util.VOCheckFor30R;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * @description
 *  У�鵱ǰ���ݵ��޶��汾�Ƿ������°汾
 * @scene
 *  �ύ������ǰ
 * @param
 *  ��
 * @since 6.3
 * @version 2014-12-10 ����2:14:20
 * @author wangshu6
 */
public class CheckMaxIversionRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    Transfer30and30RVOTool trans = new Transfer30and30RVOTool();
    SaleOrderHistoryVO[] bills = trans.transfer30TOOrderhisVO(vos);
    VOCheckFor30R vocheck  = new VOCheckFor30R();
    try {
      // ���۶����޶�����ʱ ���� У��
      vocheck.voCheckForSaveAndApprove(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    
  }
}
