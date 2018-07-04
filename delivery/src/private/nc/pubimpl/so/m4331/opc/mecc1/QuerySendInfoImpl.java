package nc.pubimpl.so.m4331.opc.mecc1;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.so.m4331.opc.mecc1.IQuerySendInfo;
import nc.pubitf.so.m4331.opc.mecc1.ReturnSendInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryBVO;

/**
 * ���ݷ���������id��ѯ��������������ϵ�ˡ�������ϵ�绰��Ҫ���ջ�����
 * 
 * @since 6.0
 * @version 2011-12-29 ����09:12:05
 * @author ����
 */
public class QuerySendInfoImpl implements IQuerySendInfo {

  @Override
  public ReturnSendInfoVO[] query(String[] bids) throws BusinessException {

    ReturnSendInfoVO[] rsivo = null;
    if (null == bids || bids.length == 0) {
      ExceptionUtils.wrappBusinessException("��ǰ�������ӱ�IDΪ�ա�");/*-=notranslate=-*/
    }
    try {
      // Ҫ��ѯ��ʵ��������
      String[] entityNames =
          new String[] {
            DeliveryBVO.CDELIVERYBID, DeliveryBVO.CSENDPERSONID,
            DeliveryBVO.DRECEIVEDATE, DeliveryBVO.VSENDTEL
          };
      VOQuery<DeliveryBVO> query =
          new VOQuery<DeliveryBVO>(DeliveryBVO.class, entityNames);
      // ���ݷ������ӱ�ID����������ѯ
      DeliveryBVO[] debvo = query.query(bids);
      if (null == debvo || debvo.length == 0) {
        return new ReturnSendInfoVO[0];
      }
      int len = debvo.length;
      rsivo = new ReturnSendInfoVO[len];
      // ��ԭ�������鸳ֵ�¶�������
      for (int i = 0; i < len; i++) {
        rsivo[i] = new ReturnSendInfoVO();
        // ������ϵ��
        rsivo[i].setCsendpersonid(debvo[i].getCsendpersonid());
        // ������ϵ�绰
        rsivo[i].setVsendtel(debvo[i].getVsendtel());
        // Ԥ�Ƶ�������
        rsivo[i].setDreceivedate(debvo[i].getDreceivedate().toString());
        // �������ӱ�ID
        rsivo[i].setCdeliverybid(debvo[i].getCdeliverybid());
      }

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return rsivo;
  }
}
