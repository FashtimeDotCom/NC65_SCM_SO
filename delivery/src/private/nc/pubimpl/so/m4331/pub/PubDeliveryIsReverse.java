package nc.pubimpl.so.m4331.pub;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryBVO;

/**
 * ������Դid��÷������Ƿ�����Ԥ��
 * 
 * @since 6.0
 * @version 2011-1-26 ����04:51:13
 * @author ף����
 */
public class PubDeliveryIsReverse {
  public Map<String, UFBoolean> queryReverseFlag(String[] bids) {
    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(" where " + DeliveryBVO.CSRCBID, bids);
    // ����id��ѯVO
    VOQuery<DeliveryBVO> query = new VOQuery<DeliveryBVO>(DeliveryBVO.class);
    DeliveryBVO[] bvos = query.queryWithWhereKeyWord(wheresql.toString(), null);
    Map<String, UFBoolean> map = this.getReverseFlag(bvos, bids);
    return map;
  }

  /*
   * ������Դid���жϷ������Ƿ�����Ԥ�� 
   * @param bvo
   * @param bids
   * @return
   */
  private Map<String, UFBoolean> getReverseFlag(DeliveryBVO[] bvos,
      String[] bids) {
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    for (String bid : bids) {
      // �п�����Դ����û���������ε��� ���Գ�ʼ��Ĭ��ֵΪû������Ԥ��
      map.put(bid, UFBoolean.FALSE);
      for (DeliveryBVO bvo : bvos) {
        String srcbid = bvo.getCsrcbid();
        if (PubAppTool.isEqual(bid, srcbid)) {
          UFDouble reqNum = bvo.getNreqrsnum();
          boolean expr1 = MathTool.compareTo(UFDouble.ZERO_DBL, reqNum) != 0;
          boolean expr2 = map.get(bid).booleanValue();
          // ���û����Ԥ��ѭ����һ��
          if (!expr1) {
            continue;
          }
          // �������Ԥ��������Դ����id��Ӧ���Ƿ�����Ԥ���ı�־λfalse ����ı�־
          if (!expr2) {
            map.put(bid, UFBoolean.TRUE);
          }
        }
      }
    }
    return map;
  }
}
