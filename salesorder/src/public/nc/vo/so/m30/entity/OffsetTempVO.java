package nc.vo.so.m30.entity;

import java.io.Serializable;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;

/**
 * ���滺��ĳ�ֶ���
 * 
 * @since 6.0
 * @version 2010-12-10 ����03:23:27
 * @author ô��
 */
public class OffsetTempVO implements Serializable {
  /**
   * VersionUID
   */
  private static final long serialVersionUID = 4461020823338021748L;

  /** ��ֹ�ϵ�� ���۷��õ��ӱ�IDΪkey����ֽ��Ϊvalue */
  private Map<String, UFDouble> hmArsubRelation;

  /** �Ƿ���ȡ����� */
  private boolean isCancelOffset;

  /**
   * ��ֹ�ϵ
   * 
   * @return ��ֹ�ϵ
   */
  public Map<String, UFDouble> getHmArsubRelation() {
    return this.hmArsubRelation;
  }

  /**
   * �Ƿ�ȡ�����
   * 
   * @return �Ƿ�ȡ�����
   */
  public boolean getIsCancelOffset() {
    return this.isCancelOffset;
  }

  /**
   * ��ֹ�ϵ
   * 
   * @param hmArsubRelation ��ֹ�ϵ
   */
  public void setHmArsubRelation(Map<String, UFDouble> hmArsubRelation) {
    this.hmArsubRelation = hmArsubRelation;
  }

  /**
   * �Ƿ�ȡ�����
   * 
   * @param isCancelOffset �Ƿ�ȡ�����
   */
  public void setIsCancelOffset(boolean isCancelOffset) {
    this.isCancelOffset = isCancelOffset;
  }

}
