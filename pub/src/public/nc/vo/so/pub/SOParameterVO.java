package nc.vo.so.pub;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * ����ǰ��̨�������������
 * <ol>
 * <li>����VO
 * <li>����VO����
 * <li>ҵ����Map
 * </ol>
 * 
 * @since 6.0
 * @version 2011-5-7 ����03:38:32
 * @author ��־ΰ
 */
public class SOParameterVO implements Serializable {

  private static final long serialVersionUID = -3143042766360177639L;

  /** ����VO */
  public AggregatedValueObject vo;

  /** ����VO���� */
  public AggregatedValueObject[] vos;

  /** ����View */
  public CircularlyAccessibleValueObject view;

  /** ����Views */
  public CircularlyAccessibleValueObject[] views;

  private Object userobject;

  /** ҵ����Map:ATP��顢 ���ü�顢�����ڽ���顢������������顢���ڿ������������ */
  private Map<String, Boolean> businessCheckMap =
      new HashMap<String, Boolean>();

  public AggregatedValueObject getVo() {
    return this.vo;
  }

  public void setVo(AggregatedValueObject vo) {
    this.vo = vo;
  }

  public AggregatedValueObject[] getVos() {
    return this.vos;
  }

  public void setVos(AggregatedValueObject[] vos) {
    this.vos = vos;
  }

  public CircularlyAccessibleValueObject getView() {
    return this.view;
  }

  public void setView(CircularlyAccessibleValueObject view) {
    this.view = view;
  }

  public CircularlyAccessibleValueObject[] getViews() {
    return this.views;
  }

  public void setViews(CircularlyAccessibleValueObject[] views) {
    this.views = views;
  }

  public Map<String, Boolean> getBusinessCheckMap() {
    if (null == this.businessCheckMap) {
      this.businessCheckMap = new HashMap<String, Boolean>();
    }
    return this.businessCheckMap;
  }

  public void setBusinessCheckMap(Map<String, Boolean> businessCheckMap) {
    this.businessCheckMap = businessCheckMap;
  }

  public Object getUserObject() {
    return this.userobject;
  }

  public void setUserObject(Object userobject) {
    this.userobject = userobject;
  }
}
