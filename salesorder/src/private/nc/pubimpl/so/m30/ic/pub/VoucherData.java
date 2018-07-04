package nc.pubimpl.so.m30.ic.pub;

import nc.pubitf.me.m35meext.so.IVoucherData;
import nc.vo.pub.lang.UFDouble;

/**
 * ��������رգ�ǩ��;��Ӱ��ͻ����õ����˽�ƾ֤����ʵ��
 * 
 * @since 6.3
 * @version 2014-07-02 09:45:13
 * @author ����
 */
public class VoucherData implements IVoucherData {

  private String saleorderid;

  private String saleorderbid;

  private UFDouble nnum;

  private UFDouble neffectnum;

  private UFDouble norigsubmny;

  private UFDouble norigcaccountmny;

  private Boolean islrgcash;

  private String ccurrencyid;

  private String financeorg;

  @Override
  public String getSaleorderid() {
    return this.saleorderid;
  }

  @Override
  public String getSaleorderbid() {
    return this.saleorderbid;
  }

  @Override
  public UFDouble getNnum() {
    return this.nnum;
  }

  @Override
  public UFDouble getNeffectnum() {
    return this.neffectnum;
  }

  @Override
  public UFDouble getNorigsubmny() {
    return this.norigsubmny;
  }

  @Override
  public UFDouble getNorigcaccountmny() {
    return this.norigcaccountmny;
  }

  @Override
  public Boolean getIsLrgCash() {
    return this.islrgcash;
  }

  /**
   * ���۶���id
   * 
   * @param saleorderid
   */
  public void setSaleorderid(String saleorderid) {
    this.saleorderid = saleorderid;
  }

  /**
   * ���۶�����id
   * 
   * @param saleorderbid
   */
  public void setSaleorderbid(String saleorderbid) {
    this.saleorderbid = saleorderbid;
  }

  /**
   * ���۶�����������
   * 
   * @param nnum
   */
  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

  /**
   * ���γ����ǩ��������
   * 
   * @param neffectnum
   */
  public void setNeffectnum(UFDouble neffectnum) {
    this.neffectnum = neffectnum;
  }

  /**
   * ���۶����г�ֽ��
   * 
   * @param norigsubmny
   */
  public void setNorigsubmny(UFDouble norigsubmny) {
    this.norigsubmny = norigsubmny;
  }

  /**
   * ���۶����г�ּ��˽�<br>
   * ������Ϊ��Ʒ�Ҹ�����ʱ��ȡ �����м��˵���* ��������������
   * ����ȡ�����г�ֽ��
   * 
   * 
   * @param norigcaccountmny
   */
  public void setNorigcaccountmny(UFDouble norigcaccountmny) {
    this.norigcaccountmny = norigcaccountmny;
  }

  /**
   * �Ƿ���Ʒ�Ҹ�����
   * 
   * @param islrgcash
   */
  public void setIslrgcash(Boolean islrgcash) {
    this.islrgcash = islrgcash;
  }

  @Override
  public String getCcurrencyid() {
    return this.ccurrencyid;
  }

  /**
   * ���ñ���
   * 
   * @param ccurrencyid
   */
  public void setCcurrencyid(String ccurrencyid) {
    this.ccurrencyid = ccurrencyid;
  }

  /**
   * ���ò�����֯
   * 
   * @param financeorg
   */
  public void setFinanceorg(String financeorg) {
    this.financeorg = financeorg;
  }

  @Override
  public String getFinanceorg() {
    return this.financeorg;
  }

}
