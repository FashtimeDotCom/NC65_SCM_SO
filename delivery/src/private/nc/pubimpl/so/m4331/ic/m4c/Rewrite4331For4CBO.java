package nc.pubimpl.so.m4331.ic.m4c;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m4331.plugin.ServicePlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m4331.ic.rule.RenovateOutInfoRule;
import nc.pubimpl.so.m4331.ic.rule.ToleranceCheck;
import nc.pubimpl.so.m4331.pub.RewriteVOUtil;
import nc.pubimpl.so.m4331.pub.RewriteValueUtil;
import nc.pubitf.so.m4331.ic.m4c.RewritePara4331For4C;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * ���۳��ⵥ��д�������ۼƳ�������
 * 
 * @author ף����
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public class Rewrite4331For4CBO {

  // Ҫ���µķ������ӱ��ֶ�
  private final String[] keys = new String[] {
    DeliveryBVO.NTOTALOUTNUM, DeliveryBVO.NTOTALNOTOUTNUM
  };

  // ����Ҫ��д���м��ʼ�����Ϣ
  private RewriteVOUtil util;

  // �����д��ֵ��Ϣ
  private RewriteValueUtil valueutil;

  public void rewrite4331OutNumFor4C(RewritePara4331For4C[] paras)
      throws BusinessException {
    this.rewrite(paras);
  }

  private void addRule(AroundProcesser<DeliveryViewVO> processer) {

    // ���۳����д������ǰ����ռ�ü��
    processer.addBeforeRule(new RenovateARByBidsBeginRule(
        M4331EngrossAction.M4331OutReWrite));

    // ���۳����д������������ռ�ü��
    processer.addAfterRule(new RenovateARByBidsEndRule(
        M4331EngrossAction.M4331OutReWrite));
  }

  private UFDouble GetNoNullDouble(UFDouble value) {
    if (value == null) {
      return UFDouble.ZERO_DBL;
    }

    return value;

  }

  /* ���� */
  private String[] getRewriteIDS(Map<String, RewritePara4331For4C> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    return bids;
  }

  /* ��û�дview��Ϣ */
  private DeliveryViewVO[] getRewriteInfos(
      Map<String, RewritePara4331For4C> index,
      Map<String, RewritePara4331For4C> checkindex) {
    String[] ids = this.getRewriteIDS(index);
    String[] checkids = this.getRewriteIDS(checkindex);
    this.util = new RewriteVOUtil(ids, checkids);
    return this.util.getAllRewriteViewVO();
  }

  private Map<String, RewritePara4331For4C> prepareCheckParams(
      RewritePara4331For4C[] paras) {
    Map<String, RewritePara4331For4C> index =
        new HashMap<String, RewritePara4331For4C>();
    for (RewritePara4331For4C para : paras) {
      if (para.getCdeliverybbid() != null) {
        index.put(para.getCdeliverybbid(), para);
      }
    }
    return index;
  }

  private Map<String, RewritePara4331For4C> prepareParams(
      RewritePara4331For4C[] paras) {
    Map<String, RewritePara4331For4C> index =
        new HashMap<String, RewritePara4331For4C>();
    for (RewritePara4331For4C para : paras) {
      if (!index.containsKey(para.getCdeliverybid())) {
        index.put(para.getCdeliverybid(), para);
      }
      else {
        String key = para.getCdeliverybid();
        UFDouble outnum = this.GetNoNullDouble(index.get(key).getOutnum());
        UFDouble nooutnum = this.GetNoNullDouble(index.get(key).getNoOutnum());

        outnum = outnum.add(this.GetNoNullDouble(para.getOutnum()));
        nooutnum = nooutnum.add(this.GetNoNullDouble(para.getNoOutnum()));
        RewritePara4331For4C newpara =
            new RewritePara4331For4C(para.getCdeliverybid(),
                para.getCdeliverybbid(), outnum, nooutnum);
        index.remove(key);
        index.put(key, newpara);
      }
    }
    return index;
  }

  /* ��д������
   * 
   * @param paras
   * 
   * @throws BusinessException */
  private void rewrite(RewritePara4331For4C[] paras) throws BusinessException {
    TimeLog.logStart();
    // ��д������Ϣ
    Map<String, RewritePara4331For4C> index = this.prepareParams(paras);
    // ��д�ʼ�����Ϣ
    Map<String, RewritePara4331For4C> checkIndex =
        this.prepareCheckParams(paras);
    // ������ʼ���Ϣ ��Ҫ���������Դ��Ϣ����Ϊ�ʼ�����Ϣ
    if (checkIndex.size() > 0) {
      this.valueutil = new RewriteValueUtil(checkIndex);
    }

    // �˴�����session�������Ա�����򵽴�����
    BSContext.getInstance().setSession(RewritePara4331For4C.class.getName(),
        index);
    TimeLog.info("���������"); /* -=notranslate=- */
    TimeLog.logStart();

    // ���Ҫ��д������Ϣ
    DeliveryViewVO[] views = this.getRewriteInfos(index, checkIndex);

    TimeLog.info("��û�д��ͼvo"); /* -=notranslate=- */
    AroundProcesser<DeliveryViewVO> processer =
        new AroundProcesser<DeliveryViewVO>(
            ServicePlugInPoint.rewrite4331outNumFor4C);

    // ����Ƿ���������������
    ToleranceCheck rule = new ToleranceCheck();
    rule.examOverToleranceSaveBusi(this.util, this.valueutil);

    this.addRule(processer);
    TimeLog.logStart();

    TimeLog.info("д���ݿ�ǰִ��ҵ�����"); /* -=notranslate=- */
    TimeLog.logStart();

    // ���·������ʼ���Ϣ�ͷ�������Ϣ
    RenovateOutInfoRule renovate =
        new RenovateOutInfoRule(this.util, this.valueutil);
    renovate.renovateState();

    processer.before(views);
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    views = bo.update(views, DeliveryBVO.class, this.keys);

    TimeLog.info("�������ݿ�"); /* -=notranslate=- */
    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("д���ݿ��ִ��ҵ�����"); /* -=notranslate=- */

    // ���·�����״̬
    renovate.updateToDB();
    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(RewritePara4331For4C.class.getName());
  }
}
