package nc.vo.so.mbuylargess.match;

import java.io.Serializable;

import nc.vo.so.mbuylargess.view.BuyLargessMatchViewVO;
import nc.vo.so.mbuylargess.view.BuyLargessShowViewVO;

/**
 * ƥ���������õĽ�����ֱ�����ˣ�
 * (1)��������չʾ��ͼ,������Ҫչʾƥ�䵽���������ߵĳ���
 * (2)����ƥ������ͼ,������Ҫƥ�䵽�����������¼�ĳ���
 * 
 * @since 6.1
 * @version 2012-10-30 17:46:13
 * @author ��ӱ�
 */
public class BuyLargessMatchResult implements Serializable {

  private static final long serialVersionUID = -3889917601872934280L;

  private BuyLargessMatchViewVO[] matchviewvos;

  private BuyLargessShowViewVO[] showviewvos;

  /**
   * ʹ������ƥ������ʼ���Ĺ�����
   * 
   * @param matchviews
   */
  public BuyLargessMatchResult(BuyLargessMatchViewVO[] matchviews) {
    this.matchviewvos = matchviews;
  }

  /**
   * ʹ������չʾ��ͼ��ʼ���Ĺ�����
   * 
   * @param showviewvos
   */
  public BuyLargessMatchResult(BuyLargessShowViewVO[] showviewvos) {
    this.showviewvos = showviewvos;
  }

  /**
   * ��������ƥ������ͼ
   * 
   * @return BuyLargessMatchViewVO
   */
  public BuyLargessMatchViewVO[] getBuyLargessMatchViews() {
    return this.matchviewvos;
  }

  /**
   * ������������չʾ��ͼ
   * 
   * @return BuyLargessShowViewVO
   */
  public BuyLargessShowViewVO[] getBuyLargessShowViews() {
    return this.showviewvos;
  }
}
