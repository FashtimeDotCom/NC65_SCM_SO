package nc.pubimpl.so.custmatrel.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * ƥ��ͻ������Ϲ�ϵ���Ž��
 * 
 * @since 6.0
 * @version 2011-4-19 ����10:25:32
 * @author ף����
 */
public class CustMatRelMatchResultRule {
  /**
   * ƥ����Ӧ�Ľ��
   * 
   * @param results
   * @param paravos
   * @return
   */
  public Map<Integer, CustMatRelParaVO> match(CustMatRelParaVO[] results) {
    if ((null == results) || (results.length == 0)) {
      return new HashMap<Integer, CustMatRelParaVO>();
    }
    return this.processMatch(results);
  }

  /**
   * ƥ���ѯ���
   * 
   * @param matchparas
   * @param matchvos
   */
  private Map<Integer, CustMatRelParaVO> processMatch(CustMatRelParaVO[] results) {
    Map<Integer, CustMatRelParaVO> map =
        new HashMap<Integer, CustMatRelParaVO>();
    Set<Integer> tempSet = new HashSet<Integer>();
    for (CustMatRelParaVO vo : results) {
      Integer curindex = vo.getParaindex();
      if (tempSet.size() == 0 || !tempSet.contains(curindex)) {
        tempSet.add(curindex);
        map.put(curindex, vo);
      }
      else if(tempSet.contains(curindex) && (map.get(curindex).getCprioritycode().equals(vo.getCprioritycode()))){
        //��һ�м�¼ƥ�䵽������ϵ��ʱ�� ����ѡ�񲻰����Ĺ�ϵ ����A�������ֹ����  ��A�µ�a���ǲ����� ����ƥ�䲻������ϵ
        if(vo.getExclude()==UFBoolean.TRUE){
          tempSet.add(curindex);
          map.put(curindex, vo);
        }
      }
    }
    return map;
  }
}
