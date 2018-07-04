package nc.ui.so.m33.pub.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.so.m33.mansquare.pub.ManualSquarePubEditProcess;
import nc.ui.so.m33.pub.SquareOutManageModel;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.pub.constant.QueryFlag;

public class SquareOutSettleRefreshAction extends SquarePubRefreshAction{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
    
    //���ÿɱ༭��
    SquareOutManageModel smodel = (SquareOutManageModel) this.getModel();
    IQueryScheme queryScheme = smodel.getQueryScheme();
    if (null == queryScheme) {
      return;
    }
    
    BillListPanel listpanel = this.getListView().getBillListPanel();
    // ���ñ��ν��������ı༭��
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    QueryCondition qc = qsp.getQueryCondition(QueryFlag.SQUAREFLAG);
    Object[] obj = qc.getValues();
    int flag = Integer.parseInt(obj[0].toString());
    if (flag == QueryFlag.SQUARE) {
      // ���ÿ�Ʊ�ͻ��ͼ۸�ı༭��
      new ManualSquarePubEditProcess().initEditEnable(listpanel);
    }
    super.getListView().setQueryFlag(flag);
    // 2013-07-03 tianft �Գ�ʱ��������༭���ν�������
    if (flag == QueryFlag.SQUARED || flag == QueryFlag.UNOUTRUSHBLUE
        || flag == QueryFlag.UNOUTRUSHRED) {
      listpanel.getBodyItem(SquareOutBVO.NTHISNUM).setEdit(false);
    }
    else {
      listpanel.getBodyItem(SquareOutBVO.NTHISNUM).setEdit(true);
    }
  }
}
