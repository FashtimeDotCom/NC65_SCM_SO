package nc.impl.so.m38.pf;

import nc.bs.pub.pf.CheckStatusCallbackContext;
import nc.bs.pub.pf.ICheckStatusCallback;
import nc.bs.scmpub.pf.PfBeforeAndAfterAction;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pub.BusinessException;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * �������ı䵥��״̬ʵ����
 * 
 * @author ��־ΰ
 */
public class PreOrderCheckStatusCallback extends PfBeforeAndAfterAction
    implements ICheckStatusCallback {

  @Override
  public void callCheckStatus(CheckStatusCallbackContext cscc)
      throws BusinessException {
    PreOrderVO bill = (PreOrderVO) cscc.getBillVo();
    PreOrderHVO head = bill.getParentVO();
    // ���±�ͷ
    String[] names = new String[] {
      PreOrderHVO.FSTATUSFLAG, PreOrderHVO.APPROVER, PreOrderHVO.TAUDITTIME
    };
    VOUpdate<PreOrderHVO> bo = new VOUpdate<PreOrderHVO>();
    bo.update(new PreOrderHVO[] {
      head
    }, names);

  }

}
