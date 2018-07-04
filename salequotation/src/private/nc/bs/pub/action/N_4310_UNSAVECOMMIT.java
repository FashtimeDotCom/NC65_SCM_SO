package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע�����۵���ȡ���ύ
 * ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ
 * 
 * �������ڣ�(2010-10-12)
 * 
 * @author ƽ̨�ű�����
 */
public class N_4310_UNSAVECOMMIT extends AbstractCompiler2 {
  /**
   * N_4310_UNSAVE ������ע�⡣
   */
  public N_4310_UNSAVECOMMIT() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "  nc.vo.so.salequotation.entity.AggSalequotationHVO[] inObject  "
        + "=(nc.vo.so.salequotation.entity.AggSalequotationHVO[])getVos ();\n"
        + "Object retValue=new nc.bs.so.salequotation.bp.SalequoUnCommitBP()."
        + "unCommit(inObject, this);\nreturn retValue;\n";
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
          new nc.bs.so.salequotation.bp.SalequoUnCommitBP().unCommit(inObject,
              this);
      return retValue;
    }
    catch (Exception ex) {
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }
}
