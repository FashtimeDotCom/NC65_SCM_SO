package nc.bs.so.m33.biz.m32.bp.square.toia;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m33.ref.pcia.m4635.PCIA4635For32ServicesUtil;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.scmpub.res.billtype.IABillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m32.entity.SquareInvVO;

/**
 * ���۷�Ʊ�����㵥�����۳ɱ���ת��vo����������
 * ��Ҫ����
 * 1.vo����ǰ�����ε���ͳһ���зֵ�����
 * 2.����vo����
 * 
 * @since 6.0
 * @version 2011-11-25 ����12:59:55
 * @author zhangcheng
 */
public class VOChange4332ToI5Util {

  /**
   * ����IA��Ҫ�����ΰ�����Դ����ID+�����֯+�ֿ���зֵ���Ȼ��vo����
   * 
   * @param sqvos
   * @return
   */
  public I5BillVO[] exchange(SquareInvVO[] sqvos) {

    SquareInvVO[] snewvos = PCIA4635For32ServicesUtil.splitSquareInvVOs(sqvos);

    // ����vo����
    I5BillVO[] i5vos =
        (I5BillVO[]) PfServiceScmUtil.executeVOChange(
            SOBillType.SquareInvoice.getCode(), IABillType.XSCBJZ.getCode(),
            snewvos);

    return i5vos;
  }

}
