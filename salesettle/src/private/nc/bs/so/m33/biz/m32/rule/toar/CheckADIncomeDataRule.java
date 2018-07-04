package nc.bs.so.m33.biz.m32.rule.toar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.ic.m4453.ICM4453ServiceUtil;
import nc.itf.so.m33.ref.ic.m4c.ICM4CServiceUtil;
import nc.pubitf.so.ic.m4c.ISaleFor4CParaQuery;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.paravo.Para4CFor32SignBiz;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * ��Ӧ������ҵ��ǰУ��:
 * 1.���۷�Ʊ���ε�;�𵥺�ǩ�տ�Ʊ�����˻ص����۳��ⵥ����ȫ���������
 * 2.���۷�Ʊ����Ӧ�գ����۷�Ʊ����ⵥ��Ӧ����֯����һ��
 * @scene
 * ��Ӧ��ҵ��ǰУ��
 * @param
 * ��
 * 
 * @since 6.0
 * @version 2011-9-30 ����10:51:17
 * @author zhangcheng
 */
public class CheckADIncomeDataRule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {
    this.checkOutWasApproved(vos);
    this.checkOutInvAROrg(vos);
  }

  /**
   * ���۷�Ʊ����Ӧ�գ����۷�Ʊ����ⵥ��Ӧ����֯����һ��
   * 
   * @param vos
   */
  private void checkOutInvAROrg(SquareInvVO[] vos) {
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareInvBVO.CSRCBID,
            String.class);
    // ��ѯ���۳��ⵥ�����㵥
    SquareOutViewVO[] outViews =
        new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(outBids);
    // <���۳��ⵥbid,���۳��ⵥ��Ӧ����֯oid>
    Map<String, String> map = new HashMap<String, String>();
    for (SquareOutViewVO view : outViews) {
      map.put(view.getItem().getCsquarebillbid(), view.getItem().getCarorgid());
    }
    for (SquareInvVO invvo : vos) {
      for (SquareInvBVO bvo : invvo.getChildrenVO()) {
        String outbid = bvo.getCsrcbid();
        String invoicearoid = bvo.getCarorgid();
        String outaroid = map.get(outbid);
        // �ų����۷�Ʊû�����γ��ⵥ�ĳ��������������ۿ��� !PubAppTool.isNull(outaroid)
        if (!PubAppTool.isNull(outaroid)
            && !PubAppTool.isEqual(invoicearoid, outaroid)) {
          ExceptionUtils
              .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                  .getStrByID("4006010_0", "04006010-0148")/*@res "���۷�Ʊ����Ӧ�գ����۷�Ʊ����ⵥ��Ӧ����֯����һ��"*/);
        }
      }
    }

  }

  /**
   * ���۷�Ʊ���ε�;�𵥺�ǩ�տ�Ʊ�����˻ص����۳��ⵥ����ȫ���������
   * 
   * @param vos
   */
  private void checkOutWasApproved(SquareInvVO[] vos) {
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareInvBVO.CSRCBID,
            String.class);

    // ���۷�Ʊ���ε����۳��ⵥ��Ӧ������;�𵥱���ȫ������
    boolean bWasBillApprove =
        ICM4453ServiceUtil.checkWasBillIsApproveByOutBids(outBids);

    // ���۷�Ʊ���ε����۳��ⵥ��Ӧ������ǩ���˻ص����۳��ⵥ����ȫ��ǩ��
    boolean bRetOutApprove = true;
    SquareInvViewVO[] views =
        SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(vos);
    Para4CFor32SignBiz[] paras = this.querySignNumBusitype(views);
    if (!VOChecker.isEmpty(paras)) {
      bRetOutApprove = this.processSignReturnOut(views, paras);
    }

    if (!bWasBillApprove || !bRetOutApprove) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0013")/*@res "��Ӧ�����̣����۷�Ʊ���ε�;�𵥺�ǩ�տ�Ʊ�����˻ص����۳��ⵥ����ȫ��������ϣ�"*/);
    }
  }

  /**
   * ǩ�տ�Ʊ�����˻ص����۳��ⵥ�Ƿ�ȫ��ǩ��
   * 
   * @param views
   * @param paras
   * @return
   */
  private boolean processSignReturnOut(SquareInvViewVO[] views,
      Para4CFor32SignBiz[] paras) {
    boolean bRetOutApprove = true;
    Map<String, UFBoolean> mpara = new HashMap<String, UFBoolean>();
    for (Para4CFor32SignBiz para : paras) {
      mpara.put(para.getPk_org() + para.getCbizid(), para.getIsSign());
    }
    List<SquareInvViewVO> lSignView = new ArrayList<SquareInvViewVO>();
    for (SquareInvViewVO view : views) {
      String key =
          view.getItem().getCsaleorgid() + view.getHead().getCbiztypeid();
      UFBoolean isSignflag = ValueUtils.getUFBoolean(mpara.get(key));
      if (isSignflag.booleanValue()) {
        lSignView.add(view);
      }
    }

    // �л���ǩ�տ�Ʊ������
    if (lSignView.size() > 0) {
      SquareInvViewVO[] sviews =
          lSignView.toArray(new SquareInvViewVO[lSignView.size()]);
      String[] outBids =
          SoVoTools.getVOsOnlyValues(sviews, SquareInvBVO.CSRCBID);
      bRetOutApprove = ICM4CServiceUtil.checkSaleOutIsSignByOutBids(outBids);
    }

    return bRetOutApprove;
  }

  /**
   * ��ѯ����ǩ�տ�Ʊ������
   * 
   * @param views
   * @return
   */
  private Para4CFor32SignBiz[] querySignNumBusitype(SquareInvViewVO[] views) {
    List<Para4CFor32SignBiz> lpara = new ArrayList<Para4CFor32SignBiz>();
    for (SquareInvViewVO svo : views) {
      String cbiztypeid = svo.getHead().getCbiztypeid();
      String csaleorgid = svo.getItem().getCsaleorgid();
      String cfirstbid = svo.getItem().getCfirstbid();
      // ����Դ��������֯�У����Ƶķ����ۿ��У�
      if (null == cfirstbid && null == csaleorgid) {
        continue;
      }
      Para4CFor32SignBiz para = new Para4CFor32SignBiz(cbiztypeid, csaleorgid);
      lpara.add(para);
    }
    Para4CFor32SignBiz[] paras =
        lpara.toArray(new Para4CFor32SignBiz[lpara.size()]);
    ISaleFor4CParaQuery pqs =
        NCLocator.getInstance().lookup(ISaleFor4CParaQuery.class);
    Para4CFor32SignBiz[] rets = null;
    try {
      rets = pqs.querySignNumBusitype(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return rets;
  }

}
