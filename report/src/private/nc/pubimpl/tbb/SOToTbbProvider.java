package nc.pubimpl.tbb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.tb.control.IAccessableBusiVO;
import nc.itf.tb.control.IBusiSysExecDataProvider;
import nc.pubitf.so.tbb.SODateMetaPath;
import nc.pubitf.so.tbb.SOTbbFieldConst;
import nc.pubitf.so.tbb.SOUninureStatus;
import nc.pubitf.so.tbb.saleinvoice.SaleInvoiceExtender;
import nc.pubitf.so.tbb.saleinvoice.SaleInvoiceSelectItem;
import nc.pubitf.so.tbb.saleorder.SaleOrderDateMetaPath;
import nc.pubitf.so.tbb.saleorder.SaleOrderExtender;
import nc.pubitf.so.tbb.saleorder.SaleOrderSelectItem;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.tbb.TbbExecDataProvider;
import nc.vo.scmpub.tbb.TbbRegister;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.tb.obj.NtbParamVO;

public class SOToTbbProvider implements IBusiSysExecDataProvider {

  // TODO ��ӱ� 2012.03.14
  @Override
  public void createBillType(NtbParamVO[] arg0) throws BusinessException {
    // ����ʵ��
  }

  @Override
  public int getCtlPoint(String arg0) throws RemoteException {
    return 0;
  }

  @Override
  public IAccessableBusiVO[] getCvtProvider(IAccessableBusiVO[] arg0)
      throws RemoteException {
    return null;
  }

  @Override
  public UFDouble[] getExecData(NtbParamVO arg0) throws BusinessException {
    return null;
  }

  @Override
  public UFDouble[][] getExecDataBatch(NtbParamVO[] ntbparamvos)
      throws BusinessException {

    List<TbbRegister> listregister = this.getSOTbbRegister();
    TbbExecDataProvider provider = new TbbExecDataProvider();
    UFDouble[][] result = provider.getExecDatas(listregister, ntbparamvos);
    return result;
  }

  private List<TbbRegister> getSOTbbRegister() {
    List<TbbRegister> listregister = new ArrayList<TbbRegister>();

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
    // 6.���ѡ���ֶ�ӳ��ӿ�
    m30register.setITbbSelectItem(new SaleOrderSelectItem());
    // 7.��Ӱ���δ��Ч�ӿ�
    m30register.setITbbUninureStatus(new SOUninureStatus());
    // 8.�����չ�ӿ�
    m30register.setFuncSqlExtender(new SaleOrderExtender());

    listregister.add(m30register);

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
    // 6.���ѡ���ֶ�ӳ��ӿ�
    m32register.setITbbSelectItem(new SaleInvoiceSelectItem());
    // 7.��Ӱ���δ��Ч�ӿ�
    m32register.setITbbUninureStatus(new SOUninureStatus());
    // 8.�����չ�ӿ�
    m32register.setFuncSqlExtender(new SaleInvoiceExtender());

    listregister.add(m32register);

    return listregister;
  }

  @Override
  public UFDouble[] getPointData(NtbParamVO arg0) throws BusinessException {
    return null;
  }

  @Override
  public UFDouble[][] getPointDataBatch(NtbParamVO[] arg0)
      throws BusinessException {
    return null;
  }

  @Override
  public UFDouble[] getReadyData(NtbParamVO arg0) throws BusinessException {
    return null;
  }

  @Override
  public UFDouble[][] getReadyDataBatch(NtbParamVO[] arg0)
      throws BusinessException {
    return null;
  }

  @Override
  public Map<NtbParamVO, Map<String, UFDouble[]>> getExecDataGroupBatch(
      String groupDocType, Map<NtbParamVO, List<String>> groupParaVOs,
      Map<String, String[]> childGroupDocs) throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<NtbParamVO, Map<String, UFDouble[]>> getReadyDataGroupBatch(
      String groupDocType, Map<NtbParamVO, List<String>> groupParaVOs,
      Map<String, String[]> childGroupDocs) throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

 
}
