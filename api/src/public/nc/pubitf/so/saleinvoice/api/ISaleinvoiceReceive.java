package nc.pubitf.so.saleinvoice.api;

import java.util.List;
import java.util.Map;

import nc.vo.lm.pgjdjjjsxx.AggCgag000003HVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.saleinvoice.MsgCGAG000003;


public interface ISaleinvoiceReceive {
	
//	public Map<String, Object> SaleInvoice(List<MsgCGAG000003> list);
//	
//	
//	public Map<String, Object> SaleInvoiceLj(List<MsgCGAG000003> list);
	
	//��������
	public String receiveMsgCGAG000003();
	//���ݲɹ���ͬ�š��ɹ���ͬ�кŻ�ȡ���۳��ⵥ�ϵ����ϱ��롢����ͺš�������λ�ֶ�
	public List<Map<String, Object>> getSaleInfo(String bpoId,String bpoLineId);
	//��������
	public boolean sendMsgAGCG000003(SaleInvoiceVO saleInvoiceVO);
	//��д�м��
	public void writeBack(AggCgag000003HVO aggCgag000003HVO);
	//�������ݺ����м�����
	public void sendZjbInsert(SaleInvoiceVO saleInvoiceVO); 
}
