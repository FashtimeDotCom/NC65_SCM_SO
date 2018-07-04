package nc.impl.so.m38trantype;

import java.util.ArrayList;

import nc.bs.pub.pf.ITranstypeBiz;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;

/**
 * <b> Ԥ�����������ͷ���ӿ� ʵ��</b>
 * 
 * ��������:2010-03-29 18:26:55
 * 
 * @author ��־ΰ
 * @version 6.0
 */
public class M38TranTypeImpl implements ITranstypeBiz {

  @Override
  public void deleteTransType(Object tranTypeVO) throws BusinessException {
    M38TranTypeVO vo = (M38TranTypeVO) tranTypeVO;
    BatchOperateVO boVO = new BatchOperateVO();
    boVO.setDelObjs(new M38TranTypeVO[] {
      vo
    });
    this.maintainAction(boVO);
  }

  @Override
  public void execOnDelPublish(BilltypeVO transTypeVO,
      ArrayList<FuncRegisterVO> funcVOs) throws BusinessException {
    return;
  }

  @Override
  public void execOnPublish(String nodecode, String newNodecode,
      boolean isExecFunc) throws BusinessException {
    return;
  }

  @Override
  public void saveTransType(Object tranTypeVO) throws BusinessException {
    M38TranTypeVO vo = (M38TranTypeVO) tranTypeVO;
    BatchOperateVO boVO = new BatchOperateVO();
    boVO.setAddObjs(new M38TranTypeVO[] {
      vo
    });
    this.maintainAction(boVO);
  }

  @Override
  public void updateTransType(Object tranTypeVO) throws BusinessException {
    M38TranTypeVO vo = (M38TranTypeVO) tranTypeVO;
    BatchOperateVO boVO = new BatchOperateVO();
    boVO.setUpdObjs(new M38TranTypeVO[] {
      vo
    });
    this.maintainAction(boVO);
  }

  /**
   * <b> BatchOperateVO����ɾ���Ĵ����� </b>
   * 
   * ��������:2010-03-29 18:26:55
   * 
   * @param BatchOperateVO
   * @author ��־ΰ
   */
  private void maintainAction(BatchOperateVO bOVO) {
    BatchSaveAction<M38TranTypeVO> action =
        new BatchSaveAction<M38TranTypeVO>();
    action.batchSave(bOVO);
  }
}
