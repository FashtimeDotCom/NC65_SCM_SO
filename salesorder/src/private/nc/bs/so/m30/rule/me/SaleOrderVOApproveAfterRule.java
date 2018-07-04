package nc.bs.so.m30.rule.me;

import java.util.ArrayList;

import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.pubitf.initgroup.InitGroupQuery;
import nc.pubitf.me.collectorder.incomerestore.ISaleOrderProceeds;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۶������������ɶ��������
 * @scene 
 * ���۶�������ͨ����
 * @param 
 * ��
 * @since 6.31
 * @version 2013-7-6����12:47:58
 * @author ���Ʒ�
 */
public class SaleOrderVOApproveAfterRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    // ���ɶ������������
    try {
      if (!InitGroupQuery.isEnabled(AppContext.getInstance().getPkGroup(),
          "4038")) {
        return;
      }
      // ֻ�е���״̬Ϊ"����ͨ��"�����ɶ��������
      ArrayList<SaleOrderVO> voList = new ArrayList<SaleOrderVO>();
      for (SaleOrderVO vo : vos) {
        if (vo.getParentVO().getFstatusflag()
            .equals(BillStatus.AUDIT.getIntegerValue())) {
          voList.add(vo);
        }
      }
      if (voList.size() > 0) {
        NCLocator.getInstance().lookup(ISaleOrderProceeds.class)
            .add(voList.toArray(new SaleOrderVO[voList.size()]));
      }
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
  }
}
