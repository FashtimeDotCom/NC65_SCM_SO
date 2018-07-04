package nc.itf.so.pub.ref.ic.m4c;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ic.atp.ATPUpdate;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * 
 * <p>
 * 
 * <b>���������½ӿ�</b>
 * 
 * <p>
 * 
 * <p>
 * ʹ��˵����
 * 
 * <p>
 * ��Ҫ�ɶԵ��ã����ڸ��±�����ǰ����
 * 
 * <p>
 * modifyATPBefore(String cbilltype,AggregatedValueObject[] bills)
 * 
 * <p>
 * ....���ݸ��¼���д.......
 * 
 * <p>
 * modifyATPAfter(String cbilltype,AggregatedValueObject[] bills)
 * 
 * @version v60
 * 
 * @since v60
 * 
 * @author yangb
 * 
 * @time 2010-6-9 ����09:43:41
 */

public class SOATPprocess {

  private SOATPprocess() {
    super();
  }

  /**
   * 
   * �����������������ü��ATP��־������û�ѡ�񲻼�飬�����ñ�־λ
   * 
   * @author yangb
   * 
   * @time 2010-6-9 ����09:51:27
   */
  public static void abandonATPCheck() {
    if (SysInitGroupQuery.isICEnabled()) {
      ATPUpdate bo = NCLocator.getInstance().lookup(ATPUpdate.class);
      bo.abandonATPCheck();
    }
  }

  /**
   * 
   * ���������������������õ���(���õ��ñ��������л�д��ɺ�
   * 
   * �������õ�������ĩβ���ô���������ʱ���)��
   * 
   * �÷������ڸ��º���п�������飬����ATPȱ���쳣�׳���
   * 
   * <b>����˵��</b>
   * 
   * @param cbilltype ---��������
   * 
   * @param bills --- ��������
   * 
   * @return
   * 
   * @throws BusinessException
   * 
   *           <p>
   * 
   * @author yangb
   * 
   * @time 2010-6-9 ����09:51:27
   */

  public static void modifyATPAfter(String cbilltype,
      AggregatedValueObject[] bills) throws BusinessException {
    if (SysInitGroupQuery.isICEnabled()) {
      ATPUpdate bo = NCLocator.getInstance().lookup(ATPUpdate.class);
      bo.modifyATPAfter(cbilltype, bills);
    }
  }

  /**
   * 
   * ������������������ǰ�õ��á�
   * 
   * <b>����˵��</b>
   * 
   * @param cbilltype ---��������
   * 
   * @param bills --- ��������
   * 
   * @return
   * 
   * @throws BusinessException
   * 
   *           <p>
   * 
   * @author yangb
   * @throws BusinessException
   * 
   * @time 2010-6-9 ����09:51:27
   */
  public static void modifyATPBefore(String cbilltype,
      AggregatedValueObject[] bills) throws BusinessException {
    if (SysInitGroupQuery.isICEnabled()) {
      ATPUpdate bo = NCLocator.getInstance().lookup(ATPUpdate.class);
      bo.modifyATPBefore(cbilltype, bills);
    }
  }

}
