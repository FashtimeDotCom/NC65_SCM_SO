/**
 * 
 */
package nc.ui.so.m30.billui.action.assist;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



import java.util.Map;







//import nc.vo.lm.intertable.AggLmpgjkHVO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pf.PfUtilTools;
import nc.bs.trade.business.HYPubBO;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.md.data.access.NCObject;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.ls.MessageBox;
import nc.ui.uif2.NCAction;
import nc.uif.pub.exception.UifException;

import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @author  wangzym
 * @version 2017��3��16�� ����2:49:32
 */
public class XsddAction extends NCAction{

	/**
	 * 
	 */
	private nc.ui.pubapp.uif2app.model.BillManageModel model;
	private nc.ui.so.m30.billui.view.SaleOrderBillForm editor;
	public XsddAction() {
		// TODO �Զ����ɵĹ��캯�����
		this.setBtnName("���۶����ӿ�");
		this.setCode("yyy");
	}

	/* ���� Javadoc��
	 * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
	 */
	@Override
	public void doAction(ActionEvent e) throws Exception {
		/*// TODO �Զ����ɵķ������
		SaleOrderVO aggSaleOrderVO = null;
		SaleOrderHVO saleOrderHVO = null;
		MsgAGXSHT0001Head msgAGXSHT0001Head = new MsgAGXSHT0001Head();
		MsgAGXSHT0001 msgAGXSHT0001 = new MsgAGXSHT0001();
		Agxsht001HVO agxsht001HVO =new Agxsht001HVO();
		aggSaleOrderVO = (SaleOrderVO) this.model.getSelectedData();
		String pkContract = aggSaleOrderVO.getParent().getAttributeValue("csaleorderid").toString();//���۶�����ʵ��
		//2017-04-19�����жϺ�ͬ�Ƿ��Ѿ����͹�������Ϣ
    	NCObject[] ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(Agxsht001HVO.class , "expordercardno= '"+pkContract+"'", false);
    	if(ncObjects!=null){
    		AggAgxsht001HVO aggHVO = (AggAgxsht001HVO)ncObjects[0].getContainmentObject();
        	Agxsht001HVO axHVO = (Agxsht001HVO)aggHVO.getParentVO(); 
        	String msflage=axHVO.getMsgflag().toString();
        	if("1".equals(msflage)){
        		MessageBox.showMessageDialog("OK", "�ú�ͬ�Ѷ����������ɹ���");
        		return;
        	}
        	if("2".equals(msflage)){
        		MessageBox.showMessageDialog("TIPS", "�ú�ͬ�Ѷ���������ʧ�ܣ�����ϵϵͳά����Ա��");
        		return;
        	}    		
    	}///
		
		
		
	    try {
	    	
	    	String vbillcode = aggSaleOrderVO.getParent().getAttributeValue("vbillcode").toString();//���ݺ�
//	    	String pkContract = aggSaleOrderVO.getParent().getAttributeValue("csaleorderid").toString();//���۶�����ʵ��
 	    	String ccustomerid = aggSaleOrderVO.getParent().getAttributeValue("ccustomerid").toString();
//	    	String tranMode = null == aggSaleOrderVO.getParent().getAttributeValue("ctransporttypeid")? "": aggSaleOrderVO.getParent().getAttributeValue("ctransporttypeid").toString();//���䷽ʽ
	    	//������ �����ݿͻ�������ҿͻ�����  	
		    nc.pubitf.uapbd.ICustomerPubService service = (nc.pubitf.uapbd.ICustomerPubService)NCLocator.getInstance().lookup(nc.pubitf.uapbd.ICustomerPubService.class);
		    CustomerVO[] customerVOs =service.getCustomerVO(new String[]{ccustomerid}, new String[]{"name"});
		    String customerName="";
		    if(customerVOs[0] == null){
		    	customerName=null;
		    }else{
		    	customerName = customerVOs[0].getName().toString();
		    } 
			//���䷽ʽ��Ҫ�ڵ������в�ѯ
	    	String ctransporttypeid = null == aggSaleOrderVO.getParent().getAttributeValue("ctransporttypeid")? null: aggSaleOrderVO.getParent().getAttributeValue("ctransporttypeid").toString();//���䷽ʽ
			String tranMode = null;
			String tranModeName = null;
	    	if(null == ctransporttypeid ){
	    		tranMode = null;
	    		tranModeName = null;
	    	}else{
	        	List<Map<String, Object>> tranType =iMsgAGXSHT0001.TransTypeQuery(ctransporttypeid);
	    		tranMode = tranType.get(0).get("code").toString();
	    		tranModeName = tranType.get(0).get("name").toString();
	    	}
	    	//	    	String tranModeName = null == aggSaleOrderVO.getParent().getAttributeValue("ctransporttypeid")? "":aggSaleOrderVO.getParent().getAttributeValue("ctransporttypeid").toString();//���䷽ʽ����
	    	String cmarbaseclassidh = null == aggSaleOrderVO.getParent().getAttributeValue("cmarbaseclassid")? "":aggSaleOrderVO.getParent().getAttributeValue("cmarbaseclassid").toString();//���ϱ���
	    	nc.pubitf.uapbd.IMaterialBaseClassPubService serviceMatl = (nc.pubitf.uapbd.IMaterialBaseClassPubService)NCLocator.getInstance().lookup(nc.pubitf.uapbd.IMaterialBaseClassPubService.class);
	    	MarBasClassVO[] marBasClassVOs =serviceMatl.queryMaterialBaseClassByPk(cmarbaseclassidh, false);
	    	String prodCode="";
	    	String prodCodeName="";
		    if(marBasClassVOs == null){
		    	prodCode=null;
		    	prodCodeName=null;
		    }else{
		    	prodCode = marBasClassVOs[0].getCode().toString();
		    	prodCodeName =marBasClassVOs[0].getName().toString();
		    }     	
//	    	String prodCode = null == aggSaleOrderVO.getParent().getAttributeValue("cmaterialvid")? "":aggSaleOrderVO.getParent().getAttributeValue("cmaterialvid").toString();//���ϱ���
//	    	String prodCodeName = null == aggSaleOrderVO.getParent().getAttributeValue("cmaterialvid")? "":aggSaleOrderVO.getParent().getAttributeValue("cmaterialvid").toString();//���ϱ���
		    String corigcurrencyid = null == aggSaleOrderVO.getParent().getAttributeValue("corigcurrencyid")? "":aggSaleOrderVO.getParent().getAttributeValue("corigcurrencyid").toString();//����
	    	//�����������ұ��ִ���ͱ�������
	    	List<Map<String, Object>> curr =iMsgAGXSHT0001.CurrtypeQuery(corigcurrencyid);
			String currency = curr.get(0).get("code").toString();
			String currencyName = curr.get(0).get("name").toString();
//		    String currency = null == aggSaleOrderVO.getParent().getAttributeValue("corigcurrencyid")? "":aggSaleOrderVO.getParent().getAttributeValue("corigcurrencyid").toString();//����
//	    	String currencyName = null == aggSaleOrderVO.getParent().getAttributeValue("corigcurrencyid")? "":aggSaleOrderVO.getParent().getAttributeValue("corigcurrencyid").toString();//��������
			//���ݽ��㷽ʽ�������ҽ��㷽ʽ���������
	    	String cbalancetypeid = null == aggSaleOrderVO.getParent().getAttributeValue("cbalancetypeid")? null:aggSaleOrderVO.getParent().getAttributeValue("cbalancetypeid").toString();//���㷽ʽ����
			String settleMode = null;
			String settleModeName = null; 	
	    	if(null == cbalancetypeid ){
	    		settleMode = null;
	    		settleModeName = null; 	
	    	}else{
	        	List<Map<String, Object>> settles =iMsgAGXSHT0001.SettletypeQuery(cbalancetypeid);
	    		settleMode = settles.get(0).get("code").toString();
	    		settleModeName = settles.get(0).get("name").toString(); 		
	    	}
//			String settleMode = null == aggSaleOrderVO.getParent().getAttributeValue("cbalancetypeid")? "":aggSaleOrderVO.getParent().getAttributeValue("cbalancetypeid").toString();//���㷽ʽ����
//	    	String settleModeName = null == aggSaleOrderVO.getParent().getAttributeValue("cbalancetypeid")? "":aggSaleOrderVO.getParent().getAttributeValue("cbalancetypeid").toString();//���㷽ʽ����
	    	String tolWeight = null == aggSaleOrderVO.getParent().getAttributeValue("ntotalnum")? "":aggSaleOrderVO.getParent().getAttributeValue("ntotalnum").toString();//������
	    	UFDouble tolMoney = null == aggSaleOrderVO.getParent().getAttributeValue("ntotalorigmny")? null:(UFDouble)aggSaleOrderVO.getParent().getAttributeValue("ntotalorigmny");//�ܽ��
	    	
	    	 * ��ȡ�ӱ�����
	    	 
	    	SaleOrderBVO[] saleOrderBVOs = (SaleOrderBVO[])aggSaleOrderVO.getChildren(SaleOrderBVO.class);
	    	for (SaleOrderBVO saleOrderBVOitm:saleOrderBVOs){
	    		  List<MsgAGXSHT0001> lsMsgAGXSHT0001 = new ArrayList<>();
	    		
	    		 * ������Ϣ
	    		 
	        	msgAGXSHT0001.setExpOrderCardNo(vbillcode);//��������-��ó
	        	msgAGXSHT0001.setExpOrderCardNo1(pkContract);//����������-��ó
	        	msgAGXSHT0001.setInExpFlag("N");//����ó��־
	        	msgAGXSHT0001.setBuyNameChn(customerName);//������
	        	msgAGXSHT0001.setBuyTele(null);//�򷽵绰
	        	msgAGXSHT0001.setSaleMode(null);;//���۷�ʽ����
	        	msgAGXSHT0001.setSaleModeName(null);//���۷�ʽ����
	        	msgAGXSHT0001.setTranMode(tranMode);//���䷽ʽ
	        	msgAGXSHT0001.setTranModeName(tranModeName);//���䷽ʽ����
	        	msgAGXSHT0001.setProdCode(prodCode);//����Ʒ�ֱ���
	        	msgAGXSHT0001.setProdCodeName(prodCodeName);//����Ʒ�ֱ���
	        	msgAGXSHT0001.setCurrency(currency);//����
	        	msgAGXSHT0001.setCurrencyName(currencyName);//��������
	        	msgAGXSHT0001.setSettleMode(settleMode);//���㷽ʽ����
	        	msgAGXSHT0001.setSettleModeName(settleModeName);//���㷽ʽ����
	        	msgAGXSHT0001.setTolWeight(tolWeight);//������
	        	msgAGXSHT0001.setTolMoney(BigDecimal.valueOf(tolMoney.doubleValue()));//�ܽ��
	        	
	        	 * �ӱ���Ϣ
	        	 	
	    		msgAGXSHT0001.setExpOrderCardItemNo(null == saleOrderBVOitm.getAttributeValue("csaleorderbid")?"":saleOrderBVOitm.getAttributeValue("csaleorderbid").toString());//���ں�ͬ��ʵ��->�����������-��ó
//	    		msgAGXSHT0001.setExpOrderCardNo(null == saleOrderBVOitm.getAttributeValue("vbillcode")?"":saleOrderBVOitm.getAttributeValue("vbillcode").toString());//���ں�ͬ��->��������-��ó
	    		UFDouble orderWt = null == saleOrderBVOitm.getAttributeValue("nastnum")?UFDouble.ZERO_DBL:(UFDouble)saleOrderBVOitm.getAttributeValue("nastnum");
	    		msgAGXSHT0001.setOrderWt(BigDecimal.valueOf(orderWt.doubleValue()));//����->��������
	    		msgAGXSHT0001.setOrderQty(BigDecimal.valueOf(orderWt.doubleValue()));//����->��������
	    		msgAGXSHT0001.setSpecDesc(null == saleOrderBVOitm.getAttributeValue("agtexture")?"":saleOrderBVOitm.getAttributeValue("agtexture").toString());//�ֹ���->�������
	    		UFDouble thick = null == saleOrderBVOitm.getAttributeValue("thicknessmm")?UFDouble.ZERO_DBL:(UFDouble)saleOrderBVOitm.getAttributeValue("thicknessmm");
	    		msgAGXSHT0001.setThick(BigDecimal.valueOf(thick.doubleValue()));//���/�ں���->���
	    		UFDouble width = null ==saleOrderBVOitm.getAttributeValue("widthmm")?UFDouble.ZERO_DBL:(UFDouble)saleOrderBVOitm.getAttributeValue("widthmm");
	    		msgAGXSHT0001.setWidth(BigDecimal.valueOf(width.doubleValue()));//��ȹ���->���
	    		UFDouble lengthMin = null ==saleOrderBVOitm.getAttributeValue("lengthmm")?UFDouble.ZERO_DBL:(UFDouble)saleOrderBVOitm.getAttributeValue("lengthmm");
	    		msgAGXSHT0001.setLengthMin(BigDecimal.valueOf(lengthMin.doubleValue()));//���ȹ���->��������
	    		msgAGXSHT0001.setLengthMax(null);//��������
	    		UFDouble actUnitprice = null ==saleOrderBVOitm.getAttributeValue("nqtorigtaxprice")?UFDouble.ZERO_DBL:(UFDouble)saleOrderBVOitm.getAttributeValue("nqtorigtaxprice");
	    		msgAGXSHT0001.setActUnitprice(BigDecimal.valueOf(actUnitprice.doubleValue()));//������->���۵���
	    		UFDouble orderDpstAmt = null ==saleOrderBVOitm.getAttributeValue("norigtaxmny")?UFDouble.ZERO_DBL:(UFDouble)saleOrderBVOitm.getAttributeValue("norigtaxmny");
	    		msgAGXSHT0001.setOrderDpstAmt(BigDecimal.valueOf(orderDpstAmt.doubleValue()));//���->���
	    		msgAGXSHT0001.setRemark(null);//��ע
	    		String cmaterialvid1 = null == saleOrderBVOitm.getAttributeValue("cmaterialvid")?"":saleOrderBVOitm.getAttributeValue("cmaterialvid").toString();
	    		String shopSign =iMsgAGXSHT0001.ShopSignQuery(cmaterialvid1);
	    		msgAGXSHT0001.setShopSign(shopSign);//���ϱ���->�ƺ�
//	    		msgAGXSHT0001.setShopSign(null == saleOrderBVOitm.getAttributeValue("cmaterialvid")?"":saleOrderBVOitm.getAttributeValue("cmaterialvid").toString());//���ϱ���->�ƺ�		   		
	    		msgAGXSHT0001.setProdStd(null == saleOrderBVOitm.getAttributeValue("agproductstandard")?"":saleOrderBVOitm.getAttributeValue("agproductstandard").toString());//������׼->��Ʒ������׼	
	    		Integer deliveryTolLower = null == saleOrderBVOitm.getAttributeValue("widthtolerance")? 0:Integer.valueOf(saleOrderBVOitm.getAttributeValue("widthtolerance").toString());
	    		msgAGXSHT0001.setDeliveryTolLower(deliveryTolLower);//��ȹ���->������������
	    		Integer deliveryTolUpper = null == saleOrderBVOitm.getAttributeValue("lengthtolerance")? 0:Integer.valueOf(saleOrderBVOitm.getAttributeValue("lengthtolerance").toString());
	    		msgAGXSHT0001.setDeliveryTolUpper(deliveryTolUpper);//���ȹ���->������������
	    		msgAGXSHT0001.setShortSizeRate(null);//�����̳���
	    		msgAGXSHT0001.setShortSizeLow(null);//�����̳�����
	    		msgAGXSHT0001.setShortSizeUp(null);//�����̳�����
	    		msgAGXSHT0001.setMultiSizeNum(0);//������
	    		UFDouble temp=UFDouble.ZERO_DBL;
	    		msgAGXSHT0001.setSingleLength(BigDecimal.valueOf(temp.doubleValue()));//�����߳���
	    		
	    		 * ƴ��msgAGXSHT0001Head
	    		 
	    		lsMsgAGXSHT0001.add(msgAGXSHT0001);
	    		msgAGXSHT0001Head.setMsgId("MsgAGXSHT0001");
	    		msgAGXSHT0001Head.setResourceId("GMERP");
	    		msgAGXSHT0001Head.setMsgBody(lsMsgAGXSHT0001);
	    		 //���÷������м�㷽��
	    	    IMsgAGXSHT0001sat is = NCLocator.getInstance().lookup(IMsgAGXSHT0001sat.class);
	    	   
	    	    try {
	    	    	//���õ���ת������д���м��,executeVOChange���������ͣ����ε������ͣ�
	    	    	AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
	    	    			aggSaleOrderVO //�м��
							    };
	    	    	//�м��AGGVO
	    	    	AggAgxsht001HVO aggAgxsht001HVO;
	    	    	aggAgxsht001HVO = (AggAgxsht001HVO) PfUtilTools.runChangeData("30-Cxx-pgdh", "DW40", srcVosAfterFilter[0]);//5720-Cxx-pgdh�ǳ��ں�ͬ�����Σ����ݽ������ͣ�DW40�����ε�������
	    	    	aggAgxsht001HVO.getParent().setStatus(VOStatus.NEW);
	    			//���õ���ת������д���м��
//	    			AggLmpgjkHVO[] aggLmpgjkHVO = PfServiceScmUtil.executeVOChange("5720-Cxx-004", "5730", new SaleOrderVO[]{aggSaleOrderVO});//5720-Cxx-004�����ε��ݽ������ͣ�5730�����ε�������
	    			String result=is.MsgAGXSHT0001(msgAGXSHT0001Head);
	    			if ("1".equals(result)){
	    				aggAgxsht001HVO.getParent().setAttributeValue("msgflag", 2);
	    				aggAgxsht001HVO.getParent().setAttributeValue("dr", 0);
	    				HYPubBO bo = new HYPubBO();
							try {
								bo.saveBill(aggAgxsht001HVO);
							} catch (UifException e2) {
								// TODO �Զ����ɵ� catch ��
								e2.printStackTrace();
							}
						
	    			}else{
	    				aggAgxsht001HVO.getParent().setAttributeValue("msgflag", 1);
	    				aggAgxsht001HVO.getParent().setAttributeValue("dr", 0);
	    				HYPubBO bo = new HYPubBO();
						try {
							bo.saveBill(aggAgxsht001HVO);
						} catch (UifException e1) {
							// TODO �Զ����ɵ� catch ��
							e1.printStackTrace();
						}
						
	    			}
	    		}catch (Exception e1) {
	    			// TODO �Զ����ɵ� catch ��
	    			e1.printStackTrace();
	    		}
	    	}
	    	MessageBox.showMessageDialog("OK", "�ú�ͬ�����ɹ���");
	    }catch (Exception be) {
	      ExceptionUtils.wrappException(be);
	    }*/
	    
	}

	/**
	 * @return model
	 */
	public nc.ui.pubapp.uif2app.model.BillManageModel getModel() {
		return model;
	}

	/**
	 * @param model Ҫ���õ� model
	 */
	public void setModel(nc.ui.pubapp.uif2app.model.BillManageModel model) {
		this.model = model;
	}

	/**
	 * @return editor
	 */
	public nc.ui.so.m30.billui.view.SaleOrderBillForm getEditor() {
		return editor;
	}

	/**
	 * @param editor Ҫ���õ� editor
	 */
	public void setEditor(nc.ui.so.m30.billui.view.SaleOrderBillForm editor) {
		this.editor = editor;
	}

}
