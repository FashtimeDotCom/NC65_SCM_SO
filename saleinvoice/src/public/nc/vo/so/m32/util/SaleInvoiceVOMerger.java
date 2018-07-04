package nc.vo.so.m32.util;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.DefaultVOMerger;

public class SaleInvoiceVOMerger extends DefaultVOMerger {

  /**
   * ���������ϻ��������Ӧ��ϵ
   */
  private Map<String, String> baseclassmaps;

  private Map<String, String> m_proWeightKey;

  /**
   * ���ϻ������༶��
   */
  private int classlevel;

  /**
   * �Ƿ����ϻ����������
   */
  private boolean ishasclass;

  public Map<String, String> getBaseclassmaps() {
    return this.baseclassmaps;
  }

  @Override
  public void setProavgingWeightKey(Map<String, String> proWeightKey) {
    this.m_proWeightKey = proWeightKey;
  }

  public int getClasslevel() {
    return this.classlevel;
  }

  public boolean isIshasclass() {
    return this.ishasclass;
  }

  public void setBaseclassmaps(Map<String, String> baseclassmaps) {
    this.baseclassmaps = baseclassmaps;
  }

  public void setClasslevel(int classlevel) {
    this.classlevel = classlevel;
  }

  public void setIshasclass(boolean ishasclass) {
    this.ishasclass = ishasclass;
  }

  @Override
  protected String getGroupKey(CircularlyAccessibleValueObject vo) {
    String[] groupNames = this.getGroupingAttr();
    StringBuffer key = new StringBuffer();
    for (int i = 0; groupNames != null && i < groupNames.length; i++) {
      String temp = " ";
      Object ob = vo.getAttributeValue(groupNames[i]);
      if (ob != null) {
        temp = ob.toString();
        key.append(temp);
      }
      else {
        key.append("NULL");
      }
    }

    // ���������͸������ĵ��ݷֳɲ�ͬ����
    Object temp = null;
    Number num = null;
    if (this.getNumAttr() != null
        && vo.getAttributeValue(this.getNumAttr()) != null) {
      temp = vo.getAttributeValue(this.getNumAttr());

      if (temp != null && !temp.toString().trim().equals("")) {
        num = (Number) temp;
        if (num.doubleValue() < 0) {
          key.append(key).append("-");
        }

      }
    }

    return key.toString();
    // return super.getGroupKey(vo);
  }

  @Override
  protected CircularlyAccessibleValueObject merge(
      CircularlyAccessibleValueObject[] vos, boolean isOtherAttr) {

    // ��������Ϸ����ж�
    if (vos == null || vos.length == 0) {
      return null;
    }
    CircularlyAccessibleValueObject vo = null;
    try {
      vo = vos[0].getClass().newInstance();
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
      return null;
    }
    // ���
    String[] sumAttr = this.getSummingAttr();
    for (int i = 0; sumAttr != null && i < sumAttr.length; i++) {
      int iValue = 0;
      double dValue = 0;
      // 0:��ʾiValue��dValue��û�б��ӹ� 1:��ʾiValue�����ӹ�
      // 2:��ʾdValue�����ӹ�
      int flag = 0;

      for (CircularlyAccessibleValueObject vo2 : vos) {
        Object ob = vo2.getAttributeValue(sumAttr[i]);
        if (ob != null && ob.getClass().equals(Integer.class)) {
          iValue += ((Integer) ob).intValue();
          flag = 1;
        }
        if (ob != null && ob.getClass().equals(UFDouble.class)) {
          dValue += ((UFDouble) ob).doubleValue();
          flag = 2;
        }
        if (ob != null && ob.getClass().equals(String.class)) {
          try {
            iValue += Integer.parseInt(((String) ob).toString());
            flag = 3;
          }
          catch (NumberFormatException e) {
            try {
              dValue += Double.parseDouble(((String) ob).toString());
              flag = 4;
            }
            catch (NumberFormatException e1) {
              ExceptionUtils
                  .wrappBusinessException(
                      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                          "4006008_0", "04006008-0155")/*@res �ֶΣ�*/
                          + sumAttr[i]
                          + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                              .getStrByID("4006008_0", "04006008-0156")/*@res ����ת������ֵ�ͣ���������ʹ���*/,
                      e1);
            }
          }
        }
      }
      if (flag == 0) {
        vo.setAttributeValue(sumAttr[i], null);
      }
      if (flag == 1) {
        vo.setAttributeValue(sumAttr[i], Integer.valueOf(iValue));
      }
      if (flag == 2) {
        vo.setAttributeValue(sumAttr[i], new UFDouble(dValue));
      }
      if (flag == 3) {
        vo.setAttributeValue(sumAttr[i], iValue + "");
      }
      if (flag == 4) {
        vo.setAttributeValue(sumAttr[i], dValue + "");
      }
    }

    // ��ƽ��
    String[] averageAttr = this.getAveragingAttr();
    for (int i = 0; averageAttr != null && i < averageAttr.length; i++) {
      int iValue = 0;
      double dValue = 0;
      // 0:��ʾiValue��dValue��û�б��ӹ� 1:��ʾiValue�����ӹ�
      // 2:��ʾdValue�����ӹ�
      int flag = 0;

      for (CircularlyAccessibleValueObject vo2 : vos) {
        Object ob = vo2.getAttributeValue(averageAttr[i]);
        if (ob != null && ob.getClass().equals(Integer.class)) {
          iValue += ((Integer) ob).intValue();
          flag = 1;
        }
        if (ob != null && ob.getClass().equals(UFDouble.class)) {
          dValue += ((UFDouble) ob).doubleValue();
          flag = 2;
        }
      }
      if (flag == 0) {
        vo.setAttributeValue(averageAttr[i], null);
      }
      if (flag == 1) {
        vo.setAttributeValue(averageAttr[i],
            Integer.valueOf(iValue / vos.length));
      }
      if (flag == 2) {
        vo.setAttributeValue(averageAttr[i], new UFDouble(dValue / vos.length));
      }
    }

    // ���Ȩƽ��
    if (this.getNumAttr() != null || this.m_proWeightKey != null
        && !this.m_proWeightKey.isEmpty()) {
      String[] proavgAttr = this.getProavgingAttr();
      String weightKey = null;
      for (int i = 0; proavgAttr != null && i < proavgAttr.length; i++) {
        // �ҵ������ļ�ȨKEY
        weightKey =
            this.m_proWeightKey == null ? null : this.m_proWeightKey
                .get(proavgAttr[i]);
        weightKey = weightKey == null ? this.getNumAttr() : weightKey;
        if (weightKey == null) {
          continue;
        }
        double dValue = 0;
        double num = 0;
        // 0:��ʾiValue��dValue��û�б��ӹ� 1:��ʾiValue�����ӹ�
        // 2:��ʾdValue�����ӹ�
        int flag = 0;

        for (CircularlyAccessibleValueObject vo2 : vos) {
          // Object numOb = vos[j].getAttributeValue(getNumAttr());
          Object numOb = vo2.getAttributeValue(weightKey);
          if (numOb != null && !numOb.toString().trim().equals("")) {
            double rowNum = ((Number) numOb).doubleValue();

            Object ob = vo2.getAttributeValue(proavgAttr[i]);
            if (ob != null && ob.getClass().equals(Integer.class)) {
              dValue += Math.abs(((Number) ob).doubleValue() * rowNum);
              flag = 1;
            }
            if (ob != null && ob.getClass().equals(UFDouble.class)) {
              dValue += Math.abs(((Number) ob).doubleValue() * rowNum);
              flag = 2;
            }
            num += Math.abs(rowNum);
          }
        }
        if (num > 0) {
          if (flag == 0) {
            vo.setAttributeValue(proavgAttr[i], null);
          }
          if (flag == 1) {
            vo.setAttributeValue(proavgAttr[i],
                Integer.valueOf((int) (dValue / num)));
          }
          if (flag == 2) {
            vo.setAttributeValue(proavgAttr[i], new UFDouble(dValue / num));
          }
        }
      }
    }
    String[] otherAttr = this.getGroupingAttr();
    if (isOtherAttr) {
      // ������������
      otherAttr = this.getOtherAttr(vos[0]);
    }

    for (int i = 0; otherAttr != null && i < otherAttr.length; i++) {
      boolean bSame = true;
      Object first = vos[0].getAttributeValue(otherAttr[i]);

      for (int j = 1; j < vos.length; j++) {
        Object next = vos[j].getAttributeValue(otherAttr[i]);

        if (first == null) {
          if (next != null) {
            bSame = false;
          }
        }
        else {
          if (next == null) {
            bSame = false;
          }
          else {
            if (!first.equals(next)) {
              bSame = false;
            }
          }
        }
      }

      if (bSame) {
        vo.setAttributeValue(otherAttr[i], first);
      }
      else {
        vo.setAttributeValue(otherAttr[i], null);
      }
    }

    // ����ϲ���Ӧ��ϵ
    if (vo != null) {

      // ������ڶ�Ӧ��ϵ,��ϳ�֮
      if (this.m_hashMergeRelations.get(vo) != null) {
        this.m_hashMergeRelations.remove(vo);
      }

      // �����µĶ�Ӧ��ϵ
      this.m_hashMergeRelations.put(vo, vos);

    }
    return vo;

  }

  private String[] getOtherAttr(CircularlyAccessibleValueObject vo) {
    String[] atts = vo.getAttributeNames();
    List<String> v = new Vector<String>();
    String[] sumAtt = this.getSummingAttr();
    String[] avgAtt = this.getAveragingAttr();
    String[] proavgAtt = this.getProavgingAttr();

    for (int i = 0; atts != null && i < atts.length; i++) {

      boolean exist = false;
      for (int j = 0; sumAtt != null && j < sumAtt.length; j++) {
        if (atts[i].equals(sumAtt[j])) {
          exist = true;
          break;
        }
      }
      if (!exist) {
        for (int j = 0; avgAtt != null && j < avgAtt.length; j++) {
          if (atts[i].equals(avgAtt[j])) {
            exist = true;
            break;
          }
        }
      }
      if (!exist) {
        for (int j = 0; proavgAtt != null && j < proavgAtt.length; j++) {
          if (atts[i].equals(proavgAtt[j])) {
            exist = true;
            break;
          }
        }
      }

      if (!exist) {
        v.add(atts[i]);
      }

    }
    if (this.getDynamicOtherKeys() != null) {
      for (String dynamicKey : this.getDynamicOtherKeys()) {
        v.add(dynamicKey);
      }
    }

    String[] others = null;
    if (v.size() > 0) {
      others = new String[v.size()];
      others = v.toArray(others);
    }
    return others;
  }

}