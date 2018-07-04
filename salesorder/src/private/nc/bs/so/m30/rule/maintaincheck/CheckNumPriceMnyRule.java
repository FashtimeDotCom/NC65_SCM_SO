package nc.bs.so.m30.rule.maintaincheck;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * �������۽���̨�пա�0��������У��
 * @scene
 * ���۶����������޸ı���ǰ
 * @param 
 * ��
 * 
 * @since 6.3
 * @version 2013-03-12 14:00:12
 * @author ������
 */
public class CheckNumPriceMnyRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO vo : vos) {
      // ��������۶���Ϊ��Ʒ�Ҹ��Ķ�������������Ϊ��Ʒ������ͷ��˰�ϼ�ֵ������Ʒ��˰�ϼƣ�֮�����ü�˰�ϼ�Ϊ��
      SaleOrderHVO hvo = vo.getParentVO();
      if (hvo.getCarsubtypeid() != null) {
        UFDouble lrg = hvo.getNlrgtotalorigmny();
        hvo.setNlrgtotalorigmny(hvo.getNtotalorigmny().add(lrg));
        hvo.setNtotalorigmny(UFDouble.ZERO_DBL);

      }
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        // �������۽���пջ�0
        this.checkZeroNull(bvo);
        // �������۽��������У��
        this.checkValid(bvo);
      }
    }
  }

  private void checkZeroNull(SaleOrderBVO bvo) {
    // ���Ϸ��ֶ�
    List<String> listValiField = new ArrayList<String>();
    StringBuilder errMsg = new StringBuilder();
    // ��Ʒ��־λ
    boolean larflag = false;
    if (null != bvo.getBlargessflag()) {
      larflag = bvo.getBlargessflag().booleanValue();
    }
    // �ۿ۱�־λ
    boolean disflag = false;
    if (null != bvo.getBdiscountflag()) {
      disflag = bvo.getBdiscountflag().booleanValue();
    }
    UFDouble astnnum = bvo.getNastnum();
    UFDouble nnum = bvo.getNnum();
    UFDouble nqtnnum = bvo.getNqtunitnum();
    // ��Ʒ���ۿ������赥���ж�
    if (larflag || disflag) {
      /**
       * �ۿۣ���������Ϊ�ջ���0������Ϊ�ջ���0
       * ��Ʒ��������������Ϊ0������Ϊ��
       */
      if (!disflag) {
        if (null == astnnum) {
          listValiField.add(NCLangResOnserver.getInstance().getStrByID(
              "4006011_0", "04006011-0312")/* ���� */);
        }
        if (nqtnnum == null) {
          listValiField.add(NCLangResOnserver.getInstance().getStrByID(
              "4006011_0", "04006011-0477")/* �������� */);
        }
        if (nnum == null) {
          listValiField.add(NCLangResOnserver.getInstance().getStrByID(
              "4006011_0", "04006011-0476")/* ������ */);
        }

      }

      // ---begin---��Ŀ���⣺���ε������Ϊ0У�� -----modified :������ 2013-03-12
      UFDouble norigmny = bvo.getNorigmny();
      if (null == norigmny || !larflag
          && norigmny.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0313")/* ��˰��� */);
      }
      UFDouble norigtaxmny = bvo.getNorigtaxmny();
      if (null == norigtaxmny || !larflag
          && norigtaxmny.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0314")/* ��˰�ϼ� */);
      }
    }
    // ������ͨ����
    else {
      /**
       * �����񡢷��ۿۡ�����Ʒ�����������ۡ�����Ϊ�ջ���0
       * �������������ۡ�����Ϊ�ջ���0
       */
      if (MathTool.isZero(astnnum)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0312")/* ���� */);
      }
      if (MathTool.isZero(nqtnnum)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0477")/* �������� */);
      }
      if (MathTool.isZero(nnum)) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0476")/* ������ */);
      }
      UFDouble nqtorigtaxprice = bvo.getNqtorigtaxprice();
      if (null == nqtorigtaxprice
          || nqtorigtaxprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0315")/* ��˰���� */);
      }
      UFDouble nqtorigprice = bvo.getNqtorigprice();
      if (null == nqtorigprice
          || nqtorigprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0316")/* ��˰���� */);
      }
      UFDouble nqtorigtaxnetprice = bvo.getNqtorigtaxnetprc();
      if (null == nqtorigtaxnetprice
          || nqtorigtaxnetprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0317")/* ��˰���� */);
      }
      UFDouble nqtorignetprice = bvo.getNqtorignetprice();
      if (null == nqtorignetprice
          || nqtorignetprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0318")/* ��˰���� */);
      }
      UFDouble norignetprice = bvo.getNorignetprice();
      if (null == norignetprice
          || norignetprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0451")/* ����˰���� */);
      }
      UFDouble nnetprice = bvo.getNnetprice();
      if (null == nnetprice || nnetprice.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0452")/* ��������˰���� */);
      }
      UFDouble norigmny = bvo.getNorigmny();
      if (null == norigmny || norigmny.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0313")/* ��˰��� */);
      }
      UFDouble norigtaxmny = bvo.getNorigtaxmny();
      if (null == norigtaxmny || norigtaxmny.compareTo(UFDouble.ZERO_DBL) == 0) {
        listValiField.add(NCLangResOnserver.getInstance().getStrByID(
            "4006011_0", "04006011-0314")/* ��˰�ϼ� */);
      }
    }
    if (listValiField.size() > 0) {
      String crowno = bvo.getCrowno();
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0327", null, new String[] {
          crowno
      })/* ��[{0}]�У� */);
      for (String field : listValiField) {
        errMsg
        .append("[")
        .append(field)
        .append("]")
        .append(
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0284")/* �� */);
      }
      errMsg.deleteCharAt(errMsg.length() - 1);
      errMsg.append("\n");
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0328", null, new String[] {
              errMsg.toString()
          })/* �����ֶ�ֵ����Ϊ�ջ�Ϊ0:\n{0} */);
    }
  }

  private void checkValid(SaleOrderBVO item) {
    UFDouble price = item.getNorigtaxprice();
    if (MathTool.lessThan(price, UFDouble.ZERO_DBL)) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0097")/* @res "���۲���С��0��" */);
    }
    price = item.getNqtorigtaxprice();
    if (MathTool.lessThan(price, UFDouble.ZERO_DBL)) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0097")/* @res "���۲���С��0��" */);
    }
    
    price = item.getNqtorigtaxnetprc();
    if (MathTool.lessThan(price, UFDouble.ZERO_DBL)) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0523")/* @res "���۲���С��0��" */);
    }
    price = item.getNorigtaxnetprice();
    if (MathTool.lessThan(price, UFDouble.ZERO_DBL)) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0523")/* @res "���۲���С��0��" */);
    }
    
    UFDouble exchangerate = item.getNexchangerate();
    if (MathTool.compareTo(exchangerate, UFDouble.ZERO_DBL) <= 0) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0098")/* @res "��֯�۱����ʲ���С�ڵ���0��" */);
    }
    
    // ���ҽ��У�飨��Ʒ���⣩
    if (item.getBlargessflag() == null
        || !item.getBlargessflag().booleanValue()) {
      UFDouble ntaxmny = item.getNtaxmny();
      UFDouble norgtaxmny = item.getNorigtaxmny();
      if (MathTool.isDiffSign(ntaxmny, norgtaxmny)) {
        ExceptionUtils
        .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0099")/* @res
             * "���ҽ���ԭ�ҽ������෴��" */);
      }
      else {
        if (MathTool.isZero(ntaxmny)) {
          ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006011_0", "04006011-0100")/* @res
               * "���Ҽ�˰�ϼƲ��ܵ���0��" */);
        }
      }
    }
  }
}
