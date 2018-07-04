package nc.vo.so.mbuylargess.view;

import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.Attribute;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;

/**
 * ��������չʾ����ͼVOMeta��
 * 
 * @since 6.1
 * @version 2012-10-30 19:58:40
 * @author ��ӱ�
 */
public class BuyLargessShowViewMeta extends DataViewMeta {

  /**
   * ���к�
   */
  public static final String PARAINDEX = "paraindex";

  /**
   * ������
   */
  public BuyLargessShowViewMeta() {
    this.add(BuyLargessBVO.class);
    this.add(BuyLargessHVO.class);
    this.addRelation(BuyLargessBVO.class, BuyLargessBVO.PK_BUYLARGESS,
        BuyLargessHVO.class, BuyLargessHVO.PK_BUYLARGESS);
    this.addExtAttributes();
  }

  private void addExtAttributes() {
    Attribute attribute =
        new Attribute(BuyLargessShowViewMeta.PARAINDEX, null, null);
    attribute.setJavaType(JavaType.Integer);
    attribute.setCustom(false);
    attribute.setStatic(false);
    attribute.setPersistence(false);
    attribute.setSerializable(true);
    this.add(attribute);
  }
}
