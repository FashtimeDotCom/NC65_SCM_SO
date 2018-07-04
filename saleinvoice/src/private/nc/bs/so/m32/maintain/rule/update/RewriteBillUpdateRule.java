package nc.bs.so.m32.maintain.rule.update;

import java.util.List;
import java.util.Map;

import nc.bs.so.m32.maintain.rule.util.RewriteBillUtil;
import nc.cmbd.vo.scmpub.res.billtype.SOBillType;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * ���۷�Ʊ���µ�ʱ��Ļ�д��Դ��Դͷ����
 * @scene
 * ���۷�Ʊ�޸ı����
 * @param
 * ��
 * @since 6.3
 * @version 2012-12-21 ����09:06:00
 * @author yaogj
 */
public class RewriteBillUpdateRule implements ICompareRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos, SaleInvoiceVO[] originVOs) {
    // ��д��Դ����
    RewriteBillUtil rewriteUtil = new RewriteBillUtil();
    // ������֯��Ϣ
    rewriteUtil.catcheOrg(vos);

    BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
    Map<String, List<RewritePara>> srcParaIndex =
        srctool.splitForUpdate(vos, originVOs);
    List<RewritePara> srcSaleOut =
        srcParaIndex.get(ICBillType.SaleOut.getCode());
    List<RewritePara> srcSaleOrder =
            srcParaIndex.get("30");
        
    if (!VOChecker.isEmpty(srcSaleOut)) {
      rewriteUtil.reWriteSrc4C(srcSaleOut);
    }
    if (!VOChecker.isEmpty(srcSaleOrder)) {
        rewriteUtil.reWriteSrc30(srcSaleOrder);
      }

  }

}
