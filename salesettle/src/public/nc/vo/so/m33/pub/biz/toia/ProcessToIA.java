package nc.vo.so.m33.pub.biz.toia;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.fi.MaterialFiVO;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.ic.transtype.TransTypeExtendVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.so.m33.ref.ic.pub.ICPubService;

/**
 * �����Ƿ񴫴�������־λ�����߼�������
 * 
 * @since 6.1
 * @version 2012-09-26 08:48:53
 * @author ��ӱ�
 */
public class ProcessToIA {

  /**
   * ��������ִ�к���
   * 
   * @param paras
   * @return Map<String, UFBoolean>
   */
  public Map<String, UFBoolean> getPubToIAResult(ProcessToIAPara[] paras) {
    // ���ϼ�ֵ����ģʽ
    Map<String, UFBoolean> mMaterialType = this.getBToIAByMaterialType(paras);
    Map<String, UFBoolean> result = new HashMap<String, UFBoolean>();
    for (ProcessToIAPara pa : paras) {
      UFBoolean bToIA = UFBoolean.TRUE;
      // �����ۿ۴��
      if (pa.isBdiscountflag() || pa.isBlaborflag()) {
        bToIA = UFBoolean.FALSE;
      }
      else {
        // ���ϼ�ֵ����ģʽΪ"�������"
        String key = pa.getFinorgoid() + pa.getMaterialvid();
        bToIA = ValueUtils.getUFBoolean(mMaterialType.get(key));
      }
      result.put(pa.getId(), bToIA);
    }
    return result;
  }

  /**
   * ����֯����ִ�к���
   * 
   * @param paras
   * @return Map<String, UFBoolean>
   */
  public Map<String, UFBoolean> getSingleToIAResult(ProcessToIAPara[] paras) {
    // �ֿ�
    Map<String, UFBoolean> mStordoc = this.getBToIAByStordoc(paras);
    // ���ⵥ��������
    Map<String, UFBoolean> mSaleOutTransType =
        this.getBToIAByOutTransType(paras);

    Map<String, UFBoolean> result = new HashMap<String, UFBoolean>();
    for (ProcessToIAPara pa : paras) {
      // �ֿ��Ƿ����������
      UFBoolean bToIA = UFBoolean.TRUE;
      if (!PubAppTool.isNull(pa.getStordocid())) {
        bToIA = ValueUtils.getUFBoolean(mStordoc.get(pa.getStordocid()));
      }

      // ��������
      String transtype = pa.getSaleOutTransType();
      if (bToIA.booleanValue() && !PubAppTool.isNull(transtype)) {
        bToIA = ValueUtils.getUFBoolean(mSaleOutTransType.get(transtype));
      }
      result.put(pa.getId(), bToIA);
    }
    return result;
  }

  private UFBoolean checkMap(String check, Map<String, String> map) {
    UFBoolean ret = UFBoolean.TRUE;
    if (!PubAppTool.isNull(check) && map.size() > 0) {
      ret = UFBoolean.valueOf(map.get(check));
    }
    return ret;
  }

  /**
   * @return <������֯oid+����vid,UFBoolean>
   */
  private Map<String, UFBoolean> getBToIAByMaterialType(ProcessToIAPara[] paras) {

    Map<String, UFBoolean> result = new HashMap<String, UFBoolean>();

    Map<String, Set<String>> mfin_mater = this.getmMaterialvid(paras);
    if (!VOChecker.isEmpty(mfin_mater)) {
      for (Entry<String, Set<String>> entry : mfin_mater.entrySet()) {
        Set<String> lmvid = entry.getValue();
        String[] mvids = lmvid.toArray(new String[lmvid.size()]);
        String finoid = entry.getKey();
        MaterialFiVO[] mfvos =
            MaterialPubService.queryMaterialFinanceInfoByPks(mvids, finoid,
                new String[] {
                  MaterialFiVO.MATERIALVALUEMGT
                });
        for (MaterialFiVO mfvo : mfvos) {
          UFBoolean flag = UFBoolean.FALSE;
          if (IMaterialEnumConst.MATERIALVALUEMGT_INVCOSTING == mfvo
              .getMaterialvaluemgt().intValue()) {
            flag = UFBoolean.TRUE;
          }
          result.put(finoid + mfvo.getPk_material(), flag);
        }
      }
    }
    return result;
  }

  private Map<String, UFBoolean> getBToIAByOutTransType(ProcessToIAPara[] paras) {

    Map<String, UFBoolean> result = new HashMap<String, UFBoolean>();

    String[] saleOutTransType = this.getSaleOutTransType(paras);
    TransTypeExtendVO[] ttEvos = null;
    if (null == saleOutTransType || saleOutTransType.length == 0) {
      return result;
    }
    try {
      ttEvos = ICPubService.queryTransType(saleOutTransType);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    if (ttEvos != null) {
      for (TransTypeExtendVO ttvo : ttEvos) {
        result.put(ttvo.getCtrantypeid(), ttvo.getBaffectcost());
      }
    }
    return result;
  }

  private Map<String, UFBoolean> getBToIAByStordoc(ProcessToIAPara[] paras) {

    Map<String, UFBoolean> result = new HashMap<String, UFBoolean>();
    String[] stordoc = this.getStordoc(paras);
    if (!VOChecker.isEmpty(stordoc)) {
      StordocVO[] stovos =
          StordocPubService.queryStordocByPks(stordoc, new String[] {
            StordocVO.ISCALCULATEDINVCOST
          });
      for (StordocVO svo : stovos) {
        UFBoolean flag = svo.getIscalculatedinvcost();
        result.put(svo.getPk_stordoc(), ValueUtils.getUFBoolean(flag));
      }
    }
    return result;
  }

  /**
   * @return <������֯oid,Set ����vid>
   */
  private Map<String, Set<String>> getmMaterialvid(ProcessToIAPara[] paras) {
    Map<String, Set<String>> mfin_mater = new HashMap<String, Set<String>>();
    for (ProcessToIAPara pa : paras) {
      String finoid = pa.getFinorgoid();
      String materialvid = pa.getMaterialvid();
      Set<String> set = mfin_mater.get(finoid);
      if (VOChecker.isEmpty(set)) {
        set = new HashSet<String>();
        mfin_mater.put(finoid, set);
      }
      set.add(materialvid);
    }
    return mfin_mater;
  }

  private String[] getSaleOutTransType(ProcessToIAPara[] paras) {
    Set<String> s_saleOutTransType = new HashSet<String>();
    for (ProcessToIAPara pa : paras) {
      String saleOutTransType = pa.getSaleOutTransType();
      if (!PubAppTool.isNull(saleOutTransType)
          && !s_saleOutTransType.contains(saleOutTransType)) {
        s_saleOutTransType.add(saleOutTransType);
      }
    }
    int size = s_saleOutTransType.size();
    String[] ret = null;
    if (size > 0) {
      ret = s_saleOutTransType.toArray(new String[size]);
    }
    return ret;
  }

  private String[] getStordoc(ProcessToIAPara[] paras) {
    Set<String> s_stordoc = new HashSet<String>();
    for (ProcessToIAPara pa : paras) {
      String stordocid = pa.getStordocid();
      if (!PubAppTool.isNull(stordocid)) {
        if (!s_stordoc.contains(stordocid)) {
          s_stordoc.add(stordocid);
        }
      }
    }
    int size = s_stordoc.size();
    String[] ret = null;
    if (size > 0) {
      ret = s_stordoc.toArray(new String[size]);
    }
    return ret;
  }

}
