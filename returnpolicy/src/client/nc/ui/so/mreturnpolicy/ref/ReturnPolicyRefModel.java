package nc.ui.so.mreturnpolicy.ref;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.AppUiContext;
import nc.ui.scmf.dm.pub.DMBaseDocUtils;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.util.TimeUtils;
import nc.vo.so.mreturnpolicy.entity.ReturnPolicyVO;

public class ReturnPolicyRefModel extends AbstractRefModel {

  public ReturnPolicyRefModel(String refNodeName) {
    this.setRefNodeName(refNodeName);
  }

  @Override
  public void setRefNodeName(String refNodeName) {
    this.m_strRefNodeName = refNodeName;
    this.setFieldCode(new String[] {
        ReturnPolicyVO.VPOLICYCODE, ReturnPolicyVO.VPOLICYNAME,
        ReturnPolicyVO.VEXPRESSNAME, ReturnPolicyVO.VPOLICYDETAIL
    });

    this.setFieldName(new String[] {
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006l-0000")/*�˻����߱���*/,
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006l-0001")/*�˻���������*/,
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006l-0002")/*�˻����߱��ʽ*/,
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006l-0003")
        /*�˻�����˵��*/
    });
    this.setHiddenFieldCode(new String[] {
        "pk_returnpolicy"
    });
    this.setPkFieldCode(ReturnPolicyVO.PK_RETURNPOLICY);
    this.setRefCodeField(ReturnPolicyVO.VPOLICYCODE);
    this.setRefNameField(ReturnPolicyVO.VPOLICYNAME);

    this.setTableName("so_returnpolicy");
    this.setRefTitle(NCLangRes.getInstance().getStrByID("4006006_0",
        "04006006l-0004")/*�˻�����*/);

    this.resetFieldName();
  }

  @Override
  protected String getEnvWherePart() {
    String pk_group = this.getPk_group();
    String pk_org = this.getPk_org();
    // zhangby5�޸� ԭ��scmpub�Ķ�����
    String visible =
        DMBaseDocUtils.getVisibleForRef(pk_group, pk_org, ReturnPolicyVO.class,
            null);
    visible = visible.replace("pk_org", "so_returnpolicy.pk_org");
    UFDate date = AppUiContext.getInstance().getBusiDate();
    UFDate startDate = TimeUtils.getStartDate(date);
    UFDate endDate = TimeUtils.getEndDate(date);
    SqlBuilder sb = new SqlBuilder();
    sb.append("so_returnpolicy.dr = 0 and so_returnpolicy."
        + ReturnPolicyVO.DSTARTDATE + "<='" + startDate + "'");
    sb.append(" and so_returnpolicy." + ReturnPolicyVO.DENDDATE + ">='"
        + endDate + "' and ");
    sb.append(visible);
    return sb.toString();
  }
}
