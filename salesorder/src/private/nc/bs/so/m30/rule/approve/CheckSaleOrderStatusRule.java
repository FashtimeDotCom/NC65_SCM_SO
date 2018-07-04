package nc.bs.so.m30.rule.approve;

import java.util.ArrayList;
import java.util.List;

import nc.vo.so.m30.util.Transfer30and30RVOTool;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.so.pub.util.ListUtil;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * У�����۶�δ�޶�ǰ���۶�������״̬����ֹ�޶�����ʱ�����۶���������
 * @scene
 * ����ǰ
 * @param
 * ��
 *
 * @since 6.3
 * @version 2015-1-5 ����9:40:34
 * @author wangshu6
 */
public class CheckSaleOrderStatusRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    Transfer30and30RVOTool trans = new Transfer30and30RVOTool();
    SaleOrderHistoryVO[] bills = trans.transfer30TOOrderhisVO(vos);
    try {
      String[] fstatusflags = query30BillStatusBeforeRevise(bills);
      if(!ArrayUtil.isEmpty(fstatusflags)){
        for(String fstatusflag : fstatusflags){
          if (!BillStatus.AUDIT.equalsValue(fstatusflag)) {
            ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0498")/*��ǰ���������۶����ѱ����󣬲���������ǰ�޶��汾��*/);
          }
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    
  }
  
  /**
   * �������۶����޶����ݲ�ѯ��Ӧ���۶�������
   * 
   * @param bills ���۶����޶�bills
   * @return saleorderbills ���۶���bills 
   * @throws BusinessException
   */
  public String[] query30BillStatusBeforeRevise(SaleOrderHistoryVO[] bills)
      throws BusinessException {
    if (ArrayUtil.isEmpty(bills)) {
      return null;
    }
    String[] ids = getOrderHistoryPKs(bills);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select fstatusflag ");
    sql.append("from so_saleorder where ");
    sql.append(SaleOrderHVO.CSALEORDERID, ids);
    sql.append(" and dr = 0");

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sql.toString());
    if (set.size() == 0) {
      return null;
    }
    return set.toOneDimensionStringArray();
  }
  /**
   * ��ȡ���۶����޶�����
   * 
   * @param bills ���۶����޶�vo
   * @return ���۶����޶�����
   */
  private String[] getOrderHistoryPKs(SaleOrderHistoryVO[] bills) {

    List<String> list = new ArrayList<String>();

    for (SaleOrderHistoryVO bill : bills) {
      String corderhistoryID = bill.getParentVO().getCorderhistoryid();
      list.add(corderhistoryID);
    }
    return ListUtil.toArray(list);
  }
}
