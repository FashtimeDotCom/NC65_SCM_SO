package nc.ui.so.m4331.billui.pub.calculator;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.bd.material.MaterialConvertVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.pub.WeightAndVolParaUtil;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.para.WeightVolPieceVO;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

public class DeliveryCardCalculator {

  private BillCardPanel cardpanel;

  /**
   * ��Ҫ�����������۽���㷨���ֶ�
   */
  private Set<String> hsNeedCalKey;

  private IKeyValue keyValue;

  private ScaleUtils scale;

  private Map<String, WeightVolPieceVO> voMap;

  public DeliveryCardCalculator(BillCardPanel card) {
    this.cardpanel = card;
    this.keyValue = new CardKeyValue(this.cardpanel);
  }

  private void calculate(int[] rows, String editKey, boolean isonlynum) {
    if (!this.getNeedCalKey().contains(editKey)) {
      return;
    }
    // 1.��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    IRelationForItems item = new RelationItemForCal();
    // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
    Condition cond = SOCalConditionRule.getCondition();
    // ���õ����۷�ʽ���ۿ�
    cond.setIsChgPriceOrDiscount(false);
    String pk_group = AppContext.getInstance().getPkGroup();
    this.scale = new ScaleUtils(pk_group);
    SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(this.keyValue);
    SOUnitChangeRateRule unitrule = new SOUnitChangeRateRule(this.keyValue);
    // �����Ż���������ȡ��λ�������Ƿ��ǹ̶������� add by zhangby5 start
    Map<String, UFBoolean> fixedMap = unitrule.isAstAndQtFixedRate(rows);
    for (int row : rows) {

      IDataSetForCal data = new BillCardPanelDataSet(this.cardpanel, row, item);
      // �����Ƿ��������
      cond.setInternational(buysellrule.isBuysellFlagOut(row));
      String cmaterialvid =
          keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      String castunitid =
          keyValue.getBodyStringValue(row, SOItemKey.CASTUNITID);
      String cqtunitid = keyValue.getBodyStringValue(row, SOItemKey.CQTUNITID);
      String keycastunitid = cmaterialvid + castunitid;
      String keycqtunitid = cmaterialvid + cqtunitid;
      // �����Ƿ�̶���λ������
      cond.setIsFixNchangerate(fixedMap.get(keycastunitid).booleanValue());
      // �Ƿ�̶����۵�λ������
      cond.setIsFixNqtunitrate(fixedMap.get(keycqtunitid).booleanValue());
      // zhangby5 end

      Calculator tool = new Calculator(data, this.scale);
      // �������� cond Ϊ����ʱ�Ĳ�������
      if (isonlynum) {
        tool.calculateOnlyNumAssNumQtNum(cond, editKey);
      }
      else {
        tool.calculate(cond, editKey);
      }
    }

    this.voMap = this.getMapInfo(rows);
    this.setWeihtVolPiece(rows);

  }

  public void calculate(int[] rows, String editKey) {
    this.calculate(rows, editKey, false);
  }

  public void calculate(List<Integer> rows, String editKey) {
    int size = rows.size();
    if (rows == null || size == 0) {
      return;
    }
    int[] calRows = new int[size];
    for (int i = 0; i < size; i++) {
      calRows[i] = rows.get(i);
    }
    this.calculate(calRows, editKey, false);
  }

  /**
   * ����������������ʼ�������������۽��༭�¼����ֶΡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author ף����
   * @time 2010-4-21 ����03:46:10
   */
  public Set<String> getNeedCalKey() {
    if (null == this.hsNeedCalKey) {
      this.hsNeedCalKey = new HashSet<String>();
      for (String key : SOConstant.STRNEEDCALKEY) {
        this.hsNeedCalKey.add(key);
      }
    }
    return this.hsNeedCalKey;
  }

  private Map<String, WeightVolPieceVO> getMapInfo(int[] rows) {
    String[] pks = new String[rows.length];
    // ȡ�ÿ�Ƭģ���������е���������
    for (int i = 0; i < rows.length; i++) {
      String materialid =
          this.keyValue.getBodyStringValue(rows[i], DeliveryBVO.CMATERIALVID);
      pks[i] = materialid;
    }
    try {
      Map<String, WeightVolPieceVO> map =
          WeightAndVolParaUtil.getWeightAndVolValue(pks);
      return map;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private void setHeadValue() {
    UFDouble totalweight = null;
    UFDouble totalnum = null;
    UFDouble totalvol = null;
    UFDouble totalpiece = null;
    BodyValueRowRule countutil = new BodyValueRowRule(this.keyValue);
    int[] rows = countutil.getMarNotNullRows();
    for (int i = 0; i < rows.length; i++) {
      UFDouble weight =
          this.keyValue.getBodyUFDoubleValue(rows[i], DeliveryBVO.NWEIGHT);
      if (weight != null) {

        totalweight = MathTool.add(weight, totalweight);
      }
      UFDouble nastnum =
          this.keyValue.getBodyUFDoubleValue(rows[i], DeliveryBVO.NASTNUM);
      if (nastnum != null) {
        totalnum = MathTool.add(nastnum, totalnum);
      }
      UFDouble vol =
          this.keyValue.getBodyUFDoubleValue(rows[i], DeliveryBVO.NVOLUME);
      if (vol != null) {
        totalvol = MathTool.add(totalvol, vol);
      }
      UFDouble piece =
          this.keyValue.getBodyUFDoubleValue(rows[i], DeliveryBVO.NPIECE);
      if (null != piece) {
        totalpiece = MathTool.add(totalpiece, piece);
      }
    }
    this.keyValue.setHeadValue(DeliveryHVO.NTOTALASTNUM, totalnum);
    this.keyValue.setHeadValue(DeliveryHVO.NTOTALVOLUME, totalvol);
    this.keyValue.setHeadValue(DeliveryHVO.NTOTALWEIGHT, totalweight);
    this.keyValue.setHeadValue(DeliveryHVO.NTOTALPIECE, totalpiece);
  }

  /**
   * ���ü���
   * 
   * @param row
   * @param num
   * @param key
   */
  private void setPiece(int row, UFDouble num, String key) {
    WeightVolPieceVO vo = this.voMap.get(key);
    if (null == vo) {
      return;
    }

    String changerate =
        MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(key,
            this.keyValue.getBodyStringValue(row, DeliveryBVO.CUNITID),
            vo.getNpiece());
    if (null == changerate || "".equals(changerate)) {
      return;
    }
    UFDouble value = HslParseUtil.hslDivUFDouble(changerate, num);
    this.scale.adjustUnitScale(value, key);
    this.keyValue.setBodyValue(row, DeliveryBVO.NPIECE, value);
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
    this.keyValue.setBodyValue(row, DeliveryBVO.NVOLUME, value);
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
    this.keyValue.setBodyValue(row, DeliveryBVO.NWEIGHT, value);
  }

  /**
   * �������� ��� ����
   */
  private void setWeihtVolPiece(int[] rows) {
    for (int i = 0; i < rows.length; i++) {
      String pk =
          this.keyValue.getBodyStringValue(rows[i], DeliveryBVO.CMATERIALVID);
      UFDouble num =
          this.keyValue.getBodyUFDoubleValue(rows[i], DeliveryBVO.NNUM);
      if (num != null) {
        this.setWeight(rows[i], num, pk);
        this.setVol(rows[i], num, pk);
        this.setPiece(rows[i], num, pk);
      }
    }
    this.setHeadValue();
  }
}
