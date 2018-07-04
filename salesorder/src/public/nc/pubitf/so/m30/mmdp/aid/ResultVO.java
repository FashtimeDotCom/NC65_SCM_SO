package nc.pubitf.so.m30.mmdp.aid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * ȡ��ָ�����������֯+����״̬+����+��������+��������+����޸����ڵ����۶�����ϸ�Ľӿڷ���ֵ
 * 
 * @since 6.0
 * @version 2011-12-5 ����03:22:00
 * @author ô��
 */
public class ResultVO implements Serializable {

  private static final long serialVersionUID = 381670671356627470L;

  private Map<String, Object> valuemap = new HashMap<String, Object>();

  /**
   * ������������ ��V65������
   * 
   * @return ������������
   */
  public String getCbilltranstypeid() {
    return (String) this.valuemap.get(SaleOrderHVO.CTRANTYPEID);
  }
  
  /**
   * ��ȡ�ͻ������� ��V63������
   * 
   * @return �ͻ�������
   */
  public String getCcustmaterialid() {
    return (String) this.valuemap.get(SaleOrderBVO.CCUSTMATERIALID);
  }

  /**
   * ��ȡ�ͻ�
   * 
   * @return �ͻ�
   */
  public String getCcustomerid() {
    return (String) this.valuemap.get(SaleOrderHVO.CCUSTOMERID);
  }

  /**
   * ��ȡ�������°汾
   * 
   * @return �������°汾
   */
  public String getCmaterialid() {
    return (String) this.valuemap.get(SaleOrderBVO.CMATERIALID);
  }

  /**
   * ��ȡ���ϱ���
   * 
   * @return ���ϱ���
   */
  public String getCmaterialvid() {
    return (String) this.valuemap.get(SaleOrderBVO.CMATERIALVID);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public String getCproductorid() {
    return (String) this.valuemap.get(SaleOrderBVO.CPRODUCTORID);
  }

  /**
   * ��ȡ��Ŀ
   * 
   * @return ��Ŀ
   */
  public String getCprojectid() {
    return (String) this.valuemap.get(SaleOrderBVO.CPROJECTID);
  }

  /**
   * ��ȡ�к�
   * 
   * @return �к�
   */
  public String getCrowno() {
    return (String) this.valuemap.get(SaleOrderBVO.CROWNO);
  }

  /**
   * ��ȡ���۶�������
   * 
   * @return ���۶�������
   */
  public String getCsaleorderbid() {
    return (String) this.valuemap.get(SaleOrderBVO.CSALEORDERBID);
  }

  /**
   * ��ȡ���۶�������_����
   * 
   * @return ���۶�������_����
   */
  public String getCsaleorderid() {
    return (String) this.valuemap.get(SaleOrderBVO.CSALEORDERID);
  }

  /**
   * ��ȡ���������֯���°汾
   * 
   * @return ���������֯���°汾
   */
  public String getCsendstockorgid() {
    return (String) this.valuemap.get(SaleOrderBVO.CSENDSTOCKORGID);
  }

  /**
   * ��ȡ���������֯
   * 
   * @return ���������֯
   */
  public String getCsendstockorgvid() {
    return (String) this.valuemap.get(SaleOrderBVO.CSENDSTOCKORGVID);
  }

  /**
   * ��ȡ����λ
   * 
   * @return ����λ
   */
  public String getCunitid() {
    return (String) this.valuemap.get(SaleOrderBVO.CUNITID);
  }

  /**
   * ��ȡ��Ӧ��
   * 
   * @return ��Ӧ��
   */
  public String getCvendorid() {
    return (String) this.valuemap.get(SaleOrderBVO.CVENDORID);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public UFDate getDsenddate() {
    return (UFDate) this.valuemap.get(SaleOrderBVO.DSENDDATE);
  }

  /**
   * ��ȡ����״̬
   * 
   * @return ����״̬
   * @see BillStatus
   */
  public Integer getFstatusflag() {
    return (Integer) this.valuemap.get(SaleOrderHVO.FSTATUSFLAG);
  }

  /**
   * ��ȡ�ۼư���������������
   * 
   * @return �ۼư���������������
   */
  public UFDouble getNarrangemonum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NARRANGEMONUM);
  }

  /**
   * ��ȡ�ۼư����빺������
   * 
   * @return �ۼư����빺������
   */
  public UFDouble getNarrangepoappnum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NARRANGEPOAPPNUM);
  }

  /**
   * ��ȡ�ۼư��Ųɹ���������
   * 
   * @return �ۼư��Ųɹ���������
   */
  public UFDouble getNarrangeponum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NARRANGEPONUM);
  }

  /**
   * ��ȡ�ۼư���ί�ⶩ������
   * 
   * @return �ۼư���ί�ⶩ������
   */
  public UFDouble getNarrangescornum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NARRANGESCORNUM);
  }

  /**
   * ��ȡ�ۼư��ŵ�����������
   * 
   * @return �ۼư��ŵ�����������
   */
  public UFDouble getNarrangetoappnum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NARRANGETOAPPNUM);
  }

  /**
   * ��ȡ�ۼư��ŵ�����������
   * 
   * @return �ۼư��ŵ�����������
   */
  public UFDouble getNarrangetoornum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NARRANGETOORNUM);
  }

  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public UFDouble getNnum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NNUM);
  }

  /**
   * ��ȡԤ������
   * 
   * @return Ԥ������
   */
  public UFDouble getNreqrsnum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NREQRSNUM);
  }

  /**
   * ��ȡ�ۼƳ�������
   * 
   * @return �ۼƳ�������
   */
  public UFDouble getNtotaloutnum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NTOTALOUTNUM);
  }

  /**
   * ��ȡ�ۼ����ɼƻ���������
   * 
   * @return �ۼ����ɼƻ���������
   */
  public UFDouble getNtotalplonum() {
    return (UFDouble) this.valuemap.get(SaleOrderBVO.NTOTALPLONUM);
  }

  /**
   * ��ȡ���ݺ�
   * 
   * @return ���ݺ�
   */
  public String getVbillcode() {
    return (String) this.valuemap.get(SaleOrderHVO.VBILLCODE);
  }

  /**
   * ��ȡ���ɸ�������1
   * 
   * @return ���ɸ�������1
   */
  public String getVfree1() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE1);
  }

  /**
   * ��ȡ���ɸ�������10
   * 
   * @return ���ɸ�������10
   */
  public String getVfree10() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE10);
  }

  /**
   * ��ȡ���ɸ�������2
   * 
   * @return ���ɸ�������2
   */
  public String getVfree2() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE2);
  }

  /**
   * ��ȡ���ɸ�������3
   * 
   * @return ���ɸ�������3
   */
  public String getVfree3() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE3);
  }

  /**
   * ��ȡ���ɸ�������4
   * 
   * @return ���ɸ�������4
   */
  public String getVfree4() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE4);
  }

  /**
   * ��ȡ���ɸ�������5
   * 
   * @return ���ɸ�������5
   */
  public String getVfree5() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE5);
  }

  /**
   * ��ȡ���ɸ�������6
   * 
   * @return ���ɸ�������6
   */
  public String getVfree6() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE6);
  }

  /**
   * ��ȡ���ɸ�������7
   * 
   * @return ���ɸ�������7
   */
  public String getVfree7() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE7);
  }

  /**
   * ��ȡ���ɸ�������8
   * 
   * @return ���ɸ�������8
   */
  public String getVfree8() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE8);
  }

  /**
   * ��ȡ���ɸ�������9
   * 
   * @return ���ɸ�������9
   */
  public String getVfree9() {
    return (String) this.valuemap.get(SaleOrderBVO.VFREE9);
  }

  public void setAttributeValue(String key, Object value) {
    this.valuemap.put(key, value);
  }

  // /**
  // * �����������°汾
  // *
  // * @param cmaterialid �������°汾
  // */
  // public void setCmaterialid(String cmaterialid) {
  // this.setAttributeValue(SaleOrderBVO.CMATERIALID, cmaterialid);
  // }
  //
  // /**
  // * �������ϱ���
  // *
  // * @param cmaterialvid ���ϱ���
  // */
  // public void setCmaterialvid(String cmaterialvid) {
  // this.setAttributeValue(SaleOrderBVO.CMATERIALVID, cmaterialvid);
  // }
  //
  // public void setCrowno(String crowno) {
  // this.setAttributeValue(SaleOrderBVO.CROWNO, crowno);
  // }
  //
  // /**
  // * �������۶�������
  // *
  // * @param csaleorderbid ���۶�������
  // */
  // public void setCsaleorderbid(String csaleorderbid) {
  // this.setAttributeValue(SaleOrderBVO.CSALEORDERBID, csaleorderbid);
  // }
  //
  // /**
  // * �������۶�������_����
  // *
  // * @param csaleorderid ���۶�������_����
  // */
  // public void setCsaleorderid(String csaleorderid) {
  // this.setAttributeValue(SaleOrderBVO.CSALEORDERID, csaleorderid);
  // }
  //
  // /**
  // * ���÷��������֯���°汾
  // *
  // * @param csendstockorgid ���������֯���°汾
  // */
  // public void setCsendstockorgid(String csendstockorgid) {
  // this.setAttributeValue(SaleOrderBVO.CSENDSTOCKORGID, csendstockorgid);
  // }
  //
  // /**
  // * ���÷��������֯
  // *
  // * @param csendstockorgvid ���������֯
  // */
  // public void setCsendstockorgvid(String csendstockorgvid) {
  // this.setAttributeValue(SaleOrderBVO.CSENDSTOCKORGVID, csendstockorgvid);
  // }
  //
  // /**
  // * ��������λ
  // *
  // * @param cunitid ����λ
  // */
  // public void setCunitid(String cunitid) {
  // this.setAttributeValue(SaleOrderBVO.CUNITID, cunitid);
  // }
  //
  // /**
  // * ���÷�������
  // *
  // * @param dsenddate ��������
  // */
  // public void setDsenddate(UFDate dsenddate) {
  // this.setAttributeValue(SaleOrderBVO.DSENDDATE, dsenddate);
  // }
  //
  // /**
  // * ���õ���״̬
  // *
  // * @param fstatusflag ����״̬
  // * @see BillStatus
  // */
  // public void setFstatusflag(Integer fstatusflag) {
  // this.setAttributeValue(SaleOrderHVO.FSTATUSFLAG, fstatusflag);
  // }
  //
  // /**
  // * �����ۼư����빺������
  // *
  // * @param narrangepoappnum �ۼư����빺������
  // */
  // public void setNarrangepoappnum(UFDouble narrangepoappnum) {
  // this.setAttributeValue(SaleOrderBVO.NARRANGEPOAPPNUM, narrangepoappnum);
  // }
  //
  // /**
  // * �����ۼư��Ųɹ���������
  // *
  // * @param narrangeponum �ۼư��Ųɹ���������
  // */
  // public void setNarrangeponum(UFDouble narrangeponum) {
  // this.setAttributeValue(SaleOrderBVO.NARRANGEPONUM, narrangeponum);
  // }
  //
  // /**
  // * �����ۼư���ί�ⶩ������
  // *
  // * @param narrangescornum �ۼư���ί�ⶩ������
  // */
  // public void setNarrangescornum(UFDouble narrangescornum) {
  // this.setAttributeValue(SaleOrderBVO.NARRANGESCORNUM, narrangescornum);
  // }
  //
  // /**
  // * �����ۼư��ŵ�����������
  // *
  // * @param narrangetoappnum �ۼư��ŵ�����������
  // */
  // public void setNarrangetoappnum(UFDouble narrangetoappnum) {
  // this.setAttributeValue(SaleOrderBVO.NARRANGETOAPPNUM, narrangetoappnum);
  // }
  //
  // /**
  // * �����ۼư��ŵ�����������
  // *
  // * @param narrangetoornum �ۼư��ŵ�����������
  // */
  // public void setNarrangetoornum(UFDouble narrangetoornum) {
  // this.setAttributeValue(SaleOrderBVO.NARRANGETOORNUM, narrangetoornum);
  // }
  //
  // /**
  // * ����������
  // *
  // * @param nnum ������
  // */
  // public void setNnum(UFDouble nnum) {
  // this.setAttributeValue(SaleOrderBVO.NNUM, nnum);
  // }
  //
  // /**
  // * ����Ԥ������
  // *
  // * @param nreqrsnum Ԥ������
  // */
  // public void setNreqrsnum(UFDouble nreqrsnum) {
  // this.setAttributeValue(SaleOrderBVO.NREQRSNUM, nreqrsnum);
  // }
  //
  // /**
  // * �����ۼƳ�������
  // *
  // * @param ntotaloutnum �ۼƳ�������
  // */
  // public void setNtotaloutnum(UFDouble ntotaloutnum) {
  // this.setAttributeValue(SaleOrderBVO.NTOTALOUTNUM, ntotaloutnum);
  // }
  //
  // public void setVbillcode(String vbillcode) {
  // this.setAttributeValue(SaleOrderHVO.VBILLCODE, vbillcode);
  // }
  //
  // /**
  // * �������ɸ�������1
  // *
  // * @param vfree1 ���ɸ�������1
  // */
  // public void setVfree1(String vfree1) {
  // this.setAttributeValue(SaleOrderBVO.VFREE1, vfree1);
  // }
  //
  // /**
  // * �������ɸ�������10
  // *
  // * @param vfree10 ���ɸ�������10
  // */
  // public void setVfree10(String vfree10) {
  // this.setAttributeValue(SaleOrderBVO.VFREE10, vfree10);
  // }
  //
  // /**
  // * �������ɸ�������2
  // *
  // * @param vfree2 ���ɸ�������2
  // */
  // public void setVfree2(String vfree2) {
  // this.setAttributeValue(SaleOrderBVO.VFREE2, vfree2);
  // }
  //
  // /**
  // * �������ɸ�������3
  // *
  // * @param vfree3 ���ɸ�������3
  // */
  // public void setVfree3(String vfree3) {
  // this.setAttributeValue(SaleOrderBVO.VFREE3, vfree3);
  // }
  //
  // /**
  // * �������ɸ�������4
  // *
  // * @param vfree4 ���ɸ�������4
  // */
  // public void setVfree4(String vfree4) {
  // this.setAttributeValue(SaleOrderBVO.VFREE4, vfree4);
  // }
  //
  // /**
  // * �������ɸ�������5
  // *
  // * @param vfree5 ���ɸ�������5
  // */
  // public void setVfree5(String vfree5) {
  // this.setAttributeValue(SaleOrderBVO.VFREE5, vfree5);
  // }
  //
  // /**
  // * �������ɸ�������6
  // *
  // * @param vfree6 ���ɸ�������6
  // */
  // public void setVfree6(String vfree6) {
  // this.setAttributeValue(SaleOrderBVO.VFREE6, vfree6);
  // }
  //
  // /**
  // * �������ɸ�������7
  // *
  // * @param vfree7 ���ɸ�������7
  // */
  // public void setVfree7(String vfree7) {
  // this.setAttributeValue(SaleOrderBVO.VFREE7, vfree7);
  // }
  //
  // /**
  // * �������ɸ�������8
  // *
  // * @param vfree8 ���ɸ�������8
  // */
  // public void setVfree8(String vfree8) {
  // this.setAttributeValue(SaleOrderBVO.VFREE8, vfree8);
  // }
  //
  // /**
  // * �������ɸ�������9
  // *
  // * @param vfree9 ���ɸ�������9
  // */
  // public void setVfree9(String vfree9) {
  // this.setAttributeValue(SaleOrderBVO.VFREE9, vfree9);
  // }

  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public String getCmffileid() {
    return (String) this.valuemap.get(SaleOrderBVO.CMFFILEID);
  }
}
