package nc.pubimpl.so.m4331.pub;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

public class RewriteVOUtil {

  // ����viewvo �������λ�д�ı���view�͸����ʼ���Ϣ��ѯ������view
  private Map<String, DeliveryViewVO> allviewMap;

  // ���λ�д������������id
  private String[] bids;
  
  //���λ�д�ʼ��е�����id
  private String[] checkids;

  // �ʼ���Ϣ����Ӧ�ķ���������id
  private String[] bidsByCheckVO;

  // ����viewVO ���ڻ�д����������
  private Map<String, DeliveryViewVO> viewMapOnBackWard;

  // ����viewVo �����ʼ����Ϣ��û�д�������ӱ����Ϣ
  private Map<String, DeliveryViewVO> viewMapOnCheck;

  // ���� ���ڻ�д�������ʼ����Ϣ
  private Map<String, DeliveryCheckVO> voMapOnBackWard;

  private Map<String, UFBoolean> voStateMap;

  public RewriteVOUtil(String[] bids1,String[] checkids) {
    this.bids = bids1;
    this.checkids=checkids;
    this.init();
  }
  public RewriteVOUtil(String[] bids1) {
    this.bids = bids1;
    this.init();
  }

  /**
   * �������Ҫ��д�ķ�����������Ϣ
   * 1.���λ�д�ķ�����������Ϣ
   * 2.�ʼ���Ϣ��д�������������Ϣ
   * 
   * @return
   */
  public DeliveryViewVO[] getAllRewriteViewVO() {
    if (this.allviewMap.size() == 0) {
      return null;
    }
    DeliveryViewVO[] views = new DeliveryViewVO[this.allviewMap.size()];
    return this.allviewMap.values().toArray(views);
  }

  /*
   * ���ݷ���������id��ѯ�ʼ���Ϣ�ĳ���״̬�Ƿ���ͬ�����������λ�д�������ʼ���Ϣ��
   * @return
   */
  public Map<String, UFBoolean> getOtherVOState() {
    this.voStateMap = new HashMap<String, UFBoolean>();
    SqlBuilder where = new SqlBuilder();
    where.append(" and ");
    where.append(DeliveryCheckVO.CDELIVERYBID, this.bidsByCheckVO);
    VOQuery<DeliveryCheckVO> query =
        new VOQuery<DeliveryCheckVO>(DeliveryCheckVO.class);
    DeliveryCheckVO[] vos = query.query(where.toString(), null);
   // Set<String> cidTempSet = new HashSet<String>();
//    String[] cids = this.getRewritCheckIDS();
//    for (String cid : cids) {
//      cidTempSet.add(cid);
//    }
    for (DeliveryCheckVO vo : vos) {
     // String cid = vo.getCdeliverycid();
//      if (cidTempSet.contains(cid)) {
//        continue;
//      }
      String bid = vo.getCdeliverybid();
      if ((this.voStateMap.size() > 0) && this.voStateMap.containsKey(bid)) {
        continue;
      }
      UFBoolean state = vo.getBoutendflag();
      for (DeliveryCheckVO newvo : vos) {
        if (!bid.equals(newvo.getCdeliverybid())) {
          continue;
        }
        UFBoolean state1 = newvo.getBoutendflag();
        if (!PubAppTool.isEqual(state, state1)) {
          this.voStateMap.put(bid, UFBoolean.FALSE);
          break;
        }
      }
      this.voStateMap.put(bid, state);
    }
    return this.voStateMap;
  }

  /**
   * ������ε���Ҫ��д���ʼ�����Ϣ
   * 
   * @return
   */
  public DeliveryCheckVO[] getRewriteCheckVO() {
    if (this.voMapOnBackWard.size() == 0) {
      return null;
    }
    DeliveryCheckVO[] vos = new DeliveryCheckVO[this.voMapOnBackWard.size()];
    return this.voMapOnBackWard.values().toArray(vos);
  }

  /**
   * ������е���Ҫ��д�������ӱ��������Ϣ
   * 
   * @return
   */
  public DeliveryViewVO[] getRewriteViewVO() {
    if (this.viewMapOnBackWard.size() == 0) {
      return null;
    }
    DeliveryViewVO[] views = new DeliveryViewVO[this.viewMapOnBackWard.size()];
    return this.viewMapOnBackWard.values().toArray(views);
  }

  /**
   * ���λ�д�ʼ쵥�ݺ��ʼ쵥����Ҫ��д�ķ�����������Ϣ
   * 
   * @return
   */
  public Map<String, DeliveryViewVO> getRewriteViewVOOnCheck() {
    return this.viewMapOnCheck;
  }

  /*
   * �����Ҫ��д�ʼ���id 
   * @return
   */
  private String[] getRewritCheckIDS() {
//    // �����д�������ӱ��������Ϣ��size==��д�ı���id�ĸ�������ֻ��д����������id
//    if (this.viewMapOnBackWard.size() == this.bids.length) {
//      return null;
//    }
//    // �����д�������ӱ��������ϢΪ�գ������λ�д��id��Ϊ�ʼ���id
//    if (this.viewMapOnBackWard.size() == 0) {
//      return this.bids;
//    }
    if(this.checkids==null || this.checkids.length==0){
      return null;
    }
    Set<String> idSet = new HashSet<String>();
    for (String bid : this.checkids) {
     // if (!this.viewMapOnBackWard.containsKey(bid)) {
        idSet.add(bid);
     // }
    }
    String[] checkIds = new String[idSet.size()];
    return idSet.toArray(checkIds);
  }

  private void init() {
    this.lockBills();
    this.initViewMapOnBackWard();
    this.initVoMapOnBackWard();
    this.initViewMapOnCheck();
    this.initAllViewMap();
  }

  /*
   * ��ʼ������Ҫ��д�ķ������ӱ���Ϣ 
   */
  private void initAllViewMap() {
    this.allviewMap = new HashMap<String, DeliveryViewVO>();
    if (this.viewMapOnCheck.size() > 0) {
      Set<Entry<String, DeliveryViewVO>> entrys =
          this.viewMapOnCheck.entrySet();
      for (Entry<String, DeliveryViewVO> entry : entrys) {
        this.allviewMap.put(entry.getKey(), entry.getValue());
      }
    }
    else if (this.viewMapOnBackWard.size() > 0) {
      Set<Entry<String, DeliveryViewVO>> entrys =
          this.viewMapOnBackWard.entrySet();
      for (Entry<String, DeliveryViewVO> entry : entrys) {
        this.allviewMap.put(entry.getKey(), entry.getValue());
      }
    }
  }

  /*
   * ��ʼ�����λ�д�������������Ϣ
   */
  private void initViewMapOnBackWard() {
    DeliveryViewVO[] views = this.queryViews(this.bids);
    this.viewMapOnBackWard = new HashMap<String, DeliveryViewVO>();
    if (null == views) {
      return;
    }
    for (DeliveryViewVO view : views) {
      this.viewMapOnBackWard.put(view.getItem().getCdeliverybid(), view);
    }
  }

  /*
   * �����ʼ�vo��ѯ�ʼ���Ϣ����Ӧ�ķ�����������
   */
  private void initViewMapOnCheck() {
    this.viewMapOnCheck = new HashMap<String, DeliveryViewVO>();
    this.bidsByCheckVO = null;
    if (this.voMapOnBackWard.size() == 0) {
      return;
    }
    // �����ʼ����Ϣ���
    Set<String> bidSet = new HashSet<String>();
    Set<Entry<String, DeliveryCheckVO>> entrys =
        this.voMapOnBackWard.entrySet();
    for (Entry<String, DeliveryCheckVO> entry : entrys) {
      DeliveryCheckVO vo = entry.getValue();
      bidSet.add(vo.getCdeliverybid());
    }
    this.bidsByCheckVO = new String[bidSet.size()];
    this.bidsByCheckVO = bidSet.toArray(this.bidsByCheckVO);
    DeliveryViewVO[] views = this.queryViews(this.bidsByCheckVO);
    for (DeliveryViewVO view : views) {
      this.viewMapOnCheck.put(view.getItem().getCdeliverybid(), view);
    }
  }

  /*
   * ��ʼ����д�ķ������ʼ���Ϣ���������Ϣ
   */
  private void initVoMapOnBackWard() {
    this.voMapOnBackWard = new HashMap<String, DeliveryCheckVO>();
    String[] ids = this.getRewritCheckIDS();
    if (null == ids ||ids.length==0) {
      return;
    }
    VOQuery<DeliveryCheckVO> query =
        new VOQuery<DeliveryCheckVO>(DeliveryCheckVO.class);
    DeliveryCheckVO[] vos = query.query(ids);
    for (DeliveryCheckVO vo : vos) {
      this.voMapOnBackWard.put(vo.getCdeliverycid(), vo);
    }
  }

  /*
   * ����
   */
  private void lockBills() {
    LockOperator locker = new LockOperator();
    String message = NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0084")/*��д����������ʧ�ܡ�*/;
    locker.lock(this.bids, message);
    if(this.checkids!=null && this.checkids.length>0){
      locker.lock(this.checkids, message);
    }
  }

  /*
   * ���ݷ���������id���õ�����������ͼvo
   */
  private DeliveryViewVO[] queryViews(String[] bids1) {
    ViewQuery<DeliveryViewVO> query =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    // ����id��ѯviewvo
    return query.query(bids1);
  }
}
