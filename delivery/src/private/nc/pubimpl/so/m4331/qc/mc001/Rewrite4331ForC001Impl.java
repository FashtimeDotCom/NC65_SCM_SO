package nc.pubimpl.so.m4331.qc.mc001;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.pubimpl.so.m4331.qc.mc001.rule.CheckEnableReportRule;
import nc.pubimpl.so.m4331.qc.mc001.rule.DeleteQualityDataRule;
import nc.pubimpl.so.m4331.qc.mc001.rule.FillReportCheckDataRule;
import nc.pubitf.so.m4331.qc.mc001.IRewrite4331ForC001;
import nc.pubitf.so.m4331.qc.mc001.RewritePara4331ForC001;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * ���쵥��д�������ۼƱ����������Ƿ񱨼��ʶ
 *
 * @author ף����
 * @since 6.0
 * @time 2010-01-28 ����13:49:07
 */
public class Rewrite4331ForC001Impl implements IRewrite4331ForC001 {

  // ����ʱ����Ҫ����ķ������ӱ�������Ϣ
  // �Ƿ񱨼� �Ƿ��ʼ� �Ƿ�����ʼ������
  // �ۼƱ������� �ۼƺϸ����� �ۼƲ��ϸ�����
  private static String[] keys = new String[] {
    DeliveryBVO.BCHECKFLAG, DeliveryBVO.BQUALITYFLAG,
    DeliveryBVO.BUSECHECKFLAG, DeliveryBVO.NTOTALREPORTNUM,
    DeliveryBVO.NTOTALELIGNUM, DeliveryBVO.NTOTALUNELIGNUM
  };

  @Override
  public void rewriteForC001(RewritePara4331ForC001[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  /*
   * �����д������ǰ�������
   * @param index
   * @param views
   */
  private void addRule(Map<String, RewritePara4331ForC001> index,
      DeliveryViewVO[] views) {
    CheckEnableReportRule check = new CheckEnableReportRule();
    check.checkEnable(views);
    // �������������±����ʱ��ɾ���Ѿ������������Ϣ
    DeleteQualityDataRule delete = new DeleteQualityDataRule();
    delete.deleteQualityData(views);

    // ���쵥��д�������������
    FillReportCheckDataRule filldata = new FillReportCheckDataRule();
    filldata.fillReportData(index, views);
  }

  /*
   * ���������ӱ��������������ڻ�д����
   */
  private String[] lockBills(Map<String, RewritePara4331ForC001> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0085")/*@res "���쵥��д�������ۼƱ���������������������ʧ��"*/;
    locker.lock(bids, message);
    return bids;
  }

  /*
   * ��д��������������Ϣ���浽map
   */
  private Map<String, RewritePara4331ForC001> prepareParams(
      RewritePara4331ForC001[] paras) {
    Map<String, RewritePara4331ForC001> index =
        new HashMap<String, RewritePara4331ForC001>();
    for (RewritePara4331ForC001 para : paras) {
      index.put(para.getCdeliverybid(), para);
    }
    return index;
  }

  /*
   * ���ݷ������ӱ�id��ѯ����������Ϣ
   */
  private DeliveryViewVO[] query(Map<String, RewritePara4331ForC001> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<DeliveryViewVO> bo =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    bo.setSharedHead(true);
    DeliveryViewVO[] views = bo.query(ids);
    if (views.length != index.size()) {
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0061")/*@res "���ֲ����������²�ѯ������"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  /*
   * �����д������
   */
  private void rewrite(RewritePara4331ForC001[] paras) {
    Map<String, RewritePara4331ForC001> index = this.prepareParams(paras);
    TimeLog.info("���������"); /*-=notranslate=-*/
    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(index);
    TimeLog.info("��ѯ����������"); /*-=notranslate=-*/
    this.addRule(index, views);
    TimeLog.logStart();
    TimeLog.info("д���ݿ�ǰִ��ҵ�����"); /*-=notranslate=-*/
    TimeLog.logStart();
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    bo.update(views, DeliveryBVO.class, Rewrite4331ForC001Impl.keys);
    TimeLog.info("�������ݿ�"); /*-=notranslate=-*/
    TimeLog.logStart();
    TimeLog.info("д���ݿ��ִ��ҵ�����"); /*-=notranslate=-*/
  }

}