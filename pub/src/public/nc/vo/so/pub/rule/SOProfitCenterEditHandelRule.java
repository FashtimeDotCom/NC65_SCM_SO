package nc.vo.so.pub.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * �����������ı༭��Բֿ��Ӱ�죺
 * �޸ķ���/�ջ��������ģ� ����ֿ�������������Ĳ�Ϊ���������������Ĳ������޸ĺ�ķ���/�ջ��������ģ�����ղֿ⡣
 * ��շ���/�ջ��������ģ�
 * ��Ӱ���ջ�/�����ֿ�ͷ���/�ջ������֯��
 * 
 * @author zhangby5
 * @version 65
 * 
 */
public class SOProfitCenterEditHandelRule {

  private IKeyValue keyValue;

  private String stordocKey = SOItemKey.CSENDSTORDOCID;

  private String profitCenterKey = SOItemKey.CSPROFITCENTERID;

  public SOProfitCenterEditHandelRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public SOProfitCenterEditHandelRule(IKeyValue keyValue, String stordocKey,
      String profitCenterKey) {
    this.keyValue = keyValue;
    this.stordocKey = stordocKey;
    this.profitCenterKey = profitCenterKey;
  }

  public void afterEdit(int[] rows) {
    if (ArrayUtil.isEmpty(rows)) {
      return;
    }
    if (this.allProfitCenterIsNull(rows)) {
      return;
    }
    String[] cstordocids = this.getEditRowStordocid(rows);
    if (ArrayUtil.isEmpty(cstordocids)) {
      return;
    }
    // ��ѯ
    Map<String, String> storedocProficentre = new HashMap<String, String>();
    StordocVO[] stVos =
        StordocPubService.queryStordocByPks(cstordocids, new String[] {
          StordocVO.PK_STORDOC, StordocVO.PROFITCENTRE
        });
    for (StordocVO stVo : stVos) {
      storedocProficentre.put(stVo.getPk_stordoc(), stVo.getProfitcentre());
    }
    this.processEditRow(rows, storedocProficentre);

  }

  private boolean allProfitCenterIsNull(int[] rows) {
    for (int row : rows) {
      String profitCenter = keyValue.getBodyStringValue(row, profitCenterKey);
      if (!PubAppTool.isNull(profitCenter)) {
        return false;
      }
    }
    return true;
  }

  private void processEditRow(int[] rows,
      Map<String, String> storedocProficentre) {
    for (int row : rows) {
      String cstordocid =
          this.keyValue.getBodyStringValue(row, this.stordocKey);
      String cprofitcenterid =
          this.keyValue.getBodyStringValue(row, this.profitCenterKey);
      String stordocProfitcentre = storedocProficentre.get(cstordocid);
      if (PubAppTool.isNull(cprofitcenterid)) {
        continue;
      }
      if (PubAppTool.isNull(stordocProfitcentre)) {
        continue;
      }
      if (stordocProfitcentre.equals(cprofitcenterid)) {
        continue;
      }
      this.keyValue.setBodyValue(row, this.stordocKey, null);
    }
  }

  private String[] getEditRowStordocid(int[] rows) {
    Set<String> cstordocidSet = new HashSet<>();
    for (int row : rows) {
      String cstordocid = keyValue.getBodyStringValue(row, this.stordocKey);
      if (PubAppTool.isNull(cstordocid)) {
        continue;
      }
      cstordocidSet.add(cstordocid);
    }
    return cstordocidSet.toArray(new String[cstordocidSet.size()]);
  }

}
