package nc.vo.so.mbuylargess.view;

import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;

/**
 * ����ƥ����ͼMETA
 * 
 * @since 6.1
 * @version 2012-11-15 15:49:23
 * @author ��ӱ�
 */
public class BuyLargessMatchViewMeta extends DataViewMeta {

  /**
   * ����չʾ���ֶ�
   * 
   */
  public static final String[] HEADVIEWKEYS = new String[] {
    BuyLargessHVO.PK_BUYLARGESS, BuyLargessHVO.NBUYNUM,
    BuyLargessHVO.CPROMOTTYPEID, BuyLargessHVO.CMARKETACTID
  };

  /**
   * �ӱ�չʾ���ֶ�
   */
  public static final String[] BODYVIEWKEYS = new String[] {
    BuyLargessBVO.PK_MATERIAL, BuyLargessBVO.PK_MEASDOC, BuyLargessBVO.NNUM,
    BuyLargessBVO.NPRICE, BuyLargessBVO.NMNY, BuyLargessBVO.FTOPLIMITTYPE,
    BuyLargessBVO.NTOPLIMITVALUE
  };

  /**
   * ��չ�ֶ�
   */
  public static final String PARAINDEX = "paraindex";

  /**
   * ������
   */
  public BuyLargessMatchViewMeta() {
    this.add(BuyLargessBVO.class, BuyLargessMatchViewMeta.BODYVIEWKEYS);
    this.add(BuyLargessHVO.class, BuyLargessMatchViewMeta.HEADVIEWKEYS);
    this.addRelation(BuyLargessBVO.class, BuyLargessBVO.PK_BUYLARGESS,
        BuyLargessHVO.class, BuyLargessHVO.PK_BUYLARGESS);
    this.addExtAttributes();
  }

  private void addExtAttributes() {
    Attribute attribute =
        new Attribute(BuyLargessMatchViewMeta.PARAINDEX, null, null);
    attribute.setJavaType(JavaType.Integer);
    attribute.setCustom(false);
    attribute.setStatic(false);
    attribute.setPersistence(false);
    attribute.setSerializable(true);
    this.add(attribute);
  }
}
