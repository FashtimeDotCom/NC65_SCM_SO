package nc.pubimpl.so.m4331.qc.mc001.rule;

import java.util.HashSet;
import java.util.Set;

import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

import nc.bs.so.m4331.quality.DeleteDeliverycheckBP;
import nc.bs.so.m4331.quality.QueryDeliveryCheckBP;

/**
 * ������ķ��������±���ʱ��ɾ���Ѿ������������
 * 
 * @since 6.0
 * @version 2010-12-28 ����02:09:11
 * @author ף����
 */
public class DeleteQualityDataRule {

  /**
   * ���±��� ɾ���Ѿ��������Ϣ
   * 
   * @param views
   */
  public void deleteQualityData(DeliveryViewVO[] views) {
    // ���淢��������id
    Set<String> bidSet = new HashSet<String>();
    for (DeliveryViewVO view : views) {
      bidSet.add(view.getItem().getCdeliverybid());
    }
    String[] bids = new String[bidSet.size()];
    bids = bidSet.toArray(bids);
    // ����ʼ���Ϣvo
    DeliveryCheckVO[] vos = this.getDeliverycheckVO(bids);
    if (null != vos) {
      // ɾ���ʼ���Ϣ
      DeleteDeliverycheckBP delete = new DeleteDeliverycheckBP();
      delete.delete(vos, true);
    }
  }

  /*
   * ���ݷ���������id ��ò�ѯ�ʼ���Ϣ��sql 
   * @param bids
   * @return
   */
  private DeliveryCheckVO[] getDeliverycheckVO(String[] bids) {
    StringBuffer sql = new StringBuffer();
    SqlBuilder sqlbuilder = new SqlBuilder();
    sql.append("select distinct(");
    sql.append(DeliveryCheckVO.CDELIVERYCID);
    sql.append(") from so_delivery_check where dr =0 and ");
    sqlbuilder.append(DeliveryCheckVO.CDELIVERYBID, bids);
    sql.append(sqlbuilder.toString());
    QueryDeliveryCheckBP query = new QueryDeliveryCheckBP();
    return query.queryDeliveryCheckVO(sql.toString());
  }
}
