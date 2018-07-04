package nc.bs.so.m33.biz.pub.cancelsquare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareActionFactory;
import nc.md.model.impl.MDEnum;
import nc.vo.pub.SuperVO;
import nc.vo.so.m33.enumeration.SquareType;

public abstract class AbstractCancelSquareDetail<T extends SuperVO> {

  private ICancelSquareAction<T> icsAction;

  public void cancelSquare(T[] vos, String squareType) {

    // 1.��������ϸ����,����֮����������
    Map<SquareType, List<T>> m_sqDetailVo =
        this.splitVOBySquareType(vos, squareType);

    // 2.���㶯�����Թ���
    ICancelSquareActionFactory<T> actFactory =
        this.initCancelSquareActionFactory();

    // 3.���ݽ��㷽ʽ����ѭ�����þ����ȡ�����㷽��
    this.cancelSquare(m_sqDetailVo, actFactory);

  }

  public final Map<SquareType, List<T>> splitVOBySquareType(T[] sqdvos,
      String squareType) {
    if (sqdvos == null) {
      return null;
    }
    Map<SquareType, List<T>> map = new HashMap<SquareType, List<T>>();
    SquareType key = null;
    List<T> list = null;
    for (T sdvo : sqdvos) {
      key =
          MDEnum.valueOf(SquareType.class, sdvo.getAttributeValue(squareType));
      list = map.get(key);
      if (list == null) {
        list = new ArrayList<T>();
        map.put(key, list);
      }
      list.add(sdvo);
    }
    return map;

  }

  protected abstract ICancelSquareActionFactory<T> initCancelSquareActionFactory();

  private void cancelSquare(Map<SquareType, List<T>> m_sqDetailVo,
      ICancelSquareActionFactory<T> actFactory) {
    // ���ݽ��㷽ʽ����ѭ�����þ����ȡ�����㷽��
    for (Entry<SquareType, List<T>> entry : m_sqDetailVo.entrySet()) {
      this.icsAction = actFactory.createCancelSquareAction(entry.getKey());
      List<T> list = entry.getValue();
      this.icsAction.cancelSquare(list);
    }
  }

}
