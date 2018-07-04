package nc.vo.so.m33.m32.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m33.enumeration.SquareType;

/**
 * ���۷�Ʊ�����嵥��ϸʵ����
 * 
 * @since 6.0
 * @version 2012-1-5 ����08:52:22
 * @author fengjb
 */
public class SquareInvDetailVO extends SuperVO {

  private static final long serialVersionUID = 4245259221757655909L;

  // ���۷�Ʊ�����嵥��ϸʵ��·��
  public static final String ENTITYNAME = "so.SquareInvDetailVO";

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
   * ���۷�Ʊ���㵥��ʵ��
   */
  public static final String CSALESQUAREBID = "csalesquarebid";

  /**
   * ���۷�Ʊ���㵥��ϸʵ��
   */
  public static final String CSALESQUAREDID = "csalesquaredid";

  /**
   * ���۷�Ʊ���㵥��ʵ��
   */
  public static final String CSALESQUAREID = "csalesquareid";

  /**
   * ���۷�Ʊ��ʵ��
   */
  public static final String CSQUAREBILLBID = "csquarebillbid";

  /**
   * ���۷�Ʊ��ʵ��
   */
  public static final String CSQUAREBILLID = "csquarebillid";

  /**
   * ����λ
   */
  public static final String CUNITID = "cunitid";

  /**
   * ���۷�Ʊ��������
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * ��������
   */
  public static final String FSQUARETYPE = "fsquaretype";

  /**
   * ��λ����
   */
  public static final String NASTNUM = "nastnum";

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
   * ˰��
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

  /******* V61�����ֶ� *******/
  /**
   * ˰��
   */
  public static final String CTAXCODEID = "ctaxcodeid";

  /**
   * ��˰���
   */
  public static final String FTAXTYPEFLAG = "ftaxtypeflag";

  /**
   * ��˰���
   */
  public static final String NCALTAXMNY = "ncaltaxmny";

  /**
   * ��ȡ��λ
   * 
   * @return ��λ
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(SquareInvDetailVO.CASTUNITID);
  }

  /**
   * ���õ�λ
   * 
   * @param castunitid ��λ
   */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SquareInvDetailVO.CASTUNITID, castunitid);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SquareInvDetailVO.CCURRENCYID);
  }

  /**
   * ���ñ���
   * 
   * @param ccurrencyid ����
   */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SquareInvDetailVO.CCURRENCYID, ccurrencyid);
  }

  /**
   * ��ȡԭ��
   * 
   * @return ԭ��
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SquareInvDetailVO.CORIGCURRENCYID);
  }

  /**
   * ����ԭ��
   * 
   * @param corigcurrencyid ԭ��
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SquareInvDetailVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * ��ȡ���۷�Ʊ���㵥��ʵ��
   * 
   * @return ���۷�Ʊ���㵥��ʵ��
   */
  public String getCsalesquarebid() {
    return (String) this.getAttributeValue(SquareInvDetailVO.CSALESQUAREBID);
  }

  /**
   * �������۷�Ʊ���㵥��ʵ��
   * 
   * @param csalesquarebid ���۷�Ʊ���㵥��ʵ��
   */
  public void setCsalesquarebid(String csalesquarebid) {
    this.setAttributeValue(SquareInvDetailVO.CSALESQUAREBID, csalesquarebid);
  }

  /**
   * ��ȡ���۷�Ʊ���㵥��ϸʵ��
   * 
   * @return ���۷�Ʊ���㵥��ϸʵ��
   */
  public String getCsalesquaredid() {
    return (String) this.getAttributeValue(SquareInvDetailVO.CSALESQUAREDID);
  }

  /**
   * �������۷�Ʊ���㵥��ϸʵ��
   * 
   * @param csalesquaredid ���۷�Ʊ���㵥��ϸʵ��
   */
  public void setCsalesquaredid(String csalesquaredid) {
    this.setAttributeValue(SquareInvDetailVO.CSALESQUAREDID, csalesquaredid);
  }

  /**
   * ��ȡ���۷�Ʊ���㵥��ʵ��
   * 
   * @return ���۷�Ʊ���㵥��ʵ��
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareInvDetailVO.CSALESQUAREID);
  }

  /**
   * �������۷�Ʊ���㵥��ʵ��
   * 
   * @param csalesquareid ���۷�Ʊ���㵥��ʵ��
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareInvDetailVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * ��ȡ���۷�Ʊ��ʵ��
   * 
   * @return ���۷�Ʊ��ʵ��
   */
  public String getCsquarebillbid() {
    return (String) this.getAttributeValue(SquareInvDetailVO.CSQUAREBILLBID);
  }

  /**
   * �������۷�Ʊ��ʵ��
   * 
   * @param csquarebillbid ���۷�Ʊ��ʵ��
   */
  public void setCsquarebillbid(String csquarebillbid) {
    this.setAttributeValue(SquareInvDetailVO.CSQUAREBILLBID, csquarebillbid);
  }

  /**
   * ��ȡ���۷�Ʊ��ʵ��
   * 
   * @return ���۷�Ʊ��ʵ��
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareInvDetailVO.CSQUAREBILLID);
  }

  /**
   * �������۷�Ʊ��ʵ��
   * 
   * @param csquarebillid ���۷�Ʊ��ʵ��
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareInvDetailVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SquareInvDetailVO.CTAXCODEID);
  }

  /**
   * ����˰��
   * 
   * @param ctaxcodeid ˰��
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SquareInvDetailVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * ��ȡ����λ
   * 
   * @return ����λ
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(SquareInvDetailVO.CUNITID);
  }

  /**
   * ��������λ
   * 
   * @param cunitid ����λ
   */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SquareInvDetailVO.CUNITID, cunitid);
  }

  /**
   * ��ȡ���۷�Ʊ��������
   * 
   * @return ���۷�Ʊ��������
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareInvDetailVO.DBILLDATE);
  }

  /**
   * �������۷�Ʊ��������
   * 
   * @param dbilldate ���۷�Ʊ��������
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareInvDetailVO.DBILLDATE, dbilldate);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   * @see SquareType
   */
  public Integer getFsquaretype() {
    return (Integer) this.getAttributeValue(SquareInvDetailVO.FSQUARETYPE);
  }

  /**
   * ���ý�������
   * 
   * @param fsquaretype ��������
   * @see SquareType
   */
  public void setFsquaretype(Integer fsquaretype) {
    this.setAttributeValue(SquareInvDetailVO.FSQUARETYPE, fsquaretype);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SquareInvDetailVO.FTAXTYPEFLAG);
  }

  /**
   * ���ÿ�˰���
   * 
   * @param ftaxtypeflag ��˰���
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SquareInvDetailVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /**
   * ��ȡ��λ����
   * 
   * @return ��λ����
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NASTNUM);
  }

  /**
   * ���õ�λ����
   * 
   * @param nastnum ��λ����
   */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(SquareInvDetailVO.NASTNUM, nastnum);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NCALTAXMNY);
  }

  /**
   * ���ü�˰���
   * 
   * @param ncaltaxmny ��˰���
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SquareInvDetailVO.NCALTAXMNY, ncaltaxmny);
  }

  /**
   * ��ȡ�����ۿ۶�
   * 
   * @return �����ۿ۶�
   */
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NDISCOUNT);
  }

  /**
   * ���ñ����ۿ۶�
   * 
   * @param ndiscount �����ۿ۶�
   */
  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SquareInvDetailVO.NDISCOUNT, ndiscount);
  }

  /**
   * ��ȡ�۱�����
   * 
   * @return �۱�����
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NEXCHANGERATE);
  }

  /**
   * �����۱�����
   * 
   * @param nexchangerate �۱�����
   */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(SquareInvDetailVO.NEXCHANGERATE, nexchangerate);
  }

  /**
   * ��ȡȫ�ֱ�λ�һ���
   * 
   * @return ȫ�ֱ�λ�һ���
   */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this
        .getAttributeValue(SquareInvDetailVO.NGLOBALEXCHGRATE);
  }

  /**
   * ����ȫ�ֱ�λ�һ���
   * 
   * @param nglobalexchgrate ȫ�ֱ�λ�һ���
   */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(SquareInvDetailVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /**
   * ��ȡȫ�ֱ�����˰���
   * 
   * @return ȫ�ֱ�����˰���
   */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NGLOBALMNY);
  }

  /**
   * ����ȫ�ֱ�����˰���
   * 
   * @param nglobalmny ȫ�ֱ�����˰���
   */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SquareInvDetailVO.NGLOBALMNY, nglobalmny);
  }

  /**
   * ��ȡȫ�ֱ��Ҽ�˰�ϼ�
   * 
   * @return ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NGLOBALTAXMNY);
  }

  /**
   * ����ȫ�ֱ��Ҽ�˰�ϼ�
   * 
   * @param nglobaltaxmny ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SquareInvDetailVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /**
   * ��ȡ���ű�λ�һ���
   * 
   * @return ���ű�λ�һ���
   */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NGROUPEXCHGRATE);
  }

  /**
   * ���ü��ű�λ�һ���
   * 
   * @param ngroupexchgrate ���ű�λ�һ���
   */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(SquareInvDetailVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /**
   * ��ȡ���ű�����˰���
   * 
   * @return ���ű�����˰���
   */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NGROUPMNY);
  }

  /**
   * ���ü��ű�����˰���
   * 
   * @param ngroupmny ���ű�����˰���
   */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SquareInvDetailVO.NGROUPMNY, ngroupmny);
  }

  /**
   * ��ȡ���ű��Ҽ�˰�ϼ�
   * 
   * @return ���ű��Ҽ�˰�ϼ�
   */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NGROUPTAXMNY);
  }

  /**
   * ���ü��ű��Ҽ�˰�ϼ�
   * 
   * @param ngrouptaxmny ���ű��Ҽ�˰�ϼ�
   */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SquareInvDetailVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /**
   * ��ȡ��Ʒ�ۿ���
   * 
   * @return ��Ʒ�ۿ���
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this
        .getAttributeValue(SquareInvDetailVO.NITEMDISCOUNTRATE);
  }

  /**
   * ���õ�Ʒ�ۿ���
   * 
   * @param nitemdiscountrate ��Ʒ�ۿ���
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SquareInvDetailVO.NITEMDISCOUNTRATE,
        nitemdiscountrate);
  }

  /**
   * ��ȡ������˰���
   * 
   * @return ������˰���
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NMNY);
  }

  /**
   * ���ñ�����˰���
   * 
   * @param nmny ������˰���
   */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SquareInvDetailVO.NMNY, nmny);
  }

  /**
   * ��ȡ������˰����
   * 
   * @return ������˰����
   */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NNETPRICE);
  }

  /**
   * ���ñ�����˰����
   * 
   * @param nnetprice ������˰����
   */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(SquareInvDetailVO.NNETPRICE, nnetprice);
  }

  /**
   * ��ȡ����λ����
   * 
   * @return ����λ����
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NNUM);
  }

  /**
   * ��������λ����
   * 
   * @param nnum ����λ����
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SquareInvDetailVO.NNUM, nnum);
  }

  /**
   * ��ȡԭ���ۿ۶�
   * 
   * @return ԭ���ۿ۶�
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NORIGDISCOUNT);
  }

  /**
   * ����ԭ���ۿ۶�
   * 
   * @param norigdiscount ԭ���ۿ۶�
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SquareInvDetailVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * ��ȡԭ����˰���
   * 
   * @return ԭ����˰���
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NORIGMNY);
  }

  /**
   * ����ԭ����˰���
   * 
   * @param norigmny ԭ����˰���
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SquareInvDetailVO.NORIGMNY, norigmny);
  }

  /**
   * ��ȡԭ����˰����
   * 
   * @return ԭ����˰����
   */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NORIGNETPRICE);
  }

  /**
   * ����ԭ����˰����
   * 
   * @param norignetprice ԭ����˰����
   */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SquareInvDetailVO.NORIGNETPRICE, norignetprice);
  }

  /**
   * ��ȡԭ����˰����
   * 
   * @return ԭ����˰����
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NORIGPRICE);
  }

  /**
   * ����ԭ����˰����
   * 
   * @param norigprice ԭ����˰����
   */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SquareInvDetailVO.NORIGPRICE, norigprice);
  }

  /**
   * ��ȡԭ�Ҽ�˰�ϼ�
   * 
   * @return ԭ�Ҽ�˰�ϼ�
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NORIGTAXMNY);
  }

  /**
   * ����ԭ�Ҽ�˰�ϼ�
   * 
   * @param norigtaxmny ԭ�Ҽ�˰�ϼ�
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SquareInvDetailVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * ��ȡԭ�Һ�˰����
   * 
   * @return ԭ�Һ�˰����
   */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this
        .getAttributeValue(SquareInvDetailVO.NORIGTAXNETPRICE);
  }

  /**
   * ����ԭ�Һ�˰����
   * 
   * @param norigtaxnetprice ԭ�Һ�˰����
   */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SquareInvDetailVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  /**
   * ��ȡԭ�Һ�˰����
   * 
   * @return ԭ�Һ�˰����
   */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NORIGTAXPRICE);
  }

  /**
   * ����ԭ�Һ�˰����
   * 
   * @param norigtaxprice ԭ�Һ�˰����
   */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SquareInvDetailVO.NORIGTAXPRICE, norigtaxprice);
  }

  /**
   * ��ȡ������˰����
   * 
   * @return ������˰����
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NPRICE);
  }

  /**
   * ���ñ�����˰����
   * 
   * @param nprice ������˰����
   */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SquareInvDetailVO.NPRICE, nprice);
  }

  /**
   * ��ȡ���ν�������
   * 
   * @return ���ν�������
   */
  public UFDouble getNsquarenum() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NSQUARENUM);
  }

  /**
   * ���ñ��ν�������
   * 
   * @param nsquarenum ���ν�������
   */
  public void setNsquarenum(UFDouble nsquarenum) {
    this.setAttributeValue(SquareInvDetailVO.NSQUARENUM, nsquarenum);
  }

  /**
   * ��ȡ����˰��
   * 
   * @return ����˰��
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NTAX);
  }

  /**
   * ���ñ���˰��
   * 
   * @param ntax ����˰��
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SquareInvDetailVO.NTAX, ntax);
  }

  /**
   * ��ȡ���Ҽ�˰�ϼ�
   * 
   * @return ���Ҽ�˰�ϼ�
   */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NTAXMNY);
  }

  /**
   * ���ñ��Ҽ�˰�ϼ�
   * 
   * @param ntaxmny ���Ҽ�˰�ϼ�
   */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SquareInvDetailVO.NTAXMNY, ntaxmny);
  }

  /**
   * ��ȡ���Һ�˰����
   * 
   * @return ���Һ�˰����
   */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NTAXNETPRICE);
  }

  /**
   * ���ñ��Һ�˰����
   * 
   * @param ntaxnetprice ���Һ�˰����
   */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(SquareInvDetailVO.NTAXNETPRICE, ntaxnetprice);
  }

  /**
   * ��ȡ���Һ�˰����
   * 
   * @return ���Һ�˰����
   */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NTAXPRICE);
  }

  /**
   * ���ñ��Һ�˰����
   * 
   * @param ntaxprice ���Һ�˰����
   */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SquareInvDetailVO.NTAXPRICE, ntaxprice);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SquareInvDetailVO.NTAXRATE);
  }

  /**
   * ����˰��
   * 
   * @param ntaxrate ˰��
   */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SquareInvDetailVO.NTAXRATE, ntaxrate);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareInvDetailVO.PK_GROUP);
  }

  /**
   * ���ü���
   * 
   * @param pk_group ����
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareInvDetailVO.PK_GROUP, pk_group);
  }

  /**
   * ��ȡ���������֯
   * 
   * @return ���������֯
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareInvDetailVO.PK_ORG);
  }

  /**
   * ���ý��������֯
   * 
   * @param pk_org ���������֯
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareInvDetailVO.PK_ORG, pk_org);
  }

  /**
   * ��ȡ�������κ�
   * 
   * @return �������κ�
   */
  public String getProcesseid() {
    return (String) this.getAttributeValue(SquareInvDetailVO.PROCESSEID);
  }

  /**
   * ���ý������κ�
   * 
   * @param processeid �������κ�
   */
  public void setProcesseid(String processeid) {
    this.setAttributeValue(SquareInvDetailVO.PROCESSEID, processeid);
  }

  /**
   * ��ȡʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareInvDetailVO.TS);
  }

  /**
   * ����ʱ���
   * 
   * @param ts ʱ���
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareInvDetailVO.TS, ts);
  }

  /**
   * ��ȡ��λ������
   * 
   * @return ��λ������
   */
  public String getVchangerate() {
    return (String) this.getAttributeValue(SquareInvDetailVO.VCHANGERATE);
  }

  /**
   * ���õ�λ������
   * 
   * @param vchangerate ��λ������
   */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SquareInvDetailVO.VCHANGERATE, vchangerate);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareInvDetailVO.ENTITYNAME);
    return meta;
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SquareInvBVO.DR, dr);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareInvBVO.DR);
  }
}
