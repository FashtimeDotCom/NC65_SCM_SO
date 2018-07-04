package nc.bs.so.salequotation.task;

import nc.bs.pub.pa.IPreAlertPlugin;
import nc.bs.pub.pa.PreAlertContext;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.so.task.SOAlertMetaDataSource;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * ���۵��Զ��ر�ʧЧԤ��ҵ����
 * 
 * @since 6.0
 * @version 2011-11-24 ����11:20:40
 * @author ������
 */
public class SalequoAlertPlugin implements IPreAlertPlugin {

  /** ��ǰԤ������ */
  private static final String ALERTDAY = "alertDay";

  @Override
  public PreAlertObject executeTask(PreAlertContext context)
      throws BusinessException {

    PreAlertObject retObj = new PreAlertObject();
    try {
      // ��ѯ�������ڵı��۵�Ԥ��
      SalequotationHVO[] toabdatehvos = this.getAlertinfo(context);

      if (null == toabdatehvos || toabdatehvos.length == 0) {
        retObj.setReturnType(PreAlertReturnType.RETURNNOTHING);
      }
      else {
        retObj.setReturnType(PreAlertReturnType.RETURNDATASOURCE);
        // modify by zhangby5 Ԥ����Ϣ��ʾʱֻ��ʾ�˱�ͷ���� begin
        AggSalequotationHVO[] aggVO = this.queryAggSalequotationVO(toabdatehvos);
        // end
        retObj.setReturnObj(new SOAlertMetaDataSource(aggVO));
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return retObj;
  }

  private AggSalequotationHVO[] queryAggSalequotationVO(
		SalequotationHVO[] toabdatehvos) {
	String[] billids = new String[toabdatehvos.length];
	int i = 0;
	for(SalequotationHVO hvo : toabdatehvos){
		billids[i] = hvo.getPk_salequotation();
	}
	BillQuery<AggSalequotationHVO> qrysrv = new BillQuery<AggSalequotationHVO>(
				AggSalequotationHVO.class);
	return qrysrv.query(billids);
  }

  /**
   * ��ȡԤ����Ϣ����ʵ�弯
   * 
   * @param context ִ�л���������
   * @return Ԥ����Ϣ����ʵ�弯
   */
  private SalequotationHVO[] getAlertinfo(PreAlertContext context) {

    String where = this.getWhereSql(context);
    VOQuery<SalequotationHVO> qrysrv =
    new VOQuery<SalequotationHVO>(
				SalequotationHVO.class,
				new String[] { SalequotationHVO.PK_SALEQUOTATION });
    SalequotationHVO[] toabdatehvos = qrysrv.queryWithWhereKeyWord(where, null);
    return toabdatehvos;
  }

  /**
   * ��ѯ�������ڵ���Ҫ���Զ��رպ�ʧЧ�ı��۵���Ϣ
   * 
   * @param context ִ�л���������
   * @return ��ѯ����������ѯ�������ڵģ��ҵ���״̬Ϊ������/������/����ͨ�����ĵ���
   * 
   */
  private String getWhereSql(PreAlertContext context) {

    SqlBuilder where = new SqlBuilder();
    where.append(" where ");
    where.append(SalequotationHVO.PK_GROUP, context.getGroupId());
    String[] pk_orgs = context.getPk_orgs();
    if (pk_orgs != null && pk_orgs.length > 0) {
      where.append(" and ");
      where.append(SalequotationHVO.PK_ORG, pk_orgs);
    }
    where.append(" and ");
    int[] status = new int[] {
      BillStatusEnum.C_FREE, BillStatusEnum.C_AUDITING, BillStatusEnum.C_AUDIT
    };
    where.append(SalequotationHVO.FSTATUSFLAG, status);
    where.append(" and ");
    UFDate serviceDate = AppContext.getInstance().getServerTime().getDate();
    UFDate dateStart = serviceDate.getDateBefore(7).asBegin();
    where.append(SalequotationHVO.DENDDATE, " >= ", dateStart.toString());
    where.append(" and ");
    int day =
        ValueUtils.getInt(context.getKeyMap().get(SalequoAlertPlugin.ALERTDAY));
    UFDate dateEnd = serviceDate.getDateAfter(day).asEnd();
    where.append(SalequotationHVO.DENDDATE, " <= ", dateEnd.toString());
    return where.toString();
  }

}
