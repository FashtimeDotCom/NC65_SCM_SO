package nc.itf.so.m33.ref.pcia.m4635;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pcia.m4635.so.m32.IPCIA4635ForSO32Settle;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.bill.SplitBill;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

/**
 * �������Ĵ�����۳ɱ���ת�������۷�Ʊ������ȡ�������Զ����㴫����ṩ�Ĺ�����
 * 
 * @author zhangby5
 * 
 */
public class PCIA4635For32ServicesUtil {

  private PCIA4635For32ServicesUtil() {
    super();
  }

  /**
   * ���۷�Ʊ��ͨ���㵽�������Ĵ��
   * 
   * @param bills ���۷�Ʊ
   */
  public static void insert4635ForSO32Settle(SquareInvVO[] bills) {
    if(!SysInitGroupQuery.isPCIAEnabled()){
      return ;
    }
    SquareInvVO[] snewvos = splitSquareInvVOs(bills);
    
    IPCIA4635ForSO32Settle bo =
        NCLocator.getInstance().lookup(IPCIA4635ForSO32Settle.class);
    try {
      
      SquareInvViewVO[] svvos =
          SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(snewvos);
      SquareTOPCIARule<SquareInvViewVO> rule=new SquareTOPCIARule<SquareInvViewVO>(null, svvos);
      SquareInvViewVO[] newview= rule.geToPCIASquareVO(); 
      
      if(newview==null || newview.length==0){
        return ;
      }
      SquareInvVO[]  svos=  SquareInvVOUtils.getInstance().combineBill(newview);
      bo.insert4635ForSO32Settle(svos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
  
  /**
   * �ֵ�����
   * 
   * @param sqvos
   * @return
   */
  public static SquareInvVO[] splitSquareInvVOs(SquareInvVO[] sqvos) {
    // ����IA��Ҫ�����ΰ�����Դ����ID+�����֯+�ֿ���зֵ�
    SplitBill<SquareInvVO> spb = new SplitBill<SquareInvVO>();
    spb.appendKey(SquareInvBVO.CSRCID);
    spb.appendKey(SquareInvBVO.CSENDSTOCKORGID);
    spb.appendKey(SquareInvBVO.CSENDSTORDOCID);
    SquareInvVO[] snewvos = spb.split(sqvos);
    return snewvos;
  }

  /**
   * ���۷�Ʊȡ������
   * 
   * @param csrcids ��Դ�������۽���ID
   * @param csrcbids ��Դ�������۽�����ϸID
   */
  public static void delete4635ForSO32UnSettle(String[] csrcids,
      String[] csrcbids) {
    if(!SysInitGroupQuery.isPCIAEnabled()){
      return ;
    }
    IPCIA4635ForSO32Settle bo =
        NCLocator.getInstance().lookup(IPCIA4635ForSO32Settle.class);
    try {
      bo.delete4635ForSO32UnSettle(csrcids, csrcbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
