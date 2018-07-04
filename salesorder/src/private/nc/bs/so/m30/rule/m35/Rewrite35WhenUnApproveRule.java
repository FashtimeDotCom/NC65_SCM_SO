package nc.bs.so.m30.rule.m35;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m35.so.m30.IArsubToSaleorder;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SaleOrderVOUtil;
import nc.vo.so.m35.entity.ArsubInterfaceVO;

/**
 * 
 * @description
 * ���۶���ȡ������ʱɾ����������Ʒ�Ҹ����õ�
 * @scene
 * ����ʱɾ����������Ʒ�Ҹ����õ�
 * @param
 * ��
 *
 * @since 6.5
 * @version 2013-12-25 ����11:28:32
 * @author ����
 */
public class Rewrite35WhenUnApproveRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    SaleOrderVOUtil voutil = new SaleOrderVOUtil();

    // ��Ʒ�Ҹ�VO����
    List<SaleOrderVO> arsubcashvolist = new ArrayList<SaleOrderVO>();
    for (SaleOrderVO vo : vos) {
      String arsubtypeid = vo.getParentVO().getCarsubtypeid();
      if (nc.vo.pubapp.pattern.pub.PubAppTool.isNull(arsubtypeid)) {
        continue;
      }
      arsubcashvolist.add(vo);
    }
    if (arsubcashvolist.size() == 0) {
      return;
    }
    if (!SysInitGroupQuery.isMeEnabled()) {
		ExceptionUtils
				.wrappBusinessException(NCLangResOnserver.getInstance()
						.getStrByID("4006011_0", "04006011-0519")/*������Ӫ������ģ�飡*/);
    }
    ArsubInterfaceVO[] arsubcashvo =
        voutil.changeToArsubInterfaceVO(arsubcashvolist
            .toArray(new SaleOrderVO[arsubcashvolist.size()]));
    // ɾ����ֹ�ϵ��д���õ�
    OffsetTempVO tempvo = new OffsetTempVO();
    tempvo.setIsCancelOffset(true);
    if (arsubcashvolist.size() == 0) {
      return;
    }
    IArsubToSaleorder service =
        NCLocator.getInstance().lookup(IArsubToSaleorder.class);

    try {
      service.writeArsubRelationForArsub(arsubcashvo, tempvo);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
  }

}
