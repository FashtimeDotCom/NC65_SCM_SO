package nc.pubimpl.so.m30.invp;

import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.pubitf.invp.plan.IReqResultForInvp;
import nc.pubitf.invp.plan.ReqResultForInvpVO;
import nc.pubitf.so.m30.invp.ISaleOrderForInvp;

/**
 * ���۶����ṩ���ƻ�ȡ���ӿڵ�ʵ��
 * 
 * @since 6.3
 * @version 2012-11-08 08:50:43
 * @author zhangkai4
 */
public class SaleOrderForInvpImpl implements ISaleOrderForInvp {

  @Override
  public IReqResultForInvp getVO(String sendStockOrg, String tempName,
      boolean needRed) {
    ReqResultForInvpVO stockVO = new ReqResultForInvpVO();
    // from��where����
    this.setFromWhere(stockVO, sendStockOrg, tempName, needRed);
    // �ֶ�������
    this.setFiled(stockVO);
    return stockVO;
  }

  /**
   * �����ֶ���������.�ֶ�����������ӵ�VO��
   * 
   * @param stockVO
   */
  private void setFiled(ReqResultForInvpVO stockVO) {
    // �������͵ı���ֵ
    stockVO.setReqTypecode(SOBillType.Order.getCode());
    // �������͵ı�����
    stockVO.setReqTypeid(SOBillType.Order.getCode());
    // ���ݽ������͵ı���ֵ
    stockVO
        .setReqTrantypecode(this.getHeadFullPath(SaleOrderHVO.VTRANTYPECODE));
    // ���ݽ������͵�����ֵ
    stockVO.setReqTrantypeid(this.getHeadFullPath(SaleOrderHVO.CTRANTYPEID));
    // ��ͷ����
    stockVO.setReqid(this.getHeadFullPath(SaleOrderHVO.CSALEORDERID));
    // ��������
    stockVO.setReqbid(this.getBodyFullPath(SaleOrderBVO.CSALEORDERBID));
    // �����к�
    stockVO.setReqRowno(this.getBodyFullPath(SaleOrderBVO.CROWNO));
    // �����֯
    stockVO.setReqOrgid(this.getBodyFullPath(SaleOrderBVO.CSENDSTOCKORGID));
    // �����֯�汾
    stockVO.setReqOrgvid(this.getBodyFullPath(SaleOrderBVO.CSENDSTOCKORGVID));
    // ��������
    stockVO.setMaterialid(this.getBodyFullPath(SaleOrderBVO.CMATERIALID));
    // ���ϰ汾����
    stockVO.setMaterialvid(this.getBodyFullPath(SaleOrderBVO.CMATERIALVID));
    // ��������λ
    stockVO.setCunitid(this.getBodyFullPath(SaleOrderBVO.CUNITID));
    // ���ݺ�
    stockVO.setReqCode(this.getHeadFullPath(SaleOrderHVO.VBILLCODE));
    // ��������(�ƻ���������)
    stockVO.setReqDate(this.getBodyFullPath(SaleOrderBVO.DSENDDATE));
    // ��������
    stockVO.setBillDate(this.getBodyFullPath(SaleOrderBVO.DBILLDATE));
    // ���������� = ������-�ۼƳ�������
    StringBuilder reqNum = new StringBuilder();
    String num = this.getBodyFullPath(SaleOrderBVO.NNUM);
    reqNum.append(" isnull( " + num + ", 0)");
    reqNum.append(" - ");
    String totalNum = this.getExeFullPath("ntotaloutnum");
    reqNum.append(" isnull( " + totalNum + ", 0)");
    stockVO.setNnum(reqNum.toString());
    // Ԥ������
    stockVO.setNallocnum(this.getExeFullPath(SaleOrderBVO.NREQRSNUM));// Ԥ��������
  }

  /**
   * ���۶��������ֶε�ȫ·��
   * 
   * @param fieldName �ֶ���
   * @return ����.�ֶ���
   */
  private String getHeadFullPath(String fieldName) {
    return SOTable.SALEORDER.getName() + SOConstant.POINT + fieldName;
  }

  /**
   * ���۶����ӱ��ֶε�ȫ·��
   * 
   * @param fieldName �ֶ���
   * @return ����.�ֶ���
   */
  private String getBodyFullPath(String fieldName) {
    return SOTable.SALEORDER_B.getName() + SOConstant.POINT + fieldName;
  }

  /**
   * ���۶���ִ�б��ֶε�ȫ·��
   * 
   * @param fieldName �ֶ���
   * @return ����.�ֶ���
   */
  private String getExeFullPath(String fieldName) {
    return SOTable.SALEORDER_EXE.getName() + SOConstant.POINT + fieldName;
  }

  /**
   * ����ȡ������sql��from��where���֣�����䵽VO��
   * 
   * @param stockVO Ҫ����VO
   * @param sendStockOrg �����֯
   * @param tempName ��ʱ��pk_material ����OID,dstart ��ʼʱ��,dend ����ʱ�䣩
   * @param needRed �Ƿ�������ֵ���
   */
  private void setFromWhere(ReqResultForInvpVO stockVO, String sendStockOrg,
      String tempName, boolean needRed) {

    /* from���� */
    SqlBuilder fromPart = new SqlBuilder();
    // ���۶���������۶����ӱ�
    fromPart
        .append(" so_saleorder so_saleorder inner join so_saleorder_b so_saleorder_b ");
    fromPart
        .append(" on so_saleorder.csaleorderid = so_saleorder_b.csaleorderid ");
    // ���۶���ִ�б�
    fromPart.append(" inner join so_saleorder_exe so_saleorder_exe ");
    fromPart
        .append(" on so_saleorder_b.csaleorderbid = so_saleorder_exe.csaleorderbid ");
    fromPart
        .append(" inner join  so_m30trantype so_m30trantype on so_m30trantype.ctrantypeid=so_saleorder.ctrantypeid  ");
    // ��ʱ��
    fromPart.append(" inner join " + tempName + " " + tempName);
    fromPart.append(" on " + tempName
        + ".pk_material = so_saleorder_b.cmaterialid");
    stockVO.setFrom(fromPart.toString());
    /* from���ֽ��� */

    /* where���� */
    SqlBuilder wherePart = new SqlBuilder();
    // ����ʱ��
    wherePart.append("so_saleorder_b.dsenddate between " + tempName
        + ".dstart " + " and " + tempName + ".dend");
    // ���������֯
    wherePart.append(" and ");
    wherePart.append("so_saleorder_b.csendstockorgid", sendStockOrg);
    // δɾ��
    wherePart
        .append(" and so_saleorder.dr = 0 and so_saleorder_b.dr = 0 and so_saleorder_exe.dr = 0");
    // ����̬������̬
    wherePart.append(" and ");
    int[] billStatus = new int[] {
      BillStatus.FREE.getIntValue(), BillStatus.AUDIT.getIntValue()
    };
    wherePart.append("so_saleorder_b.frowstatus", billStatus);
    // ����ر�
    wherePart.append(" and isnull(so_saleorder_b.bboutendflag,'N') = 'N' ");
    if (!needRed) {
      wherePart.append(" and so_saleorder_b.nnum > 0");
    }
    // ������
    String pk_group = AppContext.getInstance().getPkGroup();
    wherePart.append(" and ");
    wherePart.append("so_saleorder.pk_group", pk_group);
    wherePart.append(" and ");
    wherePart.append("so_saleorder_b.pk_group", pk_group);
    // ��ֱ��
    wherePart
        .append(" and so_m30trantype.fdirecttype",
            nc.vo.so.m30trantype.enumeration.DirectType.DIRECTTRAN_NO
                .getIntValue());
    stockVO.setWhere(wherePart.toString());
    /* where���ֽ��� */
  }
}
