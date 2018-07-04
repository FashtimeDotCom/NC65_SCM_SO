package nc.vo.so.pub.rule;

import nc.vo.so.pub.keyvalue.IKeyValue;

public class BodyUpdateByHead {

  private IKeyValue keyValue;

  /**
   * BodyUpdateByHead �Ĺ�����
   * 
   * @param keyValue
   */
  public BodyUpdateByHead(
      IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * �����������������ݱ�ͷ�ֶ�ֵ�������б��������ֶ�ֵ��Ҫ���ͷ�����ֶ���һ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param bodykeys
   * @param headkeys
   *          <p>
   * @author fengjb
   * @time 2010-5-18 ����11:26:08
   */
  public void updateAllBodyRedunValue(String[] redunkeys) {
    // ����
    int irowcount = this.keyValue.getBodyCount();
    for (int i = 0; i < irowcount; i++) {
      for (String key : redunkeys) {
        Object headValue = this.keyValue.getHeadValue(key);
        this.keyValue.setBodyValue(i, key, headValue);
      }
    }
  }

  /**
   * �����������������ݱ�ͷheadkey�ֶ�ֵ�������б���bodykey�ֶΡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param bodykey
   * @param headkey
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-30 ����11:42:45
   */
  public void updateAllBodyValueByHead(String bodykey, String headkey) {
    Object headValue = this.keyValue.getHeadValue(headkey);
    int ilen = this.keyValue.getBodyCount();
    for (int i = 0; i < ilen; i++) {
      this.keyValue.setBodyValue(i, bodykey, headValue);
    }
  }
}
