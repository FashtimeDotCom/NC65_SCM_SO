package nc.vo.gl.contrast.uap.proxy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import nc.vo.bd.accassitem.AccAssItemVO;
import nc.vo.pub.BusinessException;

public class AccassItemProxy {
	private AccassItemProxy(){
		super();
	}
	
	private static AccassItemProxy instance=new AccassItemProxy();
	
	private static HashSet<String> custSupAssitemMap=new HashSet<String>();
	
	public boolean isCustSupp(String pk_accassitem){
		boolean result=false;
		if(getCustSuppMap().contains(pk_accassitem)){
			return true;
		}
		return result;
	}
	
	public String[] getCustSuppAccassitems(){
		String[] result=new String[this.getCustSuppMap().size()];
		this.getCustSuppMap().toArray(result);
		return result;
	}
	
	public String getCustsuppluerAccassItemPk(){
		String result=null;
		try {
		AccAssItemVO[] vos=	nc.vo.fipub.freevalue.account.proxy.AccAssGL.queryAllAccAssItemVO();
		if(null!=vos && vos.length>0){
			for(AccAssItemVO vo :vos){
				if(vo.getClassid().trim().equals(nc.itf.bd.pub.IBDMetaDataIDConst.CUSTSUPPLIER.trim())){
					result = vo.getPk_accassitem();
					break;
				}
			}
		}
		} catch (BusinessException e) {
			nc.bs.logging.Logger.error(e.getMessage(), e);
		}
		return result;
	}
	
	public String getcustomerAccassItemPk(){
		String result=null;
		try {
		AccAssItemVO[] vos=	nc.vo.fipub.freevalue.account.proxy.AccAssGL.queryAllAccAssItemVO();
		if(null!=vos && vos.length>0){
			for(AccAssItemVO vo :vos){
				if(vo.getClassid().trim().equals(nc.itf.bd.pub.IBDMetaDataIDConst.CUSTOMER.trim())){
					result = vo.getPk_accassitem();
					break;
				}
			}
		}
		} catch (BusinessException e) {
			nc.bs.logging.Logger.error(e.getMessage(), e);
		}
		return result;
	}
	/**
	 * ��ѯ���и���������Ŀ�ͻ���pk �п����ж������Ϊ���ݶ��������ͬ
	 * @return
	 */
	public Set<String> getcustomerAccassItemPkSet(){
		Set<String> rtSet = new HashSet<String>();
		try {
		AccAssItemVO[] vos=	nc.vo.fipub.freevalue.account.proxy.AccAssGL.queryAllAccAssItemVO();
		if(null!=vos && vos.length>0){
			for(AccAssItemVO vo :vos){
				if(vo.getClassid().trim().equals(nc.itf.bd.pub.IBDMetaDataIDConst.CUSTOMER.trim())){
					rtSet.add(vo.getPk_accassitem());
				}
			}
		}
		} catch (BusinessException e) {
			nc.bs.logging.Logger.error(e.getMessage(), e);
		}
		return rtSet;
	}
	
	public String getsupplierAccassItemPk(){
		String result=null;
		try {
		AccAssItemVO[] vos=	nc.vo.fipub.freevalue.account.proxy.AccAssGL.queryAllAccAssItemVO();
		if(null!=vos && vos.length>0){
			for(AccAssItemVO vo :vos){
				if(vo.getClassid().trim().equals(nc.itf.bd.pub.IBDMetaDataIDConst.SUPPLIER.trim())){
					result = vo.getPk_accassitem();
					break;
				}
			}
		}
		} catch (BusinessException e) {
			nc.bs.logging.Logger.error(e.getMessage(), e);
		}
		return result;
	}
	/**
	 * ��ѯ���и������㹩Ӧ��pk �п����ж������Ϊ���ݶ��������ͬ
	 * @return
	 */
	public Set<String> getsupplierAccassItemPkSet(){
		Set<String> rtSet = new HashSet<String>();
		try {
		AccAssItemVO[] vos=	nc.vo.fipub.freevalue.account.proxy.AccAssGL.queryAllAccAssItemVO();
		if(null!=vos && vos.length>0){
			for(AccAssItemVO vo :vos){
				if(vo.getClassid().trim().equals(nc.itf.bd.pub.IBDMetaDataIDConst.SUPPLIER.trim())){
					rtSet.add(vo.getPk_accassitem());
				}
			}
		}
		} catch (BusinessException e) {
			nc.bs.logging.Logger.error(e.getMessage(), e);
		}
		return rtSet;
	}
	
	private HashSet<String> getCustSuppMap(){
	
		if(custSupAssitemMap.size()==0){
			try {
				//medify by wangxwb 
				
				AccAssItemVO[] vos=	nc.vo.fipub.freevalue.account.proxy.AccAssGL.queryAllAccAssItemVO();
				if(null!=vos && vos.length>0){
					for(AccAssItemVO vo :vos){
						if(vo.getClassid().trim().equals(nc.itf.bd.pub.IBDMetaDataIDConst.CUSTSUPPLIER.trim())
								|| nc.itf.bd.pub.IBDMetaDataIDConst.CUSTOMER.equals(vo.getClassid().trim())
								|| nc.itf.bd.pub.IBDMetaDataIDConst.SUPPLIER.equals(vo.getClassid().trim())){
							custSupAssitemMap.add(vo.getPk_accassitem());
						}
					}
				}
				/**
				 * ���̡��ڲ�����
				 *//*
				AccAssItemVO[] vos= getAccassItemsByClassid(nc.itf.bd.pub.IBDMetaDataIDConst.CUSTSUPPLIER);
				*//**
				 * ���ӿͻ��͹�Ӧ��
				 *//*
				AccAssItemVO[] custmvos= getAccassItemsByClassid(nc.itf.bd.pub.IBDMetaDataIDConst.CUSTOMER);//�ͻ�������Ϣ
				AccAssItemVO[] supplrvos= getAccassItemsByClassid(nc.itf.bd.pub.IBDMetaDataIDConst.SUPPLIER);//��Ӧ�̻�����Ϣ
				if(null!=vos && vos.length>0){
					for(AccAssItemVO vo:vos){
						 custSupAssitemMap.add(vo.getPk_accassitem());
					}
				}
				if(null!=custmvos && custmvos.length>0){
					for(AccAssItemVO vo:custmvos){
						 custSupAssitemMap.add(vo.getPk_accassitem());
					}
				}
				if(null!=supplrvos && supplrvos.length>0){
					for(AccAssItemVO vo:supplrvos){
						 custSupAssitemMap.add(vo.getPk_accassitem());
					}
				}*/
			} catch (BusinessException e) {
				nc.bs.logging.Logger.error(e.getMessage(), e);
			}
		}
		return custSupAssitemMap;
	}
	
	
	
	public  AccAssItemVO[] getAccassItemsByClassid(String classid) throws BusinessException{
		AccAssItemVO[] result=null;
		AccAssItemVO[] vos=	nc.vo.fipub.freevalue.account.proxy.AccAssGL.queryAllAccAssItemVO();
		ArrayList<AccAssItemVO> list=new ArrayList<AccAssItemVO>();
		if(null!=vos && vos.length>0){
			for(AccAssItemVO vo :vos){
				if(vo.getClassid().trim().equals(classid.trim())){
					list.add(vo);
				}
			}
			if(list.size()>0){
				result=new AccAssItemVO[list.size()];
				list.toArray(result);
			}
		}
		
		return result;
		
	}



	public static AccassItemProxy getInstance() {
		return instance;
	}

}
