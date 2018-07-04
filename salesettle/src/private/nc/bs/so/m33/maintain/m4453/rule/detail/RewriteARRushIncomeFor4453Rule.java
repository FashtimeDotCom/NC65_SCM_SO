package nc.bs.so.m33.maintain.m4453.rule.detail;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m33.maintain.m4453.UpdateSquare4453FieldsBP;
import nc.bs.so.m33.maintain.m4453.query.QuerySquare4453VOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.itf.so.m33.ref.so.m4331.SODeliveryServicesUtil;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.pubitf.so.m4331.so.m33.RewriteEstarnumPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * �س�Ӧ����ϸ��ȡ����ȷ��Ӧ�ձ�����д�ۼ�Ӧ�ս�������
 * @scene
 * �س�Ӧ����ϸ��ȡ����ȷ��Ӧ�ձ����
 * @param
 * ��
 */
public class RewriteARRushIncomeFor4453Rule implements IRule<SquareWasDetailVO> {

  @Override
  public void process(SquareWasDetailVO[] dvos) {
    String[] sqbids =
        SoVoTools.getVOsOnlyValues(dvos, SquareWasDetailVO.CSALESQUAREBID);
    Map<String, SquareWasViewVO> map =
        new QuerySquare4453VOBP().queryMapSquareWasViewVOByBID(sqbids);

    // ��ѯ;�����ε���Դ�ڷ������ĳ��ⵥ
    Map<String, String> m4c4331bid =
        new QuerySquare4453VOBP().query4C4331bid(map);

    // ��д���۶�������<30bid,Rewrite33Para>
    Map<String, Rewrite33Para> m30para = new HashMap<String, Rewrite33Para>();
    Rewrite33Para para = null;

    // ��д����������<4331bid,RewriteEstarnumPara>
    Map<String, RewriteEstarnumPara> m4331para =
        new HashMap<String, RewriteEstarnumPara>();
    RewriteEstarnumPara para4331 = null;

    for (SquareWasDetailVO dvo : dvos) {
      String bid = dvo.getCsalesquarebid();
      SquareWasViewVO view = map.get(bid);
      UFDouble oldvalue = view.getItem().getNarrushnum();
      UFDouble newvalue = dvo.getNsquarenum();

      view.getItem().setNarrushnum(MathTool.add(oldvalue, newvalue));

      // ��д������
      if (m4c4331bid.size() > 0) {
        String outbid = view.getItem().getCsrcbid();
        String delbid = m4c4331bid.get(outbid);
        if (!SOVOChecker.isEmpty(delbid)) {
          para4331 = m4331para.get(delbid);
          if (null == para4331) {
            para4331 = new RewriteEstarnumPara(delbid, newvalue);
            m4331para.put(delbid, para4331);
          }
          else {
            UFDouble new4331num =
                MathTool.add(para4331.getEstarnum(), newvalue);
            para4331.setEstarnum(new4331num);
          }
        }
      }

      // ��д�����Ĳ���
      String ordbid = view.getItem().getCfirstbid();
      para = m30para.get(ordbid);
      if (null == para) {
        para = new Rewrite33Para(ordbid, newvalue);
        m30para.put(ordbid, para);
      }
      else {
        newvalue = MathTool.add(para.getNarnum(), newvalue);
        para.setNarnum(newvalue);
      }
    }

    // ��д���㵥�ۼƻس�Ӧ������
    int size = map.values().size();
    SquareWasVO[] sqvos =
        SquareWasVOUtils.getInstance().combineBill(
            map.values().toArray(new SquareWasViewVO[size]));
    new UpdateSquare4453FieldsBP().updateFields(sqvos, null, new String[] {
      SquareWasBVO.NARRUSHNUM
    });

    // ��д������
    size = m4331para.size();
    if (size > 0) {
      RewriteEstarnumPara[] paras =
          m4331para.values().toArray(new RewriteEstarnumPara[size]);
      SODeliveryServicesUtil.rewrite4331Estarnum(paras);
    }

    // ��д���۶����ۼ��ݹ�Ӧ������(ע�����۶������ۼ��ݹ�����=�ۼ��ݹ�+�ۼƻس壬���Ǹ������õ�)
    try {
      size = m30para.values().size();
      SOSaleOrderServicesUtil.rewrite30ETFor33(m30para.values().toArray(
          new Rewrite33Para[size]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

}
