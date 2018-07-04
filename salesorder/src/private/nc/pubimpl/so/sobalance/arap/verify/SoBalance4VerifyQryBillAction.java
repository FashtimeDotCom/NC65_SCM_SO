package nc.pubimpl.so.sobalance.arap.verify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.arap.pfflow.ArapBillMapVO;
import nc.vo.arap.pub.BillEnumCollection.FromSystem;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ARAPBillType;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;

import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.itf.so.m30.sobalance.ISOBalanceQuery;

import nc.pubitf.arap.gathering.IArapGatheringBillPubServiceForSCM;
import nc.pubitf.arap.receivable.IArapReceivableBillPubQueryService;

import nc.bs.framework.common.NCLocator;

public class SoBalance4VerifyQryBillAction {

  // ת������ArapBillMapVO����ΪMap.keyʱ�õ����ֶ�����
  private static final String[] ARAPVOKEY_MAPKEYCHANGE = new String[] {
    "s_billtype", "s_system", "ybye", "maptype", "ybje", "t_termid",
    "t_itemid", "t_billid", "t_billtype", "s_termid", "s_itemid", "s_billid",
    "pk_billmap", "oldje", "ts", "dr", "pk_currtype", "pk_org"
  };

  // ת����ѯ���Ľ����ArapBillMapVO����ΪMap.valueʱ�õ����ֶ�����
  private static final String[] ARAPVOKEY_MAPVALUECHANGE = new String[] {
    "t_billtype", "s_system", "ybye", "maptype", "ybje", "s_termid",
    "s_itemid", "s_billid", "s_billtype", "t_termid", "t_itemid", "t_billid",
    "pk_billmap", "oldje", "ts", "dr", "pk_currtype", "pk_org"
  };

  private static final String[] SOBALANCEBILLMAPVOKEY = new String[] {
    "s_billtype", "s_system", "ybye", "maptype", "ybje", "t_termid",
    "t_itemid", "t_billid", "t_billtype", "s_termid", "s_itemid", "s_billid",
    "pk_billmap", "oldje", "ts", "dr", "pk_currtype", "pk_org"
  };

  /**
   * 1.processSK->processRush+processVerify
   * 2.processYS->processRush+processVerify
   * <p>
   * ע�������������۶���������Ƽ�ע��;��ͨ��,�������̺���Ӧ�յ��Ĺ��̣� �������,�������൱��ע�͵�����,�����൱��ע�͵ĺ���;
   * </p>
   */
  public HashMap<ArapBillMapVO, Collection<ArapBillMapVO>> queryArapBillmap(
      ArapBillMapVO[] arVOs) throws BusinessException {
    if (arVOs == null || arVOs.length == 0) {
      return null;
    }

    // begin:ת�������۵Ĵ���VO�Ա���벻��arap�仯Ӱ��
    SoBalanceBillMapVO[] soVOs = this.changeToSoBalanceBillMapVO(arVOs, false);

    // �������Դ�����۵�Ӧ�յ���������
    if (SOVOChecker.isEmpty(soVOs)) {
      return null;
    }

    Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> retMap =
        new HashMap<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>>();
    // �������ArapBillMapVO[] mapvos�϶���һ�ֵ������ͣ��� ys--Ӧ�յ� sk--�տ
    if (IBillFieldGet.F2.equals(soVOs[0].getT_billtype())) {
      this.processSK(soVOs, retMap);
    }
    else if (IBillFieldGet.F0.equals(soVOs[0].getT_billtype())) {
      this.processYS(soVOs, retMap);
    }
    // end:�����۵Ĵ���VOMap����arap�ķ���ֵMap
    return (HashMap<ArapBillMapVO, Collection<ArapBillMapVO>>) this
        .createReturnMap(retMap);
  }

  /**
   * �㷨�������������տ��Ӧ�յ�������ϵ(�������ֶԳ�)���浽retMap�ṹ��
   * <p>
   * �ݹ����һֱ��unKeyList/unValueListһ�������ꡣ������ϵ���浽retMap��������ʣ��û�д���
   * 
   * ��valueVOs
   * </p>
   * 
   * @param unKeyList ��Ϊmap.Key��δʹ�ù���List<SoBalanceBillMapVO>
   * @param unValueList ��Ϊmap.Value��δʹ�ù���List<SoBalanceBillMapVO>
   * @param retMap �洢����ĺ�����ϵ��map
   */
  private void calculateRelation(List<SoBalanceBillMapVO> unKeyList,
      List<SoBalanceBillMapVO> unValueList,
      Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> balanceRelationMap) {
    for (SoBalanceBillMapVO unKeyVO : unKeyList) {
      for (SoBalanceBillMapVO unValueVO : unValueList) {
        SoBalanceBillMapVO newValueVO = (SoBalanceBillMapVO) unValueVO.clone();
        // --��������ϵ����balanceRelationMap
        this.injectBalanceRelationMap(unKeyVO, newValueVO, balanceRelationMap);
      }

    }
    return;
  }

  /**
   * SoBalanceBillMapVO -> ArapBillMapVO
   * 
   * @param soVOs SoBalanceBillMapVO
   */
  private ArapBillMapVO[] changeToArapBillMapVO(SoBalanceBillMapVO[] soVOs) {
    // SoBalanceBillMapVO->ArapBillMapVO:ȷ����Ӧ�ֶ�˳����һ����
    int arapMapKeyChangeLength =
        SoBalance4VerifyQryBillAction.ARAPVOKEY_MAPKEYCHANGE.length;
    int soLength = SoBalance4VerifyQryBillAction.SOBALANCEBILLMAPVOKEY.length;
    if (arapMapKeyChangeLength != soLength) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0197")/*@res "SoBalanceBillMapVOת��ArapBillMapVO�쳣"*/);
    }
    ArapBillMapVO[] retMapvo = new ArapBillMapVO[soVOs.length];
    for (int i = 0; i < soVOs.length; i++) {
      retMapvo[i] = new ArapBillMapVO();

      for (int j = 0; j < soLength; j++) {
        String arapKey =
            SoBalance4VerifyQryBillAction.ARAPVOKEY_MAPKEYCHANGE[j];
        String soKey = SoBalance4VerifyQryBillAction.SOBALANCEBILLMAPVOKEY[j];
        retMapvo[i].setAttributeValue(arapKey,
            soVOs[i].getAttributeValue(soKey));
      }
    }
    return retMapvo;
  }

  /**
   * ArapBillMapVO -> SoBalanceBillMapVO
   * <p>
   * ����: �������Ӧ�յ����ݣ���ôArapBillMapVO��t_��Ӧ�յ���s_�����۶�������
   * 
   * @param mapvos ArapBillMapVO[]
   * @param isRetMapValue �Ƿ񷵻ظ�Arap���Map�ж�Ӧvalue��ֵ��ת��
   */
  private SoBalanceBillMapVO[] changeToSoBalanceBillMapVO(
      ArapBillMapVO[] mapvos, boolean isRetMapValue) {
    // ArapBillMapVO->SoBalanceBillMapVO:ȷ����Ӧ�ֶ�˳����һ����
    int arapMapKeyChangeLength =
        SoBalance4VerifyQryBillAction.ARAPVOKEY_MAPKEYCHANGE.length;
    int arapMapValueChangeLength =
        SoBalance4VerifyQryBillAction.ARAPVOKEY_MAPVALUECHANGE.length;
    int soLength = SoBalance4VerifyQryBillAction.SOBALANCEBILLMAPVOKEY.length;
    if (arapMapKeyChangeLength != arapMapValueChangeLength
        || arapMapKeyChangeLength != soLength) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0198")/*@res "ArapBillMapVOת��SoBalanceBillMapVO�쳣"*/);
    }

    List<SoBalanceBillMapVO> list = new ArrayList<SoBalanceBillMapVO>();
    for (int i = 0; i < mapvos.length; i++) {
      SoBalanceBillMapVO retMapvo = new SoBalanceBillMapVO();
      // ��������������
      String t_billtype = mapvos[i].getT_billtype();
      // ��Դϵͳ
      int s_system = mapvos[i].getS_system();
      // �������Դ�����۵�Ӧ�յ���������
      if (IBillFieldGet.F0.equals(t_billtype)) {
        if (FromSystem.SO.VALUE.intValue() != s_system) {
          continue;
        }
        // �������۶���ID�� Ϊ�Ժ������۶�������Ӧ�յ����տ׼��
        retMapvo.setOrderid(mapvos[i].getS_billid());
      }

      for (int j = 0; j < arapMapKeyChangeLength; j++) {
        String arapKey = null;
        if (isRetMapValue) {
          arapKey = SoBalance4VerifyQryBillAction.ARAPVOKEY_MAPVALUECHANGE[j];
        }
        else {
          arapKey = SoBalance4VerifyQryBillAction.ARAPVOKEY_MAPKEYCHANGE[j];
        }
        String soKey = SoBalance4VerifyQryBillAction.SOBALANCEBILLMAPVOKEY[j];
        retMapvo.setAttributeValue(soKey, mapvos[i].getAttributeValue(arapKey));
      }
      list.add(retMapvo);
    }

    SoBalanceBillMapVO[] retMapvo = null;
    int size = list.size();
    if (size > 0) {
      retMapvo = list.toArray(new SoBalanceBillMapVO[size]);
    }
    return retMapvo;
  }

  /**
   * �ж��Ƿ�Ӧ�յ����տͬ��
   * 
   * @param newYsVO
   * @param newSkVO
   * @return Ӧ�յ����տͬ�� -- true Ӧ�յ����տ��� -- false
   */
  private boolean checkisDiffSign(SoBalanceBillMapVO newYsVO,
      SoBalanceBillMapVO newSkVO) {
    // --�ж��Ƿ�Ӧ�յ����տͬ��
    UFDouble ysmny = newYsVO.getYbye();
    UFDouble skmny = newSkVO.getYbye();
    if (MathTool.isDiffSign(ysmny, skmny)) {
      return false;
    }
    return true;
  }

  // ====================================================================
  private Map<ArapBillMapVO, Collection<ArapBillMapVO>> createReturnMap(
      Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> voMap) {
    Map<ArapBillMapVO, Collection<ArapBillMapVO>> retMap =
        new HashMap<ArapBillMapVO, Collection<ArapBillMapVO>>();
    Set<Entry<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>>> entrySet =
        voMap.entrySet();
    for (Entry<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> entry : entrySet) {
      // --ת��key
      SoBalanceBillMapVO soBalanceBillMapVO = entry.getKey();
      ArapBillMapVO arKey =
          this.changeToArapBillMapVO(new SoBalanceBillMapVO[] {
            soBalanceBillMapVO
          })[0];
      // --ת��value
      List<SoBalanceBillMapVO> soValueList =
          (List<SoBalanceBillMapVO>) entry.getValue();
      SoBalanceBillMapVO[] soValues =
          soValueList.toArray(new SoBalanceBillMapVO[soValueList.size()]);
      ArapBillMapVO[] arValues = this.changeToArapBillMapVO(soValues);

      List<ArapBillMapVO> arValueList = new ArrayList<ArapBillMapVO>();
      for (ArapBillMapVO arValue : arValues) {
        arValueList.add(arValue);
      }
      // --��װHashMap<ArapBillMapVO, Collection<ArapBillMapVO>>
      retMap.put(arKey, arValueList);
    }
    return retMap;
  }

  private void injectBalanceRelationMap(SoBalanceBillMapVO keyVO,
      SoBalanceBillMapVO valueVO,
      Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> balanceRelationMap) {
    List<SoBalanceBillMapVO> valueList =
        (List<SoBalanceBillMapVO>) balanceRelationMap.get(keyVO);
    if (valueList == null) {
      valueList = new ArrayList<SoBalanceBillMapVO>();
    }
    valueList.add(valueVO);
    balanceRelationMap.put(keyVO, valueList);
  }

  /**
   * ���balanceRelationMap��Ӧ�յ������տ��id�ظ�����Ҫ�ϲ�
   * Ŀǰ�ĳ�����һ���տ����Ϊ���key:SoBalanceBillMapVO��
   * ��Ӧ����Collection<SoBalanceBillMapVO>
   * ��Ϊһ���տ���ԺͶ��Ų�ͬ�����۶�������������ϵ
   * 
   * @param balanceRelationMap
   */
  private void processRelationMap(
      Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> balanceRelationMap) {
    List<SoBalanceBillMapVO> l_remove = new ArrayList<SoBalanceBillMapVO>();
    Map<String, Entry<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>>> mitemid =
        new HashMap<String, Entry<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>>>();
    for (Entry<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> entry : balanceRelationMap
        .entrySet()) {
      SoBalanceBillMapVO keyVO = entry.getKey();
      String t_itemid = keyVO.getT_itemid();
      Entry<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> mentry =
          mitemid.get(t_itemid);
      if (!SOVOChecker.isEmpty(mentry)) {
        mentry.getValue().addAll(entry.getValue());
        l_remove.add(keyVO);
      }
      else {
        mitemid.put(t_itemid, entry);
      }
    }

    for (SoBalanceBillMapVO vo : l_remove) {
      balanceRelationMap.remove(vo);
    }

  }

  // /**
  // * �տ/Ӧ�յ������ֶԳ�
  // * <p>
  // * �����Գ��ϵ���浽retMap��������ʣ��û�д��������vos
  // * </p>
  // *
  // * @param mapvos Ҫ�����ArapBillMapVO[]
  // * @param retMap �洢����Ĺ�ϵ��map
  // * @return ArapBillMapVO[] û�����������ArapBillMapVO[]
  // */
  // private SoBalanceBillMapVO[] processRush(SoBalanceBillMapVO[] vos,
  // Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> balanceRelationMap)
  // {
  // if (vos == null || vos.length == 0) {
  // return null;
  // }
  // // -- ��������
  // List<SoBalanceBillMapVO> redList = new ArrayList<SoBalanceBillMapVO>();
  // List<SoBalanceBillMapVO> blueList = new ArrayList<SoBalanceBillMapVO>();
  // for (SoBalanceBillMapVO vo : vos) {
  // // --���� < 0
  // if (MathTool.compareTo(vo.getYbye(), UFDouble.ZERO_DBL) < 0) {
  // redList.add(vo);
  // }
  // else {
  // blueList.add(vo);
  // }
  // }
  // List<SoBalanceBillMapVO> newBlueVOList =
  // new ArrayList<SoBalanceBillMapVO>();
  // // �к��ֵ�
  // if (redList.size() > 0) {
  // // --���㲢������ϵ
  // newBlueVOList =
  // this.calculateRelation(redList, blueList, true, balanceRelationMap);
  // }
  // // ֻ�����ֵ�
  // else {
  // newBlueVOList = blueList;
  // }
  // return newBlueVOList.toArray(new SoBalanceBillMapVO[newBlueVOList.size()]);
  // }

  private void processSK(SoBalanceBillMapVO[] skVOs,
      Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> balanceRelationMap)
      throws BusinessException {
    // --�տ->�տbids->SoBalanceViewVO->csaleorderids->Ӧ�յ�
    String[] t_itemids = SoVoTools.getVOsOnlyValues(skVOs, "t_itemid");
    ISOBalanceQuery service =
        NCLocator.getInstance().lookup(ISOBalanceQuery.class);
    SoBalanceViewVO[] views =
        service.querySoBalanceViewByGatheringBillBodyIDs(t_itemids);
    if (views == null || views.length == 0) {
      return;
    }

    // ��Ϊ1���տ���ֱܷ�Ͷ������۶�������������Ҫ���²��skVOs
    SoBalanceBillMapVO[] SkbyorderVOs = this.processSKVOs(skVOs, views);
    if (SkbyorderVOs == null || SkbyorderVOs.length == 0) {
      return;
    }

    // // 1.processRush�������տ�����Գ�
    // SoBalanceBillMapVO[] newSkVOs =
    // this.processRush(SkbyorderVOs, balanceRelationMap);
    // // --����>���֣���ʣ�������Ϊ0�������ں�Ӧ�յ�����
    // if (newSkVOs == null || newSkVOs.length == 0) {
    // return;
    // }
    // 2.processVerify�����ݶ����տ������ϵ��������map���տ��Ӧ�յ���ϵ��
    // ͬʱ�����۶���ID���õ���Ӧ���տ��
    // -- ���Ӧ�յ���ϢVO
    SoBalanceBillMapVO[] ysVOs = this.queryYsVOsBySkVOs(views);

    // // -- ����Ӧ�յ������Գ�
    // SoBalanceBillMapVO[] newYsVOs = this.processRush(ysVOs,
    // balanceRelationMap);

    // �ж��Ƿ�Ӧ�յ����տ�Ƿ�ͬ�ţ���Ų��������
    if (SOVOChecker.isEmpty(ysVOs) || SOVOChecker.isEmpty(SkbyorderVOs)
        || !this.checkisDiffSign(ysVOs[0], SkbyorderVOs[0])) {
      return;
    }

    // -- ��䶩���տ������ϵ
    this.processVerify(SkbyorderVOs, ysVOs, balanceRelationMap);
  }

  /**
   * ��Ϊ1���տ���ֱܷ�Ͷ������۶�������������Ҫ���²��skVOs
   * 
   * @param skVOs
   * @param views
   * @return
   */
  private SoBalanceBillMapVO[] processSKVOs(SoBalanceBillMapVO[] skVOs,
      SoBalanceViewVO[] views) {
    Map<String, SoBalanceBillMapVO> mapskVOs =
        new HashMap<String, SoBalanceBillMapVO>();
    for (SoBalanceBillMapVO vo : skVOs) {
      mapskVOs.put(vo.getT_itemid(), vo);
    }

    List<SoBalanceBillMapVO> skVOList = new ArrayList<SoBalanceBillMapVO>();
    for (SoBalanceViewVO view : views) {
      SoBalanceHVO head = view.getHead();
      SoBalanceBVO body = view.getBody();
      UFDouble norigordbalmny = body.getNorigordbalmny();
      UFDouble norigaccbalmny = body.getNorigaccbalmny();
      // �ɺ������
      UFDouble canbalmny = MathTool.sub(norigordbalmny, norigaccbalmny);
      if (MathTool.absCompareTo(canbalmny, UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      String t_itemid = body.getCpaybillrowid();
      SoBalanceBillMapVO oldvo = mapskVOs.get(t_itemid);
      // ����ֵvo���տ��Ϣ����s_��
      SoBalanceBillMapVO skVO = new SoBalanceBillMapVO();
      // �����տ��Ӧ�����۶���ID
      skVO.setOrderid(head.getCsaleorderid());
      skVO.setYbye(canbalmny);
      skVO.setYbje(canbalmny);
      skVO.setS_billtype(ARAPBillType.GatheringOrder.getCode());
      skVO.setS_system(FromSystem.SO.VALUE.intValue());
      skVO.setS_termid(oldvo.getS_termid());
      skVO.setS_itemid(oldvo.getS_itemid());
      skVO.setS_billid(oldvo.getS_billid());
      skVO.setDr(oldvo.getDr());
      skVO.setPk_currtype(oldvo.getPk_currtype());
      skVO.setPk_org(oldvo.getPk_org());
      skVO.setMaptype(oldvo.getMaptype());
      skVO.setT_termid(oldvo.getT_termid());
      skVO.setT_itemid(oldvo.getT_itemid());
      skVO.setT_billid(oldvo.getT_billid());
      skVO.setT_billtype(oldvo.getT_billtype());
      skVO.setPk_billmap(oldvo.getPk_billmap());
      skVO.setOldje(oldvo.getOldje());
      skVO.setTs(oldvo.getTs());
      skVOList.add(skVO);
    }
    return skVOList.toArray(new SoBalanceBillMapVO[skVOList.size()]);
  }

  private void processVerify(SoBalanceBillMapVO[] keyVOs,
      SoBalanceBillMapVO[] valueVOs,
      Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> balanceRelationMap) {
    if (keyVOs == null || keyVOs.length == 0 || valueVOs == null
        || valueVOs.length == 0) {
      return;
    }

    // �������۶������飬һ�����۶�����Ӧһ��Ӧ�յ����տ
    // <���۶���id��List<SoBalanceBillMapVO>> keyVOList
    MapList<String, SoBalanceBillMapVO> mkeyvo =
        new MapList<String, SoBalanceBillMapVO>();
    for (SoBalanceBillMapVO svo : keyVOs) {
      mkeyvo.put(svo.getOrderid(), svo);
    }
    // <���۶���id��List<SoBalanceBillMapVO>> valueVOList
    MapList<String, SoBalanceBillMapVO> mvaluevo =
        new MapList<String, SoBalanceBillMapVO>();
    for (SoBalanceBillMapVO svo : valueVOs) {
      mvaluevo.put(svo.getOrderid(), svo);
    }
    // �����۶���Ϊ���ѭ��������Ӧ�յ����տ�ĺ�����ϵ
    Map<String, List<SoBalanceBillMapVO>> mkey = mkeyvo.toMap();
    for (Entry<String, List<SoBalanceBillMapVO>> entry : mkey.entrySet()) {
      String orderid = entry.getKey();
      List<SoBalanceBillMapVO> keyVOList = entry.getValue();
      List<SoBalanceBillMapVO> valueVOList = mvaluevo.get(orderid);
      if (SOVOChecker.isEmpty(valueVOList)) {
        continue;
      }
      // ���㲢������ϵ
      this.calculateRelation(keyVOList, valueVOList, balanceRelationMap);
    }

    // ���balanceRelationMap��Ӧ�յ������տ��id�ظ�����Ҫ�ϲ�
    this.processRelationMap(balanceRelationMap);

  }

  private void processYS(SoBalanceBillMapVO[] ysVOs,
      Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> balanceRelationMap)
      throws BusinessException {
    // 1.processRush������Ӧ�յ������Գ�
    // SoBalanceBillMapVO[] newYsVOs = this.processRush(ysVOs,
    // balanceRelationMap);
    // // --����>���֣���ʣ�������Ϊ0�������ں��տ����
    // if (newYsVOs == null || newYsVOs.length == 0) {
    // return;
    // }
    // 2.processVerify�����ݶ����տ������ϵ��������map���տ��Ӧ�յ���ϵ
    // -- ����տ��ϢVO
    SoBalanceBillMapVO[] skVOs = this.querySkVOsByYsVOs(ysVOs);
    // -- �����տ�����Գ�
    // SoBalanceBillMapVO[] newSkVOs = this.processRush(skVOs,
    // balanceRelationMap);

    // �ж��Ƿ�Ӧ�յ����տ�Ƿ�ͬ�ţ���Ų��������
    if (SOVOChecker.isEmpty(ysVOs) || SOVOChecker.isEmpty(skVOs)
        || !this.checkisDiffSign(ysVOs[0], skVOs[0])) {
      return;
    }

    // -- ��䶩���տ������ϵ
    this.processVerify(ysVOs, skVOs, balanceRelationMap);
  }

  /**
   * ����Ӧ�յ�SoBalanceBillMapVO��ѯ�ɽ�����ϵ���տSoBalanceBillMapVO
   * 
   * @param ysVOs ysVO����Դ = Ӧ�յ���Դͷ,�����۶���
   * @throws BusinessException
   */
  private SoBalanceBillMapVO[] querySkVOsByYsVOs(SoBalanceBillMapVO[] ysVOs)
      throws BusinessException {
    // --Ӧ�յ�->csaleorderids->SoBalanceVO->�տ
    String[] csaleorderids = new String[ysVOs.length];
    for (int i = 0; i < ysVOs.length; i++) {
      csaleorderids[i] = ysVOs[i].getS_billid();
    }
    ISOBalanceQuery service =
        NCLocator.getInstance().lookup(ISOBalanceQuery.class);
    SoBalanceVO[] vos = service.querySoBalanceVOBySaleOrderIDs(csaleorderids);
    if (vos == null || vos.length == 0) {
      return null;
    }
    Set<String> hids = new HashSet<String>();
    // �տID
    for (SoBalanceVO vo : vos) {
      SoBalanceBVO[] bvos = vo.getChildrenVO();
      for (SoBalanceBVO bvo : bvos) {
        hids.add(bvo.getCpaybillid());
      }
    }
    // ��ѯ�Ѿ���Ч���տIDS
    IArapGatheringBillPubServiceForSCM srv =
        NCLocator.getInstance()
            .lookup(IArapGatheringBillPubServiceForSCM.class);
    List<String> effectids =
        srv.getEffectGatherBill(hids.toArray(new String[hids.size()]));

    List<SoBalanceBillMapVO> skVOList = new ArrayList<SoBalanceBillMapVO>();
    for (SoBalanceVO vo : vos) {
      SoBalanceHVO head = vo.getParentVO();
      SoBalanceBVO[] bodys = vo.getChildrenVO();
      for (SoBalanceBVO body : bodys) {
        // �տδ��Ч
        if (!effectids.contains(body.getCpaybillid())) {
          continue;
        }
        UFDouble norigordbalmny = body.getNorigordbalmny();
        UFDouble norigaccbalmny = body.getNorigaccbalmny();
        // �ɺ������
        UFDouble canbalmny = MathTool.sub(norigordbalmny, norigaccbalmny);
        if (MathTool.absCompareTo(canbalmny, UFDouble.ZERO_DBL) == 0) {
          continue;
        }
        // ����ֵvo���տ��Ϣ����s_��
        SoBalanceBillMapVO skVO = new SoBalanceBillMapVO();
        // �����տ��Ӧ�����۶���ID
        skVO.setOrderid(head.getCsaleorderid());
        skVO.setS_billtype(ARAPBillType.GatheringOrder.getCode());
        skVO.setS_system(FromSystem.SO.VALUE.intValue());
        skVO.setS_termid(null);
        skVO.setS_itemid(body.getCpaybillrowid());
        skVO.setS_billid(body.getCpaybillid());
        skVO.setDr(Integer.valueOf(0));
        skVO.setPk_currtype(head.getCorigcurrencyid());
        skVO.setPk_org(head.getPk_org());
        skVO.setYbye(canbalmny);
        skVO.setYbje(canbalmny);
        skVO.setMaptype(0);
        skVO.setT_termid(null);
        skVO.setT_itemid(null);
        skVO.setT_billid(null);
        skVO.setT_billtype(null);
        skVO.setPk_billmap(null);
        skVO.setOldje(null);
        skVO.setTs(null);

        skVOList.add(skVO);
      }
    }
    return skVOList.toArray(new SoBalanceBillMapVO[skVOList.size()]);
  }

  /**
   * �����տSoBalanceBillMapVO��ѯ�ɽ�����ϵ��Ӧ�յ�SoBalanceBillMapVO
   * ͬʱ�����۶���ID���õ���Ӧ���տ��
   */
  private SoBalanceBillMapVO[] queryYsVOsBySkVOs(SoBalanceViewVO[] views)
      throws BusinessException {
    String[] csaleorderids = null;
    Set<String> csaleorderidSet = new HashSet<String>();
    for (SoBalanceViewVO view : views) {
      String orderid = view.getHead().getCsaleorderid();
      csaleorderidSet.add(orderid);
    }
    csaleorderids = csaleorderidSet.toArray(new String[csaleorderidSet.size()]);
    // --��ѯӦ�յ���Ϣ
    IArapReceivableBillPubQueryService arapService =
        NCLocator.getInstance()
            .lookup(IArapReceivableBillPubQueryService.class);
    ArapBillMapVO[] ysVOs = arapService.queryArapBillmap(csaleorderids);
    return this.changeToSoBalanceBillMapVO(ysVOs, true);
  }

}
