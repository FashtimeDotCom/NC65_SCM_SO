package nc.vo.so.m4331.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class DeliveryVoUtil {

  /**
   * �ѷ���������Ӧ����ֵת��������
   * 
   * @param vos
   */
  public void absDeliveryVO(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        UFDouble num = MathTool.abs(bvo.getNnum());
        bvo.setNnum(num);
        UFDouble nastnum = MathTool.abs(bvo.getNastnum());
        bvo.setNastnum(nastnum);
        UFDouble nqtunitnum = MathTool.abs(bvo.getNqtunitnum());
        bvo.setNqtunitnum(nqtunitnum);
      }
    }
  }

  /**
   * ��������id�����Ӧ�����ϵĹر����޺��ݲ�
   */
  // public Map<String, MaterialVO> getMaterials(String[] pk_materials) {
  // try {
  // String[] str = new String[2];
  // // ���ϳ���ر�����
  // str[0] = MaterialVO.OUTCLOSELOWERLIMIT;
  // // ���Ϲر��ݲ�
  // str[1] = MaterialVO.OUTTOLERANCE;
  // Map<String, MaterialVO> map =
  // MaterialPubService.queryMaterialBaseInfo(pk_materials, str);
  // return map;
  // }
  // catch (Exception e) {
  // ExceptionUtils.wrappBusinessException(e.getMessage());
  // }
  // return null;
  // }

  /**
   * ���ݵ������ͻ�ò�ͬ�Ļ�дvo
   * 
   * @author ף����
   * @time 2010-8-17 ����04:55:27
   */
  public Map<String, List<DeliveryViewVO>> getRewriteList(
      List<DeliveryViewVO> list) {
    // ������Ҫ��д������ keyΪ��������
    Map<String, List<DeliveryViewVO>> map =
        new HashMap<String, List<DeliveryViewVO>>();
    List<DeliveryViewVO> saleList = new ArrayList<DeliveryViewVO>();
    List<DeliveryViewVO> tranList = new ArrayList<DeliveryViewVO>();
    for (int i = 0; i < list.size(); i++) {
      DeliveryViewVO view = list.get(i);
      String vsrctype = view.getItem().getVsrctype();
      if (vsrctype.equals(SOBillType.Order.getCode())) {
        saleList.add(view);
      }
      if (vsrctype.equals(TOBillType.TransOrder.getCode())) {
        tranList.add(view);
      }
    }
    map.put(SOBillType.Order.getCode(), saleList);
    map.put(TOBillType.TransOrder.getCode(), tranList);
    return map;
  }

  /**
   * �ʼ�vo�е�����ֵת���ɸ���
   * 
   * @param vos
   */
  public void opposeDeliverycheckVO(DeliveryCheckVO[] vos) {
    for (DeliveryCheckVO vo : vos) {
      UFDouble nnum = MathTool.oppose(vo.getNnum());
      vo.setNnum(nnum);
      UFDouble nastnum = MathTool.oppose(vo.getNastnum());
      vo.setNastnum(nastnum);
      // UFDouble nqtunitnum = MathTool.oppose(vo.getNqtunitnum());
      // vo.setNqtunitnum(nqtunitnum);
    }
  }

  /**
   * ����״̬���ϣ�ֵ��
   * 
   * @return
   */
  public Integer[] getBillStatusValue() {
    Integer[] statusValue =
        new Integer[] {
          BillStatus.FREE.getIntegerValue(),
          BillStatus.AUDITING.getIntegerValue(),
          BillStatus.AUDIT.getIntegerValue(),
          BillStatus.NOPASS.getIntegerValue(),
          BillStatus.CLOSED.getIntegerValue()
        };
    return statusValue;
  }

  /**
   * ����״̬���ϣ����ƣ�
   * 
   * @return
   */
  public String[] getBillStatusName() {
    String[] statusName =
        new String[] {
          BillStatus.FREE.getName(), BillStatus.AUDITING.getName(),
          BillStatus.AUDIT.getName(), BillStatus.NOPASS.getName(),
          BillStatus.CLOSED.getName()
        };
    return statusName;
  }
}
