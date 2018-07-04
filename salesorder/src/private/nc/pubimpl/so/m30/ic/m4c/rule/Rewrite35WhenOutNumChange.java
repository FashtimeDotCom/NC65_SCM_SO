package nc.pubimpl.so.m30.ic.m4c.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m30.maintain.util.SOStateUtil;
import nc.bs.so.m30.rule.rewrite.me.RewriteME35WhenOutClose;
import nc.bs.so.m30.state.row.OutOpenState;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;
import nc.pubitf.so.m35.so.m30.IArsubToSaleorder;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m35.entity.ArsubInterfaceVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.util.ListUtil;
import nc.vo.so.pub.util.SOMathUtil;

/**
 * 
 * @description
 * ���۳����д���۶����ۼƳ���������
 * @scene
 * �ۼƳ�������(�����仯�����������״̬�仯��ʱ���ڴ˴�����)�����仯��ʱ�����ֽ��
 * @param
 * ��
 *
 * @since 6.0
 * @version 2011-9-13 ����09:54:15
 * @author ô��
 */
public class Rewrite35WhenOutNumChange implements IRule<SaleOrderViewVO> {

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {
    Map<String, Rewrite4CPara> mParas =
        (Map<String, Rewrite4CPara>) BSContext.getInstance().getSession(
            Rewrite4CPara.class.getName());

    Map<String, ArsubInterfaceVO> itfvos =
        new HashMap<String, ArsubInterfaceVO>();
    Map<String, OffsetParaVO> offsetparavos =
        new HashMap<String, OffsetParaVO>();

    Map<String, ArsubInterfaceVO> arsubitfvos =
        new HashMap<String, ArsubInterfaceVO>();
    Map<String, OffsetParaVO> arsuboffsetparavos =
        new HashMap<String, OffsetParaVO>();
    Map<String, UFDouble> arsuboffsetmnymap = new HashMap<String, UFDouble>();

    Map<String, UFDouble> offsetmnymap = new HashMap<String, UFDouble>();
    List<SaleOrderViewVO> toMEProcessVO = new ArrayList<>();
    Map<String, MaterialVO> materrialmaps = this.getMaterialMaps(vos);
    OutOpenState openState = new OutOpenState();
    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO hvo = vo.getHead();
      SaleOrderBVO bvo = vo.getBody();
      String bid = bvo.getCsaleorderbid();
      String arsubtypeid = hvo.getCarsubtypeid();
      // û���������
      if(SOStateUtil.isNotOffsetAndlrgcash(vo)){
    	  continue;
      }
      // �����״̬
      if (null == bvo.getBboutendflag()
          || !bvo.getBboutendflag().booleanValue()) {
        continue;
      }
      if(openState.isOutOpen(vo, materrialmaps)){
        continue;
      }
      UFDouble thischangenum =
          mParas.get(bvo.getCsaleorderbid()).getNchangenum();
      
      if (SysInitGroupQuery.isMeEnabled()) {
        toMEProcessVO.add(vo);
        continue;
      }
      ArsubInterfaceVO itfvo = new ArsubInterfaceVO();
      itfvo.setCsalebillid(bvo.getCsaleorderid());
      itfvo.setVbillcode(hvo.getVbillcode());
      itfvo.setVbilltype(SOBillType.Order.getCode());

      if (arsubtypeid != null) {

        arsubitfvos.put(bid, itfvo);

        // ��Ҫ�����ĳ�ֽ��
        UFDouble dismny =
            this.getDismnyForArsub(bvo, hvo.getCorigcurrencyid(), thischangenum);
        arsuboffsetmnymap.put(bid, dismny);
        OffsetParaVO paravo = this.getParavo(vo);
        arsuboffsetparavos.put(bid, paravo);

      }
      else {
        itfvos.put(bid, itfvo);
        // ��Ҫ�����ĳ�ֽ��
        UFDouble dismny =
            this.getDismny(bvo, hvo.getCorigcurrencyid(), thischangenum);
        offsetmnymap.put(bid, dismny);

        OffsetParaVO paravo = this.getParavo(vo);
        offsetparavos.put(bid, paravo);

      }
    }

    if (itfvos.size() != 0) {
      IArsubToSaleorder srv =
          NCLocator.getInstance().lookup(IArsubToSaleorder.class);
      try {
        srv.processOffsetWithOutClose(itfvos, offsetparavos, offsetmnymap);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    if (arsubitfvos.size() != 0) {
      IArsubToSaleorder srv =
          NCLocator.getInstance().lookup(IArsubToSaleorder.class);
      try {
        srv.processOffsetWithOutCloseForArsub(arsubitfvos, arsuboffsetparavos,
            arsuboffsetmnymap);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    if (toMEProcessVO.size() > 0) {
      new RewriteME35WhenOutClose().process(ListUtil.toArray(toMEProcessVO));
    }
  }

  private UFDouble getDismny(SaleOrderBVO bvo, String corigcurrencyid,
      UFDouble thischangenum) {
    UFDouble norigsubmny = bvo.getNorigsubmny();

    ScaleUtils scaleutil = ScaleUtils.getScaleUtilAtBS();
    UFDouble disnmny =
        thischangenum.div(bvo.getNnum()).multiply(norigsubmny);

    return scaleutil.adjustMnyScale(disnmny,
        corigcurrencyid);
  }

  /**
   * ��Ʒ�Ҹ�ʹ��
   * 
   * @param bvo
   * @param corigcurrencyid
   * @param thischangenum
   * @return
   */
  private UFDouble getDismnyForArsub(SaleOrderBVO bvo, String corigcurrencyid,
      UFDouble thischangenum) {
    UFDouble norigtaxmny = bvo.getNorigtaxmny();
    if (SOMathUtil.isZero(bvo.getNtotaloutnum())
        || SOMathUtil.isZero(bvo.getNnum())) {
      return MathTool.oppose(norigtaxmny);
    }
    ScaleUtils scaleutil = ScaleUtils.getScaleUtilAtBS();
    UFDouble disnmny =
        thischangenum.div(bvo.getNnum()).multiply(norigtaxmny);
    return scaleutil.adjustMnyScale(disnmny,
        corigcurrencyid);
  }

  private OffsetParaVO getParavo(SaleOrderViewVO vo) {
    OffsetParaVO paravo = new OffsetParaVO();
    SaleOrderHVO hvo = vo.getHead();
    SaleOrderBVO bvo = vo.getBody();
    paravo.setCorigcurrencyid(hvo.getCorigcurrencyid());
    paravo.setInvoicecusts(hvo.getCinvoicecustid());
    paravo.setOrdercusts(hvo.getCcustomerid());
    paravo.setOrdertrantype(hvo.getCtrantypeid());
    paravo.setProdline(bvo.getCprodlineid());
    paravo.setSaleorg(bvo.getPk_org());
    paravo.setSettleorg(bvo.getCsettleorgid());
    return paravo;
  }
  
  /**
   * ������ϵ����ϵĻ�����Ϣ��������
   */
  private Map<String, MaterialVO> getMaterialMaps(SaleOrderViewVO[] vos) {
    Set<String> materialvid = new HashSet<String>();
    for (SaleOrderViewVO vo : vos) {
      materialvid.add(vo.getBody().getCmaterialvid());
    }
    Map<String, MaterialVO> materrialmaps = null;
    // ������ϻ�����Ϣ
    materrialmaps =
        MaterialPubService.queryMaterialBaseInfo(
            materialvid.toArray(new String[materialvid.size()]), new String[] {
              MaterialVO.OUTCLOSELOWERLIMIT
            });

    return materrialmaps;
  }

}
