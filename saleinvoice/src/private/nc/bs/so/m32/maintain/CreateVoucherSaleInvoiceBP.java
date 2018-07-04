package nc.bs.so.m32.maintain;

import java.util.ArrayList;
import java.util.List;

import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.res.NCModule;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.itf.scmpub.reference.fip.FipMessageService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.impl.pubapp.env.BSContext;

/**
 * ����ƾ֤����
 * 
 * @since 6.0
 * @version 2011-3-28 ����11:53:20
 * @author ô��
 */
public class CreateVoucherSaleInvoiceBP {

  /**
   * ����ƾ֤
   * 
   * @param bills ���۷�Ʊvo
   */
  public void createVoucher(SaleInvoiceVO[] bills) {
    // ���û����ƽ̨
    if (!SysInitGroupQuery.isFIPEnabled()) {
      return;
    }
    // TODO ͳһ�淶���ٵ���
    TimeLog.logStart();
    List<FipMessageVO> msgvos = this.createBillsForFIP(bills);
    TimeLog.info("��������ʵʱƾ֤�ĵ���VO"); /*-=notranslate=-*/

    TimeLog.logStart();
    try {
      this.sendMessage(msgvos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    TimeLog.info("����ƽ̨������Ϣ"); /*-=notranslate=-*/
  }

  private List<FipMessageVO> createBillsForFIP(SaleInvoiceVO[] bills) {
    List<FipMessageVO> msgvos = new ArrayList<FipMessageVO>();
    for (SaleInvoiceVO bill : bills) {
      SaleInvoiceHVO hvo = bill.getParentVO();
      // SaleInvoiceBVO[] bvos = bill.getChildrenVO();
      // for (SaleInvoiceBVO bvo : bvos) {
      FipMessageVO msgvo = new FipMessageVO();
      msgvo.setBillVO(bill);
      msgvo.setMessagetype(FipMessageVO.MESSAGETYPE_ADD);
      FipRelationInfoVO infovo = new FipRelationInfoVO();
      infovo.setPk_billtype(hvo.getVtrantypecode());
      infovo.setBusidate(BSContext.getInstance().getDate());
      infovo.setPk_operator(BSContext.getInstance().getUserID());
      infovo.setPk_group(BSContext.getInstance().getGroupID());
      infovo.setPk_org(hvo.getPk_org());
      infovo.setPk_system(NCModule.SO.getName().toUpperCase());
      infovo.setRelationID(hvo.getCsaleinvoiceid());
      // ��������
      infovo.setDefdoc1(hvo.getCtrantypeid());
      // �ɱ���
      // infovo.setDefdoc2(hvo.getPk_org());
      // ��������˲�
      // infovo.setDefdoc3(hvo.getPk_book());

      // ���ݺ�
      infovo.setFreedef1(hvo.getVbillcode());
      // ��ע
      // infovo.setFreedef2(hvo.getNtotalorigmny().toString());
      // ���
      infovo.setFreedef3(hvo.getNtotalorigmny().toString());

      msgvo.setMessageinfo(infovo);
      msgvo.setBillVO(bill);
      msgvos.add(msgvo);
      // }
    }
    return msgvos;
  }

  /**
   * ����ƽ̨������Ϣ
   * 
   * @param billsmap
   * @throws BusinessException
   */
  private void sendMessage(List<FipMessageVO> msgvos) throws BusinessException {
    FipMessageVO[] msgvo = msgvos.toArray(new FipMessageVO[0]);
    try {
      FipMessageService.sendMessages(msgvo);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }

  }
}
