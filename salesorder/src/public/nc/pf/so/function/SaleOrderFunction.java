package nc.pf.so.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.pf.so.function.para.ParaForCustMat;
import nc.pf.so.function.para.ParaForDeptMat;
import nc.pf.so.function.para.ParaForReturn;
import nc.pf.so.function.para.ParaForTranMat;
import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.pubitf.so.custmatrel.ICustMatRelFor30;
import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.pubitf.so.deptmatrel.IDeptMatRelFor30;
import nc.pubitf.so.m30.IReturnAssignMatch;
import nc.pubitf.so.m30.ReturnAssignMatchVO;
import nc.pubitf.so.tanmatrel.ITranMatRelFor30;
import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
import nc.pubitf.uapbd.ICustomerPubService_C;
import nc.pubitf.uapbd.IMaterialPubService_C;
import nc.vo.bd.cust.saleinfo.CustsaleVO;
import nc.vo.bd.material.sale.MaterialSaleVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.SaleMode;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.function.SOFunctionUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * ���۶�������
 * 
 * @since 6.0
 * @author ��־ΰ
 * @time 2010-10-18 ����10:21:03
 */
public class SaleOrderFunction {

  private SaleOrderFuncTransferTool<SaleOrderVO> transferTool;

  /**
   * ���ͻ������ϵĹ�ϵ
   * 
   * @param vo
   * @return
   * @throws BusinessException
   */
  public UFBoolean checkCustMatRel(AggregatedValueObject vo)
      throws BusinessException {
    try {
      SaleOrderVO bill = this.getFullBill(vo);
      ParaForCustMat paraforcustmat = new ParaForCustMat();
      CustMatRelParaVO[] paravos = paraforcustmat.getParavos(bill);
      ICustMatRelFor30 service =
          NCLocator.getInstance().lookup(ICustMatRelFor30.class);
      service.checkCustMatRel(paravos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return UFBoolean.TRUE;
  }

  /**
   * ��鲿�������Ϲ�ϵ
   * 
   * @param vo
   * @return
   * @throws BusinessException
   */
  public UFBoolean checkDeptMatRel(AggregatedValueObject vo)
      throws BusinessException {
    try {
      SaleOrderVO bill = this.getFullBill(vo);
      ParaForDeptMat parafordeptmat = new ParaForDeptMat();
      DeptMatRelParaVO[] paravos = parafordeptmat.getParavos(bill);
      IDeptMatRelFor30 service =
          NCLocator.getInstance().lookup(IDeptMatRelFor30.class);
      service.checkDeptMatRel(paravos);
      return UFBoolean.TRUE;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return UFBoolean.TRUE;
  }

  public UFBoolean checkOrgAndCust(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    String pk_org = bill.getParentVO().getPk_org();
    String pk_customer = bill.getParentVO().getCcustomerid();
    CustsaleVO custsalevo =
        CustomerPubService.getCustSaleVOByPk(pk_customer, pk_org, new String[] {
            CustsaleVO.PK_CUSTOMER
        });
    if (custsalevo == null) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0206")/*@res "�ͻ�û�з��䵽��������֯��"*/);
    }
    return UFBoolean.TRUE;
  }

  /**
   * ��齻�����������Ϲ�ϵ
   * 
   * @param vo
   * @return
   * @throws BusinessException
   */
  public UFBoolean checkTranMatRel(AggregatedValueObject vo)
      throws BusinessException {
    try {
      SaleOrderVO bill = this.getFullBill(vo);
      ParaForTranMat parafortranmat = new ParaForTranMat();
      TranMatRelParaVO[] paravos = parafortranmat.getParavos(bill);
      ITranMatRelFor30 service =
          NCLocator.getInstance().lookup(ITranMatRelFor30.class);
      service.checkTranMatRel(paravos);
      return UFBoolean.TRUE;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return UFBoolean.TRUE;

  }

  /**
   * ���������������տ����С�ڶ����տ��޶�
   * <p>
   * <b>֧�����۶���Լ�����ã�</b>
   * <ul>
   * <li>1.����ǰԼ�����ã�����(APPOVE)
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.�������տ����С�ڶ����տ��޶� = Y
   * </ul>
   * 
   * @param vo
   * @return UFBoolean
   * @author ��־ΰ
   * @time 2010-10-27 ����04:38:02
   */
  public UFBoolean examOrigBalMnyNotLessThanPreceiveMny(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SaleOrderHVO head = bill.getParentVO();
    // ����ʵ���տ���
    UFDouble receivedmny = head.getNreceivedmny();
    // �����տ��޶�
    UFDouble preceivequota = head.getNpreceivequota();
    // �����տ����С��(>=)�����տ��޶�
    if (MathTool.compareTo(receivedmny, preceivequota) < 0) {
      return UFBoolean.FALSE;
    }
    return UFBoolean.TRUE;
  }

  /**
   * ��������˵��۲���С�����ϵ���������ۼ�*�ͻ���������������ۼ۱�����
   * 
   * @param vo �ۺ�VO
   * @return UFBoolean
   */
  public UFBoolean checkAccountPrice(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SaleOrderHVO head = bill.getParentVO();
    SaleOrderBVO[] salebvos = bill.getChildrenVO();
    Set<String> materidset = new HashSet<String>();
    for (SaleOrderBVO salebvo : salebvos) {
      materidset.add(salebvo.getCmaterialvid());
    }
    String[] materids = materidset.toArray(new String[0]);
    Map<String, UFDouble> materpricemap =
        this.getMinpriceMultiplyCustratio(materids, head.getCcustomerid(),
            head.getPk_org());
    for (SaleOrderBVO salebvo : salebvos) {
      UFDouble naccprice = salebvo.getNaccprice();
      UFDouble materprice = materpricemap.get(salebvo.getCmaterialvid());
      if (MathTool.compareTo(naccprice, materprice) < 0) {
        return UFBoolean.TRUE;
      }
    }
    return UFBoolean.FALSE;
  }

  private Map<String, UFDouble> getMinpriceMultiplyCustratio(String[] materids,
      String custid, String pk_org) {
    Map<String, UFDouble> retmap = new HashMap<String, UFDouble>();
    IMaterialPubService_C materservice =
        NCLocator.getInstance().lookup(IMaterialPubService_C.class);
    Map<String, MaterialSaleVO> matervomap = null;
    CustsaleVO[] custvos = null;
    try {
      matervomap =
          materservice.queryMaterialSaleInfoByPks(materids, pk_org,
              new String[] {
              MaterialSaleVO.MINPRICE
          });
      ICustomerPubService_C custService =
          NCLocator.getInstance().lookup(ICustomerPubService_C.class);
      custvos = custService.getCustSaleVO(new String[] {
          custid
      }, pk_org, new String[] {
          CustsaleVO.STOCKPRICERATIO
      });
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappBusinessException(ex.toString());
    }
    if (matervomap == null) {
      return retmap;
    }
    for (String materid : matervomap.keySet()) {
      MaterialSaleVO msalevo = matervomap.get(materid);
      UFDouble minprice = msalevo.getMinprice();
      if (null != custvos && null != custvos[0]) {
        Integer stockpriceratio = custvos[0].getStockpriceratio();
        MathTool.nvl(minprice).multiply(stockpriceratio.intValue() / 100);
        retmap.put(materid, minprice);
      }
      else {
        retmap.put(materid, minprice);
      }
    }
    return retmap;
  }

  /**
   * ������������Ա��Ӧҵ��Ա��ͻ�ר��ҵ��Ա�Ƿ�Ϊͬһ��
   * <p>
   * <b>֧�����۶���Լ�����ã�</b>
   * <ul>
   * <li>1.����ǰԼ�����ã�д��(STORE)��������
   * <li>2.����ǰԼ�����ã�д��(STORE)�����޸�
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.����Ա��Ӧҵ��Ա��ͻ�ר��ҵ��Ա�Ƿ�Ϊͬһ�� = Y
   * </ul>
   * 
   * @param vo
   * @return UFBoolean
   * @author ��־ΰ
   * @time 2010-10-23 ����12:49:41
   */
  public UFBoolean examRespPerson(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SaleOrderHVO head = bill.getParentVO();
    String operator = InvocationInfoProxy.getInstance().getUserId();
    String psndoc = UserManageQuery.queryPsndocByUserid(operator);
    if (psndoc != null) {
      CustsaleVO custsaleVO =
          CustomerPubService.getCustSaleVOByPk(head.getCcustomerid(),
              head.getPk_org(), new String[] {
            CustsaleVO.RESPPERSON
          });
      if (custsaleVO != null && custsaleVO.getRespperson() != null
          && psndoc.trim().equals(custsaleVO.getRespperson().trim())) {
        return UFBoolean.TRUE;
      }
    }
    return UFBoolean.FALSE;
  }

  /**
   * ���������˻���˰(��˰)���۲��ܴ����������۵ĺ�˰(��˰)����
   * 2012-07-22�ͳ¶��ͨ���ȶԵ��������壬Ӧ�ø��� ��˰\��˰���Ȳ����ȶԺ�˰���ۻ���˰����
   * <p>
   * <b>֧�����۶���Լ�����ã�</b>
   * <ul>
   * <li>1.����ǰԼ�����ã�д��(STORE)��������
   * <li>2.����ǰԼ�����ã�д��(STORE)�����޸�
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.�˻���˰(��˰)���۲��ܴ����������۵ĺ�˰(��˰)���� = Y
   * </ul>
   * 
   * @param vo
   * @return UFBoolean
   * @author ��־ΰ
   * @time 2010-10-23 ����02:37:57
   */
  public UFBoolean examReturnPriceOverOrder(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SaleOrderHVO head = bill.getParentVO();
    UFBoolean so23 =
        SOSysParaInitUtil.getSO23(AppContext.getInstance().getPkGroup());

    // --1.�������ģʽΪ�˻����˻�������ͨ+�˻�����ͨ+�˻����Ķ���
    M30TranTypeVO trantypeVO = null;
    try {
      IM30TranTypeService service =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      trantypeVO = service.queryTranTypeVO(head.getCtrantypeid());
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    if (trantypeVO != null) {
      if (SaleMode.MODE_COMMON.equalsValue(trantypeVO.getFsalemode())) {
        return UFBoolean.TRUE;
      }
    }
    // --2.��ѯԴͷ��������ID
    String[] strBodyIDs = this.getFirstbid(bill);
    // --3.��ѯ���۶���price(��˰����)
    Map<String, UFDouble> hmOrderPrice =
        this.getOrderPriceByID(strBodyIDs, so23);
    // --4.���Ƚ�
    if (hmOrderPrice != null && hmOrderPrice.size() > 0) {
      SaleOrderBVO[] bodys = bill.getChildrenVO();
      for (SaleOrderBVO body : bodys) {
        UFDouble orderPrice = hmOrderPrice.get(body.getCfirstbid());
        UFDouble returnPrice =
            so23.booleanValue() ? body.getNqtorigtaxnetprc() : body
                .getNqtorignetprice();
            if (returnPrice != null
                && MathTool.compareTo(returnPrice, orderPrice) > 0) {
              return UFBoolean.FALSE;
            }
      }
    }
    return UFBoolean.TRUE;
  }

  /**
   * �����������۾��۲���С�����ϵ���������ۼ�*�ͻ���������������ۼ۱���:
   * ���м�顢�����в����㼴����false
   * <p>
   * <b>֧�����۶���Լ�����ã�</b>
   * <ul>
   * <li>1.����ǰԼ�����ã�д��(STORE)��������
   * <li>2.����ǰԼ�����ã�д��(STORE)�����޸�
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.���۾��۲���С�����ϵ���������ۼ�*�ͻ���������������ۼ۱��� = Y
   * </ul>
   * 
   * @param vo
   * @return UFBoolean
   * @author ��־ΰ
   * @time 2010-10-20 ����09:23:56
   */
  public UFBoolean examSaleNetprice(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);

    SOFunctionUtil util = new SOFunctionUtil();
    return util.examSaleNetprice(bill);
  }

  /**
   * �����������۶�����ͷ�ϼƽ�ԭ�Һ�˰����֧��ԭ�˻����뵥�ĵ��ݺ���
   * <p>
   * <b>֧�����۶���Լ�����ã�</b>
   * <ul>
   * <li>1.����ǰԼ�����ã�д��(STORE)��������
   * <li>2.����ǰԼ�����ã�д��(STORE)�����޸�
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.���۶�����ͷ�ϼƽ�ԭ�Һ�˰�� < 100
   * <li>2.���۶�����ͷ�ϼƽ�ԭ�Һ�˰�� >= 100
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author ��־ΰ
   * @time 2010-10-18 ����10:21:03
   */
  public UFDouble getHeadSumMny(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    UFDouble ntotalorigmny = bill.getParentVO().getNtotalorigmny();
    if (ntotalorigmny == null) {
      return UFDouble.ZERO_DBL;
    }
    return ntotalorigmny;
  }

  /**
   * �����������������з��������붩�����ڵ�����ֵ
   * <p>
   * <b>֧�����۶���Լ�����ã�</b>
   * <ul>
   * <li>1.����ǰԼ�����ã�д��(STORE)��������
   * <li>2.����ǰԼ�����ã�д��(STORE)�����޸�
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.���������з��������붩�����ڵ�����ֵ < 100
   * <li>2.���������з��������붩�����ڵ�����ֵ >= 100
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author ��־ΰ
   * @time 2010-10-23 ����02:37:57
   */
  public int getMaxDate(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    UFDate billDate = bill.getParentVO().getDbilldate();
    SaleOrderBVO[] bodys = bill.getChildrenVO();
    int ret = 0;
    for (SaleOrderBVO body : bodys) {
      int date =
          UFDate.getDaysBetween(new UFDate(billDate.toStdString()), new UFDate(
              body.getDsenddate().toStdString()));
      if (date > ret) {
        ret = date;
      }
    }
    return ret;
  }

  /**
   * �������������������ۿ�*��Ʒ�ۿ۵����ֵ
   * <p>
   * <b>֧�����۶���Լ�����ã�</b>
   * <ul>
   * <li>1.����ǰԼ�����ã�д��(STORE)��������
   * <li>2.����ǰԼ�����ã�д��(STORE)�����޸�
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.�����������ۿ�*��Ʒ�ۿ۵����ֵ < 0.5
   * <li>2.�����������ۿ�*��Ʒ�ۿ۵����ֵ >= 0.5
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author ��־ΰ
   * @time 2010-10-23 ����12:49:41
   */
  public UFDouble getMaxDiscountRate(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble maxDiscountRate = util.getMaxDiscountRate(bill);
    return maxDiscountRate;
  }

  /**
   * �����������������еľ���/ѯ�����۵����ֵ
   * <p>�˺�����ҪΪ��ѯ�ۺ�ļۣ����Ƹļ۵ķ�Χ
   * <p>
   * <b>֧�����۶���Լ�����ã�</b>
   * <ul>
   * <li>1.����ǰԼ�����ã�д��(STORE)��������
   * <li>2.����ǰԼ�����ã�д��(STORE)�����޸�
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.���������еľ���/ѯ�����۵����ֵ < 100
   * <li>2.���������еľ���/ѯ�����۵����ֵ >= 100
   * </ul>
   * 
   * @param vo
   * @return
   * @author ��־ΰ
   * @time 2010-10-19 ����01:45:01
   */
  public UFDouble getMaxPriceRate(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble dMaxPriceRate = util.getMaxPriceRate(bill);
    return dMaxPriceRate;
  }

  /**
   * �����������������з��������붩�����ڵ���С��ֵ
   * <p>
   * <b>֧�����۶���Լ�����ã�</b>
   * <ul>
   * <li>1.����ǰԼ�����ã�д��(STORE)��������
   * <li>2.����ǰԼ�����ã�д��(STORE)�����޸�
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.���������з��������붩�����ڵ���С��ֵ < 100
   * <li>2.���������з��������붩�����ڵ���С��ֵ >= 100
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author ��־ΰ
   * @time 2010-10-23 ����02:37:57
   */
  public int getMinDate(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    UFDate billDate = bill.getParentVO().getDbilldate();
    SaleOrderBVO[] bodys = bill.getChildrenVO();
    int ret = 1000000;
    for (SaleOrderBVO body : bodys) {
      int date =
          UFDate.getDaysBetween(new UFDate(billDate.toStdString()), new UFDate(
              body.getDsenddate().toStdString()));
      if (date < ret) {
        ret = date;
      }
    }
    return ret;
  }

  /**
   * �������������������ۿ�*��Ʒ�ۿ۵���Сֵ
   * <p>
   * <b>֧�����۶���Լ�����ã�</b>
   * <ul>
   * <li>1.����ǰԼ�����ã�д��(STORE)��������
   * <li>2.����ǰԼ�����ã�д��(STORE)�����޸�
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.�����������ۿ�*��Ʒ�ۿ۵���Сֵ < 0.5
   * <li>2.�����������ۿ�*��Ʒ�ۿ۵���Сֵ >= 0.5
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author ��־ΰ
   * @time 2010-10-23 ����12:49:41
   */
  public UFDouble getMinDiscountRate(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble minDiscountRate = util.getMinDiscountRate(bill);
    return minDiscountRate;
  }

  /**
   * �����������������еľ���/ѯ�����۵���Сֵ
   * <p>
   * <b>֧�����۶���Լ�����ã�</b>
   * <ul>
   * <li>1.����ǰԼ�����ã�д��(STORE)��������
   * <li>2.����ǰԼ�����ã�д��(STORE)�����޸�
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.���������еľ���/ѯ�����۵���Сֵ < 100
   * <li>2.���������еľ���/ѯ�����۵���Сֵ >= 100
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author ��־ΰ
   * @time 2010-10-19 ����01:45:01
   */
  public UFDouble getMinPriceRate(AggregatedValueObject vo) {
    SaleOrderVO bill = this.getFullBill(vo);
    SOFunctionUtil util = new SOFunctionUtil();
    UFDouble dMinPriceRate = util.getMinPriceRate(bill);
    return dMinPriceRate;
  }

  /**
   * ������������������δ����ϼ�������
   * <p>
   * <b>֧�����۶���Լ�����ã�</b>
   * <ul>
   * <li>1.����ǰԼ�����ã�д��(STORE)��������
   * <li>2.����ǰԼ�����ã�д��(STORE)�����޸�
   * <li>3.����ǰԼ�����ã��޶�()
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.����������δ����ϼ������� > 100
   * <li>2.����������δ����ϼ������� <= 100
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author ��־ΰ
   * @time 2010-10-20 ����09:23:56
   */
  public UFDouble getNotNtotaloutnum(AggregatedValueObject vo) {
    return this.getNotExecuteTotalNnumByKey(vo, SaleOrderBVO.NTOTALOUTNUM);
  }

  /**
   * ������������������δ�����ϼ�������
   * <p>
   * <b>֧�����۶���Լ�����ã�</b>
   * <ul>
   * <li>1.����ǰԼ�����ã�д��(STORE)��������
   * <li>2.����ǰԼ�����ã�д��(STORE)�����޸�
   * <li>3.����ǰԼ�����ã��޶�()
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.����������δ�����ϼ������� > 100
   * <li>2.����������δ�����ϼ������� <= 100
   * <li>3...
   * </ul>
   * 
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @return UFDouble
   *         <p>
   * @author ��־ΰ
   * @time 2010-10-20 ����09:23:56
   */
  public UFDouble getNotNtotalsendnum(AggregatedValueObject vo) {
    return this.getNotExecuteTotalNnumByKey(vo, SaleOrderBVO.NTOTALSENDNUM);
  }

  /**
   * ���������������ֿ���(���۶�����ͷ)
   * <p>
   * <b>֧�����۶���Լ�����ã�</b>
   * <ul>
   * <li>1.����ǰԼ�����ã�д��(STORE)��������
   * <li>2.����ǰԼ�����ã�д��(STORE)�����޸�
   * <li>3.����ǰԼ�����ã�����(APPOVE)
   * </ul>
   * <p>
   * <b>���÷�ʽ,���磺</b>
   * <ul>
   * <li>1.�������ֿ��� > 100
   * </ul>
   * 
   * @param vo
   * @return UFDouble
   * @author ��־ΰ
   * @time 2010-10-27 ����04:38:02
   */
  public UFDouble getOrigBalMny(AggregatedValueObject vo) {
    SaleOrderVO bill = (SaleOrderVO) vo;
    UFDouble nreceivedmny = bill.getParentVO().getNreceivedmny();
    if (nreceivedmny != null) {
      return bill.getParentVO().getNreceivedmny();
    }
    return UFDouble.ZERO_DBL;
  }

  /**
   * �����˻����߼��
   * 
   * @param vo
   * @return
   * @throws BusinessException
   */
  public UFBoolean judge(AggregatedValueObject vo) throws BusinessException {
    try {
      SaleOrderVO bill = this.getFullBill(vo);
      ParaForReturn paraforreturn = new ParaForReturn();
      ReturnAssignMatchVO[] paravos = paraforreturn.getParavos(bill);
      IReturnAssignMatch service =
          NCLocator.getInstance().lookup(IReturnAssignMatch.class);
      Map<String, String> map = service.matchReturnAssign(paravos);
      if (map.size() > 0) {
        // // SaleOrderVO oldbill = (SaleOrderVO) vo;
        // SaleOrderBVO[] bvos = bill.getChildrenVO();
        // for (SaleOrderBVO bvo : bvos) {
        // int state = bvo.getStatus();
        // String row = bvo.getCrowno();
        // if (state == VOStatus.NEW) {
        // bvo.setCretpolicyid(map.get(row));
        // bvo.setStatus(VOStatus.NEW);
        // continue;
        // }
        // bvo.setCretpolicyid(map.get(row));
        // bvo.setStatus(VOStatus.UPDATED);
        // }
        // VOUpdate<SaleOrderBVO> bo = new VOUpdate<SaleOrderBVO>();
        // String[] names = new String[] {
        // SaleOrderBVO.CRETPOLICYID
        // };
        // bvos= bo.update(bvos, names);
      }

      return UFBoolean.TRUE;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return UFBoolean.TRUE;
  }

  private SaleOrderVO getClientInfoFullBill(SaleOrderVO bill) {
    if (this.transferTool == null) {
      SaleOrderVO[] bills = new SaleOrderVO[] {
          bill
      };
      this.transferTool =
          new SaleOrderFuncTransferTool<SaleOrderVO>(bills, UFBoolean.TRUE);
    }
    return this.transferTool.getClientFullInfoBill()[0];
  }

  /**
   * ���Դͷ���۶�������ID
   * 
   * @param vo AggregatedValueObject
   * @return SaleOrderVO
   * @author ��־ΰ
   * @time 2010-10-25 ����09:55:15
   */
  private String[] getFirstbid(SaleOrderVO bill) {
    List<String> alFirstbids = null;
    SaleOrderBVO[] bodys = bill.getChildrenVO();
    for (SaleOrderBVO body : bodys) {
      if (SOBillType.Order.getCode().equals(body.getVfirsttype())
          && body.getCfirstbid() != null
          && body.getCfirstbid().trim().length() > 0) {
        if (alFirstbids == null) {
          alFirstbids = new ArrayList<String>();
        }
        alFirstbids.add(body.getCfirstbid());
      }
    }
    if (alFirstbids != null && alFirstbids.size() > 0) {
      return alFirstbids.toArray(new String[0]);
    }
    return null;
  }

  /**
   * �����������޸Ļ�ò�ȫVO
   * 
   * @param vo AggregatedValueObject
   * @return SaleOrderVO
   * @author ��־ΰ
   * @time 2010-10-20 ����09:23:56
   */
  private SaleOrderVO getFullBill(AggregatedValueObject vo) {
    // ����
    SaleOrderVO bill = (SaleOrderVO) vo;
    // �޸�
    String strOrderID = bill.getParentVO().getCsaleorderid();
    if (strOrderID != null && strOrderID.trim().length() > 0) {
      bill = this.getClientInfoFullBill(bill);
    }
    return bill;
  }

  /**
   * ��ñ�����δִ��key�ϼ����������ϼ������� - �ϼ�key����
   * 
   * @param vo AggregatedValueObject
   * @param key �ۼ�ִ��*����
   * @return UFDouble
   * @author ��־ΰ
   * @time 2010-10-20 ����09:23:56
   */
  private UFDouble getNotExecuteTotalNnumByKey(AggregatedValueObject vo,
      String key) {
    UFDouble retTotalnum = UFDouble.ZERO_DBL;
    SaleOrderVO bill = (SaleOrderVO) vo;
    // ��ȫVO
    SaleOrderVO clientBill = this.getClientInfoFullBill(bill);
    // �޶�
    if (BillStatus.AUDIT.equalsValue(clientBill.getParentVO().getFstatusflag())) {
      retTotalnum =
          SoVoTools.getTotalMnySub(clientBill.getChildrenVO(),
              SaleOrderBVO.NNUM, clientBill.getChildrenVO(), key);
    }
    return retTotalnum;
  }

  /**
   * ���ݱ���IDs���Դͷ���۶������庬˰����(ԭ�Һ�˰����)
   * 
   * @param vo AggregatedValueObject
   * @return SaleOrderVO
   * @author ��־ΰ
   * @param so23
   * @time 2010-10-25 ����09:55:15
   */
  private Map<String, UFDouble> getOrderPriceByID(String[] strBodyIDs,
      UFBoolean so23) {
    Map<String, UFDouble> retMap = null;
    if (strBodyIDs == null) {
      return retMap;
    }
    VOQuery<SaleOrderBVO> voQuery =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, new String[] {
				SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.NQTORIGTAXNETPRC, 
					SaleOrderBVO.NQTORIGNETPRICE});
    SaleOrderBVO[] bodys = voQuery.query(strBodyIDs);
    if (bodys != null && bodys.length > 0) {
      for (SaleOrderBVO body : bodys) {
        if (retMap == null) {
          retMap = new HashMap<String, UFDouble>();
        }
        retMap.put(
            body.getCsaleorderbid(),
            so23.booleanValue() ? body.getNqtorigtaxnetprc() : body
                .getNqtorignetprice());
      }
    }
    return retMap;
  }
}
