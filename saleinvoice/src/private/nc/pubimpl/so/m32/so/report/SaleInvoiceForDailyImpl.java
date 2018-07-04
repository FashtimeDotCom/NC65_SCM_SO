package nc.pubimpl.so.m32.so.report;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.so.m32.so.report.ISaleInvoiceForDaily;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.MultiLangText;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.InvoiceFormReportParaVO;
import nc.vo.so.m32.paravo.InvoiceReturnToReportVO;

/**
 * ���۷�Ʊ�ṩ���ۺ��ձ��Ľӿ�ʵ����
 * 
 * @since 6.0
 * @version 2011-1-21 ����08:44:33
 * @author ô��
 */
public class SaleInvoiceForDailyImpl implements ISaleInvoiceForDaily {

  @Override
  public InvoiceReturnToReportVO[] getDailyDataFromInvoice(
      InvoiceFormReportParaVO paravo) throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select t1.csaleinvoiceid ");
    String fromwhere = this.getFormWhere(paravo);
    sql.append(fromwhere);

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    String[] hids = rowset.toOneDimensionStringArray();

    SaleInvoiceVO[] bills = null;
    try {
      BillQuery<SaleInvoiceVO> query =
          new BillQuery<SaleInvoiceVO>(SaleInvoiceVO.class);
      bills = query.query(hids);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    InvoiceReturnToReportVO[] retvo = this.getPavaVOs(bills);
    return retvo;
  }

  private void appendCustCondtion(InvoiceFormReportParaVO paravo,
      SqlBuilder where) {
    // �����ͻ�����
    String[] ccustomerid = paravo.getCcustomerids();
    if (null != ccustomerid && ccustomerid.length > 0) {
      where.append(" and ");
      where.append("t1.ccustomerid", ccustomerid);
    }

    // �����ͻ�����
    MultiLangText ccustomername = paravo.getCcustomernames();
    if (null != ccustomername) {
      where.append(" and ");
      where.append("t5.name like '" + ccustomername.getText() + "%'");
    }

    // �����ͻ���������
    String[] pk_custclass = paravo.getPk_custclass();
    if (null != pk_custclass && pk_custclass.length > 0) {
      where.append(" and ");
      where.append("t5.pk_custclass", pk_custclass);
    }

    // �����ͻ����۷���
    String[] pk_custsaleclass = paravo.getPk_custsaleclass();
    if (null != pk_custsaleclass && pk_custsaleclass.length > 0) {
      where.append(" and ");
      where.append("t6.pk_custsaleclass", pk_custsaleclass);
    }

    // �����ͻ���������
    String[] ccustomerpk_areacl = paravo.getCustomerpk_areacls();
    if (null != ccustomerpk_areacl && ccustomerpk_areacl.length > 0) {
      where.append(" and ");
      where.append("t5.pk_areacl", ccustomerpk_areacl);
    }
  }

  private void appendDbilldateAndSaleOrg(InvoiceFormReportParaVO paravo,
      SqlBuilder where, boolean ishasbodyitem) {
    where.startParentheses();
    where.append("t1.dbilldate", ">=", paravo.getStartdate().toString());
    where.append(" and ");
    where.append("t1.dbilldate", "<=", paravo.getEnddate().toLocalString());
    where.endParentheses();
    // ������֯
    String[] saleorgid = paravo.getSaleorgids();
    if (null != saleorgid && saleorgid.length > 0) {
      where.append(" and ");
      where.append("t1.pk_org", saleorgid);
    }

    if (ishasbodyitem) {
      where.append(" and ");
      where.startParentheses();
      where.append("t2.dbilldate", ">=", paravo.getStartdate().toString());
      where.append(" and ");
      where.append("t2.dbilldate", "<=", paravo.getEnddate().toString());
      where.endParentheses();
      if (null != saleorgid && saleorgid.length > 0) {
        where.append(" and ");
        where.append("t2.pk_org", saleorgid);
      }
    }
  }

  private void appendFixCondtion(boolean ishasbodyitem, SqlBuilder where) {
    String pk_group = BSContext.getInstance().getGroupID();
    where.append(" and ");
    where.append("t1.pk_group", pk_group);
    where.append(" and ");
    where.append("t1.dr", 0);
    if (ishasbodyitem) {
      where.append(" and ");
      where.append("t2.dr", 0);
    }
  }

  private void appendMaterialCondtion(InvoiceFormReportParaVO paravo,
      SqlBuilder where) {

    // ���ϱ���
    String[] cmaterialvid = paravo.getCmaterialvids();
    if (null != cmaterialvid && cmaterialvid.length > 0) {
      where.append(" and ");
      where.append("t2.cmaterialvid", cmaterialvid);
    }
    // ��������
    MultiLangText cmaterialvname = paravo.getCmaterialvname();
    if (null != cmaterialvname) {
      where.append(" and ");
      where.append("t3.name like '" + cmaterialvname.getText() + "%'");
    }
    // ���ϻ�������
    String[] pk_marbasclass = paravo.getPk_marbasclass();
    if (null != pk_marbasclass && pk_marbasclass.length > 0) {
      where.append(" and ");
      where.append("t3.pk_marbasclass", pk_marbasclass);
    }
    // �������۷���
    String[] pk_marsaleclass = paravo.getPk_marsaleclass();
    if (null != pk_marsaleclass && pk_marsaleclass.length > 0) {
      where.append(" and ");
      where.append("t4.pk_marsaleclass", pk_marsaleclass);
    }
  }

  private void appendOtherCondtion(InvoiceFormReportParaVO paravo,
      SqlBuilder where) {

    // ����
    String[] cdeptvid = paravo.getCdeptvids();
    if (null != cdeptvid && cdeptvid.length > 0) {
      where.append(" and ");
      where.append("t1.cdeptvid", cdeptvid);
    }
    // ҵ��Ա
    String[] cemployeeid = paravo.getCemployeeids();
    if (null != cemployeeid && cemployeeid.length > 0) {
      where.append(" and ");
      where.append("t1.cemployeeid", cemployeeid);
    }

    // ����
    String[] corigcurrencyid = paravo.getCorigcurrencyids();
    if (null != corigcurrencyid && corigcurrencyid.length > 0) {
      where.append(" and ");
      where.append("t1.corigcurrencyid", corigcurrencyid);
    }
    // ����״̬
    int[] fstatusflag = paravo.getFstatusflag();
    if (null != fstatusflag && fstatusflag.length > 0) {
      where.append(" and ");
      where.append("t1.fstatusflag", fstatusflag);
    }

    // �����֯
    String[] stockorgvid = paravo.getStockorgvids();
    if (null != stockorgvid && stockorgvid.length > 0) {
      where.append(" and ");
      where.append("t2.csendstockorgvid", stockorgvid);
    }
    // ��������
    String[] vtrantypecode = paravo.getVtrantypecodes();
    if (null != vtrantypecode && vtrantypecode.length > 0) {
      where.append(" and ");
      where.append("t1.vtrantypecode", vtrantypecode);
    }
    // ͳ�Ʒ�����ۿ�
    UFBoolean containlaboranddiscount = paravo.getContainlaboranddiscount();
    if (null != containlaboranddiscount
        && !containlaboranddiscount.booleanValue()) {
      where.append(" and ");
      where.append("t2.blaborflag", UFBoolean.FALSE);
      where.append(" and ");
      where.append("t2.bdiscountflag", UFBoolean.FALSE);
    }
  }

  private String getFormWhere(InvoiceFormReportParaVO paravo) {

    String from = this.getOnlyFromSQL(paravo);
    boolean ishasbodyitem = this.getIshasbodyitem(paravo);
    String where = this.getOnlyWhereSQL(paravo, ishasbodyitem);

    return from + where;
  }

  private boolean getIshasbodyitem(InvoiceFormReportParaVO paravo) {
    boolean ishasbodyitem = false;
    // ���ϱ���
    if (null != paravo.getCmaterialvids()
        && paravo.getCmaterialvids().length > 0) {
      ishasbodyitem = true;
    }
    // ��������
    if (null != paravo.getCmaterialvname()) {
      ishasbodyitem = true;
    }
    // ���ϻ�������
    if (null != paravo.getPk_marbasclass()
        && paravo.getPk_marbasclass().length > 0) {
      ishasbodyitem = true;
    }
    // �������۷���
    if (null != paravo.getPk_marsaleclass()
        && paravo.getPk_marsaleclass().length > 0) {
      ishasbodyitem = true;
    }

    // �����֯
    if (null != paravo.getStockorgvids() && paravo.getStockorgvids().length > 0) {
      ishasbodyitem = true;
    }

    // ͳ�Ʒ�����ۿ�
    if (!ishasbodyitem && UFBoolean.TRUE == paravo.getContainlaboranddiscount()) {
      ishasbodyitem = true;
    }
    return ishasbodyitem;
  }

  private boolean getIshascustitem(InvoiceFormReportParaVO paravo) {
    boolean ishascustitem = false;

    // �����ͻ�����
    if (null != paravo.getCcustomernames()) {
      ishascustitem = true;
    }
    // �����ͻ���������
    if (null != paravo.getPk_custclass() && paravo.getPk_custclass().length > 0) {
      ishascustitem = true;
    }
    // �����ͻ����۷���
    if (null != paravo.getPk_custsaleclass()
        && paravo.getPk_custsaleclass().length > 0) {
      ishascustitem = true;
    }
    // �����ͻ���������
    if (null != paravo.getCustomerpk_areacls()
        && paravo.getCustomerpk_areacls().length > 0) {
      ishascustitem = true;
    }
    return ishascustitem;
  }

  private boolean getIshasmaterialitem(InvoiceFormReportParaVO paravo) {
    boolean ishasmaterialitem = false;

    // ��������
    if (null != paravo.getCmaterialvname()) {
      ishasmaterialitem = true;
    }
    // ���ϻ�������
    if (null != paravo.getPk_marbasclass()
        && paravo.getPk_marbasclass().length > 0) {
      ishasmaterialitem = true;
    }
    // �������۷���
    if (null != paravo.getPk_marsaleclass()
        && paravo.getPk_marsaleclass().length > 0) {
      ishasmaterialitem = true;
    }
    return ishasmaterialitem;
  }

  private String getOnlyFromSQL(InvoiceFormReportParaVO paravo) {
    SqlBuilder from = new SqlBuilder();
    // �Ƿ�����ӱ�����
    boolean ishasbodyitem = this.getIshasbodyitem(paravo);

    // �Ƿ������������
    boolean ishasmaterialitem = this.getIshasmaterialitem(paravo);

    // �Ƿ�����ͻ�����
    boolean ishascustitem = this.getIshascustitem(paravo);
    from.append(" from so_saleinvoice t1");
    if (ishasbodyitem) {
      from.append(" inner join so_saleinvoice_b t2 ");
      from.append(" on t2.csaleinvoiceid = t1.csaleinvoiceid");
    }
    if (ishasmaterialitem) {
      from.append(" left outer join bd_material t3");
      from.append(" on t3.pk_material = t2.cmaterialvid");
      from.append(" inner join bd_materialsale t4 ");
      from.append(" on t4.pk_material = t3.pk_material");
    }

    if (ishascustitem) {
      from.append(" left outer join bd_customer t5");
      from.append(" on t5.pk_customer = t1.ccustomerid");
      from.append(" inner join bd_custsale t6 on t6.pk_customer = t5.pk_customer");
    }
    return from.toString();
  }

  private String getOnlyWhereSQL(InvoiceFormReportParaVO paravo,
      boolean ishasbodyitem) {
    SqlBuilder where = new SqlBuilder();
    where.append(" where ");
    this.appendDbilldateAndSaleOrg(paravo, where, ishasbodyitem);
    // ƴ�ӿͻ�����
    this.appendMaterialCondtion(paravo, where);
    // ƴ�ӿͻ�����
    this.appendCustCondtion(paravo, where);

    this.appendOtherCondtion(paravo, where);
    // ƴ�ӹ̶�����
    this.appendFixCondtion(ishasbodyitem, where);

    return where.toString();
  }

  private InvoiceReturnToReportVO[] getPavaVOs(SaleInvoiceVO[] bills) {
    if (null == bills) {
      return new InvoiceReturnToReportVO[0];
    }
    List<InvoiceReturnToReportVO> volist =
        new ArrayList<InvoiceReturnToReportVO>();
    for (SaleInvoiceVO bill : bills) {
      SaleInvoiceHVO hvo = bill.getParentVO();
      SaleInvoiceBVO[] bvos = bill.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        InvoiceReturnToReportVO retvo = new InvoiceReturnToReportVO();
        // ������֯
        retvo.setSaleorgid(hvo.getPk_org());
        // �����֯
        retvo.setStockorgvid(bvo.getCsendstockorgvid());
        retvo.setCmaterialvid(bvo.getCmaterialvid());
        retvo.setCcustomerid(bvo.getCordercustid());
        retvo.setCinvoicecustid(hvo.getCinvoicecustid());
        retvo.setBlargessflag(bvo.getBlargessflag());
        retvo.setCunitid(bvo.getCunitid());
        retvo.setNnum(bvo.getNnum());
        retvo.setNorigmny(bvo.getNorigmny());
        retvo.setNorigtaxmny(bvo.getNorigtaxmny());
        volist.add(retvo);
      }
    }
    InvoiceReturnToReportVO[] retvos =
        new InvoiceReturnToReportVO[volist.size()];
    retvos = volist.toArray(retvos);
    return retvos;
  }

}
