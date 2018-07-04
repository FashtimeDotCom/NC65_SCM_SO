package nc.ui.so.mreturnreason.ref;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.ml.NCLangRes;
import nc.ui.scmf.dm.pub.DMBaseDocUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.mreturnreason.entity.ReturnReasonVO;

/**
 * �˻�ԭ�����
 */
public class ReturnReasonRefModel extends AbstractRefModel {

  private static final long serialVersionUID = 1L;

  public ReturnReasonRefModel(String refNodeName) {
    this.setRefNodeName(refNodeName);
  }

  @Override
  public void setRefNodeName(String refNodeName) {
    this.m_strRefNodeName = refNodeName;
    this.setFieldCode(new String[] {
        ReturnReasonVO.VREASONCODE, ReturnReasonVO.VREASONNAME,
        ReturnReasonVO.FREASONTYPE, ReturnReasonVO.VRETURNMODE
    });
    // ��Ҫ�����Դ�������
    this.setFieldName(new String[] {
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0122")/*�˻�ԭ�����*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0123")/*�˻�ԭ������*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0065")/*�˻�ԭ������*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0124")/*Ĭ���˻����δ���ʽ*/
    });
    this.setHiddenFieldCode(new String[] {
        ReturnReasonVO.PK_RETURNREASON
    });
    this.setPkFieldCode(ReturnReasonVO.PK_RETURNREASON);
    this.setRefCodeField(ReturnReasonVO.VREASONCODE);
    this.setRefNameField(ReturnReasonVO.VREASONNAME);

    this.setTableName("so_returnreason");
    this.setRefTitle(NCLangRes.getInstance().getStrByID("4006006_0", "04006006l-0006")/*�˻�ԭ��*/); /*-=notranslate=-*/

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
    visible = visible.replace("pk_org", "so_returnreason.pk_org");
    SqlBuilder sb = new SqlBuilder();
    sb.append("so_returnreason.dr = 0 and ");
    sb.append(visible);
    return sb.toString();
  }
}
