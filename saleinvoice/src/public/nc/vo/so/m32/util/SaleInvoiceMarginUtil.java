package nc.vo.so.m32.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.margin.BillMarginContext;
import nc.vo.pubapp.margin.MarginContextFactory;
import nc.vo.pubapp.margin.MarginEntry;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.margin.SCMMuiltyWordMarginJudgement;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.util.ListUtil;

import nc.itf.pubapp.margin.IMarginDudgement;

/**
 * ���۷�Ʊβ�������
 * 
 * @since 6.0
 * @version 2012-3-27 ����01:35:42
 * @author ô��
 */
public class SaleInvoiceMarginUtil {

  // ��Դ�ڶ���
  private final String[] destMNYformOrder = new String[] {
    SaleInvoiceBVO.NORIGMNY, SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NORIGDISCOUNT, SaleInvoiceBVO.NCALTAXMNY,
    SaleInvoiceBVO.NTAX
  };

  // ��Դ�ڳ��ⵥ
  private final String[] destMNYformOut = new String[] {
    SaleInvoiceBVO.NORIGMNY, SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NCALTAXMNY, SaleInvoiceBVO.NTAX
  };

  private final String[] ordersrcMNY = new String[] {
    SaleOrderBVO.NORIGMNY, SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT, SaleOrderBVO.NCALTAXMNY, SaleOrderBVO.NTAX
  };

  private final String[] saleoutsrcMNY = new String[] {
    SaleOutBodyVO.NORIGMNY, SaleOutBodyVO.NORIGTAXMNY, SaleOrderBVO.NCALTAXMNY,
    SaleOutBodyVO.NTAX
  };

  // ԭ����һ�¶��յ��ֶ�
  // ��Դ�ڶ���
  private final String[] destMNYformOrdercursame = new String[] {
    SaleInvoiceBVO.NORIGMNY, SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NORIGDISCOUNT, SaleInvoiceBVO.NCALTAXMNY,
    SaleInvoiceBVO.NTAX, SaleInvoiceBVO.NMNY, SaleInvoiceBVO.NTAXMNY,
    SaleInvoiceBVO.NDISCOUNT, SaleInvoiceBVO.NGROUPMNY,
    SaleInvoiceBVO.NGROUPTAXMNY, SaleInvoiceBVO.NGLOBALMNY,
    SaleInvoiceBVO.NGLOBALTAXMNY
  };

  // ��Դ�ڳ��ⵥ
  private final String[] destMNYformOutcursame = new String[] {
    SaleInvoiceBVO.NORIGMNY, SaleInvoiceBVO.NORIGTAXMNY,
    SaleInvoiceBVO.NCALTAXMNY, SaleInvoiceBVO.NTAX, SaleInvoiceBVO.NMNY,
    SaleInvoiceBVO.NTAXMNY, SaleInvoiceBVO.NGROUPMNY,
    SaleInvoiceBVO.NGROUPTAXMNY, SaleInvoiceBVO.NGLOBALMNY,
    SaleInvoiceBVO.NGLOBALTAXMNY
  };

  private final String[] ordersrcMNYcursame = new String[] {
    SaleOrderBVO.NORIGMNY, SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT, SaleOrderBVO.NCALTAXMNY, SaleOrderBVO.NTAX,
    SaleOrderBVO.NMNY, SaleOrderBVO.NTAXMNY, SaleOrderBVO.NDISCOUNT,
    SaleOrderBVO.NGROUPMNY, SaleOrderBVO.NGROUPTAXMNY, SaleOrderBVO.NGLOBALMNY,
    SaleOrderBVO.NGLOBALTAXMNY
  };

  private final String[] saleoutsrcMNYcursame = new String[] {
    SaleOutBodyVO.NORIGMNY, SaleOutBodyVO.NORIGTAXMNY, SaleOrderBVO.NCALTAXMNY,
    SaleOutBodyVO.NTAX, SaleOutBodyVO.NMNY, SaleOutBodyVO.NTAXMNY,
    SaleOutBodyVO.NGROUPMNY, SaleOutBodyVO.NGROUPTAXMNY,
    SaleOutBodyVO.NGLOBALMNY, SaleOutBodyVO.NGLOBALTAXMNY
  };

  /**
   * β���
   * 
   * @param vos
   */
  public void processMargin(SaleInvoiceVO[] vos,
      Map<String, UFDouble> srcnummaps) {
    List<SaleInvoiceVO> srcordercurnosame = new ArrayList<SaleInvoiceVO>();
    List<SaleInvoiceVO> srcsaleoutcurnosame = new ArrayList<SaleInvoiceVO>();

    List<SaleInvoiceVO> srcordercursame = new ArrayList<SaleInvoiceVO>();
    List<SaleInvoiceVO> srcsaleoutcursame = new ArrayList<SaleInvoiceVO>();
    List<SaleInvoiceVO> allsrcsaleout = new ArrayList<SaleInvoiceVO>();

    // ׼������
    this.prepareData(vos, srcnummaps, srcordercurnosame, srcsaleoutcurnosame,
        srcordercursame, srcsaleoutcursame, allsrcsaleout);

    // ������Դ�ڶ���β��
    this.processOrderMargin(srcordercurnosame, srcordercursame);
    // ������Դ�ڳ��ⵥβ��
    this.processSaleOutMargin(srcsaleoutcurnosame, srcsaleoutcursame);

    // ��Դ�ڳ��ⵥ��ʱ����������û�г��ⵥ����Ҫ�����ۿ۶�
    if (allsrcsaleout.size() > 0) {
      SaleInvoiceVOUtil voutil = new SaleInvoiceVOUtil();
      SaleInvoiceVO[] allsrcsaleoutvos = ListUtil.toArray(allsrcsaleout);
      // �ü�˰�ϼƼ��㣨ֻ����Դ�ڳ����ʱ����Ҫ ��Ϊ���ⵥû���ۿ۶
      this.reCalculatorDiscountmnyByKey(allsrcsaleoutvos,
          SaleInvoiceBVO.NORIGTAXMNY);
      // �Ӳ�����ⵥû�е�����
      voutil.makeupInfo(allsrcsaleoutvos);
    }

  }

  /**
   * ������Դ����ⵥ��β��
   * 
   * @param srcsaleoutcurnosame ԭ���Ҳ�һ�µķ�Ʊ
   * @param srcsaleoutcursame ԭ����һֱ�ķ�Ʊ
   */
  private void processSaleOutMargin(List<SaleInvoiceVO> srcsaleoutcurnosame,
      List<SaleInvoiceVO> srcsaleoutcursame) {
    SaleInvoiceVOCalculator voculator = new SaleInvoiceVOCalculator();
    // ���� ת��ʱΪ�̶�������
    voculator.setForcefixunitrate(UFBoolean.TRUE);
    for (SaleInvoiceVO calvo : srcsaleoutcurnosame) {
      voculator.setVoInvoice(calvo);
      voculator.calculateAll(SaleInvoiceBVO.NNUM);
    }
    for (SaleInvoiceVO calvo : srcsaleoutcursame) {
      voculator.setVoInvoice(calvo);
      voculator.calculateAll(SaleInvoiceBVO.NNUM);
    }
    IMarginDudgement margindudgement =
        new SCMMuiltyWordMarginJudgement(new String[] {
          SaleOutBodyVO.NORIGNETPRICE, SaleOutBodyVO.NORIGTAXNETPRICE
        }, new String[] {
          SaleInvoiceBVO.NORIGNETPRICE, SaleInvoiceBVO.NORIGTAXNETPRICE
        });
    try {
      // ԭ���Ҳ�һ�£�������β�����۱����ʴ����㷨
      if (srcsaleoutcurnosame.size() > 0) {
        SaleInvoiceVO[] outvos = ListUtil.toArray(srcsaleoutcurnosame);
        BillMarginContext context =
            MarginContextFactory.getInstance().produceMarginContext(outvos,
                ICBillType.SaleOut.getCode(), SaleOrderBVO.NNUM,
                this.saleoutsrcMNY, SOBillType.Invoice.getCode(),
                SaleInvoiceBVO.NNUM, this.destMNYformOut,
                SaleInvoiceBVO.CSRCBID, SaleInvoiceBVO.CSRCID, margindudgement);
        MarginEntry.getInstance().process(context);
        this.reCalculatorByNexchangerate(ListUtil.toArray(srcsaleoutcurnosame),
            SaleInvoiceHVO.NEXCHANGERATE);
      }
      // ԭ����һ��
      if (srcsaleoutcursame.size() > 0) {
        SaleInvoiceVO[] outvos = ListUtil.toArray(srcsaleoutcursame);
        BillMarginContext context =
            MarginContextFactory.getInstance().produceMarginContext(outvos,
                ICBillType.SaleOut.getCode(), SaleOrderBVO.NNUM,
                this.saleoutsrcMNYcursame, SOBillType.Invoice.getCode(),
                SaleInvoiceBVO.NNUM, this.destMNYformOutcursame,
                SaleInvoiceBVO.CSRCBID, SaleInvoiceBVO.CSRCID, margindudgement);
        MarginEntry.getInstance().process(context);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ������Դ�붩����΢΢��
   * 
   * @param srcordercursame ԭ����һֱ�ķ�Ʊ
   * @param srcsaleoutcrusame ԭ���Ҳ�һ�µķ�Ʊ
   */
  private void processOrderMargin(List<SaleInvoiceVO> srcordercurnosame,
      List<SaleInvoiceVO> srcordercursame) {
    SaleInvoiceVOCalculator voculator = new SaleInvoiceVOCalculator();
    // ���� ת��ʱΪ�̶�������
    voculator.setForcefixunitrate(UFBoolean.TRUE);
    for (SaleInvoiceVO calvo : srcordercursame) {
      voculator.setVoInvoice(calvo);
      voculator.calculateAll(SaleInvoiceBVO.NNUM);
    }
    for (SaleInvoiceVO calvo : srcordercurnosame) {
      voculator.setVoInvoice(calvo);
      voculator.calculateAll(SaleInvoiceBVO.NNUM);
    }
    IMarginDudgement margindudgement =
        new SCMMuiltyWordMarginJudgement(new String[] {
          SaleOrderBVO.NORIGNETPRICE, SaleOrderBVO.NORIGTAXNETPRICE
        }, new String[] {
          SaleInvoiceBVO.NORIGNETPRICE, SaleInvoiceBVO.NORIGTAXNETPRICE
        });
    try {
      if (srcordercurnosame.size() > 0) {
        SaleInvoiceVO[] ordervos = ListUtil.toArray(srcordercurnosame);
        BillMarginContext context =
            MarginContextFactory.getInstance().produceMarginContext(ordervos,
                SOBillType.Order.getCode(), SaleOrderBVO.NNUM,
                this.ordersrcMNY, SOBillType.Invoice.getCode(),
                SaleInvoiceBVO.NNUM, this.destMNYformOrder,
                SaleInvoiceBVO.CSRCBID, SaleInvoiceBVO.CSRCID, margindudgement);
        MarginEntry.getInstance().process(context);
        this.reCalculatorByNexchangerate(ListUtil.toArray(srcordercurnosame),
            SaleInvoiceHVO.NEXCHANGERATE);
      }
      if (srcordercursame.size() > 0) {
        SaleInvoiceVO[] ordervos = ListUtil.toArray(srcordercursame);
        BillMarginContext context =
            MarginContextFactory.getInstance().produceMarginContext(ordervos,
                SOBillType.Order.getCode(), SaleOrderBVO.NNUM,
                this.ordersrcMNYcursame, SOBillType.Invoice.getCode(),
                SaleInvoiceBVO.NNUM, this.destMNYformOrdercursame,
                SaleInvoiceBVO.CSRCBID, SaleInvoiceBVO.CSRCID, margindudgement);
        MarginEntry.getInstance().process(context);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ��������
   * 
   * @param vos ԭʼVOS
   * @param srcnummaps ��Դ����
   * @param destvo1 ��һ��������VOS
   * @param srcorder ��Դ�������Ҳ��ǵ�һ��������VOS
   * @param srcsaleout ��Դ���ⵥ�Ĳ��Ҳ��ǵ�һ������vos
   * @param allsrcsaleout ������Դ�ڳ��ⵥ������
   */
  private void prepareData(SaleInvoiceVO[] vos,
      Map<String, UFDouble> srcnummaps, List<SaleInvoiceVO> srcordercurnosame,
      List<SaleInvoiceVO> srcsaleoutcurnosame,
      List<SaleInvoiceVO> srcordercursame,
      List<SaleInvoiceVO> srcsaleoutcursame, List<SaleInvoiceVO> allsrcsaleout) {
    List<SaleInvoiceVO> destvo1 = new ArrayList<SaleInvoiceVO>();
    UFDouble voicenum = null;
    UFDouble srcnum = null;
    for (SaleInvoiceVO vo : vos) {
      SaleInvoiceHVO hvo = vo.getParentVO();
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      // ��һ�������ı���
      List<SaleInvoiceBVO> onebvos = new ArrayList<SaleInvoiceBVO>();
      if (SOBillType.Order.getCode().equals(bvos[0].getVsrctype())) {
        List<SaleInvoiceBVO> bsrcorder = new ArrayList<SaleInvoiceBVO>();
        for (SaleInvoiceBVO bvo : bvos) {
          voicenum = bvo.getNnum();
          srcnum = srcnummaps.get(bvo.getCsrcbid());
          if (MathTool.compareTo(voicenum, srcnum) == 0
              || MathTool.compareTo(voicenum, UFDouble.ZERO_DBL) == 0) {
            onebvos.add(bvo);
            continue;
          }

          bsrcorder.add(bvo);
        }
        if (bsrcorder.size() > 0) {
          SaleInvoiceVO newvo = new SaleInvoiceVO();
          newvo.setParentVO(hvo);
          newvo.setChildrenVO(ListUtil.toArray(bsrcorder));

          if (hvo.getCcurrencyid().equals(hvo.getCorigcurrencyid())) {
            // ԭ����һ��
            srcordercursame.add(newvo);
          }
          else {
            // ԭ���Ҳ�һ��
            srcordercurnosame.add(newvo);
          }

        }
      }
      else if (ICBillType.SaleOut.getCode().equals(bvos[0].getVsrctype())) {
        allsrcsaleout.add(vo);
        List<SaleInvoiceBVO> bsrcsaleout = new ArrayList<SaleInvoiceBVO>();
        for (SaleInvoiceBVO bvo : bvos) {
          voicenum = bvo.getNnum();
          srcnum = srcnummaps.get(bvo.getCsrcbid());
          if (MathTool.compareTo(voicenum, srcnum) == 0
              || MathTool.compareTo(voicenum, UFDouble.ZERO_DBL) == 0) {
            onebvos.add(bvo);
            continue;

          }
          bsrcsaleout.add(bvo);
        }
        if (bsrcsaleout.size() > 0) {
          SaleInvoiceVO newvo = new SaleInvoiceVO();
          newvo.setParentVO(hvo);
          newvo.setChildrenVO(ListUtil.toArray(bsrcsaleout));
          if (hvo.getCcurrencyid().equals(hvo.getCorigcurrencyid())) {
            // ԭ����һ��
            srcsaleoutcursame.add(newvo);
          }
          else {
            // ԭ���Ҳ�һ��
            srcsaleoutcurnosame.add(newvo);
          }
        }
      }

      if (onebvos.size() > 0) {
        SaleInvoiceVO newvo = new SaleInvoiceVO();
        newvo.setParentVO(hvo);
        newvo.setChildrenVO(ListUtil.toArray(onebvos));
        destvo1.add(newvo);

      }

    }

    if (destvo1.size() > 0) {
      SaleInvoiceRateUtil rateutil = new SaleInvoiceRateUtil();
      rateutil.setBuyRate(destvo1.toArray(new SaleInvoiceVO[destvo1.size()]));

      // ���ű�λ�һ��ʸı�ķ�Ʊvo
      List<SaleInvoiceVO> groupchgratevos = rateutil.getGroupchgratevos();
      if (groupchgratevos != null && groupchgratevos.size() > 0) {
        this.reCalculatorByNexchangerate(
            groupchgratevos.toArray(new SaleInvoiceVO[groupchgratevos.size()]),
            SaleInvoiceHVO.NGROUPEXCHGRATE);
      }

      // ȫ�ֱ�λ�һ��ʸı�ķ�Ʊvo
      List<SaleInvoiceVO> globalexchgratevos = rateutil.getGlobalexchgratevos();
      if (globalexchgratevos != null && globalexchgratevos.size() > 0) {
        this.reCalculatorByNexchangerate(globalexchgratevos
            .toArray(new SaleInvoiceVO[globalexchgratevos.size()]),
            SaleInvoiceHVO.NGLOBALEXCHGRATE);
      }

      // �۱����ʸı�ķ�ƱVO
      List<SaleInvoiceVO> ratechangevos = rateutil.getRatechangevos();
      if (ratechangevos != null && ratechangevos.size() > 0) {
        this.reCalculatorByNexchangerate(
            ratechangevos.toArray(new SaleInvoiceVO[ratechangevos.size()]),
            SaleInvoiceHVO.NEXCHANGERATE);
      }
    }

  }

  /**
   * �����ۿ۶�
   * 
   * @param vos
   * @param key
   */
  private void reCalculatorDiscountmnyByKey(SaleInvoiceVO[] vos, String key) {
    SaleInvoiceVOCalculator voculator = new SaleInvoiceVOCalculator();
    for (SaleInvoiceVO vo : vos) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      List<Integer> integerlist = new ArrayList<Integer>();

      for (int i = 0; i < bvos.length; i++) {
        if (null == bvos[i].getNnum()
            || bvos[i].getNnum().compareTo(UFDouble.ZERO_DBL) == 0) {
          continue;
        }
        integerlist.add(Integer.valueOf(i));
      }

      voculator.setVoInvoice(vo);
      voculator.setForcefixunitrate(UFBoolean.TRUE);
      voculator.calculateDiscountmny(ListUtil.toArray(integerlist), key);
    }
  }

  /**
   * ��ȡ���ʼ��㵥�۽��(���۱����ʴ����۸��㷨)
   * ��������bug�����������β����۱�������û�䣬ע����һ�������۱����ʱ��ˣ�
   * 
   * @param vos
   */
  private void reCalculatorByNexchangerate(SaleInvoiceVO[] vos, String editKey) {

    SaleInvoiceVOCalculator voculator = new SaleInvoiceVOCalculator();

    for (SaleInvoiceVO vo : vos) {

      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      Integer[] intchangerows = new Integer[bvos.length];
      for (int i = 0; i < bvos.length; i++) {
        intchangerows[i] = Integer.valueOf(i);
      }

      voculator.setVoInvoice(vo);
      voculator.calculate(intchangerows, editKey);
    }
  }

}
