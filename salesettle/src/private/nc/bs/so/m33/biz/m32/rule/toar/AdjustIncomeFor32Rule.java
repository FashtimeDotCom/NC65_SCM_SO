package nc.bs.so.m33.biz.m32.rule.toar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.pub.calculator.PriceNumMnyCalculatorForVO;
import nc.vo.so.pub.util.AggVOUtil;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.pub.util.SOVOChecker;

import nc.pubitf.so.m33.self.pub.ISquare434CQuery;
import nc.pubitf.so.m33.self.pub.ISquare4353Query;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.AppBsContext;
import nc.bs.so.m33.maintain.m32.query.SquareADQuery;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;

import nc.impl.pubapp.pattern.rule.IFilterRule;

/**
 * @description
 * ��Ӧ��׼�����ݣ����۳��ⵥ�����Զ�Ӧ�ս��㣬���۷�Ʊ��Ӧ�գ�
 * 
 * 1.�����Ʊ�ۼƽ������� != ��������-;������-ǩ�տ�Ʊ�˻س��ⵥ���������ҵ����۳��ⵥ��Ӧ�յ��ĵ��ۡ�
 * a)�����Ʊ���۲��������۳��ⵥ��Ӧ�յ��ĵ��ۣ����ɵ����Ӧ�յ���
 * �����������ԭ�����ݣ�������0�����۴��գ�
 * �� ����Ʊ���� - ���۳��ⵥ��Ӧ�յ��ĵ��ۣ�* ��Ʊ������
 * b)�����Ʊ���۵������۳��ⵥ��Ӧ�յ��ĵ��ۣ������ɵ���Ӧ�յ���
 * 
 * 2.��Ʊ�ۼƽ������� = ��������-;������-ǩ�տ�Ʊ�˻س��ⵥ��������
 * ���ε���Ӧ�ս��=���ν�����+�ƣ��ѽ�����-�ѵ���Ӧ�ս�-����ȷ��Ӧ�ս�
 * ���У��ѵ���Ӧ�ս��Ҫ�������Ѿ�����Ľ��+;������Ӧ�յ����+����ǩ�տ�Ʊ�������˻�����Ӧ�յ���
 * @scene
 * ��Ӧ��׼������
 * @param
 * bTaxPrior ���� ���ۺ�˰����˰����
 * mInvADMny ���ⵥ���η�Ʊ�Ѿ�������  <���γ��ⵥ��id,[0]�ۼ�Ӧ�յ��˰���,[1]�ۼ�Ӧ�յ�����˰���>
 * mnyKey ���ݺ�˰���Ȼ���ȷ�������ֶ�
 * mOutNum ����ǩ�տ�Ʊ�˻س��ⵥ�ۼƴ�Ӧ������  <���γ��ⵥ��id,[0]�ۼ�Ӧ������,[1]�ۼ�Ӧ�պ�˰���,[2]�ۼ�Ӧ����˰���>
 * mWasNum ����;���ۼƴ�Ӧ������  <���γ��ⵥ��id,[0]�ۼ�Ӧ������,[1]�ۼ�Ӧ�պ�˰���,[2]�ۼ�Ӧ����˰���>
 * @author zhangcheng
 * 
 */
public class AdjustIncomeFor32Rule implements IFilterRule<SquareInvVO> {

  private boolean bTaxPrior = true;

  /**
   * ���ⵥ���η�Ʊ�Ѿ�������
   * <���γ��ⵥ��id,[0]�ۼ�Ӧ�յ��˰���,[1]�ۼ�Ӧ�յ�����˰���>
   */
  private Map<String, UFDouble[]> mInvADMny;

  private String mnyKey;

  /**
   * ����ǩ�տ�Ʊ�˻س��ⵥ�ۼƴ�Ӧ������
   * <���γ��ⵥ��id,[0]�ۼ�Ӧ������,[1]�ۼ�Ӧ�պ�˰���,[2]�ۼ�Ӧ����˰���>
   */
  private Map<String, UFDouble[]> mOutNum;

  /**
   * ����;���ۼƴ�Ӧ������
   * <���γ��ⵥ��id,[0]�ۼ�Ӧ������,[1]�ۼ�Ӧ�պ�˰���,[2]�ۼ�Ӧ����˰���>
   */
  private Map<String, UFDouble[]> mWasNum;

  // ���ݻ����Ƿ�˰��ȷ���Ǽ�˰�ϼƻ�����˰���
  private String priceKey;

  public AdjustIncomeFor32Rule() {
    this.setKey();
  }

  @Override
  public SquareInvVO[] process(SquareInvVO[] vos) {
    // ��ʼ���������Ѿ�����Ľ�����;�𵥡�ǩ���˻ص����۳��ⵥ�ۼƴ�Ӧ������
    this.initADWasReturnOutData(vos);

    // ��ѯ���γ��ⵥӦ�ս�������<���ⵥ��id,������ϸVO>
    Map<String, SquareOutDetailVO> m_dvo = this.queryUpARSquareOutVO(vos);

    // ��ѯͬһ�����γ��ⵥ���η�Ʊ�ۼƽ�����Ϣ���������Σ�<���ⵥ��id,(���η�Ʊ�ۼƽ�������[0],[1]�ۼƽ�����)>
    Map<String, UFDouble[]> m4cbidnum =
        new SquareADQuery().queryTotalSquareNumBy4C(vos);

    // ��Ʊ�ۼƽ��������������������ͬ-����Ӧ��
    List<SquareInvViewVO> l_noEqual = new ArrayList<SquareInvViewVO>();
    // ��Ʊ�ۼƽ������������������ͬ-����Ӧ��
    List<SquareInvViewVO> l_Equal = new ArrayList<SquareInvViewVO>();
    // ��Ʊ�ۼƽ������������������ͬ-����Ӧ��(����Ҫ���������)
    List<SquareInvViewVO> l_EqualZeroMny = new ArrayList<SquareInvViewVO>();
    SquareInvViewVO[] sqviewvos =
        SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(vos);
    for (SquareInvViewVO svo : sqviewvos) {
      // ���۷�Ʊ�������۳��ⵥ��id
      String srcoutbid = svo.getItem().getCsrcbid();
      // ���۷�Ʊ������������Դ�����ۿ���
      if (PubAppTool.isNull(srcoutbid)) {
        l_noEqual.add(svo);
        continue;
      }
      // ���γ��ⵥӦ�ս�������
      SquareOutDetailVO sqare4cvo = m_dvo.get(srcoutbid);
      // ���۷�Ʊ����Դ�����۳��ⵥ���������۶�����������У���ֱ�Ӵ�Ӧ��
      if (null == sqare4cvo) {
        l_noEqual.add(svo);
        continue;
      }
      UFDouble price32 =
          ValueUtils
              .getUFDouble(svo.getItem().getAttributeValue(this.priceKey));
      UFDouble price4C =
          ValueUtils.getUFDouble(sqare4cvo.getAttributeValue(this.priceKey));
      UFDouble[] invTotalNumMny = m4cbidnum.get(srcoutbid);
      // ���ⵥ����;������
      UFDouble wasNum =
          null == this.mWasNum || null == this.mWasNum.get(srcoutbid) ? UFDouble.ZERO_DBL
              : this.mWasNum.get(srcoutbid)[0].abs();
      // ���ⵥ����ǩ�տ�Ʊ�˻س��ⵥ������
      UFDouble returnNum =
          null == this.mOutNum || null == this.mOutNum.get(srcoutbid) ? UFDouble.ZERO_DBL
              : this.mOutNum.get(srcoutbid)[0].abs();
      // ��������-;������-ǩ�տ�Ʊ�˻س��ⵥ������
      UFDouble outwasreturnNum =
          MathTool.sub(sqare4cvo.getNsquarenum(),
              MathTool.add(wasNum, returnNum));

      // ��Ʊ�ۼƽ������� != ��������-;������-ǩ�տ�Ʊ�˻س��ⵥ������
      if (MathTool.compareTo(invTotalNumMny[0], outwasreturnNum) != 0) {
        // �� ����Ʊ���� - ���۳��ⵥ��Ӧ�յ��ĵ��ۣ�* ��Ʊ����
        if (MathTool.compareTo(price32, price4C) != 0) {
          UFDouble mny = price32.sub(price4C).multiply(svo.getItem().getNnum());
          mny =
              ScaleUtils.getScaleUtilAtBS().adjustMnyScale(mny,
                  svo.getItem().getCorigcurrencyid());
          svo.getItem().setAttributeValue(this.mnyKey, mny);
          l_noEqual.add(svo);
        }
      }

      /**
       * ��Ʊ�ۼƽ������� = ��������-;������-ǩ�տ�Ʊ�˻س��ⵥ������:
       * ���ε���Ӧ�ս��=���ν�����-����ȷ��Ӧ�ս�� + ���ѽ�����-���ѵ���Ӧ�ս�
       * �ѵ���Ӧ�ս��Ҫ������
       * �Ѿ�����Ľ��+;������Ӧ�յ����+����ǩ�տ�Ʊ�������˻�����Ӧ�յ���
       **/
      else {
        // mny = ���ν����� + ���ѽ����� - ����ȷ��Ӧ�ս��
        // invTotalNumMny[1] = ���ν����� + ���ѽ�����
        UFDouble invTotalTaxMny = invTotalNumMny[1];
        UFDouble invTotalMny = invTotalNumMny[2];
        UFDouble taxmny = invTotalTaxMny.sub(sqare4cvo.getNorigtaxmny());
        UFDouble mny = invTotalMny.sub(sqare4cvo.getNorigmny());
        // 2013-07-΢��˰��ʱ����˰�����˰�ϼƣ�ֻ��ĳһ��Ϊ0�����
        if (!MathTool.equals(UFDouble.ZERO_DBL, taxmny)
            && MathTool.equals(UFDouble.ZERO_DBL, mny)
            || !MathTool.equals(UFDouble.ZERO_DBL, mny)
            && MathTool.equals(UFDouble.ZERO_DBL, taxmny)) {
          svo.getItem().setNorigtaxmny(taxmny);
          svo.getItem().setNorigmny(mny);
          svo.getItem().setNtaxmny(taxmny);
          svo.getItem().setNmny(mny);
          UFDouble tax =
              MathTool.equals(UFDouble.ZERO_DBL, taxmny) ? mny : taxmny;
          svo.getItem().setNtax(tax);
          l_EqualZeroMny.add(svo);
        }
        else {
          svo.getItem().setNorigtaxmny(taxmny);
          svo.getItem().setNorigmny(mny);
          l_Equal.add(svo);
        }
      }
    }

    // ��Ʊ�ۼƽ������������������ͬ:�����Ѿ�����Ľ�;���ǩ���˻�
    this.processEqual(l_Equal);

    // ��Ʊ�ۼƽ����������������-;��-ǩ���˻���������ͬ
    this.processNoEqual(l_noEqual);
    // ��Ʊ�ۼƽ������������������ͬ:�����Ѿ�����Ľ�;���ǩ���˻�(����˰���˰�����˰�ϼ�Ϊ0�����
    this.processEqualZeroMny(l_EqualZeroMny);
    // �ϲ�һ��
    l_Equal.addAll(l_EqualZeroMny);

    // �����������ԭ�����ݣ�������0�����۴���
    return this.setDataForAD(l_Equal, l_noEqual);

  }

  private void processEqualZeroMny(List<SquareInvViewVO> l_EqualZeroMny) {
    if (l_EqualZeroMny.size() == 0) {
      return;
    }
    SquareInvBVO[] bvos =
        SquareInvVOUtils.getInstance().getSquareInvBVO(
            l_EqualZeroMny.toArray(new SquareInvViewVO[l_EqualZeroMny.size()]));
    // ���½��㱾�ҽ��
    new PriceNumMnyCalculatorForVO().calculateByTax(bvos);
  }

  private void initADWasReturnOutData(SquareInvVO[] vos) {
    String[] outBids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareInvBVO.CSRCBID,
            String.class);
    // ��ѯ���ⵥ���η�Ʊ�Ѿ�������
    Map<String, UFDouble[]> mInvADMny1 =
        new SquareADQuery().queryTotalSquareADMnyBy4C(outBids);
    this.mInvADMny = mInvADMny1;

    // ��ѯ����;���ۼƴ�Ӧ������
    ISquare4353Query qry53 =
        NCLocator.getInstance().lookup(ISquare4353Query.class);
    Map<String, UFDouble[]> mWasNum1 = qry53.queryARNumBy4CBID(outBids);
    this.mWasNum = mWasNum1;

    // ��ѯ����ǩ�տ�Ʊ�˻س��ⵥ�ۼƴ�Ӧ������
    ISquare434CQuery qry4C =
        NCLocator.getInstance().lookup(ISquare434CQuery.class);
    Map<String, UFDouble[]> mOutNum1 = qry4C.queryARNumBy4CBID(outBids);
    this.mOutNum = mOutNum1;
  }

  /**
   * ����������������Ʊ�ۼƽ������������������ͬ
   * <p>
   * ���ε���Ӧ�ս��=���ν�����-����ȷ��Ӧ�ս�� + ���ѽ�����-���ѵ���Ӧ�ս�
   * <p>
   * ���У��ѵ���Ӧ�ս��Ҫ�����������Ѿ�����Ľ��+;������Ӧ�յ����+����ǩ�տ�Ʊ�������˻�����Ӧ�յ���
   * <p>
   * vo�еĽ�� = ���ν�����-����ȷ��Ӧ�ս�� + ���ѽ�����˴�ֻ��Ҫ-���ѵ���Ӧ�ս� <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param l_Equal
   * @param m_4cdvo
   *          <p>
   * @author zhangcheng
   * @time 2010-9-7 ����07:24:44
   */
  private void processEqual(List<SquareInvViewVO> l_Equal) {
    if (l_Equal.size() > 0) {
      SquareInvViewVO[] svvos = l_Equal.toArray(new SquareInvViewVO[0]);
      if (!SOVOChecker.isEmpty(this.mInvADMny)
          || !SOVOChecker.isEmpty(this.mWasNum)
          || !SOVOChecker.isEmpty(this.mOutNum)) {
        for (SquareInvViewVO vvo : svvos) {
          String outBid = vvo.getItem().getCsrcbid();
          // admny = ;������Ӧ�յ����+����ǩ�տ�Ʊ�������˻�����Ӧ�յ���
          UFDouble adTaxMny = UFDouble.ZERO_DBL;
          UFDouble adMny = UFDouble.ZERO_DBL;
          if (!SOVOChecker.isEmpty(this.mInvADMny)) {
            UFDouble invADTaxMny = this.mInvADMny.get(outBid)[0];
            UFDouble invADMny = this.mInvADMny.get(outBid)[1];
            adTaxMny = adTaxMny.add(invADTaxMny);
            adMny = adMny.add(invADMny);
          }
          // ���г��ⵥ �п���ֻ�����е�������;��
          if (!SOVOChecker.isEmpty(this.mWasNum)
              && this.mWasNum.containsKey(outBid)) {
            UFDouble wasTaxMny = this.mWasNum.get(outBid)[1];
            UFDouble wasMny = this.mWasNum.get(outBid)[2];
            adTaxMny = adTaxMny.add(wasTaxMny);
            adMny = adMny.add(wasMny);
          }
          if (!SOVOChecker.isEmpty(this.mOutNum)) {
            UFDouble outTaxMny = this.mOutNum.get(outBid)[1];
            UFDouble outMny = this.mOutNum.get(outBid)[2];
            adTaxMny = adTaxMny.add(outTaxMny);
            adMny = adMny.add(outMny);
          }
          vvo.getItem().setNorigtaxmny(
              MathTool.sub(vvo.getItem().getNorigtaxmny(), adTaxMny));
          vvo.getItem().setNorigmny(
              MathTool.sub(vvo.getItem().getNorigmny(), adMny));
        }
      } // end if
      // ��β������������
      this.calculateWithMargin(svvos);

    }
  }

  /**
   * ��β������������
   * 
   * @param svvos
   */
  private void calculateWithMargin(SquareInvViewVO[] svvos) {
    SquareInvBVO[] bvos = SquareInvVOUtils.getInstance().getSquareInvBVO(svvos);
    Map<String, UFDouble[]> sameCurBvo = new HashMap<String, UFDouble[]>();
    for (SquareInvBVO item : bvos) {
      UFDouble[] mny = new UFDouble[2];
      mny[0] = item.getNorigtaxmny();
      mny[1] = item.getNorigmny();
      sameCurBvo.put(item.getCsquarebillbid(), mny);
    }
    // ���½��㱾�ҽ��
    new PriceNumMnyCalculatorForVO().calculateLocal(bvos);
    // ԭ����һ�µ����������һ�µ���
    for (SquareInvBVO item : bvos) {
      if (item.getCorigcurrencyid() != null
          && item.getCorigcurrencyid().equals(item.getCcurrencyid())) {
        UFDouble[] mny = sameCurBvo.get(item.getCsquarebillbid());
        if (mny != null) {
          item.setNorigtaxmny(mny[0]);
          item.setNorigmny(mny[1]);
          item.setNtaxmny(mny[0]);
          item.setNmny(mny[1]);
          item.setNtax(MathTool.sub(mny[0], mny[1]));
        }
      }
    }
  }

  private void processNoEqual(List<SquareInvViewVO> l_noEqual) {
    if (l_noEqual.size() > 0) {
      SquareInvViewVO[] svvos = l_noEqual.toArray(new SquareInvViewVO[0]);
      SquareInvBVO[] bvos =
          SquareInvVOUtils.getInstance().getSquareInvBVO(svvos);
      // ������¼���
      new PriceNumMnyCalculatorForVO().calculate(bvos, this.mnyKey);
    }
  }

  /**
   * ����������������ѯ���γ��ⵥӦ�ս�������
   * <p>
   * <���ⵥ��id,������ϸVO>
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <���ⵥ��id,������ϸVO>
   *         <p>
   * @author zhangcheng
   * @time 2010-7-1 ����07:02:45
   */
  private Map<String, SquareOutDetailVO> queryUpARSquareOutVO(SquareInvVO[] vos) {
    // ���۷�Ʊ��Դ����id
    String[] icbids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareInvBVO.CSRCBID,
            String.class);
    SquareOutDetailVO[] sdvos =
        new QuerySquare4CVOBP().querySquareOutDetailVOBy4CBID(icbids);
    List<SquareOutDetailVO> listvo = new ArrayList<SquareOutDetailVO>();
    if (!ArrayUtils.isEmpty(sdvos)) {
      for (SquareOutDetailVO vo : sdvos) {
        if (SquareType.SQUARETYPE_AR.getIntegerValue().equals(
            vo.getFsquaretype())) {
          listvo.add(vo);
        }
      }
    }
    // <���ⵥ��id,������ϸVO>
    Map<String, SquareOutDetailVO> m_dvo =
        new HashMap<String, SquareOutDetailVO>();
    if (listvo.size() > 0) {
      sdvos = listvo.toArray(new SquareOutDetailVO[listvo.size()]);
      for (SquareOutDetailVO dvo : sdvos) {
        m_dvo.put(dvo.getCsquarebillbid(), dvo);
      }
    }
    return m_dvo;
  }

  /**
   * �����������ԭ�����ݣ�������0�����۴���
   */
  private SquareInvVO[] setDataForAD(List<SquareInvViewVO> l_Equal,
      List<SquareInvViewVO> l_noEqual) {
    // �ϲ�
    l_Equal.addAll(l_noEqual);
    SquareInvVO[] retvos =
        SquareInvVOUtils.getInstance().combineBill(
            l_Equal.toArray(new SquareInvViewVO[0]));

    if (SOVOChecker.isEmpty(retvos)) {
      return null;
    }

    List<SquareInvViewVO> list = new LinkedList<SquareInvViewVO>();
    SquareInvViewVO[] views =
        SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(retvos);
    for (SquareInvViewVO view : views) {
      SquareInvBVO bvo = view.getItem();
      UFDouble norigtaxmny = bvo.getNorigtaxmny();
      UFDouble norigmny = bvo.getNorigmny();
      // 2013-07-20 ����˰��ʱ������һ�ֽ��Ϊ�յ����
      if (!(MathTool.isZero(norigtaxmny) && MathTool.isZero(norigmny))) {
        list.add(view);
      }
    }

    int size = list.size();
    if (size > 0) {
      SquareInvViewVO[] retviews = list.toArray(new SquareInvViewVO[size]);
      SquareInvVO[] rets = SquareInvVOUtils.getInstance().combineBill(retviews);
      for (SquareInvVO svo : rets) {
        for (SquareInvBVO bvo : svo.getChildrenVO()) {
          bvo.setDifftoarsquarenum(bvo.getNthisnum());
          bvo.setNthisnum(UFDouble.ZERO_DBL);
          bvo.setNorignetprice(null);
          bvo.setNorigprice(null);
          bvo.setNorigtaxnetprice(null);
          bvo.setNorigtaxprice(null);
          bvo.setNprice(null);
          bvo.setNnetprice(null);
          bvo.setNtaxprice(null);
          bvo.setNtaxnetprice(null);
        }
      }
      return rets;
    }
    return null;
  }

  /**
   * �����������������ݺ�˰���Ȼ���ȷ�������ֶ�
   */
  private void setKey() {
    // ���ݺ�˰���Ȼ���ȷ�������ֶ�
    this.bTaxPrior =
        SOSysParaInitUtil.getSO23(AppBsContext.getInstance().getPkGroup())
            .booleanValue();
    this.priceKey =
        this.bTaxPrior ? SquareInvBVO.NORIGTAXNETPRICE
            : SquareInvBVO.NORIGNETPRICE;
    this.mnyKey =
        this.bTaxPrior ? SquareInvBVO.NORIGTAXMNY : SquareInvBVO.NORIGMNY;
  }

}
