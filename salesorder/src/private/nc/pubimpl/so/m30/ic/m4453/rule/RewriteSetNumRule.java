package nc.pubimpl.so.m30.ic.m4453.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.ic.m4c.ISaleFor4CParaQuery;
import nc.pubitf.so.m30.ic.m4453.Rewrite4453Para;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.paravo.Para4CFor32SignBiz;
import nc.vo.so.pub.util.SOVOChecker;

/**
 * 
 * @description
 * ;�𵥻�д���۶���ִ��ǰ�Ĺ�����(before)��
 * @scene
 * <p><b>�����ۼ�ǩ���������ۼ�;������
 * <p><b>��������������ԭ�����Ƿ�һ��
 * @param
 * ��
 *
 * @since 6.0
 * @version 2010-9-20 ����11:25:44
 * @author ��־ΰ
 */
public class RewriteSetNumRule implements IRule<SaleOrderViewVO> {

  private Map<String, Rewrite4453Para> index;

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {
    this.index =
        (Map<String, Rewrite4453Para>) BSContext.getInstance().getSession(
            Rewrite4453Para.class.getName());

    // ����ǩ�տ�Ʊ,�����ۼ�ǩ������������ڵ��ڶ����ۼƿ�Ʊ����
    this.checkSignBiz(vos);

    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();
      Rewrite4453Para para = this.index.get(body.getCsaleorderbid());
      // ����ǩ������
      UFDouble ntotalsignnum = body.getNtotalsignnum();
      ntotalsignnum = MathTool.add(ntotalsignnum, para.getNsignnum());
      if (MathTool.isDiffSign(ntotalsignnum, body.getNnum())) {
        String message =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0342")/*�ۼ�ǩ�����������������������෴��*/;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0340", null, new String[] {
                  head.getVbillcode(), body.getCrowno()
                })/*���۶���{0}��{1}��*/;
        ExceptionUtils.wrappBusinessException(message, location);
      }
      body.setNtotalsignnum(ntotalsignnum);

      // ����;������
      UFDouble ntranslossnum = body.getNtranslossnum();
      ntranslossnum = MathTool.add(ntranslossnum, para.getNtranslossnum());
      if (MathTool.isDiffSign(ntranslossnum, body.getNnum())) {
        String message =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0343")/*�ۼ�;�����������������������෴��*/;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0340", null, new String[] {
                  head.getVbillcode(), body.getCrowno()
                })/*���۶���{0}��{1}��*/;
        ExceptionUtils.wrappBusinessException(message, location);
      }
      body.setNtranslossnum(ntranslossnum);
    }
  }

  /**
   * ����ǩ�տ�Ʊ,�����ۼ�ǩ������������ڵ��ڶ����ۼƿ�Ʊ����
   * 
   * @param vos
   * @param index
   */
  private void checkSignBiz(SaleOrderViewVO[] vos) {
    // ��ʼ������ǩ�տ�Ʊ��ҵ������
    Para4CFor32SignBiz[] paras = new Para4CFor32SignBiz[vos.length];
    int i = 0;
    for (SaleOrderViewVO svo : vos) {
      SaleOrderHVO head = svo.getHead();
      String cbiztypeid = head.getCbiztypeid();
      String csaleorgid = head.getPk_org();
      paras[i] = new Para4CFor32SignBiz(cbiztypeid, csaleorgid);
      i++;
    }
    ISaleFor4CParaQuery pqs =
        NCLocator.getInstance().lookup(ISaleFor4CParaQuery.class);
    Para4CFor32SignBiz[] rets = new Para4CFor32SignBiz[0];
    try {
      rets = pqs.querySignNumBusitype(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    Map<String, UFBoolean> mpara = new HashMap<String, UFBoolean>();
    if (!SOVOChecker.isEmpty(rets)) {
      for (Para4CFor32SignBiz para : rets) {
        mpara.put(para.getPk_org() + para.getCbizid(), para.getIsSign());
      }
    }

    // У��
    if (!SOVOChecker.isEmpty(mpara)) {
      for (SaleOrderViewVO vo : vos) {
        SaleOrderBVO body = vo.getBody();
        SaleOrderHVO head = vo.getHead();
        Rewrite4453Para para = this.index.get(body.getCsaleorderbid());
        UFDouble ntotalsignnum = body.getNtotalsignnum();
        ntotalsignnum = MathTool.add(ntotalsignnum, para.getNsignnum());
        UFDouble ntotalinvoicenum = body.getNtotalinvoicenum();
        String cbiztypeid = head.getCbiztypeid();
        String csaleorgid = head.getPk_org();
        boolean isSign =
            ValueUtils.getBoolean(mpara.get(csaleorgid + cbiztypeid));
        if (isSign && MathTool.lessThan(ntotalsignnum, ntotalinvoicenum)) {
          ExceptionUtils
              .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                  .getStrByID("4006011_0", "04006011-0419")/*@res "����ǩ�տ�Ʊ,�����ۼ�ǩ������������ڵ��ڶ����ۼƿ�Ʊ����"*/);
        }
      }
    }

  }
}
