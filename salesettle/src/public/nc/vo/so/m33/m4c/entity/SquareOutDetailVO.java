package nc.vo.so.m33.m4c.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;

/**
 * ���۳��ⵥ���㵥��ϸʵ��
 * 
 * @since 6.1
 * @version 2013-05-08 13:59:51
 * @author yixl
 */
public class SquareOutDetailVO extends SuperVO {

  private static final long serialVersionUID = 4245259221757655909L;

  /**
   * ���۳��ⵥ���㵥��ϸʵ��
   */
  public static final String ENTITYNAME = "so.SquareOutDetailVO";

  /**
   * ɾ����־dr
   */
  public static final String DR = "dr";

  /**
   * ���۳��ⵥ�����㵥voID(��������ʱ�ã�Ԫ������û��)
   * ������vo����ϸvo�Ķ�Ӧ��ϵ����Ϊ���ܴ�����vo���1�в�ɶ���SquareOutDetailVO
   * �����޷�����id��Ӧ
   */
  public static final String CSQUAREOUTBVOID = "csquareoutbvoid";

  /**
   * �Ƿ��Զ�����
   */
  public static final String BAUTOSQUAREFLAG = "bautosquareflag";

  /**
   * �Ƿ����Գ�
   */
  public static final String BOUTRUSHFLAG = "boutrushflag";

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
   * �Գ���ⵥ�ӱ�id
   */
  public static final String CRUSHGENERALBID = "crushgeneralbid";

  /**
   * �Գ����κ�
   */
  public static final String CRUSHOUTBATCHID = "crushoutbatchid";

  /**
   * ���۳�����㵥��ʵ��
   */
  public static final String CSALESQUAREBID = "csalesquarebid";

  /**
   * ���۳�����㵥��ϸʵ��
   */
  public static final String CSALESQUAREDID = "csalesquaredid";

  /**
   * ���۳�����㵥��ʵ��
   */
  public static final String CSALESQUAREID = "csalesquareid";

  /**
   * ���۳��ⵥ��ʵ��
   */
  public static final String CSQUAREBILLBID = "csquarebillbid";

  /**
   * ���۳��ⵥ��ʵ��
   */
  public static final String CSQUAREBILLID = "csquarebillid";

  /**
   * ����λ
   */
  public static final String CUNITID = "cunitid";

  /**
   * ���۳��ⵥ��������
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
   * ��Ʒ�ۿ�
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

  /**
   * �Գ���ⵥ��
   */
  public static final String VRUSHBILLCODE = "vrushbillcode";

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
   * �ɱ�����
   */
  public static final String NCOSTPRICE = "ncostprice";

  /**
   * �ɱ����
   */
  public static final String NCOSTMNY = "ncostmny";

  /**
   * ��ȡ�Ƿ��Զ�����
   * 
   * @return �Ƿ��Զ�����
   */
  public UFBoolean getBautosquareflag() {
    return (UFBoolean) this
        .getAttributeValue(SquareOutDetailVO.BAUTOSQUAREFLAG);
  }

  /**
   * �����Ƿ��Զ�����
   * 
   * @param bautosquareflag �Ƿ��Զ�����
   */
  public void setBautosquareflag(UFBoolean bautosquareflag) {
    this.setAttributeValue(SquareOutDetailVO.BAUTOSQUAREFLAG, bautosquareflag);
  }

  /**
   * ��ȡ�Ƿ����Գ�
   * 
   * @return �Ƿ����Գ�
   */
  public UFBoolean getBoutrushflag() {
    return (UFBoolean) this.getAttributeValue(SquareOutDetailVO.BOUTRUSHFLAG);
  }

  /**
   * �����Ƿ����Գ�
   * 
   * @param boutrushflag �Ƿ����Գ�
   */
  public void setBoutrushflag(UFBoolean boutrushflag) {
    this.setAttributeValue(SquareOutDetailVO.BOUTRUSHFLAG, boutrushflag);
  }

  /**
   * ��ȡ��λ
   * 
   * @return ��λ
   */
  public String getCastunitid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CASTUNITID);
  }

  /**
   * ���õ�λ
   * 
   * @param castunitid ��λ
   */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(SquareOutDetailVO.CASTUNITID, castunitid);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CCURRENCYID);
  }

  /**
   * ���ñ���
   * 
   * @param ccurrencyid ����
   */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SquareOutDetailVO.CCURRENCYID, ccurrencyid);
  }

  /**
   * ��ȡԭ��
   * 
   * @return ԭ��
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CORIGCURRENCYID);
  }

  /**
   * ����ԭ��
   * 
   * @param corigcurrencyid ԭ��
   */
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(SquareOutDetailVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * ��ȡ�Գ���ⵥ�ӱ�id
   * 
   * @return �Գ���ⵥ�ӱ�id
   */
  public String getCrushgeneralbid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CRUSHGENERALBID);
  }

  /**
   * ���öԳ���ⵥ�ӱ�id
   * 
   * @param crushgeneralbid �Գ���ⵥ�ӱ�id
   */
  public void setCrushgeneralbid(String crushgeneralbid) {
    this.setAttributeValue(SquareOutDetailVO.CRUSHGENERALBID, crushgeneralbid);
  }

  /**
   * ��ȡ�Գ����κ�
   * 
   * @return �Գ����κ�
   */
  public String getCrushoutbatchid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CRUSHOUTBATCHID);
  }

  /**
   * ���öԳ����κ�
   * 
   * @param crushoutbatchid �Գ����κ�
   */
  public void setCrushoutbatchid(String crushoutbatchid) {
    this.setAttributeValue(SquareOutDetailVO.CRUSHOUTBATCHID, crushoutbatchid);
  }

  /**
   * ��ȡ���۳�����㵥��ʵ��
   * 
   * @return ���۳�����㵥��ʵ��
   */
  public String getCsalesquarebid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CSALESQUAREBID);
  }

  /**
   * �������۳�����㵥��ʵ��
   * 
   * @param csalesquarebid ���۳�����㵥��ʵ��
   */
  public void setCsalesquarebid(String csalesquarebid) {
    this.setAttributeValue(SquareOutDetailVO.CSALESQUAREBID, csalesquarebid);
  }

  /**
   * ��ȡ���۳�����㵥��ϸʵ��
   * 
   * @return ���۳�����㵥��ϸʵ��
   */
  public String getCsalesquaredid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CSALESQUAREDID);
  }

  /**
   * �������۳�����㵥��ϸʵ��
   * 
   * @param csalesquaredid ���۳�����㵥��ϸʵ��
   */
  public void setCsalesquaredid(String csalesquaredid) {
    this.setAttributeValue(SquareOutDetailVO.CSALESQUAREDID, csalesquaredid);
  }

  /**
   * ��ȡ���۳�����㵥��ʵ��
   * 
   * @return ���۳�����㵥��ʵ��
   */
  public String getCsalesquareid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CSALESQUAREID);
  }

  /**
   * �������۳�����㵥��ʵ��
   * 
   * @param csalesquareid ���۳�����㵥��ʵ��
   */
  public void setCsalesquareid(String csalesquareid) {
    this.setAttributeValue(SquareOutDetailVO.CSALESQUAREID, csalesquareid);
  }

  /**
   * ��ȡ���۳��ⵥ��ʵ��
   * 
   * @return ���۳��ⵥ��ʵ��
   */
  public String getCsquarebillbid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CSQUAREBILLBID);
  }

  /**
   * �������۳��ⵥ��ʵ��
   * 
   * @param csquarebillbid ���۳��ⵥ��ʵ��
   */
  public void setCsquarebillbid(String csquarebillbid) {
    this.setAttributeValue(SquareOutDetailVO.CSQUAREBILLBID, csquarebillbid);
  }

  /**
   * ��ȡ���۳��ⵥ��ʵ��
   * 
   * @return ���۳��ⵥ��ʵ��
   */
  public String getCsquarebillid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CSQUAREBILLID);
  }

  /**
   * �������۳��ⵥ��ʵ��
   * 
   * @param csquarebillid ���۳��ⵥ��ʵ��
   */
  public void setCsquarebillid(String csquarebillid) {
    this.setAttributeValue(SquareOutDetailVO.CSQUAREBILLID, csquarebillid);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CTAXCODEID);
  }

  /**
   * ����˰��
   * 
   * @param ctaxcodeid ˰��
   */
  public void setCtaxcodeid(String ctaxcodeid) {
    this.setAttributeValue(SquareOutDetailVO.CTAXCODEID, ctaxcodeid);
  }

  /**
   * ��ȡ����λ
   * 
   * @return ����λ
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CUNITID);
  }

  /**
   * ��������λ
   * 
   * @param cunitid ����λ
   */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SquareOutDetailVO.CUNITID, cunitid);
  }

  /**
   * ��ȡ���۳��ⵥ��������
   * 
   * @return ���۳��ⵥ��������
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SquareOutDetailVO.DBILLDATE);
  }

  /**
   * �������۳��ⵥ��������
   * 
   * @param dbilldate ���۳��ⵥ��������
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SquareOutDetailVO.DBILLDATE, dbilldate);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   * @see SquareType
   */
  public Integer getFsquaretype() {
    return (Integer) this.getAttributeValue(SquareOutDetailVO.FSQUARETYPE);
  }

  /**
   * ���ý�������
   * 
   * @param fsquaretype ��������
   * @see SquareType
   */
  public void setFsquaretype(Integer fsquaretype) {
    this.setAttributeValue(SquareOutDetailVO.FSQUARETYPE, fsquaretype);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(SquareOutDetailVO.FTAXTYPEFLAG);
  }

  /**
   * ���ÿ�˰���
   * 
   * @param ftaxtypeflag ��˰���
   */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(SquareOutDetailVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /**
   * ��ȡ��λ����
   * 
   * @return ��λ����
   */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NASTNUM);
  }

  /**
   * ���õ�λ����
   * 
   * @param nastnum ��λ����
   */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(SquareOutDetailVO.NASTNUM, nastnum);
  }

  /**
   * ��ȡ��˰���
   * 
   * @return ��˰���
   */
  public UFDouble getNcaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NCALTAXMNY);
  }

  /**
   * ��óɱ�����
   * 
   * @return �ɱ�����
   */
  public UFDouble getNcostprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NCOSTPRICE);
  }

  /**
   * ��óɱ����
   * 
   * @return �ɱ����
   */
  public UFDouble getNcostmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NCOSTMNY);
  }

  /**
   * ���ü�˰���
   * 
   * @param ncaltaxmny ��˰���
   */
  public void setNcaltaxmny(UFDouble ncaltaxmny) {
    this.setAttributeValue(SquareOutDetailVO.NCALTAXMNY, ncaltaxmny);
  }

  /**
   * ���óɱ�����
   * 
   * @param ncostprice
   */
  public void setNcostprice(UFDouble ncostprice) {
    this.setAttributeValue(SquareOutDetailVO.NCOSTPRICE, ncostprice);
  }

  /**
   * ���óɱ����
   * 
   * @param ncostmny
   */
  public void setNcostmny(UFDouble ncostmny) {
    this.setAttributeValue(SquareOutDetailVO.NCOSTMNY, ncostmny);
  }

  /**
   * ��ȡ�����ۿ۶�
   * 
   * @return �����ۿ۶�
   */
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NDISCOUNT);
  }

  /**
   * ���ñ����ۿ۶�
   * 
   * @param ndiscount �����ۿ۶�
   */
  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SquareOutDetailVO.NDISCOUNT, ndiscount);
  }

  /**
   * ��ȡ�۱�����
   * 
   * @return �۱�����
   */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NEXCHANGERATE);
  }

  /**
   * �����۱�����
   * 
   * @param nexchangerate �۱�����
   */
  public void setNexchangerate(UFDouble nexchangerate) {
    this.setAttributeValue(SquareOutDetailVO.NEXCHANGERATE, nexchangerate);
  }

  /**
   * ��ȡȫ�ֱ�λ�һ���
   * 
   * @return ȫ�ֱ�λ�һ���
   */
  public UFDouble getNglobalexchgrate() {
    return (UFDouble) this
        .getAttributeValue(SquareOutDetailVO.NGLOBALEXCHGRATE);
  }

  /**
   * ����ȫ�ֱ�λ�һ���
   * 
   * @param nglobalexchgrate ȫ�ֱ�λ�һ���
   */
  public void setNglobalexchgrate(UFDouble nglobalexchgrate) {
    this.setAttributeValue(SquareOutDetailVO.NGLOBALEXCHGRATE, nglobalexchgrate);
  }

  /**
   * ��ȡȫ�ֱ�����˰���
   * 
   * @return ȫ�ֱ�����˰���
   */
  public UFDouble getNglobalmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NGLOBALMNY);
  }

  /**
   * ����ȫ�ֱ�����˰���
   * 
   * @param nglobalmny ȫ�ֱ�����˰���
   */
  public void setNglobalmny(UFDouble nglobalmny) {
    this.setAttributeValue(SquareOutDetailVO.NGLOBALMNY, nglobalmny);
  }

  /**
   * ��ȡȫ�ֱ��Ҽ�˰�ϼ�
   * 
   * @return ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public UFDouble getNglobaltaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NGLOBALTAXMNY);
  }

  /**
   * ����ȫ�ֱ��Ҽ�˰�ϼ�
   * 
   * @param nglobaltaxmny ȫ�ֱ��Ҽ�˰�ϼ�
   */
  public void setNglobaltaxmny(UFDouble nglobaltaxmny) {
    this.setAttributeValue(SquareOutDetailVO.NGLOBALTAXMNY, nglobaltaxmny);
  }

  /**
   * ��ȡ���ű�λ�һ���
   * 
   * @return ���ű�λ�һ���
   */
  public UFDouble getNgroupexchgrate() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NGROUPEXCHGRATE);
  }

  /**
   * ���ü��ű�λ�һ���
   * 
   * @param ngroupexchgrate ���ű�λ�һ���
   */
  public void setNgroupexchgrate(UFDouble ngroupexchgrate) {
    this.setAttributeValue(SquareOutDetailVO.NGROUPEXCHGRATE, ngroupexchgrate);
  }

  /**
   * ��ȡ���ű�����˰���
   * 
   * @return ���ű�����˰���
   */
  public UFDouble getNgroupmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NGROUPMNY);
  }

  /**
   * ���ü��ű�����˰���
   * 
   * @param ngroupmny ���ű�����˰���
   */
  public void setNgroupmny(UFDouble ngroupmny) {
    this.setAttributeValue(SquareOutDetailVO.NGROUPMNY, ngroupmny);
  }

  /**
   * ��ȡ���ű��Ҽ�˰�ϼ�
   * 
   * @return ���ű��Ҽ�˰�ϼ�
   */
  public UFDouble getNgrouptaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NGROUPTAXMNY);
  }

  /**
   * ���ü��ű��Ҽ�˰�ϼ�
   * 
   * @param ngrouptaxmny ���ű��Ҽ�˰�ϼ�
   */
  public void setNgrouptaxmny(UFDouble ngrouptaxmny) {
    this.setAttributeValue(SquareOutDetailVO.NGROUPTAXMNY, ngrouptaxmny);
  }

  /**
   * ��ȡ��Ʒ�ۿ���
   * 
   * @return ��Ʒ�ۿ���
   */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this
        .getAttributeValue(SquareOutDetailVO.NITEMDISCOUNTRATE);
  }

  /**
   * ���õ�Ʒ�ۿ���
   * 
   * @param nitemdiscountrate ��Ʒ�ۿ���
   */
  public void setNitemdiscountrate(UFDouble nitemdiscountrate) {
    this.setAttributeValue(SquareOutDetailVO.NITEMDISCOUNTRATE,
        nitemdiscountrate);
  }

  /**
   * ��ȡ������˰���
   * 
   * @return ������˰���
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NMNY);
  }

  /**
   * ���ñ�����˰���
   * 
   * @param nmny ������˰���
   */
  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(SquareOutDetailVO.NMNY, nmny);
  }

  /**
   * ��ȡ������˰����
   * 
   * @return ������˰����
   */
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NNETPRICE);
  }

  /**
   * ���ñ�����˰����
   * 
   * @param nnetprice ������˰����
   */
  public void setNnetprice(UFDouble nnetprice) {
    this.setAttributeValue(SquareOutDetailVO.NNETPRICE, nnetprice);
  }

  /**
   * ��ȡ����λ����
   * 
   * @return ����λ����
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NNUM);
  }

  /**
   * ��������λ����
   * 
   * @param nnum ����λ����
   */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(SquareOutDetailVO.NNUM, nnum);
  }

  /**
   * ��ȡԭ���ۿ۶�
   * 
   * @return ԭ���ۿ۶�
   */
  public UFDouble getNorigdiscount() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NORIGDISCOUNT);
  }

  /**
   * ����ԭ���ۿ۶�
   * 
   * @param norigdiscount ԭ���ۿ۶�
   */
  public void setNorigdiscount(UFDouble norigdiscount) {
    this.setAttributeValue(SquareOutDetailVO.NORIGDISCOUNT, norigdiscount);
  }

  /**
   * ��ȡԭ����˰���
   * 
   * @return ԭ����˰���
   */
  public UFDouble getNorigmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NORIGMNY);
  }

  /**
   * ����ԭ����˰���
   * 
   * @param norigmny ԭ����˰���
   */
  public void setNorigmny(UFDouble norigmny) {
    this.setAttributeValue(SquareOutDetailVO.NORIGMNY, norigmny);
  }

  /**
   * ��ȡԭ����˰����
   * 
   * @return ԭ����˰����
   */
  public UFDouble getNorignetprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NORIGNETPRICE);
  }

  /**
   * ����ԭ����˰����
   * 
   * @param norignetprice ԭ����˰����
   */
  public void setNorignetprice(UFDouble norignetprice) {
    this.setAttributeValue(SquareOutDetailVO.NORIGNETPRICE, norignetprice);
  }

  /**
   * ��ȡԭ����˰����
   * 
   * @return ԭ����˰����
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NORIGPRICE);
  }

  /**
   * ����ԭ����˰����
   * 
   * @param norigprice ԭ����˰����
   */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(SquareOutDetailVO.NORIGPRICE, norigprice);
  }

  /**
   * ��ȡԭ�Ҽ�˰�ϼ�
   * 
   * @return ԭ�Ҽ�˰�ϼ�
   */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NORIGTAXMNY);
  }

  /**
   * ����ԭ�Ҽ�˰�ϼ�
   * 
   * @param norigtaxmny ԭ�Ҽ�˰�ϼ�
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(SquareOutDetailVO.NORIGTAXMNY, norigtaxmny);
  }

  /**
   * ��ȡԭ�Һ�˰����
   * 
   * @return ԭ�Һ�˰����
   */
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this
        .getAttributeValue(SquareOutDetailVO.NORIGTAXNETPRICE);
  }

  /**
   * ����ԭ�Һ�˰����
   * 
   * @param norigtaxnetprice ԭ�Һ�˰����
   */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(SquareOutDetailVO.NORIGTAXNETPRICE, norigtaxnetprice);
  }

  /**
   * ��ȡԭ�Һ�˰����
   * 
   * @return ԭ�Һ�˰����
   */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NORIGTAXPRICE);
  }

  /**
   * ����ԭ�Һ�˰����
   * 
   * @param norigtaxprice ԭ�Һ�˰����
   */
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(SquareOutDetailVO.NORIGTAXPRICE, norigtaxprice);
  }

  /**
   * ��ȡ������˰����
   * 
   * @return ������˰����
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NPRICE);
  }

  /**
   * ���ñ�����˰����
   * 
   * @param nprice ������˰����
   */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SquareOutDetailVO.NPRICE, nprice);
  }

  /**
   * ��ȡ���ν�������
   * 
   * @return ���ν�������
   */
  public UFDouble getNsquarenum() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NSQUARENUM);
  }

  /**
   * ���ñ��ν�������
   * 
   * @param nsquarenum ���ν�������
   */
  public void setNsquarenum(UFDouble nsquarenum) {
    this.setAttributeValue(SquareOutDetailVO.NSQUARENUM, nsquarenum);
  }

  /**
   * ��ȡ����˰��
   * 
   * @return ����˰��
   */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NTAX);
  }

  /**
   * ���ñ���˰��
   * 
   * @param ntax ����˰��
   */
  public void setNtax(UFDouble ntax) {
    this.setAttributeValue(SquareOutDetailVO.NTAX, ntax);
  }

  /**
   * ��ȡ���Ҽ�˰�ϼ�
   * 
   * @return ���Ҽ�˰�ϼ�
   */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NTAXMNY);
  }

  /**
   * ���ñ��Ҽ�˰�ϼ�
   * 
   * @param ntaxmny ���Ҽ�˰�ϼ�
   */
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(SquareOutDetailVO.NTAXMNY, ntaxmny);
  }

  /**
   * ��ȡ���Һ�˰����
   * 
   * @return ���Һ�˰����
   */
  public UFDouble getNtaxnetprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NTAXNETPRICE);
  }

  /**
   * ���ñ��Һ�˰����
   * 
   * @param ntaxnetprice ���Һ�˰����
   */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.setAttributeValue(SquareOutDetailVO.NTAXNETPRICE, ntaxnetprice);
  }

  /**
   * ��ȡ���Һ�˰����
   * 
   * @return ���Һ�˰����
   */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NTAXPRICE);
  }

  /**
   * ���ñ��Һ�˰����
   * 
   * @param ntaxprice ���Һ�˰����
   */
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(SquareOutDetailVO.NTAXPRICE, ntaxprice);
  }

  /**
   * ��ȡ˰��
   * 
   * @return ˰��
   */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(SquareOutDetailVO.NTAXRATE);
  }

  /**
   * ����˰��
   * 
   * @param ntaxrate ˰��
   */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(SquareOutDetailVO.NTAXRATE, ntaxrate);
  }

  /**
   * ��ȡ����
   * 
   * @return ����
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(SquareOutDetailVO.PK_GROUP);
  }

  /**
   * ���ü���
   * 
   * @param pk_group ����
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SquareOutDetailVO.PK_GROUP, pk_group);
  }

  /**
   * ��ȡ���������֯
   * 
   * @return ���������֯
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(SquareOutDetailVO.PK_ORG);
  }

  /**
   * ���ý��������֯
   * 
   * @param pk_org ���������֯
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SquareOutDetailVO.PK_ORG, pk_org);
  }

  /**
   * ��ȡ�������κ�
   * 
   * @return �������κ�
   */
  public String getProcesseid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.PROCESSEID);
  }

  /**
   * ���ý������κ�
   * 
   * @param processeid �������κ�
   */
  public void setProcesseid(String processeid) {
    this.setAttributeValue(SquareOutDetailVO.PROCESSEID, processeid);
  }

  /**
   * ��ȡʱ���
   * 
   * @return ʱ���
   */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SquareOutDetailVO.TS);
  }

  /**
   * ����ʱ���
   * 
   * @param ts ʱ���
   */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SquareOutDetailVO.TS, ts);
  }

  /**
   * ��ȡ��λ������
   * 
   * @return ��λ������
   */
  public String getVchangerate() {
    return (String) this.getAttributeValue(SquareOutDetailVO.VCHANGERATE);
  }

  /**
   * ���õ�λ������
   * 
   * @param vchangerate ��λ������
   */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(SquareOutDetailVO.VCHANGERATE, vchangerate);
  }

  /**
   * ��ȡ�Գ���ⵥ��
   * 
   * @return �Գ���ⵥ��
   */
  public String getVrushbillcode() {
    return (String) this.getAttributeValue(SquareOutDetailVO.VRUSHBILLCODE);
  }

  /**
   * ���öԳ���ⵥ��
   * 
   * @param vrushbillcode �Գ���ⵥ��
   */
  public void setVrushbillcode(String vrushbillcode) {
    this.setAttributeValue(SquareOutDetailVO.VRUSHBILLCODE, vrushbillcode);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SquareOutDetailVO.ENTITYNAME);
    return meta;
  }

  /**
   * ����dr
   * 
   * @param dr
   */
  public void setDr(Integer dr) {
    this.setAttributeValue(SquareInvBVO.DR, dr);
  }

  /**
   * ��� dr
   * 
   * @return dr
   */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(SquareInvBVO.DR);
  }

  /**
   * �������۳��ⵥ�����㵥voID
   * 
   * @param csquareoutbvoid
   */
  public void setCsquareoutbvoid(String csquareoutbvoid) {
    this.setAttributeValue(SquareOutDetailVO.CSQUAREOUTBVOID, csquareoutbvoid);
  }

  /**
   * ������۳��ⵥ�����㵥voID
   * 
   * @return ���۳��ⵥ�����㵥voID
   */
  public String getCsquareoutbvoid() {
    return (String) this.getAttributeValue(SquareOutDetailVO.CSQUAREOUTBVOID);
  }

}
