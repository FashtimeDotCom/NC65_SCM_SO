package nc.pubimpl.so.m4331.ic.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.pubimpl.so.m4331.pub.RewriteVOUtil;
import nc.pubimpl.so.m4331.pub.RewriteValueUtil;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmf.pub.SCMCtrlType;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.exeception.DeliveryToleranceException;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.pub.util.ViewVOUtil;
import nc.vo.to.pub.para.ParaUtilsForTo;

/**
 * ���λ�д�������ݲ����
 * 
 * @since 6.0
 * @version 2011-1-28 ����03:32:45
 * @author ף����
 */
public class ToleranceCheck {

  // private Map<String, MaterialVO> materialMap;

  /**
   * �Ƿ�����������������
   * 
   * @author ף����
   * @time 2010-8-17 ����10:49:24
   */
  private final UFDouble percent = new UFDouble(0.01);

  private RewriteVOUtil util;

  private RewriteValueUtil valueUtil;

  public void examOverToleranceSaveBusi(RewriteVOUtil util1,
      RewriteValueUtil valueutil) throws BusinessException {
    this.util = util1;
    if (valueutil == null) {
      this.valueUtil = new RewriteValueUtil();
    }
    else {
      this.valueUtil = valueutil;
    }
    // this.materialMap = this.valueUtil.getMaterialInfo(util1);
    // ��д�������ʼ��������ļ��
    this.examDeliveryCheckInfo();
    // ����д���������ݲ����
    this.valueUtil = new RewriteValueUtil();
    this.examDeliveryInfo();
  }

  /*
   * ��д�������ʼ��������ļ��
   * ��������ֻ��С�ڻ��ߵ��ڷ���������
   */
  private void examDeliveryCheckInfo() {
    DeliveryCheckVO[] vos = this.util.getRewriteCheckVO();
    if (null == vos) {
      return;
    }
    Map<String, DeliveryViewVO> map = this.util.getRewriteViewVOOnCheck();
    StringBuffer errMsg = new StringBuffer();
    for (DeliveryCheckVO vo : vos) {
      String cid = vo.getCdeliverycid();
      String bid = vo.getCdeliverybid();
      DeliveryViewVO view = map.get(bid);
      // ��Դ����
      String billtype = view.getItem().getVsrctype();
      // ���λ�д�����仯��
      UFDouble reNum = this.valueUtil.getRewriteNum(cid, billtype);
      // ��������
      UFDouble num = vo.getNnum();
      // �ѳ�������
      UFDouble outNum = vo.getNtotaloutnum();
      // �仯��ĳ�������
      if (outNum == null) {
        outNum = UFDouble.ZERO_DBL;
      }
      if (reNum == null) {
        reNum = UFDouble.ZERO_DBL;
      }
      if (num == null) {
        num = UFDouble.ZERO_DBL;
      }
      outNum = outNum.add(reNum);
      if (outNum.abs().compareTo(num.abs()) > 0) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0",
            "04006002-0150")/*������:*/);
        errMsg.append(":\n");
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0",
            "04006002-0151", null, new String[] {
              view.getItem().getCrowno()
            })/*��{0}��*/);
        errMsg.append("\n");
      }
    }
    if (errMsg.length() > 0) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0",
          "04006002-0152")/*Ϊ�ʼ���Ϣ�����ܳ����������⡣*/);
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
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

  private void examDeliveryInfo() throws BusinessException {
    DeliveryViewVO[] views = this.util.getRewriteViewVO();
    // �ϸ���ƴ�����Ϣ
    StringBuffer errCtrlMsg = new StringBuffer();
    // ��ʾ������Ϣ
    StringBuffer errInfoMsg = new StringBuffer();

    String[] cmaterialvids =
        ViewVOUtil.getDistinctFieldArray(views, DeliveryBVO.class,
            SOItemKey.CMATERIALVID, String.class);
    Map<String, MaterialVO> materialMap = this.getMaterials(cmaterialvids);
    for (DeliveryViewVO view : views) {
      String para = null;
      String billtype = view.getItem().getVsrctype();
      if (SOBillType.Order.getCode().equals(billtype)) {
        String saleorg = view.getItem().getCsaleorgid();
        para = SOSysParaInitUtil.getSO06(saleorg);
      }
      else {
        // ���������֯
        String pk_senstorc = view.getItem().getCsendstockorgid();
        Integer to11 = ParaUtilsForTo.getInstance().getTO11(pk_senstorc);
        if (SCMCtrlType.CTRL.equalsValue(to11)) {
          para = "�ϸ����";/*-=notranslate=-*/
        }
        else if (SCMCtrlType.NOCTRL.equalsValue(to11)) {
          para = "������";/*-=notranslate=-*/
        }
        else {
          para = "��ʾ";/*-=notranslate=-*/
        }

      }

      if ("������".equals(para)) {/*-=notranslate=-*/
        return;
      }

      if ("�ϸ����".equals(para)) {/*-=notranslate=-*/
        errCtrlMsg.append(this.getErrMsg(view, materialMap));
      }
      if ("��ʾ".equals(para)) {/*-=notranslate=-*/
        // �ж��Ƿ�����������飬���ڡ���ʾ��ʱ���û�����
        Object o =
            BSContext.getInstance().getSession(
                BusinessCheck.DeliveryToleranceCheck.getCheckCode());
        if (null != o && !Boolean.parseBoolean(o.toString())) {
          return;
        }

        errInfoMsg.append(this.getErrMsg(view, materialMap));

      }
    }
    // �ϸ���Ƴ�����������
    if (errCtrlMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errCtrlMsg.toString());
    }
    // ������Ϊ��ʾʱ���Ի�д�������д���
    if (errInfoMsg.length() > 0) {
      errInfoMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0",
          "04006002-0149")/*�Ƿ������*/);
      throw new DeliveryToleranceException(errInfoMsg.toString());
    }
    BSContext.getInstance().removeSession(
        BusinessCheck.DeliveryToleranceCheck.getCheckCode());
  }

  /*
   * ����쳣��Ϣ
   */
  private StringBuffer getErrMsg(DeliveryViewVO view,
      Map<String, MaterialVO> materialMap) {
    StringBuffer errMsg = new StringBuffer();

    // ����id
    String pk_mertrial = view.getItem().getCmaterialvid();
    // ������Ϣvo
    MaterialVO materialVO = materialMap.get(pk_mertrial);
    // �ݲ�
    UFDouble tolerance = materialVO.getOuttolerance();
    tolerance = tolerance.multiply(this.percent);
    tolerance = MathTool.add(UFDouble.ONE_DBL, tolerance);
    // ��������
    UFDouble nnum = view.getItem().getNnum();
    // ����������
    UFDouble maxNum = nnum.multiply(tolerance);
    // �ѳ�������
    UFDouble outNum = view.getItem().getNtotaloutnum();
    // ����id
    String bid = view.getItem().getCdeliverybid();
    // ��Դ��������
    String billtype = view.getItem().getVsrctype();
    // ���λ�д����������
    UFDouble reNum = this.valueUtil.getRewriteNum(bid, billtype);
    // �����ۼƳ�������
    outNum = MathTool.add(outNum, reNum);
    if (MathTool.compareTo(outNum.abs(), maxNum.abs()) > 0) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0",
          "04006002-0153")/*�������ϲ��ܳ��ݲ����:*/);
      errMsg.append("\n");
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0",
          "04006002-0151", null, new String[] {
            view.getItem().getCrowno()
          })/*��{0}��*/);
      errMsg.append("\n");

    }
    return errMsg;
  }
}
