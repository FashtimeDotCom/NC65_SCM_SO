package nc.ui.so.m32.closingcheck;

import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.actions.PrintDirectAction;

/**
 * @description
 * 
 * @scene
 * 
 * @param
 * 
 * 
 * @since 6.36
 * @version 2015-6-15 ����9:35:10
 * @author ������
 */

public class ClosePrintAction extends PrintDirectAction {

  public ClosePrintAction() {
    super();
    this.setTitle(NCLangRes.getInstance().getStrByID("4006008_0",
        "04006008-0161")/*δ�������۷�Ʊ*/);

  }

  /**
   * 
   */
  private static final long serialVersionUID = -8602469533582977665L;

  /* @Override
   protected void processHead() {

     this.setTitle(NCLangRes.getInstance().getStrByID("4006008_0", "04006008-0161")δ�������۷�Ʊ);
   }*/

}
