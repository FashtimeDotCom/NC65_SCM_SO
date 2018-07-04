package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.so.m38.action.InsertPreOrderAction;
import nc.impl.so.m38.action.UpdatePreOrderAction;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.so.m38.entity.PreOrderVO;

public class N_38_WRITE extends AbstractCompiler2 {
  /**
   * N_38_WRITE �Ĺ�����
   */
  public N_38_WRITE() {
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
    // ԭʼ��ȫVO
    PreOrderVO[] originBills =
        (PreOrderVO[]) vo
            .getCustomProperty(PfParameterUtil.ORIGIN_VO_PARAMETER);
    super.m_tmpVo = vo;
    PreOrderVO[] inCurObjects = (PreOrderVO[]) this.getVos();

    PreOrderVO[] ret = null;
    if (StringUtil.isEmptyWithTrim(inCurObjects[0].getParentVO()
        .getCpreorderid())) {
      ret = new InsertPreOrderAction().insert(inCurObjects);
    }
    else {
      vo.m_preValueVo = inCurObjects[0];
      vo.m_preValueVos = inCurObjects;
      ret = new UpdatePreOrderAction().update(inCurObjects, originBills);
    }
    return ret;
  }
}
