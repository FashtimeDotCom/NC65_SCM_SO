package nc.ui.so.m30.billui.tranferbill;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.FillNmffilePriceRule;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * ���۶����������۳��ⵥ�˻�ת����ǰ̨���ݴ�����
 * 
 * @since 6.0
 * @version 2011-7-7 ����10:20:23
 * @author fengjb
 */
public class M30Ref4CTransferBillDataLogic extends DefaultBillDataLogic {

  @Override
  public void doTransferAddLogic(Object selectedData) {
    // �����������ڽ�����
    super.doTransferAddLogic(selectedData);

    // ���ڽ��濨Ƭ���ֵ����
    BillCardPanel cardPanel = this.getBillForm().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 1.�˻��������ڵ���佻������
    String ctrantypeid = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    if (!PubAppTool.isNull(ctrantypeid)) {
      M30TranTypeVO newtrantypevo = null;
      try {
        IM30TranTypeService m30srv =
            NCLocator.getInstance().lookup(IM30TranTypeService.class);
        newtrantypevo = m30srv.queryTranTypeVO(ctrantypeid);
      }
      catch (BusinessException e1) {
        ExceptionUtils.wrappException(e1);
      }
      if (newtrantypevo==null) {
        ExceptionUtils.wrappBusinessException("���ݽ�������ID��ѯ����VOʧ�ܣ�");/*-=notranslate=-*/
      }
      String trantypecode = newtrantypevo.getVtrantypecode();
      keyValue.setHeadValue(SaleOrderHVO.VTRANTYPECODE, trantypecode);

      // �Զ�ƥ��ҵ������
      String csaleorgid = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
      String userid = AppUiContext.getInstance().getPkUser();
      String cbiztypeid = null;
      try {
        cbiztypeid =
            PfUtilClient.retBusitypeCanStart(SOBillType.Order.getCode(),
                trantypecode, csaleorgid, userid);
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
      // ����ҵ������
      keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, cbiztypeid);
    }
    // 1.����������
    FillNmffilePriceRule nmffileRule = new FillNmffilePriceRule(keyValue);
    nmffileRule.setNmffilePrice();
    // 2.��ͷ�ϼ�
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

  }
}
