package nc.bs.so.m32.maintain.rule.delete;

import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;

import nc.bs.so.m32.maintain.rule.util.UpdateSrcOppFlagUtil;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * �Գ����ɷ�Ʊɾ��ʱ������Դ��Ʊ�Գ��־
 * @scene
 * ���۶Գ����ɷ�Ʊɾ�������
 * @param
 * ��
 * @since 6.3
 * @version 2012-12-21 ����09:05:15
 * @author yaogj
 */
public class UpdateOppFlagDeleteRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    UpdateSrcOppFlagUtil updateOppUtil = new UpdateSrcOppFlagUtil();
    updateOppUtil.updateSrcOppFlag(vos, OpposeFlag.NORMAL);

  }

}
