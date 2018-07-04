package nc.vo.so.pub.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.bd.vat.VATBDService;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoByTaxcodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.itf.scmpub.reference.uap.bd.vat.ZeroTaxCodeEnum;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.SOKeyRela;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.uap.taxcode.TaxType;

import org.apache.commons.lang.StringUtils;

/**
 * ���۹����۵���Ԥ��������������Ʊ������������ȡ˰��Ϣ����
 * 
 * @since 6.0
 * @version 2012-2-5 ����02:16:18
 * @author ����
 */
public class SOTaxInfoRule {

  private IKeyRela keyRela;

  private IKeyValue keyValue;

  /**
   * ˰�ʻ��߼�˰���ı���
   */
  private List<Integer> listchgrow;

  public SOTaxInfoRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
    this.keyRela = new SOKeyRela();
  }

  public SOTaxInfoRule(IKeyValue keyValue, IKeyRela keyRela) {
    this.keyValue = keyValue;
    this.keyRela = keyRela;
  }

  /**
   * ������˰����Ϣ���ȡ˰�ʻ��߼�˰���ı��У�
   * �����˰��ı���õ��ô˷�������Ϊ�����ж��ı�
   * 
   * @return
   */
  public int[] getTaxChangeRows() {
    if (null == this.listchgrow || this.listchgrow.size() == 0) {
      return new int[0];
    }
    int[] chgrows = new int[this.listchgrow.size()];
    int i = 0;
    for (Integer chgrow : this.listchgrow) {
      chgrows[i] = chgrow;
      i++;
    }
    return chgrows;
  }
  
  /**
   * ���� ������VAT˰�����Ϣ����Ʊ�ͻ��ڱ��壩
   * 
   * @param rows
   */
  public void setDeliveryTaxInfo(int[] rows) {
    // ��Ҫ����˰�����
    int[] zerorows = this.getDeliveryZeroTaxCodeRows(rows);
    // �����ж���ͬ��˾
    VATInfoVO vo = this.initZeroTaxCode();
    VATInfoVO[] zerotaxs = new VATInfoVO[zerorows.length];
    for (int i = 0; i < zerorows.length; i++) {
      zerotaxs[i] = vo;
    }
    this.setTaxInfo(zerotaxs, zerorows, false);
    if (zerorows.length != rows.length) {
      int[] nozerorows = ArrayUtil.subArrays(rows, zerorows);
      VATInfoQueryVO[] qryvos = this.getVATInfoQueryVOByBodyPos(nozerorows);
      VATInfoVO[] vatinfos = VATBDService.queryCustVATInfo(qryvos);
      // ����VAT˰��Ϣ
      this.setTaxInfo(vatinfos, nozerorows, false);
    }
  }

  /**
   * ���ñ��۵���Ԥ������������ȡ˰�����ڱ���ĵ���VAT˰�����Ϣ
   * 
   * @param rows
   */
  public void setTaxInfoByBodyPos(int[] rows) {
    // ��Ҫ����0˰�����
    int[] zerorows = this.getZeroTaxCodeRows(rows);
    // �����ж���ͬ��˾
    VATInfoVO vo = this.initZeroTaxCode();
    VATInfoVO[] zerotaxs = new VATInfoVO[zerorows.length];
    for (int i = 0; i < zerorows.length; i++) {
      zerotaxs[i] = vo;
    }
    this.setTaxInfo(zerotaxs, zerorows, false);
    if (zerorows.length != rows.length) {
      int[] nozerorows = ArrayUtil.subArrays(rows, zerorows);
      VATInfoQueryVO[] qryvos = this.getVATInfoQueryVOByBodyPos(nozerorows);
      VATInfoVO[] vatinfos = VATBDService.queryCustVATInfo(qryvos);
      // ����VAT˰��Ϣ
      this.setTaxInfo(vatinfos, nozerorows, false);
    }
  }

  /**
   * ���ñ��۵���Ԥ������������ȡ˰�����ڱ���ĵ���VAT˰�����Ϣ
   * 
   * @param rows
   */
  public void setOnlyTaxCodeByBodyPos(int[] rows) {
    // ��Ҫ����0˰�����
    int[] zerorows = this.getZeroTaxCodeRows(rows);
    // �����ж���ͬ��˾
    VATInfoVO vo = this.initZeroTaxCode();
    VATInfoVO[] zerotaxs = new VATInfoVO[zerorows.length];
    for (int i = 0; i < zerorows.length; i++) {
      zerotaxs[i] = vo;
    }
    this.setTaxInfo(zerotaxs, zerorows, true);
    if (zerorows.length != rows.length) {
      int[] nozerorows = ArrayUtil.subArrays(rows, zerorows);
      VATInfoQueryVO[] qryvos = this.getVATInfoQueryVOByBodyPos(nozerorows);
      VATInfoVO[] vatinfos = VATBDService.queryCustVATInfo(qryvos);
      // ����VAT˰��Ϣ
      this.setTaxInfo(vatinfos, nozerorows, true);
    }
  }

  /**
   * ���÷�Ʊ��ȡ˰�����ڱ�ͷ�ĵ���VAT˰�����Ϣ
   * 
   * @param rows
   */
  public void setTaxInfoByHeadPos(int[] rows) {
    // �Ƿ�ͬ��˾
    boolean iscomoncorp = this.isZeroTaxCodeByHead();
    if (iscomoncorp) {
      VATInfoVO vo = this.initZeroTaxCode();
      VATInfoVO[] vatinfos = new VATInfoVO[rows.length];
      for (int i = 0; i < rows.length; i++) {
        vatinfos[i] = vo;
      }
      this.setTaxInfo(vatinfos, rows, false);
    }
    else {
      VATInfoQueryVO[] qryvos = this.getVATInfoQueryVOByHeadPos(rows);
      VATInfoVO[] vatinfos = VATBDService.queryCustVATInfo(qryvos);
      // ����VAT˰��Ϣ
      this.setTaxInfo(vatinfos, rows, false);
    }
  }

  private VATInfoVO initZeroTaxCode() {
    String ZeroTaxCode = ZeroTaxCodeEnum.ZEROTAXCODE.getCode();
    VATInfoVO zerotax =
        new VATInfoVO(ZeroTaxCode, Integer.valueOf(TaxType.TAXABLE_PLUS
            .toIntValue()), UFDouble.ZERO_DBL, UFDouble.ZERO_DBL);
    return zerotax;

  }

  /**
   * ������֯�ڱ�ͷ�����۷�Ʊ��
   * 
   * @return
   */
  private boolean isZeroTaxCodeByHead() {

    // ��������
    SOBuysellTriaRule buyrule = new SOBuysellTriaRule(this.keyValue);
    if (buyrule.isHeadBuysellFlagOut()) {
      return true;
    }
    // ��Ʊ�ͻ�
    String invcustid =
        this.keyValue.getHeadStringValue(this.keyRela.getCinvoicecustidKey());
    String pk_finorg =
        this.keyValue.getHeadStringValue(this.keyRela.getCsettleorgidKey());
    Map<String, String> custmap =
        CustomerPubService.getCustomerFinorgs(new String[] {
          invcustid
        });
    String custorg = custmap.get(invcustid);
    if (pk_finorg.equals(custorg)) {
      return true;
    }
    if (null == custorg) {
      return false;
    }
    String[] finorgs = new String[] {
      pk_finorg, custorg
    };
    Map<String, String> orgcorpmap = OrgUnitPubService.getOrgCorp(finorgs);
    String finorgcorg = orgcorpmap.get(pk_finorg);
    if (finorgcorg.equals(orgcorpmap.get(custorg))) {
      return true;
    }
    return false;
  }
  
  /**
   * 
   * ���ط�������Ҫ����˰�����(��Ʊ�ͻ��ڱ���)
   * 
   * @return ��������Ҫ����˰�����
   */
  private int[] getDeliveryZeroTaxCodeRows(int[] rows) {
    Set<Integer> retrows = new HashSet<Integer>();
    Set<String> custids = new HashSet<String>();
    Set<String> finorgs = new HashSet<String>();
    for (int row : rows) {
      // ��Ʊ�ͻ�
      String invcustid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCinvoicecustidKey());
      if (!PubAppTool.isNull(invcustid)) {
        custids.add(invcustid);
      }
    }
    Map<String, String> custmap =
        CustomerPubService.getCustomerFinorgs(custids
            .toArray(new String[custids.size()]));
    for (int row : rows) {
      String pk_finorg =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCsettleorgidKey());
      finorgs.add(pk_finorg);
      String invcustid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCinvoicecustidKey());
      // ��Ʊ�ͻ�����������֯����������֯һ����ͬ��˾��
      if (pk_finorg != null && pk_finorg.equals(custmap.get(invcustid))) {
        retrows.add(Integer.valueOf(row));
      }
    }
    Map<String, String> orgcorpmap =
        OrgUnitPubService
            .getOrgCorp(finorgs.toArray(new String[finorgs.size()]));
    for (int row : rows) {
      String pk_finorg =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCsettleorgidKey());

      String invcustid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCinvoicecustidKey());
      // ���������֯���ڹ�˾
      String finorgcorg = orgcorpmap.get(pk_finorg);
      // ��Ʊ�ͻ�������֯�������
      String custorg = custmap.get(invcustid);

      // ��������
      SOBuysellTriaRule buyrule = new SOBuysellTriaRule(this.keyValue);

      // ��Ʊ�ͻ��������������֯����������֯���ڹ�˾��ͬ
      if (finorgcorg.equals(custorg) && !buyrule.isBuysellFlagOut(row)) {
        retrows.add(Integer.valueOf(row));
      }
    }
    int[] ret = new int[retrows.size()];
    int i = 0;
    for (Integer row : retrows) {
      ret[i] = row.intValue();
      i++;
    }
    return ret;
  }

  /**
   * 
   * ������Ҫ����0˰�����
   * Ԥ���������۵�������
   * 
   * @return
   */
  private int[] getZeroTaxCodeRows(int[] rows) {
    // ��Ʊ�ͻ�
    String invcustid =
        this.keyValue.getHeadStringValue(this.keyRela.getCinvoicecustidKey());
    if (StringUtils.isEmpty(invcustid)) {
      return new int[0];
    }
    Map<String, String> custmap =
        CustomerPubService.getCustomerFinorgs(new String[] {
          invcustid
        });
    String custorg = custmap.get(invcustid);
    if (null == custorg) {
      return new int[0];
    }
    Set<String> finorgs = new HashSet<String>();

    for (int row : rows) {
      String pk_finorg =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCsettleorgidKey());
      if (null != pk_finorg) {
        finorgs.add(pk_finorg);
      }
    }
    // finorgs����Ϊ0˵�� ���������֯���ǿյ�,�򶼰��繫˾����
    if (finorgs.size() == 0) {
      return new int[0];
    }
    finorgs.add(custorg);
    // finorgs����Ϊ1˵�� ��Ʊ�ͻ�����������֯����������֯һ����ͬ��˾��
    if (finorgs.size() == 1) {
      return rows;
    }
    List<Integer> zerorow = new ArrayList<Integer>();
    Map<String, String> orgcorpmap =
        OrgUnitPubService
            .getOrgCorp(finorgs.toArray(new String[finorgs.size()]));
    for (int row : rows) {
      String pk_finorg =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCsettleorgidKey());
      String finorgcorg = orgcorpmap.get(pk_finorg);
      // ��������
      SOBuysellTriaRule buyrule = new SOBuysellTriaRule(this.keyValue);

      if (finorgcorg.equals(orgcorpmap.get(custorg))
          && !buyrule.isBuysellFlagOut(row)) {
        zerorow.add(Integer.valueOf(row));
      }
    }
    int[] rets = new int[zerorow.size()];
    for (int i = 0; i < zerorow.size(); i++) {
      rets[i] = zerorow.get(i).intValue();
    }

    return rets;
  }

  /**
   * ����˰�롢ҵ����������˰�ʡ���˰���
   * 
   * @param rows
   */
  public void setTaxTypeAndRate(int[] rows) {

    UFDate dbilldate =
        this.keyValue.getHeadUFDateValue(this.keyRela.getDbilldateKey());
    Map<String, VATInfoByTaxcodeQueryVO> mapqryvo =
        new HashMap<String, VATInfoByTaxcodeQueryVO>();
    for (int row : rows) {
      String taxcode =
          this.keyValue
              .getBodyStringValue(row, this.keyRela.getCtaxcodeidKey());
      if (PubAppTool.isNull(taxcode) || mapqryvo.containsKey(taxcode)) {
        continue;
      }
      VATInfoByTaxcodeQueryVO queryvo =
          new VATInfoByTaxcodeQueryVO(taxcode, dbilldate);
      mapqryvo.put(taxcode, queryvo);
    }

    Map<String, VATInfoVO> mapvatinfo = new HashMap<String, VATInfoVO>();
    if (mapqryvo.size() > 0) {
      VATInfoByTaxcodeQueryVO[] queryvos =
          new VATInfoByTaxcodeQueryVO[mapqryvo.values().size()];
      mapqryvo.values().toArray(queryvos);
      VATInfoVO[] vatinfos = VATBDService.queryVATInfo(queryvos);

      for (VATInfoVO info : vatinfos) {
        if (null != info) {
          mapvatinfo.put(info.getCtaxcodeid(), info);
        }
      }
    }

    for (int row : rows) {
      String taxcode =
          this.keyValue
              .getBodyStringValue(row, this.keyRela.getCtaxcodeidKey());

      this.keyValue.setBodyValue(row, this.keyRela.getFtaxtypeflagKey(), null);
      this.keyValue.setBodyValue(row, this.keyRela.getNtaxrateKey(), null);

      if (!PubAppTool.isNull(taxcode)) {
        VATInfoVO vatinfo = mapvatinfo.get(taxcode);
        if (null != vatinfo) {
          this.keyValue.setBodyValue(row, this.keyRela.getFtaxtypeflagKey(),
              vatinfo.getFtaxtypeflag());
          this.keyValue.setBodyValue(row, this.keyRela.getNtaxrateKey(),
              vatinfo.getNtaxrate());
        }
      }
    }
  }

  private VATInfoQueryVO[] getVATInfoQueryVOByBodyPos(int[] rows) {

    VATInfoQueryVO[] qryvos = new VATInfoQueryVO[rows.length];
    String invcustid =
        this.keyValue.getHeadStringValue(this.keyRela.getCinvoicecustidKey());
    UFDate dbizdate =
        this.keyValue.getHeadUFDateValue(this.keyRela.getDbilldateKey());
    int i = 0;
    for (int row : rows) {
      // ��˰��
      String ctaxcountryid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCtaxcountryidKey());
      // ��������
      Integer buysellfalg =
          this.keyValue.getBodyIntegerValue(row,
              this.keyRela.getFbuysellflagKey());
      BuySellFlagEnum fbuysellfalg = BuySellFlagEnum.valueOf(buysellfalg);
      // ����ó��
      UFBoolean btriatradeflag =
          this.keyValue.getBodyUFBooleanValue(row,
              this.keyRela.getBtriatradeflagKey());
      // ������
      String csendcountryid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCsendcountryidKey());
      // �ջ���
      String crececountryid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCrececountryidKey());
      // ����
      String cmaterialvid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCmaterialvidKey());
      qryvos[i] =
          new VATInfoQueryVO(ctaxcountryid, fbuysellfalg, btriatradeflag,
              csendcountryid, crececountryid, invcustid, cmaterialvid, dbizdate);
      i++;
    }
    return qryvos;
  }

  private VATInfoQueryVO[] getVATInfoQueryVOByHeadPos(int[] rows) {
    VATInfoQueryVO[] qryvos = new VATInfoQueryVO[rows.length];
    // ��Ʊ�ͻ�
    String invcustid =
        this.keyValue.getHeadStringValue(this.keyRela.getCinvoicecustidKey());
    // ҵ������
    UFDate dbizdate =
        this.keyValue.getHeadUFDateValue(this.keyRela.getDbilldateKey());
    // ��˰��
    String ctaxcountryid =
        this.keyValue.getHeadStringValue(this.keyRela.getCtaxcountryidKey());
    // ��������
    Integer buysellfalg =
        this.keyValue.getHeadIntegerValue(this.keyRela.getFbuysellflagKey());
    BuySellFlagEnum fbuysellfalg = BuySellFlagEnum.valueOf(buysellfalg);
    // ����ó��
    UFBoolean btriatradeflag =
        this.keyValue
            .getHeadUFBooleanValue(this.keyRela.getBtriatradeflagKey());
    // ������
    String csendcountryid =
        this.keyValue.getHeadStringValue(this.keyRela.getCsendcountryidKey());
    // �ջ���
    String crececountryid =
        this.keyValue.getHeadStringValue(this.keyRela.getCrececountryidKey());

    int i = 0;
    for (int row : rows) {
      // ����
      String cmaterialvid =
          this.keyValue.getBodyStringValue(row,
              this.keyRela.getCmaterialvidKey());
      // ��������Ʊ�ͻ��ڱ���
      if (PubAppTool.isNull(invcustid)) {
        invcustid =
            this.keyValue.getBodyStringValue(row,
                this.keyRela.getCinvoicecustidKey());
      }
      qryvos[i] =
          new VATInfoQueryVO(ctaxcountryid, fbuysellfalg, btriatradeflag,
              csendcountryid, crececountryid, invcustid, cmaterialvid, dbizdate);
      i++;
    }
    return qryvos;
  }

  private void setTaxInfo(VATInfoVO[] vatinfos, int[] rows, boolean isonlycode) {
    int i = 0;
    this.listchgrow = new ArrayList<Integer>();
    for (int row : rows) {

      // ��˰����Ϣ
      String newtaxcode = null;
      Integer newtaxtype = null;
      UFDouble newtaxrate = null;
      if (null != vatinfos[i]) {
        newtaxcode = vatinfos[i].getCtaxcodeid();
        newtaxtype = vatinfos[i].getFtaxtypeflag();
        newtaxrate = vatinfos[i].getNtaxrate();
      }
      if (isonlycode) {
        this.keyValue.setBodyValue(row, this.keyRela.getCtaxcodeidKey(),
            newtaxcode);
      }
      else {
        // ԭʼ˰��
        String oldtaxcode =
            this.keyValue.getBodyStringValue(row,
                this.keyRela.getCtaxcodeidKey());
        // ԭʼ˰��
        UFDouble oldtaxrate =
            this.keyValue.getBodyUFDoubleValue(row,
                this.keyRela.getNtaxrateKey());
        // ԭʼ��˰����
        Integer oldtaxtype =
            this.keyValue.getBodyIntegerValue(row,
                this.keyRela.getFtaxtypeflagKey());

        // ˰��Ϊ�ջ�˰��仯
        if (null == oldtaxrate || !PubAppTool.isEqual(oldtaxcode, newtaxcode)) {
          this.keyValue.setBodyValue(row, this.keyRela.getCtaxcodeidKey(),
              newtaxcode);
          this.keyValue.setBodyValue(row, this.keyRela.getFtaxtypeflagKey(),
              newtaxtype);
          this.keyValue.setBodyValue(row, this.keyRela.getNtaxrateKey(),
              newtaxrate);
          this.listchgrow.add(row);
        }// ֻ�Ǽ�˰������仯
        else if (!PubAppTool.isEqual(oldtaxtype, newtaxtype)) {
          this.keyValue.setBodyValue(row, this.keyRela.getFtaxtypeflagKey(),
              newtaxtype);
          this.listchgrow.add(row);
        }
      }
      i++;
    }
  }
}
