package nc.bs.so.m33.maintain.m4c.rule.detail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.so.m33.maintain.m4c.UpdateSquare4CFieldsBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.itf.so.m33.ref.so.m4331.SODeliveryServicesUtil;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.pubitf.so.m4331.so.m33.RewriteArnumPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 *              ������ϸ�����
 *              ��д���㵥�ۼ�ȷ��Ӧ������
 *              ��д���۶����ۼ�ȷ��Ӧ�����������
 * @scene
 *        ���桢ȡ��������ϸ
 * @param ��
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-5-28 ����10:55:46
 */
public class RewriteARIncomeFor4CRule implements IRule<SquareOutDetailVO> {

  @Override
  public void process(SquareOutDetailVO[] dvos) {
    String[] sqbids =
        SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSALESQUAREBID);
    Map<String, SquareOutViewVO> map =
        new QuerySquare4CVOBP().queryMapSquareOutViewVOByBID(sqbids);

    // ��д���۶�������<30bid,Rewrite33Para>
    Map<String, Rewrite33Para> m30para = new HashMap<String, Rewrite33Para>();
    Rewrite33Para para30 = null;

    // ��д����������<4331bid,RewriteArnumPara>
    Map<String, RewriteArnumPara> m4331para =
        new HashMap<String, RewriteArnumPara>();

    // �˻صĺ��ֳ��ⱻ�ݹ�����Ҫ��ȡ��Ӧ���ֳ������Դ ������<��Ӧ�ճ��ⵥ��ԴID,��Ӧ�ճ��ⵥ����Դ���ⵥ��Դ������ID>
    Map<String, String> deliverymap = getOutSrcById(dvos, map);

    RewriteArnumPara para4331 = null;
    for (SquareOutDetailVO dvo : dvos) {
      String bid = dvo.getCsalesquarebid();
      SquareOutViewVO view = map.get(bid);
      UFDouble oldarnum = view.getItem().getNsquarearnum();
      UFDouble newarnum = dvo.getNsquarenum();
      UFDouble oldarmny = view.getItem().getNsquarearmny();
      UFDouble newarmny = dvo.getNorigtaxmny();

      // ��д���۳�������㵥
      view.getItem().setNsquarearnum(MathTool.add(oldarnum, newarnum));
      view.getItem().setNsquarearmny(MathTool.add(oldarmny, newarmny));
      if (MathTool.equals(view.getItem().getNnum(), view.getItem()
          .getNsquarearnum())) {
        view.getItem().setBsquarearfinish(UFBoolean.TRUE);
      }
      else {
        view.getItem().setBsquarearfinish(UFBoolean.FALSE);
      }

      // ��д������
      String vsrctype = view.getItem().getVsrctype();
      if (SOBillType.Delivery.getCode().equals(vsrctype)
          || ICBillType.SaleOut.getCode().equals(vsrctype)) {
        String delbid = view.getItem().getCsrcbid();
        String deliveryid = deliverymap.get(delbid);
        // ��Դ�ڷ��������˻س��ⵥ��Ӧ��
        if (!PubAppTool.isNull(deliveryid)) {
          delbid = deliveryid;
        }
        // ���ֳ��ⲻ��Դ�ڷ������˻����ⵥҲ����Դ�ڷ���������˲���д
        if ((deliverymap.size() > 0 && !PubAppTool.isNull(deliveryid))
            || SOBillType.Delivery.getCode().equals(vsrctype)) {
          para4331 = m4331para.get(delbid);
          if (null == para4331) {
            para4331 = new RewriteArnumPara(delbid, newarnum);
            m4331para.put(delbid, para4331);
          }
          else {
            UFDouble new4331num = MathTool.add(para4331.getArnum(), newarnum);
            para4331.setArnum(new4331num);
          }
        }
      }

      // ��д�����Ĳ���
      String ordbid = view.getItem().getCfirstbid();
      para30 = m30para.get(ordbid);
      if (null == para30) {
        para30 = new Rewrite33Para(ordbid, newarnum, newarmny);
        m30para.put(ordbid, para30);
      }
      else {
        UFDouble new30num = MathTool.add(para30.getNarnum(), newarnum);
        UFDouble new30mny = MathTool.add(para30.getNarmny(), newarmny);
        para30.setNarnum(new30num);
        para30.setNarmny(new30mny);
      }
    }

    int size = map.values().size();
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().combineBill(
            map.values().toArray(new SquareOutViewVO[size]));

    try {
      // ��д���㵥�ۼ�ȷ��Ӧ������
      new UpdateSquare4CFieldsBP().updateFields(sqvos, null, new String[] {
        SquareOutBVO.NSQUAREARNUM, SquareOutBVO.NSQUAREARMNY,
        SquareOutBVO.BSQUAREARFINISH
      });

      // ��д������
      size = m4331para.size();
      if (size > 0) {
        RewriteArnumPara[] paras =
            m4331para.values().toArray(new RewriteArnumPara[size]);
        SODeliveryServicesUtil.rewrite4331Arnum(paras);
      }

      // ��д���۶����ۼ�ȷ��Ӧ�����������
      size = m30para.size();
      Rewrite33Para[] paras = m30para.values().toArray(new Rewrite33Para[size]);
      SOSaleOrderServicesUtil.rewrite30ARFor33(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  private Map<String, String> getOutSrcById(SquareOutDetailVO[] dvos,
      Map<String, SquareOutViewVO> map) {
    Map<String, String> m4331bidmap = new HashMap<String, String>();
    Set<String> saleoutid = new HashSet<String>();
    for (SquareOutDetailVO dvo : dvos) {
      String bid = dvo.getCsalesquarebid();
      SquareOutViewVO view = map.get(bid);
      String vsrctype = view.getItem().getVsrctype();
      if (ICBillType.SaleOut.getCode().equals(vsrctype)) {
        String delbid = view.getItem().getCsrcbid();
        saleoutid.add(delbid);
      }
    }
    if (saleoutid.size() > 0) {
      m4331bidmap =
          new QuerySquare4CVOBP().queryOutSrcidBy4CBID(saleoutid
              .toArray(new String[0]));
    }
    return m4331bidmap;
  }

}
