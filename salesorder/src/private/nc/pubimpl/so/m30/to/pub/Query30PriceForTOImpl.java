package nc.pubimpl.so.m30.to.pub;

import java.util.HashSet;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.so.m30.to.pub.IQuery30PriceForTO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class Query30PriceForTOImpl implements IQuery30PriceForTO {

  @Override
  public SaleOrderVO[] queryOrigPrice(String[] bids) throws BusinessException {
    if (bids == null || bids.length == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0190")/*@res "��ѯ���۶����ı���IDΪ��"*/);
    }

    // 1.�����SaleOrderBVO[]
    SaleOrderBVO[] bodys = this.queryBodys(bids);

    if (bodys == null || bodys.length == 0) {
      return new SaleOrderVO[0];
    }

    // 2.���ͷSaleOrderHVO[]
    String[] hids = this.getHidsByBodys(bodys);
    SaleOrderHVO[] heads = this.queryHeads(hids);

    // 3.�ϲ�retVO
    BillComposite<SaleOrderVO> bc =
        new BillComposite<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO bill = new SaleOrderVO();
    bc.append(bill.getMetaData().getParent(), heads);
    bc.append(bill.getMetaData().getVOMeta(SaleOrderBVO.class), bodys);
    return bc.composite();
  }

  private String[] getHidsByBodys(SaleOrderBVO[] bodys) {
    Set<String> hsHids = new HashSet<String>();
    for (SaleOrderBVO body : bodys) {
      hsHids.add(body.getCsaleorderid());
    }
    return hsHids.toArray(new String[0]);
  }

  private SaleOrderHVO[] queryHeads(String[] hids) {
    // ���ͷhid��ԭ�ұ���
    VOQuery<SaleOrderHVO> hvoQuery =
        new VOQuery<SaleOrderHVO>(SaleOrderHVO.class, new String[] {
          SaleOrderHVO.CSALEORDERID, SaleOrderHVO.CORIGCURRENCYID
        });
    return hvoQuery.query(hids);
  }

  private SaleOrderBVO[] queryBodys(String[] bids) {
    // �����hid��bid��ԭ����˰���ۡ�ԭ�Һ�˰����
    VOQuery<SaleOrderBVO> bvoQuery =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, new String[] {
          SaleOrderBVO.CSALEORDERID, SaleOrderBVO.CSALEORDERBID,
          SaleOrderBVO.NQTORIGPRICE, SaleOrderBVO.NQTORIGTAXPRICE,
          SaleOrderBVO.CQTUNITID
        });
    return bvoQuery.query(bids);
  }
}