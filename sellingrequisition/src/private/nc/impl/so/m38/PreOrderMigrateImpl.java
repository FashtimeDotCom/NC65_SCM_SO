package nc.impl.so.m38;

import java.util.HashMap;
import java.util.Map;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.so.m38.migrate.action.BillItfDefMigrateAction;
import nc.impl.so.m38.migrate.action.PreOrderAfterMigNodeAction;
import nc.impl.so.m38.migrate.action.PreOrderDataMigrateAction;
import nc.impl.so.m38.migrate.action.PreOrderTranTypeMigrateAction;
import nc.impl.so.m38.migrate.action.UpdatePreOrderMigStateAction;
import nc.impl.so.m38.migrate.rule.PreOrderMigBeforeRule;
import nc.itf.so.m38.IPreOrderMigrate;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since 6.36
 * @author liylr
 * @version 2015-05-22
 */
public class PreOrderMigrateImpl implements IPreOrderMigrate {

  @Override
  public void migratePreOrder() {
    try {

      /** ����Ԥ������Ԫ����ID */
      String voMetaDataID = "575c639d-8dcb-4692-b151-c91f38525c70";

      /*
       * Ԥ����Ǩ�ƣ�Ǩ�Ƶ��������ݰ����������ͣ����ݽӿڶ��壬Ԥ�������ݣ����ε����ĸ����֡����н���������Ϣ�����ڽ���������bd_billtype
       * ���������У����ݽӿڶ�������bd_billtype��Ԥ�������ݱ����ý������ͱ����ݣ����ε���Ҳ���ý�������id
       * ����bd_billtypeΪ����Ԥ�����Լ�ECԤ�������õı����Խ�����Ԥ������bd_billtype���е�����Ǩ�Ƶ���������ģ��ʱ����Ҫ����
       * �����µ�bd_billtype����
       * ������Ҫ�µ�id��,��ôǨ��ǰ���idӳ���ϵ���洢��oldNewTrantypeIdMap֮�С���ӳ���ϵ������
       * Ԥ������������Ǩ�ơ���������Ǩ�ơ����ε������õ����ε��ݵĽ�������id���µ������ط��õ�����ӳ���ϵ��bd_billtype���н�������
       * Ǩ��ǰ��Ǩ�ƺ��pk_billtypeidӳ���ϵ
       * ����Map�Ķ���ķ�ʽ���ڣ�<Ǩ��ǰpk_billtypeid��Ǩ�ƺ�pk_billtypeid>
       */
      Map<String, String> oldNewTrantypeIdMap = new HashMap<String, String>();

      // 1:����׼ȷ��У��
      PreOrderMigBeforeRule rule = new PreOrderMigBeforeRule();
      rule.check();

      // 2.�ڿ�ʼǨ��ǰ����Ǩ��ǰ�¼�
      EventDispatcher.fireEvent(new BusinessEvent(voMetaDataID,
          IEventType.TYPE_UPGRADE_BEFORE, null));

      // 3.Ԥ������������Ǩ��
      PreOrderTranTypeMigrateAction pottmAction =
          new PreOrderTranTypeMigrateAction();
      pottmAction.migrate(oldNewTrantypeIdMap);

      // 4.���ݽӿڶ���Ǩ��
      BillItfDefMigrateAction bidmAction = new BillItfDefMigrateAction();
      bidmAction.migrate();

      // 5.����Ǩ��
      PreOrderDataMigrateAction migAction = new PreOrderDataMigrateAction();
      migAction.migrate(oldNewTrantypeIdMap);

      // 6.ɾ��Ԥ�������
      PreOrderAfterMigNodeAction afterAction = new PreOrderAfterMigNodeAction();
      afterAction.process();

      // 7.��¼Ǩ�Ʊ��
      UpdatePreOrderMigStateAction update = new UpdatePreOrderMigStateAction();
      update.update();
      
      // 8.������ģ���Ӱ��, ���������Ժ󷢳��������¼�
      EventDispatcher.fireEvent(new BusinessEvent(voMetaDataID,
          IEventType.TYPE_UPGRADE_AFTER, oldNewTrantypeIdMap));
    }catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
