package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע�����۵������� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ
 * 
 * �������ڣ�(2010-7-7)
 * 
 * @author ƽ̨�ű�����
 */
public class N_4310_UNAPPROVE extends AbstractCompiler2 {
  /**
   * N_4310_UNAPPROVE ������ע�⡣
   */
  public N_4310_UNAPPROVE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "  Object retValue = null;\n      /*********************** ���õ�����"/*-=notranslate=-*/
        + "��������� �����޸� ******************/\n      "/*-=notranslate=-*/
        + "nc.vo.so.salequotation.entity.AggSalequotationHVO[] inObject = "
        + "(nc.vo.so.salequotation.entity.AggSalequotationHVO[]) getVos();\n"
        + "      retValue = nc.bs.framework.common.NCLocator.getInstance()."
        + "lookup(nc.itf.so.salequotation.ISalequotationService.class)\n    "
        + "      .unApprove(inObject, this);\n      /*************************"
        + "**********************************************/\n     "
        + " return retValue;\n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      Object retValue = null;
      /*********************** ���õ�������������� �����޸� ******************/
      nc.vo.so.salequotation.entity.AggSalequotationHVO[] inObject =
          (nc.vo.so.salequotation.entity.AggSalequotationHVO[]) this.getVos();
      retValue =
          nc.bs.framework.common.NCLocator.getInstance()
              .lookup(nc.itf.so.salequotation.ISalequotationService.class)
              .unApprove(inObject, this);
      /***********************************************************************/
      return retValue;
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }
}
