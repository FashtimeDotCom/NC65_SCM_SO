package nc.ui.so.m32.billui.pub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.IScmpubMaintain;
import nc.itf.scmpub.reference.uap.bd.material.MaterialBaseClassPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.paravo.CombinContext;
import nc.vo.so.m32.paravo.CombinResultVO;
import nc.vo.so.m32.util.SaleInvoiceVOCalculator;
import nc.vo.so.m32.util.SaleInvoiceVOMerger;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.comparator.RowNoComparator;
import nc.vo.so.pub.enumeration.SOFInvoiceKey2Code;
import nc.vo.so.pub.res.ParameterList;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.util.ListUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * ���۷�Ʊ�ϲ���ʾ�ͺϲ��༭������
 * 
 * @since 6.3
 * @version 2012-12-21 ����10:56:29
 * @author yaogj
 */
public class SaleInvoiceCombin {

  /**
   * ����map
   * code => key
   */
  private Map<String, String> mapTransCodeToKey;

  /**
   * ��ȡ����ֵ����Map
   * 
   * @return Map<String, String>
   */
  public Map<String, String> getTransCodeToKey() {
    if (null == this.mapTransCodeToKey) {
      this.mapTransCodeToKey = new LinkedHashMap<String, String>();
      for (SOFInvoiceKey2Code rule : SOFInvoiceKey2Code.values()) {
        this.mapTransCodeToKey.put(rule.getCode(), rule.getKey());
      }
    }
    return this.mapTransCodeToKey;
  }

  /**
   * ���ŷ�Ʊ��ʾ��ʽ������ ʱ�������۷�Ʊ���л���
   * 
   * @param detailvos ��ϸvos
   * @param cachevo ����vo
   * @return �ϲ�����
   */
  public CombinResultVO combinSaleInvoices(SaleInvoiceVO[] detailvos,
      CombinCacheVO cachevo) {

    CombinResultVO combinpara = new CombinResultVO(true);
    combinpara.setCachevo(cachevo);
    if (null != detailvos) {
      this.combinDetails(detailvos, combinpara);
    }
    return combinpara;
  }

  /**
   * �ӻ�����ɾ��VOS
   * �ĺϲ���ϵ
   * 
   * @param vos
   * @param combinrela
   */
  public void deleteCombinRelation(SaleInvoiceVO[] vos,
      MapList<String, SaleInvoiceBVO> combinrela) {
    for (SaleInvoiceVO vo : vos) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        String key = bvo.getCsaleinvoicebid();
        combinrela.remove(key);
      }
    }

  }

  /**
   * �ϲ��༭�²���������ԭ�����кϲ�
   * 
   * @param detainvo ԭ�������ϵ���ϸVO
   * @param newvos �������й�������VO
   * @param cachevo ����
   * @return ��Ʊvo
   */
  public SaleInvoiceVO getCombinVOByRefAndLine(SaleInvoiceVO detainvo,
      SaleInvoiceVO[] newvos, CombinCacheVO cachevo) {
    // SaleInvoiceHVO hvo = oldvo.getParentVO();
    List<SaleInvoiceBVO> bvos = new ArrayList<SaleInvoiceBVO>();
    for (SaleInvoiceBVO bvo : detainvo.getChildrenVO()) {
      bvos.add(bvo);
    }
    for (SaleInvoiceVO newvo : newvos) {
      for (SaleInvoiceBVO bvo : newvo.getChildrenVO()) {
        bvos.add(bvo);
      }
    }
    detainvo.setChildrenVO(bvos.toArray(new SaleInvoiceBVO[bvos.size()]));
    this.deleteCombinRelation(new SaleInvoiceVO[] {
      detainvo
    }, cachevo.getCombinRela());

    CombinResultVO comvo = this.combinSaleInvoices(new SaleInvoiceVO[] {
      detainvo
    }, cachevo);
    return comvo.getCombinvos()[0];
  }

  /**
   * ��������ɾ���Ȳ�����ȡ���µ�UIVO
   * 
   * @param cachevo ����vo
   * @param oldcombinvo �ɵĺϲ�vo
   * @param olddetailvos �ɵ���ϸvo
   * @param pretObj ��ϸvo
   * @return ��Ʊvo
   */
  public SaleInvoiceVO[] getNewCombinUIVOS(CombinCacheVO cachevo,
      SaleInvoiceVO[] oldcombinvo, SaleInvoiceVO[] olddetailvos,
      Object[] pretObj) {
    SaleInvoiceVO[] retdetailvos = (SaleInvoiceVO[]) pretObj;

    new ClientBillCombinServer<SaleInvoiceVO>().combine(olddetailvos,
        retdetailvos);
    List<SaleInvoiceBVO> oldcombinbvos = new ArrayList<SaleInvoiceBVO>();
    List<SaleInvoiceBVO> newdetbvos = new ArrayList<SaleInvoiceBVO>();
    for (SaleInvoiceVO vo : oldcombinvo) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        oldcombinbvos.add(bvo);
      }
    }
    for (SaleInvoiceVO vo : olddetailvos) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        newdetbvos.add(bvo);
      }
    }

    MapList<String, SaleInvoiceBVO> cachebvomap = cachevo.getCombinRela();
    SaleInvoiceCombin combin = new SaleInvoiceCombin();
    // ���ºϲ���ϵ
    combin.updateNoEditCombinRela(ListUtil.toArray(oldcombinbvos),
        ListUtil.toArray(newdetbvos), cachebvomap);

    SaleInvoiceVO[] newcombinvos = new SaleInvoiceVO[oldcombinvo.length];
    for (int i = 0; i < oldcombinvo.length; i++) {
      newcombinvos[i] = (SaleInvoiceVO) oldcombinvo[i].clone();
      newcombinvos[i].setParentVO(retdetailvos[i].getParentVO());
    }
    return newcombinvos;
  }

  /**
   * ��ԭ��ϸ���ݣ������������ύ���ջأ�
   * 
   * @param oldconbinvos
   * @param combinRela
   * @return ��Ʊvo
   */
  public SaleInvoiceVO[] getOldDetailVOs(SaleInvoiceVO[] oldconbinvos,
      MapList<String, SaleInvoiceBVO> combinRela) {
    List<SaleInvoiceVO> volist = new ArrayList<SaleInvoiceVO>();
    for (SaleInvoiceVO vo : oldconbinvos) {
      SaleInvoiceHVO hvo = vo.getParentVO();
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      List<SaleInvoiceBVO> oldbvolist = new ArrayList<SaleInvoiceBVO>();
      for (SaleInvoiceBVO bvo : bvos) {
        String bpk = bvo.getCsaleinvoicebid();
        List<SaleInvoiceBVO> bvoslist = combinRela.get(bpk);
        if (null == bvoslist) {
          continue;
        }
        oldbvolist.addAll(bvoslist);
      }
      SaleInvoiceVO oldvo = new SaleInvoiceVO();
      oldvo.setParentVO(hvo);
      oldvo.setChildrenVO(oldbvolist.toArray(new SaleInvoiceBVO[oldbvolist
          .size()]));
      volist.add(oldvo);
    }
    return volist.toArray(new SaleInvoiceVO[volist.size()]);
  }

  /**
   * ���ؼ������۷�Ʊ��ʾ��ʽ
   * 
   * @return �ϲ���ʾ����
   */
  public boolean getSO27() {
    String pk_group = AppContext.getInstance().getPkGroup();
    String so27 = null;

    so27 = SOSysParaInitUtil.getSO27(pk_group);

    if ("����".equals(so27)) { /* -=notranslate=- */
      return true;
    }
    return false;
  }

  /**
   * �Գ巢Ʊʱ�������ID
   * ���ڶԳ巢Ʊ
   * 
   * @param detailvos
   */
  public void processVOBids(SaleInvoiceVO[] detailvos) {
    int blength = 0;
    for (SaleInvoiceVO vo : detailvos) {
      blength += vo.getChildrenVO().length;
    }

    IScmpubMaintain srv = NCLocator.getInstance().lookup(IScmpubMaintain.class);
    String[] bids;
    try {
      bids = srv.getIDs(blength);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
      return;
    }
    int i = 0;
    for (SaleInvoiceVO vo : detailvos) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        bvo.setCsaleinvoicebid(bids[i]);
        i++;
      }
    }
  }

  /**
   * �༭״̬��ԭ��ϸVO
   * 
   * @param combinvo
   * @param combinrela
   * @return ��Ʊvo
   */
  public SaleInvoiceVO splitEditSaleInvoice(SaleInvoiceVO combinvo,
      MapList<String, SaleInvoiceBVO> combinrela) {
    List<SaleInvoiceBVO> listdetail = new ArrayList<SaleInvoiceBVO>();
    boolean isnew = this.isNew(combinvo);
    String pk_org = combinvo.getParentVO().getPk_org();
    Map<String, String> mParas =
        SOSysParaInitUtil.queryBatchParaStringValues(new String[] {
          pk_org
        }, ParameterList.SO28.getCode() + ParameterList.SUFFIX);
    // String[] combinparary =
    // mParas.get(pk_org).split(ParameterList.BIGSPLITKEY);
    String[] combinparary = this.getGroupKeys(mParas.get(pk_org));
    SaleInvoiceVO detailvo = new SaleInvoiceVO();
    SaleInvoiceHVO headvo = (SaleInvoiceHVO) combinvo.getParentVO().clone();
    detailvo.setParentVO(headvo);
    // ���������� bid�ǿյ� ������в���
    String[] newids = this.getNewID(combinvo);
    int i = 0;
    for (SaleInvoiceBVO combvo : combinvo.getChildrenVO()) {
      // ������
      String bodypk = combvo.getPrimaryKey();
      if (PubAppTool.isNull(bodypk)) {
        if (PubAppTool.isNull(combvo.getCsrcid())) {
          String newid = newids[i];
          combvo.setCsaleinvoicebid(newid);
          listdetail.add(combvo);
          combinrela.put(newid, combvo);
          i++;
        }
        continue;
      }
      List<SaleInvoiceBVO> cachedetbvos = combinrela.get(bodypk);
      if (null == cachedetbvos || cachedetbvos.size() == 0) {
        if (!"isnull".equals(bodypk)) {
          listdetail.add(combvo);
        }

      }
      else {
        if (isnew) {
          listdetail.addAll(this.processNew(headvo, combvo, cachedetbvos,
              combinparary));
        }
        else {
          listdetail.addAll(this.processUpdate(headvo, combvo, cachedetbvos,
              combinparary));
        }
      }
    }
    SaleInvoiceBVO[] detailbvos = new SaleInvoiceBVO[listdetail.size()];
    listdetail.toArray(detailbvos);
    detailvo.setChildrenVO(detailbvos);
    return detailvo;
  }

  private String[] getNewID(SaleInvoiceVO combinvo) {
    String[] ids = null;
    int i = 0;
    for (SaleInvoiceBVO combvo : combinvo.getChildrenVO()) {
      // ������
      String bodypk = combvo.getPrimaryKey();
      String srcid = combvo.getCsrcid();
      if (PubAppTool.isNull(bodypk) && PubAppTool.isNull(srcid)) {
        i++;
      }
    }
    if (i > 0) {
      IScmpubMaintain srv =
          NCLocator.getInstance().lookup(IScmpubMaintain.class);
      try {
        ids = srv.getIDs(i);
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
    }
    return ids;
  }

  /**
   * �Ǳ༭̬��ԭ��ϸ״̬����
   * 
   * @param combinvos
   * @param combinrela
   * @return ��Ʊvo
   */
  public SaleInvoiceVO[] splitNoEditSaleInvoice(SaleInvoiceVO[] combinvos,
      MapList<String, SaleInvoiceBVO> combinrela) {
    List<SaleInvoiceVO> detailvos = new ArrayList<SaleInvoiceVO>();
    for (SaleInvoiceVO combinvo : combinvos) {
      SaleInvoiceVO detailvo = new SaleInvoiceVO();
      if (combinvo.getChildrenVO() == null
          || combinvo.getChildrenVO().length == 0) {
        detailvo.setParentVO((SaleInvoiceHVO) combinvo.getParentVO().clone());
        detailvo.setChildrenVO(null);
        detailvos.add(detailvo);
        continue;
      }
      List<SaleInvoiceBVO> listdetail = new ArrayList<SaleInvoiceBVO>();
      for (SaleInvoiceBVO combvo : combinvo.getChildrenVO()) {
        String key = combvo.getPrimaryKey();
        if (null == key || "isnull".equals(key)) {
          continue;
        }

        List<SaleInvoiceBVO> cachebvo = combinrela.get(key);
        if (null == cachebvo) {
          listdetail.add(combvo);
        }
        else {
          listdetail.addAll(cachebvo);
        }

      }
      SaleInvoiceHVO headvo = (SaleInvoiceHVO) combinvo.getParentVO().clone();
      detailvo.setParentVO(headvo);

      SaleInvoiceBVO[] bodyvos = new SaleInvoiceBVO[listdetail.size()];
      detailvo.setChildrenVO(listdetail.toArray(bodyvos));
      detailvos.add(detailvo);
    }
    return detailvos.toArray(new SaleInvoiceVO[detailvos.size()]);
  }

  /**
   * ɾ����������ºϲ�����ϸ�Ļ����ϵ
   * 
   * @param oldcombinbvos
   * @param combinRela
   */
  public void updateCombinRela(SaleInvoiceBVO[] oldcombinbvos,
      MapList<String, SaleInvoiceBVO> combinRela) {

    for (SaleInvoiceBVO oldbvo : oldcombinbvos) {
      combinRela.toMap().remove(oldbvo.getPrimaryKey());
    }
  }

  /**
   * ���涯������ºϲ�����ϸ�Ļ����ϵ
   * 
   * @param oldcombinbvos
   * @param newbvos
   * @param combinRela
   * @param setDelbids
   */
  public void updateEditCombinRela(SaleInvoiceBVO[] oldcombinbvos,
      SaleInvoiceBVO[] newbvos, MapList<String, SaleInvoiceBVO> combinRela,
      Set<String> setDelbids) {
    Map<String, SaleInvoiceBVO> mapNew = new HashMap<String, SaleInvoiceBVO>();
    for (SaleInvoiceBVO bvo : newbvos) {
      mapNew.put(bvo.getPrimaryKey(), bvo);
    }
    Set<String> setOldKey = new HashSet<String>();
    for (SaleInvoiceBVO oldbvo : oldcombinbvos) {
      String key = oldbvo.getPrimaryKey();
      if (VOStatus.DELETED == oldbvo.getStatus()) {
        combinRela.toMap().remove(key);
      }
      else {
        List<SaleInvoiceBVO> oldbvos = combinRela.get(key);
        if (null == oldbvos) {
          continue;
        }
        combinRela.remove(key);
        for (int i = oldbvos.size() - 1; i >= 0; i--) {
          SaleInvoiceBVO bvo = oldbvos.get(i);
          String bvokey = bvo.getPrimaryKey();
          if (!setDelbids.contains(bvokey)) {
            SaleInvoiceBVO newbvo = mapNew.get(bvokey);
            if (null != newbvo) {
              combinRela.put(key, newbvo);
              setOldKey.add(bvokey);
            }
          }
        }
      }
    }
    for (SaleInvoiceBVO bvo : newbvos) {
      String key = bvo.getPrimaryKey();
      if (!setOldKey.contains(key)) {
        combinRela.put(key, bvo);
      }
    }
  }

  /**
   * ������ˡ�����ȷǱ༭��������ºϲ�����ϸ�Ļ����ϵ
   * 
   * @param oldcombinbvos
   * @param newdetbvos
   * @param combinRela
   */
  public void updateNoEditCombinRela(SaleInvoiceBVO[] oldcombinbvos,
      SaleInvoiceBVO[] newdetbvos, MapList<String, SaleInvoiceBVO> combinRela) {
    Map<String, SaleInvoiceBVO> mapNew = new HashMap<String, SaleInvoiceBVO>();
    for (SaleInvoiceBVO bvo : newdetbvos) {
      mapNew.put(bvo.getPrimaryKey(), bvo);
    }
    for (SaleInvoiceBVO oldbvo : oldcombinbvos) {
      String key = oldbvo.getPrimaryKey();
      List<SaleInvoiceBVO> oldbvos = combinRela.get(key);
      if (null == oldbvos) {
        continue;
      }
      for (int i = 0; i < oldbvos.size(); i++) {
        SaleInvoiceBVO bvo = oldbvos.get(i);
        oldbvos.set(i, mapNew.get(bvo.getPrimaryKey()));
      }
    }
  }

  /**
   * 
   * @return �õ��۸��ֶ�
   */
  public static String getCalPriceKey() {
    if (SOCalConditionRule.isTaxPrior()) {
      return SOItemKey.NORIGTAXPRICE;
    }

    return SOItemKey.NORIGPRICE;
  }

  /**
   * 
   * @return �õ�����ֶ�
   */
  public static String getCalMnyKey() {
    if (SOCalConditionRule.isTaxPrior()) {
      return SOItemKey.NORIGTAXMNY;
    }

    return SOItemKey.NORIGMNY;
  }

  private void checkNewPriceMnyChg(SaleInvoiceHVO headvo,
      SaleInvoiceBVO combvo, List<SaleInvoiceBVO> retdetial) {
    SaleInvoiceVO voInvoice = new SaleInvoiceVO();
    voInvoice.setParentVO(headvo);
    SaleInvoiceBVO[] bvos = new SaleInvoiceBVO[retdetial.size()];
    retdetial.toArray(bvos);
    voInvoice.setChildrenVO(bvos);
    SaleInvoiceVOCalculator calc = new SaleInvoiceVOCalculator(voInvoice);
    // ԭ�ҽ���Ƿ�ı�
    boolean isorigmnychange = this.isOrigMnyChange(combvo, retdetial);

    // ���ҽ���Ƿ�ı�
    boolean ismnychange = this.isMnyChange(combvo, retdetial);
    if (!isorigmnychange) {
      // ԭ��û�� ���ұ��� ��Ϊ ���۱����ʷ����仯
      if (ismnychange) {
        calc.calculateAll(SaleInvoiceHVO.NEXCHANGERATE);
      }
      return;
    }
    String pricekey = SaleInvoiceCombin.getCalPriceKey();
    UFDouble oldprice = this.getOldPrice(retdetial);
    UFDouble nowprice = (UFDouble) combvo.getAttributeValue(pricekey);

    /*
     * int size = retdetial.size(); // ���ֵ�ı䣬�������û�иı�˵�������ı䣬ֻ�赹�����һ�н�� if
     * (isorigmnychange && MathTool.equals(oldprice, nowprice)) {
     * this.processCombinMargin(combvo, retdetial, size, calc); // ���ֵ�ı䣬���ҵ��۸ı�
     * ��Ҫ��������ϸ�ĵ��۸ı䣬���¼�����������һ�н�� } else if (isorigmnychange &&
     * !MathTool.equals(oldprice, nowprice)) { UFDouble ntaxrate =
     * combvo.getNtaxrate(); for (SaleInvoiceBVO bvo : retdetial) {
     * bvo.setNorigprice(nowprice); bvo.setNtaxrate(ntaxrate); }
     * calc.calculateAll(pricekey);
     */

    String mnykey = SaleInvoiceCombin.getCalMnyKey();
    UFDouble oldMny = this.getOldMny(retdetial);
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    String currid = headvo.getCcurrencyid();
    UFDouble nowMny = (UFDouble) combvo.getAttributeValue(mnykey);
    int size = retdetial.size();

    // ���ֵ�ı䣬�������û�иı�˵�������ı䣬ֻ�赹�����һ�н��
    if (isorigmnychange && MathTool.equals(oldprice, nowprice)) {
      this.processCombinMargin(combvo, retdetial, size, calc);
      // ���ֵ�ı䣬���ҵ��۸ı� ��Ҫ��������ϸ�ĵ��۸ı䣬���¼�����������һ�н��
    }
    else if (isorigmnychange && !MathTool.equals(oldprice, nowprice)) {
      UFDouble ntaxrate = combvo.getNtaxrate();
      // �ü�˰�ϼƴ������۽���㷨����ÿ��ԭ���ļ�˰�ϼƱ�����̯�µļ�˰�ϼ�
      UFDouble nowTotalBodyMny = UFDouble.ZERO_DBL;
      SaleInvoiceBVO[] bvo =
          retdetial.toArray(new SaleInvoiceBVO[retdetial.size()]);
      for (int i = 0; i < bvo.length - 1; i++) {
        UFDouble bodyoldMny = (UFDouble) bvo[i].getAttributeValue(mnykey);
        UFDouble nowbodyMny =
            scale.adjustMnyScale(nowMny.multiply(bodyoldMny).div(oldMny),
                currid);
        bvo[i].setAttributeValue(mnykey, nowbodyMny);
        bvo[i].setNtaxrate(ntaxrate);
        nowTotalBodyMny = nowTotalBodyMny.add(nowbodyMny);
      }
      bvo[bvo.length - 1]
          .setAttributeValue(mnykey, nowMny.sub(nowTotalBodyMny));
      bvo[bvo.length - 1].setNtaxrate(ntaxrate);
      calc.calculateAll(mnykey);
      // ����β��
      this.processCombinMargin(combvo, retdetial, size, calc);
    }
  }

  /**
   * �ɵĽ��
   * 
   * @param retdetial
   * @return
   */
  private UFDouble getOldMny(List<SaleInvoiceBVO> retdetial) {
    String mnykey = SaleInvoiceCombin.getCalMnyKey();
    UFDouble oldtotalmny = UFDouble.ZERO_DBL;
    for (SaleInvoiceBVO bvo : retdetial) {
      oldtotalmny =
          MathTool.add(oldtotalmny, (UFDouble) bvo.getAttributeValue(mnykey));
    }
    return oldtotalmny;
  }

  /**
   * У�������Ƿ�仯
   * 
   * @param headvo
   * @param combvo
   * @param detailbvos
   * @param retdetail
   * @param isnew true������ false Ϊ����
   */
  private void checkNumChange(SaleInvoiceHVO headvo, SaleInvoiceBVO combvo,
      List<SaleInvoiceBVO> detailbvos, List<SaleInvoiceBVO> retdetail,
      boolean isnew, String[] combinparary) {
    SaleInvoiceVOCalculator calc = new SaleInvoiceVOCalculator();
    UFDouble oldtotalnum = UFDouble.ZERO_DBL;
    List<SaleInvoiceBVO> clonebvos = new ArrayList<SaleInvoiceBVO>();
    for (SaleInvoiceBVO bvo : detailbvos) {
      SaleInvoiceBVO clonebvo = (SaleInvoiceBVO) bvo.clone();
      for (String key : combinparary) {
        List<String> exceptMnyFileds = getExceptMnyFileds();
        // jilu for 633 �޸ļ�˰�ϼ�ʱ�����Զ����㾻�ۼ���Ʊ�ۿۣ��˴��ٸ��߷�Ʊ�ۿ۷��㾻�ۣ��ʹ���
        if (!exceptMnyFileds.contains(key)) {
          // end
          // �б仯�����¸�ֵ
          if (combvo.getAttributeValue(key) != null
              && !combvo.getAttributeValue(key).equals(
                  clonebvo.getAttributeValue(key))
              || clonebvo.getAttributeValue(key) != null
              && !clonebvo.getAttributeValue(key).equals(
                  combvo.getAttributeValue(key))) {
            clonebvo.setAttributeValue(key, combvo.getAttributeValue(key));
            clonebvo.setAttributeValue(SaleInvoiceBVO.VCHANGERATE,
                combvo.getAttributeValue(SaleInvoiceBVO.VCHANGERATE));
            SaleInvoiceVO invoice = new SaleInvoiceVO();
            invoice.setParentVO(headvo);
            invoice.setChildrenVO(new SaleInvoiceBVO[] {
              clonebvo
            });
            calc.setVoInvoice(invoice);
            calc.calculate(0, key);
          }
        }
      }
      if (null != combvo.getAttributeValue(SaleInvoiceBVO.VCHANGERATE)
          && !combvo.getAttributeValue(SaleInvoiceBVO.VCHANGERATE).equals(
              clonebvo.getAttributeValue(SaleInvoiceBVO.VCHANGERATE))) {
        clonebvo.setAttributeValue(SaleInvoiceBVO.VCHANGERATE,
            combvo.getAttributeValue(SaleInvoiceBVO.VCHANGERATE));
        SaleInvoiceVO invoice = new SaleInvoiceVO();
        invoice.setParentVO(headvo);
        invoice.setChildrenVO(new SaleInvoiceBVO[] {
          clonebvo
        });
        calc.setVoInvoice(invoice);
        calc.calculate(0, SaleInvoiceBVO.VCHANGERATE);
      }
      if (null != combvo.getAttributeValue(SaleInvoiceBVO.VQTUNITRATE)
          && !combvo.getAttributeValue(SaleInvoiceBVO.VQTUNITRATE).equals(
              clonebvo.getAttributeValue(SaleInvoiceBVO.VQTUNITRATE))) {
        clonebvo.setAttributeValue(SaleInvoiceBVO.VQTUNITRATE,
            combvo.getAttributeValue(SaleInvoiceBVO.VQTUNITRATE));
        SaleInvoiceVO invoice = new SaleInvoiceVO();
        invoice.setParentVO(headvo);
        invoice.setChildrenVO(new SaleInvoiceBVO[] {
          clonebvo
        });
        calc.setVoInvoice(invoice);
        calc.calculate(0, SaleInvoiceBVO.VQTUNITRATE);
      }

      clonebvos.add(clonebvo);
      oldtotalnum = MathTool.add(oldtotalnum, bvo.getNnum());
    }
    // �������
    UFDouble nowtotalnum = combvo.getNnum();

    if (MathTool.isDiffSign(nowtotalnum, oldtotalnum)) {
      // ExceptionUtils.wrappBusinessException("�������޸Ŀ�Ʊ�����ķ���");
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006008_0", "04006008-0127")/*
                                                                   * @res
                                                                   * "�������޸Ŀ�Ʊ�����ķ���"
                                                                   */);
    }

    if (MathTool.absCompareTo(nowtotalnum, oldtotalnum) > 0
        && !MathTool.isZero(oldtotalnum)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006008_0", "04006008-0010")/*
                                                                   * @res
                                                                   * "������༭��������Я������"
                                                                   */);
    }
    else if (!MathTool.isZero(oldtotalnum)) {
      UFDouble remainnum = nowtotalnum;

      for (SaleInvoiceBVO bvo : clonebvos) {
        if (MathTool.absCompareTo(remainnum, bvo.getNnum()) >= 0) {
          remainnum = MathTool.sub(remainnum, bvo.getNnum());
          retdetail.add(bvo);
        }
        else if (remainnum.compareTo(UFDouble.ZERO_DBL) == 0) {
          if (isnew) {
            break;
          }
          bvo.setStatus(VOStatus.DELETED);
          retdetail.add(bvo);
        }
        else if (MathTool.absCompareTo(remainnum, UFDouble.ZERO_DBL) > 0) {
          bvo.setNnum(remainnum);
          SaleInvoiceVO invoice = new SaleInvoiceVO();
          invoice.setParentVO(headvo);
          invoice.setChildrenVO(new SaleInvoiceBVO[] {
            bvo
          });
          calc.setVoInvoice(invoice);
          calc.calculate(0, SaleInvoiceBVO.NNUM);
          retdetail.add(bvo);
          break;
        }
      }
    }
    else {
      retdetail.addAll(clonebvos);
    }
  }

  /**
   * �������ֶΰ�������ʱ������������
   * 
   * 
   * */
  public List<String> getExceptMnyFileds() {
    List<String> exceptMnyFileds = new ArrayList<String>();
    exceptMnyFileds.add("CMARBASCALSSID");
    exceptMnyFileds.add("NINVOICEDISRATE");
    exceptMnyFileds.add("NORIGMNY");
    exceptMnyFileds.add("NMNY");
    exceptMnyFileds.add("NTAXMNY");
    exceptMnyFileds.add("NTAX");

    return exceptMnyFileds;
  }

  /**
   * У�鵥���Ƿ�仯
   * 
   * @param headvo
   * @param combvo
   * @param retdetail
   */
  private void checkUpdatePriceMnyChg(SaleInvoiceHVO headvo,
      SaleInvoiceBVO combvo, List<SaleInvoiceBVO> retdetail) {
    // ȥ��ɾ������
    List<SaleInvoiceBVO> bvolistnodel = new ArrayList<SaleInvoiceBVO>();
    for (SaleInvoiceBVO bvo : retdetail) {
      if (VOStatus.DELETED == bvo.getStatus()) {
        continue;
      }
      bvolistnodel.add(bvo);
    }
    this.checkNewPriceMnyChg(headvo, combvo, bvolistnodel);
  }

  /**
   * �����кϲ�
   * 
   * @param detailvos
   * @param combinpara
   */
  private void combinDetails(SaleInvoiceVO[] detailvos,
      CombinResultVO combinpara) {
    SaleInvoiceVOMerger mergertool = new SaleInvoiceVOMerger();
    mergertool.setNumAttr(SaleInvoiceBVO.NNUM);
    Map<String, String> mparacombins = this.getCombinParas(detailvos);
    combinpara.setMapGroupKeys(mparacombins);
    String pk_org = null;

    MapList<String, SaleInvoiceBVO> cominrela = combinpara.getCombinRela();
    if (null == cominrela) {
      cominrela = new MapList<String, SaleInvoiceBVO>();
    }
    SaleInvoiceVO[] combinvo = new SaleInvoiceVO[detailvos.length];
    int i = 0;
    for (SaleInvoiceVO vo : detailvos) {
      if (vo.getChildrenVO() == null || vo.getChildrenVO().length == 0) {
        combinvo[i] = new SaleInvoiceVO();
        combinvo[i].setParentVO(vo.getParentVO());
        combinvo[i].setChildrenVO(null);
        i++;
        continue;
      }
      mergertool.setIshasclass(false);
      pk_org = vo.getParentVO().getPk_org();
      String combinparas = mparacombins.get(pk_org);
      String[] combinparary = combinparas.split(ParameterList.BIGSPLITKEY);
      String[] groupkeys = this.getGroupKeys(combinparas);
      if (groupkeys.length == 0) {
        combinvo[i] = new SaleInvoiceVO();
        combinvo[i].setParentVO(vo.getParentVO());
        combinvo[i].setChildrenVO(vo.getChildrenVO());
        i++;
        continue;
      }
      int classlevel = this.getClassLevel(combinparary[4]);
      List<SaleInvoiceBVO> megervolist = new ArrayList<SaleInvoiceBVO>();
      // �����ϼ��ν��л������Ʊ���vos
      this.getDoubleVOS(megervolist, vo, groupkeys, classlevel, detailvos);
      mergertool.setSummingAttr(this.getSumKeys(combinparas));
      mergertool.setProavgingAttr(this.getProAvgKeys(combinparas));
      String[] avergeattrs = this.getAvgKeys(combinparas);
      if (null != avergeattrs && avergeattrs.length > 0) {
        mergertool.setAveragingAttr(this.getAvgKeys(combinparas));
      }
      mergertool.setGroupingAttr(groupkeys);
      SaleInvoiceBVO[] bodys = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bodys) {
        megervolist.add(bvo);
      }
      SaleInvoiceBVO[] mergebvos = null;
      try {
        mergebvos =
            (SaleInvoiceBVO[]) mergertool.mergeByGroup(megervolist
                .toArray(new SaleInvoiceBVO[0]));
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      if (null == mergebvos) {
        continue;
      }
      UFBoolean megerEqualDetail = UFBoolean.FALSE;
      if (mergebvos.length == bodys.length) {
        megerEqualDetail = UFBoolean.TRUE;
      }

      Map<CircularlyAccessibleValueObject, CircularlyAccessibleValueObject[]> mg =
          mergertool.m_hashMergeRelations;
      int row = 10;
      for (SaleInvoiceBVO mergebvo : mergebvos) {
        SaleInvoiceBVO[] detailbvos = (SaleInvoiceBVO[]) mg.get(mergebvo);
        // �����к�
        if (megerEqualDetail.booleanValue()) {
          mergebvo.setCrowno(detailbvos[0].getCrowno());
        }
        else {
          mergebvo.setCrowno(String.valueOf(row));
          row = row + 10;
        }

        String bid = detailbvos[0].getPrimaryKey();
        if (null == bid || "isnull".equals(bid)) {
          continue;
        }
        mergebvo.setPrimaryKey(bid);
        for (SaleInvoiceBVO bvo : detailbvos) {
          SaleInvoiceBVO cachebvo = (SaleInvoiceBVO) bvo.clone();
          cominrela.put(bid, cachebvo);
        }

      }
      // ���к���������
      if (megerEqualDetail.booleanValue()) {
        RowNoComparator comp = new RowNoComparator(SaleInvoiceBVO.CROWNO);
        Arrays.sort(mergebvos, comp);
      }

      combinvo[i] = new SaleInvoiceVO();
      combinvo[i].setParentVO(vo.getParentVO());
      combinvo[i].setChildrenVO(mergebvos);
      i++;
    }
    combinpara.setCombinvos(combinvo);
    combinpara.setCombinrela(cominrela);
  }

  /**
   * ƽ��ֵ�ֶ�����
   * 
   * @param paravalue
   * @return
   */
  private String[] getAvgKeys(String paravalue) {
    // jilu for 633 �ϲ�BUG ǰ������
    // ���۷�Ʊ��ʾ����ѡ����ܣ����ܹ����ջ���������ܣ������Ŀѡ������Զ�����Ŀ1-7�����ǻ���ʱ�����Զ�����Ŀ���ܺϲ�ʱûӴ������ʾ
    // String keystring = paravalue.split(ParameterList.BIGSPLITKEY)[1];
    String keystring = paravalue.split(ParameterList.BIGSPLITKEY)[2];
    // end
    if (keystring.length() == 0) {
      return null;
    }
    return this.transCode2Key(keystring.split(ParameterList.SPLITKEY));
  }

  private int getClassLevel(String leveltext) {
    // String leveltext = listgroupkeys.get(listgroupkeys.size() - 1);
    int levelint = 0;
    if ("�𼶻���".equals(leveltext)) { /* -=notranslate=- */
      levelint = -1;
    }
    else if ("ĩ������".equals(leveltext)) { /* -=notranslate=- */
      levelint = 0;
    }
    else if ("һ������".equals(leveltext)) { /* -=notranslate=- */
      levelint = 1;
    }
    else if ("��������".equals(leveltext)) { /* -=notranslate=- */
      levelint = 2;
    }
    else if ("��������".equals(leveltext)) { /* -=notranslate=- */
      levelint = 3;
    }
    else if ("�ļ�����".equals(leveltext)) { /* -=notranslate=- */
      levelint = 4;
    }
    else if ("�弶����".equals(leveltext)) { /* -=notranslate=- */
      levelint = 5;
    }

    return levelint;
  }

  private String[] getCmaterialIDs(SaleInvoiceVO[] detailvos) {
    Set<String> cmaterialidset = new java.util.HashSet<String>();

    for (SaleInvoiceVO vo : detailvos) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      for (SaleInvoiceBVO bvo : bvos) {
        cmaterialidset.add(bvo.getCmaterialid());
      }

    }
    String[] cmaterialids =
        cmaterialidset.toArray(new String[cmaterialidset.size()]);
    return cmaterialids;
  }

  /**
   * ������֯��÷���ϲ�����
   * 
   * @param detailvos
   * @return
   */
  private Map<String, String> getCombinParas(SaleInvoiceVO[] detailvos) {
    Set<String> setOrgs = new java.util.HashSet<String>();
    for (SaleInvoiceVO vo : detailvos) {
      setOrgs.add(vo.getParentVO().getPk_org());
    }
    String[] orgs = new String[setOrgs.size()];
    orgs = setOrgs.toArray(orgs);
    Map<String, String> mParas = null;

    mParas =
        SOSysParaInitUtil.queryBatchParaStringValues(orgs,
            ParameterList.SO28.getCode() + ParameterList.SUFFIX);

    return mParas;
  }

  /**
   * ����VO
   * 
   * @param megervolist
   * @param vo
   * @param listgroupkeys
   * @param classlevel
   * @param detailvos
   */
  private void getDoubleVOS(List<SaleInvoiceBVO> megervolist, SaleInvoiceVO vo,
      String[] listgroupkeys, int classlevel, SaleInvoiceVO[] detailvos) {
    boolean ishascmrbasclass = false;
    for (String str : listgroupkeys) {
      if (str.equals(SaleInvoiceBVO.CMARBASCALSSID)) {
        ishascmrbasclass = true;
        break;
      }
    }
    if (ishascmrbasclass) {
      SaleInvoiceBVO[] bvos = vo.getChildrenVO();
      String[] pks = this.getCmaterialIDs(detailvos);

      // �𼶻���
      if (classlevel == -1) {
        Map<String, String> baseclassmaps =
            MaterialPubService.queryMaterialBaseClassPk(pks);
        ISaleInvoiceMaintain srv =
            NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);
        Map<String, String> innercodecmatermap = new HashMap<String, String>();
        try {
          innercodecmatermap = srv.getInnercodemaps(pks);
        }
        catch (BusinessException e1) {
          ExceptionUtils.wrappException(e1);
        }
        // ����������������ϵ����뼯��
        Set<String> innercodesets = new HashSet<String>();
        for (Entry<String, String> entry : innercodecmatermap.entrySet()) {
          String innercode = entry.getValue();
          // ��������4λΪ��һ����8λΪ�ڶ������Դ����ơ�
          int rownum = innercode.length() / 4;
          UFDouble basenum = new UFDouble(4);
          for (int j = 0; j < rownum; j++) {
            UFDouble level = new UFDouble(j + 1);
            String newcode =
                innercode.substring(0, basenum.multiply(level).intValue());
            innercodesets.add(newcode);
          }
        }

        Map<String, String> newcmarbascalssidmaps =
            new HashMap<String, String>();
        try {
          newcmarbascalssidmaps =
              srv.getCmaterialids(innercodesets
                  .toArray(new String[innercodesets.size()]));
        }
        catch (BusinessException e1) {
          ExceptionUtils.wrappException(e1);
        }
        for (SaleInvoiceBVO bvo : bvos) {
          String cmaterid = bvo.getCmaterialid();
          bvo.setCmarbascalssid(baseclassmaps.get(cmaterid));

          String mergecode = innercodecmatermap.get(cmaterid);
          int rownum = mergecode.length() / 4;
          // �𼶻���ʱ��������ϻ�������ֻ��1��������ֵ�����С�����ᵼ�±�������������
          if (rownum == 1) {
            continue;
          }
          SaleInvoiceBVO[] newmergebvos = this.getNewmergebvos(bvo, rownum);
          UFDouble basenum = new UFDouble(4);
          for (int j = 0; j < rownum - 1; j++) {
            UFDouble level = new UFDouble(j + 1);
            String newcode =
                mergecode.substring(0, basenum.multiply(level).intValue());
            String newcmarbascalssid = newcmarbascalssidmaps.get(newcode);
            newmergebvos[j].setCmarbascalssid(newcmarbascalssid);
            /**
             * newmergebvos[j].setPrimaryKey(NULL); �����������ύ���ջ���������Ϸ����𼶻��ܻ�������
             * ��������Ϊnull��nc.vo.pubapp.pattern.model.transfer.bill.
             * ClientBillCombinServer.combineNoCloumnIndex(E clientBill,
             * ISuperVO[] childrenVO, IVOMeta voMeta) �ᱨ��֧�ִ�ҵ��
             */
            newmergebvos[j].setPrimaryKey("isnull");
            megervolist.add(newmergebvos[j]);
          }
        }
      }
      else {

        Map<String, String> baseclassmaps = null;
        if (classlevel == 0) {
          baseclassmaps = MaterialPubService.queryMaterialBaseClassPk(pks);
        }
        else {
          baseclassmaps =
              MaterialBaseClassPubService
                  .queryMarBasClassIDByClassLevelAndMaterialOIDs(classlevel,
                      pks);
        }

        for (SaleInvoiceBVO bvo : bvos) {
          String cmaterid = bvo.getCmaterialid();
          bvo.setCmarbascalssid(baseclassmaps.get(cmaterid));
        }
      }
    }
  }

  /**
   * ���������ֶ�
   * 
   * @param paravalue
   * @return
   */
  private String[] getGroupKeys(String paravalue) {
    String groupstring = paravalue.split(ParameterList.BIGSPLITKEY)[0];
    if (paravalue.startsWith(ParameterList.DOLLER)) {
      return this.transCode2Key(groupstring.substring(1, groupstring.length())
          .split(ParameterList.SPLITKEY));
    }
    return groupstring.split(ParameterList.SPLITKEY);

  }

  /**
   * ��code����Ϊkey
   * 
   * @param split
   * @return res
   */
  private String[] transCode2Key(String[] split) {
    Map<String, String> code2Key = this.getTransCodeToKey();
    int length = split.length;
    String[] res = new String[length];
    for (int i = 0; i < length; i++) {
      res[i] = code2Key.get(split[i]);
    }
    return res;
  }

  /**
   * ����vo
   * 
   * @param mergebvo �����Ƶ�VO
   * @param rownum ���Ƶĸ���
   * @return
   */
  private SaleInvoiceBVO[] getNewmergebvos(SaleInvoiceBVO mergebvo, int rownum) {
    SaleInvoiceBVO[] bvos = Constructor.construct(SaleInvoiceBVO.class, rownum);
    String[] attrinames = mergebvo.getAttributeNames();
    for (SaleInvoiceBVO bvo : bvos) {
      for (String attriname : attrinames) {
        bvo.setAttributeValue(attriname, mergebvo.getAttributeValue(attriname));
      }
    }
    return bvos;
  }

  /**
   * �ɵĵ���
   * 
   * @param retdetial
   * @return
   */
  private UFDouble getOldPrice(List<SaleInvoiceBVO> retdetial) {
    // String pricekey = SaleInvoiceCombin.getCalPriceKey();
    String mnykey = SaleInvoiceCombin.getCalMnyKey();
    UFDouble oldprice = UFDouble.ZERO_DBL;
    UFDouble oldtotalmny = UFDouble.ZERO_DBL;
    UFDouble oldtotalnum = null;
    int power = 0;
    for (SaleInvoiceBVO bvo : retdetial) {
      if (bvo.getNorigprice() != null) {// �����ۿ��У�������Ϊ��
        power = bvo.getNorigprice().getPower();
      }
      oldtotalmny =
          MathTool.add(oldtotalmny, (UFDouble) bvo.getAttributeValue(mnykey));
      oldtotalnum = MathTool.add(oldtotalnum, bvo.getNnum());
      // oldprice = MathTool.add(oldprice, bvo.getNorigprice());
    }
    if (!MathTool.isZero(oldtotalnum)) {
      oldprice =
          oldtotalmny.div(oldtotalnum).setScale(power, UFDouble.ROUND_HALF_UP);
    }
    return oldprice;
  }

  /**
   * ��Ȩƽ���ֶ�����
   * 
   * @param paravalue
   * @return
   */
  private String[] getProAvgKeys(String paravalue) {
    String keystring = paravalue.split(ParameterList.BIGSPLITKEY)[3];
    if (null != keystring && keystring.length() > 0) {
      List<String> proavgkeylist = new ArrayList<String>();
      for (String key : CombinContext.COMBIN_AVERAG) {
        proavgkeylist.add(key);
      }
      String[] keys = keystring.split(ParameterList.SPLITKEY);
      if (paravalue.startsWith(ParameterList.DOLLER)) {
        keys = this.transCode2Key(keys);
      }
      for (String key : keys) {
        proavgkeylist.add(key);
      }
      return proavgkeylist.toArray(new String[proavgkeylist.size()]);
    }

    return CombinContext.COMBIN_AVERAG;
  }

  /**
   * ����ֶ�����
   * 
   * @param paravalue
   * @return
   */
  private String[] getSumKeys(String paravalue) {
    String keystring = paravalue.split(ParameterList.BIGSPLITKEY)[1];
    if (null != keystring && keystring.length() > 0) {
      List<String> sumkeylist = new ArrayList<String>();
      for (String key : CombinContext.COMBIN_SUMKEYS) {
        sumkeylist.add(key);
      }
      String[] keys = keystring.split(ParameterList.SPLITKEY);
      if (paravalue.startsWith(ParameterList.DOLLER)) {
        keys = this.transCode2Key(keys);
      }
      for (String key : keys) {
        sumkeylist.add(key);
      }
      return sumkeylist.toArray(new String[sumkeylist.size()]);
    }
    return CombinContext.COMBIN_SUMKEYS;
  }

  /**
   * ԭ�ҽ���Ƿ����ı� �����㵥��
   * 
   * @param combvo
   * @param retdetial
   * @param oldprice
   * @return
   */
  private boolean isOrigMnyChange(SaleInvoiceBVO combvo,
      List<SaleInvoiceBVO> retdetial) {

    UFDouble oldtotaltaxmny = UFDouble.ZERO_DBL;
    UFDouble oldtotalmny = UFDouble.ZERO_DBL;
    UFDouble oldtotalnum = UFDouble.ZERO_DBL;
    for (SaleInvoiceBVO bvo : retdetial) {
      oldtotaltaxmny = MathTool.add(oldtotaltaxmny, bvo.getNorigtaxmny());
      oldtotalmny = MathTool.add(oldtotalmny, bvo.getNorigmny());
      oldtotalnum = MathTool.add(oldtotalnum, bvo.getNnum());
    }
    UFDouble nowtotaltaxmny = combvo.getNorigtaxmny();
    UFDouble nowtotalmny = combvo.getNorigmny();
    if (oldtotaltaxmny.compareTo(nowtotaltaxmny) != 0
        || oldtotalmny.compareTo(nowtotalmny) != 0) {
      return true;
    }
    return false;
  }

  /**
   * ���ҽ���Ƿ�ı�
   * 
   * @param combvo
   * @param retdetial
   * @return
   */
  private boolean isMnyChange(SaleInvoiceBVO combvo,
      List<SaleInvoiceBVO> retdetial) {

    UFDouble oldtotaltaxmny = UFDouble.ZERO_DBL;
    UFDouble oldtotalmny = UFDouble.ZERO_DBL;
    UFDouble oldtotalnum = UFDouble.ZERO_DBL;
    for (SaleInvoiceBVO bvo : retdetial) {
      oldtotaltaxmny = MathTool.add(oldtotaltaxmny, bvo.getNtaxmny());
      oldtotalmny = MathTool.add(oldtotalmny, bvo.getNmny());
      oldtotalnum = MathTool.add(oldtotalnum, bvo.getNnum());
    }
    UFDouble nowtotaltaxmny = combvo.getNtaxmny();
    UFDouble nowtotalmny = combvo.getNmny();
    if (oldtotaltaxmny.compareTo(nowtotaltaxmny) != 0
        || oldtotalmny.compareTo(nowtotalmny) != 0) {
      return true;
    }
    return false;
  }

  private boolean isNew(SaleInvoiceVO voInvoice) {
    if (null == voInvoice.getParentVO().getCsaleinvoiceid()) {
      return true;
    }
    return false;
  }

  /**
   * ����ϲ����β��
   * 
   * @param combvo
   * @param retdetail
   * @param maxsize
   * @param calc
   */
  private void processCombinMargin(SaleInvoiceBVO combvo,
      List<SaleInvoiceBVO> retdetail, int maxsize, SaleInvoiceVOCalculator calc) {
    // ��˰�ϼ�
    UFDouble nowtotalorigtaxmny = combvo.getNorigtaxmny();
    // ��˰���
    UFDouble nowtotalorigmny = combvo.getNorigmny();
    // ˰��
    UFDouble nowtotaltax = combvo.getNtax();
    // ����β��
    UFDouble remianorigtaxmny = nowtotalorigtaxmny;
    UFDouble remianorigmny = nowtotalorigmny;
    for (int i = 0; i < maxsize - 1; i++) {
      SaleInvoiceBVO detailbvo = retdetail.get(i);
      remianorigtaxmny =
          MathTool.sub(remianorigtaxmny, detailbvo.getNorigtaxmny());
      remianorigmny = MathTool.sub(remianorigmny, detailbvo.getNorigmny());
      nowtotaltax = MathTool.sub(nowtotaltax, detailbvo.getNtax());
    }
    SaleInvoiceBVO maxsizebvo = retdetail.get(maxsize - 1);
    maxsizebvo.setNorigtaxmny(remianorigtaxmny);
    maxsizebvo.setNorigmny(remianorigmny);

    // maxsizebvo.setNorigtax(MathTool.sub(remianorigtaxmny, remianorigmny));
    calc.calculate(maxsize - 1, SaleInvoiceHVO.NEXCHANGERATE);
    maxsizebvo.setNtax(nowtotaltax);
    calc.calculate(maxsize - 1, SaleInvoiceBVO.NTAX);
  }

  private List<SaleInvoiceBVO> processNew(SaleInvoiceHVO headvo,
      SaleInvoiceBVO combvo, List<SaleInvoiceBVO> cachedetbvos,
      String[] combinparary) {
    // ���˺���ϸ
    List<SaleInvoiceBVO> retdetails = new ArrayList<SaleInvoiceBVO>();
    // �������
    this.checkNumChange(headvo, combvo, cachedetbvos, retdetails, true,
        combinparary);

    // ��鵥�۽��
    this.checkNewPriceMnyChg(headvo, combvo, retdetails);

    return retdetails;
  }

  private List<SaleInvoiceBVO> processUpdate(SaleInvoiceHVO headvo,
      SaleInvoiceBVO combvo, List<SaleInvoiceBVO> cachedetbvos,
      String[] combinparary) {
    List<SaleInvoiceBVO> retdetail = new ArrayList<SaleInvoiceBVO>();
    if (VOStatus.DELETED == combvo.getStatus()) {
      for (SaleInvoiceBVO bvo : cachedetbvos) {
        bvo.setStatus(VOStatus.DELETED);
      }
    }
    else {
      // �޸ı���ʱ�������
      this.checkNumChange(headvo, combvo, cachedetbvos, retdetail, false,
          combinparary);
      // �޸ı����Ǽ�鵥�۽��
      this.checkUpdatePriceMnyChg(headvo, combvo, retdetail);
    }
    return retdetail;
  }
}
