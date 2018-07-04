package nc.itf.so.m33.biz.canclesquare;

import nc.vo.pub.SuperVO;
import nc.vo.so.m33.enumeration.SquareType;

/**
 * ���۽�����󹤳�
 * 
 * @author zhangcheng
 * 
 */
public interface ICancelSquareActionFactory<T extends SuperVO> {

  /**
   * @return ȡ�����㶯��
   */
  ICancelSquareAction<T> createCancelSquareAction(SquareType type);

}
