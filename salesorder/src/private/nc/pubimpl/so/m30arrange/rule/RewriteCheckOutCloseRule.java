package nc.pubimpl.so.m30arrange.rule;

import java.util.List;
import java.util.Map.Entry;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * ���۶�����������ʱ
 * @scene
 * ���۶�����������ʱ��鶩������ر�״̬
 * @param
 * ��
 *
 * @since 6.5
 * @version 2015-10-19 ����2:15:58
 * @author zhangby5
 */
public class RewriteCheckOutCloseRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] viewos) {
    MapList<String, String> mapoutclose = new MapList<String, String>();
    for (SaleOrderViewVO view : viewos) {
      if (null != view.getBody().getBboutendflag()
          && view.getBody().getBboutendflag().booleanValue()) {
        mapoutclose.put(view.getHead().getVbillcode(), view.getBody()
            .getCrowno());
      }
    }
    if (mapoutclose.size() > 0) {
      StringBuilder errmsg = new StringBuilder();
      for (Entry<String, List<String>> entry : mapoutclose.entrySet()) {
        String billcode = entry.getKey();
        errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0420", null, new String[] {
              billcode
            })/*���۶���[{0}]*/);
        for (String rowno : entry.getValue()) {

          errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
              "04006011-0421", null, new String[] {
                rowno
              })/*��[{0}]�У�*/);
        }
        errmsg.deleteCharAt(errmsg.length() - 1);
        errmsg.append("\r\n");
      }
      errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
          "04006011-0422")/*@res "�Ѿ�����رգ��������ٰ��ţ���ˢ�µ�������"*/);
      ExceptionUtils.wrappBusinessException(errmsg.toString());
    }
  }
}
