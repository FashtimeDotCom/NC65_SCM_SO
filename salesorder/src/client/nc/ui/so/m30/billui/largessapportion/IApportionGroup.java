package nc.ui.so.m30.billui.largessapportion;

import java.util.List;

import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ��Ʒ�۸��̯���鷽ʽ
 * 
 * @since 6.0
 * @version 2010-12-2 ����09:18:14
 * @author �ս���
 */

public interface IApportionGroup {

  List<List<Integer>> getApportionGroupRows(IKeyValue keyValue,
      List<Integer> rowlist);

}
