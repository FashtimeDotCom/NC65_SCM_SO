package nc.impl.so.m30.revise.action.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * @description
 * ���۶����޶�����ǰУ����ó�ֽ��
 * @scene
 * ���۶����޶�����ǰ
 * @param
 * ��
 */
public class OffsetCheckRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] saleordervos) {

    for (SaleOrderVO bill : saleordervos) {
      this.checkOffSetRate(bill);
    }
  }

  private void checkOffSetRate(SaleOrderVO bill) {
    // ����֯
    String pk_org = bill.getParentVO().getPk_org();
    UFDouble so15 = SOSysParaInitUtil.getSO15(pk_org);
    if (MathTool.isZero(so15)) {
      return;
    }
    List<String> listerrrow = new ArrayList<String>();
    SaleOrderBVO[] bodys = bill.getChildrenVO();
    for (SaleOrderBVO bvo : bodys) {
      if (VOStatus.DELETED == bvo.getStatus()
          || VOStatus.UNCHANGED == bvo.getStatus()) {
        continue;
      }
      UFDouble submny = bvo.getNorigsubmny();
      if (MathTool.isZero(submny)) {
        continue;
      }
      UFDouble bfsubmny = MathTool.add(submny, bvo.getNorigtaxmny());

      UFDouble cansubmny = bfsubmny.multiply(so15.div(SOConstant.ONEHUNDRED));
      cansubmny = cansubmny.setScale(submny.getPower(), UFDouble.ROUND_HALF_UP);
      if (MathTool.compareTo(cansubmny, submny) < 0) {
        listerrrow.add(bvo.getCrowno());
      }
    }
    if (listerrrow.size() > 0) {
      StringBuilder strrow = new StringBuilder();
      for (String row : listerrrow) {
        strrow.append("[" + row + "],");
      }
      strrow.deleteCharAt(strrow.length() - 1);
      String errorstr = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
          "04006011-0447", null, new String[] {
            strrow.toString()
          });/* ������[{0}]��˰�ϼ��޶�ֵ��С�����ܱ�֤���ǰ���*��ֱ��ʴ����ۼƳ�ֽ� */
      ExceptionUtils.wrappBusinessException(errorstr);
    }
  }
}
