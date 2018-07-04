package nc.ui.so.pub.para;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nc.bs.para.ComplicatedParaContext;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITabbedPane;
import nc.ui.pub.para.ISysInitPanel2;
import nc.ui.pubapp.panel.AbstractParaListToListPanel;
import nc.vo.pub.para.CheckParaVO;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.entry.SaleInvoiceBVOCode;
import nc.vo.so.pub.enumeration.InvoiceCombinRule;
import nc.vo.so.pub.enumeration.SOFInvoiceKey2Code;
import nc.vo.so.pub.enumeration.SOInvoiceCombinRule;
import nc.vo.so.pub.enumeration.SOInvoiceCombindefrule;
import nc.vo.so.pub.res.ParameterList;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * ���۷�Ʊ���ܹ������dialog
 * 
 * @since 6.1
 * @version 2013-03-20 09:33:39
 * @author yixl
 */
public class InvoiceCombinDlg extends UIPanel implements ISysInitPanel2 {

  /**
   * �洢���Ϸ��༸�εĶ���ID��name
   */
  private Map<String, String> marLvlKeyValue;

  /**
   * �����б���Ϣ
   * 
   * @since 6.1
   * @version 2013-03-20 09:34:59
   * @author yixl
   */
  protected static class ParaListItemInfo implements Serializable {

    private static final long serialVersionUID = -4047069847846625871L;

    // ��ʾֵ
    private String text;

    // ʵ��ֵ
    private Object value;

    /**
     * �չ�����
     */
    public ParaListItemInfo() {
      //
    }

    /**
     * ������
     * 
     * @param value
     * @param text
     */
    public ParaListItemInfo(Object value, String text) {
      this.value = value;
      this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (!(obj instanceof ParaListItemInfo)) {
        return false;
      }
      ParaListItemInfo other = (ParaListItemInfo) obj;
      return this.value.equals(other.getValue());
    }

    /**
     * �����ʾֵ
     * 
     * @return ��ʾֵ
     */
    public String getText() {
      return this.text;
    }

    /**
     * ���ʵ��ֵ
     * 
     * @return ʵ��ֵ
     */
    public Object getValue() {
      return this.value;
    }

    @Override
    public int hashCode() {
      if (this.value == null) {
        return 0;
      }
      return this.value.hashCode();
    }

    /**
     * ������ʾֵ
     * 
     * @param text
     */
    public void setText(String text) {
      this.text = text;
    }

    /**
     * ����ʵ��ֵ
     * 
     * @param value
     */
    public void setValue(Object value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.text == null ? "" : this.text;
    }
  }

  private static final int HEIGHT1 = 650;

  private static final long serialVersionUID = 222937895245122719L;

  private static final int WIDTH1 = 300;

  private CombinDefPanel AvgPanel;

  /** ��ƽ�����ֶ� */
  private InvoiceCombinDlg.ParaListItemInfo[] AvgrightItems;

  private UITabbedPane bottomPanel;

  /** Ĭ���𼶻��� */
  private int classlevel = 6;

  private CombinDefPanel combinPanel;

  private SysInitVO initVO;

  private InvoiceCombinDlg.ParaListItemInfo[] leftItems;

  private Map<String, String> mapKeyName;

  /**
   * code����ʾֵ��Ӧmap
   */
  private Map<String, String> mapKeyCodeName;

  /**
   * ����map
   * key => code
   */
  private Map<String, String> mapTransKeyToCode;

  /**
   * ����map
   * code => key
   */
  private Map<String, String> mapTransCodeToKey;

  private String pk_org;

  private CombinDefPanel ProAvgPanel;

  /** ���Ȩƽ�����ֶ� */
  private InvoiceCombinDlg.ParaListItemInfo[] ProAvgrightItems;

  private UIPanel radioButtonPanel;

  private InvoiceCombinDlg.ParaListItemInfo[] rightItems;

  private CombinDefPanel SumPanel;

  /** ��͵��ֶ� */
  private InvoiceCombinDlg.ParaListItemInfo[] SumrightItems;

  private InvoiceCombinDlg.ParaListItemInfo[] vbdefleftItems;

  /**
   * 
   * @param pk_org
   */
  public InvoiceCombinDlg(String pk_org) {
    this.pk_org = pk_org;
    this.setSize(InvoiceCombinDlg.WIDTH1, InvoiceCombinDlg.HEIGHT1);
    this.setLayout(new BorderLayout());
    this.initlize();
    this.add(this.getBottomPanel(), BorderLayout.NORTH);
    // this.add(this.getTopDefPanel(), BorderLayout.NORTH);
    this.add(this.getRadioButtonPanel(), BorderLayout.LINE_START);

  }

  /**
   * ��õײ�pannel
   * 
   * @return UITabbedPane
   */
  public UITabbedPane getBottomPanel() {
    if (null == this.bottomPanel) {
      this.combinPanel = new CombinDefPanel(this.leftItems, this.rightItems);
      this.ProAvgPanel =
          new CombinDefPanel(this.vbdefleftItems, this.ProAvgrightItems);
      this.AvgPanel =
          new CombinDefPanel(this.vbdefleftItems, this.AvgrightItems);
      this.SumPanel =
          new CombinDefPanel(this.vbdefleftItems, this.SumrightItems);

      UITabbedPane p = new UITabbedPane();
      p.setFont(new Font("Dialog", Font.PLAIN, 12));
      p.add(this.combinPanel,
          NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0021")/* ���ܹ��� */);
      p.add(this.SumPanel,
          NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0022")/* ��� */);
      p.add(this.AvgPanel,
          NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0023")/* ��ƽ�� */);
      p.add(this.ProAvgPanel,
          NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0024")/* ���Ȩƽ�� */);

      this.bottomPanel = p;
    }
    return this.bottomPanel;
  }

  /**
   * 
   * 
   * @return Map<String, String>
   */
  public Map<String, String> getKeyNameRela() {
    if (null == this.mapKeyName) {
      this.mapKeyName = new LinkedHashMap<String, String>();
      for (InvoiceCombinRule rule : InvoiceCombinRule.values()) {
        this.mapKeyName.put(rule.getKey(), rule.getName());
      }
    }
    return this.mapKeyName;
  }

  /**
   * 
   * @return Map<String, String>
   */
  public Map<String, String> getKeyCodeNameRela() {
    if (null == this.mapKeyCodeName) {
      this.mapKeyCodeName = new LinkedHashMap<String, String>();
      for (SOInvoiceCombinRule rule : SOInvoiceCombinRule.values()) {
        this.mapKeyCodeName.put(rule.getKeyCode(), rule.getName());
      }
    }
    return this.mapKeyCodeName;
  }

  /**
   * ��ȡ����ֵ����Map
   * 
   * @return Map<String, String>
   */
  public Map<String, String> getTransKeyToCode() {
    if (null == this.mapTransKeyToCode) {
      this.mapTransKeyToCode = new LinkedHashMap<String, String>();
      for (SOFInvoiceKey2Code rule : SOFInvoiceKey2Code.values()) {
        this.mapTransKeyToCode.put(rule.getKey(), rule.getCode());
      }
    }
    return this.mapTransKeyToCode;
  }

  /**
   * ��ȡ����ֵ����Map
   * 
   * @return Map<String, String>
   */
  public Map<String, String> getTransCodeToKey() {
    if (null == this.mapTransCodeToKey) {
      this.mapTransCodeToKey = new LinkedHashMap<String, String>();
      for (SOFInvoiceKey2Code rule : SOFInvoiceKey2Code.values()) {
        this.mapTransCodeToKey.put(rule.getCode(), rule.getKey());
      }
    }
    return this.mapTransCodeToKey;
  }

  @Override
  public UIPanel getPanel(ComplicatedParaContext context) {
    return this;
  }

  private  ParaListItemInfo[] getParaListItemInfo( Object[] objs){
    if (null == objs || objs.length == 0) {
      return new ParaListItemInfo[0];
    }
    ParaListItemInfo[] retArray = new ParaListItemInfo[objs.length];
    int i = 0;
    for (Object obj : objs) {
      retArray[i] = new ParaListItemInfo();
      retArray[i] = (ParaListItemInfo) obj;
      i++;
    }
    return retArray;
  }
  
  @Override
  public SysInitVO[] getSysInitVOs() {

    StringBuffer value = new StringBuffer();
    Object[] rightobj = this.combinPanel.getRightData();
    if (null == rightobj || rightobj.length == 0) {
      return null;
    }
    ParaListItemInfo[] retArray = getParaListItemInfo(rightobj);
   
    String retvalue = this.getSysInitVOValue(retArray);

    retvalue = this.getCombinValue(retvalue);
    value.append(ParameterList.DOLLER + retvalue + ParameterList.BIGSPLITKEY);
    if (ArrayUtils.isEmpty(this.SumPanel.getRightData())) {
      value.append(ParameterList.BIGSPLITKEY);
    }
    else {
      retArray = getParaListItemInfo(this.SumPanel.getRightData());
      retvalue = this.getSysInitVOValue(retArray);
      value.append(retvalue + ParameterList.BIGSPLITKEY);
    }
    if (ArrayUtils.isEmpty(this.AvgPanel.getRightData())) {
      value.append(ParameterList.BIGSPLITKEY);
    }
    else {
      retArray = getParaListItemInfo(this.AvgPanel.getRightData());
      retvalue = this.getSysInitVOValue(retArray);
      value.append(retvalue + ParameterList.BIGSPLITKEY);
    }

    if (ArrayUtils.isEmpty(this.ProAvgPanel.getRightData())) {
      value.append(ParameterList.BIGSPLITKEY);
    }
    else {
      retArray = getParaListItemInfo(this.ProAvgPanel.getRightData());
      retvalue = this.getSysInitVOValue(retArray);
      value.append(retvalue + ParameterList.BIGSPLITKEY);
    }

    Component[] coms = this.getRadioButtonPanel().getComponents();
    for (Component com : coms) {
      if (com instanceof UIComboBox) {
        String marLevel = ((UIComboBox) com).getSelectdItemValue().toString();
        String Lvevlcode = this.marLvlKeyValue.get(marLevel);
        value.append(Lvevlcode);
      }
    }
    this.initVO.setValue(value.toString());
    return new SysInitVO[] {
      this.initVO
    };
  }

  /**
   * ��ò�����Ϣ
   * 
   * @param retArray
   * @return String
   */
  public String getSysInitVOValue(ParaListItemInfo[] retArray) {

    StringBuilder values = new StringBuilder();
    for (ParaListItemInfo obj : retArray) {
      values.append(obj.getValue()).append(",");
    }
    return values.toString();
  }

  /**
   * ��ʼ��
   */
  public void initlize() {
    SysInitVO[] initVOs =
        SysParaInitQuery.querySysInit(this.pk_org, this.getParamValueCode());

    for (SysInitVO vo : initVOs) {
      if (StringUtils.isNotBlank(vo.getInitcode())
          && vo.getInitcode().endsWith(AbstractParaListToListPanel.ENDWORD)
          || vo.getInitcode().endsWith(
              AbstractParaListToListPanel.ENDWORD.toUpperCase())) {
        this.initVO = vo;
      }
    }
    this.vbdefleftItems = this.initVbdefLeftItems();

    String cnClassLevel =
        this.initVO.getValue().split(ParameterList.BIGSPLITKEY)[4];
    if ("�𼶻���".equals(cnClassLevel)) { /* -=notranslate=- */
      this.classlevel = 6;
    }
    else if ("ĩ������".equals(cnClassLevel)) { /* -=notranslate=- */
      this.classlevel = 7;
    }
    else if ("һ������".equals(cnClassLevel)) { /* -=notranslate=- */
      this.classlevel = 1;
    }
    else if ("��������".equals(cnClassLevel)) { /* -=notranslate=- */
      this.classlevel = 2;
    }
    else if ("��������".equals(cnClassLevel)) { /* -=notranslate=- */
      this.classlevel = 3;
    }
    else if ("�ļ�����".equals(cnClassLevel)) { /* -=notranslate=- */
      this.classlevel = 4;
    }
    else if ("�弶����".equals(cnClassLevel)) { /* -=notranslate=- */
      this.classlevel = 5;
    }

    this.initLeftItems(this.initVO);
    this.initrightItems(this.initVO);
  }

  /**
   * 
   * 
   * @param bottomPanel
   */
  public void setBottomPanel(UITabbedPane bottomPanel) {
    this.bottomPanel = bottomPanel;
  }

  /**
   * 
   * 
   * @return UIPanel
   */
  protected UIPanel getRadioButtonPanel() {
    if (this.radioButtonPanel == null) {
      this.radioButtonPanel = new UIPanel();
      this.radioButtonPanel.setLayout(new FlowLayout());
      UIComboBox combo = new UIComboBox();
      if (null == this.marLvlKeyValue) {
        this.marLvlKeyValue = new HashMap<String, String>();
      }
      String[] arNames = this.getAllItems();
      this.marLvlKeyValue.put(arNames[0], "һ������");/* -=notranslate=- */
      this.marLvlKeyValue.put(arNames[1], "��������");/* -=notranslate=- */
      this.marLvlKeyValue.put(arNames[2], "��������");/* -=notranslate=- */
      this.marLvlKeyValue.put(arNames[3], "�ļ�����");/* -=notranslate=- */
      this.marLvlKeyValue.put(arNames[4], "�弶����");/* -=notranslate=- */
      this.marLvlKeyValue.put(arNames[5], "�𼶻���"); /* -=notranslate=- */
      this.marLvlKeyValue.put(arNames[6], "ĩ������");/* -=notranslate=- */

      combo.addItems(arNames);
      combo.setName("cmaterialbaseclass");
      combo.setSelectedItem(arNames[this.classlevel - 1]);
      UILabel uilable =
          new UILabel(NCLangRes.getInstance().getStrByID("4006004_0",
              "04006004-0224")/* ���ϻ���������ܼ��� */);
      Font font = new Font("font", 1, 14);
      uilable.setFont(font);
      this.radioButtonPanel.add(uilable);
      this.radioButtonPanel.add(combo);
    }
    return this.radioButtonPanel;
  }

  private String[] getAllItems() {
    String[] arNames = new String[7];
    arNames[0] =
        NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0028")/* һ������ */;
    arNames[1] =
        NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0029")/* �������� */;
    arNames[2] =
        NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0030")/* �������� */;
    arNames[3] =
        NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0031")/* �ļ����� */;
    arNames[4] =
        NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0032")/* �弶���� */;
    arNames[5] =
        NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0026")/* �𼶻��� */;
    arNames[6] =
        NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0027")/* ĩ������ */;
    return arNames;
  }

  private String getCombinValue(String retvalue) {
    String ret = retvalue;
    if (ret.indexOf(SaleInvoiceBVOCode.CMARBASCALSSID) != -1
        || ret.indexOf(SaleInvoiceBVOCode.CMATERIALID) != -1
        || ret.indexOf(SaleInvoiceBVOCode.CMATERIALVID) != -1) {
      if (ret.indexOf(SaleInvoiceBVOCode.CUNITID) == -1) {
        ret += SaleInvoiceBVOCode.CUNITID + ",";
      }
      if (ret.indexOf(SaleInvoiceBVOCode.CASTUNITID) == -1) {
        ret += SaleInvoiceBVOCode.CASTUNITID + ",";
      }
      if (ret.indexOf(SaleInvoiceBVOCode.CQTUNITID) == -1) {
        ret += SaleInvoiceBVOCode.CQTUNITID + ",";
      }
    }
    if (ret.length() > 0 && ret.indexOf(SaleInvoiceBVOCode.CTAXCODEID) == -1) {
      ret += SaleInvoiceBVOCode.NTAXRATE + ",";
    }
    if (ret.length() > 0 && ret.indexOf(SaleInvoiceBVOCode.NTAXRATE) == -1) {
      ret += SaleInvoiceBVOCode.NTAXRATE + ",";
    }
    if (ret.length() > 0 && ret.indexOf(SaleInvoiceBVOCode.FTAXTYPEFLAG) == -1) {
      ret += SaleInvoiceBVOCode.NTAXRATE + ",";
    }
    if (ret.length() > 0 && ret.indexOf(SaleInvoiceBVOCode.NDISCOUNTRATE) == -1) {
      ret += SaleInvoiceBVOCode.NDISCOUNTRATE + ",";
    }
    if (ret.length() > 0
        && ret.indexOf(SaleInvoiceBVOCode.NINVOICEDISRATE) == -1) {
      ret += SaleInvoiceBVOCode.NINVOICEDISRATE + ",";
    }
    if (ret.length() > 0
        && ret.indexOf(SaleInvoiceBVOCode.NITEMDISCOUNTRATE) == -1) {
      ret += SaleInvoiceBVOCode.NITEMDISCOUNTRATE + ",";
    }

    if (ret.length() > 0 && ret.indexOf(SaleInvoiceBVOCode.BLARGESSFLAG) == -1) {
      ret += SaleInvoiceBVOCode.BLARGESSFLAG + ",";
    }
    return ret;
  }

  private String getParamValueCode() {
    return ParameterList.SO28.getCode();
  }

  private void initLeftItems(SysInitVO initVO2) {
    if (null == this.leftItems) {
      String rightvalue =
          initVO2.getValue().split(ParameterList.BIGSPLITKEY)[0];
      SOInvoiceCombinRule[] fields =
          SOInvoiceCombinRule.class.getEnumConstants();
      List<InvoiceCombinDlg.ParaListItemInfo> infolist =
          new ArrayList<InvoiceCombinDlg.ParaListItemInfo>();
      // 2013_10_15 Add By Zhaodan.
      // �������ʵ�����ѡ����������.��Ϊ$��ͷ�����¹����¼��ֱ����codeֵ�ж�.
      // ����Ϊ�����ݣ����ж�ʱ��Ҫ�ж�ʵ��key.
      if (rightvalue.startsWith(ParameterList.DOLLER)) {
        for (SOInvoiceCombinRule field : fields) {
          // 2013_10_14 Mark By Zhaodan. �����rightvalueΪƴ�Ӻ���ַ������������жϼ���key.
          if (rightvalue.indexOf(field.getKeyCode() + ParameterList.SPLITKEY) != -1) {
            continue;
          }
          InvoiceCombinDlg.ParaListItemInfo info = new ParaListItemInfo();
          info.setText(field.getName());
          info.setValue(field.getKeyCode());
          infolist.add(info);
        }
      }
      else {
        for (SOInvoiceCombinRule field : fields) {
          // �Ծ������ж�ǰ�Ƚ���.
          Map<String, String> code2Key = this.getTransCodeToKey();
          if (rightvalue.indexOf(code2Key.get(field.getKeyCode())
              + ParameterList.SPLITKEY) != -1) {
            continue;
          }
          InvoiceCombinDlg.ParaListItemInfo info = new ParaListItemInfo();
          info.setText(field.getName());
          info.setValue(field.getKeyCode());
          infolist.add(info);
        }
      }
      this.leftItems =
          infolist.toArray(new InvoiceCombinDlg.ParaListItemInfo[infolist
              .size()]);
    }
  }

  private void initrightItems(SysInitVO initVO2) {
    if (null == this.rightItems) {
      String value = initVO2.getValue();
      String[] allrightValues = new String[0];
      Map<String, String> keyNameRela = null;
      if (!PubAppTool.isNull(value)) {
        keyNameRela = this.getKeyCodeNameRela();
        allrightValues = value.split(ParameterList.BIGSPLITKEY);
      }
      if (allrightValues.length > 4) {
        // 2013_10_15. Add By Zhaodan.
        // �˴����ж��Ƿ����¹���.�����´�������Ҫ��ȥ$��ʶ��.
        // ���Ǿɼ�¼������Ҫ�Ƚ�key����Ϊcode.
        if (value.startsWith(ParameterList.DOLLER)) {
          // ���ܹ���
          String[] rightValues = new String[0];
          String valueStr = allrightValues[0];
          if (!PubAppTool.isNull(valueStr)) {
            rightValues =
                valueStr.substring(1, valueStr.length()).split(
                    ParameterList.SPLITKEY);
            this.rightItems = new ParaListItemInfo[rightValues.length];
            for (int i = 0; i < rightValues.length; i++) {
              String key = rightValues[i];
              this.rightItems[i] = new ParaListItemInfo();
              String name = keyNameRela.get(key);
              this.rightItems[i].setText(name);
              this.rightItems[i].setValue(key);
            }
          }

          // ���
          if (!PubAppTool.isNull(allrightValues[1])) {
            rightValues = allrightValues[1].split(ParameterList.SPLITKEY);
            this.SumrightItems = new ParaListItemInfo[rightValues.length];
            for (int i = 0; i < rightValues.length; i++) {
              String key = rightValues[i];
              this.SumrightItems[i] = new ParaListItemInfo();
              String name = keyNameRela.get(key);
              this.SumrightItems[i].setText(name);
              this.SumrightItems[i].setValue(key);
            }
          }
          // ��ƽ��
          if (!PubAppTool.isNull(allrightValues[2])) {
            rightValues = allrightValues[2].split(ParameterList.SPLITKEY);
            this.AvgrightItems = new ParaListItemInfo[rightValues.length];
            for (int i = 0; i < rightValues.length; i++) {
              String key = rightValues[i];
              this.AvgrightItems[i] = new ParaListItemInfo();
              String name = keyNameRela.get(key);
              this.AvgrightItems[i].setText(name);
              this.AvgrightItems[i].setValue(key);
            }
          }
          // ��Ȩƽ��
          if (!PubAppTool.isNull(allrightValues[3])) {
            rightValues = allrightValues[3].split(ParameterList.SPLITKEY);
            this.ProAvgrightItems = new ParaListItemInfo[rightValues.length];
            for (int i = 0; i < rightValues.length; i++) {
              String key = rightValues[i];
              this.ProAvgrightItems[i] = new ParaListItemInfo();
              String name = keyNameRela.get(key);
              this.ProAvgrightItems[i].setText(name);
              this.ProAvgrightItems[i].setValue(key);
            }
          }
        }
        else {
          String[] rightValues = new String[0];
          String valueStr = allrightValues[0];
          Map<String, String> keyName2Code = this.getTransKeyToCode();
          if (!PubAppTool.isNull(valueStr)) {
            rightValues = valueStr.split(ParameterList.SPLITKEY);
            this.rightItems = new ParaListItemInfo[rightValues.length];
            for (int i = 0; i < rightValues.length; i++) {
              String key = keyName2Code.get(rightValues[i]);
              this.rightItems[i] = new ParaListItemInfo();
              String name = keyNameRela.get(key);
              this.rightItems[i].setText(name);
              this.rightItems[i].setValue(key);
            }
          }
          // ���
          if (!PubAppTool.isNull(allrightValues[1])) {
            rightValues = allrightValues[1].split(ParameterList.SPLITKEY);
            this.SumrightItems = new ParaListItemInfo[rightValues.length];
            for (int i = 0; i < rightValues.length; i++) {
              String key = keyName2Code.get(rightValues[i]);
              this.SumrightItems[i] = new ParaListItemInfo();
              String name = keyNameRela.get(key);
              this.SumrightItems[i].setText(name);
              this.SumrightItems[i].setValue(key);
            }
          }
          // ��ƽ��
          if (!PubAppTool.isNull(allrightValues[2])) {
            rightValues = allrightValues[2].split(ParameterList.SPLITKEY);
            this.AvgrightItems = new ParaListItemInfo[rightValues.length];
            for (int i = 0; i < rightValues.length; i++) {
              String key = keyName2Code.get(rightValues[i]);
              this.AvgrightItems[i] = new ParaListItemInfo();
              String name = keyNameRela.get(key);
              this.AvgrightItems[i].setText(name);
              this.AvgrightItems[i].setValue(key);
            }
          }
          // ��Ȩƽ��
          if (!PubAppTool.isNull(allrightValues[3])) {
            rightValues = allrightValues[3].split(ParameterList.SPLITKEY);
            this.ProAvgrightItems = new ParaListItemInfo[rightValues.length];
            for (int i = 0; i < rightValues.length; i++) {
              String key = keyName2Code.get(rightValues[i]);
              this.ProAvgrightItems[i] = new ParaListItemInfo();
              String name = keyNameRela.get(key);
              this.ProAvgrightItems[i].setText(name);
              this.ProAvgrightItems[i].setValue(key);
            }
          }
        }
      }
    }
  }


  private ParaListItemInfo[] initVbdefLeftItems() {
    if (null == this.vbdefleftItems) {
      SOInvoiceCombindefrule[] fields =
          SOInvoiceCombindefrule.class.getEnumConstants();
      List<InvoiceCombinDlg.ParaListItemInfo> infolist =
          new ArrayList<InvoiceCombinDlg.ParaListItemInfo>();
      for (SOInvoiceCombindefrule field : fields) {
        InvoiceCombinDlg.ParaListItemInfo info = new ParaListItemInfo();
        info.setText(field.getName());
        info.setValue(field.getKey());
        infolist.add(info);
      }
      this.vbdefleftItems =
          infolist.toArray(new InvoiceCombinDlg.ParaListItemInfo[infolist
              .size()]);
    }
    return this.vbdefleftItems;
  }

  @Override
  public CheckParaVO check() {
    CheckParaVO paravo = new CheckParaVO();
    String errMsg = "";
    Object[] rightobj = this.combinPanel.getRightData();
    if (null == rightobj || rightobj.length == 0) {
      errMsg = NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0006")/* ���Ϸ��༶�κ����ϱ��������һ����������ѡ�� */;
      paravo.setErrMsg(errMsg.toString());
      paravo.setLegal(false);
      return paravo;
    }
    ParaListItemInfo[] retArray = getParaListItemInfo(rightobj);
    String retvalue = this.getSysInitVOValue(retArray);

    if (retvalue.indexOf(SaleInvoiceBVOCode.CMARBASCALSSID) == -1
        && retvalue.indexOf(SaleInvoiceBVOCode.CMATERIALVID) == -1) {
      errMsg = NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0006")/* ���Ϸ��༶�κ����ϱ��������һ����������ѡ�� */;
      paravo.setErrMsg(errMsg.toString());
      paravo.setLegal(false);
      return paravo;
    }

    List<String> errmeg = new ArrayList<String>();
    String ret = retvalue;
    if (ret.indexOf(SaleInvoiceBVOCode.CMARBASCALSSID) != -1
        || ret.indexOf(SaleInvoiceBVOCode.CMATERIALID) != -1
        || ret.indexOf(SaleInvoiceBVOCode.CMATERIALVID) != -1) {
      if (ret.indexOf(SaleInvoiceBVOCode.CUNITID) == -1) {
        errmeg.add(NCLangRes.getInstance().getStrByID("4006004_0",
            "04006004-0033")/* ����λ */);
        ret += SaleInvoiceBVOCode.CUNITID + ",";
      }
      if (ret.indexOf(SaleInvoiceBVOCode.CASTUNITID) == -1) {
        errmeg.add(NCLangRes.getInstance().getStrByID("4006004_0",
            "04006004-0034")/* ��λ */);
        ret += SaleInvoiceBVOCode.CASTUNITID + ",";
      }
      if (ret.indexOf(SaleInvoiceBVOCode.CQTUNITID) == -1) {
        errmeg.add(NCLangRes.getInstance().getStrByID("4006004_0",
            "04006004-0034")/* ��λ */);

        ret += SaleInvoiceBVOCode.CQTUNITID + ",";
      }
    }
    if (ret.length() > 0 && ret.indexOf(SaleInvoiceBVOCode.CTAXCODEID) == -1) {
      errmeg.add(NCLangRes.getInstance().getStrByID("4006004_0",
          "04006004-0132")/* ˰�� */);
      ret += SaleInvoiceBVOCode.NTAXRATE + ",";
    }
    if (ret.length() > 0 && ret.indexOf(SaleInvoiceBVOCode.NTAXRATE) == -1) {
      errmeg.add(NCLangRes.getInstance().getStrByID("4006004_0",
          "04006004-0035")/* ˰�� */);
      ret += SaleInvoiceBVOCode.NTAXRATE + ",";
    }
    if (ret.length() > 0 && ret.indexOf(SaleInvoiceBVOCode.FTAXTYPEFLAG) == -1) {
      errmeg.add(NCLangRes.getInstance().getStrByID("4006004_0",
          "04006004-0226")/* ��˰��� */);
      ret += SaleInvoiceBVOCode.NTAXRATE + ",";
    }
    if (ret.length() > 0 && ret.indexOf(SaleInvoiceBVOCode.NDISCOUNTRATE) == -1) {
      errmeg.add(NCLangRes.getInstance().getStrByID("4006004_0",
          "04006004-0036")/* �����ۿ� */);
      ret += SaleInvoiceBVOCode.NDISCOUNTRATE + ",";
    }
    if (ret.length() > 0
        && ret.indexOf(SaleInvoiceBVOCode.NINVOICEDISRATE) == -1) {
      errmeg.add(NCLangRes.getInstance().getStrByID("4006004_0",
          "04006004-0037")/* ��Ʊ�ۿ� */);
      ret += SaleInvoiceBVOCode.NINVOICEDISRATE + ",";
    }
    if (ret.length() > 0
        && ret.indexOf(SaleInvoiceBVOCode.NITEMDISCOUNTRATE) == -1) {
      errmeg.add(NCLangRes.getInstance().getStrByID("4006004_0",
          "04006004-0038")/* ��Ʒ�ۿ� */);
      ret += SaleInvoiceBVOCode.NITEMDISCOUNTRATE + ",";
    }

    if (ret.length() > 0 && ret.indexOf(SaleInvoiceBVOCode.BLARGESSFLAG) == -1) {
      errmeg.add(NCLangRes.getInstance().getStrByID("4006004_0",
          "04006004-0083")/* ��Ʒ */);
      ret += SaleInvoiceBVOCode.BLARGESSFLAG + ",";
    }

    if (errmeg.size() > 0) {
      StringBuffer errbuffer = new StringBuffer();
      for (String s : errmeg) {
        errbuffer.append(NCLangRes.getInstance().getStrByID("4006004_0",
            "04006004-0207", null, new String[] {
              s
            })/* {0}�� */);
      }
      errbuffer.deleteCharAt(errbuffer.length() - 1);
      errMsg =
          NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0039",
              null, new String[] {
                errbuffer.toString()
              });/* �����ֶΣ�{0}�Ǳ�ѡ���� */
    }
    if (errMsg.length() > 0) {
      paravo.setErrMsg(errMsg.toString());
      paravo.setLegal(false);
      return paravo;
    }
    return null;
  }
}
