package nc.ui.so.mreturncondition.ref;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.ml.NCLangRes;
import nc.ui.scmf.dm.pub.DMBaseDocUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.mreturncondition.entity.ReturnConditionVO;
import nc.vo.so.mreturnreason.entity.ReturnReasonVO;

public class ReturnCondtionRefModel extends AbstractRefModel {

  public ReturnCondtionRefModel(String refNodeName) {
    this.setRefNodeName(refNodeName);
  }

  @Override
  public void setRefNodeName(String refNodeName) {
    this.m_strRefNodeName = refNodeName;
    this.setFieldCode(new String[] {
        ReturnConditionVO.VCONDITIONCODE, ReturnConditionVO.VCONDITIONNAME,
        ReturnConditionVO.VEXPRESSCODE, ReturnConditionVO.VEXPRESSDETAIL
    });

    this.setFieldName(new String[] {
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0115")/*�˻���������*/,
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0116")/*�˻���������*/,
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0117")/*�˻��������ʽ*/,
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0118")
        /*�˻�����˵��*/
    });
    this.setHiddenFieldCode(new String[] {
        ReturnConditionVO.PK_RETURNCNDTN
    });
    this.setPkFieldCode(ReturnConditionVO.PK_RETURNCNDTN);
    this.setRefCodeField(ReturnConditionVO.VCONDITIONCODE);
    this.setRefNameField(ReturnConditionVO.VCONDITIONNAME);

    this.setTableName("so_returncndtn");
    this.setRefTitle(NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0128")/*�˻�����*/);

    this.resetFieldName();
  }

  @Override
  protected String getEnvWherePart() {
    String pk_group = this.getPk_group();
    String pk_org = this.getPk_org();
    // zhangby5�޸� ԭ��scmpub�Ķ�����
    String visible =
        DMBaseDocUtils.getVisibleForRef(pk_group, pk_org, ReturnReasonVO.class,
            null);
    visible = visible.replace("pk_org", "so_returncndtn.pk_org");
    SqlBuilder sb = new SqlBuilder();
    sb.append("so_returncndtn.dr = 0 and ");
    sb.append(visible);
    return sb.toString();
  }
}
