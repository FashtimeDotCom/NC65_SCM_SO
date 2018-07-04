package nc.pubitf.so.m33.self.pub;

import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;

public interface ISquare4353Query {

  /**
   * ����������������ѯ����;���ۼƴ�ȷ��Ӧ������
   * <p>
   * <b>����˵��</b>
   * 
   * @param outBids -- ���۳��ⵥ��id
   * @return <���ⵥ��id,[0]�ۼ�Ӧ������,[1]�ۼ�Ӧ�պ�˰���,[2]�ۼ�Ӧ����˰���>
   *         <p>
   * @author zhangcheng
   * @time 2010-9-1 ����11:48:42
   */
  Map<String, UFDouble[]> queryARNumBy4CBID(String[] outBids);

  /**
   * ����������������ѯ����;���ۼƴ��س�Ӧ������
   * <p>
   * <b>����˵��</b>
   * 
   * @param outBids -- ���۳��ⵥ��id
   * @return <���ⵥ��id,[0]�ۼ�Ӧ������,[1]�ۼ�Ӧ�պ�˰���,[2]�ۼ�Ӧ����˰���>
   *         <p>
   * @author zhangcheng
   * @time 2010-9-1 ����11:48:42
   */
  Map<String, UFDouble[]> queryARRushNumBy4CBID(String[] outBids);

  /**
   * ������������������;�𵥱���id��ѯ�Ѿ������س�Ӧ�յ�;����㵥
   * <p>
   * <b>����˵��</b>
   * 
   * @param wasBids -- ;�𵥱���id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-21 ����04:02:48
   */
  SquareWasDetailVO[] querySquareWasDetailVOByBIDForETRushSquare(
      String[] wasBids);

  /**
   * ������������������;�𵥱���id��ѯδ���������;����㵥
   * <p>
   * <b>����˵��</b>
   * 
   * @param wasBids -- ;�𵥱���id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-21 ����04:02:48
   */
  SquareWasViewVO[] querySquareWasDetailVOByBIDForNoSquare(String[] wasBids);

  /**
   * ������������������;�𵥱���id��ѯ�Ѿ�����������Ʒ�����;����㵥
   * <p>
   * <b>����˵��</b>
   * 
   * @param wasBids -- ;�𵥱���id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-21 ����04:02:48
   */
  SquareWasDetailVO[] querySquareWasDetailVOByBIDForREGSquare(String[] wasBids);

  /**
   * ������������������;�𵥱���id��ѯ�Ѿ����������;����㵥
   * <p>
   * <b>����˵��</b>
   * 
   * @param wasBids -- ;�𵥱���id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-21 ����04:02:48
   */
  SquareWasDetailVO[] querySquareWasDetailVOByBIDForSquare(String[] wasBids);

  /**
   * ������������������;������㵥����id��ѯ;������㵥��ͼvo
   * <p>
   * <b>����˵��</b>
   * 
   * @param bids -- ;�𵥱���id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-21 ����04:02:48
   */
  SquareWasViewVO[] querySquareWasViewVOByBID(String[] bids);

  /**
   * ������������������;�𵥱���id��ѯδ�س�Ӧ�յ�;������㵥��ͼvo
   * <p>
   * <b>����˵��</b>
   * 
   * @param wasBids -- ;�𵥱���id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-21 ����04:02:48
   */
  SquareWasViewVO[] querySquareWasViewVOByBIDForNoETRushSquare(String[] wasBids);

  /**
   * ������������������;�𵥱���id��ѯδ������Ʒ�����;������㵥��ͼvo
   * <p>
   * <b>����˵��</b>
   * 
   * @param wasBids -- ;�𵥱���id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-21 ����04:02:48
   */
  SquareWasViewVO[] querySquareWasViewVOByBIDForNoREGSquare(String[] wasBids);
}
