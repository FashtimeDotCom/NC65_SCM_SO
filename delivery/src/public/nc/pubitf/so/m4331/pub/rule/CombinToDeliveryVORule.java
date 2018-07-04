package nc.pubitf.so.m4331.pub.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m4331.IDeliverycheckMaintain;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.util.AggVOUtil;

/**
 * ���ݷ�����vo��Ϣ����ʼ���Ϣ�����ʼ�vo�е�������Ϣ
 * ���ϲ��������������ϣ����γ��µľۺ�vo
 * 
 * @since 6.0
 * @version 2011-1-10 ����02:25:16
 * @author ף����
 */
public class CombinToDeliveryVORule {

  // ���淢��������id
  private Map<String, String> bidMap;

  // ���治��Ҫ��ѯ�ʼ������ݾͿ��Գ��������
  private Map<String, DeliveryBVO> bvoMap;

  // ��������ʼ������г�
  private Map<String, DeliveryBVO> bvoMustOutByCheckMap;

  private Map<String, DeliveryHVO> hvoMap;

  /**
   * �ϲ�
   * 
   * @param vo
   */
  public DeliveryVO[] combin(DeliveryVO[] vos) {
    if (null == vos) {
      return null;
    }
    this.initMap(vos);
    if (this.bvoMustOutByCheckMap.size() == 0) {
      return vos;
    }
    if (!this.initData()) {
      // �޳�vos��bvoMustOutByCheckMap��Ӧ�ļ�¼
      if (this.bvoMap.size() == 0) {
        return null;
      }
      // return vos;
    }
    if (this.bvoMap.size() == 0) {
      return null;
    }
    DeliveryBVO[] bvos = new DeliveryBVO[this.bvoMap.size()];
    bvos = this.bvoMap.values().toArray(bvos);
    DeliveryHVO[] hvos = this.getHvos();
    DeliveryVO[] newbills =
        (DeliveryVO[]) AggVOUtil.createBillVO(hvos, bvos, DeliveryVO.class);
    this.setDeliveryBid(newbills);
    return newbills;
  }

  /*
   * ���ݷ���������id��ѯ����Ӧ���ʼ���Ϣid
   * @return
   */
  private DeliveryCheckVO[] getCheckInfo() {
    String sql = this.getQuerySql();
    IDeliverycheckMaintain service =
        NCLocator.getInstance().lookup(IDeliverycheckMaintain.class);
    return service.queryDeliveryCheckVO(sql);
  }

  private DeliveryHVO[] getHvos() {
    DeliveryBVO[] bvos = new DeliveryBVO[this.bvoMap.size()];
    this.bvoMap.values().toArray(bvos);
    Map<String, DeliveryHVO> tempMap = new HashMap<String, DeliveryHVO>();
    for (DeliveryBVO bvo : bvos) {
      String hid = bvo.getCdeliveryid();
      DeliveryHVO hvo = this.hvoMap.get(hid);
      if (tempMap.size() == 0 || !tempMap.containsKey(hid)) {
        tempMap.put(hid, hvo);
      }
    }
    DeliveryHVO[] hvos = new DeliveryHVO[tempMap.size()];
    tempMap.values().toArray(hvos);
    return hvos;
  }

  /*
   * ��ò�ѯ�ʼ���Ϣ��sql 
   * @return
   */
  private String getQuerySql() {
    String[] bids = new String[this.bvoMustOutByCheckMap.size()];
    bids = this.bvoMustOutByCheckMap.keySet().toArray(bids);
    StringBuffer sql = new StringBuffer();
    sql.append("select distinct(" + DeliveryCheckVO.CDELIVERYCID + ")");
    sql.append(" from so_delivery_check where dr=0 and ");
    SqlBuilder sqlBuilder = new SqlBuilder();
    sqlBuilder.append(DeliveryCheckVO.BCHECKINFLAG, UFBoolean.TRUE);
    sqlBuilder.append(" and ");
    sqlBuilder.append(DeliveryCheckVO.CDELIVERYBID, bids);
    sqlBuilder.append(" and ");
    sqlBuilder.append(DeliveryCheckVO.BOUTENDFLAG, UFBoolean.FALSE);
    sql.append(sqlBuilder.toString());
    return sql.toString();
  }

  private boolean initData() {
    if (this.bvoMustOutByCheckMap.size() == 0) {
      return false;
    }
    DeliveryCheckVO[] checkInfos = this.getCheckInfo();
    if (null == checkInfos) {
      return false;
    }
    this.bidMap = new HashMap<String, String>();
    // ��������ʼ���Ϣ�ķ���������id
    for (DeliveryCheckVO info : checkInfos) {
      String srcbid = info.getCdeliverybid();
      if (this.bvoMustOutByCheckMap.containsKey(srcbid)) {
        DeliveryBVO bvo = this.bvoMustOutByCheckMap.get(srcbid);
        DeliveryBVO newBvo = (DeliveryBVO) bvo.clone();
        this.replaceBvoDatas(newBvo, info);
        this.bidMap.put(newBvo.getCdeliverybid(), bvo.getCdeliverybid());
        this.bvoMap.put(newBvo.getCdeliverybid(), newBvo);
      }
    }
    this.bvoMustOutByCheckMap.clear();
    return true;
  }

  /*
   * ���淢������Ϣ
   */
  private void initMap(DeliveryVO[] vos) {
    this.bvoMap = new HashMap<String, DeliveryBVO>();
    this.bvoMustOutByCheckMap = new HashMap<String, DeliveryBVO>();
    this.hvoMap = new HashMap<String, DeliveryHVO>();
    for (DeliveryVO vo : vos) {
      this.hvoMap.put(vo.getParentVO().getCdeliveryid(), vo.getParentVO());
      DeliveryBVO[] bvos = vo.getChildrenVO();
      // ֻ�к��ַ��������п��ܾ����ʼ���Ϣ
      for (DeliveryBVO bvo : bvos) {
        boolean ishavequality = this.isHavaQulity(bvo);
        if (!ishavequality) {
          this.bvoMap.put(bvo.getCdeliverybid(), bvo);
          continue;
        }
        this.bvoMustOutByCheckMap.put(bvo.getCdeliverybid(), bvo);
      }
    }
  }

  /**
   * �жϱ���vo�Ƿ�����ʼ�
   * 
   * @param bvo
   * @return
   */
  private boolean isHavaQulity(DeliveryBVO bvo) {
    UFDouble num = bvo.getNnum();
    if (MathTool.compareTo(num, UFDouble.ZERO_DBL) < 0) {
      UFBoolean busecheckflag = bvo.getBusecheckflag();
      if (null == busecheckflag || !busecheckflag.booleanValue()) {
        return false;
      }
      return true;
    }
    return false;
  }

  private void replaceBvoDatas(DeliveryBVO newBvo, DeliveryCheckVO info) {
    // ���������Ϣ
    this.setMaterial(newBvo, info);
    // ����־
    this.setFlag(newBvo, info);
    // ����ԭ�Ҽ۸�
    this.setOrigPrice(newBvo, info);
    // ���ñ��Ҽ۸�
    this.setPrice(newBvo, info);
    // ������
    this.setRate(newBvo, info);
    // ����������
    this.setFree(newBvo, info);
    // ���ý����Ϣ
    this.setMny(newBvo, info);
    // ���������������Ϣ
    this.setOther(newBvo, info);
  }

  private void setDeliveryBid(DeliveryVO[] newbills) {
    if (null == this.bidMap || this.bidMap.size() == 0) {
      return;
    }
    for (DeliveryVO vo : newbills) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        String bid = bvo.getCdeliverybid();
        if (this.bidMap.containsKey(bid)) {
          String newbid = this.bidMap.get(bid);
          bvo.setCdeliverybid(newbid);
        }
      }
    }
  }

  /*
   * ���Ĭ�ϵı�־��Ϣ
   */
  private void setFlag(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setBlargessflag(info.getBlargessflag());
    newBvo.setBoutendflag(info.getBoutendflag());
    newBvo.setBtransendflag(info.getBtransendflag());
  }

  /*
   * ����������
   */
  private void setFree(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setPk_batchcode(info.getPk_batchcode());
    newBvo.setVbatchcode(info.getVbatchcode());
    newBvo.setVfree1(info.getVfree1());
    newBvo.setVfree2(info.getVfree2());
    newBvo.setVfree3(info.getVfree3());
    newBvo.setVfree4(info.getVfree4());
    newBvo.setVfree5(info.getVfree5());
    newBvo.setVfree6(info.getVfree6());
    newBvo.setVfree7(info.getVfree7());
    newBvo.setVfree8(info.getVfree8());
    newBvo.setVfree9(info.getVfree9());
    newBvo.setVfree10(info.getVfree10());
  }

  /*
   * ������ϵ���Ӧ��Ϣ
   */
  private void setMaterial(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setCmaterialid(info.getCmaterialid());
    newBvo.setCmaterialvid(info.getCmaterialvid());
    newBvo.setCunitid(info.getCunitid());
    newBvo.setCastunitid(info.getCastunitid());
    newBvo.setCqtunitid(info.getCqtunitid());
  }

  /*
   * ���ý����Ϣ
   * @param bvo
   * @param info
   */
  private void setMny(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setNorigtaxmny(info.getNorigtaxmny());
    newBvo.setNorigmny(info.getNorigmny());
    newBvo.setNorigdiscount(info.getNorigdiscount());
    newBvo.setNtaxmny(info.getNtaxmny());
    newBvo.setNmny(info.getNmny());
    newBvo.setNdiscount(info.getNdiscount());
    newBvo.setNtax(info.getNtax());
  }

  /*
   * ������ԭ�Ҽ۸�ֵ��
   */
  private void setOrigPrice(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setNorignetprice(info.getNorignetprice());
    newBvo.setNorigprice(info.getNorigprice());
    newBvo.setNorigtaxnetprice(info.getNorigtaxnetprice());
    newBvo.setNorigtaxprice(info.getNorigtaxprice());
    newBvo.setNqtorignetprice(info.getNqtorignetprice());
    newBvo.setNqtorigprice(info.getNqtorigprice());
    newBvo.setNqtorigtaxnetprc(info.getNqtorigtaxnetprc());
    newBvo.setNqtorigtaxprice(info.getNqtorigtaxprice());
  }

  /*
   * ����ֵ�ĸ�ֵ
   */
  private void setOther(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setCrowno(info.getCrowno());
    newBvo.setCproductorid(info.getCproductorid());
    newBvo.setCprojectid(info.getCprojectid());
    newBvo.setCvendorid(info.getCvendorid());
    newBvo.setPk_org(info.getPk_org());
    newBvo.setCdeliverybbid(info.getCdeliverycid());
    newBvo.setCdeliverybid(info.getCdeliverycid());
    newBvo.setTts(info.getTs());
  }

  /*
   * ����������ı��Ҽ۸����õ��ʼ��
   */
  private void setPrice(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setNnetprice(info.getNnetprice());
    newBvo.setNprice(info.getNprice());
    newBvo.setNqtnetprice(info.getNqtnetprice());
    newBvo.setNqtprice(info.getNqtprice());
    newBvo.setNqttaxnetprice(info.getNqttaxnetprice());
    newBvo.setNqttaxprice(info.getNqttaxprice());
    newBvo.setNtaxnetprice(info.getNtaxnetprice());
    newBvo.setNtaxprice(info.getNtaxprice());
  }

  /*
   * �����������˰�ʡ������ʵ����õ��ʼ��
   */
  private void setRate(DeliveryBVO newBvo, DeliveryCheckVO info) {
    newBvo.setCcurrencyid(info.getCcurrencyid());
    newBvo.setCorigcurrencyid(info.getCorigcurrencyid());
    newBvo.setNdiscountrate(info.getNdiscountrate());
    newBvo.setNexchangerate(info.getNexchangerate());
    newBvo.setNitemdiscountrate(info.getNitemdiscountrate());
    newBvo.setNtaxrate(info.getNtaxrate());
    newBvo.setVchangerate(info.getVchangerate());
    newBvo.setVqtunitrate(info.getVqtunitrate());
    newBvo.setNnum(info.getNnum());
    newBvo.setNtotaloutnum(info.getNtotaloutnum());
    newBvo.setNtotalnotoutnum(info.getNtotalnotoutnum());
    newBvo.setNastnum(info.getNastnum());
    newBvo.setNqtunitnum(info.getNqtunitnum());
  }
}
