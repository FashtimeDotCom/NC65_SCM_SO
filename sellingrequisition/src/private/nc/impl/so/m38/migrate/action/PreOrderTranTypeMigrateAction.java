package nc.impl.so.m38.migrate.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.so.m38.migrate.action.pub.MigrateBillTypeAction;
import nc.impl.so.m38.migrate.action.pub.QueryBillTypeVOAction;
import nc.impl.so.m38.migrate.constant.OPC_PreData;
import nc.impl.so.m38.migrate.vo.PreOrderBilltypeVO;
import nc.itf.opc.mc1trantype.ISaveMc1TranType;
import nc.vo.opc.mc1trantype.entity.Mc1TranTypeVO;
import nc.vo.opc.mc1trantype.enumeration.SourceType;
import nc.vo.opc.mc3trantype.enumeration.SaleMode;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;

public class PreOrderTranTypeMigrateAction {

  
  /**
   * Ǩ��ʱ��Ҫ��bd_billtype�и��ƽ������͵ļ�¼�����²��뵽bd_billtype�У�������Ҫ
   * �����µ�pk_billtypeid,�����oldNewTrantypeIdMapΪ�͵�pk_billtypeid��
   * ������pk_billtypeid֮���ӳ���ϵ
   */
  private Map<String, String> oldNewTrantypeIdMap;

  /**
   * Ԥ������������Ǩ�������
   */
  public void migrate(Map<String, String> oldNewTrantypeIdMap)
      throws BusinessException {
    // ��ȡm38trantype
    M38TranTypeVO[] m38trantypeVOs = readyMigData(oldNewTrantypeIdMap);
    // ��ȡbd_billtype
    Map<String, BilltypeVO> billtypeVOs = getBillTypeVOById(m38trantypeVOs);
    // Ǩ��bd_billtype
    migrateBillType(billtypeVOs);
    // Ǩ��m38trantype
    migrateTrantype(billtypeVOs, m38trantypeVOs);
  }

  private void migrateBillType(Map<String, BilltypeVO> billtypeVOs) {
    // ��bd_billtype��������Ԥ����������������ת��ΪECԤ����ʱ������������Ե�ֵ��ECC1��Ϊģ��
    String ecc1_id = OPC_PreData.ECC1_ID;
    String tranTypeCodePrefix = OPC_PreData.ECC1_CODE;
    MigrateBillTypeAction action = new MigrateBillTypeAction();
    action.migrateBilltype(billtypeVOs, ecc1_id, tranTypeCodePrefix, this.oldNewTrantypeIdMap);
  }

  private void migrateTrantype(Map<String, BilltypeVO> billtypeVOs,
      M38TranTypeVO[] m38trantypeVOs) {
    List<Mc1TranTypeVO> mc1trantypeVOs = new ArrayList<Mc1TranTypeVO>();
    for (int i = 0; i < m38trantypeVOs.length; i++) {
      Mc1TranTypeVO newVO = new Mc1TranTypeVO();

      // ������VO�й�ͬ���ֶ���forѭ������ֵ(����vtrantypecode�ֶκ���ᵥ�����¸�ֵ)
      String[] fields = m38trantypeVOs[i].getAttributeNames();
      for (String f : fields) {
        newVO.setAttributeValue(f, m38trantypeVOs[i].getAttributeValue(f));
      }
      
      // ����ECԤ�����������ͱ�����Ԥ�����������Ͷ�������ֶΣ��������и�ֵ
      newVO.setAutoapprove(UFBoolean.FALSE);
      newVO.setAutoarrange(UFBoolean.FALSE);
      newVO.setBcheckcredit(UFBoolean.FALSE);
      newVO.setBissueportal(UFBoolean.FALSE);
      newVO.setFsalemode(SaleMode.MODE_COMMONRETURNCHANGE.getIntegerValue()); //��ͨ+�˻�����5��
      newVO.setFsourcetype(SourceType.SELF.getIntegerValue()); //����
      newVO.setIdisableday(3); //ʧЧ����
      newVO.setCtrantypeid(this.oldNewTrantypeIdMap.get(newVO.getCtrantypeid()));
      
      //�Ե������۵�trantypecode�ֶν��и�ֵ������m38trantype��û�н�������code�����Ը�codeȡ��bd_billtype����
      String m38trantypeCode = billtypeVOs.get(m38trantypeVOs[i].getCtrantypeid()).getPk_billtypecode();
      String mc1trantypeCode = new StringBuilder(OPC_PreData.ECC1_CODE).append("-")
              .append(m38trantypeCode).toString();
      newVO.setVtrantypecode(mc1trantypeCode);
      mc1trantypeVOs.add(newVO);
    }

    // д������:����EC�ṩ�ı���ӿ�
    try {
      NCLocator.getInstance().lookup(ISaveMc1TranType.class)
          .saveMc1TranTypeVO(mc1trantypeVOs.toArray(new Mc1TranTypeVO[0]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ��ȡ���е�Ԥ������������, ���ҽ��г�ʼ��ȫ�ֱ���oldNewTrantypeIdMap
   * @author liylr 2015-01-07 13:47
   * @return
   */
  private M38TranTypeVO[] readyMigData(Map<String, String> oldNewTrantypeIdMap) {
    VOQuery<M38TranTypeVO> srv =
        new VOQuery<M38TranTypeVO>(M38TranTypeVO.class);
    M38TranTypeVO[] m38trantypeVOs = srv.query("", null);

    int len = m38trantypeVOs.length;
    DBTool tool = new DBTool();
    String[] ids = tool.getOIDs(len);
    for (int i = 0; i < len; i++) {
      oldNewTrantypeIdMap.put(m38trantypeVOs[i].getCtrantypeid(), ids[i]);
    }
    this.oldNewTrantypeIdMap = oldNewTrantypeIdMap;

    return m38trantypeVOs;
  }

  /**
   * ����ID��ȡ��Ӧ�ĵ�������
   * �˴���ʹ��VOQuery����ΪVOQuery���Զ��ڲ�ѯ���������dr=0,�����ݿ��д��Ϊdr=null
   * ʹ��DataAccessUtil��Ҫ�Լ���װVO�����������ʹ��dao�ķ�ʽ��ֱ�ӷ���VO
   * 
   * @author liylr 2015-03-12 19:20
   * @param pk_billtypeids ��������ids
   * @return ���ؽ�������id����VO��ӳ���ϵ
   */
  private Map<String, BilltypeVO> getBillTypeVOById(
      M38TranTypeVO[] m38trantypeVOs) {
    Set<String> pk_trantypeids = new HashSet<String>();
    for (int i = 0; i < m38trantypeVOs.length; i++) {
      pk_trantypeids.add(m38trantypeVOs[i].getCtrantypeid());
    }
    pk_trantypeids.add(OPC_PreData.ECC1_ID);
    QueryBillTypeVOAction action = new QueryBillTypeVOAction();
    List<PreOrderBilltypeVO> list = action.queryBilltypeVOByIds(pk_trantypeids);

    Map<String, BilltypeVO> map = new HashMap<String, BilltypeVO>();
    for (BilltypeVO vo : list) {
      map.put(vo.getPk_billtypeid(), vo);
    }
    return map;
  }
}
