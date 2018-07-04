package nc.pubitf.so.m38.mmdp.aid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;

/**
 * ȡ��ָ�����������֯+����״̬+����+��������+��������+����޸����ڵ�Ԥ������ϸ�Ľӿڷ���ֵ
 * 
 * @since 6.0
 * @version 2011-12-5 ����03:22:00
 * @author ô��
 */
public class ResultVO implements Serializable {

  private static final long serialVersionUID = 381670671356627470L;

  private Map<String, Object> valuemap = new HashMap<String, Object>();

  /**
   * ��ȡ�ͻ������� ��V63������
   * 
   * @return �ͻ�������
   */
  public String getCcustmaterialid() {
    return (String) this.valuemap.get(PreOrderBVO.CCUSTMATERIALID);
  }

  /**
   * ��ȡ�ͻ�
   * 
   * @return �ͻ�
   */
  public String getCcustomerid() {
    return (String) this.valuemap.get(PreOrderHVO.CCUSTOMERID);
  }

  /**
   * ��ȡ�������°汾
   * 
   * @return �������°汾
   */
  public String getCmaterialid() {
    return (String) this.valuemap.get(PreOrderBVO.CMATERIALID);
  }

  /**
   * ��ȡ���ϱ���
   * 
   * @return ���ϱ���
   */
  public String getCmaterialvid() {
    return (String) this.valuemap.get(PreOrderBVO.CMATERIALVID);
  }

  /**
   * ��ȡԤ��������
   * 
   * @return ���۶�������
   */
  public String getCPreOrderbid() {
    return (String) this.valuemap.get(PreOrderBVO.CPREORDERBID);
  }

  /**
   * ��ȡԤ��������_����
   * 
   * @return ���۶�������_����
   */
  public String getCPreOrderid() {
    return (String) this.valuemap.get(PreOrderBVO.CPREORDERID);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public String getCproductorid() {
    return (String) this.valuemap.get(PreOrderBVO.CPRODUCTORID);
  }

  /**
   * ��ȡ��Ŀ
   * 
   * @return ��Ŀ
   */
  public String getCprojectid() {
    return (String) this.valuemap.get(PreOrderBVO.CPROJECTID);
  }

  /**
   * ��ȡ�к�
   * 
   * @return �к�
   */
  public String getCrowno() {
    return (String) this.valuemap.get(PreOrderBVO.CROWNO);
  }

  /**
   * ��ȡ���������֯���°汾
   * 
   * @return ���������֯���°汾
   */
  public String getCsendstockorgid() {
    return (String) this.valuemap.get(PreOrderBVO.CSENDSTOCKORGID);
  }

  /**
   * ��ȡ���������֯
   * 
   * @return ���������֯
   */
  public String getCsendstockorgvid() {
    return (String) this.valuemap.get(PreOrderBVO.CSENDSTOCKORGVID);
  }

  /**
   * ��ȡ����λ
   * 
   * @return ����λ
   */
  public String getCunitid() {
    return (String) this.valuemap.get(PreOrderBVO.CUNITID);
  }

  /**
   * ��ȡ��Ӧ��
   * 
   * @return ��Ӧ��
   */
  public String getCvendorid() {
    return (String) this.valuemap.get(PreOrderBVO.CVENDORID);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public UFDate getDsenddate() {
    return (UFDate) this.valuemap.get(PreOrderBVO.DSENDDATE);
  }

  /**
   * ��ȡ����״̬
   * 
   * @return ����״̬
   * 
   */
  public Integer getFstatusflag() {
    return (Integer) this.valuemap.get(PreOrderHVO.FSTATUSFLAG);
  }

  /**
   * �ۼư������۶�������
   * 
   * @return �ۼư������۶�������
   */
  public UFDouble getNarrnum() {
    UFDouble narrnum = (UFDouble) this.valuemap.get(PreOrderBVO.NARRNUM);
    return narrnum;
  }

  /**
   * δִ����
   * 
   * @return δִ����
   */
  public UFDouble getNnoarrnum() {
    UFDouble narrnum =
        MathTool.sub((UFDouble) this.valuemap.get(PreOrderBVO.NNUM),
            (UFDouble) this.valuemap.get(PreOrderBVO.NARRNUM));
    return narrnum;
  }

  /**
   * ��ȡ������
   * 
   * @return ������
   */
  public UFDouble getNnum() {
    return (UFDouble) this.valuemap.get(PreOrderBVO.NNUM);
  }

  /**
   * ��ȡ���ݺ�
   * 
   * @return ���ݺ�
   */
  public String getVbillcode() {
    return (String) this.valuemap.get(PreOrderHVO.VBILLCODE);
  }

  /**
   * ��ȡ���ɸ�������1
   * 
   * @return ���ɸ�������1
   */
  public String getVfree1() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE1);
  }

  /**
   * ��ȡ���ɸ�������10
   * 
   * @return ���ɸ�������10
   */
  public String getVfree10() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE10);
  }

  /**
   * ��ȡ���ɸ�������2
   * 
   * @return ���ɸ�������2
   */
  public String getVfree2() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE2);
  }

  /**
   * ��ȡ���ɸ�������3
   * 
   * @return ���ɸ�������3
   */
  public String getVfree3() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE3);
  }

  /**
   * ��ȡ���ɸ�������4
   * 
   * @return ���ɸ�������4
   */
  public String getVfree4() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE4);
  }

  /**
   * ��ȡ���ɸ�������5
   * 
   * @return ���ɸ�������5
   */
  public String getVfree5() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE5);
  }

  /**
   * ��ȡ���ɸ�������6
   * 
   * @return ���ɸ�������6
   */
  public String getVfree6() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE6);
  }

  /**
   * ��ȡ���ɸ�������7
   * 
   * @return ���ɸ�������7
   */
  public String getVfree7() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE7);
  }

  /**
   * ��ȡ���ɸ�������8
   * 
   * @return ���ɸ�������8
   */
  public String getVfree8() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE8);
  }

  /**
   * ��ȡ���ɸ�������9
   * 
   * @return ���ɸ�������9
   */
  public String getVfree9() {
    return (String) this.valuemap.get(PreOrderBVO.VFREE9);
  }

  /**
   * 
   * 
   * @param key
   * @param value
   */
  public void setAttributeValue(String key, Object value) {
    this.valuemap.put(key, value);
  }

  // /**
  // * �����������°汾
  // *
  // * @param cmaterialid �������°汾
  // */
  // public void setCmaterialid(String cmaterialid) {
  // this.setAttributeValue(PreOrderBVO.CMATERIALID, cmaterialid);
  // }
  //
  // /**
  // * �������ϱ���
  // *
  // * @param cmaterialvid ���ϱ���
  // */
  // public void setCmaterialvid(String cmaterialvid) {
  // this.setAttributeValue(PreOrderBVO.CMATERIALVID, cmaterialvid);
  // }
  //
  // public void setCrowno(String crowno) {
  // this.setAttributeValue(PreOrderBVO.CROWNO, crowno);
  // }
  //
  // /**
  // * �������۶�������
  // *
  // * @param cPreOrderbid ���۶�������
  // */
  // public void setCPreOrderbid(String cPreOrderbid) {
  // this.setAttributeValue(PreOrderBVO.CPreOrderBID, cPreOrderbid);
  // }
  //
  // /**
  // * �������۶�������_����
  // *
  // * @param cPreOrderid ���۶�������_����
  // */
  // public void setCPreOrderid(String cPreOrderid) {
  // this.setAttributeValue(PreOrderBVO.CPreOrderID, cPreOrderid);
  // }
  //
  // /**
  // * ���÷��������֯���°汾
  // *
  // * @param csendstockorgid ���������֯���°汾
  // */
  // public void setCsendstockorgid(String csendstockorgid) {
  // this.setAttributeValue(PreOrderBVO.CSENDSTOCKORGID, csendstockorgid);
  // }
  //
  // /**
  // * ���÷��������֯
  // *
  // * @param csendstockorgvid ���������֯
  // */
  // public void setCsendstockorgvid(String csendstockorgvid) {
  // this.setAttributeValue(PreOrderBVO.CSENDSTOCKORGVID, csendstockorgvid);
  // }
  //
  // /**
  // * ��������λ
  // *
  // * @param cunitid ����λ
  // */
  // public void setCunitid(String cunitid) {
  // this.setAttributeValue(PreOrderBVO.CUNITID, cunitid);
  // }
  //
  // /**
  // * ���÷�������
  // *
  // * @param dsenddate ��������
  // */
  // public void setDsenddate(UFDate dsenddate) {
  // this.setAttributeValue(PreOrderBVO.DSENDDATE, dsenddate);
  // }
  //
  // /**
  // * ���õ���״̬
  // *
  // * @param fstatusflag ����״̬
  // * @see BillStatus
  // */
  // public void setFstatusflag(Integer fstatusflag) {
  // this.setAttributeValue(PreOrderHVO.FSTATUSFLAG, fstatusflag);
  // }
  //
  // /**
  // * �����ۼư����빺������
  // *
  // * @param narrangepoappnum �ۼư����빺������
  // */
  // public void setNarrangepoappnum(UFDouble narrangepoappnum) {
  // this.setAttributeValue(PreOrderBVO.NARRANGEPOAPPNUM, narrangepoappnum);
  // }
  //
  // /**
  // * �����ۼư��Ųɹ���������
  // *
  // * @param narrangeponum �ۼư��Ųɹ���������
  // */
  // public void setNarrangeponum(UFDouble narrangeponum) {
  // this.setAttributeValue(PreOrderBVO.NARRANGEPONUM, narrangeponum);
  // }
  //
  // /**
  // * �����ۼư���ί�ⶩ������
  // *
  // * @param narrangescornum �ۼư���ί�ⶩ������
  // */
  // public void setNarrangescornum(UFDouble narrangescornum) {
  // this.setAttributeValue(PreOrderBVO.NARRANGESCORNUM, narrangescornum);
  // }
  //
  // /**
  // * �����ۼư��ŵ�����������
  // *
  // * @param narrangetoappnum �ۼư��ŵ�����������
  // */
  // public void setNarrangetoappnum(UFDouble narrangetoappnum) {
  // this.setAttributeValue(PreOrderBVO.NARRANGETOAPPNUM, narrangetoappnum);
  // }
  //
  // /**
  // * �����ۼư��ŵ�����������
  // *
  // * @param narrangetoornum �ۼư��ŵ�����������
  // */
  // public void setNarrangetoornum(UFDouble narrangetoornum) {
  // this.setAttributeValue(PreOrderBVO.NARRANGETOORNUM, narrangetoornum);
  // }
  //
  // /**
  // * ����������
  // *
  // * @param nnum ������
  // */
  // public void setNnum(UFDouble nnum) {
  // this.setAttributeValue(PreOrderBVO.NNUM, nnum);
  // }
  //
  // /**
  // * ����Ԥ������
  // *
  // * @param nreqrsnum Ԥ������
  // */
  // public void setNreqrsnum(UFDouble nreqrsnum) {
  // this.setAttributeValue(PreOrderBVO.NREQRSNUM, nreqrsnum);
  // }
  //
  // /**
  // * �����ۼƳ�������
  // *
  // * @param ntotaloutnum �ۼƳ�������
  // */
  // public void setNtotaloutnum(UFDouble ntotaloutnum) {
  // this.setAttributeValue(PreOrderBVO.NTOTALOUTNUM, ntotaloutnum);
  // }
  //
  // public void setVbillcode(String vbillcode) {
  // this.setAttributeValue(PreOrderHVO.VBILLCODE, vbillcode);
  // }
  //
  // /**
  // * �������ɸ�������1
  // *
  // * @param vfree1 ���ɸ�������1
  // */
  // public void setVfree1(String vfree1) {
  // this.setAttributeValue(PreOrderBVO.VFREE1, vfree1);
  // }
  //
  // /**
  // * �������ɸ�������10
  // *
  // * @param vfree10 ���ɸ�������10
  // */
  // public void setVfree10(String vfree10) {
  // this.setAttributeValue(PreOrderBVO.VFREE10, vfree10);
  // }
  //
  // /**
  // * �������ɸ�������2
  // *
  // * @param vfree2 ���ɸ�������2
  // */
  // public void setVfree2(String vfree2) {
  // this.setAttributeValue(PreOrderBVO.VFREE2, vfree2);
  // }
  //
  // /**
  // * �������ɸ�������3
  // *
  // * @param vfree3 ���ɸ�������3
  // */
  // public void setVfree3(String vfree3) {
  // this.setAttributeValue(PreOrderBVO.VFREE3, vfree3);
  // }
  //
  // /**
  // * �������ɸ�������4
  // *
  // * @param vfree4 ���ɸ�������4
  // */
  // public void setVfree4(String vfree4) {
  // this.setAttributeValue(PreOrderBVO.VFREE4, vfree4);
  // }
  //
  // /**
  // * �������ɸ�������5
  // *
  // * @param vfree5 ���ɸ�������5
  // */
  // public void setVfree5(String vfree5) {
  // this.setAttributeValue(PreOrderBVO.VFREE5, vfree5);
  // }
  //
  // /**
  // * �������ɸ�������6
  // *
  // * @param vfree6 ���ɸ�������6
  // */
  // public void setVfree6(String vfree6) {
  // this.setAttributeValue(PreOrderBVO.VFREE6, vfree6);
  // }
  //
  // /**
  // * �������ɸ�������7
  // *
  // * @param vfree7 ���ɸ�������7
  // */
  // public void setVfree7(String vfree7) {
  // this.setAttributeValue(PreOrderBVO.VFREE7, vfree7);
  // }
  //
  // /**
  // * �������ɸ�������8
  // *
  // * @param vfree8 ���ɸ�������8
  // */
  // public void setVfree8(String vfree8) {
  // this.setAttributeValue(PreOrderBVO.VFREE8, vfree8);
  // }
  //
  // /**
  // * �������ɸ�������9
  // *
  // * @param vfree9 ���ɸ�������9
  // */
  // public void setVfree9(String vfree9) {
  // this.setAttributeValue(PreOrderBVO.VFREE9, vfree9);
  // }

}
