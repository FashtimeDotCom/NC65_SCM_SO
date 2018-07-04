package nc.pubimpl.so.split;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.so.split.ISplitBillByFreeCust;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * ����ɢ���ֵ�����
 * 
 * @since 6.0
 * @version 2011-7-11 ����03:15:39
 * @author ô��
 */
public class SplitBillByFreeCustImpl implements ISplitBillByFreeCust {

  @Override
  public List<String> splitByFreeCust(AggregatedValueObject vo, String[] keys)
      throws BusinessException {
    Set<String> bidset = new HashSet<String>();
    CircularlyAccessibleValueObject[] bvos = vo.getChildrenVO();
    for (CircularlyAccessibleValueObject bvo : bvos) {
      bidset.add(ValueUtils.getString(bvo.getAttributeValue(keys[0])));
    }

    // Map<���۶�������id, ɢ��id>
    Map<String, String> returnMap = new HashMap<String, String>();
    if (bidset.size() == 0) {
      return new ArrayList<String>();
    }

    // ��ѯ����ɢ�����Ƿ�ɢ��
    VOQuery<SaleOrderHVO> headQuery =
        new VOQuery<SaleOrderHVO>(SaleOrderHVO.class, new String[] {
          SaleOrderHVO.CSALEORDERID, SaleOrderHVO.BFREECUSTFLAG,
          SaleOrderHVO.CFREECUSTID, SaleOrderHVO.CPAYTERMID
        });
    SaleOrderHVO[] heads =
        headQuery.query(bidset.toArray(new String[bidset.size()]));
    if (heads != null && heads.length > 0) {
      for (int i = 0; i < heads.length; i++) {
        returnMap.put(heads[i].getCsaleorderid(), heads[i].getCfreecustid());
      }
    }
    List<String> retlist = new ArrayList<String>();
    for (CircularlyAccessibleValueObject bvo : bvos) {
      String cfreecustid = returnMap.get(bvo.getAttributeValue(keys[0]));
      retlist.add(cfreecustid);
    }
    return retlist;
  }
}
