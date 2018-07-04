package nc.pubitf.so.m30.mmdp.sop;

import java.io.Serializable;
import java.util.List;

import nc.vo.pub.lang.UFDate;

/**
 * 
 * @since 6.0
 * @version 2011-12-5 ����02:51:40
 * @author ô��
 */
public class ParaMMVO implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * ��ʼ����
   */
  private UFDate cbegindate;

  /**
   * ��������
   */
  private UFDate cenddate;

  /**
   *
   */
  private List<ParaVO> paras;

  public UFDate getCbegindate() {
    return this.cbegindate;
  }

  public UFDate getCenddate() {
    return this.cenddate;
  }

  public List<ParaVO> getParas() {
    return this.paras;
  }

  public void setCbegindate(UFDate cbegindate) {
    this.cbegindate = cbegindate;
  }

  public void setCenddate(UFDate cenddate) {
    this.cenddate = cenddate;
  }

  public void setParas(List<ParaVO> paras) {
    this.paras = paras;
  }

}
