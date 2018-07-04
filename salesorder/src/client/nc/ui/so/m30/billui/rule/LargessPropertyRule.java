package nc.ui.so.m30.billui.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.m30.billui.largessapportion.ApportionForPriceChange;
import nc.ui.so.m30.billui.largessapportion.ApportionForPriceUnchange;
import nc.ui.so.m30.billui.largessapportion.ApportionForSameMaterial;
import nc.ui.so.m30.billui.largessapportion.ApportionGroupByLargess;
import nc.ui.so.m30.billui.largessapportion.ApportionGroupByOne;
import nc.ui.so.m30.billui.largessapportion.ApportionGroupBySameMaterial;
import nc.ui.so.m30.billui.largessapportion.IApportionAction;
import nc.ui.so.m30.billui.largessapportion.IApportionGroup;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.enumeration.Largesstype;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.LargessDisType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

public class LargessPropertyRule {

  private BillCardPanel cardPanel;

  private IKeyValue keyValue;

  private M30TranTypeVO trantypevo;

  public LargessPropertyRule(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
    this.keyValue = new CardKeyValue(cardPanel);
  }

  public void processLargessApportion() {
    // ���Ϸ���
    this.checkLargessApportion();
    // �����Ҫ��̯����
    List<Integer> listneedrow = this.getNeedApprotRows();

    // ��̯�������
    IApportionGroup appgroupstrategy = this.getApportionGroupStrategy();
    List<List<Integer>> appgroups =
        appgroupstrategy.getApportionGroupRows(this.keyValue, listneedrow);
    if (null == appgroups || appgroups.size() == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0033")/*@res "���ս���������Ʒ�۸��̯��ʽ��û�пɽ��з�̯���С�"*/);
    }
    // ��̯��������
    IApportionAction appactionstrategy = this.getApportionActionStrategy();
    List<Integer> listallapprow = new ArrayList<Integer>();
    boolean istaxprior = this.isTaxPrior();
    for (List<Integer> rowlist : appgroups) {
      appactionstrategy.apportion(this.cardPanel, rowlist, istaxprior);
      listallapprow.addAll(rowlist);
    }
    // �������۽������
    int[] allapprows = new int[listallapprow.size()];
    int i = 0;
    for (Integer row : listallapprow) {
      allapprows[i] = row.intValue();
      i++;
    }
    SaleOrderCalculator calcutor = new SaleOrderCalculator(this.cardPanel);
    String editkey = SaleOrderBVO.NORIGMNY;
    if (istaxprior) {
      editkey = SaleOrderBVO.NORIGTAXMNY;
    }
    calcutor.setTranTypeVO(this.getTranTypeVO());
    calcutor.calculate(allapprows, editkey);
    // ��ͷ�ϼ�
    HeadTotalCalculateRule totalrule =
        new HeadTotalCalculateRule(this.keyValue);
    totalrule.calculateHeadTotal();
  }

  public void undoLargessApportion() {
    // ���Ϸ���
    this.checkUnLargessApportion();
    // �����Ҫȡ����̯����
    List<Integer> listneedrow = this.getNeedUnApportionRows();
    // ȡ����Ʒ�۸��̯
    this.undoLargessApportion(listneedrow);
    // �������۽������
    int[] rows = new int[listneedrow.size()];
    int i = 0;
    for (Integer row : listneedrow) {
      rows[i] = row.intValue();
      i++;
    }
    boolean istaxprior = this.isTaxPrior();
    String mnyitemkey = SaleOrderBVO.NORIGMNY;
    if (istaxprior) {
      mnyitemkey = SaleOrderBVO.NORIGTAXMNY;
    }
    SaleOrderCalculator calcultor = new SaleOrderCalculator(this.cardPanel);
    calcultor.calculate(rows, mnyitemkey);
    // �ϼ�
    HeadTotalCalculateRule totalrule =
        new HeadTotalCalculateRule(this.keyValue);
    totalrule.calculateHeadTotal();
  }

  private void checkLargessApportion() {

    if (this.isSaleOrderApportion()) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0034")/*@res "�������ѽ��й���Ʒ�۸��̯���������ٴη�̯��"*/);
    }
    String ctrantypeid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    if (PubAppTool.isNull(ctrantypeid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0035")/*@res "����ѡ�������ͣ�Ȼ���ٽ�����Ʒ�۸��̯��"*/);
    }
    M30TranTypeVO trantypevo = this.getTranTypeVO();
    if (LargessDisType.NODISPART.equalsValue(trantypevo.getFlargessdistype())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0036")/*@res "������������Ϊ��������Ʒ�۸��̯��"*/);
    }
    String origcur =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    if (PubAppTool.isNull(origcur)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0037")/*@res "����ѡ����֣�Ȼ���ٽ�����Ʒ�۸��̯��"*/);
    }
    BodyValueRowRule rowrule = new BodyValueRowRule(this.keyValue);
    int[] rows = rowrule.getLargessRows();
    if (null == rows || rows.length == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0038")/*@res "������û����Ʒ�У��޷����м۸��̯��"*/);
    }
  }

  private void checkUnLargessApportion() {

    if (!this.isSaleOrderApportion()) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0039")/*@res "������δ���й���Ʒ�۸��̯������ȡ����̯��"*/);
    }
    String ctrantypeid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    if (PubAppTool.isNull(ctrantypeid)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0040")/*@res "����ѡ�������ͣ�Ȼ����ȡ����Ʒ�۸��̯��"*/);
    }
  }

  private IApportionAction getApportionActionStrategy() {

    M30TranTypeVO trantypevo = this.getTranTypeVO();

    Integer largessdistype = trantypevo.getFlargessdistype();
    UFBoolean ispricechg = trantypevo.getBlargesspriceno();

    IApportionAction apportionstrategy = null;
    // ͬ���Ϸ�̯
    if (LargessDisType.DISPARTBYINV.equalsValue(largessdistype)) {
      apportionstrategy = new ApportionForSameMaterial();
    }
    // ��Ʒ�۸��Ƿ�ı�
    else if (null != ispricechg && ispricechg.booleanValue()) {
      apportionstrategy = new ApportionForPriceUnchange();
    }
    // ��Ʒ�۸񲻱�
    else {
      apportionstrategy = new ApportionForPriceChange();
    }
    return apportionstrategy;

  }

  /**
   * ���ط�̯�������
   * 
   * @param largessdistype
   * @param largesspricenotchange
   * @return
   */
  private IApportionGroup getApportionGroupStrategy() {

    M30TranTypeVO trantypevo = this.getTranTypeVO();

    Integer largessdistype = trantypevo.getFlargessdistype();
    IApportionGroup apportionstrategy = null;
    // ͬ���Ϸ�̯
    if (LargessDisType.DISPARTBYINV.equalsValue(largessdistype)) {
      apportionstrategy = new ApportionGroupBySameMaterial();
    }
    // ������̯
    else if (LargessDisType.DISPARTBYLARGESS.equalsValue(largessdistype)) {
      apportionstrategy = new ApportionGroupByLargess();
    }
    // ������̯
    else if (LargessDisType.DISPARTONE.equalsValue(largessdistype)) {
      apportionstrategy = new ApportionGroupByOne();
    }
    return apportionstrategy;
  }

  private List<Integer> getNeedApprotRows() {
    List<Integer> listneedrow = new ArrayList<Integer>();
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      // ����Ϊ���в���̯
      String cmarterialvid =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CMATERIALVID);
      if (PubAppTool.isNull(cmarterialvid)) {
        continue;
      }
      // ����Ϊ�ջ��ߺ��ֲ���̯
      UFDouble nnum = this.keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NNUM);
      if (null == nnum || nnum.compareTo(UFDouble.ZERO_DBL) <= 0) {
        continue;
      }
      // �˻����в���̯
      Integer retexchange =
          this.keyValue.getBodyIntegerValue(i, SaleOrderBVO.FRETEXCHANGE);
      if (Fretexchange.EXCHANGE.equalsValue(retexchange)
          || Fretexchange.WITHDRAW.equalsValue(retexchange)) {
        continue;
      }
      listneedrow.add(Integer.valueOf(i));
    }
    return listneedrow;
  }

  private List<Integer> getNeedUnApportionRows() {

    List<Integer> listneedrow = new ArrayList<Integer>();
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      // ��Ʒ��̯��־
      Integer largessflag =
          this.keyValue.getBodyIntegerValue(i, SaleOrderBVO.FLARGESSTYPEFLAG);

      if (Largesstype.APPORTIONMATERIAL.equalsValue(largessflag)
          || Largesstype.APPORTIONLARGESS.equalsValue(largessflag)) {
        listneedrow.add(Integer.valueOf(i));
      }
    }
    return listneedrow;

  }

  private M30TranTypeVO getTranTypeVO() {
    if (null == this.trantypevo) {

      try {
        IM30TranTypeService service =
            NCLocator.getInstance().lookup(IM30TranTypeService.class);
        String trantypeid =
            this.keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
        this.trantypevo = service.queryTranTypeVO(trantypeid);
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return this.trantypevo;
  }

  /**
   * ���ݱ������ж϶����Ƿ��������Ʒ�۸��̯
   * 
   * @param saleordervo
   * @return
   */
  private boolean isSaleOrderApportion() {
    int bodycount = this.keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      Integer largesstypeflag =
          this.keyValue.getBodyIntegerValue(i, SaleOrderBVO.FLARGESSTYPEFLAG);

      if (Largesstype.APPORTIONMATERIAL.equalsValue(largesstypeflag)
          || Largesstype.APPORTIONLARGESS.equalsValue(largesstypeflag)) {
        return true;
      }
    }
    return false;
  }

  private boolean isTaxPrior() {

    UFBoolean so23 = null;

    String pk_group = AppContext.getInstance().getPkGroup();
    so23 = SOSysParaInitUtil.getSO23(pk_group);

    if (null == so23) {
      return false;
    }
    return so23.booleanValue();
  }

  private void undoLargessApportion(List<Integer> listneedrow) {

    for (Integer row : listneedrow) {
      int irow = row.intValue();

      Integer largessflag =
          this.keyValue
              .getBodyIntegerValue(irow, SaleOrderBVO.FLARGESSTYPEFLAG);
      if (Largesstype.APPORTIONLARGESS.equalsValue(largessflag)) {
        this.keyValue.setBodyValue(irow, SaleOrderBVO.BLARGESSFLAG,
            UFBoolean.TRUE);
      }
      else {
        this.keyValue.setBodyValue(irow, SaleOrderBVO.BLARGESSFLAG,
            UFBoolean.FALSE);
      }

      this.keyValue.setBodyValue(irow, SaleOrderBVO.FLARGESSTYPEFLAG,
          Largesstype.NOAPPORTION);

      // ԭʼ��˰�ϼ�
      UFDouble oldorigtaxmny =
          this.keyValue.getBodyUFDoubleValue(irow, SaleOrderBVO.NLARGESSTAXMNY);
      this.keyValue.setBodyValue(irow, SaleOrderBVO.NORIGTAXMNY, oldorigtaxmny);
      this.keyValue.setBodyValue(irow, SaleOrderBVO.NLARGESSTAXMNY, null);
      // ԭʼ��˰���
      UFDouble oldorigmny =
          this.keyValue.getBodyUFDoubleValue(irow, SaleOrderBVO.NLARGESSMNY);
      this.keyValue.setBodyValue(irow, SaleOrderBVO.NORIGMNY, oldorigmny);
      this.keyValue.setBodyValue(irow, SaleOrderBVO.NLARGESSMNY, null);
    }

  }
}
