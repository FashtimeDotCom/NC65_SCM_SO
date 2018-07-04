package nc.bs.so.m33.maintain.m4453.rule.detail;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m33.maintain.m4453.UpdateSquare4453FieldsBP;
import nc.bs.so.m33.maintain.m4453.query.QuerySquare4453VOBP;
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
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * ȷ��������ϸ��ȡ����ȷ��Ӧ�ձ�����д�ۼ�Ӧ�ս�������
 * @scene
 * ȷ��������ϸ��ȡ����ȷ��Ӧ�ձ����
 * @param
 * ��
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-5-28 ����10:55:46
 */
public class RewriteARIncomeFor4453Rule implements IRule<SquareWasDetailVO> {

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
    Rewrite33Para para30 = null;

    // ��д����������<4331bid,RewriteArnumPara>
    Map<String, RewriteArnumPara> m4331para =
        new HashMap<String, RewriteArnumPara>();
    RewriteArnumPara para4331 = null;

    for (SquareWasDetailVO dvo : dvos) {
      String bid = dvo.getCsalesquarebid();
      SquareWasViewVO view = map.get(bid);
      UFDouble oldarnum = view.getItem().getNsquarearnum();
      UFDouble newarnum = dvo.getNsquarenum();
      UFDouble newarmny = dvo.getNorigtaxmny();

      // ��д;������㵥
      view.getItem().setNsquarearnum(MathTool.add(oldarnum, newarnum));
      if (MathTool.equals(view.getItem().getNnum(), view.getItem()
          .getNsquarearnum())) {
        view.getItem().setBsquarearfinish(UFBoolean.TRUE);
      }
      else {
        view.getItem().setBsquarearfinish(UFBoolean.FALSE);
      }

      // ��д������
      if (m4c4331bid.size() > 0) {
        String outbid = view.getItem().getCsrcbid();
        String delbid = m4c4331bid.get(outbid);
        if (!SOVOChecker.isEmpty(delbid)) {
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
    SquareWasVO[] sqvos =
        SquareWasVOUtils.getInstance().combineBill(
            map.values().toArray(new SquareWasViewVO[size]));

    try {
      // ��д���㵥�ۼ�ȷ��Ӧ������
      new UpdateSquare4453FieldsBP().updateFields(sqvos, null, new String[] {
        SquareWasBVO.NSQUAREARNUM, SquareWasBVO.BSQUAREARFINISH
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

}
