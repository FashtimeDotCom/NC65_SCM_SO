package nc.vo.so.m30.util;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.price.pplimitexe.ISOUpdatePPLimitExe;
import nc.vo.price.pplimitexe.SOUpdatePPLimitExePara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ���۶�����������������
 * 
 * @since 6.36
 * @version 2015-5-30 ����12:33:20
 * @author ����
 */
public class PromoteLimitUtil {

  /**
   * ȫ�ͷ�ִ������д
   * 
   * @param paras
   */
  public static void DeletePPLimit(SOUpdatePPLimitExePara[] paras) {
    if (SysInitGroupQuery.isPRICEEnabled()) {
      ISOUpdatePPLimitExe service =
          NCLocator.getInstance().lookup(ISOUpdatePPLimitExe.class);
      try {
        service.updateExecutedNumByDelete(paras);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /**
   * ȫռ��ִ������д
   * 
   * @param paras
   */
  public static void InsertPPLimit(SOUpdatePPLimitExePara[] paras) {
    if (SysInitGroupQuery.isPRICEEnabled()) {
      ISOUpdatePPLimitExe service =
          NCLocator.getInstance().lookup(ISOUpdatePPLimitExe.class);
      // ��д�����۸��
      try {
        service.updateExecutedNumByInsert(paras);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /**
   * ����ִ������д
   */
  public static void updatePLimit(SOUpdatePPLimitExePara[] releaseparas,
      SOUpdatePPLimitExePara[] paras) {
    if (SysInitGroupQuery.isPRICEEnabled()) {
      ISOUpdatePPLimitExe service =
          NCLocator.getInstance().lookup(ISOUpdatePPLimitExe.class);
      try {
        service.updateExecutedNumByUpdate(releaseparas, paras);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /**
   * ���������ر�/��;��������ر�/��
   * 
   * @param paras ��д����
   * @throws BusinessException
   */
  public static void updateExecutedNumByOpenOrClose(
      SOUpdatePPLimitExePara[] paras) {
    if (SysInitGroupQuery.isPRICEEnabled()) {
      ISOUpdatePPLimitExe service =
          NCLocator.getInstance().lookup(ISOUpdatePPLimitExe.class);
      try {
        service.updateExecutedNumByOpenOrClose(paras);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

}
