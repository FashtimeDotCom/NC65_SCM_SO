package nc.pubimpl.so.m4331.qc.mc001.rule;

import java.util.Map;

import nc.pubitf.so.m4331.qc.mc001.RewritePara4331ForC001;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * �����д������������д������Ϣ
 * 
 * @since 6.0
 * @version 2010-12-28 ����02:21:31
 * @author ף����
 */
public class FillReportCheckDataRule {

  /**
   * ���쵥��д������ ����д����
   * 
   * @param index
   * @param views
   */
  public void fillReportData(Map<String, RewritePara4331ForC001> index,
      DeliveryViewVO[] views) {
    for (DeliveryViewVO view : views) {
      String bid = view.getItem().getCdeliverybid();
      RewritePara4331ForC001 para = index.get(bid);
      // ��û�д�ı�������
      UFDouble totalreportnum = para.getTotalreportnum();
      // ֻ�и����в��ܱ��죬�ۼƱ�������Ϊ����
      totalreportnum = MathTool.oppose(totalreportnum);
      view.getItem().setNtotalreportnum(totalreportnum);
      view.getItem().setBcheckflag(para.getBcheckflag());
      // �Ƿ��Ѿ��ʼ��������ó�N ֻ���ʼ�����ſ������±���
      view.getItem().setBqualityflag(UFBoolean.FALSE);
      // ����ۼƺϸ�����
      view.getItem().setNtotalelignum(null);
      // ����ۼƲ��ϸ�����
      view.getItem().setNtotalunelignum(null);
    }
  }
}
