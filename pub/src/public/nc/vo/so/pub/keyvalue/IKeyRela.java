package nc.vo.so.pub.keyvalue;

/**
 * Ϊʹ���������Ϲ淶�ĵ�����ʹ�ù������򣬽��������ֶε�ӳ���ϵ�ӿ�
 * 
 * @since 6.0
 * @version 2012-2-6 ����08:54:18
 * @author fengjb
 */
public interface IKeyRela {

  // ----------------���ݱ�׼�ֶ�----------------------------//
  /** ���� */
  String getPk_groupKey();

  void setPk_groupKey(String key);

  /** ����֯ */
  String getPk_orgKey();

  void setPk_orgKey(String key);
  
  /** ����֯_V */
  String getPk_org_vKey();

  void setPk_org_vKey(String key);

  /** ҵ������ */
  String getCbiztypeidKey();

  void setCbiztypeidKey(String key);

  /** �������ͱ��� */
  String getVtrantypecodeKey();

  void setVtrantypecodeKey(String key);

  /** �������� */
  String getCtrantypeidKey();

  void setCtrantypeidKey(String key);

  /** ���ݺ� */
  String getVbillcodeKey();

  void setVbillcodeKey(String key);

  /** �������� */
  String getDbilldateKey();

  void setDbilldateKey(String key);

  /** ����״̬ */
  String getFstatusflagKey();

  void setFstatusflagKey(String key);

  /** ������״̬ */
  String getFpfstatusflagKey();

  void setFpfstatusflagKey(String key);

  /** �Ƶ��� */
  String getBillmakerKey();

  void setBillmakerKey(String key);

  /** �Ƶ����� */
  String getDmakedateKey();

  void setDmakedateKey(String key);

  /** ������ */
  String getCreatorKey();

  void setCreatorKey(String key);

  /** ����ʱ�� */

  String getCreationtimeKey();

  void setCreationtimeKey(String key);

  /** �޸��� */
  String getModifierKey();

  void setModifierKey(String key);

  /** �޸�ʱ�� */
  String getModifiedtimeKey();

  void setModifiedtimeKey(String key);

  /** ������ */
  String getApproverKey();

  void setApproverKey(String key);

  /** ����ʱ�� */

  String getTaudittimeKey();

  void setTaudittimeKey(String key);

  /** �ϼ����� */
  String getNtotalnumKey();

  void setNtotalnumKey(String key);

  /** ���ϼ� */
  String getNtotalorigmnyKey();

  void setNtotalorigmnyKey(String key);

  /** ɾ����־ */
  String getDrKey();

  void setDrKey(String key);

  // ----------------���֡��ͻ������š����С����㷽ʽ�����������ֶ�----------------------------//
  /** ԭ�� */
  String getCorigcurrencyidKey();

  void setCorigcurrencyidKey(String key);

  /** �����ͻ� */
  String getCcustomeridKey();

  void setCcustomeridKey(String key);

  /** ��Ʊ�ͻ� */
  String getCinvoicecustidKey();

  void setCinvoicecustidKey(String key);

  /** ɢ�� */
  String getCfreecustidKey();

  void setCfreecustidKey(String key);

  /** ���� ���°汾 */
  String getCdeptidKey();

  void setCdeptidKey(String key);

  /** ���� */
  String getCdeptvidKey();

  void setCdeptvidKey(String key);

  /** ҵ��Ա */
  String getCemployeeidKey();

  void setCemployeeidKey(String key);

  /** ���㷽ʽ */
  String getCbalancetypeidKey();

  void setCbalancetypeidKey(String key);

  /** ������������ */
  String getCchanneltypeidKey();

  void setCchanneltypeidKey(String key);

  /** �������� */
  String getCcustbankidKey();

  void setCcustbankidKey(String key);

  /** ���������˻� */
  String getCcustbankaccidKey();

  void setCcustbankaccidKey(String key);

  /** �տ�Э�� */
  String getCpaytermidKey();

  void setCpaytermidKey(String key);

  /** ���䷽ʽ */
  String getCtransporttypeidKey();

  void setCtransporttypeidKey(String key);

  // ----------------��������ֶ�----------------------------//
  /** �к� */
  String getCrownoKey();

  void setCrownoKey(String key);

  /** ���ϰ汾 */
  String getCmaterialvidKey();

  void setCmaterialvidKey(String key);

  /** �������°汾 */
  String getCmaterialidKey();

  void setCmaterialidKey(String key);

  /** ��Ʒ�� */
  String getCprodlineidKey();

  void setCprodlineidKey(String key);

  /** �����־ */
  String getBlaborflagKey();

  void setBlaborflagKey(String key);

  /** �ۿ۱�־ */
  String getBdiscountflagKey();

  void setBdiscountflagKey(String key);

  /** ��Ʒ��־ */
  String getBlargessflagKey();

  void setBlargessflagKey(String key);

  /** �������� */
  String getCproductoridKey();

  void setCproductoridKey(String key);

  /** ��Ŀ */
  String getCprojectidKey();

  void setCprojectidKey(String key);

  /** ��Ӧ�� */
  String getCvendoridKey();

  void setCvendoridKey(String key);

  /** ���� */
  String getCfactoryidKey();

  void setCfactoryidKey(String key);

  /** �����ȼ� */
  String getCqualitylevelidKey();

  void setCqualitylevelidKey(String key);

  // ----------------���ɸ�������----------------------------//
  /** ���ɸ�������1 */
  String getVfree1Key();

  void setVfree1Key(String key);

  /** ���ɸ�������2 */
  String getVfree2Key();

  void setVfree2Key(String key);

  /** ���ɸ�������3 */
  String getVfree3Key();

  void setVfree3Key(String key);

  /** ���ɸ�������4 */
  String getVfree4Key();

  void setVfree4Key(String key);

  /** ���ɸ�������5 */
  String getVfree5Key();

  void setVfree5Key(String key);

  /** ���ɸ�������6 */
  String getVfree6Key();

  void setVfree6Key(String key);

  /** ���ɸ�������7 */
  String getVfree7Key();

  void setVfree7Key(String key);

  /** ���ɸ�������8 */
  String getVfree8Key();

  void setVfree8Key(String key);

  /** ���ɸ�������9 */
  String getVfree9Key();

  void setVfree9Key(String key);

  /** ���ɸ�������10 */
  String getVfree10Key();

  void setVfree10Key(String key);

  // ----------------�������۽���ֶ�----------------------------//
  /** ����λ */
  String getCunitidKey();

  void setCunitidKey(String key);

  /** ��λ */
  String getCastunitidKey();

  void setCastunitidKey(String key);

  /** ������ */
  String getVchangerateKey();

  void setVchangerateKey(String key);

  /** ���۵�λ */
  String getCqtunitidKey();

  void setCqtunitidKey(String key);

  /** ���ۻ����� */
  String getVqtunitrateKey();

  void setVqtunitrateKey(String key);

  /** ������ */
  String getNnumKey();

  void setNnumKey(String key);

  /** ���� */
  String getNastnumKey();

  void setNastnumKey(String key);

  /** �������� */
  String getNqtunitnumKey();

  void setNqtunitnumKey(String key);

  /** ˰�� */
  String getNtaxrateKey();

  void setNtaxrateKey(String key);

  /** �����ۿ� */
  String getNdiscountrateKey();

  void setNdiscountrateKey(String key);

  /** ��Ʒ�ۿ� */
  String getNitemdiscountrateKey();

  void setNitemdiscountrateKey(String key);

  /** ��λ�� */
  String getCcurrencyidKey();

  void setCcurrencyidKey(String key);

  /** �۱����� */
  String getNexchangerateKey();

  void setNexchangerateKey(String key);

  /** ��˰���� */
  String getNqtorigtaxpriceKey();

  void setNqtorigtaxpriceKey(String key);

  /** ��˰���� */
  String getNqtorigpriceKey();

  void setNqtorigpriceKey(String key);

  /** ��˰���� */
  String getNqtorigtaxnetprcKey();

  void setNqtorigtaxnetprcKey(String key);

  /** ��˰���� */
  String getNqtorignetpriceKey();

  void setNqtorignetpriceKey(String key);

  /** ����λ��˰���� */
  String getNorigtaxpriceKey();

  void setNorigtaxpriceKey(String key);

  /** ����λ��˰���� */
  String getNorigpriceKey();

  void setNorigpriceKey(String key);

  /** ����λ��˰���� */
  String getNorigtaxnetpriceKey();

  void setNorigtaxnetpriceKey(String key);

  /** ����λ��˰���� */
  String getNorignetpriceKey();

  void setNorignetpriceKey(String key);

  /** ��˰��� */
  String getNorigmnyKey();

  void setNorigmnyKey(String key);

  /** ��˰�ϼ� */
  String getNorigtaxmnyKey();

  void setNorigtaxmnyKey(String key);

  /** �ۿ۶� */
  String getNorigdiscountKey();

  void setNorigdiscountKey(String key);

  /** ���Һ�˰���� */
  String getNqttaxpriceKey();

  void setNqttaxpriceKey(String key);

  /** ������˰���� */
  String getNqtpriceKey();

  void setNqtpriceKey(String key);

  /** ���Һ�˰���� */
  String getNqttaxnetpriceKey();

  void setNqttaxnetpriceKey(String key);

  /** ������˰���� */
  String getNqtnetpriceKey();

  void setNqtnetpriceKey(String key);

  /** �����Һ�˰���� */
  String getNtaxpriceKey();

  void setNtaxpriceKey(String key);

  /** ��������˰���� */
  String getNpriceKey();

  void setNpriceKey(String key);

  /** �����Һ�˰���� */
  String getNtaxnetpriceKey();

  void setNtaxnetpriceKey(String key);

  /** ��������˰���� */
  String getNnetpriceKey();

  void setNnetpriceKey(String key);

  /** ˰�� */
  String getNtaxKey();

  void setNtaxKey(String key);

  /** ������˰��� */
  String getNmnyKey();

  void setNmnyKey(String key);

  /** ���Ҽ�˰�ϼ� */
  String getNtaxmnyKey();

  void setNtaxmnyKey(String key);

  /** �����ۿ۶� */
  String getNdiscountKey();

  void setNdiscountKey(String key);

  // ----------------���š�ȫ��----------------------------
  /** ȫ���۱����� */
  String getNglobalexchgrateKey();

  void setNglobalexchgrateKey(String key);

  /** ȫ�ֱ�����˰��� */
  String getNglobalmnyKey();

  void setNglobalmnyKey(String key);

  /** ȫ�ֱ��Ҽ�˰�ϼ� */
  String getNglobaltaxmnyKey();

  void setNglobaltaxmnyKey(String key);

  /** �����۱����� */
  String getNgroupexchgrateKey();

  void setNgroupexchgrateKey(String key);

  /** ���ű�����˰��� */
  String getNgroupmnyKey();

  void setNgroupmnyKey(String key);

  /** ���ű��Ҽ�˰�ϼ� */
  String getNgrouptaxmnyKey();

  void setNgrouptaxmnyKey(String key);

  // ----------------ѯ�۵���----------------------------
  /** ѯ��ԭ�Һ�˰���� */
  String getNaskqtorigtaxprcKey();

  void setNaskqtorigtaxprcKey(String key);

  /** ѯ��ԭ�Һ�˰���� */
  String getNaskqtorigtxntprcKey();

  void setNaskqtorigtxntprcKey(String key);

  /** ѯ��ԭ����˰���� */
  String getNaskqtorignetpriceKey();

  void setNaskqtorignetpriceKey(String key);

  /** ѯ��ԭ����˰���� */
  String getNaskqtorigpriceKey();

  void setNaskqtorigpriceKey(String key);

  // ----------------ѯ���ֶ�----------------------------//
  /** �۸���� */
  String getCpriceformidKey();

  void setCpriceformidKey(String key);

  /** �۸��� */
  String getCpriceitemidKey();

  void setCpriceitemidKey(String key);

  /** ��Ŀ�� */
  String getCpriceitemtableidKey();

  void setCpriceitemtableidKey(String key);

  /** ���۲��� */
  String getCpricepolicyidKey();

  void setCpricepolicyidKey(String key);

  // ----------------�ջ���Ϣ�ֶ�----------------------------//

  /** �������� */
  String getDreceivedateKey();

  void setDreceivedateKey(String key);

  /** �������� */
  String getDsenddateKey();

  void setDsenddateKey(String key);

  /** �ջ��ͻ� */
  String getCreceivecustidKey();

  void setCreceivecustidKey(String key);

  /** �ջ��ص� */
  String getCreceiveadddocidKey();

  void setCreceiveadddocidKey(String key);

  /** �ջ���ַ */
  String getCreceiveaddridKey();

  void setCreceiveaddridKey(String key);

  /** �ջ����� */
  String getCreceiveareaidKey();

  void setCreceiveareaidKey(String key);

  // ----------------��֯��Ϣ�ֶ�----------------------------//
  /** ������֯���°汾 */
  String getCsaleorgidKey();

  void setCsaleorgidKey(String key);

  /** ������֯ */
  String getCsaleorgvidKey();

  void setCsaleorgvidKey(String key);
  
  /** ������֯���°汾 */
  String getCtrafficorgidKey();

  void setCtrafficorgidKey(String key);

  /** ������֯ */
  String getCtrafficorgvidKey();

  void setCtrafficorgvidKey(String key);

  /** ���������֯���°汾 */
  String getCsendstockorgidKey();

  void setCsendstockorgidKey(String key);

  /** ���������֯ */
  String getCsendstockorgvidKey();

  void setCsendstockorgvidKey(String key);

  /** �����ֿ� */
  String getCsendstordocidKey();

  void setCsendstordocidKey(String key);

  /** ���������֯���°汾 */
  String getCsettleorgidKey();

  void setCsettleorgidKey(String key);

  /** ���������֯ */
  String getCsettleorgvidKey();

  void setCsettleorgvidKey(String key);

  /** Ӧ����֯ ���°汾 */
  String getCarorgidKey();

  void setCarorgidKey(String key);

  /** Ӧ����֯ */
  String getCarorgvidKey();

  void setCarorgvidKey(String key);

  /** �����������°汾 */
  String getCprofitcenteridKey();

  void setCprofitcenteridKey(String key);

  /** �������� */
  String getCprofitcentervidKey();

  void setCprofitcentervidKey(String key);

  // ----------------Դͷ������Ϣ�ֶ�----------------------------//
  /** Դͷ������id */
  String getCfirstbidKey();

  void setCfirstbidKey(String key);

  /** ��״̬ */
  String getFrowstatusKey();

  void setFrowstatusKey(String key);

  /** ��Ʒ�۸��̯��ʽ */
  String getFlargesstypeflagKey();

  void setFlargesstypeflagKey(String key);

  // ----------------V61�����ֶ�----------------------------//
  /** �ջ�����/���� */
  String getCrececountryidKey();

  void setCrececountryidKey(String key);

  /** ��������/���� */
  String getCsendcountryidKey();

  void setCsendcountryidKey(String key);

  /** ��˰����/���� */
  String getCtaxcountryidKey();

  void setCtaxcountryidKey(String key);

  /** ����ó�� */
  String getBtriatradeflagKey();

  void setBtriatradeflagKey(String key);

  /** ԭ������ */
  String getCorigareaidKey();

  void setCorigareaidKey(String key);

  /** ԭ���� */
  String getCorigcountryidKey();

  void setCorigcountryidKey(String key);

  /** ó������ */
  String getCtradewordidKey();

  void setCtradewordidKey(String key);

  /** ˰�� */
  String getCtaxcodeidKey();

  void setCtaxcodeidKey(String key);

  /** ��˰��� */
  String getFtaxtypeflagKey();

  void setFtaxtypeflagKey(String key);

  /** �������� */
  String getFbuysellflagKey();

  void setFbuysellflagKey(String key);

  /** ��˰��� */
  String getNcaltaxmnyKey();

  void setNcaltaxmnyKey(String key);

}
