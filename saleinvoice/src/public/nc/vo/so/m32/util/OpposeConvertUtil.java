package nc.vo.so.m32.util;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.trade.checkrule.VOChecker;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�Ʊ�Գ�����ת��������
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-8-24 ����10:03:56
 */
public class OpposeConvertUtil {

  private static OpposeConvertUtil instance = new OpposeConvertUtil();

  /**
   * OpposeConvertUtil �Ĺ�����
   */
  private OpposeConvertUtil() {
    // ȱʡ���췽��
  }

  /**
   * ������������������ת��������ʵ����
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-8-24 ����10:03:40
   */
  public static OpposeConvertUtil getInstance() {
    return OpposeConvertUtil.instance;
  }

  /**
   * �����������������Գ巢Ʊ�Ϸ��ԡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param voInvoice
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-28 ����10:32:30
   */
  public void checkOpposeVality(SaleInvoiceHVO voHead) {
    // ����״̬У��
    if (!BillStatus.AUDIT.equalsValue(voHead.getFstatusflag())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0067")/*@res "����ͨ���ķ�Ʊ������Գ�"*/);
    }
    // // �ϲ���ƱУ��
    // if (voHead.getBsubunitflag().booleanValue()) {
    // ExceptionUtils
    // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
    // .getStrByID("4006008_0", "04006008-0068")/*@res "�Ѿ����ó�ֵķ�Ʊ������Գ�"*/);
    // }
    // �Գ��־У��
    if (!OpposeFlag.NORMAL.equalsValue(voHead.getFopposeflag())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0069")/*@res "�Գ���Ϊ�����ķ�Ʊ������Գ�"*/);
    }

  }

  public SaleInvoiceVO convertToOpposeVO(SaleInvoiceVO voInvoice,
      LoginContext logctx, UFDate busidate) {
    // �Գ巢Ʊ�Ϸ���У��
    this.checkOpposeVality(voInvoice.getParentVO());

    SaleInvoiceHVO oppHead =
        this.convertToOpposeHVO(voInvoice.getParentVO(), logctx, busidate);

    SaleInvoiceBVO[] oppBodys =
        this.convertToOpposeBVO(voInvoice.getChildrenVO(), busidate);

    SaleInvoiceVO oppInvoice = new SaleInvoiceVO();
    oppInvoice.setParentVO(oppHead);
    oppInvoice.setChildrenVO(oppBodys);
    return oppInvoice;
  }

  /**
   * ���������������������۷�Ʊ�ӱ�VO�ĶԳ�VO
   * <p>
   * <b>����˵��</b>
   * 
   * @param childrenVO
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-28 ����11:44:19
   */
  private SaleInvoiceBVO[] convertToOpposeBVO(SaleInvoiceBVO[] childrenVO,
      UFDate busidate) {
    String[] clearKeys =
        new String[] {
          SaleInvoiceBVO.CSALEINVOICEBID, SaleInvoiceBVO.CSALEINVOICEID,
          SaleInvoiceBVO.NSHOULDOUTNUM, SaleInvoiceBVO.NTOTALOUTNUM,
          SaleInvoiceBVO.NTOTALINCOMENUM, SaleInvoiceBVO.NTOTALINCOMEMNY,
          SaleInvoiceBVO.NTOTALCOSTNUM, SaleInvoiceBVO.NTOTALPAYMNY
        };

    String[] oppKeys =
        new String[] {
          SaleInvoiceBVO.NNUM, SaleInvoiceBVO.NASTNUM,
          SaleInvoiceBVO.NQTUNITNUM, SaleInvoiceBVO.NTAX,
          SaleInvoiceBVO.NTAXMNY, SaleInvoiceBVO.NMNY,
          SaleInvoiceBVO.NCALTAXMNY, SaleInvoiceBVO.NDISCOUNT,
          SaleInvoiceBVO.NORIGTAXMNY, SaleInvoiceBVO.NORIGMNY,
          SaleInvoiceBVO.NORIGDISCOUNT, SaleInvoiceBVO.NGLOBALMNY,
          SaleInvoiceBVO.NGLOBALTAXMNY, SaleInvoiceBVO.NGROUPMNY,
          SaleInvoiceBVO.NGROUPTAXMNY, SaleInvoiceBVO.NORIGSUBMNY
        };
    int ilen = childrenVO.length;
    SaleInvoiceBVO[] oppbodys = new SaleInvoiceBVO[ilen];
    for (int i = 0; i < ilen; i++) {
      oppbodys[i] = (SaleInvoiceBVO) childrenVO[i].clone();
      // ����Ĭ��ֵ
      oppbodys[i].setDbilldate(busidate);
      oppbodys[i].setCopposesrcbid(oppbodys[i].getCsaleinvoicebid());
      // ����ֶ�ֵ
      for (String key : clearKeys) {
        oppbodys[i].setAttributeValue(key, null);
      }
      // ȡ���ֶ�ֵ
      for (String key : oppKeys) {
        UFDouble value =
            ValueUtils.getUFDouble(oppbodys[i].getAttributeValue(key));
        UFDouble oppvalue = null;
        if (!VOChecker.isEmpty(value)) {
          oppvalue = MathTool.oppose(value);
        }
        oppbodys[i].setAttributeValue(key, oppvalue);
      }
    }
    return oppbodys;
  }

  /**
   * ���������������������۷�Ʊ����VO�ĶԳ�VO��
   * <p>
   * <b>����˵��</b>
   * 
   * @param parent
   * @return <p>
   * @author ��ӱ�
   * @param logctx
   * @time 2010-4-28 ����10:55:32
   */
  private SaleInvoiceHVO convertToOpposeHVO(SaleInvoiceHVO voHead,
      LoginContext logctx, UFDate busidate) {
    SaleInvoiceHVO oppHead = (SaleInvoiceHVO) voHead.clone();
    // ���öԳ����ԭ��Ʊ��ͬ���ֶ�ֵ
    oppHead.setBtogoldtaxflag(UFBoolean.FALSE);
    oppHead.setDbilldate(busidate);
    oppHead.setCreator(logctx.getPk_loginUser());
    oppHead.setBillmaker(logctx.getPk_loginUser());
    oppHead.setIprintcount(Integer.valueOf(0));
    oppHead.setFstatusflag((Integer) BillStatus.FREE.value());
    oppHead.setFopposeflag((Integer) OpposeFlag.GENERAL.value());
    oppHead.setCopposesrcid(voHead.getCsaleinvoiceid());
    oppHead.setVopposesrccode(voHead.getVbillcode());
    // ��Ҫ��յ��ֶ�
    String[] clearKeys =
        new String[] {
          SaleInvoiceHVO.VBILLCODE, SaleInvoiceHVO.CSALEINVOICEID,
          SaleInvoiceHVO.TGOLDTAXTIME, SaleInvoiceHVO.MODIFIER,
          SaleInvoiceHVO.MODIFIEDTIME, SaleInvoiceHVO.APPROVER,
          SaleInvoiceHVO.TAUDITTIME
        };
    for (String key : clearKeys) {
      oppHead.setAttributeValue(key, null);
    }

    // ��Ҫȡ�����ֶ�
    String[] oppKeys =
        new String[] {
          SaleInvoiceHVO.NTOTALASTNUM, SaleInvoiceHVO.NTOTALORIGMNY,
          SaleInvoiceHVO.NTOTALORIGSUBMNY
        };

    for (String key : oppKeys) {
      UFDouble value = ValueUtils.getUFDouble(voHead.getAttributeValue(key));
      if (null != value) {
        UFDouble oppValue = MathTool.oppose(value);
        oppHead.setAttributeValue(key, oppValue);
      }
    }
    return oppHead;
  }
}
