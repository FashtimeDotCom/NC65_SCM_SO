package nc.pubimpl.so.m4331.pub;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.util.ViewVOUtil;

/**
 * ���ݵ���������Ϣ ��øõ��ݵ�״̬
 * 
 * @since 6.0
 * @version 2011-2-24 ����03:40:53
 * @author ף����
 */
public class StateTool {
  // �Ƿ�һ�γ���
  private UFBoolean boneceflag;

  // ����
  private String id;

  // ����������
  private UFDouble num;

  private Object obj;

  // ��������¼���ѳ�������
  private UFDouble oldOutNum;

  // ����ԭ����״̬
  private UFBoolean oldState;

  private final UFDouble percent = new UFDouble(0.01);

  // ����
  private String pk_material;

  // ���λ�д��ʵ�ʳ��������仯ֵ
  private UFDouble reOutNum;

  /*
   * ���浥�ݺź͸õ��ݶ�Ӧ�ı仯���״̬
   */
  private Map<String, UFBoolean> stateMap;

  /*
   *  ���浥��id��Ҫ��д��Դ���ݵı仯ֵ
   */
  private Map<String, UFDouble> valueMap;

  private RewriteValueUtil valueUtil;

  private RewriteVOUtil voUtil;

  private Map<String, UFBoolean> typeInfoMap = new HashMap<String, UFBoolean>();

  public StateTool(RewriteVOUtil voutil, RewriteValueUtil valueutil) {
    this.valueUtil = valueutil;
    this.voUtil = voutil;
  }

  /**
   * ��õ��ݵ�״̬
   * 
   * @return
   */
  public Map<String, UFBoolean> getState(Object[] vopara, Object[] viewpara) {
    if (null == this.stateMap) {
      this.stateMap = new HashMap<String, UFBoolean>();
      this.valueMap = new HashMap<String, UFDouble>();
      if (null != vopara && vopara instanceof DeliveryCheckVO[]) {
        this.initQualityState((DeliveryCheckVO[]) vopara);
      }
      if (null != viewpara && viewpara instanceof DeliveryViewVO[]) {
        this.initDeliveryState((DeliveryViewVO[]) viewpara);
      }
    }
    return this.stateMap;
  }

  public Map<String, UFDouble> getValueForRewriteSrc(Object[] vopara,
      Object[] viewpara) {
    if (null != this.valueMap) {
      return this.valueMap;
    }
    this.getState(vopara, viewpara);
    return this.valueMap;
  }

  /*
   * �������ر�����������ֵ
   */
  private UFDouble getLowerNum(Map<String, MaterialVO> materialMap) {
    MaterialVO materialvo = materialMap.get(this.pk_material);
    UFDouble lowlimit = materialvo.getOutcloselowerlimit();
    lowlimit = lowlimit.multiply(this.percent);
    lowlimit = MathTool.sub(UFDouble.ONE_DBL, lowlimit);
    // �ر�����
    UFDouble lowerNum = this.num.multiply(lowlimit);
    return lowerNum;
  }

  /*
   * ��������Ϊ��һ�γ���ر� ��õ��ݱ仯���״̬ 
   * @return
   */
  private UFBoolean getStateForNoOnece(Map<String, MaterialVO> materialMap) {
    UFBoolean state = null;
    UFDouble totalOutNum = MathTool.add(this.oldOutNum, this.reOutNum);
    if (totalOutNum.compareTo(UFDouble.ZERO_DBL) == 0) {
      state = UFBoolean.FALSE;
    }
    else {
      UFDouble lowerNum = this.getLowerNum(materialMap);
      boolean expr1 = this.oldState.booleanValue();
      boolean expr2 = totalOutNum.abs().compareTo(lowerNum.abs()) >= 0;
      boolean expr3 = this.oldOutNum.abs().compareTo(lowerNum.abs()) < 0;
      if (!expr1 && expr2) {
        // ԭ����״̬Ϊ�򿪣������ۼƳ�����������ڻ���ڹر�����
        state = UFBoolean.TRUE;
      }
      else if (!expr1 && !expr2) {
        // ԭ����״̬Ϊ�򿪣������ۼƳ���������С�ڹر�����
        state = UFBoolean.FALSE;
      }
      else if (expr3) {
        // ԭ����״̬Ϊ�ر�״̬ ��ԭ���ۼƿ�����С�ڹر����ޣ�Ϊ�ֶ��رգ�ֻ���ֶ��򿪣�
        state = UFBoolean.TRUE;
      }
      else if (!expr3 && expr2) {
        /*
         * ԭ��״̬Ϊ�ر�״̬��ԭ���ۼƿ�������С�ڹر�����
         * ���ұ仯����ۼƳ����������ڻ��ߵ��ڹر�����
         */
        state = UFBoolean.TRUE;
      }
      else {
        /*
         * ԭ��״̬Ϊ�ر�״̬��ԭ���ۼƿ�������С�ڹر�����
         * ���ұ仯����ۼƳ�������С�ڹر�����
         */
        state = UFBoolean.FALSE;
      }
    }
    this.updateQualityInfo(totalOutNum, state);
    return state;
  }

  /*
   * ��������Ϊһ�γ���رգ���õ��ݱ仯���״̬ 
   * @return
   */
  private UFBoolean getStateForOnece() {
    UFBoolean state = null;
    UFDouble totalOutNum = MathTool.add(this.oldOutNum, this.reOutNum);
    if (totalOutNum.compareTo(UFDouble.ZERO_DBL) == 0) {
      state = UFBoolean.FALSE;
    }
    else {
      state = UFBoolean.TRUE;
    }
    this.updateQualityInfo(totalOutNum, state);
    return state;
  }

  private UFDouble getUFDoubleValue(Object object) {
    UFDouble value = ValueUtils.getUFDouble(object);
    if (null == value) {
      return UFDouble.ZERO_DBL;
    }
    return value;
  }

  /**
   * ��״̬�ı�ʱ����û�д��Դ���ݵ�����
   * 
   * @param state
   * @return
   */
  private UFDouble getValueStateChange(UFBoolean state) {
    if (!state.booleanValue()) {
      // ԭ�������ǹر� ����״̬�Ǵ�
      return MathTool.sub(this.num, this.oldOutNum);
    }
    // ԭ����״̬�Ǵ� ����״̬�ǹر�
    UFDouble totaloutnum = MathTool.add(this.oldOutNum, this.reOutNum);
    return MathTool.sub(totaloutnum, this.num);
  }

  public Map<String, MaterialVO> getMaterials(String[] pk_materials) {
    try {
      String[] str = new String[2];
      // ���ϳ���ر�����
      str[0] = MaterialVO.OUTCLOSELOWERLIMIT;
      // ���Ϲر��ݲ�
      str[1] = MaterialVO.OUTTOLERANCE;
      Map<String, MaterialVO> map =
          MaterialPubService.queryMaterialBaseInfo(pk_materials, str);
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return null;
  }

  /**
   * ��ʼ������������Ϣ
   * 
   * @param views
   */
  private void initDeliveryState(DeliveryViewVO[] views) {
    String[] cmaterialvids =
        ViewVOUtil.getDistinctFieldArray(views, DeliveryBVO.class,
            SOItemKey.CMATERIALVID, String.class);
    Map<String, MaterialVO> materialMap = this.getMaterials(cmaterialvids);
    for (DeliveryViewVO view : views) {
      if (this.stateMap.containsKey(view.getItem().getCdeliverybid())) {
        continue;
      }
      this.initViewData(view);
      String bid = view.getItem().getCdeliverybid();
      if (!this.boneceflag.booleanValue()) {
        // �������ͷ�һ���Գ���ر�
        UFBoolean state = this.getStateForNoOnece(materialMap);
        this.stateMap.put(bid, state);
        continue;
      }
      // ��������Ϊһ�γ���ر�
      UFBoolean state = this.getStateForOnece();
      this.stateMap.put(bid, state);
    }
  }

  /**
   * ��ʼ���ʼ���Ϣ�ĵ�״̬
   * 
   * @param vos
   */
  private void initQualityState(DeliveryCheckVO[] vos) {
    Set<String> cmaterialvids = new HashSet<String>();
    for (DeliveryCheckVO vo : vos) {
      cmaterialvids.add(vo.getCmaterialvid());
    }

    Map<String, MaterialVO> materialMap =
        this.getMaterials(cmaterialvids.toArray(new String[0]));
    for (DeliveryCheckVO vo : vos) {
      if (this.stateMap.containsKey(vo.getCdeliverycid())) {
        continue;
      }
      this.initVOData(vo);
      String cid = vo.getCdeliverycid();
      if (!this.boneceflag.booleanValue()) {
        // ��������Ϊһ�γ���ر�
        UFBoolean state = this.getStateForNoOnece(materialMap);
        this.stateMap.put(cid, state);
        continue;
      }
      // �������ͷ�һ���Գ���ر�
      UFBoolean state = this.getStateForOnece();
      this.stateMap.put(cid, state);
    }
  }

  private void initValueMapForSrc(UFBoolean newState) {
    boolean expr1 = this.oldState.booleanValue();
    boolean expr2 = newState.booleanValue();
    if (!expr1 && !expr2) {
      // ԭ��״̬�Ǵ� ���º�״̬�ޱ仯 ��д��Դ����Ϊ0
      this.valueMap.put(this.id, UFDouble.ZERO_DBL);
    }
    else if (!expr1 && expr2 || expr1 && !expr2) {
      // ԭ��״̬�Ǵ򿪣����º�״̬Ϊ�ر�״̬
      UFDouble value = this.getValueStateChange(newState);
      this.valueMap.put(this.id, value);
    }
    else if (expr1 && expr2) {
      this.valueMap.put(this.id, this.reOutNum);
    }
  }

  /*
   * ���ݷ�������ͼvo��ʼ����Ӧ���� 
   * @param view
   */
  private void initViewData(DeliveryViewVO view) {
    // ����Դ��Ϣ����Ϊ��
    this.valueUtil = new RewriteValueUtil();
    this.obj = view;
    this.id = view.getItem().getCdeliverybid();
    this.num = this.getUFDoubleValue(view.getItem().getNnum());
    this.oldOutNum = this.getUFDoubleValue(view.getItem().getNtotaloutnum());
    this.oldState = view.getItem().getBoutendflag();
    this.pk_material = view.getItem().getCmaterialvid();
    String srcBilltype = view.getItem().getVsrctype();
    UFDouble rewriteNum = this.valueUtil.getRewriteNum(this.id, srcBilltype);
    this.reOutNum = this.getUFDoubleValue(rewriteNum);
    String billtype = view.getHead().getVtrantypecode();
    if (!this.typeInfoMap.containsKey(billtype)) {
      this.typeInfoMap.put(billtype, this.valueUtil
          .getBilltypeInfo(this.voUtil).get(billtype));

    }
    this.boneceflag = this.typeInfoMap.get(billtype);
    // �����ۼ�Ӧ��δ��������
    this.setNoOutNumForView(view, srcBilltype);
  }

  /*
   * �����ʼ���Ϣvo ��ʼ����Ӧ���� 
   * @param vo
   */
  private void initVOData(DeliveryCheckVO vo) {
    this.obj = vo;
    this.id = vo.getCdeliverycid();
    this.num = this.getUFDoubleValue(vo.getNnum());
    this.oldOutNum = this.getUFDoubleValue(vo.getNtotaloutnum());
    this.oldState = vo.getBoutendflag();
    this.pk_material = vo.getCmaterialvid();
    DeliveryViewVO view =
        this.voUtil.getRewriteViewVOOnCheck().get(vo.getCdeliverybid());
    String srcBilltype = view.getItem().getVsrctype();
    // ʵ�ʳ��������ı仯��
    UFDouble outNum = this.valueUtil.getRewriteNum(this.id, srcBilltype);
    this.reOutNum = this.getUFDoubleValue(outNum);
    String billtype = view.getHead().getVtrantypecode();
    this.boneceflag = this.valueUtil.getBilltypeInfo(this.voUtil).get(billtype);
    this.setNoOutNumForVo(view, srcBilltype, vo);
  }

  /**
   * ���÷������ӱ��ۼ�Ӧ��δ����������
   * 
   * @param view
   * @param srcBilltype
   */
  private void setNoOutNumForView(DeliveryViewVO view, String srcBilltype) {
    UFDouble totalNoOutNum = view.getItem().getNtotalnotoutnum();
    UFDouble noOutNum = this.valueUtil.getRewriteNoNum(this.id, srcBilltype);
    totalNoOutNum = this.getUFDoubleValue(totalNoOutNum);
    noOutNum = this.getUFDoubleValue(noOutNum);
    UFDouble value = MathTool.add(totalNoOutNum, noOutNum);
    view.getItem().setNtotalnotoutnum(value);
  }

  /**
   * �����ʼ���Ϣ���ۼ�Ӧ��δ����������
   * 
   * @param view
   * @param srcBilltype
   * @param vo
   */
  private void setNoOutNumForVo(DeliveryViewVO view, String srcBilltype,
      DeliveryCheckVO vo) {
    UFDouble oldNoOut = vo.getNtotalnotoutnum();
    UFDouble reNoOut = this.valueUtil.getRewriteNoNum(this.id, srcBilltype);
    UFDouble totalNoOut = view.getItem().getNtotalnotoutnum();
    oldNoOut = this.getUFDoubleValue(oldNoOut);
    reNoOut = this.getUFDoubleValue(reNoOut);
    totalNoOut = this.getUFDoubleValue(totalNoOut);
    UFDouble value = MathTool.add(reNoOut, oldNoOut);
    vo.setNtotalnotoutnum(value);
    value = MathTool.add(totalNoOut, reNoOut);
    view.getItem().setNtotalnotoutnum(value);
  }

  /*
   * �����ۼƳ��������ͳ���״̬
   */
  private void updateQualityInfo(UFDouble totaloutnum, UFBoolean state) {
    UFDouble value = totaloutnum;
    if (totaloutnum.compareTo(UFDouble.ZERO_DBL) != 0) {
      value = totaloutnum;
    }
    if (this.obj instanceof DeliveryCheckVO) {
      DeliveryCheckVO vo = (DeliveryCheckVO) this.obj;
      vo.setNtotaloutnum(value);
      vo.setBoutendflag(state);
    }
    if (this.obj instanceof DeliveryViewVO) {
      this.initValueMapForSrc(state);
      DeliveryViewVO view = (DeliveryViewVO) this.obj;
      view.getItem().setNtotaloutnum(value);
      view.getItem().setBoutendflag(state);
    }
  }
}
