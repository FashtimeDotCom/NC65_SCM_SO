package nc.bs.so.m33.maintain.m4c;

import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.util.biz.SOBusiMDEnum;
import nc.vo.so.pub.util.biz.SOBusiUtil;

/**
 * ����������Ϣ����ʱ���ã��ֹ����㲻����
 * �������۽��㵥�����־BP
 * �ý��㵥�������ñ��ν�������
 * 
 * @author zhangcheng
 * 
 */
public class UpdateSquare4CFlagBP {

  /**
   * ���۳��ⵥ��ʽ���ɽ��㵥��ʱ����±�������־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagFor4CPush33(SquareOutVO[] sqvos) {
    String[] bizs =
        AggVOUtil.getDistinctHeadFieldArray(sqvos, SquareOutHVO.CBIZTYPEID,
            String.class);
    Map<String, SOBusiMDEnum> map = new SOBusiUtil().querySOBusiType(bizs);

    // ������Ӧ��־λ
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.FALSE);
      vo.getParentVO().setBautosquarecost(UFBoolean.FALSE);
      Integer ar = SquareType.SQUARETYPE_ET.getIntegerValue();
      Integer ia = SquareType.SQUARETYPE_REG_DEBIT.getIntegerValue();
      // ҵ����������
      String biz = vo.getParentVO().getCbiztypeid();
      SOBusiMDEnum sobusitype = map.get(biz);
      if (SOBusiMDEnum.SOBUSIMDENUM_INVOICEFIRST == sobusitype
          || SOBusiMDEnum.SOBUSIMDENUM_INVOUTPARALLEL == sobusitype) {
        ar = SquareType.SQUARETYPE_NULL.getIntegerValue();
        ia = SquareType.SQUARETYPE_NULL.getIntegerValue();
      }
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreartype(ar);
        bvo.setFpreiatype(ia);
      }
    }

  }

  /**
   * ���㵥��Ӧ�ո��±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAdjustIncome(SquareOutVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_BALANCEAR.value());
      }
    }

    new UpdateSquare4CFieldsBP().updateFields(sqvos, new String[] {
      SquareOutHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareOutBVO.FPREARTYPE
    });

  }

  /**
   * ���㵥�Զ����������±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoARIncome(SquareOutVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_AR.value());
      }
    }

    new UpdateSquare4CFieldsBP().updateFields(sqvos, new String[] {
      SquareOutHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareOutBVO.FPREARTYPE
    });

  }

  /**
   * ���㵥�Զ��ݹ�Ӧ�ո��±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoETIncome(SquareOutVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_ET.value());
      }
    }

    new UpdateSquare4CFieldsBP().updateFields(sqvos, new String[] {
      SquareOutHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareOutBVO.FPREARTYPE
    });

  }

  /**
   * ���㵥�Զ��ɱ�������±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoIACost(SquareOutVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.TRUE);
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_IA.value());
      }
    }

    new UpdateSquare4CFieldsBP().updateFields(sqvos, new String[] {
      SquareOutHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareOutBVO.FPREIATYPE
    });

  }

  /**
   * ���㵥�Զ����뷢����Ʒ���±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoIARegister(SquareOutVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.TRUE);
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_REG_DEBIT.value());
      }
    }

    new UpdateSquare4CFieldsBP().updateFields(sqvos, new String[] {
      SquareOutHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareOutBVO.FPREIATYPE
    });

  }

  /**
   * ���㵥�ֹ����������±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForManualARIncome(SquareOutVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.FALSE);
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_AR.value());
      }
    }

    new UpdateSquare4CFieldsBP().updateFields(sqvos, new String[] {
      SquareOutHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareOutBVO.FPREARTYPE
    });

  }

  /**
   * ���㵥�ֹ��ɱ�������±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForManualIACost(SquareOutVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareOutVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.FALSE);
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_IA.value());
      }
    }

    new UpdateSquare4CFieldsBP().updateFields(sqvos, new String[] {
      SquareOutHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareOutBVO.FPREIATYPE
    });

  }

}
