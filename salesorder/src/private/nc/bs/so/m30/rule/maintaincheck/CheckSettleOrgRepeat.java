package nc.bs.so.m30.rule.maintaincheck;

import java.util.HashSet;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 
 * @description
 * ���۶�������ʱ
 * @scene
 * ��������������֯�Ƿ�һ��
 * @param
 * ��
 *
 * @since 6.35
 * @version 2013-11-26 08:34:37
 * @author liangjm
 */
public class CheckSettleOrgRepeat implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO vo : vos) {
      String carsubtypeid = vo.getParentVO().getCarsubtypeid();
      if(PubAppTool.isNull(carsubtypeid)){
    	  continue;
      }
      this.checkRepeat(vo);
    }
  }

  private void checkRepeat(SaleOrderVO vo) {
    SaleOrderBVO[] bvos = vo.getChildrenVO();
    Set<String> settleorg = new HashSet<String>();
    for (SaleOrderBVO bvo : bvos) {
      // ��������������֯�Ƿ�һ��
      if (bvo.getStatus() != VOStatus.DELETED) {
        settleorg.add(bvo.getCsettleorgvid());
      }

    }
    if (settleorg.size() > 1) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0497")/*���۶�����ϸ�Ľ��������֯����һ��.*/);
    }
  }
}
