package nc.vo.so.m4331.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.bd.cust.addressdoc.AddressDocVO;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.org.StockOrgVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.pub.DeliveryVOCalculator;
import nc.vo.so.m4331.pub.WeightAndVolParaUtil;
import nc.vo.so.pub.para.WeightVolPieceVO;
import nc.vo.to.m5x.entity.BillItemVO;
import nc.vo.to.m5x.entity.BillVO;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.reference.uap.bd.addrdoc.AddrdocPubService;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;

import nc.impl.pubapp.env.BSContext;

/**
 * ��ʽ������ʽ���ɷ�����ʱ������Ĭ������
 * 
 * @since 6.0
 * @version 2010-12-29 ����11:38:07
 * @author ף����
 */
public class FillDeFaultDataRule {

  // ������Ҫ���е��۽�����ķ���������vo
  private Set<DeliveryBVO> bvoSet;

  private Map<String, String> orgVidMap;

  // ���淢�������֯��Ϣ
  private Map<String, StockOrgVO> storMap;

  private Map<String, WeightVolPieceVO> voMap;

  // ���淢���ֿ���Ϣ
  private Map<String, StordocVO> wareMap;

  /**
   * ���÷�������Ĭ��ֵ
   * 
   * @param srcVos
   * 
   * @param vos
   */
  public void setDefaultData(AggregatedValueObject[] srcVOs,
      AggregatedValueObject[] destVOs) {
    if(destVOs == null){
      return;
    }

    DeliveryVO[] vos = (DeliveryVO[]) destVOs;
    // �����Դ�����ܷ����ɷ�����
    this.checkRule(vos);

    // �к�����
    for (DeliveryVO vo : vos) {
      VORowNoUtils.setVOsRowNoByRule(vo.getAllChildrenVO(), DeliveryBVO.CROWNO);
    }

    // ������Դ��Ϣ���ÿͻ��ͳ�ʼ��Ҫ���е��۽�����ķ�����vo
    this.setCustAndCalVO(srcVOs, vos);
    this.initMaps(vos);
    this.setDate(vos);

    for (DeliveryVO vo : vos) {
      this.setBodyData(vo);
      this.setHeadData(vo);
    }
    // ���е��۽�����
    this.calculator();
//    this.setWeightVolPiece(vos);
    this.setHeadValue(vos);
    DeliveryMarginRule margin = new DeliveryMarginRule();
    margin.process(vos);
  }

  /* ���е��۽����� */
  private void calculator() {
    if (null == this.bvoSet || this.bvoSet.size() == 0) {
      return;
    }
    DeliveryBVO[] bvos = new DeliveryBVO[this.bvoSet.size()];
    this.bvoSet.toArray(bvos);
    int[] rows = new int[bvos.length];
    for (int i = 0; i < bvos.length; i++) {
      rows[i] = i;
    }
    DeliveryVOCalculator calculator = new DeliveryVOCalculator(bvos);
    calculator.calculate(rows, DeliveryBVO.NNUM);
  }

  /**
   * �����Դ�����ܷ����ɷ�����
   * 
   * @param destVOs
   */
  private void checkRule(DeliveryVO[] destVOs) {
    for (DeliveryVO vo : destVOs) {
      if (PubAppTool.isNull(vo.getParentVO().getPk_org())) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006002_0", "04006002-0161")/* ��Դ���ݵ�������֯Ϊ�գ�ת��ʧ�ܣ� */);
      }
    }
  }

  private Map<String, String> getCusPk(String[] pk_strocs) {
    Map<String, String> storcMap =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(pk_strocs);
    if (VOChecker.isEmpty(storcMap)) {
      return null;
    }
    List<String> list = new ArrayList<String>();
    for (String pk : pk_strocs) {
      list.add(storcMap.get(pk));
    }
    String[] pk_org = new String[list.size()];
    pk_org = list.toArray(pk_org);
    Map<String, String> custMap = CustomerPubService.queryCusPkByOrgPk(pk_org);
    Map<String, String> map = new HashMap<String, String>();
    for (String pk : pk_strocs) {
      String org = storcMap.get(pk);
      String cust = custMap.get(org);
      map.put(pk, cust);
    }
    return map;
  }

  /**
   * ���������������Ĺ�����
   * 
   * @param tempSet
   * 
   * @return
   */
  private Map<String, WeightVolPieceVO> getWeightAndVolInfo(
      Set<DeliveryBVO> tempSet) {
    String[] pks = new String[tempSet.size()];
    DeliveryBVO[] bvos = new DeliveryBVO[tempSet.size()];
    tempSet.toArray(bvos);
    // ȡ�ÿ�Ƭģ���������е���������
    int i = 0;
    for (DeliveryBVO bvo : bvos) {
      pks[i] = bvo.getCmaterialvid();
      i++;
    }
    try {
      return WeightAndVolParaUtil.getWeightAndVolValue(pks);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /* ���淢���ֿ������ͷ����ֿ�vo
   * ���ݷ����ֿ����ã���ַ �绰����Ϣ
   * ���淢�������֯�����Ϳ����֯vo
   * ���ݷ��������֯���ã���ַ����Ϣ */
  private void initMaps(DeliveryVO[] vos) {
    // �����ֿ�
    Set<String> sendstordocSet = new HashSet<String>();
    // ���������֯
    Set<String> sendstockSet = new HashSet<String>();
    // ����id
    Set<String> pkorgSet = new HashSet<String>();
    for (DeliveryVO vo : vos) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        String pk_sendstordoc = bvo.getCsendstordocid();
        String pk_sendstorc = bvo.getCsendstockorgid();
        if (pk_sendstordoc != null && !"".equals(pk_sendstordoc)) {
          sendstordocSet.add(pk_sendstordoc);
        }
        if (pk_sendstorc != null && !"".equals(pk_sendstorc)) {
          sendstockSet.add(pk_sendstorc);
        }
      }
      pkorgSet.add(vo.getParentVO().getPk_org());
    }
    this.initStorcMap(sendstordocSet);
    this.initStorcOrgMap(sendstockSet);
    this.initOrgVidMap(pkorgSet);
  }

  private void initOrgVidMap(Set<String> pkorgSet) {
    if (pkorgSet.size() == 0) {
      return;
    }
    String[] pk_orgs = new String[pkorgSet.size()];
    pkorgSet.toArray(pk_orgs);
    this.orgVidMap = OrgUnitPubService.getNewVIDSByOrgIDS(pk_orgs);
  }

  /* ���淢���ֿ���Ϣ
   * 
   * @param sendstordocList */
  private void initStorcMap(Set<String> sendstordocList) {
    if (sendstordocList.size() == 0) {
      return;
    }
    this.wareMap = new HashMap<String, StordocVO>();
    String[] pk_sendstordocs = new String[sendstordocList.size()];
    pk_sendstordocs = sendstordocList.toArray(pk_sendstordocs);
    StordocVO[] vos =
        StordocPubService.queryStordocByPks(pk_sendstordocs, new String[] {
          StordocVO.PK_ADDRESS, StordocVO.STORADDR, StordocVO.PRINCIPALCODE,
          StordocVO.PHONE
        });
    for (String pk_sendstordoc : pk_sendstordocs) {
      for (StordocVO vo : vos) {
        if (pk_sendstordoc.equals(vo.getPk_stordoc())) {
          this.wareMap.put(pk_sendstordoc, vo);
        }
      }
    }

  }

  /* ���淢�������֯��Ϣ
   * 
   * @param sendstockList */
  private void initStorcOrgMap(Set<String> sendstockList) {
    if (sendstockList.size() == 0) {
      return;
    }
    this.storMap = new HashMap<String, StockOrgVO>();
    String[] pk_sendstorcs = new String[sendstockList.size()];
    pk_sendstorcs = sendstockList.toArray(pk_sendstorcs);
    StockOrgVO[] vos =
        StockOrgPubService.queryStockOrgByIDs(pk_sendstorcs, new String[] {
          StockOrgVO.PK_ADDRESS, StockOrgVO.ADDRESSBOOK
        });
    for (String pk_sendstorc : pk_sendstorcs) {
      for (StockOrgVO vo : vos) {
        if (pk_sendstorc.equals(vo.getPk_stockorg())) {
          this.storMap.put(pk_sendstorc, vo);
        }
      }
    }
  }

  private boolean isNullOrZero(UFDouble num) {
    if (null == num || UFDouble.ZERO_DBL.compareTo(num) == 0) {
      return true;
    }
    return false;
  }

  /* ���ñ���Ĭ��ֵ
   * 
   * @param vo */
  private void setBodyData(DeliveryVO vo) {
    DeliveryBVO[] bvos = vo.getChildrenVO();
    for (DeliveryBVO bvo : bvos) {
      // ���ü���
      bvo.setPk_group(vo.getParentVO().getPk_group());
      // �����ֿ�
      String pk_sendstordoc = bvo.getCsendstordocid();
      // ���������֯
      String pk_sendstorc = bvo.getCsendstockorgid();
      // ���ݷ����ֿ����÷�����Ϣ
      if (pk_sendstordoc != null && !"".equals(pk_sendstordoc)) {
        StordocVO stordocvo = this.wareMap.get(pk_sendstordoc);
        if (!VOChecker.isEmpty(stordocvo)) {
          // �����ص�
          bvo.setCsendadddocid(stordocvo.getPk_address());
          // ������ַ
          bvo.setCsendaddrid(stordocvo.getStoraddr());
          // ������ϵ��
          bvo.setCsendpersonid(stordocvo.getPrincipalcode());
          // ������ϵ�绰
          bvo.setVsendtel(stordocvo.getPhone());
        }
      }
      else if (!VOChecker.isEmpty(pk_sendstorc)) {
        StockOrgVO stockorgvo = this.storMap.get(pk_sendstorc);
        if (!VOChecker.isEmpty(stockorgvo)) {
          // �����ص�
          bvo.setCsendadddocid(stockorgvo.getPk_address());
          // ������ַ
          bvo.setCsendaddrid(stockorgvo.getAddressbook());
        }
      }
      this.setSendArea(bvo);
    }
  }

  /**
   * ������Դ�ڵ�����������Ҫ���е��۽�����ķ�����
   * 
   * @param billvos
   * @param vos
   */
  private void setCalFor5X(BillVO[] billvos, DeliveryVO[] vos) {
    Map<String, BillItemVO> billMap = new HashMap<String, BillItemVO>();
    for (BillVO bill : billvos) {
      BillItemVO[] items = bill.getChildrenVO();
      for (BillItemVO item : items) {
        billMap.put(item.getCbill_bid(), item);
      }
    }
    this.bvoSet = new HashSet<DeliveryBVO>();
    for (DeliveryVO vo : vos) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        String srcbid = bvo.getCfirstbid();
        BillItemVO item = billMap.get(srcbid);
        // ��Դ���ݵ��ۼƷ�����������Ϊ�ջ���0�򷢻���Ҫ���е��۽�����
        UFDouble srcsendnum = item.getNsendnum();
        if (this.isNullOrZero(srcsendnum)) {
          // ����Ҫ���е��۽�����Ļ� ��������������Դ�����ε���
          UFDouble nastnum = item.getNastnum();
          bvo.setNastnum(nastnum);
          UFDouble qtnum = item.getNqtunitnum();
          bvo.setNqtunitnum(qtnum);
          continue;
        }
        this.bvoSet.add(bvo);
      }
    }

  }

  /**
   * ������Դ�����۶�������Ҫ���е��۽�����ķ�����
   * 
   * @param salevos
   * @param vos
   */
  private void setCalVOFor30(SaleOrderVO[] salevos, DeliveryVO[] vos) {
    // �������۶�������vo
    Map<String, SaleOrderBVO> tempMap = new HashMap<String, SaleOrderBVO>();
    for (SaleOrderVO bill : salevos) {
      SaleOrderBVO[] items = bill.getChildrenVO();
      for (SaleOrderBVO item : items) {
        tempMap.put(item.getCsaleorderbid(), item);
      }
    }
    this.bvoSet = new HashSet<DeliveryBVO>();
    for (DeliveryVO vo : vos) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        String srcbid = bvo.getCsrcbid();
        SaleOrderBVO item = tempMap.get(srcbid);
        // ��Դ�����ۼƷ��������������Ϊ�ջ���0 ����Ҫ���е��۽�����
        UFDouble sendnum = item.getNtotalsendnum();
        if (this.isNullOrZero(sendnum)) {
          // ����Ҫ���е��۽�����������ֱ�Ӵ����δ��뷢����
          UFDouble nastnum = item.getNastnum();
          bvo.setNastnum(nastnum);
          UFDouble qtnum = item.getNqtunitnum();
          bvo.setNqtunitnum(qtnum);
          continue;
        }
        this.bvoSet.add(bvo);
      }
    }
  }

  /**
   * ���ÿͻ���Ϣ�ͳ�ʼ��Ҫ���е��۽�����ķ���������vo
   * 
   * @param srcVOs
   * @param vos
   */
  private void setCustAndCalVO(AggregatedValueObject[] srcVOs, DeliveryVO[] vos) {
    if(vos==null){
      return;
    }
    for(DeliveryVO vo:vos){
      if(vo.getChildrenVO()==null||vo.getChildrenVO().length==0){
        return;
      }
    }
    String srcBilltype = vos[0].getChildrenVO()[0].getVsrctype();
    if (SOBillType.Order.getCode().equals(srcBilltype)) {
      SaleOrderVO[] salevos = (SaleOrderVO[]) srcVOs;
      this.setCalVOFor30(salevos, vos);
      return;
    }
    BillVO[] billvos = (BillVO[]) srcVOs;
    this.setCustAndCalVOFor5X(billvos, vos);
  }

  /**
   * �����Դ�ڵ������� ��Ҫ���ÿͻ� ���ҳ�ʼ����Ҫ���ĵ��۽�����ķ���������vo
   * 
   * @param billvos
   * @param vos
   */
  private void setCustAndCalVOFor5X(BillVO[] billvos, DeliveryVO[] vos) {
    this.setCustFor5X(billvos, vos);
    this.setCalFor5X(billvos, vos);
  }

  private void setCustFor5X(BillVO[] billvos, DeliveryVO[] vos) {
    Map<String, String> tempMap = new HashMap<String, String>();
    for (BillVO bill : billvos) {
      String hid = bill.getParentVO().getCbillid();
      String instorcid = bill.getParentVO().getCinstockorgid();
      tempMap.put(hid, instorcid);
    }

    if (tempMap.size() == 0) {
      return;
    }
    String[] pk_strocs = new String[tempMap.size()];
    pk_strocs = tempMap.values().toArray(pk_strocs);
    Map<String, String> map = this.getCusPk(pk_strocs);
    for (DeliveryVO vo : vos) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        String csrcid = bvo.getCsrcid();
        String instorc = tempMap.get(csrcid);
        String pk_cust = map.get(instorc);
        bvo.setCordercustid(pk_cust);
        bvo.setCinvoicecustid(pk_cust);
      }
    }
  }

  private void setDate(DeliveryVO[] newvos) {
    for (DeliveryVO vo : newvos) {
      DeliveryHVO hvo = vo.getParentVO();
      UFDate date = AppContext.getInstance().getBusiDate();
      hvo.setDbilldate(date);
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        bvo.setDbilldate(date);
        UFDate sendDate = bvo.getDsenddate();
        UFDate receiveDate = bvo.getDreceivedate();
        if (null == sendDate || sendDate.before(date)) {
          bvo.setDsenddate(date.asLocalEnd());
        }
        if (null == receiveDate || receiveDate.before(date)) {
          bvo.setDreceivedate(date.asLocalEnd());
        }
      }
    }
  }

  /* ���ñ�ͷĬ��ֵ
   * 
   * @param vo */
  private void setHeadData(DeliveryVO vo) {
    this.setOrgVid(vo);
  }

  private void setHeadValue(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      UFDouble totalweight = null;
      UFDouble totalnum = null;
      UFDouble totalvol = null;
      UFDouble totalpiece = null;
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        UFDouble weight = bvo.getNweight();
        if (weight != null) {
          totalweight = MathTool.add(weight, totalweight);
        }
        UFDouble nastnum = bvo.getNastnum();
        if (nastnum != null) {
          totalnum = MathTool.add(nastnum, totalnum);
        }
        UFDouble vol = bvo.getNvolume();
        if (vol != null) {
          totalvol = MathTool.add(totalvol, vol);
        }
        UFDouble piece = bvo.getNpiece();
        if (null != piece) {
          totalpiece = MathTool.add(totalpiece, piece);
        }
      }
      vo.getParentVO().setNtotalastnum(totalnum);
      vo.getParentVO().setNtotalvolume(totalvol);
      vo.getParentVO().setNtotalweight(totalweight);
      vo.getParentVO().setNtotalpiece(totalpiece);
    }
  }

  private void setOrgVid(DeliveryVO vo) {
    String pk_org = vo.getParentVO().getPk_org();
    String pk_org_v = this.orgVidMap.get(pk_org);
    if (null != pk_org_v) {
      vo.getParentVO().setPk_org_v(pk_org_v);
    }
  }

  /**
   * ���ü���
   * 
   * @param scale
   * 
   * @param row
   * @param num
   */
  private void setPiece(DeliveryBVO bvo, ScaleUtils scale) {
    UFDouble num = bvo.getNnum();
    if (null == num) {
      return;
    }
    String pk = bvo.getCmaterialvid();
    WeightVolPieceVO vo = this.voMap.get(pk);
    if (null == vo) {
      return;
    }
    String changerate =
        MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(pk,
            bvo.getCunitid(), vo.getNpiece());
    if (null == changerate || "".equals(changerate)) {
      return;
    }
    UFDouble value = HslParseUtil.hslDivUFDouble(changerate, num);
    value = scale.adjustUnitScale(value, pk);
    bvo.setNpiece(value);
  }

  /* ���ݷ����ص����÷������� */
  private void setSendArea(DeliveryBVO bvo) {
    if (null == bvo.getCsendadddocid() || "".equals(bvo.getCsendadddocid())) {
      return;
    }
    AddressDocVO vo =
        AddrdocPubService.queryAddrDocVOByID(bvo.getCsendadddocid());
    if (!VOChecker.isEmpty(vo)) {
      bvo.setCsendareaid(vo.getPk_areacl());
    }

  }

  /**
   * �������
   * 
   * @param bvo
   * @param scale
   */
  private void setVol(DeliveryBVO bvo, ScaleUtils scale) {
    UFDouble num = bvo.getNnum();
    if (null == num) {
      return;
    }
    String pk = bvo.getCmaterialvid();
    WeightVolPieceVO vo = this.voMap.get(pk);
    if (null == vo) {
      return;
    }
    UFDouble vol = vo.getNvol();
    if (vol == null) {
      return;
    }
    UFDouble value = num.multiply(vol);
    value = scale.adjustStandardVolumnScale(value);
    bvo.setNvolume(value);
  }

  /**
   * ��������
   * 
   * @param bvo
   * @param scale
   */
  private void setWeight(DeliveryBVO bvo, ScaleUtils scale) {
    UFDouble num = bvo.getNnum();
    if (null == num) {
      return;
    }
    String pk = bvo.getCmaterialvid();
    WeightVolPieceVO vo = this.voMap.get(pk);
    if (null == vo) {
      return;
    }
    UFDouble weight = vo.getNweight();
    if (weight == null) {
      return;
    }
    UFDouble value = num.multiply(weight);
    value = scale.adjustStandardWeightScale(value);
    bvo.setNweight(value);
  }

  private void setWeightVolPiece(DeliveryVO[] vos) {
    Set<DeliveryBVO> tempSet = new HashSet<DeliveryBVO>();
    for (DeliveryVO vo : vos) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        tempSet.add(bvo);
      }
    }
    this.voMap = this.getWeightAndVolInfo(tempSet);
    DeliveryBVO[] bvos = new DeliveryBVO[tempSet.size()];
    String pk_group = BSContext.getInstance().getGroupID();
    ScaleUtils scale = new ScaleUtils(pk_group);
    tempSet.toArray(bvos);
    for (DeliveryBVO bvo : bvos) {
      this.setPiece(bvo, scale);
      this.setWeight(bvo, scale);
      this.setVol(bvo, scale);
    }
  }

}
