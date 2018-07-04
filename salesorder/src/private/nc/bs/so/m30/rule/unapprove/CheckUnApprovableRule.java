package nc.bs.so.m30.rule.unapprove;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.pub.power.BillPowerChecker;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.util.StringUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.ArrayUtil;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.pubitf.ic.m4c.I4CQueryPubService;
import nc.pubitf.scmf.dm.m4816.IPrePaidServiceForM30;
import nc.pubitf.so.m32.so.m30.IQuery32For30;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 
 * @description
 *              ���۶���ȡ������ǰ����Ƿ��������
 * @scene
 *        ���۶���ȡ������ǰ
 * @param ��
 */
public class CheckUnApprovableRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    for (SaleOrderVO bill : bills) {
      SaleOrderHVO header = bill.getParentVO();
      if (!BillStatus.AUDIT.equalsValue(header.getFstatusflag())
          && !BillStatus.AUDITING.equalsValue(header.getFstatusflag())
            && !BillStatus.NOPASS.equalsValue(header.getFstatusflag())) {

        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0074")/*@res "����Ϊ������״̬��������״̬�����ɽ�������"*/);
      }
      // ������Ȩ��

      boolean ishaveper =
          BillPowerChecker.hasApproverPermission(bill,
              SOBillType.Order.getCode());
      if (!ishaveper) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006011_0", "04006011-0432")/*��ǰ�û��Ե��ݲ�����������Ȩ��.*/);
      }
      // �Ƿ�������ε���
      this.checkIsExistPostBusiness(bill);

      // add by wangshu6 ����ǰУ�鵱ǰ���ݵİ汾�����޶��������汾�ţ��粻һ�£�����������
      String reviseLastesIversion = this.getReviseLatestIversion(bill);
      if(StringUtil.isEmptyTrimSpace(reviseLastesIversion)){
        return;
      }
      Integer iversion = bill.getParentVO().getIversion();
      int newiversion=-1;
      if(iversion!=null){
        newiversion=iversion.intValue();
      }
      if (newiversion!=Integer.parseInt(reviseLastesIversion)) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0516")/*��ǰ���۶�����δ�������޶��°汾�����ɽ�������*/);
      }
    }
  }

  /**
   * ��õ�ǰ���ݵ��޶���߰汾��
   * 
   * @param bill
   */
  private String getReviseLatestIversion(SaleOrderVO bill) {

    String reviseLastesIversion = this.queryMaxIversionFromOrderHistory(bill);
    if (reviseLastesIversion == null) {
      return null;
    }
    return reviseLastesIversion;

  }

  /**
   * �����۶����޶����в�ѯ���°汾��
   * 
   * @param vos ���۶����޶�vo
   * @return �汾��
   */
  public String queryMaxIversionFromOrderHistory(SaleOrderVO vo) {

    String[] iversions = null;
    SaleOrderHVO head = vo.getParentVO();
    String csaleorderid = head.getCsaleorderid();
    SqlBuilder sql = new SqlBuilder();
    sql.append("select iversion ");
    sql.append("from so_orderhistory where csaleorderid = '");
    sql.append(csaleorderid);
    sql.append("' and iversion = (select max(iversion) from so_orderhistory");
    sql.append(" where csaleorderid = '");
    sql.append(csaleorderid);
    sql.append("')");
    sql.append(" and dr = 0");

    DataAccessUtils dataUtil = new DataAccessUtils();
    IRowSet set = dataUtil.query(sql.toString());
    iversions = set.toOneDimensionStringArray();
    if (ArrayUtil.isEmpty(iversions)) {
      return null;
    }
    return iversions[0];
  }

  private void checkIsExistPostBusiness(SaleOrderVO billvo) {
    UFBoolean iscoop = billvo.getParentVO().getBcooptopoflag();
    if (null != iscoop && iscoop.booleanValue()) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0075")/*@res "���۶����Ѿ�Эͬ���ɲɹ���������������"*/);
    }
    SaleOrderBVO[] bodyvos = billvo.getChildrenVO();
    for (SaleOrderBVO bodyvo : bodyvos) {
      // �ۼƿ�Ʊ����
      UFDouble ntotalinvoicenum = bodyvo.getNtotalinvoicenum();
      if (MathTool.compareTo(ntotalinvoicenum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0076")/*@res "�����Ѿ���Ʊ�����ɽ�������"*/);
      }
      // �ۼ�Ӧ����������
      UFDouble ntotalnotoutnum = bodyvo.getNtotalnotoutnum();
      if (MathTool.compareTo(ntotalnotoutnum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0077")/*@res "�����Ѿ�Ӧ�����⣬���ɽ�������"*/);
      }
      // �ۼ�ʵ����������
      UFDouble ntotaloutnum = bodyvo.getNtotaloutnum();
      if (MathTool.compareTo(ntotaloutnum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0078")/*@res "�����Ѿ�ʵ�����⣬���ɽ�������"*/);
      }
      // �ۼƷ�������
      UFDouble ntotalsendnum = bodyvo.getNtotalsendnum();
      if (MathTool.compareTo(ntotalsendnum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0079")/*@res "�����Ѿ����������ɽ�������"*/);
      }
      // �ۼ��˻�����
      UFDouble ntotalreturnnum = bodyvo.getNtotalreturnnum();
      if (MathTool.compareTo(ntotalreturnnum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0080")/*@res "�����Ѿ��˻������ɽ�������"*/);
      }
      // �ۼư��Ųɹ���������
      UFDouble narrangeponum = bodyvo.getNarrangeponum();
      if (MathTool.compareTo(narrangeponum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0081")/*@res "�����Ѿ����ɲɹ����������ɽ�������"*/);
      }

      // �ۼư��ŵ�����������
      UFDouble narrangetoappnum = bodyvo.getNarrangetoappnum();
      if (MathTool.compareTo(narrangetoappnum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0072")/*@res "�����Ѿ����ɵ������룬���ɽ�������"*/);
      }
      // �ۼư����빺������
      UFDouble narrangepoappnum = bodyvo.getNarrangepoappnum();
      if (MathTool.compareTo(narrangepoappnum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0073")/*@res "�����Ѿ������빺�������ɽ�������"*/);
      }
      // �ۼư��ŵ�����������
      UFDouble narrangetoornum = bodyvo.getNarrangetoornum();
      if (MathTool.compareTo(narrangetoornum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0082")/*@res "�����Ѿ����ɵ������������ɽ�������"*/);
      }
      // �ۼư���ί�ⶩ������
      UFDouble narrangescornum = bodyvo.getNarrangescornum();
      if (MathTool.compareTo(narrangescornum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0083")/*@res "�����Ѿ�����ί�ⶩ�������ɽ�������"*/);
      }
      // �ۼư���������������
      UFDouble narrangemonum = bodyvo.getNarrangemonum();
      if (MathTool.compareTo(narrangemonum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0084")/*@res "�����Ѿ������������������ɽ�������"*/);
      }
      // �ۼư��Ž��ں�ͬ����
      UFDouble narrangeitcnum = bodyvo.getNarrangeitcnum();
      if (MathTool.compareTo(narrangeitcnum, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0480")/*@res "�����Ѿ����ɽ��ں�ͬ�����ɽ�������"*/);
      }
      // �ۼ����ɼƻ���������
      UFDouble ntotalplonu = bodyvo.getNtotalplonum();
      if (MathTool.compareTo(ntotalplonu, UFDouble.ZERO_DBL) != 0) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006011_0", "04006011-0449")/*�����Ѿ����ɼƻ����������ɽ�������*/);
      }
    }
    // ���ýӿڼ���Ƿ�������γ��ⵥ����Ʊ
    String orderhid = billvo.getParentVO().getCsaleorderid();
    String[] orderhids = new String[] {
      orderhid
    };
    try {
      if (SysInitGroupQuery.isICEnabled()) {
        I4CQueryPubService outqrysrv =
            NCLocator.getInstance().lookup(I4CQueryPubService.class);
        Map<String, UFBoolean> map4cexist =
            outqrysrv.existBill(orderhids, true, SOBillType.Order.getCode());
        UFBoolean isexit4c = map4cexist.get(orderhid);
        if (isexit4c.booleanValue()) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4006011_0", "04006011-0433")/*�����Ѿ����⣬���ɽ�������*/);
        }
      }

      IQuery32For30 invqrysrv =
          NCLocator.getInstance().lookup(IQuery32For30.class);
      Map<String, UFBoolean> map32exist =
          invqrysrv.isExistNextInvoice(orderhids);
      UFBoolean isexit32 = map32exist.get(orderhid);
      if (isexit32.booleanValue()) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0076")/*@res "�����Ѿ���Ʊ�����ɽ�������"*/);
      }

      if (SysInitGroupQuery.isDMEnabled()) {
        // ������õ�
        IPrePaidServiceForM30 prepaidsrv =
            NCLocator.getInstance().lookup(IPrePaidServiceForM30.class);
        boolean isnextinvoice = false;

        isnextinvoice = prepaidsrv.hasNextInvoice(orderhids);

        if (isnextinvoice) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006011_0", "04006011-0085")/*@res "�����Ѿ����ɴ�����õ������ɽ�������"*/);
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
