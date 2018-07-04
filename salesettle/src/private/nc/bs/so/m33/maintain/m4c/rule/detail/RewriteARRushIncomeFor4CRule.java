package nc.bs.so.m33.maintain.m4c.rule.detail;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m33.maintain.m4c.UpdateSquare4CFieldsBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.itf.so.m33.ref.so.m4331.SODeliveryServicesUtil;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.pubitf.so.m4331.so.m33.RewriteEstarnumPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * ���۳��ⵥȡ��������ϸ��д�ۼ�Ӧ�ս�������
 * @scene
 * ȡ��������ϸ��ȡ���س�Ӧ�յ� ������Գ崫�س�Ӧ��
 * @param 
 * ��
 */
public class RewriteARRushIncomeFor4CRule implements IRule<SquareOutDetailVO> {

  @Override
  public void process(SquareOutDetailVO[] dvos) {
    String[] sqbids =
        SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSALESQUAREBID);
    Map<String, SquareOutViewVO> map =
        new QuerySquare4CVOBP().queryMapSquareOutViewVOByBID(sqbids);

    // ��д���۶�������<30bid,Rewrite33Para>
    Map<String, Rewrite33Para> m30para = new HashMap<String, Rewrite33Para>();
    Rewrite33Para para30 = null;

    // ��ѯǩ���˻ص����۳��ⵥ���ε���Դ�ڷ��������������۳��ⵥ<���ֳ���bid,4331bid>
    Map<String, String> m4c4331bid =
        new QuerySquare4CVOBP().query4C4331bid(map);

    // ��д����������<4331bid,RewriteEstarnumPara>
    Map<String, RewriteEstarnumPara> m4331para =
        new HashMap<String, RewriteEstarnumPara>();
    RewriteEstarnumPara para4331 = null;

    for (SquareOutDetailVO dvo : dvos) {
      String bid = dvo.getCsalesquarebid();
      SquareOutViewVO view = map.get(bid);
      UFDouble oldvalue = view.getItem().getNarrushnum();
      UFDouble newarnum = dvo.getNsquarenum();

      view.getItem().setNarrushnum(MathTool.add(oldvalue, newarnum));
      // ��д�����Ĳ���
      String ordbid = view.getItem().getCfirstbid();
      para30 = m30para.get(ordbid);
      if (null == para30) {
        para30 = new Rewrite33Para(ordbid, newarnum);
        m30para.put(ordbid, para30);
      }
      else {
        newarnum = MathTool.add(para30.getNarnum(), newarnum);
        para30.setNarnum(newarnum);
      }

      // ��д������-�������۳��ⵥ�����Ƿ�����
      String vsrctype = view.getItem().getVsrctype();
      if (SOBillType.Delivery.getCode().equals(vsrctype)) {
        String delbid = view.getItem().getCsrcbid();
        para4331 = m4331para.get(delbid);
        if (null == para4331) {
          para4331 = new RewriteEstarnumPara(delbid, newarnum);
          m4331para.put(delbid, para4331);
        }
        else {
          UFDouble new4331num = MathTool.add(para4331.getEstarnum(), newarnum);
          para4331.setEstarnum(new4331num);
        }
      }
      // ǩ���˻صĳ��ⵥ�����������۳��ⵥ�������Ƿ�����
      else if (m4c4331bid.size() > 0) {
        String outbid = view.getItem().getCsrcbid();
        String delbid = m4c4331bid.get(outbid);
        if (!SOVOChecker.isEmpty(delbid)) {
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

    }

    // ��д���㵥�ۼƻس�Ӧ������
    int size = map.values().size();
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().combineBill(
            map.values().toArray(new SquareOutViewVO[size]));
    new UpdateSquare4CFieldsBP().updateFields(sqvos, null, new String[] {
      SquareOutBVO.NARRUSHNUM
    });

    try {
      // ��д�������ۼ��ݹ�Ӧ������(ע�����������ۼ��ݹ�����=�ۼ��ݹ�+�ۼƻس壬���Ǹ������õ�)
      size = m4331para.size();
      if (size > 0) {
        RewriteEstarnumPara[] paras =
            m4331para.values().toArray(new RewriteEstarnumPara[size]);
        SODeliveryServicesUtil.rewrite4331Estarnum(paras);
      }

      // ��д���۶����ۼ��ݹ�Ӧ������(ע�����۶������ۼ��ݹ�����=�ۼ��ݹ�+�ۼƻس壬���Ǹ������õ�)
      size = m30para.values().size();
      SOSaleOrderServicesUtil.rewrite30ETFor33(m30para.values().toArray(
          new Rewrite33Para[size]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

}
