package nc.pubimpl.so.m30.pu.m21.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * Эͬ���۶������ɲɹ��������ɹ����������д���۶����Է�������
 * 
 * @since 6.0
 * @version 2011-3-21 ����09:33:19
 * @author ף����
 */
public class Rewrite30For21Rule {

  public void rewrite30For21(Map<String, String> codeMap)
      throws BusinessException {
    try {
      this.rewrite(codeMap);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  /**
   * �����۶�������
   * 
   * @param ids
   */
  private void lockBills(String[] ids) {
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0183")/*@res "�ɹ�������д���۶����������۶�������ʧ�ܡ�"*/;
    locker.lock(ids, message);
  }

  /**
   * ������Դ���۶���id��ѯ���۶�����Ϣ
   * 
   * @param codeMap
   * @return
   */
  private SaleOrderHVO[] query(Map<String, String> codeMap) {
    String[] ids = new String[codeMap.size()];
    ids = codeMap.keySet().toArray(ids);
    this.lockBills(ids);
    VOQuery<SaleOrderHVO> query = new VOQuery<SaleOrderHVO>(SaleOrderHVO.class);
    SaleOrderHVO[] hvos = query.query(ids);
    if (VOChecker.isEmpty(hvos) || hvos.length != ids.length) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0184")/*@res "�Ҳ����ɹ�������Ӧ�����۶�������дʧ�ܡ�"*/);
    }
    for (SaleOrderHVO hvo : hvos) {
      // �Ѿ�����
      if (hvo.getBcooptopoflag().booleanValue()) {
        String message =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
                "04006011-0171")/*@res "���ֲ����������²�ѯ���۶���"*/;
        ExceptionUtils.wrappBusinessException(message);
      }
      String hid = hvo.getCsaleorderid();
      String srccode = codeMap.get(hid);
      hvo.setVcooppohcode(srccode);
      hvo.setBcooptopoflag(UFBoolean.TRUE);
    }
    return hvos;
  }

  private void rewrite(Map<String, String> codeMap) {
    TimeLog.logStart();
    SaleOrderHVO[] hvos = this.query(codeMap);
    TimeLog.info("��ѯ���۶�����Ϣ"); /*-=notranslate=-*/
    TimeLog.logStart();
    String[] names = new String[] {
      SaleOrderHVO.BCOOPTOPOFLAG, SaleOrderHVO.VCOOPPOHCODE
    };
    VOUpdate<SaleOrderHVO> update = new VOUpdate<SaleOrderHVO>();
    update.update(hvos, names);
    TimeLog.info("�������ݿ�"); /*-=notranslate=-*/
  }
}
