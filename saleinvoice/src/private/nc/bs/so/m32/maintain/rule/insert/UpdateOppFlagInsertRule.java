package nc.bs.so.m32.maintain.rule.insert;

import nc.bs.so.m32.maintain.rule.util.UpdateSrcOppFlagUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;

/**
 * @description
 * ���۷�Ʊ����� <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�Գ����ɷ�Ʊ����������Դ��Ʊ�Գ��־�ֶ�
 * </ul>
 * <p>
 * @scene
 * �Գ����ɷ�Ʊ�����
 * @param
 * ��
 * @version ���汾�� 6.0
 * @since ��һ�汾�� 5.6
 * @author ��ӱ�
 * @time 2010-1-21 ����04:59:51
 */
public class UpdateOppFlagInsertRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    UpdateSrcOppFlagUtil updateOppUtil = new UpdateSrcOppFlagUtil();
    updateOppUtil.updateSrcOppFlag(vos, OpposeFlag.FINSH);
  }

}
