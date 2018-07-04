package nc.bs.so.m30.rule.approve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m30.revise.UpdateSaleOrderBP;
import nc.bs.so.m30.revise.rule.ReviseApproveStateRule;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryBVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.m30.rule.EditableAndRewiteItems;
import nc.vo.so.m30.util.Transfer30and30RVOTool;
import nc.vo.so.pub.util.ListUtil;

/**
 * @description
 *              ���۶����޶�������������۶�������
 * @scene
 *        ���۶�������ͨ����
 * @param ��
 * @since 6.3
 * @version 2014-12-16 ����3:11:04
 * @author wangshu6
 */
public class SaleOrderReviseApproveAfterRule implements IRule {

  @Override
  public void process(Object[] vos) {

    SaleOrderHistoryVO[] revisebills = (SaleOrderHistoryVO[]) vos;

    // ���ж�������״̬ ֻ�е�ѡ������ʱ�����в�����ѡ�񡰲��ء���������������
    for (SaleOrderHistoryVO bill : revisebills) {
      if (bill.getParentVO().getFpfstatusflag() == BillStatusEnum.APPROVED
          .toIntValue()) {
        SaleOrderVO[] saleOrderbills;
        try {
          // 1.��ȡ�޶�����vo
          revisebills = getOrderVOs(revisebills);
          // 2.�����ݿ��ѯ �޶�ǰ�汾VO
          saleOrderbills = query30BeforeRevise(revisebills);
          // 3.�����޶��ֶε�ֵ��ϳ��µ����۶���vo
          SaleOrderVO[] newVO =
              this.copyChangedValueTo30VO(revisebills, saleOrderbills);
          // 4.���µ����ݿ�
          this.updateSaleOrderByOrderHistoryVO(newVO, saleOrderbills);
        }
        catch (BusinessException ex) {
          ExceptionUtils.wrappException(ex);
        }
      }
    }
  }

  /**
   * �����۶����޶����ݸ��µ����ݿ����۶�����
   * 
   * @param newVO ���޶��ֶ���������۶���vo
   * @param saleOrderbills �޶�ǰ���۶���vo
   */
  private void updateSaleOrderByOrderHistoryVO(SaleOrderVO[] newVO,
      SaleOrderVO[] saleOrderbills) {
    BillTransferTool<SaleOrderVO> transferTool =
        new BillTransferTool<SaleOrderVO>(newVO);
    SaleOrderVO[] updatebills = transferTool.getClientFullInfoBill();
    SaleOrderVO[] originBills = transferTool.getOriginBills();
    if (!ArrayUtil.isEmpty(saleOrderbills)) {
      UpdateSaleOrderBP udpateBP = new UpdateSaleOrderBP();
      this.beforeUpdateSaleOrder(updatebills);
      udpateBP.update(updatebills, originBills);
    }
  }

  /**
   * ���۶����޶��������۶���֮ǰ�貹��ִ��
   * 
   * @param updatebills ����vo
   */
  protected void beforeUpdateSaleOrder(SaleOrderVO[] updatebills) {

    // ������û�����κ��޸�ʱ��ǰ̨û�а�VOStatus���ó��޸�״̬�����µ���������Ϣ�����¡�
    for (SaleOrderVO sobill : updatebills) {
      sobill.getParent().setStatus(VOStatus.UPDATED);
      for (SaleOrderBVO item : sobill.getChildrenVO()) {
        if (VOStatus.NEW == item.getStatus()) {
          // �޶�ʱ�������еĴ���
          this.processNewItem(item);
        }
      }
    }
  }

  /**
   * ��ѯ���۶����޶�ǰ���۶���vo
   * 
   * @param bills ���۶����޶�����
   * @return ���۶���
   * @throws BusinessException
   */
  private SaleOrderVO[] query30BeforeRevise(SaleOrderHistoryVO[] bills)
      throws BusinessException {
    if (ArrayUtil.isEmpty(bills)) {
      return null;
    }
    // ��ȡ�޶�vo�ϵ����۶���id
    String[] ids = getSaleOrderPKs(bills);
    // ��ѯ���۶���vo
    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] saleorderbills = query.query(ids);

    return saleorderbills;

  }

  /**
   * ��ѯ���۶����޶�vo����
   * 
   * @param bills
   * @return
   */
  private String[] getOrderPKs(SaleOrderHistoryVO[] bills) {
    List<String> hids = new ArrayList<String>();
    for (SaleOrderHistoryVO bill : bills) {
      String hid = bill.getParentVO().getCorderhistoryid();
      hids.add(hid);
    }
    return ListUtil.toArray(hids);
  }

  /**
   * ��ȡ�����������޶�vo
   * 
   * @param bills
   * @return
   */
  private SaleOrderHistoryVO[] getOrderVOs(SaleOrderHistoryVO[] bills) {
    // ��ѯ���۶����޶�vo����
    String[] ids = getOrderPKs(bills);
    BillQuery<SaleOrderHistoryVO> query =
        new BillQuery<SaleOrderHistoryVO>(SaleOrderHistoryVO.class);
    // ��ѯ�����޶�vo
    SaleOrderHistoryVO[] orderHistoryVOs = query.query(ids);
    return orderHistoryVOs;
  }

  /**
   * ��ѯ���۶���vo����
   * 
   * @param bills
   * @return
   */
  private String[] getSaleOrderPKs(SaleOrderHistoryVO[] bills) {
    String[] ids = getOrderPKs(bills);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select csaleorderid ");
    sql.append("from so_orderhistory where ");
    sql.append(SaleOrderHistoryHVO.CORDERHISTORYID, ids);
    sql.append(" and dr = 0");

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sql.toString());
    if (set.size() == 0) {
      return null;
    }
    return set.toOneDimensionStringArray();
  }

  /**
   * �����޶��ֶε�ֵ��ϳ��µ����۶���vo
   * 
   * @param revisebills �޶�vo
   * @param beforeRevise30Bills �޶�ǰ���۶���vo
   * @return ��Ϻ�������۶���vo
   */
  private SaleOrderVO[] copyChangedValueTo30VO(
      SaleOrderHistoryVO[] revisebills, SaleOrderVO[] beforeRevise30Bills) {
    List<SaleOrderVO> list = new ArrayList<SaleOrderVO>();
    SaleOrderVO newVO = null;
    for (SaleOrderHistoryVO revisebill : revisebills) {
      String csaleorderidBy30R = revisebill.getParentVO().getCsaleorderid();
      for (SaleOrderVO beforeRevise30Bill : beforeRevise30Bills) {
        String csaleorderidBy30 = beforeRevise30Bill.getPrimaryKey();
        if (csaleorderidBy30R.equals(csaleorderidBy30)) {
          // �����۶����޶�vo�еĿ��޶��ֶ����һ���µ�vo
          newVO = getAttributesFor30By30R(revisebill, beforeRevise30Bill);
          list.add(newVO);
        }
      }
    }
    return ListUtil.toArray(list);
  }

  /**
   * �����۶����޶�vo�еĿ��޶��ֶ����һ���µ�vo
   * 
   * @param revisebill ���۶����޶�vo
   * @param beforeRevise30Bill ���۶����޶�ǰ ���۶���vo
   * @return ��Ϻ�����۶���vo
   */
  private SaleOrderVO getAttributesFor30By30R(SaleOrderHistoryVO revisebill,
      SaleOrderVO beforeRevise30Bill) {
    SaleOrderVO saleorder = new SaleOrderVO();
    // ���Ʊ�ͷ
    SaleOrderHVO soHead =
        this.getHeadAttributes(revisebill, beforeRevise30Bill);
    // ���Ʊ���
    List<SaleOrderBVO> saleOrderList =
        getBodyAttributes(revisebill, beforeRevise30Bill);
    saleorder.setParentVO(soHead);
    saleorder.setChildrenVO(ListUtil.toArray(saleOrderList));
    return saleorder;
  }

  /**
   * �����۶����޶�vo�еı�����޶��ֶ����ϵ����۶���bvo��
   * 
   * @param revisebill ���۶����޶�vo
   * @param beforeRevise30Bill ���۶����޶�ǰ ���۶���vo
   * @return ��Ϻ�����۶���bvo
   */
  private List<SaleOrderBVO> getBodyAttributes(SaleOrderHistoryVO revisebill,
      SaleOrderVO beforeRevise30Bill) {
    SaleOrderHistoryBVO[] revisebody = revisebill.getChildrenVO();
    SaleOrderBVO[] soBody = beforeRevise30Bill.getChildrenVO();

    // ѭ�����۶��������ó��γ�һ��saleorderMap<bid,bvo>
    Map<String, SaleOrderBVO> saleorderMap =
        new HashMap<String, SaleOrderBVO>();
    for (SaleOrderBVO vo : soBody) {
      saleorderMap.put(vo.getCsaleorderbid(), vo);
    }
    List<SaleOrderBVO> addList = new ArrayList<SaleOrderBVO>();
    for (int i = 0; i < revisebody.length; i++) {

      // 1. �жϱ���id�Ƿ���ͬ����ͬ��������ֵ�� ����ͬ����һ��addlist,˵���������С�
      SaleOrderBVO newSaleOrderBVO =
          saleorderMap.get(revisebody[i].getCsaleorderbid());
      // 2. ���۶����޶�bvo��Ӧ�����۶���bvoΪ�գ�˵������Ϊ�����У�����addList��
      if (newSaleOrderBVO == null) {
        Transfer30and30RVOTool trans = new Transfer30and30RVOTool();
        SaleOrderBVO[] newbvo = trans.transferVOS(new SaleOrderHistoryBVO[] {
          revisebody[i]
        }, SaleOrderBVO.class);
        newbvo[0].setStatus(VOStatus.NEW);
        addList.add(newbvo[0]);
      }
      else {
        // 3. ѭ��������޶��ֶ� ��������
        for (String str : EditableAndRewiteItems.BODYEDITABLEITEMKEY) {
          newSaleOrderBVO.setAttributeValue(str,
              revisebody[i].getAttributeValue(str));
        }
        // 4. ѭ�� ��дӰ���ֶ� ��������
        for (String str : EditableAndRewiteItems.BODYREWRITEMKEY) {
          newSaleOrderBVO.setAttributeValue(str,
              revisebody[i].getAttributeValue(str));
        }
      }
      // 5. ����bvo״̬
      this.setBVOStatus(revisebill, beforeRevise30Bill);
    }

    List<SaleOrderBVO> saleOrderList = new ArrayList<SaleOrderBVO>();
    saleOrderList.addAll(saleorderMap.values());
    // ����������
    for (int i = 0; i < addList.size(); i++) {
      saleOrderList.add(addList.get(i));
    }
    return saleOrderList;
  }

  /**
   * �����۶����޶�vo�еı�ͷ���޶��ֶ����ϵ����۶���hvo��
   * 
   * @param revisebill ���۶����޶�vo
   * @param beforeRevise30Bill ���۶����޶�ǰ ���۶���vo
   * @return ��Ϻ�����۶���hvo
   */
  private SaleOrderHVO getHeadAttributes(SaleOrderHistoryVO revisebill,
      SaleOrderVO beforeRevise30Bill) {

    SaleOrderHistoryHVO revisehead = revisebill.getParentVO();
    SaleOrderHVO soHead = beforeRevise30Bill.getParentVO();
    // 1. ѭ����ͷ���޶��ֶ� ��������
    for (String str : EditableAndRewiteItems.HEADEDITABLEITEMKEY) {
      soHead.setAttributeValue(str, revisehead.getAttributeValue(str));
    }
    // 2. ѭ�� ��дӰ���ֶ� ��������
    for (String str : EditableAndRewiteItems.HEADREWRITEMKEY) {
      soHead.setAttributeValue(str, revisehead.getAttributeValue(str));
    }
    // 3. ����hvo״̬
    soHead.setStatus(VOStatus.UPDATED);
    return soHead;
  }

  private void setBVOStatus(SaleOrderHistoryVO bill,
      SaleOrderVO beforeRevise30Bill) {

    // ��ȡ���۶����޶���������
    List<String> reviseBIDs = getReviseBIDs(bill);
    // ��ȡ���۶����޶���������
    List<String> saleorderBIDs = getSaleOrderBIDs(beforeRevise30Bill);

    // 1. saleorderBIDs��reviseBVOIDsһ�£��޸�̬,
    for (int i = 0; i < saleorderBIDs.size(); i++) {
      if (reviseBIDs.contains(saleorderBIDs.get(i))) {
        beforeRevise30Bill.getChildrenVO()[i].setStatus(VOStatus.UPDATED);
      }
    }
    // 2. �޶�bids�գ�����̬��
    for (int i = 0; i < reviseBIDs.size(); i++) {
      if (reviseBIDs.get(i) == null) {
        bill.getChildrenVO()[i].setStatus(VOStatus.NEW);
      }
    }
    // 3. reviseBIDs������saleorderBIDs,ɾ��̬
    for (int i = 0; i < saleorderBIDs.size(); i++) {
      if (!reviseBIDs.contains(saleorderBIDs.get(i))) {
        beforeRevise30Bill.getChildrenVO()[i].setStatus(VOStatus.DELETED);
      }
    }
  }

  private List<String> getSaleOrderBIDs(SaleOrderVO beforeRevise30Bill) {
    List<String> saleorderBVOIDs = new ArrayList<String>();
    SaleOrderBVO[] saleorderBVOs = beforeRevise30Bill.getChildrenVO();
    for (SaleOrderBVO saleorderBVO : saleorderBVOs) {
      String saleorderBVOID = saleorderBVO.getCsaleorderbid();
      saleorderBVOIDs.add(saleorderBVOID);
    }
    return saleorderBVOIDs;
  }

  private List<String> getReviseBIDs(SaleOrderHistoryVO bill) {
    List<String> reviseBVOIDs = new ArrayList<String>();
    SaleOrderHistoryBVO[] reviseBVOs = bill.getChildrenVO();

    for (SaleOrderHistoryBVO reviseBVO : reviseBVOs) {
      String reviseBVOID = reviseBVO.getCsaleorderbid();
      reviseBVOIDs.add(reviseBVOID);

    }
    return reviseBVOIDs;
  }

  private void processNewItem(SaleOrderBVO item) {
    // ��������ۿ������ϣ��������������⣬���ɱ�����
    if (UFBoolean.TRUE.equals(item.getBdiscountflag())
        || UFBoolean.TRUE.equals(item.getBlaborflag())) {
      item.setBbsendendflag(UFBoolean.TRUE);
      item.setBbcostsettleflag(UFBoolean.TRUE);
      item.setBboutendflag(UFBoolean.TRUE);
    }
  }

}
