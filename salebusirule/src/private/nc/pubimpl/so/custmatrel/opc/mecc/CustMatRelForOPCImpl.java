package nc.pubimpl.so.custmatrel.opc.mecc;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.pubitf.so.custmatrel.ICustMatRelFor30;
import nc.pubitf.so.custmatrel.opc.mecc.CustMatRelParaForOPCVO;
import nc.pubitf.so.custmatrel.opc.mecc.ICustMatRelForOPC;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ���ۿͻ����Ϲ�ϵ���������ͳһ���������ṩ�Ľӿ�
 * ���������������֯+�ͻ�+���ϼ��ϵõ��������۵Ŀͻ�+���ϼ���
 * 
 * @since 6.0
 * @version 2012-2-28 ����16:20:21
 * @author ����
 */
public class CustMatRelForOPCImpl implements ICustMatRelForOPC {

  @Override
  public CustMatRelParaForOPCVO[] filterData(CustMatRelParaForOPCVO[] paravos)
      throws BusinessException {
    List<CustMatRelParaForOPCVO> custMatRel =
        new ArrayList<CustMatRelParaForOPCVO>();
    try {
      // ת��VO����
      int len = paravos.length;
      CustMatRelParaVO[] cstmrlVO = new CustMatRelParaVO[len];
      for (int i = 0; i < len; i++) {
        cstmrlVO[i] = new CustMatRelParaVO();
        cstmrlVO[i].setPk_org(paravos[i].getPk_org());
        cstmrlVO[i].setPk_material(paravos[i].getPk_material());
        cstmrlVO[i].setPk_customer(paravos[i].getPk_customer());
      }
      // ���ͻ������Ϲ�ϵ
      ICustMatRelFor30 service =
          NCLocator.getInstance().lookup(ICustMatRelFor30.class);
      UFBoolean[] flagArray = service.getCustMatRelSaleFlag(cstmrlVO);
      for (int i = 0; i < flagArray.length; i++) {
        // װ�غϷ��Ŀͻ�������
        if (flagArray[i].booleanValue()) {
          custMatRel.add(paravos[i]);
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return custMatRel.toArray(new CustMatRelParaForOPCVO[custMatRel.size()]);
  }
}
