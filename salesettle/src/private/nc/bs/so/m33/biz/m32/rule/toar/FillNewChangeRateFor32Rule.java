package nc.bs.so.m33.biz.m32.rule.toar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyInfo;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.pub.calculator.PriceNumMnyCalculatorForVO;
import nc.vo.so.pub.util.SOCurrencyUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۷�ƱӦ�ս���ǰ���ʴ��� ���������֯��λ�Һ�Ӧ��/Ӧ����֯��λ��һ�£��ӹ�Ӧ��������Ӧ����Ʊ���Ա༭���ʣ����ʴ�����Я������
 * ���򣬻���ԭ���������㣨������Ϊ���е�ί��Ӧ��/Ӧ����������֯��Ӧ��/Ӧ����֯�ı�λ�Ҷ���һ�µġ� ��һ�²���Ϊ���Ժ������Ҫ������
 * @scene
 * ���۷�ƱӦ�ս���ǰ
 * @param
 * ��
 * @author zhangcheng
 * @time 2010-5-28 ����10:15:43
 */
public class FillNewChangeRateFor32Rule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {

    // ��֯�۱����ʴ���
    SquareInvBVO[] bvos = this.orgChangeRateProcess(vos);

    // ���¼�����ر��ҽ��
    if (!VOChecker.isEmpty(bvos)) {
      this.calLocalMny(bvos);
    }
  }

  /**
   * ���¼�����ر��ҽ��
   * 
   * @param vos
   */
  private void calLocalMny(SquareInvBVO[] bvos) {
    new PriceNumMnyCalculatorForVO().calculateLocal(bvos);
  }

  /**
   * ��֯�۱����ʴ���
   * 
   * @param vos
   */
  private SquareInvBVO[] orgChangeRateProcess(SquareInvVO[] vos) {
    // Ӧ����֯��λ��<Ӧ����֯,Ӧ����֯��λ��>
    Map<String, String> marorg_cy = new HashMap<String, String>();
    for (SquareInvVO svo : vos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {
        String arorg = bvo.getCarorgid();
        String arcy = CurrencyInfo.getLocalCurrtypeByOrgID(arorg);
        marorg_cy.put(arorg, arcy);
      }
    }

    UFDate sysdate = BSContext.getInstance().getDate();
    List<SquareInvBVO> list = new ArrayList<SquareInvBVO>();
    // ����������֯�۱�����
    for (SquareInvVO svo : vos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {
        String src_currency_pk = bvo.getCorigcurrencyid();
        String sq_currency_pk = bvo.getCcurrencyid();
        String arorg = bvo.getCarorgid();
        String ar_currency_pk = marorg_cy.get(arorg);
        // ������֯��λ�Һ�Ӧ����֯��λ�Ҳ�һ��
        if (!PubAppTool.isEqual(sq_currency_pk, ar_currency_pk)) {
          // String pk_group = svo.getParentVO().getPk_group();
          UFDouble nexchangerate =
              SOCurrencyUtil.getInCurrencyRateByOrg(arorg, src_currency_pk,
                  ar_currency_pk, sysdate);
          bvo.setNexchangerate(nexchangerate);
          list.add(bvo);
        }
      }
    }

    SquareInvBVO[] bvos = null;
    if (list.size() > 0) {
      bvos = list.toArray(new SquareInvBVO[list.size()]);
    }
    return bvos;
  }
}
