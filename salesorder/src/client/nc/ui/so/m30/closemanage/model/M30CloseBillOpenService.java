package nc.ui.so.m30.closemanage.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.so.m30.closemanage.ISaleOrderCloseManageMaintain;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.scmpub.model.SCMResumeExceptionInvoker;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.SOParameterVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.util.ListUtil;

public class M30CloseBillOpenService implements
    ISingleBillService<SOParameterVO> {

  @Override
  public SOParameterVO operateBill(SOParameterVO paraVO) throws Exception {
    SOParameterVO[] ret = null;
    SaleOrderVO view = (SaleOrderVO) paraVO.getVo();
    SCMResumeExceptionInvoker invoker = new SCMResumeExceptionInvoker();
    ISaleOrderCloseManageMaintain service = invoker.getService(ISaleOrderCloseManageMaintain.class);//IMyServiceΪ����õķ���
      ret = service.openSaleOrder(new SaleOrderVO[] {
        view
      }, false);
    // �˷��ص�ֵ��ȱ�ݣ�������ȫ��ȷ�Ľ��仯view������ǰ̨����Ҫˢ�½���
    if (ret != null) {
      ret[0].setVo(this.filterReturnbvo(view, (SaleOrderVO) ret[0].getVo()));
    }
    return null == ret ? null : ret[0];
  }
  
  private SaleOrderVO filterReturnbvo(SaleOrderVO srcvo, SaleOrderVO returnvo) {
    SaleOrderBVO[] srcbvos = srcvo.getChildrenVO();
    SaleOrderBVO[] retbvos = returnvo.getChildrenVO();
    if (srcbvos.length == retbvos.length) {
      return returnvo;
    }
    Map<String, SaleOrderBVO> retbvomap =
        AggVOUtil.createItemMap(new SaleOrderVO[] {
          returnvo
        });
    List<SaleOrderBVO> bvolist = new ArrayList<>();
    for (SaleOrderBVO bvo : srcbvos) {
      bvolist.add(retbvomap.get(bvo.getCsaleorderbid()));
    }
    returnvo.setChildrenVO(ListUtil.toArray(bvolist));
    return returnvo;
  }

}
