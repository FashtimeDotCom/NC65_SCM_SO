package nc.impl.so.m30.api;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyInfo;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.pubitf.org.IDeptPubService;
import nc.pubitf.so.m30.api.ISaleOrderExternal;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.br.AttributeCoordinate;
import nc.vo.scmpub.br.bill.BillNullCheck;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderExternalBVO;
import nc.vo.so.m30.entity.SaleOrderExternalHVO;
import nc.vo.so.m30.entity.SaleOrderExternalVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCustMaterialInfoRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;

import org.apache.commons.lang.ArrayUtils;

/**
 * ���۶������빫���ӿ�ʵ����
 * 
 * @since 6.3
 * @time 2013-5-25 ����02:05:04
 * @author dongli2
 * 
 */
public class SaleOrderExternalImpl implements ISaleOrderExternal {

  @Override
  public SaleOrderVO[] insertInterfaceVOForSaleOrder(SaleOrderExternalVO[] vos)
      throws BusinessException {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    // VOת��
    SaleOrderVO[] saleOrderVOS = new SaleOrderVO[vos.length];
    saleOrderVOS = chgToSaleOrderVO(vos);

    insertBusinessProcess(saleOrderVOS);

    // ���ýű�
    return (SaleOrderVO[]) PfServiceScmUtil.processBatch("WRITE",
        SOBillType.Order.getCode(), saleOrderVOS, null, null);

  }

  @Override
  public SaleOrderVO[] insertSaleOrderVOFor30(SaleOrderVO[] vos)
      throws BusinessException {

    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    insertBusinessProcess(vos);

    // ���ýű�
    return (SaleOrderVO[]) PfServiceScmUtil.processBatch("WRITE",
        SOBillType.Order.getCode(), vos, null, null);

  }

  @Override
  public SaleOrderVO[] insertSaleOrderVOForSaleOrder(SaleOrderVO[] vos)
      throws BusinessException {

    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    // ���ýű�
    return (SaleOrderVO[]) PfServiceScmUtil.processBatch("WRITE",
        SOBillType.Order.getCode(), vos, null, null);

  }

  // ID
  @Override
  public void deleteSVOForSaleOrder(String[] pks) throws BusinessException {

    if (!ArrayUtils.isEmpty(pks)) {

      // ��PK��SaleOrderVO
      SaleOrderVO[] saleOrderVOS = new SaleOrderVO[0];
      BillQuery<SaleOrderVO> query =
          new BillQuery<SaleOrderVO>(SaleOrderVO.class);
      try {
        saleOrderVOS = query.query(pks);
      }
      catch (Exception ex) {
        ExceptionUtils.marsh(ex);
      }

      // ���ýű�
      PfServiceScmUtil.processBatch("DELETE", SOBillType.Order.getCode(),
          saleOrderVOS, null, null);
    }
  }

  /**
   * VOת��
   * 
   * @param SaleOrderExternalVO[]
   * @return SaleOrderVO[]
   */
  private SaleOrderVO[] chgToSaleOrderVO(SaleOrderExternalVO[] originBills) {
    SaleOrderVO[] saleOrderVOS = new SaleOrderVO[originBills.length];
    int i = 0;
    for (SaleOrderExternalVO originBill : originBills) {
      // ��ͷ
      String[] headMetas = originBill.getParentVO().getAttributeNames();

      SaleOrderHVO headVO = new SaleOrderHVO();
      saleOrderVOS[i] = new SaleOrderVO();
      SaleOrderExternalHVO originHead = originBill.getParentVO();
      for (String headMeta : headMetas) {
        headVO.setAttributeValue(headMeta,
            originHead.getAttributeValue(headMeta));
      }
      saleOrderVOS[i].setParentVO(headVO);
      // ����
      SaleOrderExternalBVO[] originBodys = originBill.getChildrenVO();
      SaleOrderBVO[] bodyVOS = new SaleOrderBVO[originBodys.length];
      String[] bodyMetas = originBodys[0].getAttributeNames();
      for (int j = 0; j < originBodys.length; j++) {
        bodyVOS[j] = new SaleOrderBVO();
        for (String bodyMeta : bodyMetas) {
          bodyVOS[j].setAttributeValue(bodyMeta,
              originBodys[j].getAttributeValue(bodyMeta));
        }
      }
      saleOrderVOS[i].setChildrenVO(bodyVOS);
      i++;
    }
    return saleOrderVOS;
  }

  /**
   * ����VO���顢��䡢����
   * 
   * @param vos
   * @throws BusinessException
   */
  private void insertBusinessProcess(SaleOrderVO[] vos)
      throws BusinessException {

    // VO��������
    checkExternalInsertVO(vos);
    // VO���������
    fillupVOData(vos);
    // �ɼ�����
    calculateNumAndMny(vos);
  }

  /**
   * VO��������
   * 
   * @param vos
   */
  private void checkExternalInsertVO(SaleOrderVO[] vos) {
    checkImportHVOField(vos);
    checkImportBVOField(vos);
  }

  /**
   * VO��ȫ
   * 
   * @param saleOrderVOS
   * 
   * @throws BusinessException
   */
  private void fillupVOData(SaleOrderVO[] saleOrderVOS)
      throws BusinessException {
    // ��ͷ��ȫ
    fillUpHVOData(saleOrderVOS);
    // ���岹ȫ
    fillUpBVOData(saleOrderVOS);
  }

  /**
   * ��ͷ��������
   * ɢ���ݲ���У��
   * 
   * @param head
   */
  private void checkImportHVOField(SaleOrderVO[] vos) {

    BillNullCheck<SaleOrderVO> nullCheck = new BillNullCheck<SaleOrderVO>();
    String[] headnullkeys =
        new String[] {
          SaleOrderHVO.PK_ORG, SaleOrderHVO.CTRANTYPEID,
          SaleOrderHVO.DBILLDATE, SaleOrderHVO.CCUSTOMERID,
          SaleOrderHVO.CDEPTVID, SaleOrderHVO.CORIGCURRENCYID
        };
    for (String field : headnullkeys) {
      nullCheck.add(new AttributeCoordinate(SaleOrderHVO.class, field));
    }
    nullCheck.check(vos);
  }

  /**
   * �����������
   * 
   * @param vos
   */
  private void checkImportBVOField(SaleOrderVO[] vos) {

    BillNullCheck<SaleOrderVO> nullCheck = new BillNullCheck<SaleOrderVO>();
    String[] bodynullkeys =
        new String[] {
          SaleOrderBVO.CMATERIALVID, SaleOrderBVO.CSENDSTOCKORGVID,
          SaleOrderBVO.CSETTLEORGVID, SaleOrderBVO.NASTNUM,
          SaleOrderBVO.CASTUNITID, SaleOrderBVO.NORIGTAXMNY
        };
    for (String field : bodynullkeys) {
      nullCheck.add(new AttributeCoordinate(SaleOrderBVO.class, field));
    }
    nullCheck.check(vos);
  }

  /**
   * ����ͷ����
   * 
   * @param saleOrderVOS
   * @throws BusinessException
   */
  private void fillUpHVOData(SaleOrderVO[] saleOrderVOS)
      throws BusinessException {
    Set<String> vorgSet = new HashSet<String>();
    Set<String> vtranTypeCodeSet = new HashSet<String>();
    Set<String> vdeptIDSet = new HashSet<String>();

    // ����ȥ��
    for (SaleOrderVO vo : saleOrderVOS) {
      vorgSet.add(vo.getParentVO().getPk_org());
      vtranTypeCodeSet.add(vo.getParentVO().getCtrantypeid());
      vdeptIDSet.add(vo.getParentVO().getCdeptvid());
    }

    // ��ȡ����������Map
    Map<String, String> vorgMap =
        OrgUnitPubService.getNewVIDSByOrgIDS(vorgSet.toArray(new String[vorgSet
            .size()]));
    Map<String, String> vtranTypeCodeMap =
        PfServiceScmUtil.getTrantypecodeByids(vtranTypeCodeSet
            .toArray(new String[vtranTypeCodeSet.size()]));

    // SCMPUBû��װgetOIDSByDeptVIDS������ô��--2013.5.28 dongli
    IDeptPubService service =
        NCLocator.getInstance().lookup(IDeptPubService.class);
    Map<String, String> vdeptIDMap =
        service.getOIDSByDeptVIDS(vdeptIDSet.toArray(new String[vdeptIDSet
            .size()]));

    // �����ͷ������ֶ�
    for (SaleOrderVO vo : saleOrderVOS) {
      SaleOrderHVO head = vo.getParentVO();
      head.setPk_org_v(vorgMap.get(head.getPk_org()));// pk_org_v
      head.setVtrantypecode(vtranTypeCodeMap.get(head.getCtrantypeid()));// vtrantypecode
      head.setPk_group(AppContext.getInstance().getPkGroup());// pk_group
      head.setCdeptid(vdeptIDMap.get(head.getCdeptvid())); // Cdeptid
      head.setNdiscountrate(SOConstant.ONEHUNDRED);// �����ۿ�Ĭ��100

      // ��ȫ��Ʊ�ͻ�
      if (PubAppTool.isNull(head.getCinvoicecustid())) {
        head.setCinvoicecustid(head.getCcustomerid());
      }

      if (null == head.getFstatusflag()) {
        head.setFstatusflag(BillStatus.FREE.getIntegerValue());
      }

    }
    // ҵ������
    PfServiceScmUtil.setBusiType(saleOrderVOS, SOBillType.Order.getCode());
  }

  /**
   * ����������
   * 
   * @param saleOrderVOS
   */
  private void fillUpBVOData(SaleOrderVO[] saleOrderVOS) {

    for (SaleOrderVO vo : saleOrderVOS) {
      // ������ȫ����
      preFillBodys(vo);

      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(vo);
      SaleOrgRelationRule orgRule = new SaleOrgRelationRule(keyValue);
      BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
      int[] rows = countutil.getMarNotNullRows();

      // ������֯��ȫ
      int[] rowsTrafficOrg =
          countutil.getValueNullRows(SaleOrderBVO.CTRAFFICORGVID);
      orgRule.setTrafficOrg(rowsTrafficOrg);

      // ������Ϣ(��Ϊ����)
      SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
      int[] rowsReceiveCountry =
          countutil.getValueNullRows(SaleOrderBVO.CRECECOUNTRYID);
      countryrule.setReceiveCountry(rowsReceiveCountry);
      int[] rowsTaxCountry =
          countutil.getValueNullRows(SaleOrderBVO.CTAXCOUNTRYID);
      countryrule.setTaxCountry(rowsTaxCountry);
      int[] rowsSendCountry =
          countutil.getValueNullRows(SaleOrderBVO.CSENDCOUNTRYID);
      countryrule.setSendCountry(rowsSendCountry);

      // ���ù������ͺ�����ó��
      SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
      buyflgrule.setBuysellAndTriaFlag(rows);

      // ���ÿͻ�������
      SOCustMaterialInfoRule socustmar = new SOCustMaterialInfoRule(keyValue);
      socustmar.setCustMaterial(rows);

      // ѯ˰
      SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
      taxInfo.setTaxInfoByBodyPos(rows);

      // ���û�����
      SOUnitChangeRateRule unitrate = new SOUnitChangeRateRule(keyValue);
      //�����Ż����������� add by zhangby5
      unitrate.calcAstAndQtChangeRate(rows);
    }
  }

  /**
   * ������ȫ����
   * 
   * @param vo
   */
  private void preFillBodys(SaleOrderVO vo) {

    // ׼��VO
    SaleOrderHVO head = vo.getParentVO();
    SaleOrderBVO[] bodys = vo.getChildrenVO();

    Set<String> materialVIDSet = new HashSet<String>();
    Set<String> orgsVIDSet = new HashSet<String>();
    for (SaleOrderBVO bvo : bodys) {
      materialVIDSet.add(bvo.getCmaterialvid());
      orgsVIDSet.add(bvo.getCsendstockorgvid());
      orgsVIDSet.add(bvo.getCsettleorgvid());
    }

    // ������Ϣ
    String[] materialVIDS =
        materialVIDSet.toArray(new String[materialVIDSet.size()]);
    String[] conditions =
        new String[] {
          MaterialVO.PK_SOURCE, MaterialVO.PK_MEASDOC, MaterialVO.PK_PRODLINE,
          MaterialVO.FEE, MaterialVO.DISCOUNTFLAG
        };
    Map<String, MaterialVO> mapMaterialVOS =
        MaterialPubService.queryMaterialBaseInfo(materialVIDS, conditions);

    // ��֯��Ϣ
    String[] orgsVIDS = orgsVIDSet.toArray(new String[orgsVIDSet.size()]);
    Map<String, String> orgsOIDS = OrgUnitPubService.getOrgIDSByVIDS(orgsVIDS);

    for (SaleOrderBVO bvo : bodys) {

      // ��ȫ��������������
      if (null == bvo.getDsenddate()) {
        bvo.setDsenddate(head.getDbilldate());
      }
      if (null == bvo.getDreceivedate()) {
        bvo.setDsenddate(head.getDbilldate());
      }

      // ��ȫ���š�������֯
      if (PubAppTool.isNull(bvo.getPk_org())) {
        bvo.setPk_org(head.getPk_org());
      }
      if (PubAppTool.isNull(bvo.getPk_group())) {
        bvo.setPk_group(head.getPk_group());
      }

      // ��ȫӦ����֯
      if (PubAppTool.isNull(bvo.getCarorgvid())) {
        bvo.setCarorgvid(bvo.getCsettleorgvid());
      }
      String carorgvid = OrgUnitPubService.getOrgIDByVID(bvo.getCarorgvid());
      bvo.setCarorgid(carorgvid);

      // �ջ��ͻ���ȫ
      if (PubAppTool.isNull(bvo.getCreceivecustid())) {
        bvo.setCreceivecustid(head.getCcustomerid());
      }

      // �����ۿ�
      UFDouble ndiscountrate = SOConstant.ONEHUNDRED;
      if (null == bvo.getNdiscountrate()) {
        bvo.setNdiscountrate(ndiscountrate);
      }

      // ��Ʒ�ۿ�
      UFDouble itemdisrate = bvo.getNitemdiscountrate();
      if (null == itemdisrate) {
        bvo.setNitemdiscountrate(SOConstant.ONEHUNDRED);
      }

      // ��λ��
      String ccurrencyid =
          CurrencyInfo.getDefaultCurrtypeByOrgID(bvo.getPk_org());
      if (PubAppTool.isNull(bvo.getCcurrencyid())) {
        bvo.setCcurrencyid(ccurrencyid);
      }

      // ����OID
      String cmaterialid =
          mapMaterialVOS.get(bvo.getCmaterialvid()).getPk_source();
      if (PubAppTool.isNull(bvo.getCmaterialid())) {
        bvo.setCmaterialid(cmaterialid);
      }

      // ���������֯���°汾
      String csendstockorgid = orgsOIDS.get(bvo.getCsendstockorgvid());
      bvo.setCsendstockorgid(csendstockorgid);

      // ���������֯���°汾
      String csettleorgid = orgsOIDS.get(bvo.getCsettleorgvid());
      bvo.setCsettleorgid(csettleorgid);

      // ����VIDȡ��������λ
      String cunitid =
          mapMaterialVOS.get(bvo.getCmaterialvid()).getPk_measdoc();
      if (PubAppTool.isNull(bvo.getCunitid())) {
        bvo.setCunitid(cunitid);
      }

      // ��Ʒ�ߡ������ࡢ�ۿ���
      String cprodlineid =
          mapMaterialVOS.get(bvo.getCmaterialvid()).getPk_prodline();
      bvo.setCprodlineid(cprodlineid);
      UFBoolean blaborflag = mapMaterialVOS.get(bvo.getCmaterialvid()).getFee();
      bvo.setBlaborflag(blaborflag);
      UFBoolean bdiscountflag =
          mapMaterialVOS.get(bvo.getCmaterialvid()).getDiscountflag();
      bvo.setBdiscountflag(bdiscountflag);

      // ���۵�λ
      String castunitid = bvo.getCastunitid();
      bvo.setCqtunitid(castunitid);
      // ��������
      UFDouble nqtunitnum = bvo.getNastnum();
      bvo.setNqtunitnum(nqtunitnum);
    }
  }

  /**
   * ���ۣ�������������
   * 
   * @param saleOrderVOS
   */
  private void calculateNumAndMny(SaleOrderVO[] saleOrderVOS) {

    for (SaleOrderVO saleOrderVO : saleOrderVOS) {

      SaleOrderBVO[] saleOrderBVOS = saleOrderVO.getChildrenVO();
      UFDouble[] discountMny = new UFDouble[saleOrderBVOS.length];
      UFDouble[] taxMny = new UFDouble[saleOrderBVOS.length];

      for (int i = 0; i < saleOrderBVOS.length; i++) {
        discountMny[i] = saleOrderBVOS[i].getNorigdiscount();
        taxMny[i] = saleOrderBVOS[i].getNorigtaxmny();
        saleOrderBVOS[i]
            .setNorigtaxmny(MathTool.add(discountMny[i], taxMny[i]));
      }

      SaleOrderVOCalculator cal = new SaleOrderVOCalculator(saleOrderVO);

      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(saleOrderVO);
      BodyValueRowRule countutil = new BodyValueRowRule(keyValue);
      int[] rows = countutil.getMarNotNullRows();

      // ����������۱�����
      SOExchangeRateRule exraterule = new SOExchangeRateRule(keyValue);
      exraterule.calcAllBodyExchangeRate();

      // ��������
      cal.calculateOnlyNum(rows, SaleOrderBVO.NASTNUM);
      // �Ƿ�ǿ�Ƶ����ۿ�-true ������
      cal.setChangePrice(UFBoolean.TRUE);
      // ��˰�ϼ�
      cal.calculate(rows, SaleOrderBVO.NORIGTAXMNY);

      // ��ԭ��˰�ϼ�
      for (int i = 0; i < saleOrderBVOS.length; i++) {
        saleOrderBVOS[i].setNorigtaxmny(taxMny[i]);
      }
      // �Ƿ�ǿ�Ƶ����ۿ�-false ���ۿ�
      cal.setChangePrice(UFBoolean.FALSE);
      // ��˰�ϼ�
      cal.calculate(rows, SaleOrderBVO.NORIGTAXMNY);
      // ��ԭ�ۿ۶�
      for (int i = 0; i < saleOrderBVOS.length; i++) {
        saleOrderBVOS[i].setNorigdiscount(discountMny[i]);
      }
    }
  }
}
