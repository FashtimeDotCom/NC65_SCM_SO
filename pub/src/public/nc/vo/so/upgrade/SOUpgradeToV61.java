package nc.vo.so.upgrade;

import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.DBHintConstantValue;
import nc.vo.scmpub.vatupdate.VatFieldEnum;
import nc.vo.scmpub.vatupdate.VatUpdateProcess;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.SOTable;

import nc.impl.pubapp.pattern.database.DataAccessUtils;

import nc.scmmm.upgrade.scmpub.AbstractUpgradeToV61;

/**
 * ���۹���V61������
 * 
 * @since 6.1
 * @version 2012-11-29 09:50:45
 * @author ��ӱ�
 */
public class SOUpgradeToV61 extends AbstractUpgradeToV61 {

  @Override
  protected void doAfterUpdateDataFrom60To61() throws BusinessException {

    DataAccessUtils accessutil = new DataAccessUtils();

    /*** ����V60->V61�����ֶ� begin */
    // ���������������š������������°汾�������ƻ�Ա�ֶα��
    String parallel = DBHintConstantValue.getHintCode("so_delivery");

    String deliverysql =
        "update "
            + parallel
            + "so_delivery set csenddeptvid  = cdeptvid ,csenddeptid  = cdeptid ,csendemployeeid  = cemployeeid  where dr = 0";
    accessutil.update(deliverysql);

    // ���۷�Ʊ���㵥�ӱ��б�ע�ֶα��
    parallel = DBHintConstantValue.getHintCode("so_squareinv_b");
    String squareinvsql =
        " update " + parallel
            + "so_squareinv_b set vrownote = frownote  where dr = 0";
    accessutil.update(squareinvsql);

    // ���۳�����㵥�ӱ��б�ע�ֶα��
    parallel = DBHintConstantValue.getHintCode("so_squareout_b");
    String squareoutsql =
        " update " + parallel
            + "so_squareout_b set vrownote = frownote  where dr = 0";
    accessutil.update(squareoutsql);

    // ;����㵥�ӱ��б�ע�ֶα��
    parallel = DBHintConstantValue.getHintCode("so_squarewas_b");
    String squarewassql =
        " update " + parallel
            + "so_squarewas_b set vrownote = frownote  where dr = 0";
    accessutil.update(squarewassql);

    /*** ����V60->V61�����ֶ� end */

    /*** �������ҡ��������͡�����ó�ס���˰�������� begin */
    // ����VAT
    VatUpdateProcess vatupdate = new VatUpdateProcess();
    // ���۵��ӱ��ջ�����/������������/��������˰��/�������������͡�����ó�ס���˰���
    VatFieldEnum[] m4331vatfields =
        new VatFieldEnum[] {
          VatFieldEnum.CRECECOUNTRYID, VatFieldEnum.CSENDCOUNTRYID,
          VatFieldEnum.CTAXCOUNTRYID, VatFieldEnum.FBUYSELLFLAG,
          VatFieldEnum.BTRIATRADEFLAG, VatFieldEnum.FTAXTYPEFLAG,
        };
    vatupdate.processVatUpdate(SOTable.SALEQUOTATION_B.getName(),
        m4331vatfields);

    // Ԥ�����ӱ����۶����ӱ��������ӱ��������ʼ��
    // �ջ�����/������������/��������˰��/�������������͡�����ó�ס���˰��𡢼�˰���
    VatFieldEnum[] pubvatfields =
        new VatFieldEnum[] {
          VatFieldEnum.CRECECOUNTRYID, VatFieldEnum.CSENDCOUNTRYID,
          VatFieldEnum.CTAXCOUNTRYID, VatFieldEnum.FBUYSELLFLAG,
          VatFieldEnum.BTRIATRADEFLAG, VatFieldEnum.FTAXTYPEFLAG,
          VatFieldEnum.NCALTAXMNY
        };
    vatupdate.processVatUpdate(SOTable.PREORDER_B.getName(), pubvatfields);
    vatupdate.processVatUpdate(SOTable.SALEORDER_B.getName(), pubvatfields);
    vatupdate.processVatUpdate(SOTable.DELIVERY_B.getName(), pubvatfields);
    vatupdate.processVatUpdate(SOTable.DELIVERY_CHECK.getName(), pubvatfields);

    // ���۷�Ʊ������Ʊ�����嵥����������㵥����;����㵥����:
    // �ջ�����/������������/��������˰��/�������������͡�����ó��
    VatFieldEnum[] h_vatfields =
        new VatFieldEnum[] {
          VatFieldEnum.CRECECOUNTRYID, VatFieldEnum.CSENDCOUNTRYID,
          VatFieldEnum.CTAXCOUNTRYID, VatFieldEnum.FBUYSELLFLAG,
          VatFieldEnum.BTRIATRADEFLAG
        };
    vatupdate.processVatUpdate(SOTable.SALEINVOICE.getName(), h_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREINV.getName(), h_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREOUT.getName(), h_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREWAS.getName(), h_vatfields);

    // ���۷�Ʊ�ӱ���Ʊ���㵥�ӱ���Ʊ������ϸ��������㵥�ӱ����������ϸ��;����㵥�ӱ�;�������ϸ��
    // ˰�롢��˰���
    VatFieldEnum[] b_vatfields = new VatFieldEnum[] {
      VatFieldEnum.FTAXTYPEFLAG, VatFieldEnum.NCALTAXMNY
    };
    vatupdate.processVatUpdate(SOTable.SALEINVOICE_B.getName(), b_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREINV_B.getName(), b_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREINV_D.getName(), b_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREOUT_B.getName(), b_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREOUT_D.getName(), b_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREWAS_B.getName(), b_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREWAS_D.getName(), b_vatfields);
    /*** �������ҡ��������͡�����ó�ס���˰�������� end */

    /*** ����˰������ begin */
    // ���۵��ӱ�
    vatupdate.processTaxCodeUpdate(SOTable.SALEQUOTATION_B.getName(),
        SOItemKey.CTAXCODEID, "pk_material_v");
    // Ԥ�����ӱ�
    vatupdate.processTaxCodeUpdate(SOTable.PREORDER_B.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);
    // ���۶����ӱ�
    vatupdate.processTaxCodeUpdate(SOTable.SALEORDER_B.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);
    // �������ӱ�
    vatupdate.processTaxCodeUpdate(SOTable.DELIVERY_B.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);
    // // �������ʼ��
    vatupdate.processTaxCodeUpdate(SOTable.DELIVERY_CHECK.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);
    // ���۷�Ʊ�ӱ�
    vatupdate.processTaxCodeUpdate(SOTable.SALEINVOICE_B.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);
    // ��Ʊ���㵥�ӱ�
    vatupdate.processTaxCodeUpdate(SOTable.SQUAREINV_B.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);
    // ������㵥�ӱ�
    vatupdate.processTaxCodeUpdate(SOTable.SQUAREOUT_B.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);
    // ;����㵥�ӱ�
    vatupdate.processTaxCodeUpdate(SOTable.SQUAREWAS_B.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);

    // ������ϸ����Ʊ������ϸ�����������ϸ��;�������ϸ��
    String parallel_d = DBHintConstantValue.getHintCode("so_squareinv_d");
    String parallel_b = DBHintConstantValue.getHintCode("so_squareinv_b");
    String squareinvtaxsql =
        "update "
            + parallel_d
            + "so_squareinv_d set so_squareinv_d.ctaxcodeid = "
            + "( select "
            + parallel_b
            + "so_squareinv_b.ctaxcodeid from so_squareinv_b where so_squareinv_b.csalesquarebid = so_squareinv_d.csalesquarebid and so_squareinv_b.dr = 0 ) where so_squareinv_d.dr = 0 ";
    accessutil.update(squareinvtaxsql);

    parallel_d = DBHintConstantValue.getHintCode("so_squareout_d");
    parallel_b = DBHintConstantValue.getHintCode("so_squareout_b");
    String squareouttaxsql =
        "update "
            + parallel_d
            + "so_squareout_d set so_squareout_d.ctaxcodeid = "
            + "( select "
            + parallel_b
            + "so_squareout_b.ctaxcodeid from so_squareout_b where so_squareout_b.csalesquarebid = so_squareout_d.csalesquarebid and so_squareout_b.dr = 0 ) where so_squareout_d.dr = 0 ";
    accessutil.update(squareouttaxsql);

    parallel_d = DBHintConstantValue.getHintCode("so_squarewas_d");
    parallel_b = DBHintConstantValue.getHintCode("so_squarewas_b");
    String squarewastaxsql =
        "update "
            + parallel_d
            + "so_squarewas_d set so_squarewas_d.ctaxcodeid = "
            + "( select "
            + parallel_b
            + "so_squarewas_b.ctaxcodeid from so_squarewas_b where so_squarewas_b.csalesquarebid = so_squarewas_d.csalesquarebid and so_squarewas_b.dr = 0 ) where so_squarewas_d.dr = 0 ";
    accessutil.update(squarewastaxsql);
    /*** ����˰������ end */

    /*** ��������ģ�� begin */
    // ɾ��ԭ��˰��
    String sotempsql =
        "delete from pub_billtemplet_b where pk_billtemplet in "
            + "(select h.pk_billtemplet from  pub_billtemplet h  where h.nodecode  like '4006%') and itemkey = 'norigtax'";
    accessutil.update(sotempsql);

    // ���µ���ģ�����ϱ༭������
    sotempsql =
        "update pub_billtemplet_b set metadatarelation =replace(metadatarelation,',ntaxrate=pk_taxitems.taxrate','') where "
            + "metadatarelation like '%pk_taxitems.taxrate%' and pk_billtemplet in ( select h.pk_billtemplet from pub_billtemplet h where h.nodecode like '4006%') ";
    accessutil.update(sotempsql);

    // ɾ���������������š�ҵ��Ա
    String deliverttempsql =
        "delete from pub_billtemplet_b where pk_billtemplet in "
            + "(select h.pk_billtemplet from  pub_billtemplet h  where h.nodecode  = '40060402') and metadataproperty in ('so.delivery.cdeptvid','so.delivery.cdeptid','so.delivery.cemployeeid')";
    accessutil.update(deliverttempsql);
    /*** ��������ģ�� end */

    /*** �������ݽ��� begin */
    String sovochgsql =
        "delete from pub_vochange_b where pk_vochange in "
            + "( select h.pk_vochange from pub_vochange h where h.dest_billtype in('4310','30','4331','32','434C','4332','4353') ) and dest_attr like '%norigtax'";
    accessutil.update(sovochgsql);

    sovochgsql =
        "delete from pub_vochange_b where pk_vochange in "
            + "(select h.pk_vochange from pub_vochange h where h.src_billtype = '4H' and h.dest_billtype = '30') and dest_attr = 'so_saleorder_b.ntaxrate'";
    accessutil.update(sovochgsql);
    /*** �������ݽ��� end */

    /*** ����Ԥ�ò��� begin */
    String parachgsql =
        "delete from pub_sysinittemp where initcode in('SO23','SO24','SO25') ";
    accessutil.update(parachgsql);

    parachgsql =
        "delete from pub_sysinit where initcode in('SO23','SO24','SO25') ";
    accessutil.update(parachgsql);
    /*** ����Ԥ�ò��� end */

    /*** ��������������״̬ begin **/
    // ���۶�������V61���������״̬fpfstatusflag�ֶΣ�����ʱ��ֵ����Ϊ��
    // ����������״̬Ϊ����ʱ��������״̬ΪIPfRetCheckInfo.NOSTATE = -1
    // ����������״̬Ϊ����δͨ��ʱ��������״̬ΪIPfRetCheckInfo.NOPASS = 0
    // ����������״̬Ϊ������ʱ��������״̬ΪIPfRetCheckInfo.GOINGON = 2
    // ����������״̬Ϊ����/����/�ر�ʱ��������״̬ΪIPfRetCheckInfo.PASSING = 1
    String parallel_table = DBHintConstantValue.getHintCode("so_saleorder");
    String pfstatesql =
        "update "
            + parallel_table
            + " so_saleorder set fpfstatusflag = case when fstatusflag = 1 then -1 when fstatusflag = 8 then 0 when fstatusflag = 7 then 2 else 1 end  where dr = 0 ";
    accessutil.update(pfstatesql);
    /*** ��������������״̬ end **/
  }
}
