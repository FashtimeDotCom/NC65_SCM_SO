package nc.bs.so.m33.biz.m4453.rule.ar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyInfo;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.calculator.PriceNumMnyCalculatorForVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.util.SOCurrencyUtil;

/**
 * @description
 * /** ����;��Ӧ�ս���ǰ���ʴ��� 1.���»�ȡ�����۱����ʡ�ȫ���۱����ʡ���֯�۱�����
 * 2.���������֯��λ�Һ�Ӧ��/Ӧ����֯��λ��һ�£��ӹ�Ӧ��������Ӧ����Ʊ���Ա༭���ʣ����ʴ�����Я������
 * ���򣬻���ԭ���������㣨������Ϊ���е�ί��Ӧ��/Ӧ����������֯��Ӧ��/Ӧ����֯�ı�λ�Ҷ���һ�µġ� ��һ�²���Ϊ���Ժ������Ҫ������
 * 3.�����¼��㱾�ҽ��
 * @scene
 * ����;��Ӧ�ս���ǰ���ʴ���
 * @param
 * ��
 * 
 * @author zhangcheng
 * 
 * @since 6.0
 * @version 2011-4-19 ����03:41:07
 * @author zhangcheng
 */
public class FillNewChangeRateFor4453Rule implements IRule<SquareWasVO> {

  @Override
  public void process(SquareWasVO[] vos) {
    
    // ��֯�۱����ʴ���
    List<SquareWasVO> listexchg = new ArrayList<SquareWasVO>();
    Set<String> setchgid = new HashSet<String>();
    this.orgChangeRateProcess(vos,listexchg,setchgid);
    
    // �����۱����ʡ�ȫ���۱����ʴ���
    List<SquareWasVO> listgroupexchg = new ArrayList<SquareWasVO>();
    List<SquareWasVO> listglobalexchg = new ArrayList<SquareWasVO>();
    this.gloupaAllChangeRateProcess(vos,setchgid,listgroupexchg,listglobalexchg);

     // �۱����ʱ仯��
    if (listexchg.size() > 0) {
      SquareWasVO[] exchgvos = new SquareWasVO[listexchg.size()];
      listexchg.toArray(exchgvos);
      this.calLocalMny(exchgvos, SquareWasBVO.NEXCHANGERATE);
    }
    // �����۱����ʱ仯��
    if (listgroupexchg.size() > 0) {
      SquareWasVO[] exchgvos = new SquareWasVO[listgroupexchg.size()];
      listgroupexchg.toArray(exchgvos);
      this.calLocalMny(exchgvos, SquareWasBVO.NGROUPEXCHGRATE);
    }
    // ȫ���۱����ʱ仯��
    if (listgroupexchg.size() > 0) {
      SquareWasVO[] exchgvos = new SquareWasVO[listglobalexchg.size()];
      listglobalexchg.toArray(exchgvos);
      this.calLocalMny(exchgvos, SquareWasBVO.NGLOBALEXCHGRATE);
    }
  }

  /**
   * ���¼�����ر��ҽ��
   * 
   * @param vos
   */
  private void calLocalMny(SquareWasVO[] vos,String chgkey) {
    Condition cond = new Condition();
    PriceNumMnyCalculatorForVO pc = new PriceNumMnyCalculatorForVO();
    pc.setCondition(cond);
    pc.calculate(vos, chgkey);
  }

  /**
   * ȡ���»���
   * 
   * @param vos
   * @param listglobalexchg 
   * @param listgroupexchg 
   * @param setchgid 
   */
  private void gloupaAllChangeRateProcess(SquareWasVO[] vos, Set<String> setchgid, 
      List<SquareWasVO> listgroupexchg, List<SquareWasVO> listglobalexchg) {


    // ����ɵ�ȫ�ֻ��ʡ����Ż���
    for (SquareWasVO svo : vos) {
      IKeyValue keyValue = new VOKeyValue<SquareWasVO>(svo);
      int ilen = keyValue.getBodyCount();
      int[] rows = new int[ilen];
      UFDouble[] oldgroupchgrates = new UFDouble[ilen];
      UFDouble[] oldglobalchgrates = new UFDouble[ilen];
      for (int i = 0; i < ilen; i++) {
        rows[i] = i;
        oldgroupchgrates[i] =
            keyValue.getBodyUFDoubleValue(i, SOItemKey.NGROUPEXCHGRATE);
        oldglobalchgrates[i] =
            keyValue.getBodyUFDoubleValue(i, SOItemKey.NGLOBALEXCHGRATE);
      }

      // �����µļ����۱�����
      SOGroupExchangeRate groupchgrate = new SOGroupExchangeRate(keyValue);
      groupchgrate.calcGroupExchgRateAtBodyByBusidate(rows);

      // �����µ�ȫ���۱�����
      SOGlobalExchangeRate globalerate = new SOGlobalExchangeRate(keyValue);
      globalerate.calcGlobalExchgRateAtBodyByBusidate(rows);

      List<SquareWasBVO> listgroupchgbvo = new ArrayList<SquareWasBVO>();
      List<SquareWasBVO> listglobalchgbvo = new ArrayList<SquareWasBVO>();
      int i = 0;
      for (SquareWasBVO bvo : svo.getChildrenVO()) {
        if (setchgid.contains(bvo.getCsalesquarebid())) {
          continue;
        }
        if (!MathTool.equals(oldgroupchgrates[i], bvo.getNgroupexchgrate())) {
          listgroupchgbvo.add(bvo);
        }
        if (!MathTool.equals(oldglobalchgrates[i], bvo.getNglobalexchgrate())) {
          listglobalchgbvo.add(bvo);
        }
        i++;
      }
      // �����۱����ʱ仯����������¾ۺ�VO
      if (listgroupchgbvo.size() > 0) {
        SquareWasVO squVO = new SquareWasVO();
        squVO.setParentVO(svo.getParentVO());
        SquareWasBVO[] bvos = new SquareWasBVO[listgroupchgbvo.size()];
        listgroupchgbvo.toArray(bvos);
        squVO.setChildrenVO(bvos);
        listgroupexchg.add(squVO);
      }
      // ȫ���۱����ʱ仯����������¾ۺ�VO
      if (listglobalchgbvo.size() > 0) {
        SquareWasVO squVO = new SquareWasVO();
        squVO.setParentVO(svo.getParentVO());
        SquareWasBVO[] bvos = new SquareWasBVO[listglobalchgbvo.size()];
        listglobalchgbvo.toArray(bvos);
        squVO.setChildrenVO(bvos);
        listglobalexchg.add(squVO);
      }
    }

  
  }

  /**
   * ��֯�۱����ʴ���
   * 
   * @param vos
   */
  private void orgChangeRateProcess(SquareWasVO[] vos, 
      List<SquareWasVO> listexchg, Set<String> setchgid) {
    // Ӧ����֯��λ��<Ӧ����֯,Ӧ����֯��λ��>
    Map<String, String> marorg_cy = new HashMap<String, String>();
    for (SquareWasVO svo : vos) {
      for (SquareWasBVO bvo : svo.getChildrenVO()) {
        String arorg = bvo.getCarorgid();
        String arcy = CurrencyInfo.getLocalCurrtypeByOrgID(arorg);
        marorg_cy.put(arorg, arcy);
      }
    }

    UFDate sysdate = BSContext.getInstance().getDate();
    // ����������֯�۱�����
    for (SquareWasVO svo : vos) {
      List<SquareWasBVO> listtchgbvo = new ArrayList<SquareWasBVO>();
      for (SquareWasBVO bvo : svo.getChildrenVO()) {
        String src_currency_pk = bvo.getCorigcurrencyid();
        String sq_currency_pk = bvo.getCcurrencyid();
        String arorg = bvo.getCarorgid();
        String ar_currency_pk = marorg_cy.get(arorg);
        //����ɵ��۱�����
        UFDouble oldchgrate = bvo.getNexchangerate();
        // String pk_group = svo.getParentVO().getPk_group();
        // ������֯��λ�Һ�Ӧ����֯��λ��һ��
        if (PubAppTool.isEqual(sq_currency_pk, ar_currency_pk)) {
          String pk_org = bvo.getPk_org();
          UFDouble nexchangerate =
              SOCurrencyUtil.getInCurrencyRateByOrg(pk_org, src_currency_pk,
                  sq_currency_pk, sysdate);
          bvo.setNexchangerate(nexchangerate);
        }
        // ������֯��λ�Һ�Ӧ����֯��λ�Ҳ�һ��
        else {
          UFDouble nexchangerate =
              SOCurrencyUtil.getInCurrencyRateByOrg(arorg, src_currency_pk,
                  ar_currency_pk, sysdate);
          bvo.setNexchangerate(nexchangerate);
        }
        if(!MathTool.equals(oldchgrate, bvo.getNexchangerate())){
          setchgid.add(bvo.getCsalesquarebid());
          listtchgbvo.add(bvo);
        }
      }
      //���������vo
      if(listtchgbvo.size() >0){
        SquareWasVO squVO = new SquareWasVO();
        squVO.setParentVO(svo.getParentVO());
        SquareWasBVO[] bvos = new SquareWasBVO[listtchgbvo.size()];
        listtchgbvo.toArray(bvos);
        squVO.setChildrenVO(bvos);
        listexchg.add(squVO);
      }
    }
  }

}
