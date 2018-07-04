package nc.pubimpl.so.m30.ic.m4c.rule;

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
import nc.pubitf.so.m30.ic.m4453.Rewrite4453Para;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;
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
 * ���۳����д���۶���ִ��ǰ�Ĺ�����(before)
 * @scene
 * ���ݲ���SO04��������Ƿ���������ڶ��������ݲΧ��
 * @param
 * index4453 ;�𵥻�д���۶�������ӿڲ�����
 * index4C ���۳��ⵥ��д���۶����Ļ�д������
 * mMaterialVOs key:����ID value:����VO
 * sSO04 ��֯����
 * tranTypeMap key:��������ID value:��������VO
 * tranTypeService �������Ͳ�ѯ����
 *
 * @since 6.3
 * @version 2010-01-28 ����13:49:07
 * @author ��־ΰ
 */
public class RewriteToleranceCheck {
  private Map<String, Rewrite4453Para> index4453;

  private Map<String, Rewrite4CPara> index4C;

  private Map<String, MaterialVO> mMaterialVOs;

  private String sSO04;

  private Map<String, M30TranTypeVO> tranTypeMap;

  private IM30TranTypeService tranTypeService;

  public void process(SaleOrderViewVO[] views) throws BusinessException {

    this.initPara(views);

    if ("������".equals(this.sSO04)) {/*-=notranslate=-*/
      return;
    }
    else if ("��ʾ".equals(this.sSO04)) {/*-=notranslate=-*/
      this.hintCheckOutRange(views);
    }
    else if ("�ϸ����".equals(this.sSO04)) {/*-=notranslate=-*/
      this.strictCheckOutRange(views);
    }
  }

  /**
   * ������ϳ����ݲ�
   * 
   * @author ��־ΰ
   * @time 2010-8-17 ����08:07:15
   */
  private String checkOutRange(SaleOrderViewVO[] views) {
    StringBuffer errMsg = new StringBuffer();
    for (SaleOrderViewVO view : views) {
      SaleOrderBVO body = view.getBody();
      Rewrite4CPara para4c = this.index4C.get(body.getCsaleorderbid());
      Rewrite4453Para para4453 = this.index4453.get(body.getCsaleorderbid());
      MaterialVO materialVO = this.mMaterialVOs.get(body.getCmaterialvid());
      // ���ϳ����ݲ�
      UFDouble tolerance = materialVO.getOuttolerance();
      // �����ݲΧ
      UFDouble range = UFDouble.ONE_DBL.add(tolerance.multiply(0.01));
      // ��������
      UFDouble rangeNum = body.getNnum().multiply(range);
      boolean isLossReNew = this.isLossReNew(view.getHead().getCtrantypeid());
      UFDouble nchangelossnum =
          para4453 == null ? UFDouble.ZERO_DBL : para4453.getNtranslossnum();
      UFDouble ntranslossnum =
          isLossReNew ? MathTool.add(body.getNtranslossnum(), nchangelossnum)
              : UFDouble.ZERO_DBL;
      UFDouble nchangenum =
          para4c == null ? UFDouble.ZERO_DBL : para4c.getNchangenum();
      UFDouble nchangenotoutnum =
          para4c == null ? UFDouble.ZERO_DBL : para4c.getNchangenotoutnum();
      // ʵ���ۼƳ�������
      UFDouble tempNum =
          MathTool.sub(MathTool.add(
              MathTool.add(body.getNtotaloutnum(), nchangenum),
              MathTool.add(body.getNtotalnotoutnum(), nchangenotoutnum)),
              ntranslossnum);

      // ��������*(1+�ݲ����)>=(�ۼƳ�������+�ۼ�Ӧ��δ��������-is;�𲹻�(�ۼ�;������,0))+(��ǰ����ʵ��+��ǰ����Ӧ��)
      if (MathTool.absCompareTo(rangeNum, tempNum) < 0) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0349")/*���ɳ����������ݲ���ʳ���:*/);
        errMsg.append(":\n");
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0340", null, new String[] {
              view.getHead().getVbillcode(), body.getCrowno()
            })/*���۶���{0}��{1}��*/);
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

  private void hintCheckOutRange(SaleOrderViewVO[] views)
      throws OrderToleranceException {
    Object o =
        BSContext.getInstance().getSession(
            BusinessCheck.OrderToleranceCheck.getCheckCode());
    if (null != o && !Boolean.parseBoolean(o.toString())) {
      return;
    }
    // ����ݲ�
    String errMsg = this.checkOutRange(views);
    if (errMsg.length() > 0) {
      throw new OrderToleranceException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0350", null, new String[] {
            errMsg.toString()
          })/*{0}�Ƿ����?*/);
    }
    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(
        BusinessCheck.OrderToleranceCheck.getCheckCode());
  }

  /**
   * ��ʼ����...
   * <ul>
   * <li>Rewrite4CPara
   * <li>SO04�������������ݲ���Ʒ�ʽ
   * <li>���ϻ���ҳǩ�������ݲ�
   * </ul>
   * 
   * @author ��־ΰ
   * @time 2010-8-17 ����08:07:15
   */
  @SuppressWarnings("unchecked")
  private void initPara(SaleOrderViewVO[] views) {

    // 1.��ʼ��д����
    this.index4C =
        (Map<String, Rewrite4CPara>) BSContext.getInstance().getSession(
            Rewrite4CPara.class.getName());
    if (this.index4C == null) {
      this.index4C = new HashMap<String, Rewrite4CPara>();
    }
    this.index4453 =
        (Map<String, Rewrite4453Para>) BSContext.getInstance().getSession(
            Rewrite4453Para.class.getName());
    if (this.index4453 == null) {
      this.index4453 = new HashMap<String, Rewrite4453Para>();
    }
    // 2.�����������ݲ���Ʒ�ʽ:���η��������֯SO04
    this.sSO04 =
        SysParaInitQuery.getParaString(views[0].getBody().getPk_org(),
            ParameterList.SO04.getCode());

    // 3.�ϸ����/��ʾʱ������ϻ�����Ϣ
    if ("������".equals(this.sSO04)) {/*-=notranslate=-*/
      return;
    }
    Set<String> sPk_materials = new HashSet<String>();
    for (SaleOrderViewVO view : views) {
      sPk_materials.add(view.getBody().getCmaterialvid());
    }
    this.mMaterialVOs =
        MaterialPubService.queryMaterialBaseInfo(
            sPk_materials.toArray(new String[0]), new String[] {
              MaterialVO.OUTTOLERANCE
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

  private void strictCheckOutRange(SaleOrderViewVO[] views)
      throws BusinessException {
    // ����ݲ�
    String errMsg = this.checkOutRange(views);
    if (errMsg.length() > 0) {
      throw new BusinessException(errMsg.toString());
    }
  }
}
