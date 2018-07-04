/**
 * $�ļ�˵��$
 * 
 * @author ô��
 * @version 6.0
 * @see
 * @since
 * @time 2010-11-12 ����11:59:09
 */
package nc.vo.so.m30.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ���ó�ֽӿ������ռ�
 * �������ϵ������ƬVO��
 * 
 * @since 6.0
 * @version 2010-12-10 ����10:16:01
 * @author ô��
 */
public class OffsetItfVOUtil {

  /** ���ڼ����ֱ����ĳ��� */
  // private static final UFDouble HUNDRES = new UFDouble(100);

  /** ����key��ú����ö�������ֵ�� */
  private IKeyValue keyValue;

  /**
   * ���췽��
   * 
   * @param keyValue ����key��ú����ö�������ֵ��
   */
  public OffsetItfVOUtil(
      IKeyValue keyValue) {
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
      UFDouble submny =
          MathTool.add(oldsubmny, thissubmny);
      this.keyValue.setBodyValue(row, SaleOrderBVO.NORIGSUBMNY, submny);
      // ���������˰�ϼ� = ԭ��˰�ϼ� - ���γ�ֽ��
      UFDouble nowtaxmny = MathTool.sub(origtaxmny, thissubmny);
      this.keyValue.setBodyValue(row, SaleOrderBVO.NORIGTAXMNY, nowtaxmny);
    }
  }

  /**
   * �ռ����۷��õ�ʹ�õĽӿ���������ƴ��Ĭ��sql�Ͱ��չ�����з���
   * 
   * @return �ӿ�vo
   */
  public Map<Integer, OffsetParaVO> getinterfaceData() {
    Map<Integer, OffsetParaVO> offsetvomap =
        new HashMap<Integer, OffsetParaVO>();
    for (int i = 0; i < this.keyValue.getBodyCount(); i++) {
      UFBoolean discountflag =
          this.keyValue.getBodyUFBooleanValue(i, SaleOrderBVO.BDISCOUNTFLAG);
      UFBoolean laborflag =
          this.keyValue.getBodyUFBooleanValue(i, SaleOrderBVO.BLABORFLAG);
      UFBoolean largessflag =
          this.keyValue.getBodyUFBooleanValue(i, SaleOrderBVO.BLARGESSFLAG);
      // �ۿۡ����������Ϻ���Ʒ�����
      if (discountflag.booleanValue() || laborflag.booleanValue()
          || largessflag.booleanValue()) {
        continue;
      }
      // ��˰�ϼ�Ϊ�ջ�0�Ĳ����
      UFDouble origtaxmny =
          this.keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NORIGTAXMNY);
      if (null == origtaxmny || origtaxmny.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      String settleorg =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CSETTLEORGID);
      // if (MathTool.isZero(this.getSO15(settleorg))) {
      // continue;
      // }
      // ���ýӿ�VO��ʵ��
      OffsetParaVO bvo = this.getBVO(i, settleorg);
      offsetvomap.put(Integer.valueOf(i), bvo);
    }
    return offsetvomap;
  }

  /**
   * �ռ�������������
   * 
   * @param i ������
   * @param settleorg ������֯
   * @return
   */
  private OffsetParaVO getBVO(int i, String settleorg) {
    OffsetParaVO vo = new OffsetParaVO();
    vo.setSaleorg(this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG));
    vo.setPk_group(this.keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP));
    vo.setInvoicecusts(this.keyValue
        .getHeadStringValue(SaleOrderHVO.CINVOICECUSTID));
    vo.setCorigcurrencyid(this.keyValue
        .getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID));
    vo.setOrdercusts(this.keyValue.getHeadStringValue(SaleOrderHVO.CCUSTOMERID));
    vo.setOrdertrantype(this.keyValue
        .getHeadStringValue(SaleOrderHVO.CTRANTYPEID));
    vo.setOrigsubmny(this.keyValue.getBodyUFDoubleValue(i,
        SaleOrderBVO.NORIGSUBMNY));
    vo.setOrigtaxmny(this.keyValue.getBodyUFDoubleValue(i,
        SaleOrderBVO.NORIGTAXMNY));
    vo.setProdline(this.keyValue
        .getBodyStringValue(i, SaleOrderBVO.CPRODLINEID));
    vo.setSaleorg(this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG));
    vo.setSettleorg(settleorg);
    return vo;
  }

  /**
   * ���غϲ���Ʊ����
   * 
   * @return ���س�ֱ���
   */
  // private UFDouble getSO15(String settleorg) {
  // UFDouble so15 = null;
  // try {
  // so15 = SOSysParaInitUtil.getSO15(settleorg);
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappBusinessException(e.getMessage());
  // }
  //
  // return so15 == null ? UFDouble.ZERO_DBL :
  // so15.div(OffsetItfVOUtil.HUNDRES);
  // }
}
