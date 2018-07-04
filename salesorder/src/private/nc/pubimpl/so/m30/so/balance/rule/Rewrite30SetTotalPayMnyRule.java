package nc.pubimpl.so.m30.so.balance.rule;

import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m30.so.balance.RewriteVerifyPara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * 
 * @description
 * �տ������д���۶���ʵ��Ԥ�տ���
 * @scene
 * ��д���۶���ʵ��Ԥ�տ���ʱ�����ۼƲ���������
 * @param
 * ��
 *
 * @since 6.0
 * @version 2011-6-3 ����03:42:58
 * @author ��־ΰ
 */
public class Rewrite30SetTotalPayMnyRule implements IRule<SaleOrderBVO> {

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderBVO[] bodys) {
    Map<String, RewriteVerifyPara> mParas =
        (Map<String, RewriteVerifyPara>) BSContext.getInstance().getSession(
            RewriteVerifyPara.class.getName());

    for (SaleOrderBVO body : bodys) {
      RewriteVerifyPara para = mParas.get(body.getCsaleorderbid());
      // �ۼƲ���������
      UFDouble totalpaymny = para.getNmny();
      UFDouble old_totalpaymny = body.getNtotalpaymny();
      body.setNtotalpaymny(MathTool.add(old_totalpaymny, totalpaymny));
    }
  }
}
