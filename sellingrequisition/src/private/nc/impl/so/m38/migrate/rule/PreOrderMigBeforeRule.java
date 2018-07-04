package nc.impl.so.m38.migrate.rule;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.so.m38.migrate.action.QuerPreOrderMigStateAction;
import nc.impl.so.m38.migrate.action.QuerySaleOrgAction;
import nc.impl.so.m38.migrate.constant.PreorderMigState;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.pub.util.ListUtil;

public class PreOrderMigBeforeRule {

  public void check() throws BusinessException {
    // 1.��ȡԤ����Ǩ�Ʊ�ǣ��ж��Ƿ��Ѿ�������Ǩ
    QuerPreOrderMigStateAction migState = new QuerPreOrderMigStateAction();
    if(migState.getPreOrderMigStatus() == PreorderMigState.FINISHED){
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
          "4006012_0", "04006012-0123")/* Ԥ����Ǩ��ֻ��ִ��һ�Ρ� */);
    }
    
    // 2.�Ƿ������˵�������ģ��, δ���ã����쳣����ʾδ����
    if (!SysInitGroupQuery.isOPCEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006012_0", "04006012-0110")/* ��������ģ��δ���ã��޷������������� */);
    }

    // 3.�ж�����Ԥ��������֯�����Ƿ���δ��ѡ���������ۡ��ģ�����У�������쳣��ʾ
    QuerySaleOrgAction action = new QuerySaleOrgAction();
    Map<String, List<String>> groupOrg_CodeMap = action.getSaleOrgNotElectSaleOrgs();
    if(groupOrg_CodeMap.size() > 0){
      hint(groupOrg_CodeMap);
    }
  }

  private void hint(Map<String, List<String>> groupOrg_CodeMap) throws BusinessException {
    if (groupOrg_CodeMap.size() > 0) {
      StringBuilder sb = new StringBuilder();
      Iterator<Entry<String, List<String>>> it = groupOrg_CodeMap.entrySet().iterator();
      while (it.hasNext()) {
        Entry<String, List<String>> entry = it.next();
        String groupCode = entry.getKey();
        List<String> orgCodes = entry.getValue();
        sb.append("\n");
        sb.append(NCLangResOnserver.getInstance().getStrByID("4006012_0","04006012-0111")/*���ű���*/);
        sb.append(":");
        sb.append(groupCode);
        sb.append(",");
        sb.append(NCLangResOnserver.getInstance().getStrByID("4006012_0","04006012-0112")/*��֯����*/);
        sb.append(":");
        sb.append(Arrays.toString(ListUtil.toArray(orgCodes)));
        sb.append(";");
      }

      String msg = sb.substring(0, sb.length() - 1);
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006012_0", "04006012-0114", null, new String[] {
            msg
          })/* ������Ԥ��������֯����δ��ѡ���������ۡ�ѡ����ֶ���ѡ�����µ��Ǩ�ư�ť����Ǩ�ƣ���Ӧ�ļ�������֯����Ϊ��{0} */);
    }
  }
}
