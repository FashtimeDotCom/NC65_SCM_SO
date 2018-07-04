package nc.bs.so.m30.rule.rewrite.m35;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m35.so.m30.IArsubToSaleorder;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SaleOrderVOUtil;
import nc.vo.so.m35.entity.ArsubInterfaceVO;

/**
 * @description
 * ���۶���ɾ�����д���۷��õ�
 * @scene
 * ���۶���ɾ����
 * @param 
 * ��
 * @since 6.0
 * @version 2010-12-10 ����12:12:57
 * @author ô��
 */
public class Rewrite35WhenDelete implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    SaleOrderVOUtil voutil = new SaleOrderVOUtil();
    // ���ó��VO����
    List<SaleOrderVO> arsubvolist = new ArrayList<SaleOrderVO>();
    // ��Ʒ�Ҹ�VO����
    List<SaleOrderVO> arsubcashvolist = new ArrayList<SaleOrderVO>();
    for (SaleOrderVO vo : vos) {
      String arsubtypeid = vo.getParentVO().getCarsubtypeid();
      if (nc.vo.pubapp.pattern.pub.PubAppTool.isNull(arsubtypeid)) {
        arsubvolist.add(vo);
      }
      else {
        arsubcashvolist.add(vo);
      }
    }
    if (arsubvolist.size() == 0 && arsubcashvolist.size() == 0) {
      return;
    }
    ArsubInterfaceVO[] arsubvo =
        voutil.changeToArsubInterfaceVO(arsubvolist
            .toArray(new SaleOrderVO[arsubvolist.size()]));
    ArsubInterfaceVO[] arsubcashvo =
        voutil.changeToArsubInterfaceVO(arsubcashvolist
            .toArray(new SaleOrderVO[arsubcashvolist.size()]));
    // ɾ����ֹ�ϵ��д���õ�
    OffsetTempVO tempvo = new OffsetTempVO();
    tempvo.setIsCancelOffset(true);
    if (arsubvolist.size() == 0 && arsubcashvolist.size() == 0) {
      return;
    }
    IArsubToSaleorder service =
        NCLocator.getInstance().lookup(IArsubToSaleorder.class);
    if (arsubvo.length != 0) {
      try {
        service.writeArsubRelation(arsubvo, tempvo);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }
    }
    if (arsubcashvo.length != 0) {
      try {
        service.writeArsubRelationForArsub(arsubcashvo, tempvo);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }
    }

  }
}
