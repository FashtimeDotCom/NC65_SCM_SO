package nc.vo.so.m30.arrange.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.pubitf.so.m30.pub.M30TranTypeUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.util.CombineViewToAggUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class M30ArrangeUtil {

  public SaleOrderVO[] filterSrcVOs(Object[] objs) {
    SaleOrderVO[] bills = new SaleOrderVO[objs.length];
    for (int i = 0; i < bills.length; i++) {
      bills[i] = (SaleOrderVO) objs[i];
    }
    // 1.ת����view����
    IObjectConvert<SaleOrderVO, SaleOrderViewVO> viewConvert =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    SaleOrderViewVO[] views = viewConvert.convert(bills);

    List<SaleOrderViewVO> rtnVOList = new ArrayList<SaleOrderViewVO>();
    Set<String> setid = new HashSet<String>();
    for (SaleOrderViewVO view : views) {
      setid.add(view.getHead().getCtrantypeid());
    }
    String[] tranids = new String[views.length];
    setid.toArray(tranids);
    Map<String, UFBoolean> mapdir =
        M30TranTypeUtil.getInstance().queryIsDirectPO(tranids);
    // 2.����
    for (SaleOrderViewVO view : views) {
      // --���˵������ۿ���
      boolean laborflag =
          view.getBody().getBlaborflag() == null ? false : view.getBody()
              .getBlaborflag().booleanValue();
      boolean discountflag =
          view.getBody().getBdiscountflag() == null ? false : view.getBody()
              .getBdiscountflag().booleanValue();
      if (laborflag || discountflag) {
        continue;
      }
      // --���˳���ر���
      String trantypeid = view.getHead().getCtrantypeid();
      UFBoolean isdir = mapdir.get(trantypeid);
      if (null == isdir) {
        isdir = UFBoolean.FALSE;
      }
      UFBoolean boutendflag = view.getBody().getBboutendflag();
      if (!isdir.booleanValue() && boutendflag.booleanValue()) {
        continue;
      }
      // --���˰�������
      SaleOrderBVO body = view.getBody();
      UFDouble totalArrangeNum = UFDouble.ZERO_DBL;
      // �ۼư���ί�ⶩ������
      totalArrangeNum =
          MathTool.add(totalArrangeNum, body.getNarrangescornum());
      // �ۼư����빺������
      totalArrangeNum =
          MathTool.add(totalArrangeNum, body.getNarrangepoappnum());
      // �ۼư��ŵ�����������
      totalArrangeNum =
          MathTool.add(totalArrangeNum, body.getNarrangetoornum());
      // �ۼư��ŵ�����������
      totalArrangeNum =
          MathTool.add(totalArrangeNum, body.getNarrangetoappnum());
      // �ۼư���������������
      totalArrangeNum = MathTool.add(totalArrangeNum, body.getNarrangemonum());
      // �ۼư��Ųɹ���������
      totalArrangeNum = MathTool.add(totalArrangeNum, body.getNarrangeponum());

      UFDouble nnum = body.getNnum();
      if (MathTool.absCompareTo(nnum, totalArrangeNum) > 0) {
        rtnVOList.add(view);
      }
    }

    // 3.ת��bill
    SaleOrderVO[] returnVOs =
        new CombineViewToAggUtil<SaleOrderVO>(SaleOrderVO.class,
            SaleOrderHVO.class, SaleOrderBVO.class).combineViewToAgg(
            rtnVOList.toArray(new SaleOrderViewVO[rtnVOList.size()]),
            SaleOrderHVO.CSALEORDERID);
    return returnVOs;
  }
}
