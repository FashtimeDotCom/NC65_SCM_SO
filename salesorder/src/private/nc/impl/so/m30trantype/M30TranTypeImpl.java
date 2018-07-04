package nc.impl.so.m30trantype;

import java.util.ArrayList;

import nc.bs.bd.cache.CacheProxy;
import nc.bs.pub.pf.ITranstypeBiz;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.impl.so.m30trantype.rule.CheckSaleModeEditableRule;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.transtype.CheckTransTypeRef;

public class M30TranTypeImpl implements ITranstypeBiz {

  @Override
  public void deleteTransType(Object tranTypeVO) throws BusinessException {
    M30TranTypeVO vo = (M30TranTypeVO) tranTypeVO;
    BatchOperateVO boVO = new BatchOperateVO();
    boVO.setDelObjs(new M30TranTypeVO[] {
      vo
    });
    this.maintainAction(boVO);
    // ���»���
    CacheProxy.fireDataInserted("SO_M30TRANTYPE");
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
    M30TranTypeVO vo = (M30TranTypeVO) tranTypeVO;
    BatchOperateVO boVO = new BatchOperateVO();
    boVO.setAddObjs(new M30TranTypeVO[] {
      vo
    });
    this.maintainAction(boVO);
    // ���»���
    CacheProxy.fireDataInserted("SO_M30TRANTYPE");
  }

  @Override
  public void updateTransType(Object tranTypeVO) throws BusinessException {
    M30TranTypeVO vo = (M30TranTypeVO) tranTypeVO;

    // ����У�飺���۶�����ĳ���������ͱ����ú�ֱ�����ͱ�Ǻ�����ģʽ���������޸�
    CheckTransTypeRef<SaleOrderHVO, M30TranTypeVO> check =
        new CheckTransTypeRef<SaleOrderHVO, M30TranTypeVO>(SaleOrderHVO.class,
            M30TranTypeVO.class);
    check.checkRefByFields(SaleOrderHVO.CTRANTYPEID, vo.getCtrantypeid(), vo,
        new String[] {M30TranTypeVO.FDIRECTTYPE
        });

    BatchOperateVO boVO = new BatchOperateVO();
    boVO.setUpdObjs(new M30TranTypeVO[] {
      vo
    });
    this.maintainAction(boVO);
    // ���»���
    CacheProxy.fireDataInserted("SO_M30TRANTYPE");
  }

  private void maintainAction(BatchOperateVO bOVO) {
    BatchSaveAction<M30TranTypeVO> action =
        new BatchSaveAction<M30TranTypeVO>();
    ICompareRule<M30TranTypeVO> updateRule =
        new CheckSaleModeEditableRule();
    // ���۶�����������ǰ����
    action.getUpdateProcesser().addBeforeRule(updateRule);
    action.batchSave(bOVO);
  }

}
