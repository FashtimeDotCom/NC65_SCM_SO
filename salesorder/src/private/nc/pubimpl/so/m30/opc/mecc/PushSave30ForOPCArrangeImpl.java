package nc.pubimpl.so.m30.opc.mecc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m30.ISaleOrgPubService;

import nc.pubitf.so.m30.opc.mecc.IPushSave30ForOPCArrange;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.env.BSContext;

/**
 * ���۶����ṩ������ͳһ�������ĵ���ʽ����ERP���۶����ӿ�ʵ��
 * 
 * @since 6.1
 * @version 2012-02-23 ����04:37:51
 * @author ����
 */
public class PushSave30ForOPCArrangeImpl implements IPushSave30ForOPCArrange {

  @Override
  public void pushSave(SaleOrderVO[] paravo,
      Map<String, Boolean> businessCheckMap) throws BusinessException {
    try {
      List<SaleOrderBVO> needidlist = new ArrayList<SaleOrderBVO>();
      Map<String, SaleOrderBVO> cfirstbidMap =
          new HashMap<String, SaleOrderBVO>();
      for (SaleOrderVO vo : paravo) {
        SaleOrderBVO[] bvos = vo.getChildrenVO();
        for (SaleOrderBVO bvo : bvos) {
          bvo.setStatus(VOStatus.NEW);
          needidlist.add(bvo);
          cfirstbidMap.put(bvo.getCfirstbid(), bvo);
        }
      }
      // ���۹���ȫ����ID add zhangby5
      this.fillBID(needidlist);
      // ������Ʒ�ж�Ӧ��Դ������ID add zhangby5
      this.changeClargesssrcidForESO(paravo, cfirstbidMap);
      PfUserObject[] userobjs = new PfUserObject[paravo.length];
      for (int i = 0; i < paravo.length; i++) {
        userobjs[i] = new PfUserObject();
        userobjs[i].setBusinessCheckMap(businessCheckMap);
      }
      // �������쳣 add by zhangby5
      if (businessCheckMap != null && businessCheckMap.size() > 0) {
        for (Entry<String, Boolean> entry : businessCheckMap.entrySet()) {
          if (entry.getKey().equals(BusinessCheck.ATPCheck.getCheckCode())) {
            if (entry.getValue().booleanValue()) {
              BSContext.getInstance()
                  .setSession(entry.getKey(), UFBoolean.TRUE);
            }
            else {
              BSContext.getInstance().setSession(entry.getKey(),
                  UFBoolean.FALSE);
            }
          }
          else {
            BSContext.getInstance()
                .setSession(entry.getKey(), entry.getValue());
          }
        }
      }
      // �������쳣 end

      // �������۶�������ű�
      PfServiceScmUtil.processBatch("WRITE", SOBillType.Order.getCode(),
          paravo, userobjs, null);
    }
    catch (Exception exception) {
      ExceptionUtils.marsh(exception);
    }
  }

  /**
   * ��ȫ����ID��Ŀ����Ϊ�˸���Ʒ�����ö�Ӧ����Դ������ID
   * 
   * @param needidlist
   */
  private void fillBID(List<SaleOrderBVO> needidlist) {
    if (needidlist.size() > 0) {
      ISaleOrgPubService service =
          NCLocator.getInstance().lookup(ISaleOrgPubService.class);
      try {
        String[] ids = service.getOIDArray(needidlist.size());
        for (int i = 0; i < needidlist.size(); i++) {
          needidlist.get(i).setCsaleorderbid(ids[i]);
        }
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /**
   * ����������������Ʒ�ж�Ӧ��Դ������ID(clargesssrcid)ʱ������û���ӱ�ID�����Խ�clargesssrcid��Ϊ��Դͷ�ӱ�ID��
   * �˷����ǽ���Ʒ�е�clargesssrcid��Ϊ��Ӧ����Դ������ID
   * 
   * @param paravo
   * @param cfirstbidMap
   */
  private void changeClargesssrcidForESO(SaleOrderVO[] paravo,
      Map<String, SaleOrderBVO> cfirstbidMap) {
    for (SaleOrderVO vo : paravo) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        if (this.isNotFromESO(bvo)) {
          continue;
        }
        if (this.isNotBuylargesses(bvo)) {
          continue;
        }
        SaleOrderBVO buyVO = cfirstbidMap.get(bvo.getClargesssrcid());
        bvo.setClargesssrcid(buyVO.getCsaleorderbid());
      }
    }
  }

  private boolean isNotFromESO(SaleOrderBVO bvo) {
    if (!PubAppTool.isNull(bvo.getVfirsttype())
        && "ECC1".equals(bvo.getVfirsttype())) {
      return false;
    }
    return true;
  }

  private boolean isNotBuylargesses(SaleOrderBVO bvo) {
    if (bvo.getBlargessflag() != null && bvo.getBlargessflag().booleanValue()) {
      String largesssrcid = bvo.getClargesssrcid();
      if (PubAppTool.isNull(largesssrcid) || largesssrcid.equals("~")) {
        return true;
      }
      return false;
    }
    return true;
  }
}
