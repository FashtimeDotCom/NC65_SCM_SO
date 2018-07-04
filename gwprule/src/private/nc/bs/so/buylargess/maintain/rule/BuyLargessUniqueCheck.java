package nc.bs.so.buylargess.maintain.rule;

import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.trade.checkrule.VOChecker;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ����ǰΨһ��У�飺��ͷ������֯+����+���Ϸ���+�������۷���+�ͻ�+�ͻ�����+�ͻ����۷��಻�����ظ�
 * @scene
 * �������ñ���ǰ����
 * @param
 * ��
 * @since 6.3
 * @version 2010-1-12
 * @author ף����
 */
public class BuyLargessUniqueCheck implements IRule<BuyLargessVO> {

  @Override
  public void process(BuyLargessVO[] bills) {
    for (BuyLargessVO bill : bills) {
      this.process(bill);
    }

  }

  private SqlBuilder getSql(SqlBuilder querysql, BuyLargessHVO head) {
    querysql
        .append("select so_buylargess.pk_buylargess from so_buylargess where ");
    querysql.append("so_buylargess.pk_org", head.getPk_org());
    // ����
    querysql.append(" and ");
    querysql.append("so_buylargess.cbuymarid", head.getCbuymarid());
    // ���Ϸ���
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_marbasclass", head.getPk_marbasclass());
    // �������۷���
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_marsaleclass", head.getPk_marsaleclass());
    // �͑�
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_customer", head.getPk_customer());
    // �ͻ�����
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_custclass", head.getPk_custclass());
    // �ͻ����۷���
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_custsaleclass",
        head.getPk_custsaleclass());

    // ɾ����־
    querysql.append(" and  so_buylargess.dr = 0 ");
    return querysql;
  }

  private void process(BuyLargessVO bill) {
    SqlBuilder querysql = new SqlBuilder() {

      @Override
      public void append(String name, String value) {
        if (VOChecker.isEmpty(value)) {
          super.append(name);
          super.append(" = '~' ");
        }
        else {
          super.append(name, value);
        }

      }
    };
    BuyLargessHVO head = bill.getParentVO();
    querysql = this.getSql(querysql, head);
    StringBuffer errMsg = new StringBuffer();
    errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
        "04006003-0013")/*������֯+*/);

    String material = head.getCbuymarid();
    if (PubAppTool.isNull(material)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0014")/*����+*/);
    }
    String marbascl = head.getPk_marbasclass();
    String pk_group = BSContext.getInstance().getGroupID();
    boolean ismarbas = BaseSaleClassUtil.isMarBaseClass(pk_group);
    if (ismarbas && PubAppTool.isNull(marbascl)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0015")/*���Ϸ���+*/);
    }
    String marsalecl = head.getPk_marsaleclass();
    if (!ismarbas && PubAppTool.isNull(marsalecl)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0016")/*�������۷���+*/);
    }

    String custid = head.getPk_customer();
    if (PubAppTool.isNull(custid)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0017")/*�ͻ�+*/);
    }

    String custbasecl = head.getPk_custclass();
    boolean iscustbas = BaseSaleClassUtil.isCustBaseClass(pk_group);
    if (iscustbas && PubAppTool.isNull(custbasecl)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0018")/*�ͻ�����+*/);
    }

    String custsalecl = head.getPk_custsaleclass();
    if (!iscustbas && PubAppTool.isNull(custsalecl)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0019")/*�ͻ����۷���+*/);
    }
    errMsg.deleteCharAt(errMsg.length() - 1);

    errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
        "04006003-0020")/*�ظ���*/);
    DataAccessUtils util = new DataAccessUtils();
    IRowSet rs = util.query(querysql.toString());
    while (rs.next()) {
      String oldpk = rs.getString(0);
      String newpk = head.getPk_buylargess();
      if (null == newpk || !newpk.equals(oldpk)) {
        ExceptionUtils.wrappBusinessException(errMsg.toString());
      }
    }
  }
}
