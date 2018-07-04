package nc.bs.so.upgrade;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.scmmm.upgrade.scmpub.v633.ISCMUpdateAccount;
import nc.scmmm.upgrade.scmpub.v633.V633UpgradePath;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 63������ͳһӪ��631ʱ��Ҫִ�е�������
 * 
 * @since 6.33
 * @version 2014-6-26 ����11:10:33
 * @author ��¼
 */
public class SOUpgrade63ToV63eimm implements ISCMUpdateAccount {

  @Override
  public V633UpgradePath getUpgradePath() {
    return V633UpgradePath.V63TO63EIMM;
  }

  @Override
  public void doBeforeUpdateData() throws BusinessException {
    this.updateM35BilltypeName();
    this.updatePermissionName();
    this.updateM35BillCodeRuleName();
  }

  @Override
  public void doBeforeUpdateDB() throws BusinessException {
  }

  @Override
  public void doAfterUpdateData() throws BusinessException {
    this.doAfterUpdateDataFrom63To631();
  }

  private void doAfterUpdateDataFrom63To631() throws BusinessException {

    try {
      DataAccessUtils accessutil = new DataAccessUtils();
      accessutil
          .update("update so_m35trantype set baddmanualflag='Y' where baddmanualflag is null  and dr=0 ");
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  private void updateM35BilltypeName() throws BusinessException {
    try {
      DataAccessUtils accessutil = new DataAccessUtils();
      accessutil
          .update("update bd_billtype set BILLTYPENAME='~', BILLTYPENAME2='~',BILLTYPENAME3='~',BILLTYPENAME4='~',BILLTYPENAME5='~',BILLTYPENAME6='~' where PK_BILLTYPEID='35' ");
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  private void updatePermissionName() throws BusinessException {
    try {
      DataAccessUtils accessutil = new DataAccessUtils();
      accessutil
          .update("update sm_permission_res set RESOURCENAME='~', RESOURCENAME2='~',RESOURCENAME3='~',RESOURCENAME4='~',RESOURCENAME5='~',RESOURCENAME6='~' where PK_PERMISSION_RES='1001Z810000000000NX6' ");
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
  
  /**
   * ��ԭ�������۷��õ�-���ݱ���������Ƹĳɿͻ����õ�
   * 
   * @throws BusinessException
   */
  private void updateM35BillCodeRuleName() throws BusinessException {
    try {
      DataAccessUtils accessutil = new DataAccessUtils();
      accessutil
          .update("update pub_bcr_rulebase set rulename='�ͻ����õ�' where rulecode='35InitCodeRule' and rulename = '���۷��õ�' ");
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
