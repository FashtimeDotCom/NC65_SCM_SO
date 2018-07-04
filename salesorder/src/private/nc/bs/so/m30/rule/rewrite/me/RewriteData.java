package nc.bs.so.m30.rule.rewrite.me;

import nc.pubitf.me.m35meext.so.IRewriteData;
import nc.vo.pub.lang.UFDouble;

/**
 * ��дӪ�����ò����ӿ�ʵ��
 * 
 * @since 6.3
 * @version 2014-06-27 14:27:28
 * @author ����
 */
public class RewriteData implements IRewriteData {

  /**
   * ����
   * 
   */
  public String ccurrencyid;

  /**
   * ��������;������
   */
  public UFDouble ncurtranslossnum;

  /**
   * �����в�����֯
   * 
   */
  public String financeorg;

  /**
   * 
   * �����з��ó�ֽ��
   */

  public UFDouble norigsubmny;

  /**
   * �������ۼ�;��������
   */

  public UFDouble ntranslossnum;

  /**
   * 
   * ���۶���id
   */
  public String saleorderid;

  /**
   * 
   * ���۶�����id
   */

  public String saleorderbid;

  /**
   * 
   * ������������
   */
  public UFDouble nnum;

  /**
   * 
   * �������ۼ�ǩ��������
   */

  public UFDouble ntotalsignnum;

  /**
   * 
   * �������ۼƳ���������
   */

  public UFDouble ntotalsendnum;

  /**
   * 
   * �����б���ǩ��������
   */
  public UFDouble ncursignnum;

  /**
   * �������Ƿ����ر�(ǩ��;�𵥱����ɾ��ʱ����)
   * 
   */

  public boolean isoutclosed;

  /**
   * 
   * �Ƿ���Ʒ�Ҹ����۶���
   */

  public boolean islrgcash;
  
  /**
   * �����б��γ���仯��
   */
  public UFDouble nchangenum;

  public UFDouble getNchangenum() {
	return nchangenum;
  }

  public void setNchangenum(UFDouble nchangenum) {
	this.nchangenum = nchangenum;
  }

@Override
  public String getCcurrencyid() {
    return this.ccurrencyid;
  }

  @Override
  public String getFinanceorg() {
    return this.financeorg;
  }

  @Override
  public UFDouble getNorigsubmny() {
    return this.norigsubmny;
  }

  @Override
  public UFDouble getNtranslossnum() {
    return this.ntranslossnum;
  }

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
  public UFDouble getNtotalsignnum() {
    return this.ntotalsignnum;
  }

  @Override
  public UFDouble getNtotalsendnum() {
    return this.ntotalsendnum;
  }

  @Override
  public UFDouble getNcursignnum() {
    return this.ncursignnum;
  }

  @Override
  public boolean getIsOutClosed() {
    return this.isoutclosed;
  }

  @Override
  public boolean getIsLrgCash() {
    return this.islrgcash;
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

  /**
   * ���ö����з��ó�ֽ��
   * 
   * @param norigsubmny
   */
  public void setNorigsubmny(UFDouble norigsubmny) {
    this.norigsubmny = norigsubmny;
  }

  /**
   * ���ö������ۼ�;��������
   * 
   * @param ntranslossnum
   */
  public void setNtranslossnum(UFDouble ntranslossnum) {
    this.ntranslossnum = ntranslossnum;
  }

  /**
   * ���ö�����ͷid
   * 
   * @param saleorderid
   */
  public void setSaleorderid(String saleorderid) {
    this.saleorderid = saleorderid;
  }

  /**
   * ���ö�����id
   * 
   * @param saleorderbid
   */
  public void setSaleorderbid(String saleorderbid) {
    this.saleorderbid = saleorderbid;
  }

  /**
   * ���ö�����������
   * 
   * @param nnum
   */
  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

  /**
   * 
   * ���ö������ۼ�ǩ��������
   * 
   * @param ntotalsignnum
   */
  public void setNtotalsignnum(UFDouble ntotalsignnum) {
    this.ntotalsignnum = ntotalsignnum;
  }

  /**
   * ���� �������ۼƳ���������
   * 
   * @param ntotalsendnum
   */
  public void setNtotalsendnum(UFDouble ntotalsendnum) {
    this.ntotalsendnum = ntotalsendnum;
  }

  /**
   * ���ö����б���ǩ��������
   * 
   * @param ncursignnum
   */
  public void setNcursignnum(UFDouble ncursignnum) {
    this.ncursignnum = ncursignnum;
  }

  /**
   * ���ö������Ƿ����ر�(ǩ��;�𵥱����ɾ��ʱ����)
   * 
   * @param isoutclosed
   */
  public void setIsoutclosed(boolean isoutclosed) {
    this.isoutclosed = isoutclosed;
  }

  /**
   * �����Ƿ���Ʒ�Ҹ����۶���
   * 
   * @param islrgcash
   */
  public void setIslrgcash(boolean islrgcash) {
    this.islrgcash = islrgcash;
  }
  
  @Override
  public UFDouble getNcurtranslossnum() {
    return this.ncurtranslossnum;
  }

  /**
   * ���ñ���;������
   * 
   * @param ncurtranslossnum
   */
  public void setNcurtranslossnum(UFDouble ncurtranslossnum) {
    this.ncurtranslossnum = ncurtranslossnum;
  }

}
