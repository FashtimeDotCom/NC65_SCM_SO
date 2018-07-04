package nc.pubimpl.so.m30.pub;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.m30.rule.PayTermRule;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOCustRelaDefValueRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.util.ArrayUtil;

import nc.itf.scmpub.reference.uap.bd.psn.PsndocPubService;
import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;

import nc.pubitf.so.m30.pub.ISaleOrderFillDataForPub;

import nc.bs.pubapp.AppBsContext;

/**
 * ���۶��������ģ���������۶���ʱ�ṩ�Ĳ�ȫ���ݽӿ�ʵ��
 * 
 * @since 6.3
 * @version 2013-11-20 09:11:32
 * @author liujingn
 */
public class SaleOrderFillDataForPubImpl implements ISaleOrderFillDataForPub {

  @Override
  public SaleOrderVO[] getFillSaleorderVO(SaleOrderVO[] ordervos)
      throws BusinessException {

    SaleOrderVO[] oldordervos = ordervos;
    Map<String, String> mapbiztype = new HashMap<String, String>();

    // ��Ա������
    this.setEmplyDept(ordervos);

    for (SaleOrderVO salebillvo : ordervos) {
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(salebillvo);

      // ƥ��ҵ������
      this.setBusitype(mapbiztype, keyValue);

      // ���ǿ�Ƶ���Ĭ��ֵ
      this.setForceDefValue(keyValue);

      // �����տ�Э����Ϣ
      PayTermRule payTermRule = new PayTermRule(keyValue);
      payTermRule.setPayTermInfo();

      if (salebillvo.getChildrenVO() == null
          || salebillvo.getChildrenVO().length == 0) {
        continue;
      }

      // ���������֯��������֯��������֯
      BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);

      SaleOrgRelationRule orgrelrule = new SaleOrgRelationRule(keyValue);
      int[] sendstockrows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CSENDSTOCKORGVID);
      orgrelrule.setSendStockOrg(sendstockrows);

      int[] trafficrows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CTRAFFICORGVID);
      orgrelrule.setTrafficOrg(trafficrows);

      int[] finacerows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CSETTLEORGVID);
      orgrelrule.setFinanceOrg(finacerows);

      // ��֯��λ��
      SOCurrencyRule currule = new SOCurrencyRule(keyValue);
      currule.setCurrency(finacerows);
      // �۱�����
      SOExchangeRateRule exrule = new SOExchangeRateRule(keyValue);
      exrule.calcBodyExchangeRates(finacerows);

      // �ջ��ͻ�
      int[] custisnullrows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CRECEIVECUSTID);
      SOCustRelaDefValueRule custrefrule = new SOCustRelaDefValueRule(keyValue);
      custrefrule.setRelaReceiveCust(custisnullrows);

      // ����
      int[] needchangerows = ArrayUtil.combinArrays(sendstockrows, finacerows);
      SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
      countryrule.setCountryInfo(needchangerows);
      // ��������
      SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
      buyflgrule.setBuysellAndTriaFlag(needchangerows);

      // ѯ˰
      SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
      taxInfo.setTaxInfoByBodyPos(needchangerows);

      int[] rows = bodycouuitl.getMarNotNullRows();
      // ���㻻����
      SOUnitChangeRateRule unitrate = new SOUnitChangeRateRule(keyValue);
      //�����Ż����������� add by zhangby5
      unitrate.calcAstAndQtChangeRate(rows);
      // ����������
      for (int row : rows) {
        UFDouble nnum = keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NNUM);

        UFDouble nassistnum =
            keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NASTNUM);

        String nchangerate =
            keyValue.getBodyStringValue(row, SaleOrderBVO.VCHANGERATE);

        if (MathTool.isZero(nnum)) {
          nnum = HslParseUtil.hslMultiplyUFDouble(nchangerate, nassistnum);
          keyValue.setBodyValue(row, SaleOrderBVO.NNUM, nnum);
        }
      }

      // ֱ�˲�
      if (!PubAppTool.isNull(salebillvo.getParentVO().getCtrantypeid())) {
        DirectStoreRule dirstorerule = new DirectStoreRule(keyValue);
        dirstorerule.setDirectStore(rows);
      }

      // ���š�ȫ�ֻ��ʼ���
      SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
      globalraterule.calcGlobalExchangeRate(rows);

      SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
      groupraterule.calcGroupExchangeRate(rows);
    }

    // ��Ϊ�˽ӿڹ������ģ�飬ÿ��ģ���������ݲ�ͬ�����ܻᵼ�¸������ε������ݣ������ڴ����ϲ�����
    return (SaleOrderVO[]) AggVOUtil.combinBillVO(oldordervos, ordervos);
  }

  @Override
  public void calSaleOrderNumpriceMny(SaleOrderVO[] ordervos, String editkey)
      throws BusinessException {

    for (SaleOrderVO salebillvo : ordervos) {
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(salebillvo);

      SaleOrderVOCalculator vocalcultor = new SaleOrderVOCalculator(salebillvo);

      BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);

      vocalcultor.calculate(bodycouuitl.getMarNotNullRows(), editkey);
    }
  }

  private void setEmplyDept(SaleOrderVO[] ordervos) {
    Set<String> setemploy = new HashSet<String>();

    for (SaleOrderVO salebillvo : ordervos) {
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(salebillvo);
      String cemployeeid =
          keyValue.getHeadStringValue(SaleOrderHVO.CEMPLOYEEID);
      if (PubAppTool.isNull(cemployeeid)) {
        cemployeeid =
            UserManageQuery.queryPsndocByUserid(AppContext.getInstance()
                .getPkUser());
      }
      keyValue.setHeadValue(SaleOrderHVO.CEMPLOYEEID, cemployeeid);
      setemploy.add(cemployeeid);
    }
    if (setemploy.size() == 0) {
      return;
    }
    Map<String, List<String>> deptoldid =
        PsndocPubService.queryDeptIDByPsndocIDs(setemploy
            .toArray(new String[setemploy.size()]));
    Set<String> depold = new HashSet<String>();
    for (SaleOrderVO salebillvo : ordervos) {
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(salebillvo);
      String cemployeeid =
          keyValue.getHeadStringValue(SaleOrderHVO.CEMPLOYEEID);
      String tmpeolddep = deptoldid.get(cemployeeid).get(0);
      keyValue.setHeadValue(SaleOrderHVO.CDEPTID, tmpeolddep);
      depold.add(tmpeolddep);
    }
    Map<String, String> depvid =
        DeptPubService.getLastVIDSByDeptIDS(depold.toArray(new String[depold
            .size()]));
    for (SaleOrderVO salebillvo : ordervos) {
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(salebillvo);
      String cdeptid = keyValue.getHeadStringValue(SaleOrderHVO.CDEPTID);
      keyValue.setHeadValue(SaleOrderHVO.CDEPTVID, depvid.get(cdeptid));
    }
  }

  private void setBusitype(Map<String, String> mapbiztype, IKeyValue keyValue) {
    // ƥ��ҵ������
    String trantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    if (PubAppTool.isNull(trantypecode)) {
      return;
    }
    String pk_org = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    String bizkey = pk_org + trantypecode;
    if (mapbiztype.containsKey(bizkey)) {
      keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, mapbiztype.get(bizkey));
    }
    else {
      String userId = AppContext.getInstance().getPkUser();
      String newbiztype =
          PfServiceScmUtil.getBusitype(SOBillType.Order.getCode(),
              trantypecode, pk_org, userId);
      keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, newbiztype);
      mapbiztype.put(bizkey, newbiztype);
    }
  }

  /**
   * ����ת����ǿ�Ƶ�����Ĭ��ֵ
   */
  private void setForceDefValue(IKeyValue keyValue) {

    // ����״̬
    keyValue.setHeadValue(SaleOrderHVO.FSTATUSFLAG,
        BillStatus.FREE.getIntegerValue());
    // �����ۿ�
    UFDouble discountrate =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NDISCOUNTRATE);
    if (null == discountrate) {
      discountrate = SOConstant.ONEHUNDRED;
      keyValue.setHeadValue(SaleOrderHVO.NDISCOUNTRATE, discountrate);
    }

    // ��Ʊ�ͻ����
    String invoicecust =
        keyValue.getHeadStringValue(SaleOrderHVO.CINVOICECUSTID);
    if (PubAppTool.isNull(invoicecust)) {
      SOCustRelaDefValueRule custrelarule =
          new SOCustRelaDefValueRule(keyValue);
      custrelarule.setCustRelaInvoiceCust();
    }

    // ��������
    UFDate busdate = AppBsContext.getInstance().getBusiDate();
    keyValue.setHeadValue(SaleOrderHVO.DBILLDATE, busdate);

    UFDate enddate = busdate.asLocalEnd();
    int bodycount = keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      keyValue.setBodyValue(i, SaleOrderBVO.DBILLDATE, busdate);
      // �ƻ���������
      UFDate senddate = keyValue.getBodyUFDateValue(i, SaleOrderBVO.DSENDDATE);
      if (null == senddate || senddate.before(busdate)) {
        keyValue.setBodyValue(i, SaleOrderBVO.DSENDDATE, enddate);
      }

      // Ҫ�󵽻�����
      UFDate receivedate =
          keyValue.getBodyUFDateValue(i, SaleOrderBVO.DRECEIVEDATE);
      if (null == receivedate || receivedate.before(busdate)) {
        keyValue.setBodyValue(i, SaleOrderBVO.DRECEIVEDATE, enddate);
      }

      // �����ۿ�
      UFDouble disrate =
          keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NDISCOUNTRATE);
      if (null == disrate) {
        keyValue.setBodyValue(i, SaleOrderHVO.NDISCOUNTRATE, discountrate);
      }
      // ��Ʒ�ۿ�
      UFDouble itemdisrate =
          keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NITEMDISCOUNTRATE);
      if (null == itemdisrate) {
        keyValue.setBodyValue(i, SaleOrderBVO.NITEMDISCOUNTRATE,
            SOConstant.ONEHUNDRED);
      }
      // ��״̬
      keyValue.setBodyValue(i, SaleOrderBVO.FROWSTATUS,
          BillStatus.FREE.getIntegerValue());
    }
  }

}
