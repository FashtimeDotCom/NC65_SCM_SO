package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.so.m4331.IDeliveryScriptMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

public class N_4331_DELETE extends AbstractCompiler2 {
  public N_4331_DELETE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "  \n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    super.m_tmpVo = vo;
    Object retValue = null;
    try {
      Object[] inCurObjects = this.getPfParameterVO().m_preValueVos;
      if (VOChecker.isEmpty(inCurObjects)) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006002_0", "04006002-0020")/*@res "����:��ϣ���h���ĳ�Ӧ�յ�û������"*/);
      }
      if (!(inCurObjects instanceof DeliveryVO[])) {

        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006002_0", "04006002-0021")/*@res "����:��ϣ���h���ĳ�Ӧ�յ����Ͳ�ƥ��"*/);
      }
      DeliveryVO[] voInvoices = new DeliveryVO[inCurObjects.length];
      int length = inCurObjects.length;
      for (int i = 0; i < length; i++) {
        voInvoices[i] = (DeliveryVO) inCurObjects[i];
      }
      PfUserObject userObj = (PfUserObject) this.getUserObj();
      IDeliveryScriptMaintain maintainsrv =
          NCLocator.getInstance().lookup(IDeliveryScriptMaintain.class);
      maintainsrv.deliveryDelete(voInvoices, userObj);
      return retValue;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
