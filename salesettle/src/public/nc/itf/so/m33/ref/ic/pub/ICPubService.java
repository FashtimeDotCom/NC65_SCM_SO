package nc.itf.so.m33.ref.ic.pub;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.transtype.IInOutTransTypeQueryService;
import nc.vo.ic.transtype.TransTypeExtendVO;
import nc.vo.pub.BusinessException;

public class ICPubService {

  private ICPubService() {
    super();
  }
  
  /**
   * ��������������ȡ���۳��ⵥ��������
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param transTypeCode -- ���۳��ⵥ��������
   * @return
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-8-11 ����09:21:43
   */
  public static TransTypeExtendVO[] queryTransType(String[] transTypeCode)
      throws BusinessException {
    IInOutTransTypeQueryService ioutSvr =
        NCLocator.getInstance().lookup(IInOutTransTypeQueryService.class);
    TransTypeExtendVO[] ttEvos = ioutSvr.query(transTypeCode);
    return ttEvos;
  }

}
