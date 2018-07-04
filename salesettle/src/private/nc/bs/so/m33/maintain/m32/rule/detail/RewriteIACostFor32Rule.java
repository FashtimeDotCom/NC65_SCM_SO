package nc.bs.so.m33.maintain.m32.rule.detail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m33.maintain.m32.UpdateSquare32FieldsBP;
import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.pubitf.so.m32.so.m33.RewritePara32For33;
import nc.pubitf.so.m33.self.m4332.IRewrite434CFor4332;
import nc.pubitf.so.m33.self.m4332.Rewrite434CPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * ȡ���ɱ������ȡ��������ϸ����д�ۼƳɱ���������
 * @scene
 * ȡ���ɱ������ȡ��������ϸ��
 * @param
 * ��
 * @since 6.0
 * @version 2011-6-14 ����08:05:48
 * @author zhangcheng
 */
public class RewriteIACostFor32Rule implements IRule<SquareInvDetailVO> {

  /**
   * ���෽����д
   * ��Ϊ�����ʱ���һ�����ǲ�ѯ���㵥�����Դ�ʱ��д��ʱ����Ҫ���²�ѯ
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#setCustRelaDefValue(E[])
   */
  @Override
  public void process(SquareInvDetailVO[] dvos) {
    try {
      String[] sqbids =
          SoVoTools.getVOsOnlyValues(dvos, SquareInvDetailVO.CSALESQUAREBID);
      Map<String, SquareInvViewVO> map =
          new QuerySquare32VOBP().queryMapSquareInvViewVOByBID(sqbids);

      // ��д���۶�������<30bid,Rewrite33Para>
      Map<String, Rewrite33Para> m30para = new HashMap<String, Rewrite33Para>();
      Rewrite33Para para30 = null;

      // ��д���۷�Ʊ����,���۷�Ʊ�����۷�Ʊ���㵥һһ��Ӧ
      List<RewritePara32For33> list32 = new ArrayList<RewritePara32For33>();
      RewritePara32For33 para32 = null;

      // ��д���۳�����㵥���� <4cbid,Rewrite434CPara>
      Map<String, Rewrite434CPara> m4cpara =
          new HashMap<String, Rewrite434CPara>();
      Rewrite434CPara para4C = null;

      for (SquareInvDetailVO dvo : dvos) {
        String bid = dvo.getCsalesquarebid();
        SquareInvViewVO view = map.get(bid);
        SquareInvBVO bvo = view.getItem();
        UFDouble oldianum = bvo.getNsquareianum();
        UFDouble newianum = dvo.getNsquarenum();

        // ��д���۷�Ʊ�����㵥
        bvo.setNsquareianum(MathTool.add(oldianum, newianum));
        if (MathTool.equals(bvo.getNnum(), bvo.getNsquareianum())) {
          bvo.setBsquareiafinish(UFBoolean.TRUE);
        }
        else {
          bvo.setBsquareiafinish(UFBoolean.FALSE);
        }

        // ��д���۷�Ʊ
        String invbid = dvo.getCsquarebillbid();
        para32 = new RewritePara32For33(invbid, null, null, newianum);
        list32.add(para32);

        // ��д����,���۷�Ʊ�����ڱ༭��ʱ����������Դ�������ۿ���Ҫ���˵�
        String ordbid = bvo.getCfirstbid();
        if (!PubAppTool.isNull(ordbid)) {
          para30 = m30para.get(ordbid);
          if (null == para30) {
            para30 = new Rewrite33Para(ordbid, newianum);
            m30para.put(ordbid, para30);
          }
          else {
            UFDouble new30num = MathTool.add(para30.getNarnum(), newianum);
            para30.setNarnum(new30num);
          }
        }

        // ���۷�Ʊ������Դ�����۳��ⵥ����д���۳�������㵥
        String srctype = bvo.getVsrctype();
        if (ICBillType.SaleOut.getCode().equals(srctype)) {
          String outbid = bvo.getCsrcbid();
          para4C = m4cpara.get(outbid);
          if (null == para4C) {
            para4C = new Rewrite434CPara(outbid, newianum);
            m4cpara.put(outbid, para4C);
          }
          else {
            UFDouble new4cnum = MathTool.add(para4C.getNarnum(), newianum);
            para4C.setNarnum(new4cnum);
          }
        }
      }

      int size = map.values().size();
      SquareInvVO[] sqvos =
          SquareInvVOUtils.getInstance().combineBill(
              map.values().toArray(new SquareInvViewVO[size]));

      // ��д���۷�Ʊ�����㵥�ۼƳɱ���������
      new UpdateSquare32FieldsBP().updateFields(sqvos, null, new String[] {
        SquareInvBVO.NSQUAREIANUM, SquareInvBVO.BSQUAREIAFINISH
      });

      // ��д���۷�Ʊ�ۼƳɱ���������
      SOSaleOrderServicesUtil.reWriteBalNumMny(list32
          .toArray(new RewritePara32For33[list32.size()]));

      // ��д���۶����ۼƳɱ���������
      size = m30para.size();
      Rewrite33Para[] paras = m30para.values().toArray(new Rewrite33Para[size]);
      SOSaleOrderServicesUtil.rewrite30IAFor33(paras);

      // ��д�������۳��ⵥ�����㵥���ۼ�����ȷ��Ӧ��(�ɱ�)����
      size = m4cpara.size();
      if (size > 0) {
        Rewrite434CPara[] paras4C =
            m4cpara.values().toArray(new Rewrite434CPara[size]);
        IRewrite434CFor4332 bo =
            NCLocator.getInstance().lookup(IRewrite434CFor4332.class);
        bo.rewrite434CDownIANumFor4332(paras4C);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }
}
