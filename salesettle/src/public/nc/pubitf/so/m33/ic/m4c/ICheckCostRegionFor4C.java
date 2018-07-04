package nc.pubitf.so.m33.ic.m4c;

import java.util.Map;

import nc.vo.pub.BusinessException;

public interface ICheckCostRegionFor4C {

  /**
   * �����������������۳��ⵥ�����ʱ��У����ⵥ����ɱ��������۷�Ʊ������ĳɱ����Ƿ�һ��
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param srcbid_costregid --- Map<�������۷�Ʊ��id,�������ɱ���id>
   *          <p>Map��ֻ������۳��ⵥ��������Դ�����۷�Ʊ�ļ�¼
   * 
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-8-16 ����03:57:49
   */
  void checkCostRegion(Map<String, String> srcbid_costregid)
      throws BusinessException;

}
