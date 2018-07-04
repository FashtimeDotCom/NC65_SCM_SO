package nc.pubimpl.so.m38.so.m30.rule;

import java.util.Map;

import nc.bs.pubapp.AppBsContext;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m38.so.m30.Rewrite30Para;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderViewVO;

/**
 * @description
 * ���۶�����дԤ����ִ��ǰ�Ĺ�����(before)��
 * <p><b>�����ۼư���������������ˡ����������
 * @scene
 * ���۶�����дԤ����ִ��ǰ�Ĺ�����
 * @param
 * ��
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-04-08 ����10:37:16
 */
public class RewriteSetArrInfoRule implements IRule<PreOrderViewVO> {

  @Override
  @SuppressWarnings("unchecked")
  public void process(PreOrderViewVO[] vos) {
    Map<String, Rewrite30Para> mParas =
        (Map<String, Rewrite30Para>) BSContext.getInstance().getSession(
            Rewrite30Para.class.getName());
    String carrangeid = AppContext.getInstance().getPkUser();

    UFDate darrdate = AppBsContext.getInstance().getBusiDate();

    for (PreOrderViewVO vo : vos) {
      PreOrderBVO item = vo.getItem();
      Rewrite30Para para = mParas.get(item.getCpreorderbid());
      // ԭ�ۼư�������
      UFDouble narrnum = item.getNarrnum();
      // ���ۼư�������
      narrnum = MathTool.add(narrnum, para.getNnum());
      item.setNarrnum(narrnum);
      // �������
      item.setCarrangeid(carrangeid);
      // ���������
      item.setDarrdate(darrdate);
    }
  }
}
