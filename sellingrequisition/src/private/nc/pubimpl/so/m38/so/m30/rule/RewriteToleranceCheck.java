package nc.pubimpl.so.m38.so.m30.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.pubitf.so.m38.so.m30.Rewrite30Para;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.exeception.PreOrderToleranceException;
import nc.vo.so.pub.res.ParameterList;

/**
 * @description
 * ���۶�����дԤ����ִ��ǰ(before)������Ƿ񶩵�������Ԥ���������ݲΧ��
 * @scene
 * ���۶�����дԤ����ִ��ǰ
 * @param
 * index  key: Ԥ�����ӱ�����  value:���۶�����дԤ�����Ļ�д������
 * sSO26 ��Ԥ���������������Ʒ�ʽ
 * @author ��־ΰ
 * @time 2010-01-28 ����13:49:07
 */
public class RewriteToleranceCheck {

  private Map<String, Rewrite30Para> index;

  private String sSO26;

  public void process(PreOrderViewVO[] views) throws BusinessException {

    this.initPara(views);

    if ("������".equals(this.sSO26)) {/*-=notranslate=-*/
      return;
    }
    else if ("��ʾ".equals(this.sSO26)) {/*-=notranslate=-*/
      this.hintCheckArrangeRange(views);
    }
    else if ("�ϸ����".equals(this.sSO26)) {/*-=notranslate=-*/
      this.strictCheckArrangeRange(views);
    }
  }

  /**
   * �������Ԥ���������ݲ�(ʵ���������ݲֻ��Ԥ����������)
   * 
   * @author ��־ΰ
   * @time 2010-8-17 ����08:07:15
   */
  private String checkArrangeRange(PreOrderViewVO[] views) {
    StringBuffer errMsg = new StringBuffer();
    for (PreOrderViewVO view : views) {
      PreOrderBVO body = view.getItem();
      // ��������(Ԥ����������)
      UFDouble rangeNum = body.getNnum();
      // �ۼư������� = �ۼư������� + ��ǰ��������
      UFDouble arrnum =
          MathTool.add(body.getNarrnum(), this.index
              .get(body.getCpreorderbid()).getNnum());
      // Ԥ�������� < �ۼư�������
      if (MathTool.absCompareTo(rangeNum, arrnum) < 0) {
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006012_0", "04006012-0064")/*���ɳ�Ԥ�����������Ŷ���:*/);
        errMsg.append("\n");
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006012_0", "04006012-0065", null, new String[]{view.getHead().getVbillcode(),body.getCrowno()})/*Ԥ����{0}��{1}��*/);
        errMsg.append("\n");
      }
    }
    return errMsg.toString();
  }

  private void hintCheckArrangeRange(PreOrderViewVO[] views)
      throws PreOrderToleranceException {
    Object o =
        BSContext.getInstance().getSession(
            BusinessCheck.PreOrderToleranceCheck.getCheckCode());
    if (null != o && !Boolean.parseBoolean(o.toString())) {
      return;
    }
    // ����ݲ�
    String errMsg = this.checkArrangeRange(views);
    if (errMsg.length() > 0) {
      throw new PreOrderToleranceException(NCLangResOnserver.getInstance().getStrByID("4006012_0", "04006012-0066", null, new String[]{errMsg.toString()})/*{0}�Ƿ����?*/);
    }
    // �˴��ͷ�session�����������˷��ڴ�
    BSContext.getInstance().removeSession(
        BusinessCheck.PreOrderToleranceCheck.getCheckCode());
  }

  /**
   * ��ʼ����...
   * <ul>
   * <li>Rewrite30Para
   * <li>SO26����Ԥ�������Ŷ������Ʒ�ʽ
   * </ul>
   * 
   * @author ��־ΰ
   * @time 2010-8-17 ����08:07:15
   */
  @SuppressWarnings("unchecked")
  private void initPara(PreOrderViewVO[] views) {

    // 1.��ʼ��д����
    this.index =
        (Map<String, Rewrite30Para>) BSContext.getInstance().getSession(
            Rewrite30Para.class.getName());

    // 2.��Ԥ���������������Ʒ�ʽ��SO05
    this.sSO26 =
        SysParaInitQuery.getParaString(views[0].getHead().getPk_org(),
            ParameterList.SO26.getCode());

  }

  private void strictCheckArrangeRange(PreOrderViewVO[] views)
      throws BusinessException {
    // ����ݲ�
    String errMsg = this.checkArrangeRange(views);
    if (errMsg.length() > 0) {
      throw new BusinessException(errMsg.toString());
    }
  }

}
