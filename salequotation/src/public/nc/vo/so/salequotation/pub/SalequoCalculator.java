package nc.vo.so.salequotation.pub;

import java.util.HashSet;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;

/**
 * ���۵�public�˵��۽���㷨������
 * 
 * @since 6.31
 * @version 2013-11-20 11:20:51
 * @author liujingn
 */
public class SalequoCalculator {

  /**
   * �̳��������۽�����Ĺ�����Ƭģ�����ݼ�
   */
  private static class SalequoCardDataSet extends VODataSetForCal {

    private SalequotationHVO voHead;

    public SalequoCardDataSet(SalequotationHVO voHead, SalequotationBVO currVO,
        IRelationForItems item) {
      super(currVO, item);
      this.voHead = voHead;
    }

    /** ���ԭ�ұ��� */
    @Override
    public String getCorigcurrencyid() {
      return this.voHead.getPk_currtype();
    }

    /** ��ü��� */
    @Override
    public String getPk_group() {

      return this.voHead.getPk_group();
    }

    @Override
    public String getCcurrencyid() {
      // ���ڵ��۽���㷨ȡ����λ��Ϊ�գ���ǿ��ȡ��֯��λ�ң����¼�����ı��ҽ�����
      // �������Ӧuap������Ҫ�󣬷���һ����null��ֵ��
      String ret = "unreachable";
      return ret;
    }

    @Override
    public boolean hasItemKey(String key) {
      boolean retValue = true;
      if (SalequotationHVO.PK_CURRTYPE.equals(key)) {
        retValue = true;
      }
      else if (SalequotationHVO.PK_GROUP.equals(key)) {
        retValue = true;
      }
      else {
        retValue = super.hasItemKey(key);
      }
      return retValue;
    }
  }

  private static final String[] STRNEEDCALKEY = new String[] {
    // �������������������ʡ����ۻ�����
    SalequotationBVO.NASSISTNUM,
    SalequotationBVO.NNUM,
    SalequotationBVO.NCHANGERATE,
    SalequotationBVO.NQTCHANGERATE,
    // ��λ������λ�����۵�λ
    SalequotationBVO.CASTUNITID,
    SalequotationBVO.PK_UNIT,
    SalequotationBVO.CQTUNITID,
    // ���۵�λ���������ۻ����ʡ�˰��
    SalequotationBVO.NQTNUM,
    SalequotationBVO.NCHANGERATE,
    SalequotationBVO.NTAXRATE,
    // �����ۿۡ���Ʒ�ۿۡ�����˰���ۡ�����˰����
    SalequotationBVO.NDISCOUNTRATE, SalequotationBVO.NITEMDISCOUNTRATE,
    SalequotationBVO.NORIGTAXPRICE,
    SalequotationBVO.NORIGPRICE,
    // ����˰���ۡ�����˰���ۡ���˰����
    SalequotationBVO.NORIGTAXNETPRICE, SalequotationBVO.NORIGNETPRICE,
    SalequotationBVO.NQTORIGTAXPRICE,
    // ��˰���ۡ���˰���ۡ���˰����
    SalequotationBVO.NQTORIGPRICE, SalequotationBVO.NQTORIGTAXNETPRC,
    SalequotationBVO.NQTORIGNETPRICE,
    // ��˰����˰�ϼ�
    /*--ȥ��˰���ֶΣ���Ϊ���۵�û�б����ֶΣ����۵�˰��ȡԭ��˰����ɱ༭ wangtwa2012-03-01-*/
    SalequotationBVO.NORIGMNY, SalequotationBVO.NORIGTAXMNY,
    // �ۿ۶˰��
    SalequotationBVO.NORIGDISCOUNT, SalequotationBVO.NTAXRATE,
    // ��˰���
    SalequotationBVO.FTAXTYPEFLAG
  };

  private boolean bSCM13 = true;

  private Set<String> hsNeedCalKey;

  private boolean isChgPriceOrDiscount;

  private boolean isfix;

  private boolean isqtfix;

  private boolean origCurToGlobalMoney;

  private AggSalequotationHVO salequotationvo;

  private IKeyValue keyValue;

  /**
   * 
   * @param salequotationvo
   */
  public SalequoCalculator(AggSalequotationHVO salequotationvo) {
    super();
    this.salequotationvo = salequotationvo;
    this.keyValue = new VOKeyValue<AggSalequotationHVO>(salequotationvo);
  }

  /**
   * 
   * @param rows
   * @param itemKey
   */
  public void calculate(int[] rows, String itemKey) {
    // ����༭�ֶβ���Ӱ�쵽�������۽������м���
    if (!this.getNeedCalKey().contains(itemKey)) {
      return;
    }
    // 1.��������ӳ��ʵ�� ���������֮���ӳ���ϵ
    IRelationForItems item = new RelationItemForCal();
    // item.setNqtorignetpriceKey("nqtorignetprice");
    item.setNchangerateKey(SalequotationBVO.NCHANGERATE);
    item.setNqtunitrateKey(SalequotationBVO.NQTCHANGERATE);
    item.setCunitidKey(SalequotationBVO.PK_UNIT);
    item.setNqtunitnumKey(SalequotationBVO.NQTNUM);
    item.setPk_org(SalequotationHVO.PK_ORG);
    item.setNassistnumKey(SalequotationBVO.NASSISTNUM);
    SalequotationHVO voHead = this.salequotationvo.getParentVO();
    SalequotationBVO[] voBodys = this.salequotationvo.getChildrenVO();
    for (int row : rows) {

      // 2.�������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
      IDataSetForCal data = new SalequoCardDataSet(voHead, voBodys[row], item);
      String pk_group = AppContext.getInstance().getPkGroup();
      ScaleUtils scale = new ScaleUtils(pk_group);
      Calculator tool = new Calculator(data, scale);

      // ��������ʵ�����ڼ����ʱ��������ò����������Ƿ�˰���ȵ�
      Condition cond = new Condition();
      Integer buysellflag =
          this.keyValue.getBodyIntegerValue(row, SalequotationBVO.FBUYSELLFLAG);
      cond.setInternational(BuySellFlagEnum.OUTPUT.equalsValue(buysellflag));

      this.initPara(row);

      // �����Ƿ���б��һ���
      cond.setIsCalLocalCurr(false);
      // ���õ����۷�ʽ���ۿ�
      cond.setIsChgPriceOrDiscount(this.isChgPriceOrDiscount());
      // ���ú�˰���Ȼ�����˰����
      cond.setIsTaxOrNet(this.bSCM13);
      // �����Ƿ�̶�������
      cond.setIsFixNchangerate(this.isfix);
      // �����Ƿ�̶����ۻ�����
      cond.setIsFixNqtunitrate(this.isqtfix);
      // ����NC001����
      cond.setGroupLocalCurrencyEnable(false);
      cond.setOrigCurToGroupMoney(false);
      // ����NC002����
      cond.setGlobalLocalCurrencyEnable(false);
      cond.setOrigCurToGlobalMoney(false);

      // �������� cond Ϊ����ʱ�Ĳ�������
      tool.calculate(cond, itemKey);
    }

  }

  private Set<String> getNeedCalKey() {
    if (null == this.hsNeedCalKey) {
      this.hsNeedCalKey = new HashSet<String>();
      for (String key : SalequoCalculator.STRNEEDCALKEY) {
        this.hsNeedCalKey.add(key);
      }
    }
    return this.hsNeedCalKey;
  }

  /**
   * ��ʼConditionҪ�õ��Ĳ���
   */
  private void initPara(int row) {
    String pk_group =
        this.keyValue.getHeadStringValue(SalequotationHVO.PK_GROUP);

    // 1.������SO23:�Ƿ�˰����
    UFBoolean scm13 = SOSysParaInitUtil.getSO23(pk_group);
    if (null == scm13) {
      scm13 = UFBoolean.FALSE;
    }
    this.bSCM13 = scm13.booleanValue();
    // 3.�Ƿ�̶���λ������
    String cmaterialvid =
        this.keyValue.getBodyStringValue(row, SalequotationBVO.PK_MATERIAL_V);
    String cunitid =
        this.keyValue.getBodyStringValue(row, SalequotationBVO.PK_UNIT);
    String castunitid =
        this.keyValue.getBodyStringValue(row, SalequotationBVO.CASTUNITID);
    String cqtunitid =
        this.keyValue.getBodyStringValue(row, SalequotationBVO.CQTUNITID);
    if (!PubAppTool.isNull(cmaterialvid) && !PubAppTool.isNull(castunitid)
        && !PubAppTool.isNull(cunitid)) {
      this.isfix =
          MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(cmaterialvid,
              cunitid, castunitid);
    }
    // 4.�Ƿ�̶����۵�λ������
    if (!PubAppTool.isNull(cmaterialvid) && !PubAppTool.isNull(cqtunitid)
        && !PubAppTool.isNull(cunitid)) {

      this.isqtfix =
          MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(cmaterialvid,
              cunitid, cqtunitid);
    }

  }

  /**
   * 
   * @return isChgPriceOrDiscount
   */
  public boolean isChgPriceOrDiscount() {
    return this.isChgPriceOrDiscount;
  }

  /**
   * 
   * @param isChgPriceOrDiscount
   */
  public void setIsChgPriceOrDiscount(boolean isChgPriceOrDiscount) {
    this.isChgPriceOrDiscount = isChgPriceOrDiscount;
  }
}
