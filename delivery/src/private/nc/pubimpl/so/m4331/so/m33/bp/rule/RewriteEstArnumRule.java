package nc.pubimpl.so.m4331.so.m33.bp.rule;

import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m4331.so.m33.RewriteEstarnumPara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 
 * @description
 * ��д�������ݹ�Ӧ������ʱ
 * @scene
 * ��д�������ݹ�Ӧ������ʱ�����ۼ�ȷ��Ӧ������
 * @param
 * ��
 *
 * @since 6.5
 * @version 2015-10-19 ����2:12:13
 * @author zhangby5
 */
public class RewriteEstArnumRule implements IRule<DeliveryViewVO> {

  @Override
  public void process(DeliveryViewVO[] views) {
    @SuppressWarnings("unchecked")
    Map<String, RewriteEstarnumPara> index =
        (Map<String, RewriteEstarnumPara>) BSContext.getInstance().getSession(
            RewriteEstarnumPara.class.getName());
    for (DeliveryViewVO view : views) {
      String cdeliverybid = view.getItem().getCdeliverybid();
      UFDouble arnum = index.get(cdeliverybid).getEstarnum();
      UFDouble totalArnum = view.getItem().getNtotalarnum();
      totalArnum = MathTool.add(arnum, totalArnum);
      view.getItem().setNtotalarnum(totalArnum);
    }
  }
}
