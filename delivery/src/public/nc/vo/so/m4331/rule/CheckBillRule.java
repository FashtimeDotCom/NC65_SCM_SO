package nc.vo.so.m4331.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.qc.pub.util.QCSysParamUtil;
import nc.vo.so.m4331.entity.DeliveryBVO;

/**
 * ������������rule
 * 
 * @since 6.0
 * @version 2011-5-17 ����09:31:08
 * @author ף����
 */
public class CheckBillRule {
  // ������Խ��б���ķ���������vo
  private Map<String, DeliveryBVO> bvoMap;

  private DeliveryBVO[] bvos;

  private StringBuffer errMsg;

  // ���������Ƿ���Ա���
  private Map<String, UFBoolean> matMap;

  public CheckBillRule(DeliveryBVO[] vos) {
    if (null == vos) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006002_0", "04006002-0155")
      /*��ѡ�з����������н��б��졣*/);
      return;
    }

    this.matMap = new HashMap<String, UFBoolean>();
    this.bvos = vos;
    this.bvoMap = new HashMap<String, DeliveryBVO>();
    for (DeliveryBVO bvo : this.bvos) {
      this.bvoMap.put(bvo.getCdeliverybid(), bvo);
    }
    this.initMatMap();
  }

  /*
   * ���ݼ���ж� ֻ�и����еķ��������Խ��б���
   * �������ϵ�������֯ҳǩ�������˻���족����Ϊ����ʱ�����ܽ��б���
   */

  /**
   * �ж�ѡ�еı������ܲ���������
   * 
   * @param bvos
   */
  public void isCheckBill() {
    this.errMsg = new StringBuffer();
    for (DeliveryBVO bvo : this.bvos) {
      // ���ѡ�е����Ƿ��Ǹ�����
      this.checkOppose(bvo);
      // �������
      this.checkMaterial(bvo);
      // ��鱨���Ƿ����
      this.ischeckFinsh(bvo);
    }
    this.bvoMap.clear();
    if (this.errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(this.errMsg.toString());
    }
  }

  /*
   * �������ϵ��Ƿ�����ǹ��˳���Ҫ����Ͳ���Ҫ����ı���vo
   */
  private void checkMaterial(DeliveryBVO bvo) {
    if (!this.isHave(bvo)) {
      return;
    }
    String pk_material = bvo.getCmaterialvid();
    UFBoolean flag = this.matMap.get(pk_material);
    if (!flag.booleanValue()) {
      return;
    }
    this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0156", null, new String[] {
          bvo.getCrowno()
        })/*�������У�{0}������Ϊ������ϣ����ܱ��졣*/);
    this.errMsg.append("\n");
    this.bvoMap.remove(bvo.getCdeliverybid());
  }

  /*
   * ��鷢�������Ա������
   * ���˳������к͸����� ���浽map��
   */
  private void checkOppose(DeliveryBVO bvo) {
    if (!this.isHave(bvo)) {
      return;
    }
    UFDouble num = bvo.getNnum();
    // ����Ϊ�� ���Ա���
    if (num.compareTo(UFDouble.ZERO_DBL) < 0) {
      return;
    }
    this.bvoMap.remove(bvo.getCdeliverybid());
    this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0157", null, new String[] {
          bvo.getCrowno()
        })/*�������У�{0}�����в��ܱ��졣*/);
    this.errMsg.append("\n");
  }

  private void initMatMap() {
    String pk_sendorg = null;
    // �������ϰ汾
    Set<String> materialSet = new HashSet<String>();
    for (DeliveryBVO bvo : this.bvos) {
      materialSet.add(bvo.getCmaterialvid());
      // ���������֯
      pk_sendorg = bvo.getCsendstockorgid();
      if (null == pk_sendorg || "".equals(pk_sendorg)) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006002_0", "04006002-0158")/*���������巢�������֯Ϊ�գ����ܱ��졣*/);
      }
      // ��ȡ����INI01(������������ģ��)��ֵ
      if (QCSysParamUtil.getINI01(pk_sendorg) == UFBoolean.FALSE) {
        ExceptionUtils
            .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006002_0", "04006002-0175")/*������INI01  ������������ģ�顿Ϊ��,�����н��б��졣*/);
      }
    }
    String[] pk_materials = new String[materialSet.size()];
    pk_materials = materialSet.toArray(pk_materials);
    // ��ѯ���������֯�µ����ϵ�������� ΪN�Ŀ��Ա���
    Map<String, MaterialStockVO> materialMap =
        MaterialPubService.queryMaterialStockInfo(pk_materials, pk_sendorg,
            new String[] {
              MaterialStockVO.ISRETFREEOFCHK, MaterialStockVO.PK_MATERIAL
            });
    if (materialMap.size() == 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006002_0", "04006002-0159")/*�����е�����û�з��䵽��Ӧ�ķ��������֯��*/);
    }
    for (DeliveryBVO bvo : this.bvos) {
      String pk_material = bvo.getCmaterialvid();
      UFBoolean checkfreeflag =
          materialMap.get(pk_material).getIsretfreeofchk();
      if (null == checkfreeflag || !checkfreeflag.booleanValue()) {
        this.matMap.put(pk_material, UFBoolean.FALSE);
        continue;
      }
      this.matMap.put(pk_material, UFBoolean.TRUE);
    }
  }

  /*
   * ��鱨���Ƿ���� ����Ƿ��ʼ����N,�Ƿ񱨼�ΪY�����ܽ��б���
   */
  private void ischeckFinsh(DeliveryBVO bvo) {
    if (!this.isHave(bvo)) {
      return;
    }
    UFBoolean checkflag = bvo.getBcheckflag();
    if (null == checkflag) {
      checkflag = UFBoolean.FALSE;
    }
    UFBoolean qualityflag = bvo.getBqualityflag();
    if (null == qualityflag) {
      qualityflag = UFBoolean.FALSE;
    }
    if (checkflag.booleanValue() && !qualityflag.booleanValue()) {
      this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006002_0", "04006002-0160", null, new String[] {
            bvo.getCrowno()
          })/*��������:{0}��û���ʼ�����������ٴα��졣*/);
      this.errMsg.append("\n");
      this.bvoMap.remove(bvo.getCdeliverybid());
    }
  }

  private boolean isHave(DeliveryBVO bvo) {
    String bid = bvo.getCdeliverybid();
    if (!this.bvoMap.containsKey(bid)) {
      return false;
    }
    return true;
  }
}
