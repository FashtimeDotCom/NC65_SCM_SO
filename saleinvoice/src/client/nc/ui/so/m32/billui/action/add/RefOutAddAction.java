package nc.ui.so.m32.billui.action.add;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.ml.NCLangRes;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ�������۳��ⵥ������̳����۷�Ʊת�����������࣬�������⴦��ɸ�д��ط���
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-4-29 ����07:36:02
 */
public class RefOutAddAction extends RefAddAction {
  /**
     * 
     */
  private static final long serialVersionUID = 6994676476366579989L;
  
  @Override
  public void doAction(ActionEvent e) throws Exception {
    if(!SysInitGroupQuery.isICEnabled()){
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID("4006008_0", "04006008-0160")/*���ģ��û������,�������ÿ��ģ�飡*/);
    }
    super.doAction(e);
  }
  
  @Override
	protected void beforeTranProcessor(SaleInvoiceVO[] newvos) {
		super.beforeTranProcessor(newvos);
	}
}
