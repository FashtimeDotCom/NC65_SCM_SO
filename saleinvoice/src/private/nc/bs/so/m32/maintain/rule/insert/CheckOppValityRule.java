package nc.bs.so.m32.maintain.rule.insert;

import java.util.ArrayList;
import java.util.List;

import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;
import nc.vo.so.m32.util.OpposeConvertUtil;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۷�Ʊ����ǰ���ɶԳ巢ƱУ�����
 * @scene
 * ���۷�Ʊ��������ǰ
 * @param
 * ��
 * @since 6.3
 * @version 2012-12-21 ����09:00:23
 * @author yaogj
 */
public class CheckOppValityRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    // ���˶Գ��������۷�Ʊ��ԴID
    List<String> aloppsrcid = new ArrayList<String>();
    for (SaleInvoiceVO voInvoice : vos) {
      if (OpposeFlag.GENERAL.equalsValue(voInvoice.getParentVO()
          .getFopposeflag())) {
        aloppsrcid.add(voInvoice.getParentVO().getCopposesrcid());
      }
    }
    // ��ѯ��Դ��Ʊ��Ϣ�����Գ�Ϸ���
    if (aloppsrcid.size() > 0) {
      String[] hids = aloppsrcid.toArray(new String[0]);
      String[] selKeys =
          new String[] {
            SaleInvoiceHVO.FSTATUSFLAG, SaleInvoiceHVO.FOPPOSEFLAG,
            SaleInvoiceHVO.BSUBUNITFLAG
          };
      VOQuery<SaleInvoiceHVO> querysrv =
          new VOQuery<SaleInvoiceHVO>(SaleInvoiceHVO.class, selKeys);
      SaleInvoiceHVO[] voHeads = querysrv.query(hids);
      for (SaleInvoiceHVO head : voHeads) {
        OpposeConvertUtil.getInstance().checkOpposeVality(head);
      }
    }

  }

}
