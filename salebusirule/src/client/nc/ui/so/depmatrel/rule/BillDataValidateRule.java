package nc.ui.so.depmatrel.rule;

import nc.ui.ml.NCLangRes;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.depmatrel.entity.DepMatRelBVO;
import nc.vo.so.depmatrel.entity.DepMatRelVO;

/**
 * ����ʱ�����ݽ���ǰ̨�ǿ�У��
 * 
 * @since 6.0
 * @version 2011-7-6 ����02:02:08
 * @author ף����
 */
public class BillDataValidateRule {
  public void validate(DepMatRelVO vo) {
    StringBuffer errMsg = new StringBuffer();
    DepMatRelBVO[] bvos = vo.getChildrenVO();
    if (null == bvos) {
      return;
    }
    for (DepMatRelBVO bvo : bvos) {
      this.checkMaterial(bvo, errMsg);
      if (errMsg.length() > 1) {
        ExceptionUtils.wrappBusinessException(errMsg.toString());
      }
    }
  }

  private void checkMaterial(DepMatRelBVO bvo, StringBuffer errMsg) {
    String pk_mat = bvo.getPk_material_v();
    String pk_base = bvo.getPk_materialbaseclass();
    String pk_sale = bvo.getPk_materialsaleclass();
    if (null == pk_mat && null == pk_base && null == pk_sale) {
      errMsg.append(NCLangRes.getInstance().getStrByID("4006007_0", "04006007-0003")/*���Ϻ����Ϸ��಻�ܶ�Ϊ�ա�*/);
      errMsg.append("\n");
    }
  }
}
