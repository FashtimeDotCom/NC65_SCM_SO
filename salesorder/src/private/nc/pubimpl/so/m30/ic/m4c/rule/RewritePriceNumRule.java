package nc.pubimpl.so.m30.ic.m4c.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.price.pplimitexe.SOUpdatePPLimitExePara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.util.PromoteLimitUtil;
import nc.vo.so.m30.util.RewriteProPriceUtil;
import nc.vo.so.m30.util.SaleOrderNumScaleUtil;

/**
 * 
 * @description
 * ���۳����д���۶����ۼƳ���ر�������
 * @scene
 * ���۳����д���۶����ۼƳ���ر������󣬸������ۼ۸����������ִ����
 * @param
 * ��
 *
 * @since 6.5
 * @version 2015-10-19 ����2:28:41
 * @author zhangyfr
 */
public class RewritePriceNumRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {

    List<SOUpdatePPLimitExePara> plimitParas =
        new ArrayList<SOUpdatePPLimitExePara>();
    // �����ݴ�<��Դ������ID����д����>������B2BԤ������ѯ�ӿڷ���ֵ����д������ֵ
    Map<String, SOUpdatePPLimitExePara> map =
        new HashMap<String, SOUpdatePPLimitExePara>();
    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();

      if (body.getBboutendflag().booleanValue()) {
        int scale = SaleOrderNumScaleUtil.getNumPower(body);
        SOUpdatePPLimitExePara plimitPara = new SOUpdatePPLimitExePara();
        plimitPara.setBilltypecode(SOBillType.Order.getCode());
        plimitPara.setCcustomerid(head.getCcustomerid());
        plimitPara.setCpromoetpriceid(body.getCpromotpriceid());
        plimitPara.setRowID(body.getCsaleorderbid());
        plimitPara.setRowNo(body.getCrowno());
        String qtunitrate = body.getVqtunitrate();
        UFDouble ntotaloutnum = body.getNtotaloutnum();
        UFDouble nqtunittotaloutnum =
            HslParseUtil.hslDivUFDouble(qtunitrate, ntotaloutnum);
        nqtunittotaloutnum =
            nqtunittotaloutnum.setScale(scale, UFDouble.ROUND_HALF_UP);
        plimitPara.setNnum(nqtunittotaloutnum);
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

}
