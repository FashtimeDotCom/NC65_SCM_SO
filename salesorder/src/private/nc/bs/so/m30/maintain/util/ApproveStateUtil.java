package nc.bs.so.m30.maintain.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m33.pub.biz.toia.ProcessToIA;
import nc.vo.so.m33.pub.biz.toia.ProcessToIAPara;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.pub.util.biz.SOBusiUtil;

import nc.bs.so.m30.state.SaleOrderStateMachine;
import nc.bs.so.m30.state.row.ArSettleCloseState;
import nc.bs.so.m30.state.row.ArSettleOpenState;
import nc.bs.so.m30.state.row.CostSettleCloseState;
import nc.bs.so.m30.state.row.CostSettleOpenState;
import nc.bs.so.m30.state.row.InvoiceCloseState;
import nc.bs.so.m30.state.row.InvoiceOpenState;
import nc.bs.so.m30.state.row.OutCloseState;
import nc.bs.so.m30.state.row.OutOpenState;
import nc.bs.so.m30.state.row.SendCloseState;
import nc.bs.so.m30.state.row.SendOpenState;

import nc.impl.pubapp.bill.state.IState;

/**
 * �����������۶�����״̬
 * 
 * @since 6.0
 * @version 2012-4-23 ����11:32:26
 * @author ô��
 */
public class ApproveStateUtil {

  private Map<String, Set<String>> billTypeMap =
      new HashMap<String, Set<String>>();

  private SOBusiUtil busiUtil = new SOBusiUtil();

  /**
   * ����״̬
   * ��������4Cû��4331,�򷢻��ر�/��
   * ����/ȡ������ʱ����״̬���¹���
   * 
   * @param views
   */
  public void processSendState(SaleOrderViewVO[] views) {
    // 2.�����Ҫ�������
    SaleOrderViewVO[] processViews = this.getProcessSendViews(views);

    // 3.���·���״̬
    this.updateSendState(processViews);
  }

  /**
   * ����״̬(����/ȡ������ʱ����״̬���¹���)
   * <ol>
   * <li>�����ۿ��г���ر�/��
   * <li>�������ۿ����ж���������û��4C,û�������ر�/��
   * </ol>
   * 
   * @param views
   */
  public void processOutState(SaleOrderViewVO[] views) {
    // 2.�����Ҫ����������
    SaleOrderViewVO[] processViews = this.getProcessOutViews(views);

    // 3.���³���״̬
    this.updateOutState(processViews);
  }

  /**
   * ��Ʊ״̬
   * <ol>
   * <li>��Ʒ &&SO20��Ʒ���Ƿ�Ʊ(��),��Ʊ�ر�/��
   * <li>
   * </ol>
   * 
   * @param views
   */
  public void processInvoiceState(SaleOrderViewVO[] views) {
    // 2.�����Ҫ����Ŀ�Ʊ��
    SaleOrderViewVO[] processViews = this.getProcessInvoiceViews(views);

    // 3.���¿�Ʊ״̬
    this.updateInvoiceState(processViews);
  }

  /**
   * �ɱ�����״̬
   * ������������������Զ��ɱ�����ر�
   * 
   * @param views
   */
  public void processCostSettleCloseState(SaleOrderViewVO[] views) {
    // �ж��Ƿ���Դ��ɱ�
    ProcessToIAPara[] paras = this.getProcessToIAPara(views);
    Map<String, UFBoolean> ret = new ProcessToIA().getPubToIAResult(paras);
    IState<SaleOrderViewVO> state = new CostSettleCloseState();
    List<SaleOrderViewVO> list = new ArrayList<SaleOrderViewVO>();
    int index = 0;
    for (SaleOrderViewVO view : views) {
      UFBoolean flag = ret.get(String.valueOf(index));
      UFBoolean costflag = view.getBody().getBbcostsettleflag();
      if (!ValueUtils.getBoolean(flag) && !ValueUtils.getBoolean(costflag) && !state.isThisState(view)) {
        list.add(view);
      }
      index++;
    }
    int size = list.size();
    if (size > 0) {
      SaleOrderStateMachine bo = new SaleOrderStateMachine();
      bo.setState(state, list.toArray(new SaleOrderViewVO[size]));
    }
  }

  /**
   * �����������������Զ��ɱ������
   * 
   * @param views
   */
  public void processCostSettleOpenState(SaleOrderViewVO[] views) {
    // �ж��Ƿ���Դ��ɱ�
    ProcessToIAPara[] paras = this.getProcessToIAPara(views);
    Map<String, UFBoolean> ret = new ProcessToIA().getPubToIAResult(paras);

    List<SaleOrderViewVO> list = new ArrayList<SaleOrderViewVO>();
    int index = 0;
    for (SaleOrderViewVO view : views) {
      UFBoolean flag = ret.get(String.valueOf(index));
      UFBoolean costflag = view.getBody().getBbcostsettleflag();
      if (!ValueUtils.getBoolean(flag) && ValueUtils.getBoolean(costflag)) {
        list.add(view);
      }
      index++;
    }

    int size = list.size();
    if (size > 0) {
      SaleOrderStateMachine bo = new SaleOrderStateMachine();
      IState<SaleOrderViewVO> state = new CostSettleOpenState();
      bo.setState(state, list.toArray(new SaleOrderViewVO[size]));
    }
  }

  /**
   * ����������Ʒ���Զ�Ӧ�ս���ر�
   * 
   * @param views
   */
  public void processARSettleCloseState(SaleOrderViewVO[] views) {
    List<SaleOrderViewVO> list = new ArrayList<SaleOrderViewVO>();
    IState<SaleOrderViewVO> state = new ArSettleCloseState();
    for (SaleOrderViewVO view : views) {
      SaleOrderBVO bvo = view.getBody();
      if (ValueUtils.getBoolean(bvo.getBlargessflag())&& !state.isThisState(view)) {
        list.add(view);
      }
    }
    int size = list.size();
    if (size > 0) {
      SaleOrderStateMachine bo = new SaleOrderStateMachine();
      bo.setState(state, list.toArray(new SaleOrderViewVO[size]));
    }
  }

  /**
   * ����������Ʒ���Զ�Ӧ�ս����
   * 
   * @param views
   */
  public void processARSettleOpenState(SaleOrderViewVO[] views) {
    List<SaleOrderViewVO> list = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      SaleOrderBVO bvo = view.getBody();
      boolean arendflag = ValueUtils.getBoolean(bvo.getBbarsettleflag());
      if (ValueUtils.getBoolean(bvo.getBlargessflag()) && arendflag) {
        list.add(view);
      }
    }
    int size = list.size();
    if (size > 0) {
      SaleOrderStateMachine bo = new SaleOrderStateMachine();
      IState<SaleOrderViewVO> state = new ArSettleOpenState();
      bo.setState(state, list.toArray(new SaleOrderViewVO[size]));
    }
  }

  private ProcessToIAPara[] getProcessToIAPara(SaleOrderViewVO[] svvos) {
    ProcessToIAPara[] paras = new ProcessToIAPara[svvos.length];
    int index = 0;
    for (SaleOrderViewVO view : svvos) {
      SaleOrderBVO bvo = view.getBody();
      paras[index] = new ProcessToIAPara();
      paras[index].setId(String.valueOf(index));
      // ���������֯OID
      paras[index].setFinorgoid(bvo.getCsettleorgid());
      // ����VID
      paras[index].setMaterialvid(bvo.getCmaterialvid());
      // �ֿ�
      paras[index].setStordocid(bvo.getCsendstordocid());
      paras[index].setBdiscountflag(bvo.getBdiscountflag().booleanValue());
      paras[index].setBlaborflag(bvo.getBlaborflag().booleanValue());
      index++;
    }
    return paras;
  }

  private void updateInvoiceState(SaleOrderViewVO[] views) {
    if (views == null || views.length == 0) {
      return;
    }
    Integer statusFlag = views[0].getHead().getFstatusflag();
    List<SaleOrderViewVO> processCloseList = new ArrayList<SaleOrderViewVO>();
    List<SaleOrderViewVO> processOpenList = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      SaleOrderBVO bvo = view.getBody();
      boolean invoicendflag = ValueUtils.getBoolean(bvo.getBbinvoicendflag());
      if (BillStatus.AUDIT.equalsValue(statusFlag) && !invoicendflag) {
        processCloseList.add(view);
      }
      else if ((BillStatus.FREE.equalsValue(statusFlag) || BillStatus.AUDITING
          .equalsValue(statusFlag)) && invoicendflag) {
        processOpenList.add(view);
      }
    }

    int size = processCloseList.size();
    if (size > 0) {
      SaleOrderViewVO[] cviews =
          processCloseList.toArray(new SaleOrderViewVO[size]);
      this.setState(cviews, new InvoiceCloseState());
    }

    size = processOpenList.size();
    if (size > 0) {
      SaleOrderViewVO[] cviews =
          processOpenList.toArray(new SaleOrderViewVO[size]);
      this.setState(cviews, new InvoiceOpenState());
    }

  }

  private SaleOrderViewVO[] getProcessInvoiceViews(SaleOrderViewVO[] views) {
    List<SaleOrderViewVO> processList = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      String cbiztypeid = view.getHead().getCbiztypeid();
      Set<String> typeSet = this.getBusiAllBillType(cbiztypeid);
      // ��Ʒ &&SO20��Ʒ���Ƿ�Ʊ(��)
      boolean largessflag =
          view.getBody().getBlargessflag() == null ? false : view.getBody()
              .getBlargessflag().booleanValue();
      String settleorgid = view.getBody().getCsettleorgid();
      boolean bSO20 =
          SOSysParaInitUtil.getSO20(settleorgid) == null ? false
              : SOSysParaInitUtil.getSO20(view.getBody().getCsettleorgid())
                  .booleanValue();
      if (!typeSet.contains(SOBillType.Invoice.getCode())) {
        processList.add(view);
      }
      else if (largessflag && !bSO20) {
        processList.add(view);
      }
    }

    return processList.toArray(new SaleOrderViewVO[processList.size()]);
  }

  private void updateOutState(SaleOrderViewVO[] views) {
    if (views == null || views.length == 0) {
      return;
    }

    List<SaleOrderViewVO> closeViews = new ArrayList<SaleOrderViewVO>();
    IState<SaleOrderViewVO> close = new OutCloseState();

    List<SaleOrderViewVO> openViews = new ArrayList<SaleOrderViewVO>();
    IState<SaleOrderViewVO> open = new OutOpenState();
    for (SaleOrderViewVO view : views) {
      Integer statusFlag = view.getHead().getFstatusflag();
      if (BillStatus.AUDIT.equalsValue(statusFlag) && !close.isThisState(view)) {
        closeViews.add(view);
      }
      else if ((BillStatus.FREE.equalsValue(statusFlag)
          || BillStatus.AUDITING.equalsValue(statusFlag))
          && !open.isThisState(view)) {
        openViews.add(view);
      }
    }
    // newViews = this.filerIsNotThisStateViews(views, state);

    if (closeViews.size() > 0) {
      this.setState(closeViews.toArray(new SaleOrderViewVO[closeViews.size()]),
          close);
    }
    if (openViews.size() > 0) {
      this.setState(openViews.toArray(new SaleOrderViewVO[openViews.size()]),
          open);
    }

  }

  private SaleOrderViewVO[] getProcessOutViews(SaleOrderViewVO[] views) {
    List<SaleOrderViewVO> processList = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      String cbiztypeid = view.getHead().getCbiztypeid();
      Set<String> typeSet = this.getBusiAllBillType(cbiztypeid);
      // �����ۿ��г���ر�/��
      boolean laborflag =
          view.getBody().getBlaborflag() == null ? false : view.getBody()
              .getBlaborflag().booleanValue();
      boolean discountflag =
          view.getBody().getBdiscountflag() == null ? false : view.getBody()
              .getBdiscountflag().booleanValue();
      if (laborflag || discountflag) {
        processList.add(view);
      }
      // �������ۿ����ж���������û��4C,û�������ر�/��
      else if (!typeSet.contains(ICBillType.SaleOut.getCode())) {
        processList.add(view);
      }
    }
    return processList.toArray(new SaleOrderViewVO[processList.size()]);
  }

  /**
   * 1. �������з�ĩ����ȡ��������������״̬Ϊ��״̬�����Բ���Ҫ�����
   * 2.
   */
  private SaleOrderViewVO[] filerIsNotThisStateViews(SaleOrderViewVO[] views,
      IState<SaleOrderViewVO> state) {
    List<SaleOrderViewVO> retList = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      if (!state.isThisState(view)) {
        retList.add(view);
      }
    }
    return retList.toArray(new SaleOrderViewVO[retList.size()]);
  }

  private Set<String> getBusiAllBillType(String cbiztypeid) {
    if (!this.billTypeMap.containsKey(cbiztypeid)) {
      Set<String> typeSet = this.busiUtil.queryAllBillType(cbiztypeid);
      this.billTypeMap.put(cbiztypeid, typeSet);
    }
    return this.billTypeMap.get(cbiztypeid);
  }

  private SaleOrderViewVO[] getProcessSendViews(SaleOrderViewVO[] views) {
    List<SaleOrderViewVO> processList = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      String cbiztypeid = view.getHead().getCbiztypeid();
      Set<String> typeSet = this.getBusiAllBillType(cbiztypeid);

      // �����ۿ��г���ر�/��
      boolean laborflag =
          view.getBody().getBlaborflag() == null ? false : view.getBody()
              .getBlaborflag().booleanValue();
      boolean discountflag =
          view.getBody().getBdiscountflag() == null ? false : view.getBody()
              .getBdiscountflag().booleanValue();
      if (laborflag || discountflag) {
        processList.add(view);
        continue;
      }

      // ����״̬˵������ʱ����
      Integer statusFlag = views[0].getHead().getFstatusflag();
      // �������в�����������,���ǰ������ⵥʱ,�ɴ˹���������رշ���
      if (!typeSet.contains(SOBillType.Delivery.getCode())
          && typeSet.contains(ICBillType.SaleOut.getCode())) {
        processList.add(view);
      }
      // �����ʱ�򷢻��Զ���
      else if (BillStatus.FREE.equalsValue(statusFlag)
          && !typeSet.contains(SOBillType.Delivery.getCode())) {
        processList.add(view);
      }
    }
    return processList.toArray(new SaleOrderViewVO[processList.size()]);
  }

  private void updateSendState(SaleOrderViewVO[] views) {
    if (views == null || views.length == 0) {
      return;
    }

    List<SaleOrderViewVO> closeViews = new ArrayList<SaleOrderViewVO>();
    IState<SaleOrderViewVO> close = new SendCloseState();

    List<SaleOrderViewVO> openViews = new ArrayList<SaleOrderViewVO>();
    IState<SaleOrderViewVO> open = new SendOpenState();
    for (SaleOrderViewVO view : views) {
      Integer statusFlag = view.getHead().getFstatusflag();
      if (BillStatus.AUDIT.equalsValue(statusFlag) && !close.isThisState(view)) {
        closeViews.add(view);
      }
      else if ((BillStatus.FREE.equalsValue(statusFlag)
          || BillStatus.AUDITING.equalsValue(statusFlag))
          && !open.isThisState(view)) {
        openViews.add(view);
      }
    }
    // newViews = this.filerIsNotThisStateViews(views, state);

    if (closeViews.size() > 0) {
      this.setState(closeViews.toArray(new SaleOrderViewVO[closeViews.size()]),
          close);
    }
    if (openViews.size() > 0) {
      this.setState(openViews.toArray(new SaleOrderViewVO[openViews.size()]),
          open);
    }
  }

  private void setState(SaleOrderViewVO[] views, IState<SaleOrderViewVO> state) {
    if (views == null || views.length == 0) {
      return;
    }
    SaleOrderStateMachine bo = new SaleOrderStateMachine();
    bo.setState(state, views);
  }
}
