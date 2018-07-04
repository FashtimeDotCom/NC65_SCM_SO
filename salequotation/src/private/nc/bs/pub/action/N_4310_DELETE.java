package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע�����۵���ɾ�� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ
 * 
 * �������ڣ�(2010-7-8)
 * 
 * @author ƽ̨�ű�����
 */
public class N_4310_DELETE extends AbstractCompiler2 {
  /**
   * N_4310_DELETE ������ע�⡣
   */
  public N_4310_DELETE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "  nc.vo.so.salequotation.entity.AggSalequotationHVO[] inObject "
        + " =(nc.vo.so.salequotation.entity.AggSalequotationHVO[])getVos ();"
        + "\nnc.bs.framework.common.NCLocator.getInstance().lookup(nc.itf.so"
        + ".salequotation.ISalequotationService.class).delete(inObject);"
        + "\nreturn null;\n";
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
      nc.bs.framework.common.NCLocator.getInstance()
          .lookup(nc.itf.so.salequotation.ISalequotationService.class)
          .delete(inObject);
      return null;
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }
}
