package nc.vo.so.pub.util;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.pubitf.initgroup.InitGroupQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;

/**
 * �ж�ģ���Ƿ�����
 * 
 * @since 6.0
 * @version 2011-6-18 ����11:39:23
 * @author ����
 */

public class SOSysInitGroupQuery {

  private static String getOrgPk() {
    return InvocationInfoProxy.getInstance().getGroupId();
  }

  /**
   * �ж��ʲ���Ϣ����ģ���Ƿ�����
   * 
   * @return
   */
  public static boolean isAIMEnabled() {
    try {
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), NCModule.AIM.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * �ж��ʲ����޹���ģ���Ƿ�����
   * 
   * @return
   */
  public static boolean isALMEnabled() {
    try {
      // TODO ����������
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), /* NCModule.ALM.getCode() */"4530");
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * �ʲ�����
   * 
   * @return boolean
   */
  public static boolean isAMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.AM.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ����ģ�飺Ӧ������
   * 
   * @return boolean
   */
  public static boolean isAPEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.AP.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ����ģ�飺Ӧ�չ���
   * 
   * @return boolean
   */
  public static boolean isAREnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.AR.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * �ж��ʲ�ʹ�ù���ģ���Ƿ�����
   * 
   * @return
   */
  public static boolean isAUMEnabled() {
    try {
      // TODO ����������
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), /* NCModule.AUM.getCode() */"4520");
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺���۷���
   * 
   * @return boolean
   */
  public static boolean isBRMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.BRM.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ս�Թ���ģ�飺�����ɱ�����
   * 
   * @return boolean
   */
  public static boolean isCMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.CM.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺��������
   * 
   * @return boolean
   */
  public static boolean isCREDITEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.CREDIT.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺��ͬ����
   * 
   * @return boolean
   */
  public static boolean isCTEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.CT.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺�������
   * 
   * @return boolean
   */
  public static boolean isDMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.DM.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * �ж� ά������ģ���Ƿ�����
   * 
   * @return
   */
  public static boolean isEMMEnabled() {
    try {
      // TODO ����������
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), /* NCModule.EMM.getCode() */"4550");
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * �ж�ĳ��ģ���ڵ�ǰ�����Ƿ�����
   * 
   * @param module
   *          NCģ��
   * @return
   */
  public static boolean isEnable(NCModule module) {
    boolean flag = false;
    String pk_group = SOSysInitGroupQuery.getOrgPk();
    try {
      flag = InitGroupQuery.isEnabled(pk_group, module.getCode());
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return flag;
  }

  /**
   * �ж����й���ģ���Ƿ�����
   * 
   * @return
   */
  public static boolean isEOMEnabled() {
    try {
      // TODO ����������
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), /* NCModule.EOM.getCode() */"4540");
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * �ж�ά�޹���ģ���Ƿ�����
   * 
   * @return
   */
  public static boolean isEWMEnabled() {
    try {
      // TODO ����������
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), /* NCModule.EWM.getCode() */"4560");
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺�̶��ʲ�
   * 
   * @return boolean
   */
  public static boolean isFAEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.FA.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��ҵ��ģ�����ƽ̨
   * 
   * @return boolean
   */
  public static boolean isFIPEnabled() {
    return SOSysInitGroupQuery.isEnable(NCModule.FIP);
  }

  /**
   * ���������Ƿ��ڱ������Ѿ�����
   * 
   * @return ����ģ�����÷�����
   */
  public static boolean isGLEnabled() {
    return SOSysInitGroupQuery.isEnable(NCModule.GL);
  }

  /**
   * ����ģ�飺�������
   * 
   * @return boolean
   */
  public static boolean isIAEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.IA.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺������
   * 
   * @return boolean
   */
  public static boolean isICEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.IC.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺���ƻ�
   * 
   * @return boolean
   */
  public static boolean isINVPEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.INVP.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��ɢ�����������ģ��
   * 
   * @return boolean
   */
  public static boolean isMMDPACEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.MMDPAC.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * �������ģ��
   * 
   * @return boolean
   */
  public static boolean isMMDPEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.MMDP.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��������ģ��
   * 
   * @return boolean
   */
  public static boolean isMMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.MM.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * �����������ģ��
   * 
   * @return boolean
   */
  public static boolean isMMPACEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.MMPAC.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ���������������ģ��
   * 
   * @return boolean
   */
  public static boolean isMMPPACEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.MMPPAC.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺�ɹ��ƻ�
   * 
   * @return boolean
   */
  public static boolean isMPPEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.MPP.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��������
   * 
   * @return boolean
   */
  public static boolean isOPCEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.OPC.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ŀ����ģ�飺��Ŀ�ۺϹ���
   * 
   * @return boolean
   */
  public static boolean isPIMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.PIM.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺�ɹ�����
   * 
   * @return boolean
   */
  public static boolean isPOEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.PO.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺�ɹ��۸�
   * 
   * @return boolean
   */
  public static boolean isPPEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.PURP.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺���ۼ۱�
   * 
   * @return boolean
   */
  public static boolean isPPMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.PPM.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺���ۼ۸�
   * 
   * @return boolean
   */
  public static boolean isPRICEEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.PRICE.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * �����ų�
   * 
   * @return boolean
   */
  public static boolean isPSEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.PS.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺���ۼƻ�
   * 
   * @return boolean
   */
  public static boolean isPSPEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.PSP.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /********************** ս�Թ��� ********************/

  /**
   * ��������
   * 
   * @return boolean
   */
  public static boolean isQCEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.QC.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /********************** ���� ********************/

  /**
   * �ж���ת���������ģ���Ƿ�����
   * 
   * @return
   */
  public static boolean isRLMEnabled() {
    try {
      // TODO ����������
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), /* NCModule.RLM.getCode() */"4585");
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * �ж���ת���������ģ���Ƿ�����
   * 
   * @return
   */
  public static boolean isROMEnabled() {
    try {
      // TODO ����������
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), /* NCModule.ROM.getCode() */"4583");
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * �ж��׺�Ʒ����ģ���Ƿ�����
   * 
   * @return
   */
  public static boolean isRUMEnabled() {
    try {
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), NCModule.RUM.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /********************** �ʽ���� ********************/

  /**
   * ��Ӧ��ģ�飺ί��ӹ�
   * 
   * @return boolean
   */
  public static boolean isSCEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.SC.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /********************** �ʲ����� ********************/

  /**
   * ��Ӧ��ģ��
   * 
   * @return boolean
   */
  public static boolean isSCMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.SCM.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /********************** �������� ********************/

  /**
   * ��Ӧ��ģ�飺��Ӧ����������
   * 
   * @return boolean
   */
  public static boolean isSCMFEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.SCMF.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺��Ӧ��������
   * 
   * @return boolean
   */
  public static boolean isSCMMREnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.SCMMR.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺���۹���
   * 
   * @return boolean
   */
  public static boolean isSOEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.SO.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺���۷���
   * 
   * @return boolean
   */
  public static boolean isSREnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(), "4036");// NCModule.SR.getCode()
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * �ʽ����ģ�飺�ڲ��ʻ�����
   * 
   * @return boolean
   */
  public static boolean isTAMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.TAM.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * �ʽ����
   * 
   * @return boolean
   */
  public static boolean isTMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.TM.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺�ڲ�����
   * 
   * @return boolean
   */
  public static boolean isTOEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.TO.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺U8���۽ӿ�
   * 
   * @return boolean
   */
  public static boolean isU8RMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.U8RM.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺��Ӧ�̹���
   * 
   * @return boolean
   */
  public static boolean isVRMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.VRM.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * ��Ӧ��ģ�飺Ӫ������
   * 
   * @return boolean
   */
  public static boolean isMeEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(), "4038");
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

}
