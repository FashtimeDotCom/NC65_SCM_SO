package nc.pubimpl.so.m33.so.m32;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.arap.util.BillTermUtils;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubimpl.so.m33.arap.ar.QueryAccountDateFromM33Action;
import nc.pubitf.arap.pub.IArapTermDateQueryService;
import nc.pubitf.ct.saledaily.so.IZ3QueryForOrder;
import nc.pubitf.so.m33.arap.AccountDateType;
import nc.vo.arap.termitem.ArapTermDateVO;
import nc.vo.bd.income.IncomeChVO;
import nc.vo.bd.payment.IPaymentUtil;
import nc.vo.ct.saledaily.entity.CtSalePayTermVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.ListUtil;

/**
 * ���۽��㴫Ӧ��ʱ�㣬���������ϰ����տ�Э���ֲ�����Ӧ�յ�
 * 
 * @author quyt
 * 
 */
public class ArapTermDateQueryServiceImpl implements IArapTermDateQueryService {

  @Override
  public Map<String, ArapTermDateVO[]> queryTermDataVOs(String top_billtype,
      Map<String, String> toppaymentMap) throws BusinessException {
    if (toppaymentMap == null || toppaymentMap.size() == 0
        || top_billtype == null) {
      return Collections.emptyMap();
    }
    if (!SOBillType.Invoice.getCode().equals(top_billtype)
        && !ICBillType.SaleOut.getCode().equals(top_billtype)) {
      return Collections.emptyMap();
    }
    // ���۴�������ϸid
    Set<String> sqdetalid = new HashSet<String>();
    // �տ�Э��id
    Set<String> incomeId = new HashSet<String>();
    for (Entry<String, String> entry : toppaymentMap.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();
      if (!PubAppTool.isNull(key)&& !PubAppTool.isNull(value)) {
        sqdetalid.add(key);
        incomeId.add(value);
      }
    }
    if (sqdetalid.size() == 0 || incomeId.size() == 0) {
      return Collections.emptyMap();
    }
    String[] sqdetalids = sqdetalid.toArray(new String[sqdetalid.size()]);

    Map<String, String> squarebidMap = new HashMap<String, String>();
    // ͨ�����۳��⡢��Ʊ��������ϸ��ȡ���ۺ�ͬpk
    Map<String, String> ctsaleMap =
        getCtsaleMap(top_billtype, sqdetalids, squarebidMap);
    String[] cctmanageids = getcctmanageids(ctsaleMap);
    // ���ݺ�ͬ������ȡ��ͬ�տ�Э��vo
    Map<String, CtSalePayTermVO[]> ctvoMap = null;
    if ((SysInitGroupQuery.isCTEnabled())) {
      ctvoMap =
          NCLocator.getInstance().lookup(IZ3QueryForOrder.class)
              .queryIsShowPayterm(cctmanageids);
    }
    // 1.�տ�Э����Դ��չ�����ۺ�ͬ
    Map<String, ArapTermDateVO[]> ctdaremap = null;
    if (ctvoMap != null && ctvoMap.size() > 0) {
      Map<String, UFDate[]> retMap =
          getperiodDate(sqdetalids, squarebidMap, ctsaleMap, ctvoMap,
              top_billtype);
      // �������ͬ�ӿڷ��ص�mapΪ��Ϊ�����ʾ����ϸ����Դ��չ���տ�Э��ģ���ʹ��retMap
      ctdaremap =
          getArapTermDateVOMap(squarebidMap, ctsaleMap, ctvoMap, retMap);
    }
    // 2.�տ�Э����Դ���տ�Э�鵵������
    Map<String, ArapTermDateVO[]> arapdatemap =
        new HashMap<String, ArapTermDateVO[]>();
    Map<String, UFDate[]> incomeMap = new HashMap<String, UFDate[]>();
    if (incomeId != null && incomeId.size() > 0) {
      incomeMap =
          getperiodDate(sqdetalid, incomeId, toppaymentMap, top_billtype);
      arapdatemap =
          getArapTermDateVOByIncomeVOMap(ctdaremap, incomeMap, incomeId,
              toppaymentMap);
    }

    // 3.�ϲ���Դչ�����ۺ�ͬ����Դ�ڻ������ݵ��տ�Э��VO
    if (ctdaremap != null) {
      for (Map.Entry<String, ArapTermDateVO[]> map : ctdaremap.entrySet()) {
        if (map.getValue().length > 0) {
          arapdatemap.put(map.getKey(), map.getValue());
        }
      }
    }
    return arapdatemap;

  }

  @Override
  public boolean isSupportBilltype(String top_billtype) {
    if (SOBillType.Invoice.getCode().equals(top_billtype)
         ||ICBillType.SaleOut.getCode().equals(top_billtype)) {
      return true;
    }else{
      return false;
    }
  }

  /**
   * �жϴ���ı�������������ȷ�����ص�map
   * 
   * @param sqibidList
   *          ���۷�Ʊ��������
   * @param sqobidList
   *          ���۳��ⵥ��������
   * @param ctsaleMap
   *          ��Ʊ����ⵥ�ͺ�ͬ������map
   * @return
   */
  private Map<String, String> getCtsaleMap(String top_billtype,
      String[] sqdetalids, Map<String, String> squarebidMap) {
    List<String> sqinvibidList = new ArrayList<String>();
    List<String> sqoutbidList = new ArrayList<String>();
    // �����Դ��������Ϊ���۷�Ʊ
    if (SOBillType.Invoice.getCode().equals(top_billtype)) {
      VOQuery<SquareInvDetailVO> voQuery =
          new VOQuery<SquareInvDetailVO>(SquareInvDetailVO.class);
      SquareInvDetailVO[] sdVOs = voQuery.query(sqdetalids);
      for (SquareInvDetailVO sdVO : sdVOs) {
        sqinvibidList.add(sdVO.getCsquarebillbid());
        squarebidMap.put(sdVO.getCsalesquaredid(), sdVO.getCsquarebillbid());
      }
    }
    // �����Դ��������Ϊ���۳��ⵥ
    else if (top_billtype != null
        && ICBillType.SaleOut.getCode().equals(top_billtype)) {
      VOQuery<SquareOutDetailVO> voQuery =
          new VOQuery<SquareOutDetailVO>(SquareOutDetailVO.class);
      SquareOutDetailVO[] sdVOs = voQuery.query(sqdetalids);
      for (SquareOutDetailVO sdVO : sdVOs) {
        sqoutbidList.add(sdVO.getCsquarebillbid());
        squarebidMap.put(sdVO.getCsalesquaredid(), sdVO.getCsquarebillbid());
      }
    }
    Map<String, String> saleCtidMap = new HashMap<String, String>();
    if (!ListUtil.isEmpty(sqinvibidList)) {
      saleCtidMap = getPrimaryKeyBySaleInv(sqinvibidList);
    }
    else if (!ListUtil.isEmpty(sqoutbidList)) {
      saleCtidMap = getPrimaryKeyBySaleOut(sqoutbidList);
    }

    return saleCtidMap;
  }

  /**
   * ���ݴ���ĵ����տ�Э�����������ذ������ж�����ϸ�������յ�map
   * 
   * @param keys ������ϸid
   * @param values ���������տ�Э��pk
   * @param toppaymentMap <������ϸid�����������տ�Э��pk>
   * @param top_billtype ��Դ��������
   * @return
   */
  private Map<String, UFDate[]> getperiodDate(Set<String> keys,
      Set<String> values, Map<String, String> toppaymentMap, String top_billtype) {
    Map<String, List<AccountDateType>> hashMap =
        new HashMap<String, List<AccountDateType>>();
    Map<String, UFDate[]> incomeMap = new HashMap<String, UFDate[]>();
    if (values != null && values.size() > 0) {
      Map<String, IncomeChVO[]> ctsaleMap = getIncomeBill(values);
      for (String key : keys) {
        String pk_payterm = toppaymentMap.get(key);
        // ��ȡ������������
        IncomeChVO[] incomechVOs = ctsaleMap.get(pk_payterm);
        List<AccountDateType> list = new ArrayList<AccountDateType>();
        for (IncomeChVO incomechVO : incomechVOs) {
          String pk_incomeperiod = incomechVO.getPk_incomeperiod();
          list.add(getAccountDateType(pk_incomeperiod));
        }
        hashMap.put(key, list);
      }
      QueryAccountDateFromM33Action action =
          new QueryAccountDateFromM33Action();
      incomeMap = action.queryAccountDate(hashMap, top_billtype);
    }
    return incomeMap;
  }

  /**
   * �����տ�Э��������ȡ�տ�Э��vo
   * 
   * @param pk_payterms
   * @return
   * 
   */
  private Map<String, IncomeChVO[]> getIncomeBill(Set<String> keys) {
    String[] pk_payterms = keys.toArray(new String[keys.size()]);
    StringBuilder whereSql = new StringBuilder();
    whereSql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    whereSql.append(iq.buildSQL(IncomeChVO.PK_PAYMENT, pk_payterms));
    
    VOQuery<IncomeChVO> voQuery = new VOQuery<IncomeChVO>(IncomeChVO.class);
    IncomeChVO[] incomechVOs = voQuery.query(whereSql.toString(), null);
    
    Map<String, IncomeChVO[]> hashMap = new HashMap<String, IncomeChVO[]>();
    for(IncomeChVO incomechVO:incomechVOs){
      hashMap.put(incomechVO.getPk_payment(), incomechVOs);
    }
    return hashMap;
  }

  /**
   * ���ݴ�����տ�Э��vo��hashmap�����ز�����Ҫ���տ�Э��vo
   * 
   * @param csbidMap
   * @param ctsaleMap
   * @param ctvoMap
   * @param retMap
   * @return
   */
  private Map<String, ArapTermDateVO[]> getArapTermDateVOMap(
      Map<String, String> csbidMap, Map<String, String> ctsaleMap,
      Map<String, CtSalePayTermVO[]> ctvoMap, Map<String, UFDate[]> retMap) {
    Map<String, ArapTermDateVO[]> map = new HashMap<String, ArapTermDateVO[]>();
    if (retMap != null) {
      for (Map.Entry<String, UFDate[]> ret : retMap.entrySet()) {
        Set<ArapTermDateVO> arapTermDateVOs = new HashSet<ArapTermDateVO>();
        // ��ȡ��ϸ��pk
        String detialPrimaryKey = ret.getKey();
        // ��ȡ��ϸ�ж�Ӧ��˵��Э��������������
        UFDate[] paydates = ret.getValue();
        UFDate expiredate = new UFDate();
        int i = 0;
        for (UFDate paydate : paydates) {
          if (paydate == null) {
            // ������ϸ��pk��ȡ
            CtSalePayTermVO[] ctSalePayTermVOs =
                ctvoMap.get(ctsaleMap.get(csbidMap.get(detialPrimaryKey)));
            // ��ÿһ���տ�Э��vo��ֵ��ת�ɲ�����Ҫ��vo
            // �ж�������������
            // ��������ղ���ϵͳԤ�õ�
            // �����տ��ڶ�Ӧ��ʵ�ʵ����ա��ƻ������ա��������ɵ�ҵ������
            if (ctSalePayTermVOs[i].getDrealenddate() != null) {
              // ʵ�ʵ�����
              expiredate = ctSalePayTermVOs[i].getDrealenddate();
            }
            else if (ctSalePayTermVOs[i].getDplaneffectdate() != null) {
              // �ƻ�������
              expiredate = ctSalePayTermVOs[i].getDplanenddate();
            }
            else {
              // ��������ҵ������(��ǰҵ������)
              expiredate = AppContext.getInstance().getBusiDate();
            }
            // �������ա������ա��տ�Э��vo��ֵ��ArapTermDateVO
            // �����ۺ�ͬvoת��Ϊincomechvo�����������տ�Э�飩
            ArapTermDateVO arapTermDateVO = new ArapTermDateVO();
            IncomeChVO incomevo = setATDVOToCSTVO(ctSalePayTermVOs[i]);
            arapTermDateVO.setPaydate(paydate);
            arapTermDateVO.setExpiredate(expiredate);
            arapTermDateVO.setIncomevo(incomevo);
            arapTermDateVOs.add(arapTermDateVO);
          }
          else if (paydates.length > 0) {
            CtSalePayTermVO[] ctSalePayTermVOs =
                ctvoMap.get(ctsaleMap.get(csbidMap.get(detialPrimaryKey)));
            // ��ÿһ���տ�Э��vo��ֵ��ת�ɲ�����Ҫ��vo
            // �������������Ϊϵͳ����(�������ڲ���ϵͳԤ�õ�)����ͬ���ڡ��������ڡ���Ʊ���ڵȣ����������ȹ���ȡֵ��ʵ�ʵ����ա����������ռ���ĵ�����
            ArapTermDateVO arapTermDateVO = new ArapTermDateVO();
            if (ctSalePayTermVOs[i].getDrealenddate() != null) {
              // ʵ�ʵ�����
              expiredate = ctSalePayTermVOs[i].getDrealenddate();
              arapTermDateVO.setExpiredate(expiredate);
              // �������ա������ա��տ�Э��vo��ֵ��ArapTermDateVO
              // �����ۺ�ͬvoת��Ϊincomechvo�����������տ�Э�飩
              IncomeChVO incomevo = setATDVOToCSTVO(ctSalePayTermVOs[i]);
              arapTermDateVO.setPaydate(paydate);
              arapTermDateVO.setIncomevo(incomevo);
            }
            else {
              // �������ա������ա��տ�Э��vo��ֵ��ArapTermDateVO
              // �����ۺ�ͬvoת��Ϊincomechvo�����������տ�Э�飩
              IncomeChVO incomevo = setATDVOToCSTVO(ctSalePayTermVOs[i]);
              arapTermDateVO.setPaydate(paydate);
              arapTermDateVO.setIncomevo(incomevo);
              // ������ӿڣ�����ȥ�����������ڵ��տ�Э��vo���ص�����
              // ���������ռ���ĵ�����
              expiredate =
                  BillTermUtils.getExpiredateByTermDateVO(arapTermDateVO);
              arapTermDateVO.setExpiredate(expiredate);
            }
            arapTermDateVOs.add(arapTermDateVO);
          }
          i++;
        }
        map.put(detialPrimaryKey,
            arapTermDateVOs.toArray(new ArapTermDateVO[arapTermDateVOs.size()]));
      }
    }
    return map;
  }

  /**
   * ���ݴ�����տ�Э��vo��hashmap�����ز�����Ҫ���տ�Э��vo
   * 
   * @param ctdaremap ȫ����<������ϸ��id, �տ�Э��vo����>
   * @param incomeMap <������ϸ��id��������(��������)>
   * @param incomeId ���������տ�Э��pk
   * @param toppaymentMap
   * @return
   */
  private Map<String, ArapTermDateVO[]> getArapTermDateVOByIncomeVOMap(
      Map<String, ArapTermDateVO[]> ctdaremap, Map<String, UFDate[]> incomeMap,
      Set<String> incomeId, Map<String, String> toppaymentMap) {
    Map<String, ArapTermDateVO[]> newarapmap =
        new HashMap<String, ArapTermDateVO[]>();
    if (ctdaremap == null || ctdaremap.size() == 0) {
      return null;
    }
    // �����տ�Э��PK��ȡ���������տ�Э��vo
    Map<String, IncomeChVO[]> incomechmap = this.getIncomeBill(incomeId);

    for (Map.Entry<String, ArapTermDateVO[]> ctdare : ctdaremap.entrySet()) {
      // ������ϸ��id
      String detailid = ctdare.getKey();
      // ��ȡ��ϸ�ж�Ӧ���տ�Э��������������
      UFDate[] paydates = incomeMap.get(detailid);
      // ��ȡ���������տ�Э��vo����
      IncomeChVO[] incomechVOs = incomechmap.get(toppaymentMap.get(detailid));
      Set<ArapTermDateVO> arapTermDateVOs = new HashSet<ArapTermDateVO>();
      int i = 0;
      for (UFDate paydate : paydates) {
        ArapTermDateVO arapTermDateVO = new ArapTermDateVO();
        // �����ǻ������ݵ��տ�Э�飬����ĵ�����ȡ���ǵ�ǰҵ������
        UFDate expiredate = AppContext.getInstance().getBusiDate();
        if (paydate != null) {
          // �������ա������ա��տ�Э��vo��ֵ��ArapTermDateVO
          // �����ۺ�ͬvoת��Ϊincomechvo�����������տ�Э�飩
          arapTermDateVO.setPaydate(paydate);
          arapTermDateVO.setIncomevo(incomechVOs[i]);
          // ������ӿڣ�����ȥ�����������ڵ��տ�Э��vo���ص�����
          expiredate = BillTermUtils.getExpiredateByTermDateVO(arapTermDateVO);
        }
        else {
          // �������ա������ա��տ�Э��vo��ֵ��ArapTermDateVO
          // �����ۺ�ͬvoת��Ϊincomechvo�����������տ�Э�飩
          arapTermDateVO.setPaydate(paydate);
          arapTermDateVO.setIncomevo(incomechVOs[i]);
        }
        arapTermDateVO.setExpiredate(expiredate);
        arapTermDateVOs.add(arapTermDateVO);
        i++;
      }
      newarapmap.put(detailid, arapTermDateVOs.toArray(new ArapTermDateVO[0]));
    }
    return newarapmap;
  }

  /**
   * ��ȡ������
   * 
   * @param keys
   *          ������ϸ��id
   * @param squareMap
   *          �����ϸʵ�����Դ������ʵ���map
   * @param invAndOutMap
   *          ��ŷ�Ʊ,������������ͺ�ͬ����
   * @param ctMap
   *          ��ź�ͬ�������տ�Э��vo
   * @param incomeperiodMap
   *          ����տ�ʱ���name��pk
   * @param top_billtype
   *          ���ε�������
   * @return map
   */
  private Map<String, UFDate[]> getperiodDate(String[] keys,
      Map<String, String> squareMap, Map<String, String> invAndOutMap,
      Map<String, CtSalePayTermVO[]> ctMap, String top_billtype) {
    Map<String, List<AccountDateType>> hashMap =
        new HashMap<String, List<AccountDateType>>();
    for (String key : keys) {
      if (ctMap == null || ctMap.size() == 0) {
        continue;
      }
      if (invAndOutMap == null || invAndOutMap.size() == 0) {
        continue;
      }
      if (squareMap == null || squareMap.size() == 0) {
        continue;
      }
      // ������ϸ��pk��ȡ
      CtSalePayTermVO[] ctSalePayTermVOs =
          ctMap.get(invAndOutMap.get(squareMap.get(key)));

      List<AccountDateType> list = new ArrayList<AccountDateType>();
      if (ctSalePayTermVOs != null) {
        for (CtSalePayTermVO ctSalePayTermVO : ctSalePayTermVOs) {
          // ��ȡ������������
          String pk_incomeperiod = ctSalePayTermVO.getPk_incomeperiod();
          list.add(getAccountDateType(pk_incomeperiod));
        }
      }
      hashMap.put(key, list);
    }
    QueryAccountDateFromM33Action action = new QueryAccountDateFromM33Action();
    Map<String, UFDate[]> ret = new HashMap<String, UFDate[]>();
    if (hashMap != null) {
      ret = action.queryAccountDate(hashMap, top_billtype);
    }
    return ret;
  }

  /**
   * ����ȡ�����տ���pkת����ö������
   * 
   * @param pk_incomeperiod
   * @return
   */
  private AccountDateType getAccountDateType(String pk_incomeperiod) {
    AccountDateType datetype = null;
    // ��������
    if (IPaymentUtil.OUT_STORE_DATE.equals(pk_incomeperiod)) {
      datetype = AccountDateType.OUT_STORE_DATE;
    }
    // ����ǩ������
    if (IPaymentUtil.OUTSTORE_SIGNATURE_DATE.equals(pk_incomeperiod)) {
      datetype = AccountDateType.OUTSTORE_SIGNATURE_DATE;
    }
    // ���ۺ�ͬ��Ч����
    if (IPaymentUtil.SALE_CONTRACT_EFFECTIVE_DATE.equals(pk_incomeperiod)) {
      datetype = AccountDateType.SALE_CONTRACT_EFFECTIVE_DATE;
    }
    // ���۷�Ʊ�������
    if (IPaymentUtil.SALE_INVOICE_APPROVE_DATE.equals(pk_incomeperiod)) {
      datetype = AccountDateType.SALE_INVOICE_APPROVE_DATE;
    }
    // ���ۿ�Ʊ����
    if (IPaymentUtil.SALE_MAKE_BILL_DATE.equals(pk_incomeperiod)) {
      datetype = AccountDateType.SALE_MAKE_BILL_DATE;
    }
    // ���۶�������
    if (IPaymentUtil.SALE_ORDER_DATE.equals(pk_incomeperiod)) {
      datetype = AccountDateType.SALE_ORDER_DATE;
    }
    return datetype;
  }

  /**
   * �����ۺ�ͬvoת��Ϊincomevo�����������տ�Э�飩
   * 
   * @param arapTermDateVO
   * @param cstVO
   *          ��ͬ�տ�Э��vo
   */
  private IncomeChVO setATDVOToCSTVO(CtSalePayTermVO cstVO) {
    IncomeChVO ichVO = new IncomeChVO();
    ichVO.setAccrate(cstVO.getAccrate());
    ichVO.setCheckdata(cstVO.getCheckdata());
    ichVO.setEffectaddmonth(cstVO.getEffectaddmonth());
    ichVO.setEffectdateadddate(cstVO.getEffectdateadddate());
    ichVO.setEffectmonth(cstVO.getEffectmonth());
    ichVO.setIsdeposit(cstVO.getIsdeposit());
    ichVO.setPaymentday(cstVO.getPaymentday());
    ichVO.setPk_balatype(cstVO.getPk_balatype());
    ichVO.setPk_incomeperiod(cstVO.getPk_incomeperiod());
    ichVO.setPk_rate(cstVO.getPk_rate());
    ichVO.setPrepayment(cstVO.getPrepayment());
    ichVO.setPrimaryKey(cstVO.getPrimaryKey());
    ichVO.setShoworder(cstVO.getShoworder());
    ichVO.setStatus(cstVO.getStatus());
    return ichVO;
  }

  /**
   * ��map��ȡ���ĺ�ͬ�������浽String[]��
   * 
   * @param ctsaleMap
   * @return
   */
  private String[] getcctmanageids(Map<String, String> ctsaleMap) {
    Iterator<Entry<String, String>> it = ctsaleMap.entrySet().iterator();
    Set<String> cctmanageids = new HashSet<String>();
    // ȡMap�к�ͬ����pk����������
    while (it.hasNext()) {
      String str = it.next().getValue();
      if (str != null) {
        cctmanageids.add(str);
      }
    }
    return cctmanageids.toArray(new String[cctmanageids.size()]);
  }

  /**
   * �������۷�Ʊ��ѯ��Դ��Դ��ͬ����
   * 
   * @param sqvos
   * @return
   */
  private Map<String, String> getPrimaryKeyBySaleInv(List<String> csbidList) {

    IDQueryBuilder builder = new IDQueryBuilder();
    String inSQL =
        builder.buildSQL(SaleInvoiceBVO.CSALEINVOICEBID,
            ListUtil.toArray(csbidList));
    String querySql =
        " select csaleinvoicebid, cctmanageid from so_saleinvoice_b where dr = 0 and "
            + inSQL;
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet results = utils.query(querySql.toString());
    Map<String, String> hashMap = new HashMap<String, String>();
    for (String[] res : results.toTwoDimensionStringArray()) {
      hashMap.put(res[0], res[1]);
    }
    return hashMap;
  }

  /**
   * �������۳����ѯ��Դ��ͬ����
   * 
   * @param sqvos
   * @return
   */
  private Map<String, String> getPrimaryKeyBySaleOut(List<String> csbidList) {

    IDQueryBuilder builder = new IDQueryBuilder();
    String inSQL =
        builder.buildSQL(SquareOutBVO.CSQUAREBILLBID,
            ListUtil.toArray(csbidList));
    // �������۳�����㵥�����۶������ڳ�����㵥��ȡԴͷ�����ӱ����۶����ӱ���������ѯ����ͬ���������۳��ⵥ����������������map
    String querySql =
        " select a.csquarebillbid, b.cctmanageid from so_squareout_b a left join so_saleorder_b b on a.cfirstbid = b.csaleorderbid where a.dr = 0 and b.dr = 0 and "
            + inSQL;
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet results = utils.query(querySql.toString());
    Map<String, String> hashMap = new HashMap<String, String>();
    for (String[] res : results.toTwoDimensionStringArray()) {
      hashMap.put(res[0], res[1]);
    }
    return hashMap;
  }

}
