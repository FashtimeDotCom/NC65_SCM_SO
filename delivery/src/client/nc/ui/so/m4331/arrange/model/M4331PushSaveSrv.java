package nc.ui.so.m4331.arrange.model;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.itf.so.m4331.IDeliveryMaintain;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.exp.IResumeExceptionEx;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.SOParameterVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ϊ���������ṩ����ʽ�������
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-5-31 ����02:35:23
 */
public class M4331PushSaveSrv implements ISingleBillService<Object> {

  @Override
  public Object operateBill(Object obj) throws Exception {

    SOParameterVO paravo = this.getParavo(obj);
    if (null == paravo || null == paravo.getVos()
        || paravo.getVos().length == 0) {
      return null;
    }

    DeliveryViewVO[] retViews = null;

    boolean isContinue = true;
    Map<String, Object> result  = new HashMap<String, Object>();
    while (isContinue) {
      try {
        if (paravo.getUserObject() == null) {
          paravo.setUserObject(new HashMap<>());
        }
        if(result.size() > 0){
          ((Map<String, Object>) paravo.getUserObject()).put(
              IResumeExceptionEx.RESUME_EXCEPTION_RESULT, result);
        }
        DeliveryVO[] rets = this.getService().pushWriteDelivery(paravo);
        BillToViewConvertor<DeliveryVO, DeliveryViewVO> convertor =
            new BillToViewConvertor<DeliveryVO, DeliveryViewVO>(
                DeliveryViewVO.class);
        retViews = convertor.convert(rets);
        isContinue = false;
      }
      catch (Exception exc) {
        Throwable ex = ExceptionUtils.unmarsh(exc);
        if (ex instanceof IResumeException) {
          // add by zhangby5 ʹ�ý����쳣���
          if (ResumeExceptionUIProcessUtils.isResume((IResumeException) exc, result)) {
            isContinue = true;
          }
          else {
            isContinue = false;
          }
        }
        else {
          ExceptionUtils.wrappException(exc);
        }
      }
    }
    return retViews;
  }

  private IDeliveryMaintain getService() {
    return NCLocator.getInstance().lookup(IDeliveryMaintain.class);
  }

  private SOParameterVO getParavo(Object obj) {
    SOParameterVO paravo = null;
    if (obj instanceof SOParameterVO) {
      paravo = (SOParameterVO) obj;

    }
    if (obj instanceof DeliveryViewVO[]) {
      DeliveryViewVO[] views = (DeliveryViewVO[]) obj;
      if (VOChecker.isEmpty(views)) {
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
                "04006002-0000")/* @res "������ѡ��һ�м�¼" */;
        ExceptionUtils.wrappBusinessException(msg);
        return null;
      }
      paravo = new SOParameterVO();
      DeliveryVO[] vos = new DeliveryVO[views.length];
      for (int i = 0; i < vos.length; i++) {
        vos[i] = new DeliveryVO();
        // ��ͼVO��Ԫ����Ϊ�ӱ����������ֶλ����ӱ���ֵ����Ҫ���ӱ��ֵ��������
        DeliveryHVO head = views[i].getHead();
        DeliveryBVO body = views[i].getItem();
        head.setPk_org(body.getPk_org());
        head.setDbilldate(body.getDbilldate());
        vos[i].setParent(head);
        vos[i].setChildrenVO(new DeliveryBVO[] {
          body
        });
      }
      paravo.setVos(vos);
      paravo.setBusinessCheckMap(null);
    }
    return paravo;
  }
}
