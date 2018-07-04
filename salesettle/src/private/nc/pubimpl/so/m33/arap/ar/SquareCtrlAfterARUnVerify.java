package nc.pubimpl.so.m33.arap.ar;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.itf.arap.forso.IDataFromVerifyForM33;
import nc.pubitf.arap.pub.GetSODataByArapUtils;
import nc.vo.arap.dataforso.DataFromVerifyForM33;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * ���۽���������񷴺����ӿ�ʵ����
 * ��Ҫ������񷴺�����д���۷�Ʊ�����۳�������㵥���ۼ�ԭ�Ҳ���������
 * 
 * @since 6.0
 * @version 2011-9-2 ����10:22:33
 * @author zhangcheng
 */
public class SquareCtrlAfterARUnVerify implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    IDataFromVerifyForM33[] datas =
        new GetSODataByArapUtils().getIDataFromVerifyForM33(event);

    // ����Դ�����۶�����Ӧ�յ����տ�������˵�
    if (datas.length == 0) {
      return;
    }

    // ���������ӿ��е������������ʱ��ͬ����������ȡ��
    DataFromVerifyForM33[] unVerifyDatas =
        new DataFromVerifyForM33[datas.length];
    int i = 0;
    for (IDataFromVerifyForM33 idata : datas) {
      unVerifyDatas[i] = new DataFromVerifyForM33();
      unVerifyDatas[i].setSrcID(idata.getSrcID());
      unVerifyDatas[i].setSrcBID(idata.getSrcBID());
      unVerifyDatas[i].setSrcBillType(idata.getSrcBillType());
      unVerifyDatas[i].setPaybillmny(MathTool.oppose(idata.getPayBillmny()));
      i++;
    }

    try {
      new SquareCtrlAfterARVerifyPubProcess().doAction(unVerifyDatas);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
