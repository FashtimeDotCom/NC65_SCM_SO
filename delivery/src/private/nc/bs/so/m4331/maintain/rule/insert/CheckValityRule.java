package nc.bs.so.m4331.maintain.rule.insert;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.pub.rule.SOProfitCenterUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * ���۷���������ǰ���ݺϷ���У��
 * 1.��ͷ�Ϸ���У�顢�������ݺϷ���У��
 * 2.��Դ����������������ҡ�˰�롢˰�ʵ��ֶοɿ�
 * 3.�����ֵ�ĺϷ���
 * @scene
 * ���۷������������޸ı���ǰ
 * @param
 * ��
 */
public class CheckValityRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] invoices) {

    for (DeliveryVO vo : invoices) {
      // ��ͷ�Ϸ���У��
      this.checkHeadValidity(vo.getParentVO());
      // �������ݺϷ���У��
      this.checkBodyValidity(vo.getChildrenVO());
      // ��Դ����������������ҡ�˰�롢˰�ʵ��ֶοɿ�
      this.checkVAT(vo.getChildrenVO());
      // �����ֵ�ĺϷ���
      this.checkValue(vo);
      SOProfitCenterUtil.checkProfitCenterValue(vo);
    }

  }

  private void checkBodyValidity(DeliveryBVO[] childrenVOs) {
    List<String> errField = new ArrayList<String>();
    for (DeliveryBVO bvo : childrenVOs) {
      // ����ɾ����
      if (bvo.getStatus() == VOStatus.DELETED) {
        continue;
      }
      if (VOChecker.isEmpty(bvo.getCmaterialid())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0118")/*����*/);
      }
      if (VOChecker.isEmpty(bvo.getCastunitid())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0119")/*��λ*/);
      }
      if (VOChecker.isEmpty(bvo.getNnum())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0120")/*����*/);
      }
      if (VOChecker.isEmpty(bvo.getCunitid())) {
        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0121")/*����λ*/);
      }
      if (errField.size() > 0) {
        StringBuilder errMsg =
            new StringBuilder(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0122", null, new String[]{bvo.getCrowno()})/*��������[{0}]�������ֶβ���Ϊ�գ�*/);
        errMsg.append(errField.get(0));
        for (int i = 1; i < errField.size(); i++) {
          errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0123")/*��*/).append(errField.get(i));
        }
        ExceptionUtils.wrappBusinessException(errMsg.toString());
      }
    }
  }

  private void checkVAT(DeliveryBVO[] childrenVOs) {
	  List<String> errField = new ArrayList<String>();
	    for (DeliveryBVO bvo : childrenVOs) {
	      // ����ɾ����
	      if (bvo.getStatus() == VOStatus.DELETED || TOBillType.TransOrder.getCode().equals(bvo.getVsrctype())) {
	        continue;
	      }
	      if (VOChecker.isEmpty(bvo.getCsendcountryid())) {
	        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0179")/*�ջ�����/����*/);
	      }
	      if (VOChecker.isEmpty(bvo.getCrececountryid())) {
	        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0180")/*��������/����*/);
	      }
	      if (VOChecker.isEmpty(bvo.getCtaxcountryid())) {
	        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0181")/*��˰����/����*/);
	      }
	      if (VOChecker.isEmpty(bvo.getFbuysellflag())) {
	        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0182")/*��������*/);
	      }
	      if (VOChecker.isEmpty(bvo.getBtriatradeflag())) {
		        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0183")/*����ó��*/);
		  }
	      if (VOChecker.isEmpty(bvo.getCtaxcodeid())) {
		        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0184")/*˰��*/);
		  }
	      if (VOChecker.isEmpty(bvo.getFtaxtypeflag())) {
		        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0185")/*��˰���*/);
		  }
	      if (VOChecker.isEmpty(bvo.getNcaltaxmny())) {
		        errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0186")/*��˰���*/);
		  }
	      if (errField.size() > 0) {
	        StringBuilder errMsg =
	            new StringBuilder(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0122", null, new String[]{bvo.getCrowno()})/*��������[{0}]�������ֶβ���Ϊ�գ�*/);
	        errMsg.append(errField.get(0));
	        for (int i = 1; i < errField.size(); i++) {
	          errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0123")/*��*/).append(errField.get(i));
	        }
	        ExceptionUtils.wrappBusinessException(errMsg.toString());
	      }
	    }
  }
  
  
  private void checkHeadValidity(DeliveryHVO head) {
    List<String> errField = new ArrayList<String>();

    if (VOChecker.isEmpty(head.getPk_org())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0114")/*������֯*/);
    }
    if (VOChecker.isEmpty(head.getDbilldate())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0124")/*��������*/);
    }
    if (VOChecker.isEmpty(head.getCtrantypeid())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0125")/*��������*/);
    }
    if (VOChecker.isEmpty(head.getCbiztypeid())) {
      errField.add(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0126")/*ҵ������*/);
    }
    if (errField.size() > 0) {
      StringBuilder errMsg = new StringBuilder(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0127")/*��������ͷ�����ֶβ���Ϊ�գ�*/);
      errMsg.append(errField.get(0));
      for (int i = 1; i < errField.size(); i++) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0123")/*��*/).append(errField.get(i));
      }
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

  private void checkValue(DeliveryVO vo) {
    StringBuffer errMsg = new StringBuffer();
    for (DeliveryBVO bvo : vo.getChildrenVO()) {
      if (MathTool.compareTo(UFDouble.ZERO_DBL, bvo.getNnum()) == 0) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0128", null, new String[]{bvo.getCrowno()})/*��������[{0}]�е�����������Ϊ0*/);
      }
      UFDouble exchangerate = bvo.getNexchangerate();
      if (MathTool.compareTo(exchangerate, UFDouble.ZERO_DBL) <= 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0041")/*@res "��֯�۱����ʲ���С�ڵ���0��"*/);
      }
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }
}