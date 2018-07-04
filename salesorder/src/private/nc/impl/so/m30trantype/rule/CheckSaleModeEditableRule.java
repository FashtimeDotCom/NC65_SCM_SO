package nc.impl.so.m30trantype.rule;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.so.m30trantype.tool.CheckKeyEditableTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;

/**
 * @description
 *  У�齻����������ģʽ�����Ƿ���޸�
 * @scene
 *  ���������޸ı���ǰ����
 * @param
 *  ��
 * @since 6.36
 * @version 2015-1-14 ����6:26:20
 * @author wangshu6
 */
public class CheckSaleModeEditableRule implements ICompareRule<M30TranTypeVO> {


  @Override
  public void process(M30TranTypeVO[] updateTransTypeVOs,
      M30TranTypeVO[] originVOs) {
    CheckKeyEditableTool<SaleOrderHVO, M30TranTypeVO> tool =
        new CheckKeyEditableTool<SaleOrderHVO, M30TranTypeVO>(
            SaleOrderHVO.class, M30TranTypeVO.class, M30TranTypeVO.FSALEMODE);
    tool.checkKeySuccessModify(updateTransTypeVOs, originVOs);
  }
}
