package nc.pubimpl.so.m30.opc.mecc;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m30.opc.mecc.ISaleOrderQueryForMecc;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * ���ݵ�������Ԥ����ͷID����ID��ȡ�������۶���ͷID����ID�����۶����г���ر�״̬
 * 
 * @since 6.0
 * @version 2012-02-14 ����15:44:05
 * @author ����
 */
public class SaleOrderQueryForMeccImpl implements ISaleOrderQueryForMecc {

  @Override
  public SaleOrderBVO[] query(String[] csrcid, String[] csrcbid,
      String[] fieldnames) throws BusinessException {
    SaleOrderBVO[] saleOrderBVO = null;
    try {
      SqlBuilder swhere = new SqlBuilder();
      String pk_group = AppContext.getInstance().getPkGroup();
      swhere.append(" and ");
      swhere.append(SaleOrderBVO.PK_GROUP, pk_group);
      swhere.append(" and ");
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      swhere.append(iq.buildSQL(SaleOrderBVO.CSRCID, csrcid));
      swhere.append(" and ");

      iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
      swhere.append(iq.buildSQL(SaleOrderBVO.CSRCBID, csrcbid));
      VOQuery<SaleOrderBVO> query =
          new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, fieldnames);
      saleOrderBVO = query.query(swhere.toString(), null);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return saleOrderBVO;
  }

  /**
   * ������������۶�����Ų�ѯ���۶�����Ϣ
   * 
   * @param saleorerbids ���۶�������ID����
   * @param fieldnames ������ѯ���ֶ�����
   * @return ���۶���viewVO����
   * @throws BusinessException
   * @author ������
   */
  @Override
  public SaleOrderViewVO[] querySaleOrderViewVO(String[] saleorerbids,
      String[] fieldnames) throws BusinessException {
    if (null == saleorerbids || saleorerbids.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0461")/*���۶�������IDΪ��*/);
    }
    if (null == fieldnames || fieldnames.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0462")/*���۶����ֶ�����Ϊ��*/);
    }
    SaleOrderViewVO[] saleOrderViewVOs = null;
    try {
      // ���viewVO
      EfficientViewQuery<SaleOrderViewVO> viewQuery =
          new EfficientViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class,
              fieldnames);
      saleOrderViewVOs = viewQuery.query(saleorerbids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return saleOrderViewVOs;
  }
}
