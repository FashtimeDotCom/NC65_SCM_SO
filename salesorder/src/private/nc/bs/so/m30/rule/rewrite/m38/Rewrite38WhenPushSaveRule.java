package nc.bs.so.m30.rule.rewrite.m38;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m38.so.m30.IPreOrderFor30;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * Ԥ���������Ƶ��������۶���ʱ������дԤ������Ҫ�ر��й���
 * 
 * @since 6.0
 * @version 2011-4-2 ����03:11:35
 * @author ��־ΰ
 */
public class Rewrite38WhenPushSaveRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    List<String> cprebidList = new ArrayList<String>();
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        boolean closeFlag =
            bvo.getBprerowcloseflag() == null ? false : bvo
                .getBprerowcloseflag().booleanValue();
        if (closeFlag) {
          cprebidList.add(bvo.getCsrcbid());
        }
      }
    }
    if (cprebidList.size() > 0) {
      String[] cpreorderbids =
          cprebidList.toArray(new String[cprebidList.size()]);
      IPreOrderFor30 service =
          NCLocator.getInstance().lookup(IPreOrderFor30.class);
      try {
        service.closeRowFor38Arrange(cpreorderbids);
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
    }
  }

}
