package nc.pubimpl.so.tranmatrel.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * �������������ϣ�ƥ�䵥�����������Ϲ�ϵ���Ž��
 * 
 * @since 6.0
 * @version 2011-4-19 ����10:25:32
 * @author ף����
 */
public class TranMatRelMatchResultRule {
  /**
   * ƥ����Ӧ�Ľ��
   * 
   * @param results
   * @param paravos
   * @return
   */
  public Map<Integer, TranMatRelParaVO> match(TranMatRelParaVO[] results) {
    if ((null == results) || (results.length == 0)) {
      return new HashMap<Integer, TranMatRelParaVO>();
    }
    return this.processMatch(results);
  }

  /**
   * ƥ���ѯ���
   * 
   * @param matchparas
   * @param matchvos
   */
  private Map<Integer, TranMatRelParaVO> processMatch(TranMatRelParaVO[] results) {
    Map<Integer, TranMatRelParaVO> map =
        new HashMap<Integer, TranMatRelParaVO>();
    Set<Integer> tempSet = new HashSet<Integer>();
    for (TranMatRelParaVO vo : results) {
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
