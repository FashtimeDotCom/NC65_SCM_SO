package nc.bs.so.m30.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m30.ref.po.m20.POm20ServicesUtil;
import nc.itf.so.m30.ref.to.m5a.TOm5AServicesUtil;
import nc.pubitf.scmf.sourcing.sour4so.ISourceSOService;
import nc.pubitf.so.m30.pub.M30TranTypeUtil;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmf.sourcing.entity.SORetVO;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.to.m5a.entity.TransInVO;

/**
 * ����ת������aciton
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>�繫˾����ת��������ת��������
 * <li>����˾����ת��������ת�빺
 * <li>...
 * </ul>
 * 
 * @version 6.0
 * @author zhangcheng
 * @time 2010-6-7 ����06:04:22
 */
public class Push5Aor20Action {

  private List<SaleOrderViewVO> alPo;

  private List<SaleOrderViewVO> alTo;

  private Map<String, SORetVO> sourceMap;

  public void process(SaleOrderVO[] vos) throws BusinessException {

    // ת����view����
    IObjectConvert<SaleOrderVO, SaleOrderViewVO> viewConvert =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    SaleOrderViewVO[] views = viewConvert.convert(vos);

    // ѰԴ
    this.querySource(views);

    // ����VO
    this.filterVOs(views);

    if (this.alTo != null && this.alTo.size() > 0) {
      this.processPush5A();
    }
    if (this.alPo != null && this.alPo.size() > 0) {
      this.processPush20();
    }
  }

  private SaleOrderVO[] convertViewToBill(SaleOrderViewVO[] views) {
    MapList<String, SaleOrderViewVO> viewMapList =
        new MapList<String, SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      viewMapList.put(view.getHead().getPrimaryKey(), view);
    }
    int mapSize = viewMapList.size();
    SaleOrderVO[] bills = new SaleOrderVO[mapSize];
    String[] csaleorderids = viewMapList.keySet().toArray(new String[mapSize]);
    for (int i = 0; i < mapSize; i++) {
      // ��֯����vo
      List<SaleOrderViewVO> viewList = viewMapList.get(csaleorderids[i]);
      int listSize = viewList.size();
      SaleOrderBVO[] bodys = new SaleOrderBVO[listSize];
      SaleOrderViewVO[] viewsTemp =
          viewList.toArray(new SaleOrderViewVO[listSize]);
      for (int j = 0; j < listSize; j++) {
        bodys[j] = viewsTemp[j].getBody();
      }
      // ��װbill
      bills[i] = new SaleOrderVO();
      bills[i].setParentVO(viewList.get(0).getHead());
      bills[i].setChildrenVO(bodys);
    }
    return bills;
  }

  /**
   * ������ת��������/�빺������
   */
  private void filterVOs(SaleOrderViewVO[] views) {
    // ��ȡ�����֯��Ӧ�Ľ��������֯
    String[] sendstocks =
        AggVOUtil.getDistinctFieldArray(views, SaleOrderBVO.CSENDSTOCKORGID,
            String.class);
    Map<String, String> m_st_fin = null;
    m_st_fin = StockOrgPubService.queryFinanceOrgIDByStockOrgID(sendstocks);
    if (m_st_fin == null || m_st_fin.size() == 0) {
      return;
    }

    int directType = DirectType.DIRECTTRAN_NO.getIntValue();
    this.alPo = new ArrayList<SaleOrderViewVO>();
    this.alTo = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      directType = this.getDirecttype(view.getHead());
      String settleorg = view.getBody().getCsettleorgid();
      String finorgBystock = m_st_fin.get(view.getBody().getCsendstockorgid());
      // ��֧�ֿ���֯����
      if (!settleorg.equals(finorgBystock)) {
        continue;
      }
      // ���˵������ۿ���
      boolean laborflag =
          view.getBody().getBlaborflag() == null ? false : view.getBody()
              .getBlaborflag().booleanValue();
      boolean discountflag =
          view.getBody().getBdiscountflag() == null ? false : view.getBody()
              .getBdiscountflag().booleanValue();
      if (laborflag || discountflag) {
        continue;
      }
      // ����ֱ�����ͷ���
      if (DirectType.DIRECTTRAN_TO.getIntValue() == directType) {
        this.alTo.add(view);
      }
      else if (DirectType.DIRECTTRAN_PO.getIntValue() == directType) {
        this.alPo.add(view);
      }
      // ��ֱ�˵����۶���������ѰԴ�㷨ȷ��ת�빺����ת��������
      else if (DirectType.DIRECTTRAN_NO.getIntValue() == directType) {
        String materialid = view.getBody().getCmaterialid();
        String sendstockid = view.getBody().getCsendstockorgid();
        SORetVO sourceVO = this.sourceMap.get(materialid + sendstockid);
        if (sourceVO != null
            && TOBillType.TransIn.getCode().equals(
                sourceVO.getBillType().getCode())) {
          this.alTo.add(view);
        }
        else if (sourceVO != null
            && POBillType.PrayBill.getCode().equals(
                sourceVO.getBillType().getCode())) {
          this.alPo.add(view);
        }
      }
    }
  }

  private int getDirecttype(SaleOrderHVO hvo) {
    String ctrantypeid = hvo.getCtrantypeid();
    int flag = 0;
    Map<String, Integer> directMap =
        M30TranTypeUtil.getInstance().queryDirectType(new String[] {
          ctrantypeid
        });
    flag =
        directMap.get(ctrantypeid) == null ? flag : directMap.get(ctrantypeid)
            .intValue();
    return flag;
  }

  private void processPush20() {
    try {
      SaleOrderViewVO[] sopoview =
          this.alPo.toArray(new SaleOrderViewVO[this.alPo.size()]);
      SaleOrderVO[] sopovos = this.convertViewToBill(sopoview);
      // vo������30--->20
      PraybillVO[] bills =
          (PraybillVO[]) PfServiceScmUtil.executeVOChange(
              SOBillType.Order.getCode(), POBillType.PrayBill.getCode(),
              sopovos);

      // ��ʽ�����빺��
      POm20ServicesUtil.push20ByPo(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void processPush5A() {
    try {
      SaleOrderViewVO[] sotoview =
          this.alTo.toArray(new SaleOrderViewVO[this.alTo.size()]);
      SaleOrderVO[] sotovos = this.convertViewToBill(sotoview);
      // vo������30--->5A
      TransInVO[] bills =
          (TransInVO[]) PfServiceScmUtil
              .executeVOChange(SOBillType.Order.getCode(),
                  TOBillType.TransIn.getCode(), sotovos);

      // ��ʽ���ɵ�������
      TOm5AServicesUtil.push5AByTo(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ѰԴ�㷨Ҫ��
   * 1.����[]���֣�ֻ���Ĳɹ���������������������
   * 2.�ɹ���ҪѰԴ���ɹ���֯�͹�Ӧ��:�ɹ���VO{��֯=�ɹ���֯����Ӧ��=��Ӧ�̡���������=�빺��}
   * 3.����������ҪѰԴ�����������֯:����������VO{��֯=���������֯����Ӧ��=null����������=��������}
   * 4.������VO{��֯=null����Ӧ��=null����������=null}
   * ע������������ѰԴ�㷨�ظ����ɹ���֯����Ӧ�̡����������֯������ѰԴ�㷨�����˴�ֻ���Ĺ������ϡ�
   * 
   * @throws BusinessException
   */
  private void querySource(SaleOrderViewVO[] views) throws BusinessException {
    List<String> materialIDList = new ArrayList<String>();
    List<String> sendStockIDList = new ArrayList<String>();
    for (SaleOrderViewVO view : views) {
      materialIDList.add(view.getBody().getCmaterialid());
      sendStockIDList.add(view.getBody().getCsendstockorgid());
    }
    String[] materialIDs =
        materialIDList.toArray(new String[materialIDList.size()]);
    String[] sendStockIDs =
        sendStockIDList.toArray(new String[sendStockIDList.size()]);
    // ����ѰԴ�ӿڲ�ѯ
    ISourceSOService service =
        NCLocator.getInstance().lookup(ISourceSOService.class);
    SORetVO[] sourceVOs = null;
    try {
      sourceVOs = service.query(materialIDs, sendStockIDs);

      // ��������ֵ�洢�ṹ
      this.sourceMap = new HashMap<String, SORetVO>();
      List<String> orgIDList = new ArrayList<String>();
      if (sourceVOs != null && sourceVOs.length > 0) {
        for (int i = 0; i < sourceVOs.length; i++) {
          // ǰ���Ƿ���ֵ�Ͳ���˳����һһ��Ӧ��
          if (sourceVOs[i] == null) {
            continue;
          }
          this.sourceMap.put(materialIDs[i] + sendStockIDs[i], sourceVOs[i]);
          orgIDList.add(sourceVOs[i].getPk_org());
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
  }
}
