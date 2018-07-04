package nc.pubimpl.tbb.detail;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.pubitf.so.tbb.SODateMetaPath;
import nc.pubitf.so.tbb.SOTbbFieldConst;
import nc.pubitf.so.tbb.SOUninureStatus;
import nc.pubitf.so.tbb.detail.SODetailDataProvider;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.tbb.TbbRegister;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;
import nc.vo.tb.obj.NtbParamVO;

public class InvoiceTbbDetailUtil {
  // TODO ��ӱ� 2012.03.14
  public SaleInvoiceViewVO[] getExecDataBatch(NtbParamVO ntbparamvos) {

    TbbRegister listregister = this.getSOTbbRegister();
    SODetailDataProvider provider = new SODetailDataProvider();
    String sql = "select distinct(so_saleinvoice_b.csaleinvoicebid) ";
    String where = provider.getExecDatas(listregister, ntbparamvos);
    sql = sql + where;
    sql = sql + " and so_saleinvoice_b.dr=0";
    ISaleInvoiceMaintain service =
        NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);
    try {
      SaleInvoiceViewVO[] vos = service.queryViewvo(sql);
      return vos;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private TbbRegister getSOTbbRegister() {

    /** ������۷�Ʊע����Ϣ */
    TbbRegister m32register =
        new TbbRegister(SOBillType.Invoice.getCode(), SaleInvoiceHVO.class);
    // 1.������ݿ�ע����ͨ�ֶ�
    // ������֯
    m32register.addNormalPara(SOTbbFieldConst.CSETTLEORGID, "pk_org");
    // ���������֯
    m32register.addNormalPara(SOTbbFieldConst.CSENDSTOCKORGID,
        "csaleinvoicebid.csendstockorgid");
    // ����
    m32register.addNormalPara(SOTbbFieldConst.CMATERIALID,
        "csaleinvoicebid.cmaterialid");
    // �ͻ�
    m32register.addNormalPara(SOTbbFieldConst.CUSTOMER,
        "csaleinvoicebid.cordercustid");
    // ҵ��Ա
    m32register.addNormalPara(SOTbbFieldConst.CEMPLOYEEID,
        "csaleinvoicebid.cemployeeid");
    // ��Ʒ��
    m32register.addNormalPara(SOTbbFieldConst.CPRODLINEID,
        "csaleinvoicebid.cprodlineid");
    // Ʒ��
    m32register.addNormalPara(SOTbbFieldConst.BRANDDOC,
        "csaleinvoicebid.cmaterialid.pk_brand");
    // ����ȡ������--�ݲ�����

    // 2.������ݿ�ע�ἶ���ֶ�
    // ������֯
    m32register.addLevelPara(SOTbbFieldConst.SALEORG,
        "csaleinvoicebid.csaleorgid");
    // ��������
    m32register.addLevelPara(SOTbbFieldConst.AREACLASS,
        "csaleinvoicebid.cordercustid.pk_areacl");
    // �ͻ�����
    m32register.addLevelPara(SOTbbFieldConst.CUSTOMERCLASS,
        "csaleinvoicebid.cordercustid.pk_custclass");
    // ����
    m32register
        .addLevelPara(SOTbbFieldConst.CDEPTID, "csaleinvoicebid.cdeptid");
    // ���ϻ�������
    m32register.addLevelPara(SOTbbFieldConst.INVCLASS,
        "csaleinvoicebid.cmaterialvid.pk_marbasclass");
    // ��Ŀ
    m32register.addLevelPara(SOTbbFieldConst.CPROJECTID,
        "csaleinvoicebid.cprojectid");

    // 3.�������Ԫ����·��ӳ��ӿ�
    m32register.setITbbDateMetaPath(new SODateMetaPath());
    // 4.���ԭ�ұ���Ԫ����·��
    m32register.setOrigcurrtypeMetaPath(SaleInvoiceHVO.CORIGCURRENCYID);
    // 5.��������ֶ���Ϣ
    // m32register.addParaReduncyMap("dbilldate", "csaleinvoicebid.dbilldate");
    // m32register.addParaReduncyMap("pk_org", "csaleinvoicebid.pk_org");
    // 6.��Ӱ���δ��Ч�ӿ�
    m32register.setITbbUninureStatus(new SOUninureStatus());

    return m32register;
  }
}
