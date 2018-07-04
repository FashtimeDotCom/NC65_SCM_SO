package nc.bs.so.m32.maintain.rule.insert;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.rule.UniteArsubRule;
import nc.vo.so.m32.util.RemoteFormSOUtil;
import nc.vo.so.m32.util.SaleInvoiceVOCalculator;
import nc.vo.so.m32.util.SaleInvoiceVOUtil;
import nc.vo.so.m35.entity.ArsubInterfaceVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.rule.OffsetUtil;
import nc.vo.so.util.OffsetDefaltSqlUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ��������ʱ�Զ��ϲ���Ʊ����
 * </ul>
 * @scene
 * ���۷�Ʊ��������ǰ
 * @param
 * ��
 * @version ���汾��6.0
 * @author fengjb
 * @time 2010-1-19 ����04:01:56
 */
public class AutoUniteInvoiceRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] invoices) {
    IKeyValue keyValue = null;
    UniteArsubRule uniteRule = null;
    try {
      for (SaleInvoiceVO voInvoice : invoices) {
        keyValue = new VOKeyValue<SaleInvoiceVO>(voInvoice);
        uniteRule = new UniteArsubRule(keyValue);
        UFBoolean so14 = uniteRule.getSO14();
        UFDouble so15 = uniteRule.getSO15();
        UFBoolean subflag = voInvoice.getParentVO().getBsubunitflag();
        if ((VOChecker.isEmpty(subflag) || !subflag.booleanValue())
            && so14.booleanValue() && so15.compareTo(UFDouble.ZERO_DBL) > 0) {

          String pk_group = voInvoice.getParentVO().getPk_group();
          Map<Integer, OffsetParaVO> itfvo = uniteRule.getinterfaceDatas();
          if (null == itfvo || itfvo.size() == 0) {
            continue;
          }
          OffsetUtil interfacerule = new OffsetUtil(pk_group, itfvo);

          OffsetDefaltSqlUtil sqlutil = new OffsetDefaltSqlUtil();
          String defaultwhere = sqlutil.getInvoiceDefaultSql(pk_group, itfvo);
          // ������
          Map<Integer, UFDouble> dismap =
              interfacerule.autoOffsetArsub(defaultwhere);
          if (null == dismap || dismap.size() == 0) {
            continue;
          }
          this.distributeMapToVO(dismap, voInvoice);
          Integer[] changerows = dismap.keySet().toArray(new Integer[0]);
          this.doafter(changerows, voInvoice);

          // ��д���۷��õ��ͳ�ֹ�ϵ
          SaleInvoiceVOUtil voutil = new SaleInvoiceVOUtil();
          SaleInvoiceVO[] rets = new SaleInvoiceVO[] {
            voInvoice
          };
          ArsubInterfaceVO[] arsubvo = voutil.changeToArsubInterfaceVO(rets);
          OffsetTempVO temvo = new OffsetTempVO();
          temvo.setHmArsubRelation(interfacerule.getNewRelation(null));

          OffsetTempVO[] tempvos = new OffsetTempVO[] {
            temvo
          };
          RemoteFormSOUtil.writeArsubRelation(arsubvo, tempvos);
          // ��д���۷��õ��ͳ�ֹ�ϵ����
        }
      }
    }
    catch (BusinessException ex) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0014")/* @res "��д���۷��õ��ͳ�ֹ�ϵ����" */);
    }

  }

  private void distributeMapToVO(Map<Integer, UFDouble> distributeMap,
      SaleInvoiceVO voInvoice) {
    UFDouble totalsubmny = new UFDouble(0);
    for (Map.Entry<Integer, UFDouble> entry : distributeMap.entrySet()) {
      // ������ݷ���Ĺ�ϵ�ı䶩��VO
      // ��thissub��Ϊ��κϲ���Ʊ���
      int row = entry.getKey().intValue();
      UFDouble thissub = entry.getValue();
      totalsubmny = totalsubmny.add(thissub);
      SaleInvoiceBVO bvo = voInvoice.getChildrenVO()[row];
      // ��origtaxmny��Ϊԭ��˰�ϼ�
      UFDouble origtaxmny = bvo.getNorigtaxmny();
      // this.keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGTAXMNY);
      // ��������ϲ���Ʊ��� = ԭ�ϲ���Ʊ��� + ���κϲ���Ʊ���
      // ��oldsubmny��Ϊԭ�ϲ���Ʊ���

      UFDouble oldsubmny = bvo.getNorigsubmny();
      UFDouble submny = MathTool.add(oldsubmny, thissub);
      bvo.setNorigsubmny(submny);
      // this.keyValue.setBodyValue(row, SaleInvoiceBVO.NORIGSUBMNY, submny);
      // ���������˰�ϼ� = ԭ��˰�ϼ� - ���κϲ���Ʊ���
      UFDouble nowtaxmny = MathTool.sub(origtaxmny, thissub);
      bvo.setNorigtaxmny(nowtaxmny);
      // this.keyValue.setBodyValue(row, SaleInvoiceBVO.NORIGTAXMNY, nowtaxmny);
    }
    UFDouble oldtotalsubmny = voInvoice.getParentVO().getNtotalorigsubmny();
    UFDouble oldtotaltaxmny = voInvoice.getParentVO().getNtotalorigmny();
    voInvoice.getParentVO().setNtotalorigsubmny(
        MathTool.add(oldtotalsubmny, totalsubmny));
    voInvoice.getParentVO().setNtotalorigmny(
        MathTool.sub(oldtotaltaxmny, totalsubmny));
    voInvoice.getParentVO().setBsubunitflag(UFBoolean.TRUE);

  }

  private void doafter(Integer[] changerows, SaleInvoiceVO voInvoice) {
    // �����������۽�ʼ
    Integer[] intchangerows = new Integer[changerows.length];
    for (int i = 0; i < changerows.length; i++) {
      intchangerows[i] = changerows[i];
    }
    SaleInvoiceVOCalculator voculator = new SaleInvoiceVOCalculator(voInvoice);
    voculator.calculate(intchangerows, SaleInvoiceBVO.NORIGTAXMNY);
    // �����������۽�����

  }

}
