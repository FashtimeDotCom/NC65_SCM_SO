package nc.ui.so.m30.revise.action;

import java.util.ArrayList;
import java.util.List;

import nc.ui.ml.NCLangRes;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * @description
 *              ���۶����޶�����������ȡ���������ύ���ջ�ǰ��Ҫ���˲��ܲ��������ݡ�����մ����۶�����ѯ�����ĵ��ݣ�û���޶���<br>
 *              ���������ԭ�򣺴����۶�����ѯ�����ĵ��ݣ��޶������붩��idһ�¡������͵�����������
 *              ����λ�ã�(nc.bs.pub.pf.pfframe.PlatFormEntryImpl.processAction(
 *              PlatFormEntryImpl.java:117))
 * @scene
 * 
 * @param
 * 
 * 
 * @since 6.5
 * @version 2015-12-5 ����10:45:16
 * @author ����
 */
public class ReviseVOFiltrateUtls {

  /**
   * �ṩ�����۶����޶��ύ���ջء�������ȡ������Ation��processBefore����ʹ��
   * 
   * @param vos
   * @return
   */
  public static Object[] getIsPFlowActionBillVO(Object[] vos) {
    // û���޶��Ķ�������������
    SaleOrderHistoryVO[] salevos = (SaleOrderHistoryVO[]) vos;
    if (salevos == null || salevos.length == 0) {
      return vos;
    }
    List<SaleOrderHistoryVO> salevolist = new ArrayList<>();
    for (SaleOrderHistoryVO salevo : salevos) {
      Integer iversion = salevo.getParentVO().getIversion();
      String saleorderid = salevo.getParentVO().getCsaleorderid();
      String orderhistoryid = salevo.getParentVO().getCorderhistoryid();

      // �޶��İ汾����0�����۶����޶������붩��������һ������²ſ��Խ������̲���

      // ���۶����޶����������۶�������һ��ʱ��һ���Ǵ����۶�����ѯ������VO��������Ҫ�޶������������ȡ���������ύ���ջ�
      // (nc.bs.pub.pf.pfframe.PlatFormEntryImpl.processAction(PlatFormEntryImpl.java:117))
      if ((iversion != null && iversion > 0)
          && !saleorderid.equals(orderhistoryid)) {
        salevolist.add(salevo);
      }
    }
    if (salevolist.size() == 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0533")/*���˵�δ�޶��ĵ��ݺ�û��Ҫ�����ĵ��ݣ�������ѡ�񵥾ݡ�*/);
    }
    return salevolist.toArray(new SaleOrderHistoryVO[salevolist.size()]);
  }

}
