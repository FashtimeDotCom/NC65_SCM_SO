package nc.pubimpl.so.m4331.ic.m4y;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m4331.plugin.ServicePlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m4331.ic.rule.RenovateOutInfoRule;
import nc.pubimpl.so.m4331.ic.rule.ToleranceCheck;
import nc.pubimpl.so.m4331.pub.RewriteVOUtil;
import nc.pubitf.so.m4331.ic.m4y.IRewrite4331For4Y;
import nc.pubitf.so.m4331.ic.m4y.RewritePara4331For4Y;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * �������ⵥ��д�������ۼƳ�������
 * 
 * @author ף����
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public class Rewrite4331For4YImpl implements IRewrite4331For4Y {

  // Ҫ���µķ������ӱ��ֶ�
  private final String[] keys = new String[] {
      DeliveryBVO.NTOTALOUTNUM, DeliveryBVO.NTOTALNOTOUTNUM
  };

  private RewriteVOUtil util;

  @Override
  public void rewrite4331OutNumFor4Y(RewritePara4331For4Y[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void addRule() throws BusinessException {
    // ����Ƿ���������������
    ToleranceCheck rule = new ToleranceCheck();
    rule.examOverToleranceSaveBusi(this.util,null);

    // ���·������ʼ���Ϣ�ͷ�������Ϣ
    RenovateOutInfoRule renovate = new RenovateOutInfoRule(this.util,null);
    renovate.renovateState();
    //���·�����״̬
    renovate.updateToDB();

  }

  private String[] getRewriteIDS(Map<String, RewritePara4331For4Y> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    return bids;
  }

  /*
   * ��û�дview��Ϣ
   */
  private DeliveryViewVO[] getRewriteInfos(
      Map<String, RewritePara4331For4Y> index) {
    String[] ids = this.getRewriteIDS(index);
    this.util = new RewriteVOUtil(ids);
    return this.util.getAllRewriteViewVO();
  }

  private Map<String, RewritePara4331For4Y> prepareParams(
      RewritePara4331For4Y[] paras) {
    Map<String, RewritePara4331For4Y> index =
        new HashMap<String, RewritePara4331For4Y>();
    for (RewritePara4331For4Y para : paras) {
      index.put(para.getCdeliverybid(), para);
    }
    return index;
  }

  /*
   * ��д������
   * @param paras
   * @throws BusinessException
   */
  private void rewrite(RewritePara4331For4Y[] paras) throws BusinessException {
    TimeLog.logStart();
    Map<String, RewritePara4331For4Y> index = this.prepareParams(paras);
    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance().setSession(RewritePara4331For4Y.class.getName(),
        index);
    TimeLog.info("���������"); /*-=notranslate=-*/
    TimeLog.logStart();
    DeliveryViewVO[] views = this.getRewriteInfos(index);
    TimeLog.info("��û�д��ͼvo"); /*-=notranslate=-*/
    AroundProcesser<DeliveryViewVO> processer =
        new AroundProcesser<DeliveryViewVO>(
            ServicePlugInPoint.rewrite4331OutNumFor4Y);
    this.addRule();
    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("д���ݿ�ǰִ��ҵ�����"); /*-=notranslate=-*/
    TimeLog.logStart();
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    views = bo.update(views, DeliveryBVO.class, this.keys);
    TimeLog.info("�������ݿ�"); /*-=notranslate=-*/
    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("д���ݿ��ִ��ҵ�����"); /*-=notranslate=-*/

    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(RewritePara4331For4Y.class.getName());
  }
}
