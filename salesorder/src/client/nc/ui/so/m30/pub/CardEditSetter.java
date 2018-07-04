package nc.ui.so.m30.pub;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.enumeration.Largesstype;
import nc.vo.so.m30.util.SpecialBusiUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ��Ƭ�����ʼ����ʱ�򻺴濨Ƭ�༭��
 * ������ֱ�־�����ı�ʱ���ƿ�Ƭ�༭��
 * 
 * @since 6.0
 * @version 2010-12-10 ����12:14:04
 * @author ô��
 */
public class CardEditSetter {

  /**
   * �����տ�󣬱�ͷ���ܱ༭���ֶ�
   */
  public static final  String[] HEADNOTEDITFIELDS = {
    // ��Ʊ�ͻ����ͻ�������
    SaleOrderHVO.CINVOICECUSTID, SaleOrderHVO.CCUSTOMERID,
    SaleOrderHVO.CFREECUSTID, SaleOrderHVO.CORIGCURRENCYID
  };

  /**
   * �����տ�󣬱��岻�ܱ༭���ֶ�
   */
  public  static final  String[] BODYNOTEDITFIELDS = {
    // ����
    SaleOrderBVO.CMATERIALVID,
    // ������֯��������֯�汾
    SaleOrderBVO.CSETTLEORGID, SaleOrderBVO.CSETTLEORGVID,
    // ������֯
    SaleOrderBVO.CSETTLEORGID, SaleOrderBVO.CSETTLEORGVID,
    // Ӧ����֯
    SaleOrderBVO.CARORGID, SaleOrderBVO.CARORGVID,
    // �����֯
    SaleOrderBVO.CSENDSTOCKORGID, SaleOrderBVO.CSENDSTOCKORGVID
  };

  /** �̶����ɱ༭�ı����ֶ� */
  private static final String[] BODYFIXFALSEKEY = new String[] {
    SaleOrderBVO.PK_GROUP
  };

  /** ��Ҫ���Ʊ༭�Եı����ֶ� */
  private static final String[] BODYKEY = new String[] {
    // ���ϡ���λ
    SaleOrderBVO.CMATERIALVID,
    SaleOrderBVO.CASTUNITID,
    // ������������
    SaleOrderBVO.NASTNUM,
    SaleOrderBVO.NNUM,
    // �����ʡ���������
    SaleOrderBVO.VCHANGERATE,
    SaleOrderBVO.NQTUNITNUM,
    // ���۵�λ������λ
    SaleOrderBVO.CQTUNITID,
    SaleOrderBVO.CUNITID,
    // ��Ʒ�����ۻ�����
    SaleOrderBVO.BLARGESSFLAG,
    SaleOrderBVO.VQTUNITRATE,
    // ����˰���ۡ�����˰����
    SaleOrderBVO.NORIGTAXPRICE,
    SaleOrderBVO.NORIGPRICE,
    // ����˰���ۡ�����˰����
    SaleOrderBVO.NORIGTAXNETPRICE,
    SaleOrderBVO.NORIGNETPRICE,
    // ��˰���ۡ���˰����
    SaleOrderBVO.NQTORIGTAXPRICE,
    SaleOrderBVO.NQTORIGPRICE,
    // ��˰���ۡ���˰����
    SaleOrderBVO.NQTORIGTAXNETPRC,
    SaleOrderBVO.NQTORIGNETPRICE,
    // ˰���˰���
    SaleOrderBVO.NORIGMNY,
    // ��˰�ϼơ��ۿ۶�
    SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT,
    // ������֯��������֯�汾
    SaleOrderBVO.CSETTLEORGID,
    SaleOrderBVO.CSETTLEORGVID,
    // �������ġ������ȼ�
    SaleOrderBVO.CPROFITCENTERID,
    SaleOrderBVO.CQUALITYLEVELID,
    // �ջ�����
    SaleOrderBVO.CRECEIVEAREAID, SaleOrderBVO.CPRICEITEMID,
    SaleOrderBVO.VFREE1, SaleOrderBVO.VFREE2, SaleOrderBVO.VFREE3,
    SaleOrderBVO.VFREE4, SaleOrderBVO.VFREE5, SaleOrderBVO.VFREE6,
    SaleOrderBVO.VFREE7, SaleOrderBVO.VFREE8, SaleOrderBVO.VFREE9,
    SaleOrderBVO.VFREE10
  };

  /** �̶����ɱ༭�ı�ͷ�ֶ� */
  private static final String[] HEADFIXFALSEKEY = new String[] {
    SaleOrderHVO.BOFFSETFLAG
  };

  /** ��Ҫ���Ʊ༭�Եı�ͷ�ֶ� */
  private static final String[] HEADKEY = new String[] {
    // ��Ʊ�ͻ����ͻ�
    SaleOrderHVO.CINVOICECUSTID, SaleOrderHVO.CCUSTOMERID,
    SaleOrderHVO.CFREECUSTID,
    // �������͡������ۿ�
    SaleOrderHVO.CTRANTYPEID, SaleOrderHVO.VTRANTYPECODE,
    // �ۿۡ���������
    SaleOrderHVO.NDISCOUNTRATE, SaleOrderHVO.CCHANNELTYPEID,
    // ���� ��������
    SaleOrderHVO.CORIGCURRENCYID, SaleOrderHVO.DBILLDATE,
    // ���㷽ʽ\���䷽ʽ
    SaleOrderHVO.CBALANCETYPEID, SaleOrderHVO.CTRANSPORTTYPEID,
    // ��������
    SaleOrderHVO.DBILLDATE
  };

  private static final String[] LargessApportionKey = new String[] {
    // ����˰���ۡ�����˰����
    SaleOrderBVO.NORIGTAXPRICE, SaleOrderBVO.NORIGPRICE,
    // ����˰���ۡ�����˰����
    SaleOrderBVO.NORIGTAXNETPRICE, SaleOrderBVO.NORIGNETPRICE,
    // ��˰���ۡ���˰����
    SaleOrderBVO.NQTORIGTAXPRICE, SaleOrderBVO.NQTORIGPRICE,
    // ��˰���ۡ���˰����
    SaleOrderBVO.NQTORIGTAXNETPRC, SaleOrderBVO.NQTORIGNETPRICE
  };

  /** ����ı༭�� */
  private Map<String, Boolean> hmEditEnable;

  private SaleOrderBillForm billform;

  private BillCardPanel cardpanel;

  /**
   * CardEditSetter �Ĺ�����
   * 
   * @param billform
   */
  public CardEditSetter(SaleOrderBillForm billform) {

    this.billform = billform;
    this.cardpanel = this.billform.getBillCardPanel();
  }

  /**
   * ����ԭʼ��Ƭ��������Ŀ�ı༭��,�����ʼ����ʱ�����
   * 
   */
  public void cacheEditEnable() {
    this.billform.setHmEditEnable(new HashMap<String, Boolean>(
        CardEditSetter.HEADKEY.length + CardEditSetter.BODYKEY.length));
    for (String key : CardEditSetter.HEADKEY) {
      if (null != this.cardpanel.getHeadItem(key)) {
        this.billform.getHmEditEnable().put(key,
            Boolean.valueOf(this.cardpanel.getHeadItem(key).isEdit()));
      }
    }

    for (String key : CardEditSetter.BODYKEY) {
      if (null != this.cardpanel.getBodyItem(key)) {
        this.billform.getHmEditEnable().put(key,
            Boolean.valueOf(this.cardpanel.getBodyItem(key).isEdit()));
      }
    }
  }

  /**
   * ���ó�ʼ��Ƭǿ�Ʊ༭��
   * 
   */
  public void setCardFixEditFalse() {
    for (String key : CardEditSetter.HEADFIXFALSEKEY) {
      if (null != this.cardpanel.getHeadItem(key)) {
        this.cardpanel.getHeadItem(key).setEdit(false);
      }
    }
    for (String key : CardEditSetter.BODYFIXFALSEKEY) {
      if (null != this.cardpanel.getBodyItem(key)) {
        this.cardpanel.getBodyItem(key).setEdit(false);
      }
    }
  }

  /**
   * ���ݱ�ͷ���ó�ֱ�־���ÿ�Ƭ����༭��
   * 
   */
  public void setEditEnable() {
    if (this.isSaleOrderOffset()) {
      this.setOffsetEdit();
    }
    else {
        //==== lijj ������Ҫ�� ���۶�����δ��������ǰ���»������ν��ں�ͬ�����������ν��ں�ͬ������б�Ҫ�༭������ ===
        SpecialBusiUtil busiUtil = new SpecialBusiUtil();
        IKeyValue keyvalue = new CardKeyValue(this.cardpanel);
        String saleorderId = keyvalue.getHeadStringValue(SaleOrderHVO.CSALEORDERID);
        if(busiUtil.hasLowerBill(saleorderId)){
        	setHasLowerBillEdit();
        }
        else{
        	setOriginalEdit();
        }
        //==== lijj ������Ҫ�� ���۶�����δ��������ǰ���»������ν��ں�ͬ�����������ν��ں�ͬ������б�Ҫ�༭������ ===
    	
      //this.setOriginalEdit();
    }
    


  }

  
  /** ��Ҫ������Ʊ༭�Ե��ֶ� */
  private static final String[] MYBODYKEY = new String[] {
    // ����˰���ۡ�����˰����
    SaleOrderBVO.NORIGTAXPRICE,
    SaleOrderBVO.NORIGPRICE,
    // ����˰���ۡ�����˰����
    SaleOrderBVO.NORIGTAXNETPRICE,
    SaleOrderBVO.NORIGNETPRICE,
    // ��˰���ۡ���˰����
    SaleOrderBVO.NQTORIGTAXPRICE,
    SaleOrderBVO.NQTORIGPRICE,
    // ��˰���ۡ���˰����
    SaleOrderBVO.NQTORIGTAXNETPRC,
    SaleOrderBVO.NQTORIGNETPRICE,
    // ˰���˰���
    SaleOrderBVO.NORIGMNY,
    // ��˰�ϼơ��ۿ۶�
    SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT

  };
  
  private void setHasLowerBillEdit(){
	  //�������޸ı�ͷ����
	  for (String key : CardEditSetter.HEADKEY) {
		  if(SaleOrderHVO.DBILLDATE.equals(key)){
			  continue;
		  }
		  
	      if (null != this.cardpanel.getHeadItem(key)) {
	        this.cardpanel.getHeadItem(key).setEdit(false);
	      }
	  }
	  
	//�������޸ı�������
	 for (String key : CardEditSetter.BODYKEY) {
        if (null != this.cardpanel.getBodyItem(key)) {
          this.cardpanel.getBodyItem(key).setEdit(false);
        }
	 }
	 
	 //�������޸ı��岿��
	 for (String key : CardEditSetter.MYBODYKEY) {
		 if (null != this.cardpanel.getBodyItem(key)) {
			 this.cardpanel.getBodyItem(key).setEdit(true);
		 }
	 }
  
  }
  
  /**
   * ���ó��ʱ�����ֶα༭��
   * 
   */
  public void setOffsetEdit() {
    for (String key : CardEditSetter.HEADKEY) {
      if (null != this.cardpanel.getHeadItem(key)) {
        this.cardpanel.getHeadItem(key).setEdit(false);
      }
    }
    for (String key : CardEditSetter.BODYKEY) {
      if (null != this.cardpanel.getBodyItem(key)) {
        this.cardpanel.getBodyItem(key).setEdit(false);
      }
    }
  }

  /**
   * �ָ��ֶ�ԭʼ�༭��
   * 
   */
  public void setOriginalEdit() {
    for (String key : CardEditSetter.HEADKEY) {
      if (null != this.cardpanel.getHeadItem(key)) {
        this.cardpanel.getHeadItem(key).setEdit(
            this.billform.getHmEditEnable().get(key).booleanValue());
      }
    }
    for (String key : CardEditSetter.BODYKEY) {
      if (null != this.cardpanel.getBodyItem(key)) {
        this.cardpanel.getBodyItem(key).setEdit(
            this.billform.getHmEditEnable().get(key).booleanValue());
      }
    }
  }

  /**
   * ������Ʒ�۸��̯��־���ÿ�Ƭ����༭��
   * 
   */
  public void setEditEnableByFlargessTypeFlag() {

    boolean isSaleOrderApportion = this.isSaleOrderApportion(this.cardpanel);
    // �������ó�ֵĲ����ٸ�����Ʒ�۸��̯��־���ÿ�Ƭ����༭�ԣ����򵥼ۺ;��۱༭�Կ��ƴ���
    if (!isSaleOrderOffset() && isSaleOrderApportion) {
      this.setOffsetEditByFlargessTypeFlag();
    }
    else if (!isSaleOrderOffset() && !isSaleOrderApportion) {
      this.setOriginalEditByFlargessTypeFlag();
    }

  }

  /**
   * ��Ʒ�۸��̯ʱ�����ֶα༭��
   * 
   */
  public void setOffsetEditByFlargessTypeFlag() {
    for (String key : CardEditSetter.LargessApportionKey) {
      if (null != this.cardpanel.getBodyItem(key)) {
        this.cardpanel.getBodyItem(key).setEdit(false);
      }
    }
  }

  /**
   * �ָ��ֶ�ԭʼ�༭�ԣ���Ʒ�۸��̯��
   * 
   */
  public void setOriginalEditByFlargessTypeFlag() {
    for (String key : CardEditSetter.LargessApportionKey) {
      if (null != this.cardpanel.getBodyItem(key)) {
        this.cardpanel.getBodyItem(key).setEdit(
            this.billform.getHmEditEnable().get(key).booleanValue());
      }
    }
  }

  /**
   * ���ݱ������ж϶����Ƿ��������Ʒ�۸��̯
   * 
   * @param saleordervo
   * @return
   */
  private boolean isSaleOrderApportion(BillCardPanel cardPanel) {
    int bodycount = cardPanel.getRowCount();
    for (int i = 0; i < bodycount; i++) {
      Integer largesstypeflag =
          ValueUtils.getInteger(cardPanel.getBodyValueAt(i,
              SaleOrderBVO.FLARGESSTYPEFLAG));
      if (Largesstype.APPORTIONMATERIAL.equalsValue(largesstypeflag)
          || Largesstype.APPORTIONLARGESS.equalsValue(largesstypeflag)) {
        return true;
      }
    }
    return false;
  }
  
  private boolean isSaleOrderOffset() {
    IKeyValue keyvalue = new CardKeyValue(this.cardpanel);
    UFBoolean offsetfalg =
        keyvalue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    return offsetfalg == null ? false : offsetfalg.booleanValue();
  }

}
