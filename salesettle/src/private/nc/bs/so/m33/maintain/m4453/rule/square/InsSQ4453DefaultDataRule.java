package nc.bs.so.m33.maintain.m4453.rule.square;

import java.util.HashMap;
import java.util.Map;

import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasHVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.util.SquareCalculatorForVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.trade.checkrule.VOChecker;

import nc.pubitf.ic.m4c.m32.IQuery4CFor32;
import nc.pubitf.so.m33.self.pub.ISquare434CQuery;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ��ʽ����;������㵥����ǰĬ��ֵ������
 * @scene
 * ��ʽ����;������㵥����ǰ
 * @param
 * ��
 */
public class InsSQ4453DefaultDataRule implements IRule<SquareWasVO> {

  @Override
  public void process(SquareWasVO[] vos) {

    // �����۳�������㵥�ϲ�������
    this.fillDataFromSquareOut(vos);

    // ���¼��㵥�۽��
    SquareWasBVO[] bvos = SquareWasVOUtils.getInstance().getSquareWasBVO(vos);
    new SquareCalculatorForVO().calculate(bvos, SquareWasBVO.NTHISNUM);
  }

  /**
   * �Ӷ����ϲ������ݣ�
   * ���������֯
   * ���������֯�汾
   * ���㷽ʽ
   * ��������
   * ɢ��
   * ���������Ƿ���Դ�Ӧ�ա��ɱ���־
   * 
   * @param vos
   */
  private void fillDataFromSquareOut(SquareWasVO[] vos) {
    try {
      // ���γ��ⵥ��id
      String[] outBids =
          AggVOUtil.getDistinctItemFieldArray(vos, SquareWasBVO.CSRCBID,
              String.class);

      // ���۳�������ѯ�ӿ�,��ѯ���۳�������㵥
      Map<String, SquareOutViewVO> moutidvo =
          this.querySquareOutViewVO(outBids);

      // �����۳��ⵥ��ȡԭ�ҵ��ۡ�ԭ�Ҿ��ۡ���֯�۱����ʡ������۱����ʡ�ȫ���۱�����
      IQuery4CFor32 qry4C = NCLocator.getInstance().lookup(IQuery4CFor32.class);
      Map<String, SaleOutBodyVO> m4C = qry4C.queryBodys(outBids);

      // ;������㵥��ֵ
      SquareOutViewVO soutvo = null;
      for (SquareWasVO swvo : vos) {
        for (SquareWasBVO bvo : swvo.getChildrenVO()) {
          String outbid = bvo.getCsrcbid();
          soutvo = moutidvo.get(outbid);

          if (soutvo == null) {
            if (!SOBillType.Order.isEqual(bvo.getVfirsttype())) {
              ExceptionUtils
                  .wrappBusinessException(NCLangResOnserver.getInstance()
                      .getStrByID("4006010_0", "04006010-0152")/*"����ʧ�ܣ�Դͷ����Ϊ���ں�ͬ����֧��;�����̣�"*/);
            }
          }
          /**
           * ���۳�������㵥���������ֵ
           * ���Կ���ֱ�Ӵ����۳�������㵥��ȡ���������֯��
           * ;���ϵĽ��������֯������ͻ������̵ģ����ܶ��չ���
           */
          String pk_org = soutvo.getHead().getPk_org();
          String pk_org_v = soutvo.getHead().getPk_org_v();
          SquareWasHVO hvo = swvo.getParentVO();
          hvo.setPk_org(pk_org);
          hvo.setPk_org_v(pk_org_v);
          bvo.setPk_org(pk_org);
          this.setSquareWasBVOBySquareOutViewVO(bvo, soutvo);

          // �ӳ��ⵥ�����ü۸�ҵ������
          SaleOutBodyVO outbvo = m4C.get(outbid);
          this.setSquareWasBVOBySaleOutBodyVO(bvo, outbvo);

          // ���ý�������
          bvo.setNnum(MathTool.oppose(bvo.getNnum()));
          bvo.setNastnum(MathTool.oppose(bvo.getNastnum()));
          bvo.setNthisnum(MathTool.oppose(bvo.getNthisnum()));
        }
      }

    } // end try
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
  }

  private Map<String, SquareOutViewVO> querySquareOutViewVO(String[] outBids) {
    ISquare434CQuery square4cQry =
        NCLocator.getInstance().lookup(ISquare434CQuery.class);
    SquareOutViewVO[] sovvos = square4cQry.querySquareOutViewVOBy4CBID(outBids);
    // <���۳��ⵥ��id,SquareOutViewVO>
    Map<String, SquareOutViewVO> moutidvo =
        new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO svvo : sovvos) {
      moutidvo.put(svvo.getItem().getCsquarebillbid(), svvo);
    }

    return moutidvo;
  }

  private void setSquareType(SquareWasBVO bvo, SquareOutViewVO soutvo) {
    // ������γ��ⵥ�ݹ�Ӧ�գ�;�𵥻س�Ӧ��
    Integer artype =
        SquareWasVOUtils.getInstance().getARSquareType(
            soutvo.getItem().getFpreartype());
    Integer iatype =
        SquareWasVOUtils.getInstance().getIASquareType(
            soutvo.getItem().getFpreiatype());
    if (!VOChecker.isEmpty(artype)) {
      bvo.setFpreartype(artype);
    }
    if (!VOChecker.isEmpty(iatype)) {
      bvo.setFpreiatype(iatype);
    }
  }

  private void setSquareWasBVOBySaleOutBodyVO(SquareWasBVO bvo,
      SaleOutBodyVO outbvo) {
    bvo.setDbizdate(outbvo.getDbizdate());
    bvo.setNorigdiscount(outbvo.getNitemdiscountrate());
    bvo.setNorigtaxprice(outbvo.getNorigtaxprice());
    bvo.setNorigtaxnetprice(outbvo.getNorigtaxnetprice());
    bvo.setNorigprice(outbvo.getNorigprice());
    bvo.setNorignetprice(outbvo.getNorignetprice());
    bvo.setNexchangerate(outbvo.getNchangestdrate());
    bvo.setNgroupexchgrate(outbvo.getNgroupexchgrate());
    bvo.setNglobalexchgrate(outbvo.getNglobalexchgrate());
  }

  private void setSquareWasBVOBySquareOutViewVO(SquareWasBVO bvo,
      SquareOutViewVO soutvo) {
    // ���ý�������
    this.setSquareType(bvo, soutvo);

    bvo.setCarorgid(soutvo.getItem().getCarorgid());
    bvo.setCarorgvid(soutvo.getItem().getCarorgvid());
    bvo.setCprofitcenterid(soutvo.getItem().getCprofitcenterid());
    bvo.setCprofitcentervid(soutvo.getItem().getCprofitcentervid());
    bvo.setCcostorgid(soutvo.getItem().getCcostorgid());
    bvo.setCordercustid(soutvo.getItem().getCordercustid());
    bvo.setCinvoicecustid(soutvo.getItem().getCinvoicecustid());
    bvo.setCfreecustid(soutvo.getItem().getCfreecustid());
    bvo.setCpaytermid(soutvo.getItem().getCpaytermid());
    bvo.setCchanneltypeid(soutvo.getItem().getCchanneltypeid());
    bvo.setCdeptid(soutvo.getItem().getCdeptid());
    bvo.setCdeptvid(soutvo.getItem().getCdeptvid());
    bvo.setCsaleorgid(soutvo.getItem().getCsaleorgid());
    bvo.setCsaleorgvid(soutvo.getItem().getCsaleorgvid());
    bvo.setCemployeeid(soutvo.getItem().getCemployeeid());
    bvo.setCprolineid(soutvo.getItem().getCprolineid());
    bvo.setCcustbankaccid(soutvo.getItem().getCcustbankaccid());

    // ���ñ����Ƿ���Դ�Ӧ�ա��ɱ���־
    bvo.setBcost(soutvo.getItem().getBcost());
    bvo.setBincome(soutvo.getItem().getBincome());
  }

}
