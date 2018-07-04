package nc.bs.so.m38.maintain.rule.insert;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.rule.HeadTotalCalculateRule;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.trade.checkrule.VOChecker;

 /**
 * @description
 * Ԥ������������ǰ�������
 * @scene
 * ����Ԥ������������ǰ
 * @param
 * ��
 * 
 * @author ��־ΰ
 * @time 2010-01-25
 */
public class FillDataBeforeRule implements IRule<PreOrderVO> {

  @Override
  public void process(PreOrderVO[] vos) {
    for (PreOrderVO vo : vos) {
      this.setHeadDefault(vo);
      this.setBodyDefault(vo);
    }
    this.setBillCode(vos);
  }

  /**
   * ���õ��ݺ�
   */
  private void setBillCode(PreOrderVO[] vos) {
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.PreOrder.getCode(), PreOrderHVO.VBILLCODE,
            PreOrderHVO.PK_GROUP, PreOrderHVO.PK_ORG, PreOrderHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.createBillCode(vos);
  }

  private void setBodyDefault(PreOrderVO vo) {
    // ���ñ�������������֯����������
    PreOrderHVO headvo = vo.getParentVO();
    String orgid = headvo.getPk_org();
    String pk_group = headvo.getPk_group();
    UFDate billdate = headvo.getDbilldate();
    PreOrderBVO[] bodyvos = vo.getChildrenVO();
    for (PreOrderBVO bodyvo : bodyvos) {
      bodyvo.setPk_org(orgid);
      bodyvo.setPk_group(pk_group);
      bodyvo.setDbilldate(billdate);
    }

    // Ϊ�к�Ϊ�յ��в����кš�
    VORowNoUtils.setVOsRowNoByRule(bodyvos, PreOrderBVO.CROWNO);
  }

  private void setHeadDefault(PreOrderVO vo) {

    this.setHeadDefValue(vo);
    // ��ͷ�ϼ�ֵ
    IKeyValue keyValue = new VOKeyValue<PreOrderVO>(vo);
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();
  }

  private void setHeadDefValue(PreOrderVO vo) {
    PreOrderHVO head = vo.getParentVO();
    InvocationInfoProxy proxy = InvocationInfoProxy.getInstance();
    String groupid = proxy.getGroupId();
    // ����
    if (VOChecker.isEmpty(head.getPk_group())) {
      head.setPk_group(groupid);
    }
    // ʧЧ����
    if (VOChecker.isEmpty(head.getDabatedate())) {
      head.setDabatedate(TimeUtils.getEndDate(head.getDbilldate()));
    }
    // �ⲿƽ̨����ʱ��һ��������״̬
    if (null == head.getFstatusflag()) {
      head.setFstatusflag(BillStatus.FREE.getIntegerValue());
    }
  }

}
