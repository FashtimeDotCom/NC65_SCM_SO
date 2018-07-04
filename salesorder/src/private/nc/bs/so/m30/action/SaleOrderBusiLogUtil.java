package nc.bs.so.m30.action;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.bill.convertor.ViewToBillConvertor;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.ActionType;
import nc.vo.so.pub.enumeration.FuncodeType;
import nc.vo.so.pub.util.BusinessLogUtil;

public class SaleOrderBusiLogUtil {

  public SaleOrderBusiLogUtil() {
    /*
     * �չ��캯��
     */
  }

  /**
   * ���۶���������¼ҵ����־����
   *
   * @since 6.0
   * @version 2011-7-12 ����10:02:42
   * @author ������
   */
  public void addApproveBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.APPROVE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0048")/*���۶�������*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0048")/*@res "���۶�������"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶��������ر�
   */
  public void addBillCloseBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.BILLCLOSE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0049")/*���۶��������ر�*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0049")/*@res "���۶��������ر�"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public void addBillOpenBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.BILLOPEN);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0050")/*���۶���������*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0050")/*@res "���۶���������"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶����йر�
   */
  public void addBillRowClose(SaleOrderViewVO[] vos) {
    if ((null == vos) || (vos.length == 0)) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.ROWCLOSE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0051")/*���۶����йر�*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0051")/*@res "���۶����йر�"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶����йر�
   */
  public void addBillRowOpen(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.ROWOPEN);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0052")/*���۶����д�*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0052")/*@res "���۶����д�"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶���ɾ����¼ҵ����־����
   *
   * @since 6.0
   * @version 2011-7-12 ����10:02:42
   * @author ������
   */
  public void addDeleteBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.DELETE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0053")/*���۶���ɾ��*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0053")/*@res "���۶���ɾ��"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶�������
   */
  public void addFreezeBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.FREEZE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0054")/*���۶�������*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0054")/*@res "���۶�������"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶����п�Ʊ�ر�
   */
  public void addInvoiceCloseBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.VOICECLOSE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0055")/*���۶����п�Ʊ�ر�*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0055")/*@res "���۶����п�Ʊ�ر�"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶����п�Ʊ��
   */
  public void addInvoiceOpenBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.VOICEOPEN);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0056")/*���۶����п�Ʊ��*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0056")/*@res "���۶����п�Ʊ��"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶����г���ر�
   */
  public void addOutCloseBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.OUTCLOSE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0057")/*���۶����г���ر�*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0057")/*@res "���۶����г���ر�"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶����г����
   */
  public void addOutOpenBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.OUTOPEN);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0058")/*���۶����г����*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0058")/*@res "���۶����г����"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶��������¼ҵ����־����
   *
   * @since 6.0
   * @version 2011-7-12 ����10:02:42
   * @author ������
   */

  public void addSaveBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.SAVE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0059")/*���۶�������*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0059")/*@res "���۶�������"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶����з����ر�
   */
  public void addSendCloseBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.SENDCLOSE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0060")/*���۶����з����ر�*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0060")/*@res "���۶����з����ر�"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶����з�����
   */
  public void addSendOpenBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.SENDOPEN);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0061")/*���۶����з�����*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0061")/*@res "���۶����з�����"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶����н���ر�
   */
  public void addSetCloseBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.SETCLOSE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0062")/*���۶����н���ر�*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0062")/*@res "���۶����н���ر�"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶����н����
   */
  public void addSetOpenBusiLog(SaleOrderViewVO[] vos) {
    if (null == vos) {
      return;
    }
    IObjectConvert<SaleOrderViewVO, SaleOrderVO> billconvert =
        new ViewToBillConvertor<SaleOrderViewVO, SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] newbills = billconvert.convert(vos);
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.SETOPEN);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0063")/*���۶����н����*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0063")/*@res "���۶����н����"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(newbills, false);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶���������¼ҵ����־����
   *
   * @since 6.0
   * @version 2011-7-12 ����10:02:42
   * @author ������
   */

  public void addUnApproveBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.UNAPPROVE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0064")/*���۶�������*/);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0064")/*@res "���۶�������"*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ���۶����ⶳ ��дҵ����־
   *
   * @since 6.0
   * @version 2011-5-19 ����01:43:19
   * @author ף����
   */
  public void addUnFreezeBusiLog(SaleOrderVO[] vos) {
    if (null == vos) {
      return;
    }
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.UNFREEZE);
    util.setFuncnode(FuncodeType.SALEORDER);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0065")/*���۶����ⶳ */);
    util.setLogmsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0065")/*@res "���۶����ⶳ "*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}