package nc.ui.so.m4331.arrange.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.so.m30.pub.ISaleOrderForPub;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.billref.push.PushAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfServiceUtil;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.to.m5x.entity.BillItemVO;
import nc.vo.to.m5x.entity.BillVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ŵİ��Ź���ʵ��
 * </ul>
 * <p>
 * 
 * @version ���汾�� 6.0
 * @since
 * @author fengjb
 * @time 2010-5-27 ����01:55:45
 */
public class ArrangeAction extends PushAction {

  private static final long serialVersionUID = -4404042322034244013L;

  /**
   * ���෽����д
   */
  @Override
  public void intActionUI() {
    super.intActionUI();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_BILLPUSH);
  }

  /**
   * ���෽����д
   */
  @Override
  protected void afterVOChange(AggregatedValueObject[] srcVos,
      AggregatedValueObject[] destVos) {
    if (null == srcVos || srcVos.length == 0) {
      return;
    }
    List<String> sobids = new ArrayList<String>();
    StringBuffer errMsg = new StringBuffer();
    for (AggregatedValueObject srcvo : srcVos) {
      if (srcvo instanceof SaleOrderVO) {
    	CircularlyAccessibleValueObject[] bvos = srcvo.getChildrenVO();
        for (CircularlyAccessibleValueObject bvo : bvos) {
          sobids
              .add((String) bvo.getAttributeValue(SaleOrderBVO.CSALEORDERBID));
        }
      }
      else if (srcvo instanceof BillVO) {
        BillVO vo = (BillVO) srcvo;
        BillItemVO[] items = vo.getChildrenVO();
        for (BillItemVO item : items) {
          UFBoolean flag = item.getBsendendflag();
          if (null != flag && flag.booleanValue()) {
            errMsg.append(NCLangRes.getInstance().getStrByID("4006002_0",
                "04006002-0105", null, new String[] {
                  vo.getParentVO().getVbillcode(), item.getCrowno()
                })/*���ݺ�{0}�Ķ���������{1}�Ѿ������رգ����ܰ��š�*/);
            errMsg.append("\n");
          }
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
                    SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.BBSENDENDFLAG,SaleOrderHVO.VBILLCODE,
                    SaleOrderBVO.CROWNO
                  });

          for (SaleOrderViewVO viewvo : viewvos) {
        	UFBoolean flag = viewvo.getBody().getBbsendendflag();
            if (flag != null && flag.booleanValue()) {
              errMsg.append(NCLangRes.getInstance().getStrByID("4006002_0",
                  "04006002-0105", null, new String[] {
            		  viewvo.getHead().getVbillcode(), viewvo.getBody().getCrowno()
                  })/*���ݺ�{0}�Ķ���������{1}�Ѿ������رգ����ܰ��š�*/);
              errMsg.append("\n");
            }
          }

        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
      }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
    String pk_group =
        WorkbenchEnvironment.getInstance().getGroupVO().getPk_group();
    PfServiceUtil.processDestBillTranType(destVos,
        SOBillType.Delivery.getCode(), pk_group);
    //���հ��ֹ�ó������Ҫ��������ת����ʱ��͸ĳ�ʣ��ɷ�������������������������
    //add by ����ܲ 2017-11-15
    /*filterNumber(destVos);*/
    
  }

  /**
   * @author ����ܲ
   * ����������ʱ��ȥ�Ѿ�����������
   * <b>��ʽ��ȷ�ϣ�</b>
   */
private void filterNumber(AggregatedValueObject[] destVos) {
	// TODO �Զ����ɵķ������
	for (int i = 0; i < destVos.length; i++) {
		DeliveryVO deAggvo = (DeliveryVO)destVos[i];
		UFDouble Ntotalastnum = deAggvo.getParentVO().getNtotalastnum();
		//��ѯ�õ����Ѿ������˶��ٷ���
		UFDouble hasDone=queryForHasDone(deAggvo);
		//���ж���û������������
		UFDouble res=Ntotalastnum.sub(hasDone);
		//��VO��ֵ��Ҳ���Ǹ����渳ֵ
		deAggvo.getParentVO().setNtotalastnum(res);
	}
	
}

/** 
 * @Title: queryForHasDone 
 * @Description: TODO
 * @param deAggvo
 * @return
 * @return: UFDouble
 * @throws MetaDataException 
 */
private UFDouble queryForHasDone(DeliveryVO deAggvo)  {
	// TODO �Զ����ɵķ������
	//ͨ�������ҵ��������۶������˶��ٳ���
	String csrcid=deAggvo.getChildrenVO()[0].getCsrcid();
	String wherePart=" and csrcid ='"+csrcid+"'";
	Collection<DeliveryBVO> ret = null;
	try {
		ret=MDPersistenceService.lookupPersistenceQueryService().queryBillOfVOByCond(DeliveryBVO.class, wherePart, false);
	} catch (MetaDataException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	}
	
	if(ret==null){
		return UFDouble.ZERO_DBL;
	}
	DeliveryBVO [] res=ret.toArray(new DeliveryBVO[ret.size()]);
	UFDouble num=UFDouble.ZERO_DBL;
	for (int i = 0; i < res.length; i++) {
		DeliveryBVO bvo=res[i];
		num.add(bvo.getNastnum());
	}
	return num;
}
}
