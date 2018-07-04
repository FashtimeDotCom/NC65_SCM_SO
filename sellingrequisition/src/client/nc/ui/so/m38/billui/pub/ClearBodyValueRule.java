package nc.ui.so.m38.billui.pub;

import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ����Ԥ������ձ����ֶε�ֵ
 * 
 * @since 6.0
 * @version 2012-2-9 ����10:55:15
 * @author ����
 */
public class ClearBodyValueRule {

  /**
   * ������Ҫ��յ��ֶ�
   */
  private static final String[] DEMANDCLEAR_KEY = new String[] {
    // ���ɸ�������
    PreOrderBVO.CVENDORID,
    PreOrderBVO.CPROJECTID,
    PreOrderBVO.CQUALITYLEVELID,
    PreOrderBVO.CPRODUCTORID,
    PreOrderBVO.VFREE1,
    PreOrderBVO.VFREE2,
    PreOrderBVO.VFREE3,
    PreOrderBVO.VFREE4,
    PreOrderBVO.VFREE5,
    PreOrderBVO.VFREE6,
    PreOrderBVO.VFREE7,
    PreOrderBVO.VFREE8,
    PreOrderBVO.VFREE9,
    PreOrderBVO.VFREE10,
    // ѯ����Ϣ
    PreOrderBVO.NASKQTORIGNETPRICE,
    PreOrderBVO.NASKQTORIGPRICE,
    PreOrderBVO.NASKQTORIGTAXPRC,
    PreOrderBVO.NASKQTORIGTXNTPRC,
    PreOrderBVO.CPRICEPOLICYID,
    PreOrderBVO.CPRICEITEMID,
    PreOrderBVO.CPRICEITEMTABLEID,
    PreOrderBVO.CPRICEFORMID,
    // ��Ʒ��־λ
    PreOrderBVO.BLARGESSFLAG,

    // ��ԭ�ҵ���
    PreOrderBVO.NORIGNETPRICE,
    PreOrderBVO.NORIGPRICE,
    PreOrderBVO.NORIGTAXPRICE,
    PreOrderBVO.NORIGTAXNETPRICE,
    // �����ҵ���
    PreOrderBVO.NNETPRICE,
    PreOrderBVO.NPRICE,
    PreOrderBVO.NTAXPRICE,
    PreOrderBVO.NTAXNETPRICE,
    // ���ҵ���
    PreOrderBVO.NQTNETPRICE,
    PreOrderBVO.NQTPRICE,
    PreOrderBVO.NQTTAXNETPRICE,
    PreOrderBVO.NQTTAXPRICE,
    // ԭ�ҵ���
    PreOrderBVO.NQTORIGTAXNETPRC, PreOrderBVO.NQTORIGTAXPRICE,
    PreOrderBVO.NQTORIGPRICE,
    PreOrderBVO.NQTORIGNETPRICE,
    // ���
    PreOrderBVO.NGLOBALTAXMNY, PreOrderBVO.NGROUPTAXMNY,
    PreOrderBVO.NORIGTAXMNY, PreOrderBVO.NORIGMNY, PreOrderBVO.NORIGDISCOUNT,
    // ���ҽ��
    PreOrderBVO.NTAX, PreOrderBVO.NMNY, PreOrderBVO.NTAXMNY,
    PreOrderBVO.NDISCOUNT,
    // ���Ž��
    PreOrderBVO.NGROUPMNY, PreOrderBVO.NGROUPTAXMNY,
    // ȫ�ֽ��
    PreOrderBVO.NGLOBALMNY, PreOrderBVO.NGLOBALTAXMNY,
  };

  private IKeyValue keyValue;

  public ClearBodyValueRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ��ձ����ֶε�ֵ
   * 
   * @param editrow �к�����
   */
  public void clearBodyValue(int[] rows) {
    for (int row : rows) {
      for (String key : ClearBodyValueRule.DEMANDCLEAR_KEY) {
        this.keyValue.setBodyValue(row, key, null);
      }
    }
  }
}
