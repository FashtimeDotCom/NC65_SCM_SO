package nc.bs.so.m33.maintain.m4c.rule.detail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.so.m33.maintain.m4c.UpdateSquare4CFieldsBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.itf.so.m33.ref.so.m4331.SODeliveryServicesUtil;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.pubitf.so.m4331.so.m33.RewriteEstarnumPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 *              ���۳��ⵥ���㵥��ϸ
 *              ��д���㵥�ۼ�ȷ��Ӧ������
 *              ��д���۶����ۼ�ȷ��Ӧ�����������
 * @scene
 *        ȡ��������ϸ���ݹ�Ӧ����ϸ����
 * @param ��
 */
public class RewriteETIncomeFor4CRule implements IRule<SquareOutDetailVO> {

  @Override
  public void process(SquareOutDetailVO[] dvos) {

    String[] sqbids =
        SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSALESQUAREBID);
    Map<String, SquareOutViewVO> map =
        new QuerySquare4CVOBP().queryMapSquareOutViewVOByBID(sqbids);

    // ��д���۶�������<30bid,Rewrite33Para>
    Map<String, Rewrite33Para> m30para = new HashMap<String, Rewrite33Para>();
    Rewrite33Para para30 = null;

    // ��д����������<4331bid,RewriteEstarnumPara>
    Map<String, RewriteEstarnumPara> m4331para =
        new HashMap<String, RewriteEstarnumPara>();

    // �˻صĺ��ֳ��ⱻ�ݹ�����Ҫ��ȡ��Ӧ���ֳ������Դ ������<�ݹ����ⵥ��ԴID,�ݹ����ⵥ����Դ���ⵥ��Դ������ID>
    Map<String, String> deliverymap = getOutSrcById(dvos, map);
    RewriteEstarnumPara para4331 = null;

    for (SquareOutDetailVO dvo : dvos) {
      String bid = dvo.getCsalesquarebid();
      SquareOutViewVO view = map.get(bid);
      UFDouble oldarnum = view.getItem().getNsquareestnum();
      UFDouble newarnum = dvo.getNsquarenum();
      UFDouble newarmny = dvo.getNorigtaxmny();

      // ��д���۳�������㵥
      view.getItem().setNsquareestnum(MathTool.add(oldarnum, newarnum));
      if (MathTool.equals(view.getItem().getNnum(), view.getItem()
          .getNsquareestnum())) {
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
        // �˻س��ⵥ���ݹ�
        if (!PubAppTool.isNull(deliveryid)) {
          delbid = deliveryid;
        }
        // ���ֳ��ⲻ��Դ�ڷ������˻����ⵥҲ����Դ�ڷ���������˲���д
        if ((deliverymap.size() > 0 && !PubAppTool.isNull(deliveryid))
            || SOBillType.Delivery.getCode().equals(vsrctype)) {
          para4331 = m4331para.get(delbid);
          if (null == para4331) {
            para4331 = new RewriteEstarnumPara(delbid, newarnum);
            m4331para.put(delbid, para4331);
          }
          else {
            UFDouble new4331num =
                MathTool.add(para4331.getEstarnum(), newarnum);
            para4331.setEstarnum(new4331num);
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
      // ��д���㵥�ۼ��ݹ�Ӧ������
      new UpdateSquare4CFieldsBP().updateFields(sqvos, null, new String[] {
        SquareOutBVO.NSQUAREESTNUM, SquareOutBVO.BSQUAREARFINISH
      });

      // ��д������
      size = m4331para.size();
      if (size > 0) {
        RewriteEstarnumPara[] paras =
            m4331para.values().toArray(new RewriteEstarnumPara[size]);
        SODeliveryServicesUtil.rewrite4331Estarnum(paras);
      }

      // ��д���۶����ۼ��ݹ�Ӧ�����������
      size = m30para.size();
      Rewrite33Para[] paras = m30para.values().toArray(new Rewrite33Para[size]);
      SOSaleOrderServicesUtil.rewrite30ETFor33(paras);
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
          new QuerySquare4CVOBP().query4C4331bid(saleoutid
              .toArray(new String[0]));
    }
    return m4331bidmap;
  }

}
