package nc.ui.so.salequotation.scale;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoScaleProcessor {

  // ����
  private static final String[] ASTNUMKEYS = new String[] {
    SalequotationBVO.NASSISTNUM
  };

  // ������
  private static final String[] CONVERTRATEKEYS = new String[] {
    SalequotationBVO.NQTCHANGERATE, SalequotationBVO.NCHANGERATE
  };

  // �����ۿ�
  private static final String[] DISCOUNTRATE = new String[] {
    SalequotationBVO.NDISCOUNTRATE, SalequotationBVO.NITEMDISCOUNTRATE
  };

  // ��ͷ�ۿ�
  private static final String[] HEADDISCOUNTRATE = new String[] {
    SalequotationHVO.NDISCOUNT
  };

  // ��ͷ��˰�ϼ�
  private static final String[] HEADTAXMNY = new String[] {
    SalequotationHVO.NTOTALMNY
  };

  private static SalequoScaleProcessor instance = new SalequoScaleProcessor();

  // ԭ�ҽ��
  private static final String[] MNYKEYS = new String[] {
    SalequotationBVO.NORIGMNY, SalequotationBVO.NORIGTAXMNY,
    SalequotationBVO.NORIGDISCOUNT,
  };

  // ������
  private static final String[] NUMKEYS = new String[] {
    SalequotationBVO.NNUM, SalequotationBVO.NORDERNUM,
    SalequotationBVO.NCONTRACTNUM
  };

  // ����
  private static final String[] PRICEKEYS = new String[] {
    SalequotationBVO.NQTORIGPRICE, SalequotationBVO.NQTORIGTAXPRICE,
    SalequotationBVO.NQTORIGNETPRICE, SalequotationBVO.NQTORIGTAXNETPRC,
    SalequotationBVO.NORIGPRICE, SalequotationBVO.NORIGTAXPRICE,
    SalequotationBVO.NORIGNETPRICE, SalequotationBVO.NORIGTAXNETPRICE
  };

  // ��������
  private static final String[] QTNUMKEYS = new String[] {
    SalequotationBVO.NQTNUM
  };

  // ����˰��
  private static final String[] TAXRATEKEY = new String[] {
    SalequotationBVO.NTAXRATE
  };

  public static SalequoScaleProcessor getInstance() {
    return SalequoScaleProcessor.instance;
  }

  private SalequoScaleProcessor() {
    // do nothing
  }

  private void setBillPrecision(BillScaleProcessor scaleprocess) {
    this.setBillPrecision(scaleprocess, null);
  }

  private void setBillPrecision(BillScaleProcessor scaleprocess,
      TotalValueScale totalscale) {
    // �����ۿۣ���ͷ��
    scaleprocess.setSaleDiscountCtlInfo(SalequoScaleProcessor.HEADDISCOUNTRATE,
        PosEnum.head, null);
    // ԭ�Ҽ�˰�ϼƣ���ͷ��
    scaleprocess.setMnyCtlInfo(SalequoScaleProcessor.HEADTAXMNY, PosEnum.head,
        null, SalequotationHVO.PK_CURRTYPE, PosEnum.head, null);

    // ˰��
    scaleprocess.setTaxRateCtlInfo(SalequoScaleProcessor.TAXRATEKEY,
        PosEnum.body, null);
    // �ۿ�
    scaleprocess.setSaleDiscountCtlInfo(SalequoScaleProcessor.DISCOUNTRATE,
        PosEnum.body, null);
    // ������
    scaleprocess.setHslCtlInfo(SalequoScaleProcessor.CONVERTRATEKEYS,
        PosEnum.body, null);
    // ����
    scaleprocess.setNumCtlInfo(SalequoScaleProcessor.ASTNUMKEYS, PosEnum.body,
        null, SalequotationBVO.CASTUNITID, PosEnum.body, null);
    // ������
    scaleprocess.setNumCtlInfo(SalequoScaleProcessor.NUMKEYS, PosEnum.body,
        null, SalequotationBVO.PK_UNIT, PosEnum.body, null);
    // ��������
    scaleprocess.setNumCtlInfo(SalequoScaleProcessor.QTNUMKEYS, PosEnum.body,
        null, SalequotationBVO.CQTUNITID, PosEnum.body, null);
    // ����
    scaleprocess.setPriceCtlInfo(SalequoScaleProcessor.PRICEKEYS, PosEnum.body,
        null,SalequotationHVO.PK_CURRTYPE,PosEnum.head,null);
    scaleprocess.setMnyCtlInfo(SalequoScaleProcessor.MNYKEYS, PosEnum.body,
        null, SalequotationHVO.PK_CURRTYPE, PosEnum.head, null);
    // ��ͷ�ϼƾ��ȴ���
    if (totalscale != null) {
      totalscale.setHeadTailKeys(new String[] {
        SalequotationHVO.NTOTALNUM
      });
    }
    scaleprocess.process();

  }

  public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(pk_group, cardpanel);
    TotalValueScale totalscale = new TotalValueScaleProcessor(cardpanel);
    this.setBillPrecision(scaleprocess, totalscale);
  }

  public void setListPrecision(String pk_group, BillListPanel listPanel) {
    BillScaleProcessor scaleprocess =
        new ListPaneScaleProcessor(pk_group, listPanel);
    TotalValueScale totalscale = new TotalValueScaleProcessor(listPanel);
    this.setBillPrecision(scaleprocess, totalscale);
  }

  public void setModelPrecision(String pk_group, BillModel model) {
    TableScaleProcessor scaleprocess =
        new BillModelScaleProcessor(pk_group, model);
    this.setTablePrecision(scaleprocess);
  }

  public void setScaleForSingleTable(BillScaleProcessor scale) {
    if (scale != null) {
      this.setBillPrecision(scale);
    }
  }

  /**
   * �����ȴ���
   * 
   * @param panel
   */
  public void setSingleTableScale(String pk_group, BillListPanel panel) {
    this.setModelPrecision(pk_group, panel.getHeadBillModel());
  }

  private void setTablePrecision(TableScaleProcessor scaleprocess) {
    // ԭ�Ҽ�˰�ϼƣ���ͷ��
    scaleprocess.setMnyCtlInfo(SalequoScaleProcessor.HEADTAXMNY,
        SalequotationHVO.PK_CURRTYPE);
    // ˰��
    scaleprocess.setTaxRateCtlInfo(SalequoScaleProcessor.TAXRATEKEY);
    // ������
    scaleprocess.setHslCtlInfo(SalequoScaleProcessor.CONVERTRATEKEYS);
    // ����
    scaleprocess.setNumCtlInfo(SalequoScaleProcessor.ASTNUMKEYS,
        SalequotationBVO.CASTUNITID);
    // ������
    scaleprocess.setNumCtlInfo(SalequoScaleProcessor.NUMKEYS,
        SalequotationBVO.PK_UNIT);
    // ��������
    scaleprocess.setNumCtlInfo(SalequoScaleProcessor.QTNUMKEYS,
        SalequotationBVO.CQTUNITID);
    // ����
    scaleprocess.setPriceCtlInfo(SalequoScaleProcessor.PRICEKEYS,SalequotationHVO.PK_CURRTYPE);
    // ���
    scaleprocess.setMnyCtlInfo(SalequoScaleProcessor.MNYKEYS,
        SalequotationHVO.PK_CURRTYPE);

    scaleprocess.process();
  }

  public void setVOPrecision(String pk_group, AggregatedValueObject[] bills) {
    BillScaleProcessor scaleprocess = new BillVOScaleProcessor(pk_group, bills);
    this.setBillPrecision(scaleprocess);
  }
}
