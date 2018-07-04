package nc.pubitf.so.m30.so.m32;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.paravo.Info30For32Para;

/**
 * ���۶����ṩ�����۷�Ʊ�ӿڷ���
 * 
 * @since 6.0
 * @version 2011-5-4 ����10:03:29
 * @author ��־ΰ
 */
public interface ISaleOrderFor32 {

  /**
   * ����32�������ƺ�32����Դͷ����id���ƹ�����˵�30�ѳ���رչ���SQLƬ��
   * 
   * @param invbodytable ���ⵥ����ID
   * @param cfirstbid Դͷ����ID
   * @return IQueryScheme sqlƬ��
   * @throws BusinessException
   */
  IQueryScheme getOutEndSQL4Filter32(String invbodytable, String cfirstbid)
      throws BusinessException;

  /**
   * �������۶�������id��ѯɢ��id
   * <p>����ɢ����ɢ��idΪnull</p>
   * 
   * @param ids ���۶�������id[]
   * @return Map<String, Info30For32Para> Map<���۶�����ͷid, ɢ��id���տ�Э��>
   * @throws BusinessException
   */
  Map<String, Info30For32Para> queryInfosByOrderBIDs(String[] bids)
      throws BusinessException;
}
