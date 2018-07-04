package nc.ui.so.m30.billui.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.bd.feature.ffile.IPubFFileQueryService;
import nc.pubitf.ct.saledaily.saleorder.IRelateSaleCT;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.ct.entity.CtRelatingVO;
import nc.vo.ct.saledaily.entity.CtSalebillViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.res.NCModule;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.SaleMode;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * ���۶����Զ�������ͬ����
 * 
 * @since 6.3
 * @version 2012-12-05 14:57:20
 * @author ��ӱ�
 */
public class AssociateConstractRule {

  private BillCardPanel cardPanel;

  private IKeyValue keyValue;

  private M30TranTypeVO trantypevo;

  /**
   * ������
   * 
   * @param cardPanel
   * @param trantype
   */
  public AssociateConstractRule(
      BillCardPanel cardPanel, M30TranTypeVO trantype) {
    this.cardPanel = cardPanel;
    this.keyValue = new CardKeyValue(cardPanel);
    this.trantypevo = trantype;
  }

  /**
   * �Զ�������ͬ
   * 
   * @param rows
   */
  public void associateCT(int[] rows) {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return;
    }
    if (null == rows || rows.length == 0 || null == this.trantypevo) {
      return;
    }
    // �˻���������֧�ֹ�����ͬ
    Integer salemodel = this.trantypevo.getFsalemode();
    if (SaleMode.MODE_RETURN.equalsValue(salemodel)
        || SaleMode.MODE_RETURNCHANGE.equalsValue(salemodel)) {
      return;
    }
    // �����Ҫ�����е�ctid��ctbid
    this.cleanCTInfo(rows);

    // 1.��֯����VO
    Map<Integer, CtRelatingVO> mRelatingVOs = this.getCtRelatingVOMap(rows);
    int size = mRelatingVOs.size();
    if (size > 0) {
      CtRelatingVO[] relatingVOs = new CtRelatingVO[size];
      int[] associateCTrows = new int[size];
      int i = 0;
      for (Entry<Integer, CtRelatingVO> entry : mRelatingVOs.entrySet()) {
        associateCTrows[i] = entry.getKey().intValue();
        relatingVOs[i] = entry.getValue();
        i++;
      }
      // 2.������ͬ
      IRelateSaleCT ictrelating =
          NCUILocator.getInstance().lookup(IRelateSaleCT.class, NCModule.CT);
      CtSalebillViewVO[] constractviewvos = null;
      try {
        constractviewvos = ictrelating.relateCT(relatingVOs);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      // 3.���ù������
      if (null != constractviewvos && constractviewvos.length > 0) {
        int[] changeRows =
            this.setValueFromCt(constractviewvos, associateCTrows);
        if (changeRows != null && changeRows.length > 0) {
          // ���������͵��۽��
          this.calculate(changeRows);
        }
      }
    }
  }

  private void calculate(int[] changerows) {
    boolean bSO23 = true;

    // SO23���ۺ�˰����˰����
    String pk_group = this.keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP);
    UFBoolean ufbSO23 = SOSysParaInitUtil.getSO23(pk_group);
    if (null != ufbSO23) {
      bSO23 = ufbSO23.booleanValue();
    }

    String editkey =
        bSO23 ? SaleOrderBVO.NQTORIGTAXPRICE : SaleOrderBVO.NQTORIGPRICE;
    SaleOrderCalculator calculator = new SaleOrderCalculator(this.cardPanel);
    // ���㱨������������
    calculator.calculateOnlyNum(changerows, SaleOrderBVO.NNUM);
    // �����˰�ϼ�
    calculator.calculate(changerows, editkey);
  }

  private void cleanCTInfo(int[] rows) {
    for (int row : rows) {
      this.keyValue.setBodyValue(row, SaleOrderBVO.CCTMANAGEBID, null);
      this.keyValue.setBodyValue(row, SaleOrderBVO.CCTMANAGEID, null);
      this.keyValue.setBodyValue(row, SaleOrderBVO.VCTCODE, null);
    }
  }

  private Map<Integer, CtRelatingVO> getCtRelatingVOMap(int[] rows) {
    Map<Integer, CtRelatingVO> mRelatingVOs =
        new HashMap<Integer, CtRelatingVO>();
    String pk_org = this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    String ccustomerid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CCUSTOMERID);
    String corigcurrencyid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    UFDate dbilldate = this.keyValue.getHeadUFDateValue(SaleOrderHVO.DBILLDATE);
    for (int row : rows) {
      String materialid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
      String rowNO = this.keyValue.getBodyStringValue(row, SaleOrderBVO.CROWNO);
      String cqtunitid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CQTUNITID);
      // �̶�ά�ȣ���֯���ͻ������ϡ����֡�����, �⼸���ֶ�ȫ����Ϊ��
      if (PubAppTool.isNull(pk_org) || PubAppTool.isNull(ccustomerid)
          || PubAppTool.isNull(materialid)
          || PubAppTool.isNull(corigcurrencyid) || dbilldate == null) {
        continue;
      }
      CtRelatingVO relatingvo = new CtRelatingVO();
      relatingvo.setPk_org(pk_org);
      relatingvo.setCvendor(ccustomerid);
      relatingvo.setCurrency(corigcurrencyid);
      relatingvo.setDate(dbilldate);
      relatingvo.setMaterial(materialid);
      relatingvo.setSourerowno(rowNO);
      relatingvo.setCqtunit(cqtunitid);
      mRelatingVOs.put(Integer.valueOf(row), relatingvo);
    }
    return mRelatingVOs;
  }

  /**
   * ͨ��������ͬ�����������Ӧ��
   * <p>
   * <b>ct�ӿڱ�֤����vo�ͽ��vo11��Ӧ��û�й�����Ϊnull</b>
   * 
   * @since 6.0
   * @author ��־ΰ
   * @time 2010-10-15 ����07:47:58
   */
  private int[] setValueFromCt(CtSalebillViewVO[] constractviewvos, int[] rows) {
    CtSalebillViewVO constractviewvo = null;
    List<Integer> changeRowList = new ArrayList<Integer>();
    int[] retRows = null;
    // ���ܼ��㻻����
    SOUnitChangeRateRule unitraterule = new SOUnitChangeRateRule(this.keyValue);
    // add by zhangby5 V65��֧ͬ�������룬������ͬʱ��Ҫ��������������������������� 
    // ������ͺ�ͬ����ȷ��,�����ٸ���������Ӱ�충������
    Map<String, UFDouble> nmffilepriceMap = this.queryFfileprice(constractviewvos);
    for (int i = 0; i < rows.length; i++) {
      constractviewvo = constractviewvos[i];
      if (constractviewvo == null) {
        continue;
      }
      // ����������������� begin zhangby5
      String orderFfile =
          this.keyValue.getBodyStringValue(rows[i], SaleOrderBVO.CMFFILEID);
      String ctFfile = constractviewvo.getItem().getCffileid();
      UFDouble ffilePrice = nmffilepriceMap.get(ctFfile);
      if (PubAppTool.isNull(orderFfile) && !PubAppTool.isNull(ctFfile)) {
        this.keyValue.setBodyValue(rows[i], SaleOrderBVO.CMFFILEID, ctFfile);
        this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NMFFILEPRICE,
            ffilePrice);
      }
      // end zhangby5
      changeRowList.add(rows[i]);
      // ��ͬ����ID������ID
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.CCTMANAGEID,
          constractviewvo.getHead().getPk_ct_sale());
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.CCTMANAGEBID,
          constractviewvo.getPk_ct_sale_b());
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.VCTCODE,
          constractviewvo.getVbillcode());
      // �տ�Э��
      this.keyValue.setHeadValue(SaleOrderHVO.CPAYTERMID, constractviewvo
          .getHead().getPk_payterm());

      // ��Ŀ
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.CPROJECTID,
          constractviewvo.getHead().getCprojectid());
      // ��������Ҫ�󣬱�׼��Ʒ�в��������Զ�����Ĺ����ˣ������Ŀ��Ҫ�����Դ򿪸öδ��뼴�ɣ�ͬʱ��Ҫ��ͬ���ű�Z301���Զ��������
      // ���ñ����Զ�����
      // this.setVbdef(rows[i], constractviewvo);
      // ����λ
      String unitid =
          this.keyValue.getBodyStringValue(rows[i], SaleOrderBVO.CUNITID);
      // ҵ������
      // dongli2 2013.8.29 ������ͬʱ Ҫ�ж�һ�� �����ͬ�Ͳ�Я����λ�������Я��
      String astunitid = constractviewvo.getCastunitid();
      if (PubAppTool.isNull(astunitid)) {
        astunitid = unitid;
      }
      else {
        this.keyValue.setBodyValue(rows[i], SaleOrderBVO.CASTUNITID, astunitid);
        unitraterule.calcAstChangeRate(rows[i]);
      }
      // ���۵�λ
      String cqtunitid = constractviewvo.getCqtunitid();
      if (PubAppTool.isNull(cqtunitid)) {
        cqtunitid = astunitid;
      }
      else {
        this.keyValue.setBodyValue(rows[i], SaleOrderBVO.CQTUNITID, cqtunitid);
        unitraterule.calcQtChangeRate(rows[i]);
      }

      // ˰��
      if (null != constractviewvo.getNtaxrate()) {
        this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NTAXRATE,
            constractviewvo.getNtaxrate());
      }
      // ����
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NORIGPRICE,
          constractviewvo.getNorigprice());
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NQTORIGPRICE,
          constractviewvo.getNqtorigprice());
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NORIGTAXPRICE,
          constractviewvo.getNorigtaxprice());
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NQTORIGTAXPRICE,
          constractviewvo.getNqtorigtaxprice());

      // ���������۵�λ���� = ��ͬ���� - �ۼƶ�������
      // �ͻ������ڡ�ԭ�Ҵ���������ͬ���������ֵ���������䣬���ú�ͬ������
      UFDouble num =
          this.keyValue.getBodyUFDoubleValue(rows[i], SaleOrderBVO.NNUM);
      if (num == null || MathTool.compareTo(num, UFDouble.ZERO_DBL) == 0) {
        UFDouble numTemp =
            MathTool.sub(constractviewvo.getItem().getNnum(),
                constractviewvo.getNordnum());
        if (MathTool.compareTo(numTemp, UFDouble.ZERO_DBL) > 0) {
          this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NNUM, numTemp);
        }
        else {
          this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NNUM,
              UFDouble.ZERO_DBL);
        }
      }
    }
    if (changeRowList.size() > 0) {
      retRows = new int[changeRowList.size()];
      for (int i = 0; i < changeRowList.size(); i++) {
        retRows[i] = changeRowList.get(i).intValue();
      }
    }
    return retRows;
  }
  
  private Map<String, UFDouble> queryFfileprice(
      CtSalebillViewVO[] constractviewvos) {
    Set<String> cmffileSet = new HashSet<>();
    for (CtSalebillViewVO constractviewvo : constractviewvos) {
      if (constractviewvo == null) {
        continue;
      }
      String ctFfileId = constractviewvo.getItem().getCffileid();
      if (PubAppTool.isNull(ctFfileId)) {
        continue;
      }
      cmffileSet.add(ctFfileId);
    }
    String corigcurrencyid =
        keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    Map<String, UFDouble> nmffilepriceMap = new HashMap<>();
    if (cmffileSet.size() > 0) {
      // ���ݱ��ֺ������뵽ƥ��������Ŀ��õ�������
      IPubFFileQueryService ffileQueryService =
          NCLocator.getInstance().lookup(IPubFFileQueryService.class);
      try {
        nmffilepriceMap =
            ffileQueryService.queryPriceByPK(
                cmffileSet.toArray(new String[cmffileSet.size()]),
                corigcurrencyid);
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
    }
    return nmffilepriceMap;
  }

  private void setVbdef(int row, CtSalebillViewVO constractviewvo) {
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF1, constractviewvo
        .getItem().getVbdef1());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF2, constractviewvo
        .getItem().getVbdef2());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF3, constractviewvo
        .getItem().getVbdef3());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF4, constractviewvo
        .getItem().getVbdef4());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF5, constractviewvo
        .getItem().getVbdef5());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF6, constractviewvo
        .getItem().getVbdef6());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF7, constractviewvo
        .getItem().getVbdef7());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF8, constractviewvo
        .getItem().getVbdef8());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF9, constractviewvo
        .getItem().getVbdef9());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF10, constractviewvo
        .getItem().getVbdef10());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF11, constractviewvo
        .getItem().getVbdef11());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF12, constractviewvo
        .getItem().getVbdef12());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF13, constractviewvo
        .getItem().getVbdef13());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF14, constractviewvo
        .getItem().getVbdef14());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF15, constractviewvo
        .getItem().getVbdef15());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF16, constractviewvo
        .getItem().getVbdef16());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF17, constractviewvo
        .getItem().getVbdef17());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF18, constractviewvo
        .getItem().getVbdef18());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF19, constractviewvo
        .getItem().getVbdef19());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF20, constractviewvo
        .getItem().getVbdef20());
  }
}
