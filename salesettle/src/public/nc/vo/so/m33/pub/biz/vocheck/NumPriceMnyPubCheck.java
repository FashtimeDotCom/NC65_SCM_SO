package nc.vo.so.m33.pub.biz.vocheck;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.pub.calculator.INumPriceMnyCheckData;
import nc.vo.so.pub.calculator.SOVODataSetForCal;
import nc.vo.trade.checkrule.VOChecker;

public class NumPriceMnyPubCheck<T extends AbstractBill> {

  public void checkData(T[] vos) {
    SOVODataSetForCal data = null;
    IRelationForItems item = new RelationItemForCal();
    for (T vo : vos) {
      for (CircularlyAccessibleValueObject bvo : vo.getChildrenVO()) {
        data = new SOVODataSetForCal(vo.getParentVO(), bvo, item);
        this.check(data);
      }
    }
  }

  private void check(INumPriceMnyCheckData checkData) {
    // ��Ʒ  12
    boolean blargessflag = false;
    
    
    if (ValueUtils.getBoolean(checkData.getBlargessflag())) {
      blargessflag = true;
      // ���ڽ������ۿ����ϣ�ͬʱ�ֹ�ѡ�ˡ���Ʒ�������⴦��һ�£���Ȼ���������岻��˭����������ҵ���أ�
      if (ValueUtils.getBoolean(checkData.getBdiscountflag())) {
        this.checkDiscount(checkData);
      }
      else {
        this.checkLargess(checkData);
      }
    }
    // ��������
    boolean blaborflag = false;
    if (ValueUtils.getBoolean(checkData.getBlaborflag())) {
      blaborflag = true;
      this.checkLabor(checkData);
    }
    // �ۿ�����
    boolean bdiscountflag = false;
    if (ValueUtils.getBoolean(checkData.getBdiscountflag())) {
      bdiscountflag = true;
      this.checkDiscount(checkData);
    }
    // ��ͨ���
    if (!blargessflag && !blaborflag && !bdiscountflag) {
      this.checkNormal(checkData);
    }
    // У�鵥�۽������֮��Ĺ�ϵ
    // ���ҵ�񲻴��ڴ˹�ϵ��ʱȥ��У��
    // this.checkNumPriceMny(checkData);
  }

  private void checkDiscount(INumPriceMnyCheckData checkData) {
    String msg =
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
            "04006010-0121")/* �ۿ����� */;
    if(!ValueUtils.getBoolean(checkData.getBlargessflag())){
      this.checkMny(checkData, msg);
    }
  }

  private void checkLabor(INumPriceMnyCheckData checkData) {
    String msg =
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
            "04006010-0122")/* �������� */;
    this.checkNumNull(checkData, msg);
    if(!ValueUtils.getBoolean(checkData.getBlargessflag())){
        this.checkMny(checkData, msg);
    }

  }

  private void checkLargess(INumPriceMnyCheckData checkData) {
    String msg =
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
            "04006010-0123")/* ��Ʒ���� */;
    this.checkNum(checkData, msg);
    this.checkPriceNull(checkData, msg);
    this.checkMnyNull(checkData, msg);
  }

  private void checkMny(INumPriceMnyCheckData checkData, String msg) {
    if (MathTool.isZero(checkData.getNorigtaxmny())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0124", null, new String[] {
            msg
          })/* ���������У�{0}ԭ�Ҽ�˰�ϼ�Ϊ�ջ���0 */);
    }
    else if (MathTool.isZero(checkData.getNorigmny())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0125", null, new String[] {
            msg
          })/* ���������У�{0}ԭ����˰���Ϊ�ջ���0 */);
    }
    // ԭ��˰���ֶβ������� ��ʱ��ȥ��
    // else if (VOChecker.isEmpty(checkData.getNorigtax())) {
    // ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
    // .getStrByID("4006010_0", "04006010-0126", null, new String[] {
    // msg
    // })/*���������У�{0}ԭ��˰��Ϊ��*/);
    // }
  }

  private void checkMnyNull(INumPriceMnyCheckData checkData, String msg) {
    if (VOChecker.isEmpty(checkData.getNorigtaxmny())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0127", null, new String[] {
            msg
          })/* ���������У�{0}ԭ�Ҽ�˰�ϼ�Ϊ�� */);
    }
    else if (VOChecker.isEmpty(checkData.getNorigmny())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0128", null, new String[] {
            msg
          })/* ���������У�{0}ԭ����˰���Ϊ�� */);
    }
    // ԭ��˰���ֶβ������� ��ʱ��ȥ��
    // else if (VOChecker.isEmpty(checkData.getNorigtax())) {
    // ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
    // .getStrByID("4006010_0", "04006010-0126", null, new String[] {
    // msg
    // })/*���������У�{0}ԭ��˰��Ϊ��*/);
    // }
  }

  private void checkNormal(INumPriceMnyCheckData checkData) {
    String msg =
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
            "04006010-0129")/* ��ͨ��� */;
    this.checkNum(checkData, msg);
    this.checkPrice(checkData, msg);
    this.checkMny(checkData, msg);
  }

  private void checkNum(INumPriceMnyCheckData checkData, String msg) {
    if (MathTool.isZero(checkData.getNnum())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0130", null, new String[] {
            msg
          })/* ���������У�{0}������Ϊ�ջ���0 */);
    }
  }

  private void checkNumNull(INumPriceMnyCheckData checkData, String msg) {
    if (VOChecker.isEmpty(checkData.getNnum())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0131", null, new String[] {
            msg
          })/* ���������У�{0}������Ϊ�� */);
    }
  }

  private void checkNumPriceMny(INumPriceMnyCheckData checkData) {
    // ���Ҽ�˰�ϼ� = ������˰��� + ����˰��
    UFDouble temp = MathTool.add(checkData.getNmny(), checkData.getNtax());
    if (!MathTool.equals(temp, checkData.getNtaxmny())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0081")/* @res
                                                        * "���������У�����λ���Ҽ�˰�ϼ� != ����λ������˰��� + ˰��" */);
    }
  }

  private void checkPrice(INumPriceMnyCheckData checkData, String msg) {
    if (MathTool.isZero(checkData.getNorigtaxnetprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0132", null, new String[] {
            msg
          })/* ���������У�{0}����λԭ�Һ�˰����Ϊ�ջ���0 */);
    }
    else if (MathTool.isZero(checkData.getNorignetprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0133", null, new String[] {
            msg
          })/* ���������У�{0}����λԭ����˰����Ϊ�ջ���0 */);
    }
    else if (MathTool.isZero(checkData.getNorigtaxprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0134", null, new String[] {
            msg
          })/* ���������У�{0}����λԭ�Һ�˰����Ϊ�ջ���0 */);
    }
    else if (MathTool.isZero(checkData.getNorigprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0135", null, new String[] {
            msg
          })/* ���������У�{0}����λԭ����˰����Ϊ�ջ���0 */);
    }
  }

  private void checkPriceNull(INumPriceMnyCheckData checkData, String msg) {
    if (VOChecker.isEmpty(checkData.getNorigtaxnetprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0136", null, new String[] {
            msg
          })/* ���������У�{0}����λԭ�Һ�˰����Ϊ�� */);
    }
    else if (VOChecker.isEmpty(checkData.getNorignetprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0137", null, new String[] {
            msg
          })/* ���������У�{0}����λԭ����˰����Ϊ�� */);
    }
    else if (VOChecker.isEmpty(checkData.getNorigtaxprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0138", null, new String[] {
            msg
          })/* ���������У�{0}����λԭ�Һ�˰����Ϊ�� */);
    }
    else if (VOChecker.isEmpty(checkData.getNorigprice())) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006010_0", "04006010-0139", null, new String[] {
            msg
          })/* ���������У�{0}����λԭ����˰����Ϊ�� */);
    }
  }

}
