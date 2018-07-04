package nc.pubimpl.so.saleinvoice.api;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.so.saleinvoice.MsgAGCG000002;
import nc.vo.so.saleinvoice.MsgAGCG000002Head;
import nc.vo.so.saleinvoice.MsgAGCG000003;
import nc.vo.so.saleinvoice.MsgAGCG000003Head;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.impl.so.m32.action.InsertSaleInvoiceAction;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.itf.uif.pub.IUifService;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.para.SysInitQuery;
import nc.pubitf.so.saleinvoice.api.ISaleinvoiceReceive;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.lm.pgerpxsfp.Agcg000002HVO;
import nc.vo.lm.pgerpxsfpmx.Agcg000003HVO;
import nc.vo.lm.pgjdjjjsxx.AggCgag000003HVO;
import nc.vo.lm.pgjdjjjsxx.Cgag000003HVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

public class SaleinvoiceReceiveImpl implements ISaleinvoiceReceive {

	@Override
	public String receiveMsgCGAG000003() {
		// TODO �Զ����ɵķ������
		String errorMsg = "";
		try{
			IUifService queryService = NCLocator.getInstance().lookup(IUifService.class);
			Cgag000003HVO[] hvos = (Cgag000003HVO[])queryService.queryByCondition(Cgag000003HVO.class, "msgflag=0 and dr=0");
			
			if(hvos!=null){
				Map<String, List<Cgag000003HVO>> mapData = new HashMap<String, List<Cgag000003HVO>>();
				for(int i=0;i<hvos.length;i++){
					//��ȡ���㵥���ţ����ݽ��㵥�Ž��з���
					String balanceid = hvos[i].getBalanceid();
					
					List<Cgag000003HVO> list = mapData.get(balanceid);
					if(list == null){
						List<Cgag000003HVO> newList = new ArrayList<Cgag000003HVO>();
						newList.add(hvos[i]);
						mapData.put(balanceid, newList);
					}else{
						list.add(hvos[i]);
					}
				}
				
				errorMsg = genAggSaleInvoiceVO(mapData);
			}
				
	
		}catch (Exception e) {
			// TODO �Զ����ɵ� catch ��/*////////////
			ExceptionUtils.wrappBusinessException("���ݽ���ʧ��!");
		}
		return errorMsg;
	}

	private String genAggSaleInvoiceVO(Map<String, List<Cgag000003HVO>> mapData){
		String errorMsg = "";
		if(mapData == null){
			return "û�д���������";
		}else if(mapData.size()==0){
			return "û�д���������";
		}
		ISaleInvoiceMaintain iMaintain = NCLocator.getInstance().lookup(
				ISaleInvoiceMaintain.class);
		for(String keyString : mapData.keySet()){
			String vbillcode = getKeyValue(); 
			
			Cgag000003HVO voCgag000003hvo = mapData.get(keyString).get(0);
			SaleInvoiceHVO saleInvoiceHVO = new SaleInvoiceHVO();
			List<Map<String, Object>> cList = this.isHaveVbillcode(vbillcode);
			List<SaleInvoiceBVO> saleinvoiceList = new ArrayList<SaleInvoiceBVO>();
			if (cList!=null&&cList.size()>0) {
				Object obj = cList.get(0).get("csaleinvoiceid");
				String hid = obj + "";
				String []hids = {hid};
				SaleInvoiceVO saleInvoiceVO = new SaleInvoiceVO();
				try {
					SaleInvoiceVO []saleInvoiceVOs = iMaintain.querySaleInvoice(hids);
					saleInvoiceVO = saleInvoiceVOs[0];
				} catch (BusinessException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				saleInvoiceHVO = saleInvoiceVO.getParentVO();
				saleInvoiceHVO.setStatus(VOStatus.UPDATED);
				
				for (int i = 0; i < mapData.get(keyString).size(); i++) {
					SaleInvoiceBVO saleInvoiceBVO = new SaleInvoiceBVO();
					Cgag000003HVO cgag000003hvo1 = mapData.get(keyString).get(i);
					saleInvoiceBVO.setVbdef9(cgag000003hvo1.getBpoid());
					saleInvoiceBVO.setVbdef10(cgag000003hvo1.getBpolineid());
					saleInvoiceBVO.setVbdef11(cgag000003hvo1.getBalanceid());
					saleInvoiceBVO.setVbdef12(cgag000003hvo1.getBalancelineid());
					saleInvoiceBVO.setVbdef13(cgag000003hvo1.getOrderid());
					saleInvoiceBVO.setVbdef14(cgag000003hvo1.getOrderlineid());
					saleInvoiceBVO.setVbdef8(cgag000003hvo1.getDrymeasure());
					saleInvoiceBVO.setVbdef7(cgag000003hvo1.getWetmeasure()+"");
					//�����к�
					Integer it = (i + 1) * 10;
					String crowno = Integer.toString(it);
					saleInvoiceBVO.setCrowno(crowno);

					//��˰���
					saleInvoiceBVO.setNorigmny(cgag000003hvo1.getBalance_price());
					//��˰����
					saleInvoiceBVO.setNqtorigprice(cgag000003hvo1.getBalance_unit_price());
					//��˰����
					UFDouble ufDouble = new UFDouble();
					double rate = ufDouble.add(cgag000003hvo1.getTaxrate()).doubleValue();
					double balanceunitprice = ufDouble.add(cgag000003hvo1.getBalance_unit_price()).doubleValue();
					saleInvoiceBVO.setNqtorigtaxprice(new UFDouble(balanceunitprice*(1+rate)));
					//��˰����
					saleInvoiceBVO.setNqtorigtaxnetprc(new UFDouble(balanceunitprice*(1+rate)));
					//��˰����
					saleInvoiceBVO.setNqtorignetprice(cgag000003hvo1.getBalance_unit_price());
					//���Ҽ�˰�ϼ�
					saleInvoiceBVO.setNtaxmny(cgag000003hvo1.getTaxamount());
					//��������
					saleInvoiceBVO.setNnum(new UFDouble(cgag000003hvo1.getDrymeasure()));
					//����
					saleInvoiceBVO.setNastnum(new UFDouble(cgag000003hvo1.getDrymeasure()));
					//������
					saleInvoiceBVO.setVchangerate("1/1");
					//˰��
					saleInvoiceBVO.setNtaxrate(new UFDouble(cgag000003hvo1.getTaxrate().doubleValue()*100));
					//˰��
					saleInvoiceBVO.setNtax(cgag000003hvo1.getTaxprice());
					//˰��-NC���ǲ�����ֵ˰˰��˰�ʵ���bd_taxcode��Ĭ�ϱ���CN01
					String ctaxcodeid = this.getTaxCodeByCn01("CN01");
					saleInvoiceBVO.setCtaxcodeid(ctaxcodeid);
					//��˰���-NC��Ϊö���Ĭ��ö��ֵΪ1
					saleInvoiceBVO.setFtaxtypeflag(1);
					//��˰�ϼ�
					saleInvoiceBVO.setNorigtaxmny(cgag000003hvo1.getTaxamount());
					saleInvoiceBVO.setStatus(VOStatus.NEW);
					//�����ۿ�
					saleInvoiceBVO.setNdiscountrate(new UFDouble(100));
					//��Ʒ�ۿ�
					saleInvoiceBVO.setNitemdiscountrate(new UFDouble(100));
					//��Ʊ�ۿ�
					saleInvoiceBVO.setNinvoicedisrate(new UFDouble(100));
					
					//�ɹ���ͬ��
					String bpoId = cgag000003hvo1.getBpoid();
					//�ɹ���ͬ�к�
					String bpoLineId = cgag000003hvo1.getBpolineid();
					//ִ�е���
					String orderid = cgag000003hvo1.getOrderid();
					//ִ�е��к�
					String orderlineid = cgag000003hvo1.getOrderlineid();
					
					//�ͻ�˰�š��绰�������˺�
					String customer = "";
					String tel = "";
					String bankid = "";
					String ywlc = "";
					String bwb = "";
					String shgj = "";
					String fhgj = "";
					String sjmy = "";
					String bsgj = "";
					String wl = "";
					String jsje = "";
					String zwsjj = "";
					String zbbwsjj = "";
					String bbjshj = "";
					String bbwsje = "";
					List<Map<String, Object>> saleInfoList = new ArrayList<Map<String, Object>>();
					List<Map<String, Object>> saleInfoList1 = this.getSaleInfo(bpoId, bpoLineId);
					List<Map<String, Object>> saleInfoList2 = this.getSaleInfo(orderid, orderlineid);
					if (saleInfoList1.size()>0) {
						saleInfoList = saleInfoList1;
					}else if(saleInfoList2.size()>0){
						saleInfoList = saleInfoList2;
					}
					else {
						mapData.get(keyString).get(i).setMsginfo("δ�ҵ����۶���");
//						ExceptionUtils.wrappBusinessException("δ�ҵ����۶���");
						continue;
					}
					
					if (saleInfoList.size()>0) {
						saleInvoiceBVO.setCmaterialvid(checkIsNull(saleInfoList.get(0).get("cmaterialvid")));
						saleInvoiceBVO.setCunitid(checkIsNull(saleInfoList.get(0).get("cunitid")));
						//��λ
						saleInvoiceBVO.setCastunitid(checkIsNull(saleInfoList.get(0).get("castunitid")));
						//�ͻ�����
						customer = checkIsNull(saleInfoList.get(0).get("ccustomerid"));
						saleInvoiceHVO.setCinvoicecustid(customer);
//						//ҵ������
//						ywlc = checkIsNull(saleInfoList.get(0).get("cbiztypeid"));
//						saleInvoiceHVO.setCbiztypeid(ywlc);
//						//��λ��
//						bwb = checkIsNull(saleInfoList.get(0).get("ccurrencyid"));
//						saleInvoiceHVO.setCcurrencyid(bwb);
//						//�ջ�����/����
//						shgj = checkIsNull(saleInfoList.get(0).get("crececountryid"));
//						saleInvoiceHVO.setCrececountryid(shgj);
//						//��������/����
//						fhgj = checkIsNull(saleInfoList.get(0).get("csendcountryid"));
//						saleInvoiceHVO.setCsendcountryid(fhgj);
//						//����ó��
//						sjmy = checkIsNull(saleInfoList.get(0).get("btriatradeflag"));
//						saleInvoiceHVO.setBtriatradeflag(UFBoolean.valueOf(sjmy));
//						//��˰����
//						bsgj = checkIsNull(saleInfoList.get(0).get("ctaxcountryid"));
//						saleInvoiceHVO.setCtaxcountryid(bsgj);
						//����
						wl = checkIsNull(saleInfoList.get(0).get("cmaterialid"));
						saleInvoiceBVO.setCmaterialid(wl);
						//��˰���
						jsje = checkIsNull(saleInfoList.get(0).get("ncaltaxmny"));
						saleInvoiceBVO.setNcaltaxmny(new UFDouble(jsje));
						//����˰����
						zwsjj = checkIsNull(saleInfoList.get(0).get("norignetprice"));
						saleInvoiceBVO.setNorignetprice(new UFDouble(zwsjj));
						//��������˰����
						zbbwsjj = checkIsNull(saleInfoList.get(0).get("nnetprice"));
						saleInvoiceBVO.setNnetprice(new UFDouble(zbbwsjj));
						//���Ҽ�˰�ϼ�
						bbjshj = checkIsNull(saleInfoList.get(0).get("ntaxmny"));
//						saleInvoiceBVO.setNtaxmny(new UFDouble(bbjshj));
						//������˰���
						bbwsje = checkIsNull(saleInfoList.get(0).get("nmny"));
						saleInvoiceBVO.setNmny(new UFDouble(bbwsje));
					}
					
					//�绰
					//�����˺�
//					bankid = checkIsNull(saleInfoList.get(0).get(""));
					//Դͷ���ݺš�Դͷ�����к�
					List<Map<String, Object>> vfirstInfoList = this.getVfirstInfo(bpoId, bpoLineId);
					if (vfirstInfoList.size()>0) {
						saleInvoiceBVO.setVfirstcode(vfirstInfoList.get(0).get("vfirstcode")+"");
						saleInvoiceBVO.setVfirstrowno(vfirstInfoList.get(0).get("vfirstrowno")+"");
					}
					
					//��Դ���ݺš���Դ�����к�
					List<Map<String, Object>> vSrcInfoList = this.getVSrcInfo(bpoId, bpoLineId);
					if (vSrcInfoList.size()>0) {
						saleInvoiceBVO.setVfirstcode(vSrcInfoList.get(0).get("vsrccode")+"");
						saleInvoiceBVO.setVfirstrowno(vSrcInfoList.get(0).get("vsrcrowno")+"");
					}
					saleinvoiceList.add(saleInvoiceBVO);
				}
			}
			else {
				//��Ʊ��-ϵͳ�Զ�����
				saleInvoiceHVO.setVbillcode(getKeyValue());
				String gorg = "GLOBLE00000000000000";
				String pk_orgs="";
				String pk_group="";
				String pk_org_v="";
				try {
					pk_orgs = SysInitQuery.getParaString(gorg, "PGJSD002");
					pk_group = SysInitQuery.getParaString(gorg, "PGJSD001");
					pk_org_v = SysInitQuery.getParaString(gorg, "PGJSD003");
				} catch (BusinessException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				if ("".equals(pk_orgs)) {
					return "û���ҵ���֯��Ϣ";
				}else {
					//��֯-��ó��֦����˾
					saleInvoiceHVO.setPk_org(pk_orgs);
				}
				if ("".equals(pk_group)) {
					return "û���ҵ�������Ϣ";
				}else {
					//����
					saleInvoiceHVO.setPk_group(pk_group);
				}
				if ("".equals(pk_org_v)) {
					return "û���ҵ���֯�汾��Ϣ";
				}else {
					//��Ʊ��֯�汾-��ó��֦����˾
					saleInvoiceHVO.setPk_org_v(pk_org_v);
				}
				
				//��Ʊ���ͱ���-��ֵ˰��Ʊ
				saleInvoiceHVO.setVtrantypecode("32-01");
				//��Ʊ����
				saleInvoiceHVO.setCtrantypeid("0001N6100000000023MO");
				//��Ʊ����-���յ�������
				saleInvoiceHVO.setDbilldate(new UFDate(voCgag000003hvo.getCreationtime()+""));
				//����-CNY
				saleInvoiceHVO.setCorigcurrencyid("1002Z0100000000001K1");
				//��������-��������
				saleInvoiceHVO.setFbuysellflag(1);
				//�۱�����
				saleInvoiceHVO.setNexchangerate(new UFDouble(1));
				//�Գ��ǣ�NC���ֶ�Ϊö�٣�ö��ȡֵ0=����
				saleInvoiceHVO.setFopposeflag(0);
				//����״̬  Ĭ��1-����̬
				saleInvoiceHVO.setFstatusflag(1);
				//�Ƶ���
				saleInvoiceHVO.setBillmaker(AppContext.getInstance().getPkUser());
				//�Ƶ�ʱ��
				saleInvoiceHVO.setCreationtime(AppContext.getInstance().getServerTime());
				//��˰�ϼ�-ͬһ���㵥��˰���ϼ�
				
				//��˰���ϼ�
				UFDouble taxAmount = UFDouble.ZERO_DBL;
				for(Cgag000003HVO tempvo : mapData.get(keyString)){
					taxAmount = taxAmount.add(tempvo.getTaxamount());
				}
				saleInvoiceHVO.setNtotalorigmny(new UFDouble(taxAmount));
				//�ͻ�����
				saleInvoiceHVO.setCinvoicecustid(voCgag000003hvo.getCompanyname());
				saleInvoiceHVO.setStatus(VOStatus.NEW);
				for (int i = 0; i < mapData.get(keyString).size(); i++) {
					SaleInvoiceBVO saleInvoiceBVO = new SaleInvoiceBVO();
					Cgag000003HVO cgag000003hvo1 = mapData.get(keyString).get(i);
					saleInvoiceBVO.setVbdef9(cgag000003hvo1.getBpoid());
					saleInvoiceBVO.setVbdef10(cgag000003hvo1.getBpolineid());
					saleInvoiceBVO.setVbdef11(cgag000003hvo1.getBalanceid());
					saleInvoiceBVO.setVbdef12(cgag000003hvo1.getBalancelineid());
					saleInvoiceBVO.setVbdef13(cgag000003hvo1.getOrderid());
					saleInvoiceBVO.setVbdef14(cgag000003hvo1.getOrderlineid());
					saleInvoiceBVO.setVbdef8(cgag000003hvo1.getDrymeasure());
					saleInvoiceBVO.setVbdef7(cgag000003hvo1.getWetmeasure()+"");
					//�����к�
					Integer it = (i + 1) * 10;
					String crowno = Integer.toString(it);
					saleInvoiceBVO.setCrowno(crowno);

					//��˰���
					saleInvoiceBVO.setNorigmny(cgag000003hvo1.getBalance_price());
					//��˰����
					saleInvoiceBVO.setNqtorigprice(cgag000003hvo1.getBalance_unit_price());
					//��˰����
					UFDouble ufDouble = new UFDouble();
					double rate = ufDouble.add(cgag000003hvo1.getTaxrate()).doubleValue();
					double balanceunitprice = ufDouble.add(cgag000003hvo1.getBalance_unit_price()).doubleValue();
					saleInvoiceBVO.setNqtorigtaxprice(new UFDouble(balanceunitprice*(1+rate)));
					//��˰����
					saleInvoiceBVO.setNqtorigtaxnetprc(new UFDouble(balanceunitprice*(1+rate)));
					//��˰����
					saleInvoiceBVO.setNqtorignetprice(cgag000003hvo1.getBalance_unit_price());
					//���Ҽ�˰�ϼ�
					saleInvoiceBVO.setNtaxmny(cgag000003hvo1.getTaxamount());
					//��������
					saleInvoiceBVO.setNnum(new UFDouble(cgag000003hvo1.getDrymeasure()));
					//����
					saleInvoiceBVO.setNastnum(new UFDouble(cgag000003hvo1.getDrymeasure()));
					//������
					saleInvoiceBVO.setVchangerate("1/1");
					//˰��
					saleInvoiceBVO.setNtaxrate(new UFDouble(cgag000003hvo1.getTaxrate().doubleValue()*100));
					//˰��
					saleInvoiceBVO.setNtax(cgag000003hvo1.getTaxprice());
					//˰��-NC���ǲ�����ֵ˰˰��˰�ʵ���bd_taxcode��Ĭ�ϱ���CN01
					String ctaxcodeid = this.getTaxCodeByCn01("CN01");
					saleInvoiceBVO.setCtaxcodeid(ctaxcodeid);
					//��˰���-NC��Ϊö���Ĭ��ö��ֵΪ1
					saleInvoiceBVO.setFtaxtypeflag(1);
					//��˰�ϼ�
					saleInvoiceBVO.setNorigtaxmny(cgag000003hvo1.getTaxamount());
					saleInvoiceBVO.setStatus(VOStatus.NEW);
					//�����ۿ�
					saleInvoiceBVO.setNdiscountrate(new UFDouble(100));
					//��Ʒ�ۿ�
					saleInvoiceBVO.setNitemdiscountrate(new UFDouble(100));
					//��Ʊ�ۿ�
					saleInvoiceBVO.setNinvoicedisrate(new UFDouble(100));
					
					//�ɹ���ͬ��
					String bpoId = cgag000003hvo1.getBpoid();
					//�ɹ���ͬ�к�
					String bpoLineId = cgag000003hvo1.getBpolineid();
					//ִ�е���
					String orderid = cgag000003hvo1.getOrderid();
					//ִ�е��к�
					String orderlineid = cgag000003hvo1.getOrderlineid();
					
					//�ͻ�˰�š��绰�������˺�
					String customer = "";
					String tel = "";
					String bankid = "";
					String ywlc = "";
					String bwb = "";
					String shgj = "";
					String fhgj = "";
					String sjmy = "";
					String bsgj = "";
					String wl = "";
					String jsje = "";
					String zwsjj = "";
					String zbbwsjj = "";
					String bbjshj = "";
					String bbwsje = "";
					List<Map<String, Object>> saleInfoList = new ArrayList<Map<String, Object>>();
					List<Map<String, Object>> saleInfoList1 = this.getSaleInfo(bpoId, bpoLineId);
					List<Map<String, Object>> saleInfoList2 = this.getSaleInfo(orderid, orderlineid);
					if (saleInfoList1.size()>0) {
						saleInfoList = saleInfoList1;
					}else if(saleInfoList2.size()>0){
						saleInfoList = saleInfoList2;
					}
					else {
						mapData.get(keyString).get(i).setMsginfo("δ�ҵ����۶���");
//						ExceptionUtils.wrappBusinessException("δ�ҵ����۶���");
						continue;
					}
					
					if (saleInfoList.size()>0) {
						saleInvoiceBVO.setCmaterialvid(checkIsNull(saleInfoList.get(0).get("cmaterialvid")));
						saleInvoiceBVO.setCunitid(checkIsNull(saleInfoList.get(0).get("cunitid")));
						//��λ
						saleInvoiceBVO.setCastunitid(checkIsNull(saleInfoList.get(0).get("castunitid")));
						//�ͻ�����
						customer = checkIsNull(saleInfoList.get(0).get("ccustomerid"));
						saleInvoiceHVO.setCinvoicecustid(customer);
						//ҵ������
						ywlc = checkIsNull(saleInfoList.get(0).get("cbiztypeid"));
						saleInvoiceHVO.setCbiztypeid(ywlc);
						//��λ��
						bwb = checkIsNull(saleInfoList.get(0).get("ccurrencyid"));
						saleInvoiceHVO.setCcurrencyid(bwb);
						//�ջ�����/����
						shgj = checkIsNull(saleInfoList.get(0).get("crececountryid"));
						saleInvoiceHVO.setCrececountryid(shgj);
						//��������/����
						fhgj = checkIsNull(saleInfoList.get(0).get("csendcountryid"));
						saleInvoiceHVO.setCsendcountryid(fhgj);
						//����ó��
						sjmy = checkIsNull(saleInfoList.get(0).get("btriatradeflag"));
						saleInvoiceHVO.setBtriatradeflag(UFBoolean.valueOf(sjmy));
						//��˰����
						bsgj = checkIsNull(saleInfoList.get(0).get("ctaxcountryid"));
						saleInvoiceHVO.setCtaxcountryid(bsgj);
						//����
						wl = checkIsNull(saleInfoList.get(0).get("cmaterialid"));
						saleInvoiceBVO.setCmaterialid(wl);
						//��˰���
						jsje = checkIsNull(saleInfoList.get(0).get("ncaltaxmny"));
						saleInvoiceBVO.setNcaltaxmny(new UFDouble(jsje));
						//����˰����
						zwsjj = checkIsNull(saleInfoList.get(0).get("norignetprice"));
						saleInvoiceBVO.setNorignetprice(new UFDouble(zwsjj));
						//��������˰����
						zbbwsjj = checkIsNull(saleInfoList.get(0).get("nnetprice"));
						saleInvoiceBVO.setNnetprice(new UFDouble(zbbwsjj));
						//���Ҽ�˰�ϼ�
						bbjshj = checkIsNull(saleInfoList.get(0).get("ntaxmny"));
//						saleInvoiceBVO.setNtaxmny(new UFDouble(bbjshj));
						//������˰���
						bbwsje = checkIsNull(saleInfoList.get(0).get("nmny"));
						saleInvoiceBVO.setNmny(new UFDouble(bbwsje));
					}
					
					//�绰
					//�����˺�
//					bankid = checkIsNull(saleInfoList.get(0).get(""));
					//Դͷ���ݺš�Դͷ�����к�
					List<Map<String, Object>> vfirstInfoList = this.getVfirstInfo(bpoId, bpoLineId);
					if (vfirstInfoList.size()>0) {
						saleInvoiceBVO.setVfirstcode(vfirstInfoList.get(0).get("vfirstcode")+"");
						saleInvoiceBVO.setVfirstrowno(vfirstInfoList.get(0).get("vfirstrowno")+"");
					}
					
					//��Դ���ݺš���Դ�����к�
					List<Map<String, Object>> vSrcInfoList = this.getVSrcInfo(bpoId, bpoLineId);
					if (vSrcInfoList.size()>0) {
						saleInvoiceBVO.setVfirstcode(vSrcInfoList.get(0).get("vsrccode")+"");
						saleInvoiceBVO.setVfirstrowno(vSrcInfoList.get(0).get("vsrcrowno")+"");
					}
					saleinvoiceList.add(saleInvoiceBVO);
				}
			}
			
			
			
			SaleInvoiceVO saleInvoiceVO = new SaleInvoiceVO();
			saleInvoiceVO.setParent(saleInvoiceHVO);
			SaleInvoiceBVO []saleInvoiceBVOs = saleinvoiceList.toArray(new SaleInvoiceBVO[0]);
			saleInvoiceVO.setChildrenVO((CircularlyAccessibleValueObject[])saleInvoiceBVOs);
			
//			BaseDAO baseDAO = new BaseDAO();
			NCObject objs = NCObject.newInstance(saleInvoiceVO);
			InsertSaleInvoiceAction insertSaleInvoiceAction = new InsertSaleInvoiceAction();
			SaleInvoiceVO[] newvos = new SaleInvoiceVO[]{saleInvoiceVO};
			
//			insertSaleInvoiceAction.insert(newvos);
			try {
				insertSaleInvoiceAction.insert(newvos);
//				MDPersistenceService.lookupPersistenceService().saveBill(objs);

//				baseDAO.insertVO(saleInvoiceBVO);
				//��д�м��
				for (int i = 0; i < mapData.get(keyString).size(); i++) {
					this.writeBack(mapData.get(keyString).get(i));
				}
			} catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
//				errorMsg = "�м���дʧ��";
				errorMsg = e.toString();
			}
		}
		return errorMsg;
	}
	
	public void dsinsert(AggCgag000003HVO aggCgag000003HVO) {
		Cgag000003HVO cgag000003hvo = aggCgag000003HVO.getParentVO();
		SaleInvoiceHVO saleInvoiceHVO = new SaleInvoiceHVO();
		//��Ʊ��-ϵͳ�Զ�����
		saleInvoiceHVO.setVbillcode(getKeyValue());
		//����
		saleInvoiceHVO.setPk_group("0001N610000000000IT0");
		//��֯-��ó��֦����˾
		saleInvoiceHVO.setPk_org("0001N610000000002JW4");
		//��Ʊ��֯�汾-��ó��֦����˾
		saleInvoiceHVO.setPk_org_v("0001N610000000002JW3");
		//��Ʊ���ͱ���-��ֵ˰��Ʊ
		saleInvoiceHVO.setVtrantypecode("32-01");
		//��Ʊ����
		saleInvoiceHVO.setCtrantypeid("0001N6100000000023MO");
		//��Ʊ����-���յ�������
		saleInvoiceHVO.setDbilldate(new UFDate(cgag000003hvo.getCreationtime()+""));
		//����-CNY
		saleInvoiceHVO.setCorigcurrencyid("1002Z0100000000001K1");
		
		//�Գ��ǣ�NC���ֶ�Ϊö�٣�ö��ȡֵ0=����
		saleInvoiceHVO.setFopposeflag(0);
		//����״̬  Ĭ��1-����̬
		saleInvoiceHVO.setFstatusflag(1);
		//�Ƶ���
		saleInvoiceHVO.setBillmaker(AppContext.getInstance().getPkUser());
		//�Ƶ�ʱ��
		saleInvoiceHVO.setCreationtime(AppContext.getInstance().getServerTime());
		//��˰�ϼ�-ͬһ���㵥��˰���ϼ�
		//���㵥����
		String balanceId = cgag000003hvo.getBalanceid();
		List<Map<String, String>> cgag000003List = this.getbalanceIdList(balanceId);
		//��˰���ϼ�
		double taxAmount = 0;
		if (cgag000003List!=null) {
			for (int i = 0; i < cgag000003List.size(); i++) {
				double jsje = Double.parseDouble(cgag000003List.get(i).get("jsje")) ;
				taxAmount += jsje;
			}
		}
		saleInvoiceHVO.setNtotalorigmny(new UFDouble(taxAmount));
		//�ͻ�����
		saleInvoiceHVO.setCinvoicecustid(cgag000003hvo.getCompanyname());
		//Դͷ���ݺ�
		NCObject[] ncObjects={};
		List<Cgag000003HVO> cgagbovList = new ArrayList<Cgag000003HVO>();
		try {
			ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond
					(Cgag000003HVO.class,"BALANCEID='"+balanceId+"'",false);
		} catch (MetaDataException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		
		if(ncObjects!=null){
			for(int i=0;i<ncObjects.length;i++){
				AggCgag000003HVO aggvo =new AggCgag000003HVO();
				aggvo=(AggCgag000003HVO)ncObjects[i].getContainmentObject();
				Cgag000003HVO cgag000003hvo2 = (Cgag000003HVO)aggvo.getParent();
				cgagbovList.add(cgag000003hvo2);
				}
		}
		
		List<SaleInvoiceBVO> saleinvoiceList = new ArrayList<SaleInvoiceBVO>();
		
		for (int i = 0; i < cgagbovList.size(); i++) {
			SaleInvoiceBVO saleInvoiceBVO = new SaleInvoiceBVO();
			Cgag000003HVO cgag000003hvo1 = cgagbovList.get(i);
			saleInvoiceBVO.setVbdef9(cgag000003hvo1.getBpoid());
			saleInvoiceBVO.setVbdef10(cgag000003hvo1.getBpolineid());
			saleInvoiceBVO.setVbdef11(cgag000003hvo1.getBalanceid());
			saleInvoiceBVO.setVbdef12(cgag000003hvo1.getBalancelineid());
			saleInvoiceBVO.setVbdef13(cgag000003hvo1.getOrderid());
			saleInvoiceBVO.setVbdef14(cgag000003hvo1.getOrderlineid());
			saleInvoiceBVO.setVbdef8(cgag000003hvo1.getDrymeasure());
			saleInvoiceBVO.setVbdef7(cgag000003hvo1.getWetmeasure()+"");
			//�����к�
			Integer it = (i + 1) * 10;
			String crowno = Integer.toString(it);
			saleInvoiceBVO.setCrowno(crowno);

			//��˰���
			saleInvoiceBVO.setNorigmny(cgag000003hvo1.getBalance_price());
			//��˰����
			saleInvoiceBVO.setNqtorigprice(cgag000003hvo1.getBalance_unit_price());
			//��˰����
			UFDouble ufDouble = new UFDouble();
			double rate = ufDouble.add(cgag000003hvo1.getTaxrate()).doubleValue();
			double balanceunitprice = ufDouble.add(cgag000003hvo1.getBalance_unit_price()).doubleValue();
			saleInvoiceBVO.setNqtorigtaxprice(new UFDouble(balanceunitprice*(1+rate)));
			//��������
			saleInvoiceBVO.setNnum(new UFDouble(cgag000003hvo1.getDrymeasure()));
			//˰��
			saleInvoiceBVO.setNtaxrate(cgag000003hvo1.getTaxrate());
			//˰��
			saleInvoiceBVO.setNtax(cgag000003hvo1.getTaxprice());
			//˰��-NC���ǲ�����ֵ˰˰��˰�ʵ���bd_taxcode��Ĭ�ϱ���CN01
			String ctaxcodeid = this.getTaxCodeByCn01("CN01");
			saleInvoiceBVO.setCtaxcodeid(ctaxcodeid);
			//��˰���-NC��Ϊö���Ĭ��ö��ֵΪ1
			saleInvoiceBVO.setFtaxtypeflag(1);
			//��˰�ϼ�
			saleInvoiceBVO.setNorigtaxmny(cgag000003hvo1.getTaxamount());
			saleInvoiceBVO.setStatus(VOStatus.NEW);
			//�ɹ���ͬ��
			String bpoId = cgag000003hvo1.getBpoid();
			//�ɹ���ͬ�к�
			String bpoLineId = cgag000003hvo1.getBpolineid();
			//�ͻ�˰�š��绰�������˺�
			String customer = "";
			String tel = "";
			String bankid = "";		
			List<Map<String, Object>> saleInfoList = this.getSaleInfo(bpoId, bpoLineId);
			if (saleInfoList.size()>0) {
				saleInvoiceBVO.setCmaterialvid(saleInfoList.get(0).get("cmaterialvid")+"");
				saleInvoiceBVO.setCunitid(saleInfoList.get(0).get("cunit")+"");
				
			}
			//�ͻ�����
			customer = checkIsNull(saleInfoList.get(0).get("ccustomerid"));
			saleInvoiceHVO.setCinvoicecustid(customer);
			saleInvoiceHVO.setStatus(VOStatus.NEW);
			//�绰
			//�����˺�
			bankid = checkIsNull(saleInfoList.get(0).get(""));
			//Դͷ���ݺš�Դͷ�����к�
			List<Map<String, Object>> vfirstInfoList = this.getVfirstInfo(bpoId, bpoLineId);
			if (vfirstInfoList.size()>0) {
				saleInvoiceBVO.setVfirstcode(vfirstInfoList.get(0).get("vfirstcode")+"");
				saleInvoiceBVO.setVfirstrowno(vfirstInfoList.get(0).get("vfirstrowno")+"");
			}
			
			//��Դ���ݺš���Դ�����к�
			List<Map<String, Object>> vSrcInfoList = this.getVSrcInfo(bpoId, bpoLineId);
			if (vSrcInfoList.size()>0) {
				saleInvoiceBVO.setVfirstcode(vSrcInfoList.get(0).get("vsrccode")+"");
				saleInvoiceBVO.setVfirstrowno(vSrcInfoList.get(0).get("vsrcrowno")+"");
			}
			saleinvoiceList.add(saleInvoiceBVO);
		}
		
		
		SaleInvoiceVO saleInvoiceVO = new SaleInvoiceVO();
		saleInvoiceVO.setParent(saleInvoiceHVO);
		SaleInvoiceBVO []saleInvoiceBVOs = saleinvoiceList.toArray(new SaleInvoiceBVO[0]);
		saleInvoiceVO.setChildrenVO((CircularlyAccessibleValueObject[])saleInvoiceBVOs);
		
//		BaseDAO baseDAO = new BaseDAO();
		NCObject objs = NCObject.newInstance(saleInvoiceVO);

		try {
			MDPersistenceService.lookupPersistenceService().saveBill(objs);

//			baseDAO.insertVO(saleInvoiceBVO);
			//��д�м��
			this.writeBack(aggCgag000003HVO);
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}


		@SuppressWarnings("unchecked")
		@Override
		public List<Map<String, Object>> getSaleInfo(String bpoId,String bpoLineId) {
			// TODO �Զ����ɵķ������
			BaseDAO baseDao = new BaseDAO();
			String querySql = "SELECT SAB.cmaterialvid,SAB.cunitid,SA.ccustomerid,bc.TAXPAYERID,bc.TEL1,BC.NAME,sab.CASTUNITID,SA.cbiztypeid,SAB.ccurrencyid,sab.crececountryid,sab.csendcountryid,sab.btriatradeflag,sab.ctaxcountryid,SAB.cmaterialid,sab.ncaltaxmny,sab.norignetprice,sab.nnetprice,SAB.ntaxmny,sab.nmny from so_saleorder_b sab,SO_SALEORDER sa,BD_CUSTOMER bc WHERE SAB.CSALEORDERID=SA.CSALEORDERID and SA.CCUSTOMERID=BC.PK_CUSTOMER and sab.dr=0 and vbdef2='"+bpoId+"' and vbdef3='"+bpoLineId+"'";
//			String querySql = "SELECT cmaterialvid,cunitid,csaleorderbid from so_saleorder_b where vbdef2='"+bpoId+"' and vbdef3='"+bpoLineId+"'";
			List<Map<String, Object>> list = null;
			try {
				list = (List<Map<String, Object>>)baseDao.executeQuery(querySql, new ResultSetProcessor() {
					
					@Override
					public List<Map<String, Object>> handleResultSet(ResultSet rs) throws SQLException {
						// TODO �Զ����ɵķ������
						List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
						Map<String, Object> m = new HashMap<String, Object>();
						while (rs.next()) {
							//���ϱ���
							m.put("cmaterialvid", rs.getString(1));
							//������λ
							m.put("cunitid", rs.getString(2));
							//�ͻ����
							m.put("ccustomerid", rs.getString(3));
							//�ͻ�˰��
							m.put("taxpayerid", rs.getString(4));
							//�ͻ��绰
							m.put("tel1", rs.getString(5));
							//�ͻ�����
							m.put("name", rs.getString(6));
							//��λ
							m.put("castunitid", rs.getString(7));
							//ҵ������
							m.put("cbiztypeid", rs.getString(8));
							//��λ��
							m.put("ccurrencyid", rs.getString(9));
							//�ջ�����/����
							m.put("crececountryid", rs.getString(10));
							//��������/����
							m.put("csendcountryid", rs.getString(11));
							//����ó��
							m.put("btriatradeflag", rs.getString(12));
							//��˰����
							m.put("ctaxcountryid", rs.getString(13));
							//����
							m.put("cmaterialid", rs.getString(14));
							//��˰���
							m.put("ncaltaxmny", rs.getString(15));
							//����˰����
							m.put("norignetprice", rs.getString(16));
							//��������˰����
							m.put("nnetprice", rs.getString(17));
							//���Ҽ�˰�ϼ�
							m.put("ntaxmny", rs.getString(18));
							//������˰���
							m.put("nmny", rs.getString(19));
							list1.add(m);
							
							
						}
						return list1;
					}
				});
			} catch (DAOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			return list;
		}

		@Override
		public boolean sendMsgAGCG000003(SaleInvoiceVO saleInvoiceVO) {
			// TODO �Զ����ɵķ������
			//�������
			List<MsgAGCG000002> agcg000002s = new ArrayList<MsgAGCG000002>();
			//�ӱ����
			List<MsgAGCG000003> agcg000003s = new ArrayList<MsgAGCG000003>();
			//
			SaleInvoiceHVO saleInvoiceHVO = saleInvoiceVO.getParentVO();
			SaleInvoiceBVO []saleInvoiceBVOs = saleInvoiceVO.getChildrenVO();
			
			// �ӿ����ݷ����Ƿ�ɹ���ʶ��trueΪ���ɹ���falseΪ����һ��ʧ��
			boolean flag = true;
			
			//���۷�Ʊ����
			MsgAGCG000002 msgAGCG000002 = new MsgAGCG000002();
			msgAGCG000002.setInvoiceId(saleInvoiceHVO.getVbillcode());
			//��Ʊ����
			msgAGCG000002.setInvoiceCode(saleInvoiceHVO.getVbillcode());
			if (saleInvoiceHVO.getVgoldtaxcode()==null) {
				msgAGCG000002.setInvoiceNum(saleInvoiceHVO.getVdef2());
			}else {
				msgAGCG000002.setInvoiceNum(saleInvoiceHVO.getVgoldtaxcode());
			}
			msgAGCG000002.setInvoiceDate(saleInvoiceHVO.getDbilldate()+"");
			//˰��-ȡ�ӱ��һ��
			UFDouble ufDouble = new UFDouble();
			double ntaxrate = ufDouble.add(checkDoubleIsNull(saleInvoiceBVOs[0].getNtaxrate())).doubleValue();
			msgAGCG000002.setTaxRate(BigDecimal.valueOf(ntaxrate));
			
	//		��˰���-ȡ����ϼ�
			double norigmnySum = 0;
			for (int i = 0; i < saleInvoiceBVOs.length; i++) {
				SaleInvoiceBVO saleInvoiceBVO = saleInvoiceBVOs[i];
				double norigmny = ufDouble.add(saleInvoiceBVO.getNorigmny() == null?UFDouble.ZERO_DBL:saleInvoiceBVO.getNorigmny()).doubleValue();
				norigmnySum+=norigmny;
			}
			msgAGCG000002.setTotNetAmt(BigDecimal.valueOf(norigmnySum));
	//		��˰�ϼ�
			double ntotalorigmny = ufDouble.add(checkDoubleIsNull(saleInvoiceHVO.getNtotalorigmny())).doubleValue();
			msgAGCG000002.setTotAmt(BigDecimal.valueOf(ntotalorigmny));
			//˰��
			double ntaxSum = 0;
			for (int i = 0; i < saleInvoiceBVOs.length; i++) {
				SaleInvoiceBVO saleInvoiceBVO = saleInvoiceBVOs[i];
				double ntax = ufDouble.add(saleInvoiceBVO.getNtax()).doubleValue();
				ntaxSum+=ntax;
			}
			DecimalFormat df = new DecimalFormat("#.##");
			String ntaxString = df.format(ntaxSum);
			UFDouble ntaxDouble = new UFDouble(ntaxString);
			ntaxSum = ufDouble.add(ntaxDouble).doubleValue();
			msgAGCG000002.setTotTaxAmt(BigDecimal.valueOf(ntaxSum));
			//���㵥����
			msgAGCG000002.setBalanceId(saleInvoiceBVOs[0].getVbdef11());
			
			//�ɹ���ͬ��
			msgAGCG000002.setBpoId(saleInvoiceBVOs[0].getVbdef9());
			//ִ�е���
			msgAGCG000002.setOrderId(saleInvoiceBVOs[0].getVbdef13());
			agcg000002s.add(msgAGCG000002);
			MsgAGCG000002Head msgAGCG000002Head = new MsgAGCG000002Head();
			msgAGCG000002Head.setMsgBody(agcg000002s);
			RestTemplate rt = new RestTemplate();
			String headFlag = rt.postForObject(this.getRestURL("Agcg000002"), msgAGCG000002Head, String.class);
			
//			String headFlag = rt.postForObject("http://192.168.2.233:8084/mq/sendMsgAGCG000002", msgAGCG000002Head, String.class);
//			String headFlag = rt.postForObject("http://192.1.103.156:8084/conn-mq/mq/sendMsgAGCG000002", msgAGCG000002Head, String.class);
			//����
			for (int i = 0; i < saleInvoiceBVOs.length; i++) {
				//���۷�Ʊ�ӱ�
				MsgAGCG000003 msgAGCG000003 = new MsgAGCG000003();
				SaleInvoiceBVO saleInvoiceBVO = saleInvoiceBVOs[i];
				//��Ʊ��(ȡ����)
				msgAGCG000003.setInvoiceId(saleInvoiceHVO.getVbillcode());
				//��ƱƱ�����(ȡ����)
				msgAGCG000003.setInvoiceNum(nullToString(saleInvoiceHVO.getVgoldtaxcode()));
				//��Ʊ��ϸ�ţ���ϸ��Ϣ������
				msgAGCG000003.setInvoiceLineId(saleInvoiceBVO.getCsaleinvoicebid());
				//��Ʊ����
				msgAGCG000003.setInvoiceCode(saleInvoiceHVO.getVbillcode());
				//�ɹ���ͬ�к�
				msgAGCG000003.setBpoLineId(saleInvoiceBVO.getVbdef10());
				//ִ�е��к�
				msgAGCG000003.setOrderLineId(saleInvoiceBVO.getVbdef14());
				//���㵥�к�
				msgAGCG000003.setBalanceLineId(saleInvoiceBVO.getVbdef12());
				//���ϱ���
				String cmaterialvid = saleInvoiceBVO.getCmaterialvid();
				msgAGCG000003.setItemId(cmaterialvid);
				//��������
				String cmaterialvname = "";
				
				if (this.getCmaterialvInfoById(cmaterialvid)!=null&&this.getCmaterialvInfoById(cmaterialvid).size()>0) {
					cmaterialvname = this.getCmaterialvInfoById(cmaterialvid).get(0).get("name");
				}
				msgAGCG000003.setItemName(cmaterialvname);
				//������λ
				String uom = "";
				if (this.getCmaterialvInfoById(cmaterialvid)!=null&&this.getCmaterialvInfoById(cmaterialvid).size()>0) {
					uom = this.getCmaterialvInfoById(cmaterialvid).get(0).get("uom");
				}
				msgAGCG000003.setUom(uom);
				//������
				double nnum = ufDouble.add(checkDoubleIsNull(saleInvoiceBVO.getNnum())).doubleValue();
				msgAGCG000003.setInvoicedQty(BigDecimal.valueOf(nnum));
				//δ˰����
				double netPrice = ufDouble.add(checkDoubleIsNull(saleInvoiceBVO.getNqtorigprice())).doubleValue();
				msgAGCG000003.setNetPrice(BigDecimal.valueOf(netPrice));
				//��˰����
				double taxPrice = ufDouble.add(checkDoubleIsNull(saleInvoiceBVO.getNqtorigtaxprice())).doubleValue();
				msgAGCG000003.setTaxPrice(BigDecimal.valueOf(taxPrice));
				//˰��
				double taxRate = ufDouble.add(checkDoubleIsNull(saleInvoiceBVO.getNtaxrate())).doubleValue();
				msgAGCG000003.setTaxRate(BigDecimal.valueOf(taxRate));
				//δ˰�ܽ���Ʊ��
				double totNetAmt = ufDouble.add(checkDoubleIsNull(saleInvoiceBVO.getNorigmny())).doubleValue();
				msgAGCG000003.setTotNetAmt(BigDecimal.valueOf(totNetAmt));
				//��˰�ܽ���Ʊ��
				double totAmt = ufDouble.add(checkDoubleIsNull(saleInvoiceBVO.getNorigtaxmny())).doubleValue();
				msgAGCG000003.setTotAmt(BigDecimal.valueOf(totAmt));
				//��˰��
				double totTaxAmt = ufDouble.add(checkDoubleIsNull(saleInvoiceBVO.getNtax())).doubleValue();
				msgAGCG000003.setTotTaxAmt(BigDecimal.valueOf(totTaxAmt));
				//���㵥��
				msgAGCG000003.setBalanceId(saleInvoiceBVO.getVbdef11());
				//��ͬ��
				msgAGCG000003.setBpoId(saleInvoiceBVO.getVbdef9());
				//ִ�е���
				msgAGCG000003.setOrderId(saleInvoiceBVO.getVbdef13());
				//��ƱƱ������
				msgAGCG000003.setInvoiceDate(checkIsNull(saleInvoiceHVO.getDbilldate()));
				agcg000003s.add(msgAGCG000003);
			}
			MsgAGCG000003Head msgAGCG000003Head = new MsgAGCG000003Head();
			msgAGCG000003Head.setMsgBody(agcg000003s);
			
			String bodyFlag = rt.postForObject(this.getRestURL("Agcg000003"), msgAGCG000003Head, String.class);
//			String bodyFlag = rt.postForObject("http://192.168.2.233:8084/mq/sendMsgAGCG000003", msgAGCG000003Head, String.class);
			
			if ("0".equals(headFlag)&&"0".equals(bodyFlag)) {// cs 0�ɹ���1ʧ��
				// �ɹ���
				agcg000002s.clear();
				agcg000003s.clear();
				//�޸ķ��ͱ�ʶ
				this.updateIsSend(saleInvoiceHVO);
			} else {
				flag = false;
			}
			 
			return flag;
		}
		
		public void updateIsSend(SaleInvoiceHVO saleInvoiceHVO){
			String csaleinvoiceid = saleInvoiceHVO.getCsaleinvoiceid();
			String sql = "update so_saleinvoice set vdef18='1' where csaleinvoiceid='"+csaleinvoiceid+"'";
			BaseDAO baseDAO = new BaseDAO();
			try {
				baseDAO.executeUpdate(sql);
			} catch (DAOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}

		@Override
		public void writeBack(AggCgag000003HVO aggCgag000003HVO) {
			// TODO �Զ����ɵķ������
			Cgag000003HVO cgag000003hvo = aggCgag000003HVO.getParentVO();
			String pkid = cgag000003hvo.getPk_pgjdjjjsxx();
			String msginfo="";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currtime = df.format(new Date());
			BaseDAO baseDao = new BaseDAO();
			String updateSql = "update msg_cgag000003 set msgflag='1',msginfo='"+msginfo+"',MODIFIEDTIME='"+currtime +"' where PK_PGJDJJJSXX='"+pkid+"'";
			
//			cgag000003hvo.setMsgflag("1");
//			cgag000003hvo.setModifiedtime(new UFDateTime());
//			cgag000003hvo.setMsginfo("");
//			cgag000003hvo.setStatus(VOStatus.UPDATED);
			aggCgag000003HVO.setParent(cgag000003hvo);
			try {
				baseDao.executeUpdate(updateSql);
			} catch (DAOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
		}
		
		public void writeBack(Cgag000003HVO cgag000003hvo) {
			// TODO �Զ����ɵķ������
			String pkid = cgag000003hvo.getPk_pgjdjjjsxx();
			String msginfo=cgag000003hvo.getMsginfo();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currtime = df.format(new Date());
			BaseDAO baseDao = new BaseDAO();
			String updateSql = "update msg_cgag000003 set msgflag='1',msginfo='"+msginfo+"',MODIFIEDTIME='"+currtime +"' where PK_PGJDJJJSXX='"+pkid+"'";
			
			try {
				baseDao.executeUpdate(updateSql);
			} catch (DAOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
		}

		@Override
		public void sendZjbInsert(SaleInvoiceVO saleInvoiceVO) {
			// TODO �Զ����ɵķ������
			BaseDAO baseDAO = new BaseDAO();
			//ת������������
			Agcg000002HVO agcg000002hvo = this.agcgZjbHVO(saleInvoiceVO);
			
			List<Agcg000003HVO> vos = this.agcgZjbBVO(saleInvoiceVO);
			try {
				//���м�������������
				String idString = baseDAO.insertVO(agcg000002hvo);
				//���м���ӱ��������
//				NCObject objs = NCObject.newInstance(vos.toArray(new Agcg000003HVO[0]));
//				MDPersistenceService.lookupPersistenceService().saveBill(objs);

				

				String []idsString = baseDAO.insertVOList(vos);
			}catch (DAOException e) {
				ExceptionUtils.wrappBusinessException("�м�����ʧ��!");
			}
			
		}
		/**
		 * �������ȷ���м���ͷ����vo
		 * �м��
		 */
		public Agcg000002HVO agcgZjbHVO(SaleInvoiceVO saleInvoiceVO){
			SaleInvoiceHVO saleInvoiceHVO = saleInvoiceVO.getParentVO();
			SaleInvoiceBVO[] saleInvoiceBVOs = saleInvoiceVO.getChildrenVO();
			//���۷�Ʊ����
			Agcg000002HVO agcg000002hvo = new Agcg000002HVO();
			
			agcg000002hvo.setInvoiceid(saleInvoiceHVO.getVbillcode());
			agcg000002hvo.setInvoicenum(saleInvoiceHVO.getVgoldtaxcode());
			agcg000002hvo.setInvoicedate(saleInvoiceHVO.getDbilldate()+"");
			//˰��-ȡ�ӱ��һ��
			agcg000002hvo.setTaxrate(saleInvoiceBVOs[0].getNtaxrate());
			
	//		��˰���-ȡ����ϼ�
			UFDouble ufDouble = new UFDouble();
			double norigmnySum = 0;
			for (int i = 0; i < saleInvoiceBVOs.length; i++) {
				SaleInvoiceBVO saleInvoiceBVO = saleInvoiceBVOs[i];
				double norigmny = ufDouble.add(saleInvoiceBVO.getNorigmny()).doubleValue();
				norigmnySum+=norigmny;
			}
			agcg000002hvo.setTotnetamt(new UFDouble(norigmnySum));
	//		��˰�ϼ�
			agcg000002hvo.setTotamt(saleInvoiceHVO.getNtotalorigmny());
			//˰��
			double ntaxSum = 0;
			for (int i = 0; i < saleInvoiceBVOs.length; i++) {
				SaleInvoiceBVO saleInvoiceBVO = saleInvoiceBVOs[i];
				double ntax = ufDouble.add(saleInvoiceBVO.getNtax()).doubleValue();
				ntaxSum+=ntax;
			}
			agcg000002hvo.setTottaxamt(new UFDouble(ntaxSum));
			//���㵥����
			agcg000002hvo.setBalanceid(saleInvoiceBVOs[0].getVbdef11());
			//��Ʊ����
			agcg000002hvo.setInvoicecode(saleInvoiceHVO.getVbillcode());
			//�ɹ���ͬ��
			agcg000002hvo.setBpoid(saleInvoiceBVOs[0].getVbdef9());
			//�ɹ���ʶ
			agcg000002hvo.setAttributeValue("msgflag", "1");	
			//ִ�е���
			agcg000002hvo.setOrderid(saleInvoiceBVOs[0].getVbdef13());
			//����
			agcg000002hvo.setPk_group(saleInvoiceHVO.getPk_group());
			//��֯
			agcg000002hvo.setPk_org(saleInvoiceHVO.getPk_org());
			//��֯�汾
			agcg000002hvo.setPk_org_v(saleInvoiceHVO.getPk_org_v());
			//��������
			
			//�Ƶ���
			agcg000002hvo.setBillmaker(AppContext.getInstance().getPkUser());
			//��������
			agcg000002hvo.setDbilldate(new UFDate(new Date().getTime()));
			//������
			agcg000002hvo.setCreator(AppContext.getInstance().getPkUser());
			//����ʱ��
			agcg000002hvo.setCreationtime(new UFDate(new Date().getTime()));
			//������
			//����ʱ��
			//��Դ��������
			agcg000002hvo.setStatus(VOStatus.NEW);
			agcg000002hvo.setBillstatus(-1);
			agcg000002hvo.setMsgresource("���۷�Ʊ");
			return agcg000002hvo;
		}
		
		/**
		 * �������ȷ���м���ͷ����vo
		 * �м��
		 */
		public List<Agcg000003HVO> agcgZjbBVO(SaleInvoiceVO saleInvoiceVO){
			List<Agcg000003HVO> vos = new ArrayList<Agcg000003HVO>();
			SaleInvoiceHVO saleInvoiceHVO = saleInvoiceVO.getParentVO();
			SaleInvoiceBVO []saleInvoiceBVOs = saleInvoiceVO.getChildrenVO();
			
			for (int j = 0; j < saleInvoiceBVOs.length; j++) {
				Agcg000003HVO agcg000003hvo = new Agcg000003HVO();
				
				SaleInvoiceBVO saleInvoiceBVO = saleInvoiceBVOs[j];
				//��Ʊ��(ȡ����)
				agcg000003hvo.setInvoiceid(saleInvoiceHVO.getVbillcode());
				//��ƱƱ�����(ȡ����)
				agcg000003hvo.setInvoicenum(saleInvoiceHVO.getVgoldtaxcode());
				//��Ʊ����
				agcg000003hvo.setInvoicecode(saleInvoiceHVO.getVbillcode());
				//��Ʊ��ϸ�ţ���ϸ��Ϣ������
				agcg000003hvo.setInvoicelineid(saleInvoiceBVO.getCsaleinvoicebid());
				//�ɹ���ͬ�к�
				agcg000003hvo.setBpolineid(saleInvoiceBVO.getVbdef10());
				//ִ�е��к�
				agcg000003hvo.setOrderlineid(saleInvoiceBVO.getVbdef14());
				//���㵥�к�
				agcg000003hvo.setBalancelineid(saleInvoiceBVO.getVbdef12());
				//���ϱ���
				String cmaterialvid = saleInvoiceBVO.getCmaterialvid();
				agcg000003hvo.setItemid(cmaterialvid);
				//��������
				String cmaterialvname = "";
				
				if (this.getCmaterialvInfoById(cmaterialvid)!=null&&this.getCmaterialvInfoById(cmaterialvid).size()>0) {
					cmaterialvname = this.getCmaterialvInfoById(cmaterialvid).get(0).get("name");
				}
				agcg000003hvo.setItemname(cmaterialvname);
				//������λ
				String uom = "";
				if (this.getCmaterialvInfoById(cmaterialvid)!=null&&this.getCmaterialvInfoById(cmaterialvid).size()>0) {
					uom = this.getCmaterialvInfoById(cmaterialvid).get(0).get("uom");
				}
				agcg000003hvo.setUom(uom);
				//��������
				//������
				agcg000003hvo.setInvoicedqty(saleInvoiceBVO.getNtaxrate());
				//δ˰����
				agcg000003hvo.setNetprice(saleInvoiceBVO.getNorigprice());
				//��˰����
				agcg000003hvo.setTaxprice(saleInvoiceBVO.getNorigtaxprice());
				//˰��
				agcg000003hvo.setTaxrate(saleInvoiceBVO.getNtaxrate());
				//δ˰�ܽ���Ʊ��
				agcg000003hvo.setTotnetamt(saleInvoiceBVO.getNorigmny());
				//��˰�ܽ���Ʊ��
				agcg000003hvo.setTotamt(saleInvoiceBVO.getNorigtaxmny());
				//��˰��
				agcg000003hvo.setTottaxamt(saleInvoiceBVO.getNtax());
				//���㵥��
				agcg000003hvo.setBalanceid(saleInvoiceBVO.getVbdef11());
				//��ͬ��
				agcg000003hvo.setBpoid(saleInvoiceBVO.getVbdef9());
				//ִ�е���
				agcg000003hvo.setOrderid(saleInvoiceBVO.getVbdef13());
				//��ƱƱ������
				agcg000003hvo.setInvoicedate(saleInvoiceHVO.getDbilldate()+"");
				//����
				agcg000003hvo.setPk_group(saleInvoiceHVO.getPk_group());
				//��֯
				agcg000003hvo.setPk_org(saleInvoiceHVO.getPk_org());
				//��֯�汾
				agcg000003hvo.setPk_org_v(saleInvoiceHVO.getPk_org_v());
				//��������
				
				//�Ƶ���
				agcg000003hvo.setBillmaker(AppContext.getInstance().getPkUser());
				//��������
				agcg000003hvo.setDbilldate(new UFDate(new Date().getTime()));
				//������
				agcg000003hvo.setCreator(AppContext.getInstance().getPkUser());
				//����״̬  1-���ͳɹ�
				agcg000003hvo.setMsgflag("1");
				//����ʱ��
				agcg000003hvo.setCreationtime(new UFDateTime());
				//������
				//����ʱ��
				//��Դ��������
				agcg000003hvo.setStatus(VOStatus.NEW);
				agcg000003hvo.setBillstatus(-1);
				agcg000003hvo.setMsgresource("���۷�Ʊ��ϸ");
				vos.add(agcg000003hvo);
			}
			
			return vos;
		}
		
		public String getRestURL(String telId){
			BaseDAO baseDao = new BaseDAO();
			String rsSql = "SELECT URL_PATH FROM RT_BASEURL WHERE PK_ID='"+ telId + "'";
			String retString = null;
			try {
				retString =(String) baseDao.executeQuery(rsSql, new ResultSetProcessor() {
					@Override
					public String handleResultSet(ResultSet rs) throws SQLException {
						String url = null;
						while (rs.next()) {
							url = rs.getString(1);
						}
						return url;
					}
				});
			} catch (DAOException e) {
				e.printStackTrace();
			}
			return retString.trim();
		}
		//��ȡ˰��
		public String getTaxCodeByCn01(String code){
			BaseDAO baseDao = new BaseDAO();
			String rsSql = "select PK_TAXCODE from BD_TAXCODE where code='"+ code + "'";
			String retString = null;
			try {
				retString =(String) baseDao.executeQuery(rsSql, new ResultSetProcessor() {
					@Override
					public String handleResultSet(ResultSet rs) throws SQLException {
						String taxcode = null;
						while (rs.next()) {
							taxcode = rs.getString(1);
						}
						return taxcode;
					}
				});
			} catch (DAOException e) {
				e.printStackTrace();
			}
			return retString.trim();
		}
		
		//�������ϱ�����������Ϣ
		@SuppressWarnings("unchecked")
		public List<Map<String, String>> getCmaterialvInfoById(String code){
			BaseDAO baseDao = new BaseDAO();
			String rsSql = "select bmv.name as name,BM.name as unit from bd_material_v bmv,bd_measdoc bm where BMV.pk_measdoc=BM.pk_measdoc and bmv.PK_SOURCE='"+ code + "'";
			List<Map<String, String>> list = null;
			try {
				list = (List<Map<String, String>>)baseDao.executeQuery(rsSql, new ResultSetProcessor() {
					
					@Override
					public List<Map<String, String>> handleResultSet(ResultSet rs) throws SQLException {
						// TODO �Զ����ɵķ������
						List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
						Map<String, String> m = new HashMap<String, String>();
						while (rs.next()) {
							m.put("name", rs.getString(1));
							m.put("uom", rs.getString(2));
							list1.add(m);
						}
						return list1;
					}
				});
			} catch (DAOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			return list;
		}
		
		@SuppressWarnings("unchecked")
		public List<Map<String, String>> getbalanceIdList(String balanceId){
			BaseDAO baseDao = new BaseDAO();
			String querySql = "select taxamount from msg_cgag000003 where BALANCEID= '"+balanceId+"'";
			List<Map<String, String>> list = null;
			try {
				list = (List<Map<String, String>>)baseDao.executeQuery(querySql, new ResultSetProcessor() {
					
					@Override
					public List<Map<String, String>> handleResultSet(ResultSet rs) throws SQLException {
						// TODO �Զ����ɵķ������
						List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
						Map<String, String> m = new HashMap<String, String>();
						while (rs.next()) {
							m.put("jsje", rs.getString(1));
							list1.add(m);
						}
						return list1;
					}
				});
			} catch (DAOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			return list;
		}
		public String getKeyValue() {
//			String previousTime = "";	
//			Integer ORDER = 0;
			String defString = "SI";
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String keyValue = defString+currentTime;
			String ranString = String.valueOf(Math.ceil(Math.random()*500000+500000));
			keyValue+=ranString;
			if (keyValue.length()>18) {
				keyValue = keyValue.substring(0, 18);
			}

//			if (currentTime.equals(previousTime)) {
//				ORDER++;
//				StringBuffer orderStr = new StringBuffer("");
//				for (int i = 0; i < 6 - ORDER.toString().length(); i++) {
//					orderStr.append("0");
//				}
//				orderStr.append(ORDER.toString());
//				keyValue += orderStr;
//			} else {
//				previousTime = currentTime;
//				ORDER = 1;
//				StringBuffer orderStr = new StringBuffer("");
//				for (int i = 0; i < 6 - ORDER.toString().length(); i++) {
//					orderStr.append("0");
//				}
//				orderStr.append(ORDER.toString());
//				keyValue += orderStr;
//			}
			return keyValue;
		}
		
		//Դͷ���ݺš�Դͷ�����к�
		
		@SuppressWarnings("unchecked")
		public List<Map<String, Object>> getVfirstInfo(String bpoId,String bpoLineId) {
			// TODO �Զ����ɵķ������
			BaseDAO baseDao = new BaseDAO();
			String querySql = "SELECT csaleorderbid,crowno from so_saleorder_b where vbdef2='"+bpoId+"' and vbdef3='"+bpoLineId+"'";
			List<Map<String, Object>> list = null;
			try {
				list = (List<Map<String, Object>>)baseDao.executeQuery(querySql, new ResultSetProcessor() {
					
					@Override
					public List<Map<String, Object>> handleResultSet(ResultSet rs) throws SQLException {
						// TODO �Զ����ɵķ������
						List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
						Map<String, Object> m = new HashMap<String, Object>();
						while (rs.next()) {
							//���۶�����
							m.put("vfirstcode", rs.getString(1));
							//���۶����к�
							m.put("vfirstrowno", rs.getString(2));
							list1.add(m);
						}
						return list1;
					}
				});
			} catch (DAOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			return list;
		}
		
		
		
		//��Դ���ݺš���Դ�����к�
		@SuppressWarnings("unchecked")
		public List<Map<String, Object>> getVSrcInfo(String bpoId,String bpoLineId) {
			// TODO �Զ����ɵķ������
			BaseDAO baseDao = new BaseDAO();
			String querySql = "SELECT cgeneralbid,crowno from ic_saleout_b where vbdef2='"+bpoId+"' and vbdef3='"+bpoLineId+"'";
			List<Map<String, Object>> list = null;
			try {
				list = (List<Map<String, Object>>)baseDao.executeQuery(querySql, new ResultSetProcessor() {
					
					@Override
					public List<Map<String, Object>> handleResultSet(ResultSet rs) throws SQLException {
						// TODO �Զ����ɵķ������
						List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
						Map<String, Object> m = new HashMap<String, Object>();
						while (rs.next()) {
							//���ϱ���
							m.put("vsrccode", rs.getString(1));
							//������λ
							m.put("vsrcrowno", rs.getString(2));
							
							list1.add(m);
						}
						return list1;
					}
				});
			} catch (DAOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			return list;
		}
		
		public String checkIsNull(Object byCheckStr){
			if (byCheckStr==null) {
				return null;
			}else {
				return byCheckStr+"";
			}
		}
		public UFDouble checkDoubleIsNull(UFDouble checkDouble){
			if (checkDouble == null) {
				return UFDouble.ZERO_DBL;
			}else {
				return checkDouble;
			}
		}
		
		public String nullToString(Object object) {
			if (object == null) {
				return "";
			}else {
				return object+"";
			}
		}
		
		@SuppressWarnings("unchecked")
		public List<Map<String, Object>> isHaveVbillcode(String vbillcode) {
			// TODO �Զ����ɵķ������
			BaseDAO baseDao = new BaseDAO();
			String querySql = "SELECT csaleinvoiceid FROM so_saleinvoice where vbillcode='"+vbillcode+"'";
			List<Map<String, Object>> list = null;
			try {
				list = (List<Map<String, Object>>)baseDao.executeQuery(querySql, new ResultSetProcessor() {
					
					@Override
					public List<Map<String, Object>> handleResultSet(ResultSet rs) throws SQLException {
						// TODO �Զ����ɵķ������
						List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
						Map<String, Object> m = new HashMap<String, Object>();
						while (rs.next()) {
							m.put("csaleinvoiceid", rs.getString(1));
							list1.add(m);
						}
						return list1;
					}
				});
			} catch (DAOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			return list;
		}
	
}
