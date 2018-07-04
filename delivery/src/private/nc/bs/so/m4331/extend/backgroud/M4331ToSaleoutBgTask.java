package nc.bs.so.m4331.extend.backgroud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pf.PfUtilTools;
import nc.itf.uap.pf.IPFBusiAction;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.pubitf.para.SysInitQuery;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutVO;
import nc.vo.lm.erpsbbjjhjk.ErpsbbjjhjkHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * <p>������Ҫʵ�ֹ��ܣ�</p>
 *
 * <li>����������</li>
 * <li></li>
 * <li></li>
 * 
 * @author lyw
 * @version 6.5
 * @time 2017��7��5������4:45:11
 */
public class M4331ToSaleoutBgTask extends M4331ToSaleoutBgPlugin {

	/* ���� Javadoc��
	 * @see nc.bs.so.m4331.extend.backgroud.M4331ToSaleoutBgPlugin#genSaleouttBillInfo(java.util.Map)
	 */
	@Override
	public Map<String, List<String[]>> genSaleouttBillInfo(
			Map<String, ArrayList<ErpsbbjjhjkHVO>> mapvos) throws DAOException, BusinessException {
		// TODO �Զ����ɵķ������
		Map<String, List<String[]>> map_mess = new HashMap<String,List<String[]>>();
		List<String[]> list_error = new ArrayList<String[]>();
		List<String[]> list_sucess = new ArrayList<String[]>();
		SaleOutVO outvo = null;
		if (mapvos != null && mapvos.size() > 0) {
			IPFBusiAction pf = (IPFBusiAction) NCLocator.getInstance().lookup(IPFBusiAction.class.getName());
			//Map<String, SaleOutVO> map_outvo = new HashMap<String, SaleOutVO>();
			for (String key : mapvos.keySet()) {
				ArrayList<ErpsbbjjhjkHVO> lerpvo = mapvos.get(key);
				DeliveryVO deliveryvo = getDeliveryVOData(key);
				if (deliveryvo != null) {
					try {
						outvo = genSaleOutData(deliveryvo,lerpvo,key);
					} catch (Exception e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
						throw new BusinessException(e.toString());
					}
				}
				if (outvo!=null) {
					try {
						pf.processAction("WRITE", "4C", null, outvo, null, null);
						list_sucess.add(new String[] { key,"���յ���:[" +key + "]����ɹ�\n" });
						map_mess.put("success", list_sucess);
					} catch(BusinessException e) {
						list_error.add(new String[] { key, "���յ���:[" +key + "]����ʧ��\n"+e.toString()});
						map_mess.put("error", list_error);
					}
				} else {
					list_error.add(new String[] { key, "δ��ѯ���뱸�����յ�["+key+"]ƥ��ķ��������ݣ������Ӧ�������Ƿ���������\r\n"});
					map_mess.put("error", list_error);
				}
			}
		}
		return map_mess;
	}

	/**
	 * @param deliveryvo
	 * @param lerpvo
	 * @param key
	 * @return
	 */
	private SaleOutVO genSaleOutData(DeliveryVO deliveryvo,ArrayList<ErpsbbjjhjkHVO> lerpvo, String key) {
		// TODO �Զ����ɵķ������
		SaleOutVO outvo = null;
		try {
			outvo = (SaleOutVO) PfUtilTools.runChangeData("4331", "4C", deliveryvo);
			//SaleOutHeadVO hvo = outvo.getHead();
			SaleOutBodyVO[] bvos = outvo.getBodys();
			for (int i = 0; i < lerpvo.size(); i++) {
				//�ⲿ�ɹ���ͬ��
				String erporderno = lerpvo.get(i).getCfirstid();
				//�ⲿ�ɹ���ͬ�����
				String erporderitem = lerpvo.get(i).getCfirstbid().toString();
				//��������
				UFDouble nnum = lerpvo.get(i).getNum();
				for (SaleOutBodyVO bvo:bvos) {
					if (bvo.getVbdef2().equals(erporderno) &&  bvo.getVbdef3().equals(erporderitem) ) {
						//��˰���� nqtorigprice
						UFDouble nqtorigprice = bvo.getNqtorigprice();
						//˰��
						UFDouble ntaxrate = bvo.getNtaxrate();
						//˰��
						UFDouble ntax = (nqtorigprice.multiply(nnum).multiply(ntaxrate.div(new UFDouble(100)))).setScale(2, UFDouble.ROUND_HALF_UP);
						//ʵ������ nassistnum
						bvo.setNassistnum(nnum);
						//ʵ�������� nnum
						bvo.setNnum(nnum);
						//�������� nqtunitnum 
						bvo.setNqtunitnum(nnum);
						//��˰��� norigmny 
						bvo.setNorigmny(nqtorigprice.multiply(nnum).setScale(2, UFDouble.ROUND_HALF_UP));
						//��˰�ϼ� norigtaxmny 
						bvo.setNorigtaxmny(bvo.getNorigmny().add(ntax));
						//������˰��� nmny 
						bvo.setNmny(nqtorigprice.multiply(nnum).setScale(2, UFDouble.ROUND_HALF_UP));
						//���Ҽ�˰�ϼ� ntaxmny 
						bvo.setNtaxmny(bvo.getNmny().add(ntax));
						//˰�� ntax
						bvo.setNtax(ntax);
						//��˰���
						bvo.setNcaltaxmny(nqtorigprice.multiply(nnum).setScale(2, UFDouble.ROUND_HALF_UP));
						//�������յ� ���� vbdef4
						bvo.setVbdef5(key);
					}
				}
			}
			outvo.setChildrenVO(bvos);
		} catch (BusinessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return outvo;
	}

	/**
	 * @param key
	 * @return
	 * @throws BusinessException,DAOException 
	 */
	private DeliveryVO getDeliveryVOData(String key) throws BusinessException,DAOException {
		// TODO �Զ����ɵķ������
		Map<String,String> map_stock = getWarehouse();
		/**
		 * ��ȡ���������ķ�����
		 */
		//��������ͷ
		StringBuffer hsqlstr = new StringBuffer();
		hsqlstr.append("select a.* from so_delivery a ");
		hsqlstr.append("join so_delivery_b b on a.cdeliveryid = b.cdeliveryid ");
		hsqlstr.append("join MSG_ERPSBBJJHJK c on b.vbdef2 = c.cfirstid and b.vbdef3 = to_char(c.Cfirstbid) ");
		hsqlstr.append("where a.fstatusflag = 2 and a.dr = 0 and c.dr = 0 and c.hdef1 = 0 and c.ysd = '");
		hsqlstr.append( key );
		hsqlstr.append("'");
		ArrayList lhvo =  (ArrayList) getBD().executeQuery(hsqlstr.toString(), new BeanListProcessor(DeliveryHVO.class));
		//�������ӱ�
		StringBuffer bsqlstr = new StringBuffer();
		bsqlstr.append("select b.* from so_delivery a ");
		bsqlstr.append("join so_delivery_b b on a.cdeliveryid = b.cdeliveryid ");
		bsqlstr.append("join MSG_ERPSBBJJHJK c on b.vbdef2 = c.cfirstid and b.vbdef3 = to_char(c.Cfirstbid) ");
		bsqlstr.append("where  a.fstatusflag = 2 and a.dr = 0 and c.dr = 0 and c.hdef1 = 0 and c.ysd = '");
		bsqlstr.append( key );
		bsqlstr.append("'");
		ArrayList lbvo =  (ArrayList) getBD().executeQuery(bsqlstr.toString(), new BeanListProcessor(DeliveryBVO .class));
		DeliveryVO aggvo =  new DeliveryVO();
		DeliveryBVO[] bvos = new DeliveryBVO[lbvo.size()];
		if ( lhvo.size() == 0 ) {
			return null;
		} else {
			DeliveryHVO hvo = new DeliveryHVO();
			DeliveryBVO bvo = new DeliveryBVO();
			for (int i = 0 ; i < lhvo.size(); i++) {
				hvo = (DeliveryHVO) lhvo.get(i);
				aggvo.setParentVO(hvo);
				String hid = hvo.getCdeliveryid();
				for (int j = 0; j < lbvo.size(); j++) {
					bvos[j] = (DeliveryBVO) lbvo.get(j);
					//��ȡ���Ų���������ֿ����
					String stockno = SysInitQuery.getParaString(bvos[j].getPk_group(), "IC130");
					bvos[j].setCsendstordocid(map_stock.get(stockno));
				}
				aggvo.setChildrenVO(bvos);
			}
		}
		return aggvo;
	}

	/**
	 * ��ȡ�ֿ���Ϣ
	 * @return Map<String, String>
	 * @throws DAOException 
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> getWarehouse() throws DAOException {
		BaseDAO dao = new BaseDAO();
		// TODO �Զ����ɵķ������
		Map<String, String> map = new HashMap<String, String>();
		String sql = "select * from bd_stordoc where nvl(dr,0)=0";
		List<StordocVO> list = (List<StordocVO>) dao.executeQuery(sql,
				new BeanListProcessor(StordocVO.class));
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				StordocVO vo = list.get(i);
				map.put(vo.getCode(), vo.getPk_stordoc());
			}
		}
		return map;
	}
	
	BaseDAO bd = null;
	public BaseDAO getBD() {
		if (bd == null) {
			bd = new BaseDAO();
		}
		return bd;
	}

}
