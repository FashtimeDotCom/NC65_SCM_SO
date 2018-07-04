package nc.pubimpl.so.m33.so.m32;

import nc.bs.so.m33.biz.m32.bp.check.SquareInvoiceCheckBP;
import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;
import nc.pubitf.so.m33.so.m32.ISquare33For32Rush;
import nc.vo.so.m33.m32.entity.SquareInvVO;

public class Square33For32RushImpl implements ISquare33For32Rush {

  @Override
  public void checkIFCanInvoiceRush(String[] invoiceIDs) {
    // ��ѯ���㵥����
    SquareInvVO[] sqvos =
        new QuerySquare32VOBP().querySquareInvVOBy32ID(invoiceIDs);
    // �����Ʊ����û�����ý��㶯�� �򷵻�
    if (null == sqvos || sqvos.length == 0) {
      return;
    }
    // ������γ��ⵥ�����ݹ���������Ʒ����Ʊ�������ɶԳ巢Ʊ
    new SquareInvoiceCheckBP().checkETREGForCreateRushInvoice(sqvos);
  }

}
