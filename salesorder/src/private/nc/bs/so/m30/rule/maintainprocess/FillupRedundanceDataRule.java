package nc.bs.so.m30.rule.maintainprocess;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30.ref.so.rtpolicy.SORtPolicyServicesUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;

/**
 * @description
 * ���䵥���������ݹ��򡣲�ȫ������֯���������� �޸����޸�ʱ�䡢Ĭ��ֵ��
 * @scene 
 * ���۶����������޸ı���ǰ
 * @param 
 * ��
 */
public class FillupRedundanceDataRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO bill : vos) {
      // ����ǲ�ȫVO������ʱ��Ҫ������״̬
      // ������������ֶ�
      this.fillupRowByHead(bill);
//    modify by wangshu6 �������˻��������ȡ��ȥ�ɹ����� 20150319
//   // �����˻������
//   this.fillupFretexchange(bill);
      // �����˻�����
      SORtPolicyServicesUtil.fillupReturnPolicy(bill);

      // ��ȫ��״̬
      this.fillupRowStateByHead(bill);

      // ����ǲ�ȫ�кţ�����ʱ��Ҫ������״̬
      this.fillupRowNo(bill);
      // ��������Ĭ��ֵ
      this.fillupBooleanFields(bill);
      // �����ͷ�ϼ���Ϣ
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(bill);
      HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
      totalrule.calculateHeadTotal();
    }
  }

  private void fillupRowStateByHead(SaleOrderVO bill) {

    SaleOrderHVO headvo = bill.getParentVO();

    Integer status = headvo.getFstatusflag();
    SaleOrderBVO[] bodyvos = bill.getChildrenVO();
    for (SaleOrderBVO bodyvo : bodyvos) {
      // ɾ�����в�����������Ϣ
      if (VOStatus.DELETED == bodyvo.getStatus()) {
        continue;
      }
      if (null == bodyvo.getFrowstatus()) {
        bodyvo.setFrowstatus(status);
      }
    }
  }

  private void fillupRowNo(IBill vo) {
    AbstractBill bill = (AbstractBill) vo;
    // Ϊ�к�Ϊ�յ��в����кš�
    CircularlyAccessibleValueObject[] items = bill.getChildrenVO();
    List<CircularlyAccessibleValueObject> bvos =
        new ArrayList<CircularlyAccessibleValueObject>();
    for (CircularlyAccessibleValueObject item : items) {
      int vostatus = item.getStatus();
      // ������ɾ������
      if (vostatus == VOStatus.DELETED) {
        continue;
      }
      bvos.add(item);
    }
    items = bvos.toArray(new CircularlyAccessibleValueObject[0]);
    VORowNoUtils.setVOsRowNoByRule(items, SOItemKey.CROWNO);
  }


  private void fillupRowByHead(SaleOrderVO bill) {

    SaleOrderHVO headvo = bill.getParentVO();
    String orgid = headvo.getPk_org();
    String pk_group = headvo.getPk_group();
    UFDate billdate = headvo.getDbilldate();
    SaleOrderBVO[] bodyvos = bill.getChildrenVO();
    for (SaleOrderBVO bodyvo : bodyvos) {
      int vostatus = bodyvo.getStatus();
      // ɾ�����в�����������Ϣ
      if (VOStatus.DELETED == vostatus) {
        continue;
      }
      bodyvo.setPk_org(orgid);
      bodyvo.setPk_group(pk_group);
      bodyvo.setDbilldate(billdate);

    }
  }

  /**
   * ����ֵ��Ĭ�����
   * 
   * @param bill
   */
  private void fillupBooleanFields(SaleOrderVO bill) {

    SaleOrderBVO[] bodyvos = bill.getChildrenVO();
    String[] booleanFields =
        new String[] {
          SaleOrderBVO.BLARGESSFLAG, SaleOrderBVO.BDISCOUNTFLAG,
          SaleOrderBVO.BLABORFLAG, SaleOrderBVO.BARRANGEDFLAG,
          SaleOrderBVO.BBARSETTLEFLAG, SaleOrderBVO.BBCOSTSETTLEFLAG,
          SaleOrderBVO.BBINDFLAG, SaleOrderBVO.BBINVOICENDFLAG,
          SaleOrderBVO.BBOUTENDFLAG, SaleOrderBVO.BBSENDENDFLAG,
          SaleOrderBVO.BBSETTLEENDFLAG, SaleOrderBVO.BJCZXSFLAG,
          SaleOrderBVO.BPREROWCLOSEFLAG, SaleOrderBVO.BTRIATRADEFLAG
        };
    for (SaleOrderBVO bodyvo : bodyvos) {
      // ɾ�����в�����������Ϣ
      if (VOStatus.DELETED == bodyvo.getStatus()) {
        continue;
      }
      for (String key : booleanFields) {
        if (bodyvo.getAttributeValue(key) == null) {
          bodyvo.setAttributeValue(key, UFBoolean.FALSE);
        }
      }
    }
  }

}
