package nc.pubitf.so.m4331.qc.mc003;

import nc.vo.pub.BusinessException;
import nc.vo.qc.c003.entity.ReportVO;

/**
 * �ʼ챨������ӿ�
 * 
 * @since 6.0
 * @version 2011-7-13 ����08:41:18
 * @author ף����
 */
public interface IQueryReportVOForC003 {
  ReportVO[] queryReportVOs(String[] deliverybids) throws BusinessException;
}
