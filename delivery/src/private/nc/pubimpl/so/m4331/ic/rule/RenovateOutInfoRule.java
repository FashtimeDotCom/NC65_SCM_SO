package nc.pubimpl.so.m4331.ic.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.so.m4331.assist.state.DeliveryStateMachine;
import nc.bs.so.m4331.assist.state.row.RowCloseState;
import nc.bs.so.m4331.assist.state.row.RowOpenState;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.pubimpl.so.m4331.pub.RewriteSrcUtil;
import nc.pubimpl.so.m4331.pub.RewriteVOUtil;
import nc.pubimpl.so.m4331.pub.RewriteValueUtil;
import nc.pubimpl.so.m4331.pub.StateTool;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * ���·�����������Ϣ ����״̬���ۼƳ�������
 * 
 * @since 6.0
 * @version 2011-2-18 ����09:32:48
 * @author ף����
 */
public class RenovateOutInfoRule {
  // ����Ҫ�رյķ�����
  private Set<DeliveryViewVO> closeSet;

  // ����Ҫ�򿪵ķ�����
  private Set<DeliveryViewVO> openSet;

  // ���淢������д��Դ���ݵı仯��
  private Map<String, UFDouble> reSrcValueMap;

  // �������λ�д��������ĳ���״̬
  private Map<String, UFBoolean> stateMap;

  // �������λ�д�������ı仯��
  private Map<String, UFDouble> valueMap;

  // ������Ϣ������
  private RewriteValueUtil valueUtil;

  // ��дvo������
  private RewriteVOUtil voutil;

  public RenovateOutInfoRule(RewriteVOUtil util,RewriteValueUtil valueutil) {
    this.voutil = util;
    if(valueutil==null){
      this.valueUtil = new RewriteValueUtil();
    }
    else{
      this.valueUtil = valueutil;
    }
    this.initMap();
  }

  public void renovateState() {
    // �����ʼ���Ӧ��Ϣ
    this.manageDeliverycheckInfo();
    // ���·�����������Ӧ��Ϣ
    this.manageDeliveryInfo();
    // ��д��Դ����
    this.rewriteSrc();
    // ���µ����ݿ�
    //this.updateToDB();
    // �����ʼ���Ϣ�����ݿ�
    this.updateQualityInfoToDB();
  }

  /**
   * �����ʼ���Ϣ����ʼ���Ϣ����Ӧ�ķ���������ĳ���״̬
   * 
   * @param entry
   * @param view
   * @return
   */
  private UFBoolean getViewStateByQualityInfo(Entry<String, UFBoolean> entry,
      DeliveryViewVO view) {
    String bid = entry.getKey();
    UFBoolean state = entry.getValue();
    UFBoolean bcloseflag = UFBoolean.FALSE;
    // ֻҪ�д򿪵��ʼ���Ϣ ���ʼ���Ϣ��Ӧ�ķ����������Ϊ��
    if (!state.booleanValue()
        || !this.voutil.getOtherVOState().get(bid).booleanValue()) {
      bcloseflag = UFBoolean.FALSE;
    }
    else if (!this.voutil.getOtherVOState().containsKey(bid)) {
      // ��д�ʼ���Ϣ�ĸ����͸��ݷ���������id��ѯ�������ʼ���Ϣ������һ��
      bcloseflag = state;
    }
    else {
      bcloseflag = UFBoolean.TRUE;
    }
    this.setReNumByQualityInfo(view, bcloseflag);
    return bcloseflag;
  }

  /*
   * ��ʼ����ʼ��map����
   */
  private void initMap() {
    // ��ʼ���ʼ���Ϣid����Ӧ�ı仯��
    this.initQualityValueMap();
    StateTool tool = new StateTool(this.voutil, this.valueUtil);
    DeliveryCheckVO[] vos = this.voutil.getRewriteCheckVO();
    DeliveryViewVO[] views = this.voutil.getRewriteViewVO();
    this.stateMap = tool.getState(vos, views);
    this.reSrcValueMap = tool.getValueForRewriteSrc(vos, views);
    this.openSet = new HashSet<DeliveryViewVO>();
    this.closeSet = new HashSet<DeliveryViewVO>();
  }

  /*
   * ��ʼ���ʼ���Ϣid����Ӧ�ı仯��
   */
  private void initQualityValueMap() {
    DeliveryCheckVO[] vos = this.voutil.getRewriteCheckVO();
    if (null == vos) {
      return;
    }
    this.valueMap = new HashMap<String, UFDouble>();
    for (DeliveryCheckVO vo : vos) {
      String bid = vo.getCdeliverybid();
      if ((this.valueMap.size() > 0) && this.valueMap.containsKey(bid)) {
        continue;
      }
      DeliveryViewVO view = this.voutil.getRewriteViewVOOnCheck().get(bid);
      String srctype = view.getItem().getVsrctype();
      UFDouble totalRenum = new UFDouble(0.0);
      for (DeliveryCheckVO newvo : vos) {
        String newbid = newvo.getCdeliverybid();
        if (newbid.equals(bid)) {
          String newcid = newvo.getCdeliverycid();
          UFDouble newReNum = this.valueUtil.getRewriteNum(newcid, srctype);
          totalRenum = MathTool.add(totalRenum, newReNum);
        }
      }
      this.valueMap.put(bid, totalRenum);
    }
  }

  /*
   * ���������ʼ���Ϣ
   * ���³���״̬�͸����ۼƳ������� 
   */
  private void manageDeliverycheckInfo() {
    if (null == this.voutil.getRewriteCheckVO()) {
      return;
    }
    // ���³���״̬
    this.updateQualityInfo();
  }

  private void manageDeliveryInfo() {
    DeliveryViewVO[] views = this.voutil.getRewriteViewVO();
    if (null == views) {
      return;
    }
    this.updateViewsInfo();
  }

  /**
   * ��д��Դ����
   */
  private void rewriteSrc() {
    RewriteSrcUtil rewrite = new RewriteSrcUtil(this.voutil);
    rewrite.rewriteSrc(this.reSrcValueMap);
  }

  /*
   * �����ʼ���Ϣ ���û�д��Դ���ݵı仯��
   * @param view 
   * @param state �ʼ���Ϣ��Ӧ�ķ������������µĳ���״̬
   */
  private void setReNumByQualityInfo(DeliveryViewVO view, UFBoolean state) {
    String bid = view.getItem().getCdeliverybid();
    UFDouble num = view.getItem().getNnum();
    UFDouble totalReNum = this.valueMap.get(bid);
    UFDouble oldTotalOutNum = view.getItem().getNtotaloutnum();
    UFDouble newTotalOutNum = MathTool.add(oldTotalOutNum, totalReNum);
    UFBoolean oldState = view.getItem().getBoutendflag();
    boolean expr1 = oldState.booleanValue();
    boolean expr2 = state.booleanValue();
    UFDouble reValueForSrc = UFDouble.ZERO_DBL;
    if (!expr1 && !expr2) {
      // �ı�ǰ״̬�Ǵ򿪣��ı��״̬Ϊ�� ����Ҫ��д��Դ����
      reValueForSrc = UFDouble.ZERO_DBL;
    }
    else if (!expr1 && expr2) {
      // �ı�״̬�Ǵ�,�ı��״̬�ǹر�
      reValueForSrc = MathTool.sub(newTotalOutNum, num);
    }
    else if (expr1 && !expr2) {
      // �ı�ǰ�ǹر� �ı��Ϊ��
      reValueForSrc = MathTool.sub(num, oldTotalOutNum);
    }
    else if (expr1 && expr2) {
      reValueForSrc = totalReNum;
    }
    // ���÷������ӱ��ۼƳ�������
    view.getItem().setNtotaloutnum(newTotalOutNum);
    this.reSrcValueMap.put(bid, reValueForSrc);
  }

  private void updateDeliveryInfoToDB() {
    DeliveryStateMachine bo = new DeliveryStateMachine();
    if (this.openSet.size() > 0) {
      DeliveryViewVO[] openviews = new DeliveryViewVO[this.openSet.size()];
      openviews = this.openSet.toArray(openviews);
      bo.setState(new RowOpenState(), openviews);
    }
    if (this.closeSet.size() > 0) {
      DeliveryViewVO[] closeviews = new DeliveryViewVO[this.closeSet.size()];
      closeviews = this.closeSet.toArray(closeviews);
      bo.setState(new RowCloseState(), closeviews);
    }
  }

  /*
   * �����ʼ���Ϣ 
   */
  private void updateQualityInfo() {
    // �����ʼ���Ϣ״̬���� ����Ӧ�ķ���������ĳ���״̬
    Map<String, UFBoolean> outStateMap = new HashMap<String, UFBoolean>();
    // Ĭ����ͬ����������id��Ӧ���ʼ���ϢҪ���µ�״̬��ͬ
    boolean isSame = true;
    DeliveryCheckVO[] vos = this.voutil.getRewriteCheckVO();
    for (DeliveryCheckVO vo : vos) {
      String bid = vo.getCdeliverybid();
      if ((outStateMap.size() > 0) && outStateMap.containsKey(bid)) {
        continue;
      }
      UFBoolean state = this.stateMap.get(bid);
      for (DeliveryCheckVO newvo : vos) {
        String newcid = newvo.getCdeliverycid();
        String newbid = newvo.getCdeliverybid();
        if (newcid.equals(vo.getCdeliverycid()) || !newbid.equals(bid)) {
          continue;
        }
        UFBoolean state1 = this.stateMap.get(newbid);
        // ��ͬ����id���ʼ쵥�ݳ���״̬�������Ĳ�ͬ��Ϊfalse
        if (!state1.equals(state)) {
          outStateMap.put(bid, UFBoolean.FALSE);
          isSame = false;
        }
      }
      if (!isSame) {
        // ��ͬ����id���ʼ쵥�ݳ���״̬��ͬ���򷢻��������Ϊ��״̬
        outStateMap.put(bid, UFBoolean.FALSE);
        continue;
      }
      outStateMap.put(bid, state);
    }
    this.updateViewByQuality(outStateMap);
  }

  private void updateQualityInfoToDB() {
    DeliveryCheckVO[] vos = this.voutil.getRewriteCheckVO();
    if (null == vos) {
      return;
    }
    String[] names =
        new String[] {
          DeliveryCheckVO.NTOTALOUTNUM, DeliveryCheckVO.BOUTENDFLAG,
          DeliveryCheckVO.NTOTALNOTOUTNUM
        };
    VOUpdate<DeliveryCheckVO> bo = new VOUpdate<DeliveryCheckVO>();
    bo.update(vos, names);
  }

  public void updateToDB() {
    // �����ʼ���Ϣ�����ݿ�
   // this.updateQualityInfoToDB();
    // ���·�������Ϣ�����ݿ�
    this.updateDeliveryInfoToDB();
  }

  /*
   * �����ʼ���Ϣ��������Ӧ�ķ����������ۼƳ��������ͳ���״̬
   * @param value
   * @param i
   * @param bclose
   * @param bopen
   */
  private void updateViewByQuality(Map<String, UFBoolean> outStateMap) {
    Map<String, DeliveryViewVO> viewMap = this.voutil.getRewriteViewVOOnCheck();
    Set<Entry<String, UFBoolean>> entrys = outStateMap.entrySet();
    for (Entry<String, UFBoolean> entry : entrys) {
      String bid = entry.getKey();
      DeliveryViewVO view = viewMap.get(bid);
    //  this.getViewStateByQualityInfo(entry, view);
      UFBoolean bcloseflag = this.getViewStateByQualityInfo(entry, view);
      if (!bcloseflag.booleanValue()) {
        view.getItem().setBoutendflag(UFBoolean.FALSE);
        this.openSet.add(view);
        continue;
      }
      view.getItem().setBoutendflag(UFBoolean.TRUE);
      this.closeSet.add(view);
    }
  }

  private void updateViewsInfo() {
    DeliveryViewVO[] views = this.voutil.getRewriteViewVO();
    for (DeliveryViewVO view : views) {
      UFBoolean state = this.stateMap.get(view.getItem().getCdeliverybid());
      if (!state.booleanValue()) {
        this.openSet.add(view);
        continue;
      }
      this.closeSet.add(view);
    }
  }
}
