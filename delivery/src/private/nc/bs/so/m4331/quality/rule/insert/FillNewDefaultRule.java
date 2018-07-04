package nc.bs.so.m4331.quality.rule.insert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.m4331.pub.DeliveryVOCalculator;
import nc.vo.so.m4331.pub.DeliveryVoUtil;

/**
 * @description
 * ���۷������ʼ���Ϣ����ǰ���Ĭ��ֵ
 * @scene
 * ���۷������ʼ���Ϣ����ǰ
 * @param
 * ��
 */
public class FillNewDefaultRule {

  /**
   * ���ʼ�vo����Ĭ��ֵ
   * 
   * @param vos
   */
  public void setData(DeliveryCheckVO[] vos, DeliveryViewVO[] views) {
    Map<String, DeliveryViewVO> dataMap = this.getDataMap(views);
    this.fillDefaultData(dataMap, vos);
  }

  /*
   * ���е��۽����㡣
   */
  private void calculator(DeliveryCheckVO[] vos) {
    // ���ݵ������������
    int nnumint = 0;
    // ���ݽ���㵥�۵�����
    int moneyint = 0;
    for (DeliveryCheckVO vo : vos) {
      if (vo.getNorigtaxmny() != null
          && !vo.getNorigtaxmny().equals(UFDouble.ZERO_DBL)) {
        moneyint++;
      }
      else {
        nnumint++;
      }
    }
    DeliveryCheckVO[] nnumvos = new DeliveryCheckVO[nnumint];
    DeliveryCheckVO[] moneyvos = new DeliveryCheckVO[moneyint];
    int m = 0;
    int n = 0;
    for (DeliveryCheckVO vo : vos) {
      if (vo.getNorigtaxmny() != null
          && !vo.getNorigtaxmny().equals(UFDouble.ZERO_DBL)) {
        moneyvos[m] = vo;
        m++;
      }
      else {
        nnumvos[n] = vo;
        n++;
      }
    }
    if (nnumint > 0) {
      int[] rows = new int[nnumvos.length];
      for (int i = 0; i < nnumvos.length; i++) {
        rows[i] = i;
      }
      DeliveryVOCalculator cal = new DeliveryVOCalculator(nnumvos);
      cal.calculate(rows, DeliveryCheckVO.NNUM);
    }
    if (moneyint > 0) {
      int[] rows = new int[moneyvos.length];
      for (int i = 0; i < moneyvos.length; i++) {
        rows[i] = i;
      }
      DeliveryVOCalculator cal = new DeliveryVOCalculator(moneyvos);
      cal.calculate(rows, DeliveryCheckVO.NORIGTAXMNY);
    }
  }

  /*
   * ���ݷ���������vo����ʼ쵥��Ӧ������ 
   * @param bvos
   * @param vos
   */
  private void fillDefaultData(Map<String, DeliveryViewVO> dataMap,
      DeliveryCheckVO[] vos) {
    VORowNoUtils.setVOsRowNoByRule(vos, DeliveryCheckVO.CROWNO);
    List<DeliveryCheckVO> needcalvos = new ArrayList<DeliveryCheckVO>();
    for (DeliveryCheckVO checkvo : vos) {
      String bid = checkvo.getCdeliverybid();
      DeliveryViewVO view = dataMap.get(bid);
      // ���䵥������
      checkvo.setDbilldate(view.getItem().getDbilldate());
      // ���������Ϣ
      this.setMaterial(checkvo, view);
      // ����־
      this.setFlag(checkvo, view);
      // ����ʼ���������������ͬ ����Ҫ��ֵ��� �ɽ��㵥�� ����ᵼ���ʼ��кͷ����еļ�˰�ϼƲ�ͬ
      if (MathTool.absCompareTo(checkvo.getNnum(), view.getItem().getNnum()) == 0) {
        this.setMoney(checkvo, view);
        this.setAllOther(checkvo, view);
      }
      else {
        needcalvos.add(checkvo);
      }
      // ����ԭ�Ҽ۸�
      this.setOrigPrice(checkvo, view);
      // ���ñ��Ҽ۸�
      this.setPrice(checkvo, view);
      // ������
      this.setRate(checkvo, view);
      // ����������
      this.setFree(checkvo, view);
      // ���������������Ϣ
      this.setOther(checkvo, view);
    }
    // ���ݱ�Ϊ��ֵ����д������ֵ����Ҫ����ת��
    this.oppose(vos);
    if (needcalvos.size() > 0) {
      // ���۽�����
      DeliveryCheckVO[] calvos = new DeliveryCheckVO[needcalvos.size()];
      needcalvos.toArray(calvos);
      this.calculator(calvos);
      // ����β��
      this.adjustMny(calvos, dataMap);
    }

  }

  private void adjustMny(DeliveryCheckVO[] calvos,
      Map<String, DeliveryViewVO> dataMap) {

    Map<String, UFDouble> maptotalnum = new HashMap<String, UFDouble>();
    // ���ܵ�ԭ�Ҽ�˰�ϼơ���˰��˰��
    Map<String, UFDouble[]> maptotalorigmny = new HashMap<String, UFDouble[]>();
    // ���ܵı��Ҽ�˰�ϼơ���˰��˰��
    Map<String, UFDouble[]> maptotalmny = new HashMap<String, UFDouble[]>();

    for (DeliveryCheckVO checkvo : calvos) {
      String bid = checkvo.getCdeliverybid();
      DeliveryViewVO view = dataMap.get(bid);

      UFDouble curtotal = checkvo.getNnum();
      if (maptotalnum.containsKey(bid)) {
        curtotal = MathTool.add(curtotal, maptotalnum.get(bid));
      }
      maptotalnum.put(bid, curtotal);

      if (MathTool.absCompareTo(curtotal, view.getItem().getNnum()) == 0
          && maptotalorigmny.containsKey(bid)) {
        // ԭ��
        UFDouble vieworigtaxmny = view.getItem().getNorigtaxmny();
        UFDouble vieworigmny = view.getItem().getNorigmny();

        UFDouble[] totalorigmny = maptotalorigmny.get(bid);
        checkvo.setNorigtaxmny(MathTool.sub(vieworigtaxmny, totalorigmny[0]));
        checkvo.setNorigmny(MathTool.sub(vieworigmny, totalorigmny[1]));
        // ����
        UFDouble viewtaxmny = view.getItem().getNtaxmny();
        UFDouble viewmny = view.getItem().getNmny();
        UFDouble viewtax = view.getItem().getNtax();

        UFDouble[] totalmny = maptotalmny.get(bid);
        checkvo.setNtaxmny(MathTool.sub(viewtaxmny, totalmny[0]));
        checkvo.setNmny(MathTool.sub(viewmny, totalmny[1]));
        checkvo.setNtax(MathTool.sub(viewtax, totalmny[2]));
      }
      else {
        // ԭ��
        UFDouble[] totalorigmny = null;
        if (maptotalorigmny.containsKey(bid)) {
          totalorigmny = maptotalorigmny.get(bid);
        }
        else {
          totalorigmny = new UFDouble[3];
        }
        totalorigmny[0] =
            MathTool.add(totalorigmny[0], checkvo.getNorigtaxmny());
        totalorigmny[1] = MathTool.add(totalorigmny[1], checkvo.getNorigmny());

        maptotalorigmny.put(bid, totalorigmny);
        // ����
        UFDouble[] totalmny = null;
        if (maptotalmny.containsKey(bid)) {
          totalmny = maptotalmny.get(bid);
        }
        else {
          totalmny = new UFDouble[3];
        }
        totalmny[0] = MathTool.add(totalmny[0], checkvo.getNtaxmny());
        totalmny[1] = MathTool.add(totalmny[1], checkvo.getNmny());
        totalmny[2] = MathTool.add(totalmny[2], checkvo.getNtax());

        maptotalmny.put(bid, totalmny);
      }
    }
  }

  private void setAllOther(DeliveryCheckVO checkvo, DeliveryViewVO view) {
    // ������˰���
    if (null == checkvo.getNmny()) {
      checkvo.setNmny(view.getItem().getNmny());
    }
    // ����˰��
    if (null == checkvo.getNtax()) {
      checkvo.setNtax(view.getItem().getNtax());
    }
    // �ۿ�
    if (null == checkvo.getNdiscount()) {
      checkvo.setNdiscount(view.getItem().getNdiscount());
    }
    // ��˰���
    if (null == checkvo.getNorigmny()) {
      checkvo.setNorigmny(view.getItem().getNorigmny());
    }
    // �ۿ�
    if (null == checkvo.getNorigdiscount()) {
      checkvo.setNorigdiscount(view.getItem().getNorigdiscount());
    }
    // ��������
    if (null == checkvo.getNqtunitnum()) {
      checkvo.setNqtunitnum(view.getItem().getNqtunitnum());
    }
  }

  private void setMoney(DeliveryCheckVO checkvo, DeliveryViewVO view) {
    // ���Ҽ�˰�ϼ�
    if (null == checkvo.getNtaxmny()) {
      checkvo.setNtaxmny(view.getItem().getNtaxmny());
    }
    // ��˰�ϼ�
    if (null == checkvo.getNorigtaxmny()) {
      checkvo.setNorigtaxmny(view.getItem().getNorigtaxmny());
    }
  }

  private Map<String, DeliveryViewVO> getDataMap(DeliveryViewVO[] views) {
    Map<String, DeliveryViewVO> dataMap = new HashMap<String, DeliveryViewVO>();
    for (DeliveryViewVO view : views) {
      dataMap.put(view.getItem().getCdeliverybid(), view);
    }
    return dataMap;
  }

  /*
   * ���String���͵�ֵ�Ƿ�Ϊ�� 
   * @param str
   * @return true Ϊ�� false ��Ϊ��
   */
  private boolean isStringNull(String str) {
    if (null == str || "".equals(str)) {
      return true;
    }
    return false;
  }

  /*
   * �ʼ���˻�д������������ ��Ҫת���ɸ���
   */
  private void oppose(DeliveryCheckVO[] vos) {
    DeliveryVoUtil util = new DeliveryVoUtil();
    util.opposeDeliverycheckVO(vos);
  }

  /*
   * ���Ĭ�ϵı�־��Ϣ
   */
  private void setFlag(DeliveryCheckVO checkvo, DeliveryViewVO view) {
    if (null == checkvo.getBlargessflag()) {
      checkvo.setBlargessflag(view.getItem().getBlargessflag());
    }
    if (null == checkvo.getBoutendflag()) {
      checkvo.setBoutendflag(view.getItem().getBoutendflag());
    }
    if (null == checkvo.getBtransendflag()) {
      checkvo.setBtransendflag(view.getItem().getBtransendflag());
    }
  }

  /*
   * ����������
   */
  private void setFree(DeliveryCheckVO checkvo, DeliveryViewVO view) {
    if (this.isStringNull(checkvo.getPk_batchcode())) {
      checkvo.setPk_batchcode(view.getItem().getPk_batchcode());
    }
    if (this.isStringNull(checkvo.getVbatchcode())) {
      checkvo.setVbatchcode(view.getItem().getVbatchcode());
    }
    if (this.isStringNull(checkvo.getVfree1())) {
      checkvo.setVfree1(view.getItem().getVfree1());
    }
    if (this.isStringNull(checkvo.getVfree2())) {
      checkvo.setVfree2(view.getItem().getVfree2());
    }
    if (this.isStringNull(checkvo.getVfree3())) {
      checkvo.setVfree3(view.getItem().getVfree3());
    }
    if (this.isStringNull(checkvo.getVfree4())) {
      checkvo.setVfree4(view.getItem().getVfree4());
    }
    if (this.isStringNull(checkvo.getVfree5())) {
      checkvo.setVfree5(view.getItem().getVfree5());
    }
    if (this.isStringNull(checkvo.getVfree6())) {
      checkvo.setVfree6(view.getItem().getVfree6());
    }
    if (this.isStringNull(checkvo.getVfree7())) {
      checkvo.setVfree7(view.getItem().getVfree7());
    }
    if (this.isStringNull(checkvo.getVfree8())) {
      checkvo.setVfree8(view.getItem().getVfree8());
    }
    if (this.isStringNull(checkvo.getVfree9())) {
      checkvo.setVfree9(view.getItem().getVfree9());
    }
    if (this.isStringNull(checkvo.getVfree10())) {
      checkvo.setVfree10(view.getItem().getVfree10());
    }
  }

  /*
   * ������ϵ���Ӧ��Ϣ
   */
  private void setMaterial(DeliveryCheckVO checkvo, DeliveryViewVO view) {
    if (this.isStringNull(checkvo.getCmaterialid())) {
      checkvo.setCmaterialid(view.getItem().getCmaterialid());
    }
    if (this.isStringNull(checkvo.getCmaterialvid())) {
      checkvo.setCmaterialvid(view.getItem().getCmaterialvid());
    }
    if (this.isStringNull(checkvo.getCunitid())) {
      checkvo.setCunitid(view.getItem().getCunitid());
    }
    if (this.isStringNull(checkvo.getCastunitid())) {
      checkvo.setCastunitid(view.getItem().getCastunitid());
    }
    if (this.isStringNull(checkvo.getCqtunitid())) {
      checkvo.setCqtunitid(view.getItem().getCqtunitid());
    }
  }

  /*
   * ������ԭ�Ҽ۸�ֵ��
   */
  private void setOrigPrice(DeliveryCheckVO checkvo, DeliveryViewVO view) {
    if (null == checkvo.getNorignetprice()) {
      checkvo.setNorignetprice(view.getItem().getNorignetprice());
    }
    if (null == checkvo.getNorigprice()) {
      checkvo.setNorigprice(view.getItem().getNorigprice());
    }
    if (null == checkvo.getNorigtaxnetprice()) {
      checkvo.setNorigtaxnetprice(view.getItem().getNorigtaxnetprice());
    }
    if (null == checkvo.getNorigtaxprice()) {
      checkvo.setNorigtaxprice(view.getItem().getNorigtaxprice());
    }
    if (null == checkvo.getNqtorignetprice()) {
      checkvo.setNqtorignetprice(view.getItem().getNqtorignetprice());
    }
    if (null == checkvo.getNqtorigprice()) {
      checkvo.setNqtorigprice(view.getItem().getNqtorigprice());
    }
    if (null == checkvo.getNqtorigtaxnetprc()) {
      checkvo.setNqtorigtaxnetprc(view.getItem().getNqtorigtaxnetprc());
    }
    if (null == checkvo.getNqtorigtaxprice()) {
      checkvo.setNqtorigtaxprice(view.getItem().getNqtorigtaxprice());
    }
  }

  /*
   * ����ֵ�ĸ�ֵ
   */
  private void setOther(DeliveryCheckVO checkvo, DeliveryViewVO view) {
    DeliveryBVO bvo = view.getItem();
    checkvo.setVsrcrowno(view.getItem().getCrowno());
    if (this.isStringNull(checkvo.getCproductorid())) {
      checkvo.setCproductorid(view.getItem().getCproductorid());
    }
    if (this.isStringNull(checkvo.getCprojectid())) {
      checkvo.setCprojectid(view.getItem().getCprojectid());
    }
    if (this.isStringNull(checkvo.getCvendorid())) {
      checkvo.setCvendorid(view.getItem().getCvendorid());
    }
    if (this.isStringNull(checkvo.getPk_org())) {
      checkvo.setPk_org(view.getItem().getPk_org());
    }

    if (this.isStringNull(checkvo.getCtaxcodeid())) {
      checkvo.setCtaxcodeid(view.getItem().getCtaxcodeid());
    }

    checkvo.setFtaxtypeflag(view.getItem().getFtaxtypeflag());
    checkvo.setNcaltaxmny(view.getItem().getNcaltaxmny());

    checkvo.setCorigareaid(bvo.getCorigareaid());
    checkvo.setCorigcountryid(bvo.getCorigcountryid());
    checkvo.setCsendcountryid(bvo.getCsendcountryid());
    checkvo.setCtaxcountryid(bvo.getCtaxcountryid());
    checkvo.setCrececountryid(bvo.getCrececountryid());
    checkvo.setFbuysellflag(bvo.getFbuysellflag());
    checkvo.setBtriatradeflag(bvo.getBtriatradeflag());

  }

  /*
   * ����������ı��Ҽ۸����õ��ʼ��
   */
  private void setPrice(DeliveryCheckVO checkvo, DeliveryViewVO view) {
    if (null == checkvo.getNnetprice()) {
      checkvo.setNnetprice(view.getItem().getNnetprice());
    }
    if (null == checkvo.getNprice()) {
      checkvo.setNprice(view.getItem().getNprice());
    }
    if (null == checkvo.getNqtnetprice()) {
      checkvo.setNqtnetprice(view.getItem().getNqtnetprice());
    }
    if (null == checkvo.getNqtprice()) {
      checkvo.setNqtprice(view.getItem().getNqtprice());
    }
    if (null == checkvo.getNqttaxnetprice()) {
      checkvo.setNqttaxnetprice(view.getItem().getNqttaxnetprice());
    }
    if (null == checkvo.getNqttaxprice()) {
      checkvo.setNqttaxprice(view.getItem().getNqttaxprice());
    }
    if (null == checkvo.getNtaxnetprice()) {
      checkvo.setNtaxnetprice(view.getItem().getNtaxnetprice());
    }
    if (null == checkvo.getNtaxprice()) {
      checkvo.setNtaxprice(view.getItem().getNtaxprice());
    }
  }

  /*
   * �����������˰�ʡ������ʵ����õ��ʼ��
   */
  private void setRate(DeliveryCheckVO checkvo, DeliveryViewVO view) {
    if (this.isStringNull(checkvo.getCcurrencyid())) {
      checkvo.setCcurrencyid(view.getItem().getCcurrencyid());
    }
    if (this.isStringNull(checkvo.getCorigcurrencyid())) {
      checkvo.setCorigcurrencyid(view.getItem().getCorigcurrencyid());
    }
    if (null == checkvo.getNdiscountrate()) {
      checkvo.setNdiscountrate(view.getItem().getNdiscountrate());
    }
    if (null == checkvo.getNexchangerate()) {
      checkvo.setNexchangerate(view.getItem().getNexchangerate());
    }
    if (null == checkvo.getNitemdiscountrate()) {
      checkvo.setNitemdiscountrate(view.getItem().getNitemdiscountrate());
    }
    if (null == checkvo.getNtaxrate()) {
      checkvo.setNtaxrate(view.getItem().getNtaxrate());
    }
    if (null == checkvo.getVchangerate()) {
      checkvo.setVchangerate(view.getItem().getVchangerate());
    }
    if (null == checkvo.getVqtunitrate()) {
      checkvo.setVqtunitrate(view.getItem().getVqtunitrate());
    }
  }
}
