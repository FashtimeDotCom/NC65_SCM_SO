package nc.pubimpl.so.m33.arap.ar;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.itf.arap.forso.IDataFromVerifyForM33;
import nc.pubitf.arap.pub.GetSODataByArapUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ���۽��������������ӿ�ʵ����
 * ��Ҫ������������д���۷�Ʊ�����۳�������㵥���ۼ�ԭ�Ҳ���������
 * 
 * @since 6.0
 * @version 2011-9-2 ����10:20:32
 * @author zhangcheng
 */
public class SquareCtrlAfterARVerify implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    IDataFromVerifyForM33[] datas =
        new GetSODataByArapUtils().getIDataFromVerifyForM33(event);
    // ����Դ�����۶�����Ӧ�յ����տ�������˵�
    if (datas.length == 0) {
      return;
    }

    try {
      new SquareCtrlAfterARVerifyPubProcess().doAction(datas);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

}
