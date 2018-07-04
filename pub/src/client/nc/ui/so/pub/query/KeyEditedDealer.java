package nc.ui.so.pub.query;

import java.util.HashSet;
import java.util.Set;

import nc.ui.pubapp.uif2app.query2.totalvo.IQueryConditionVODealer;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.QueryConditionVO;

/**
 * ��ѯģ���ֶα༭��
 * ��һ�� setEditKey �Ϳ���
 * 
 * @since 6.0
 * @version 2011-12-31 ����10:26:57
 * @author ô��
 */
public class KeyEditedDealer implements IQueryConditionVODealer {

  private Set<String> keyset = new HashSet<String>();

  @Override
  public QueryConditionVO[] deal(QueryConditionVO[] conds) {
    for (QueryConditionVO qcvo : conds) {
      if (this.keyset.contains(qcvo.getFieldCode())) {
        qcvo.setIfImmobility(UFBoolean.TRUE);
      }
    }
    return conds;
  }

  public void setEditKey(String key) {
    this.keyset.add(key);
  }
}
