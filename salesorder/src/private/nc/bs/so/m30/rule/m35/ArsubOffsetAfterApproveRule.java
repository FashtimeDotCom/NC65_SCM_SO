package nc.bs.so.m30.rule.m35;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 
 * @description
 * ���۶���������ִ����Ʒ�Ҹ�
 * @scene
 * ������ִ����Ʒ�Ҹ�
 * @param
 * ��
 *
 * @since 6.5
 * @version 2013-12-25 ����09:09:31
 * @author ����
 */
public class ArsubOffsetAfterApproveRule extends ArsubOffsetAfterSaveRule {

  public void process(SaleOrderVO[] vos) {
    List<SaleOrderVO> volist = new ArrayList<SaleOrderVO>();
    for (SaleOrderVO vo : vos) {
      SaleOrderHVO headitem = vo.getParentVO();
      String arsubtypeid = headitem.getCarsubtypeid();
      // ����ͨ��״̬����Ҫ����Ʒ�Ҹ�����
      Integer statusFlag = headitem.getFstatusflag();
      if (arsubtypeid != null && BillStatus.AUDIT.equalsValue(statusFlag)) {
        volist.add(vo);
      }
      else {
        continue;
      }
    }
    if (volist.size() > 0) {
      if (!SysInitGroupQuery.isMeEnabled()) {
			ExceptionUtils
					.wrappBusinessException(NCLangResOnserver.getInstance()
							.getStrByID("4006011_0", "04006011-0519")/*������Ӫ������ģ�飡*/);
	  }
      SaleOrderVO[] ordervos = volist.toArray(new SaleOrderVO[volist.size()]);
      super.process(ordervos);
    }
  }
}
