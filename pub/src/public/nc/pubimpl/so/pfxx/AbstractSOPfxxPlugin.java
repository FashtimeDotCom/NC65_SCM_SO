package nc.pubimpl.so.pfxx;

import java.util.List;

import nc.vo.pfxx.auxiliary.AggxsysregisterVO;
import nc.vo.pfxx.util.PfxxPluginUtils;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.pub.PubAppTool;

import nc.bs.pfxx.ISwapContext;
import nc.bs.pfxx.plugin.AbstractPfxxPlugin;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * �ⲿƽ̨�������۹���������
 * 
 * @since 6.0
 * @version 2012-2-21 ����03:31:13
 * @author ô��
 */
public abstract class AbstractSOPfxxPlugin extends AbstractPfxxPlugin {

  /**
   * �ⲿ����У��������
   */
  private List<IRule<AggregatedValueObject>> checkers;

  public List<IRule<AggregatedValueObject>> getCheckers() {
    return this.checkers;
  }

  /**
   * ����XMLת��������VO����NCϵͳ��ҵ����ʵ�ִ˷������ɡ�<br>
   * ��ע�⣬ҵ�񷽷���У��һ��Ҫ���
   * 
   * @param vo
   *          ת�����vo���ݣ���NCϵͳ�п���ΪValueObject,SuperVO,AggregatedValueObject,
   *          IExAggVO�ȡ�
   * @param swapContext
   *          ���ֽ�����������֯�����ܷ������ͷ������׵ȵ�
   * @param aggxsysvo
   *          ������Ϣvo
   * @return �����󷵻ص�vo
   * @throws BusinessException
   */
  @Override
  protected Object processBill(Object vo, ISwapContext swapContext,
      AggxsysregisterVO aggxsysvo) throws BusinessException {

    // 1.�õ�ת�����VO����,ȡ�����򵼵�һ��ע���VO��Ϣ
    AggregatedValueObject resvo = (AggregatedValueObject) vo;

    // 2.��ѯ�˵����Ƿ��Ѿ��������������������������ʹ����һ����ο�����˵��javadoc
    String vopk =
        PfxxPluginUtils.queryBillPKBeforeSaveOrUpdate(
            swapContext.getBilltype(), swapContext.getDocID());

    // 3. ������������и�����Ϣ��aggxsysvoΪ�û����õľ��帨����Ϣ

    // 4.����˵���û�е��������ô׼�������µ��ݣ����浥��ǰ����б�Ҫ�����ݼ�飬��������ȷ��ҵ���쳣...
    AggregatedValueObject returnVO = null;

    // �������ݼ���߼�
    this.checkBill(resvo);

    if (PubAppTool.isNull(vopk)) {
      resvo.getParentVO().setStatus(VOStatus.NEW);
      for (CircularlyAccessibleValueObject bvo : resvo.getChildrenVO()) {
        bvo.setStatus(VOStatus.NEW);
      }
      returnVO = this.insert(resvo);
    }
    else {
      // 5.����˵����Ѿ�������������PfxxPluginUtils.checkBillCanBeUpdate(UfinterfaceVO
      // swapContext)��鵥���Ƿ��������
      // ������������,�˷������׳�ҵ���쳣
      // PfxxPluginUtils.checkBillCanBeUpdate(swapContext);
      returnVO = this.update(resvo, vopk);
    }
    vopk = returnVO.getParentVO().getPrimaryKey();
    // 6.���ϣ�����ݽ������Ը��£���������нӿڲ����ĵ���ˮ��������PK�Ķ��չ�ϵ
    PfxxPluginUtils.addDocIDVsPKContrast(swapContext.getBilltype(),
        swapContext.getDocID(), vopk);

    // 7.׼������ֵ,�˺����ķ���ֵ�����ջ����ַ�������ʽ���ظ���ϵͳ��
    // ������ͨ���ݿ��Է���NCϵͳ���ɵ�PKֵ������ƾ֤���ܷ���ƾ֤�ţ������ӵ��ݶ���
    // ���ڲ�ѯ���Ҫ�󷵻�org.w3c.dom.Node[]���� ����org.w3c.dom.Node
    return vopk;
  }

  /**
   * �������������������Լ�����
   * 
   * @param vo
   * @return ������vo
   */
  protected abstract AggregatedValueObject insert(AggregatedValueObject vo);

  /**
   * ���²��������������Լ�����
   * 
   * @param vo
   * @return ���º��vo
   */
  protected abstract AggregatedValueObject update(AggregatedValueObject vo,
      String vopk);

  /**
   * ���ݼ��
   * 
   * @param vo
   */
  private void checkBill(AggregatedValueObject vo) {
    if (this.getCheckers() != null) {
      for (IRule<AggregatedValueObject> checker : this.getCheckers()) {
        checker.process(new AggregatedValueObject[] {
          vo
        });
      }
    }
  }

}
