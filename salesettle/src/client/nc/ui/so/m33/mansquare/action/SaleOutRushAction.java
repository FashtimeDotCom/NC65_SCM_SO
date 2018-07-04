package nc.ui.so.m33.mansquare.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m33.ic.m4c.ISquareAcionFor4C;
import nc.ui.pubapp.billref.src.action.SuperAction;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.trade.checkrule.VOChecker;

/**
 * ����Գ�
 * ���۷�Ʊ�������۳��ⵥת��������ʹ��
 * 
 * @since 6.0
 * @version 2010-12-7 ����10:38:00
 * @author zhangcheng
 */

public class SaleOutRushAction extends SuperAction {

  private static final long serialVersionUID = -4995201754887027841L;

  public SaleOutRushAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.IC_SALEOUTRUSH);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleOutVO[] selectVOs =
        (SaleOutVO[]) this.getRefContext().getRefBill().getSelectVOs();
    if (VOChecker.isEmpty(selectVOs)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0005")/*@res "����ѡ����Ҫ�Գ�ĵ���!"*/);
      return;
    }
    // ���ֵ��ݱ���ID
    List<String> blueBids = new ArrayList<String>();
    // ���ֵ��ݱ���ID
    List<String> redBids = new ArrayList<String>();
    for (SaleOutVO vo : selectVOs) {
      this.splitForBlueAndRed(vo, blueBids, redBids);
    }
    if (VOChecker.isEmpty(blueBids) || VOChecker.isEmpty(redBids)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0006")/*@res "�Գ�ĵ��ݲ���ֻ�����ֻ����!"*/);
      return;
    }
    ISquareAcionFor4C rush =
        NCLocator.getInstance().lookup(ISquareAcionFor4C.class);
    rush.outRush(blueBids.toArray(new String[blueBids.size()]),
        redBids.toArray(new String[redBids.size()]));

    // �Գ���ɺ�ˢ��ת������
    IRefQueryService refQueryService =
        (IRefQueryService) this.getRefContext().getRefInfo().getQueryService();
    IQueryScheme queryScheme =
        this.getRefContext().getBillReferQuery().getQueryScheme();
    Object[] billvos = refQueryService.queryByQueryScheme(queryScheme);
    this.getRefBillModel().setBillVOs((AggregatedValueObject[]) billvos);
  }

  /**
   * ��ѡ��ĵ����й��˳��������ݵ�bid
   * 
   * @param vo
   * @param blueBids ����bid
   * @param redBids ����bid
   */
  private void splitForBlueAndRed(SaleOutVO vo, List<String> blueBids,
      List<String> redBids) {
    if (VOChecker.isEmpty(vo)) {
      return;
    }
    SaleOutBodyVO[] bodys = vo.getBodys();
    if (VOChecker.isEmpty(bodys)) {
      return;
    }
    for (SaleOutBodyVO body : bodys) {
      // ��ζԳ�ʱ,body����Ϊ��
      if (VOChecker.isEmpty(body)) {
        continue;
      }
      if (MathTool.greaterThan(body.getNnum(), UFDouble.ZERO_DBL)) {
        blueBids.add(body.getCgeneralbid());
      }
      else {
        redBids.add(body.getCgeneralbid());
      }
    }
  }

}
