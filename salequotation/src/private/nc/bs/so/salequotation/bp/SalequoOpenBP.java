package nc.bs.so.salequotation.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * ���ݴ�BP���
 *
 * @author chenyyb
 *
 */
public class SalequoOpenBP {
  /**
   * �򿪵���
   *
   * @param aggVO
   *          ����VO
   * @return ����VO
   * @throws Exception
   */
  public AggSalequotationHVO[] open(AggSalequotationHVO[] aggVOs)
      throws Exception {
    if (aggVOs == null || aggVOs.length == 0) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0023")/*@res "��ѡ�񵥾ݣ�"*/);
    }
    BillUpdate<AggSalequotationHVO> updateAction =
        new BillUpdate<AggSalequotationHVO>();
    BillTransferTool<AggSalequotationHVO> transferTool =
        new BillTransferTool<AggSalequotationHVO>(aggVOs);
    AggSalequotationHVO[] originBills = transferTool.getOriginBills();
    AggSalequotationHVO[] fullBills = transferTool.getClientFullInfoBill();

    for (int i = 0; i < fullBills.length; i++) {
      SalequotationHVO hvo = fullBills[i].getParentVO();
      if (hvo != null) {
        if (hvo.getFstatusflag() != null
            && hvo.getFstatusflag().intValue() == BillStatusEnum.C_CLOSE) {
          hvo.setFstatusflag(Integer.valueOf(BillStatusEnum.C_AUDIT));
          hvo.setStatus(VOStatus.UPDATED);
        }
      }
    }
    this.checkBillStatus(originBills);
    AggSalequotationHVO[] newbills =
        updateAction.update(fullBills, originBills);

    // ��ǰ̨����ֻ�����˸ı������vo
    return transferTool.getBillForToClient(newbills);
  }

  private void checkBillStatus(AggSalequotationHVO[] originBills) {
    for (AggSalequotationHVO bill : originBills) {
      if (bill == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0024")/*@res "��Ч����"*/);
        continue;
      }
      SalequotationHVO hvo = (SalequotationHVO) bill.getParent();
      if (hvo == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0024")/*@res "��Ч����"*/);
        continue;
      }
      int billStatus = hvo.getFstatusflag().intValue();
      if (billStatus != BillStatusEnum.C_CLOSE) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0027")/*@res "ֻ�йرյĵ��ݲ��ܱ���"*/);
      }
    }
  }
}