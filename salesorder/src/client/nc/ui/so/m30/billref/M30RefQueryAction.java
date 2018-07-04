package nc.ui.so.m30.billref;

import java.util.List;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.so.pub.query.SOQuerySchemeUtils;

/**
 * �������۶�����ѯ��ť����
 * <p>�������дexecuteQuery����Լ����߼�</p>
 * 
 * @since 6.0
 * @version 2011-6-18 ����02:45:55
 * @author ��־ΰ
 */
public class M30RefQueryAction extends
    nc.ui.pubapp.billref.src.action.QueryAction {

  private static final long serialVersionUID = 5527544800630921634L;

  private SOQuerySchemeUtils querySchemeUtils;

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    this.appendBusitypeWhr(queryScheme);
    this.appendTranTypeWhr(queryScheme);
    super.executeQuery(queryScheme);
  }

  private void appendBusitypeWhr(IQueryScheme qs) {
    List<String> busiLst =
        this.getRefContext().getBillReferQuery().getBusitypes();
    if (null != busiLst) {
      this.getQuerySchemeUtils().storeBusitype(qs, busiLst);
    }
  }

  private void appendTranTypeWhr(IQueryScheme qs) {
    List<String> tranList =
        this.getRefContext().getBillReferQuery().getTranstypes();
    // �����ν������ͼ��Ϸŵ�sheme�У��ں�̨ȡ��ƴ����Ӧ������
    if (null != tranList) {
      this.getQuerySchemeUtils().storeTranType(qs, tranList);
    }
  }

  private SOQuerySchemeUtils getQuerySchemeUtils() {
    if (this.querySchemeUtils == null) {
      this.querySchemeUtils = new SOQuerySchemeUtils();
    }
    return this.querySchemeUtils;
  }
}
