package nc.vo.so.m30.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * ���۶�������VO
 * 
 * @since 6.0
 * @version 2011-6-10 ����01:50:34
 * @author fengjb
 */

public class SaleOrderHVO extends SuperVO {

	/**
	 * ��Ʒ�Ҹ�����
	 */
	public static final String CARSUBTYPEID = "carsubtypeid";

	/**
	 * ������Ʒ�Ҹ�����
	 * 
	 * @param carsubtypeid
	 * 
	 */
	public void setCarsubtypeid(String carsubtypeid) {
		this.setAttributeValue(SaleOrderHVO.CARSUBTYPEID, carsubtypeid);
	}

	/**
	 * ��ȡ��Ʒ�Ҹ�����
	 * 
	 * @return ��Ʒ�Ҹ�����
	 */
	public String getCarsubtypeid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CARSUBTYPEID);
	}

	/**
	 * ������
	 */
	public static final String APPROVER = "approver";

	/**
	 * �����˷�
	 */
	public static final String BADVFEEFLAG = "badvfeeflag";

	/**
	 * �������ر�
	 */
	public static final String BARSETTLEFLAG = "barsettleflag";

	/**
	 * ��Эͬ���ɲɹ�����
	 */
	public static final String BCOOPTOPOFLAG = "bcooptopoflag";

	/**
	 * �ɱ�����ر�
	 */
	public static final String BCOSTSETTLEFLAG = "bcostsettleflag";

	/**
	 * �Ƿ�ɢ��
	 */
	public static final String BFREECUSTFLAG = "bfreecustflag";

	/**
	 * �Ƶ���
	 */
	public static final String BILLMAKER = "billmaker";

	/**
	 * ��Ʊ�ر�
	 */
	public static final String BINVOICENDFLAG = "binvoicendflag";

	/**
	 * �Ƿ���
	 */
	public static final String BOFFSETFLAG = "boffsetflag";

	/**
	 * ����ر�
	 */
	public static final String BOUTENDFLAG = "boutendflag";

	/**
	 * �ɲɹ�����Эͬ����
	 */
	public static final String BPOCOOPTOMEFLAG = "bpocooptomeflag";

	/**
	 * �տ��޶����Ԥ��
	 */
	public static final String BPRECEIVEFLAG = "bpreceiveflag";

	/**
	 * �����ر�
	 */
	public static final String BSENDENDFLAG = "bsendendflag";

	/**
	 * ���㷽ʽ
	 */
	public static final String CBALANCETYPEID = "cbalancetypeid";

	/**
	 * ҵ������
	 */
	public static final String CBIZTYPEID = "cbiztypeid";

	/**
	 * ������������
	 */
	public static final String CCHANNELTYPEID = "cchanneltypeid";

	/**
	 * ���������˻�
	 */
	public static final String CCUSTBANKACCID = "ccustbankaccid";

	/**
	 * ��������
	 */
	public static final String CCUSTBANKID = "ccustbankid";

	/**
	 * �ͻ�
	 */
	public static final String CCUSTOMERID = "ccustomerid";

	/**
	 * �������°汾
	 */
	public static final String CDEPTID = "cdeptid";

	/**
	 * ����
	 */
	public static final String CDEPTVID = "cdeptvid";

	/**
	 * ҵ��Ա
	 */
	public static final String CEMPLOYEEID = "cemployeeid";

	/**
	 * ɢ��
	 */
	public static final String CFREECUSTID = "cfreecustid";

	/**
	 * ��Ʊ�ͻ�
	 */
	public static final String CINVOICECUSTID = "cinvoicecustid";

	/**
	 * ԭ��
	 */
	public static final String CORIGCURRENCYID = "corigcurrencyid";

	/**
	 * �տ�Э��
	 */
	public static final String CPAYTERMID = "cpaytermid";

	/**
	 * ����ʱ��
	 */
	public static final String CREATIONTIME = "creationtime";

	/**
	 * ������
	 */
	public static final String CREATOR = "creator";

	/**
	 * �޶���
	 */
	public static final String CREVISERID = "creviserid";

	/**
	 * ��������ID
	 */
	public static final String CSALEORDERID = "csaleorderid";

	/**
	 * ó�����61��
	 */
	public static final String CTRADEWORDID = "ctradewordid";

	/**
	 * ���䷽ʽ
	 */
	public static final String CTRANSPORTTYPEID = "ctransporttypeid";

	/**
	 * ��������
	 */
	public static final String CTRANTYPEID = "ctrantypeid";

	/**
	 * ��������
	 */
	public static final String DBILLDATE = "dbilldate";

	/**
	 * �������ԣ�����Ŀ�ĵ�������֯
	 */
	public static final String DEST_PK_ORG = "dest_pk_org";

	/**
	 * �Ƶ�����
	 */
	public static final String DMAKEDATE = "dmakedate";

	/**
	 * dr
	 */
	public static final String DR = "dr";

	/**
	 * ������״̬
	 */
	public static final String FPFSTATUSFLAG = "fpfstatusflag";

	/**
	 * ����״̬
	 */
	public static final String FSTATUSFLAG = "fstatusflag";

	/**
	 * ��ӡ����
	 */
	public static final String IPRINTCOUNT = "iprintcount";

	/**
	 * �޶��汾��
	 */
	public static final String IVERSION = "iversion";

	/**
	 * �޸�ʱ��
	 */
	public static final String MODIFIEDTIME = "modifiedtime";

	/**
	 * �޸���
	 */
	public static final String MODIFIER = "modifier";

	/**
	 * �����ۿ�
	 */
	public static final String NDISCOUNTRATE = "ndiscountrate";

	/**
	 * ʵ��Ԥ�տ���
	 */
	public static final String NPRECEIVEMNY = "npreceivemny";

	/**
	 * �����տ��޶�
	 */
	public static final String NPRECEIVEQUOTA = "npreceivequota";

	/**
	 * �����տ����
	 */
	public static final String NPRECEIVERATE = "npreceiverate";

	/**
	 * ʵ���տ���
	 */
	public static final String NRECEIVEDMNY = "nreceivedmny";

	/**
	 * �������ԣ������տ���
	 */
	public static final String NTHISRECEIVEMNY = "nthisreceivemny";

	/**
	 * ntotalmny
	 */
	public static final String NTOTALMNY = "ntotalmny";

	/**
	 * �ϼ�����
	 */
	public static final String NTOTALNUM = "ntotalnum";

	/**
	 * ���ϼ�(��˰�ϼ�)
	 */
	public static final String NTOTALORIGMNY = "ntotalorigmny";

	/**
	 * ��Ʒ�Ľ��ϼ�(��Ʒ�ļ�˰�ϼ�)
	 */
	public static final String NLRGTOTALORIGMNY = "nlrgtotalorigmny";

	/**
	 * ���ó�ֽ��
	 */
	public static final String NTOTALORIGSUBMNY = "ntotalorigsubmny";

	/**
	 * �ܼ���
	 */
	public static final String NTOTALPIECE = "ntotalpiece";

	/**
	 * �ϼ����
	 */
	public static final String NTOTALVOLUME = "ntotalvolume";

	/**
	 * �ϼ�����
	 */
	public static final String NTOTALWEIGHT = "ntotalweight";

	/**
	 * ����
	 */
	public static final String PK_GROUP = "pk_group";

	/**
	 * ������֯
	 */
	public static final String PK_ORG = "pk_org";

	/**
	 * ������֯�汾
	 */
	public static final String PK_ORG_V = "pk_org_v";

	private static final long serialVersionUID = -567754544473734276L;

	/**
	 * ����ʱ��
	 */
	public static final String TAUDITTIME = "taudittime";

	/**
	 * �޶�ʱ��
	 */
	public static final String TREVISETIME = "trevisetime";

	/**
	 * ʱ���
	 */
	public static final String TS = "ts";

	/**
	 * ���ݺ�
	 */
	public static final String VBILLCODE = "vbillcode";

	/**
	 * �Է�������
	 */
	public static final String VCOOPPOHCODE = "vcooppohcode";

	/**
	 * ����֤��
	 */
	public static final String VCREDITNUM = "vcreditnum";

	/**
	 * �Զ�����1
	 */
	public static final String VDEF1 = "vdef1";

	/**
	 * �Զ�����10
	 */
	public static final String VDEF10 = "vdef10";

	/**
	 * �Զ�����11
	 */
	public static final String VDEF11 = "vdef11";

	/**
	 * �Զ�����12
	 */
	public static final String VDEF12 = "vdef12";

	/**
	 * �Զ�����13
	 */
	public static final String VDEF13 = "vdef13";

	/**
	 * �Զ�����14
	 */
	public static final String VDEF14 = "vdef14";

	/**
	 * �Զ�����15
	 */
	public static final String VDEF15 = "vdef15";

	/**
	 * �Զ�����16
	 */
	public static final String VDEF16 = "vdef16";

	/**
	 * �Զ�����17
	 */
	public static final String VDEF17 = "vdef17";

	/**
	 * �Զ�����18
	 */
	public static final String VDEF18 = "vdef18";

	/**
	 * �Զ�����19
	 */
	public static final String VDEF19 = "vdef19";

	/**
	 * �Զ�����2
	 */
	public static final String VDEF2 = "vdef2";

	/**
	 * �Զ�����20
	 */
	public static final String VDEF20 = "vdef20";

	/**
	 * �Զ�����3
	 */
	public static final String VDEF3 = "vdef3";

	/**
	 * �Զ�����4
	 */
	public static final String VDEF4 = "vdef4";

	/**
	 * �Զ�����5
	 */
	public static final String VDEF5 = "vdef5";

	/**
	 * �Զ�����6
	 */
	public static final String VDEF6 = "vdef6";

	/**
	 * �Զ�����7
	 */
	public static final String VDEF7 = "vdef7";

	/**
	 * �Զ�����8
	 */
	public static final String VDEF8 = "vdef8";

	/**
	 * �Զ�����9
	 */
	public static final String VDEF9 = "vdef9";

	/**
	 * ��ע
	 */
	public static final String VNOTE = "vnote";

	/**
	 * �޶�����
	 */
	public static final String VREVISEREASON = "vrevisereason";

	/**
	 * �������ͱ���
	 */
	public static final String VTRANTYPECODE = "vtrantypecode";

	/**
	 * ������Դ��������
	 */
	public static final String VBILLSRCTYPE = "vbillsrctype";

	/**
	 * ������Դ����ID
	 */
	public static final String CBILLSRCID = "cbillsrcid";

	/**
	 * ��ͷ�ջ��ͻ�
	 */
	public static final String CHRECEIVECUSTID = "chreceivecustid";

	/**
	 * ��ͷ�ͻ��ջ���ַ
	 */
	public static final String CHRECEIVEADDID = "chreceiveaddid";
	/**
	 * add by lyw 2017-6-9 �������
	 */
	public static final String DLFL = "dlfl";
	/**
	 * add by lyw 2017-6-9 ����ȡ�� ö��
	 */
	public static final String DJQZ = "djqz";

	/**
	 * add by lyw 2017-6-9 ����
	 */
	public static final String EXCHANGERATE = "exchange_rate";

	public static UFDouble exchange_rate;
	/**
	 * add by lyw 2017-6-12 �ɹ�����
	 */
	public static final String BUYCCURRENCYID = "buyccurrencyid";

	public static String  buyccurrencyid;
	/**
	 * add by lyw 2017-6-12 ��Ȩ��
	 */
	public static final String SQG = "sqg";

	public static String sqg;
	

	/**
	 * ��ȡ������
	 * 
	 * @return ������
	 */
	public String getApprover() {
		return (String) this.getAttributeValue(SaleOrderHVO.APPROVER);
	}

	/**
	 * ��ȡ�����˷�
	 * 
	 * @return �����˷�
	 */
	public UFBoolean getBadvfeeflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BADVFEEFLAG);
	}

	/**
	 * add by wangzym ��׼���� 2017-01-18
	 */
	public static UFDate jzrq;

	public static UFDate getJzrq() {
		return jzrq;
	}

	public static void setJzrq(UFDate jzrq) {
		SaleOrderHVO.jzrq = jzrq;
	}

	public static Integer getJhq() {
		return jhq;
	}

	public static void setJhq(Integer jhq) {
		SaleOrderHVO.jhq = jhq;
	}

	/********************* The End *********************/

	/**
	 * add by wangzym ��׼���� 2017-01-18
	 */
	public static Integer jhq;

	public static UFDate zcjhq;
	public static UFDouble dlfl;
	public static Integer djqz;

	/**
	 * ��ȡ�������ر�
	 * 
	 * @return �������ر�
	 */
	public UFBoolean getBarsettleflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BARSETTLEFLAG);
	}

	/**
	 * ��ȡ��Эͬ���ɲɹ�����
	 * 
	 * @return ��Эͬ���ɲɹ�����
	 */
	public UFBoolean getBcooptopoflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BCOOPTOPOFLAG);
	}

	/**
	 * ��ȡ�ɱ�����ر�
	 * 
	 * @return �ɱ�����ر�
	 */
	public UFBoolean getBcostsettleflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BCOSTSETTLEFLAG);
	}

	/**
	 * ��ȡ�Ƿ�ɢ��
	 * 
	 * @return �Ƿ�ɢ��
	 */
	public UFBoolean getBfreecustflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BFREECUSTFLAG);
	}

	/**
	 * ��ȡ�Ƶ���
	 * 
	 * @return �Ƶ���
	 */
	public String getBillmaker() {
		return (String) this.getAttributeValue(SaleOrderHVO.BILLMAKER);
	}

	/**
	 * ��ȡ��Ʊ�ر�
	 * 
	 * @return ��Ʊ�ر�
	 */
	public UFBoolean getBinvoicendflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BINVOICENDFLAG);
	}

	/**
	 * ��ȡ�Ƿ���
	 * 
	 * @return �Ƿ���
	 */
	public UFBoolean getBoffsetflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BOFFSETFLAG);
	}

	/**
	 * ��ȡ����ر�
	 * 
	 * @return ����ر�
	 */
	public UFBoolean getBoutendflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BOUTENDFLAG);
	}

	/**
	 * ��ȡ�ɲɹ�����Эͬ����
	 * 
	 * @return �ɲɹ�����Эͬ����
	 */
	public UFBoolean getBpocooptomeflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BPOCOOPTOMEFLAG);
	}

	/**
	 * ��ȡ�տ��޶����Ԥ��
	 * 
	 * @return �տ��޶����Ԥ��
	 */
	public UFBoolean getBpreceiveflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BPRECEIVEFLAG);
	}

	/**
	 * ��ȡ�����ر�
	 * 
	 * @return �����ر�
	 */
	public UFBoolean getBsendendflag() {
		return (UFBoolean) this.getAttributeValue(SaleOrderHVO.BSENDENDFLAG);
	}

	/**
	 * ��ȡ���㷽ʽ
	 * 
	 * @return ���㷽ʽ
	 */
	public String getCbalancetypeid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CBALANCETYPEID);
	}

	/**
	 * ��ȡҵ������
	 * 
	 * @return ҵ������
	 */
	public String getCbiztypeid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CBIZTYPEID);
	}

	/**
	 * ��ȡ������������
	 * 
	 * @return ������������
	 */
	public String getCchanneltypeid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CCHANNELTYPEID);
	}

	/**
	 * ��ȡ���������˻�
	 * 
	 * @return ���������˻�
	 */
	public String getCcustbankaccid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CCUSTBANKACCID);
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public String getCcustbankid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CCUSTBANKID);
	}

	/**
	 * ��ȡ�ͻ�
	 * 
	 * @return �ͻ�
	 */
	public String getCcustomerid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CCUSTOMERID);
	}

	/**
	 * ��ȡ�������°汾
	 * 
	 * @return �������°汾
	 */
	public String getCdeptid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CDEPTID);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getCdeptvid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CDEPTVID);
	}

	/**
	 * ��ȡҵ��Ա
	 * 
	 * @return ҵ��Ա
	 */
	public String getCemployeeid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CEMPLOYEEID);
	}

	/**
	 * ��ȡɢ��
	 * 
	 * @return ɢ��
	 */
	public String getCfreecustid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CFREECUSTID);
	}

	/**
	 * ��ȡ��Ʊ�ͻ�
	 * 
	 * @return ��Ʊ�ͻ�
	 */
	public String getCinvoicecustid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CINVOICECUSTID);
	}

	/**
	 * ��ȡԭ��
	 * 
	 * @return ԭ��
	 */
	public String getCorigcurrencyid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CORIGCURRENCYID);
	}

	/**
	 * ��ȡ�տ�Э��
	 * 
	 * @return �տ�Э��
	 */
	public String getCpaytermid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CPAYTERMID);
	}

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return ����ʱ��
	 */
	public UFDateTime getCreationtime() {
		return (UFDateTime) this.getAttributeValue(SaleOrderHVO.CREATIONTIME);
	}

	/**
	 * ��ȡ������
	 * 
	 * @return ������
	 */
	public String getCreator() {
		return (String) this.getAttributeValue(SaleOrderHVO.CREATOR);
	}

	/**
	 * ��ȡ�޶���
	 * 
	 * @return �޶���
	 */
	public String getCreviserid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CREVISERID);
	}

	/**
	 * ��ȡ��������ID
	 * 
	 * @return ��������ID
	 */
	public String getCsaleorderid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CSALEORDERID);
	}

	/**
	 * ��ȡó������
	 * 
	 * @return ó������
	 */
	public String getCtradewordid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CTRADEWORDID);
	}

	/**
	 * ��ȡ���䷽ʽ
	 * 
	 * @return ���䷽ʽ
	 */
	public String getCtransporttypeid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CTRANSPORTTYPEID);
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public String getCtrantypeid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CTRANTYPEID);
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return ��������
	 */
	public UFDate getDbilldate() {
		return (UFDate) this.getAttributeValue(SaleOrderHVO.DBILLDATE);
	}

	/**
	 * ��ȡ����Ŀ�ĵ�������֯
	 * 
	 * @return dest_pk_org
	 */
	public String getDest_pk_org() {
		return (String) this.getAttributeValue(SaleOrderHVO.DEST_PK_ORG);
	}

	/**
	 * ��ȡ�Ƶ�����
	 * 
	 * @return �Ƶ�����
	 */
	public UFDate getDmakedate() {
		return (UFDate) this.getAttributeValue(SaleOrderHVO.DMAKEDATE);
	}

	public Integer getDr() {
		return (Integer) this.getAttributeValue(SaleOrderHVO.DR);
	}

	/**
	 * ��ȡ������״̬
	 * 
	 * @return ������״̬
	 */
	public Integer getFpfstatusflag() {
		return (Integer) this.getAttributeValue(SaleOrderHVO.FPFSTATUSFLAG);
	}

	/**
	 * ��ȡ����״̬
	 * 
	 * @return ����״̬
	 * @see BillStatus
	 */
	public Integer getFstatusflag() {
		return (Integer) this.getAttributeValue(SaleOrderHVO.FSTATUSFLAG);
	}

	/**
	 * ��ȡ��ӡ����
	 * 
	 * @return ��ӡ����
	 */
	public Integer getIprintcount() {
		return (Integer) this.getAttributeValue(SaleOrderHVO.IPRINTCOUNT);
	}

	/**
	 * ��ȡ�޶��汾��
	 * 
	 * @return �޶��汾��
	 */
	public Integer getIversion() {
		return (Integer) this.getAttributeValue(SaleOrderHVO.IVERSION);
	}

	@Override
	public IVOMeta getMetaData() {
		IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.so_saleorder");
		return meta;
	}

	/**
	 * ��ȡ�޸�ʱ��
	 * 
	 * @return �޸�ʱ��
	 */
	public UFDateTime getModifiedtime() {
		return (UFDateTime) this.getAttributeValue(SaleOrderHVO.MODIFIEDTIME);
	}

	/**
	 * ��ȡ�޸���
	 * 
	 * @return �޸���
	 */
	public String getModifier() {
		return (String) this.getAttributeValue(SaleOrderHVO.MODIFIER);
	}

	/**
	 * ��ȡ�����ۿ�
	 * 
	 * @return �����ۿ�
	 */
	public UFDouble getNdiscountrate() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NDISCOUNTRATE);
	}

	/**
	 * ��ȡʵ��Ԥ�տ���
	 * 
	 * @return ʵ��Ԥ�տ���
	 */
	public UFDouble getNpreceivemny() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NPRECEIVEMNY);
	}

	/**
	 * ��ȡ�����տ��޶�
	 * 
	 * @return �����տ��޶�
	 */
	public UFDouble getNpreceivequota() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NPRECEIVEQUOTA);
	}

	/**
	 * ��ȡ�����տ����
	 * 
	 * @return �����տ����
	 */
	public UFDouble getNpreceiverate() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NPRECEIVERATE);
	}

	/**
	 * ��ȡʵ���տ���
	 * 
	 * @return ʵ���տ���
	 */
	public UFDouble getNreceivedmny() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NRECEIVEDMNY);
	}

	/**
	 * ��ȡnthisreceivemny
	 * 
	 * @return nthisreceivemny
	 */
	public UFDouble getNthisreceivemny() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTHISRECEIVEMNY);
	}

	/**
	 * ��ȡntotalmny
	 * 
	 * @return ntotalmny
	 */
	public UFDouble getNtotalmny() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTOTALMNY);
	}

	/**
	 * ��ȡ�ϼ�����
	 * 
	 * @return �ϼ�����
	 */
	public UFDouble getNtotalnum() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTOTALNUM);
	}

	/**
	 * ��ȡ���ϼ�
	 * 
	 * @return ���ϼ�
	 */
	public UFDouble getNtotalorigmny() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTOTALORIGMNY);
	}

	/**
	 * ��ȡ��Ʒ���ϼ�
	 * 
	 * @return ���ϼ�
	 */
	public UFDouble getNlrgtotalorigmny() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NLRGTOTALORIGMNY);
	}

	/**
	 * ��ȡ���ó�ֽ��
	 * 
	 * @return ���ó�ֽ��
	 */
	public UFDouble getNtotalorigsubmny() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTOTALORIGSUBMNY);
	}

	/**
	 * ��ȡ�ܼ���
	 * 
	 * @return �ܼ���
	 */
	public UFDouble getNtotalpiece() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTOTALPIECE);
	}

	/**
	 * ��ȡ�ϼ����
	 * 
	 * @return �ϼ����
	 */
	public UFDouble getNtotalvolume() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTOTALVOLUME);
	}

	/**
	 * ��ȡ�ϼ�����
	 * 
	 * @return �ϼ�����
	 */
	public UFDouble getNtotalweight() {
		return (UFDouble) this.getAttributeValue(SaleOrderHVO.NTOTALWEIGHT);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return ����
	 */
	public String getPk_group() {
		return (String) this.getAttributeValue(SaleOrderHVO.PK_GROUP);
	}

	/**
	 * ��ȡ������֯
	 * 
	 * @return ������֯
	 */
	public String getPk_org() {
		return (String) this.getAttributeValue(SaleOrderHVO.PK_ORG);
	}

	/**
	 * ��ȡ������֯�汾
	 * 
	 * @return ������֯�汾
	 */
	public String getPk_org_v() {
		return (String) this.getAttributeValue(SaleOrderHVO.PK_ORG_V);
	}

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return ����ʱ��
	 */
	public UFDate getTaudittime() {
		return (UFDate) this.getAttributeValue(SaleOrderHVO.TAUDITTIME);
	}

	/**
	 * ��ȡ�޶�ʱ��
	 * 
	 * @return �޶�ʱ��
	 */
	public UFDate getTrevisetime() {
		return (UFDate) this.getAttributeValue(SaleOrderHVO.TREVISETIME);
	}

	/**
	 * ��ȡʱ���
	 * 
	 * @return ʱ���
	 */
	public UFDateTime getTs() {
		return (UFDateTime) this.getAttributeValue(SaleOrderHVO.TS);
	}

	/**
	 * ��ȡ���ݺ�
	 * 
	 * @return ���ݺ�
	 */
	public String getVbillcode() {
		return (String) this.getAttributeValue(SaleOrderHVO.VBILLCODE);
	}

	/**
	 * ��ȡ�Է�������
	 * 
	 * @return �Է�������
	 */
	public String getVcooppohcode() {
		return (String) this.getAttributeValue(SaleOrderHVO.VCOOPPOHCODE);
	}

	/**
	 * ��ȡ����֤��
	 * 
	 * @return ����֤��
	 */
	public String getVcreditnum() {
		return (String) this.getAttributeValue(SaleOrderHVO.VCREDITNUM);
	}

	/**
	 * ��ȡ�Զ�����1
	 * 
	 * @return �Զ�����1
	 */
	public String getVdef1() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF1);
	}

	/**
	 * ��ȡ�Զ�����10
	 * 
	 * @return �Զ�����10
	 */
	public String getVdef10() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF10);
	}

	/**
	 * ��ȡ�Զ�����11
	 * 
	 * @return �Զ�����11
	 */
	public String getVdef11() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF11);
	}

	/**
	 * ��ȡ�Զ�����12
	 * 
	 * @return �Զ�����12
	 */
	public String getVdef12() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF12);
	}

	/**
	 * ��ȡ�Զ�����13
	 * 
	 * @return �Զ�����13
	 */
	public String getVdef13() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF13);
	}

	/**
	 * ��ȡ�Զ�����14
	 * 
	 * @return �Զ�����14
	 */
	public String getVdef14() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF14);
	}

	/**
	 * ��ȡ�Զ�����15
	 * 
	 * @return �Զ�����15
	 */
	public String getVdef15() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF15);
	}

	/**
	 * ��ȡ�Զ�����16
	 * 
	 * @return �Զ�����16
	 */
	public String getVdef16() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF16);
	}

	/**
	 * ��ȡ�Զ�����17
	 * 
	 * @return �Զ�����17
	 */
	public String getVdef17() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF17);
	}

	/**
	 * ��ȡ�Զ�����18
	 * 
	 * @return �Զ�����18
	 */
	public String getVdef18() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF18);
	}

	/**
	 * ��ȡ�Զ�����19
	 * 
	 * @return �Զ�����19
	 */
	public String getVdef19() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF19);
	}

	/**
	 * ��ȡ�Զ�����2
	 * 
	 * @return �Զ�����2
	 */
	public String getVdef2() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF2);
	}

	/**
	 * ��ȡ�Զ�����20
	 * 
	 * @return �Զ�����20
	 */
	public String getVdef20() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF20);
	}

	/**
	 * ��ȡ�Զ�����3
	 * 
	 * @return �Զ�����3
	 */
	public String getVdef3() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF3);
	}

	/**
	 * ��ȡ�Զ�����4
	 * 
	 * @return �Զ�����4
	 */
	public String getVdef4() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF4);
	}

	/**
	 * ��ȡ�Զ�����5
	 * 
	 * @return �Զ�����5
	 */
	public String getVdef5() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF5);
	}

	/**
	 * ��ȡ�Զ�����6
	 * 
	 * @return �Զ�����6
	 */
	public String getVdef6() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF6);
	}

	/**
	 * ��ȡ�Զ�����7
	 * 
	 * @return �Զ�����7
	 */
	public String getVdef7() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF7);
	}

	/**
	 * ��ȡ�Զ�����8
	 * 
	 * @return �Զ�����8
	 */
	public String getVdef8() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF8);
	}

	/**
	 * ��ȡ�Զ�����9
	 * 
	 * @return �Զ�����9
	 */
	public String getVdef9() {
		return (String) this.getAttributeValue(SaleOrderHVO.VDEF9);
	}

	/**
	 * ��ȡ��ע
	 * 
	 * @return ��ע
	 */
	public String getVnote() {
		return (String) this.getAttributeValue(SaleOrderHVO.VNOTE);
	}

	/**
	 * ��ȡ�޶�����
	 * 
	 * @return �޶�����
	 */
	public String getVrevisereason() {
		return (String) this.getAttributeValue(SaleOrderHVO.VREVISEREASON);
	}

	/**
	 * ��ȡ�������ͱ���
	 * 
	 * @return �������ͱ���
	 */
	public String getVtrantypecode() {
		return (String) this.getAttributeValue(SaleOrderHVO.VTRANTYPECODE);
	}

	/**
	 * ��ȡ��ͷ�ջ��ͻ�
	 * 
	 * @return ��ͷ�ջ��ͻ�
	 */
	public String getChreceivecustid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CHRECEIVECUSTID);
	}

	/**
	 * ��ȡ��ͷ�ջ���ַ
	 * 
	 * @return �ջ���ַ
	 */
	public String getChreceiveaddid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CHRECEIVEADDID);
	}

	/**
	 * ��ȡ������Դ��������
	 * 
	 * @return ������Դ��������
	 */
	public String getVbillsrctype() {
		return (String) this.getAttributeValue(SaleOrderHVO.VBILLSRCTYPE);
	}

	/**
	 * ��ȡ������Դ����ID
	 * 
	 * @return ������Դ����ID
	 */
	public String getCbillsrcid() {
		return (String) this.getAttributeValue(SaleOrderHVO.CBILLSRCID);
	}

	/**
	 * ����������
	 * 
	 * @param approver
	 *            ������
	 */
	public void setApprover(String approver) {
		this.setAttributeValue(SaleOrderHVO.APPROVER, approver);
	}

	/**
	 * ���ô����˷�
	 * 
	 * @param badvfeeflag
	 *            �����˷�
	 */
	public void setBadvfeeflag(UFBoolean badvfeeflag) {
		this.setAttributeValue(SaleOrderHVO.BADVFEEFLAG, badvfeeflag);
	}

	/**
	 * �����������ر�
	 * 
	 * @param barsettleflag
	 *            �������ر�
	 */
	public void setBarsettleflag(UFBoolean barsettleflag) {
		this.setAttributeValue(SaleOrderHVO.BARSETTLEFLAG, barsettleflag);
	}

	/**
	 * ������Эͬ���ɲɹ�����
	 * 
	 * @param bcooptopoflag
	 *            ��Эͬ���ɲɹ�����
	 */
	public void setBcooptopoflag(UFBoolean bcooptopoflag) {
		this.setAttributeValue(SaleOrderHVO.BCOOPTOPOFLAG, bcooptopoflag);
	}

	/**
	 * ���óɱ�����ر�
	 * 
	 * @param bcostsettleflag
	 *            �ɱ�����ر�
	 */
	public void setBcostsettleflag(UFBoolean bcostsettleflag) {
		this.setAttributeValue(SaleOrderHVO.BCOSTSETTLEFLAG, bcostsettleflag);
	}

	/**
	 * �����Ƿ�ɢ��
	 * 
	 * @param bfreecustflag
	 *            �Ƿ�ɢ��
	 */
	public void setBfreecustflag(UFBoolean bfreecustflag) {
		this.setAttributeValue(SaleOrderHVO.BFREECUSTFLAG, bfreecustflag);
	}

	/**
	 * �����Ƶ���
	 * 
	 * @param billmaker
	 *            �Ƶ���
	 */
	public void setBillmaker(String billmaker) {
		this.setAttributeValue(SaleOrderHVO.BILLMAKER, billmaker);
	}

	/**
	 * ���ÿ�Ʊ�ر�
	 * 
	 * @param binvoicendflag
	 *            ��Ʊ�ر�
	 */
	public void setBinvoicendflag(UFBoolean binvoicendflag) {
		this.setAttributeValue(SaleOrderHVO.BINVOICENDFLAG, binvoicendflag);
	}

	/**
	 * �����Ƿ���
	 * 
	 * @param boffsetflag
	 *            �Ƿ���
	 */
	public void setBoffsetflag(UFBoolean boffsetflag) {
		this.setAttributeValue(SaleOrderHVO.BOFFSETFLAG, boffsetflag);
	}

	/**
	 * ���ó���ر�
	 * 
	 * @param boutendflag
	 *            ����ر�
	 */
	public void setBoutendflag(UFBoolean boutendflag) {
		this.setAttributeValue(SaleOrderHVO.BOUTENDFLAG, boutendflag);
	}

	/**
	 * �����ɲɹ�����Эͬ����
	 * 
	 * @param bpocooptomeflag
	 *            �ɲɹ�����Эͬ����
	 */
	public void setBpocooptomeflag(UFBoolean bpocooptomeflag) {
		this.setAttributeValue(SaleOrderHVO.BPOCOOPTOMEFLAG, bpocooptomeflag);
	}

	/**
	 * �����տ��޶����Ԥ��
	 * 
	 * @param bpreceiveflag
	 *            �տ��޶����Ԥ��
	 */
	public void setBpreceiveflag(UFBoolean bpreceiveflag) {
		this.setAttributeValue(SaleOrderHVO.BPRECEIVEFLAG, bpreceiveflag);
	}

	/**
	 * ���÷����ر�
	 * 
	 * @param bsendendflag
	 *            �����ر�
	 */
	public void setBsendendflag(UFBoolean bsendendflag) {
		this.setAttributeValue(SaleOrderHVO.BSENDENDFLAG, bsendendflag);
	}

	/**
	 * ���ý��㷽ʽ
	 * 
	 * @param cbalancetypeid
	 *            ���㷽ʽ
	 */
	public void setCbalancetypeid(String cbalancetypeid) {
		this.setAttributeValue(SaleOrderHVO.CBALANCETYPEID, cbalancetypeid);
	}

	/**
	 * ����ҵ������
	 * 
	 * @param cbiztypeid
	 *            ҵ������
	 */
	public void setCbiztypeid(String cbiztypeid) {
		this.setAttributeValue(SaleOrderHVO.CBIZTYPEID, cbiztypeid);
	}

	/**
	 * ����������������
	 * 
	 * @param cchanneltypeid
	 *            ������������
	 */
	public void setCchanneltypeid(String cchanneltypeid) {
		this.setAttributeValue(SaleOrderHVO.CCHANNELTYPEID, cchanneltypeid);
	}

	/**
	 * ���ÿ��������˻�
	 * 
	 * @param ccustbankaccid
	 *            ���������˻�
	 */
	public void setCcustbankaccid(String ccustbankaccid) {
		this.setAttributeValue(SaleOrderHVO.CCUSTBANKACCID, ccustbankaccid);
	}

	/**
	 * ���ÿ�������
	 * 
	 * @param ccustbankid
	 *            ��������
	 */
	public void setCcustbankid(String ccustbankid) {
		this.setAttributeValue(SaleOrderHVO.CCUSTBANKID, ccustbankid);
	}

	/**
	 * ���ÿͻ�
	 * 
	 * @param ccustomerid
	 *            �ͻ�
	 */
	public void setCcustomerid(String ccustomerid) {
		this.setAttributeValue(SaleOrderHVO.CCUSTOMERID, ccustomerid);
	}

	/**
	 * ���ò������°汾
	 * 
	 * @param cdeptid
	 *            �������°汾
	 */
	public void setCdeptid(String cdeptid) {
		this.setAttributeValue(SaleOrderHVO.CDEPTID, cdeptid);
	}

	/**
	 * ���ò���
	 * 
	 * @param cdeptvid
	 *            ����
	 */
	public void setCdeptvid(String cdeptvid) {
		this.setAttributeValue(SaleOrderHVO.CDEPTVID, cdeptvid);
	}

	/**
	 * ����ҵ��Ա
	 * 
	 * @param cemployeeid
	 *            ҵ��Ա
	 */
	public void setCemployeeid(String cemployeeid) {
		this.setAttributeValue(SaleOrderHVO.CEMPLOYEEID, cemployeeid);
	}

	/**
	 * ����ɢ��
	 * 
	 * @param cfreecustid
	 *            ɢ��
	 */
	public void setCfreecustid(String cfreecustid) {
		this.setAttributeValue(SaleOrderHVO.CFREECUSTID, cfreecustid);
	}

	/**
	 * ���ÿ�Ʊ�ͻ�
	 * 
	 * @param cinvoicecustid
	 *            ��Ʊ�ͻ�
	 */
	public void setCinvoicecustid(String cinvoicecustid) {
		this.setAttributeValue(SaleOrderHVO.CINVOICECUSTID, cinvoicecustid);
	}

	/**
	 * ����ԭ��
	 * 
	 * @param corigcurrencyid
	 *            ԭ��
	 */
	public void setCorigcurrencyid(String corigcurrencyid) {
		this.setAttributeValue(SaleOrderHVO.CORIGCURRENCYID, corigcurrencyid);
	}

	/**
	 * �����տ�Э��
	 * 
	 * @param cpaytermid
	 *            �տ�Э��
	 */
	public void setCpaytermid(String cpaytermid) {
		this.setAttributeValue(SaleOrderHVO.CPAYTERMID, cpaytermid);
	}

	/**
	 * ���ô���ʱ��
	 * 
	 * @param creationtime
	 *            ����ʱ��
	 */
	public void setCreationtime(UFDateTime creationtime) {
		this.setAttributeValue(SaleOrderHVO.CREATIONTIME, creationtime);
	}

	/**
	 * ���ô�����
	 * 
	 * @param creator
	 *            ������
	 */
	public void setCreator(String creator) {
		this.setAttributeValue(SaleOrderHVO.CREATOR, creator);
	}

	/**
	 * �����޶���
	 * 
	 * @param creviserid
	 *            �޶���
	 */
	public void setCreviserid(String creviserid) {
		this.setAttributeValue(SaleOrderHVO.CREVISERID, creviserid);
	}

	/**
	 * ������������ID
	 * 
	 * @param csaleorderid
	 *            ��������ID
	 */
	public void setCsaleorderid(String csaleorderid) {
		this.setAttributeValue(SaleOrderHVO.CSALEORDERID, csaleorderid);
	}

	/**
	 * ����ó������
	 * 
	 * @param ctradewordid
	 *            ó������
	 */
	public void setCtradewordid(String ctradewordid) {
		this.setAttributeValue(SaleOrderHVO.CTRADEWORDID, ctradewordid);
	}

	/**
	 * �������䷽ʽ
	 * 
	 * @param ctransporttypeid
	 *            ���䷽ʽ
	 */
	public void setCtransporttypeid(String ctransporttypeid) {
		this.setAttributeValue(SaleOrderHVO.CTRANSPORTTYPEID, ctransporttypeid);
	}

	/**
	 * ���ý�������
	 * 
	 * @param ctrantypeid
	 *            ��������
	 */
	public void setCtrantypeid(String ctrantypeid) {
		this.setAttributeValue(SaleOrderHVO.CTRANTYPEID, ctrantypeid);
	}

	/**
	 * ���õ�������
	 * 
	 * @param dbilldate
	 *            ��������
	 */
	public void setDbilldate(UFDate dbilldate) {
		this.setAttributeValue(SaleOrderHVO.DBILLDATE, dbilldate);
	}

	/**
	 * ��������Ŀ�ĵ�������֯
	 * 
	 * @param dest_pk_org
	 *            dest_pk_org
	 */
	public void setDest_pk_org(String dest_pk_org) {
		this.setAttributeValue(SaleOrderHVO.DEST_PK_ORG, dest_pk_org);
	}

	/**
	 * �����Ƶ�����
	 * 
	 * @param Dmakedate
	 *            �Ƶ�����
	 */
	public void setDmakedate(UFDate dmakedate) {
		this.setAttributeValue(SaleOrderHVO.DMAKEDATE, dmakedate);
	}

	public void setDr(Integer dr) {
		this.setAttributeValue(SaleOrderHVO.DR, dr);
	}

	/**
	 * ����������״̬
	 * 
	 * @param fpfstatusflag
	 *            ������״̬
	 */
	public void setFpfstatusflag(Integer fpfstatusflag) {
		this.setAttributeValue(SaleOrderHVO.FPFSTATUSFLAG, fpfstatusflag);
	}

	/**
	 * ���õ���״̬
	 * 
	 * @param fstatusflag
	 *            ����״̬
	 * @see BillStatus
	 */
	public void setFstatusflag(Integer fstatusflag) {
		this.setAttributeValue(SaleOrderHVO.FSTATUSFLAG, fstatusflag);
	}

	/**
	 * ���ô�ӡ����
	 * 
	 * @param iprintcount
	 *            ��ӡ����
	 */
	public void setIprintcount(Integer iprintcount) {
		this.setAttributeValue(SaleOrderHVO.IPRINTCOUNT, iprintcount);
	}

	/**
	 * �����޶��汾��
	 * 
	 * @param iversion
	 *            �޶��汾��
	 */
	public void setIversion(Integer iversion) {
		this.setAttributeValue(SaleOrderHVO.IVERSION, iversion);
	}

	/**
	 * �����޸�ʱ��
	 * 
	 * @param modifiedtime
	 *            �޸�ʱ��
	 */
	public void setModifiedtime(UFDateTime modifiedtime) {
		this.setAttributeValue(SaleOrderHVO.MODIFIEDTIME, modifiedtime);
	}

	/**
	 * �����޸���
	 * 
	 * @param modifier
	 *            �޸���
	 */
	public void setModifier(String modifier) {
		this.setAttributeValue(SaleOrderHVO.MODIFIER, modifier);
	}

	/**
	 * ���������ۿ�
	 * 
	 * @param ndiscountrate
	 *            �����ۿ�
	 */
	public void setNdiscountrate(UFDouble ndiscountrate) {
		this.setAttributeValue(SaleOrderHVO.NDISCOUNTRATE, ndiscountrate);
	}

	/**
	 * ����ʵ��Ԥ�տ���
	 * 
	 * @param npreceivemny
	 *            ʵ��Ԥ�տ���
	 */
	public void setNpreceivemny(UFDouble npreceivemny) {
		this.setAttributeValue(SaleOrderHVO.NPRECEIVEMNY, npreceivemny);
	}

	/**
	 * ���ö����տ��޶�
	 * 
	 * @param npreceivequota
	 *            �����տ��޶�
	 */
	public void setNpreceivequota(UFDouble npreceivequota) {
		this.setAttributeValue(SaleOrderHVO.NPRECEIVEQUOTA, npreceivequota);
	}

	/**
	 * ���ö����տ����
	 * 
	 * @param npreceiverate
	 *            �����տ����
	 */
	public void setNpreceiverate(UFDouble npreceiverate) {
		this.setAttributeValue(SaleOrderHVO.NPRECEIVERATE, npreceiverate);
	}

	/**
	 * ����ʵ���տ���
	 * 
	 * @param nreceivedmny
	 *            ʵ���տ���
	 */
	public void setNreceivedmny(UFDouble nreceivedmny) {
		this.setAttributeValue(SaleOrderHVO.NRECEIVEDMNY, nreceivedmny);
	}

	/**
	 * ����nthisreceivemny
	 * 
	 * @param nthisreceivemny
	 *            nthisreceivemny
	 */
	public void setNthisreceivemny(UFDouble nthisreceivemny) {
		this.setAttributeValue(SaleOrderHVO.NTHISRECEIVEMNY, nthisreceivemny);
	}

	/**
	 * ����ntotalmny
	 * 
	 * @param ntotalmny
	 *            ntotalmny
	 */
	public void setNtotalmny(UFDouble ntotalmny) {
		this.setAttributeValue(SaleOrderHVO.NTOTALMNY, ntotalmny);
	}

	/**
	 * ���úϼ�����
	 * 
	 * @param ntotalnum
	 *            �ϼ�����
	 */
	public void setNtotalnum(UFDouble ntotalnum) {
		this.setAttributeValue(SaleOrderHVO.NTOTALNUM, ntotalnum);
	}

	/**
	 * ���ý��ϼ�
	 * 
	 * @param ntotalorigmny
	 *            ���ϼ�
	 */
	public void setNtotalorigmny(UFDouble ntotalorigmny) {
		this.setAttributeValue(SaleOrderHVO.NTOTALORIGMNY, ntotalorigmny);
	}

	/**
	 * ������Ʒ���ϼ�
	 * 
	 * @param nlrgtotalorigmny
	 */
	public void setNlrgtotalorigmny(UFDouble nlrgtotalorigmny) {
		this.setAttributeValue(SaleOrderHVO.NLRGTOTALORIGMNY, nlrgtotalorigmny);
	}

	/**
	 * ���÷��ó�ֽ��
	 * 
	 * @param ntotalorigsubmny
	 *            ���ó�ֽ��
	 */
	public void setNtotalorigsubmny(UFDouble ntotalorigsubmny) {
		this.setAttributeValue(SaleOrderHVO.NTOTALORIGSUBMNY, ntotalorigsubmny);
	}

	/**
	 * �����ܼ���
	 * 
	 * @param ntotalpiece
	 *            �ܼ���
	 */
	public void setNtotalpiece(UFDouble ntotalpiece) {
		this.setAttributeValue(SaleOrderHVO.NTOTALPIECE, ntotalpiece);
	}

	/**
	 * ���úϼ����
	 * 
	 * @param ntotalvolume
	 *            �ϼ����
	 */
	public void setNtotalvolume(UFDouble ntotalvolume) {
		this.setAttributeValue(SaleOrderHVO.NTOTALVOLUME, ntotalvolume);
	}

	/**
	 * ���úϼ�����
	 * 
	 * @param ntotalweight
	 *            �ϼ�����
	 */
	public void setNtotalweight(UFDouble ntotalweight) {
		this.setAttributeValue(SaleOrderHVO.NTOTALWEIGHT, ntotalweight);
	}

	/**
	 * ���ü���
	 * 
	 * @param pk_group
	 *            ����
	 */
	public void setPk_group(String pk_group) {
		this.setAttributeValue(SaleOrderHVO.PK_GROUP, pk_group);
	}

	/**
	 * ����������֯
	 * 
	 * @param pk_org
	 *            ������֯
	 */
	public void setPk_org(String pk_org) {
		this.setAttributeValue(SaleOrderHVO.PK_ORG, pk_org);
	}

	/**
	 * ����������֯�汾
	 * 
	 * @param pk_org_v
	 *            ������֯�汾
	 */
	public void setPk_org_v(String pk_org_v) {
		this.setAttributeValue(SaleOrderHVO.PK_ORG_V, pk_org_v);
	}

	/**
	 * ��������ʱ��
	 * 
	 * @param taudittime
	 *            ����ʱ��
	 */
	public void setTaudittime(UFDate taudittime) {
		this.setAttributeValue(SaleOrderHVO.TAUDITTIME, taudittime);
	}

	/**
	 * �����޶�ʱ��
	 * 
	 * @param trevisetime
	 *            �޶�ʱ��
	 */
	public void setTrevisetime(UFDate trevisetime) {
		this.setAttributeValue(SaleOrderHVO.TREVISETIME, trevisetime);
	}

	/**
	 * ����ʱ���
	 * 
	 * @param ts
	 *            ʱ���
	 */
	public void setTs(UFDateTime ts) {
		this.setAttributeValue(SaleOrderHVO.TS, ts);
	}

	/**
	 * ���õ��ݺ�
	 * 
	 * @param vbillcode
	 *            ���ݺ�
	 */
	public void setVbillcode(String vbillcode) {
		this.setAttributeValue(SaleOrderHVO.VBILLCODE, vbillcode);
	}

	/**
	 * ���öԷ�������
	 * 
	 * @param vcooppohcode
	 *            �Է�������
	 */
	public void setVcooppohcode(String vcooppohcode) {
		this.setAttributeValue(SaleOrderHVO.VCOOPPOHCODE, vcooppohcode);
	}

	/**
	 * ��������֤��
	 * 
	 * @param vcreditnum
	 *            ����֤��
	 */
	public void setVcreditnum(String vcreditnum) {
		this.setAttributeValue(SaleOrderHVO.VCREDITNUM, vcreditnum);
	}

	/**
	 * �����Զ�����1
	 * 
	 * @param vdef1
	 *            �Զ�����1
	 */
	public void setVdef1(String vdef1) {
		this.setAttributeValue(SaleOrderHVO.VDEF1, vdef1);
	}

	/**
	 * �����Զ�����10
	 * 
	 * @param vdef10
	 *            �Զ�����10
	 */
	public void setVdef10(String vdef10) {
		this.setAttributeValue(SaleOrderHVO.VDEF10, vdef10);
	}

	/**
	 * �����Զ�����11
	 * 
	 * @param vdef11
	 *            �Զ�����11
	 */
	public void setVdef11(String vdef11) {
		this.setAttributeValue(SaleOrderHVO.VDEF11, vdef11);
	}

	/**
	 * �����Զ�����12
	 * 
	 * @param vdef12
	 *            �Զ�����12
	 */
	public void setVdef12(String vdef12) {
		this.setAttributeValue(SaleOrderHVO.VDEF12, vdef12);
	}

	/**
	 * �����Զ�����13
	 * 
	 * @param vdef13
	 *            �Զ�����13
	 */
	public void setVdef13(String vdef13) {
		this.setAttributeValue(SaleOrderHVO.VDEF13, vdef13);
	}

	/**
	 * �����Զ�����14
	 * 
	 * @param vdef14
	 *            �Զ�����14
	 */
	public void setVdef14(String vdef14) {
		this.setAttributeValue(SaleOrderHVO.VDEF14, vdef14);
	}

	/**
	 * �����Զ�����15
	 * 
	 * @param vdef15
	 *            �Զ�����15
	 */
	public void setVdef15(String vdef15) {
		this.setAttributeValue(SaleOrderHVO.VDEF15, vdef15);
	}

	/**
	 * �����Զ�����16
	 * 
	 * @param vdef16
	 *            �Զ�����16
	 */
	public void setVdef16(String vdef16) {
		this.setAttributeValue(SaleOrderHVO.VDEF16, vdef16);
	}

	/**
	 * �����Զ�����17
	 * 
	 * @param vdef17
	 *            �Զ�����17
	 */
	public void setVdef17(String vdef17) {
		this.setAttributeValue(SaleOrderHVO.VDEF17, vdef17);
	}

	/**
	 * �����Զ�����18
	 * 
	 * @param vdef18
	 *            �Զ�����18
	 */
	public void setVdef18(String vdef18) {
		this.setAttributeValue(SaleOrderHVO.VDEF18, vdef18);
	}

	/**
	 * �����Զ�����19
	 * 
	 * @param vdef19
	 *            �Զ�����19
	 */
	public void setVdef19(String vdef19) {
		this.setAttributeValue(SaleOrderHVO.VDEF19, vdef19);
	}

	/**
	 * �����Զ�����2
	 * 
	 * @param vdef2
	 *            �Զ�����2
	 */
	public void setVdef2(String vdef2) {
		this.setAttributeValue(SaleOrderHVO.VDEF2, vdef2);
	}

	/**
	 * �����Զ�����20
	 * 
	 * @param vdef20
	 *            �Զ�����20
	 */
	public void setVdef20(String vdef20) {
		this.setAttributeValue(SaleOrderHVO.VDEF20, vdef20);
	}

	/**
	 * �����Զ�����3
	 * 
	 * @param vdef3
	 *            �Զ�����3
	 */
	public void setVdef3(String vdef3) {
		this.setAttributeValue(SaleOrderHVO.VDEF3, vdef3);
	}

	/**
	 * �����Զ�����4
	 * 
	 * @param vdef4
	 *            �Զ�����4
	 */
	public void setVdef4(String vdef4) {
		this.setAttributeValue(SaleOrderHVO.VDEF4, vdef4);
	}

	/**
	 * �����Զ�����5
	 * 
	 * @param vdef5
	 *            �Զ�����5
	 */
	public void setVdef5(String vdef5) {
		this.setAttributeValue(SaleOrderHVO.VDEF5, vdef5);
	}

	/**
	 * �����Զ�����6
	 * 
	 * @param vdef6
	 *            �Զ�����6
	 */
	public void setVdef6(String vdef6) {
		this.setAttributeValue(SaleOrderHVO.VDEF6, vdef6);
	}

	/**
	 * �����Զ�����7
	 * 
	 * @param vdef7
	 *            �Զ�����7
	 */
	public void setVdef7(String vdef7) {
		this.setAttributeValue(SaleOrderHVO.VDEF7, vdef7);
	}

	/**
	 * �����Զ�����8
	 * 
	 * @param vdef8
	 *            �Զ�����8
	 */
	public void setVdef8(String vdef8) {
		this.setAttributeValue(SaleOrderHVO.VDEF8, vdef8);
	}

	/**
	 * �����Զ�����9
	 * 
	 * @param vdef9
	 *            �Զ�����9
	 */
	public void setVdef9(String vdef9) {
		this.setAttributeValue(SaleOrderHVO.VDEF9, vdef9);
	}

	/**
	 * ���ñ�ע
	 * 
	 * @param vnote
	 *            ��ע
	 */
	public void setVnote(String vnote) {
		this.setAttributeValue(SaleOrderHVO.VNOTE, vnote);
	}

	/**
	 * �����޶�����
	 * 
	 * @param vrevisereason
	 *            �޶�����
	 */
	public void setVrevisereason(String vrevisereason) {
		this.setAttributeValue(SaleOrderHVO.VREVISEREASON, vrevisereason);
	}

	/**
	 * ���ý������ͱ���
	 * 
	 * @param vtrantypecode
	 *            �������ͱ���
	 */
	public void setVtrantypecode(String vtrantypecode) {
		this.setAttributeValue(SaleOrderHVO.VTRANTYPECODE, vtrantypecode);
	}

	/**
	 * ����������Դ����ID
	 * 
	 * @param cbillsrcid
	 *            ������Դ����ID
	 */
	public void setCbillsrcid(String cbillsrcid) {
		this.setAttributeValue(SaleOrderHVO.CBILLSRCID, cbillsrcid);
	}

	/**
	 * ����������Դ��������
	 * 
	 * @param vbillsrctype
	 *            ������Դ��������
	 */
	public void setVbillsrctype(String vbillsrctype) {
		this.setAttributeValue(SaleOrderHVO.VBILLSRCTYPE, vbillsrctype);
	}

	/**
	 * ���ñ�ͷ�ջ��ͻ�
	 * 
	 * @param chreceivecustid
	 *            �ջ��ͻ�
	 */
	public void setChreceivecustid(String chreceivecustid) {
		this.setAttributeValue(SaleOrderHVO.CHRECEIVECUSTID, chreceivecustid);
	}

	/**
	 * ���ñ�ͷ�ջ���ַ
	 * 
	 * @param chreceiveaddid
	 *            �ͻ��ջ���ַ
	 */
	public void setChreceiveaddid(String chreceiveaddid) {
		this.setAttributeValue(SaleOrderHVO.CHRECEIVEADDID, chreceiveaddid);
	}

	/**
	 * 2017-03-01����
	 * 
	 * @return zcjhq
	 */
	public static UFDate getZcjhq() {
		return zcjhq;
	}

	/**
	 * @param zcjhq
	 *            Ҫ���õ� zcjhq
	 */
	public static void setZcjhq(UFDate zcjhq) {
		SaleOrderHVO.zcjhq = zcjhq;
	}

	/**
	 * @return dlfl
	 */
	public static UFDouble getDlfl() {
		return dlfl;
	}

	/**
	 * @param dlfl
	 *            Ҫ���õ� dlfl
	 */
	public static void setDlfl(UFDouble dlfl) {
		SaleOrderHVO.dlfl = dlfl;
	}

	/**
	 * @return djqz
	 */
	public static Integer getDjqz() {
		return djqz;
	}

	/**
	 * @param djqz
	 *            Ҫ���õ� djqz
	 */
	public static void setDjqz(Integer djqz) {
		SaleOrderHVO.djqz = djqz;
	}

	/**
	 * @return exchange_rate
	 */
	public UFDouble getExchange_rate() {
		return exchange_rate;
	}

	/**
	 * @param exchange_rate
	 *            Ҫ���õ� exchange_rate
	 */
	public void setExchange_rate(UFDouble exchange_rate) {
		SaleOrderHVO.exchange_rate = exchange_rate;
	}

	/**
	 * @return buyccurrencyid
	 */
	public static String getBuyccurrencyid() {
		return buyccurrencyid;
	}

	/**
	 * @param buyccurrencyid
	 *            Ҫ���õ� buyccurrencyid
	 */
	public static void setBuyccurrencyid(String buyccurrencyid) {
		SaleOrderHVO.buyccurrencyid = buyccurrencyid;
	}

	/**
	 * @return sqg
	 */
	public static String getSqg() {
		return sqg;
	}

	/**
	 * @param sqg Ҫ���õ� sqg
	 */
	public static void setSqg(String sqg) {
		SaleOrderHVO.sqg = sqg;
	}

}
