package nc.itf.so.pub.para;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.so.pub.para.WeightVolPieceVO;

/**
 * ������������ӿ�
 * 
 * @since 6.0
 * @version 2011-7-5 ����11:44:18
 * @author ף����
 */
public interface IWeightAndVolMaintain {
  /**
   * ��ñ�׼�������������
   * 
   * @return
   * @throws BusinessException
   */
  Map<String, String> getWeightAndVolName(String pk_group)
      throws BusinessException;

  /**
   * ��ñ�׼�����������ֵ �Լ�����������
   * 
   * @return
   * @throws BusinessException
   */
  Map<String, WeightVolPieceVO> getWeightAndVolValue(String[] pk_materials)
      throws BusinessException;

}
