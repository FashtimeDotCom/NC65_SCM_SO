/**
 * 
 */
package nc.itf.so.m30.msg;

import nc.vo.so.m30.entity.SaleOrderVO;


/**
 * @author  wangzym
 * @version 2017��7��26�� ����11:03:21
 */
public interface ISend2Gf {
	public void  process(SaleOrderVO[] vos);

}
