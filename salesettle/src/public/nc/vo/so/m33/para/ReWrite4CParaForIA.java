package nc.vo.so.m33.para;

import nc.vo.pub.lang.UFDouble;

/**
 * �ṩ���������Ĳ���VO
 * 
 * @since 6.0
 * @version 2011-6-1 ����01:23:24
 * @author ô��
 */
public class ReWrite4CParaForIA {

  /**
   * ��Դ��ϸID
   */
  private String csrcbid;

  /**
   * �ɱ����
   */
  private UFDouble ncostmny;

  /**
   * �ɱ�����
   */
  private UFDouble ncostprice;

  /**
   * �����Դ��ϸID
   * 
   * @return ��Դ��ϸID
   */
  public String getCsrcbid() {
    return this.csrcbid;
  }

  /**
   * ��óɱ����
   * 
   * @return �ɱ����
   */
  public UFDouble getNcostmny() {
    return this.ncostmny;
  }

  /**
   * ������Դ��ϸID
   * 
   * @param csrcbid
   */
  public void setCsrcbid(String csrcbid) {
    this.csrcbid = csrcbid;
  }

  /**
   * ���óɱ����
   * 
   * @param ncostmny
   */
  public void setNcostmny(UFDouble ncostmny) {
    this.ncostmny = ncostmny;
  }

  /**
   * ��óɱ�����
   * 
   * @return �ɱ�����
   */
  public UFDouble getNcostprice() {
    return this.ncostprice;
  }

  /**
   * ���óɱ�����
   * 
   * @param ncostprice
   */
  public void setNcostprice(UFDouble ncostprice) {
    this.ncostprice = ncostprice;
  }

}
