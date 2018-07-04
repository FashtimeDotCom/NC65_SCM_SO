package nc.pubimpl.so.deptmatrel.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * ���š�ҵ��Ա������֮���ϵ���壺ƥ�䲿�š�ҵ�������Ϲ�ϵ���Ž��
 * 
 * @since 6.0
 * @version 2011-4-19 ����10:25:32
 * @author ף����
 */
public class DeptMatRelMatchResultRule {
  /**
   * ƥ����Ӧ�Ľ��
   * 
   * @param results
   * @param paravos
   * @return
   */
  public Map<Integer, DeptMatRelParaVO> match(DeptMatRelParaVO[] results) {
    if ((null == results) || (results.length == 0)) {
      return new HashMap<Integer, DeptMatRelParaVO>();
    }
    return this.processMatch(results);
  }

  /**
   * ƥ���ѯ���
   * 
   * @param matchparas
   * @param matchvos
   */
  private Map<Integer, DeptMatRelParaVO> processMatch(DeptMatRelParaVO[] results) {
    Map<Integer, DeptMatRelParaVO> map =
        new HashMap<Integer, DeptMatRelParaVO>();
    Set<Integer> tempSet = new HashSet<Integer>();
    for (DeptMatRelParaVO vo : results) {
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
