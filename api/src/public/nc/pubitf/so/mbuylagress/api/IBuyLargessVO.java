package nc.pubitf.so.mbuylagress.api;

import java.io.Serializable;

/**
 * @description
 * �������ò�ѯAPI�������쳣����
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-12 ����10:55:51
 * @author ����
 */
public interface IBuyLargessVO extends Serializable {
  
  /**
   * ��������id
   */
  public static final String PK_BUYLARGESS = "pk_buylargess";
  /**
   * ������֯
   */
  public static final String PK_ORG = "pk_org";
  /**
   * ����
   */
  public static final String PK_GROUP = "pk_group";
  /**
   * ���ϱ���
   */
  public static final String CBUYMARID = "cbuymarid";
  /**
   * ��λ
   */
  public static final String CBUYUNITID = "cbuyunitid";
  /**
   * ���ϻ�������
   */
  public static final String PK_MARBASCLASS = "pk_marbasclass";
  /**
   * �������۷���
   */
  public static final String PK_MARSALECLASS = "pk_marsaleclass";
  /**
   * �ͻ�
   */
  public static final String PK_CUSTOMER = "pk_customer";
  /**
   * �ͻ���������
   */
  public static final String PK_CUSTCLASS = "pk_custclass";
  /**
   * �ͻ����۷���
   */
  public static final String PK_CUSTSALECLASS = "pk_custsaleclass";
  /**
   * ��������
   */
  public static final String NBUYNUM = "nbuynum";
  /**
   * ����
   */
  public static final String PK_CURRINFO = "pk_currinfo";
  /**
   * �����¼�
   */
  public static final String ISLOW = "islow";
  /**
   * ������
   */
  public static final String CPRIORITYCODE = "cprioritycode";
  /**
   * ��������
   */
  public static final String CPROMOTTYPEID = "cpromottypeid";
  /**
   * Ӫ���
   */
  public static final String CMARKETACTID = "cmarketactid";
  /**
   * vostatus
   */
  public static final String STATUS = "status";
  /**
   * dr
   */
  public static final String DR = "dr";
  /**
   * ts
   */
  public static final String TS = "ts";
  /**
   * �����ӱ�VO.�����ӱ�id
   */
  public static final String PK_BUYLARGESS_B_PK_BUYLARGESS_B = "pk_buylargess_b.pk_buylargess_b";
  /**
   * �����ӱ�VO.���ϱ���
   */
  public static final String PK_BUYLARGESS_B_PK_MATERIAL = "pk_buylargess_b.pk_material";
  /**
   * �����ӱ�VO.��λ
   */
  public static final String PK_BUYLARGESS_B_PK_MEASDOC = "pk_buylargess_b.pk_measdoc";
  /**
   * �����ӱ�VO.��������
   */
  public static final String PK_BUYLARGESS_B_NNUM = "pk_buylargess_b.nnum";
  /**
   * �����ӱ�VO.����
   */
  public static final String PK_BUYLARGESS_B_NPRICE = "pk_buylargess_b.nprice";
  /**
   * �����ӱ�VO.���
   */
  public static final String PK_BUYLARGESS_B_NMNY = "pk_buylargess_b.nmny";
  /**
   * �����ӱ�VO.��������
   */
  public static final String PK_BUYLARGESS_B_FTOPLIMITTYPE = "pk_buylargess_b.ftoplimittype";
  /**
   * �����ӱ�VO.����ֵ
   */
  public static final String PK_BUYLARGESS_B_NTOPLIMITVALUE = "pk_buylargess_b.ntoplimitvalue";
  /**
   * �����ӱ�VO.��ʼ����
   */
  public static final String PK_BUYLARGESS_B_DBEGDATE = "pk_buylargess_b.dbegdate";
  /**
   * �����ӱ�VO.��ֹ����
   */
  public static final String PK_BUYLARGESS_B_DENDDATE = "pk_buylargess_b.denddate";
  /**
   * �����ӱ�VO.����
   */
  public static final String PK_BUYLARGESS_B_PK_GROUP = "pk_buylargess_b.pk_group";
  /**
   * �����ӱ�VO.vostatus
   */
  public static final String PK_BUYLARGESS_B_STATUS = "pk_buylargess_b.status";
  /**
   * �����ӱ�VO.dr
   */
  public static final String PK_BUYLARGESS_B_DR = "pk_buylargess_b.dr";
  /**
   * �����ӱ�VO.ts
   */
  public static final String PK_BUYLARGESS_B_TS = "pk_buylargess_b.ts";
}


