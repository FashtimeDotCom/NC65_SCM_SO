package nc.vo.so.m4331.entity;

import java.io.Serializable;


/**
 * ������ǰ̨����̨���ݲ��������ݽṹ
 * 
 * @since 6.36
 * @version 2015-6-3 ����4:06:51
 * @author ����
 */
public class DeliveryUserObject  implements Serializable{
  

  /**
   * 
   */
  private static final long serialVersionUID = 6975292130693340847L;
  /**
   * �������Ƿ��ǰ̨���汣�棬�������ⲿ����ƽ̨����ģ����ǵ�Ч�����⣬ǰ̨���汣��ĵ��ݲ�����Ч�ʼ�飩
   */
  private boolean isclientsave=false;

  
  /**
   * @return ��ȡ�������Ƿ��ǰ̨���汣���
   */
  public boolean isIsclientsave() {
    return isclientsave;
  }

  
  /**
   * ���÷������Ƿ��ǰ̨���汣���
   * @param isclientsave
   */
  public void setIsclientsave(boolean isclientsave) {
    this.isclientsave = isclientsave;
  }

}
