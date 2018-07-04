package nc.bs.so.m33.biz.m4c.action.manual;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import nc.bs.so.m33.biz.m4c.action.outrush.CancelOutRushFor4CAction;
import nc.bs.so.m33.biz.m4c.action.outrush.OutRushFor4CAction;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBizBP;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

public class SaleOutManualRushAction {

  public void manualOutRush(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos) {

    // ��������
    SquareOutVOUtils.getInstance().fill4CIDPkOrgToHead(bluevos);
    SquareOutVOUtils.getInstance().fill4CIDPkOrgToHead(redvos);

    // ����Գ�
    new OutRushFor4CAction().execOutRush(bluevos, redvos);

  }

  public void manualUnOutRush(SquareOutViewVO[] vos) {
    // ��������
    SquareOutVOUtils.getInstance().fill4CIDPkOrgToHead(vos);

    // �Ƿ����ȡ������Գ�
    checkETREGForCancelOutRush(vos);
    
    // �������ֳ��ⵥȡ������Գ�
    if (vos[0].getItem().getNnum().compareTo(UFDouble.ZERO_DBL) > 0) {
      new CancelOutRushFor4CAction().cancelBlueOutRush(vos);
    }
    // ������ֳ��ⵥȡ������Գ�
    else {
      new CancelOutRushFor4CAction().cancelRedOutRush(vos);
    }
  }
  
  /**
   * ������ⵥ�����ݹ���������Ʒ�����ֹȡ������Գ�
   * @param vos
   */
  public void checkETREGForCancelOutRush(SquareOutViewVO[] vos){
    List<String> list = new LinkedList<String>();
    for(SquareOutViewVO view : vos){
      list.add(view.getItem().getCsalesquarebid());
    }
    String[] bids = list.toArray(new String[list.size()]);
    // ��ѯ��������Ʒ���������۳�����㵥
    SquareOutDetailVO[] sdvos = new QuerySquare4CVOBizBP().queryREGCreditSquareOutDetailVOBySQBID(bids);
    Map<String,SquareOutDetailVO> mapreg = new HashMap<String,SquareOutDetailVO>();
    for(SquareOutDetailVO dvo : sdvos){
      mapreg.put(dvo.getCsalesquarebid(), dvo);
    }
    
    for (SquareOutViewVO view : vos){
      SquareOutBVO bvo = view.getItem();
      UFDouble nestnum = bvo.getNsquareestnum();
      UFDouble nrushnum = bvo.getNarrushnum();
      UFDouble nregnum = bvo.getNsquareregnum();
      // ���ⵥ�Ѿ��ݹ�Ӧ�գ�û�д����س�Ӧ��
      if (!MathTool.isZero(nestnum) && MathTool.isZero(nrushnum)){
       // ExceptionUtils.wrappBusinessException("�������۳��ⵥ�����ݹ�Ӧ�գ����ֹ��û�лس�Ӧ�յ����۳��ⵥȡ������Գ�");
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0143")/*@res "�������۳��ⵥ�����ݹ�Ӧ�գ����ֹ��û�лس�Ӧ�յ����۳��ⵥȡ������Գ�"*/);
      }
      String sqbid = view.getItem().getCsalesquarebid();
      if (!MathTool.isZero(nregnum) && mapreg.get(sqbid)==null){
       // ExceptionUtils.wrappBusinessException("�������۳��ⵥ����������Ʒ�跽�����ֹ��û�з�����Ʒ���������۳��ⵥȡ������Գ�");
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0144")/*@res "�������۳��ⵥ����������Ʒ�跽�����ֹ��û�з�����Ʒ���������۳��ⵥȡ������Գ�"*/);     
      }
    }
  }
}
