package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.so.m30.self.ISaleOrderScriptMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;


/**
 * 
 * ɾ�������ű�
 * 
 * @author ƽ̨�ű�����
 * @since 6.0
 */
public class N_30_DELETE extends AbstractCompiler2 {
  public N_30_DELETE() {
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

      SaleOrderVO[] inCurVOs = (SaleOrderVO[]) this.getVos();
      PfUserObject userObj = (PfUserObject) this.getUserObj();
      ISaleOrderScriptMaintain maintainsrv =
          NCLocator.getInstance().lookup(ISaleOrderScriptMaintain.class);
      return maintainsrv.saleOrderDelete(inCurVOs, userObj);
    }
    catch (Exception ex) {

      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  }
