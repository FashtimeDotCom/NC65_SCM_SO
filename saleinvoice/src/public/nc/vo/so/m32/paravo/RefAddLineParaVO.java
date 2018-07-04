package nc.vo.so.m32.paravo;

import nc.vo.pub.lang.UFBoolean;

/**
 * ������USEROBJ
 * 
 * @since 6.0
 * @version 2011-6-10 ����10:44:20
 * @author ô��
 */
public class RefAddLineParaVO {
  /** ���ⵥ����Ʊ������ */
  private String[] busitypes;

  /** ԴͷBID */
  private String[] cfirstbids;

  /** ԴͷID����ʱ���ã� */
  private String[] cfirstids;

  /** ��ԴBID */
  private String[] csrcbids;

  /** ��Դ ID(��ʱ����) */
  private String[] csrcids;

  /** �����Ƿ��Ƕ�������Ʊ����(���ڲ�������) */
  private UFBoolean is30to32busitypes;

  /** ����֯ */
  private String pk_org;

  public String[] getBusitypes() {
    return this.busitypes;
  }

  public String[] getCfirstbids() {
    return this.cfirstbids;
  }

  public String[] getCfirstids() {
    return this.cfirstids;
  }

  public String[] getCsrcbids() {
    return this.csrcbids;
  }

  public String[] getCsrcids() {
    return this.csrcids;
  }

  public UFBoolean getIs30to32busitypes() {
    return this.is30to32busitypes;
  }

  public String getPk_org() {
    return this.pk_org;
  }

  public void setBusitypes(String[] busitypes) {
    this.busitypes = busitypes;
  }

  public void setCfirstbids(String[] cfirstbids) {
    this.cfirstbids = cfirstbids;
  }

  public void setCfirstids(String[] cfirstids) {
    this.cfirstids = cfirstids;
  }

  public void setCsrcbids(String[] csrcbids) {
    this.csrcbids = csrcbids;
  }

  public void setCsrcids(String[] csrcids) {
    this.csrcids = csrcids;
  }

  public void setIs30to32busitypes(UFBoolean is30to32busitypes) {
    this.is30to32busitypes = is30to32busitypes;
  }

  public void setPk_org(String pk_org) {
    this.pk_org = pk_org;
  }

}
