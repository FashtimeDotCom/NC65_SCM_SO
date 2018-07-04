package nc.bs.so.m33.biz.m32.bp.check;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.pubitf.so.m33.self.pub.ISquare434CQuery;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * ���۷�Ʊ�����㵥����У��BP
 * @since 6.0
 * @version 2011-8-29 ����08:13:57
 * @author zc
 */
public class SquareInvoiceCheckBP {
  
  /**
   * ������γ��ⵥ�����ݹ���������Ʒ����Ʊ����ȡ������
   * @param sqvos
   */
  public void checkETREGForCancelSquare(SquareInvVO[] sqvos){
    String ethit = NCLangResOnserver
    .getInstance().getStrByID("4006010_0", "04006010-0107", null,
        new String[] {})/*�������۳��ⵥ�����ݹ�Ӧ�գ����ֹ��û�лس�Ӧ�յ��������۷�Ʊȡ�����㣡*/;
    String reghit = NCLangResOnserver
    .getInstance().getStrByID("4006010_0", "04006010-0108", null,
        new String[] {})/*�������۳��ⵥ����������Ʒ�����ֹ��û�з�����Ʒ���������۷�Ʊȡ�����㣡*/;
    checkETREG(sqvos,ethit,reghit);
  }
  
  /**
   * ������γ��ⵥ�����ݹ���������Ʒ����Ʊ�������ɶԳ巢Ʊ
   * @param sqvos
   */
  public void checkETREGForCreateRushInvoice(SquareInvVO[] sqvos){
   // String ethit = "�������۳��ⵥ�����ݹ�Ӧ�գ����ֹ��û�лس�Ӧ�յ��������۷�Ʊ���ɶԳ巢Ʊ";
   // String reghit = "�������۳��ⵥ����������Ʒ�����ֹ��û�з�����Ʒ���������۷�Ʊ���ɶԳ巢Ʊ";
    String ethit = NCLangResOnserver
    .getInstance().getStrByID("4006010_0", "04006010-0146", null,
        new String[] {})/*�������۳��ⵥ�����ݹ�Ӧ�գ����ֹ��û�лس�Ӧ�յ��������۷�Ʊ���ɶԳ巢Ʊ*/;
    String reghit = NCLangResOnserver
    .getInstance().getStrByID("4006010_0", "04006010-0147", null,
        new String[] {})/*�������۳��ⵥ����������Ʒ�����ֹ��û�з�����Ʒ���������۷�Ʊ���ɶԳ巢Ʊ*/;
    checkETREG(sqvos,ethit,reghit);
  }
  
  private void checkETREG(SquareInvVO[] sqvos,String ethit,String reghit){
    // ���۳�������ѯ�ӿ�
    ISquare434CQuery square4cQry =
        NCLocator.getInstance().lookup(ISquare434CQuery.class);
    // ��ѯ���γ��ⵥ�ݹ�Ӧ�ռ�¼
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(sqvos, SquareInvBVO.CSRCBID,
            String.class);
    SquareOutViewVO[] outETREGviews =
        square4cQry.queryETIncomeREGCostBidBy4CBID(outBids);
    if (!VOChecker.isEmpty(outETREGviews)) {

      // <���γ��ⵥbid,������㵥>
      Map<String, SquareOutViewVO> moutbiddvo =
          new HashMap<String, SquareOutViewVO>();
      for (SquareOutViewVO view : outETREGviews) {
        moutbiddvo.put(view.getItem().getCsquarebillbid(), view);
      }

      // ���γ��ⵥ���ݹ�Ӧ��
      for (SquareInvVO svo : sqvos) {
        for (SquareInvBVO bvo : svo.getChildrenVO()) {
          // ���۷�Ʊ
          UFDouble nrushnum = bvo.getNarrushnum();
          UFDouble nregnum = bvo.getNsquareregnum();
          Integer iarsqtype = bvo.getFpreartype();
          Integer iiasqtype = bvo.getFpreiatype();

          // ���γ��ⵥ��������
          SquareOutViewVO outview = moutbiddvo.get(bvo.getCsrcbid());
          // ���۷�Ʊ���β��ǳ��ⵥ����
          if (VOChecker.isEmpty(outview)) {
            continue;
          }
          Integer ioutarsqtype = outview.getItem().getFpreartype();
          Integer ioutiasqtype = outview.getItem().getFpreiatype();
          UFDouble noutetnum = outview.getItem().getNsquareestnum();
          UFDouble noutregnum = outview.getItem().getNsquareregnum();

          // ���۷�Ʊ��û�лس�Ӧ��ͬʱ���γ��ⵥ���ݹ�Ӧ��
          if (SquareType.SQUARETYPE_ET.getIntValue() == ioutarsqtype.intValue()
              && !VOChecker.isEmpty(iarsqtype)
              && SquareType.SQUARETYPE_AR.getIntValue() == iarsqtype.intValue()
              && !MathTool.isZero(noutetnum) && MathTool.isZero(nrushnum)
              && moutbiddvo.containsKey(bvo.getCsrcbid())) {
            ExceptionUtils.wrappBusinessException(ethit);
          }
          // ���۷�Ʊ��û�з�����Ʒͬʱ���γ��ⵥ�з�����Ʒ
          if (SquareType.SQUARETYPE_REG_DEBIT.getIntValue() == ioutiasqtype
              .intValue()
              && !VOChecker.isEmpty(iiasqtype)
              && SquareType.SQUARETYPE_IA.getIntValue() == iiasqtype.intValue()
              && !MathTool.isZero(noutregnum)
              && MathTool.isZero(nregnum)
              && moutbiddvo.containsKey(bvo.getCsrcbid())) {
            ExceptionUtils.wrappBusinessException(reghit);
          }
        }
      }
    }
  }
  
}
