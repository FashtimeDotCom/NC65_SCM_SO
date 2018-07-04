package nc.vo.so.m32.rule;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * ���ó�ֽӿ������ռ�
 * �������ϵ������ƬVO��
 * 
 * @since 6.0
 * @version 2010-12-10 ����01:35:46
 * @author ô��
 */
public class UniteArsubRule {

  /** ���ڼ����ֱ����ĳ��� */
  private static final UFDouble HUNDRES = new UFDouble(100);

  /** ����key��ú����ö�������ֵ�� */
  private IKeyValue keyValue;

  /**
   * ���췽��
   * 
   * @param keyValue ����key��ú����ö�������ֵ��
   */
  public UniteArsubRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ���ݺϲ���Ʊ�ķ����ϵд������VO
   * 
   * @param distributeMap �����ϵ��������Ϊkey��ֽ��ΪValue��
   */
  public void distributeMapToVO(Map<Integer, UFDouble> distributeMap) {
    for (Map.Entry<Integer, UFDouble> entry : distributeMap.entrySet()) {
      // ������ݷ���Ĺ�ϵ�ı䶩��VO
      // ��thissub��Ϊ��κϲ���Ʊ���
      int row = entry.getKey().intValue();
      UFDouble thissub = entry.getValue();
      // ��origtaxmny��Ϊԭ��˰�ϼ�
      UFDouble origtaxmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGTAXMNY);
      // ��������ϲ���Ʊ��� = ԭ�ϲ���Ʊ��� + ���κϲ���Ʊ���
      // ��oldsubmny��Ϊԭ�ϲ���Ʊ���
      UFDouble oldsubmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGSUBMNY);
      UFDouble submny = MathTool.add(oldsubmny, thissub);
      this.keyValue.setBodyValue(row, SaleInvoiceBVO.NORIGSUBMNY, submny);
      // ���������˰�ϼ� = ԭ��˰�ϼ� - ���κϲ���Ʊ���
      UFDouble nowtaxmny = MathTool.sub(origtaxmny, thissub);
      this.keyValue.setBodyValue(row, SaleInvoiceBVO.NORIGTAXMNY, nowtaxmny);
    }
  }

  // /**
  // * �ռ����۷��õ�ʹ�õĽӿ���������ƴ��Ĭ��sql�Ͱ��չ�����з���
  // *
  // * @return �ӿ�vo
  // */
  // public OffsetOrUniteVO getinterfaceData() {
  // OffsetOrUniteVO offsetvo = new OffsetOrUniteVO();
  // offsetvo.setPk_org(this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG));
  // offsetvo.setPk_group(this.keyValue
  // .getHeadStringValue(SaleInvoiceHVO.PK_GROUP));
  // offsetvo.setInvoicecust(this.keyValue
  // .getHeadStringValue(SaleInvoiceHVO.CINVOICECUSTID));
  // offsetvo.setCorigcurrencyid(this.keyValue
  // .getHeadStringValue(SaleInvoiceHVO.CORIGCURRENCYID));
  //
  // Map<Integer, OffsetOrUniteBVO> bvomap =
  // new HashMap<Integer, OffsetOrUniteBVO>();
  // if (MathTool.isZero(this.getSO15())) {
  // ExceptionUtils
  // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
  // .getStrByID("4006008_0", "04006008-0065")/*@res "��ֱ���Ϊ0��������"*/);
  // }
  // for (int i = 0; i < this.keyValue.getBodyCount(); i++) {
  // UFBoolean discountflag =
  // this.keyValue.getBodyUFBooleanValue(i, SaleInvoiceBVO.BDISCOUNTFLAG);
  // UFBoolean laborflag =
  // this.keyValue.getBodyUFBooleanValue(i, SaleInvoiceBVO.BLABORFLAG);
  // UFBoolean largessflag =
  // this.keyValue.getBodyUFBooleanValue(i, SaleInvoiceBVO.BLARGESSFLAG);
  // // �ۿۡ����������Ϻ���Ʒ���ϲ���Ʊ
  // if (discountflag.booleanValue() || laborflag.booleanValue()
  // || largessflag.booleanValue()) {
  // continue;
  // }
  // // ��˰�ϼ�Ϊ�ջ�0�Ĳ��ϲ���Ʊ
  // UFDouble origtaxmny =
  // this.keyValue.getBodyUFDoubleValue(i, SaleInvoiceBVO.NORIGTAXMNY);
  // if (null == origtaxmny || origtaxmny.compareTo(UFDouble.ZERO_DBL) == 0) {
  // continue;
  // }
  // // ���ýӿ�VO��ʵ��
  // OffsetOrUniteBVO unitebvo = this.getBVO(i);
  //
  // bvomap.put(Integer.valueOf(i), unitebvo);
  // }
  // offsetvo.setHmbody(bvomap);
  // return offsetvo;
  // }

  /**
   * �ռ����۷��õ�ʹ�õĽӿ�����
   * ����ƴ��Ĭ��sql�Ͱ��չ�����з���
   * 
   * @return
   */
  public Map<Integer, OffsetParaVO> getinterfaceDatas() {
    if (MathTool.isZero(this.getSO15())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0066")/* @res "��Ʊ��ֱ���Ϊ0" */);
    }
    Map<Integer, OffsetParaVO> offsetvomap =
        new HashMap<Integer, OffsetParaVO>();
    for (int i = 0; i < this.keyValue.getBodyCount(); i++) {
      UFBoolean discountflag =
          this.keyValue.getBodyUFBooleanValue(i, SaleInvoiceBVO.BDISCOUNTFLAG);
      UFBoolean laborflag =
          this.keyValue.getBodyUFBooleanValue(i, SaleInvoiceBVO.BLABORFLAG);
      UFBoolean largessflag =
          this.keyValue.getBodyUFBooleanValue(i, SaleInvoiceBVO.BLARGESSFLAG);
      // �ۿۡ����������Ϻ���Ʒ�����
      if (discountflag.booleanValue() || laborflag.booleanValue()
          || largessflag.booleanValue()) {
        continue;
      }
      // ��˰�ϼ�Ϊ�ջ�0�Ĳ����
      UFDouble origtaxmny =
          this.keyValue.getBodyUFDoubleValue(i, SaleInvoiceBVO.NORIGTAXMNY);
      if (null == origtaxmny || origtaxmny.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      // ���ýӿ�VO��ʵ��
      OffsetParaVO bvo = this.getBVOs(i);
      offsetvomap.put(Integer.valueOf(i), bvo);
    }
    return offsetvomap;
  }

  /**
   * �����Ƿ��Զ��ϲ���Ʊ
   * 
   * @return �����Ƿ��Զ��ϲ���Ʊ
   */
  public UFBoolean getSO14() {
    UFBoolean so14 = null;

    so14 = SOSysParaInitUtil.getSO14(this.getPk_org());

    return so14 == null ? UFBoolean.FALSE : so14;
  }

  /**
   * ���غϲ���Ʊ����
   * 
   * @return ���غϲ���Ʊ����
   */
  public UFDouble getSO15() {
    UFDouble so15 = null;

    so15 = SOSysParaInitUtil.getSO15(this.getPk_org());

    return so15 == null ? UFDouble.ZERO_DBL : so15.div(UniteArsubRule.HUNDRES);
  }

  // private OffsetOrUniteBVO getBVO(int i) {
  // OffsetOrUniteBVO bvo = new OffsetOrUniteBVO();
  // bvo.setOrdercusts(this.keyValue.getBodyStringValue(i,
  // SaleInvoiceBVO.CORDERCUSTID));
  // bvo.setOrdertrantype(this.keyValue.getBodyStringValue(i,
  // SaleInvoiceBVO.VFIRSTTRANTYPE));
  // bvo.setOrigsubmny(this.keyValue.getBodyUFDoubleValue(i,
  // SaleInvoiceBVO.NORIGSUBMNY));
  // bvo.setOrigtaxmny(this.keyValue.getBodyUFDoubleValue(i,
  // SaleInvoiceBVO.NORIGTAXMNY));
  // bvo.setProdline(this.keyValue.getBodyStringValue(i,
  // SaleInvoiceBVO.CPRODLINEID));
  // bvo.setSaleorg(this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG));
  // bvo.setSettleorg(this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG));
  // return bvo;
  // }

  private OffsetParaVO getBVOs(int i) {
    OffsetParaVO vo = new OffsetParaVO();
    // ������֯
    vo.setSaleorg(this.keyValue
        .getBodyStringValue(i, SaleInvoiceBVO.CSALEORGID));
    // ����
    vo.setPk_group(this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_GROUP));
    // ��Ʊ�ͻ�
    vo.setInvoicecusts(this.keyValue
        .getHeadStringValue(SaleInvoiceHVO.CINVOICECUSTID));
    // ����
    vo.setCorigcurrencyid(this.keyValue
        .getHeadStringValue(SaleInvoiceHVO.CORIGCURRENCYID));
    // �����ͻ�
    vo.setOrdercusts(this.keyValue.getBodyStringValue(i,
        SaleInvoiceBVO.CORDERCUSTID));
    // ������������
    vo.setOrdertrantype(this.keyValue.getBodyStringValue(i,
        SaleInvoiceBVO.VFIRSTTRANTYPE));
    vo.setOrigsubmny(this.keyValue.getBodyUFDoubleValue(i,
        SaleInvoiceBVO.NORIGSUBMNY));
    vo.setOrigtaxmny(this.keyValue.getBodyUFDoubleValue(i,
        SaleInvoiceBVO.NORIGTAXMNY));
    vo.setProdline(this.keyValue.getBodyStringValue(i,
        SaleInvoiceBVO.CPRODLINEID));
    vo.setSettleorg(this.getPk_org());
    return vo;
  }

  private String getPk_org() {
    return this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
  }

}
