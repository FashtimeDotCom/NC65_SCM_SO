package nc.ui.so.m30.billui.largessapportion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.enumeration.Largesstype;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ͬ���Ϸ�̯�ķ�̯��ʽ
 * 
 * @since 6.0
 * @version 2011-7-13 ����01:03:08
 * @author fengjb
 */

public class ApportionForSameMaterial implements IApportionAction {

  @Override
  public void apportion(BillCardPanel cardPanel, List<Integer> rowlist,
      boolean istaxprior) {

    boolean istotalshow = cardPanel.getBodyPanel().isTatolRow();
    cardPanel.getBodyPanel().setTotalRowShow(false);

    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String mnyitemkey = SaleOrderBVO.NORIGMNY;
    if (istaxprior) {
      mnyitemkey = SaleOrderBVO.NORIGTAXMNY;
    }

    // ��ȡ��Ʒ�С����������С����ϼ�
    List<Integer> largessrows = new ArrayList<Integer>();
    List<Integer> normalrows = new ArrayList<Integer>();

    Map<Integer, UFDouble> rownummap = new HashMap<Integer, UFDouble>();

    UFDouble normalsummny = UFDouble.ZERO_DBL;
    UFDouble sumnum = UFDouble.ZERO_DBL;

    for (Integer rowindex : rowlist) {
      UFBoolean larflag =
          keyValue.getBodyUFBooleanValue(rowindex.intValue(),
              SaleOrderBVO.BLARGESSFLAG);
      UFDouble rowmny =
          keyValue.getBodyUFDoubleValue(rowindex.intValue(), mnyitemkey);
      // ��Ʒ��
      if (null != larflag && larflag.booleanValue()) {
        largessrows.add(rowindex);
      }
      else {
        normalrows.add(rowindex);
        normalsummny = MathTool.add(normalsummny, rowmny);
      }

      UFDouble num =
          keyValue.getBodyUFDoubleValue(rowindex.intValue(), SaleOrderBVO.NNUM);
      sumnum = MathTool.add(sumnum, num);
      rownummap.put(rowindex, num);
    }

    // û����Ʒ�����÷�̯
    if (largessrows.size() == 0) {
      return;
    }
    // ֻ����Ʒ�У����÷�̯
    if (normalrows.size() == 0) {
      return;
    }
    //
    if (MathTool.isZero(sumnum)) {
      return;
    }

    // ���н���̯
    Map<Integer, UFDouble> rowmnymap = new HashMap<Integer, UFDouble>();
    UFDouble price = normalsummny.div(sumnum);
    UFDouble tempsummny = UFDouble.ZERO_DBL;
    // ��ñ��־������ڿ����м����
    String corigcurr =
        keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);

    for (int i = 0; i < largessrows.size(); i++) {
      UFDouble num = rownummap.get(largessrows.get(i));
      UFDouble rowmny =
          UIScaleUtils.getScaleUtils().adjustMnyScale(num.multiply(price),
              corigcurr);
      rowmnymap.put(largessrows.get(i), rowmny);
      tempsummny = MathTool.add(tempsummny, rowmny);
    }
    Collections.sort(normalrows);

    for (int i = 0; i < normalrows.size() - 1; i++) {
      UFDouble num = rownummap.get(normalrows.get(i));
      UFDouble rowmny =
          UIScaleUtils.getScaleUtils().adjustMnyScale(num.multiply(price),
              corigcurr);
      rowmnymap.put(normalrows.get(i), rowmny);
      tempsummny = MathTool.add(tempsummny, rowmny);
    }
    // β���
    UFDouble lastrowmny = MathTool.sub(normalsummny, tempsummny);
    rowmnymap.put(normalrows.get(normalrows.size() - 1), lastrowmny);

    // ���ý��������Ʒ����̯���
    for (Integer rowindex : normalrows) {
      // ��̯ǰ��˰���
      UFDouble origmny =
          keyValue.getBodyUFDoubleValue(rowindex.intValue(),
              SaleOrderBVO.NORIGMNY);
      keyValue.setBodyValue(rowindex.intValue(), SaleOrderBVO.NLARGESSMNY,
          origmny);
      // ��̯ǰ��˰�ϼ�
      UFDouble origtaxmny =
          keyValue.getBodyUFDoubleValue(rowindex.intValue(),
              SaleOrderBVO.NORIGTAXMNY);
      keyValue.setBodyValue(rowindex.intValue(), SaleOrderBVO.NLARGESSTAXMNY,
          origtaxmny);
      // ��̯����
      keyValue.setBodyValue(rowindex.intValue(), mnyitemkey,
          rowmnymap.get(rowindex));
      // ��̯��־
      keyValue.setBodyValue(rowindex.intValue(), SaleOrderBVO.FLARGESSTYPEFLAG,
          Largesstype.APPORTIONMATERIAL.value());
    }
    for (Integer rowindex : largessrows) {
      // ��̯ǰ��˰���
      UFDouble origmny =
          keyValue.getBodyUFDoubleValue(rowindex.intValue(),
              SaleOrderBVO.NORIGMNY);
      keyValue.setBodyValue(rowindex.intValue(), SaleOrderBVO.NLARGESSMNY,
          origmny);
      // ��̯ǰ��˰�ϼ�
      UFDouble origtaxmny =
          keyValue.getBodyUFDoubleValue(rowindex.intValue(),
              SaleOrderBVO.NORIGTAXMNY);
      keyValue.setBodyValue(rowindex.intValue(), SaleOrderBVO.NLARGESSTAXMNY,
          origtaxmny);
      // ��̯����
      keyValue.setBodyValue(rowindex.intValue(), mnyitemkey,
          rowmnymap.get(rowindex));
      // ��̯��־
      keyValue.setBodyValue(rowindex.intValue(), SaleOrderBVO.FLARGESSTYPEFLAG,
          Largesstype.APPORTIONLARGESS.value());
      keyValue.setBodyValue(rowindex.intValue(), SaleOrderBVO.BLARGESSFLAG,
          UFBoolean.FALSE);
    }
    cardPanel.getBodyPanel().setTotalRowShow(istotalshow);
  }

}
