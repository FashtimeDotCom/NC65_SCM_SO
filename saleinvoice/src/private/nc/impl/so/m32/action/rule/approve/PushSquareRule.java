package nc.impl.so.m32.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;

/**
 * @description
 * ���۷�Ʊ�������Զ���ʽ�������۽��㵥
 * @scene
 * ���۷�Ʊ������
 * @param
 * ��
 * @since 6.0
 * @version 2011-11-9 ����10:47:02
 * @author ô��
 */
public class PushSquareRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    RemoteFormSOUtil.pushSquareSrv(vos);
  }

}
