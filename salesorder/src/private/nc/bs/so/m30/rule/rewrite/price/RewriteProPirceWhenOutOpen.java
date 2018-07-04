package nc.bs.so.m30.rule.rewrite.price;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.price.pplimitexe.SOUpdatePPLimitExePara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.util.PromoteLimitUtil;
import nc.vo.so.m30.util.RewriteProPriceUtil;

/**
 * @description
 * ����򿪻�д���������۸�ִ����
 * @scene
 * �����ʱ��д���������۸�ִ����
 * @param
 * ��
 * @since 6.5
 * @version 2014-02-26 14:56:57
 * @author zhangyfr
 */
public class RewriteProPirceWhenOutOpen implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {

    List<SOUpdatePPLimitExePara> paras =
        new ArrayList<SOUpdatePPLimitExePara>();
    // �����ݴ�<��Դ������ID����д����>������B2BԤ������ѯ�ӿڷ���ֵ����д������ֵ
    Map<String, SOUpdatePPLimitExePara> map =
        new HashMap<String, SOUpdatePPLimitExePara>();
    for (SaleOrderViewVO vo : vos) {
      SaleOrderBVO bvo = vo.getBody();
      // ������жϷ���״̬����������رգ����ҷ���������Ϊ�գ����÷�������ռ��
      SOUpdatePPLimitExePara para = new SOUpdatePPLimitExePara();
      para.setBilltypecode(SOBillType.Order.getCode());
      para.setCcustomerid(vo.getHead().getCcustomerid());
      para.setCpromoetpriceid(bvo.getCpromotpriceid());
      para.setRowID(bvo.getCsaleorderbid());
      para.setRowNo(bvo.getCrowno());
      UFDouble ntotalsendnum = bvo.getNtotalsendnum();
      UFBoolean bsendendflag = bvo.getBbsendendflag();
      if (bsendendflag.booleanValue() && null != ntotalsendnum) {
        int scale = bvo.getNqtunitnum().getPower();
        String qtunitrate = bvo.getVqtunitrate();
        UFDouble nqtunittotalsendnum =
            HslParseUtil.hslDivUFDouble(qtunitrate, ntotalsendnum);
        nqtunittotalsendnum =
            nqtunittotalsendnum.setScale(scale, UFDouble.ROUND_HALF_UP);
        para.setNnum(nqtunittotalsendnum);
      }
      else {
        para.setNnum(bvo.getNqtunitnum());

      }
      // ��Դ��������Ϊ���Ӷ���'ECC1'
      if (OPCBillType.OPCORDER.getCode().equals(bvo.getVsrctype())) {
        para.setSrcbilltypecode(bvo.getVsrctype());
        String srcbic = bvo.getCsrcbid();
        map.put(srcbic, para);
      }
      else {
        paras.add(para);
      }
    }

    if (map.size() > 0) {
      RewriteProPriceUtil util = new RewriteProPriceUtil();
      paras = util.setSrcParas(map);

    }
    PromoteLimitUtil.updateExecutedNumByOpenOrClose(paras
        .toArray(new SOUpdatePPLimitExePara[paras.size()]));

  }
}
