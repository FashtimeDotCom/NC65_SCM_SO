package nc.impl.so.m4331.action.maintain.rule.unapprove;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.dm.m4804.IDelivBillServiceFor4331;
import nc.pubitf.ic.m4c.I4CQueryPubService;
import nc.pubitf.ic.m4y.I4YQueryPubService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * ���۷���������ǰ��鵥���Ƿ��������
 * @scene
 * ���۷���������ǰ
 * @param
 * ��
 */
public class CheckEnableUnApproveRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    // ��鷢�����Ƿ���б��졢����ķ�������������
    this.isChecked(vos);
    // ����Ƿ��Ѿ��ر�
    this.checkIsClose(vos);
    // ����Ƿ�������ε���
    this.existBill(vos);
    // �����Ȩ��У��
    this.validate(vos);
  }

  private void validate(DeliveryVO[] aggVOs) {
    // DeliveryVO[] aggVOs = (DeliveryVO[]) vo;
    if (null != aggVOs && aggVOs.length > 0) {
      for (DeliveryVO aggVO : aggVOs) {
        if (!nc.vo.pubapp.pub.power.BillPowerChecker.hasApproverPermission(
            aggVO, "4331")) {
          String msg =
              NCLangResOnserver.getInstance().getStrByID("4006002_0",
                  "04006002-0176");// "�����������Ȩ�ޡ�"
          ExceptionUtils.wrappBusinessException(msg);
        }
      }
    }
  }

  private void checkExist4804(String[] cdeliveryids,
      Map<String, DeliveryVO> temMap) {
    // ����������ģ��δ���ã�������
    if (!SysInitGroupQuery.isDMEnabled()) {
      return;
    }
    IDelivBillServiceFor4331 service =
        NCLocator.getInstance().lookup(IDelivBillServiceFor4331.class);
    try {
      Map<String, Boolean> hmExit =
          service.queryHasDownriverBillFor4331(cdeliveryids);
      for (String key : cdeliveryids) {
        if (hmExit.get(key).booleanValue()) {
          DeliveryVO vo = temMap.get(key);
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4006002_0", "04006002-0134", null, new String[] {
                vo.getParentVO().getVbillcode()
              })/*����{0}�Ѿ��������ε��ݣ���������*/);
        }

      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void checkExist4CAnd4Y(String[] cdeliveryids,
      Map<String, DeliveryVO> temMap) {
    // ����Ƿ�������ε���
    I4CQueryPubService icquerysrv =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    I4YQueryPubService ic4yquerysrv =
        NCLocator.getInstance().lookup(I4YQueryPubService.class);
    try {
      Map<String, UFBoolean> hmExit =
          icquerysrv.existBill(cdeliveryids, true,
              SOBillType.Delivery.getCode());
      Map<String, UFBoolean> ic4yExit =
          ic4yquerysrv.existBill(cdeliveryids, true,
              SOBillType.Delivery.getCode());
      for (String key : cdeliveryids) {
        if (hmExit.get(key).booleanValue()) {
          DeliveryVO vo = temMap.get(key);
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4006002_0", "04006002-0134", null, new String[] {
                vo.getParentVO().getVbillcode()
              })/*����{0}�Ѿ��������ε��ݣ���������*/);
        }
        if (ic4yExit.get(key).booleanValue()) {
          DeliveryVO vo = temMap.get(key);
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4006002_0", "04006002-0134", null, new String[] {
                vo.getParentVO().getVbillcode()
              })/*����{0}�Ѿ��������ε��ݣ���������*/);
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void checkIsClose(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      Integer state = vo.getParentVO().getFstatusflag();
      String code = vo.getParentVO().getVbillcode();
      if (BillStatus.CLOSED.equalsValue(state)) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006002_0", "04006002-0135", null, new String[] {
              code
            })/*����{0}�Ѿ������رգ���������*/);
      }
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        UFBoolean flag = bvo.getBoutendflag();
        if ((flag == null) || !flag.booleanValue()) {
          continue;
        }
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006002_0", "04006002-0136", null, new String[] {
              code
            })/*����{0}�Ѿ��йرգ���������*/);
      }
    }
  }

  /*
   * ��鷢�����Ƿ�������ε��ݣ�������ڲ�������
   */
  private void existBill(DeliveryVO[] vos) {
    Map<String, DeliveryVO> temMap = new HashMap<String, DeliveryVO>();
    String[] cdeliveryids = new String[vos.length];
    for (int i = 0; i < vos.length; i++) {
      boolean expr1 =
          BillStatus.AUDIT.equalsValue(vos[i].getParentVO().getFstatusflag());
      boolean expr2 =
          BillStatus.AUDITING
              .equalsValue(vos[i].getParentVO().getFstatusflag());
      boolean expr3 =
          BillStatus.NOPASS
              .equalsValue(vos[i].getParentVO().getFstatusflag());

      if (!expr1 && !expr2 && !expr3) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006002_0", "04006002-0137", null, new String[] {
              vos[i].getParentVO().getVbillcode()
            })/*����{0}��ǰ״̬�����ɽ��� ����*/);
      }
      temMap.put(vos[i].getParentVO().getCdeliveryid(), vos[i]);
      cdeliveryids[i] = vos[i].getParentVO().getCdeliveryid();
    }
    if (SysInitGroupQuery.isICEnabled()) {
      this.checkExist4CAnd4Y(cdeliveryids, temMap);
    }
    this.checkExist4804(cdeliveryids, temMap);
  }

  /*
   * �������ķ������Ƿ��Ѿ����� ������ķ�������������
   * @param vos
   */
  private void isChecked(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        if (bvo.getBcheckflag().booleanValue()) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006002_0", "04006002-0066")/*@res "��ǰ�������Ѿ����죬���ɽ�������"*/);
        }
      }
    }
  }
}
