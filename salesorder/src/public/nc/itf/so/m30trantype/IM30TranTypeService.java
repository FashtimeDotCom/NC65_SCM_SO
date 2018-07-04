package nc.itf.so.m30trantype;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;

/**
 * <b> ���۶����������ͷ���ӿ� </b>
 * ��������:2010-01-25 18:42:55
 * 
 * @author ��־ΰ
 * @version 6.0
 */
public interface IM30TranTypeService {

  /**
   * ��������ֱ�����ͱ��
   * 
   * @param ctrantypeids ��������ID
   * @return Map<String, Integer> Map<ctrantypeid, ֱ�����ͱ��>
   * @throws BusinessException
   */
  Map<String, Integer> queryDirectType(String[] ctrantypeids)
      throws BusinessException;

  /**
   * ��������ֱ�����ͱ���Ƿ�ֱ�˲ɹ�
   * 
   * @param ctrantypeids ��������ID
   * @return Map<String, UFBoolean> Map<ctrantypeid, �Ƿ�ֱ�˲ɹ�>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsDirectPO(String[] ctrantypeids)
      throws BusinessException;

  /**
   * ��������ֱ�����ͱ���Ƿ�ֱ�˵���
   * 
   * @param ctrantypeids ��������ID
   * @return Map<String, UFBoolean> Map<ctrantypeid, �Ƿ�ֱ�˵���>
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryIsDirectTO(String[] ctrantypeids)
      throws BusinessException;

  /**
   * ��ѯ��ǰ������ֱ�����͵����н�������
   * 
   * @author ��־ΰ
   * @time 2010-08-03 ����01:32:25
   */
  String[] queryDirectTypeAllBillTypeCode(String pk_group)
      throws BusinessException;

  /**
   * ���ݽ������ͱ����ѯ��������VO
   * 
   * @author ��־ΰ
   * @time 2010-08-03 ����01:32:25
   */
  M30TranTypeVO queryTranType(String pk_group, String pk_billtypecode)
      throws BusinessException;

  /**
   * ���ݽ������ͱ���[]��ѯ��������VOs
   * 
   * @param pk_group ����
   * @param pk_billtypecodes ��������[]
   * @return M30TranTypeVO[]
   */
  M30TranTypeVO[] queryTranTypeVOs(String pk_group, String[] pk_billtypecodes)
      throws BusinessException;

  /**
   * ���ݽ�������ID��ѯ��������VO
   * 
   * @param pk_group ����
   * @param pk_billtypecodes ��������[]
   * @return M30TranTypeVO[]
   */
  M30TranTypeVO queryTranTypeVO(String trantypeid) throws BusinessException;

  /**
   * ���ݽ�������IDs��ѯ��������VOs
   * 
   * @param pk_group ����
   * @param pk_billtypecodes ��������[]
   * @return M30TranTypeVO[]
   */
  M30TranTypeVO[] queryTranTypeVOs(String[] ctrantypeids)
      throws BusinessException;

  /**
   * ���ݽ�������IDs
   * 
   * @param ctrantypeids ��������ID
   * @return Map<String, Integer> Map<ctrantypeid, ѯ�۹���>
   * @throws BusinessException
   */
  Map<String, Integer> queryAskPriceRule(String[] ctrantypeids)
      throws BusinessException;
}
