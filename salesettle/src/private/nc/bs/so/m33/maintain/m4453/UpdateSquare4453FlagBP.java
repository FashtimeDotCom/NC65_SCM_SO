package nc.bs.so.m33.maintain.m4453;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasHVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;

/**
 * ����������Ϣ����ʱ���ã��ֹ����㲻����
 * �������۽��㵥�����־BP
 * �ý��㵥�������ñ��ν�������
 * 
 * @author zhangcheng
 * 
 */
public class UpdateSquare4453FlagBP {

  /**
   * ���۳��ⵥ��ʽ���ɽ��㵥��ʱ����±�������־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagFor4453Push33(SquareWasVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.FALSE);
      vo.getParentVO().setBautosquarecost(UFBoolean.FALSE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_ET.value());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_REG_DEBIT.value());
      }
    }

  }

  /**
   * ���㵥��Ӧ�ո��±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAdjustIncome(SquareWasVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_BALANCEAR.value());
      }
    }

    new UpdateSquare4453FieldsBP().updateFields(sqvos, new String[] {
      SquareWasHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareWasBVO.FPREARTYPE
    });

  }

  /**
   * ���㵥�Զ����������±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoARIncome(SquareWasVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_AR.value());
      }
    }

    new UpdateSquare4453FieldsBP().updateFields(sqvos, new String[] {
      SquareWasHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareWasBVO.FPREARTYPE
    });

  }

  /**
   * ���㵥�Զ��ݹ�Ӧ�ո��±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoETIncome(SquareWasVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.TRUE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_ET.value());
      }
    }

    new UpdateSquare4453FieldsBP().updateFields(sqvos, new String[] {
      SquareWasHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareWasBVO.FPREARTYPE
    });

  }

  /**
   * ���㵥�Զ��ɱ�������±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoIACost(SquareWasVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.TRUE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_IA.value());
      }
    }

    new UpdateSquare4453FieldsBP().updateFields(sqvos, new String[] {
      SquareWasHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareWasBVO.FPREIATYPE
    });

  }

  /**
   * ���㵥�Զ����뷢����Ʒ���±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForAutoIARegister(SquareWasVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.TRUE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setNthisnum(bvo.getNnum());
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_REG_DEBIT.value());
      }
    }

    new UpdateSquare4453FieldsBP().updateFields(sqvos, new String[] {
      SquareWasHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareWasBVO.FPREIATYPE
    });

  }

  /**
   * ���㵥�ֹ����������±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForManualARIncome(SquareWasVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquareincome(UFBoolean.FALSE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreartype((Integer) SquareType.SQUARETYPE_AR.value());
      }
    }

    new UpdateSquare4453FieldsBP().updateFields(sqvos, new String[] {
      SquareWasHVO.BAUTOSQUAREINCOME
    }, new String[] {
      SquareWasBVO.FPREARTYPE
    });

  }

  /**
   * ���㵥�ֹ��ɱ�������±�־λ
   * 
   * @param sqvos
   */
  public void updateSquareBFlagForManualIACost(SquareWasVO[] sqvos) {

    // ������Ӧ��־λ
    for (SquareWasVO vo : sqvos) {
      vo.getParentVO().setBautosquarecost(UFBoolean.FALSE);
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        bvo.setFpreiatype((Integer) SquareType.SQUARETYPE_IA.value());
      }
    }

    new UpdateSquare4453FieldsBP().updateFields(sqvos, new String[] {
      SquareWasHVO.BAUTOSQUARECOST
    }, new String[] {
      SquareWasBVO.FPREIATYPE
    });

  }

}
