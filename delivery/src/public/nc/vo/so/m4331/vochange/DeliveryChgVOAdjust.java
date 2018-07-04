package nc.vo.so.m4331.vochange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bd.userdef.UserDefCheckUtils;
import nc.pubitf.so.m30.pub.ISaleOrderForPub;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.rule.FillDeFaultDataRule;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.comparator.BillNOandRowNoComparator;
import nc.vo.to.m5x.entity.BillItemVO;
import nc.vo.to.m5x.entity.BillVO;

public class DeliveryChgVOAdjust implements IChangeVOAdjust {

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    AggregatedValueObject[] srcVOs = new AggregatedValueObject[] {
      srcVO
    };

    AggregatedValueObject[] destVOs = new AggregatedValueObject[] {
      destVO
    };
    this.batchAdjustAfterChange(srcVOs, destVOs, adjustContext);
    return destVO;
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    return null;
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {

    // modify by jilu for EHP1���̵�633 20140703
    // �Ƶ���ʱ���������۶��������ۿ��� �������������رգ���������Ҫȥ����Ӧ����
    Map<String, UFBoolean> upmap = this.getUpbboutendflagMap(srcVOs);
    DeliveryVO[] vos = (DeliveryVO[]) destVOs;
    destVOs = this.fiterDestVOs(upmap, destVOs);
    // ������Դ���ݺź��к����򣬱�֤�����ε��ݵ���˳��
    for (DeliveryVO vo : vos) {
      BillNOandRowNoComparator c = new BillNOandRowNoComparator();
      Arrays.sort(vo.getChildrenVO(), c);
    }
    FillDeFaultDataRule util = new FillDeFaultDataRule();
    util.setDefaultData(srcVOs, destVOs);
   // this.checkVOS(vos);
    return destVOs;
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return null;
  }

  /**
   * �������ε���
   * ������ζ��������ر� �����۶��������ۿ����������������رգ���ȥ��������Ӧ����
   * 
   * @param upmap
   * @param destVOs
   */
  private AggregatedValueObject[] fiterDestVOs(Map<String, UFBoolean> upmap,
      AggregatedValueObject[] destVOs) {
    for (AggregatedValueObject destVO : destVOs) {
      List<DeliveryBVO> bvolist = new ArrayList<DeliveryBVO>();
      CircularlyAccessibleValueObject[] bvos = destVO.getChildrenVO();
      for (CircularlyAccessibleValueObject bvo : bvos) {
        String srcid = (String) bvo.getAttributeValue(DeliveryBVO.CSRCBID);
        UFBoolean bbsendendflag = upmap.get(srcid);
        if (null == bbsendendflag || !bbsendendflag.booleanValue()) {
          bvolist.add((DeliveryBVO) bvo);
        }
      }
      destVO.setChildrenVO(bvolist.toArray(new DeliveryBVO[bvolist.size()]));
    }
    boolean allNull = true;
    for (AggregatedValueObject destVO : destVOs) {
      if (destVO.getChildrenVO().length > 0 ) {
        allNull = false;
        break;
      }
    }
    if (allNull) {
      return null;
    }else{
      return destVOs;
    }
  }

  @SuppressWarnings("rawtypes")
  private void checkVOS(DeliveryVO[] destVOs) {
    if (destVOs == null) {
      return;
    }
    String[] ruleCodes = new String[] {
      "4331_H", "4331_B"
    };
    String[] prefixs = new String[] {
      SOConstant.VDEF, SOConstant.VBDEF
    };
    Class[] voClasses = new Class[] {
      DeliveryHVO.class, DeliveryBVO.class
    };
    UserDefCheckUtils.check(destVOs, ruleCodes, prefixs, voClasses);
  }

  /**
   * ��ȡ���ζ����ķ����ر�״̬
   * 
   * @param srcVOs
   * @return
   */
  private Map<String, UFBoolean> getUpbboutendflagMap(
      AggregatedValueObject[] srcVOs) {
    Map<String, UFBoolean> upmap = new HashMap<String, UFBoolean>();
    List<String> sobids = new ArrayList<String>();
    for (AggregatedValueObject srcVO : srcVOs) {
      if (srcVO instanceof SaleOrderVO) {
        CircularlyAccessibleValueObject[] bvos = srcVO.getChildrenVO();
        for (CircularlyAccessibleValueObject bvo : bvos) {
          sobids
              .add((String) bvo.getAttributeValue(SaleOrderBVO.CSALEORDERBID));
        }
      }
      // ��Դ�����������
      else if (srcVO instanceof BillVO) {
        CircularlyAccessibleValueObject[] bvos = srcVO.getChildrenVO();
        for (CircularlyAccessibleValueObject bvo : bvos) {
          upmap.put((String) bvo.getAttributeValue(BillItemVO.CBILL_BID),
              (UFBoolean) bvo.getAttributeValue(BillItemVO.BSENDENDFLAG));
        }
      }
    }
    if (sobids.size() > 0) {
      ISaleOrderForPub squaresrv =
          NCLocator.getInstance().lookup(ISaleOrderForPub.class);
      try {
        SaleOrderViewVO[] viewvos =
            squaresrv.querySaleOrderViewVOs(
                sobids.toArray(new String[sobids.size()]), new String[] {
                  SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.BBSENDENDFLAG
                });

        for (SaleOrderViewVO viewvo : viewvos) {
          upmap.put(viewvo.getBody().getCsaleorderbid(), viewvo.getBody()
              .getBbsendendflag());
        }

      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }

    return upmap;
  }

}
