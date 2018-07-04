package nc.vo.so.pub;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * ���۹�������
 * 
 * @since 6.0
 * @version 2011-12-2 ����09:03:34
 * @author fengjb
 */
public class SOConstant {

  /**
   * �����رչ����ѯ��������
   */
  public static final int CLOSEMANAGEMAXROWS = 5001;

  /**
   * ���������ű���ʶ
   */
  public static final String APPROVE = "APPROVE";

  /**
   * ���涯����ʾ
   */
  public static final String WRITE = "WRITE";

  /** ɾ��������ʾ*/
  public static final String DELETE = "DELETE";

  /** ��׼������λ */
  public static final String BD305 = "BD305";
  
  /**
   * ��׼�����λ
   */
  public static final String BD304="BD304";

  public static final String AGGFFILEVO = "aggffilevo";

  public static final String SO_BATCHCODE_PATH =
      "nc/ui/so/pub/model/so_batchcode.xml";

  public static final String[] ONHANDDLG_BODY_KEY = new String[] {
    SOItemKey.CMATERIALVID, SOItemKey.CASTUNITID, SOItemKey.VBATCHCODE,
    SOItemKey.VCHANGERATE, SOItemKey.CPROJECTID, SOItemKey.CPRODUCTORID,
    SOItemKey.CVENDORID, SOItemKey.CMFFILEID, SOItemKey.VFREE1,
    SOItemKey.VFREE2, SOItemKey.VFREE3, SOItemKey.VFREE4, SOItemKey.VFREE5,
    SOItemKey.VFREE6, SOItemKey.VFREE7, SOItemKey.VFREE8, SOItemKey.VFREE9,
    SOItemKey.VFREE10,
  };

  /**
   * ���۷�Ʊ֧�����ĵ��ֶ�
   */
  public static final String[] DELIVERYFILLENABLEDKEY = new String[] {
    // �ֿ�
    SOItemKey.CSENDSTORDOCID,
    // �������ڡ�Ѻ��Ա�������̡����͡�����
    SOItemKey.DSENDDATE, SOItemKey.CSUPERCARGOID, SOItemKey.CTRANSCUSTID,
    SOItemKey.CVEHICLETYPEID, SOItemKey.CVEHICLEID
  };

  /**
   * ���۶�����Ԥ��֧�����ĵ��ֶ�
   */
  public static final String[] FILLENABLEDKEY = new String[] {
    // ��Ʒ��־(V635����-��ܽ����Ŀ)
    SOItemKey.BLARGESSFLAG,
    // ���ʡ�
    SOItemKey.NEXCHANGERATE,
    // ˰��
    SOItemKey.NTAXRATE,
    // ��Ʒ�ۿۡ�����˰���ۡ�����˰����
    SOItemKey.NITEMDISCOUNTRATE,
    SOItemKey.NORIGTAXPRICE,
    SOItemKey.NORIGPRICE,
    // ����˰���ۡ�����˰���ۡ���˰����
    SOItemKey.NORIGTAXNETPRICE,
    SOItemKey.NORIGNETPRICE,
    SOItemKey.NQTORIGTAXPRICE,
    // ��˰���ۡ���˰���ۡ���˰����
    SOItemKey.NQTORIGPRICE,
    SOItemKey.NQTORIGTAXNETPRC,
    SOItemKey.NQTORIGNETPRICE,

    // ���������֯���ֿ⡢�������ڡ��ջ��ͻ����ջ��������ջ��ص㡢�ջ���ַ����������
    // ��Ŀ����ע
    SOItemKey.CSENDSTOCKORGVID, SOItemKey.CSENDSTORDOCID, SOItemKey.DSENDDATE,
    SOItemKey.CRECEIVECUSTID, SOItemKey.CRECEIVEAREAID,
    SOItemKey.CRECEIVEADDDOCID, SOItemKey.CRECEIVEADDRID,
    SOItemKey.DRECEIVEDATE, SOItemKey.CPROJECTID, SOItemKey.VROWNOTE
  };

  /** �ҵĶ����б�ģ��ID */
  public static final String MYORDER_BILLTEMPLET = "1005Z8100000000054EZ";

  /** �ۿ�Ĭ��ֵ100 */
  public static final UFDouble ONEHUNDRED = new UFDouble(100);

  /** ����ʧ�� */
  public static final String OPRETAION_FAIL = "1";

  /** �����ɹ� */
  public static final String OPRETAION_SUCCESS = "0";

  /** �ҵĶ�����ϸģ��ID */
  public static final String ORDERDTL_BILLTEMPLET = "1005Z8100000000058A4";

  /** ��ָ��� */
  public static final String POINT = ".";

  /** ȡȨ����Ҫ�ĳ���(��������) */
  public static final String PSNDOC = "psndoc";

  /**
   * ���۷�Ʊ֧�����ĵ��ֶ�
   */
  public static final String[] SALEINVOICEFILLENABLEDKEY = new String[] {
    // ����˰���ۡ�����˰����
    SOItemKey.NORIGTAXPRICE,
    SOItemKey.NORIGPRICE,
    // ����˰���ۡ�����˰���ۡ���˰����
    SOItemKey.NORIGTAXNETPRICE, SOItemKey.NORIGNETPRICE,
    SOItemKey.NQTORIGTAXPRICE,
    // ��˰���ۡ���˰���ۡ���˰����
    SOItemKey.NQTORIGPRICE, SOItemKey.NQTORIGTAXNETPRC,
    SOItemKey.NQTORIGNETPRICE,

    // �ֿ�
    SOItemKey.CSENDSTORDOCID,
  };

  /** ȡȨ����Ҫ�ĳ���(��ԴȨ��ʵ�����) */
  public static final String SCMDEFAULT = "SCMDefault";

  /**
   * ��Ҫ�������۽���㷨���ֶ�
   */
  public static final String[] STRNEEDCALKEY = new String[] {
    // ��������������������
    SOItemKey.NASTNUM,
    SOItemKey.NNUM,
    SOItemKey.VCHANGERATE,
    // ��λ
    SOItemKey.CASTUNITID,
    // ���۵�λ���������ۻ����ʡ�˰��
    SOItemKey.NQTUNITNUM,
    SOItemKey.VQTUNITRATE,
    SOItemKey.NTAXRATE,
    /**
     * add by lyw 2017-6-9
     * ������ʡ�����ȡ�������ʡ��ɹ��۸񡢲ɹ�����
     */
    SOItemKey.DLFL,
    SOItemKey.DJQZ,
    SOItemKey.EXCHANGE_RATE,
    SOItemKey.CGJG,
    SOItemKey.BUYCCURRENCYID,
    // �����ۿۡ���Ʒ�ۿۡ�����˰���ۡ�����˰����
    SOItemKey.NDISCOUNTRATE,
    SOItemKey.NITEMDISCOUNTRATE,
    SOItemKey.NORIGTAXPRICE,
    SOItemKey.NORIGPRICE,
    // ����˰���ۡ�����˰���ۡ���˰����
    SOItemKey.NORIGTAXNETPRICE,
    SOItemKey.NORIGNETPRICE,
    SOItemKey.NQTORIGTAXPRICE,
    // ��˰���ۡ���˰���ۡ���˰����
    SOItemKey.NQTORIGPRICE,
    SOItemKey.NQTORIGTAXNETPRC,
    SOItemKey.NQTORIGNETPRICE,
    // ˰���˰����˰�ϼ�
    SOItemKey.NTAX, SOItemKey.NORIGMNY, SOItemKey.NORIGTAXMNY, SOItemKey.NMNY,
    SOItemKey.NTAXMNY,
    // �ۿ۶�۱�����
    SOItemKey.NORIGDISCOUNT, SOItemKey.NEXCHANGERATE,
    // ���ű�λ�һ��ʡ�ȫ�ֱ�λ�һ��ʡ���˰��𡢼�˰���
    SOItemKey.NGROUPEXCHGRATE, SOItemKey.NGLOBALEXCHGRATE,
    SOItemKey.FTAXTYPEFLAG, SOItemKey.NCALTAXMNY
  };

  /** ��������Ĭ��ֵ */
  public static final UFDate SYSENDDATE = new UFDate("2099-12-31");

  /** ����չʾ�ĵ������ڱ��� */
  public static final String VAR_DBILLDATE = "var_dbilldate";

  /** ����չʾ��ҵ�����ڱ��� */
  // public static final String VAR_DBUSIDATE = "var_dbusidate";

  /** �����Զ�����ǰ׺ */
  public static final String VBDEF = "vbdef";

  /** ��ͷ�Զ�����ǰ׺ */
  public static final String VDEF = "vdef";

}
