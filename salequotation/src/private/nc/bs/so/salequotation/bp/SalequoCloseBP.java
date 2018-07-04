package nc.bs.so.salequotation.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * ���ݹر�BP���
 *
 * @author chenyyb
 *
 */
public class SalequoCloseBP {

  /**
   * �رյ���
   *
   * @param aggVO
   *          ����VO����
   * @return ����VO
   * @throws Exception
   */
  public AggSalequotationHVO[] close(AggSalequotationHVO[] aggVOs)
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
    for (AggSalequotationHVO aggVO : fullBills) {
      SalequotationHVO hvo = aggVO.getParentVO();
      if (hvo != null) {
        // ֻ������֮����ܹر�
        if (hvo.getFstatusflag().intValue() == BillStatusEnum.C_AUDIT) {
          hvo.setFstatusflag(Integer.valueOf(BillStatusEnum.C_CLOSE));
          hvo.setStatus(VOStatus.UPDATED);
        }
      }
    }
    // ����֮�󷵻س�ɾ����VO֮���ȫVO��TS�Ѿ������¹�
    AggSalequotationHVO[] newbills =
        updateAction.update(fullBills, originBills);
    // ��ǰ̨����ֻ�����˸ı������vo
    return transferTool.getBillForToClient(newbills);
  }
}