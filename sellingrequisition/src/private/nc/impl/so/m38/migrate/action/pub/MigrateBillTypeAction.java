package nc.impl.so.m38.migrate.action.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uif.pub.IUifService;
import nc.uif.pub.exception.UifException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.pub.util.ListUtil;

/**
 * ��bd_billtype��������Ԥ����������������ת��ΪECԤ����
 * billtypeVOs����һ��Ϊ����ECԤ���������ݣ�����Ϊ����Ԥ���������ݣ�
 * ����Ԥ����Ǩ��ʱҪ������ECԤ����������������Ϊ����ģ��
 * @since 6.36
 * @version 2015-05-23
 * @author liylr
 *
 */
public class MigrateBillTypeAction {
  public void migrateBilltype(Map<String, BilltypeVO> billtypeVOs,
      String ecc_pk, String prefix, Map<String, String> oldNewTrantypeIdMap) {
    BilltypeVO ec_billTypeVO = billtypeVOs.get(ecc_pk);
    List<BilltypeVO> newBillTypeVOs = new ArrayList<BilltypeVO>();
    for (String pk : billtypeVOs.keySet()) {
      if (pk.equals(ecc_pk))
        continue;
      BilltypeVO newVO = (BilltypeVO) billtypeVOs.get(pk).clone();
      // ��m38trantype��opc_trantype֮���в����billtypeVO�ֶν������µ�����ecc_pk��Ӧ��billtypeVO��Ϊ������ģ��
      for (String field : this.MIGFIELDS) {
        newVO.setAttributeValue(field,
            ec_billTypeVO.getAttributeValue(field));
      }
      newVO.setParentbilltype(prefix);
      newVO.setPk_billtypeid(oldNewTrantypeIdMap.get(newVO.getPk_billtypeid()));
      newVO.setPk_billtypecode(new StringBuffer(prefix).append("-")
          .append(newVO.getPk_billtypecode()).toString());
      newVO.setIsLock(UFBoolean.FALSE);
      newBillTypeVOs.add(newVO);
    }

    // ����ת����Ľ������ͣ�bd_billtype��
    if (newBillTypeVOs.size() > 0) {
      try {
        NCLocator.getInstance().lookup(IUifService.class)
            .insertAry(ListUtil.toArray(newBillTypeVOs));
      }
      catch (UifException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  public final String[] MIGFIELDS = new String[] {
    "checkclassname", "classname", "component", "forwardbilltype",
    "isapprovebill", "isEditableProperty", "isEnableButton", "isroot",
    "ncbrcode", "nodecode", "referclassname", "systemcode",
    "transtype_class", "wherestring"
  };
}
