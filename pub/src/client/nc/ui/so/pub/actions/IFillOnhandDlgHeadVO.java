package nc.ui.so.pub.actions;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.so.entry.SOOnhandDlgHeadVO;

/**
 * 
 * @description
 *              ��ȫ��������ͷ���ݽӿ�
 * @scene
 *        �������ȫ����
 * @param ��
 * 
 * @since 6.5
 * @version 2015-11-19 ����7:21:42
 * @author zhangby5
 */
public interface IFillOnhandDlgHeadVO {

  /**
   * ��ȫ��������ͷ����
   * 
   * @return ��������ͷVO
   */
  SOOnhandDlgHeadVO fillOnhandVO(CircularlyAccessibleValueObject hvo,
      CircularlyAccessibleValueObject bvo);
}
