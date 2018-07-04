package nc.vo.so.upgrade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.pub.enumeration.SOFInvoiceKey2Code;
import nc.vo.so.pub.res.ParameterList;

/**
 * @description
 *              SO28����63�Բ���ֵ���ܺͼ��ܴ��������ַ����ĳ��ȣ���������ʷ����û�д�������
 * @scene
 * 
 * @param
 * 
 * 
 * @since 6.5
 * @version 2015-11-12 ����7:03:26
 * @author ����
 */
public class SO28ParaUpgrade {

  private static Map<String, String> mapTransKeyToCode =
      new HashMap<String, String>();

  /**
   * ��������
   */
  public static void Upgrade() {

    // 1.��ѯ��ʷ��������
    String[][] sysinits = getSysinit();
    if (sysinits == null) {
      return;
    }
    // 2. ��ʼ������ֵ
    initTransKeyToCode();

    // 3.����ʷ���ݽ��б���
    Map<String, String> sysinitmap = getConverSysInit(sysinits);

    // 4.���²���
    updateSysinit(sysinitmap);
  }

  private static void initTransKeyToCode() {
    for (SOFInvoiceKey2Code rule : SOFInvoiceKey2Code.values()) {
      mapTransKeyToCode.put(rule.getKey(), rule.getCode());
    }
  }

  private static Map<String, String> getConverSysInit(String[][] sysinits) {

    Map<String, String> sysinitmap = new HashMap<String, String>();
    for (String[] sysinit : sysinits) {
      if (sysinit[1] == null) {
        continue;
      }
      if (!sysinit[1].startsWith(ParameterList.DOLLER)) {
        sysinitmap.put(sysinit[0], getTransKeyToCode(sysinit[1]));
      }
    }
    return sysinitmap;

  }

  private static String[][] getSysinit() {
    DataAccessUtils accessutil = new DataAccessUtils();
    String sql =
        "select pk_sysinit,value from pub_sysinit where initcode='SO28_V' and dr=0";
    IRowSet rs = accessutil.query(sql);
    String[][] sysinits = rs.toTwoDimensionStringArray();
    return sysinits;
  }

  private static void updateSysinit(Map<String, String> sysinitmap) {
    if (sysinitmap.size() == 0) {
      return;
    }
    List<List<Object>> data = new ArrayList<List<Object>>();
    for (Map.Entry<String, String> entry : sysinitmap.entrySet()) {
      List<Object> obj = new ArrayList<Object>();
      obj.add(entry.getValue());
      obj.add(entry.getKey());
      data.add(obj);
    }
    String updatesql = "update pub_sysinit set value = ? where pk_sysinit=?";
    JavaType[] types = new JavaType[] {
      JavaType.String, JavaType.String
    };
    DataAccessUtils accessutil = new DataAccessUtils();
    accessutil.update(updatesql, types, data);

  }

  /**
   * ��ȡ����ֵ����Map
   * 
   * @return Map<String, String>
   */
  private static String getTransKeyToCode(String sysinitvalule) {
    String[] allrightValues = sysinitvalule.split(ParameterList.BIGSPLITKEY);
    StringBuffer newvalule = new StringBuffer();
    newvalule.append(ParameterList.DOLLER);
    for (String allrightValue : allrightValues) {
      String[] rightValues = allrightValue.split(ParameterList.SPLITKEY);
      for (String rightValue : rightValues) {
        String value = mapTransKeyToCode.get(rightValue);
        newvalule.append(value);
      }
      newvalule.append(ParameterList.BIGSPLITKEY);
    }

    int len = newvalule.length();
    if (len > 0) {
      newvalule.deleteCharAt(len - 1);
    }
    return newvalule.toString();
  }
}
