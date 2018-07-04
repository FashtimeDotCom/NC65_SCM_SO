package nc.bs.so.m32.maintain.rule.insert;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.pub.rule.rowno.SORowNoUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * ���۷�Ʊ����ǰ���ݺϷ���У��
 * @scene
 * ���۷�Ʊ�������޸ı���ǰ
 * @param
 * ��
 * @since 6.0
 * @version 2011-12-12 ����11:03:38
 * @author ô��
 */
public class CheckBillValityRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] invoices) {

    for (SaleInvoiceVO invoicevo : invoices) {
      // ��ͷ�Ϸ���У��
      this.checkHeadValidity(invoicevo.getParentVO());
      // �������ݺϷ���У��
      this.checkBodyValidity(invoicevo);
      // // VO�ֶγ��ȺϷ���У��
      // this.checkFieldLengthValidity(invoicevo);
      // ��������
      this.checkRowCountLimit(invoicevo);
      // У���к�
      SORowNoUtil.checkRowNo(invoicevo);
    }
  }

  /**
   * ����������������������ʱУ�����Ϸ��ԡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param childrenVO
   *          <p>
   * @author ��ӱ�
   * @time 2010-1-21 ����03:18:00
   */
  private void checkBodyValidity(SaleInvoiceVO vo) {
    IKeyValue keyValue = new VOKeyValue<SaleInvoiceVO>(vo);
    SOBuysellTriaRule buyselrule = new SOBuysellTriaRule(keyValue);
    boolean isInternational = buyselrule.isHeadBuysellFlagOut();
    SaleInvoiceBVO[] childrenVOs = vo.getChildrenVO();
    // �޸Ĵ� zhangby5 2014.7.29 ���۷�Ʊ�����в���Ϊ��
    if (null == childrenVOs || this.getVORowCount(vo) == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0138")/* @res "���岻��Ϊ�ա�" */);
      return;
    }
    NCLangResOnserver resservice = NCLangResOnserver.getInstance();
    List<String> errField = new ArrayList<String>();
    for (SaleInvoiceBVO bvo : childrenVOs) {
      // ����ɾ����
      if (bvo.getStatus() == VOStatus.DELETED) {
        continue;
      }
      if (VOChecker.isEmpty(bvo.getCmaterialid())) {
        errField
            .add(resservice.getStrByID("4006008_0", "04006008-0086")/* ���� */);
      }
      if (VOChecker.isEmpty(bvo.getCastunitid())) {
        errField
            .add(resservice.getStrByID("4006008_0", "04006008-0087")/* ��λ */);
      }

      if (null == bvo.getCtaxcodeid()) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
            "04006008-0130")/* ˰�� */);
      }

      if (null == bvo.getFtaxtypeflag()) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
            "04006008-0131")/* ��˰��� */);
      }

      if (null == bvo.getNcaltaxmny()) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
            "04006008-0132")/* ��˰��� */);
      }

      UFBoolean disflag =
          bvo.getBdiscountflag() == null ? UFBoolean.FALSE : bvo
              .getBdiscountflag();
      UFBoolean freeflag =
          bvo.getBlaborflag() == null ? UFBoolean.FALSE : bvo.getBlaborflag();
      // ��Ʒ
      UFBoolean largessflag =
          bvo.getBlargessflag() == null ? UFBoolean.FALSE : bvo
              .getBlargessflag();
      if ((disflag.booleanValue() || freeflag.booleanValue())) {
        if (freeflag.booleanValue() && null == bvo.getNnum()) {
          errField
              .add(resservice.getStrByID("4006008_0", "04006008-0088")/* ���� */);
        }

        // begin-������Ӧ��-liujingn-NCdp205575534-2016/1/20-ר��
        /**
         * �������ۿ�����Ϊ��Ʒʱ��˰�ϼ�Ӧ�ÿ���Ϊ��
         */
        if (MathTool.isZero(bvo.getNorigtaxmny())
            && !largessflag.booleanValue()) {
          errField
              .add(resservice.getStrByID("4006008_0", "04006008-0089")/* ��˰�ϼ� */);
        }
        if (MathTool.isZero(bvo.getNtaxmny()) && !largessflag.booleanValue()) {
          errField
              .add(resservice.getStrByID("4006008_0", "04006008-0091")/* ���Ҽ�˰�ϼ� */);
        }
        // end-������Ӧ��-liujingn-NCdp205575534-2016/1/20-ר��

      }
      else if (largessflag.booleanValue()) {
        if (MathTool.isZero(bvo.getNnum())) {
          errField
              .add(resservice.getStrByID("4006008_0", "04006008-0088")/* ���� */);
        }

      }
      else {
        if (MathTool.isZero(bvo.getNqtorigtaxprice())) {
          errField
              .add(resservice.getStrByID("4006008_0", "04006008-0090")/* ��˰���� */);
        }
        if (MathTool.isZero(bvo.getNorignetprice())) {
          errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
              "04006008-0118")/* ����˰���� */);
        }
        if (MathTool.isZero(bvo.getNnetprice())) {
          errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
              "04006008-0139")/* ��������˰���� */);
        }
        if (MathTool.isZero(bvo.getNorigtaxmny())) {
          errField
              .add(resservice.getStrByID("4006008_0", "04006008-0089")/* ��˰�ϼ� */);
        }

        if (MathTool.isZero(bvo.getNtaxmny())) {
          errField
              .add(resservice.getStrByID("4006008_0", "04006008-0091")/* ���Ҽ�˰�ϼ� */);
        }
      }

      if (errField.size() > 0) {
        StringBuilder errMsg =
            new StringBuilder(resservice.getStrByID("4006008_0",
                "04006008-0092", null, new String[] {
                  bvo.getCrowno()
                })/* ���۷�Ʊ��[{0}]�������ֶβ���Ϊ�ջ���0�� */);

        for (String field : errField) {
          errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006008_0",
              "04006008-0149", null, new String[] {
                field
              })/* [{0}]�� */);

        }
        errMsg.deleteCharAt(errMsg.length() - 1);
        ExceptionUtils.wrappBusinessException(errMsg.toString());
      }

//       ��ʱ��ȥ�����ҵ��û�д˹�ϵ
      if (!isInternational) {
        UFDouble ntaxmny = bvo.getNtaxmny();
        UFDouble naddtaxmny = MathTool.add(bvo.getNtax(), bvo.getNmny());
        if (!MathTool.equals(ntaxmny, naddtaxmny)) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID(
                  "4006008_0",
                  "04006008-0140",
                  null,
                  new String[] {
                    bvo.getCrowno(), ValueUtils.getString(ntaxmny),
                    ValueUtils.getString(bvo.getNmny()),
                    ValueUtils.getString(bvo.getNtax())
                  })/* ���۷�Ʊ������{0}���Ҽ�˰�ϼ�({1})�����ڱ�����˰���({2})��˰��{3} */);
        }
      }

    }
  }

  // /**
  // * ������������������ʱУ��VO�ֶγ��ȡ�
  // * <p>
  // * <b>����˵��</b>
  // *
  // * @param invoicevo
  // * <p>
  // * @author ��ӱ�
  // * @time 2010-3-17 ����02:22:16
  // */
  // private void checkFieldLengthValidity(SaleInvoiceVO invoicevo) {
  // // ���ù�������У�鹤����
  // VOFieldLengthChecker.checkVOFieldsLength(invoicevo);
  // }

  /**
   * ����������������������ʱУ���ͷ�Ϸ��ԡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param parent
   *          <p>
   * @author ��ӱ�
   * @time 2010-1-21 ����02:52:13
   */
  private void checkHeadValidity(SaleInvoiceHVO head) {
    List<String> errField = new ArrayList<String>();

    if (VOChecker.isEmpty(head.getPk_org())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0093")/* ��Ʊ��֯ */);
    }
    if (VOChecker.isEmpty(head.getDbilldate())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0094")/* ��Ʊ���� */);
    }
    if (VOChecker.isEmpty(head.getCtrantypeid())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0095")/* ��Ʊ���� */);
    }
    if (VOChecker.isEmpty(head.getCinvoicecustid())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0096")/* ��Ʊ�ͻ� */);
    }
    if (VOChecker.isEmpty(head.getCbiztypeid())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0097")/* ҵ������ */);
    }
    if (VOChecker.isEmpty(head.getCorigcurrencyid())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0098")/* ���� */);
    }
    if (VOChecker.isEmpty(head.getNexchangerate())
        || head.getNexchangerate().compareTo(UFDouble.ZERO_DBL) <= 0) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0099")/* �۱����� */);
    }

    if (null == head.getCrececountryid()) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0133")/* �ջ�����/���� */);
    }

    if (null == head.getCsendcountryid()) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0134")/* ��������/���� */);
    }

    if (null == head.getCtaxcountryid()) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0135")/* ��˰����/���� */);
    }

    if (null == head.getFbuysellflag()) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0136")/* �������� */);
    }

    if (null == head.getBtriatradeflag()) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0137")/* ����ó�� */);
    }

    if (errField.size() > 0) {
      StringBuilder errMsg =
          new StringBuilder(NCLangResOnserver.getInstance().getStrByID(
              "4006008_0", "04006008-0100")/* �������Ե�ֵ����Ϊ�ջ�0�� */);
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006008_0",
          "04006008-0101")/* \n ��ͷ */);
      for (String field : errField) {
        errMsg.append("[").append(field).append("]").append(",");
      }
      errMsg.deleteCharAt(errMsg.length() - 1);

      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
    // if (VOChecker.isEmpty(head.getNexchangerate())
    // || head.getNexchangerate().compareTo(UFDouble.ZERO_DBL) <= 0) {
    // ExceptionUtils.wrappBusinessException("���۷�Ʊ�۱����ʲ���С�ڵ���0��");
    // }
  }

  private void checkRowCountLimit(SaleInvoiceVO vo) {
    Object pk_org = vo.getParentVO().getPk_org();
    int rowlimit = 0;

    rowlimit =
        SOSysParaInitUtil.getSO17(pk_org.toString()) == null ? 0
            : SOSysParaInitUtil.getSO17(pk_org.toString()).intValue();

    int rowCount = this.getVORowCount(vo);
    if (rowlimit > 0 && rowCount > rowlimit) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006008_0", "04006008-0102", null, new String[] {
            Integer.toString(rowlimit)
          })/* ����Ʊ��������![����SO17��������:{0}] */);
    }
  }

  // ��ȡVO�б����з�ɾ��״̬������ zhangby5 2014.7.29
  private int getVORowCount(SaleInvoiceVO vo) {

    int count = 0;
    for (SaleInvoiceBVO bvo : vo.getChildrenVO()) {

      if (bvo.getStatus() != VOStatus.DELETED) {
        count++;
      }
    }
    return count;
  }
}
