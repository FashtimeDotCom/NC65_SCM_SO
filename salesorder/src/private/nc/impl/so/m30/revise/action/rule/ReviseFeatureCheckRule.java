package nc.impl.so.m30.revise.action.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.FeatureSelectUtil;
import nc.vo.so.pub.util.AggVOUtil;

/**
 * @description
 * ���۶����޶�����У��������
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-9 ����3:26:14
 * @author �ű���
 */
public class ReviseFeatureCheckRule implements ICompareRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos, SaleOrderVO[] originVOs) {
    Map<String, SaleOrderBVO> bvosmap = AggVOUtil.createItemMap(originVOs);
    List<String> errorrow = new ArrayList<>();
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        SaleOrderBVO originbvo = bvosmap.get(bvo.getCsaleorderbid());
        if (originbvo == null) {
          continue;
        }
        if (bvo.getAggffilevo() == null
            && PubAppTool.isEqual(bvo.getCmffileid(), originbvo.getCmffileid())) {
          continue;
        }
        if (FeatureSelectUtil.isOut(originbvo)) {
          errorrow.add(bvo.getCrowno());
        }
      }
    }
    if (errorrow.size() == 0) {
      return;
    }
    StringBuilder strrow = new StringBuilder();
    for (String row : errorrow) {
      strrow.append("[" + row + "],");
    }
    strrow.deleteCharAt(strrow.length() - 1);
    String errorstr = NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0", "04006011-0537",
        null, new String[] {
          strrow.toString()
        });/* ������[{0}]�Ѿ��������Σ����ܽ�������ѡ����߸��������롣 */
    ExceptionUtils.wrappBusinessException(errorstr);
  }
}
