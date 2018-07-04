package nc.pubimpl.so.m30.so.m4331.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;
import nc.vo.price.pplimitexe.SOUpdatePPLimitExePara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.util.PromoteLimitUtil;
import nc.vo.so.m30.util.RewriteProPriceUtil;
import nc.vo.so.m30.util.SaleOrderNumScaleUtil;

/**
 * 
 * @description
 * ��������д���۶����ۼƷ���������
 * @scene
 * ��������д���۶����ۼƷ��������󣬸������ۼ۸���������ִ����
 * @param
 * ��
 *
 * @since 6.5
 * @version 2014-03-13 16:22:05
 * @author zhangyfr
 */
public class RewritePriceNumRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {

    if (!SysInitGroupQuery.isPRICEEnabled()) {
      return;
    }

    // 1.��ʼ��д����
    Map<String, Rewrite4331Para> rewriteParas =
        (Map<String, Rewrite4331Para>) BSContext.getInstance().getSession(
            Rewrite4331Para.class.getName());

    List<SOUpdatePPLimitExePara> plimitParas =
        new ArrayList<SOUpdatePPLimitExePara>();
    // �����ݴ�<��Դ������ID����д����>������B2BԤ������ѯ�ӿڷ���ֵ����д������ֵ
    Map<String, SOUpdatePPLimitExePara> map =
        new HashMap<String, SOUpdatePPLimitExePara>();
    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();

      if (!body.getBboutendflag().booleanValue()
          && body.getBbsendendflag().booleanValue()) {
        UFDouble thischangenum =
            rewriteParas.get(body.getCsaleorderbid()).getNchangenum();
        // ������С���ĺ��ۼƳ�������С�ڶ��������������������ֶ������رգ������Զ������򿪣��Զ��رղŻᷢ���򿪣���״̬��������ԣ�
        // zhangby5 ���ֱ仯�������㲻�û�д������С���㲻�û�д
        boolean isAutoSendClose = this.getIsAutoSendClose(body);

        if (isAutoSendClose
            && !Fretexchange.WITHDRAW.equalsValue(body.getFretexchange())
            && MathTool.compareTo(thischangenum, UFDouble.ZERO_DBL) < 0) {
          continue;
        }
        if (isAutoSendClose
            && Fretexchange.WITHDRAW.equalsValue(body.getFretexchange())
            && MathTool.compareTo(thischangenum, UFDouble.ZERO_DBL) > 0) {
          continue;
        }
        int scale = SaleOrderNumScaleUtil.getNumPower(body);
        SOUpdatePPLimitExePara plimitPara = new SOUpdatePPLimitExePara();
        plimitPara.setBilltypecode(SOBillType.Order.getCode());
        plimitPara.setCcustomerid(head.getCcustomerid());
        plimitPara.setCpromoetpriceid(body.getCpromotpriceid());
        plimitPara.setRowID(body.getCsaleorderbid());
        plimitPara.setRowNo(body.getCrowno());
        String qtunitrate = body.getVqtunitrate();
        UFDouble ntotalsendnum =
            MathTool.add(body.getNtotalsendnum(), thischangenum);
        UFDouble nqtunittotalsendnum =
            HslParseUtil.hslDivUFDouble(qtunitrate, ntotalsendnum);
        nqtunittotalsendnum =
            nqtunittotalsendnum.setScale(scale, UFDouble.ROUND_HALF_UP);
        plimitPara.setNnum(nqtunittotalsendnum);
        // ��Դ��������Ϊ���Ӷ���'ECC1'
        if (OPCBillType.OPCORDER.getCode().equals(body.getVsrctype())) {
          plimitPara.setSrcbilltypecode(body.getVsrctype());
          String srcbic = body.getCsrcbid();
          map.put(srcbic, plimitPara);
        }
        else {
          plimitParas.add(plimitPara);
        }
      }
    }

    if (map.size() > 0) {
      RewriteProPriceUtil util = new RewriteProPriceUtil();
      plimitParas = util.setSrcParas(map);
    }
    if (plimitParas.size() > 0) {
      PromoteLimitUtil.updateExecutedNumByOpenOrClose(plimitParas
          .toArray(new SOUpdatePPLimitExePara[plimitParas.size()]));
    }

  }

  private boolean getIsAutoSendClose(SaleOrderBVO bvo) {
    if (null != bvo.getBbsendendflag()
        && bvo.getBbsendendflag().booleanValue()
        && MathTool
            .compareTo(bvo.getNnum().abs(), bvo.getNtotalsendnum().abs()) > 0) {
      // �������ֹ��ر�״̬
      return false;
    }
    return true;
  }

}
