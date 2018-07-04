package nc.pubitf.so.mbuylargess.pub;

import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

/**
 * �������ö����ṩ�����ӿ�
 * 
 * @since 6.3
 * @version 2014-04-09 14:34:49
 * @author ����
 */
public interface IBuylargessOperate {

  /**
   * 
   * �������ñ���У��ӿ�
   * 
   * ʹ�ó�����
   * CRMӪ�������ʱ��ģ���������߱���У��Ĺ��̣���ǰ����У�飬
   * ������Ӫ�������ʱ���������ò��׳�У��ʧ����Ϣ��
   * 
   * @param buylargessVOs �������þۺ�VO����
   * @return Map<У��ʧ�������±�,ʧ��ԭ��>
   * @throws BusinessException
   */
  Map<Integer, String> checkBuylargessVO(BuyLargessVO[] buylargessVOs)
      throws BusinessException;

  /**
   * �������ñ���ӿ�
   * 
   * 
   * @param buylargessVOs �������þۺ�VO����
   * @return �������þۺ�VO����
   * @throws BusinessException
   */
  BuyLargessVO[] saveBuylargessVO(BuyLargessVO[] buylargessVOs)
      throws BusinessException;

  /**
   * ��������ɾ���ӿ�
   * 
   * @param pk_buylargess ������������
   * @throws BusinessException
   */
  void deleteBuylargessVO(String[] pk_buylargess) throws BusinessException;
  
  /**
	 * ��������ɾ���ӿ�
	 * 
	 * @param buyLargessVOs
	 * @throws BusinessException
	 */
	void deleteBuylargessVOs(BuyLargessVO[] buyLargessVOs)
			throws BusinessException;
	
	/**
	 * ����Ӫ���id��ѯ����VO
	 * 
	 * @param cmarketactids
	 *            Ӫ���id����
	 * @param whereCon
	 *            ������ѯ����(����Ҫ��dr=0��Ĭ���Ѿ�����)����ͷ���ü�and�����������ֻ���������������
	 *            ��Ҫ��so_buylargess.�ı���������û������������Ϊnull
	 * @return Map<cmarketactid, List<BuyLargessVO>>
	 * @throws BusinessException
	 */
	Map<String, List<BuyLargessVO>> queryBuylargessVOsByActIDs(
			String[] cmarketactids, String whereCon) throws BusinessException;
}
