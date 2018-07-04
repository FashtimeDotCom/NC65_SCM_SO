package nc.vo.so.pub.keyvalue;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����࣬����key����ȡ�����ö�������ֵ
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-4-20 ����08:22:04
 */
public abstract class AbstractKeyValue implements IKeyValue {
  /**
   * <p>
   * <b>������Ҫ������¹��ܣ�</b>
   * <ul>
   * <li>��״̬ö��
   * </ul>
   * <p>
   * 
   * @version ���汾�� 6.0
   * @since ��һ�汾�� 5.6
   * @author
   * @time 2010-4-21 ����10:15:05
   */
  public enum RowStatus {
    /**
     * ɾ��
     */
    DELETED,

    /**
     * ����
     */
    NEW,

    /**
     * �޸ı�
     */
    UNCHANGED,

    /**
     * ����
     */
    UPDATED,
  }

  @Override
  public abstract int getBodyCount();

  @Override
  public Integer getBodyIntegerValue(int index, String key) {
    Object value = this.getBodyValue(index, key);
    return ValueUtils.getInteger(value);
  }

  @Override
  public String getBodyStringValue(int index, String key) {
    Object value = this.getBodyValue(index, key);
    return ValueUtils.getString(value);
  }

  @Override
  public UFBoolean getBodyUFBooleanValue(int index, String key) {
    Object value = this.getBodyValue(index, key);
    return ValueUtils.getUFBoolean(value);
  }

  @Override
  public UFDate getBodyUFDateValue(int index, String key) {
    Object value = this.getBodyValue(index, key);

    return ValueUtils.getUFDate(value);
  }

  @Override
  public UFDouble getBodyUFDoubleValue(int index, String key) {
    Object value = this.getBodyValue(index, key);

    return ValueUtils.getUFDouble(value);
  }

  @Override
  public abstract Object getBodyValue(int index, String key);

  @Override
  public Integer getHeadIntegerValue(String key) {
    Object value = this.getHeadValue(key);

    return ValueUtils.getInteger(value);
  }

  @Override
  public String getHeadStringValue(String key) {
    Object value = this.getHeadValue(key);

    return ValueUtils.getString(value);
  }

  @Override
  public UFBoolean getHeadUFBooleanValue(String key) {
    Object value = this.getHeadValue(key);

    return ValueUtils.getUFBoolean(value);
  }

  @Override
  public UFDate getHeadUFDateValue(String key) {
    Object value = this.getHeadValue(key);

    return ValueUtils.getUFDate(value);
  }

  @Override
  public UFDouble getHeadUFDoubleValue(String key) {
    Object value = this.getHeadValue(key);

    return ValueUtils.getUFDouble(value);
  }

  @Override
  public abstract Object getHeadValue(String key);

  @Override
  public abstract RowStatus getRowStatus(int index);

  @Override
  public abstract void setBodyValue(int index, String key, Object value);

  @Override
  public abstract void setHeadValue(String key, Object value);

}
