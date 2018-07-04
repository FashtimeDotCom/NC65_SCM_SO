package nc.ui.so.m33.mansquare.pub;

import java.util.Map;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.res.ParameterList;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * SO12���۳��ⵥӦ�ս��������޸Ŀͻ��͵��ۡ����
 * 
 * @since 6.0
 * @version 2011-6-13 ����01:31:30
 * @author zhangcheng
 */
public class ManualSquarePubEditProcess {

  /**
   * SO12���۳��ⵥӦ�ս��������޸Ŀͻ��͵���
   * 
   */
  public void initEditEnable(BillListPanel listPanel) {
    String[] itemKeys = new String[] {
      SquareOutBVO.CINVOICECUSTID, SquareOutBVO.NORIGTAXNETPRICE
    };

    // ������
    int len = listPanel.getBodyBillModel().getRowCount();
    String[] pk_orgs = new String[len];
    ListKeyValue listkey = new ListKeyValue(listPanel, 0, ListTemplateType.SUB);
    for (int i = 0; i < len; i++) {
      pk_orgs[i] = listkey.getBodyStringValue(i, SquareOutBVO.PK_ORG);
    }
    Map<String, String> map = null;

    map =
        SOSysParaInitUtil.queryBatchParaStringValues(pk_orgs,
            ParameterList.SO12.getCode());

    if (null != map) {
      // ���ñ༭��
      for (int i = 0; i < len; i++) {
        String pk_org = listkey.getBodyStringValue(i, SquareOutBVO.PK_ORG);
        boolean flag = ValueUtils.getBoolean(map.get(pk_org));
        for (String key : itemKeys) {
          listPanel.getBodyBillModel().setCellEditable(i, key, flag);
        }
      }
    }
  }
}
