package nc.itf.so.m33.ref.pcia.m4635;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pcia.m4635.so.m4c.IPCIA4635ForSO4CSettle;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

/**
 * �������Ĵ�����۳ɱ���ת�������۳�������ṩ�Ĺ�����
 * 
 * @author zhangby5
 * 
 */
public class PCIA4635For4CServicesUtil {

  private PCIA4635For4CServicesUtil() {
    super();
  }

  /**
   * ���۳��ⵥ���������������Ĵ�����۳ɱ���ת��
   * 
   * @param bills ���۳������㵥VO
   */
  public static void insert4635ForSO4CSettle(SquareOutVO[] bills) {
    if(!SysInitGroupQuery.isPCIAEnabled()){
      return ;
    }
    IPCIA4635ForSO4CSettle bo =
        NCLocator.getInstance().lookup(IPCIA4635ForSO4CSettle.class);
    try {
      SquareOutViewVO[] svvos =
          SquareOutVOUtils.getInstance().changeToSaleSquareViewVO(bills);
      SquareTOPCIARule<SquareOutViewVO> rule=new SquareTOPCIARule<SquareOutViewVO>(null, svvos);
      SquareOutViewVO[] newview= rule.geToPCIASquareVO(); 
      
      if(newview==null || newview.length==0){
        return ;
      }
      SquareOutVO[]  svos=  SquareOutVOUtils.getInstance().combineBill(newview);
      bo.insert4635ForSO4CSettle(svos);
      
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  /**
   * ���۳��ⵥȡ������ɾ����Ӧ�������
   * 
   * @param csrcids ��Դ����ID�����۽���ID��
   * @param csrcbids ��Դ����ID�����۽�����ϸID��
   */
  public static void delete4635ForSO4CUnSettle(String[] csrcids,
      String[] csrcbids) {
    if(!SysInitGroupQuery.isPCIAEnabled()){
      return ;
    }
    IPCIA4635ForSO4CSettle bo =
        NCLocator.getInstance().lookup(IPCIA4635ForSO4CSettle.class);
    try {
      bo.delete4635ForSO4CUnSettle(csrcids, csrcbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
