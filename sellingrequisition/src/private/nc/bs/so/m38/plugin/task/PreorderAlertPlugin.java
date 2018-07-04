package nc.bs.so.m38.plugin.task;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.md.model.impl.MDEnum;

import nc.bs.pub.pa.IPreAlertPlugin;
import nc.bs.pub.pa.PreAlertContext;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.so.task.SOAlertMetaDataSource;

/**
 * Ԥ�����Զ�ʧЧ�ر�Ԥ�����
 * 
 * @since 6.0
 * @version 2011-11-03 ����08:51:31
 * @author ������
 */
public class PreorderAlertPlugin implements IPreAlertPlugin {

  /** ��ǰԤ������ */
  private static final String ALERTDAY = "alertDay";

  @Override
  public PreAlertObject executeTask(PreAlertContext context)
      throws BusinessException {

    PreAlertObject retObj = new PreAlertObject();
    try {
      PreOrderHVO[] toabdatehvos = this.getAlertInfo(context);
      if (toabdatehvos.length == 0) {
        retObj.setReturnType(PreAlertReturnType.RETURNNOTHING);
      }
      else {
        retObj.setReturnType(PreAlertReturnType.RETURNDATASOURCE);
        retObj.setReturnObj(new SOAlertMetaDataSource(toabdatehvos));
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return retObj;
  }

  /**
   * ��ѯ�����Ԥ������ʵ�弯��
   * 
   * @param context Ԥ��ִ��������
   * @return Ԥ������ʵ�弯��
   */
  private PreOrderHVO[] getAlertInfo(PreAlertContext context) {

    VOQuery<PreOrderHVO> qrysrv = new VOQuery<PreOrderHVO>(PreOrderHVO.class);
    String where = this.getWhereSql(context);
    PreOrderHVO[] toabdatehvos = qrysrv.query(where, null);
    return toabdatehvos;
  }

  /**
   * ��ѯ���������˼����Զ�ʧЧ�͹رյĵ��ݵ�Ԥ����Ϣ
   * 
   * @param context Ԥ��ִ�л���
   * @return where ��ѯ���������� <li>����״̬��������̬/������/����ͨ�� <li>ʧЧ���ڡ�����������ʧЧ���ڵ���
   */
  private String getWhereSql(PreAlertContext context) {

    SqlBuilder where = new SqlBuilder();
    where.append(" and ");
    where.append(PreOrderHVO.PK_GROUP, context.getGroupId());
    String[] pk_orgs = context.getPk_orgs();
    if (pk_orgs != null && pk_orgs.length > 0) {
      where.append(" and ");
      where.append(PreOrderHVO.PK_ORG, pk_orgs);
    }
    where.append(" and ");
    MDEnum[] status = new BillStatus[] {
      BillStatus.AUDIT, BillStatus.AUDITING, BillStatus.FREE
    };
    where.append(PreOrderHVO.FSTATUSFLAG, status);
    where.append(" and ");
    UFDate serviceDate = AppContext.getInstance().getServerTime().getDate();
    UFDate dateStart = serviceDate.getDateBefore(7).asBegin();
    where.append(PreOrderHVO.DABATEDATE, ">= ", dateStart.toString());
    where.append(" and ");
    int day =
        ValueUtils
            .getInt(context.getKeyMap().get(PreorderAlertPlugin.ALERTDAY));
    UFDate dateEnd = serviceDate.getDateAfter(day).asEnd();
    where.append(PreOrderHVO.DABATEDATE, "<= ", dateEnd.toString());
    return where.toString();
  }

}
