package nc.vo.so.pub.keyvalue;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;

/**
 * ����ָ�ı�ͷ���嶼��vo�ı�ͷ����
 * 
 * @since 6.0
 * @version 2011-8-1 ����02:35:05
 * @author zhangcheng
 */
public interface IKeyValue {
  /**
   * �����������������ر���������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����10:17:36
   */
  int getBodyCount();

  Integer getBodyIntegerValue(int index, String key);

  /**
   * �����������������ر���index���ֶ�key��String����ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @param key
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����11:22:28
   */
  String getBodyStringValue(int index, String key);

  /**
   * �����������������ر���index���ֶ�key��UFBoolean����ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @param key
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����11:25:09
   */
  UFBoolean getBodyUFBooleanValue(int index, String key);

  UFDate getBodyUFDateValue(int index, String key);

  /**
   * �����������������ر���index���ֶ�key��UFDouble����ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @param key
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����11:23:48
   */
  UFDouble getBodyUFDoubleValue(int index, String key);

  /**
   * �����������������ر���index���ֶ�key��ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @param key
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����10:16:03
   */
  Object getBodyValue(int index, String key);

  /**
   * ���ر�ͷindex���ֶ�key��ֵ
   * 
   * @param key
   * @return
   */
  Integer getHeadIntegerValue(String key);

  /**
   * �����������������ر�ͷ�ֶ�key��String����ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param key
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����11:18:12
   */
  String getHeadStringValue(String key);

  /**
   * �����������������ر�ͷ�ֶ�key��UFBoolean����ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param key
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����11:19:53
   */
  UFBoolean getHeadUFBooleanValue(String key);

  /**
   * �����������������ر�ͷ�ֶ�key��UFDate����ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param key
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����11:19:53
   */
  UFDate getHeadUFDateValue(String key);

  /**
   * �����������������ر�ͷ�ֶ�key��UFDouble����ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param key
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����11:21:12
   */
  UFDouble getHeadUFDoubleValue(String key);

  /**
   * �����������������ر�ͷ�ֶ�key��ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param key
   * @return <p>
   * @author
   * @time 2010-4-21 ����10:15:31
   */
  Object getHeadValue(String key);

  /**
   * �����������������ر���index����״̬��
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����10:17:51
   */
  RowStatus getRowStatus(int index);

  /**
   * �����������������ñ���index���ֶ�key��ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @param key
   * @param value
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����10:17:14
   */
  void setBodyValue(int index, String key, Object value);

  /**
   * �����������������ñ�ͷkey�ֶ�ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param key
   * @param value
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����10:16:47
   */
  void setHeadValue(String key, Object value);

}
