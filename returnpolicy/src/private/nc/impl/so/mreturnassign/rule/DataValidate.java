package nc.impl.so.mreturnassign.rule;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.mreturnassign.entity.ReturnAssignVO;
import nc.vo.trade.checkrule.VOChecker;

public class DataValidate implements IRule<BatchOperateVO> {

  @Override
  public void process(BatchOperateVO[] vos) {
    for (BatchOperateVO bill : vos) {
      this.process(bill);
    }
  }

  private SqlBuilder getSql(SqlBuilder querysql, ReturnAssignVO bill) {
    querysql.append("select pk_returnassign from so_returnassign where ");
    querysql.append(" pk_saleorg", bill.getPk_saleorg());
    querysql.append(" and ");

    querysql.append("pk_productline", bill.getPk_productline());
    // ����
    querysql.append(" and ");
    querysql.append(" pk_material", bill.getPk_material());
    // ���Ϸ���
    querysql.append(" and ");
    querysql.append(" pk_marbasclass", bill.getPk_marbasclass());
    // �������۷���
    querysql.append(" and ");
    querysql.append(" pk_marsaleclass", bill.getPk_marsaleclass());
    // �͑�
    querysql.append(" and ");
    querysql.append(" pk_customer", bill.getPk_customer());
    // �ͻ�����
    querysql.append(" and ");
    querysql.append(" pk_custclass", bill.getPk_custclass());
    // �ͻ����۷���
    querysql.append(" and ");
    querysql.append(" pk_custsaleclass", bill.getPk_custsaleclass());

    // ɾ����־
    querysql.append(" and  dr = 0 ");
    return querysql;
  }

  private void process(BatchOperateVO batchOperateVO) {
    SqlBuilder querysql = new SqlBuilder() {
      @Override
      public void append(String name, String value) {
        if (VOChecker.isEmpty(value)) {
          super.append(name);
          super.append(" = ");
          super.append(" '~' ");
        }
        else {
          super.append(name, value);
        }

      }
    };

    Object[] addVOs = batchOperateVO.getAddObjs();
    Object[] updateVOs = batchOperateVO.getUpdObjs();
    if (((VOChecker.isEmpty(updateVOs)) || (updateVOs.length == 0))
        && ((addVOs == null) || (addVOs.length == 0))) {
      return;
    }
    Object[] newVOs = new Object[updateVOs.length + addVOs.length];
    System.arraycopy(addVOs, 0, newVOs, 0, addVOs.length);
    System.arraycopy(updateVOs, 0, newVOs, addVOs.length, updateVOs.length);
    for (int i = 0; i < newVOs.length; i++) {
      ReturnAssignVO bill = (ReturnAssignVO) newVOs[i];
      querysql = this.getSql(querysql, bill);
      DataAccessUtils util = new DataAccessUtils();
      IRowSet rs = util.query(querysql.toString());
      while (rs.next()) {
        String oldpk = rs.getString(0);
        String newpk = bill.getPk_returnassign();
        if ((null == newpk) || !newpk.equals(oldpk)) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006006_0","04006006-0030")/*@res "������֯����Ʒ�ߡ����ϡ����Ϸ��ࡢ�ͻ����ͻ�������ϲ�Ψһ��"*/);
        }
      }
    }
  }

}