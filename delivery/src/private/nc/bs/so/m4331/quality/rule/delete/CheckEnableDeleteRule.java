package nc.bs.so.m4331.quality.rule.delete;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryViewVO;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ic.m4c.I4CQueryPubService;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;

/**
 * @description
 * ���۷������ʼ���Ϣɾ��ǰУ���ܷ�ɾ��
 * @scene
 * ���۷������ʼ���Ϣɾ��ǰ
 * @param
 * ��
 */
public class CheckEnableDeleteRule {

  public void checkDelete(DeliveryViewVO[] views, boolean isCheck) {
    if(!SysInitGroupQuery.isICEnabled()) {
  	  return;
  	}
    // ����Ƿ�������ε���
    this.existBill(views, isCheck);
  }

  /*
   * ��鷢�����Ƿ�������ε��ݣ�������ڲ�������
   */
  private void existBill(DeliveryViewVO[] views, boolean isCheck) {
    String[] cdeliveryids = new String[views.length];
    for (int i = 0; i < views.length; i++) {
      cdeliveryids[i] = views[i].getItem().getCdeliverybid();// .getHead().getCdeliveryid();
    }
    // ����Ƿ�������ε���
    I4CQueryPubService icquerysrv =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    try {
      Map<String, UFBoolean> hmExit =
          icquerysrv.existBill(cdeliveryids, false,
              SOBillType.Delivery.getCode());
      for (String key : cdeliveryids) {
        if (hmExit.get(key).booleanValue()) {
          if (!isCheck) {
            ExceptionUtils
                .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0129")/*��ǰ�ʼ챨���Ӧ�ķ��������Ѿ��������ε��ݣ����ɱ��졣*/);
          }
          else {
            ExceptionUtils
                .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                    .getNCLangRes().getStrByID("4006002_0", "04006002-0054")/*@res
                                                                            "��ǰ�������Ѿ��������ε��ݣ����ɽ��б��졣"*/);
          }
          // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0054")/*@res
          // "��ǰ�������Ѿ��������ε��ݣ����ɽ��б��졣"*/);
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
