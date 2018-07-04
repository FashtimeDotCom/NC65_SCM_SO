package nc.pubimpl.so.m32.ic.m4c.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

import nc.pubitf.so.m32.ic.m4c.RewritePara32For4C;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۳��ⵥ��д���۷�Ʊ������������
 * @scene
 * ���۳��ⵥ���������仯ʱ(��Ʊ����ȳ���)
 * @param
 * ��
 * @since 6.3
 * @version 2012-12-21 ����10:38:01
 * @author yaogj
 */
public class RewriteOutNumRule implements IRule<SaleInvoiceViewVO> {

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleInvoiceViewVO[] vos) {

    Map<String, RewritePara32For4C> mappara =
        (Map<String, RewritePara32For4C>) BSContext.getInstance().getSession(
            RewritePara32For4C.class.getName());

    this.check32TotalOutNum(vos, mappara);
    this.setTotalOutNum(vos, mappara);
  }

  /**
   * ��������������������۷�Ʊ�ۼƳ������� <= ���۷�Ʊ������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   *          <p>
   * @author ��ӱ�
   * @param mappara
   * @time 2010-3-24 ����02:14:37
   */
  private void check32TotalOutNum(SaleInvoiceViewVO[] viewvos,
      Map<String, RewritePara32For4C> mappara) {

    Map<String, List<String>> mapOverRows = new HashMap<String, List<String>>();
    RewritePara32For4C para = null;
    for (SaleInvoiceViewVO view : viewvos) {
      SaleInvoiceHVO head = view.getHead();
      SaleInvoiceBVO body = view.getItem();

      para = mappara.get(body.getCsaleinvoicebid());

      UFDouble outNum =
          MathTool.add(body.getNtotaloutnum(), para.getNoutnum()).add(
              para.getNshouldoutnum());

      UFDouble invoicenum = body.getNnum();

      if (MathTool.absCompareTo(outNum, invoicenum) > 0) {
        String billcode = head.getVbillcode();
        if (mapOverRows.containsKey(billcode)) {
          mapOverRows.get(billcode).add(body.getCrowno());
        }
        else {
          List<String> rownos = new ArrayList<String>();
          rownos.add(body.getCrowno());
          mapOverRows.put(billcode, rownos);
        }
      }
    }
    if (mapOverRows.size() > 0) {
      StringBuilder message = new StringBuilder();
      for (Entry<String, List<String>> entry : mapOverRows.entrySet()) {
        String billcode = entry.getKey();
        StringBuilder strrow = new StringBuilder();
        for (String rowno : entry.getValue()) {
          strrow.append("[").append(rowno).append("]"); /* -=notranslate=- */
        }
        message.append(NCLangResOnserver.getInstance().getStrByID("4006008_0",
            "04006008-0116", null, new String[] {
              billcode, strrow.toString()
            })/*���۷�Ʊ{0}������{1}�ۼƳ����������ڿ�Ʊ����*/);
        message.append("/n");
      }
      ExceptionUtils.wrappBusinessException(message.toString());
    }

  }

  /**
   * 
   * ��������������д�����۷�Ʊ�ۼƳ���������
   * <p>
   * <b>����˵��</b>
   * 
   * @param viewvos
   * @param mappara
   *          <p>
   * @author fengjb
   * @time 2010-9-1 ����10:23:06
   */
  private void setTotalOutNum(SaleInvoiceViewVO[] viewvos,
      Map<String, RewritePara32For4C> mappara) {
    RewritePara32For4C para = null;
    for (SaleInvoiceViewVO view : viewvos) {
      SaleInvoiceBVO body = view.getItem();

      para = mappara.get(body.getCsaleinvoicebid());
      // д������
      UFDouble shouldnum =
          MathTool.add(body.getNshouldoutnum(), para.getNshouldoutnum());
      body.setNshouldoutnum(shouldnum);
      UFDouble totaloutnum =
          MathTool.add(body.getNtotaloutnum(), para.getNoutnum());
      body.setNtotaloutnum(totaloutnum);
    }
  }
}
