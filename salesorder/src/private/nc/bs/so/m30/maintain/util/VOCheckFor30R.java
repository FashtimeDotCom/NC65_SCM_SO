package nc.bs.so.m30.maintain.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.m30.util.Transfer30and30RVOTool;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.so.pub.util.ListUtil;

/**
 * voУ����1
 * 
 * @since 6.36
 * @version 2014-12-26 ����3:05:57
 * @author wangshu6
 */
public class VOCheckFor30R {
  
  /**
   * ���۶����޶�����ʱ vocheck
   * 
   * @param bills
   * @throws BusinessException
   */
  public void voCheckForRevise(SaleOrderHistoryVO[] bills)
      throws BusinessException {
    // 1. ���� ���۶����޶�����ʱ �����۶����������м���
    lock(bills);
    // 2. У��汾���Ƿ�����
    checkLatestIversion(bills);
    SaleOrderVO[] saleorderVO = this.query30BeforeRevise(bills);
    // 3. �޶�ʱ У�����۶��������۶����޶�ts
    if (saleorderVO[0] != null) {
      check30And30RTS(bills[0], saleorderVO[0]);
    }
  }

  /**
   * ���۶����޶�����ʱ vocheck
   * 
   * @param bills
   * @throws BusinessException
   */
  public SaleOrderHistoryVO[] voCheckAndQueryOriginForRevise(
      SaleOrderHistoryVO[] bills) throws BusinessException {
    // 1. ���� ���۶����޶�����ʱ �����۶����������м���
    lock(bills);
    // 2. У��汾���Ƿ�����
    checkLatestIversion(bills);
    SaleOrderVO[] saleorderVO = this.query30BeforeRevise(bills);
    // 3. �޶�ʱ У�����۶��������۶����޶�ts
    if (saleorderVO[0] != null) {
      check30And30RTS(bills[0], saleorderVO[0]);
    }
    return new Transfer30and30RVOTool().transfer30TOOrderhisVO(saleorderVO);
  }
  
  /**
   * ���۶����޶� �ύ ����ʱ vocheck
   * 
   * @param bills
   * @throws BusinessException
   */
  public void voCheckForSaveAndApprove(SaleOrderHistoryVO[] bills)
      throws BusinessException {

    // 1. ����
    lock(bills);
    // 2. У��ts
    this.check30RTS(bills);
    // 3. У��汾���Ƿ�����
    this.checkLatestIversion(bills);
  }

  /**
   * У��汾���Ƿ�����
   * 
   * @param bills
   * @return �Ƿ��й��޶��汾
   * @throws BusinessException
   */
  private boolean checkLatestIversion(SaleOrderHistoryVO[] bills)
      throws BusinessException {

    String[] saleoderIDs = getSaleOrderPKs(bills);
    // 1. �����ݿ��ѯ���°汾�汾��
    SaleOrderHistoryVO[] orderhisVOs =
        this.queryMaxIversionFromOrderHistory(saleoderIDs);

    Map<String, SaleOrderHistoryVO> saleorderIDAndHisVOMap =
        getSaleOrderIDAndMaxVersionHisVOMap(orderhisVOs);
    if (saleorderIDAndHisVOMap == null) {
      return false;
    }
    for (SaleOrderHistoryVO bill : bills) {

      String csaleorderID = bill.getParentVO().getCsaleorderid();
      SaleOrderHistoryVO hisVO = saleorderIDAndHisVOMap.get(csaleorderID);

      // 2. ������޶��汾�� ��У�鵱ǰ�����Ƿ����޶����°汾; ���û���޶��汾 ���ѯ�����۶�������
      if (hisVO == null) {
        return false;
      }
      else {
        // У�鵱ǰ�����Ƿ����޶����°汾
        Integer iversion = bill.getParentVO().getIversion();
        if (!iversion.equals(hisVO.getParentVO().getIversion())) {
          ExceptionUtils
              .wrappBusinessException(NCLangResOnserver.getInstance()
                  .getStrByID("4006011_0", "04006011-0511")/*
                                                            * ��ǰ�������ݲ��������޶��汾����ˢ�£�
                                                            */);
        }
      }
    }
    return true;
  }

  /**
   * �����۶����޶����汾vo���һ��<���۶����޶�caleorderid,�Ѿ��޶��������汾vo>��Map
   * 
   * @param orderhisVOs �Ѿ������汾�ŵ��޶�vo
   * @return
   */
  private Map<String, SaleOrderHistoryVO> getSaleOrderIDAndMaxVersionHisVOMap(
      SaleOrderHistoryVO[] orderhisVOs) {
    if (ArrayUtil.isEmpty(orderhisVOs)) {
      return new HashMap<String, SaleOrderHistoryVO>();
    }
    Map<String, SaleOrderHistoryVO> saleorderIDAndHisVOMap =
        new HashMap<String, SaleOrderHistoryVO>();
    for (SaleOrderHistoryVO saleOrderHistoryVO : orderhisVOs) {
      saleorderIDAndHisVOMap.put(saleOrderHistoryVO.getParentVO()
          .getCsaleorderid(), saleOrderHistoryVO);
    }
    return saleorderIDAndHisVOMap;
  }

  /**
   * �������۶����޶����ݲ�ѯ��Ӧ���۶�������
   * 
   * @param bills ���۶����޶�bills
   * @return saleorderbills ���۶���bills
   * @throws BusinessException
   */
  public SaleOrderVO[] query30BeforeRevise(SaleOrderHistoryVO[] bills)
      throws BusinessException {
    if (ArrayUtil.isEmpty(bills)) {
      return null;
    }
    String[] ids = getSaleOrderPKs(bills);
    SaleOrderVO[] saleorderbills = null;
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select csaleorderid ");
    sql.append(" from so_saleorder where ");
    sql.append(SaleOrderHVO.CSALEORDERID, ids);
    sql.append(" and dr = 0");

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sql.toString());
    if (set.size() == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0525")/*���۶����ѱ�ɾ�����������޶��������б����ˢ�µ���*/);
    }

    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    saleorderbills = query.query(set.toOneDimensionStringArray());
    return saleorderbills;

  }

  /**
   * �����۶����޶����в�ѯ���°汾��
   * 
   * @param vos ���۶����޶�vo
   * @return �汾��
   */
  public String queryMaxIversionFromOrderHistory2(SaleOrderHistoryVO[] vos) {

    String[] iversions = null;
    for (SaleOrderHistoryVO vo : vos) {
      SaleOrderHistoryHVO head = vo.getParentVO();
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
    }
    if (ArrayUtil.isEmpty(iversions)) {
      return null;
    }
    return iversions[0];
  }

  public SaleOrderHistoryVO[] queryMaxIversionFromOrderHistory(
      String[] saleoderIDs) {
    IDQueryBuilder sqlBuilder = new IDQueryBuilder();
    String insql =
        sqlBuilder.buildSQL("h." + SaleOrderHistoryHVO.CSALEORDERID,
            saleoderIDs);

    SaleOrderHistoryVO[] bills = null;
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select a.corderhistoryid from so_orderhistory a, ");
    sql.append(" (select csaleorderid, max(iversion) as iversion from so_orderhistory h ");
    sql.append(" where ");
    sql.append(insql);
    sql.append(" and h.dr = 0 ");
    sql.append(" group by csaleorderid) maxhs ");
    sql.append(" where a.csaleorderid = maxhs.csaleorderid ");
    sql.append(" and a.iversion = maxhs.iversion ");
    sql.append(" and a.dr = 0 ");

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sql.toString());
    if (set.size() == 0) {
      return null;
    }

    BillQuery<SaleOrderHistoryVO> query =
        new BillQuery<SaleOrderHistoryVO>(SaleOrderHistoryVO.class);
    bills = query.query(set.toOneDimensionStringArray());
    return bills;
  }

  /**
   * ����
   * 
   * @param ��������
   */
  private void lock(SaleOrderHistoryVO[] bills) {
    String[] csaleOrderIDs = getSaleOrderPKs(bills);
    new LockOperator()
        .lock(
            csaleOrderIDs,
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0512")/* �����۶������ڱ����˲��������Ժ�ˢ�²����²����� */);
  }

  /**
   * ��ȡ���۶�������
   * 
   * @param bills ���۶����޶�vo
   * @return ���۶�������
   */
  private String[] getSaleOrderPKs(SaleOrderHistoryVO[] bills) {

    List<String> list = new ArrayList<String>();

    for (SaleOrderHistoryVO bill : bills) {
      String csaleorderID = bill.getParentVO().getCsaleorderid();
      list.add(csaleorderID);
    }
    return ListUtil.toArray(list);
  }

  /**
   * У�����۶��������۶����޶�voTS
   * 
   * @param orderhistoryVO ���۶����޶�
   * @param saleorderVO ���۶���
   */
  public void check30And30RTS(SaleOrderHistoryVO bill, SaleOrderVO saleorderVO) {
    if (!bill.getParentVO().getTs().equals(saleorderVO.getParentVO().getTs())
        && !BillStatus.AUDIT.equalsValue(saleorderVO.getParentVO()
            .getFstatusflag())) {

      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0526")/* ���۶����ѱ��������������۶������ٲ�����*/);

    }
  }
  
  /**
   * У�����۶����޶�voʱ���
   * 
   * @param updatebills ǰ̨����������ʵ��
   * @param originBills ���ݿ�����ԭʼʵ��
   */
  private void check30RTS(SaleOrderHistoryVO[] bills) {
    BillTransferTool<SaleOrderHistoryVO> transferTool =
        new BillTransferTool<SaleOrderHistoryVO>(bills);
    SaleOrderHistoryVO[] updatebills = transferTool.getClientFullInfoBill();
    SaleOrderHistoryVO[] originBills = transferTool.getOriginBills();
    for (SaleOrderHistoryVO updatebill : updatebills) {
      String corderhisIDforUpdate = updatebill.getPrimaryKey();
      for (SaleOrderHistoryVO originBill : originBills) {
        if (corderhisIDforUpdate.equals(originBill.getPrimaryKey())) {
          if (updatebill.getParentVO().getTs()
              .equals(originBill.getParentVO().getTs())) {
            return;
          }
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4006011_0", "04006011-0514")/* ��ǰ�����ѱ���������ˢ�£� */);
        }
      }
    }
  }
}
