package nc.pubitf.so.m33.ic.m4c;

import nc.vo.pub.BusinessException;
import nc.vo.so.m33.m4c.linkqryoutrush.entity.LinkQueryOutRushVO;
import nc.vo.so.m33.m4c.linkqryoutrush.entity.OutRushExeInfoVO;

/**
 * Ϊ���۳��ⵥ�ṩ��ѯ����Գ���Ϣ�ķ���ӿ�
 * 
 * @since 6.0
 * @version 2011-9-7 ����01:19:48
 * @author zhangcheng
 */
public interface ISquareOutRushLinkQuery {

  OutRushExeInfoVO[] outRushLinkQuery(LinkQueryOutRushVO[] paravo)
      throws BusinessException;

}
