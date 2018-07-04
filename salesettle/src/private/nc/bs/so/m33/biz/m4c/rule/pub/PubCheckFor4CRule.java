package nc.bs.so.m33.biz.m4c.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

/**
* @description
 * ���۳��ⵥ�ֹ�����ǰ����У��
 * @scene
 * ���۳��ⵥ�ֹ�����
 * @param
 * ��
 */
public class PubCheckFor4CRule implements IRule<SquareOutViewVO> {

  @Override
  public void process(SquareOutViewVO[] vos) {

    String errorMsg = null;

    for (SquareOutViewVO svo : vos) {
      UFDouble ntotalSquareNum = this.getNtotalSquareNum(svo);
      UFDouble nNum = svo.getItem().getNnum();
      // ���ν�������<=���۳�������-�ۼƽ�������
      UFDouble canSquareNum = MathTool.sub(nNum, ntotalSquareNum);
      if (MathTool.absCompareTo(svo.getItem().getNthisnum(), canSquareNum) > 0) {
        errorMsg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0",
                "04006010-0031")/* @res "���ν�������Ӧ��С�ڵ������۳�������-�ۼƽ���������" */;
        break;
      }
    }

    if (errorMsg != null) {
      ExceptionUtils.wrappBusinessException(errorMsg);
    }
  }

  /**
   * ���������������ۼƽ�������:���������߳ɱ�����ĳ���
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param bvo
   * @return
   *         <p>
   * @author zhangcheng
   * @time 2010-9-20 ����01:20:19
   */
  private UFDouble getNtotalSquareNum(SquareOutViewVO bvo) {
    UFDouble ntotalSquareNum = null;
    int artype = bvo.getItem().getFpreartype().intValue();
    int iatype = bvo.getItem().getFpreiatype().intValue();
    boolean manualar = !bvo.getHead().getBautosquareincome().booleanValue();
    boolean manualia = !bvo.getHead().getBautosquarecost().booleanValue();

    if (manualia && SquareType.SQUARETYPE_IA.getIntValue() == iatype
        && UFBoolean.TRUE.equals(bvo.getItem().getBcost())) {
      ntotalSquareNum =
          bvo.getItem().getNsquareianum() == null ? UFDouble.ZERO_DBL : bvo
              .getItem().getNsquareianum();
    }
    else if (manualar && SquareType.SQUARETYPE_AR.getIntValue() == artype
        && UFBoolean.TRUE.equals(bvo.getItem().getBincome())) {
      ntotalSquareNum =
          bvo.getItem().getNsquarearnum() == null ? UFDouble.ZERO_DBL : bvo
              .getItem().getNsquarearnum();
    }
    else {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0032")/* @res "���ֹ��������ݣ����飡" */);
    }
    return ntotalSquareNum;
  }
}
