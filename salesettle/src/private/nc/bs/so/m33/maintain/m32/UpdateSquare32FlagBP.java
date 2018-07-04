package nc.bs.so.m33.maintain.m32;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvHVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;

/**
 * ����������Ϣ����ʱ���ã��ֹ����㲻����
 * �������۽��㵥�����־BP
 * �ý��㵥�������ñ��ν�������
 * 
 * @author zhangcheng
 * 
 */
public class UpdateSquare32FlagBP {

  /**
   * ���۳��ⵥ��ʽ���ɽ��㵥��ʱ����±�������־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagFor4CPush33(SquareInvVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.FALSE);
      vo.getParentVO().setBautosquarecost(UFBoolean.FALSE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_ET.value());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_REG_CREDIT.value());
      }
    }

  }

  /**
   * ���㵥��Ӧ�ո��±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAdjustIncome(SquareInvVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_BALANCEAR.value());
      }
    }

    new UpdateSquare32FieldsBP().updateFields(sqvos, new String[] {
      SquareInvHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareInvBVO.FPREARTYPE
    });

  }

  /**
   * ���㵥�Զ����������±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoARIncome(SquareInvVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_AR.value());
      }
    }

    new UpdateSquare32FieldsBP().updateFields(sqvos, new String[] {
      SquareInvHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareInvBVO.FPREARTYPE
    });

  }

  /**
   * ���㵥�Զ��ݹ�Ӧ�ո��±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoETIncome(SquareInvVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_ET.value());
      }
    }

    new UpdateSquare32FieldsBP().updateFields(sqvos, new String[] {
      SquareInvHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareInvBVO.FPREARTYPE
    });

  }

  /**
   * ���㵥�Զ��ɱ�������±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoIACost(SquareInvVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.TRUE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_IA.value());
      }
    }

    new UpdateSquare32FieldsBP().updateFields(sqvos, new String[] {
      SquareInvHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareInvBVO.FPREIATYPE
    });

  }

  /**
   * ���㵥�Զ����뷢����Ʒ���±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoIARegister(SquareInvVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.TRUE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_REG_CREDIT.value());
      }
    }

    new UpdateSquare32FieldsBP().updateFields(sqvos, new String[] {
      SquareInvHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareInvBVO.FPREIATYPE
    });

  }

  /**
   * ���㵥�ֹ����������±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForManualARIncome(SquareInvVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.FALSE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_AR.value());
      }
    }

    new UpdateSquare32FieldsBP().updateFields(sqvos, new String[] {
      SquareInvHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareInvBVO.FPREARTYPE
    });

  }

  /**
   * ���㵥�ֹ��ɱ�������±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForManualIACost(SquareInvVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareInvVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.FALSE);
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_IA.value());
      }
    }

    new UpdateSquare32FieldsBP().updateFields(sqvos, new String[] {
      SquareInvHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareInvBVO.FPREIATYPE
    });

  }

}
