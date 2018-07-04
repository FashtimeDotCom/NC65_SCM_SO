package nc.pubitf.so.m33.so.m30;

import nc.vo.so.m33.m32.entity.SquareInvViewVO;

/**
 * ���۷�Ʊ���㵥Ϊ�����ر��ṩ����ӿ�
 * 
 * @since 6.1
 * @version 2012-11-29 11:10:10
 * @author ��ӱ�
 */
public interface ISquare32QryFor30SqEnd {

  /**
   * �����������������ݶ�����ID��ѯ���۶������η�Ʊ���Ƿ����ر�
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param ordBids
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-8-31 ����02:06:47
   */
  RetVOFor30[] querySqEndByOrdBID(String[] ordBids);

  /**
   * �����������������ݶ�����ID��ѯ���۶������ν��㵥�����۷�Ʊ��Ϣ
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param ordBids
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-8-31 ����02:06:44
   */
  SquareInvViewVO[] queryViewVOByOrdBID(String[] ordBids);

}
