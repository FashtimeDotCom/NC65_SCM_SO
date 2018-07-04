package nc.bs.so.pub.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.price.priceform.IPriceFormService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;

/**
 * Ԥ���������۶������������±���ʱ��Ϊ�۸�ģ���ṩ�۸�����ַ������ݿ����ñ������
 * 
 * @since 6.0
 * @version 2011-9-30 ����04:54:28
 * @author ������
 */
public class FindPriceFromBeforeRule implements IRule<AbstractBill> {

  private IPriceFormService priceFormService;

  @Override
  public void process(AbstractBill[] vos) {
    for (AbstractBill vo : vos) {
      this.savePriceForm(vo);
    }
  }

  private IPriceFormService getPriceFormService() {
    if (this.priceFormService == null) {
      this.priceFormService =
          NCLocator.getInstance().lookup(IPriceFormService.class);
    }
    return this.priceFormService;
  }

  private void savePriceForm(AbstractBill bill) {
    CircularlyAccessibleValueObject[] bodys = bill.getChildrenVO();
    List<String> alpriceform = new ArrayList<String>();
    for (CircularlyAccessibleValueObject body : bodys) {
      if (VOStatus.DELETED == body.getStatus()) {
        continue;
      }
      String priceform =
          (String) body.getAttributeValue(SOItemKey.CPRICEFORMID);
      if (!PubAppTool.isNull(priceform)) {
        alpriceform.add(priceform);
      }
    }
    if (alpriceform.size() > 0) {
      String[] priceforms = new String[alpriceform.size()];
      alpriceform.toArray(priceforms);
      try {
        String primarykey = bill.getParentVO().getPrimaryKey();
        this.getPriceFormService().savePriceForm(primarykey, priceforms, true);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }

  }
}
