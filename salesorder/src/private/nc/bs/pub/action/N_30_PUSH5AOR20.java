package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.so.m30.action.Push5Aor20Action;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * ����ת�����齨�ű�
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>�繫˾����ת��������ת��������
 * <li>����˾����ת��������ת�빺
 * <li>...
 * </ul>
 * 
 * @version v6.0
 * @author zhangcheng
 * @time 2010-6-7 ����06:01:04
 */
public class N_30_PUSH5AOR20 extends AbstractCompiler2 {

  public N_30_PUSH5AOR20() {
    super();
  }

  @Override
  public String getCodeRemark() {
    return "\n";
  }

  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    super.m_tmpVo = vo;

    try {
      new Push5Aor20Action().process(new SaleOrderVO[] {
        (SaleOrderVO) this.getVo()
      });
    }
    catch (Exception e) {

      ExceptionUtils.marsh(e);
    }

    return null;

  }

}
