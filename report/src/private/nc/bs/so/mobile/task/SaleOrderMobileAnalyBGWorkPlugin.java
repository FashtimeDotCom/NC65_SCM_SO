package nc.bs.so.mobile.task;

import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * ���۶����ƶ������м��ת��
 * 
 * @since 6.1
 * @version 2012-5-17 ����09:57:55
 * @author yixl
 */

public class SaleOrderMobileAnalyBGWorkPlugin implements IBackgroundWorkPlugin {

  @Override
  public PreAlertObject executeTask(BgWorkingContext bgwc)
      throws BusinessException {
    PreAlertObject preobj = new PreAlertObject();

    // ���ƶ�Ӧ�ñ���������������
    this.insertDataToMobileOrderanaly();

    // ������Ϣ
    preobj.setReturnType(PreAlertReturnType.RETURNNOTHING);

    return preobj;
  }

  private String getInsertSql() {

    UFDate busidate = AppContext.getInstance().getBusiDate();
    UFDate enddate = busidate.asEnd();

    UFDate bfthreemoth = busidate.getDateBefore(90);
    UFDate begindate = bfthreemoth.asBegin();

    SqlBuilder insertSql = new SqlBuilder();

    insertSql
        .append("insert into so_mb_orderanaly(pk_group,dbilldate,pk_org,vbillcode,ccustomerid,");
    insertSql
        .append("cdeptid,cemployeeid,cchanneltypeid,cmaterialid,cprodlineid,cbrandid,nnum,norigtaxmny,corigcurrencyid,ntaxmny,ngrouptaxmny,csaleorderid)");
    insertSql
        .append(" select soh.pk_group,soh.dbilldate,soh.pk_org,soh.vbillcode,");
    insertSql
        .append(" soh.ccustomerid,soh.cdeptid,soh.cemployeeid,soh.cchanneltypeid,");
    insertSql
        .append(" sob.cmaterialid,sob.cprodlineid,mar.pk_brand,sum(sob.nnum),sum(sob.norigtaxmny),");
    insertSql.append("soh.corigcurrencyid,sum(sob.ntaxmny),sum(sob.ngrouptaxmny),soh.csaleorderid");
    insertSql
        .append(" from so_saleorder soh inner join so_saleorder_b sob on soh.csaleorderid = sob.csaleorderid");
    insertSql
        .append(" inner join bd_material_v mar on sob.cmaterialid = mar.pk_source ");

    insertSql.append(" where ");
    insertSql.append(" soh.pk_group", AppContext.getInstance().getPkGroup());
    insertSql.append(" and ");
    insertSql.append(" sob.pk_group", AppContext.getInstance().getPkGroup());
    insertSql.append(" and ");
    insertSql.append(" soh.dr", 0);
    insertSql.append(" and ");
    insertSql.append(" sob.dr", 0);
    insertSql.append(" and ");
    insertSql.append(" soh.dbilldate ", " >=", begindate.toString());
    insertSql.append(" and ");
    insertSql.append(" soh.dbilldate ", " <=", enddate.toString());
    insertSql.append(" and ");
    insertSql.append(" sob.dbilldate ", " >=", begindate.toString());
    insertSql.append(" and ");
    insertSql.append(" sob.dbilldate ", " <=", enddate.toString());

    insertSql
        .append(" group by soh.pk_group,soh.dbilldate,soh.pk_org,soh.vbillcode,soh.ccustomerid,");
    insertSql
        .append(" soh.cdeptid,soh.cemployeeid,soh.cchanneltypeid,sob.cmaterialid,sob.cprodlineid,mar.pk_brand,soh.corigcurrencyid,soh.csaleorderid");

    return insertSql.toString();
  }

  /**
   * ִ��ת������
   */
  private void insertDataToMobileOrderanaly() {

    // ֱ��ɾ�����м�¼
    String delSql = "delete from so_mb_orderanaly";

    DataAccessUtils dataAcsUtils = new DataAccessUtils();
    dataAcsUtils.update(delSql); // ɾ����������µļ�¼

    String insertSql = this.getInsertSql();
    dataAcsUtils.update(insertSql); // ���²���������µļ�¼
  }
}
