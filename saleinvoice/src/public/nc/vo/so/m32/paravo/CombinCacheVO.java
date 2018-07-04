package nc.vo.so.m32.paravo;

import java.util.Map;

import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

/**
 * ��Ʊ�ϲ��������
 * 
 * @since 6.0
 * @version 2011-8-12 ����10:49:24
 * @author ô��
 */
public class CombinCacheVO {
  // �Ƿ���ܱ�־λ
  private boolean bcombinflag;

  // ���ܺ���ϸ���չ�ϵ
  private MapList<String, SaleInvoiceBVO> combinrela =
      new MapList<String, SaleInvoiceBVO>();

  // key:��Ʊ��֯ value:�ϲ��ֶ�
  private Map<String, String> mapgroupkeys;

  /**
   * 
   * 
   * @param combinflag
   */
  public CombinCacheVO(boolean combinflag) {
    this.bcombinflag = combinflag;
  }

  public boolean getBcombinflag() {
    return this.bcombinflag;
  }

  public MapList<String, SaleInvoiceBVO> getCombinRela() {
    return this.combinrela;
  }

  public String getGroupKeys(String pk_org) {
    return this.mapgroupkeys.get(pk_org);
  }

  public Map<String, String> getMapGroupKeys() {
    return this.mapgroupkeys;
  }

  public void setBcombinflag(boolean combinflag) {
    this.bcombinflag = combinflag;
  }

  public void setCombinrela(MapList<String, SaleInvoiceBVO> combinrela) {
    this.combinrela = combinrela;
  }

  public void setMapGroupKeys(Map<String, String> mapgroupkey) {
    this.mapgroupkeys = mapgroupkey;
  }
}
