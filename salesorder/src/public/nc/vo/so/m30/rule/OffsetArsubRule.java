/**
 * $�ļ�˵��$
 *
 * @author ô��
 * @version 6.0
 * @see
 * @since
 * @time 2010-11-12 ����11:59:09
 */
package nc.vo.so.m30.rule;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ���ó�ֽӿ������ռ�
 * �������ϵ������ƬVO��
 * 
 * @since 6.0
 * @version 2010-12-10 ����10:16:01
 * @author ô��
 */
public class OffsetArsubRule {

  /** ���ڼ����ֱ����ĳ��� */
  // private static final UFDouble HUNDRES = new UFDouble(100);

  /** ����key��ú����ö�������ֵ�� */
  private IKeyValue keyValue;

  /**
   * ���췽��
   * 
   * @param keyValue ����key��ú����ö�������ֵ��
   */
  public OffsetArsubRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ���ݳ�ֵķ����ϵд������VO
   * 
   * @param distributeMap �����ϵ��������Ϊkey��ֽ��ΪValue��
   */
  public void distributeMapToVO(Map<Integer, UFDouble> distributeMap) {
    Set<Entry<Integer, UFDouble>> entrys = distributeMap.entrySet();
    for (Map.Entry<Integer, UFDouble> entry : entrys) {
      // ������ݷ���Ĺ�ϵ�ı䶩��VO
      // ��thissub��Ϊ��γ�ֽ��
      int row = entry.getKey().intValue();
      UFDouble thissubmny = entry.getValue();
      // ��origtaxmny��Ϊԭ��˰�ϼ�
      UFDouble origtaxmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGTAXMNY);
      // ���������ֽ�� = ԭ��ֽ�� + ���γ�ֽ��
      // ��oldsubmny��Ϊԭ��ֽ��
      UFDouble oldsubmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGSUBMNY);
      UFDouble submny = MathTool.add(oldsubmny, thissubmny);
      this.keyValue.setBodyValue(row, SaleOrderBVO.NORIGSUBMNY, submny);
      // ���������˰�ϼ� = ԭ��˰�ϼ� - ���γ�ֽ��
      UFDouble nowtaxmny = MathTool.sub(origtaxmny, thissubmny);
      this.keyValue.setBodyValue(row, SaleOrderBVO.NORIGTAXMNY, nowtaxmny);
    }
  }

  // /**
  // * �ռ����۷��õ�ʹ�õĽӿ���������ƴ��Ĭ��sql�Ͱ��չ�����з���
  // *
  // * @return �ӿ�vo
  // */
  // public OffsetOrUniteVO getinterfaceData() {
  // OffsetOrUniteVO offsetvo = new OffsetOrUniteVO();
  // offsetvo.setPk_org(this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG));
  // offsetvo.setPk_group(this.keyValue
  // .getHeadStringValue(SaleOrderHVO.PK_GROUP));
  // offsetvo.setInvoicecust(this.keyValue
  // .getHeadStringValue(SaleOrderHVO.CINVOICECUSTID));
  // offsetvo.setCorigcurrencyid(this.keyValue
  // .getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID));
  // Map<Integer, OffsetOrUniteBVO> bvomap =
  // new HashMap<Integer, OffsetOrUniteBVO>();
  // for (int i = 0; i < this.keyValue.getBodyCount(); i++) {
  // UFBoolean discountflag =
  // this.keyValue.getBodyUFBooleanValue(i, SaleOrderBVO.BDISCOUNTFLAG);
  // UFBoolean laborflag =
  // this.keyValue.getBodyUFBooleanValue(i, SaleOrderBVO.BLABORFLAG);
  // UFBoolean largessflag =
  // this.keyValue.getBodyUFBooleanValue(i, SaleOrderBVO.BLARGESSFLAG);
  // // �ۿۡ����������Ϻ���Ʒ�����
  // if (discountflag.booleanValue() || laborflag.booleanValue()
  // || largessflag.booleanValue()) {
  // continue;
  // }
  // // ��˰�ϼ�Ϊ�ջ�0�Ĳ����
  // UFDouble origtaxmny =
  // this.keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NORIGTAXMNY);
  // if (null == origtaxmny || origtaxmny.compareTo(UFDouble.ZERO_DBL) == 0) {
  // continue;
  // }
  // String settleorg =
  // this.keyValue.getBodyStringValue(i, SaleOrderBVO.CSETTLEORGID);
  // if (MathTool.isZero(this.getSO15(settleorg))) {
  // continue;
  // }
  // // ���ýӿ�VO��ʵ��
  // OffsetOrUniteBVO bvo = this.getBVO(i, settleorg);
  // bvomap.put(Integer.valueOf(i), bvo);
  // }
  // offsetvo.setHmbody(bvomap);
  // return offsetvo;
  // }

  /**
   * �ռ�������������
   * 
   * @param i ������
   * @param settleorg ������֯
   * @return
   */
  // private OffsetOrUniteBVO getBVO(int i, String settleorg) {
  // OffsetOrUniteBVO offsetbvo = new OffsetOrUniteBVO();
  // offsetbvo.setOrdercusts(this.keyValue
  // .getHeadStringValue(SaleOrderHVO.CCUSTOMERID));
  // offsetbvo.setOrdertrantype(this.keyValue
  // .getHeadStringValue(SaleOrderHVO.VTRANTYPECODE));
  // offsetbvo.setOrigsubmny(this.keyValue.getBodyUFDoubleValue(i,
  // SaleOrderBVO.NORIGSUBMNY));
  // offsetbvo.setOrigtaxmny(this.keyValue.getBodyUFDoubleValue(i,
  // SaleOrderBVO.NORIGTAXMNY));
  // offsetbvo.setProdline(this.keyValue.getBodyStringValue(i,
  // SaleOrderBVO.CPRODLINEID));
  // offsetbvo.setSaleorg(this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG));
  // offsetbvo.setSettleorg(settleorg);
  // return offsetbvo;
  // }

  /**
   * ���غϲ���Ʊ����
   * 
   * @return ���س�ֱ���
   */
  // private UFDouble getSO15(String settleorg) {
  // UFDouble so15 = null;
  //
  // so15 = SOSysParaInitUtil.getSO15(settleorg);
  //
  // return so15 == null ? UFDouble.ZERO_DBL :
  // so15.div(OffsetArsubRule.HUNDRES);
  // }
}
