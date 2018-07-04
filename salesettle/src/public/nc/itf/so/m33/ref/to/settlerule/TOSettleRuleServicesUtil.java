package nc.itf.so.m33.ref.to.settlerule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pcto.settlerule.so.ISettleRuleMatchForSOService;
import nc.pubitf.to.settlerule.ic.MatchSettleRuleResult;
import nc.pubitf.to.settlerule.so.IMatchSettleruleServiceForSo;
import nc.pubitf.to.settlerule.so.MatchSettleRuleVOForSo;
import nc.vo.pcto.settlerule.para.MatchSettleRuleParaVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class TOSettleRuleServicesUtil {

  private TOSettleRuleServicesUtil() {
    super();
  }

  /**
   * 
   * �����������������۽��㵥���������֯����������֯�ͽ��������֯��һ��
   * <p>
   * ��ʱ�����TO�ڲ��������Ľӿ�ȡ�ɱ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 ����02:26:47
   */
  public static Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> matchSettlerule(
      List<MatchSettleRuleVOForSo> vos) throws BusinessException {
    if (!SysInitGroupQuery.isTOEnabled()) {
      new HashMap<MatchSettleRuleVOForSo, MatchSettleRuleResult>();
    }
    IMatchSettleruleServiceForSo bo =
        NCLocator.getInstance().lookup(IMatchSettleruleServiceForSo.class);
    return bo.matchSettleruleForSo(vos);
  }

  /**
   * ���۽��㷢���������ĺͽ����������Ĳ�һ�µ���ʱ������ڲ��������ӿڣ������ж��Ƿ񴫴��
   * 
   * @param vos
   * @return
   * @throws BusinessException
   */
  public static Map<MatchSettleRuleParaVO, UFBoolean> matchSettleRule(
      MatchSettleRuleParaVO[] vos)  {
    ISettleRuleMatchForSOService bo =
        NCLocator.getInstance().lookup(ISettleRuleMatchForSOService.class);
    Map<MatchSettleRuleParaVO, UFBoolean> settlerule =
        new HashMap<MatchSettleRuleParaVO, UFBoolean>();
    try {
      settlerule = bo.matchSettleRuleForSO(vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return settlerule;
  }

}
