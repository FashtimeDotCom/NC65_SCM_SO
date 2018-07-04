package nc.ui.so.m32.billui.action.line;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.ml.NCLangRes;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.RefAddLineParaVO;

/**
 * �������۳��ⵥ����
 * 
 * @since 6.0
 * @version 2011-8-20 ����08:05:51
 * @author ô��
 */
public class RefOutAddLineAction extends AbstractRefAddLineAction {

  private static final long serialVersionUID = -2679018295054427297L;

  public RefOutAddLineAction() {
    super();
  }
  
  @Override
  public void doAction(ActionEvent e) throws Exception {
    if(!SysInitGroupQuery.isICEnabled()){
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID("4006008_0", "04006008-0160")/*���ģ��û������,�������ÿ��ģ�飡*/);
    } 
    super.doAction(e);
  }

  @Override
  protected RefAddLineParaVO getUserOjb(SaleInvoiceVO vo) {
    RefAddLineParaVO userobj = new RefAddLineParaVO();
    userobj.setPk_org(vo.getParentVO().getPk_org());
    Set<String> srcbid = new HashSet<String>();
    Set<String> srcid = new HashSet<String>();
    Set<String> firstbid = new HashSet<String>();
    Set<String> firstid = new HashSet<String>();
    SaleInvoiceBVO[] bvos = vo.getChildrenVO();
    for (SaleInvoiceBVO bvo : bvos) {
      srcbid.add(bvo.getCsrcbid());
      srcid.add(bvo.getCsrcid());
      firstid.add(bvo.getCfirstid());
      firstbid.add(bvo.getCfirstbid());
    }
    userobj.setCfirstbids(firstbid.toArray(new String[firstbid.size()]));
    userobj.setCfirstids(firstid.toArray(new String[firstid.size()]));
    userobj.setCsrcids(srcid.toArray(new String[srcid.size()]));
    userobj.setCsrcbids(srcbid.toArray(new String[srcbid.size()]));
    return userobj;

  }

}
