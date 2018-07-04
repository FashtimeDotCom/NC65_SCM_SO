package nc.vo.so.m33.m4453.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m33.enumeration.SquareType;

public class SquareWasDetailVO extends SuperVO {

  private static final long serialVersionUID = 4245259221757655909L;

  // ;�𵥽�����ϸʵ��·��
  public static final String ENTITYNAME = "so.SquareWasDetailVO";

  // ɾ����־dr
  public static final String DR = "dr";

  /**
   * ��λ
   */
  public static final String CASTUNITID = "castunitid";

  /**
   * ����
   */
  public static final String CCURRENCYID = "ccurrencyid";

  /**
   * ԭ��
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /**
   * ;����㵥��ʵ��
   */
  public static final String CSALESQUAREBID = "csalesquarebid";

  /**
   * ;����㵥��ϸʵ��
   */
  public static final String CSALESQUAREDID = "csalesquaredid";

  /**
   * ;����㵥��ʵ��
   */
  public static final String CSALESQUAREID = "csalesquareid";

  /**
   * ;����ʵ��
   */
  public static final String CSQUAREBILLBID = "csquarebillbid";

  /**
   * ;����ʵ��
   */
  public static final String CSQUAREBILLID = "csquarebillid";

  /**
   * ˰��
   */
  public static final String CTAXCODEID = "ctaxcodeid";

  /**
   * ����λ
   */
  public static final String CUNITID = "cunitid";

  /**
   * ;�𵥵�������
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * ��������
   */
  public static final String FSQUARETYPE = "fsquaretype";

  /**
   * ��˰���
   */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /**
   * ��λ����
   */
  public static final String NASTNUM = "nastnum";

  /**
   * ��˰���
   */
  public static final String NCALTAXMNY = "ncaltaxmny";

  /**
   * �����ۿ۶�
   */
  public static final String NDISCOUNT = "ndiscount";

  /**
   * �۱�����
   */
  public static final String NEXCHANGERATE = "nexchangerate";

  /**
   * ȫ�ֱ�λ�һ���
   */
  public static final String NGLOBALEXCHGRATE = "nglobalexchgrate";

  /**
   * ȫ�ֱ�����˰���
   */
  public static final String NGLOBALMNY = "nglobalmny";

  /**
   * ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public static final String NGLOBALTAXMNY = "nglobaltaxmny";

  /**
   * ���ű�λ�һ���
   */
  public static final String NGROUPEXCHGRATE = "ngroupexchgrate";

  /**
   * ���ű�����˰���
   */
  public static final String NGROUPMNY = "ngroupmny";

  /**
   * ���ű��Ҽ�˰�ϼ�
   */
  public static final String NGROUPTAXMNY = "ngrouptaxmny";

  /**
   * ��Ʒ�ۿ���
   */
  public static final String NITEMDISCOUNTRATE = "nitemdiscountrate";

  /**
   * ������˰���
   */
  public static final String NMNY = "nmny";

  /**
   * ������˰����
   */
  public static final String NNETPRICE = "nnetprice";

  /**
   * ����λ����
   */
  public static final String NNUM = "nnum";

  /**
   * ԭ���ۿ۶�
   */
  public static final String NORIGDISCOUNT = "norigdiscount";

  /**
   * ԭ����˰���
   */
  public static final String NORIGMNY = "norigmny";

  /**
   * ԭ����˰����
   */
  public static final String NORIGNETPRICE = "norignetprice";

  /**
   * ԭ����˰����
   */
  public static final String NORIGPRICE = "norigprice";

  /**
   * ԭ��˰��
   */
  public static final String NORIGTAX = "norigtax";

  /**
   * ԭ�Ҽ�˰�ϼ�
   */
  public static final String NORIGTAXMNY = "norigtaxmny";

  /**
   * ԭ�Һ�˰����
   */
  public static final String NORIGTAXNETPRICE = "norigtaxnetprice";

  /**
   * ԭ�Һ�˰����
   */
  public static final String NORIGTAXPRICE = "norigtaxprice";

  /**
   * ������˰����
   */
  public static final String NPRICE = "nprice";

  /**
   * ���ν�������
   */
  public static final String NSQUARENUM = "nsquarenum";

  /**
   * ����˰��
   */
  public static final String NTAX = "ntax";

  /**
   * ���Ҽ�˰�ϼ�
   */
  public static final String NTAXMNY = "ntaxmny";

  /**
   * ���Һ�˰����
   */
  public static final String NTAXNETPRICE = "ntaxnetprice";

  /**
   * ���Һ�˰����
   */
  public static final String NTAXPRICE = "ntaxprice";

  /**
   * ˰��
   */
  public static final String NTAXRATE = "ntaxrate";

  /**
   * ����
   */
  public static final String PK_GROUP = "pk_group";

  /**
   * ���������֯
   */
  public static final String PK_ORG = "pk_org";

  /**
   * �������κ�
   */
  public static final String PROCESSEID = "processeid";

  /**
   * ʱ���
   */
  public static final String TS = "ts";

  /**
   * ��λ������
   */
  public static final String VCHANGERATE = "vchangerate";

  /**
   * ��ȡ��λ
   * 
   * @return ��λ
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CASTUNITID);
  }

  /**
   * ���õ�λ
   * 
   * @param castunitid ��λ
   */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SquareWasDetailVO.CASTUNITID, castunitid);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CCURRENCYID);
  }

  /**
   * ���ñ���
   * 
   * @param ccurrencyid ����
   */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SquareWasDetailVO.CCURRENCYID, ccurrencyid);
  }

  /**
   * ��ȡԭ��
   * 
   * @return ԭ��
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CORIGCURRENCYID);
  }

  /**
   * ����ԭ��
   * 
   * @param corigcurrencyid ԭ��
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SquareWasDetailVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * ��ȡ;����㵥��ʵ��
   * 
   * @return ;����㵥��ʵ��
   */
  public String getCsalesquarebid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CSALESQUAREBID);
  }

  /**
   * ����;����㵥��ʵ��
   * 
   * @param csalesquarebid ;����㵥��ʵ��
   */
  public void setCsalesquarebid(String csalesquarebid) {
    this.setAttributeValue(SquareWasDetailVO.CSALESQUAREBID, csalesquarebid);
  }

  /**
   * ��ȡ;����㵥��ϸʵ��
   * 
   * @return ;����㵥��ϸʵ��
   */
  public String getCsalesquaredid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CSALESQUAREDID);
  }

  /**
   * ����;����㵥��ϸʵ��
   * 
   * @param csalesquaredid ;����㵥��ϸʵ��
   */
  public void setCsalesquaredid(String csalesquaredid) {
    this.setAttributeValue(SquareWasDetailVO.CSALESQUAREDID, csalesquaredid);
  }

  /**
   * ��ȡ;����㵥��ʵ��
   * 
   * @return ;����㵥��ʵ��
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CSALESQUAREID);
  }

  /**
   * ����;����㵥��ʵ��
   * 
   * @param csalesquareid ;����㵥��ʵ��
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareWasDetailVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * ��ȡ;����ʵ��
   * 
   * @return ;����ʵ��
   */
  public String getCsquarebillbid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CSQUAREBILLBID);
  }

  /**
   * ����;����ʵ��
   * 
   * @param csquarebillbid ;����ʵ��
   */
  public void setCsquarebillbid(String csquarebillbid) {
    this.setAttributeValue(SquareWasDetailVO.CSQUAREBILLBID, csquarebillbid);
  }

  /**
   * ��ȡ;����ʵ��
   * 
   * @return ;����ʵ��
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CSQUAREBILLID);
  }

  /**
   * ����;����ʵ��
   * 
   * @param csquarebillid ;����ʵ��
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareWasDetailVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CTAXCODEID);
  }

  /**
   * ����˰��
   * 
   * @param ctaxcodeid ˰��
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SquareWasDetailVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * ��ȡ����λ
   * 
   * @return ����λ
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.CUNITID);
  }

  /**
   * ��������λ
   * 
   * @param cunitid ����λ
   */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SquareWasDetailVO.CUNITID, cunitid);
  }

  /**
   * ��ȡ;�𵥵�������
   * 
   * @return ;�𵥵�������
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareWasDetailVO.DBILLDATE);
  }

  /**
   * ����;�𵥵�������
   * 
   * @param dbilldate ;�𵥵�������
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareWasDetailVO.DBILLDATE, dbilldate);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   * @see SquareType
   */
  public Integer getFsquaretype() {
    return (Integer) this.getAttributeValue(SquareWasDetailVO.FSQUARETYPE);
  }

  /**
   * ���ý�������
   * 
   * @param fsquaretype ��������
   * @see SquareType
   */
  public void setFsquaretype(Integer fsquaretype) {
    this.setAttributeValue(SquareWasDetailVO.FSQUARETYPE, fsquaretype);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SquareWasDetailVO.FTAXTYPEFLAG);
  }

  /**
   * ���ÿ�˰���
   * 
   * @param ftaxtypeflag ��˰���
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SquareWasDetailVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /**
   * ��ȡ��λ����
   * 
   * @return ��λ����
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NASTNUM);
  }

  /**
   * ���õ�λ����
   * 
   * @param nastnum ��λ����
   */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(SquareWasDetailVO.NASTNUM, nastnum);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NCALTAXMNY);
  }

  /**
   * ���ü�˰���
   * 
   * @param ncaltaxmny ��˰���
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SquareWasDetailVO.NCALTAXMNY, ncaltaxmny);
  }

  /**
   * ��ȡ�����ۿ۶�
   * 
   * @return �����ۿ۶�
   */
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NDISCOUNT);
  }

  /**
   * ���ñ����ۿ۶�
   * 
   * @param ndiscount �����ۿ۶�
   */
  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SquareWasDetailVO.NDISCOUNT, ndiscount);
  }

  /**
   * ��ȡ�۱�����
   * 
   * @return �۱�����
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NEXCHANGERATE);
  }

  /**
   * �����۱�����
   * 
   * @param nexchangerate �۱�����
   */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(SquareWasDetailVO.NEXCHANGERATE, nexchangerate);
  }

  /**
   * ��ȡȫ�ֱ�λ�һ���
   * 
   * @return ȫ�ֱ�λ�һ���
   */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this
        .getAttributeValue(SquareWasDetailVO.NGLOBALEXCHGRATE);
  }

  /**
   * ����ȫ�ֱ�λ�һ���
   * 
   * @param nglobalexchgrate ȫ�ֱ�λ�һ���
   */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(SquareWasDetailVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /**
   * ��ȡȫ�ֱ�����˰���
   * 
   * @return ȫ�ֱ�����˰���
   */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NGLOBALMNY);
  }

  /**
   * ����ȫ�ֱ�����˰���
   * 
   * @param nglobalmny ȫ�ֱ�����˰���
   */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SquareWasDetailVO.NGLOBALMNY, nglobalmny);
  }

  /**
   * ��ȡȫ�ֱ��Ҽ�˰�ϼ�
   * 
   * @return ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NGLOBALTAXMNY);
  }

  /**
   * ����ȫ�ֱ��Ҽ�˰�ϼ�
   * 
   * @param nglobaltaxmny ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SquareWasDetailVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /**
   * ��ȡ���ű�λ�һ���
   * 
   * @return ���ű�λ�һ���
   */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NGROUPEXCHGRATE);
  }

  /**
   * ���ü��ű�λ�һ���
   * 
   * @param ngroupexchgrate ���ű�λ�һ���
   */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(SquareWasDetailVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /**
   * ��ȡ���ű�����˰���
   * 
   * @return ���ű�����˰���
   */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NGROUPMNY);
  }

  /**
   * ���ü��ű�����˰���
   * 
   * @param ngroupmny ���ű�����˰���
   */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SquareWasDetailVO.NGROUPMNY, ngroupmny);
  }

  /**
   * ��ȡ���ű��Ҽ�˰�ϼ�
   * 
   * @return ���ű��Ҽ�˰�ϼ�
   */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NGROUPTAXMNY);
  }

  /**
   * ���ü��ű��Ҽ�˰�ϼ�
   * 
   * @param ngrouptaxmny ���ű��Ҽ�˰�ϼ�
   */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SquareWasDetailVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /**
   * ��ȡ��Ʒ�ۿ���
   * 
   * @return ��Ʒ�ۿ���
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this
        .getAttributeValue(SquareWasDetailVO.NITEMDISCOUNTRATE);
  }

  /**
   * ���õ�Ʒ�ۿ���
   * 
   * @param nitemdiscountrate ��Ʒ�ۿ���
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SquareWasDetailVO.NITEMDISCOUNTRATE,
        nitemdiscountrate);
  }

  /**
   * ��ȡ������˰���
   * 
   * @return ������˰���
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NMNY);
  }

  /**
   * ���ñ�����˰���
   * 
   * @param nmny ������˰���
   */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SquareWasDetailVO.NMNY, nmny);
  }

  /**
   * ��ȡ������˰����
   * 
   * @return ������˰����
   */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NNETPRICE);
  }

  /**
   * ���ñ�����˰����
   * 
   * @param nnetprice ������˰����
   */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(SquareWasDetailVO.NNETPRICE, nnetprice);
  }

  /**
   * ��ȡ����λ����
   * 
   * @return ����λ����
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NNUM);
  }

  /**
   * ��������λ����
   * 
   * @param nnum ����λ����
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SquareWasDetailVO.NNUM, nnum);
  }

  /**
   * ��ȡԭ���ۿ۶�
   * 
   * @return ԭ���ۿ۶�
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NORIGDISCOUNT);
  }

  /**
   * ����ԭ���ۿ۶�
   * 
   * @param norigdiscount ԭ���ۿ۶�
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SquareWasDetailVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * ��ȡԭ����˰���
   * 
   * @return ԭ����˰���
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NORIGMNY);
  }

  /**
   * ����ԭ����˰���
   * 
   * @param norigmny ԭ����˰���
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SquareWasDetailVO.NORIGMNY, norigmny);
  }

  /**
   * ��ȡԭ����˰����
   * 
   * @return ԭ����˰����
   */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NORIGNETPRICE);
  }

  /**
   * ����ԭ����˰����
   * 
   * @param norignetprice ԭ����˰����
   */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SquareWasDetailVO.NORIGNETPRICE, norignetprice);
  }

  /**
   * ��ȡԭ����˰����
   * 
   * @return ԭ����˰����
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NORIGPRICE);
  }

  /**
   * ����ԭ����˰����
   * 
   * @param norigprice ԭ����˰����
   */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SquareWasDetailVO.NORIGPRICE, norigprice);
  }

  /**
   * ��ȡԭ��˰��
   * 
   * @return ԭ��˰��
   */
  public UFDouble getNorigtax() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NORIGTAX);
  }

  /**
   * ����ԭ��˰��
   * 
   * @param norigtax ԭ��˰��
   */
  public void setNorigtax(UFDouble norigtax) {
    this.setAttributeValue(SquareWasDetailVO.NORIGTAX, norigtax);
  }

  /**
   * ��ȡԭ�Ҽ�˰�ϼ�
   * 
   * @return ԭ�Ҽ�˰�ϼ�
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NORIGTAXMNY);
  }

  /**
   * ����ԭ�Ҽ�˰�ϼ�
   * 
   * @param norigtaxmny ԭ�Ҽ�˰�ϼ�
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SquareWasDetailVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * ��ȡԭ�Һ�˰����
   * 
   * @return ԭ�Һ�˰����
   */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this
        .getAttributeValue(SquareWasDetailVO.NORIGTAXNETPRICE);
  }

  /**
   * ����ԭ�Һ�˰����
   * 
   * @param norigtaxnetprice ԭ�Һ�˰����
   */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SquareWasDetailVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  /**
   * ��ȡԭ�Һ�˰����
   * 
   * @return ԭ�Һ�˰����
   */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NORIGTAXPRICE);
  }

  /**
   * ����ԭ�Һ�˰����
   * 
   * @param norigtaxprice ԭ�Һ�˰����
   */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SquareWasDetailVO.NORIGTAXPRICE, norigtaxprice);
  }

  /**
   * ��ȡ������˰����
   * 
   * @return ������˰����
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NPRICE);
  }

  /**
   * ���ñ�����˰����
   * 
   * @param nprice ������˰����
   */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SquareWasDetailVO.NPRICE, nprice);
  }

  /**
   * ��ȡ���ν�������
   * 
   * @return ���ν�������
   */
  public UFDouble getNsquarenum() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NSQUARENUM);
  }

  /**
   * ���ñ��ν�������
   * 
   * @param nsquarenum ���ν�������
   */
  public void setNsquarenum(UFDouble nsquarenum) {
    this.setAttributeValue(SquareWasDetailVO.NSQUARENUM, nsquarenum);
  }

  /**
   * ��ȡ����˰��
   * 
   * @return ����˰��
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NTAX);
  }

  /**
   * ���ñ���˰��
   * 
   * @param ntax ����˰��
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SquareWasDetailVO.NTAX, ntax);
  }

  /**
   * ��ȡ���Ҽ�˰�ϼ�
   * 
   * @return ���Ҽ�˰�ϼ�
   */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NTAXMNY);
  }

  /**
   * ���ñ��Ҽ�˰�ϼ�
   * 
   * @param ntaxmny ���Ҽ�˰�ϼ�
   */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SquareWasDetailVO.NTAXMNY, ntaxmny);
  }

  /**
   * ��ȡ���Һ�˰����
   * 
   * @return ���Һ�˰����
   */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NTAXNETPRICE);
  }

  /**
   * ���ñ��Һ�˰����
   * 
   * @param ntaxnetprice ���Һ�˰����
   */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(SquareWasDetailVO.NTAXNETPRICE, ntaxnetprice);
  }

  /**
   * ��ȡ���Һ�˰����
   * 
   * @return ���Һ�˰����
   */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NTAXPRICE);
  }

  /**
   * ���ñ��Һ�˰����
   * 
   * @param ntaxprice ���Һ�˰����
   */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SquareWasDetailVO.NTAXPRICE, ntaxprice);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SquareWasDetailVO.NTAXRATE);
  }

  /**
   * ����˰��
   * 
   * @param ntaxrate ˰��
   */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SquareWasDetailVO.NTAXRATE, ntaxrate);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareWasDetailVO.PK_GROUP);
  }

  /**
   * ���ü���
   * 
   * @param pk_group ����
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareWasDetailVO.PK_GROUP, pk_group);
  }

  /**
   * ��ȡ���������֯
   * 
   * @return ���������֯
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareWasDetailVO.PK_ORG);
  }

  /**
   * ���ý��������֯
   * 
   * @param pk_org ���������֯
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareWasDetailVO.PK_ORG, pk_org);
  }

  /**
   * ��ȡ�������κ�
   * 
   * @return �������κ�
   */
  public String getProcesseid() {
    return (String) this.getAttributeValue(SquareWasDetailVO.PROCESSEID);
  }

  /**
   * ���ý������κ�
   * 
   * @param processeid �������κ�
   */
  public void setProcesseid(String processeid) {
    this.setAttributeValue(SquareWasDetailVO.PROCESSEID, processeid);
  }

  /**
   * ��ȡʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareWasDetailVO.TS);
  }

  /**
   * ����ʱ���
   * 
   * @param ts ʱ���
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareWasDetailVO.TS, ts);
  }

  /**
   * ��ȡ��λ������
   * 
   * @return ��λ������
   */
  public String getVchangerate() {
    return (String) this.getAttributeValue(SquareWasDetailVO.VCHANGERATE);
  }

  /**
   * ���õ�λ������
   * 
   * @param vchangerate ��λ������
   */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SquareWasDetailVO.VCHANGERATE, vchangerate);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareWasDetailVO.ENTITYNAME);
    return meta;
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SquareWasHVO.DR, dr);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareWasHVO.DR);
  }
}
