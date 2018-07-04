package nc.bs.so.pub.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;

/**
 * 
 * @description
 * ��������ʱ���䵥�ݱ�β��Ϣ��Ŀǰ���Ƶ��ˡ��Ƶ�����
 * @scene
 * �������ݱ���ǰ
 * @param 
 * ��
 * 
 * @since 6.0
 * @version 2011-8-11
 * @author ������
 */
public class FillBillTailInfoRuleForIns<E extends IBill> implements IRule<E> {

  @Override
  public void process(E[] vos) {
    for (IBill vo : vos) {
      this.setBillMakeInfo(vo);
    }
  }

  private void setBillMakeInfo(IBill vo) {
    ISuperVO head = vo.getParent();
    AppContext proxy = AppContext.getInstance();
    String billmaker = (String) head.getAttributeValue(SOItemKey.BILLMAKER);
    if (PubAppTool.isNull(billmaker)) {
      head.setAttributeValue(SOItemKey.BILLMAKER, proxy.getPkUser());
    }
    Object makedate = head.getAttributeValue(SOItemKey.DMAKEDATE);
    if (null == makedate) {
      head.setAttributeValue(SOItemKey.DMAKEDATE, proxy.getBusiDate());
    }
  }

}
