package nc.ui.so.m32.billref.ic.vmi.itf;

import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.ic.m50.entity.VmiSumGenerateParam;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

public interface IInvoiceToVmiControl {
  /**
   * ������������������ѡ�е����۷�Ʊ��ͼVO���顣
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-8-3 ����07:22:29
   */
  SaleInvoiceViewVO[] getSelectedVOs();

  /**
   * ���������������������Ļ��ܲ�ѯ����ѡ������Ļ��ܹ���
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-8-6 ����01:09:22
   */
  VmiSumGenerateParam getVmiSumGenerateParam();

  /**
   * ��ѯ�����ؽ�Ҫ���Ļ��ܵ����۷�Ʊ��¼
   * 
   * @param model model
   */
  void queryAndLoadInvoice(AbstractAppModel pmodel);
}
