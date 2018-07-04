package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��������ʽ���沢�������
 *
 * @since 6.0
 * @version 2010-11-10 ����02:49:46
 * @author ף����
 */
public class N_4331_PUSHWRITE extends AbstractCompiler2 {
  /**
   * N_32_WRITE �Ĺ�����
   */
  public N_4331_PUSHWRITE() {
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
    try {
      Object[] inCurObjects = this.getPfParameterVO().m_preValueVos;
      if (null == inCurObjects) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0022")/*@res "������ϣ����ʽ����ķ�����û������"*/);
        return null;
      }
      if (!(inCurObjects instanceof DeliveryVO[])) {

        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0023")/*@res "������ϣ����ʽ����ķ��������Ͳ�ƥ��"*/);

      }

      int ilength = inCurObjects.length;
      DeliveryVO[] voInvoices = new DeliveryVO[ilength];
      for (int i = 0; i < ilength; i++) {
        voInvoices[i] = (DeliveryVO) inCurObjects[i];
      }
      // ���÷���������ű�
      DeliveryVO[] vos =
          (DeliveryVO[]) PfServiceScmUtil.processBatch("WRITE",
              SOBillType.Delivery.getCode(), voInvoices, null, null);
      return vos;
    }
    catch (Exception e) {
      throw new PFBusinessException(e.getMessage(), e);
    }
  }
}