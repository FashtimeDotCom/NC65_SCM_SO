package nc.pubimpl.so.m4331.qc.mc003.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bs.so.m4331.quality.QueryDeliveryCheckBP;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * �����ʼ��д�ķ������ʼ�id���ȫ�ʼ�vo
 *
 * @since 6.0
 * @version 2010-12-30 ����07:20:31
 * @author ף����
 */
public class FullDeliveryCheckRule {
  /**
   * ���ݷ���������id���ʼ쵥�ݺ� ��ѯ�ʼ�vo
   *
   * @param vos
   * @return
   */
  public DeliveryCheckVO[] getFullInfo(DeliveryCheckVO[] vos) {
    if (VOChecker.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0086")/*@res "�ʼ챨��������д���������ʼ�vo����Ϊ�ա�"*/);
    }
    // ���淢��������id
    Set<String> bidSet = new HashSet<String>();
    // �����ʼ쵥�ݺ�
    Set<String> billcodeSet = new HashSet<String>();
    for (DeliveryCheckVO vo : vos) {
      String bid = vo.getCdeliverybid();
      String billcode = vo.getVcheckcode();
      if ((null == bid) || "".equals(bid)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0087")/*@res "�ʼ챨��������д������������������id����Ϊ�ա�"*/);
      }
      if ((null == billcode) || "".equals(billcode)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0088")/*@res "�ʼ챨��������д���������ʼ쵥�ݺŲ���Ϊ�ա�"*/);
      }
      bidSet.add(bid);
      billcodeSet.add(billcode);
    }
    String[] bids = new String[bidSet.size()];
    String[] billcodes = new String[billcodeSet.size()];
    bids = bidSet.toArray(bids);
    billcodes = billcodeSet.toArray(billcodes);
    String sql = this.getQuerySql(bids, billcodes);
    QueryDeliveryCheckBP query = new QueryDeliveryCheckBP();
    return query.queryDeliveryCheckVO(sql);
  }

  /*
   *�����ʼ��id���ʼ쵥�ݺŲ�ѯҪɾ�����ʼ���Ϣ
   *
   * @param bids
   * @param billcodes
   * @return
   */
  private String getQuerySql(String[] bids, String[] billcodes) {
    StringBuffer sql = new StringBuffer();
    sql.append("select distinct(");
    sql.append(DeliveryCheckVO.CDELIVERYCID);
    sql.append(") from so_delivery_check where dr=0 and ");
    SqlBuilder sqlbuilder = new SqlBuilder();
    sqlbuilder.append(DeliveryCheckVO.CDELIVERYBID, bids);
    sqlbuilder.append(" and ");
    sqlbuilder.append(DeliveryCheckVO.VCHECKCODE, billcodes);
    sql.append(sqlbuilder.toString());
    return sql.toString();
  }
}