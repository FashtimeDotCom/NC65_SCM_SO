package nc.bs.so.m30.rule.unapprove;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.pub.power.BillPowerChecker;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 *  ���۶����޶�ȡ������ǰ����Ƿ��������
 * @scene
 *  ���۶����޶�ȡ������ǰ
 * @param
 * 
 *
 * @since 6.3
 * @version 2015-1-9 ����3:03:33
 * @author wangshu6
 */
public class CheckReviseUnApprovableRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    for (SaleOrderVO bill : bills) {
      SaleOrderHVO header = bill.getParentVO();
      if (!BillStatus.AUDIT.equalsValue(header.getFstatusflag())
          && !BillStatus.AUDITING.equalsValue(header.getFstatusflag())
          && !BillStatus.NOPASS.equalsValue(header.getFstatusflag())) {

        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006011_0", "04006011-0074")/*
                                                                     * @res
                                                                     * "����Ϊ������״̬��������״̬�����ɽ�������"
                                                                     */);
      }
      // ������Ȩ��

      boolean ishaveper =
          BillPowerChecker.hasApproverPermission(bill,
              SOBillType.Order.getCode());
      if (!ishaveper) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006011_0", "04006011-0432")/* ��ǰ�û��Ե��ݲ�����������Ȩ��. */);
      }

      // ���۶����޸ĺ󣬶����޶�Ӧ�ò�����������ʱ����ɾ����ɾ�У�
      int bodyrownum = bill.getChildrenVO().length;
      String csaleorderid = header.getCsaleorderid();
      DataAccessUtils queryUtil = new DataAccessUtils();
      SqlBuilder sql = new SqlBuilder();
      sql.append("select so_saleorder.csaleorderbid from so_saleorder_b so_saleorder where ");
      sql.append("so_saleorder.csaleorderid", csaleorderid);
      sql.append(" and ");
      sql.append("so_saleorder.dr", 0);
      IRowSet result = queryUtil.query(sql.toString());
      if (result.size() == 0 || bodyrownum != result.size()) {
        ExceptionUtils
            .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
                "4006011_0", "04006011-0527")/* ���۶����ѱ�ɾ������ɾ�У������б����ˢ�µ���. */);
      }
    }
  }
}
