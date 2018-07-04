package nc.impl.so.m38.migrate.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.so.m38.migrate.action.pub.MigrateBillTypeAction;
import nc.impl.so.m38.migrate.action.pub.QueryBillTypeVOAction;
import nc.impl.so.m38.migrate.constant.OPC_PreData;
import nc.impl.so.m38.migrate.vo.PreOrderBilltypeVO;
import nc.itf.uap.pf.IPFMetaModel;
import nc.vo.opc.mc3trantype.entity.Mc3TranTypeVO;
import nc.vo.pf.change.BillItfDefVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;
import nc.vo.so.pub.util.ListUtil;

/**
 * @since 636
 * @author liylr
 * @version 2015-02-03
 *          ���ݶ���ӿ�Ǩ��: ��Դ�������Ͳ�Ϊ�յ�Ԥ�������ݽӿڶ����Ǩ��
 */
public class BillItfDefMigrateAction {

  /**
   * 
   * Ǩ��ʱ��Ҫ��bd_billtype�и��ƽ������͵ļ�¼�����²��뵽bd_billtype�У�������Ҫ
   * �����µ�pk_billtypeid,�����oldNewTrantypeIdMapΪm38TrantypeId��
   * ������pk_billtypeid֮���ӳ���ϵ
   */
  private Map<String, String> oldNewTrantypeIdMap;

  public void migrate() throws BusinessException {
    // 1. ��ȡ��Ҫ���µĵ��ݽӿڶ���
    BillItfDefVO[] billItfDefVOs = readyMigData();

    // 2. ���ݽӿڶ�������Ǩ��
    migrateBillItfDef(billItfDefVOs);

    // 3. ɾ��ԭ���ݽӿڶ���
    deleteOldBillItfDef();
  }

  /**
   * ��ȡ��ҪǨ�Ƶ�Ԥ�����ӿڶ���
   * ���ݽӿڶ���������Դ��������Ϊ���Լ���Դ�������Ͳ��ڽ���������չ������Ķ���Ǩ��
   * 
   * @author liylr
   * @return
   */
  private BillItfDefVO[] readyMigData() {
    // ���ݽӿڶ���������Դ��������Ϊ�գ���Դ�������Ͳ��ڽ���������չ������Ķ���Ǩ��
    String sql =
        "select a.pk_billitfdef, a.src_billtype, a.src_transtype, a.dest_billtype, a.dest_transtype,  a.pk_group, a.pk_org, a.pk_busitype, c.pk_trantype from pub_billitfdef a left join bd_billtype b on a.pk_group = b.pk_group and a.src_transtype = b.pk_billtypecode and isnull(b.dr,0) = 0 left join so_m38trantype c on b.pk_billtypeid = c.ctrantypeid  and isnull(c.dr,0) = 0 where a.src_billtype = '38' and a.src_transtype <> '~' and a.dest_billtype = '30' and c.pk_trantype is not null and isnull(a.dr, 0) = 0";
    DataAccessUtils dau = new DataAccessUtils();
    IRowSet rs = dau.query(sql);
    List<BillItfDefVO> list = new ArrayList<BillItfDefVO>();
    while (rs.next()) {
      BillItfDefVO bidv = new BillItfDefVO();
      bidv.setPk_billitfdef(rs.getString(0));
      bidv.setSrc_billtype(rs.getString(1));
      bidv.setSrc_transtype(rs.getString(2));
      bidv.setDest_billtype(rs.getString(3));
      bidv.setDest_transtype(rs.getString(4));
      bidv.setPk_group(rs.getString(5));
      bidv.setPk_org(rs.getString(6));
      bidv.setPk_busitype(rs.getString(7));
      bidv.setSrc_transid(rs.getString(8));
      list.add(bidv);
    }
    BillItfDefVO[] billItfDefVOs = ListUtil.toArray(list);
    if (billItfDefVOs != null) {
      // ��ʼ��oldNewTrantypeIdMap
      this.oldNewTrantypeIdMap = getOldNewTrantypeIdMap(billItfDefVOs);
    }
    return billItfDefVOs;
  }

  /**
   * ɾ��Ǩ�ƺ������Ԥ�����ӿڶ���
   */
  private void deleteOldBillItfDef() {
    String sql =
        "update  pub_billitfdef set dr=1 where SRC_BILLTYPE = '38' and DEST_BILLTYPE = '30'  and dr = 0";
    DataAccessUtils dau = new DataAccessUtils();
    dau.update(sql);
  }

  /**
   * ת����ECԤ�������ݽӿڶ���
   * 
   * @author liylr
   * @param bitfdVOs ת��ǰ�ĵ��ݽӿڶ���VO
   *          ������Ԥ�����ĵ��ݽӿڶ���
   * @return
   * @throws BusinessException
   */
  private void migrateBillItfDef(BillItfDefVO[] billItfDefVOs)
      throws BusinessException {
    if (billItfDefVOs == null || billItfDefVOs.length == 0) {
      return;
    }
    // 1. Ǩ��ECC3�Ľ�������
    migrateTrantype(billItfDefVOs);

    // 2. �������ݽӿڶ���
    createBillItfDef(billItfDefVOs);
  }

  private void migrateTrantype(BillItfDefVO[] billItfDefVOs) {
    // ��Դso_m38trantype
    M38TranTypeVO[] m38TrantypeVOs = getSrc_TrantypeVOs(billItfDefVOs);
    
    // ��ȡbd_billtype
    Map<String, BilltypeVO> billtypeVOs = getBillTypeVOs(m38TrantypeVOs);
    
    // Ǩ��bd_billtype
    migrateBillType(billtypeVOs);

    // Ǩ��so_m38trantype
    migrateMc3trantype(billtypeVOs, m38TrantypeVOs);
  }

  private void migrateBillType(Map<String, BilltypeVO> billtypeVOs) {
    String ecc3_id = OPC_PreData.ECC3_ID;
    String tranTypeCodePrefix = OPC_PreData.ECC3_CODE;
    MigrateBillTypeAction action = new MigrateBillTypeAction();
    action.migrateBilltype(billtypeVOs, ecc3_id, tranTypeCodePrefix,
        this.oldNewTrantypeIdMap);
  }

  private void migrateMc3trantype(Map<String, BilltypeVO> billtypeVOs, M38TranTypeVO[] m38TrantypeVOs) {
    Mc3TranTypeVO[] mc3TrantypeVOs = new Mc3TranTypeVO[m38TrantypeVOs.length];
    for (int i = 0; i < m38TrantypeVOs.length; i++) {
      Mc3TranTypeVO newVO = new Mc3TranTypeVO();

      // ���ڱ�׼�����������Ͱ������и�ֵ
      newVO.setBmodifyaskedqt(m38TrantypeVOs[i].getBmodifyaskedqt());
      newVO.setBmodifydiscount(m38TrantypeVOs[i].getBmodifydiscount());
      newVO.setBmodifyunaskedqt(m38TrantypeVOs[i].getBmodifyunaskedqt());
      newVO.setBnofindpricehit(m38TrantypeVOs[i].getBnofindpricehit());
      newVO.setCtrantypeid(this.oldNewTrantypeIdMap.get(m38TrantypeVOs[i]
          .getPkTrantype()));
      newVO.setFaskqtrule(m38TrantypeVOs[i].getFaskqtrule());
      newVO.setFlargessgetqtrule(m38TrantypeVOs[i].getFlargessgetqtrule());
      newVO.setFmodifymny(m38TrantypeVOs[i].getFmodifymny());
      newVO
          .setFsalemode(nc.vo.opc.mc3trantype.enumeration.SaleMode.MODE_COMMONRETURN
              .getIntegerValue());
      newVO.setPk_group(m38TrantypeVOs[i].getPk_group());
      newVO.setPk_trantype(null);

      // �Ե������۵�trantypecode�ֶν��и�ֵ������m38trantype��û�н�������code�����Ը�codeȡ��bd_billtype����
      String m38trantypeCode = billtypeVOs.get(m38TrantypeVOs[i].getCtrantypeid()).getPk_billtypecode();
      String mc3trantypeCode = new StringBuilder(OPC_PreData.ECC3_CODE).append("-")
          .append(m38trantypeCode).toString();
      newVO.setVtrantypecode(mc3trantypeCode);

      mc3TrantypeVOs[i] = newVO;
    }
    // �����׼������������
    VOInsert<Mc3TranTypeVO> insert = new VOInsert<Mc3TranTypeVO>();
    insert.insert(mc3TrantypeVOs);
  }

  private void createBillItfDef(BillItfDefVO[] billItfDefVOs) {
    List<BillItfDefVO> billitfDefList = new ArrayList<BillItfDefVO>();
    for (int i = 0; i < billItfDefVOs.length; i++) {
      BillItfDefVO billItfDefVO = (BillItfDefVO) billItfDefVOs[i].clone();
      billitfDefList.add(ecc1ToEcc3(billItfDefVO));
      billitfDefList.add(ecc3ToSO(billItfDefVO));
    }

    // ���浥�ݽӿڶ���
    IPFMetaModel model = NCLocator.getInstance().lookup(IPFMetaModel.class);
    for (BillItfDefVO b : billitfDefList) {
      try {
        model.saveBillItfDefVO(b);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006012_0", "04006012-0115")/* ���ݽӿڶ���Ǩ��д��ʱʧ�ܣ� */);
      }
    }
  }

  /**
   * ����ECԤ��������׼�����ĵ��ݽӿڶ���
   * @param billItfDefVOs
   */
  private BillItfDefVO ecc1ToEcc3(BillItfDefVO billItfDefVO) {
    BillItfDefVO newVO = (BillItfDefVO) billItfDefVO.clone();
    newVO.setSrc_billtype(OPC_PreData.ECC1_CODE);
    newVO.setSrc_transtype(joinTranTypeCode(OPC_PreData.ECC1_CODE,
        billItfDefVO.getSrc_transtype()));
    newVO.setDest_billtype(OPC_PreData.ECC3_CODE);
    newVO.setDest_transtype(joinTranTypeCode(OPC_PreData.ECC3_CODE,
        billItfDefVO.getSrc_transtype()));
    newVO.setPk_billitfdef(null);
    return newVO;
  }

  /**
   * ������׼���������۶����ĵ��ݽӿڶ���
   * @param billItfDefVOs
   */
  private BillItfDefVO ecc3ToSO(BillItfDefVO billItfDefVO) {
    BillItfDefVO newVO = (BillItfDefVO) billItfDefVO.clone();
    newVO.setSrc_billtype(OPC_PreData.ECC3_CODE);
    newVO.setSrc_transtype(joinTranTypeCode(OPC_PreData.ECC3_CODE,
        billItfDefVO.getSrc_transtype()));
    newVO.setPk_billitfdef(null);
    return newVO;
  }

  private String joinTranTypeCode(String prefix, String trantypeCode) {
    return new StringBuffer(prefix).append("-").append(trantypeCode).toString();
  }

  /**
   * ��ѯ�����ݽӿڶ����е���Դ��������VO
   * @param billItfDefVOs
   * @return
   */
  private M38TranTypeVO[] getSrc_TrantypeVOs(BillItfDefVO[] billItfDefVOs) {
    Set<String> m38TrantypePKs = getAllTrantypePKs(billItfDefVOs);
    VOQuery<M38TranTypeVO> srv =
        new VOQuery<M38TranTypeVO>(M38TranTypeVO.class);
    return srv.query(m38TrantypePKs.toArray(new String[0]));
  }

  /**
   * ���ݽ������ͻ�ȡ��Ӧ�Ľ���������չVO
   * 
   * @author liylr 2015-03-12 19:20
   * @param m38TrantypeVOs ��������VO
   * @return ����pk_billtypeid����VO��ӳ���ϵ
   */
  private Map<String, BilltypeVO> getBillTypeVOs(M38TranTypeVO[] m38TrantypeVOs) {
    Set<String> pk_billTypeIds = getAllBillTypeIds(m38TrantypeVOs);
    // ��ӵ�������
    pk_billTypeIds.add(OPC_PreData.ECC3_ID);
    QueryBillTypeVOAction action = new QueryBillTypeVOAction();
    List<PreOrderBilltypeVO> list = action.queryBilltypeVOByIds(pk_billTypeIds);
    Map<String, BilltypeVO> map = new HashMap<String, BilltypeVO>();
    for (BilltypeVO vo : list) {
      map.put(vo.getPk_billtypeid(), vo);
    }
    return map;
  }

  private Map<String, String> getOldNewTrantypeIdMap(
      BillItfDefVO[] billItfDefVOs) {
    DBTool tool = new DBTool();
    String[] ids = tool.getOIDs(billItfDefVOs.length);
    Map<String, String> oldNewTrantypeIdMap = new HashMap<String, String>();
    for (int i = 0; i < billItfDefVOs.length; i++) {
      oldNewTrantypeIdMap.put(billItfDefVOs[i].getSrc_transid(), ids[i]);
    }
    return oldNewTrantypeIdMap;
  }

  private Set<String> getAllTrantypePKs(BillItfDefVO[] billItfDefVOs) {
    Set<String> pk_trantypeids = new HashSet<String>();
    for (int i = 0; i < billItfDefVOs.length; i++) {
      pk_trantypeids.add(billItfDefVOs[i].getSrc_transid());
    }
    return pk_trantypeids;
  }
  
  private Set<String> getAllBillTypeIds(M38TranTypeVO[] m38TrantypeVOs){
    Set<String> pk_billtypeIds = new HashSet<String>();
    for (int i = 0; i < m38TrantypeVOs.length; i++) {
      pk_billtypeIds.add(m38TrantypeVOs[i].getCtrantypeid());
    }
    return pk_billtypeIds;
  }
}
