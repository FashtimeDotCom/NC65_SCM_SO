package nc.vo.so.m33.m4453.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.md.model.impl.MDEnum;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.bill.CombineBill;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.pub.util.SOVOChecker;

public class SquareWasVOUtils {

  private static SquareWasVOUtils squtil = new SquareWasVOUtils();

  private SquareWasVOUtils() {
    super();
  }

  public static SquareWasVOUtils getInstance() {
    return SquareWasVOUtils.squtil;
  }

  /**
   * ����ȷ��Ӧ�ս��㵥VOת��Ϊ��ȷ��Ӧ�ս�����ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareWasDetailVO[] changeSQVOtoSQDVOForAR(SquareWasVO[] sqvos) {
    // ���ý�������:ȷ��Ӧ��
    return this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_AR);
  }

  /**
   * ���س���㵥VOת��Ϊ���س������ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareWasDetailVO[] changeSQVOtoSQDVOForARRUSH(SquareWasVO[] sqvos) {
    // ���ý�������:�ɱ�����
    return this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_ARRUSH);
  }

  /**
   * �����ݹ����㵥VOת��Ϊ���ݹ�Ӧ�ս�����ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareWasDetailVO[] changeSQVOtoSQDVOForET(SquareWasVO[] sqvos) {
    // ���ý�������:�ɱ�����
    return this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_ET);
  }

  /**
   * �����ɱ����㵥VOת��Ϊ���ɱ�Ӧ�ս�����ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareWasDetailVO[] changeSQVOtoSQDVOForIA(SquareWasVO[] sqvos) {
    // ���ý�������:�ɱ�����
    return this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_IA);
  }

  /**
   * ������Գ���㵥VOת��Ϊ������Գ������ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareWasDetailVO[] changeSQVOtoSQDVOForOUTRUSH(SquareWasVO[] sqvos) {
    // ���ý�������:�ɱ�����
    return this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_OUTRUSH);
  }

  /**
   * ����������Ʒ���㵥VOת��Ϊ��������Ʒ������ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareWasDetailVO[] changeSQVOtoSQDVOForREG(SquareWasVO[] sqvos) {
    // ���ý�������:�ɱ�����
    return this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_REG_DEBIT);
  }

  /**
   * ���ۺ�VO����ת��Ϊ��ͼVO����
   * 
   * @param sqvos
   * @return
   */
  public SquareWasViewVO[] changeToSaleSquareViewVO(SquareWasVO[] sqvos) {
    List<SquareWasViewVO> list = new ArrayList<SquareWasViewVO>();
    for (SquareWasVO svo : sqvos) {
      list.addAll(Arrays.asList(svo.changeToSquareWasViewVO()));
    }
    return new ListToArrayTool<SquareWasViewVO>().convertToArray(list);
  }

  public SquareWasVO[] combineBill(SquareWasViewVO[] vos) {
    int len = vos.length;
    SquareWasVO[] bills = new SquareWasVO[len];
    for (int i = 0; i < len; i++) {
      bills[i] = vos[i].changeToSquareWasVO();
    }
    CombineBill<SquareWasVO> cb = new CombineBill<SquareWasVO>();
    cb.appendKey(SquareWasHVO.CSALESQUAREID);
    return cb.combine(bills);
  }

  public SquareWasVO[] composite(SquareWasHVO[] hvos, SquareWasBVO[] bvos) {
    BillComposite<SquareWasVO> bc =
        new BillComposite<SquareWasVO>(SquareWasVO.class);
    SquareWasVO svo = new SquareWasVO();
    bc.append(svo.getMetaData().getParent(), hvos);
    bc.append(svo.getMetaData().getVOMeta(SquareWasBVO.class), bvos);
    return bc.composite();
  }

  /**
   * ����ϸID����SquareVO
   * 
   * @param sqvos
   * @return
   */
  public void fillDidToSquareVO(SquareWasVO[] sqvos, SquareWasDetailVO[] dvos) {
    Map<String, SquareWasDetailVO> map =
        new HashMap<String, SquareWasDetailVO>();
    for (SquareWasDetailVO dvo : dvos) {
      map.put(dvo.getCsalesquarebid(), dvo);
    }
    for (SquareWasVO svo : sqvos) {
      for (SquareWasBVO bvo : svo.getChildrenVO()) {
        bvo.setCsalesquaredid(map.get(bvo.getCsalesquarebid())
            .getCsalesquaredid());
      }
    }
  }

  /**
   * �����������������˿��Դ�����Ľ���vo
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @author zhangcheng
   * @time 2010-8-10 ����02:55:33
   */
  public SquareWasVO[] filterCostableVO(SquareWasVO[] vos) {
    SquareWasViewVO[] sviewvos = this.changeToSaleSquareViewVO(vos);
    List<SquareWasViewVO> list = new ArrayList<SquareWasViewVO>();
    for (SquareWasViewVO view : sviewvos) {
      if (view.getItem().getBcost() == null
          || view.getItem().getBcost().booleanValue()) {
        list.add(view);
      }
    }
    if (list.size() == 0) {
      return null;
    }
    SquareWasViewVO[] retviewVOs = list.toArray(new SquareWasViewVO[0]);
    SquareWasVO[] retvos = this.combineBill(retviewVOs);
    return retvos;
  }

  public SquareWasVO[] filterIncomeableVO(SquareWasVO[] vos) {
    SquareWasViewVO[] sviewvos = this.changeToSaleSquareViewVO(vos);
    List<SquareWasViewVO> list = new ArrayList<SquareWasViewVO>();
    for (SquareWasViewVO view : sviewvos) {
      if (view.getItem().getBincome() == null
          || view.getItem().getBincome().booleanValue()) {
        list.add(view);
      }
    }
    if (list.size() == 0) {
      return null;
    }
    SquareWasViewVO[] retviewVOs = list.toArray(new SquareWasViewVO[0]);
    SquareWasVO[] retvos = this.combineBill(retviewVOs);
    return retvos;
  }

  /**
   * ��������������;�𵥽���ר�ã����ݳ�����㵥��������;����㵥Ӧ�ս�����Ϊ
   * <p>
   * <b>����˵��</b>
   * 
   * @param squaretype
   * @return <p>
   * @author zhangcheng
   * @time 2010-8-30 ����01:25:10
   */
  public Integer getARSquareType(Integer squaretype) {
    Integer ret = null;
    if (SquareType.SQUARETYPE_AR.getIntValue() == squaretype.intValue()) {
      ret = squaretype;
    }
    else if (SquareType.SQUARETYPE_ET.getIntValue() == squaretype.intValue()) {
      ret = SquareType.SQUARETYPE_ARRUSH.getIntegerValue();
    }
    return ret;
  }

  /**
   * ��������������;�𵥽���ר�ã����ݳ�����㵥��������;����㵥�ɱ�������Ϊ
   * <p>
   * <b>����˵��</b>
   * 
   * @param squaretype
   * @return <p>
   * @author zhangcheng
   * @time 2010-8-30 ����01:25:13
   */
  public Integer getIASquareType(Integer squaretype) {
    Integer ret = null;
    if (SquareType.SQUARETYPE_IA.getIntValue() == squaretype.intValue()) {
      ret = squaretype;
    }
    else if (SquareType.SQUARETYPE_REG_DEBIT.getIntValue() == squaretype
        .intValue()) {
      ret = squaretype;
    }
    return ret;
  }

  public SquareWasBVO[] getSquareWasBVO(SquareWasVO[] sqvos) {
    List<SquareWasBVO> blist = new ArrayList<SquareWasBVO>();
    for (SquareWasVO vo : sqvos) {
      for (SquareWasBVO bvo : vo.getChildrenVO()) {
        blist.add(bvo);
      }
    }
    return blist.toArray(new SquareWasBVO[0]);
  }

  public SquareWasHVO[] getSquareWasHVO(SquareWasVO[] sqvos) {
    int len = sqvos.length;
    SquareWasHVO[] hvos = new SquareWasHVO[len];
    for (int i = 0; i < len; i++) {
      hvos[i] = (SquareWasHVO) sqvos[i].getParent();
    }
    return hvos;
  }

  public void setMinusToNumMny(SquareWasViewVO[] sqvvos) {
    for (SquareWasViewVO svvo : sqvvos) {
      SquareWasBVO bvo = svvo.getItem();

      bvo.setNthisnum(MathTool.oppose(bvo.getNthisnum()));

      bvo.setNorigtaxmny(MathTool.oppose(bvo.getNorigtaxmny()));
      bvo.setNorigmny(MathTool.oppose(bvo.getNorigmny()));

      bvo.setNtaxmny(MathTool.oppose(bvo.getNtaxmny()));
      bvo.setNmny(MathTool.oppose(bvo.getNmny()));
      bvo.setNtax(MathTool.oppose(bvo.getNtax()));

      bvo.setNgrouptaxmny(MathTool.oppose(bvo.getNgrouptaxmny()));
      bvo.setNgroupmny(MathTool.oppose(bvo.getNgroupmny()));
      bvo.setNglobaltaxmny(MathTool.oppose(bvo.getNglobaltaxmny()));
      bvo.setNglobalmny(MathTool.oppose(bvo.getNglobalmny()));

      // V61�����ֶ�
      bvo.setNcaltaxmny(MathTool.oppose(bvo.getNcaltaxmny()));

    }
  }

  /**
   * ;���ݹ�Ӧ��������;����������ͳ��ⵥ�ݹ�Ӧ����������Сֵ
   * 
   * @param view ���ݹ�Ӧ��;������㵥
   * @param outSquareNum ���۳��ⵥ�ݹ�Ӧ������
   */
  public void setNthisETRushNumUseMinNum(SquareWasViewVO view, UFDouble netnum) {
    UFDouble nwasnum =
        MathTool.sub(view.getItem().getNnum(), view.getItem().getNarrushnum());
    UFDouble nthisnum = nwasnum;
    if (MathTool.greaterThan(nwasnum.abs(), netnum.abs())) {
      nthisnum = MathTool.oppose(netnum);
    }
    view.getItem().setNnum(nthisnum);
    view.getItem().setNthisnum(nthisnum);
  }

  /**
   * ���������������ֹ�ȡ������ǰ���ñ��ν��������������Ļ�д����ʹ��
   * ��Ϊ���������ǻ���SquareWasVO���˴�����SquareWasVO��Nthisnum,
   * 
   * @param sqdvos
   * @param sqvos
   *          <p>
   * @author zhangcheng
   * @time 2010-7-1 ����06:07:39
   */
  public void setNthisnumForCancelSquare(SquareWasDetailVO[] sqdvos,
      SquareWasVO[] sqvos) {
    // <BID,SquareWasDetailVO>
    Map<String, SquareWasDetailVO> msdvo =
        new HashMap<String, SquareWasDetailVO>();
    String bid = null;
    for (SquareWasDetailVO dvo : sqdvos) {
      dvo.setNsquarenum(MathTool.oppose(dvo.getNsquarenum()));
      dvo.setNorigtaxmny(MathTool.oppose(dvo.getNorigtaxmny()));
      bid = dvo.getCsalesquarebid();
      if (!msdvo.containsKey(bid)) {
        msdvo.put(bid, dvo);
      }
    }

    for (SquareWasVO svo : sqvos) {
      for (SquareWasBVO bvo : svo.getChildrenVO()) {
        SquareWasDetailVO dvo = msdvo.get(bvo.getCsalesquarebid());
        if (!SOVOChecker.isEmpty(dvo)) {
          bvo.setNthisnum(dvo.getNsquarenum());
          bvo.setNorigtaxmny(dvo.getNorigtaxmny());
        }
        else {
          bvo.setNthisnum(MathTool.oppose(bvo.getNnum()));
          bvo.setNorigtaxmny(MathTool.oppose(bvo.getNorigtaxmny()));
        }
      }
    }

  }

  /**
   * ;�𵥷�����Ʒ������;����������ͳ��ⵥ������Ʒ��������Сֵ
   * 
   * @param view ��������Ʒ;������㵥
   * @param outSquareNum ���۳��ⵥ������Ʒ����
   */
  public void setNthisREGNumUseMinNum(SquareWasViewVO view, UFDouble nregnum) {
    UFDouble nwasnum =
        MathTool.sub(view.getItem().getNnum(), view.getItem()
            .getNsquareregnum());
    UFDouble nthisnum = nwasnum;
    if (MathTool.greaterThan(nwasnum.abs(), nregnum.abs())) {
      nthisnum = MathTool.oppose(nregnum);
    }
    view.getItem().setNnum(nthisnum);
    view.getItem().setNthisnum(nthisnum);
  }

  public Map<String, List<SquareWasVO>> splitBillByTransType(SquareWasVO[] svos) {
    if (svos == null) {
      return null;
    }
    Map<String, List<SquareWasVO>> map =
        new HashMap<String, List<SquareWasVO>>();
    String key = null;
    List<SquareWasVO> list = null;
    for (SquareWasVO sdvo : svos) {
      key = sdvo.getParentVO().getVtrantypecode();
      list = map.get(key);
      if (list == null) {
        list = new ArrayList<SquareWasVO>();
        map.put(key, list);
      }
      list.add(sdvo);
    }
    return map;
  }

  /**
   * ���ս������ͷ���SquareWasDetailVO
   * 
   * @param sqdvos
   * @return <����,List<SquareWasDetailVO>> <�ɱ�,List<SquareWasDetailVO>>
   *         <�س�,List<SquareWasDetailVO>> <�ݹ�,List<SquareWasDetailVO>>
   *         <������Ʒ,List<SquareWasDetailVO>>
   */
  public Map<SquareType, List<SquareWasDetailVO>> splitDetailVOBySquareType(
      SquareWasDetailVO[] sqdvos) {
    if (sqdvos == null) {
      return null;
    }
    Map<SquareType, List<SquareWasDetailVO>> map =
        new HashMap<SquareType, List<SquareWasDetailVO>>();
    SquareType key = null;
    List<SquareWasDetailVO> list = null;
    for (SquareWasDetailVO sdvo : sqdvos) {
      key = MDEnum.valueOf(SquareType.class, sdvo.getFsquaretype());
      list = map.get(key);
      if (list == null) {
        list = new ArrayList<SquareWasDetailVO>();
        map.put(key, list);
      }
      list.add(sdvo);
    }
    return map;
  }

  /**
   * �����������������ս������ͷ���SquareWasViewVO
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param sqdvos
   * @return <p>
   * @author zhangcheng
   * @time 2010-6-10 ����06:45:35
   */
  public Map<SquareType, List<SquareWasViewVO>> splitViewVOBySquareType(
      SquareWasViewVO[] sqdvos) {
    if (sqdvos == null) {
      return null;
    }
    Map<SquareType, List<SquareWasViewVO>> map =
        new HashMap<SquareType, List<SquareWasViewVO>>();
    SquareType iaKey = null;
    SquareType arKey = null;
    List<SquareWasViewVO> list = null;
    for (SquareWasViewVO sdvo : sqdvos) {
      iaKey = MDEnum.valueOf(SquareType.class, sdvo.getItem().getFpreiatype());
      arKey = MDEnum.valueOf(SquareType.class, sdvo.getItem().getFpreartype());
      if (iaKey != null) {
        list = map.get(iaKey);
        if (list == null) {
          list = new ArrayList<SquareWasViewVO>();
          map.put(iaKey, list);
        }
        list.add(sdvo);
      }
      if (arKey != null) {
        list = map.get(arKey);
        if (list == null) {
          list = new ArrayList<SquareWasViewVO>();
          map.put(arKey, list);
        }
        list.add(sdvo);
      }
    }
    return map;
  }

  public SquareWasViewVO[] sub(SquareWasViewVO[] vos1, SquareWasViewVO[] vos2) {
    SquareWasViewVO[] leftvos = new SquareWasViewVO[vos1.length - vos2.length];
    Set<String> bids = new HashSet<String>();
    for (SquareWasViewVO view : vos2) {
      bids.add(view.getItem().getPrimaryKey());
    }
    int i = 0;
    for (SquareWasViewVO view : vos1) {
      if (!bids.contains(view.getItem().getPrimaryKey())) {
        leftvos[i++] = view;
      }
    }
    return leftvos;
  }

  /**
   * �����㵥VOת��Ϊ������ϸVO
   * 
   * @param sqvos
   * @return
   */
  private SquareWasDetailVO[] changeSQVOtoSQDVO(SquareWasVO[] sqvos) {
    List<SquareWasDetailVO> list = new ArrayList<SquareWasDetailVO>();
    SquareWasDetailVO tempdvo = null;
    for (SquareWasVO svo : sqvos) {
      for (SquareWasBVO sbvo : svo.getChildrenVO()) {
        tempdvo = new SquareWasDetailVO();
        this.setBVOtoDetailVO(tempdvo, sbvo);
        list.add(tempdvo);
      }
    }
    return new ListToArrayTool<SquareWasDetailVO>().convertToArray(list);
  }

  private SquareWasDetailVO[] changeSQVOtoSQDVOByFlag(SquareWasVO[] sqvos,
      SquareType type) {
    SquareWasDetailVO[] sqdvos = this.changeSQVOtoSQDVO(sqvos);
    // ���ý�������
    for (SquareWasDetailVO sqdvo : sqdvos) {
      sqdvo.setFsquaretype((Integer) type.value());
    }
    return sqdvos;
  }

  /**
   * �ý��㵥��ͷ����VO������ϸ��VO
   * 
   * @param dvo
   * @param hvo
   * @param bvo
   */
  private void setBVOtoDetailVO(SquareWasDetailVO dvo, SquareWasBVO bvo) {

    dvo.setCsalesquareid(bvo.getCsalesquareid());
    dvo.setCsalesquarebid(bvo.getCsalesquarebid());
    dvo.setCsalesquaredid(bvo.getCsalesquaredid());
    dvo.setCsquarebillid(bvo.getCsquarebillid());
    dvo.setCsquarebillbid(bvo.getCsquarebillbid());
    dvo.setNsquarenum(bvo.getNthisnum());
    dvo.setNnum(bvo.getNthisnum());

    dvo.setCorigcurrencyid(bvo.getCorigcurrencyid());
    dvo.setCcurrencyid(bvo.getCcurrencyid());
    dvo.setCunitid(bvo.getCunitid());
    dvo.setCastunitid(bvo.getCastunitid());
    dvo.setVchangerate(bvo.getVchangerate());
    dvo.setNexchangerate(bvo.getNexchangerate());
    dvo.setNglobalexchgrate(bvo.getNglobalexchgrate());
    dvo.setNgroupexchgrate(bvo.getNgroupexchgrate());
    dvo.setNtaxrate(bvo.getNtaxrate());
    dvo.setNitemdiscountrate(bvo.getNitemdiscountrate());

    dvo.setNorigtaxnetprice(bvo.getNorigtaxnetprice());
    dvo.setNorignetprice(bvo.getNorignetprice());
    dvo.setNorigtaxprice(bvo.getNorigtaxprice());
    dvo.setNorigprice(bvo.getNorigprice());
    dvo.setNorigtaxmny(bvo.getNorigtaxmny());
    dvo.setNorigmny(bvo.getNorigmny());
    dvo.setProcesseid(bvo.getProcesseid());
    dvo.setPk_org(bvo.getPk_org());
    dvo.setPk_group(bvo.getPk_group());
    dvo.setDbilldate(bvo.getDbizdate());

    // V61�����ֶ�
    dvo.setCtaxcodeid(bvo.getCtaxcodeid());
    dvo.setFtaxtypeflag(bvo.getFtaxtypeflag());
    dvo.setNcaltaxmny(bvo.getNcaltaxmny());
  }

}
