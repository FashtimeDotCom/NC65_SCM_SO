package nc.pubitf.so.m33.so.m30;

import java.util.Map;

import nc.vo.so.m30.balend.enumeration.VirtualBalType;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

/**
 * ���۳�����㵥Ϊ�����ر��ṩ����ӿ�
 * 
 * @since 6.1
 * @version 2012-11-29 11:11:05
 * @author ��ӱ�
 */
public interface ISquare4CQryFor30SqEnd {

  /**
   * ����������������Ӧ�ĳ��ⵥ��Ȩ����������� VirtualBalType ����
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param ordBids --- ���۶�������id
   * @return
   *         <p>
   *         // ���ⵥ�ѽ��� public static final VirtualBalType BALED =
   *         MDEnum.valueOf(VirtualBalType.class, Integer.valueOf(0));
   *         <p>
   *         // ���ⵥ�����(�����۳��ⵥ���η�Ʊ�Ѿ�����������۳��ⵥ��������Գ��Ѿ��޷��ٽ���)
   *         <p>
   *         public static final VirtualBalType HALFBAL =
   *         MDEnum.valueOf(VirtualBalType.class, Integer.valueOf(1));
   *         <p>
   *         // ���ⵥδ���� public static final VirtualBalType NOTBAL =
   *         MDEnum.valueOf(VirtualBalType.class, Integer.valueOf(2));
   *         <p>
   * @author zhangcheng
   * @time 2010-9-13 ����10:39:24
   */
  Map<String, VirtualBalType> query4CVirtualBalType(String[] ordBids);

  /**
   * ���ݶ�����ID��ѯ���۶��������Ѿ��ݹ���δ��ɻس�������۳�������㵥��Ϣ
   * 
   * @param ordBids
   * @return ���������ͼVO
   */
  SquareOutViewVO[] queryETViewVOByOrdBIDForOrderEnd(String[] ordBids);

  /**
   * ���ݶ�����ID��ѯ���۶��������Ѿ�������Ʒ�跽δ��ȫ�����س�����۳�������㵥
   * 
   * @param ordBids
   * @return ���������ͼVO
   */
  SquareOutViewVO[] queryREGViewVOByOrdBIDForOrderEnd(String[] ordBids);

  /**
   * �����������������ݶ�����ID��ѯ���۶������γ��ⵥ���Ƿ����ر�
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param ordBids
   * @return RetVOFor30
   *         <p>
   * @author zhangcheng
   * @time 2010-8-31 ����02:08:39
   */
  RetVOFor30[] querySqEndByOrdBID(String[] ordBids);

  /**
   * �����������������ݶ�����ID��ѯ���۶������ν��㵥�����۳��ⵥ��Ϣ
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
   * @time 2010-8-31 ����02:07:16
   */
  SquareOutViewVO[] queryViewVOByOrdBID(String[] ordBids);
}
