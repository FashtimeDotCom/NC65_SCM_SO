package nc.ui.so.m32.billui.action.add;

import java.util.Map;

import nc.ui.pubapp.uif2app.actions.AddMenuAction;
import nc.vo.pub.pf.PfAddInfo;
import nc.vo.scmpub.res.billtype.SOBillType;

/**
 * ���۷�Ʊ������ť��
 * 
 * @since 6.0
 * @version 2011-6-10 ����06:09:00
 * @author ô��
 */
public class InvoiceAddMenuAction extends AddMenuAction {

  private static final long serialVersionUID = 1L;

  @Override
  protected Map<String, PfAddInfo> getSourceBillTypes() {
    Map<String, PfAddInfo> ret = super.getSourceBillTypes();
    if (!ret.containsKey(SOBillType.Order.getCode())) {
      PfAddInfo pfaddinfo = new PfAddInfo();
      pfaddinfo.setSrc_billtype(SOBillType.Order.getCode());
      pfaddinfo.setSrc_billtypename(SOBillType.Order.getName());
      pfaddinfo.setMakeflag(false);
      ret.put(SOBillType.Order.getCode(), pfaddinfo);
    }
//    Ӧ�շ��ý��㵥��ԭ�����۽��㵥���������۽��㵥���ֲĴ�����㵥���Ǹִ�����㵥������ԭ�ϱ��˵�
    PfAddInfo pfaddinfo = new PfAddInfo();
    pfaddinfo.setSrc_billtype("IT02");
    pfaddinfo.setSrc_billtypename("ԭ�����۽��㵥");
    pfaddinfo.setMakeflag(false);
    ret.put("IT02", pfaddinfo);
    
    PfAddInfo pfaddinfo1 = new PfAddInfo();
    pfaddinfo.setSrc_billtype("IT01");
    pfaddinfo.setSrc_billtypename("�������۽��㵥");
    pfaddinfo.setMakeflag(false);
    ret.put("IT01", pfaddinfo1);

    PfAddInfo pfaddinfo2 = new PfAddInfo();
    pfaddinfo.setSrc_billtype("ET02");
    pfaddinfo.setSrc_billtypename("�Ǹִ�����㵥");
    pfaddinfo.setMakeflag(false);
    ret.put("ET02", pfaddinfo2);
    
    PfAddInfo pfaddinfo3 = new PfAddInfo();
    pfaddinfo.setSrc_billtype("ET03");
    pfaddinfo.setSrc_billtypename("�ֲĴ�����㵥");
    pfaddinfo.setMakeflag(false);
    ret.put("ET03", pfaddinfo3);
    
    PfAddInfo pfaddinfo4 = new PfAddInfo();
    pfaddinfo.setSrc_billtype("LM40");
    pfaddinfo.setSrc_billtypename("����ԭ�ϱ��˵�");
    pfaddinfo.setMakeflag(false);
    ret.put("LM40", pfaddinfo4);
    
    PfAddInfo pfaddinfo5 = new PfAddInfo();
    pfaddinfo.setSrc_billtype("LM21");
    pfaddinfo.setSrc_billtypename("Ӧ�����ý��㵥");
    pfaddinfo.setMakeflag(false);
    ret.put("LM21", pfaddinfo5);
    
    
    return ret;
  }

}
