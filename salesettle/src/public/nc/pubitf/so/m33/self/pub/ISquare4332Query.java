package nc.pubitf.so.m33.self.pub;

import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;

public interface ISquare4332Query {

  /**
   * ����������������ѯ�������۷�Ʊ�ۼƴ�Ӧ������
   * <p>
   * <b>����˵��</b>
   * 
   * @param outBids -- ���۳��ⵥ��id
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-1 ����11:48:42
   */
  Map<String, UFDouble> queryARNumBy4CBID(String[] outBids);

  /**
   * �������۷�Ʊ����id��ѯ �Ѿ��ɱ���������۷�Ʊ����vo
   * 
   * @param invBids
   *          -- ���۷�Ʊ����id
   * @return �Ѿ��ɱ���������۷�Ʊ����vo
   */
  SquareInvViewVO[] queryCostSquareInvVOBy32BID(String[] invBids);

}
