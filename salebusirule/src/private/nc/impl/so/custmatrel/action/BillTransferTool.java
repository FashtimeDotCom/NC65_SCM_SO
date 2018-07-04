package nc.impl.so.custmatrel.action;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.transfer.bill.ServerBillCombinClient;
import nc.vo.pubapp.pattern.model.transfer.bill.ServerBillToClient;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * ǰ̨����ͺ�̨����ʱ�ĵ�����ϢVO�Ĵ��ݴ���
 * 
 * @param <E> ��������
 * @since 6.0
 * @version 2009-5-19 ����01:22:51
 * @author ����
 */
public class BillTransferTool<E extends IBill> {

  /**
   * �ͻ��㴫���ĵ���
   */
  private E[] clientBills;

  /**
   * ���ݿ��д��ڵ�ԭʼ����
   */
  private E[] originBills;
  
  /**
   * ǰ��̨���ݴ��乤�߹��캯��
   * 
   * @param bills ����ʵ��
   */
  public BillTransferTool(E[] bills) {
    // Ϊ�˷�ֹ�����жϵ��µ����ظ����ӣ��˴��������ݵ����������Ѿ�����
    if ((bills[0].getPrimaryKey() == null)
        || (bills[0].getParent().getStatus() == VOStatus.NEW)) {
      this.initInserted(bills);
    }
    else {
      this.initUpdateed(bills);
    }
  }

  /**
   * ��ǰ̨����ʵ�����Ƚϣ���ȡ��Ҫ���ݵ�ǰ̨�ĵ���ʵ�����
   * 
   * @param bills �����ĵ���ʵ��
   * @return ��Ҫ���ݵ�ǰ̨�ĵ���ʵ�����
   */
  @SuppressWarnings({
    "rawtypes", "unchecked"
  })
  public E[] getBillForToClient(E[] bills) {
    ServerBillToClient clientTransfer = new ServerBillToClient();
    E[] vos = (E[]) clientTransfer.construct(this.clientBills, bills);

    return vos;
  }

  /**
   * ��ȡǰ̨����������ʵ�塣
   * 
   * @return ǰ̨����������ʵ��
   */
  @SuppressWarnings("unchecked")
  public E[] getClientFullInfoBill() {
    // ��¡��Ϊ�˷��㷵��ǰ̨ʱ֪����̨�������ָı���ʲô�ֶε�ֵ
    int length = this.clientBills.length;
    E[] bills =
        (E[]) Constructor.construct(this.clientBills[0].getClass(), length);
    for (int i = 0; i < length; i++) {
      bills[i] = (E) this.clientBills[i].clone();
    }
    return bills;
  }

  /**
   * ��ȡ���ݿ��д�ŵ�����ԭʼ����ʵ�塣
   * 
   * @return ���ݿ��д�ŵ�����ԭʼ����ʵ��
   */
  public E[] getOriginBills() {
    return this.originBills;
  }

  @SuppressWarnings("unchecked")
  private void initInserted(E[] bills) {
    int size = bills.length;
    E[] vos = (E[]) Constructor.construct(bills[0].getClass(), size);
    for (int i = 0; i < size; i++) {
      vos[i] = (E) bills[i].clone();
    }
    this.originBills = vos;
    this.clientBills = vos;
  }

  /**
   * ��ȫǰ̨�������ĵ��ݡ�
   * 
   * @param bills
   */
@SuppressWarnings({
    "unchecked", "rawtypes"
  })
	private void initUpdateed(E[] bills) {
		BillConcurrentTool tool = new BillConcurrentTool();
		TimeLog.logStart();
		tool.lockBill(bills);
		TimeLog.info("������ͷ����������"); /* -=notranslate=- */

		TimeLog.logStart();
		String[] ids = new String[bills.length];
		int length = bills.length;
		List<String> bids = getPrimaryKey(bills, ids, length);
		TimeLog.info("��ȡ��������"); /* -=notranslate=- */

		TimeLog.logStart();
		VOQuery query = new VOQuery(bills[0].getParent().getClass());
		CustMatRelHVO[] queryVO = (CustMatRelHVO[]) query.query(ids);
		CustMatRelBVO[] bvos = getCustMatRelBVO(bids);
		// �ۺ�vo
		CustMatRelVO[] custMatRelVOs = buildCustMatRelVO(queryVO, bvos);
		originBills = (E[]) custMatRelVOs;
		TimeLog.info("��ѯԭʼ����VO"); /* -=notranslate=- */

		TimeLog.logStart();
		length = this.originBills.length;
		E[] vos = (E[]) Constructor.construct(bills[0].getClass(), length);
		for (int i = 0; i < length; i++) {
			vos[i] = (E) this.originBills[i].clone();
		}
		ServerBillCombinClient<E> combineClient = new ServerBillCombinClient<E>();
		combineClient.combine(vos, bills);
		this.clientBills = vos;
		TimeLog.info("ǰ̨����VO��������"); /* -=notranslate=- */  

		TimeLog.logStart();
		tool.checkTS(bills, this.originBills);
		TimeLog.info("���ʱ���"); /* -=notranslate=- */
	}

	/**
	 * 
	 * �ֱ��ȡ��ͷVO����VO����vo������ȷ��ͬһ���ݲ��ۺϡ�
	 * 
	 * @param queryVO
	 * @param bvos  
	 * @return
	 */
	private CustMatRelVO[] buildCustMatRelVO(CustMatRelHVO[] queryVO,
			CustMatRelBVO[] bvos) {
		int leng = queryVO.length;
		CustMatRelVO[] custMatRelVOs = new CustMatRelVO[leng];
		for (int i = 0; i < leng; i++) {
			CustMatRelHVO hvo = queryVO[i];
			String hid = hvo.getPrimaryKey();
			CustMatRelVO aggVO = new CustMatRelVO();
			List<CustMatRelBVO> custMatRelBVOs = new ArrayList<CustMatRelBVO>();

			if (bvos == null) {
				aggVO.setParentVO(hvo);
				aggVO.setChildrenVO(null);
				custMatRelVOs[i] = aggVO;
				continue;
			}
			for (CustMatRelBVO bvo : bvos) {
				String bid = bvo.getPk_custmatrel();
				if (PubAppTool.isEqual(hid, bid)) {
					custMatRelBVOs.add(bvo);
				}
			}
			// �ۺ�vo
			aggVO.setParentVO(hvo);
			aggVO.setChildrenVO(custMatRelBVOs
					.toArray(new CustMatRelBVO[custMatRelBVOs.size()]));
			custMatRelVOs[i] = aggVO;
		}
		return custMatRelVOs;
	}

	/**
	 * 
	 * ���ݱ���PK����ñ���VO��
	 * 
	 * @param bids
	 * @return
	 */
	private CustMatRelBVO[] getCustMatRelBVO(List<String> bids) {
		VOQuery queryBVO = new VOQuery(CustMatRelBVO.class);
		CustMatRelBVO[] bvos = null;
		// ���������ı�����û�б��������ݿ��У���û������
		if (bids.size() > 0) {
			bvos = (CustMatRelBVO[]) queryBVO.query(bids
					.toArray(new String[bids.size()]));
		}
		return bvos;
	}

	/**
	 * 
	 * ��ȡ��ñ��壬��ͷVO������
	 * 
	 * @param bills
	 * @param ids
	 * @param length
	 * @return
	 */
	private List<String> getPrimaryKey(E[] bills, String[] ids, int length) {
		List<String> bids = new ArrayList<String>();
		for (int i = 0; i < length; i++) {
			ids[i] = bills[i].getPrimaryKey();
			if (bills[i].getChildren(CustMatRelBVO.class) == null
					|| bills[i].getChildren(SuperVO.class).length == 0) {
				continue;
			}
			// ��ȡBVO����������������������
			for (ISuperVO bvo : bills[i].getChildren(SuperVO.class)) {
				if (bvo.getPrimaryKey() != null) {
					bids.add(bvo.getPrimaryKey());
				}
			}
		}
		return bids;
	}
}
