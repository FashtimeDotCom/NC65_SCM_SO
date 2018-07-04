package nc.bs.so.m30.revise.rule;

import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.IPfRetCheckInfo;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryBVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 
 * @description
 * ���۶����޶�
 * @scene
 * ���۶����޶�����������
 * @param
 * ��
 *
 * @since 6.36
 * @version 2015-7-13 ����10:39:27
 * @author ����
 */
public class FillDataHistoryRule implements ICompareRule<SaleOrderHistoryVO> {

  @Override
  public void process(SaleOrderHistoryVO[] vos, SaleOrderHistoryVO[] originVOs) {
    fillDataHistory(vos);
  }

  private void fillDataHistory(SaleOrderHistoryVO[] vos) {
    int index = 0;
    for (SaleOrderHistoryVO vo : vos) {
      SaleOrderHistoryHVO hvo = vo.getParentVO();
      // ������״̬
      hvo.setFpfstatusflag(IPfRetCheckInfo.NOSTATE);
      // ����״̬
      hvo.setFstatusflag(BillStatus.I_FREE);
      // ��յ�ǰ�����˺�����ʱ�� ��Ϣ
      hvo.setApprover(null);
      hvo.setTaudittime(null);
      hvo.setTs(null);
      hvo.setVhistrantypecode(SOBillType.Order30R.getCode());
      hvo.setChistrantypeid(SOBillType.Order30R.getCode());
      SaleOrderHistoryBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderHistoryBVO bvo : bvos) {
        // ������޶�vo��ɾ��̬���ѵ���״̬�óɲ����޸ġ�
        if (bvo.getStatus() == VOStatus.DELETED) {
          bvo.setStatus(VOStatus.UNCHANGED);
        }
        else {
          // ����״̬���������� �����޸� ��Ӧ�ý����������ÿ�
          bvo.setStatus(VOStatus.NEW);
          bvo.setCorderhistorybid(null);
          bvo.setFrowstatus(BillStatus.I_FREE);
          // �޶�������
          if (PubAppTool.isNull(bvo.getCsaleorderbid())
              || PubAppTool.isNull(bvo.getCsaleorderid())) {
            index++;
          }
        }
      }
    }
    if (index == 0) {
      return;
    }
    // �����޶�ʱ�����е����۶���id
    DBTool dao = new DBTool();
    String[] ids = dao.getOIDs(index);
    int tempindex = 0;
    for (SaleOrderHistoryVO vo : vos) {
      SaleOrderHistoryHVO hvo = vo.getParentVO();
      SaleOrderHistoryBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderHistoryBVO bvo : bvos) {
        if (PubAppTool.isNull(bvo.getCsaleorderbid())
            || PubAppTool.isNull(bvo.getCsaleorderid())) {
          bvo.setCsaleorderid(hvo.getCsaleorderid());
          bvo.setCsaleorderbid(ids[tempindex]);
          tempindex++;
        }
      }
    }
  }

}
