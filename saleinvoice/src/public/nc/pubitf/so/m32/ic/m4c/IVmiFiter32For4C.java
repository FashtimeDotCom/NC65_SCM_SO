package nc.pubitf.so.m32.ic.m4c;

import java.util.List;

import nc.vo.pub.BusinessException;

public interface IVmiFiter32For4C {

  /**
   * �������۷�Ʊ�Ѿ��������ܵļ�¼
   * 
   * @param listBids ����IDS
   * @param ids ��ͷids
   * @return
   * @throws BusinessException
   */
  List<String> getSumBids(List<String> listBids, String[] ids)
      throws BusinessException;

}
