package nc.ui.so.m30.arrange.push;

import nc.ui.ml.NCLangRes;
import nc.ui.to.m5a.billref.m30.OpenNodePushActionM5A;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;


public class OpenNodePushActionFor5A extends OpenNodePushActionM5A{

  @Override
  protected String getOrgPk() {
    if (this.isShowOrgPanel()) {
      String orgPk = this.getOrgDialog().getOrgPK();
      if(PubAppTool.isNull(orgPk)){
        ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
            "4006011_0", "04006011-0528")/*���ŵ���֯���߹�������Ϊ�ա�*/);
      }
      return orgPk;
    }
    else {
      return null;
    }
  }
  
}
