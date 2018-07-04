package nc.vo.so.pub.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialConvertVO;
import nc.vo.bd.pub.BDCacheQueryUtil;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class SOUnitChangeRateRule {

  private IKeyValue keyValue;

  /**
   * UnitChangeRateHandler �Ĺ�����
   * 
   * @param keyValue
   */
  public SOUnitChangeRateRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ������������������ҵ��λ������λ֮�任���ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-26 ����10:31:34
   */
  public void calcAstChangeRate(int index) {
    String astChangeRate = this.calcChangeRate(index, SOItemKey.CASTUNITID);
    this.keyValue.setBodyValue(index, SOItemKey.VCHANGERATE, astChangeRate);
  }

  /**
   * ����������������������ҵ��λ������λ�����۵�λ������λ֮�任���ʡ�
   * 
   * @param rows ������
   * @author zhangby5
   */
  public void calcAstAndQtChangeRate(int[] rows) {
    Map<String, MaterialConvertVO> ratemap =
        this.getMaterMeasddocByPK(rows, MaterialConvertVO.MEASRATE);
    this.processRateMap(ratemap, rows);
  }

  /**
   * �����������������㱨�۵�λ������λ֮�任���ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   *          <p>
   * @author ��ӱ�
   * @time 2010-4-26 ����01:22:59
   */
  public void calcQtChangeRate(int index) {
    String qtChangeRate = this.calcChangeRate(index, SOItemKey.CQTUNITID);
    this.keyValue.setBodyValue(index, SOItemKey.VQTUNITRATE, qtChangeRate);
  }

  /**
   * ���������������ӱ��index�У�ҵ��λ������λ�Ƿ�̶������ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @param editunitkey
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-26 ����05:53:19
   */
  public boolean isAstFixedRate(int index) {
    return this.isFixedRate(index, SOItemKey.CASTUNITID);
  }

  /**
   * ���������������ӱ��index�У����۵�λ������λ�Ƿ�̶������ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @param editunitkey
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-26 ����05:53:51
   */
  public boolean isQtFixedRate(int index) {
    return this.isFixedRate(index, SOItemKey.CQTUNITID);
  }

  /**
   * ��������������������ѯ���۵�λ������λ��ҵ��λ������λ֮���Ƿ�̶������ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @author zhangby5
   * @time 2015-5-5 ����05:53:51
   */
  public Map<String, UFBoolean> isAstAndQtFixedRate(int[] rows) {
    Map<String, MaterialConvertVO> fixedMap =
        this.getMaterMeasddocByPK(rows, MaterialConvertVO.FIXEDFLAG);
    Map<String, UFBoolean> unit_fixedmap = new HashMap<String, UFBoolean>();
    for (int row : rows) {
      String cmaterialvid =
          keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      String cunitid = keyValue.getBodyStringValue(row, SOItemKey.CUNITID);
      String castunitid =
          keyValue.getBodyStringValue(row, SOItemKey.CASTUNITID);
      String cqtunitid = keyValue.getBodyStringValue(row, SOItemKey.CQTUNITID);
      String materastunit = cmaterialvid + castunitid;
      String materqunit = cmaterialvid + cqtunitid;
      // ����+����λ�Ƿ��ǹ̶�������nc.itf.scmpub.reference.uap.bd.material.MaterialPubService
      if (StringUtil.isEmpty(cunitid) || StringUtil.isEmpty(castunitid)) {
        unit_fixedmap.put(materastunit, UFBoolean.FALSE);
      }
      if (PubAppTool.isEqual(cunitid, castunitid)) {
        unit_fixedmap.put(materastunit, UFBoolean.TRUE);
      }
      else {
        unit_fixedmap.put(materastunit,
            fixedMap.get(materastunit) == null ? UFBoolean.FALSE : fixedMap
                .get(materastunit).getFixedflag());
      }

      // ����+���۵�λ�Ƿ��ǹ̶�������
      if (StringUtil.isEmpty(cunitid) || StringUtil.isEmpty(cqtunitid)) {
        unit_fixedmap.put(materastunit, UFBoolean.FALSE);
      }
      if (PubAppTool.isEqual(cunitid, cqtunitid)) {
        unit_fixedmap.put(materqunit, UFBoolean.TRUE);
      }
      else {
        unit_fixedmap.put(
            materqunit,
            fixedMap.get(materqunit) == null ? UFBoolean.FALSE : fixedMap.get(
                materqunit).getFixedflag());
      }
    }
    return unit_fixedmap;
  }

  /**
   * �����������������㴫��༭��λ������λ֮�任���ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @param editunitkey
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-26 ����01:23:21
   */
  private String calcChangeRate(int index, String editunitkey) {

    String material =
        this.keyValue.getBodyStringValue(index, SOItemKey.CMATERIALVID);
    String unit = this.keyValue.getBodyStringValue(index, SOItemKey.CUNITID);
    String editunit = this.keyValue.getBodyStringValue(index, editunitkey);

    String changerate = null;

    changerate =
        MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(material,
            unit, editunit);

    return changerate;

  }

  /**
   * �������������������index�У����뵥λ������λ�Ƿ�̶������ʡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param index
   * @param unitkey
   * @return <p>
   * @author ��ӱ�
   * @time 2010-4-26 ����05:58:01
   */
  private boolean isFixedRate(int index, String unitkey) {

    String material =
        this.keyValue.getBodyStringValue(index, SOItemKey.CMATERIALVID);
    String unit = this.keyValue.getBodyStringValue(index, SOItemKey.CUNITID);
    String tounit = this.keyValue.getBodyStringValue(index, unitkey);

    boolean isfixed = false;
    isfixed =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material, unit,
            tounit);

    return isfixed;

  }

  /**
   * ������ȡָ���ֶε�MaterialConvertVO
   * 
   * @param rows
   * @param itemKey
   * @return
   */
  private Map<String, MaterialConvertVO> getMaterMeasddocByPK(int[] rows,
      String itemKey) {
    Set<String> setmaterid = new HashSet<String>();
    Set<String> setunitid = new HashSet<String>();
    for (int row : rows) {
      String cmaterialvid =
          keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      String castunitid =
          keyValue.getBodyStringValue(row, SOItemKey.CASTUNITID);
      String cqtunitid = keyValue.getBodyStringValue(row, SOItemKey.CQTUNITID);
      String cunitid = keyValue.getBodyStringValue(row, SOItemKey.CUNITID);
      // ��λ�ͱ��۵�λ��Ϊ�յ����������Ҫ������λҲ��ӽ���
      setmaterid.add(cmaterialvid);
      setunitid.add(castunitid);
      setunitid.add(cqtunitid);
      setunitid.add(cunitid);
    }
    if (setunitid.size() == 0) {
      return new HashMap<String, MaterialConvertVO>();
    }
    Map<String, MaterialConvertVO> measdocMap =
        this.queryMeasVOByMaterialAndMeasdoc(setmaterid.toArray(new String[0]),
            setunitid.toArray(new String[0]), itemKey);
    return measdocMap;
  }

  /**
   * ��ѯ���ϼ�������VO��Ϣ������֯��key������PK+������λPK value:ָ��itemKey��MaterialConvertVO
   * 
   * @param pk_material
   * @param pk_measdoc
   * @param itemKey
   * @return
   */
  private Map<String, MaterialConvertVO> queryMeasVOByMaterialAndMeasdoc(
      String[] pk_material, String[] pk_measdoc, String itemKey) {
    HashMap<String, Object[]> paramField_paramValues_map =
        new HashMap<String, Object[]>();
    paramField_paramValues_map.put(MaterialConvertVO.PK_MATERIAL, pk_material);
    paramField_paramValues_map.put(MaterialConvertVO.PK_MEASDOC, pk_measdoc);
    MaterialConvertVO[] materialConvertVOs = null;
    try {
      materialConvertVOs =
          (MaterialConvertVO[]) BDCacheQueryUtil.queryVOs(
              MaterialConvertVO.class, new String[] {
                MaterialConvertVO.PK_MATERIAL, MaterialConvertVO.PK_MEASDOC,
                itemKey
              }, paramField_paramValues_map);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.toString());
    }
    materialConvertVOs =
        BDCacheQueryUtil.filterNullVO(MaterialConvertVO.class,
            materialConvertVOs);
    Map<String, MaterialConvertVO> ratemap =
        new HashMap<String, MaterialConvertVO>();
    for (MaterialConvertVO vo : materialConvertVOs) {
      String keymatermasdoc = vo.getPk_material() + vo.getPk_measdoc();
      ratemap.put(keymatermasdoc, vo);
    }
    return ratemap;
  }

  private void processRateMap(Map<String, MaterialConvertVO> ratemap, int[] rows) {
    for (int row : rows) {
      String cmaterialvid =
          keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      String cunitid = keyValue.getBodyStringValue(row, SOItemKey.CUNITID);
      String castunitid =
          keyValue.getBodyStringValue(row, SOItemKey.CASTUNITID);
      String cqtunitid = keyValue.getBodyStringValue(row, SOItemKey.CQTUNITID);

      // ����λ�͵�λ��Ļ�����
      if (PubAppTool.isEqual(cunitid, castunitid)) {
        // ��������λ���ڱ��۵�λ���������λ�����ϴ��ڻ����ʣ�����λ�����ϲ����ڻ����ʣ����ֱ�Ӳ�ѯ���ۻ��ʻ�ȡ������
        this.keyValue.setBodyValue(row, SOItemKey.VCHANGERATE,
            MaterialPubService.CHANGERATE_ONE);
      }
      else {
        String keycastunitid = cmaterialvid + castunitid;
        this.keyValue.setBodyValue(
            row,
            SOItemKey.VCHANGERATE,
            ratemap.get(keycastunitid) == null ? null : ratemap.get(
                keycastunitid).getMeasrate());
      }

      // ����λ�ͱ��۵�λ��Ļ�����
      if (PubAppTool.isEqual(cunitid, cqtunitid)) {
        this.keyValue.setBodyValue(row, SOItemKey.VQTUNITRATE,
            MaterialPubService.CHANGERATE_ONE);
      }
      else {
        String keycqtunitid = cmaterialvid + cqtunitid;
        this.keyValue.setBodyValue(row, SOItemKey.VQTUNITRATE, ratemap
            .get(keycqtunitid) == null ? null : ratemap.get(keycqtunitid)
            .getMeasrate());
      }
    }
  }
}
