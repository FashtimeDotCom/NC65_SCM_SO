package nc.pubimpl.so.m4310.pub;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.tool.performance.DeepCloneTool;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.so.salequotation.keyrela.SalequoKeyRela;
import nc.vo.so.salequotation.pub.SalequoCalculator;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.pubitf.so.salequotation.pub.ISalequotaionFillDataForPub;

import nc.ui.so.salequotation.rule.SaleQuotationUnitChangeRateRule;

/**
 * ���۵������ģ�����ɱ��۵�ʱ�ṩ�Ĳ�ȫ����ʵ��
 * 
 * @since 6.31
 * @version 2013-11-20 09:59:29
 * @author liujingn
 */
public class SalequotaionFillDataForPubImpl implements
    ISalequotaionFillDataForPub {

  @Override
  public AggSalequotationHVO[] getFillSalequotationVO(
      AggSalequotationHVO[] Salequotationvos) throws BusinessException {

    AggSalequotationHVO[] oldSalequotationvos =
        (AggSalequotationHVO[]) new DeepCloneTool().deepClone(Salequotationvos);
    this.setPkOrgVValues(Salequotationvos);
    for (AggSalequotationHVO billvo : Salequotationvos) {
      IKeyValue keyValue = new VOKeyValue<AggSalequotationHVO>(billvo);
      IKeyRela keyRela = new SalequoKeyRela();

      // ����ǿ������
      this.setForceDefValue(keyValue);

      if (billvo.getChildrenVO() == null || billvo.getChildrenVO().length == 0) {
        continue;
      }

      BodyValueRowRule rowrule = new BodyValueRowRule(keyValue);
      int[] maternotnullrows = rowrule.get4310MarNotNullRows();

      // �����ջ���������������˰����Ϣ
      SOCountryInfoRule conutry = new SOCountryInfoRule(keyValue);
      conutry.setCountryInfoByPk_Org(maternotnullrows);

      // ���ù������͡�����ó��
      SOBuysellTriaRule buysellTria = new SOBuysellTriaRule(keyValue);
      buysellTria.setBuysellAndTriaFlag(maternotnullrows);

      // ѯ˰
      SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue, keyRela);
      taxInfo.setTaxInfoByBodyPos(maternotnullrows);

      // ���ò����㻻����
      SaleQuotationUnitChangeRateRule unitrate =
          new SaleQuotationUnitChangeRateRule(keyValue);
      for (int row : maternotnullrows) {
        unitrate.calcAstChangeRate(row);
        unitrate.calcQtChangeRate(row);
      }

      // ��������
      for (int row : maternotnullrows) {
        UFDouble nnum =
            keyValue.getBodyUFDoubleValue(row, SalequotationBVO.NNUM);

        UFDouble nassistnum =
            keyValue.getBodyUFDoubleValue(row, SalequotationBVO.NASSISTNUM);

        String nchangerate =
            keyValue.getBodyStringValue(row, SalequotationBVO.NCHANGERATE);

        if (MathTool.isZero(nnum)) {
          nnum = HslParseUtil.hslMultiplyUFDouble(nchangerate, nassistnum);
          keyValue.setBodyValue(row, SalequotationBVO.NNUM, nnum);
        }
      }
    }
    // ��Ϊ�˽ӿڹ������ģ�飬ÿ��ģ���������ݲ�ͬ�����ܻᵼ�¸������ε������ݣ������ڴ����ϲ�����
    return (AggSalequotationHVO[]) AggVOUtil.combinBillVO(oldSalequotationvos,
        Salequotationvos);
  }

  private void setForceDefValue(IKeyValue keyValue) {

    UFDate serverDate = AppContext.getInstance().getBusiDate();
    keyValue.setHeadValue(SalequotationHVO.DQUOTEDATE, serverDate);
    keyValue.setHeadValue(SalequotationHVO.DENDDATE,
        serverDate.getDateAfter(6 * 30).asLocalEnd());
    keyValue.setHeadValue(SalequotationHVO.PK_GROUP, AppContext.getInstance()
        .getPkGroup());
    // ����״̬
    keyValue.setHeadValue(SalequotationHVO.FSTATUSFLAG,
        BillStatusEnum.FREE.value());
    keyValue.setHeadValue(SalequotationHVO.NDISCOUNT, new UFDouble(100));

    String pk_org = keyValue.getHeadStringValue(SalequotationHVO.PK_ORG);
    String pk_org_v = keyValue.getHeadStringValue(SalequotationHVO.PK_ORG_V);
    for (int i = 0; i < keyValue.getBodyCount(); i++) {
      // ������֯
      if (pk_org != null) {
        keyValue.setBodyValue(i, SalequotationHVO.PK_ORG, pk_org);
        keyValue.setBodyValue(i, SalequotationHVO.PK_ORG_V, pk_org_v);
      }

      keyValue.setBodyValue(i, SalequotationBVO.NDISCOUNTRATE,
          new UFDouble(100));
      keyValue.setBodyValue(i, SalequotationBVO.NITEMDISCOUNTRATE,
          new UFDouble(100));
    }
  }

  @Override
  public void calSalequotationNumPriceMny(
      AggSalequotationHVO[] salequotationvos, String editkey)
      throws BusinessException {

    for (AggSalequotationHVO billvo : salequotationvos) {

      IKeyValue keyValue = new VOKeyValue<AggSalequotationHVO>(billvo);

      BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);

      SalequoCalculator vocalcultor = new SalequoCalculator(billvo);

      vocalcultor.calculate(bodycouuitl.get4310MarNotNullRows(), editkey);
    }

  }

  private void setPkOrgVValues(AggSalequotationHVO[] vos) {
    // pk_org����
    Set<String> orgidset = new HashSet<String>();
    for (AggSalequotationHVO vo : vos) {
      SalequotationHVO head = vo.getParentVO();
      orgidset.add(head.getPk_org());
    }
    Map<String, String> orgmapmap =
        OrgUnitPubService.getNewVIDSByOrgIDS(orgidset
            .toArray(new String[orgidset.size()]));
    // ����pk_org_v
    for (AggSalequotationHVO vo : vos) {
      SalequotationHVO head = vo.getParentVO();
      String orgid = head.getPk_org();
      head.setPk_org_v(orgmapmap.get(orgid));
    }
  }
}
