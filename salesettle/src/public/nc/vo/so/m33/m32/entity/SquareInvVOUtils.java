package nc.vo.so.m33.m32.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pubapp.bill.CombineBill;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.enumeration.SquareType;

import nc.md.model.impl.MDEnum;

public class SquareInvVOUtils {

  private static SquareInvVOUtils squtil = new SquareInvVOUtils();

  private SquareInvVOUtils() {
    super();
  }

  public static SquareInvVOUtils getInstance() {
    return SquareInvVOUtils.squtil;
  }

  /**
   * ����ȷ��Ӧ�ս��㵥VOת��Ϊ��ȷ��Ӧ�ս�����ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareInvDetailVO[] changeSQVOtoSQDVOForAR(SquareInvVO[] sqvos) {
    // ���ý�������:ȷ��Ӧ��
    return this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_AR);
  }

  /**
   * ���س���㵥VOת��Ϊ���س������ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareInvDetailVO[] changeSQVOtoSQDVOForARRUSH(SquareInvVO[] sqvos) {
    // ���ý�������:�ɱ�����
    return this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_ARRUSH);
  }

  /**
   * �����ݹ����㵥VOת��Ϊ���ݹ�Ӧ�ս�����ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareInvDetailVO[] changeSQVOtoSQDVOForET(SquareInvVO[] sqvos) {
    // ���ý�������:�ɱ�����
    return this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_ET);
  }

  /**
   * �����ɱ����㵥VOת��Ϊ���ɱ�Ӧ�ս�����ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareInvDetailVO[] changeSQVOtoSQDVOForIA(SquareInvVO[] sqvos) {
    // ���ý�������:�ɱ�����
    return this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_IA);
  }

  /**
   * ������Գ���㵥VOת��Ϊ������Գ������ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareInvDetailVO[] changeSQVOtoSQDVOForOUTRUSH(SquareInvVO[] sqvos) {
    // ���ý�������:�ɱ�����
    return this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_OUTRUSH);
  }

  /**
   * ����������Ʒ���㵥VOת��Ϊ��������Ʒ������ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareInvDetailVO[] changeSQVOtoSQDVOForREG(SquareInvVO[] sqvos) {
    // ���ý�������:�ɱ�����
    return this
        .changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_REG_CREDIT);
  }

  /**
   * ���ۺ�VO����ת��Ϊ��ͼVO����
   * 
   * @param sqvos
   * @return
   */
  public SquareInvViewVO[] changeToSaleSquareViewVO(SquareInvVO[] sqvos) {
    List<SquareInvViewVO> list = new ArrayList<SquareInvViewVO>();
    for (SquareInvVO svo : sqvos) {
      list.addAll(Arrays.asList(svo.changeToSquareInvViewVO()));
    }
    return new ListToArrayTool<SquareInvViewVO>().convertToArray(list);
  }

  public SquareInvVO[] combineBill(SquareInvViewVO[] vos) {
    int len = vos.length;
    SquareInvVO[] bills = new SquareInvVO[len];
    for (int i = 0; i < len; i++) {
      bills[i] = vos[i].changeToSquareInvVO();
    }
    CombineBill<SquareInvVO> cb = new CombineBill<SquareInvVO>();
    cb.appendKey(SquareInvHVO.CSALESQUAREID);
    return cb.combine(bills);
  }

  public SquareInvVO[] composite(SquareInvHVO[] hvos, SquareInvBVO[] bvos) {
    BillComposite<SquareInvVO> bc =
        new BillComposite<SquareInvVO>(SquareInvVO.class);
    SquareInvVO svo = new SquareInvVO();
    bc.append(svo.getMetaData().getParent(), hvos);
    bc.append(svo.getMetaData().getVOMeta(SquareInvBVO.class), bvos);
    return bc.composite();
  }

  /**
   * ����ϸID����SquareVO
   * 
   * @param sqvos
   * @return
   */
  public void fillDidToSquareVO(SquareInvVO[] sqvos, SquareInvDetailVO[] dvos) {
    Map<String, SquareInvDetailVO> map =
        new HashMap<String, SquareInvDetailVO>();
    for (SquareInvDetailVO dvo : dvos) {
      map.put(dvo.getCsalesquarebid(), dvo);
    }
    for (SquareInvVO svo : sqvos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {
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
  public SquareInvVO[] filterCostableVO(SquareInvVO[] vos) {
    SquareInvViewVO[] sviewvos = this.changeToSaleSquareViewVO(vos);
    List<SquareInvViewVO> list = new ArrayList<SquareInvViewVO>();
    for (SquareInvViewVO view : sviewvos) {
      if (view.getItem().getBcost() == null
          || view.getItem().getBcost().booleanValue()) {
        list.add(view);
      }
    }

    if (list.size() == 0) {
      return null;
    }

    SquareInvViewVO[] retviewVOs = list.toArray(new SquareInvViewVO[0]);
    SquareInvVO[] retvos = this.combineBill(retviewVOs);

    return retvos;
  }

  /**
   * ���˿��Դ�Ӧ�յ�vo
   * 
   * @param vos
   * @return
   */
  public SquareInvVO[] filterIncomeableVO(SquareInvVO[] vos) {
    SquareInvViewVO[] sviewvos = this.changeToSaleSquareViewVO(vos);
    List<SquareInvViewVO> list = new ArrayList<SquareInvViewVO>();
    for (SquareInvViewVO view : sviewvos) {
      if (view.getItem().getBincome() == null
          || view.getItem().getBincome().booleanValue()) {
        list.add(view);
      }
    }
    if (list.size() == 0) {
      return null;
    }
    SquareInvViewVO[] retviewVOs = list.toArray(new SquareInvViewVO[0]);
    SquareInvVO[] retvos = this.combineBill(retviewVOs);
    return retvos;
  }

  public SquareInvBVO[] getSquareInvBVO(SquareInvViewVO[] sqvos) {
    List<SquareInvBVO> blist = new ArrayList<SquareInvBVO>();
    for (SquareInvViewVO vo : sqvos) {
      blist.add(vo.getItem());
    }
    return blist.toArray(new SquareInvBVO[blist.size()]);
  }

  public SquareInvBVO[] getSquareInvBVO(SquareInvVO[] sqvos) {
    List<SquareInvBVO> blist = new ArrayList<SquareInvBVO>();
    for (SquareInvVO vo : sqvos) {
      for (SquareInvBVO bvo : vo.getChildrenVO()) {
        blist.add(bvo);
      }
    }
    return blist.toArray(new SquareInvBVO[0]);
  }

  public SquareInvHVO[] getSquareInvHVO(SquareInvViewVO[] sqvos) {
    Set<SquareInvHVO> hset = new HashSet<SquareInvHVO>();
    SquareInvHVO hvo = null;
    for (SquareInvViewVO vo : sqvos) {
      hvo = vo.getHead();
      if (!hset.contains(hvo)) {
        hset.add(vo.getHead());
      }
    }
    return hset.toArray(new SquareInvHVO[hset.size()]);
  }

  public SquareInvHVO[] getSquareInvHVO(SquareInvVO[] sqvos) {
    int len = sqvos.length;
    SquareInvHVO[] hvos = new SquareInvHVO[len];
    for (int i = 0; i < len; i++) {
      hvos[i] = (SquareInvHVO) sqvos[i].getParent();
    }
    return hvos;
  }

  public Map<String, List<SquareInvVO>> splitBillByTransType(SquareInvVO[] svos) {
    if (svos == null) {
      return null;
    }
    Map<String, List<SquareInvVO>> map =
        new HashMap<String, List<SquareInvVO>>();
    String key = null;
    List<SquareInvVO> list = null;
    for (SquareInvVO sdvo : svos) {
      key = sdvo.getParentVO().getVtrantypecode();
      list = map.get(key);
      if (list == null) {
        list = new ArrayList<SquareInvVO>();
        map.put(key, list);
      }
      list.add(sdvo);
    }
    return map;
  }

  /**
   * ���ս������ͷ���SquareInvDetailVO
   * 
   * @param sqdvos
   * @return <����,List<SquareInvDetailVO>> <�ɱ�,List<SquareInvDetailVO>>
   *         <�س�,List<SquareInvDetailVO>> <�ݹ�,List<SquareInvDetailVO>>
   *         <������Ʒ,List<SquareInvDetailVO>>
   */
  public Map<SquareType, List<SquareInvDetailVO>> splitDetailVOBySquareType(
      SquareInvDetailVO[] sqdvos) {
    if (sqdvos == null) {
      return null;
    }
    Map<SquareType, List<SquareInvDetailVO>> map =
        new HashMap<SquareType, List<SquareInvDetailVO>>();
    SquareType key = null;
    List<SquareInvDetailVO> list = null;
    for (SquareInvDetailVO sdvo : sqdvos) {
      key = MDEnum.valueOf(SquareType.class, sdvo.getFsquaretype());
      list = map.get(key);
      if (list == null) {
        list = new ArrayList<SquareInvDetailVO>();
        map.put(key, list);
      }
      list.add(sdvo);
    }
    return map;
  }

  /**
   * �����㵥VOת��Ϊ������ϸVO
   * 
   * @param sqvos
   * @return
   */
  private SquareInvDetailVO[] changeSQVOtoSQDVO(SquareInvVO[] sqvos) {
    List<SquareInvDetailVO> list = new ArrayList<SquareInvDetailVO>();
    SquareInvDetailVO tempdvo = null;
    for (SquareInvVO svo : sqvos) {
      for (SquareInvBVO sbvo : svo.getChildrenVO()) {
        tempdvo = new SquareInvDetailVO();
        this.setBVOtoDetailVO(tempdvo, sbvo);
        list.add(tempdvo);
      }
    }
    return new ListToArrayTool<SquareInvDetailVO>().convertToArray(list);
  }

  private SquareInvDetailVO[] changeSQVOtoSQDVOByFlag(SquareInvVO[] sqvos,
      SquareType type) {
    SquareInvDetailVO[] sqdvos = this.changeSQVOtoSQDVO(sqvos);
    // ���ý�������
    for (SquareInvDetailVO sqdvo : sqdvos) {
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
  private void setBVOtoDetailVO(SquareInvDetailVO dvo, SquareInvBVO bvo) {

    dvo.setCsalesquareid(bvo.getCsalesquareid());
    dvo.setCsalesquarebid(bvo.getCsalesquarebid());
    dvo.setCsalesquaredid(bvo.getCsalesquaredid());
    dvo.setCsquarebillid(bvo.getCsquarebillid());
    dvo.setCsquarebillbid(bvo.getCsquarebillbid());

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

    dvo.setNsquarenum(bvo.getNthisnum());
    // ��Ӧ��ʱNthisnum����գ����¶������㲻�رա�
    if (MathTool.isZero(bvo.getNthisnum())) {
      dvo.setNsquarenum(bvo.getDifftoarsquarenum());
    }
    dvo.setNnum(bvo.getNthisnum());
    dvo.setNorigtaxnetprice(bvo.getNorigtaxnetprice());
    dvo.setNorignetprice(bvo.getNorignetprice());
    dvo.setNorigtaxprice(bvo.getNorigtaxprice());
    dvo.setNorigprice(bvo.getNorigprice());
    dvo.setNorigtaxmny(bvo.getNorigtaxmny());
    dvo.setNorigmny(bvo.getNorigmny());
    dvo.setProcesseid(bvo.getProcesseid());
    dvo.setPk_org(bvo.getPk_org());
    dvo.setPk_group(bvo.getPk_group());
    dvo.setDbilldate(bvo.getDbilldate());
    // V61�����ֶ�
    dvo.setCtaxcodeid(bvo.getCtaxcodeid());
    dvo.setFtaxtypeflag(bvo.getFtaxtypeflag());
    dvo.setNcaltaxmny(bvo.getNcaltaxmny());
  }

}
