package nc.itf.so.mbuylagress;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.uif2.LoginContext;

public interface IBuyLargessMaintain {

  /**
   * ɾ��VO
   * 
   * @param todelVO
   * @throws BusinessException
   */
  void deleteBuylargess(BuyLargessVO todelVO) throws BusinessException;

  /**
   * ����VO
   * 
   * @param newVO
   * @return
   * @throws BusinessException
   */
  BuyLargessVO insertBuylargess(BuyLargessVO newVO) throws BusinessException;

  /**
   * ����where������ѯ��������
   * 
   * @param where
   * @return
   * @throws Exception
   */
  BuyLargessVO[] queryBuylargess(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * �������Ϸ����ѯ��Ӧ��������������λ�Ƿ�Ψһ����Ψһ���ظü�����λ�����򷵻�null
   * 
   * @param materialclass
   * @param SO_79
   * @return
   * @throws BusinessException
   */
  String queryMaterialClassMeas(String materialclass) throws BusinessException;

  Object[] queryTariffDef(LoginContext context) throws BusinessException;

  /**
   * �޸�VO
   * 
   * @param updateVO
   * @return
   * @throws BusinessException
   */
  BuyLargessVO updateBuylargess(BuyLargessVO updateVO) throws BusinessException;

}
