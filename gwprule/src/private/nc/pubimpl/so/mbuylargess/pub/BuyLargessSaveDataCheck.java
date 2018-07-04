package nc.pubimpl.so.mbuylargess.pub;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.trade.checkrule.VOChecker;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;

/**
 * �������ñ�ǰ����У�鹫���߼�
 * 
 * @since 6.3
 * @version 2014-04-09 15:42:41
 * @author ����
 */
public class BuyLargessSaveDataCheck {

  /**
   * ��ȡ�������ñ���ʧ����Ϣ
   * 
   * @param bills �������þۺ�VO����
   * @return Map<У��ʧ�������±�,ʧ��ԭ��>
   */
  public Map<Integer, String> getCheckSaveErroMsgMap(BuyLargessVO[] bills) {
    Map<Integer, String> indexErromap = new HashMap<Integer, String>();
    int i = 0;
    for (BuyLargessVO bill : bills) {
      // ����������У��
      if (VOChecker.isEmpty(bill.getChildrenVO())) {
        indexErromap
            .put(Integer.valueOf(i), nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006003_0", "04006003-0003")/*@res "�����岻��Ϊ�ա�"*/);
      }
      // �������ñ���ʱ���ݺϷ���У������ǿ�У���ҵ��У��
      this.checkNotNull(bill, i, indexErromap);
      // ����ǰΨһ��У�飺��ͷ������֯+����+���Ϸ���+�������۷���+�ͻ�+�ͻ�����+�ͻ����۷��಻�����ظ�
      this.checkUnique(bill, i, indexErromap);
      i++;
    }
    return indexErromap;
  }

  private void checkBody(BuyLargessBVO[] bodys, int index,
      Map<Integer, String> indexErromap) {
    if (null == bodys || bodys.length == 0) {
      return;
    }
    int length = bodys.length;
    for (int i = 0; i < length; i++) {
      BuyLargessBVO body = bodys[i];
      if (VOChecker.isEmpty(body.getNnum())
          || body.getNnum().compareTo(new UFDouble(0)) <= 0) {
        indexErromap
            .put(Integer.valueOf(index), nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006003_0", "04006003-0004")/*@res "������������Ϊ�գ��������0��"*/);
      }
      if (VOChecker.isEmpty(body.getDbegdate())) {
        indexErromap
            .put(Integer.valueOf(index), nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006003_0", "04006003-0005")/*@res "��ʼ���ڲ���Ϊ�ա�"*/);
      }
      if (VOChecker.isEmpty(body.getDenddate())) {
        indexErromap
            .put(Integer.valueOf(index), nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006003_0", "04006003-0006")/*@res "��ֹ���ڲ���Ϊ�ա�"*/);
      }
      if (!VOChecker.isEmpty(body.getDbegdate())
          && !VOChecker.isEmpty(body.getDenddate())
          && body.getDbegdate().compareTo(body.getDenddate()) > 0) {
        indexErromap
            .put(Integer.valueOf(index), nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006003_0", "04006003-0007")/*@res "��ֹ���ڲ���С�ڿ�ʼ���ڡ�"*/);
      }
      if (!VOChecker.isEmpty(body.getFtoplimittype())
          && body.getFtoplimittype().intValue() != 2
          && VOChecker.isEmpty(body.getNtoplimitvalue())) {
        indexErromap
            .put(Integer.valueOf(index), nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006003_0", "04006003-0008")/*@res "����ֵ����Ϊ�ա�"*/);
      }
      if (!VOChecker.isEmpty(body.getFtoplimittype())
          && body.getFtoplimittype().intValue() == 1
          && VOChecker.isEmpty(body.getNprice())) {
        indexErromap
            .put(Integer.valueOf(index), nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006003_0", "04006003-0009")/*@res "��������Ϊ�����۲���Ϊ�ա�"*/);
      }
      for (int j = 0; j < i; j++) {
        if (body.getPk_material().equals(bodys[j].getPk_material())) {
          if (this.isAfter(body.getDenddate(), bodys[j].getDbegdate())
              && this.isAfter(bodys[j].getDenddate(), body.getDbegdate())) {
            indexErromap
                .put(Integer.valueOf(index), nc.vo.ml.NCLangRes4VoTransl
                    .getNCLangRes().getStrByID("4006003_0", "04006003-0010")/*@res "��ͬ��Ʒ���ϣ���Ʒʱ����н����ص������������롣"*/);
          }
        }
      }
    }
  }

  private void checkHead(BuyLargessHVO head, boolean isbas, int index,
      Map<Integer, String> indexErromap) {
    StringBuilder errmsg = new StringBuilder();
    if (isbas) {
      if (VOChecker.isEmpty(head.getCbuymarid())
          && VOChecker.isEmpty(head.getPk_marbasclass())) {
        errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
            "04006003-0022")/*���ϱ�������Ϸ��಻��ͬʱΪ��.*/);
        errmsg.append("\n");
      }

    }
    if (!isbas) {
      if (VOChecker.isEmpty(head.getCbuymarid())
          && VOChecker.isEmpty(head.getPk_marsaleclass())) {
        errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
            "04006003-0024")/*���ϱ�����������۷��಻��ͬʱΪ��.*/);
        errmsg.append("\n");
      }
    }
    // ��λ
    if (PubAppTool.isNull(head.getCbuyunitid())) {
      errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0025")/*��λ����Ϊ��.*/);
      errmsg.append("\n");
    }
    // ��������
    if (VOChecker.isEmpty(head.getNbuynum())
        || head.getNbuynum().compareTo(new UFDouble(0)) <= 0) {
      errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0026")/*������������Ϊ�գ��������0.*/);
      errmsg.append("\n");
    }
    if (errmsg.length() > 0) {
      indexErromap.put(Integer.valueOf(index), errmsg.toString());
    }
  }

  /**
   * ���������������ж�����date1��date2�Ⱥ�˳����date1��date2��һΪ�շ���true�����򷵻�date1�Ƿ�����date2��
   * <b>����˵��</b>
   * 
   * @param date1
   * @param date2
   * @return
   * @author fengjb
   * @time 2009-6-4 ����07:22:48
   */
  private boolean isAfter(UFDate date1, UFDate date2) {
    if (null == date1 || null == date2) {
      return true;
    }
    return date1.after(date2) || date1.equals(date2);
  }

  private void checkNotNull(BuyLargessVO bill, int index,
      Map<Integer, String> indexErromap) {
    // ��ͷ���ݺϷ���У��
    BuyLargessHVO head = bill.getParentVO();
    String pk_group = head.getPk_group();
    // ���ϡ����Ϸ��಻��ͬʱΪ��
    boolean isbas = BaseSaleClassUtil.isMarBaseClass(pk_group);
    this.checkHead(head, isbas, index, indexErromap);
    BuyLargessBVO[] bodys = bill.getChildrenVO();
    this.checkBody(bodys, index, indexErromap);
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

  private void checkUnique(BuyLargessVO bill, int i,
      Map<Integer, String> indexErromap) {
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
        indexErromap.put(Integer.valueOf(i), errMsg.toString());
      }
    }
  }
}
