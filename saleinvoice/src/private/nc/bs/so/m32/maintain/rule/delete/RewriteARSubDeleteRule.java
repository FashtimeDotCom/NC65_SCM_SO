package nc.bs.so.m32.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;
import nc.vo.so.m32.util.SaleInvoiceVOUtil;
import nc.vo.so.m35.entity.ArsubInterfaceVO;

/**
 * @description
 * ���۷�Ʊɾ��������ɾ����д���۷��õ�
 * @scene
 * ���۷�Ʊɾ�������
 * @param
 * ��
 * @since 6.0
 * @version 2010-12-10 ����01:01:28
 * @author ô��
 */
public class RewriteARSubDeleteRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    SaleInvoiceVOUtil voutil = new SaleInvoiceVOUtil();
    ArsubInterfaceVO[] arsubvo = voutil.changeToArsubInterfaceVO(vos);
    // ɾ���ϲ���Ʊ��ϵ��д���õ�

    OffsetTempVO tempvo = new OffsetTempVO();
    tempvo.setIsCancelOffset(true);

    try {
      RemoteFormSOUtil.writeArsubRelation(arsubvo, tempvo);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }

  }

}
