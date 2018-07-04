package nc.ui.so.m30.billui.rule;

import java.util.Map;

import nc.vo.ct.business.enumeration.Ninvctlstyle;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.enumeration.Largesstype;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class ClearBodyValueRule {

  private static final String[] FORCECLEAR_KEY = new String[] {
    // ���ɸ�������
    SaleOrderBVO.CVENDORID,
    //    SaleOrderBVO.CPROJECTID,  // �������Ŀ modify by jilu for EHP1�ϵ�633��ò������Ϊ��Ŀ�Ӻ�ͬ�ϴ������ģ����Բ����
    SaleOrderBVO.CPRODUCTORID,
    SaleOrderBVO.CFACTORYID, SaleOrderBVO.CQUALITYLEVELID, SaleOrderBVO.VFREE1,
    SaleOrderBVO.VFREE2, SaleOrderBVO.VFREE3,
    SaleOrderBVO.VFREE4,
    SaleOrderBVO.VFREE5,
    SaleOrderBVO.VFREE6,
    SaleOrderBVO.VFREE7,
    SaleOrderBVO.VFREE8,
    SaleOrderBVO.VFREE9,
    SaleOrderBVO.VFREE10,
    // ѯ����Ϣ
    SaleOrderBVO.NASKQTORIGTAXPRC,
    SaleOrderBVO.NASKQTORIGPRICE,
    SaleOrderBVO.NASKQTORIGTXNTPRC,
    SaleOrderBVO.NASKQTORIGNETPRICE,
    SaleOrderBVO.CPRICEPOLICYID,
    SaleOrderBVO.CPRICEITEMID,
    SaleOrderBVO.CPRICEITEMTABLEID,
    SaleOrderBVO.CPRICEFORMID,
    // ��Ʒ��־λ
    // SaleOrderBVO.BLARGESSFLAG,
    // ��������
    SaleOrderBVO.CLARGESSSRCID, SaleOrderBVO.CBINDSRCID,
    SaleOrderBVO.CBUYPROMOTTYPEID, SaleOrderBVO.CBUYLARGESSACTID,

    // �˻���Ϣ
    SaleOrderBVO.CRETREASONID, SaleOrderBVO.VRETURNMODE,
    SaleOrderBVO.CRETPOLICYID,
    // �۸���Ʒ��̯
    SaleOrderBVO.NLARGESSMNY, SaleOrderBVO.NLARGESSTAXMNY,
    // ������
    SaleOrderBVO.CMFFILEID,SaleOrderBVO.NMFFILEPRICE,
    SOConstant.AGGFFILEVO,
    // ɾ�����ε��������κ�
    SaleOrderBVO.PK_BATCHCODE,
    SaleOrderBVO.VBATCHCODE
  };
  
  private static final String[] HEAD_PRICE_KEY = new String[]{
	  SaleOrderHVO.NTOTALORIGSUBMNY, SaleOrderHVO.NTOTALORIGMNY,
	  SaleOrderHVO.NTOTALMNY
  };
  
  private static final String[] BODY_FFILE_KEY = new String[]{
	  	// ������
	    SaleOrderBVO.CMFFILEID,SaleOrderBVO.NMFFILEPRICE,
	    SOConstant.AGGFFILEVO
  };
  
  private static final String[] BODY_PRICE_KEY = new String[]{
	  	// ԭ�ҵ���
	    SaleOrderBVO.NQTORIGTAXPRICE,
	    SaleOrderBVO.NQTORIGPRICE,
	    SaleOrderBVO.NQTORIGTAXNETPRC,
	    SaleOrderBVO.NQTORIGNETPRICE,
	    // ��ԭ�ҵ���
	    SaleOrderBVO.NORIGPRICE,
	    SaleOrderBVO.NORIGTAXPRICE,
	    SaleOrderBVO.NORIGNETPRICE,
	    SaleOrderBVO.NORIGTAXNETPRICE,
	    // ���
	    SaleOrderBVO.NORIGMNY,
	    SaleOrderBVO.NORIGTAXMNY,
	    SaleOrderBVO.NORIGDISCOUNT,
	    // ���ҵ���
	    SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE,
	    SaleOrderBVO.NQTTAXPRICE,
	    SaleOrderBVO.NQTPRICE,
	    // �����ҵ���
	    SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NNETPRICE,
	    SaleOrderBVO.NTAXNETPRICE,
	    // ���ҽ��
	    SaleOrderBVO.NTAX, SaleOrderBVO.NMNY, SaleOrderBVO.NTAXMNY,
	    SaleOrderBVO.NDISCOUNT,
	    // ���Ž��
	    SaleOrderBVO.NGROUPMNY, SaleOrderBVO.NGROUPTAXMNY,
	    // ȫ�ֽ��
	    SaleOrderBVO.NGLOBALMNY, SaleOrderBVO.NGLOBALTAXMNY,
	    SaleOrderBVO.NBFORIGSUBMNY,SaleOrderBVO.NORIGSUBMNY
  };

  private static final String[] NUMPRICE_KEY = new String[] {
    // ����
    SaleOrderBVO.NNUM,
    SaleOrderBVO.NASTNUM,
    SaleOrderBVO.NQTUNITNUM,
    // ԭ�ҵ���
    SaleOrderBVO.NQTORIGTAXPRICE,
    SaleOrderBVO.NQTORIGPRICE,
    SaleOrderBVO.NQTORIGTAXNETPRC,
    SaleOrderBVO.NQTORIGNETPRICE,
    // ��ԭ�ҵ���
    SaleOrderBVO.NORIGPRICE,
    SaleOrderBVO.NORIGTAXPRICE,
    SaleOrderBVO.NORIGNETPRICE,
    SaleOrderBVO.NORIGTAXNETPRICE,
    // ���
    SaleOrderBVO.NORIGMNY,
    SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT,
    // ���ҵ���
    SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE,
    SaleOrderBVO.NQTTAXPRICE,
    SaleOrderBVO.NQTPRICE,
    // �����ҵ���
    SaleOrderBVO.NPRICE, SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NNETPRICE,
    SaleOrderBVO.NTAXNETPRICE,
    // ���ҽ��
    SaleOrderBVO.NTAX, SaleOrderBVO.NMNY, SaleOrderBVO.NTAXMNY,
    SaleOrderBVO.NDISCOUNT,
    // ���Ž��
    SaleOrderBVO.NGROUPMNY, SaleOrderBVO.NGROUPTAXMNY,
    // ȫ�ֽ��
    SaleOrderBVO.NGLOBALMNY, SaleOrderBVO.NGLOBALTAXMNY
  };

  private static final String[] SRCCLEAR_KEY = new String[] {
    // ��Դ������Ϣ
    SaleOrderBVO.VSRCTYPE, SaleOrderBVO.CSRCID, SaleOrderBVO.CSRCBID,
    SaleOrderBVO.VSRCCODE, SaleOrderBVO.VSRCROWNO,
    SaleOrderBVO.VSRCTRANTYPE,
    // ��ͬ��Ϣ �޸ģ�zhangby5 ���պ�ͬ�������۶���ʱ��������ͬ�����޸�����ʱ����ͬ����Ŀ�������
    SaleOrderBVO.CCTMANAGEBID, SaleOrderBVO.CCTMANAGEID,
    SaleOrderBVO.VCTCODE,
    SaleOrderBVO.CPROJECTID,
    // Դͷ������Ϣ
    SaleOrderBVO.VFIRSTTYPE, SaleOrderBVO.VFIRSTCODE, SaleOrderBVO.CFIRSTID,
    SaleOrderBVO.CFIRSTBID, SaleOrderBVO.VFIRSTROWNO,
    SaleOrderBVO.VFIRSTTRANTYPE
  };

  Map<String, CtBusinessVO> ctMap;

  private IKeyValue keyValue;

  public ClearBodyValueRule(IKeyValue keyValue, Map<String, CtBusinessVO> ctMap) {
    this.ctMap = ctMap;
    this.keyValue = keyValue;
  }

  public void clearBodyNoNumPriceMnyValue(int editrow) {
    for (String key : ClearBodyValueRule.FORCECLEAR_KEY) {
      this.keyValue.setBodyValue(editrow, key, null);
    }

    this.keyValue.setBodyValue(editrow, SaleOrderBVO.FLARGESSTYPEFLAG,
        Largesstype.NOAPPORTION.getIntValue());

    this.clearBodySrcValue(editrow);
  }

  public void clearBodyNumPirceMnyValue(int editrow) {
    if (this.isRefCTRows(editrow)) {
      return;
    }
    if(isRefMatappRows(editrow)){
    	return;
    }
    for (String key : ClearBodyValueRule.NUMPRICE_KEY) {
      this.keyValue.setBodyValue(editrow, key, null);
    }
  }

  private void clearBodySrcValue(int editrow) {
    if (this.isRefCTRows(editrow)) {
      return;
    }
    if(isRefMatappRows(editrow)){
    	return;
    }
    for (String key : ClearBodyValueRule.SRCCLEAR_KEY) {
      this.keyValue.setBodyValue(editrow, key, null);
    }
  }
  
  private boolean isRefMatappRows(int row){
	  String vsrctype =
		        this.keyValue.getBodyStringValue(row, SaleOrderBVO.VSRCTYPE);
	return "4648".equals(vsrctype);
  }

  /**
   * ��Դ��ͬ���Ϸ����С����༭���϶�Ϊ��ǰ���Ϸ��������ϣ������д���Ϸ����С����Բ�Ӧ��գ����豣������
   */
  private boolean isRefCTRows(int row) {

    String vsrctype =
        this.keyValue.getBodyStringValue(row, SaleOrderBVO.VSRCTYPE);
    String cctmanagebid =
        this.keyValue.getBodyStringValue(row, SaleOrderBVO.CCTMANAGEBID);
    if (CTBillType.SaleDaily.getCode().equals(vsrctype) && null != this.ctMap
        && this.ctMap.containsKey(cctmanagebid)) {

      CtBusinessVO busiVO = this.ctMap.get(cctmanagebid);
      if (null != busiVO) {
        boolean isMarbasclass =
            Ninvctlstyle.MARBASCLASS.value().equals(busiVO.getNinvctlstyle());
        boolean isWithOut =
            Ninvctlstyle.WITHOUT.value().equals(busiVO.getNinvctlstyle());
        if (isMarbasclass || isWithOut) {
          return true;
        }
      }
    }
    return false;
  }
  
  /**
   * �����ͷ�ͱ������н������ֶ�
   * 
   * @param rows
   */
  public void clearAllPriceKey(int[] rows){
	  
	  if(rows==null||rows.length==0){
		  return;
	  }
	  for(int row : rows){
		  for (String key : ClearBodyValueRule.BODY_PRICE_KEY) {
		      this.keyValue.setBodyValue(row, key, null);
		  }
	  }
	  for (String key : ClearBodyValueRule.HEAD_PRICE_KEY) {
	      this.keyValue.setHeadValue(key, null);
	  }
  }
  
  /**
   * �������������ֶ�
   * 
   * @param rows
   */
  public void clearAllFfileKey(int[] rows){
	  if(rows==null||rows.length==0){
		  return;
	  }
	  
	  for(int row : rows){
		  for (String key : ClearBodyValueRule.BODY_FFILE_KEY) {
		      this.keyValue.setBodyValue(row, key, null);
		  }
	  }
	  
  }

}
