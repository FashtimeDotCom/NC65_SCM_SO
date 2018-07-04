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
import nc.itf.so.m33.ref.so.m4331.SODeliveryServicesUtil;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.pubitf.so.m32.so.m33.RewritePara32For33;
import nc.pubitf.so.m33.self.m4332.IRewrite434CFor4332;
import nc.pubitf.so.m33.self.m4332.Rewrite434CPara;
import nc.pubitf.so.m4331.so.m33.RewriteArnumPara;
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
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * 
 * <ul>
 * <li>��д���㵥�ۼ�ȷ��Ӧ������
 * <li>��д���۶����ۼ�ȷ��Ӧ�����������
 * </ul>
 * @scene
 * ȡ��������ϸ��д�ۼ�Ӧ�ս�������
 * @param
 * ��
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhangcheng
 * @time 2010-5-28 ����10:55:46
 */
public class RewriteARIncomeFor32Rule implements IRule<SquareInvDetailVO> {

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

      // ��д����������<4331bid,RewriteArnumPara>
      Map<String, RewriteArnumPara> m4331para =
          new HashMap<String, RewriteArnumPara>();
      RewriteArnumPara para4331 = null;

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
        UFDouble oldarnum = bvo.getNsquarearnum();
        UFDouble newarnum = dvo.getNsquarenum();
        UFDouble newarmny = dvo.getNorigtaxmny();

        // ��д���۷�Ʊ�����㵥
        bvo.setNsquarearnum(MathTool.add(oldarnum, newarnum));
        if (MathTool.equals(bvo.getNnum(), bvo.getNsquarearnum())) {
          bvo.setBsquarearfinish(UFBoolean.TRUE);
        }
        else {
          bvo.setBsquarearfinish(UFBoolean.FALSE);
        }

        // ��д���۷�Ʊ
        String invbid = dvo.getCsquarebillbid();
        para32 = new RewritePara32For33(invbid, newarnum, newarmny, null);
        list32.add(para32);

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

        // ��д����,���۷�Ʊ�����ڱ༭��ʱ����������Դ�������ۿ���Ҫ���˵�
        // �¼��ж��ۿ����־����������ۿ����ٽ��л�д���� 
          String ordbid = bvo.getCfirstbid();
          if (!PubAppTool.isNull(ordbid)) {
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

          // ���۷�Ʊ������Դ�����۳��ⵥ����д���۳�������㵥
          String srctype = bvo.getVsrctype();
          if (ICBillType.SaleOut.getCode().equals(srctype)) {
            String outbid = bvo.getCsrcbid();
            para4C = m4cpara.get(outbid);
            if (null == para4C) {
              para4C = new Rewrite434CPara(outbid, newarnum);
              para4C.setNarmny(newarmny);
              m4cpara.put(outbid, para4C);
            }
            else {
              UFDouble new4cnum = MathTool.add(para4C.getNarnum(), newarnum);
              UFDouble new4cmny = MathTool.add(para4C.getNarmny(), newarmny);
              para4C.setNarnum(new4cnum);
              para4C.setNarmny(new4cmny);
            }
          }
        }
       
      int size = map.values().size();
      SquareInvVO[] sqvos =
          SquareInvVOUtils.getInstance().combineBill(
              map.values().toArray(new SquareInvViewVO[size]));

      // ��д���۷�Ʊ�����㵥�ۼ�ȷ��Ӧ������
      new UpdateSquare32FieldsBP().updateFields(sqvos, null, new String[] {
        SquareInvBVO.NSQUAREARNUM, SquareInvBVO.BSQUAREARFINISH
      });

      // ��д���۷�Ʊ�ۼ�ȷ��Ӧ�����������
      SOSaleOrderServicesUtil.reWriteBalNumMny(list32
          .toArray(new RewritePara32For33[list32.size()]));

      // ��д������
      size = m4331para.size();
      if (size > 0) {
        RewriteArnumPara[] paras =
            m4331para.values().toArray(new RewriteArnumPara[size]);
        SODeliveryServicesUtil.rewrite4331Arnum(paras);
      }

      // ��д���۶����ۼ�ȷ��Ӧ�����������
      size = m30para.size();
      // ��sizeΪ0 �����л�д
      if(size>0){
        Rewrite33Para[] paras = m30para.values().toArray(new Rewrite33Para[size]);
        SOSaleOrderServicesUtil.rewrite30ARFor33(paras);
      }

      // ��д�������۳��ⵥ�����㵥���ۼ�����ȷ��Ӧ��(�ɱ�)����
      size = m4cpara.size();
      if (size > 0) {
        Rewrite434CPara[] paras4C =
            m4cpara.values().toArray(new Rewrite434CPara[size]);
        IRewrite434CFor4332 bo =
            NCLocator.getInstance().lookup(IRewrite434CFor4332.class);
        bo.rewrite434CDownARNumMnyFor4332(paras4C);
      }

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
