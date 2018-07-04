package nc.vo.so.m33.pub.util;

import nc.vo.arap.basebill.BaseAggVO;
import nc.vo.arap.basebill.BaseBillVO;
import nc.vo.arap.basebill.BaseItemVO;
import nc.vo.arap.pub.BillEnumCollection;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;

public class ARBillUtil {

  private static ARBillUtil instance = new ARBillUtil();

  private ARBillUtil() {
    super();
  }

  public static ARBillUtil getInstance() {
    return ARBillUtil.instance;
  }

  /**
   * ��������������Ӧ�յ��ݱ������Ƿ�Դͷ�����۶���
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-8-16 ����11:24:58
   */
  public boolean isFirstFromSaleOrder(BaseItemVO itemVO) {
    boolean isFirstFromSaleOrder = false;
    String src_billtype = itemVO.getSrc_billtype();
    if (!PubAppTool.isNull(src_billtype)) {
      if (SOBillType.Order.getCode().equals(src_billtype.trim())) {
        isFirstFromSaleOrder = true;
      }
    }
    return isFirstFromSaleOrder;
  }

  /**
   * ��������������Ӧ�յ����Ƿ���Դ������
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param billvo
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-8-16 ����11:17:25
   */
  public boolean isFromSO(BaseAggVO billvo) {
    boolean isFromSO = false;
    BaseBillVO parent = (BaseBillVO) billvo.getParentVO();
    int syscode = parent.getSrc_syscode().intValue();
    if (BillEnumCollection.FromSystem.SO.VALUE.intValue() == syscode) {
      isFromSO = true;
    }
    return isFromSO;
  }

  /**
   * ��������������Ӧ�յ����Ƿ������۽������ɵĵ���
   * Ӧ�յ����Ƿ���Դ������ϵͳ
   * Ҫ�������ȫ��ԴͷΪ���۶�����������Դ�����۷�Ʊ�������۳��ⵥ
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param billvo
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-8-16 ����11:19:47
   */
  public boolean isSOSquareDriveARBill(BaseAggVO billvo) {
    boolean isSOSquareDriveARBill = false;
    boolean isFromSO = this.isFromSO(billvo);

    // ��Դ������ϵͳ
    if (isFromSO) {
      BaseItemVO[] items = (BaseItemVO[]) billvo.getChildrenVO();
      for (BaseItemVO itemVO : items) {
        if (!this.isFirstFromSaleOrder(itemVO)) {
          isSOSquareDriveARBill = false;
          break;
        }
        if (!this.isSrcFrom4Cor32(itemVO)) {
          isSOSquareDriveARBill = false;
          break;
        }
        isSOSquareDriveARBill = true;
      }
    }

    return isSOSquareDriveARBill;
  }

  /**
   * ��������������Ӧ�յ��ݱ������Ƿ���Դ�����۳��ⵥ�������۷�Ʊ
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemVO
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-8-16 ����11:32:43
   */
  public boolean isSrcFrom4Cor32(BaseItemVO itemVO) {
    boolean isSrcFrom4Cor32 = false;
    String top_billtype = itemVO.getTop_billtype();
    if (!PubAppTool.isNull(top_billtype)) {
      if (SOBillType.Invoice.getCode().equals(top_billtype.trim())
          || ICBillType.SaleOut.getCode().equals(top_billtype.trim())) {
        isSrcFrom4Cor32 = true;
      }
    }
    return isSrcFrom4Cor32;
  }
  
  public MapList<String, AggReceivableBillVO> splitArapVO(
      AggReceivableBillVO[] arapvos) {
    MapList<String, AggReceivableBillVO> arapvoMapList =
        new MapList<String, AggReceivableBillVO>();
    for (AggReceivableBillVO billVO : arapvos) {
      arapvoMapList.put(billVO.getHeadVO().getPk_org(), billVO);
    }
    return arapvoMapList;
  }

}
