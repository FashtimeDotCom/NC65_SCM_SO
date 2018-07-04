package nc.bs.so.m38.state;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.itf.so.m38trantype.IM38TranTypeService;
import nc.pubitf.so.m38.so.m30.Rewrite30Para;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * Ԥ����״̬ԾǨ�жϹ���
 * 
 * @author ��־ΰ
 * @since 6.0
 * @time 2010-04-08 ����09:27:07
 */
public class StateCalculateUtil {

  Map<String, UFBoolean> maparronce = new HashMap<String, UFBoolean>();

  private Map<String, Rewrite30Para> mParas;

  /**
   * ��������������������״̬�ж��Ƿ���������رա�
   * <p>
   * <ul>
   * <li>�����йرգ��������ر�(��ͷת�ر�״̬) ��return true
   * <li>��һ�д򿪣���������(��ͷת����״̬) ��return false
   * </ul>
   * 
   * @param vo ������ĵ���VO
   * @return �Ƿ��Զ��ر�
   *         <p>
   * @author ��־ΰ
   * @time 2010-04-09 ����02:43:18
   */
  public boolean isAutoTransitBillClose(PreOrderVO vo) {
    PreOrderBVO[] items = vo.getChildrenVO();
    for (PreOrderBVO item : items) {
      UFBoolean blineclose = item.getBlineclose();
      if (!UFBoolean.TRUE.equals(blineclose)) {
        return false;
      }
    }
    return true;
  }

  /**
   * ������״̬�ж��Ƿ����������
   * 
   * @since 6.0
   * @version 2010-12-14 ����09:02:17
   * @author ��־ΰ
   */
  public boolean isAutoTransitBillOpen(PreOrderVO vo) {
    return !this.isAutoTransitBillClose(vo);
  }

  /**
   * �����رյ�����ֱ�ӹر�
   * 
   * @param views
   * @return
   */
  public boolean isAutoTransitRowClose(PreOrderViewVO view) {
    return BillStatus.CLOSED.equalsValue(view.getHead().getFstatusflag());
  }

  /**
   * �����򿪵�����ֱ�Ӵ�
   * 
   * @param views
   * @return
   */
  public boolean isAutoTransitRowOpen(PreOrderViewVO view) {
    return BillStatus.AUDIT.equalsValue(view.getHead().getFstatusflag());
  }

  /**
   * ��д����ʱ����ǰ�ۼư������� >= ������ �п����Զ��ر� ��
   * <p>����:�����ǰ��д�����������Ѿ��ֹ��ر��ҵ�ǰ�ۼ�����>=������ʱ������Ϊ�ֹ��ر�״̬�Զ���Ϊ�Զ��ر�״̬(ҵ��Ĭ��Ϊת�����˲���д�������)��
   * �����´λ�д����ʱ��Ҫ�Զ��򿪵ġ�
   * 
   * @param views
   * @return �ܷ��Զ��ر�
   */
  public boolean isRowClose(PreOrderViewVO view) {
    // ֻ����һ��ʱ��ֱ�ӷ���
    if (this.isArrangeOnce(view).booleanValue()) {
      return true;
    }
    PreOrderBVO item = view.getItem();
    Rewrite30Para para = this.getRewrite30Paras().get(item.getCpreorderbid());
    // ��д������Զ���ر�
    if (MathTool.compareTo(para.getNnum(), UFDouble.ZERO_DBL) < 0) {
      return false;
    }

    // ��д����ʱ��(��ǰ�ۼư������� >= ������) �п����Զ��ر� ��
    return MathTool.compareTo(item.getNarrnum(), item.getNnum()) >= 0;
  }

  /**
   * ��д����ʱ:�Զ��ر� && (��ǰ�ۼư������� < ������) �п����Զ���
   * 
   * @param views PreOrderViewVO
   * @return boolean �ܷ��Զ���
   */
  public boolean isRowOpen(PreOrderViewVO view) {
    PreOrderBVO item = view.getItem();
    Rewrite30Para para = this.getRewrite30Paras().get(item.getCpreorderbid());
    // �ǹرյ��ݲ��ܴ� || ��д����(����0)��Զ����
    if (!item.getBlineclose().booleanValue()
        || MathTool.compareTo(para.getNnum(), UFDouble.ZERO_DBL) >= 0) {
      return false;
    }

    // Դ�ۼư������� = ��ǰ�ۼư������� - ��ǰ��������
    UFDouble origArrnum = MathTool.sub(item.getNarrnum(), para.getNnum());
    // �Ƿ��Զ��ر� (Դ�ۼư������� >= ������)
    boolean bautoClose =
        MathTool.compareTo(origArrnum, item.getNnum()) >= 0 ? true : false;

    // ��д����ʱ:�Զ��ر� && (��ǰ�ۼư������� < ������) �п����Զ���
    return bautoClose
        && MathTool.compareTo(item.getNarrnum(), item.getNnum()) < 0;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Rewrite30Para> getRewrite30Paras() {
    if (this.mParas == null) {
      this.mParas =
          (Map<String, Rewrite30Para>) BSContext.getInstance().getSession(
              Rewrite30Para.class.getName());
    }
    return this.mParas;
  }

  private UFBoolean isArrangeOnce(PreOrderViewVO preview) {

    String ctrantypeid = preview.getHead().getCtrantypeid();
    if (this.maparronce.containsKey(ctrantypeid)) {
      return this.maparronce.get(ctrantypeid);
    }

    IM38TranTypeService transrv =
        NCLocator.getInstance().lookup(IM38TranTypeService.class);
    M38TranTypeVO tranvo = null;
    try {
      tranvo = transrv.queryTranTypeVO(ctrantypeid);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    UFBoolean arronce = UFBoolean.FALSE;
    if (null != tranvo && null != tranvo.getBarrange()) {
      arronce = tranvo.getBarrange();
    }
    this.maparronce.put(ctrantypeid, arronce);
    return arronce;

  }

}
