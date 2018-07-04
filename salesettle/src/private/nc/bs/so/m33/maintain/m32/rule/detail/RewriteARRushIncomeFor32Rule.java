package nc.bs.so.m33.maintain.m32.rule.detail;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m33.maintain.m32.UpdateSquare32FieldsBP;
import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.itf.so.m33.ref.so.m4331.SODeliveryServicesUtil;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.pubitf.so.m4331.so.m33.RewriteEstarnumPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * �س�Ӧ��ʱ��д�ۼ�Ӧ�ս�������
 * @scene
 * �س�Ӧ�պ�
 * @param
 * ��
 */
public class RewriteARRushIncomeFor32Rule implements IRule<SquareInvDetailVO> {

  @Override
  public void process(SquareInvDetailVO[] dvos) {
    try {
      String[] sqbids =
          SoVoTools.getVOsOnlyValues(dvos, SquareInvDetailVO.CSALESQUAREBID);
      Map<String, SquareInvViewVO> map =
          new QuerySquare32VOBP().queryMapSquareInvViewVOByBID(sqbids);

      // ��ѯ���۷�Ʊ���ε���Դ�ڷ������ĳ��ⵥ
      Map<String, String> m4c4331bid =
          new QuerySquare32VOBP().query4C4331bid(map);

      // ��д���۶�������<30bid,Rewrite33Para>
      Map<String, Rewrite33Para> m30para = new HashMap<String, Rewrite33Para>();
      Rewrite33Para para30 = null;

      // ��д����������<4331bid,RewriteEstarnumPara>
      Map<String, RewriteEstarnumPara> m4331para =
          new HashMap<String, RewriteEstarnumPara>();
      RewriteEstarnumPara para4331 = null;

      for (SquareInvDetailVO dvo : dvos) {
        String bid = dvo.getCsalesquarebid();
        SquareInvViewVO view = map.get(bid);
        SquareInvBVO bvo = view.getItem();
        UFDouble oldarnum = bvo.getNarrushnum();
        UFDouble newarnum = dvo.getNsquarenum();

        // ��д���۷�Ʊ�����㵥
        bvo.setNarrushnum(MathTool.add(oldarnum, newarnum));

        // ��д������
        if (m4c4331bid.size() > 0) {
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

        // ��д����,���۷�Ʊ�����ڱ༭��ʱ����������Դ�������ۿ���Ҫ���˵�
        String ordbid = bvo.getCfirstbid();
        if (!PubAppTool.isNull(ordbid)) {
          para30 = m30para.get(ordbid);
          if (null == para30) {
            para30 = new Rewrite33Para(ordbid, newarnum);
            m30para.put(ordbid, para30);
          }
          else {
            UFDouble new30num = MathTool.add(para30.getNarnum(), newarnum);
            para30.setNarnum(new30num);
          }
        }
      }

      int size = map.values().size();
      SquareInvVO[] sqvos =
          SquareInvVOUtils.getInstance().combineBill(
              map.values().toArray(new SquareInvViewVO[size]));

      // ��д���۷�Ʊ�����㵥�ۼƻس���Ӧ������
      new UpdateSquare32FieldsBP().updateFields(sqvos, null, new String[] {
        SquareInvBVO.NARRUSHNUM
      });

      // ��д������
      size = m4331para.size();
      if (size > 0) {
        RewriteEstarnumPara[] paras =
            m4331para.values().toArray(new RewriteEstarnumPara[size]);
        SODeliveryServicesUtil.rewrite4331Estarnum(paras);
      }

      // ��д���۶����ۼ��ݹ�Ӧ������(ע�����۶������ۼ��ݹ�����=�ۼ��ݹ�+�ۼƻس壬���Ǹ������õ�)
      size = m30para.size();
      Rewrite33Para[] paras = m30para.values().toArray(new Rewrite33Para[size]);
      SOSaleOrderServicesUtil.rewrite30ETFor33(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
