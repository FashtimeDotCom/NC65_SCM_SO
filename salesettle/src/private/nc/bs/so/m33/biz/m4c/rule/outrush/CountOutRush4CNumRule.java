package nc.bs.so.m33.biz.m4c.rule.outrush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m33.biz.m4c.bp.pub.ProcessWC;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.so.m33.ref.ic.m4c.ICM4CServiceUtil;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.util.ParaUtils;

/**
 * @description
 * ���ֳ��ⵥ�ɶԳ�����
 * 
 * ���ڳ���������Ʊ��
 * �ɶԳ����� = ���������Cmax(�ۼƿ�Ʊ����+�ۼ�;��������X���C�ۼƳ���Գ�����
 * X��������ⵥ��ȷ��Ӧ�գ�����ⵥ�ֹ����㣨�����ɱ�����)
 * X = ���ⵥ�ۼƽ�������;����X = 0
 * 
 * ����ǩ��������Ʊ��
 * �ɶԳ����� = ǩ������ �C �ۼƿ�Ʊ���� �C �ۼƳ���Գ�������
 * 
 * ���ֳ��ⵥ�ɶԳ�������
 * ����Գ�ĺ��ֳ��ⵥ���������ۼ�Ӧ�ս�������=0���ۼƶԳ�����=0���ۼƿ�Ʊ����=0���ۼ�;������ = 0��
 * �ɶԳ����� = ���ⵥ����������ɶԳ����� = 0
 * @scene
 * ���۳��ⵥ����Գ�ǰ
 * @param 
 * ��
 * @since 6.0
 * @version 2010-11-29 ����03:38:53
 * @author zhangcheng
 */
public class CountOutRush4CNumRule implements ICompareRule<SquareOutViewVO> {

  @Override
  public void process(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos) {
    List<String> lbid = new ArrayList<String>();
    for (SquareOutViewVO blue : bluevos) {
      lbid.add(blue.getItem().getCsquarebillbid());
    }
    for (SquareOutViewVO red : redvos) {
      lbid.add(red.getItem().getCsquarebillbid());
    }

    // ���ÿ��ӿڣ���ѯ���ⵥ�ۼƿ�Ʊ�������ۼ�;���������ۼƳ���Գ����� ���ۼ�ǩ������
    SaleOutBodyVO[] outBvos = null;
    try {
      outBvos =
          ICM4CServiceUtil.queryTotalNumBy4CBids(lbid.toArray(new String[0]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }

    // ���㱾�γ���Գ�����
    if (null != outBvos) {
      Map<String, SaleOutBodyVO> m4cbvo = new HashMap<String, SaleOutBodyVO>();
      for (SaleOutBodyVO bvo : outBvos) {
        m4cbvo.put(bvo.getPrimaryKey(), bvo);
      }
      this.countRushNum(bluevos, redvos, m4cbvo);
    }

  }

  private void checkOutNum(SquareOutViewVO vo) {
    if (vo.getItem().getNthisnum().compareTo(UFDouble.ZERO_DBL) == 0) {
      String vbillcode = vo.getHead().getVbillcode();
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006010_0", "04006010-0096", null, new String[] {
            vbillcode
          })/*���ݺ�Ϊ��{0} �ĳ��ⵥ�ɶԳ���������Ϊ0!*/);
    }
  }

  private void countBlueRushNum(SquareOutViewVO[] bluevos,
      Map<String, SaleOutBodyVO> m4cbvo, UFDouble redTotalNum) {
    // ����������֯����
    Set<String> set = new HashSet<String>();
    String csaleorgid = null;
    for (SquareOutViewVO vo : bluevos) {
      csaleorgid = vo.getItem().getCsaleorgid();
      if (!set.contains(csaleorgid)) {
        set.add(csaleorgid);
      }
    }
    String[] saleorgid = set.toArray(new String[0]);

    // �Ƿ����ǩ�տ�Ʊ<������֯oid,ҵ������set>
    Map<String, List<String>> mifBaseSignInvoice =
        ParaUtils.getifBaseSignInvoiceBiz(saleorgid);

    // ���ֳ��ⵥ�ɶԳ�����
    for (SquareOutViewVO bluevo : bluevos) {
      // ����ǩ�տ�Ʊ��ҵ������
      List<String> sbiz =
          mifBaseSignInvoice.get(bluevo.getItem().getCsaleorgid());
      // ����ǩ�տ�Ʊ
      if (sbiz != null && sbiz.size() > 0
          && sbiz.contains(bluevo.getHead().getCbiztypeid())) {
        this.countBlueRushNumForSign(bluevo, m4cbvo, redTotalNum);
      }
      // ���ڳ��⿪Ʊ
      else {
        this.countBlueRushNumForOut(bluevo, m4cbvo, redTotalNum);
      }
    }

    // ���ڶԳ��������ܲ����ڳ��ⵥ��������������Ҫ���¼���
    new ProcessWC().reCalNumMny(bluevos);
  }

  private void countBlueRushNumForOut(SquareOutViewVO bluevo,
      Map<String, SaleOutBodyVO> m4cbvo, UFDouble redTotalNum) {
    // ��������
    UFDouble nout = bluevo.getItem().getNnum();
    // �ۼƿ�Ʊ����
    UFDouble ninvoice =
        m4cbvo.get(bluevo.getItem().getCsquarebillbid()).getNsignnum();
    ninvoice = MathTool.nvl(ninvoice);
    // �ۼ�;������
    UFDouble nwas =
        m4cbvo.get(bluevo.getItem().getCsquarebillbid()).getNaccumwastnum();
    nwas = MathTool.nvl(nwas);
    // �ۼƳ���Գ�����
    UFDouble nrush =
        m4cbvo.get(bluevo.getItem().getCsquarebillbid()).getNrushnum();
    nrush = MathTool.nvl(nrush);
    // �ۼƽ�������
    UFDouble nsquare = bluevo.getItem().getNsquarearnum();
    // ֻ����Ӧ�ս�����ֹ�����
    if (nsquare == null || nsquare.compareTo(UFDouble.ZERO_DBL) == 0) {
      UFBoolean bautosquarecost = bluevo.getHead().getBautosquarecost();
      if (!bautosquarecost.booleanValue()) {
        nsquare = bluevo.getItem().getNsquareianum();
      }
    }
    nsquare = MathTool.nvl(nsquare);

    UFDouble temp = ninvoice.add(nwas);
    if (temp.compareTo(nsquare) >= 0) {
      nsquare = temp;
    }

    UFDouble ncanrushout = nout.sub(nsquare).sub(nrush);
    this.setBlueRushNum(bluevo, ncanrushout, redTotalNum);
  }

  private void countBlueRushNumForSign(SquareOutViewVO bluevo,
      Map<String, SaleOutBodyVO> m4cbvo, UFDouble redTotalNum) {
    // �ۼ�ǩ������
    UFDouble nsign =
        m4cbvo.get(bluevo.getItem().getCsquarebillbid()).getNaccumoutsignnum();
    // �ۼƿ�Ʊ����
    UFDouble ninvoice =
        m4cbvo.get(bluevo.getItem().getCsquarebillbid()).getNsignnum();
    // �ۼƳ���Գ�����
    UFDouble nrush =
        m4cbvo.get(bluevo.getItem().getCsquarebillbid()).getNrushnum();
    // �ɶԳ����� = �ۼ�ǩ������ - �ۼƿ�Ʊ���� - �ۼƳ���Գ�����
    UFDouble ncanrushout = nsign.sub(ninvoice).sub(nrush);

    this.setBlueRushNum(bluevo, ncanrushout, redTotalNum);
  }

  private void countRedRushNum(SquareOutViewVO[] redvos,
      Map<String, SaleOutBodyVO> m4cbvo) {
    // ���ֳ��ⵥ�ɶԳ�����
    SaleOutBodyVO outbvo = null;
    for (SquareOutViewVO redvo : redvos) {
      outbvo = m4cbvo.get(redvo.getItem().getCsquarebillbid());
      UFDouble total32num = outbvo.getNsignnum();
      UFDouble total4453num = outbvo.getNaccumwastnum();
      UFDouble totalrushnum = outbvo.getNrushnum();
      // ֻ����Ӧ��
      UFDouble total33arnum = redvo.getItem().getNsquarearnum();
      if (total32num != null && total32num.compareTo(UFDouble.ZERO_DBL) != 0
          || total4453num != null
          && total4453num.compareTo(UFDouble.ZERO_DBL) != 0
          || totalrushnum != null
          && totalrushnum.compareTo(UFDouble.ZERO_DBL) != 0
          || total33arnum != null
          && total33arnum.compareTo(UFDouble.ZERO_DBL) != 0) {
        redvo.getItem().setNthisnum(UFDouble.ZERO_DBL);
      }
      else {
        redvo.getItem().setNthisnum(redvo.getItem().getNnum());
      }
      // ���ֳ��ⵥ�ɶԳ���������Ϊ0
      this.checkOutNum(redvo);
    }
  }

  private void countRushNum(SquareOutViewVO[] bluevos,
      SquareOutViewVO[] redvos, Map<String, SaleOutBodyVO> m4cbvo) {
    this.countRedRushNum(redvos, m4cbvo);

    // ���ֳ��ⵥ�ۼƿɳ���Գ������ľ���ֵ
    UFDouble redTotalNum = UFDouble.ZERO_DBL;
    for (SquareOutViewVO redvo : redvos) {
      redTotalNum = MathTool.add(redTotalNum, redvo.getItem().getNthisnum());
    }
    redTotalNum = MathTool.abs(redTotalNum);

    this.countBlueRushNum(bluevos, m4cbvo, redTotalNum);
  }

  /**
   * 
   * @param bluevo ------- ���ֳ�������㵥
   * @param ncanrushout -- ���ֳ��ⵥ�ɳ���Գ�����
   * @param redTotalNum -- ���ֳ��ⵥ�ۼƿɳ���Գ������ľ���ֵ
   */
  private void setBlueRushNum(SquareOutViewVO bluevo, UFDouble ncanrushout,
      UFDouble redTotalNum) {
    // �ɶԳ����� = min(�ɶԳ�����,�ۼƺ��ֳ��ⵥ�ɶԳ�����)
    if (MathTool.lessThan(ncanrushout, redTotalNum)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0030")/*@res "���ֳ��ⵥ�ɶԳ�����������ڵ��ں��ֳ��ⵥ�ɶԳ�����"*/);
    }
    bluevo.getItem().setNthisnum(redTotalNum);
    // ���ֳ��ⵥ�ɶԳ���������Ϊ0
    this.checkOutNum(bluevo);
  }

}
