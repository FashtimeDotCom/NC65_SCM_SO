package nc.pubimpl.so.m33.so.m32;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billaction.SOBillAction;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.votools.SoVoTools;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;

import nc.pubitf.so.m33.so.m32.ISquare33For32;

import nc.bs.so.m33.biz.m32.bp.cancelsquare.CancelSquareInvDetailBP;
import nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar.CancelARIncomeFor32BP;
import nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar.CancelARRushIncomeFor32BP;
import nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelia.CancelIACostFor32BP;
import nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelia.CancelIARegisterCreditFor32BP;
import nc.bs.so.m33.biz.m32.bp.check.SquareInvoiceCheckBP;
import nc.bs.so.m33.biz.pub.cancelsquare.AbstractCancelSquareDetail;
import nc.bs.so.m33.maintain.m32.DeleteSquare32BP;
import nc.bs.so.m33.maintain.m32.InsertSquare32BP;
import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;
import nc.bs.so.m33.plugin.ServicePlugInPoint;
import nc.bs.so.m33.pub.CheckSquareBiz;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * ���۽����ṩ�����۷�Ʊ����ӿ�ʵ����
 * 
 * @since 6.1
 * @version 2012-11-29 11:11:45
 * @author ��ӱ�
 */
public class Square33For32Impl implements ISquare33For32 {

  @Override
  public void cancelSquareSrv(SaleInvoiceVO[] voInvoice)
      throws BusinessException {
    try {
      // ����
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(voInvoice);

      // ��ѯ���㵥����
      SquareInvVO[] sqvos =
          new QuerySquare32VOBP().querySquareInvVOBy32ID(SoVoTools
              .getVOPKValues(voInvoice));
      if (sqvos == null || sqvos.length == 0) {
        return;
      }

      // ������γ��ⵥ�����ݹ���������Ʒ����Ʊ����ȡ������
      new SquareInvoiceCheckBP().checkETREGForCancelSquare(sqvos);

      // �Է�Ʊ�����㵥����
      tool.lockBill(sqvos);

      // ȡ�����㵥
      this.cancelSquareDetail(sqvos);

      // ȡ�������㵥
      new DeleteSquare32BP().delete(sqvos);

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void pushSquareSrv(SaleInvoiceVO[] voInvoice) throws BusinessException {
    try {
      // ����
      BillConcurrentTool tool = new BillConcurrentTool();
      tool.lockBill(voInvoice);

      // ���û�����ý��㶯��ֱ������
      TimeLog.logStart();

      boolean hasSquareBiz =
          new CheckSquareBiz().ifHasSquareAction(voInvoice[0].getParentVO()
              .getVtrantypecode(), voInvoice[0].getParentVO().getCbiztypeid(),
              SOBillAction.SaleInvoiceApprove.getCode());
      if (!hasSquareBiz) {
        return;
      }

      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006010_0", "04006010-0063")/*@res "�жϷ�Ʊ�Ƿ��н��㶯��"*/);

      // 32��33��VO����
      TimeLog.logStart();
      SquareInvVO[] sqvos =
          PfServiceScmUtil.executeVOChange(SOBillType.Invoice.getCode(),
              SOBillType.SquareInvoice.getCode(), voInvoice);

      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006010_0", "04006010-0064")/*@res "����32��33��VO����"*/);

      TimeLog.logStart();
      AroundProcesser<SquareInvVO> processer =
          new AroundProcesser<SquareInvVO>(ServicePlugInPoint.Push33For32);

      processer.before(sqvos);

      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006010_0", "04006010-0017")/*@res "���ý��㵥����BPǰ����"*/);

      // ���㵥����
      TimeLog.logStart();
      new InsertSquare32BP().insert(sqvos);

      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006010_0", "04006010-0018")/*@res "���ý��㵥��������BP"*/);

      TimeLog.logStart();
      processer.after(sqvos);

      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006010_0", "04006010-0019")/*@res "���ý��㵥����BP�����"*/);

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  private void cancelSquareDetail(
      Map<SquareType, List<SquareInvDetailVO>> m_sqDetailVo) {
    // ȡ���س�
    new CancelARRushIncomeFor32BP().cancelSquare(m_sqDetailVo
        .get(SquareType.SQUARETYPE_ARRUSH));

    // ȡ��ȷ��
    new CancelARIncomeFor32BP().cancelSquare(m_sqDetailVo
        .get(SquareType.SQUARETYPE_AR));

    // ȡ���ɱ�
    new CancelIACostFor32BP().cancelSquare(m_sqDetailVo
        .get(SquareType.SQUARETYPE_IA));

    // ȡ��������Ʒ
    new CancelIARegisterCreditFor32BP().cancelSquare(m_sqDetailVo
        .get(SquareType.SQUARETYPE_REG_CREDIT));
  }

  /**
   * ��������������ȡ�����㵥
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param sqvos
   *          <p>
   * @author zhangcheng
   * @time 2010-8-18 ����03:05:27
   */
  private void cancelSquareDetail(SquareInvVO[] sqvos) {
    // ������ϸ����
    SquareInvDetailVO[] sqdvos =
        new QuerySquare32VOBP().querySquareInvDetailVOBySQHID(AggVOUtil
            .getDistinctItemFieldArray(sqvos, SquareInvDetailVO.CSALESQUAREID,
                String.class));

    if (sqdvos != null) {
      // ����
      new VOConcurrentTool().lock(sqdvos);

      // ���ñ���ȡ����������,���ڷ�Ʊ��������ԭ�򣬹�ֱ���ô����㵥����ȡ��
      for (SquareInvVO svo : sqvos) {
        for (SquareInvBVO bvo : svo.getChildrenVO()) {
          bvo.setNthisnum(MathTool.oppose(bvo.getNnum()));
          bvo.setNorigtaxmny(MathTool.oppose(bvo.getNorigtaxmny()));
        }
      }
      for (SquareInvDetailVO dvo : sqdvos) {
        dvo.setNsquarenum(MathTool.oppose(dvo.getNsquarenum()));
        dvo.setNorigtaxmny(MathTool.oppose(dvo.getNorigtaxmny()));
      }

      // �����۳�������㵥����
      BSContext.getInstance().setSession(SquareInvVO.class.getName(), sqvos);

      // ���㵥���ݷ���
      AbstractCancelSquareDetail<SquareInvDetailVO> caction =
          new CancelSquareInvDetailBP();
      Map<SquareType, List<SquareInvDetailVO>> m_sqDetailVo =
          caction.splitVOBySquareType(sqdvos, SquareInvDetailVO.FSQUARETYPE);
      // caction.cancelSquare(sqdvos, SquareInvDetailVO.FSQUARETYPE);

      // ȡ������
      this.cancelSquareDetail(m_sqDetailVo);

      // �ͷŻ���
      BSContext.getInstance().removeSession(SquareOutVO.class.getName());
    }
  }

  @Override
  public Map<String, String> queryInvSquareDetail(String[] invhids,
      String[] invbids) throws BusinessException {
    String[] keys = new String[] {
      SquareInvDetailVO.CSQUAREBILLBID, SquareInvDetailVO.CSALESQUAREDID
    };
    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(" and ");
    IDExQueryBuilder idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = idqb.buildSQL(SquareInvDetailVO.CSQUAREBILLID, invhids);
    wheresql.append(where);

    idqb = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    where = idqb.buildSQL(SquareInvDetailVO.CSQUAREBILLBID, invbids);
    wheresql.append("and  " + where);

    VOQuery<SquareInvDetailVO> qrysrv =
        new VOQuery<SquareInvDetailVO>(SquareInvDetailVO.class, keys);
    SquareInvDetailVO[] detailvos = qrysrv.query(wheresql.toString(), null);
    if (null == detailvos || detailvos.length == 0) {
      return new HashMap<String, String>();
    }
    Map<String, String> mapdet = new HashMap<String, String>();
    for (SquareInvDetailVO detvo : detailvos) {
      mapdet.put(detvo.getCsalesquaredid(), detvo.getCsquarebillbid());
    }
    return mapdet;

  }

}
