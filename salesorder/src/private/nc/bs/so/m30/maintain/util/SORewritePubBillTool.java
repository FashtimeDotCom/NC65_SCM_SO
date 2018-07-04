package nc.bs.so.m30.maintain.util;

import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.vo.opc.mc1.entity.CustomerPOBVO;
import nc.vo.opc.mc1.entity.CustomerPOHVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.scmpub.res.billtype.POBillType;

public class SORewritePubBillTool {
	
	public static void addOPCSrcBillRewriter(BillRewriter tool){
		// ���Ӷ���
	    tool.addSRCHeadClazz(OPCBillType.OPCORDER.getCode(), CustomerPOHVO.class);
	    tool.addSRCItemClazz(OPCBillType.OPCORDER.getCode(), CustomerPOBVO.class);
	}
	
	public static void addPOSrcBillRewriter(BillRewriter tool){
      // Ԥ����
      tool.addSRCHeadClazz(POBillType.Order.getCode(), OrderHeaderVO.class);
      tool.addSRCItemClazz(POBillType.Order.getCode(), OrderItemVO.class);
  }
	
}
