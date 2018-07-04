package nc.pubitf.so.m33.self.pub;

import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

/**
 * ���۳�������㵥�����㵥�����ṩ�Ĳ�ѯ����
 * 
 */
public interface ISquare434CQuery {

  /**
   * ����������������ѯ����ǩ�տ�Ʊ�˻س��ⵥ�ۼƴ�ȷ��Ӧ������
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
   * ����������������ѯ����ǩ�տ�Ʊ�˻س��ⵥ�ۼƴ��س�Ӧ������
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
   * ���ݳ��ⵥ����id��ѯ �Ѿ������ݹ�Ӧ�յĳ��ⵥ����id
   * 
   * @param outBids -- ���ⵥ����id
   * @return �Ѿ������ݹ�Ӧ�յĳ��ⵥ����id
   */
  String[] queryETIncomeBidBy4CBID(String[] outBids);

  /**
   * ���ݳ��ⵥ����id��ѯ �Ѿ������ݹ�Ӧ�յĳ��ⵥ����id
   * 
   * @param outBids -- ���ⵥ����id
   * @return �Ѿ������ݹ�Ӧ�յ����۳�����㵥
   */
  Map<String, SquareOutDetailVO> queryETIncomeDvosBy4CBID(String[] outBids);

  /**
   * ���ݳ��ⵥ����id��ѯ �Ѿ������ݹ�Ӧ�ջ��߷�����Ʒ�ĳ��ⵥ�����㵥
   * 
   * @param outBids -- ���ⵥ����id
   * @return �Ѿ������ݹ�Ӧ�յĳ��ⵥ����id
   */
  SquareOutViewVO[] queryETIncomeREGCostBidBy4CBID(String[] outBids);

  /**
   * ���ݳ��ⵥ����id��ѯ �Ѿ������ݹ�Ӧ�յĳ��ⵥ����id
   * 
   * @param outBids -- ���ⵥ����id
   * @return �Ѿ������ݹ�Ӧ�յĳ��ⵥ����id
   */
  String[] queryREGCostBidBy4CBID(String[] outBids);

  /**
   * �������۳��ⵥbid��ѯ���۳�����㵥
   * 
   * @param bidValues -- ���۳��ⵥ����id
   * @return SquareOutDetailVO[]
   */
  SquareOutDetailVO[] querySquareOutDetailVOBy4CBID(String[] bidValues);

  /**
   * �������۳�����㵥PK��ѯ���۳�����㵥
   * 
   * @param OutDetailPKs
   * @return SquareOutDetailVO[]
   */
  SquareOutDetailVO[] querySquareOutDetailVOByPK(String[] outDetailPKs);

  /**
   * �������۳��ⵥbid��ѯ���۳�������㵥
   * 
   * @param bidValues -- ���۳��ⵥ����id
   * @return SquareOutDetailVO[]
   */
  SquareOutViewVO[] querySquareOutViewVOBy4CBID(String[] outbids);

}
