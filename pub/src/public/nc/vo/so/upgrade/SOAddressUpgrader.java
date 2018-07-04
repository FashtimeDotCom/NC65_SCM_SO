package nc.vo.so.upgrade;

import nc.vo.scmpub.upgrade.address.CustSupAddrUpgradeSqlVO;
import nc.vo.scmpub.upgrade.address.CustSupAddrUpgrader;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.SOTable;

/**
 * ���۹���ͻ���ַ����
 * 
 * @since 6.3
 * @version 2013-06-05 09:20:27
 * @author liujingn
 */
public class SOAddressUpgrader {

  /**
   * 
   */
  public void doUpgrade() {
    CustSupAddrUpgrader upgrader = new CustSupAddrUpgrader();
    upgrader.addSqlVO(this.getSqlForCustOn38());
    upgrader.addSqlVO(this.getSqlForCustOn30());
    upgrader.addSqlVO(this.getSqlForCustOn30history());
    // upgrader.addSqlVO(this.getSqlForCustOn4331());
    upgrader.addSqlVO(this.getSqlForCustOn32());
    upgrader.doUpgrade();
  }

  /**
   * �ͻ� - Ԥ����B��
   * 
   * @return SQL��װ��ʵ����
   */
  private CustSupAddrUpgradeSqlVO getSqlForCustOn38() {
    CustSupAddrUpgradeSqlVO vo = new CustSupAddrUpgradeSqlVO();
    vo.setCustSupExp("so_preorder_b.creceivecustid");
    vo.setGroupExp("so_preorder_b.pk_group");
    vo.setOrgExp("so_preorder_b.pk_org");
    vo.setRefField(PreOrderBVO.CRECEIVEADDRID);
    vo.setRefTable(SOTable.PREORDER_B.getName());
    vo.setRefTablePkField(PreOrderBVO.CPREORDERBID);
    vo.setSameTableFlag(true);
    vo.setType(CustSupAddrUpgradeSqlVO.CUSTOMER);
    return vo;
  }

  /**
   * �ͻ� - ���۶���B��
   * 
   * @return SQL��װ��ʵ����
   */
  private CustSupAddrUpgradeSqlVO getSqlForCustOn30() {
    CustSupAddrUpgradeSqlVO vo = new CustSupAddrUpgradeSqlVO();
    vo.setCustSupExp("so_saleorder_b.creceivecustid");
    vo.setGroupExp("so_saleorder_b.pk_group");
    vo.setOrgExp("so_saleorder_b.pk_org");
    vo.setRefField(SaleOrderBVO.CRECEIVEADDRID);
    vo.setRefTable(SOTable.SALEORDER_B.getName());
    vo.setRefTablePkField(SaleOrderBVO.CSALEORDERBID);
    vo.setSameTableFlag(true);
    vo.setType(CustSupAddrUpgradeSqlVO.CUSTOMER);
    return vo;
  }

  /**
   * �ͻ� - ���۶����޶�B��
   * 
   * @return SQL��װ��ʵ����
   */
  private CustSupAddrUpgradeSqlVO getSqlForCustOn30history() {
    CustSupAddrUpgradeSqlVO vo = new CustSupAddrUpgradeSqlVO();
    vo.setCustSupExp("so_orderhistory_b.creceivecustid");
    vo.setGroupExp("so_orderhistory_b.pk_group");
    vo.setOrgExp("so_orderhistory_b.pk_org");
    vo.setRefField(SaleOrderBVO.CRECEIVEADDRID);
    vo.setRefTable(SOTable.ORDERHISTORY_B.getName());
    vo.setRefTablePkField(SaleOrderBVO.CSALEORDERBID);
    vo.setSameTableFlag(true);
    vo.setType(CustSupAddrUpgradeSqlVO.CUSTOMER);
    return vo;
  }

  // /**
  // * �ͻ� - ������B��
  // *
  // * @return SQL��װ��ʵ����
  // */
  // private CustSupAddrUpgradeSqlVO getSqlForCustOn4331() {
  // CustSupAddrUpgradeSqlVO vo = new CustSupAddrUpgradeSqlVO();
  // vo.setCustSupExp("so_delivery_b.creceivecustid");
  // vo.setGroupExp("so_delivery_b.pk_group");
  // vo.setOrgExp("so_delivery_b.pk_org");
  // vo.setRefField(DeliveryBVO.CRECEIVEADDRID);
  // vo.setRefTable(SOTable.DELIVERY_B.getName());
  // vo.setRefTablePkField(DeliveryBVO.CDELIVERYBID);
  // vo.setSameTableFlag(true);
  // vo.setType(CustSupAddrUpgradeSqlVO.CUSTOMER);
  // return vo;
  // }

  /**
   * �ͻ� - ���۷�ƱB��
   * 
   * @return SQL��װ��ʵ����
   */
  private CustSupAddrUpgradeSqlVO getSqlForCustOn32() {
    CustSupAddrUpgradeSqlVO vo = new CustSupAddrUpgradeSqlVO();
    vo.setCustSupExp("so_saleinvoice_b.creceivecustid");
    vo.setGroupExp("so_saleinvoice_b.pk_group");
    vo.setOrgExp("so_saleinvoice_b.pk_org");
    vo.setRefField(SaleInvoiceBVO.CRECEIVEADDRID);
    vo.setRefTable(SOTable.SALEINVOICE_B.getName());
    vo.setRefTablePkField(SaleInvoiceBVO.CSALEINVOICEBID);
    vo.setSameTableFlag(true);
    vo.setType(CustSupAddrUpgradeSqlVO.CUSTOMER);
    return vo;
  }
}
