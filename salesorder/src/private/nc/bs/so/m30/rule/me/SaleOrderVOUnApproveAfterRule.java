package nc.bs.so.m30.rule.me;

import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;

import nc.pubitf.initgroup.InitGroupQuery;
import nc.pubitf.me.collectorder.incomerestore.ISaleOrderProceeds;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۶���ȡ��������ɾ�����������
 * @scene 
 * ���۶���ȡ��������
 * @param 
 * ��
 * @since 6.31
 * @version 2013-7-6����12:53:38
 * @author ���Ʒ�
 */
public class SaleOrderVOUnApproveAfterRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    // ɾ����Ӧ�Ķ������������
    try {
      if (!InitGroupQuery.isEnabled(AppContext.getInstance().getPkGroup(),
          "4038")) {
        return;
      }
      NCLocator.getInstance().lookup(ISaleOrderProceeds.class).delete(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

}
