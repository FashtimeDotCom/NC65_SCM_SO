package nc.pubimpl.so.m30.pfxx;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.so.m30.action.main.InsertSaleOrderAction;
import nc.impl.so.m30.action.main.UpdateSaleOrderAction;
import nc.pubimpl.so.pfxx.AbstractSOPfxxPlugin;
import nc.pubimpl.so.pfxx.check.BillFreeStatusCheckRule;
import nc.pubimpl.so.pfxx.check.MnyTaxCheckRule;
import nc.pubimpl.so.pfxx.check.WriteBackInfoCheckRule;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class M30PfxxPlugin extends AbstractSOPfxxPlugin {

  @Override
  public List<IRule<AggregatedValueObject>> getCheckers() {
    List<IRule<AggregatedValueObject>> rules =
        new ArrayList<IRule<AggregatedValueObject>>();
    // ����״̬��飬������̬�����Ե���
    rules.add(new BillFreeStatusCheckRule());
    // ��˰�ϼ�=��˰���+˰����ң�
    rules.add(new MnyTaxCheckRule(SaleOrderBVO.NTAX, SaleOrderBVO.NMNY,
        SaleOrderBVO.NTAXMNY));
    // �ۼ������ֶ�
    rules.add(new WriteBackInfoCheckRule(new String[] {
      SaleOrderBVO.NTOTALSENDNUM,// �ۼƷ���������
      SaleOrderBVO.NTOTALINVOICENUM,// �ۼƿ�Ʊ������
      SaleOrderBVO.NTOTALOUTNUM,// �ۼƳ���������
      SaleOrderBVO.NTOTALNOTOUTNUM,// �ۼ�Ӧ��δ����������
      SaleOrderBVO.NTOTALSIGNNUM,// �ۼ�ǩ��������
      SaleOrderBVO.NTRANSLOSSNUM,// �ۼ�;��������
      SaleOrderBVO.NTOTALRUSHNUM,// �ۼƳ���Գ�������
      SaleOrderBVO.NTOTALESTARNUM,// �ۼ��ݹ�Ӧ��������
      SaleOrderBVO.NTOTALARNUM,// �ۼ�ȷ��Ӧ��������
      SaleOrderBVO.NTOTALCOSTNUM,// �ۼƳɱ�����������
      SaleOrderBVO.NTOTALESTARMNY,// �ۼ��ݹ�Ӧ�ս��
      SaleOrderBVO.NTOTALARMNY,// �ۼ�ȷ��Ӧ�ս��
      SaleOrderBVO.NTOTALPAYMNY,// �ۼ��տ�������
      SaleOrderBVO.NORIGSUBMNY,// �ۼƳ�ֽ��
      SaleOrderBVO.NARRANGESCORNUM,// �ۼư���ί�ⶩ��������
      SaleOrderBVO.NARRANGEPOAPPNUM,// �ۼư����빺��������
      SaleOrderBVO.NARRANGETOORNUM,// �ۼư��ŵ�������������
      SaleOrderBVO.NARRANGETOAPPNUM,// �ۼư��ŵ�������������
      SaleOrderBVO.NARRANGEMONUM,// �ۼư�����������������
      SaleOrderBVO.NARRANGEPONUM,// �ۼư��Ųɹ�����������
      SaleOrderBVO.NTOTALPLONUM,// �ۼ����ɼƻ�����������
      SaleOrderBVO.NTOTALRETURNNUM,// �ۼ��˻�������
      SaleOrderBVO.NTOTALTRADENUM
    // �ۼƷ�����Ʒ������
        }));
    return rules;
  }

  @Override
  protected AggregatedValueObject insert(AggregatedValueObject vo) {

    SaleOrderVO[] insertvo = new SaleOrderVO[] {
      (SaleOrderVO) vo
    };
    InsertSaleOrderAction insertact = new InsertSaleOrderAction();
    SaleOrderVO[] retvos = insertact.insert(insertvo);
    if (null == retvos || retvos.length == 0) {
      return null;
    }
    return retvos[0];
  }

  @Override
  protected AggregatedValueObject update(AggregatedValueObject vo, String vopk) {

    SaleOrderVO[] updatevo = new SaleOrderVO[] {
      (SaleOrderVO) vo
    };
    BillQuery<SaleOrderVO> billquery =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] origvos = billquery.query(new String[] {
      vopk
    });
    BillConcurrentTool tool = new BillConcurrentTool();
    tool.lockBill(origvos);
    UpdateSaleOrderAction insertact = new UpdateSaleOrderAction();
    SaleOrderVO[] retvos = insertact.update(updatevo, origvos);
    if (null == retvos || retvos.length == 0) {
      return null;
    }
    return retvos[0];
  }

}
