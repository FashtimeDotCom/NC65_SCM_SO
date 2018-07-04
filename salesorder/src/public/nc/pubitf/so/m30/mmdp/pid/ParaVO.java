package nc.pubitf.so.m30.mmdp.pid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 
 * @since 6.0
 * @version 2011-12-5 ����02:52:18
 * @author ô��
 */
public class ParaVO implements Serializable {

  public enum ParaVOKeyEnum {
    CCUSTOMERID(SaleOrderHVO.CCUSTOMERID),
    CMATERIALID(SaleOrderBVO.CMATERIALID),
    CPRODUCTORID(SaleOrderBVO.CPRODUCTORID),
    CPROJECTID(SaleOrderBVO.CPROJECTID),
    CSENDSTOCKORGID(SaleOrderBVO.CSENDSTOCKORGID),
    CVENDORID(SaleOrderBVO.CVENDORID),
    VFREE1(SaleOrderBVO.VFREE1),
    VFREE10(SaleOrderBVO.VFREE10),
    VFREE2(SaleOrderBVO.VFREE2),
    VFREE3(SaleOrderBVO.VFREE3),
    VFREE4(SaleOrderBVO.VFREE4),
    VFREE5(SaleOrderBVO.VFREE5),
    VFREE6(SaleOrderBVO.VFREE6),
    VFREE7(SaleOrderBVO.VFREE7),
    VFREE8(SaleOrderBVO.VFREE8),
    VFREE9(SaleOrderBVO.VFREE9),
    CMFFILEID(SaleOrderBVO.CMFFILEID);

    private String keyname;

    private ParaVOKeyEnum(String value) {
      this.keyname = value;
    }

    public String getValue() {
      return this.keyname;
    }
  }

  // /** �����ͻ� */
  // public static final String CCUSTOMERID = SaleOrderHVO.CCUSTOMERID;
  //
  // /** ����ID */
  // public static final String CMATERIALID = SaleOrderBVO.CMATERIALID;
  //
  // /** �������� */
  // public static final String CPRODUCTORID = SaleOrderBVO.CPRODUCTORID;
  //
  // /** ��Ŀ */
  // public static final String CPROJECTID = SaleOrderBVO.CPROJECTID;
  //
  // /** ������֯ID */
  // public static final String CSENDSTOCKORGID = SaleOrderBVO.CSENDSTOCKORGID;
  //
  // /** ��Ӧ�� */
  // public static final String CVENDORID = SaleOrderBVO.CVENDORID;
  //
  // /** ���ɸ������� */
  // public static final String VFREE1 = SaleOrderBVO.VBDEF1;
  //
  // /** ���ɸ������� */
  // public static final String VFREE10 = SaleOrderBVO.VBDEF10;
  //
  // /** ���ɸ������� */
  // public static final String VFREE2 = SaleOrderBVO.VBDEF2;
  //
  // /** ���ɸ������� */
  // public static final String VFREE3 = SaleOrderBVO.VBDEF3;
  //
  // /** ���ɸ������� */
  // public static final String VFREE4 = SaleOrderBVO.VBDEF4;
  //
  // /** ���ɸ������� */
  // public static final String VFREE5 = SaleOrderBVO.VBDEF5;
  //
  // /** ���ɸ������� */
  // public static final String VFREE6 = SaleOrderBVO.VBDEF6;
  //
  // /** ���ɸ������� */
  // public static final String VFREE7 = SaleOrderBVO.VBDEF7;
  //
  // /** ���ɸ������� */
  // public static final String VFREE8 = SaleOrderBVO.VBDEF8;
  //
  // /** ���ɸ������� */
  // public static final String VFREE9 = SaleOrderBVO.VBDEF9;

  private static final long serialVersionUID = 6137272069299049888L;

  private Map<String, String> valuemap = new HashMap<String, String>();

  public Object getAttributeValue(String key) {
    return this.valuemap.get(key);
  }

  /**
   * ���ÿͻ�
   * 
   * @param ccustomerid �ͻ�
   */
  public void setCcustomerid(String ccustomerid) {
    this.valuemap.put(SaleOrderHVO.CCUSTOMERID, ccustomerid);
  }

  /**
   * �����������°汾
   * 
   * @param cmaterialid �������°汾
   */
  public void setCmaterialid(String cmaterialid) {
    this.valuemap.put(SaleOrderBVO.CMATERIALID, cmaterialid);
  }

  /**
   * ������������
   * 
   * @param cproductorid ��������
   */
  public void setCproductorid(String cproductorid) {
    this.valuemap.put(SaleOrderBVO.CPRODUCTORID, cproductorid);
  }

  /**
   * ������Ŀ
   * 
   * @param cprojectid ��Ŀ
   */
  public void setCprojectid(String cprojectid) {
    this.valuemap.put(SaleOrderBVO.CPROJECTID, cprojectid);
  }

  /**
   * ���÷��������֯���°汾
   * 
   * @param csendstockorgid ���������֯���°汾
   */
  public void setCsendstockorgid(String csendstockorgid) {
    this.valuemap.put(SaleOrderBVO.CSENDSTOCKORGID, csendstockorgid);
  }

  /**
   * ���ù�Ӧ��
   * 
   * @param cvendorid ��Ӧ��
   */
  public void setCvendorid(String cvendorid) {
    this.valuemap.put(SaleOrderBVO.CVENDORID, cvendorid);
  }

  /**
   * �������ɸ�������1
   * 
   * @param vfree1 ���ɸ�������1
   */
  public void setVfree1(String vfree1) {
    this.valuemap.put(SaleOrderBVO.VFREE1, vfree1);
  }

  /**
   * �������ɸ�������10
   * 
   * @param vfree10 ���ɸ�������10
   */
  public void setVfree10(String vfree10) {
    this.valuemap.put(SaleOrderBVO.VFREE10, vfree10);
  }

  /**
   * �������ɸ�������2
   * 
   * @param vfree2 ���ɸ�������2
   */
  public void setVfree2(String vfree2) {
    this.valuemap.put(SaleOrderBVO.VFREE2, vfree2);
  }

  /**
   * �������ɸ�������3
   * 
   * @param vfree3 ���ɸ�������3
   */
  public void setVfree3(String vfree3) {
    this.valuemap.put(SaleOrderBVO.VFREE3, vfree3);
  }

  /**
   * �������ɸ�������4
   * 
   * @param vfree4 ���ɸ�������4
   */
  public void setVfree4(String vfree4) {
    this.valuemap.put(SaleOrderBVO.VFREE4, vfree4);
  }

  /**
   * �������ɸ�������5
   * 
   * @param vfree5 ���ɸ�������5
   */
  public void setVfree5(String vfree5) {
    this.valuemap.put(SaleOrderBVO.VFREE5, vfree5);
  }

  /**
   * �������ɸ�������6
   * 
   * @param vfree6 ���ɸ�������6
   */
  public void setVfree6(String vfree6) {
    this.valuemap.put(SaleOrderBVO.VFREE6, vfree6);
  }

  /**
   * �������ɸ�������7
   * 
   * @param vfree7 ���ɸ�������7
   */
  public void setVfree7(String vfree7) {
    this.valuemap.put(SaleOrderBVO.VFREE7, vfree7);
  }

  /**
   * �������ɸ�������8
   * 
   * @param vfree8 ���ɸ�������8
   */
  public void setVfree8(String vfree8) {
    this.valuemap.put(SaleOrderBVO.VFREE8, vfree8);
  }

  /**
   * �������ɸ�������9
   * 
   * @param vfree9 ���ɸ�������9
   */
  public void setVfree9(String vfree9) {
    this.valuemap.put(SaleOrderBVO.VFREE9, vfree9);
  }

  /**
   * ����������
   * 
   * @param cmffileid ������
   */
  public void setCmffileid(String cmffileid) {
    this.valuemap.put(SaleOrderBVO.CMFFILEID, cmffileid);
  }

}
