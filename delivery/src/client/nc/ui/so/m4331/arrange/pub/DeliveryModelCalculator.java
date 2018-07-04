package nc.ui.so.m4331.arrange.pub;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.bill.BillListPanel;
import nc.ui.pubapp.calculator.data.BillModelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.pub.CalculatorCondtionUtil;
import nc.vo.so.m4331.pub.WeightAndVolParaUtil;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.para.WeightVolPieceVO;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

/**
 * 
 * @since 6.0
 * @version 2011-6-30 ����02:58:53
 * @author ף����
 */
public class DeliveryModelCalculator {
  /**
   * ��Ҫ�����������۽���㷨���ֶ�
   */
  private static final String[] STRNEEDCALKEY = new String[] {
    // ��������������������
    DeliveryBVO.NASTNUM,
    DeliveryBVO.NNUM,
    DeliveryBVO.VCHANGERATE,
    // ���۵�λ���������ۻ����ʡ�˰��
    DeliveryBVO.NQTUNITNUM,
    DeliveryBVO.VQTUNITRATE,
    DeliveryBVO.NTAXRATE,
    // ��Ʒ�ۿۡ�����˰���ۡ�����˰����
    DeliveryBVO.NITEMDISCOUNTRATE,
    DeliveryBVO.NORIGTAXPRICE,
    DeliveryBVO.NORIGPRICE,
    // ����˰���ۡ�����˰���ۡ���˰����
    DeliveryBVO.NORIGTAXNETPRICE, DeliveryBVO.NORIGNETPRICE,
    DeliveryBVO.NQTORIGTAXPRICE,
    // ��˰���ۡ���˰���ۡ���˰����
    DeliveryBVO.NQTORIGPRICE, DeliveryBVO.NQTORIGTAXNETPRC,
    DeliveryBVO.NQTORIGNETPRICE,
    // ˰���˰����˰�ϼ�
    DeliveryBVO.NTAX, DeliveryBVO.NORIGMNY, DeliveryBVO.NORIGTAXMNY,
    // �ۿ۶�۱�����
    DeliveryBVO.NORIGDISCOUNT, DeliveryBVO.NEXCHANGERATE,
  };

  private BillModel billmodel;

  private BillListPanel listPanel;

  private Set<String> hsNeedCalKey;

  private ScaleUtils scale;

  private Map<String, WeightVolPieceVO> voMap;

  /**
   * BillModelCalculator �Ĺ�����
   * 
   * @param billmodel
   */
  public DeliveryModelCalculator(BillListPanel billListPanle) {
    this.billmodel = billListPanle.getBodyBillModel();
    this.listPanel = billListPanle;
  }

  /**
   * ���������������������۽�����㡣
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows
   * @param editkey
   *          <p>
   * @author fengjb
   * @time 2010-6-1 ����02:25:42
   */
  public void calculate(int[] rows, String editkey) {
    // ����༭�ֶβ���Ӱ�쵽�������۽������м���
    if (!this.getNeedCalKey().contains(editkey)) {
      return;
    }
    // 1.��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    IRelationForItems item = new RelationItemForCal();
    // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    Calculator tool = null;
    this.scale = UIScaleUtils.getScaleUtils();
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    CalculatorCondtionUtil util = new CalculatorCondtionUtil();
    Condition cond = util.getCalcCondition();
    // �������� cond Ϊ����ʱ�Ĳ�������
    for (int row : rows) {
      IKeyValue keyValue =
          new ListKeyValue(this.listPanel, row, ListTemplateType.SUB);

      IDataSetForCal data = new BillModelDataSet(this.billmodel, row, item);
      // �����Ƿ��������
      SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(keyValue);
      cond.setInternational(buysellrule.isBuysellFlagOut(row));
      // �����Ƿ�̶���λ������
      SOUnitChangeRateRule unitrule = new SOUnitChangeRateRule(keyValue);
      cond.setIsFixNchangerate(unitrule.isAstFixedRate(row));
      cond.setIsFixNqtunitrate(unitrule.isQtFixedRate(row));

      tool = new Calculator(data, this.scale);
      // �������� cond Ϊ����ʱ�Ĳ�������
      tool.calculate(cond, editkey);
    }
    if (DeliveryBVO.VCHANGERATE.equals(editkey)
        || DeliveryBVO.NASTNUM.equals(editkey)
        || DeliveryBVO.NNUM.equals(editkey)) {
      this.initMapInfo(rows);
      this.setWeihtVolPiece(rows);
    }
  }

  /**
   * ����������������ʼ�������������۽��༭�¼����ֶΡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����03:46:10
   */
  public Set<String> getNeedCalKey() {
    if (null == this.hsNeedCalKey) {
      this.hsNeedCalKey = new HashSet<String>();
      for (String key : DeliveryModelCalculator.STRNEEDCALKEY) {
        this.hsNeedCalKey.add(key);
      }
    }
    return this.hsNeedCalKey;
  }

  private Map<String, WeightVolPieceVO> getWeightAndVolInfo(String[] pks) {
    try {
      return WeightAndVolParaUtil.getWeightAndVolValue(pks);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private void initMapInfo(int[] rows) {
    String[] pks = new String[rows.length];
    // ȡ�ÿ�Ƭģ���������е���������
    for (int i = 0; i < rows.length; i++) {
      DefaultConstEnum materialid =
          (DefaultConstEnum) this.billmodel.getValueObjectAt(rows[i],
              DeliveryBVO.CMATERIALVID);
      pks[i] = (String) materialid.getValue();
    }
    this.voMap = this.getWeightAndVolInfo(pks);
  }

  /**
   * ���ü���
   * 
   * @param row
   * @param num
   * @param key
   */
  private void setPiece(int row, UFDouble num, String key) {
    DefaultConstEnum cunitid =
        (DefaultConstEnum) this.billmodel.getValueObjectAt(row,
            DeliveryBVO.CASTUNITID);
    WeightVolPieceVO vo = this.voMap.get(key);
    if (null == vo) {
      return;
    }
    String changerate =
        MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(key,
            (String) cunitid.getValue(), vo.getNpiece());
    if (null == changerate || "".equals(changerate)) {
      return;
    }
    UFDouble value = HslParseUtil.hslDivUFDouble(changerate, num);
    this.scale.adjustUnitScale(value, key);
    this.billmodel.setValueAt(value, row, DeliveryBVO.NPIECE);
    this.billmodel.setValueAt(value, row, DeliveryHVO.NTOTALPIECE);
  }

  /**
   * �������
   * 
   * @param row
   * @param num
   * @param key
   */
  private void setVol(int row, UFDouble num, String key) {
    WeightVolPieceVO vo = this.voMap.get(key);
    if (null == vo) {
      return;
    }
    UFDouble vol = vo.getNvol();
    if (vol == null) {
      return;
    }
    UFDouble value = num.multiply(vol);
    value = this.scale.adjustStandardVolumnScale(value);
    this.billmodel.setValueAt(value, row, DeliveryBVO.NVOLUME);
    this.billmodel.setValueAt(value, row, DeliveryHVO.NTOTALVOLUME);
  }

  /**
   * ��������
   * 
   * @param row
   * @param num
   * @param key
   */
  private void setWeight(int row, UFDouble num, String key) {
    WeightVolPieceVO vo = this.voMap.get(key);
    if (null == vo) {
      return;
    }
    UFDouble weight = vo.getNweight();
    if (weight == null) {
      return;
    }
    UFDouble value = num.multiply(weight);
    value = this.scale.adjustStandardWeightScale(value);
    this.billmodel.setValueAt(value, row, DeliveryBVO.NWEIGHT);
    this.billmodel.setValueAt(value, row, DeliveryHVO.NTOTALWEIGHT);
  }

  /**
   * �������� ��� ����
   */
  private void setWeihtVolPiece(int[] rows) {
    for (int i = 0; i < rows.length; i++) {
      DefaultConstEnum pk =
          (DefaultConstEnum) this.billmodel.getValueObjectAt(rows[i],
              DeliveryBVO.CMATERIALVID);
      String key = (String) pk.getValue();
      UFDouble num =
          (UFDouble) this.billmodel.getValueObjectAt(rows[i], DeliveryBVO.NNUM);
      if (num != null) {
        this.setWeight(rows[i], num, key);
        this.setVol(rows[i], num, key);
        this.setPiece(rows[i], num, key);
      }
    }
  }
}
