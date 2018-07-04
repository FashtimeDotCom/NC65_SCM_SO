package nc.bs.so.m38.plugin.task;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.bs.so.m38.state.PreOrderStateMachine;
import nc.bs.so.m38.state.row.RowCloseState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.md.model.impl.MDEnum;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * Ԥ�����Զ�ʧЧ�͹رպ�̨����ִ�в��
 * 
 * @since 6.0
 * @version 2011-11-03 ����03:27:55
 * @author ������
 */
public class PreorderBGWorkPlugin implements IBackgroundWorkPlugin {

  /**
   * ִ�йرա��Զ�ʧЧ���ݲ���
   * 
   * @param hvos ��Ҫִ�йرա��Զ�ʧЧ����ʵ��
   */
  private void closeInvaliAction(PreOrderHVO[] hvos) {

    List<PreOrderHVO> invalidationList = new ArrayList<PreOrderHVO>();
    List<PreOrderHVO> closeList = new ArrayList<PreOrderHVO>();
    for (PreOrderHVO hvo : hvos) {
      Integer billStatus = hvo.getFstatusflag();
      if (BillStatus.FREE.equalsValue(billStatus)
          || BillStatus.AUDITING.equalsValue(billStatus)) {
        invalidationList.add(hvo);
      }
      else {
        closeList.add(hvo);
      }
    }

    if (closeList.size() > 0) {
      this.closePreorder(closeList);
    }
    if (invalidationList.size() > 0) {
      this.invalidationPreorder(invalidationList);
    }
  }

  /**
   * �ر�Ԥ����
   * 
   * @param closeList �ر�̬��Ԥ��������ʵ�弯��
   */
  private void closePreorder(List<PreOrderHVO> closeList) {

    PreOrderHVO[] hvos = new PreOrderHVO[closeList.size()];
    closeList.toArray(hvos);
    String[] ids = new String[closeList.size()];
    for (int i = 0; i < hvos.length; i++) {
      ids[i] = hvos[i].getCpreorderid();
    }
    SqlBuilder cond = new SqlBuilder();
    cond.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String insql = iq.buildSQL(PreOrderBVO.CPREORDERID, ids);
    cond.append(insql);
    VOQuery<PreOrderBVO> voquery = new VOQuery<PreOrderBVO>(PreOrderBVO.class);
    PreOrderBVO[] bvos = voquery.query(cond.toString(), null);
    String[] bids = new String[bvos.length];
    for (int i = 0; i < bvos.length; i++) {
      bids[i] = bvos[i].getCpreorderbid();
    }
    ViewQuery<PreOrderViewVO> query =
        new ViewQuery<PreOrderViewVO>(PreOrderViewVO.class);
    PreOrderViewVO[] views = query.query(bids);

    PreOrderStateMachine stateMachine = new PreOrderStateMachine();
    IState<PreOrderViewVO> state = new RowCloseState();
    stateMachine.setState(state, views);
  }

  @Override
  public PreAlertObject executeTask(BgWorkingContext bgwc)
      throws BusinessException {

    PreAlertObject retObj = new PreAlertObject();
    try {
      // ��ȡ��Ҫ�Զ��رջ�ʧЧ�ģ����Ѿ������ĵ���ʵ�弯��
      PreOrderHVO[] hvos = this.getBGWorkInfo(bgwc);
      // ��̨����ִ��
      this.closeInvaliAction(hvos);
      // Ԥ�����Զ�ʧЧ�͹رպ�̨������Ҫ������Ϣ���ʷ���ֵ����returnType��ΪRETURNNOTHING
      retObj.setReturnType(PreAlertReturnType.RETURNNOTHING);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return retObj;
  }

  /**
   * ��ȡ��Ҫ�Զ�ʧЧ�͹رյĵ���ʵ������
   * 
   * @param bgwc ��̨���񻷾�����
   * @return ��Ҫִ�к�̨����ĵ���ʵ�����������Ѿ��������ҷ����Զ��رպ�ʧЧ�����ĵ���ʵ�弯
   */
  private PreOrderHVO[] getBGWorkInfo(BgWorkingContext bgwc) {

    String[] keys = new String[] {
      PreOrderHVO.CPREORDERID
    };
    SqlBuilder where = this.getWhereSql(bgwc);
    VOQuery<PreOrderHVO> qrysrvOrig =
        new VOQuery<PreOrderHVO>(PreOrderHVO.class, keys);
    PreOrderHVO[] toabdatehvos = qrysrvOrig.query(where.toString(), null);
    if (toabdatehvos.length == 0) {
      return toabdatehvos;
    }
    List<String> idsList = new ArrayList<String>();
    for (PreOrderHVO hvo : toabdatehvos) {
      idsList.add(hvo.getCpreorderid());
    }
    String[] ids = idsList.toArray(new String[idsList.size()]);
    // ����������رպ�ʧЧ���ݹ����е��ݱ��޸�
    LockOperator lock = new LockOperator();
    lock.lock(
        ids,
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0",
            "04006012-000007")/*��̨����ִ��ʧ�ܣ����Ժ����´�����̨����*/);
    // ƴ���Ѿ������ĵ���ID��ѯ����������û��û�м����ĵ���
    where.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String insql = iq.buildSQL(PreOrderHVO.CPREORDERID, ids);
    where.append(insql);
    // ���²�ѯ����������������е����޸ı����������֤��ѯ���Ľ��Ϊ���Ѿ������ҷ����Զ��ر�ʧЧ�����ĵ���
    VOQuery<PreOrderHVO> qrysrv = new VOQuery<PreOrderHVO>(PreOrderHVO.class);
    toabdatehvos = qrysrv.query(where.toString(), null);
    return toabdatehvos;
  }

  /**
   * ��ѯ��ǰ��Ҫ�Զ�ʧЧ�͹رյĵ���
   * 
   * @param bgwc ��̨���񻷾�����
   * @return where ��ѯ����
   */
  private SqlBuilder getWhereSql(BgWorkingContext bgwc) {

    UFDate serviceDate = AppContext.getInstance().getServerTime().getDate();
    SqlBuilder where = new SqlBuilder();
    where.append(" and ");
    where.append(PreOrderHVO.PK_GROUP, bgwc.getGroupId());
    String[] pkorgs = bgwc.getPk_orgs();
    if (pkorgs != null && pkorgs.length > 0) {
      where.append(" and ");
      where.append(PreOrderHVO.PK_ORG, pkorgs);
    }
    where.append(" and ");
    MDEnum[] status = new MDEnum[] {
      BillStatus.AUDIT, BillStatus.AUDITING, BillStatus.FREE
    };
    where.append(PreOrderHVO.FSTATUSFLAG, status);
    where.append(" and ");
    UFDate dateStart = serviceDate.getDateBefore(7).asBegin();
    where.append(PreOrderHVO.DABATEDATE, ">= ", dateStart.toString());
    where.append(" and ");
    UFDate dateEnd = serviceDate.asBegin();
    where.append(PreOrderHVO.DABATEDATE, "<= ", dateEnd.toString());
    return where;
  }

  /**
   * �Զ�ʧЧԤ����
   * 
   * @param invalidationList �����к�����̬��Ԥ��������ʵ�弯��
   */
  private void invalidationPreorder(List<PreOrderHVO> invalidationList) {

    PreOrderHVO[] hvos = new PreOrderHVO[invalidationList.size()];
    invalidationList.toArray(hvos);
    for (PreOrderHVO hvo : hvos) {
      hvo.setFstatusflag(BillStatus.INVALIDATE.getIntegerValue());
      hvo.setStatus(VOStatus.UPDATED);
    }
    String[] names = new String[] {
      PreOrderHVO.FSTATUSFLAG
    };
    VOUpdate<PreOrderHVO> update = new VOUpdate<PreOrderHVO>();
    update.update(hvos, names);
  }

}
