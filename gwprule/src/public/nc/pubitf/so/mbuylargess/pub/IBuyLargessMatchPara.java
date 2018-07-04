package nc.pubitf.so.mbuylargess.pub;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * ��������ƥ������ӿ�
 * 
 * @since 6.1
 * @version 2012-10-30 17:57:32
 * @author ��ӱ�
 */
public interface IBuyLargessMatchPara {

  /**
   * ����������֯
   * 
   * @return saleorgid
   */
  String getCsaleorgid();

  /**
   * ��������OID
   * 
   * @return marterialid
   */
  String getCmarterialid();

  /**
   * ����ҵ��λID
   * 
   * @return astunitid
   */
  String getCastunitid();

  /**
   * ���ض����ͻ�
   * 
   * @return customerid
   */
  String getCcustomerid();

  /**
   * ���ر���
   * 
   * @return origcurrencyid
   */
  String getCorigcurrencyid();

  /**
   * ���ص�������
   * 
   * @return billdate
   */
  UFDate getDbilldate();

  /**
   * ��������
   * 
   * @return astnum
   */
  UFDouble getNastnum();
}
