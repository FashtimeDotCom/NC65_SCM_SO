package nc.bs.so.m30.rule.m35;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m35.so.m30.IArsubToSaleorder;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SaleOrderVOUtil;
import nc.vo.so.m35.entity.ArsubBVO;
import nc.vo.so.m35.entity.ArsubHVO;
import nc.vo.so.m35.entity.ArsubInterfaceVO;
import nc.vo.so.m35.entity.ArsubViewVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.m35trantype.entity.M35TranTypeVO;

/**
 * 
 * @description
 * ���۶�������ʱ
 * @scene
 * ���۶�������ʱ��Ʒ�Ҹ����õ���ֱ������
 * @param
 * allcanoffsetmny ���õ��ɳ�ֽ��
 * alloffsettaxmny ���������ֽ��
 * hmrelation ���γ�ֵĳ�ֹ�ϵ
 * @since 6.35
 * @version 2013-11-28 ����03:51:53
 * @author dongli2
 */
public class ArsubOffsetAfterSaveRule implements IRule<SaleOrderVO> {

  /** ���õ��ɳ�ֽ�� */
  private UFDouble allcanoffsetmny = UFDouble.ZERO_DBL;

  /** ���������ֽ�� */
  private UFDouble alloffsettaxmny = UFDouble.ZERO_DBL;

  /** ���γ�ֵĳ�ֹ�ϵ */
  private Map<String, UFDouble> hmrelation = new HashMap<String, UFDouble>();

  @Override
  public void process(SaleOrderVO[] vos) {
    List<OffsetTempVO> tempvos = new ArrayList<OffsetTempVO>();
    SaleOrderVOUtil voutil = new SaleOrderVOUtil();
    List<SaleOrderVO> arordervo = new ArrayList<SaleOrderVO>();
    boolean isarsub = false;
    for (SaleOrderVO bill : vos) {
      SaleOrderHVO header = bill.getParentVO();
      String arsubtypeid = header.getCarsubtypeid();
      if (null != arsubtypeid) {
    	if (!SysInitGroupQuery.isMeEnabled()) {
  			ExceptionUtils
  					.wrappBusinessException(NCLangResOnserver.getInstance()
  							.getStrByID("4006011_0", "04006011-0519")/*������Ӫ������ģ�飡*/);
    	}
        // 1.���˱�����
        Map<Integer, OffsetParaVO> newitfvo = this.getinterfaceData(bill);
        // 2.����Ƿ��пɳ�ֵ���
        if (null == newitfvo || newitfvo.size() == 0) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0499")/*��˰�ϼ�Ϊ0���в�������Ʒ�Ҹ���*/);
        }
        ArsubViewVO[] viewoffsets = null;
        try {
          viewoffsets = this.queryArsubViewVO(bill, newitfvo);
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0500")/*��ѯ�ͻ����õ�����*/);
        }
        if (null != viewoffsets && viewoffsets.length > 0) {
          // 3.���
          ArsubViewVO[] views = this.filterArsubViewVO(viewoffsets, newitfvo);
          if (null != views
              && views.length > 0
              && !MathTool.greaterThan(this.alloffsettaxmny,
                  this.allcanoffsetmny)) {
            this.offsetArsubSave(bill, newitfvo, views);
          }
          else {
        	  String errorMsg =
                      NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0501")/*�ͻ��������㣬��ǰ������Ʒ�Ҹ����Ϊ��(%s)��ƥ�䵽�Ŀͻ����õ��ܿɳ�ֽ��Ϊ��(%s)�������������Ʒ�Ҹ���*/;
            // add by wangshu6 for 636 ��ʾ��Ϣ���ȴ��� 20150413
            ScaleUtils scale = ScaleUtils.getScaleUtilAtBS();
            String currid = header.getCorigcurrencyid();
                this.alloffsettaxmny = scale.adjustMnyScale(this.alloffsettaxmny, currid);
                this.allcanoffsetmny = scale.adjustMnyScale(this.allcanoffsetmny, currid);
            errorMsg =
                String.format(errorMsg, this.alloffsettaxmny,
                    this.allcanoffsetmny);
            ExceptionUtils.wrappBusinessException(errorMsg);
          }

          // 3.���ó�ֹ�ϵ����
          OffsetTempVO tempvo = new OffsetTempVO();
          tempvo.setHmArsubRelation(this.hmrelation);
          tempvo.setIsCancelOffset(false);
          tempvos.add(tempvo);
          arordervo.add(bill);

          isarsub = true;
        }
        else {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0502")/*û��ƥ�䵽�ͻ����õ�������*/);
        }
      }
    }
    if (isarsub) {
      // 4.��д���õ���ֹ�ϵ
      SaleOrderVO[] forwirteoder =
          arordervo.toArray(new SaleOrderVO[arordervo.size()]);
      OffsetTempVO[] forwritetemp =
          tempvos.toArray(new OffsetTempVO[tempvos.size()]);
      ArsubInterfaceVO[] arsubvo =
          voutil.changeToArsubInterfaceVO(forwirteoder);
      IArsubToSaleorder service =
          NCLocator.getInstance().lookup(IArsubToSaleorder.class);
      try {
        service.writeArsubRelationForArsub(arsubvo, forwritetemp);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }
    }
  }

  /**
   * ��������ʱ��Ʒ�Ҹ����
   * 
   * @param bill
   * @param newitfvo
   * @param totalCanSubMny
   * @param viewoffsets
   */
  private void offsetArsubSave(SaleOrderVO bill,
      Map<Integer, OffsetParaVO> newitfvo, ArsubViewVO[] offsetviews) {
    // 1.ѭ��OffsetParaVO���г��
    for (Entry<Integer, OffsetParaVO> para : newitfvo.entrySet()) {
      OffsetParaVO paravo = para.getValue();
      // ���ַ��õ����
      UFDouble realviewtotalmny = UFDouble.ZERO_DBL;
      // 2.ѭ��ArsubViewVO���г�ַ���
      for (ArsubViewVO arviewvo : offsetviews) {
        UFDouble remainmny = UFDouble.ZERO_DBL;
        UFDouble submny = UFDouble.ZERO_DBL;
        // 2.1 ��ʼ��ArsubViewVO��ֽ��
        UFDouble viewtotalmny = arviewvo.getItem().getNorigarsubmny();
        UFDouble ordersubmny = arviewvo.getItem().getNordersubmny();
        UFDouble invoicesubmny = arviewvo.getItem().getNinvoicesubmny();
        UFDouble redarsubmny = arviewvo.getItem().getNredarsubmny();
        UFDouble lrgcashmny = arviewvo.getItem().getNlrgcashmny();
        UFDouble arviewsub =
            this.hmrelation.get(arviewvo.getItem().getCarsubbid());

        submny =
            MathTool.add(
                MathTool.add(MathTool.add(ordersubmny, arviewsub),
                    MathTool.add(invoicesubmny, redarsubmny)), lrgcashmny);
        remainmny = MathTool.sub(viewtotalmny, submny);
        // 2.2 ��ʼ��OffsetParaVO��ֽ��
        UFDouble orderremainmny =
            MathTool.sub(paravo.getOrigtaxmny(), paravo.getOrigsubmny());
        UFDouble thisordersub = UFDouble.ZERO_DBL;

        // 2.3����ArsubViewVO����
        if (MathTool.isDiffSign(orderremainmny, remainmny)) {
          // �������� add by zhangby5 δ�ۼӺ��ֽ��
          realviewtotalmny = MathTool.add(realviewtotalmny,MathTool.oppose(remainmny));
          // �����ֹ�ϵ,�����Ѵ�����
          thisordersub = remainmny;
        }
        else {
          // �����ֽ��δ������
          if (!realviewtotalmny.equals(UFDouble.ZERO_DBL)) {
            // 2.4��ֺ��ֽ��
            if (MathTool.greaterThan(remainmny, realviewtotalmny)) {
              remainmny = MathTool.sub(remainmny, realviewtotalmny);
              thisordersub =
                  this.normalOffsetCalculate(paravo, remainmny,
                      orderremainmny);
              // ��ֺ��ֲ���Ҳ�����ֹ�ϵ
              thisordersub = MathTool.add(thisordersub, realviewtotalmny);
              realviewtotalmny = UFDouble.ZERO_DBL;
            }
            else if (MathTool.equals(remainmny, realviewtotalmny)) {
              thisordersub = realviewtotalmny;
              realviewtotalmny = UFDouble.ZERO_DBL;
            }
            else if (MathTool.lessThan(remainmny, realviewtotalmny)) {
              thisordersub = remainmny;
              realviewtotalmny = MathTool.sub(realviewtotalmny, remainmny);
            }
          }
          else {
            // 2.5 �����г��(���޺��ֽ����޺��ַ��õ�)
            thisordersub =
                this.normalOffsetCalculate(paravo, remainmny,
                    orderremainmny);
            if (thisordersub.compareTo(UFDouble.ZERO_DBL) == 0) {
              continue;
            }
          }
        }
        remainmny =
 MathTool.add(thisordersub, arviewsub);
        // 2.4 ��¼��ֹ�ϵ
        this.hmrelation.put(arviewvo.getItem().getCarsubbid(), remainmny);
        orderremainmny = MathTool.sub(orderremainmny, thisordersub);
        if (orderremainmny.compareTo(UFDouble.ZERO_DBL) == 0) {
          break;
        }
      }
      this.changeOrderbody(para.getKey(), bill, newitfvo);
    }
  }

  /**
   * ��ֹ��̼���
   * 
   * @param paravo
   * @param orderarscale
   * @param remainmny
   * @param orderremainmny
   * @return
   */
  private UFDouble normalOffsetCalculate(OffsetParaVO paravo,
      UFDouble remainmny, UFDouble orderremainmny) {
    UFDouble ordersub;
    UFDouble thisordersub;
    if (MathTool.greaterThan(orderremainmny, remainmny)) {
      thisordersub = remainmny;
      ordersub = paravo.getOrigsubmny();
      ordersub =
 MathTool.add(ordersub, thisordersub);
      paravo.setOrigsubmny(ordersub);
    }
    else {
      thisordersub =
 orderremainmny;
      ordersub = paravo.getOrigsubmny();
      ordersub =
 MathTool.add(ordersub, thisordersub);
      paravo.setOrigsubmny(ordersub);
    }
    return thisordersub;
  }

  /**
   * ������Ʒ����Ʒ�Ҹ����
   * 
   * @param thissub
   * @param rowindex
   */
  private void changeOrderbody(Integer rowindex, SaleOrderVO bill,
      Map<Integer, OffsetParaVO> newitfvo) {
    OffsetParaVO bvo = newitfvo.get(rowindex);
    UFDouble origtaxmny = bvo.getOrigtaxmny();
    UFDouble origsubmny = bvo.getOrigsubmny();
    if (!origtaxmny.equals(origsubmny)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0503")/*���ó��ʧ�ܣ����飡*/);
    }
  }

  /**
   * �ռ����۷��õ�ʹ�õĽӿ���������ƴ��Ĭ��sql�Ͱ��չ�����з���
   * 
   * @return �ӿ�vo
   */
  private Map<Integer, OffsetParaVO> getinterfaceData(SaleOrderVO bill) {
    Map<Integer, OffsetParaVO> offsetvomap =
        new HashMap<Integer, OffsetParaVO>();
    for (int i = 0; i < bill.getChildrenVO().length; i++) {
      // ��˰�ϼ�Ϊ�ջ�0�Ĳ����
      UFDouble origtaxmny = bill.getChildrenVO()[i].getNorigtaxmny();
      if (null == origtaxmny || origtaxmny.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      String settleorg = bill.getChildrenVO()[i].getCsettleorgid();

      // ���ýӿ�VO��ʵ��
      OffsetParaVO bvo = this.getBVO(i, settleorg, bill);
      offsetvomap.put(Integer.valueOf(i), bvo);
      // �ۼƶ�����˰�ϼ�
      this.alloffsettaxmny =
          MathTool.add(this.alloffsettaxmny, bvo.getOrigtaxmny());
    }
    return offsetvomap;
  }

  /**
   * �ռ�������������
   * 
   * @param i ������
   * @param settleorg ������֯
   * @return
   */
  private OffsetParaVO getBVO(int i, String settleorg, SaleOrderVO bill) {
    OffsetParaVO vo = new OffsetParaVO();
    vo.setSaleorg(bill.getParentVO().getPk_org());
    vo.setPk_group(bill.getParentVO().getPk_group());
    vo.setInvoicecusts(bill.getParentVO().getCinvoicecustid());
    vo.setCorigcurrencyid(bill.getParentVO().getCorigcurrencyid());
    vo.setOrdercusts(bill.getParentVO().getCcustomerid());
    vo.setOrdertrantype(bill.getParentVO().getCtrantypeid());
    vo.setOrigsubmny(bill.getChildrenVO()[i].getNorigsubmny());
    vo.setOrigtaxmny(bill.getChildrenVO()[i].getNorigtaxmny());
    vo.setProdline(bill.getChildrenVO()[i].getCprodlineid());
    vo.setSaleorg(bill.getParentVO().getPk_org());
    vo.setSettleorg(settleorg);
    return vo;
  }

  /**
   * �������۶�����ѯ���õ���ͼVO
   * 
   * @return ArsubViewVO
   */
  private ArsubViewVO[] queryArsubViewVO(SaleOrderVO bill,
      Map<Integer, OffsetParaVO> newitfvo) throws BusinessException {
    List<String> settleorgs = new ArrayList<String>();
    String ordercust = bill.getParentVO().getCcustomerid();
    String invcust = bill.getParentVO().getCinvoicecustid();
    String corigcurrencyid = bill.getParentVO().getCorigcurrencyid();
    String carsubtypeid = bill.getParentVO().getCarsubtypeid();
    String csaleorgid = bill.getParentVO().getPk_org();
    M35TranTypeVO m35TranTypeVo = this.queryTranTypeVO(carsubtypeid);

    boolean isByInvoiceCust = m35TranTypeVo.getBsubinvcustflag().booleanValue();
    boolean isBySaleOrg = m35TranTypeVo.getBsubsaleorgflag().booleanValue();

    for (Entry<Integer, OffsetParaVO> entry : newitfvo.entrySet()) {
      OffsetParaVO vo = entry.getValue();
      settleorgs.add(vo.getSettleorg());
    }
    String last = null;
    for (int i = 0; i < settleorgs.size(); i++) {
      if (last == null) {
        last = settleorgs.get(i);
      }
      else {
        if (!last.equals(settleorgs.get(i))) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0504")/*��Ʒ�Ҹ������۶��������н��������֯����һ��.*/);
        }
      }
    }

    SqlBuilder sql =
        this.querySqlBuilder(settleorgs.get(0), ordercust, invcust,
            corigcurrencyid, carsubtypeid, isByInvoiceCust, isBySaleOrg,
            csaleorgid);

    // ��ѯ���۷��õ�
    ArsubViewVO[] bills = null;
    IArsubToSaleorder queryarsrv =
        NCLocator.getInstance().lookup(IArsubToSaleorder.class);
    try {
      bills = queryarsrv.queryOffsetArSub(sql.toString());
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return bills;
  }

  public M35TranTypeVO queryTranTypeVO(String ctrantypeid)
      throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ");
    sql.append(M35TranTypeVO.CTRANTYPEID, ctrantypeid);
    VOQuery<M35TranTypeVO> srv =
        new VOQuery<M35TranTypeVO>(M35TranTypeVO.class);
    M35TranTypeVO[] vos = srv.query(sql.toString(), null);
    return vos[0];
  }

  /**
   * ������ѯ�ű�
   * 
   * @param settleorgs
   * @param ordercust
   * @param invcust
   * @param corigcurrencyid
   * @param carsubtypeid
   * @param isByInvoiceCust
   * @param isBySaleOrg
   * @return SqlBuilder
   */
  private SqlBuilder querySqlBuilder(String settleorg, String ordercust,
      String invcust, String corigcurrencyid, String carsubtypeid,
      boolean isByInvoiceCust, boolean isBySaleOrg, String csaleorgid) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select so_arsub_b.carsubbid from so_arsub ");
    sql.append(" inner join so_arsub_b so_arsub_b");
    sql.append(" on so_arsub.carsubid=so_arsub_b.carsubid");
    sql.append(" where ");
    sql.append(" so_arsub." + ArsubHVO.PK_ORG, settleorg);
    sql.append(" and ");
    if (isBySaleOrg) {
      sql.append(" so_arsub." + ArsubHVO.CSALEORGID, csaleorgid);
      sql.append(" and ");
    }
    sql.append(" so_arsub." + ArsubHVO.CORIGCURRENCYID, corigcurrencyid);
    sql.append(" and ");
    sql.append(" so_arsub." + ArsubHVO.CTRANTYPEID, carsubtypeid);
    sql.append(" and ");
    if (isByInvoiceCust) {
      sql.append(" so_arsub." + ArsubHVO.CINVOICECUSTID, invcust);
    }
    else {
      sql.append(" so_arsub." + ArsubHVO.CORDERCUSTID, ordercust);
    }
    sql.append(" and so_arsub." + ArsubHVO.FSTATUSFLAG, 2);
    sql.append(" and so_arsub.dr = 0 ");
    sql.append(" order ");
    sql.append(" by so_arsub." + ArsubHVO.DBILLDATE);
    sql.append(" , so_arsub_b." + ArsubBVO.CARSUBBID);
    return sql;
  }

  /**
   * ���õ�ɸѡ
   * 
   * @param vos
   * @param hmtran
   * @param newitfvo
   * @return newViewVOS
   */
  private ArsubViewVO[] filterArsubViewVO(ArsubViewVO[] vos,
      Map<Integer, OffsetParaVO> newitfvo) {
    List<ArsubViewVO> newViewVOS = new ArrayList<ArsubViewVO>();
    List<ArsubViewVO> newRedViewVOS = new ArrayList<ArsubViewVO>();
    List<String> bidslist = new ArrayList<String>();
    for (ArsubViewVO vo : vos) {
      boolean isNeed = false;
      String arsubprodline = vo.getItem().getCprodlineid();
      String trantype = vo.getItem().getCordertrantypeid();
      String arsubpk_org = vo.getItem().getPk_org();
      for (Integer row : newitfvo.keySet()) {
        OffsetParaVO bvo = newitfvo.get(row);
        // ���۷��õ���Ʒ�߲�Ϊ�գ����Һ����۶������岻ͬ�������
        String prodline = bvo.getProdline();
        if (null != arsubprodline && !arsubprodline.equals(prodline)) {
          continue;
        }
        String ordertran = bvo.getOrdertrantype();
        if (null != trantype && !trantype.equals(ordertran)) {
          continue;
        }
        String settleorg = bvo.getSettleorg();
        if (!arsubpk_org.equals(settleorg)) {
          continue;
        }
        isNeed = true;
      }
      // ���ٷ���һ�������е�View������Ҫ�ķ��õ�
      if (isNeed) {
        bidslist.add(vo.getItem().getCarsubbid());
        // �ۼƿɳ�ַ��ý��
        UFDouble viewtotalmny = vo.getItem().getNorigarsubmny();
        if (MathTool.compareTo(viewtotalmny, UFDouble.ZERO_DBL) < 0) {
          newRedViewVOS.add(vo);
        }
        else {
          newViewVOS.add(vo);
        }
        UFDouble ordersubmny = vo.getItem().getNordersubmny();
        UFDouble invoicesubmny = vo.getItem().getNinvoicesubmny();
        UFDouble redarsubmny = vo.getItem().getNredarsubmny();
        UFDouble lrgcashmny = vo.getItem().getNlrgcashmny();
        UFDouble viewusedmny =
            MathTool.add(
                MathTool.add(ordersubmny,
                    MathTool.add(invoicesubmny, redarsubmny)), lrgcashmny);
        UFDouble viewmny = MathTool.sub(viewtotalmny, viewusedmny);
        this.allcanoffsetmny = MathTool.add(this.allcanoffsetmny, viewmny);
      }
    }
    String[] bids = new String[bidslist.size()];
    // ����
    VOConcurrentTool tool = new VOConcurrentTool();
    tool.lock(ArsubBVO.class, bidslist.toArray(bids));

    // ���ַ��õ��ź��ֺ���
    newRedViewVOS.addAll(newViewVOS);
    ArsubViewVO[] resultArsubVO = new ArsubViewVO[newRedViewVOS.size()];
    return newRedViewVOS.toArray(resultArsubVO);
  }
}
