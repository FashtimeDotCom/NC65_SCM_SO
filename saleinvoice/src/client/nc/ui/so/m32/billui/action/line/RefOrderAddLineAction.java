package nc.ui.so.m32.billui.action.line;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.pf.PfAddInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.paravo.RefAddLineParaVO;

/**
 * �������۶�������
 * 
 * @since 6.0
 * @version 2011-8-20 ����08:06:08
 * @author ô��
 */
public class RefOrderAddLineAction extends AbstractRefAddLineAction {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public RefOrderAddLineAction() {
    super();
  }

  @Override
  protected RefAddLineParaVO getUserOjb(SaleInvoiceVO vo) {
    // �������ͣ��ڵ�Ϊ�������ͷ����ɵĽڵ�ʱ����ֵ���������û��ֵ
    List<String> busitypes4cto32 = new ArrayList<String>();
    PfAddInfo[] vosInfoAdd;

    try {
      // �������ͣ��ڵ�Ϊ�������ͷ����ɵĽڵ�ʱ����ֵ���������û��ֵ
      String trantype =
          TrantypeFuncUtils.getTrantype(this.getModel().getContext());
      vosInfoAdd =
          PfUtilClient.retAddInfo(SOBillType.Invoice.getCode(), trantype, this
              .getModel().getContext().getPk_group(), this.getModel()
              .getContext().getPk_loginUser(), true);

      for (PfAddInfo voInfoAdd : vosInfoAdd) {
        if (ICBillType.SaleOut.getCode().equals(voInfoAdd.getSrc_billtype())) {
          List<String> busitypes = voInfoAdd.getBusitypes();
          busitypes4cto32 = busitypes;
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
      // �������е�����
      return null;
    }
    // }

    RefAddLineParaVO userobj = new RefAddLineParaVO();
    userobj.setPk_org(vo.getParentVO().getPk_org());
    Set<String> srcbid = new HashSet<String>();
    Set<String> firstbid = new HashSet<String>();
    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) super.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    UFBoolean is30to32busitypes = UFBoolean.TRUE;

    if (busitypes4cto32.contains(vo.getParentVO().getCbiztypeid())) {
      userobj.setBusitypes(new String[] {
        vo.getParentVO().getCbiztypeid()
      });
      is30to32busitypes = UFBoolean.FALSE;
    }
    if (cachevo.getBcombinflag()) {
      MapList<String, SaleInvoiceBVO> maplist = cachevo.getCombinRela();
      Set<String> keyset = maplist.keySet();
      for (String key : keyset) {
        List<SaleInvoiceBVO> bvolist = maplist.get(key);
        for (SaleInvoiceBVO bvo : bvolist) {
          String csrcbid = bvo.getCsrcbid();
          String cfirstbid=bvo.getCfirstbid();
          if(!PubAppTool.isNull(csrcbid)){
          srcbid.add(csrcbid);
          }
          if(!PubAppTool.isNull(cfirstbid)){
          firstbid.add(cfirstbid);
          }
        }
      }
    }
    else {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        String csrcbid = bvo.getCsrcbid();
        String cfirstbid=bvo.getCfirstbid();
        if(!PubAppTool.isNull(csrcbid)){
        srcbid.add(csrcbid);
        }
        if(!PubAppTool.isNull(cfirstbid)){
        firstbid.add(cfirstbid);
        }
      }
    }

    userobj.setCfirstbids(firstbid.toArray(new String[firstbid.size()]));
    userobj.setCsrcbids(srcbid.toArray(new String[srcbid.size()]));
    userobj.setIs30to32busitypes(is30to32busitypes);
    return userobj;
  }

}
