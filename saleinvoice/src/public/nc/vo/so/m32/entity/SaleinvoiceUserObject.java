package nc.vo.so.m32.entity;

import java.io.Serializable;

import nc.vo.so.m30.entity.OffsetTempVO;


/**
 * ���۷�Ʊǰ̨����̫���ݵ����ݽṹ
 * 
 * @since 6.36
 * @version 2015-6-3 ����6:33:03
 * @author ����
 */
public class SaleinvoiceUserObject  implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = -3350140407540789363L;


  /**
   * ��ֲ����Ļ���
   */
  private  OffsetTempVO tempvo;
  

  /**
   * �����Ƿ��ǰ̨���汣�棬�������ⲿ����ƽ̨����ģ����ǵ�Ч�����⣬ǰ̨���汣��ĵ��ݲ�����Ч�ʼ�飩
   */
  private boolean isclientsave=false;
  
  
  public OffsetTempVO getTempvo() {
    return tempvo;
  }


  
  public void setTempvo(OffsetTempVO tempvo) {
    this.tempvo = tempvo;
  }

  
  /**
   * @return ��ȡ�����Ƿ��ǰ̨���汣���
   */
  public boolean isIsclientsave() {
    return isclientsave;
  }

  
  /**
   * ���õ����Ƿ��ǰ̨���汣���
   * @param isclientsave
   */
  public void setIsclientsave(boolean isclientsave) {
    this.isclientsave = isclientsave;
  }
  

}
