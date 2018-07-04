package nc.ui.so.pub.query.refregion;

import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.refedit.IFieldValueElementEditorCreator;
import nc.ui.querytemplate.meta.FilterMeta;
import nc.ui.querytemplate.valueeditor.IFieldValueElementEditor;
import nc.ui.scmf.ic.batchcode.ref.ScmBatchQueryEditor;
import nc.ui.scmf.ic.batchcode.ref.ScmBatchRef;
import nc.vo.scmf.ic.batchcode.entity.BatchRefInfo;

public class QBatchCodeFilter {
  /**
   * �����������������β��յĹ���
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.36
   * @author liylr
   * @time 2015-05-13 ����10:06:35
   */
  public void filter(QueryConditionDLGDelegator delegator, String vbatchcode) {
    delegator.setFieldValueElementEditor(null , vbatchcode,
        new IFieldValueElementEditorCreator() {
          @Override
          public IFieldValueElementEditor createFVEditor(FilterMeta meta) {
            // ÿ�δ�����ʵ���������ѡ����ѡ�����κ�ԭ�����β��ճ���
            BatchRefInfo config = new BatchRefInfo(null, true, true);
            ScmBatchRef batchRef = new ScmBatchRef(config);

            IFieldValueElementEditor refEditor =
                new ScmBatchQueryEditor(batchRef);
            return refEditor;
          }
        });
  }
}
