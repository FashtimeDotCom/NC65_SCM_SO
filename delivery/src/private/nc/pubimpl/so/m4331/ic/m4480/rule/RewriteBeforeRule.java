package nc.pubimpl.so.m4331.ic.m4480.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.pubitf.so.m4331.ic.m4480.RewritePara4331For4480;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * Ԥ����д������ǰ����
 * 
 * @since 6.0
 * @version 2011-3-25 ����05:57:02
 * @author ף����
 */
public class RewriteBeforeRule {

  private Map<String, RewritePara4331For4480> index;

  public void process(DeliveryViewVO[] views,
      Map<String, RewritePara4331For4480> index2) {
    this.index = index2;
    for (DeliveryViewVO view : views) {
      this.checkRedBill(view);
      // Ԥ���ĳ��ⵥ�޸ĵ�ʱ�� �Ȼ�дԤ������ ��ʱ��û��д�������� ����������У��û������ ����
      // this.checkReqrsNumRange(view);
      this.setReqrsNum(view);
    }
  }

  private void checkRedBill(DeliveryViewVO view) {
    DeliveryBVO body = view.getItem();
    if (MathTool.compareTo(body.getNnum(), UFDouble.ZERO_DBL) < 0) {
      String message =
          NCLangResOnserver.getInstance().getStrByID("4006002_0",
              "04006002-0146")/*���� < 0�����ַ��������ܱ�Ԥ��*/;
      ExceptionUtils.wrappBusinessException(message);
    }
  }

  private void checkReqrsNumRange(DeliveryViewVO vo) {
    DeliveryHVO head = vo.getHead();
    DeliveryBVO body = vo.getItem();
    RewritePara4331For4480 para = this.index.get(body.getCdeliverybid());

    UFDouble icNum =
        MathTool.add(MathTool.add(body.getNreqrsnum(), para.getNreqrsnum()),
            body.getNtotaloutnum());

    if (MathTool.compareTo(body.getNnum(), icNum) < 0) {
      String message =
          NCLangResOnserver.getInstance().getStrByID("4006002_0",
              "04006002-0147")/*���� < �ۼƳ������� + Ԥ������*/;
      String location =
          NCLangResOnserver.getInstance().getStrByID("4006002_0",
              "04006002-0148", null, new String[] {
                head.getVbillcode(), body.getCrowno()
              })/*������{0}��{1}��*/;

      ExceptionUtils.wrappBusinessException(message, location);
    }
  }

  private void setReqrsNum(DeliveryViewVO vo) {
    DeliveryBVO body = vo.getItem();
    RewritePara4331For4480 para = this.index.get(body.getCdeliverybid());
    // ����Ԥ������
    UFDouble nreqrsnum = body.getNreqrsnum();

    nreqrsnum = MathTool.add(nreqrsnum, para.getNreqrsnum());
    body.setNreqrsnum(nreqrsnum);
  }

}
