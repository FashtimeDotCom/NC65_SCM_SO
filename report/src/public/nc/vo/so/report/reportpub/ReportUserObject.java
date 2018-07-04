package nc.vo.so.report.reportpub;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import nc.vo.pub.query.ConditionVO;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ��ѯģ����Ϣ������
 * 
 * @since 6.3
 * @version 2012-09-04 15:25:02
 * @author ������
 */
public class ReportUserObject implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1705606554457774819L;

  private Set<String> summarykeys;

  private IQueryScheme qryscheme;

  /**
   * ���췽��
   * 
   * @param qryscheme
   */
  public ReportUserObject(IQueryScheme qryscheme) {
    this.qryscheme = qryscheme;
  }

  /**
   * ��û�������
   * 
   * @return summkeys
   */
  public Set<String> getSummaryKeys() {
    if (null == this.summarykeys) {
      this.summarykeys = this.getSummaryKeyFromScheme();
    }
    return this.summarykeys;
  }

  /**
   * ��ò�ѯ����
   * 
   * @return qryscheme
   */
  public IQueryScheme getIQueryScheme() {
    return this.qryscheme;
  }

  private Set<String> getSummaryKeyFromScheme() {
    Set<String> groupset = new HashSet<String>();
    ConditionVO[] conds =
        (ConditionVO[]) this.qryscheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);
    // 1.��������
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals(ReportContext.SUMMARYKEY)) {
        String groupstr = cond.getValue();
        groupstr = groupstr.replace("(", "");
        groupstr = groupstr.replace(")", "");
        groupstr = groupstr.replace("'", "");
        String[] keys = groupstr.substring(0, groupstr.length()).split(",");
        for (String key : keys) {
          groupset.add(key);
        }
      }
    }
    return groupset;
  }
}
