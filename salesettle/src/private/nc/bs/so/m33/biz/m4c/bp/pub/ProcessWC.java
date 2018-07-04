package nc.bs.so.m33.biz.m4c.bp.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBizBP;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.util.SquareCalculatorForVO;
import nc.vo.so.pub.calculator.PriceNumMnyCalculatorForVO;
import nc.vo.so.pub.util.SOCurrencyUtil;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

/**
 * ���۳�������㵥
 * ���ݵ�ǰ�����������¼����������۽��
 * ������β���
 * 
 * @since 6.0
 * @version 2011-6-20 ����04:30:27
 * @author zhangcheng
 */
public class ProcessWC {

  /**
   * ����β��
   */
  public void processMnyForWC(SquareOutViewVO[] retsvos) {
    SquareOutBVO[] bvos =
        SquareOutVOUtils.getInstance().getSquareOutBVO(retsvos);
    String[] bids =
        SoVoTools.getVOsOnlyValues(bvos, SquareOutBVO.CSALESQUAREBID);
    // ��ѯԭʼ�����۳�������㵥<bid,SquareOutViewVO>
    Map<String, SquareOutViewVO> moldview = this.getOldSquareOutViewVO(bids);

    // �ֹ�����ɱ�������ϸ��vo
    SquareOutDetailVO[] sdvos =
        new QuerySquare4CVOBizBP().queryManualDetailVOBySQBIDandSQType(bids,
            new SquareType[] {
            SquareType.SQUARETYPE_AR, SquareType.SQUARETYPE_IA
        });

    // ��ȡ���е���ʷ�۸�
    Map<String, UFDouble> mapsprice = new HashMap<String, UFDouble>();
    Set<String> setchgbid = new HashSet<String>();
    for (SquareOutDetailVO dvo : sdvos) {
      String squarebid = dvo.getCsalesquarebid();
      // �Ѿ������۸�ı�
      if (setchgbid.contains(squarebid)) {
        continue;
      }
      UFDouble taxnetprice = dvo.getNorigtaxnetprice();
      // δ�����˼۸���Ҫ���õ�MAP��
      if (mapsprice.containsKey(squarebid)) {
        UFDouble oldtaxnetprice = mapsprice.get(squarebid);
        if (!MathTool.equals(oldtaxnetprice, taxnetprice)) {
          setchgbid.add(squarebid);
        }
      }
      else {
        mapsprice.put(squarebid, taxnetprice);
      }
    }
    // �Ѿ�����������۳�����㵥�ۼ���Ϣ<bid,dvo(�ۼ���Ϣ���������)>
    Map<String, SquareOutDetailVO> msquaredvo =
        this.getManualSquareDetailVO(sdvos);

    // β����۸�䶯���ٴ���
    for (SquareOutViewVO view : retsvos) {
      String squarebid = view.getItem().getCsalesquarebid();
      if (setchgbid.contains(squarebid)) {
        continue;
      }

      UFDouble newtaxnetprice = view.getItem().getNorigtaxnetprice();
      // ԭʼ�����۳�������㵥
      SquareOutViewVO oview = moldview.get(view.getItem().getCsalesquarebid());
      // spriceֻ����1����¼��������¼˵����θļۣ���������Ѿ�����
      UFDouble oldtaxnetprice = mapsprice.get(squarebid);
      // �۸���ͬ��β���,��Ϊ�����޶���ǩ�ա�����ļ�������Ҫ�Ƚ���ʷ��ϸ�۸�
      if (MathTool.equals(newtaxnetprice, oldtaxnetprice)) {
        // �ѽ������۳�����㵥���ۼ���Ϣ��
        SquareOutDetailVO sqedview =
            msquaredvo.get(view.getItem().getCsalesquarebid());
        // ԭ����Ϣ
        UFDouble totaltaxmny =
            MathTool.sub(oview.getItem().getNorigtaxmny(),
                sqedview.getNorigtaxmny());
        view.getItem().setNorigtaxmny(totaltaxmny);
        UFDouble totalmny =
            MathTool.sub(oview.getItem().getNorigmny(), sqedview.getNorigmny());
        view.getItem().setNorigmny(totalmny);
        // TODO 2012.02.16 fbinly v61ɾ��ԭ��˰���ֶ�
        // UFDouble totaltax =
        // MathTool.sub(oview.getItem().getNorigtax(), sqedview.getNorigtax());
        // view.getItem().setNorigtax(totaltax);

        String corgcurcy = view.getItem().getCorigcurrencyid();
        String ccurcy = view.getItem().getCcurrencyid();
        // ���ԭ�ұ��ұ��ֲ�һ�£��򱾱Ҳ���β��
        if (PubAppTool.isEqual(corgcurcy, ccurcy)) {
          // ������Ϣ
          UFDouble totaltaxmnylocal =
              MathTool.sub(oview.getItem().getNtaxmny(), sqedview.getNtaxmny());
          view.getItem().setNtaxmny(totaltaxmnylocal);
          UFDouble totalmnylocal =
              MathTool.sub(oview.getItem().getNmny(), sqedview.getNmny());
          view.getItem().setNmny(totalmnylocal);
          UFDouble totaltaxlocal =
              MathTool.sub(oview.getItem().getNtax(), sqedview.getNtax());
          view.getItem().setNtax(totaltaxlocal);
        }

        // ���š�ȫ����Ϣ
        UFDouble ngrouptaxmny =
            MathTool.sub(oview.getItem().getNgrouptaxmny(),
                sqedview.getNgrouptaxmny());
        view.getItem().setNgrouptaxmny(ngrouptaxmny);
        UFDouble ngroupmny =
            MathTool.sub(oview.getItem().getNgroupmny(),
                sqedview.getNgroupmny());
        view.getItem().setNgrouptaxmny(ngroupmny);
        UFDouble nglobaltaxmny =
            MathTool.sub(oview.getItem().getNglobaltaxmny(),
                sqedview.getNglobaltaxmny());
        view.getItem().setNglobaltaxmny(nglobaltaxmny);
        UFDouble nglobalmny =
            MathTool.sub(oview.getItem().getNglobalmny(),
                sqedview.getNglobalmny());
        view.getItem().setNglobaltaxmny(nglobalmny);

      }
    }

  }

  /**
   * ���ν�����������������������Ҫ���¼�����
   * 
   * @param svos
   */
  public void reCalNumMny(SquareOutViewVO[] svos) {
    this.setNewExchangeRate(svos);
    // ��Ҫ���¼������vo
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO svo : svos) {
      UFDouble nthisnum = svo.getItem().getNthisnum();
      UFDouble nnum = svo.getItem().getNnum();
      if (!MathTool.equals(nthisnum, nnum)) {
        list.add(svo);
      }
    }
    // ���ν�����������������������Ҫ���¼�����
    if (list.size() > 0) {
      SquareOutViewVO[] views = list.toArray(new SquareOutViewVO[list.size()]);
      SquareOutBVO[] bvos =
          SquareOutVOUtils.getInstance().getSquareOutBVO(views);
      new SquareCalculatorForVO().calculate(bvos, SquareOutBVO.NTHISNUM);
    }

  }

  /**
   * ��ѯ���ֹ��������ݵ�ʱ����β��
   * 
   * @param svos
   */
  public void reCalNumMnyAndWCForManualSquareQuery(SquareOutViewVO[] svos) {

    // ��Ҫ���¼������vo
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO svo : svos) {
      UFDouble nthisnum = svo.getItem().getNthisnum();
      UFDouble nnum = svo.getItem().getNnum();
      if (!MathTool.equals(nthisnum, nnum)) {
        list.add(svo);
      }
    }
    // ���ν�����������������������Ҫ���¼�����
    if (list.size() > 0) {
      SquareOutViewVO[] views = list.toArray(new SquareOutViewVO[list.size()]);
      SquareOutBVO[] bvos =
          SquareOutVOUtils.getInstance().getSquareOutBVO(views);
      new SquareCalculatorForVO().calculate(bvos, SquareOutBVO.NTHISNUM);
      this.processMnyForWC(views);
    }

  }

  /**
   * ��ȡ�Ѿ���������
   * 
   * @return <���۳�������㵥bid,SquareOutDetailVO>
   *         SquareOutDetailVO�д�����ۼ����������ۼ�ԭ�Ҽ�˰�ϼơ���˰��˰��
   * @param sdvos --- ���۳�����㵥
   */
  private Map<String, SquareOutDetailVO> getManualSquareDetailVO(
      SquareOutDetailVO[] sdvos) {
    // <���۳�������㵥bid,SquareOutDetailVO>
    // SquareOutDetailVO�д�����ۼ����������ۼ�ԭ�Ҽ�˰�ϼơ���˰��˰��
    Map<String, SquareOutDetailVO> msquaredvo =
        new HashMap<String, SquareOutDetailVO>();
    for (SquareOutDetailVO dvo : sdvos) {
      SquareOutDetailVO tempdvo = msquaredvo.get(dvo.getCsalesquarebid());
      if (VOChecker.isEmpty(tempdvo)) {
        msquaredvo.put(dvo.getCsalesquarebid(), dvo);
      }
      // ����������ͬ�Ĳ��ۼӽ�����Ϣ
      else if (tempdvo.getFsquaretype().intValue() == dvo.getFsquaretype()
          .intValue()) {
        // ����
        UFDouble totalnum =
            MathTool.add(tempdvo.getNsquarenum(), dvo.getNsquarenum());
        tempdvo.setNsquarenum(totalnum);
        // ��˰�ϼ�
        UFDouble totaltaxmny =
            MathTool.add(tempdvo.getNorigtaxmny(), dvo.getNorigtaxmny());
        UFDouble totaltaxmnylocal =
            MathTool.add(tempdvo.getNtaxmny(), dvo.getNtaxmny());
        tempdvo.setNorigtaxmny(totaltaxmny);
        tempdvo.setNtaxmny(totaltaxmnylocal);
        // ��˰���
        UFDouble totalmny =
            MathTool.add(tempdvo.getNorigmny(), dvo.getNorigmny());
        UFDouble totalmnylocal = MathTool.add(tempdvo.getNmny(), dvo.getNmny());
        tempdvo.setNorigmny(totalmny);
        tempdvo.setNmny(totalmnylocal);
        // TODO 2012.02.16 fbinly v61ɾ��ԭ��˰���ֶ�
        // // ˰��
        // UFDouble totaltax =
        // MathTool.add(tempdvo.getNorigtax(), dvo.getNorigtax());
        // tempdvo.setNorigtax(totaltax);
        UFDouble totaltaxlocal = MathTool.add(tempdvo.getNtax(), dvo.getNtax());
        tempdvo.setNtax(totaltaxlocal);
        // 2012.02.16 fbinly v61�����ֶ�
        UFDouble totalcaltaxmny =
            MathTool.add(tempdvo.getNcaltaxmny(), dvo.getNcaltaxmny());
        tempdvo.setNcaltaxmny(totalcaltaxmny);
        // ���Ž��
        UFDouble totalgrouptaxmny =
            MathTool.add(tempdvo.getNgrouptaxmny(), dvo.getNgrouptaxmny());
        UFDouble totalgroupmny =
            MathTool.add(tempdvo.getNgroupmny(), dvo.getNgroupmny());
        tempdvo.setNgrouptaxmny(totalgrouptaxmny);
        tempdvo.setNgroupmny(totalgroupmny);
        // ȫ�ֽ��
        UFDouble totalglobaltaxmny =
            MathTool.add(tempdvo.getNglobaltaxmny(), dvo.getNglobaltaxmny());
        UFDouble totalglobalmny =
            MathTool.add(tempdvo.getNglobalmny(), dvo.getNglobalmny());
        tempdvo.setNglobaltaxmny(totalglobaltaxmny);
        tempdvo.setNglobalmny(totalglobalmny);
      }
    }
    return msquaredvo;
  }

  /**
   * ��ѯԭʼ�����۳�������㵥
   */
  private Map<String, SquareOutViewVO> getOldSquareOutViewVO(String[] bids) {
    SquareOutViewVO[] oldview =
        new QuerySquare4CVOBP().querySquareOutViewVOByBID(bids);
    // <bid,SquareOutViewVO>
    Map<String, SquareOutViewVO> moldview =
        new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO view : oldview) {
      moldview.put(view.getItem().getCsalesquarebid(), view);
    }
    return moldview;
  }

  private void setNewExchangeRate(SquareOutViewVO[] svos) {
    Map<String, UFDouble> mapexrate = new HashMap<String, UFDouble>();
    for (SquareOutViewVO svo : svos) {
      SquareOutBVO bvo = svo.getItem();
      String corigcurrencyid = bvo.getCorigcurrencyid();
      String ccurrencyorgid = bvo.getCcurrencyid();
      String csettleorgid = bvo.getPk_org();
      UFDouble exchangerate = bvo.getNexchangerate();
      if (!PubAppTool.isNull(corigcurrencyid)
          && !PubAppTool.isNull(ccurrencyorgid)
          && !PubAppTool.isNull(csettleorgid)) {
        String key = csettleorgid + ccurrencyorgid;
        if (mapexrate.containsKey(key)) {
          exchangerate = mapexrate.get(key);
        }
        else {
          exchangerate =
              SOCurrencyUtil.getInCurrencyRateByOrg(csettleorgid,
                  corigcurrencyid, ccurrencyorgid, AppContext.getInstance()
                  .getBusiDate());
        }
        bvo.setNexchangerate(exchangerate);
      }
    }

    SquareOutBVO[] bvos = SquareOutVOUtils.getInstance().getSquareOutBVO(svos);
    new PriceNumMnyCalculatorForVO().calculateLocal(bvos);
  }

}
