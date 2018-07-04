package nc.pubimpl.tbb.detail;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.self.ISaleOrderMaintain;
import nc.pubitf.so.tbb.SOTbbFieldConst;
import nc.pubitf.so.tbb.SOUninureStatus;
import nc.pubitf.so.tbb.detail.SODetailDataProvider;
import nc.pubitf.so.tbb.saleorder.SaleOrderDateMetaPath;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.tbb.TbbRegister;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.tb.obj.NtbParamVO;

public class OrderTbbDetailUtil {
  // TODO ��ӱ� 2012.03.14
  public SaleOrderViewVO[] getExecDataBatch(NtbParamVO ntbparamvos) {

    TbbRegister listregister = this.getSOTbbRegister();
    SODetailDataProvider provider = new SODetailDataProvider();
    String sql = "select distinct(so_saleorder_b.csaleorderbid) ";
    String where = provider.getExecDatas(listregister, ntbparamvos);
    sql = sql + where;
    sql = sql + " and so_saleorder_b.dr=0";
    ISaleOrderMaintain service =
        NCLocator.getInstance().lookup(ISaleOrderMaintain.class);
    try {
      SaleOrderViewVO[] vos = service.querySaleorderForTbb(sql);
      return vos;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private TbbRegister getSOTbbRegister() {

    /** ������۶���ע����Ϣ */
    TbbRegister m30register =
        new TbbRegister(SOBillType.Order.getCode(), SaleOrderHVO.class);
    // 1.������ݿ�ע����ͨ�ֶ�
    // ������֯
    m30register.addNormalPara(SOTbbFieldConst.CSETTLEORGID,
        "so_saleorder_b.csettleorgid");
    // ���������֯
    m30register.addNormalPara(SOTbbFieldConst.CSENDSTOCKORGID,
        "so_saleorder_b.csendstockorgid");
    // ����
    m30register.addNormalPara(SOTbbFieldConst.CMATERIALID,
        "so_saleorder_b.cmaterialid");
    // �ͻ�
    m30register.addNormalPara(SOTbbFieldConst.CUSTOMER, "ccustomerid");
    // ҵ��Ա
    m30register.addNormalPara(SOTbbFieldConst.CEMPLOYEEID, "cemployeeid");
    // ��Ʒ��
    m30register.addNormalPara(SOTbbFieldConst.CPRODLINEID,
        "so_saleorder_b.cprodlineid");
    // Ʒ��
    m30register.addNormalPara(SOTbbFieldConst.BRANDDOC,
        "so_saleorder_b.cmaterialid.pk_brand");
    // ������������
    m30register.addNormalPara(SOTbbFieldConst.CCHANNELTYPEID,
        SaleOrderHVO.CCHANNELTYPEID);

    // 2.������ݿ�ע�ἶ���ֶ�
    // ������֯
    m30register.addLevelPara(SOTbbFieldConst.SALEORG, "so_saleorder_b.pk_org");
    // ��������
    m30register
        .addLevelPara(SOTbbFieldConst.AREACLASS, "ccustomerid.pk_areacl");
    // �ͻ�����
    m30register.addLevelPara(SOTbbFieldConst.CUSTOMERCLASS,
        "ccustomerid.pk_custclass");
    // ����
    m30register.addLevelPara(SOTbbFieldConst.CDEPTID, "cdeptid");
    // ���ϻ�������
    m30register.addLevelPara(SOTbbFieldConst.INVCLASS,
        "so_saleorder_b.cmaterialvid.pk_marbasclass");
    // ��Ŀ
    m30register.addLevelPara(SOTbbFieldConst.CPROJECTID,
        "so_saleorder_b.cprojectid");

    // 3.�������Ԫ����·��ӳ��ӿ�
    m30register.setITbbDateMetaPath(new SaleOrderDateMetaPath());
    // 4.���ԭ�ұ���Ԫ����·��
    m30register.setOrigcurrtypeMetaPath(SaleOrderHVO.CORIGCURRENCYID);
    // 5.��������ֶ���Ϣ
    // m30register.addParaReduncyMap("dbilldate", "so_saleorder_b.dbilldate");
    // m30register.addParaReduncyMap("pk_org", "so_saleorder_b.pk_org");
    // 6.��Ӱ���δ��Ч�ӿ�
    m30register.setITbbUninureStatus(new SOUninureStatus());

    return m30register;
  }
}
