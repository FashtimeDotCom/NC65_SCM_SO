package nc.vo.so.billinformation;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2017-12-25
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class BillInforMationVO extends SuperVO {
	
/**
*����
*/
public java.lang.String pk_billinformation;
/**
*��֯
*/
public java.lang.String pk_org;
/**
*��֯�汾
*/
public java.lang.String pk_org_v;
/**
*ҵ������
*/
public java.lang.String business;
/**
*��������
*/
public java.lang.String billtype;
/**
*����
*/
public java.lang.String pk_group;
/**
*��˰Ʊ��
*/
public java.lang.String taxbillno;
/**
*����
*/
public java.lang.String invname;
/**
*��˰���
*/
public java.lang.String notaxmny;
/**
*˰��
*/
public java.lang.String taxrate;
/**
*˰��
*/
public java.lang.String taxmny;
/**
*��ע
*/
public java.lang.String memo;
/**
*��Դ��ͷid
*/
public java.lang.String srchvoid;
/**
*��Դ����id
*/
public java.lang.String srcbvoid;
/**
*��Դ��������
*/
public java.lang.String srctype;
/**
*���ݺ�
*/
public java.lang.String code;
/**
*��������
*/
public UFDate dbilldate;
/**
*������
*/
public java.lang.String creator;
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*�޸���
*/
public java.lang.String modifier;
/**
*�޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*�Զ�����1
*/
public java.lang.String def1;
/**
*�Զ�����2
*/
public java.lang.String def2;
/**
*�Զ�����3
*/
public java.lang.String def3;
/**
*�Զ�����30
*/
public java.lang.String def30;
/**
*�Զ�����29
*/
public java.lang.String def29;
/**
*�Զ�����28
*/
public java.lang.String def28;
/**
*�Զ�����27
*/
public java.lang.String def27;
/**
*�Զ�����26
*/
public java.lang.String def26;
/**
*�Զ�����25
*/
public java.lang.String def25;
/**
*�Զ�����24
*/
public java.lang.String def24;
/**
*�Զ�����23
*/
public java.lang.String def23;
/**
*�Զ�����22
*/
public java.lang.String def22;
/**
*�Զ�����21
*/
public java.lang.String def21;
/**
*�Զ�����20
*/
public java.lang.String def20;
/**
*�Զ�����19
*/
public java.lang.String def19;
/**
*�Զ�����18
*/
public java.lang.String def18;
/**
*�Զ�����17
*/
public java.lang.String def17;
/**
*�Զ�����16
*/
public java.lang.String def16;
/**
*�Զ�����15
*/
public java.lang.String def15;
/**
*�Զ�����14
*/
public java.lang.String def14;
/**
*�Զ�����13
*/
public java.lang.String def13;
/**
*�Զ�����12
*/
public java.lang.String def12;
/**
*�Զ�����11
*/
public java.lang.String def11;
/**
*�Զ�����10
*/
public java.lang.String def10;
/**
*�Զ�����9
*/
public java.lang.String def9;
/**
*�Զ�����8
*/
public java.lang.String def8;
/**
*�Զ�����4
*/
public java.lang.String def4;
/**
*�Զ�����5
*/
public java.lang.String def5;
/**
*�Զ�����6
*/
public java.lang.String def6;
/**
*�Զ�����7
*/
public java.lang.String def7;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_billinformation��Getter����.������������
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getPk_billinformation() {
return this.pk_billinformation;
} 

/**
* ����pk_billinformation��Setter����.������������
* ��������:2017-12-25
* @param newPk_billinformation java.lang.String
*/
public void setPk_billinformation ( java.lang.String pk_billinformation) {
this.pk_billinformation=pk_billinformation;
} 
 
/**
* ���� pk_org��Getter����.����������֯
*  ��������:2017-12-25
* @return nc.vo.org.FinanceOrgVO
*/
public java.lang.String getPk_org() {
return this.pk_org;
} 

/**
* ����pk_org��Setter����.����������֯
* ��������:2017-12-25
* @param newPk_org nc.vo.org.FinanceOrgVO
*/
public void setPk_org ( java.lang.String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* ���� pk_org_v��Getter����.����������֯�汾
*  ��������:2017-12-25
* @return nc.vo.vorg.OrgVersionVO
*/
public java.lang.String getPk_org_v() {
return this.pk_org_v;
} 

/**
* ����pk_org_v��Setter����.����������֯�汾
* ��������:2017-12-25
* @param newPk_org_v nc.vo.vorg.OrgVersionVO
*/
public void setPk_org_v ( java.lang.String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* ���� business��Getter����.��������ҵ������
*  ��������:2017-12-25
* @return nc.vo.pf.pub.BusitypeVO
*/
public java.lang.String getBusiness() {
return this.business;
} 

/**
* ����business��Setter����.��������ҵ������
* ��������:2017-12-25
* @param newBusiness nc.vo.pf.pub.BusitypeVO
*/
public void setBusiness ( java.lang.String business) {
this.business=business;
} 
 
/**
* ���� billtype��Getter����.����������������
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getBilltype() {
return this.billtype;
} 

/**
* ����billtype��Setter����.����������������
* ��������:2017-12-25
* @param newBilltype java.lang.String
*/
public void setBilltype ( java.lang.String billtype) {
this.billtype=billtype;
} 
 
/**
* ���� pk_group��Getter����.������������
*  ��������:2017-12-25
* @return nc.vo.org.GroupVO
*/
public java.lang.String getPk_group() {
return this.pk_group;
} 

/**
* ����pk_group��Setter����.������������
* ��������:2017-12-25
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( java.lang.String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* ���� taxbillno��Getter����.����������˰Ʊ��
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getTaxbillno() {
return this.taxbillno;
} 

/**
* ����taxbillno��Setter����.����������˰Ʊ��
* ��������:2017-12-25
* @param newTaxbillno java.lang.String
*/
public void setTaxbillno ( java.lang.String taxbillno) {
this.taxbillno=taxbillno;
} 
 
/**
* ���� invname��Getter����.������������
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getInvname() {
return this.invname;
} 

/**
* ����invname��Setter����.������������
* ��������:2017-12-25
* @param newInvname java.lang.String
*/
public void setInvname ( java.lang.String invname) {
this.invname=invname;
} 
 
/**
* ���� notaxmny��Getter����.����������˰���
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getNotaxmny() {
return this.notaxmny;
} 

/**
* ����notaxmny��Setter����.����������˰���
* ��������:2017-12-25
* @param newNotaxmny java.lang.String
*/
public void setNotaxmny ( java.lang.String notaxmny) {
this.notaxmny=notaxmny;
} 
 
/**
* ���� taxrate��Getter����.��������˰��
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getTaxrate() {
return this.taxrate;
} 

/**
* ����taxrate��Setter����.��������˰��
* ��������:2017-12-25
* @param newTaxrate java.lang.String
*/
public void setTaxrate ( java.lang.String taxrate) {
this.taxrate=taxrate;
} 
 
/**
* ���� taxmny��Getter����.��������˰��
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getTaxmny() {
return this.taxmny;
} 

/**
* ����taxmny��Setter����.��������˰��
* ��������:2017-12-25
* @param newTaxmny java.lang.String
*/
public void setTaxmny ( java.lang.String taxmny) {
this.taxmny=taxmny;
} 
 
/**
* ���� memo��Getter����.����������ע
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getMemo() {
return this.memo;
} 

/**
* ����memo��Setter����.����������ע
* ��������:2017-12-25
* @param newMemo java.lang.String
*/
public void setMemo ( java.lang.String memo) {
this.memo=memo;
} 
 
/**
* ���� srchvoid��Getter����.����������Դ��ͷid
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getSrchvoid() {
return this.srchvoid;
} 

/**
* ����srchvoid��Setter����.����������Դ��ͷid
* ��������:2017-12-25
* @param newSrchvoid java.lang.String
*/
public void setSrchvoid ( java.lang.String srchvoid) {
this.srchvoid=srchvoid;
} 
 
/**
* ���� srcbvoid��Getter����.����������Դ����id
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getSrcbvoid() {
return this.srcbvoid;
} 

/**
* ����srcbvoid��Setter����.����������Դ����id
* ��������:2017-12-25
* @param newSrcbvoid java.lang.String
*/
public void setSrcbvoid ( java.lang.String srcbvoid) {
this.srcbvoid=srcbvoid;
} 
 
/**
* ���� srctype��Getter����.����������Դ��������
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getSrctype() {
return this.srctype;
} 

/**
* ����srctype��Setter����.����������Դ��������
* ��������:2017-12-25
* @param newSrctype java.lang.String
*/
public void setSrctype ( java.lang.String srctype) {
this.srctype=srctype;
} 
 
/**
* ���� code��Getter����.�����������ݺ�
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getCode() {
return this.code;
} 

/**
* ����code��Setter����.�����������ݺ�
* ��������:2017-12-25
* @param newCode java.lang.String
*/
public void setCode ( java.lang.String code) {
this.code=code;
} 
 
/**
* ���� dbilldate��Getter����.����������������
*  ��������:2017-12-25
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* ����dbilldate��Setter����.����������������
* ��������:2017-12-25
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* ���� creator��Getter����.��������������
*  ��������:2017-12-25
* @return nc.vo.sm.UserVO
*/
public java.lang.String getCreator() {
return this.creator;
} 

/**
* ����creator��Setter����.��������������
* ��������:2017-12-25
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( java.lang.String creator) {
this.creator=creator;
} 
 
/**
* ���� creationtime��Getter����.������������ʱ��
*  ��������:2017-12-25
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* ����creationtime��Setter����.������������ʱ��
* ��������:2017-12-25
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* ���� modifier��Getter����.���������޸���
*  ��������:2017-12-25
* @return nc.vo.sm.UserVO
*/
public java.lang.String getModifier() {
return this.modifier;
} 

/**
* ����modifier��Setter����.���������޸���
* ��������:2017-12-25
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( java.lang.String modifier) {
this.modifier=modifier;
} 
 
/**
* ���� modifiedtime��Getter����.���������޸�ʱ��
*  ��������:2017-12-25
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* ����modifiedtime��Setter����.���������޸�ʱ��
* ��������:2017-12-25
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* ���� def1��Getter����.���������Զ�����1
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef1() {
return this.def1;
} 

/**
* ����def1��Setter����.���������Զ�����1
* ��������:2017-12-25
* @param newDef1 java.lang.String
*/
public void setDef1 ( java.lang.String def1) {
this.def1=def1;
} 
 
/**
* ���� def2��Getter����.���������Զ�����2
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef2() {
return this.def2;
} 

/**
* ����def2��Setter����.���������Զ�����2
* ��������:2017-12-25
* @param newDef2 java.lang.String
*/
public void setDef2 ( java.lang.String def2) {
this.def2=def2;
} 
 
/**
* ���� def3��Getter����.���������Զ�����3
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef3() {
return this.def3;
} 

/**
* ����def3��Setter����.���������Զ�����3
* ��������:2017-12-25
* @param newDef3 java.lang.String
*/
public void setDef3 ( java.lang.String def3) {
this.def3=def3;
} 
 
/**
* ���� def30��Getter����.���������Զ�����30
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef30() {
return this.def30;
} 

/**
* ����def30��Setter����.���������Զ�����30
* ��������:2017-12-25
* @param newDef30 java.lang.String
*/
public void setDef30 ( java.lang.String def30) {
this.def30=def30;
} 
 
/**
* ���� def29��Getter����.���������Զ�����29
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef29() {
return this.def29;
} 

/**
* ����def29��Setter����.���������Զ�����29
* ��������:2017-12-25
* @param newDef29 java.lang.String
*/
public void setDef29 ( java.lang.String def29) {
this.def29=def29;
} 
 
/**
* ���� def28��Getter����.���������Զ�����28
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef28() {
return this.def28;
} 

/**
* ����def28��Setter����.���������Զ�����28
* ��������:2017-12-25
* @param newDef28 java.lang.String
*/
public void setDef28 ( java.lang.String def28) {
this.def28=def28;
} 
 
/**
* ���� def27��Getter����.���������Զ�����27
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef27() {
return this.def27;
} 

/**
* ����def27��Setter����.���������Զ�����27
* ��������:2017-12-25
* @param newDef27 java.lang.String
*/
public void setDef27 ( java.lang.String def27) {
this.def27=def27;
} 
 
/**
* ���� def26��Getter����.���������Զ�����26
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef26() {
return this.def26;
} 

/**
* ����def26��Setter����.���������Զ�����26
* ��������:2017-12-25
* @param newDef26 java.lang.String
*/
public void setDef26 ( java.lang.String def26) {
this.def26=def26;
} 
 
/**
* ���� def25��Getter����.���������Զ�����25
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef25() {
return this.def25;
} 

/**
* ����def25��Setter����.���������Զ�����25
* ��������:2017-12-25
* @param newDef25 java.lang.String
*/
public void setDef25 ( java.lang.String def25) {
this.def25=def25;
} 
 
/**
* ���� def24��Getter����.���������Զ�����24
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef24() {
return this.def24;
} 

/**
* ����def24��Setter����.���������Զ�����24
* ��������:2017-12-25
* @param newDef24 java.lang.String
*/
public void setDef24 ( java.lang.String def24) {
this.def24=def24;
} 
 
/**
* ���� def23��Getter����.���������Զ�����23
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef23() {
return this.def23;
} 

/**
* ����def23��Setter����.���������Զ�����23
* ��������:2017-12-25
* @param newDef23 java.lang.String
*/
public void setDef23 ( java.lang.String def23) {
this.def23=def23;
} 
 
/**
* ���� def22��Getter����.���������Զ�����22
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef22() {
return this.def22;
} 

/**
* ����def22��Setter����.���������Զ�����22
* ��������:2017-12-25
* @param newDef22 java.lang.String
*/
public void setDef22 ( java.lang.String def22) {
this.def22=def22;
} 
 
/**
* ���� def21��Getter����.���������Զ�����21
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef21() {
return this.def21;
} 

/**
* ����def21��Setter����.���������Զ�����21
* ��������:2017-12-25
* @param newDef21 java.lang.String
*/
public void setDef21 ( java.lang.String def21) {
this.def21=def21;
} 
 
/**
* ���� def20��Getter����.���������Զ�����20
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef20() {
return this.def20;
} 

/**
* ����def20��Setter����.���������Զ�����20
* ��������:2017-12-25
* @param newDef20 java.lang.String
*/
public void setDef20 ( java.lang.String def20) {
this.def20=def20;
} 
 
/**
* ���� def19��Getter����.���������Զ�����19
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef19() {
return this.def19;
} 

/**
* ����def19��Setter����.���������Զ�����19
* ��������:2017-12-25
* @param newDef19 java.lang.String
*/
public void setDef19 ( java.lang.String def19) {
this.def19=def19;
} 
 
/**
* ���� def18��Getter����.���������Զ�����18
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef18() {
return this.def18;
} 

/**
* ����def18��Setter����.���������Զ�����18
* ��������:2017-12-25
* @param newDef18 java.lang.String
*/
public void setDef18 ( java.lang.String def18) {
this.def18=def18;
} 
 
/**
* ���� def17��Getter����.���������Զ�����17
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef17() {
return this.def17;
} 

/**
* ����def17��Setter����.���������Զ�����17
* ��������:2017-12-25
* @param newDef17 java.lang.String
*/
public void setDef17 ( java.lang.String def17) {
this.def17=def17;
} 
 
/**
* ���� def16��Getter����.���������Զ�����16
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef16() {
return this.def16;
} 

/**
* ����def16��Setter����.���������Զ�����16
* ��������:2017-12-25
* @param newDef16 java.lang.String
*/
public void setDef16 ( java.lang.String def16) {
this.def16=def16;
} 
 
/**
* ���� def15��Getter����.���������Զ�����15
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef15() {
return this.def15;
} 

/**
* ����def15��Setter����.���������Զ�����15
* ��������:2017-12-25
* @param newDef15 java.lang.String
*/
public void setDef15 ( java.lang.String def15) {
this.def15=def15;
} 
 
/**
* ���� def14��Getter����.���������Զ�����14
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef14() {
return this.def14;
} 

/**
* ����def14��Setter����.���������Զ�����14
* ��������:2017-12-25
* @param newDef14 java.lang.String
*/
public void setDef14 ( java.lang.String def14) {
this.def14=def14;
} 
 
/**
* ���� def13��Getter����.���������Զ�����13
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef13() {
return this.def13;
} 

/**
* ����def13��Setter����.���������Զ�����13
* ��������:2017-12-25
* @param newDef13 java.lang.String
*/
public void setDef13 ( java.lang.String def13) {
this.def13=def13;
} 
 
/**
* ���� def12��Getter����.���������Զ�����12
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef12() {
return this.def12;
} 

/**
* ����def12��Setter����.���������Զ�����12
* ��������:2017-12-25
* @param newDef12 java.lang.String
*/
public void setDef12 ( java.lang.String def12) {
this.def12=def12;
} 
 
/**
* ���� def11��Getter����.���������Զ�����11
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef11() {
return this.def11;
} 

/**
* ����def11��Setter����.���������Զ�����11
* ��������:2017-12-25
* @param newDef11 java.lang.String
*/
public void setDef11 ( java.lang.String def11) {
this.def11=def11;
} 
 
/**
* ���� def10��Getter����.���������Զ�����10
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef10() {
return this.def10;
} 

/**
* ����def10��Setter����.���������Զ�����10
* ��������:2017-12-25
* @param newDef10 java.lang.String
*/
public void setDef10 ( java.lang.String def10) {
this.def10=def10;
} 
 
/**
* ���� def9��Getter����.���������Զ�����9
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef9() {
return this.def9;
} 

/**
* ����def9��Setter����.���������Զ�����9
* ��������:2017-12-25
* @param newDef9 java.lang.String
*/
public void setDef9 ( java.lang.String def9) {
this.def9=def9;
} 
 
/**
* ���� def8��Getter����.���������Զ�����8
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef8() {
return this.def8;
} 

/**
* ����def8��Setter����.���������Զ�����8
* ��������:2017-12-25
* @param newDef8 java.lang.String
*/
public void setDef8 ( java.lang.String def8) {
this.def8=def8;
} 
 
/**
* ���� def4��Getter����.���������Զ�����4
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef4() {
return this.def4;
} 

/**
* ����def4��Setter����.���������Զ�����4
* ��������:2017-12-25
* @param newDef4 java.lang.String
*/
public void setDef4 ( java.lang.String def4) {
this.def4=def4;
} 
 
/**
* ���� def5��Getter����.���������Զ�����5
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef5() {
return this.def5;
} 

/**
* ����def5��Setter����.���������Զ�����5
* ��������:2017-12-25
* @param newDef5 java.lang.String
*/
public void setDef5 ( java.lang.String def5) {
this.def5=def5;
} 
 
/**
* ���� def6��Getter����.���������Զ�����6
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef6() {
return this.def6;
} 

/**
* ����def6��Setter����.���������Զ�����6
* ��������:2017-12-25
* @param newDef6 java.lang.String
*/
public void setDef6 ( java.lang.String def6) {
this.def6=def6;
} 
 
/**
* ���� def7��Getter����.���������Զ�����7
*  ��������:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef7() {
return this.def7;
} 

/**
* ����def7��Setter����.���������Զ�����7
* ��������:2017-12-25
* @param newDef7 java.lang.String
*/
public void setDef7 ( java.lang.String def7) {
this.def7=def7;
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-12-25
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-12-25
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("so.BillInforMationVO");
    }
   }
    