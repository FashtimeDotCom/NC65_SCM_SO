package nc.bs.so.m33.biz.m4c.rule.toar;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * ��Ӧ��ǰ ��ȡӦ����֯������֯�汾
 * @scene
 * ���۳��ⵥ��Ӧ�ա����س�Ӧ�ա�����Գ崫�س�Ӧ��ǰ 
 * @param 
 * ��
 * @author zhangcheng
 * 
 */
public class GetNewARorgVidFor4CRule implements IRule<SquareOutVO> {

  @Override
  public void process(SquareOutVO[] vos) {
    SquareOutBVO[] bvos = SquareOutVOUtils.getInstance().getSquareOutBVO(vos);

    String[] arids = SoVoTools.getVOsOnlyValues(bvos, SquareOutBVO.CARORGID);

    if (arids == null || arids.length == 0) {
      return;
    }

    Map<String, String> omap = OrgUnitPubService.getOrgVid(arids);

    for (SquareOutBVO bvo : bvos) {
      bvo.setCarorgvid(omap.get(bvo.getCarorgid()));
    }
  }

}
