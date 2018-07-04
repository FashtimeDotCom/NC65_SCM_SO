package nc.impl.so.m38.migrate.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.pubapp.pattern.page.SecondaryPage;
import nc.impl.pubapp.pattern.page.db.IDDBPage;
import nc.impl.so.m38.migrate.constant.OPC_PreData;
import nc.pubitf.opc.mc1.opc.m38.ISaveCustomerPO;
import nc.vo.opc.mc1.entity.CustomerPOBVO;
import nc.vo.opc.mc1.entity.CustomerPOHVO;
import nc.vo.opc.mc1.entity.CustomerPOVO;
import nc.vo.opc.mc1trantype.enumeration.SaleMode;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class PreOrderDataMigrateAction {

  /**
   * ÿҳ��С
   */
  private static final int fetchSize = 500;

  private final Integer OPC_FREE;

  private final Integer OPC_AUDIT;

  private final Integer OPC_AUDITING;

  private final Integer OPC_INVALIDATE;

  private final Integer OPC_SIGN;

  private final Integer OPC_FINISH;

  private final Integer OPC_NOPASS;

  public PreOrderDataMigrateAction() {
    OPC_FREE = nc.vo.opc.opcpub.enumeration.BillStatus.FREE.getIntegerValue();
    OPC_AUDIT = nc.vo.opc.opcpub.enumeration.BillStatus.AUDIT.getIntegerValue();
    OPC_AUDITING =
        nc.vo.opc.opcpub.enumeration.BillStatus.AUDITING.getIntegerValue();
    OPC_INVALIDATE =
        nc.vo.opc.opcpub.enumeration.BillStatus.INVALIDATE.getIntegerValue();
    OPC_SIGN = nc.vo.opc.opcpub.enumeration.BillStatus.SIGN.getIntegerValue();
    OPC_FINISH =
        nc.vo.opc.opcpub.enumeration.BillStatus.FINISH.getIntegerValue();
    OPC_NOPASS =
        nc.vo.opc.opcpub.enumeration.BillStatus.NOPASS.getIntegerValue();
  }

  /**
   * Ԥ��������Ǩ��
   * 
   * @author liylr @2015-01-09
   */
  public void migrate(Map<String, String> oldNewTrantypeIdMap) {
    IDDBPage ds =
        new IDDBPage("select cpreorderid from so_preorder where dr=0");
    ISaveCustomerPO save_inter =
        NCLocator.getInstance().lookup(ISaveCustomerPO.class);
    IPage<String> page = new SecondaryPage<String>(ds, fetchSize);
    while (page.hasNext()) {
      String[] ids = page.next();
      // 1. ׼������
      PreOrderHVO[] hvos = getPreOrderHVOs(ids);
      PreOrderBVO[] bvos = getPreOrderBVOs(ids);
      Map<String, SaleOrderBVO> saleOrderBVOMap = getSaleOrderBVO(ids);

      // 2. ����ת��
      CustomerPOVO[] customerPOVOs =
          transferVOs(hvos, bvos, oldNewTrantypeIdMap, saleOrderBVOMap);

      // 3. ��������
      try {
        save_inter.saveCustomerPO(customerPOVOs);
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /**
   * ������Ԥ�����ı�ͷ����VOת��ECԤ�����ľۺ�VO
   * 
   * @param preOrderHVOs
   * @param preOrderBVOs
   * @param saleOrderBVOMap
   * @return
   */
  private CustomerPOVO[] transferVOs(PreOrderHVO[] preOrderHVOs,
      PreOrderBVO[] preOrderBVOs, Map<String, String> oldNewTrantypeIdMap,
      Map<String, SaleOrderBVO> saleOrderBVOMap) {
    Map<String, SaleOrderBVO> preOrderBidDestBillValueMap =
        getDestBillValues(saleOrderBVOMap);
    CustomerPOHVO[] hvos =
        transferHVOs(preOrderHVOs, oldNewTrantypeIdMap, saleOrderBVOMap);
    CustomerPOBVO[] bvos =
        transferBVOs(preOrderBVOs, saleOrderBVOMap, preOrderBidDestBillValueMap);
    CustomerPOVO[] vos = aggvo(hvos, bvos, preOrderBidDestBillValueMap);
    return vos;
  }

  /**
   * ת����EcԤ�����ľۺ�VO
   * 
   * @param hvos
   * @param bvos
   * @param preOrderBidDestBillValueMap
   * @param saleOrderBVOMap
   * @return
   */
  private CustomerPOVO[] aggvo(CustomerPOHVO[] hvos, CustomerPOBVO[] bvos,
      Map<String, SaleOrderBVO> preOrderBidDestBillValueMap) {
    CustomerPOVO[] vos = new CustomerPOVO[hvos.length];
    MapList<String, CustomerPOBVO> map = new MapList<String, CustomerPOBVO>();
    for (CustomerPOBVO bvo : bvos) {
      map.put(bvo.getCcustomerpoid(), bvo);
    }

    // ����pk�ҵ���Ӧ��BVO��Ȼ���γɾۺ�VO
    for (int i = 0; i < hvos.length; i++) {
      int opc_finish_flag = 0;
      int opc_sign_flag = 0;
      CustomerPOHVO hvo = hvos[i];
      List<CustomerPOBVO> list = map.get(hvo.getCcustomerpoid());
      CustomerPOBVO[] cpbvo = new CustomerPOBVO[list.size()];
      // ������HVO��Ӧ������BVO������ÿ�������е�״̬
      for (int j = 0; j < list.size(); j++) {
        Integer billstate = hvo.getFstatusflag();
        CustomerPOBVO bvo = list.get(j);

        // �����ͷ�ǰ��Źرյģ�������Ԥ��������״̬�ǹرյĻ��߶���ģ��������ȫ��Ϊ�йر�
        if (hvo.getBarrangeflag().booleanValue()) {
          bvo.setBlineclose(UFBoolean.TRUE);
        }
        // ������״ֱ̬�Ӹ��Ƶ���״̬
        bvo.setFrowstatus(hvo.getFstatusflag());
        // ���Ԥ����״̬Ϊ����ͨ��
        if (OPC_AUDIT.equals(billstate)) {

          // 1.�ۼư�������>=������(����!������>�ۼư�������) ����״̬Ϊ���Źر�
          if (!MathTool.greaterThan(bvo.getNnum(), bvo.getNarrnum())) {
            bvo.setBlineclose(UFBoolean.TRUE);
          }

          // 2.���ۼư�������=0�����йرգ��򶩵�����Ԥ�����г���رա�
          if (MathTool.equals(bvo.getNarrnum(), UFDouble.ZERO_DBL)
              && bvo.getBlineclose().booleanValue()) {
            bvo.setBboutendflag(UFBoolean.TRUE);
          }

          // 3. ���ۼư�������>0�����йرգ��������۶���������رգ��򶩵�����Ԥ�����г���رա�
          SaleOrderBVO saleorderBVO =
              preOrderBidDestBillValueMap.get(bvo.getCcustomerpobid());
          boolean destBillOutSendClose;
          if (saleorderBVO == null) {
            destBillOutSendClose = false;
          }
          else {
            destBillOutSendClose =
                saleorderBVO.getBboutendflag().booleanValue();
          }
          if (MathTool.greaterThan(bvo.getNarrnum(), UFDouble.ZERO_DBL)
              && bvo.getBlineclose().booleanValue() && destBillOutSendClose) {
            bvo.setBboutendflag(UFBoolean.TRUE);
          }

          // 4. �ۼƳ�����������0����״̬Ϊ�ѷ���
          if (MathTool.greaterThan(bvo.getNtotaloutnum(), UFDouble.ZERO_DBL)) {
            opc_sign_flag++;
            bvo.setFrowstatus(OPC_SIGN);
          }

          // 5. �ۼ�ǩ����������0���ҳ���رգ���״̬Ϊ�������
          if (MathTool.greaterThan(bvo.getNtotalsignnum(), UFDouble.ZERO_DBL)
              && bvo.getBboutendflag().booleanValue()) {
            opc_finish_flag++;
            bvo.setFrowstatus(OPC_FINISH);
          }

        }
        cpbvo[j] = bvo;
      }
      // �����������ѷ���״̬�ģ����ͷ����״̬����Ϊ�ѷ���
      if (opc_sign_flag > 0) {
        hvo.setFstatusflag(OPC_SIGN);
      }
      // ������״̬��Ϊ��������ɡ�ʱͷ״̬תΪ��������ɡ�
      if (opc_finish_flag == list.size()) {
        hvo.setFstatusflag(OPC_FINISH);
      }

      CustomerPOVO temp = new CustomerPOVO();
      temp.setParent(hvo);
      temp.setChildrenVO(cpbvo);
      vos[i] = temp;
    }
    return vos;
  }

  /**
   * 
   * ����ID��ȡԤ��������VO
   * 
   * @author liylr
   */
  private PreOrderBVO[] getPreOrderBVOs(String[] ids) {
    // ����ids��ȡԤ��������VO
    IDQueryBuilder whereSql = new IDQueryBuilder();
    StringBuilder condition = new StringBuilder();
    condition.append(" and ");
    condition.append(whereSql.buildSQL(PreOrderBVO.CPREORDERID, ids));
    VOQuery<PreOrderBVO> srv = new VOQuery<PreOrderBVO>(PreOrderBVO.class);
    return srv.query(condition.toString(), null);
  }

  /**
   * ����ID��ȡԤ������ͷVO
   * 
   * @author liylr
   */
  private PreOrderHVO[] getPreOrderHVOs(String[] ids) {
    // ����ids��ȡԤ������ͷVO
    IDQueryBuilder idqb = new IDQueryBuilder();
    StringBuilder wheresql = new StringBuilder();
    wheresql.append(" and ");
    wheresql.append(idqb.buildSQL(PreOrderHVO.CPREORDERID, ids));
    VOQuery<PreOrderHVO> srv = new VOQuery<PreOrderHVO>(PreOrderHVO.class);
    return srv.query(wheresql.toString(), null);
  }

  /**
   * ������Ԥ������ͷVOת����EcԤ������ͷVO
   * 
   * @author liylr
   * @param vos
   * @param saleOrderBVOMap
   * @return
   */
  private CustomerPOHVO[] transferHVOs(PreOrderHVO[] vos,
      Map<String, String> oldNewTrantypeIdMap,
      Map<String, SaleOrderBVO> saleOrderBVOMap) {
    /*
     * ����vos��ȡ���ζ����ĳ���ر�״̬
     * ���ڴ洢ÿ��Ԥ������Ӧ�����ζ����Ĺر�״̬��ÿ��Ԥ�������ܶ�Ӧ������ζ�����
     */
    Map<String, Boolean> descBillCloseStatus = new HashMap<String, Boolean>();
    Set<String> saleorderbids = saleOrderBVOMap.keySet();
    for (String saleorderbid : saleorderbids) {
      // ����ֻ�ܲ�ѯ�������ζ����ļ�¼��û�����ζ�����Ԥ������ID������������map��
      UFBoolean bboutendflag =
          saleOrderBVOMap.get(saleorderbid).getBboutendflag();
      String cpreorderid = saleOrderBVOMap.get(saleorderbid).getCsrcid();
      if (descBillCloseStatus.get(cpreorderid) == null) {
        descBillCloseStatus.put(cpreorderid, convertBool(bboutendflag));
      }
      else {
        descBillCloseStatus.put(cpreorderid, convertBool(bboutendflag)
            && descBillCloseStatus.get(cpreorderid));
      }
    }

    CustomerPOHVO[] cusHVOs = new CustomerPOHVO[vos.length];
    for (int i = 0; i < vos.length; i++) {
      CustomerPOHVO cusHVO = new CustomerPOHVO();
      for (String field : this.HEADMIGFIELDS) {
        cusHVO.setAttributeValue(field, vos[i].getAttributeValue(field));
      }

      String code =
          new StringBuilder(OPC_PreData.ECC1_CODE).append("-")
              .append(vos[i].getVtrantypecode()).toString();
      cusHVO.setVtrantypecode(code);

      // Ԥ��������
      cusHVO.setCcustomerpoid(vos[i].getCpreorderid());

      // ��������
      cusHVO.setCtrantypeid(oldNewTrantypeIdMap.get(cusHVO.getCtrantypeid()));

      // ���Źرգ� ���ݵ���״̬�ǹرյĻ��߶���ģ���ѡ��Y�������򣬲���ѡ��N��
      if (vos[i].getFstatusflag() == BillStatus.I_CLOSED
          || vos[i].getFstatusflag() == BillStatus.I_FREEZE) {
        cusHVO.setBarrangeflag(UFBoolean.TRUE);
      }
      else {
        cusHVO.setBarrangeflag(UFBoolean.FALSE);
      }

      // ����رգ��������״̬�ر������ζ�����ȫ������رգ�Y������ѡ��Y�������򲻹�ѡ��N����
      if (vos[i].getFstatusflag() == BillStatus.I_CLOSED
          && (descBillCloseStatus.get(vos[i].getCpreorderid()) == null ? false
              : descBillCloseStatus.get(vos[i].getCpreorderid()))) {
        cusHVO.setBoutendflag(UFBoolean.TRUE);
      }
      else {
        cusHVO.setBoutendflag(UFBoolean.FALSE);
      }

      // ����״̬
      int state = vos[i].getFstatusflag();
      switch (state) {
        case BillStatus.I_FREE:
          cusHVO.setFstatusflag(OPC_FREE);
          break;
        case BillStatus.I_INVALIDATE:
          cusHVO.setFstatusflag(OPC_INVALIDATE);
          break;
        case BillStatus.I_AUDITING:
          cusHVO.setFstatusflag(OPC_AUDITING);
          break;
        case BillStatus.I_NOPASS:
          cusHVO.setFstatusflag(OPC_NOPASS);
          break;
        case BillStatus.I_AUDIT:
          break;
        case BillStatus.I_CLOSED:
          break;
        case BillStatus.I_FREEZE:
          cusHVO.setFstatusflag(OPC_AUDIT);
          break;
      }

      cusHVOs[i] = cusHVO;
    }
    return cusHVOs;
  }

  /**
   * ������Ԥ��������VOת����EcԤ��������VO
   * 
   * @author liylr
   * @param vos
   * @param saleOrderBVOMap
   * @return
   */
  private CustomerPOBVO[] transferBVOs(PreOrderBVO[] vos,
      Map<String, SaleOrderBVO> saleOrderBVOMap,
      Map<String, SaleOrderBVO> preOrderBidDestBillValueMap) {
    CustomerPOBVO[] cusBVOs = new CustomerPOBVO[vos.length];
    for (int i = 0; i < vos.length; i++) {
      CustomerPOBVO cusBVO = new CustomerPOBVO();
      for (String attr : this.BODYMIGFIELDS) {
        cusBVO.setAttributeValue(attr, vos[i].getAttributeValue(attr));
      }
      // Ԥ��������_����
      cusBVO.setCcustomerpoid(vos[i].getCpreorderid());
      // Ԥ�����ӱ�����
      cusBVO.setCcustomerpobid(vos[i].getCpreorderbid());
      // ���ε���
      cusBVO.setVbatchcode(vos[i].getPk_batchcode());
      // ��״̬
      String frowstatus = vos[i].getFrowstatus();
      if (frowstatus != null) {
        int rowState = Integer.parseInt(frowstatus);
        switch (rowState) {
          case BillStatus.I_FREE:
            cusBVO.setFrowstatus(OPC_FREE);
            break;
          case BillStatus.I_INVALIDATE:
            cusBVO.setFrowstatus(OPC_INVALIDATE);
            break;
          case BillStatus.I_AUDITING:
            cusBVO.setFrowstatus(OPC_AUDITING);
            break;
          case BillStatus.I_NOPASS:
            cusBVO.setFrowstatus(OPC_NOPASS);
            break;
          case BillStatus.I_AUDIT:
            break;
          case BillStatus.I_CLOSED:
            break;
          case BillStatus.I_FREEZE:
            cusBVO.setFrowstatus(OPC_AUDIT);
            break;
        }
      }

      SaleOrderBVO saleorderBVO =
          preOrderBidDestBillValueMap.get(vos[i].getCpreorderbid());
      if (saleorderBVO == null) {
        cusBVOs[i] = cusBVO;
        continue;
      }

      for (String field : DESTBILLFEILDS) {
        cusBVO.setAttributeValue(field, saleorderBVO.getAttributeValue(field));
      }
      // ����ǰ��ŹرյĲ������ε��ݳ���رպ�Ԥ�����ſ�������Ϊ����ر�
      if (cusBVO.getBlineclose().booleanValue()
          && saleorderBVO.getBboutendflag().booleanValue()) {
        cusBVO.setBboutendflag(UFBoolean.TRUE);
      }
      else {
        cusBVO.setBboutendflag(UFBoolean.FALSE);
      }

      cusBVO.setFretexchange(saleorderBVO.getFretexchange());
      // cusBVO.setNtotalexenum(saleorderBVO.getNnum());

      cusBVOs[i] = cusBVO;
    }

    return cusBVOs;
  }

  /**
   * ��ȡ���ε�����ص�ֵ�����Ԥ�������������ζ����������ĳһ��Ԥ������Ӧ���������ε��ݵ�ֵȫ���ۺ�������
   * Ȼ�����´洢��SaleOrderBVO�����У�Ȼ��ͽ���Ԥ������������SaleOrderBVOͨ��map��Ӧ����
   * 
   * @param saleOrderBVOMap
   * @return
   */
  private Map<String, SaleOrderBVO> getDestBillValues(
      Map<String, SaleOrderBVO> saleOrderBVOMap) {
    // �洢���ζ�����һЩֵ
    Map<String, SaleOrderBVO> preOrderBidDestBillValueMap =
        new HashMap<String, SaleOrderBVO>();
    for (String saleOrderBid : saleOrderBVOMap.keySet()) {
      SaleOrderBVO saleOrderBVO = saleOrderBVOMap.get(saleOrderBid);
      String cpreorderbid = saleOrderBVO.getCsrcbid();
      SaleOrderBVO destBillValuesVO =
          preOrderBidDestBillValueMap.get(cpreorderbid);
      if (destBillValuesVO == null) {
        destBillValuesVO = new SaleOrderBVO();
        for (String field : this.DESTBILLFEILDS) {
          destBillValuesVO.setAttributeValue(field,
              saleOrderBVO.getAttributeValue(field));
        }
        destBillValuesVO.setBboutendflag(saleOrderBVO.getBboutendflag());
      }
      else {

        // ���ó���ر�״̬
        // ��ȡ���������ĳ���ر�״̬
        boolean f1 = convertBool(saleOrderBVO.getBboutendflag());
        // ��ȡ���е����������ĳ���ر�״̬
        boolean f2 = convertBool(destBillValuesVO.getBboutendflag());
        destBillValuesVO.setBboutendflag(UFBoolean.valueOf(f1 && f2));

        // ���ý��
        for (String field : this.DESTBILLNUMFEILDS) {
          UFDouble newVal = (UFDouble) saleOrderBVO.getAttributeValue(field);
          UFDouble oldVal =
              (UFDouble) destBillValuesVO.getAttributeValue(field);
          destBillValuesVO.setAttributeValue(field,
              MathTool.add(newVal, oldVal));
        }
      }

      // �����˻������
      if (saleOrderBVO.getNastnum().compareTo(UFDouble.ZERO_DBL) < 0) {
        destBillValuesVO.setFretexchange(SaleMode.MODE_COMMONRETURNCHANGE
            .getIntegerValue()); // �����Ǹ�������Ϊ��ͨ+�˻���
      }
      else {
        destBillValuesVO
            .setFretexchange(SaleMode.MODE_COMMON.getIntegerValue()); // ����Ϊ������ջ�0����Ϊ��ͨ
      }

      preOrderBidDestBillValueMap.put(cpreorderbid, destBillValuesVO);
    }
    return preOrderBidDestBillValueMap;
  }

  /**
   * ���ݴ����BFBooleanֵ�����ض�Ӧ�Ĳ���ֵ
   * 
   * @param UFboolVal UFBooleanֵ
   * @return
   */
  private boolean convertBool(UFBoolean boolVal) {
    if (boolVal == null)
      return false;
    return boolVal.booleanValue();
  }

  /**
   * @param ids ���۶�����������
   * @return <���۶����ֱ����������۶���BVO>
   */
  private Map<String, SaleOrderBVO> getSaleOrderBVO(String[] ids) {
    VOQuery<SaleOrderBVO> srv = new VOQuery<SaleOrderBVO>(SaleOrderBVO.class);
    IDQueryBuilder whereSql = new IDQueryBuilder();
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ");
    sql.append(whereSql.buildSQL(SaleOrderBVO.CSRCID, ids));
    SaleOrderBVO[] saleOrderBVOs = srv.query(sql.toString(), null);

    Map<String, SaleOrderBVO> saleOrderVOMap =
        new HashMap<String, SaleOrderBVO>();
    for (SaleOrderBVO vo : saleOrderBVOs) {
      saleOrderVOMap.put(vo.getCsaleorderbid(), vo);
    }
    return saleOrderVOMap;
  }

  /**
   * Ԥ����Ǩ��ʱ������Ԥ��������������Ԥ������Ӧ�ı�ͷ�ֶ�
   */
  private final String[] HEADMIGFIELDS = new String[] {
    PreOrderHVO.APPROVER, PreOrderHVO.BILLMAKER, PreOrderHVO.CCHANNELTYPEID,
    PreOrderHVO.CCUSTOMERID, PreOrderHVO.CDEPTID, PreOrderHVO.CDEPTVID,
    PreOrderHVO.CEMPLOYEEID, PreOrderHVO.CINVOICECUSTID,
    PreOrderHVO.CORIGCURRENCYID, PreOrderHVO.CPAYTERMID,
    PreOrderHVO.CREATIONTIME, PreOrderHVO.CREATOR,
    PreOrderHVO.CTRANSPORTTYPEID, PreOrderHVO.CTRANTYPEID,
    PreOrderHVO.DABATEDATE, PreOrderHVO.DBILLDATE, PreOrderHVO.DMAKEDATE,
    PreOrderHVO.IPRINTCOUNT, PreOrderHVO.MODIFIEDTIME, PreOrderHVO.MODIFIER,
    PreOrderHVO.NDISCOUNTRATE, PreOrderHVO.NHEADSUMMNY, PreOrderHVO.NTOTALNUM,
    PreOrderHVO.PK_GROUP, PreOrderHVO.PK_ORG, PreOrderHVO.PK_ORG_V,
    PreOrderHVO.TAUDITTIME, PreOrderHVO.TS, PreOrderHVO.VBILLCODE,
    PreOrderHVO.VDEF1, PreOrderHVO.VDEF10, PreOrderHVO.VDEF11,
    PreOrderHVO.VDEF12, PreOrderHVO.VDEF13, PreOrderHVO.VDEF14,
    PreOrderHVO.VDEF15, PreOrderHVO.VDEF16, PreOrderHVO.VDEF17,
    PreOrderHVO.VDEF18, PreOrderHVO.VDEF19, PreOrderHVO.VDEF2,
    PreOrderHVO.VDEF20, PreOrderHVO.VDEF3, PreOrderHVO.VDEF4,
    PreOrderHVO.VDEF5, PreOrderHVO.VDEF6, PreOrderHVO.VDEF7, PreOrderHVO.VDEF8,
    PreOrderHVO.VDEF9, PreOrderHVO.VNOTE, PreOrderHVO.VSRCTYPE,
    PreOrderHVO.VTRANTYPECODE, "pseudocolumn", "dr"
  };

  private final String[] BODYMIGFIELDS = new String[] {
    PreOrderBVO.BLARGESSFLAG, PreOrderBVO.BLINECLOSE,
    PreOrderBVO.BTRIATRADEFLAG, PreOrderBVO.CARORGID, PreOrderBVO.CARORGVID,
    PreOrderBVO.CARRANGEID, PreOrderBVO.CASTUNITID, PreOrderBVO.CCURRENCYID,
    PreOrderBVO.CCUSTMATERIALID, PreOrderBVO.CMATERIALID,
    PreOrderBVO.CMATERIALVID, PreOrderBVO.CORIGCURRENCYID,
    PreOrderBVO.CPRICEFORMID, PreOrderBVO.CPRICEITEMID,
    PreOrderBVO.CPRICEITEMTABLEID, PreOrderBVO.CPRICEPOLICYID,
    PreOrderBVO.CPRODUCTORID, PreOrderBVO.CPROFITCENTERID,
    PreOrderBVO.CPROFITCENTERVID, PreOrderBVO.CPROJECTID,
    PreOrderBVO.CQTUNITID, PreOrderBVO.CQUALITYLEVELID,
    PreOrderBVO.CRECECOUNTRYID, PreOrderBVO.CRECEIVEADDRID,
    PreOrderBVO.CRECEIVEAREAID, PreOrderBVO.CRECEIVECUSTID,
    PreOrderBVO.CRECEIVESITEID, PreOrderBVO.CROWNO, PreOrderBVO.CSENDCOUNTRYID,
    PreOrderBVO.CSENDSTOCKORGID, PreOrderBVO.CSENDSTOCKORGVID,
    PreOrderBVO.CSENDSTORDOCID, PreOrderBVO.CSETTLEORGID,
    PreOrderBVO.CSETTLEORGVID, PreOrderBVO.CTAXCODEID,
    PreOrderBVO.CTAXCOUNTRYID, PreOrderBVO.CTRAFFICORGID,
    PreOrderBVO.CTRAFFICORGVID, PreOrderBVO.CUNITID, PreOrderBVO.CVENDORID,
    PreOrderBVO.DARRDATE, PreOrderBVO.DBILLDATE, PreOrderBVO.DRECEIVEDATE,
    PreOrderBVO.DSENDDATE, PreOrderBVO.FBUYSELLFLAG, PreOrderBVO.FTAXTYPEFLAG,
    PreOrderBVO.NARRNUM, PreOrderBVO.NASKQTORIGNETPRICE,
    PreOrderBVO.NASKQTORIGPRICE, PreOrderBVO.NASKQTORIGTAXPRC,
    PreOrderBVO.NASKQTORIGTXNTPRC, PreOrderBVO.NASTNUM, PreOrderBVO.NCALTAXMNY,
    PreOrderBVO.NDISCOUNT, PreOrderBVO.NDISCOUNTRATE,
    PreOrderBVO.NEXCHANGERATE, PreOrderBVO.NGLOBALEXCHGRATE,
    PreOrderBVO.NGLOBALMNY, PreOrderBVO.NGLOBALTAXMNY,
    PreOrderBVO.NGROUPEXCHGRATE, PreOrderBVO.NGROUPMNY,
    PreOrderBVO.NGROUPTAXMNY, PreOrderBVO.NITEMDISCOUNTRATE, PreOrderBVO.NMNY,
    PreOrderBVO.NNETPRICE, PreOrderBVO.NNUM, PreOrderBVO.NORIGDISCOUNT,
    PreOrderBVO.NORIGMNY, PreOrderBVO.NORIGNETPRICE, PreOrderBVO.NORIGPRICE,
    PreOrderBVO.NORIGTAXMNY, PreOrderBVO.NORIGTAXNETPRICE,
    PreOrderBVO.NORIGTAXPRICE, PreOrderBVO.NPRICE, PreOrderBVO.NQTNETPRICE,
    PreOrderBVO.NQTORIGNETPRICE, PreOrderBVO.NQTORIGPRICE,
    PreOrderBVO.NQTORIGTAXNETPRC, PreOrderBVO.NQTORIGTAXPRICE,
    PreOrderBVO.NQTPRICE, PreOrderBVO.NQTTAXNETPRICE, PreOrderBVO.NQTTAXPRICE,
    PreOrderBVO.NQTUNITNUM, PreOrderBVO.NTAX, PreOrderBVO.NTAXMNY,
    PreOrderBVO.NTAXNETPRICE, PreOrderBVO.NTAXPRICE, PreOrderBVO.NTAXRATE,
    PreOrderBVO.PK_GROUP, PreOrderBVO.PK_ORG, PreOrderBVO.TS,
    PreOrderBVO.VBATCHCODE, PreOrderBVO.VBDEF1, PreOrderBVO.VBDEF10,
    PreOrderBVO.VBDEF11, PreOrderBVO.VBDEF12, PreOrderBVO.VBDEF13,
    PreOrderBVO.VBDEF14, PreOrderBVO.VBDEF15, PreOrderBVO.VBDEF16,
    PreOrderBVO.VBDEF17, PreOrderBVO.VBDEF18, PreOrderBVO.VBDEF19,
    PreOrderBVO.VBDEF2, PreOrderBVO.VBDEF20, PreOrderBVO.VBDEF3,
    PreOrderBVO.VBDEF4, PreOrderBVO.VBDEF5, PreOrderBVO.VBDEF6,
    PreOrderBVO.VBDEF7, PreOrderBVO.VBDEF8, PreOrderBVO.VBDEF9,
    PreOrderBVO.VCHANGERATE, PreOrderBVO.VFREE1, PreOrderBVO.VFREE10,
    PreOrderBVO.VFREE2, PreOrderBVO.VFREE3, PreOrderBVO.VFREE4,
    PreOrderBVO.VFREE5, PreOrderBVO.VFREE6, PreOrderBVO.VFREE7,
    PreOrderBVO.VFREE8, PreOrderBVO.VFREE9, PreOrderBVO.VQTUNITRATE,
    PreOrderBVO.VROWNOTE, "pseudocolumn", "dr"
  };

  /**
   * Ԥ�����������۶��������۶����������漰���������ֶ�
   * ����Ԥ���������붩������Ϊһ�Զ��ϵ���������ֶ����֮�����Ԥ�����ж�Ӧ���ֶ�ֵ
   */
  private final String[] DESTBILLNUMFEILDS = new String[] {
    SaleOrderBVO.NTOTALARMNY, SaleOrderBVO.NTOTALARNUM,
    SaleOrderBVO.NTOTALINVOICENUM, SaleOrderBVO.NTOTALOUTNUM,
    SaleOrderBVO.NTOTALPAYMNY, SaleOrderBVO.NTOTALRETURNNUM,
    SaleOrderBVO.NTOTALSIGNNUM, SaleOrderBVO.NNUM
  };

  /**
   * Ԥ�����������۶��������۶����������漰���������ֶ�
   */
  private final String[] DESTBILLFEILDS = new String[] {
    SaleOrderBVO.NTOTALARMNY, SaleOrderBVO.NTOTALARNUM,
    SaleOrderBVO.NTOTALINVOICENUM, SaleOrderBVO.NTOTALOUTNUM,
    SaleOrderBVO.NTOTALPAYMNY, SaleOrderBVO.NTOTALRETURNNUM,
    SaleOrderBVO.NTOTALSIGNNUM,
  };

}
