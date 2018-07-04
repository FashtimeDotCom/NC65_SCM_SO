package nc.bs.so.m30.revise.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.m30.util.Transfer30and30RVOTool;

/**
 * @description
 *              ���۶����޶�����ǰ�汾�Ÿ��¹��򡢱�����ԭʼ�汾
 * @scene
 *        ���۶����޶�����ǰ
 * @param ��
 * @since 6.0
 * @version 2011-6-9 ����04:13:15
 * @author ��־ΰ
 */
public class UpdateVersionNumRule implements ICompareRule<SaleOrderHistoryVO> {

  @Override
  public void process(SaleOrderHistoryVO[] vos, SaleOrderHistoryVO[] originVOs) {
    for (SaleOrderVO vo : vos) {
      // modify by wangshu6 for ���۶����޶�֧�������� �汾�Ÿ���ʱ��ѯ���ݿ⣬����汾���м�1
      SaleOrderHVO head = vo.getParentVO();
      String csaleorderid = head.getCsaleorderid();
      SqlBuilder sql = new SqlBuilder();
      sql.append("select max(iversion) iversion ");
      sql.append("from so_orderhistory where ");
      sql.append(SaleOrderHVO.CSALEORDERID, csaleorderid);
      sql.append(" and dr = 0");

      DataAccessUtils dataUtil = new DataAccessUtils();
      IRowSet set = dataUtil.query(sql.toString());
      String[] iversions = set.toOneDimensionStringArray();

      int iMaxVersion = 0;
      if (!ArrayUtil.isEmpty(iversions)&&iversions[0]!=null) {
        iMaxVersion = Integer.valueOf(iversions[0]);
      }
      else {
        // û�в�ѯ���޶��汾�ľ���Ϊ��û���޶���������Ҫ������ԭʼ�汾
        // modify by zhangby5 ���۶����޶�ReviseSaveSaleOrderAction��ȡ����originVOsΪ��ǰ�޸ĺ�ģ���Ҫ��ѯ���ݿ������µ����۶���
        BillQuery<SaleOrderVO> query =
            new BillQuery<SaleOrderVO>(SaleOrderVO.class);
        SaleOrderVO[] originSaleOrderVOs = query.query(new String[]{csaleorderid});
        if(ArrayUtil.isEmpty(originSaleOrderVOs)){
          ExceptionUtils
              .wrappBusinessException(NCLangResOnserver.getInstance()
                  .getStrByID("4006011_0", "04006011-0512")/*�����۶������ڱ����˲��������Ժ�ˢ�²����²�����*/);
        }
        Transfer30and30RVOTool trans = new Transfer30and30RVOTool();
        SaleOrderHistoryVO hisVO = trans.transfer30TOOrderhisVO(originSaleOrderVOs[0]);
        hisVO.getParentVO().setCorderhistoryid(vo.getPrimaryKey());
        hisVO.getParentVO().setIversion(iMaxVersion);
        BillInsert<SaleOrderHistoryVO> bo =
            new BillInsert<SaleOrderHistoryVO>();
        bo.insert(new SaleOrderHistoryVO[] {
            hisVO
        });
      }
      head.setIversion(iMaxVersion + 1);
    }
  }
}
