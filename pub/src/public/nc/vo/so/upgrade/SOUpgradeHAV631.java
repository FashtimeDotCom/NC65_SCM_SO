package nc.vo.so.upgrade;

import nc.bs.sm.accountmanage.IUpdateAccount;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 *  �㰲����Ԫ����������
 * 
 * @since 6.31
 * @version 2014-8-25 ����11:14:35
 * @author quyt
 */

public class SOUpgradeHAV631 implements IUpdateAccount {
  
  @Override
  public void doBeforeUpdateData(String oldVersion, String newVersion)
      throws Exception {
    if (StringUtils.isEmpty(oldVersion)){
      // �汾��֪���Ļ����޷�������������
      return;
    }
    if (oldVersion.startsWith("6.3")){
      // ������Ʒ�Ҹ��ֶ�
      this.updateM30TrantypeBlrgcashflag();
      // ���� �����˵���ȡ�۹����ֶ�     
      this.updateM30TrantypeNaccpricerule();
    }
  }
  
  @Override
  public void doBeforeUpdateDB(String oldVersion, String newVersion)
      throws Exception {
  }


  @Override
  public void doAfterUpdateData(String oldVersion, String newVersion)
      throws Exception {
  }

  /**
   * �������۶����������ͣ�������Ʒ�Ҹ�Ĭ��ֵ
   * @throws Exception
   */
  private void updateM30TrantypeBlrgcashflag() throws Exception{
    try{
      DataAccessUtils accessutil = new DataAccessUtils();
      accessutil.update("update so_m30trantype set blrgcashflag='N' where blrgcashflag is null  and dr=0 ");
    }
    catch (Exception e){
      ExceptionUtils.marsh(e);
    }
  }
  /**
   * �������۶����������ͣ����������˵���ȡ�۹���Ĭ��ֵ
   * @throws Exception
   */
  private void updateM30TrantypeNaccpricerule() throws Exception{
    try{
      DataAccessUtils accessutil = new DataAccessUtils();
      accessutil.update("update so_m30trantype set naccpricerule=4 where naccpricerule is null  and dr=0 ");
      }
    catch (Exception e){
      ExceptionUtils.marsh(e);
    }
  }

}
