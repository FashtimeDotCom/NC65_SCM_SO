package nc.ui.so.m33.pub;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.print.IDigitProcessor;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;

public class SaleOutPrintProcesser implements IDigitProcessor {

  private AbstractAppModel model;

  /**
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * @param model
   *          Ҫ���õ� model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  @Override
  public void cardpanelDigitProcess(BillCardPanel cardpanel) throws Exception {

    // // ���ű��ҽ��
    // String[] groupmnykeys = new String[] {
    // SquareOutBVO.NGROUPTAXMNY, SquareOutBVO.NGROUPMNY
    // };
    //
    // // ȫ�ֱ��ҽ��
    // String[] globalmnykeys = new String[] {
    // SquareOutBVO.NGLOBALTAXMNY, SquareOutBVO.NGLOBALMNY
    // };

    // ���ҽ��
    String[] mnykeys =
        new String[] {
          SquareOutBVO.NTAX, SquareOutBVO.NMNY, SquareOutBVO.NTAXMNY,
          SquareOutBVO.NDISCOUNT,
          // 2012.02.16 fbinly v61�����ֶ�
          SquareOutBVO.NCALTAXMNY
        };

    // �����ʾ���
    String[] hslkeys = new String[] {
      SquareOutBVO.VCHANGERATE
    };

    // ����
    String[] pricekeys =
        new String[] {
          SquareOutBVO.NORIGTAXPRICE, SquareOutBVO.NORIGPRICE,
          SquareOutBVO.NORIGTAXNETPRICE, SquareOutBVO.NORIGNETPRICE,
        
        };
    String[] netpricekeys=new String[]{
        SquareOutBVO.NTAXPRICE, SquareOutBVO.NPRICE,
        SquareOutBVO.NTAXNETPRICE, SquareOutBVO.NNETPRICE,
    };

    // ����
    String[] astnumkeys = new String[] {
      SquareOutBVO.NASTNUM
    };

    // ������
    String[] numkeys = new String[] {
      SquareOutBVO.NNUM, SquareOutBVO.NTHISNUM, SquareOutBVO.NTOTALSQUARENUM,SquareOutBVO.NRUSHNUM
    };

    // ԭ�ҽ��
    String[] origmnykeys =
        new String[] {
          // TODO 2012.02.16 fbinly v61ɾ��ԭ��˰���ֶ�
          // SquareOutBVO.NORIGTAX,
          SquareOutBVO.NORIGMNY, SquareOutBVO.NORIGTAXMNY,
          SquareOutBVO.NORIGDISCOUNT,
        };

    // ��Ʒ�ۿ�
    String[] itemdiscountratekeys = new String[] {
      SquareOutBVO.NITEMDISCOUNTRATE
    };

    // ˰��
    String[] taxratekeys = new String[] {
      SquareOutBVO.NTAXRATE
    };

    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(this.getModel().getContext().getPk_group(),
            cardpanel);
    // ���ҽ��
    scaleprocess.setMnyCtlInfo(mnykeys, PosEnum.body, null,
        SquareOutBVO.CCURRENCYID, PosEnum.body, null);
    // �����ʾ���
    scaleprocess.setHslCtlInfo(hslkeys, PosEnum.body, null);
    // ����
    scaleprocess.setPriceCtlInfo(pricekeys, PosEnum.body, null, SquareOutBVO.CORIGCURRENCYID,PosEnum.body,null);
    
    // ��λ�ҵ���
    scaleprocess.setPriceCtlInfo(netpricekeys, PosEnum.body, null, SquareOutBVO.CCURRENCYID,PosEnum.body,null);
    // ����
    scaleprocess.setNumCtlInfo(astnumkeys, PosEnum.body, null,
        SquareOutBVO.CASTUNITID, PosEnum.body, null);
    // ������
    scaleprocess.setNumCtlInfo(numkeys, PosEnum.body, null,
        SquareOutBVO.CUNITID, PosEnum.body, null);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(origmnykeys, PosEnum.body, null,
        SquareOutBVO.CORIGCURRENCYID, PosEnum.body, null);

    // �ۿ�
    scaleprocess.setSaleDiscountCtlInfo(itemdiscountratekeys, PosEnum.body,
        null);

    // ˰��
    scaleprocess.setTaxRateCtlInfo(taxratekeys, PosEnum.body, null);

    scaleprocess.process();
  }

}
