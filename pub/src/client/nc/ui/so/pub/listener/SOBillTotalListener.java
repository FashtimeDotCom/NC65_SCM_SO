package nc.ui.so.pub.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.ui.pub.bill.BillTotalListener;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.salequotation.entity.SalequotationBVO;

public class SOBillTotalListener implements BillTotalListener {

  private IKeyValue keyValue;

  // Ĭ�ϵĹ������������ƷҲ��Ҫ�ϼƵ��ֶΣ������������������۵�λ����������������������������ֶ�����Ҫ�ϼƽ�ȥ���������
  private String[] defaultIncludeSumNumKeys = new String[] {
    SOItemKey.NNUM, SOItemKey.NASTNUM, SOItemKey.NQTUNITNUM, SOItemKey.NWEIGHT,
    SOItemKey.NVOLUME, SOItemKey.NPIECE,SalequotationBVO.NASSISTNUM
  };

  /**
   * �洢���������Ʒ����Ҫ�ϼƵ��ֶ�
   */
  private List<String> includeSumNumKeys;

  public SOBillTotalListener(IKeyValue keyValue) {
    this.keyValue = keyValue;
    includeSumNumKeys = new ArrayList<String>();
    // ��Ĭ�ϵ��ֶμӽ�ȥ
    includeSumNumKeys.addAll(Arrays.asList(defaultIncludeSumNumKeys));
  }

  /**
   * ���������ƷҲ��Ҫ�ϼƵ��ֶ�������ֶ�
   * 
   * @param key
   */
  public void addIncludeSumNumKey(String key) {
    if (!includeSumNumKeys.contains(key)) {
      includeSumNumKeys.add(key);
    }
  }

  /**
   * ���������ƷҲ��Ҫ�ϼƵ��ֶ�������ֶ�
   * 
   * @param key
   */
  public void addIncludeSumNumKey(String[] keys) {
    if (keys == null || keys.length == 0) {
      return;
    }
    includeSumNumKeys.addAll(Arrays.asList(keys));
  }

  /**
   * ���������ƷҲ��Ҫ�ϼƵ��ֶ���ɾ���ֶ�
   * 
   * @param key
   */
  public void removeIncludeSumNumKey(String key) {
    if (includeSumNumKeys.contains(key)) {
      includeSumNumKeys.remove(key);
    }
  }

  public List<String> getIncludeSumNumKeys() {
    return includeSumNumKeys;
  }

  @Override
  public UFDouble calcurateTotal(String key) {

    UFDouble total = UFDouble.ZERO_DBL;
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {

      UFDouble ufvalue = this.keyValue.getBodyUFDoubleValue(i, key);
      UFBoolean larflag =
          this.keyValue.getBodyUFBooleanValue(i, SOItemKey.BLARGESSFLAG);
      // ���磺����������Ʒ�е�
      if (getIncludeSumNumKeys().contains(key)) {
        total = MathTool.add(total, ufvalue);
      }
      else if (null == larflag || !larflag.booleanValue()) {
        total = MathTool.add(total, ufvalue);
      }
    }
    return total;
  }

}
