package nc.bs.so.m33.biz.m4c.rule.outrush;

import java.util.HashMap;
import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.so.m33.ref.ic.m4c.ICM4CServiceUtil;
import nc.vo.ic.m4c.entity.SaleOutViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.so.pub.votools.SoVoTools;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * ���۳��ⵥ����Գ������۳�������㵥�Ƿ���Բ������Գ�
 * @scene
 * ���۳��ⵥ����Գ�ǰ
 * @param 
 * ��
 */
public class CheckOutRush4CRule implements ICompareRule<SquareOutViewVO> {

  @Override
  public void process(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos) {
    // ���ֳ��ⵥ
    if (VOChecker.isEmpty(bluevos) || bluevos.length > 1) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0022")/*@res "ֻ��ѡ��һ�����ֳ��ⵥ"*/);
    }
    // ���ֳ��ⵥ
    if (VOChecker.isEmpty(redvos)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0023")/*@res "û�к��ֳ��ⵥ"*/);
    }

    // ��ȡ���۳�������㵥��Ӧ�����۳��ⵥ
    Map<String, SaleOutViewVO> moutview =
        this.getSaleOutViewVO(bluevos, redvos);
    this.check(bluevos[0], redvos, moutview);
  }

  private void check(SquareOutViewVO bluevo, SquareOutViewVO[] redvos,
      Map<String, SaleOutViewVO> moutview) {
    // �Զ�Ӧ�ս��㡢�ֹ�����(Ӧ�ա��ɱ�)��֧�ֳ���Գ�
    this.checkSquareType(bluevo);

    for (SquareOutViewVO redvo : redvos) {
      // �Զ�Ӧ�ս��㡢�ֹ�����(Ӧ�ա��ɱ�)��֧�ֳ���Գ�
      this.checkSquareType(redvo);

      // ��Ʒ�ͷ���Ʒ���ⵥ�в��ܶԳ�
      this.checkLargess(bluevo, redvo);

      // ���ֺ��ֳ��ⵥ���������Ҫһ��
      this.checkEqual(bluevo, redvo, moutview);
    }
  }

  private void checkEqual(SquareOutViewVO bluevo, SquareOutViewVO redvo,
      Map<String, SaleOutViewVO> moutview) {
    // ���ϡ������ͻ�����Ʊ�ͻ������κš��ɱ�����ͬ;���֡�ԭ�Һͱ��ҵ���˰���ۡ���˰������ͬ
    String bluemaroid = bluevo.getItem().getCmaterialid();
    String blueordcus = bluevo.getItem().getCordercustid();
    String blueinvcus = bluevo.getItem().getCinvoicecustid();
    String bluecurid = bluevo.getItem().getCorigcurrencyid();
    String bluelocalcurid = bluevo.getItem().getCcurrencyid();
    String bluecostorgid = bluevo.getItem().getCcostorgid();
    String bluebatchcode = bluevo.getItem().getVbatchcode();
    String redmaroid = redvo.getItem().getCmaterialid();
    String redordcus = redvo.getItem().getCordercustid();
    String redinvcus = redvo.getItem().getCinvoicecustid();
    String redcurid = redvo.getItem().getCorigcurrencyid();
    String redlocalcurid = redvo.getItem().getCcurrencyid();
    String redcostorgid = redvo.getItem().getCcostorgid();
    String redbatchcode = redvo.getItem().getVbatchcode();

    // �����漰ǩ�ոļۣ������������۳�������㵥��ԭʼ�ļ۸����Ա����ó��ⵥʵʱ�ļ۸�Ƚ�
    SaleOutViewVO blueview = moutview.get(bluevo.getItem().getCsquarebillbid());
    if (VOChecker.isEmpty(blueview)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0024")/*@res "�����쳣��δ�ҵ��������۳��ⵥ��"*/);
    }
    UFDouble blueotaxprice = blueview.getItem().getNorigtaxnetprice();
    UFDouble blueltaxprice = blueview.getItem().getNtaxnetprice();
    UFDouble blueoprice = blueview.getItem().getNorignetprice();
    UFDouble bluelprice = blueview.getItem().getNnetprice();
    SaleOutViewVO redview = moutview.get(redvo.getItem().getCsquarebillbid());
    if (VOChecker.isEmpty(redview)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0025")/*@res "�����쳣��δ�ҵ��������۳��ⵥ��"*/);
    }
    UFDouble redotaxprice = redview.getItem().getNorigtaxnetprice();
    UFDouble redltaxprice = redview.getItem().getNtaxnetprice();
    UFDouble redoprice = redview.getItem().getNorignetprice();
    UFDouble redlprice = redview.getItem().getNnetprice();

    this.checkEqualItem(bluemaroid, redmaroid, NCLangResOnserver.getInstance()
        .getStrByID("4006010_0", "04006010-0109")/*���ֳ��ⵥ�ͺ��ֳ��ⵥ����*/);
    this.checkEqualItem(blueordcus, redordcus, NCLangResOnserver.getInstance()
        .getStrByID("4006010_0", "04006010-0110")/*���ֳ��ⵥ�ͺ��ֳ��ⵥ�����ͻ�*/);
    this.checkEqualItem(blueinvcus, redinvcus, NCLangResOnserver.getInstance()
        .getStrByID("4006010_0", "04006010-0111")/*���ֳ��ⵥ�ͺ��ֳ��ⵥ��Ʊ�ͻ�*/);
    this.checkEqualItem(bluecurid, redcurid, NCLangResOnserver.getInstance()
        .getStrByID("4006010_0", "04006010-0112")/*���ֳ��ⵥ�ͺ��ֳ��ⵥ����ԭ��*/);
    this.checkEqualItem(bluelocalcurid, redlocalcurid, NCLangResOnserver
        .getInstance().getStrByID("4006010_0", "04006010-0113")/*���ֳ��ⵥ�ͺ��ֳ��ⵥ����*/);
    this.checkEqualItem(bluecostorgid, redcostorgid, NCLangResOnserver
        .getInstance().getStrByID("4006010_0", "04006010-0114")/*���ֳ��ⵥ�ͺ��ֳ��ⵥ�ɱ���*/);
    this.checkEqualItem(bluebatchcode, redbatchcode, NCLangResOnserver
        .getInstance().getStrByID("4006010_0", "04006010-0115")/*���ֳ��ⵥ�ͺ��ֳ��ⵥ���κ�*/);
    this.checkEqualItem(blueotaxprice, redotaxprice, NCLangResOnserver
        .getInstance().getStrByID("4006010_0", "04006010-0116")/*���ֳ��ⵥ�ͺ��ֳ��ⵥԭ�Һ�˰����*/);
    this.checkEqualItem(blueltaxprice, redltaxprice, NCLangResOnserver
        .getInstance().getStrByID("4006010_0", "04006010-0117")/*���ֳ��ⵥ�ͺ��ֳ��ⵥ���Һ�˰����*/);
    this.checkEqualItem(blueoprice, redoprice, NCLangResOnserver.getInstance()
        .getStrByID("4006010_0", "04006010-0118")/*���ֳ��ⵥ�ͺ��ֳ��ⵥԭ����˰����*/);
    this.checkEqualItem(bluelprice, redlprice, NCLangResOnserver.getInstance()
        .getStrByID("4006010_0", "04006010-0119")/*���ֳ��ⵥ�ͺ��ֳ��ⵥ������˰����*/);
  }

  private void checkEqualItem(String blue, String red, String hit) {
    if (PubAppTool.isNull(blue) && !PubAppTool.isNull(red)
        || PubAppTool.isNull(red) && !PubAppTool.isNull(blue)
        || !PubAppTool.isNull(blue) && !PubAppTool.isNull(red)
        && !blue.equals(red)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006010_0", "04006010-0120", null, new String[] {
            hit
          })/*{0}�������*/);
    }
  }

  private void checkEqualItem(UFDouble blue, UFDouble red, String hit) {
    if (VOChecker.isEmpty(blue) && !VOChecker.isEmpty(red)
        || !VOChecker.isEmpty(blue) && VOChecker.isEmpty(red)
        || !VOChecker.isEmpty(blue) && !VOChecker.isEmpty(red)
        && blue.compareTo(red) != 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006010_0", "04006010-0120", null, new String[] {
            hit
          })/*{0}�������*/);
    }
  }

  private void checkLargess(SquareOutViewVO bluevo, SquareOutViewVO redvo) {
    // ���ֳ��ⵥ���Ƿ���Ʒ
    UFBoolean ifBlueLar = bluevo.getItem().getBlargessflag();
    boolean bifBlueLar = false;
    if (ifBlueLar != null) {
      bifBlueLar = ifBlueLar.booleanValue();
    }

    // ���ֳ��ⵥ���Ƿ���Ʒ
    UFBoolean ifRedLar = redvo.getItem().getBlargessflag();
    boolean bifRedLar = false;
    if (ifRedLar != null) {
      bifRedLar = ifRedLar.booleanValue();
    }

    if (bifBlueLar && !bifRedLar || !bifBlueLar && bifRedLar) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0026")/*@res "��Ʒ�ͷ���Ʒ���ⵥ�в��ܶԳ�"*/);
    }

  }

  /**
   * �Զ�Ӧ�ս��㡢�ֹ�Ӧ�ս��㲻֧�ֳ���Գ�
   * 
   * @param view
   */
  private void checkSquareType(SquareOutViewVO view) {
    boolean autoARFlag = view.getHead().getBautosquareincome().booleanValue();
    int artype = view.getItem().getFpreartype().intValue();
    if (autoARFlag && artype == SquareType.SQUARETYPE_AR.getIntValue()) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0027")/*@res "���۳��ⵥ�Զ�Ӧ�ս��㲻���Գ���Գ壡"*/);
    }
    if (!autoARFlag && artype == SquareType.SQUARETYPE_AR.getIntValue()) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0028")/*@res "���۳��ⵥ�ֹ�Ӧ�ս��㲻���Գ���Գ壡"*/);
    }
  }

  /**
   * ��ȡ���۳�������㵥��Ӧ�����۳��ⵥ
   * 
   * @param bluevos
   * @param redvos
   * @return <4cbid,SaleOutViewVO>
   */
  private Map<String, SaleOutViewVO> getSaleOutViewVO(
      SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos) {
    SquareOutBVO[] bluebvos =
        SquareOutVOUtils.getInstance().getSquareOutBVO(bluevos);
    SquareOutBVO[] redbvos =
        SquareOutVOUtils.getInstance().getSquareOutBVO(redvos);
    String[] bluebids =
        SoVoTools.getVOsOnlyValues(bluebvos, SquareOutBVO.CSQUAREBILLBID);
    String[] redbids =
        SoVoTools.getVOsOnlyValues(redbvos, SquareOutBVO.CSQUAREBILLBID);
    String[] outbids = ArrayUtil.combinArrays(bluebids, redbids);
    Map<String, SaleOutViewVO> moutview = new HashMap<String, SaleOutViewVO>();
    SaleOutViewVO[] views = null;
    try {
      views = ICM4CServiceUtil.queryViewVOsByBids(outbids);
      for (SaleOutViewVO view : views) {
        moutview.put(view.getItem().getCgeneralbid(), view);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return moutview;
  }

}
