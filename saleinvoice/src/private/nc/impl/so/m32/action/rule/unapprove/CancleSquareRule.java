package nc.impl.so.m32.action.rule.unapprove;

import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۷�Ʊ��������۷�Ʊȡ���������
 * @scene
 * ���۷�Ʊ�����
 * @param
 * ��
 * @since 6.1
 * @version 2012-12-21 ����09:21:23
 * @author yaogj
 */
public class CancleSquareRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {

    RemoteFormSOUtil.cancelSquareSrv(vos);

  }

}
