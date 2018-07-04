package nc.itf.so.m30.ref.scmpub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.itf.scmf.coop.ICoopVOChange;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.uap.pf.IPFConfig;
import nc.pubitf.org.ISaleOrgPubService;
import nc.pubitf.pu.m21.so.m30.OrderCoopDataEntity;
import nc.vo.bd.cust.saleinfo.CustsaleVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmf.coop.entity.AbstractCoopDataEntity;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.trade.checkrule.VOChecker;

public class CoopVOChangeUtil {

  // �������۶����ͻ�id
  private Set<String> setCustid = new HashSet<String>();

  // �������۶������������֯id
  private Set<String> setFinancialOrg = new HashSet<String>();

  /*
   * ����Эͬ������ֵ
   */
  private SaleOrderVO[] coopVOChange(
      AbstractCoopDataEntity<OrderVO>[] srcCoopDatas,
      AbstractCoopDataEntity<SaleOrderVO>[] destCoopDatas)
          throws BusinessException {
    ICoopVOChange service = NCLocator.getInstance().lookup(ICoopVOChange.class);
    SaleOrderVO[] vos =
        (SaleOrderVO[]) service.coopVOChange(srcCoopDatas, destCoopDatas);
    return vos;
  }

  private void dealDate(SaleOrderVO[] desBills) {
    UFDate busidate = AppContext.getInstance().getBusiDate();
    UFDate enddate = busidate.asLocalEnd();
    for (SaleOrderVO bill : desBills) {
      SaleOrderBVO[] bvos = bill.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        UFDate sendDate = bvo.getDsenddate();
        UFDate receiveDate = bvo.getDreceivedate();
        if (null == sendDate || sendDate.before(enddate)) {
          bvo.setDsenddate(enddate);
        }
        if (null == receiveDate || receiveDate.before(enddate)) {
          bvo.setDreceivedate(enddate);
        }
        // ����Эͬ�ɹ���������ʱ�����Ȼ����pubapp�ķ�������Ʒ�ۿ���ֵ
        // �˴���Ҫ���п��ڸ�ֵ��
        if (null == bvo.getNitemdiscountrate()) {
          bvo.setNitemdiscountrate(SOConstant.ONEHUNDRED);
        }
      }
    }
  }

  /*
   * �������۶���Ĭ��ֵ
   */
  private void fillDefaultData(OrderVO[] srcBills, SaleOrderVO[] desBills) {
    for (int i = 0; i < srcBills.length; i++) {
      String vcooppohcode = srcBills[i].getHVO().getVbillcode();
      desBills[i].getParentVO().setVcooppohcode(vcooppohcode);
      desBills[i].getParentVO().setBpocooptomeflag(UFBoolean.TRUE);
      this.setBiztype(desBills[i]);
    }
    if (!VOChecker.isEmpty(desBills)) {
      // ��ʼ����������id
      this.initCashData(desBills);
      // ������Ա�Ͳ���
      this.setDeptAndPsn(desBills);
      // ���÷��������֯
      this.setSendStockOrg(desBills);
      // ������Ӧ�����ݰ汾
      this.itemVid(desBills);
      // ���������ں��ջ�����
      this.dealDate(desBills);
      // ������֯��λ��
      this.setCcurrencyorgid(desBills);
      // ������֯�汾
      this.setOrgVid(desBills);

    }
  }

  /*
   * ���ݿͻ�id��ȡ�ͻ�����ҳǩ�µĻ�����Ϣ
   */
  private Map<String, CustsaleVO> getCustsaleVO(String pk_org) {
    Map<String, CustsaleVO> mapcust = new HashMap<String, CustsaleVO>();

    if (this.setCustid.size() == 0) {
      return mapcust;
    }
    String[] custids = new String[this.setCustid.size()];
    this.setCustid.toArray(custids);
    // ��ÿͻ�����ҳǩ����Ļ�����Ϣ
    CustsaleVO[] custsalevos =
        CustomerPubService.getCustSaleVO(custids, pk_org, new String[] {
            CustsaleVO.PK_CUSTOMER, CustsaleVO.RESPDEPT, CustsaleVO.RESPPERSON
        });
    if (null == custsalevos) {
      return mapcust;
    }
    for (CustsaleVO custvo : custsalevos) {
      if (null == custvo) {
        continue;
      }
      mapcust.put(custvo.getPk_customer(), custvo);
    }
    return mapcust;
  }

  /*
   * ���ݲ���id��ȡ���Ű汾
   */
  private Map<String, String> getDeptVid(SaleOrderVO[] salevos) {
    // ���沿�� id
    List<String> listDeptId = new ArrayList<String>();
    for (SaleOrderVO salevo : salevos) {
      String deptid = salevo.getParentVO().getCdeptid();
      if (listDeptId.size() == 0 || !listDeptId.contains(deptid)) {
        listDeptId.add(deptid);
      }
    }
    if (listDeptId.size() == 0) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0205")/*@res "���۶������Ų���Ϊ�ա�"*/);
    }
    Map<String, String> mapDeptVId = new HashMap<String, String>();
    if (listDeptId.size() > 0) {
      String[] pk_depts = new String[listDeptId.size()];
      pk_depts = listDeptId.toArray(pk_depts);
      // ��ò���vid
      mapDeptVId = DeptPubService.getLastVIDSByDeptIDS(pk_depts);
    }
    return mapDeptVId;
  }

  private AbstractCoopDataEntity<SaleOrderVO>[] getDestVO(
      AbstractCoopDataEntity<OrderVO>[] srcCoopDatas,
      AbstractCoopDataEntity<SaleOrderVO>[] destCoopDatas) {
    Map<String, AbstractCoopDataEntity<SaleOrderVO>> tempMap =
        new HashMap<String, AbstractCoopDataEntity<SaleOrderVO>>();
    for (AbstractCoopDataEntity<SaleOrderVO> destCoopData : destCoopDatas) {
      SaleOrderVO vo = destCoopData.getBill();
      String srchid = vo.getChildrenVO()[0].getCsrcid();
      tempMap.put(srchid, destCoopData);
    }
    AbstractCoopDataEntity<SaleOrderVO>[] newDatas =
        new SaleOrderCoopDataEntity[destCoopDatas.length];
    int i = 0;
    for (AbstractCoopDataEntity<OrderVO> srcCoopData : srcCoopDatas) {
      OrderVO vo = srcCoopData.getBill();
      String hid = vo.getHVO().getPk_order();
      newDatas[i] = tempMap.get(hid);
      i++;
    }
    return newDatas;
  }

  /*
   * ��÷��������֯�汾
   */
  private Map<String, String> getSendStockVid(SaleOrderVO[] desBills) {
    // ���淢�������֯id
    List<String> stockList = new ArrayList<String>();
    for (SaleOrderVO bill : desBills) {
      SaleOrderBVO[] bvos = bill.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        if (stockList.size() == 0
            || !stockList.contains(bvo.getCsendstockorgid())) {
          stockList.add(bvo.getCsendstockorgid());
        }
      }
    }
    // ��÷��������֯�汾
    String[] stockIDS = new String[stockList.size()];
    stockIDS = stockList.toArray(stockIDS);
    Map<String, String> stockVidMap =
        OrgUnitPubService.getNewVIDSByOrgIDS(stockIDS);
    return stockVidMap;
  }

  /*
   * ��ý��������֯vid
   */
  private Map<String, String> getSetOrgVid() {
    if (this.setFinancialOrg.size() == 0) {
      return null;
    }
    String[] setorgs = new String[this.setFinancialOrg.size()];
    this.setFinancialOrg.toArray(setorgs);
    Map<String, String> setorgVidMap =
        OrgUnitPubService.getNewVIDSByOrgIDS(setorgs);
    return setorgVidMap;
  }
  /*
   * �����������vid
   * by wangshu6 
   */
  private Map<String, String> getFitCenterVidMap(String[] fitcenter) {
    if (fitcenter == null) {
      return new HashMap<String, String>();
    }
    Map<String, String> fitcentervid =
        OrgUnitPubService.getNewVIDSByOrgIDS(fitcenter);
    return fitcentervid;
  }

 
  // ��ʼ����������
  private void initCashData(SaleOrderVO[] desBills) {
    for (SaleOrderVO bill : desBills) {
      String custid = bill.getParentVO().getCcustomerid();
      // ����ͻ�id
      this.setCustid.add(custid);

      SaleOrderBVO[] bvos = bill.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        String financialOrgPK = bvo.getCsettleorgid();
        // ������������֯id
        if (!PubAppTool.isNull(financialOrgPK)) {
          this.setFinancialOrg.add(financialOrgPK);
        }
      }
    }
  }

  /*
   * ���ý��������֯��Ӧ����֯�汾 ���ò��Ű汾 ���÷��������֯�汾
   */
  private void itemVid(SaleOrderVO[] desBills) {
    Map<String, String> deptVidMap = this.getDeptVid(desBills);
    Map<String, String> setorgVidMap = this.getSetOrgVid();
    Map<String, String> sendStockVIdMap = this.getSendStockVid(desBills);
    for (SaleOrderVO bill : desBills) {
      String deptId = bill.getParentVO().getCdeptid();
      // ���ò��Ű汾
      if (null != deptVidMap && deptVidMap.size() > 0) {
        bill.getParentVO().setCdeptvid(deptVidMap.get(deptId));
      }
      SaleOrderBVO[] bvos = bill.getChildrenVO();
      String[] saleorgIDs = new String[bvos.length];
      String[] materialIDs = new String[bvos.length];
      String[] stockorgIDs = new String[bvos.length];
      for (int i = 0; i < bvos.length; i++) {
        
        SaleOrderBVO bvo = bvos[i];
        String sendstockId = bvo.getCsendstockorgid();
        if (!VOChecker.isEmpty(sendStockVIdMap)) {
          bvo.setCsendstockorgvid(sendStockVIdMap.get(sendstockId));
        }
        String settleorgid = bvo.getCsettleorgid();
        saleorgIDs[i] = bvo.getPk_org();
        materialIDs[i] = bvo.getCmaterialvid();
        stockorgIDs[i] = bvo.getCsendstockorgid();
        if (!VOChecker.isEmpty(setorgVidMap)) {
          String neworgid = setorgVidMap.get(settleorgid);
          // ���ý��������֯
          bvo.setCsettleorgvid(neworgid);
          // ����Ӧ����֯
          bvo.setCarorgid(settleorgid);
          bvo.setCarorgvid(neworgid);
          // �޸�: zhangby5 2014.8.2 �ѽ��������֯�����������ĺ����Ƶ��������۶�����������
          // ������������
          // bvo.setCprofitcenterid(settleorgid);
          // bvo.setCprofitcentervid(neworgid);
        }
      }
      //  ������������ begin  by wangshu6
      try {
        Map<String, String[]> cstmidAndOrgs =
            NCLocator
                .getInstance()
                .lookup(ISaleOrgPubService.class)
                .getDefaultFinanceOrgIDSAndReceiveOrgIDSAndLiaCenterIDSByCustomIDAndSaleOrgIDSAndMaterialIDS(
                    bill.getParentVO().getCcustomerid(), saleorgIDs,
                    materialIDs, stockorgIDs);
        if (cstmidAndOrgs != null && cstmidAndOrgs.size() > 0) {

          // ����������ȡ�����������鷽���� �����������
          String[] fitcenters = getFitCenter(cstmidAndOrgs);

          // ���û����������vid����,�����������vids
          Map<String, String> fitcentervids = getFitCenterVidMap(fitcenters);

          for (int i = 0; i < bvos.length; i++) {
            SaleOrderBVO bvo = bvos[i];
            String fitcenter = cstmidAndOrgs.get(bvo.getCmaterialvid())[2];
            if (fitcenter == null) {
              continue;

            }
            // �����������vid
            String fitcentervid = fitcentervids.get(fitcenter);
            // ������������ �� ��������vid
            bvo.setCprofitcenterid(fitcenter);
            bvo.setCprofitcentervid(fitcentervid);
          }

        }
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      // end
    }
  }
  
  /*
   * �������������������
   * by wangshu6 
   */
  private String[] getFitCenter(Map<String, String[]> cstmidAndOrgs){
    List<String> fitcenterlist =  new ArrayList<String>();
    for (Map.Entry<String, String[]> entrysetvo : cstmidAndOrgs.entrySet()) {
      String fitcenterarray = entrysetvo.getValue()[2];
      //�Д��Ƿ���ڣ�
      if(fitcenterlist.contains(fitcenterarray)){
        continue;
      }
      //�]����������
      fitcenterlist.add(fitcenterarray);
   }
      //ת��������
    String[] fitcenters = fitcenterlist.toArray(new String[0]);
     return fitcenters;
  }
 
  @SuppressWarnings({
    "rawtypes", "unchecked"
  })
  public SaleOrderVO[] processVO(OrderVO[] srcBills, SaleOrderVO[] destBills)
      throws Exception {
    // 1.����Эͬ����
    AbstractCoopDataEntity[] srcCoopDatas =
        AbstractCoopDataEntity.createCoopDatas(OrderCoopDataEntity.class,
            srcBills);
    AbstractCoopDataEntity[] destCoopDatas =
        AbstractCoopDataEntity.createCoopDatas(SaleOrderCoopDataEntity.class,
            destBills);
    AbstractCoopDataEntity[] datas =
        this.getDestVO(srcCoopDatas, destCoopDatas);
    // 2.����Эͬ������ֵ
    SaleOrderVO[] vos = this.coopVOChange(srcCoopDatas, datas);
    // 3.���ݿͻ�������ֵ
    this.fillDefaultData(srcBills, vos);
    return vos;
  }

  private void setBiztype(SaleOrderVO vo) {
    if (VOChecker.isEmpty(vo)) {
      return;
    }
    // ƥ��ҵ������
    String vtrantype = vo.getParentVO().getVtrantypecode();
    String csaleorgid = vo.getParentVO().getPk_org();
    String userid = InvocationInfoProxy.getInstance().getUserId();
    if (vtrantype != null && vtrantype.trim().length() > 0
        && csaleorgid != null && csaleorgid.trim().length() > 0) {
      try {
        String cbiztypeid =
            NCLocator
            .getInstance()
            .lookup(IPFConfig.class)
            .retBusitypeCanStart(SOBillType.Order.getCode(), vtrantype,
                csaleorgid, userid);
        vo.getParentVO().setCbiztypeid(cbiztypeid);
      }
      catch (BusinessException e1) {
        ExceptionUtils.wrappBusinessException(e1.getMessage());
      }
    }
  }

  private void setCcurrencyorgid(SaleOrderVO[] desBills) {
    for (SaleOrderVO bill : desBills) {
      SaleOrderBVO[] bvos = bill.getChildrenVO();
      String csettleorgid = bvos[0].getCsettleorgid();

      String ccur = OrgUnitPubService.queryOrgCurrByPk(csettleorgid);
      for (SaleOrderBVO bvo : bvos) {
        bvo.setCcurrencyid(ccur);
      }

    }
  }

  /*
   * ���ݿͻ��������۶�������Ĳ��ź�ҵ��Ա
   */
  private void setDeptAndPsn(SaleOrderVO[] desBills) {
    String pk_org = desBills[0].getParentVO().getPk_org();
    Map<String, CustsaleVO> mapCustSale = this.getCustsaleVO(pk_org);
    if (mapCustSale.size() == 0) {
      return;
    }
    for (SaleOrderVO bill : desBills) {
      String custid = bill.getParentVO().getCcustomerid();
      CustsaleVO custvo = mapCustSale.get(custid);
      if (null == custvo) {
        continue;
      }
      // �ͻ���������Ĳ���Ϊ�յĻ�����Эͬ���������õĲ���
      String respdept = custvo.getRespdept();
      if (!PubAppTool.isNull(respdept)) {
        bill.getParentVO().setCdeptid(respdept);
      }
      // �ͻ������������ԱΪ�յĻ�����Эͬ���������õ���Ա
      String respperson = custvo.getRespperson();
      if (!PubAppTool.isNull(respperson)) {
        bill.getParentVO().setCemployeeid(respperson);
      }
    }

  }

  /**
   * ����������֯�İ汾
   * 
   * @param desBills
   */
  private void setOrgVid(SaleOrderVO[] desBills) {
    Set<String> orgSet = new HashSet<String>();
    for (SaleOrderVO vo : desBills) {
      orgSet.add(vo.getParentVO().getPk_org());
    }
    String[] pk_orgs = new String[orgSet.size()];
    orgSet.toArray(pk_orgs);

    Map<String, String> map = OrgUnitPubService.getNewVIDSByOrgIDS(pk_orgs);
    for (SaleOrderVO vo : desBills) {
      String pk_org = vo.getParentVO().getPk_org();
      vo.getParentVO().setPk_org_v(map.get(pk_org));
    }

  }

  /*
   * ���÷��������֯
   */
  private void setSendStockOrg(SaleOrderVO[] desBills) {
    for (SaleOrderVO billvo : desBills) {
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(billvo);
      BodyValueRowRule bodyrule = new BodyValueRowRule(keyValue);
      SaleOrgRelationRule orgrelarule = new SaleOrgRelationRule(keyValue);
      // ���������֯
      int[] sendstockrows =
          bodyrule.getValueNullRows(SaleOrderBVO.CSENDSTOCKORGVID);
      orgrelarule.setSendStockOrg(sendstockrows);
      // ������֯
      int[] traffrows = bodyrule.getValueNullRows(SaleOrderBVO.CTRAFFICORGVID);
      orgrelarule.setTrafficOrg(traffrows);
    }
  }
}
