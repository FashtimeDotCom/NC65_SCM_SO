package nc.ui.so.m30.billrefForLS41;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.bd.material.assign.IMaterialAssignService;
import nc.itf.bd.material.baseinfo.IMaterialBaseInfoService;
import nc.itf.bd.material.measdoc.IMeasdocService;
import nc.itf.bd.material.stock.IMaterialStockService;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.para.SysInitQuery;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.cost.MaterialCostmodeVO;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.bd.material.measdoc.MeasdocVO;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lm.lsdlxy.AggLsxywtHVO;
import nc.vo.lm.lsdlxy.LsxywtHVO;
import nc.vo.lm.lsdlxy.LsxywtbBVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/*���ε��ݴ����������ε���*/
public class AddLS41Action extends AbstractReferenceAction {

	private static final long serialVersionUID = -4417976703049420354L;

	private BillForm editor;

	private AbstractAppModel model;
	
	String pk_group = "0001N610000000000IT0";// ��֯������ֱ��д��
	String pk_org = "0001N610000000000IT0";
	
	UFDateTime ufDateTime = new UFDateTime();
	String wlbm=String.valueOf(ufDateTime.getMillis());

	@Override
	public void doAction(ActionEvent e) throws Exception {

		
		PfUtilClient.childButtonClickedNew(createPfButtonClickContext());
		if (PfUtilClient.isCloseOK()) {
			AggLsxywtHVO[] lsvos=(AggLsxywtHVO[]) PfUtilClient.getRetOldVos();
			//�����Ͻ��д���
			this.processForMaterial(lsvos);
			SaleOrderVO[] vos = (SaleOrderVO[]) PfUtilClient.getRetVos();
			
			// ��ʾ��ת��������
			
			for (SaleOrderVO saleOrderVO : vos) {
				SaleOrderHVO hvo = saleOrderVO.getParentVO();
				
				if((String)hvo.getAttributeValue("buyccurrencyid")==null){
					continue;
				}
				UFDouble localcurrate = CurrencyRate
						.getGlobalLocalCurrencySellRate(
								(String)hvo.getAttributeValue("buyccurrencyid"),
								TimeUtils.getsrvBaseDate());
				// �����ʸ�ֵ
				saleOrderVO.getParentVO().setAttributeValue("exchange_rate", localcurrate);
			}	
			
			
			this.getTransferViewProcessor().processBillTransfer(vos);
			
		}
	}

	private void processForMaterial(AggLsxywtHVO[] lsvos) throws BusinessException {
		// TODO �Զ����ɵķ������
		List<String> maPkList = new ArrayList<String>();//�����������۶���
		for(AggLsxywtHVO aggvo:lsvos){
			LsxywtHVO parentVO = aggvo.getParentVO();//
			LsxywtbBVO[] bvos = (LsxywtbBVO[]) aggvo.getChildrenVO();
			for(LsxywtbBVO lsbvo:bvos){
				String zwmc=(String)lsbvo.getAttributeValue("zwmc");
				String ggxh=(String)lsbvo.getAttributeValue("ggxh");
				//�ж������Ƿ����
				if(!isHas(zwmc+"-"+ggxh)){
					MaterialVO returnVO = insertMaterial(lsbvo, pk_org, pk_group);// ���ø������ϵ�������
					String pk = returnVO.getPk_material();
					maPkList.add(pk);
				}
			}
			if(maPkList != null && maPkList.size()>0){
				//�Զ���������
				autoMaterialAssign(maPkList);

				//�Զ����óɱ���Ϣ���κ���
				autoEnableBatch(maPkList);
				
				//�Զ����ÿ����Ϣ���ι���
				autoEnableStock(maPkList);
			}

		}
	}

	private PfButtonClickContext createPfButtonClickContext() {
		PfButtonClickContext context = new PfButtonClickContext();
		context.setParent(this.getModel().getContext().getEntranceUI());
		context.setSrcBillType(this.getSourceBillType());
		context.setPk_group(this.getModel().getContext().getPk_group());
		context.setUserId(this.getModel().getContext().getPk_loginUser());
		// ����ýڵ����ɽ������ͷ����ģ���ô�������Ӧ�ô��������ͣ����򴫵�������
		String vtrantype = TrantypeFuncUtils.getTrantype(this.getModel()
				.getContext());
		if (StringUtil.isEmptyWithTrim(vtrantype)) {
			// ���õ�ǰ(����)�������ͱ���
			context.setCurrBilltype("30");
		} else {
			context.setCurrBilltype(vtrantype);
		}
		context.setUserObj(null);
		context.setSrcBillId(null);
		context.setBusiTypes(this.getBusitypes());
		// ����Ĳ�����ԭ�����õķ����ж����漰��ֻ���������һ�����ṹ�����������������¼ӵĲ���
		// ���εĽ������ͼ���
		context.setTransTypes(this.getTranstypes());
		// ��־�ڽ�������Ŀ�Ľ������ͷ���ʱ������Ŀ�Ľ������͵����ݣ�������������ֵ��1�����ݽӿڶ��壩��
		// 2�������������ã���-1�������ݽ������ͷ��飩
		context.setClassifyMode(PfButtonClickContext.ClassifyByItfdef);
		return context;
	}

	public BillForm getEditor() {
		return this.editor;
	}

	public AbstractAppModel getModel() {
		return this.model;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {
		return this.model.getUiState() == UIState.NOT_EDIT;
	}
	
	/**
	 *  �Զ����ÿ����Ϣ���ι���
	 * @author ljf
	 * @throws BusinessException 
	 */
	@SuppressWarnings("unchecked")
	private void autoEnableStock(List<String> maPkList) throws BusinessException {
		String orgsql = "select pk_org from org_orgs where pk_group <> pk_org and (orgtype43='Y' or isbusinessunit ='Y' or orgtype19='Y' ) and (enablestate = 2) and pk_group = '0001N610000000000IT0'";
		
		List<String> orglist = (List<String>) getQueryBS().executeQuery(orgsql, new ColumnListProcessor());
		if(orglist == null || orglist.size() == 0){
			return;
		}
		
		IMaterialStockService stockservice = NCLocator.getInstance().lookup(IMaterialStockService.class);
		
		SqlBuilder querysql = new SqlBuilder();
		querysql.append(MaterialStockVO.PK_MATERIAL, maPkList.toArray(new String[maPkList.size()]));
		List<MaterialStockVO> stockvolist = (List<MaterialStockVO>) getMDQueryService().queryBillOfVOByCond(MaterialStockVO.class, querysql.toString(), false);
		if(stockvolist == null || stockvolist.size() == 0){
			return;
		}
		
		for(int i = 0; i < stockvolist.size(); i++){
			MaterialStockVO stockvo = stockvolist.get(i);
			stockvo.setWholemanaflag(UFBoolean.TRUE);
		}
		
		stockservice.updateMaterialStockVOs(stockvolist.toArray(new MaterialStockVO[stockvolist.size()]));
	}
	
	private IMDPersistenceQueryService getMDQueryService() {
	    return MDPersistenceService.lookupPersistenceQueryService();
	 }
	
	/**
	 *  �Զ����óɱ���Ϣ���κ���
	 * @author ljf
	 * @throws MetaDataException 
	 */
	@SuppressWarnings("unchecked")
	private void autoEnableBatch(List<String> maPkList) throws BusinessException {
		SqlBuilder sql = new SqlBuilder();
		sql.append(MaterialCostmodeVO.PK_MATERIAL, maPkList.toArray(new String[maPkList.size()]));
		
		List<MaterialCostmodeVO> costmodevolist = (List<MaterialCostmodeVO>) getMDQueryService().queryBillOfVOByCond(MaterialCostmodeVO.class, sql.toString(), false);
		if(costmodevolist == null || costmodevolist.size() == 0){
			return;
		}
		
		for(int i = 0; i < costmodevolist.size(); i++){
			MaterialCostmodeVO costmodevo = costmodevolist.get(i);
			costmodevo.setBatchcost(UFBoolean.TRUE);
		}
		
		HYPubBO_Client.updateAry(costmodevolist.toArray(new MaterialCostmodeVO[costmodevolist.size()]));
	}
	
	/**
	 * �ж������Ƿ����
	 * @param name 
	 * @throws BusinessException 
	 */
	public Boolean isHas(String zwmc) throws BusinessException{
		String sqlmact = "select count(pk_material) from bd_material where name ='"+ zwmc + "' and nvl(dr,0)=0 ";//+"and materialspec='"+ggxh+"'";// ���ϵ���
		int k = 0;
		k = (Integer)getQueryBS().executeQuery(sqlmact,new ColumnProcessor());
		if (k==0) {
			return false;
		}
		return true;
	}
	
	public MaterialVO insertMaterial(LsxywtbBVO lsbvo, String pk_group,
			String pk_org) throws BusinessException {
		HYPubBO_Client bo = new HYPubBO_Client();
		IMaterialBaseInfoService maservice = NCLocator.getInstance().lookup(
				IMaterialBaseInfoService.class);
		
		MaterialVO maVO = new MaterialVO();
		maVO.setPk_group(pk_group);
		maVO.setPk_org(pk_org);
		// Ĭ�ϳ�һ����˰��Ʒ����ʽ��δ֪
		maVO.setPk_mattaxes("1001Z01000000003W0WH");
		// ���Ӳɹ���
		maVO.setIselectrans(UFBoolean.FALSE);
		// ��Ʒ�ز���
		maVO.setProductfamily(UFBoolean.FALSE);
		// �������۲���
		maVO.setElectronicsale(UFBoolean.FALSE);
		// �������۲���
		maVO.setRetail(UFBoolean.FALSE);
		// ����״̬,������
		maVO.setEnablestate(2);
		// ����ݲ�
		maVO.setIntolerance(new UFDouble(0));
		// ����ݲ�
		maVO.setOuttolerance(new UFDouble(0));
		// ����ݲ�
		maVO.setOutcloselowerlimit(new UFDouble(0));
		// ��������
		if((String) lsbvo.getAttributeValue("ggxh")==null){
			maVO.setName((String) lsbvo.getAttributeValue("zwmc"));
		}else{
			maVO.setName((String) lsbvo.getAttributeValue("zwmc")+"-"+(String) lsbvo.getAttributeValue("ggxh"));
		}
		// ���Ϲ��
		//maVO.setMaterialspec((String) lsbvo.getAttributeValue("ggxh"));// materialspec
		// �����ͺ�
		//maVO.setMaterialtype((String) lsbvo.getAttributeValue("ggxh"));
		// ���Ϸ���
		String Pk_marbasclass = null;
		String code = this.getMarbasclasscode();// ���Ϸ������
		MarBasClassVO[] classVO = (MarBasClassVO[]) bo.queryByCondition(
				MarBasClassVO.class, " code='" + code + "'");
		Pk_marbasclass = classVO[0].getPk_marbasclass();
		maVO.setPk_marbasclass(Pk_marbasclass);
		// ���ϱ���
		if (lsbvo.getAttributeValue("wlbm") == null || ((String) lsbvo.getAttributeValue("wlbm")).trim().length() == 0) {
			// �豸ϵͳû�����ϱ��룬�õ��ݺ���Ϊ���ϱ���			
			maVO.setCode(wlbm);
		} else {			
			maVO.setCode((String) lsbvo.getAttributeValue("wlbm"));
		}
		// ���ϵ�λ
		MeasdocVO[] measdocVOs = (MeasdocVO[]) bo.queryByCondition(
		// MeasdocVO.class, " name='" + srchvo.getCastunitid() + "' and code='"
		// + srchvo.getCastunitid() + "'");
				MeasdocVO.class, " code='" + "'");

		String pk_measdoc = "1001C010000000088AB4";
/*		if (measdocVOs != null && measdocVOs.length > 0) {
			pk_measdoc = measdocVOs[0].getPk_measdoc();
		} else {			
			// �Զ�������������
			MeasdocVO measdocVO = new MeasdocVO();
			measdocVO.setName("ǧ��");
			if(!isMeasdoc(measdocVO.getName())){
				measdocVO.setCode(wlbm);// ����
				measdocVO.setName("ǧ��");// ����
				measdocVO.setPk_group(pk_group);
				measdocVO.setPk_org("GLOBLE00000000000000");// ȫ����֯
				measdocVO.setOppdimen("E");// ��������
				measdocVO.setBasecodeflag(UFBoolean.FALSE);// �Ƿ�Ϊ������λ
				measdocVO.setBitnumber(4);// С��λ��
				IMeasdocService measdocSer = NCLocator.getInstance().lookup(
						IMeasdocService.class);
				measdocVO = measdocSer.insertMeasdocForPfxx(measdocVO);
				pk_measdoc = measdocVO.getPk_measdoc();
			}
		}*/
		//��������λ
		maVO.setPk_measdoc(pk_measdoc);
		// ������
		maVO.setIsfeature(UFBoolean.FALSE);
		// ���׼�
		maVO.setSetpartsflag(UFBoolean.FALSE);
		// ������
		maVO.setFee(UFBoolean.FALSE);
		// ������Ʒ
		maVO.setIshproitems(UFBoolean.FALSE);
		// �ۿۼ۸�
		maVO.setDiscountflag(UFBoolean.FALSE);
		// �Ƿ�����
		maVO.setLatest(UFBoolean.FALSE);
		// �Զ�׷�Ӹ�������λ
		// MaterialConvertVO conVO = new MaterialConvertVO();
		// // ������λͬ������
		// String main_measdocID = pk_measdoc;
		// conVO.setPk_measdoc(main_measdocID);
		// // ����������λ������Ϊ1/1
		// conVO.setMeasrate("1/1");
		// // Ĭ��Ϊ�̶�����
		// conVO.setFixedflag(UFBoolean.TRUE);
		// // �Զ�������и�����
		// conVO.setIsprodmeasdoc(UFBoolean.TRUE);
		// conVO.setIspumeasdoc(UFBoolean.TRUE);
		// conVO.setIsretailmeasdoc(UFBoolean.TRUE);
		// conVO.setIssalemeasdoc(UFBoolean.TRUE);
		// conVO.setIsstockmeasdoc(UFBoolean.TRUE);
		// ϵͳ����Ķ�û�и������������Զ����ӵ�Ҳ�����ø�����
		// maVO.setMaterialconvert(new MaterialConvertVO[]{conVO});
//		MaterialVO returnVO = maservice.insertMaterial(maVO);
		return maservice.insertMaterial(maVO);
	}
	
	/**
	 *  �Զ���������
	 * @author ljf
	 */
	@SuppressWarnings("unchecked")
	private void autoMaterialAssign(List<String> maPkList) throws BusinessException {
		String sql = "select pk_org from org_orgs where pk_group <> pk_org and (orgtype43='Y' or isbusinessunit ='Y' or orgtype19='Y' ) and (enablestate = 2) and pk_group = '0001N610000000000IT0'";
		
		List<String> orglist = (List<String>) getQueryBS().executeQuery(sql, new ColumnListProcessor());
		if(orglist == null || orglist.size() == 0){
			return;
		}
		getAssignService().assignMaterialByPks(maPkList.toArray(new String[maPkList.size()]), orglist.toArray(new String[orglist.size()]), null);
	}
	
	/**
	 * 
	 * @author zjf ���ݲ���ֵ��ȡ���Ϸ������
	 * @throws BusinessException 
	 */
	public String getMarbasclasscode() throws BusinessException {
		SysInitVO initVO = null;
		String pk_org = "GLOBLE00000000000000";// ȫ�ֲ�����֯����

		initVO = SysInitQuery.querySysinitVO(pk_org, "GFCGJH001");// ��ȡҵ�������������֯�������������룩
		String code = initVO.getValue();// ��ȡ����ֵ(���Ϸ������)

		return code;

	}
	
	private IMaterialAssignService getAssignService() {
	    return NCLocator.getInstance().lookup(IMaterialAssignService.class);
	}
	
	private IUAPQueryBS getQueryBS(){
		return NCLocator.getInstance().lookup(IUAPQueryBS.class);
	}

}