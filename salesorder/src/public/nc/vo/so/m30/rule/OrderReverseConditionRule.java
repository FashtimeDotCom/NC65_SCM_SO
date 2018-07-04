package nc.vo.so.m30.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.pubitf.so.m4331.so.m30.IDeliveryFor30;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;

/**
 * ���۶���Ԥ�������ж�
 * 
 * @since 6.0
 * @version 2011-5-14 ����01:58:05
 * @author ף����
 */
public class OrderReverseConditionRule {
  // �������Ԥ���ķ�����
  private Map<String, SaleOrderBVO> bvoMap;

  private SaleOrderBVO[] bvos;

  // �������ε����Ƿ�����Ԥ��
  private Map<String, UFBoolean> destMap;

  private StringBuffer errMsg;

  // ���������Ƿ��ҲԤ����ʶ
  private Map<String, UFBoolean> matMap;

  public OrderReverseConditionRule(SaleOrderBVO[] vos) {
    if (null == vos) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0227")/*@res "��ѡ�б����н���Ԥ����"*/);
    }
    this.bvos = vos;
    this.matMap = new HashMap<String, UFBoolean>();
    this.bvoMap = new HashMap<String, SaleOrderBVO>();
    this.destMap = new HashMap<String, UFBoolean>();
    for (SaleOrderBVO bvo : this.bvos) {
      this.bvoMap.put(bvo.getCsaleorderbid(), bvo);
    }
    this.errMsg = new StringBuffer();
    this.initMatMap();
    this.initDestMap();
  }

  /**
   * �ж��Ƿ������Ԥ��
   * 
   * @param bvos
   * @return
   */
  public void checkReverse() {
    for (SaleOrderBVO bvo : this.bvos) {
      this.checkRowClose(bvo);
      this.checkbvo(bvo);
      this.checkNum(bvo);
      // ����Ƿ��Ԥ������ q q
      this.checkIsReserveMaterial(bvo);
      this.checkIsReserve4331(bvo);
    }
    if (this.errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(this.errMsg.toString());
    }
  }

  private void checkbvo(SaleOrderBVO bvo) {
    if (!this.bvoMap.containsKey(bvo.getCsaleorderbid())) {
      return;
    }
    // ���ת���ۡ���־�����۶�����֧��Ԥ��
    if (!PubAppTool.isNull(bvo.getVsrctype())
        && ICBillType.BorrowOut.getCode().equals(bvo.getVsrctype())) {
      this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0", "04006011-0356", null, new String[]{bvo.getCrowno()})/*���۶����У�+{0}���ת���۵����۶�����֧��Ԥ��.*/);
      this.bvoMap.remove(bvo.getCsaleorderbid());
    }
  }

  /**
   * ������εķ������Ƿ�����Ԥ��
   * 
   * @param bvo
   */
  private void checkIsReserve4331(SaleOrderBVO bvo) {
    String bid = bvo.getCsaleorderbid();
    UFBoolean isReverse = this.destMap.get(bid);
    if (isReverse.booleanValue()) {
      this.bvoMap.remove(bvo.getCsaleorderbid());
      this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0", "04006011-0357", null, new String[]{bvo.getCrowno()})/*���۶����У�{0}�����ε����Ѿ�����Ԥ��������Ԥ����\n*/);
    }
  }

  /*
   * ��������Ƿ���Ԥ������
   * @param vo
   * @throws BusinessException
   */
  private void checkIsReserveMaterial(SaleOrderBVO bvo) {
    String bid = bvo.getCsaleorderbid();
    if (!this.bvoMap.containsKey(bid)) {
      return;
    }
    String pk_material = bvo.getCmaterialvid();
    UFBoolean remain = this.matMap.get(pk_material);
    if (!remain.booleanValue()) {
      this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0", "04006011-0358", null, new String[]{bvo.getCrowno()})/*���۶����У�{0}�����ϲ��ǿ�Ԥ�����ϣ�����Ԥ����\n*/);
      this.bvoMap.remove(bvo.getCsaleorderbid());
    }
  }

  private void checkNum(SaleOrderBVO bvo) {
    if (!this.bvoMap.containsKey(bvo.getCsaleorderbid())) {
      return;
    }
    UFDouble nnum = bvo.getNnum();
    UFDouble outNum = bvo.getNtotaloutnum();
    UFDouble reqNum = bvo.getNreqrsnum();
    UFDouble value = MathTool.add(outNum, reqNum);
    if (MathTool.compareTo(nnum, value) <= 0) {
      this.errMsg
          .append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0", "04006011-0359", null, new String[]{bvo.getCrowno()})/*�����У�{0}����С���ۼƳ���������Ԥ������֮�ͣ�����Ԥ����\n*/);
      this.bvoMap.remove(bvo.getCsaleorderbid());
    }
  }

  /**
   * ������Ƿ�ر�
   * 
   * @param bvos
   */
  private void checkRowClose(SaleOrderBVO bvo) {
    if (!this.bvoMap.containsKey(bvo.getCsaleorderbid())) {
      return;
    }
    UFBoolean flag = bvo.getBboutendflag();
    if (null == flag || !flag.booleanValue()) {
      return;
    }
    this.bvoMap.remove(bvo.getCsaleorderbid());
    this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0", "04006011-0360", null, new String[]{bvo.getCrowno()})/*���۶�����{0}�Ѿ��йرգ�������Ԥ����*/);
    this.errMsg.append("\n");
  }

  /*
   * ������εķ������Ƿ�����Ԥ��
   */
  private void initDestMap() {
    // �������۶�������id
    Set<String> idSet = new HashSet<String>();
    for (SaleOrderBVO bvo : this.bvos) {
      idSet.add(bvo.getCsaleorderbid());
    }
    String[] ids = new String[idSet.size()];
    IDeliveryFor30 service =
        NCLocator.getInstance().lookup(IDeliveryFor30.class);
    try {
      this.destMap = service.queryReverseFlag(idSet.toArray(ids));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void initMatMap() {
    Set<String> tempSet = new HashSet<String>();
    for (SaleOrderBVO bvo : this.bvos) {
      String pk_material = bvo.getCmaterialvid();
      String csendstockorgid = bvo.getCsendstockorgid();
      String key = pk_material + csendstockorgid;
      if (tempSet.size() != 0 && tempSet.contains(key)) {
        continue;
      }
      tempSet.add(key);
      // ��������id ���������֯��ѯ������Ϣ
      MaterialStockVO[] stockvo =
          MaterialPubService.queryMaterialStockInfoByPks(new String[] {
            pk_material
          }, csendstockorgid, new String[] {
            MaterialStockVO.REMAIN
          });
      if (null == stockvo) {
        this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0", "04006011-0361", null, new String[]{bvo.getCrowno()})/*���������У�{0}�����ϲ����ڸñ����еķ��������֯������Ԥ����*/);
        this.errMsg.append("\n");
        this.bvoMap.remove(bvo.getCsaleorderbid());
        continue;
      }
      UFBoolean remain = stockvo[0].getRemain();
      if (null == remain || !remain.booleanValue()) {
        this.matMap.put(pk_material, UFBoolean.FALSE);
        continue;
      }
      this.matMap.put(pk_material, UFBoolean.TRUE);
    }
    tempSet.clear();
  }
}
