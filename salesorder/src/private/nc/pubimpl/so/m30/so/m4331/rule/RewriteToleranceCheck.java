package nc.pubimpl.so.m30.so.m4331.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.exeception.OrderToleranceException;
import nc.vo.so.pub.res.ParameterList;

/**
 * 
 * @description
 * ��������д���۶���ִ�к������(before):
 * @scene
 * ���ݲ���SO05��������Ƿ񷢻������ڶ��������ݲΧ��
 * @param
 * 
 *
 * @since 6.3
 * @version 2010-01-28 ����13:49:07
 * @author ��־ΰ
 */
public class RewriteToleranceCheck {
  private Map<String, Rewrite4331Para> index;

  private Map<String, MaterialVO> mMaterialVOs;

  private String sSO05;

  private Map<String, M30TranTypeVO> tranTypeMap;

  private IM30TranTypeService tranTypeService;

  public void process(SaleOrderViewVO[] views) throws BusinessException {

    this.initPara(views);

    if ("������".equals(this.sSO05)) {/*-=notranslate=-*/
      return;
    }
    else if ("��ʾ".equals(this.sSO05)) {/*-=notranslate=-*/
      this.hintCheckSendRange(views);
    }
    else if ("�ϸ����".equals(this.sSO05)) {/*-=notranslate=-*/
      this.strictCheckSendRange(views);
    }
  }

  /**
   * ������ϳ����ݲ�
   * 
   * @author ��־ΰ
   * @time 2010-8-17 ����08:07:15
   */
  private String checkSendRange(SaleOrderViewVO[] views) {
    StringBuffer errMsg = new StringBuffer();
    for (SaleOrderViewVO view : views) {
      SaleOrderBVO body = view.getBody();
      MaterialVO materialVO = this.mMaterialVOs.get(body.getCmaterialvid());
      // ���ϳ����ݲ�
      UFDouble tolerance = materialVO.getOuttolerance();
      // �����ݲΧ
      UFDouble range = UFDouble.ONE_DBL.add(tolerance.multiply(0.01));
      // ��������
      UFDouble rangeNum = body.getNnum().multiply(range);
      // �ۼƷ�������
      UFDouble sendnum =
          MathTool.add(body.getNtotalsendnum(),
              this.index.get(body.getCsaleorderbid()).getNchangenum());
      boolean isLossReNew = this.isLossReNew(view.getHead().getCtrantypeid());
      UFDouble ntranslossnum =
          isLossReNew ? body.getNtranslossnum() : UFDouble.ZERO_DBL;
      UFDouble tempNum = MathTool.sub(sendnum, ntranslossnum);
      // ��������* (1 + �ݲ����) < �ۼƷ������� - is;�𲹻�(�ۼ�;������,0)
      if (MathTool.absCompareTo(rangeNum, tempNum) < 0) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0389")/*���ɳ����������ݲ���ʷ���:*/);
        errMsg.append("\n");
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0340", null, new String[]{view.getHead().getVbillcode(),body.getCrowno()})/*���۶���{0}��{1}��*/);
        errMsg.append("\n");
      }
    }
    return errMsg.toString();
  }

  private IM30TranTypeService getTranTypeService() {
    if (this.tranTypeService == null) {
      this.tranTypeService =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
    }
    return this.tranTypeService;
  }

  private M30TranTypeVO getTranTypeVOByID(String ctrantypeid) {
    if (this.tranTypeMap == null) {
      this.tranTypeMap = new HashMap<String, M30TranTypeVO>();
    }
    if (this.tranTypeMap.get(ctrantypeid) == null) {
      M30TranTypeVO[] tranTypeVOs = null;
      try {
        tranTypeVOs = this.getTranTypeService().queryTranTypeVOs(new String[] {
          ctrantypeid
        });
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      if (tranTypeVOs != null && tranTypeVOs.length > 0) {
        this.tranTypeMap.put(tranTypeVOs[0].getCtrantypeid(), tranTypeVOs[0]);
      }
    }
    return this.tranTypeMap.get(ctrantypeid);
  }

  private void hintCheckSendRange(SaleOrderViewVO[] views)
      throws OrderToleranceException {
    Object o =
        BSContext.getInstance().getSession(
            BusinessCheck.OrderToleranceCheck.getCheckCode());
    if (null != o && !Boolean.parseBoolean(o.toString())) {
      return;
    }
    // ����ݲ�
    String errMsg = this.checkSendRange(views);
    if (errMsg.length() > 0) {
      throw new OrderToleranceException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0350", null, new String[]{errMsg.toString()})/*{0}�Ƿ����?*/);
    }
    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(
        BusinessCheck.OrderToleranceCheck.getCheckCode());
  }

  /**
   * ��ʼ����...
   * <ul>
   * <li>Rewrite4331Para
   * <li>SO05�������������ݲ���Ʒ�ʽ
   * <li>���ϻ���ҳǩ�������ݲ�
   * </ul>
   * 
   * @author ��־ΰ
   * @time 2010-8-17 ����08:07:15
   */
  @SuppressWarnings("unchecked")
  private void initPara(SaleOrderViewVO[] views) {

    // 1.��ʼ��д����
    this.index =
        (Map<String, Rewrite4331Para>) BSContext.getInstance().getSession(
            Rewrite4331Para.class.getName());

    // 2.�����������ݲ���Ʒ�ʽ������������֯SO05
    this.sSO05 =
        SysParaInitQuery.getParaString(views[0].getBody().getPk_org(),
            ParameterList.SO05.getCode());

    // 3.������ϻ�����Ϣ
    Set<String> sPk_materials = new HashSet<String>();
    for (SaleOrderViewVO view : views) {
      sPk_materials.add(view.getBody().getCmaterialvid());
    }
    this.mMaterialVOs =
        MaterialPubService.queryMaterialBaseInfo(
            sPk_materials.toArray(new String[0]), new String[] {
              MaterialVO.OUTTOLERANCE,
            });

  }

  /**
   * �������͡����Ƿ�;�𲹻�
   * 
   * @author ��־ΰ
   * @time 2010-01-28 ����13:49:07
   */
  private boolean isLossReNew(String ctrantypeid) {
    M30TranTypeVO tranTypeVO = this.getTranTypeVOByID(ctrantypeid);
    return tranTypeVO.getBlossrenew() == null ? false : tranTypeVO
        .getBlossrenew().booleanValue();
  }

  private void strictCheckSendRange(SaleOrderViewVO[] views)
      throws BusinessException {
    // ����ݲ�
    String errMsg = this.checkSendRange(views);
    if (errMsg.length() > 0) {
      throw new BusinessException(errMsg.toString());
    }
  }
}
