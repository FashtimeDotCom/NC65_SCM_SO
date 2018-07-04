package nc.bs.so.m32.maintain.rule.util;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;

/**
 * �Գ巢Ʊ���������Դ����
 * 
 * @since 6.0
 * @version 2011-11-11 ����10:50:09
 * @author ô��
 */
public class UpdateSrcOppFlagUtil {

  /**
   * ������Դ���ݵ�״̬
   * 
   * @param voInvoices ��ƱVOS
   * @param oppflag ��Ҫ���³ɵı�־
   */
  public void updateSrcOppFlag(SaleInvoiceVO[] voInvoices, OpposeFlag oppflag) {

    // ���˵õ��Գ����ɵ����۷�Ʊ
    List<String> aloppid = new ArrayList<String>();
    for (SaleInvoiceVO invoice : voInvoices) {
      if (OpposeFlag.GENERAL
          .equalsValue(invoice.getParentVO().getFopposeflag())) {
        aloppid.add(invoice.getParentVO().getCopposesrcid());
      }
    }
    // û�жԳ����ɵ����۷�Ʊ,ֱ�ӷ���
    if (aloppid.size() <= 0) {
      return;
    }
    // �Գ����ɷ�Ʊ����Դ��Ʊ����
    String[] oppids = aloppid.toArray(new String[aloppid.size()]);
    // �������ݿ�����Դ��Ʊ�ĶԳ��־λ
    String[] updateKeys = new String[] {
      SaleInvoiceHVO.FOPPOSEFLAG
    };

    String[] selectKeys =
        new String[] {
          SaleInvoiceHVO.CSALEINVOICEID, SaleInvoiceHVO.FOPPOSEFLAG,
          SaleInvoiceHVO.TS
        };
    VOQuery<SaleInvoiceHVO> querysrv =
        new VOQuery<SaleInvoiceHVO>(SaleInvoiceHVO.class, selectKeys);
    SaleInvoiceHVO[] hvos = querysrv.query(oppids);
    VOConcurrentTool tool = new VOConcurrentTool();
    tool.checkTSWithDB(hvos);
    for (SaleInvoiceHVO hvo : hvos) {
      hvo.setFopposeflag((Integer) oppflag.value());
    }
    VOUpdate<SaleInvoiceHVO> bo = new VOUpdate<SaleInvoiceHVO>();
    bo.update(hvos, updateKeys);

  }
}
