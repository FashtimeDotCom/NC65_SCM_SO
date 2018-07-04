package nc.vo.so.upgrade;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.DBHintConstantValue;
import nc.vo.so.pub.SOTable;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.bs.pub.pa.AlertRegistryUpgrader;

import nc.impl.pubapp.pattern.database.DataAccessUtils;

import nc.scmmm.upgrade.scmpub.AbstractUpgradeToV61;

/**
 * ���۹����V61��V63������
 * 
 * @since 6.3
 * @version 2012-12-25 13:30:03
 * @author wangtwa
 */
public class SOUpgradeToV63 extends AbstractUpgradeToV61 {

  /**
   * Ԥ��/��̨���������۹���ģ�����֯��Ԫ��ֵ�ֶ���
   */
  private static final String PK_ORG = "pk_org";

  @Override
  protected void doBeforeUpdateDBFrom61To63() throws BusinessException {
    super.doBeforeUpdateDBFrom61To63();
    DataAccessUtils accessutil = new DataAccessUtils();
    // ��Ĭ��ģ�����õ��ʲ���ζ��޸ĳ�ˮƽ
    SqlBuilder updatesql3 = new SqlBuilder();
    updatesql3.append("update");
    updatesql3.append(" pub_systemplate_base ");
    updatesql3.append(" set ");
    updatesql3.append(" layer= '0' ");
    updatesql3.append(" where ");
    updatesql3.append(" (layer is null or layer<> 0) and (moduleid = '4006')");
    accessutil.update(updatesql3.toString());
  }

  @Override
  protected void doBeforeUpdateDataFrom61To63() throws BusinessException {

    /*** 1. ����Ԥ��/��̨������֯��Ԫ���� begin */
    AlertRegistryUpgrader upgrader =
        new AlertRegistryUpgrader(NCModule.SO.getName());
    // ɾ����Ԥ��/��̨�����ӱ�����ֵΪ��֯��Ԫ������
    // ����1Ϊ���۹���Ԥ�����͵�pkֵ(��pub_alerttype�е�pk_alerttype�ֶ�)
    // Ԥ�����Զ�ʧЧ�رպ�̨��������
    upgrader.putAlertType("1001Z810000000002PZU", SOUpgradeToV63.PK_ORG);
    // ���۵��Զ�ʧЧ�رպ�̨��������
    upgrader.putAlertType("1001Z810000000002PZY", SOUpgradeToV63.PK_ORG);
    // ���۵��Զ�ʧЧԤ������
    upgrader.putAlertType("1001Z81000000000K301", SOUpgradeToV63.PK_ORG);
    // Ԥ�����Զ�ʧЧԤ��Ԥ������
    upgrader.putAlertType("1001Z81000000000KURT", SOUpgradeToV63.PK_ORG);
    upgrader.doUpgrade();
    /*** ����Ԥ��/��̨������֯��Ԫ���� end */

    /*** 2. �������۱��۵����涯����"SAVEBASE"��Ϊ"WRITE" begin */
    DataAccessUtils accessutil = new DataAccessUtils();
    // ���ı��ݶ����� pub_billaction
    SqlBuilder updatesql1 = new SqlBuilder();
    updatesql1.append(" update ");
    updatesql1.append(" pub_billaction ");
    updatesql1.append(" set ");
    updatesql1.append(" actiontype= 'WRITE' ");
    updatesql1.append(" where ");
    updatesql1.append(" pk_billtype like  '4310%' and actiontype='SAVEBASE' ");
    accessutil.update(updatesql1.toString());
    // ���ı��ݶ�����Ӧ�Ľű�class�� pub_busiclass
    SqlBuilder updatesql2 = new SqlBuilder();
    updatesql2.append(" update ");
    updatesql2.append(" pub_busiclass ");
    updatesql2.append(" set ");
    updatesql2.append(" actiontype= 'WRITE', classname = 'N_4310_WRITE' ");
    updatesql2.append(" where ");
    updatesql2
        .append(" classname = 'N_4310_SAVEBASE' and actiontype= 'SAVEBASE' ");
    accessutil.update(updatesql2.toString());
    /*** 2. �������۱��۵����涯����"SAVEBASE"��Ϊ"WRITE" end */

  }

  @Override
  protected void doAfterUpdateDataFrom61To63() throws BusinessException {
    super.doAfterUpdateDataFrom61To63();

    /*** 1.��ַ������ ****/
    new SOAddressUpgrader().doUpgrade();

    DataAccessUtils accessutil = new DataAccessUtils();

    /*** 2.���۳��ⵥ������ϸ���ɱ����ͳɱ��������� ****/
    if (SysInitGroupQuery.isIAEnabled()) {
      String updatemny =
          "update SO_SQUAREOUT_D outd set outd.ncostmny=(select nmny "
              + "from IA_I5BILL_B i5b where i5b.csrcbid=outd.csalesquaredid and "
              + "i5b.dr=0 and i5b.nprice is not null and i5b.nmny is not null) "
              + "where outd.dr=0 and fsquaretype =4 ";

      String updateprice =
          "update SO_SQUAREOUT_D outd set outd.ncostprice=(select nprice "
              + "from IA_I5BILL_B i5b where i5b.csrcbid=outd.csalesquaredid and "
              + "i5b.dr=0 and i5b.nprice is not null and i5b.nmny is not null) "
              + "where outd.dr=0 and fsquaretype =4";
      accessutil.update(updatemny);
      accessutil.update(updateprice);
    }
    /*** 3.Ԥ�������Ų�ѯģ���������ܽڵ�ż�nodekey���� ****/
    accessutil
        .update(" update pub_systemplate set nodekey=null,funnode = '40060202' where tempstyle=1 and templateid='1002Z810000000005B4K' and funnode = '40060201' ");
    accessutil
        .update(" update pub_query_templet set node_code = '40060202' where parentid='1002Z810000000005B4K' and  node_code ='40060201' ");
    /*** 4.�˻����յ�����ģ��nodekey���� ****/
    accessutil
        .update(" update pub_systemplate set nodekey=null where tempstyle=0 and templateid='1001Z81000000000L7AC' and funnode = '4006040201' ");

  }

  @Override
  protected void doAfterUpdateDataFrom60To61() throws BusinessException {
    /*** 1.�ֶα�� ***/
    DataAccessUtils accessutil = new DataAccessUtils();
    // �����������������ϡ�����λ�ֶα��
    String parallel =
        DBHintConstantValue.getHintCode(SOTable.BUYLARGESS.getName());
    String updatesql =
        "update "
            + parallel
            + " so_buylargess set cbuymarid = pk_material,cbuyunitid = pk_measdoc where dr = 0 ";
    accessutil.update(updatesql);
    // �����ӱ�ȡ����ļ����ֶ�ֵ
    String parallel_b =
        DBHintConstantValue.getHintCode(SOTable.BUYLARGESS_B.getName());
    updatesql =
        "update "
            + parallel_b
            + " so_buylargess_b set so_buylargess_b.pk_group = "
            + "( select "
            + parallel
            + " so_buylargess.pk_group from so_buylargess where so_buylargess_b.pk_buylargess = so_buylargess.pk_buylargess and so_buylargess_b.dr = 0 ) "
            + "where so_buylargess_b.dr = 0 ";
    accessutil.update(updatesql);
    /*** 2.����ģ�� ***/
    String billtempsql =
        "delete from pub_billtemplet_b where pk_billtemplet in "
            + "(select h.pk_billtemplet from  pub_billtemplet h  where h.nodecode  = '40060102') and pos = 0 "
            + "and itemkey in ('pk_material','pk_material.name','pk_material.materialtype','pk_material.materialspec','pk_measdoc')";
    accessutil.update(billtempsql);

  }
}
