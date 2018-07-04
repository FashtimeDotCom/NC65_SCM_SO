package nc.vo.so.m30.rule;

import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryBVO;

/**
 * ���۶����޶��ֶ� �޶�Ӱ���ֶ� ����
 * 
 * @since 6.36
 * @version 2015-1-6 ����5:37:30
 * @author wangshu6
 */
public class EditableAndRewiteItems {

	// ע���ͷ�޶����Ա༭���ֶ�
	public static final String[] HEADEDITABLEITEMKEY = new String[] {
	/** ---------- ��ͷ ---------- */
	SaleOrderHVO.CTRADEWORDID, SaleOrderHVO.VREVISEREASON, SaleOrderHVO.VNOTE,
	/** -------��ͷ�Զ�����------ **/
	SaleOrderHVO.VDEF1, SaleOrderHVO.VDEF2, SaleOrderHVO.VDEF3,
			SaleOrderHVO.VDEF4, SaleOrderHVO.VDEF5, SaleOrderHVO.VDEF6,
			SaleOrderHVO.VDEF7, SaleOrderHVO.VDEF8, SaleOrderHVO.VDEF9,
			SaleOrderHVO.VDEF10, SaleOrderHVO.VDEF11, SaleOrderHVO.VDEF12,
			SaleOrderHVO.VDEF13, SaleOrderHVO.VDEF14,
			SaleOrderHVO.VDEF15,
			SaleOrderHVO.VDEF16,
			SaleOrderHVO.VDEF17,
			SaleOrderHVO.VDEF18,
			SaleOrderHVO.VDEF19,
			SaleOrderHVO.VDEF20,
			// ����ܲ ����Ԫ����
			"jzrq", "jhq", "zcjhq", "dlfl", "djqz", "cmarbaseclassid",
			"chvendorid", "agdef1", "agdef2", "agdef3", "agdef4", "agdef5",
			"agdef6", "agdef7", "agdef8", "agdef9", "agdef10", "agdef11",
			"exchange_rate ", "buyccurrencyid", "sqg", "vbilllcode",
			"signinstructions", "dlxybz", "hblob1", "hblob2", "hblob3",
			"hblob4", "hblob5", };

	// ע������޶����Ա༭���ֶ�
	public static final String[] BODYEDITABLEITEMKEY = new String[] {

			/** ---------- ���� ---------- */
			// �ͻ����ϱ��롢���ϱ��롢
			SaleOrderBVO.CCUSTMATERIALID,
			SaleOrderBVO.CMATERIALVID,

			// ���������������
			SaleOrderBVO.NVOLUME,
			SaleOrderBVO.NWEIGHT,
			SaleOrderBVO.NPIECE,

			// ������֯�����۵�λ����λ
			SaleOrderBVO.PK_ORG,
			SaleOrderBVO.CQTUNITID,
			SaleOrderBVO.CASTUNITID,
			// ��Ʒ�������ȼ���˰��
			SaleOrderBVO.BLARGESSFLAG,
			SaleOrderBVO.CQUALITYLEVELID,
			SaleOrderBVO.CTAXCODEID,

			// ��������������������
			SaleOrderBVO.NASTNUM,
			SaleOrderBVO.NNUM,

			// ���۵�λ���������ۻ����ʡ�˰��
			SaleOrderBVO.NQTUNITNUM,
			SaleOrderBVO.NTAXRATE,
			// ����˰���ۡ�����˰���ۡ�����˰���ۡ�����˰���ۡ�
			SaleOrderBVO.NORIGTAXPRICE,
			SaleOrderBVO.NORIGPRICE,
			SaleOrderBVO.NORIGTAXNETPRICE,
			SaleOrderBVO.NORIGNETPRICE,
			// ��˰���� ����˰���ۡ���˰���ۡ���˰����
			SaleOrderBVO.NQTORIGTAXPRICE,
			SaleOrderBVO.NQTORIGPRICE,
			SaleOrderBVO.NQTORIGTAXNETPRC,
			SaleOrderBVO.NQTORIGNETPRICE,
			// ��˰����˰�ϼơ���˰���
			SaleOrderBVO.NORIGMNY,
			SaleOrderBVO.NORIGTAXMNY,
			// ����˰��
			SaleOrderBVO.NTAX,
			// �ۿ۶�۱�����
			SaleOrderBVO.NORIGDISCOUNT,
			SaleOrderBVO.NEXCHANGERATE,
			// �޶�����
			SaleOrderBVO.VBREVISEREASON,
			// �����֯��������֯
			SaleOrderBVO.CSENDSTOCKORGVID,
			SaleOrderBVO.CTRAFFICORGVID,
			// �����ֿ�
			SaleOrderBVO.CSENDSTORDOCID,
			// ���������֯��Ӧ����֯
			SaleOrderBVO.CSETTLEORGVID,
			SaleOrderBVO.CARORGVID,
			// ��������
			SaleOrderBVO.CPROFITCENTERVID,
			// ������������
			SaleOrderBVO.CSPROFITCENTERVID,
			// ��������������
			SaleOrderBVO.DSENDDATE,
			SaleOrderBVO.DRECEIVEDATE,
			// �������ҵ������ջ����ҵ�������˰���ҵ������������͡�����ó�ס�
			SaleOrderBVO.CSENDCOUNTRYID,
			SaleOrderBVO.CRECECOUNTRYID,
			SaleOrderBVO.CTAXCOUNTRYID,
			SaleOrderBVO.FBUYSELLFLAG,
			SaleOrderBVO.BTRIATRADEFLAG,
			// �б�ע
			SaleOrderBVO.VROWNOTE, SaleOrderBVO.CMFFILEID,
			SaleOrderBVO.NMFFILEPRICE,
			/** �̶��������� */
			SaleOrderBVO.CPRODUCTORID, SaleOrderBVO.CPROJECTID,
			SaleOrderBVO.CVENDORID, SaleOrderBVO.CMFFILEID,
			/** �������� */
			SaleOrderBVO.VFREE1, SaleOrderBVO.VFREE2, SaleOrderBVO.VFREE3,
			SaleOrderBVO.VFREE4, SaleOrderBVO.VFREE5, SaleOrderBVO.VFREE6,
			SaleOrderBVO.VFREE7, SaleOrderBVO.VFREE9, SaleOrderBVO.VFREE8,
			SaleOrderBVO.VFREE10,
			/** -------�����Զ�����------ **/
			SaleOrderBVO.VBDEF1, SaleOrderBVO.VBDEF2, SaleOrderBVO.VBDEF3,
			SaleOrderBVO.VBDEF4, SaleOrderBVO.VBDEF5, SaleOrderBVO.VBDEF6,
			SaleOrderBVO.VBDEF7, SaleOrderBVO.VBDEF8, SaleOrderBVO.VBDEF9,
			SaleOrderBVO.VBDEF10, SaleOrderBVO.VBDEF11, SaleOrderBVO.VBDEF12,
			SaleOrderBVO.VBDEF13, SaleOrderBVO.VBDEF14, SaleOrderBVO.VBDEF15,
			SaleOrderBVO.VBDEF16, SaleOrderBVO.VBDEF17, SaleOrderBVO.VBDEF18,
			SaleOrderBVO.VBDEF19, SaleOrderBVO.VBDEF20,

	};

	// ���������ε��ݿ��޶��ֶ�
	public static final String[] EDITABLEITEMKEYFOROUT = new String[] {

			// ��������������
			SaleOrderBVO.NASTNUM,
			SaleOrderBVO.NNUM,
			// ���۵�λ������
			SaleOrderBVO.NQTUNITNUM,
			// ��˰���ۡ���˰���ۡ���˰���ۡ���˰����
			SaleOrderBVO.NQTORIGPRICE,
			SaleOrderBVO.NQTORIGTAXPRICE,
			SaleOrderBVO.NQTORIGNETPRICE,
			SaleOrderBVO.NQTORIGTAXNETPRC,
			// ����˰���ۡ�����˰����
			SaleOrderBVO.NORIGTAXPRICE,
			SaleOrderBVO.NORIGPRICE,
			// ����˰���ۡ�����˰���ۡ�
			SaleOrderBVO.NORIGTAXNETPRICE,
			SaleOrderBVO.NORIGNETPRICE,
			// ��˰�ϼơ�
			SaleOrderBVO.NORIGTAXMNY,
			// ��˰��˰��ۿ۶�
			SaleOrderBVO.NORIGMNY,
			SaleOrderBVO.NTAX,
			// �۱�����
			SaleOrderBVO.NEXCHANGERATE,
			// ��ע
			SaleOrderBVO.VROWNOTE,
			SaleOrderHVO.VNOTE,
			/** -------��ͷ�Զ�����------ **/
			SaleOrderHVO.VDEF1,
			SaleOrderHVO.VDEF2,
			SaleOrderHVO.VDEF3,
			SaleOrderHVO.VDEF4,
			SaleOrderHVO.VDEF5,
			SaleOrderHVO.VDEF6,
			SaleOrderHVO.VDEF7,
			SaleOrderHVO.VDEF8,
			SaleOrderHVO.VDEF9,
			SaleOrderHVO.VDEF10,
			SaleOrderHVO.VDEF11,
			SaleOrderHVO.VDEF12,
			SaleOrderHVO.VDEF13,
			SaleOrderHVO.VDEF14,
			SaleOrderHVO.VDEF15,
			SaleOrderHVO.VDEF16,
			SaleOrderHVO.VDEF17,
			SaleOrderHVO.VDEF18,
			SaleOrderHVO.VDEF19,
			SaleOrderHVO.VDEF20,
			// ����ܲ���� ����
			"vbilllcode",
			/** -------�����Զ�����------ **/
			SaleOrderBVO.VBDEF1,
			SaleOrderBVO.VBDEF2,
			SaleOrderBVO.VBDEF3,
			SaleOrderBVO.VBDEF4,
			SaleOrderBVO.VBDEF5,
			SaleOrderBVO.VBDEF6,
			SaleOrderBVO.VBDEF7,
			SaleOrderBVO.VBDEF8,
			SaleOrderBVO.VBDEF9,
			SaleOrderBVO.VBDEF10,
			SaleOrderBVO.VBDEF11,
			SaleOrderBVO.VBDEF12,
			SaleOrderBVO.VBDEF13,
			SaleOrderBVO.VBDEF14,
			SaleOrderBVO.VBDEF15,
			SaleOrderBVO.VBDEF16,
			SaleOrderBVO.VBDEF17,
			SaleOrderBVO.VBDEF18,
			SaleOrderBVO.VBDEF19,
			SaleOrderBVO.VBDEF20,
			// ����ܲ���ӿ��޶�������
			/******************** ��ͷ ******************************************/
			"jzrq",
			"jhq",
			"zcjhq",
			"dlfl",
			"djqz",
			"cmarbaseclassid",
			"chvendorid",
			"agdef1",
			"agdef2",
			"agdef3",
			"agdef4",
			"agdef5",
			"agdef6",
			"agdef7",
			"agdef8",
			"agdef9",
			"agdef10",
			"agdef11",
			"exchange_rate ",
			"buyccurrencyid",
			"sqg",
			"vbilllcode",
			"signinstructions",
			"dlxybz",
			"hblob1",
			"hblob2",
			"hblob3",
			"hblob4",
			"hblob5",
			/******************** ���� ******************************************/
			// ����ܲ���ӿ����޶�������
			"bidding_no", "project_name", "project_content",
			"supplier_requirements", "sumplannum", "sumnum", "typeplan",
			"typebuy", "ratereply", "bid_evaluation", "combination_standard",
			"procurementplan", "num_bj", "swq_bj", "offer_type",
			"qualification_way", "payment", "business_types", "procurement",
			"estimate", "delivery_term", "requirements", "supplier_code",
			"supplier", "no_delegate", "swqq_delegate", "ccategoryid",
			"projectexecutor", "no_pasdoc", "customer_no", "customer_name",
			"plan_num", "request_date", "host_name", "material", "rated_life",
			"manufacturer", "plan_pricea", "plan_priceb", "plansum_pricea",
			"plansum_priceb", "factory_plan", "factory_code", "factory_name",
			"plan", "application_no", "application_line", "number_code",
			"tally", "plan_time", "freight", "added_tax", "exchangeb_rate",
			"currency", "unit_leaders", "unit_dales", "unit_charge", "epein",
			"slysfs", "xlysfs", "ysfs", "htqdsj", "bjwlmc", "materialnamex",
			"chand", "cgjg", "csjhq", "jiaohuodate", "thicknessmm",
			"thicknessinch", "widthmm", "widthinch", "lengthmm", "lengthinch",
			"specificationmm", "specificationinch", "thicknesstolerance",
			"widthtolerance", "lengthtolerance", "sizekg", "unitweight",
			"minunitweight", "maxunitweight", "nmorerate", "nlessrate", "pcs",
			"tolerancepcs", "promethod", "agdef1", "agdef2", "agdef3",
			"agdef4", "agdef5", "agdef6", "agdef7", "agdef8", "agdef9",
			"agdef10", "agdef11", "agproductstandard", "agtexture", "mrid",
			"mrlineid", "req_group", "unit_pric", "code_facty", "ggxhhth",
			"cgjshj", "cgjgbhs", "cgse",

	};

	// ���д��ͷ�ֶ�
	public static final String[] HEADREWRITEMKEY = new String[] {
			/** ---------- ��ͷ ---------- */
			SaleOrderHVO.VREVISEREASON,
			SaleOrderHVO.VNOTE,
			SaleOrderHVO.TREVISETIME,
			/** -------��ͷ�Զ�����------ **/
			SaleOrderHVO.VDEF1,
			SaleOrderHVO.VDEF2,
			SaleOrderHVO.VDEF3,
			SaleOrderHVO.VDEF4,
			SaleOrderHVO.VDEF5,
			SaleOrderHVO.VDEF6,
			SaleOrderHVO.VDEF7,
			SaleOrderHVO.VDEF8,
			SaleOrderHVO.VDEF9,
			SaleOrderHVO.VDEF10,
			SaleOrderHVO.VDEF11,
			SaleOrderHVO.VDEF12,
			SaleOrderHVO.VDEF13,
			SaleOrderHVO.VDEF14,
			SaleOrderHVO.VDEF15,
			SaleOrderHVO.VDEF16,
			SaleOrderHVO.VDEF17,
			SaleOrderHVO.VDEF18,
			SaleOrderHVO.VDEF19,
			SaleOrderHVO.VDEF20,
			/** -------�޶���Ϣ------- */
			SaleOrderHVO.CREVISERID,
			SaleOrderHVO.APPROVER,
			SaleOrderHVO.IVERSION,
			// ����ܲ ����Ԫ����
			"jzrq", "jhq", "zcjhq", "dlfl",
			"djqz",
			"cmarbaseclassid",
			"chvendorid",
			"agdef1",
			"agdef2",
			"agdef3",
			"agdef4",
			"agdef5",
			"agdef6",
			"agdef7",
			"agdef8",
			"agdef9",
			"agdef10",
			"agdef11",
			"exchange_rate ",
			"buyccurrencyid",
			"sqg",
			"vbilllcode",
			"signinstructions",
			"dlxybz",
			"hblob1",
			"hblob2",
			"hblob3",
			"hblob4",
			"hblob5",
			// ����ܲ 2018-04-02��ͷ�ֶ�ȫ���Ի�д
			"approver", "badvfeeflag", "barsettleflag", "bcooptopoflag",
			"bcostsettleflag", "bfreecustflag", "billmaker", "binvoicendflag",
			"boffsetflag", "boutendflag", "bpocooptomeflag", "bpreceiveflag",
			"bsendendflag", "carsubtypeid", "cbalancetypeid", "cbillsrcid",
			"cbiztypeid", "cchanneltypeid", "ccustbankaccid", "ccustbankid",
			"ccustomerid", "cdeptid", "cdeptvid", "cemployeeid", "cfreecustid",
			"chistrantypeid", "chreceiveaddid", "chreceivecustid",
			"cinvoicecustid", "corderhistoryid", "corigcurrencyid",
			"cpaytermid", "creationtime", "creator", "creviserid",
			"csaleorderid", "ctradewordid", "ctransporttypeid", "ctrantypeid",
			"dbilldate", "dmakedate", "fpfstatusflag", "fstatusflag",
			"iprintcount", "iversion", "modifiedtime", "modifier",
			"ndiscountrate", "nlrgtotalorigmny", "npreceivemny",
			"npreceivequota", "npreceiverate", "nreceivedmny", "ntotalnum",
			"ntotalorigmny", "ntotalorigsubmny", "ntotalpiece", "ntotalvolume",
			"ntotalweight", "pk_group", "pk_org", "pk_org_v", "taudittime",
			"trevisetime", "vbillcode", "vbillsrctype", "vcooppohcode",
			"vcreditnum", "vdef1", "vdef10", "vdef11", "vdef12", "vdef13",
			"vdef14", "vdef15", "vdef16", "vdef17", "vdef18", "vdef19",
			"vdef2", "vdef20", "vdef3", "vdef4", "vdef5", "vdef6", "vdef7",
			"vdef8", "vdef9", "vhistrantypecode", "vnote", "vrevisereason",
			"vtrantypecode", };

	// �����д
	public static final String[] BODYREWRITEMKEY = new String[] {

			/** ---------- ���� ---------- */
			// ������λ
			// ��λ�����۵�λ
			SaleOrderBVO.CASTUNITID,
			SaleOrderBVO.CQTUNITID,
			// ������ ���۵�λ������
			SaleOrderBVO.VCHANGERATE,
			SaleOrderBVO.VQTUNITRATE,
			// ����
			// �۱����ʡ����ű��һ��ʡ�ȫ�ֱ��һ���
			SaleOrderBVO.NEXCHANGERATE,
			SaleOrderBVO.NGROUPEXCHGRATE,
			SaleOrderBVO.NGLOBALTAXMNY,
			// ����˰���
			SaleOrderBVO.NCALTAXMNY,
			// ˰��
			SaleOrderBVO.NTAXRATE,
			// �ۿ���
			SaleOrderBVO.NORIGDISCOUNT,

			// ���������������
			SaleOrderBVO.NVOLUME,
			SaleOrderBVO.NWEIGHT,
			SaleOrderBVO.NPIECE,

			// ���������֯��������֯��
			SaleOrderBVO.CSENDSTOCKORGID,
			SaleOrderBVO.CSENDSTOCKORGVID,
			SaleOrderBVO.CTRAFFICORGID,
			SaleOrderBVO.CTRAFFICORGVID,
			// ���������֯
			SaleOrderBVO.CSETTLEORGVID,
			SaleOrderBVO.CSETTLEORGID,
			// �����������ġ�������������
			SaleOrderBVO.CSPROFITCENTERID,
			SaleOrderBVO.CSPROFITCENTERVID,
			SaleOrderBVO.CPROFITCENTERID,
			SaleOrderBVO.CPROFITCENTERVID,
			// �����ۿۡ���Ʒ�ۿ�
			SaleOrderBVO.NDISCOUNTRATE,
			SaleOrderBVO.NITEMDISCOUNTRATE,
			// ����
			// ����������������������
			SaleOrderBVO.NASTNUM,
			SaleOrderBVO.NNUM,
			SaleOrderBVO.NQTUNITNUM,

			// ����
			// ��˰���ۡ���˰���ۡ���˰���ۡ���˰����
			SaleOrderBVO.NQTORIGPRICE,
			SaleOrderBVO.NQTORIGTAXPRICE,
			SaleOrderBVO.NQTORIGNETPRICE,
			SaleOrderBVO.NQTORIGTAXNETPRC,

			// ����λ��˰���ۡ�����λ��˰���ۡ�����λ��˰���ۡ�����λ��˰����
			SaleOrderBVO.NORIGTAXPRICE,
			SaleOrderBVO.NORIGPRICE,
			SaleOrderBVO.NORIGTAXNETPRICE,
			SaleOrderBVO.NORIGNETPRICE,
			// ������˰���ۡ����Һ�˰���ۡ�������˰���ۡ����Һ�˰���ۡ�
			SaleOrderBVO.NQTPRICE,
			SaleOrderBVO.NQTTAXPRICE,
			SaleOrderBVO.NQTNETPRICE,
			SaleOrderBVO.NQTTAXNETPRICE,
			// ����λ���Һ�˰���ۡ�����λ������˰���ۡ�����λ���Һ�˰���ۡ�����λ������˰���ۡ�
			SaleOrderBVO.NTAXPRICE,
			SaleOrderBVO.NPRICE,
			SaleOrderBVO.NTAXNETPRICE,
			SaleOrderBVO.NNETPRICE,
			// ѯ��ԭ�Һ�˰���ۡ�ѯ��ԭ����˰����
			SaleOrderBVO.NASKQTORIGTAXPRC,
			SaleOrderBVO.NASKQTORIGPRICE,
			// ���
			// ��˰����˰�ϼơ�˰��ۿ۶�
			SaleOrderBVO.NORIGMNY,
			SaleOrderBVO.NORIGTAXMNY,
			SaleOrderBVO.NTAX,
			SaleOrderBVO.NORIGDISCOUNT,
			// ������˰�����Ҽ�˰�ϼơ�����˰������ۿ۶
			SaleOrderBVO.NMNY,
			SaleOrderBVO.NTAXMNY,
			SaleOrderBVO.NTAX,
			SaleOrderBVO.NDISCOUNT,
			// ���ű�����˰�����ű��Ҽ�˰�ϼơ�ȫ�ֱ�����˰��ȫ�ֱ��Ҽ�˰�ϼ�
			SaleOrderBVO.NGROUPMNY,
			SaleOrderBVO.NGROUPTAXMNY,
			SaleOrderBVO.NGLOBALMNY,
			SaleOrderBVO.NGLOBALTAXMNY,
			// �۸���أ��۸���ɡ��۸����
			SaleOrderBVO.CPRICEFORMID,
			SaleOrderBVO.CPRICEITEMID,
			SaleOrderBVO.CPRICEITEMTABLEID,
			SaleOrderBVO.CPRICEPOLICYID,
			// ��ó������ҵ������ ����ܲ 2018-03-29
			"bidding_no", "project_name", "project_content",
			"supplier_requirements", "sumplannum", "sumnum", "typeplan",
			"typebuy", "ratereply", "bid_evaluation", "combination_standard",
			"procurementplan", "num_bj", "swq_bj", "offer_type",
			"qualification_way", "payment", "business_types", "procurement",
			"estimate", "delivery_term", "requirements", "supplier_code",
			"supplier", "no_delegate", "swqq_delegate", "ccategoryid",
			"projectexecutor", "no_pasdoc", "customer_no", "customer_name",
			"plan_num", "request_date", "host_name", "material", "rated_life",
			"manufacturer", "plan_pricea", "plan_priceb", "plansum_pricea",
			"plansum_priceb", "factory_plan", "factory_code", "factory_name",
			"plan", "application_no",
			"application_line",
			"number_code",
			"tally",
			"plan_time",
			"freight",
			"added_tax",
			"exchangeb_rate",
			"currency",
			"unit_leaders",
			"unit_dales",
			"unit_charge",
			"epein",
			"slysfs",
			"xlysfs",
			"ysfs",
			"htqdsj",
			"bjwlmc",
			"materialnamex",
			"chand",
			"cgjg",
			"csjhq",
			"jiaohuodate",
			"thicknessmm",
			"thicknessinch",
			"widthmm",
			"widthinch",
			"lengthmm",
			"lengthinch",
			"specificationmm",
			"specificationinch",
			"thicknesstolerance",
			"widthtolerance",
			"lengthtolerance",
			"sizekg",
			"unitweight",
			"minunitweight",
			"maxunitweight",
			"nmorerate",
			"nlessrate",
			"pcs",
			"tolerancepcs",
			"promethod",
			"agdef1",
			"agdef2",
			"agdef3",
			"agdef4",
			"agdef5",
			"agdef6",
			"agdef7",
			"agdef8",
			"agdef9",
			"agdef10",
			"agdef11",
			"agproductstandard",
			"agtexture",
			"mrid",
			"mrlineid",
			"req_group",
			"unit_pric",
			"code_facty",
			"ggxhhth",
			"cgjshj",
			"cgjgbhs",
			"cgse",
			// 2018-04-02���б���ȫ����д
			"barrangedflag", "bbarsettleflag", "bbcostsettleflag", "bbindflag",
			"bbinvoicendflag", "bboutendflag", "bbsendendflag",
			"bbsettleendflag", "bdiscountflag", "bjczxsflag", "blaborflag",
			"blargessflag", "blrgcashflag", "bprerowcloseflag",
			"btriatradeflag", "carorgid", "carorgvid", "carrangepersonid",
			"castunitid", "cbindsrcid", "cbuylargessactid", "cbuylargessid",
			"cbuypromottypeid", "cctmanagebid", "cctmanageid", "ccurrencyid",
			"ccustmaterialid", "cexchangesrcretid", "cfactoryid", "cfirstbid",
			"cfirstid", "clargesssrcid", "cmaterialid", "cmaterialvid",
			"cmffileid", "corderhistorybid", "corigareaid", "corigcountryid",
			"cprcpromottypeid", "cpriceformid", "cpriceitemid",
			"cpriceitemtableid", "cpricepolicyid", "cpricepromtactid",
			"cprodlineid", "cproductorid", "cprofitcenterid",
			"cprofitcentervid", "cprojectid", "cpromotpriceid", "cqtunitid",
			"cqualitylevelid", "crececountryid", "creceiveadddocid",
			"creceiveaddrid", "creceiveareaid", "creceivecustid",
			"cretpolicyid", "cretreasonid", "crowno", "csaleorderbid",
			"csaleorderid", "csendcountryid", "csendstockorgid",
			"csendstockorgvid", "csendstordocid", "csettleorgid",
			"csettleorgvid", "csprofitcenterid", "csprofitcentervid",
			"csrcbid", "csrcid", "ctaxcodeid", "ctaxcountryid",
			"ctrafficorgid", "ctrafficorgvid", "cunitid", "cvendorid",
			"dbilldate", "dreceivedate", "dsenddate", "fbuysellflag",
			"flargesstypeflag", "fretexchange", "frowstatus", "ftaxtypeflag",
			"naccprice", "narrangemonum", "narrangepoappnum", "narrangeponum",
			"narrangescornum", "narrangetoappnum", "narrangetoornum",
			"naskqtorignetprice", "naskqtorigprice", "naskqtorigtaxprc",
			"naskqtorigtxntprc", "nastnum", "nbforigsubmny", "ncaltaxmny",
			"ndiscount", "ndiscountrate", "nexchangerate", "nglobalexchgrate",
			"nglobalmny", "nglobaltaxmny", "ngroupexchgrate", "ngroupmny",
			"ngrouptaxmny", "nitemdiscountrate", "nlargessmny",
			"nlargesstaxmny", "nmffileprice", "nmny", "nnetprice", "nnum",
			"norigdiscount", "norigmny", "norignetprice", "norigprice",
			"norigsubmny", "norigtaxmny", "norigtaxnetprice", "norigtaxprice",
			"npiece", "nprice", "nqtnetprice", "nqtorignetprice",
			"nqtorigprice", "nqtorigtaxnetprc", "nqtorigtaxprice", "nqtprice",
			"nqttaxnetprice", "nqttaxprice", "nqtunitnum", "ntax", "ntaxmny",
			"ntaxnetprice", "ntaxprice", "ntaxrate", "ntotalarmny",
			"ntotalarnum", "ntotalcostnum", "ntotalestarmny", "ntotalestarnum",
			"ntotalinvoicenum", "ntotalnotoutnum", "ntotaloutnum",
			"ntotalreturnnum", "ntotalrushnum", "ntotalsendnum",
			"ntotalsignnum", "ntotaltradenum", "ntranslossnum", "nvolume",
			"nweight", "pk_batchcode", "pk_group", "pk_org", "srcbts",
			"srcorgid", "srcts", "tlastarrangetime", "vbatchcode", "vbdef1",
			"vbdef10", "vbdef11", "vbdef12", "vbdef13", "vbdef14", "vbdef15",
			"vbdef16", "vbdef17", "vbdef18", "vbdef19", "vbdef2", "vbdef20",
			"vbdef3", "vbdef4", "vbdef5", "vbdef6", "vbdef7", "vbdef8",
			"vbdef9", "vbrevisereason", "vchangerate", "vclosereason",
			"vctcode", "vcttype", "vcustombillcode", "vfirstcode",
			"vfirstrowno", "vfirsttrantype", "vfirsttype", "vfree1", "vfree10",
			"vfree2", "vfree3", "vfree4", "vfree5", "vfree6", "vfree7",
			"vfree8", "vfree9", "vqtunitrate", "vreturnmode", "vrownote",
			"vsrccode", "vsrcrowno", "vsrctrantype", "vsrctype",

	};

	// �ۼ���������
	public static final String[] TOTALNUMKEY = new String[] {
			// �ۼƷ����������ۼƿ�Ʊ����
			SaleOrderHistoryBVO.NTOTALSENDNUM,
			SaleOrderHistoryBVO.NTOTALINVOICENUM,
			// �ۼƳ��������� �ۼ�Ӧ��δ��������
			SaleOrderHistoryBVO.NTOTALOUTNUM,
			SaleOrderHistoryBVO.NTOTALNOTOUTNUM,
			// �ۼ�ǩ�������� �ۼ�;������
			SaleOrderHistoryBVO.NTOTALSIGNNUM,
			SaleOrderHistoryBVO.NTRANSLOSSNUM,
			// �ۼƳ���Գ��������ۼ��ݹ�Ӧ������
			SaleOrderHistoryBVO.NTOTALRUSHNUM,
			SaleOrderHistoryBVO.NTOTALESTARNUM,
			// �ۼ�ȷ��Ӧ���������ۼƳɱ���������
			SaleOrderHistoryBVO.NTOTALARNUM,
			SaleOrderHistoryBVO.NTOTALCOSTNUM,
			// �ۼ��ݹ�Ӧ�ս� �ۼ�ȷ��Ӧ�ս��
			SaleOrderHistoryBVO.NTOTALESTARMNY,
			SaleOrderHistoryBVO.NTOTALARMNY,
			// �ۼư���ί�ⶩ���������ۼư����빺������
			SaleOrderHistoryBVO.NARRANGESCORNUM,
			SaleOrderHistoryBVO.NARRANGEPOAPPNUM,
			// �ۼư��ŵ��������������ۼư��ŵ�����������
			SaleOrderHistoryBVO.NARRANGETOORNUM,
			SaleOrderHistoryBVO.NARRANGETOAPPNUM,
			// �ۼư������������������ۼư��Ųɹ���������
			SaleOrderHistoryBVO.NARRANGEMONUM,
			SaleOrderHistoryBVO.NARRANGEPONUM,
			// �ۼƷ�����Ʒ�� �ۼ��˻�����
			SaleOrderHistoryBVO.NTOTALRETURNNUM,
			SaleOrderHistoryBVO.NTOTALTRADENUM };

	// �����ֶ�
	// �ۼ���������
	public static final String[] PRICE = new String[] {

			// ��˰���ۡ���˰���ۡ���˰���ۡ���˰����
			SaleOrderBVO.NQTORIGPRICE,
			SaleOrderBVO.NQTORIGTAXPRICE,
			SaleOrderBVO.NQTORIGNETPRICE,
			SaleOrderBVO.NQTORIGTAXNETPRC,
			// ����λ��˰���ۡ�����λ��˰���ۡ�����λ��˰���ۡ�����λ��˰����
			SaleOrderBVO.NORIGTAXPRICE, SaleOrderBVO.NORIGPRICE,
			SaleOrderBVO.NORIGTAXNETPRICE,
			SaleOrderBVO.NORIGNETPRICE,
			// ������˰���ۡ����Һ�˰���ۡ�������˰���ۡ����Һ�˰���ۡ�
			SaleOrderBVO.NQTPRICE, SaleOrderBVO.NQTTAXPRICE,
			SaleOrderBVO.NQTNETPRICE, SaleOrderBVO.NQTTAXNETPRICE,
			// ����λ���Һ�˰���ۡ�����λ������˰���ۡ�����λ���Һ�˰���ۡ�����λ������˰���ۡ�
			SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NPRICE,
			SaleOrderBVO.NTAXNETPRICE, SaleOrderBVO.NNETPRICE,
			// ѯ��ԭ�Һ�˰���ۡ�ѯ��ԭ����˰����
			SaleOrderBVO.NASKQTORIGTAXPRC, SaleOrderBVO.NASKQTORIGPRICE };

}
