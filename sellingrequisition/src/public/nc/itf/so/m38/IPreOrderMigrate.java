package nc.itf.so.m38;

import nc.vo.pub.BusinessException;

/**
 * ����Ԥ����Ǩ����������������Ԥ����
 * @author liylr
 */
public interface IPreOrderMigrate {
	/**
	 * Ԥ����Ǩ��
	 * @throws BusinessException
	 */
	public void migratePreOrder() throws BusinessException;
}
