package nc.impl.so.outprofit.processer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.parameter.AskCostPriceParaVO;
import nc.vo.so.report.outprofit.OutProfitViewVO;

import nc.itf.so.report.RemoteCallService;

/**
 * ������Ӧ����Ϣ
 * 
 * @since 6.3
 * @version 2012-09-09 16:07:37
 * @author ������
 */
public class OutProfitCalculateProcesser {

  /**
   * ������������ë����Ϣ
   * 
   * @param views
   */
  public void calculateProfit(OutProfitViewVO[] views) {
    // ��ȡ�ɱ�����ɱ����ɱ����ɱ�����
    Map<String, UFDouble> outpricrmap = this.getOutpriceMap(views);
    for (OutProfitViewVO view : views) {

      // 1.����������
      this.setMainnum(view);

      // 2.������˰������˰����
      this.setNnotaxmny(view);
      // 3.���óɱ�����ɱ����ɱ����ɱ�����
      this.setNtotalcostmny(view, outpricrmap);

      // 4.����ë����ë����
      view.setNprofitmny(MathTool.sub(view.getNnotaxmny(),
          view.getNtotalcostmny()));
      // view.setNprofitrate(view.getNprofitmny().div(view.getNnotaxmny()));
    }

  }

  private Map<String, UFDouble> getOutpriceMap(OutProfitViewVO[] views) {
    List<AskCostPriceParaVO> costpricelist =
        new ArrayList<AskCostPriceParaVO>();
    for (OutProfitViewVO view : views) {
      AskCostPriceParaVO acpp = new AskCostPriceParaVO();
      // dongli2 2013-08-16 �˴��޸�Ϊ����OID����Ϊ������㲻��עVID
      acpp.setCinventoryid(view.getCmaterialoid());
      acpp.setPk_financeorg(view.getCfanaceorgoid());
      acpp.setPk_org(view.getPk_org());
      acpp.setPk_stordoc(view.getCbodywarehouseid());
      acpp.setPk_stockorg(view.getPk_org());
      acpp.setVbatch(view.getVbatchcode());
      costpricelist.add(acpp);
    }
    Map<String, UFDouble> outpricemap =
        RemoteCallService.getPriceBySCM02(costpricelist
            .toArray(new AskCostPriceParaVO[costpricelist.size()]));
    return outpricemap;
  }

  private void setNtotalcostmny(OutProfitViewVO view,
      Map<String, UFDouble> outpricrmap) {
    String key = this.getKey(view);
    UFDouble outprice = outpricrmap.get(key);
    // �ɱ�����ɱ����
    UFDouble nocostnum = view.getNocostnum();
    UFDouble nocostmny = UFDouble.ZERO_DBL;
    if (null != outprice && null != nocostnum) {
      nocostmny = nocostnum.multiply(outprice);
    }
    UFDouble ncostmuy = MathTool.add(view.getNcost(), nocostmny);
    view.setNcostmny(ncostmuy);

    // �ɱ����=�������ɱ���� + ��������-�����������������* ����ģ��ȡ�ɱ����򣺽�浥��or�ο��ɱ�or�ƻ��ۣ�
    UFDouble mny = UFDouble.ZERO_DBL;
    if (null != outprice) {
      mny =
          MathTool.sub(view.getNmainnum(), view.getNcostnum()).multiply(
              outprice);
    }

    view.setNtotalcostmny(MathTool.add(view.getNcostmny(), mny));
    // �ɱ�����
    // view.setNcostprice(view.getNtotalcostmny().div(view.getNmainnum()));
  }

  private String getKey(OutProfitViewVO view) {

    StringBuffer ret = new StringBuffer();
    ret.append(view.getPk_org() + "|");
    ret.append(view.getCfanaceorgoid() + "|");
    ret.append(view.getPk_org() + "|");
    ret.append(view.getCbodywarehouseid() + "|");
    ret.append(view.getCmaterialoid() + "|");
    ret.append(view.getVbatchcode());
    return ret.toString();
  }

  private void setNnotaxmny(OutProfitViewVO view) {
    // ��˰���=Ӧ����˰��� + �������� - Ӧ����������* ������˰����(�����ݼӹ��м���)
    if (view.getFlargess() == null || !view.getFlargess().booleanValue()) {
      UFDouble qtorignetprice = view.getNqtorignetprice();
      UFDouble mny = UFDouble.ZERO_DBL;
      if (null != qtorignetprice) {
        mny =
            MathTool.sub(view.getNmainnum(), view.getNshouldreceivnum())
                .multiply(qtorignetprice);
      }
      view.setNnotaxmny(MathTool.add(view.getNtotalreceivmny(), mny));
      // ��˰����
      // view.setNnotaxprice(view.getNnotaxmny().div(view.getNmainnum()));
    }
    else {
      view.setNnotaxmny(UFDouble.ZERO_DBL);
    }
  }

  private void setMainnum(OutProfitViewVO view) {
    UFDouble retmainnum = view.getNnum();
    UFDouble recinum = view.getNshouldreceivnum();
    UFDouble costnum = view.getNcostnum();
    if (MathTool.absCompareTo(recinum, retmainnum) > 0) {
      retmainnum = recinum;
    }
    if (MathTool.absCompareTo(costnum, retmainnum) > 0) {
      retmainnum = costnum;
    }
    view.setNmainnum(retmainnum);
  }
}
