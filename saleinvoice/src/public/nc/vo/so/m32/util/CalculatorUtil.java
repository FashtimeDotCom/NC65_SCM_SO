package nc.vo.so.m32.util;

import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m32trantype.IM32TranTypeService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32trantype.entity.M32TranTypeVO;
import nc.vo.so.m32trantype.enumeration.Adjuster;
import nc.vo.so.pub.SOConstant;

public class CalculatorUtil {

  /**
   * �ڷ�Ʊģ���ͷ���ֶ�
   */
  private static final String[] ATHEADKEYS = new String[] {
    SaleInvoiceHVO.NEXCHANGERATE, SaleInvoiceHVO.NGROUPEXCHGRATE,
    SaleInvoiceHVO.NGLOBALEXCHGRATE
  };

  private static CalculatorUtil util = new CalculatorUtil();

  private Set<String> hsAtHeadKey;

  private Set<String> hsNeedCalKey;

  /**
   * CalculatorUtil �Ĺ�����
   */
  private CalculatorUtil() {
    // ˽�л�������
  }

  public static CalculatorUtil getInstance() {
    return CalculatorUtil.util;
  }

  /**
   * ����������������ʼ���ڱ�ͷ���ֶΡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author ��ӱ�
   * @time 2010-5-5 ����02:17:37
   */
  public Set<String> getAtHeadKey() {
    if (null == this.hsAtHeadKey) {
      this.hsAtHeadKey = new HashSet<String>();
      for (String key : CalculatorUtil.ATHEADKEYS) {
        this.hsAtHeadKey.add(key);
      }
    }
    return this.hsAtHeadKey;
  }

  /**
   * ����������������ʼ�������������۽��༭�¼����ֶΡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-21 ����03:46:10
   */
  public Set<String> getNeedCalKey() {
    if (null == this.hsNeedCalKey) {
      this.hsNeedCalKey = new HashSet<String>();
      for (String key : SOConstant.STRNEEDCALKEY) {
        this.hsNeedCalKey.add(key);
      }
      this.hsNeedCalKey.add(SaleInvoiceBVO.NINVOICEDISRATE);
      this.hsNeedCalKey.add(SaleInvoiceBVO.NPRICE);
      // ȡ�ɱ�����Ҫ����ֶδ���
      this.hsNeedCalKey.add(SaleInvoiceBVO.NNETPRICE);
    }
    return this.hsNeedCalKey;
  }

  /**
   * �����������������ط�Ʊ�������͵����
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_group
   * @param trantypecode
   * @return <p>
   * @author fengjb
   * @time 2010-6-25 ����09:45:58
   */
  public boolean getChgPriceOrDiscount(String pk_group, String trantypecode) {
    boolean chgprice = false;
    // Ĭ�ϵ����ۿ�
    if (StringUtil.isEmptyWithTrim(pk_group)
        || StringUtil.isEmptyWithTrim(trantypecode)) {
      return chgprice;
    }
    M32TranTypeVO trantype = null;
    try {
      IM32TranTypeService service =
          NCLocator.getInstance().lookup(IM32TranTypeService.class);
      trantype = service.queryTranType(pk_group, trantypecode);
    }
    catch (Exception e) {
      Log.info(e);
      ExceptionUtils.wrappException(e);
    }
    // ��ѯ���ķ�Ʊ��������Ϊ��
    if (null != trantype
        && Adjuster.ADJUSTPRICE.equalsValue(trantype.getFadjuster())) {
      chgprice = true;
    }
    return chgprice;
  }
}
