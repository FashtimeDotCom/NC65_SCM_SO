package nc.itf.so.m38trantype;

import nc.vo.pub.BusinessException;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;

/**
 * <b> Ԥ�����������ͷ���ӿ� </b>
 * 
 * 
 * @since 6.0
 * @version 2010-11-3 ����10:48:33
 * @author ��־ΰ
 */
public interface IM38TranTypeService {

  M38TranTypeVO queryTranTypeVO(String ctrantypeid) throws BusinessException;

  M38TranTypeVO[] queryTranTypeVOs(String[] ctrantypeids)
      throws BusinessException;
}
