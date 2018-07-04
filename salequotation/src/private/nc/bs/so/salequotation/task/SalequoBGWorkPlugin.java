package nc.bs.so.salequotation.task;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * ���۵��Զ�ʧЧ�͹رպ�̨����ҵ����
 * 
 * @since 6.0
 * @version 2011-11-24 ����04:08:23
 * @author ������
 */
public class SalequoBGWorkPlugin implements IBackgroundWorkPlugin {

  /**
   * �رպ��Զ�ʧЧ���ڵ����Ѿ������ı��۵�
   * 
   * @param bgWorkhvos ���Խ��йرպ��Զ�ʧЧ�ĵ���ʵ�弯
   */
  private void autoInvaliClose(SalequotationHVO[] bgWorkhvos) {

    List<SalequotationHVO> closeList = new ArrayList<SalequotationHVO>();
    List<SalequotationHVO> invalidationList = new ArrayList<SalequotationHVO>();
    for (SalequotationHVO vo : bgWorkhvos) {
      Integer billStatus = vo.getFstatusflag();
      if (BillStatusEnum.FREE.equalsValue(billStatus)
          || BillStatusEnum.AUDITING.equalsValue(billStatus)) {
        invalidationList.add(vo);
      }
      else {
        closeList.add(vo);
      }
    }
    if (closeList.size() > 0) {
      this.closeOrder(closeList);
    }
    if (invalidationList.size() > 0) {
      this.invalidationOrder(invalidationList);
    }

  }

  /**
   * �رձ��۵�
   * 
   * @param closeList ���رյĵ���ʵ�弯
   */
  private void closeOrder(List<SalequotationHVO> closeList) {

    SalequotationHVO[] hvos = new SalequotationHVO[closeList.size()];
    closeList.toArray(hvos);
    for (SalequotationHVO hvo : hvos) {
      hvo.setFstatusflag(Integer.valueOf(BillStatusEnum.C_CLOSE));
      hvo.setStatus(VOStatus.UPDATED);
    }
    String[] names = new String[] {
      SalequotationHVO.FSTATUSFLAG
    };
    VOUpdate<SalequotationHVO> update = new VOUpdate<SalequotationHVO>();
    update.update(hvos, names);
  }

  @Override
  public PreAlertObject executeTask(BgWorkingContext bgwc)
      throws BusinessException {

    PreAlertObject retObj = new PreAlertObject();
    try {
      // ��ȡ��Ҫ�Զ��رպ��Զ�ʧЧ�ĵ���ʵ�弯��
      SalequotationHVO[] bgWorkhvos = this.getBGWorkInfo(bgwc);
      // �Զ�ʧЧ���ڱ��۵����ر�����ͨ���ĵ��ڱ��۵�
      this.autoInvaliClose(bgWorkhvos);
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
   * @return ��Ҫ�Զ�ʧЧ�͹رյģ����Ѿ������ĵ���ʵ������
   */
  private SalequotationHVO[] getBGWorkInfo(BgWorkingContext bgwc) {

    SqlBuilder where = this.getWhereSql(bgwc);
    String[] key = new String[] {
      SalequotationHVO.PK_SALEQUOTATION
    };
    VOQuery<SalequotationHVO> qrysrvOrig =
        new VOQuery<SalequotationHVO>(SalequotationHVO.class, key);
    SalequotationHVO[] keyhvos = qrysrvOrig.query(where.toString(), null);
    if (keyhvos.length == 0) {
      return keyhvos;
    }
    List<String> idsList = new ArrayList<String>();
    for (SalequotationHVO hvo : keyhvos) {
      idsList.add(hvo.getPk_salequotation());
    }
    String[] ids = idsList.toArray(new String[idsList.size()]);
    // ��pk���������̨����ִ�й����е��ݷ����ı�
    LockOperator lock = new LockOperator();
    lock.lock(
        ids,
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0",
            "04006012-000007")/*��̨����ִ��ʧ�ܣ����Ժ����´�����̨����*/);
    // ʹ��ԭʼwhere��֤�����й�����Ϣ���Ǽ���ǰ����Ϣ���䣬��ʱ�������
    where.append(" and ");
    // ������ʱ������ѯ����id��������������������
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String insql = iq.buildSQL(SalequotationHVO.PK_SALEQUOTATION, ids);
    where.append(insql);
    VOQuery<SalequotationHVO> qrysrv =
        new VOQuery<SalequotationHVO>(SalequotationHVO.class);
    SalequotationHVO[] toabdatehvos = qrysrv.query(where.toString(), null);

    return toabdatehvos;
  }

  /**
   * ��ѯ��Ҫ�Զ�ʧЧ�͹رձ��۵���Ϣ
   * 
   * @param bgwc ��̨���񻷾�����
   * @return ��ѯ���������Ѿ����ڵģ��ҵ���״̬Ϊ������/������/����ͨ�����ĵ���
   */
  private SqlBuilder getWhereSql(BgWorkingContext bgwc) {

    SqlBuilder where = new SqlBuilder();
    where.append(" and ");
    where.append(SalequotationHVO.PK_GROUP, bgwc.getGroupId());
    String[] pkorgs = bgwc.getPk_orgs();
    if (pkorgs != null && pkorgs.length > 0) {
      where.append(" and ");
      where.append(PreOrderHVO.PK_ORG, pkorgs);
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
    UFDate dateEnd = serviceDate.asBegin();
    where.append(SalequotationHVO.DENDDATE, " <= ", dateEnd.toString());
    return where;
  }

  /**
   * ʧЧ���۵�
   * 
   * @param invalidationList ���Զ�ʧЧ�ĵ���ʵ�弯
   */
  private void invalidationOrder(List<SalequotationHVO> invalidationList) {

    SalequotationHVO[] hvos = new SalequotationHVO[invalidationList.size()];
    invalidationList.toArray(hvos);
    for (SalequotationHVO hvo : hvos) {
      hvo.setFstatusflag(Integer.valueOf(BillStatusEnum.C_INVALIDATE));
      hvo.setStatus(VOStatus.UPDATED);
    }
    String[] names = new String[] {
      SalequotationHVO.FSTATUSFLAG
    };
    VOUpdate<SalequotationHVO> update = new VOUpdate<SalequotationHVO>();
    update.update(hvos, names);
  }

}
