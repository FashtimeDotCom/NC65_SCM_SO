package nc.pubimpl.so.sobalance.arap.verify;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * for-Arap�տ������ϵ��ѯʱ����VO��û������Ҫ��ͱ仯��ʵ����copy��ArapBillMapVO
 * 
 * 
 * @since 6.0
 * @version 2011-4-11 ����04:37:04
 * @author ��־ΰ
 */
public class SoBalanceBillMapVO extends SuperVO {

  private static final long serialVersionUID = -2227134510196596773L;

  private Integer dr;

  /** ռ�ñ�־ */
  private int maptype;

  /** ��ʷ��� */
  private UFDouble oldje;

  /**
   * Դͷ���۶���ID
   */
  private String orderid;

  /**
   * ����
   * */
  private String pk_billmap;

  private String pk_currtype;

  private String pk_org;

  /** Դ����pk */
  private String s_billid;

  /** Դ�������� */
  private String s_billtype;

  /** Դ������pk */
  private String s_itemid;

  /** ϵͳ��Դ */
  private int s_system;

  /** Դ����Э����pk */
  private String s_termid;

  /** Ŀ�굥��pk */
  private String t_billid;

  /** Ŀ�굥������ */
  private String t_billtype;

  /** Ŀ�굥����pk */
  private String t_itemid;

  /** Ŀ�굥��Э����pk */
  private String t_termid;

  private UFDateTime ts;

  /** ������ */
  private UFDouble ybje;

  /** ������� */
  private UFDouble ybye;

  public Integer getDr() {
    return this.dr;
  }

  public int getMaptype() {
    return this.maptype;
  }

  public UFDouble getOldje() {
    return this.oldje;
  }

  public String getOrderid() {
    return this.orderid;
  }

  @Override
  public String getParentPKFieldName() {
    return null;
  }

  public String getPk_billmap() {
    return this.pk_billmap;
  }

  public String getPk_currtype() {
    return this.pk_currtype;
  }

  public String getPk_org() {
    return this.pk_org;
  }

  @Override
  public String getPKFieldName() {
    return "pk_billmap";
  }

  public String getS_billid() {
    return this.s_billid;
  }

  public String getS_billtype() {
    return this.s_billtype;
  }

  public String getS_itemid() {
    return this.s_itemid;
  }

  public int getS_system() {
    return this.s_system;
  }

  public String getS_termid() {
    return this.s_termid;
  }

  public String getT_billid() {
    return this.t_billid;
  }

  public String getT_billtype() {
    return this.t_billtype;
  }

  public String getT_itemid() {
    return this.t_itemid;
  }

  public String getT_termid() {
    return this.t_termid;
  }

  @Override
  public String getTableName() {
    return "arap_billmap";
  }

  public UFDateTime getTs() {
    return this.ts;
  }

  public UFDouble getYbje() {
    return this.ybje;
  }

  public UFDouble getYbye() {
    return this.ybye;
  }

  public void setDr(Integer dr) {
    this.dr = dr;
  }

  public void setMaptype(int maptype) {
    this.maptype = maptype;
  }

  public void setOldje(UFDouble oldje) {
    this.oldje = oldje;
  }

  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }

  public void setPk_billmap(String pk_billmap) {
    this.pk_billmap = pk_billmap;
  }

  public void setPk_currtype(String pkCurrtype) {
    this.pk_currtype = pkCurrtype;
  }

  public void setPk_org(String pkOrg) {
    this.pk_org = pkOrg;
  }

  public void setS_billid(String s_billid) {
    this.s_billid = s_billid;
  }

  public void setS_billtype(String s_billtype) {
    this.s_billtype = s_billtype;
  }

  public void setS_itemid(String s_itemid) {
    this.s_itemid = s_itemid;
  }

  public void setS_system(int s_system) {
    this.s_system = s_system;
  }

  public void setS_termid(String sTermid) {
    this.s_termid = sTermid;
  }

  public void setT_billid(String t_billid) {
    this.t_billid = t_billid;
  }

  public void setT_billtype(String t_billtype) {
    this.t_billtype = t_billtype;
  }

  public void setT_itemid(String t_itemid) {
    this.t_itemid = t_itemid;
  }

  public void setT_termid(String tTermid) {
    this.t_termid = tTermid;
  }

  public void setTs(UFDateTime ts) {
    this.ts = ts;
  }

  public void setYbje(UFDouble ybje) {
    this.ybje = ybje;
  }

  public void setYbye(UFDouble ybye) {
    this.ybye = ybye;
  }
}
