package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.so.m30.revise.IM30ReviseMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.m30.util.FeatureSelectUtil;

/**
 * ���۶����޶�����
 * 
 * @since 6.0
 * @version 2011-8-9 ����04:22:50
 * @author fengjb
 */
public class N_30R_REVISEWRITE extends AbstractCompiler2 {

  /**
   * N_30_REVISEWRITE ������ע�⡣
   */
  public N_30R_REVISEWRITE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "\n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      PfUserObject userObj = (PfUserObject) this.getUserObj();
      /** -------------- �޶����涯�� -------------- */
      SaleOrderHistoryVO[] inCurVOs = (SaleOrderHistoryVO[]) this.getVos();
      FeatureSelectUtil.fillAggffileVO(inCurVOs,userObj);
      IM30ReviseMaintain maintainsrv =
          NCLocator.getInstance().lookup(IM30ReviseMaintain.class);
     // ReviseSaveSaleOrderAction action = new ReviseSaveSaleOrderAction();
     // �����µķ����������bills �����۶����޶���ʷvo
      SaleOrderHistoryVO[] ret = maintainsrv.reviseOrderHisVOSave(inCurVOs);
      /** -------------- �޶����涯�� -------------- */

      return ret;
    }
    catch (Exception ex) {

      ExceptionUtils.marsh(ex);
    }
    return null;
  }

}
