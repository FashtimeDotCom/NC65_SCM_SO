package nc.ui.so.m33.pub.view;

import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;

import nc.desktop.ui.WorkbenchEnvironment;

import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public abstract class AbstractM33BillPubListView extends ShowUpableBillListView {

  private static final long serialVersionUID = -63336330577496381L;

  /**
   * ��ǰ��ѯ�������ͣ�QueryFlag�еĳ���
   */
  private int queryFlag;

  public int getQueryFlag() {
    return this.queryFlag;
  }

  public void setQueryFlag(int queryFlag) {
    this.queryFlag = queryFlag;
  }

  protected abstract String[] getNoEditEnableKey();

  @Override
  protected void setBillListPanelProp() {

    super.setBillListPanelProp();

    // ���þ���
    this.initDataDigit();

    // �����ֶα༭��
    this.initEditEnable();

  }

  private void initDataDigit() {
    // ���ű��ҽ��
    String[] groupmnykeys = new String[] {
      SquareOutBVO.NGROUPTAXMNY, SquareOutBVO.NGROUPMNY
    };

    // ȫ�ֱ��ҽ��
    String[] globalmnykeys = new String[] {
      SquareOutBVO.NGLOBALTAXMNY, SquareOutBVO.NGLOBALMNY
    };

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
    
    String[] netpricekeys=new String[]{ SquareOutBVO.NTAXPRICE, SquareOutBVO.NPRICE,
        SquareOutBVO.NTAXNETPRICE, SquareOutBVO.NNETPRICE,};

    // ����
    String[] astnumkeys = new String[] {
      SquareOutBVO.NASTNUM
    };

    // ������
    String[] numkeys =
        new String[] {
          SquareOutBVO.NNUM, SquareOutBVO.NTHISNUM,
          SquareOutBVO.NTOTALSQUARENUM, SquareOutBVO.NRUSHNUM
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

    TableScaleProcessor scaleprocess =
        new BillModelScaleProcessor(WorkbenchEnvironment.getInstance()
            .getGroupVO().getPk_group(), this.billListPanel.getBodyBillModel());

    // ȫ�ֱ��ҽ��
    scaleprocess.setGlobalLocMnyCtlInfo(globalmnykeys);
    // ���ű��ҽ��
    scaleprocess.setGroupLocMnyCtlInfo(groupmnykeys);
    // ���ҽ��
    scaleprocess.setMnyCtlInfo(mnykeys, SquareOutBVO.CCURRENCYID);
    // �����ʾ���
    scaleprocess.setHslCtlInfo(hslkeys);
    // ����
    scaleprocess.setPriceCtlInfo(pricekeys,SquareOutBVO.CORIGCURRENCYID);
    
    // ���ҵ���
    scaleprocess.setPriceCtlInfo(netpricekeys,SquareOutBVO.CCURRENCYID);
    
    // ����
    scaleprocess.setNumCtlInfo(astnumkeys, SquareOutBVO.CASTUNITID);
    // ������
    scaleprocess.setNumCtlInfo(numkeys, SquareOutBVO.CUNITID);
    // ԭ�ҽ��
    scaleprocess.setMnyCtlInfo(origmnykeys, SquareOutBVO.CORIGCURRENCYID);

    // �ۿ�
    scaleprocess.setSaleDiscountCtlInfo(itemdiscountratekeys);

    // ˰��
    scaleprocess.setTaxRateCtlInfo(taxratekeys);

    scaleprocess.process();

  }

  private void initEditEnable() {
    String[] itemKeys = this.getNoEditEnableKey();
    for (String key : itemKeys) {
      this.billListPanel.getBodyBillModel().getItemByKey(key).setEdit(false);
    }
  }

}
