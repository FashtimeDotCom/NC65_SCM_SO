package nc.bs.so.pub;

import nc.impl.pubapp.pattern.data.bill.IAttributeOrderConvert;

/**
 * 
 * @description
 *              ���۹����ѯ���尴���к�����
 * @scene
 *        ��ѯ����
 * @param ��
 * 
 * @since 6.5
 * @version 2015-11-16 ����2:42:21
 * @author zhangby5
 */
public class SORowNoOrderConvert implements IAttributeOrderConvert {

  @Override
  public String convert(String sqlname) {
    return " cast(" + sqlname + " as float) ";
  }
}
