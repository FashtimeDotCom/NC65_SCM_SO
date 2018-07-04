package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע�����۵��Ĺر� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ
 * 
 * �������ڣ�(2010-7-8)
 * 
 * @author ƽ̨�ű�����
 */
public class N_4310_CLOSE extends AbstractCompiler2 {
  /**
   * N_4310_CLOSE ������ע�⡣
   */
  public N_4310_CLOSE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "  nc.vo.so.salequotation.entity.AggSalequotationHVO[] inObject"
        + "  =(nc.vo.so.salequotation.entity.AggSalequotationHVO[])getVos ();"
        + "\n  Object retValue=nc.bs.framework.common.NCLocator.getInstance()"
        + ".lookup(nc.itf.so.salequotation.ISalequotationService.class)."
        + "close(inObject);\n  return retValue;\n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      nc.vo.so.salequotation.entity.AggSalequotationHVO[] inObject =
          (nc.vo.so.salequotation.entity.AggSalequotationHVO[]) this.getVos();
      Object retValue =
          nc.bs.framework.common.NCLocator.getInstance()
              .lookup(nc.itf.so.salequotation.ISalequotationService.class)
              .close(inObject);
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
