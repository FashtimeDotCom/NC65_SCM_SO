package nc.impl.so.m38.migrate.vo;

import nc.vo.arap.pub.VOMetaFactory;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.billtype.BilltypeVO;

/**
 * ��Ԥ����Ǩ�ƹ����У�������Ҫ�漰��bd_billtype������Ǩ�ƣ��ڶ�ȡ�Ͳ���bd_billtype������ʱ��
 * Ŀǰû���ֳɵĽӿڿ��Ե��ã�Ҳ����ʹ������VOInsert��VOQuery֮��ķ�ʽȥ����billtype���ݣ�
 * �������ʹ��PreOrderBilltypeVO�̳���BilltypeVO,��ʹ֮����ʹ��VOQuery��VOInsert
 * ����������bd_billtype����
 * @since 6.36
 * @version 2015-05-25
 * @author liylr
 */
public class PreOrderBilltypeVO extends BilltypeVO{
	
	private static final long serialVersionUID = 5365296687429519177L;

	@Override
	public IVOMeta getMetaData() {
	    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("uap.BilltypeVO");
	    return meta;
	  }
}
