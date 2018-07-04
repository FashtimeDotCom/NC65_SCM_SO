package nc.vo.so.m33.m4c.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.bill.CombineBill;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.tool.performance.DeepCloneTool;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.trade.checkrule.VOChecker;

import nc.md.model.impl.MDEnum;

import nc.impl.pubapp.pattern.database.DBTool;

public class SquareOutVOUtils {

  private static SquareOutVOUtils squtil = new SquareOutVOUtils();

  private SquareOutVOUtils() {
    super();
  }

  public static SquareOutVOUtils getInstance() {
    return SquareOutVOUtils.squtil;
  }

  /**
   * ��������������������ͼvo�Ͷ�Ӧ��ϸvo�����ѽ�����ͼvo һ����ͼvo��Ӧ1�������ϸvo������
   * ����ͼvo���ݶ�Ӧ����ͼ�ĸ�����¡����������Ӧ���Ѿ���������ֵ
   * 
   * @param bvos
   * @param sdvos
   * @author zhangcheng
   */
  public SquareOutViewVO[] buildSquareOutdVO(SquareOutViewVO[] vos,
      SquareOutDetailVO[] sdvos) {
    // <bid,bid��Ӧ��SquareOutDetailVO>
    Map<String, List<SquareOutDetailVO>> mbid_dvos =
        new HashMap<String, List<SquareOutDetailVO>>();
    List<SquareOutDetailVO> listdvo = null;
    for (SquareOutDetailVO dvo : sdvos) {
      listdvo = mbid_dvos.get(dvo.getCsalesquarebid());
      if (listdvo == null) {
        listdvo = new ArrayList<SquareOutDetailVO>();
        mbid_dvos.put(dvo.getCsalesquarebid(), listdvo);
      }
      listdvo.add(dvo);
    }

    List<SquareOutViewVO> retList = new ArrayList<SquareOutViewVO>();
    String bid = null;
    int sdvoLen = 1;
    SquareOutViewVO tempvo = null;
    for (SquareOutViewVO svo : vos) {
      bid = svo.getItem().getPrimaryKey();
      listdvo = mbid_dvos.get(bid);
      if (VOChecker.isEmpty(listdvo)) {
        continue;
      }
      sdvoLen = listdvo.size();

      // ����ԭʼ����
      UFDouble num = svo.getItem().getNnum();
      // ���ν�������
      UFDouble nsquarenum = listdvo.get(0).getNsquarenum();
      // ��ν���(������ϸ��¼����1�����ҵ�ǰ��������<����ԭʼ����)
      if (sdvoLen > 1 && MathTool.absCompareTo(nsquarenum, num) < 0) {
        Set<String> sProcesseid = new HashSet<String>();
        for (int i = 0; i < sdvoLen; i++) {
          SquareOutDetailVO dvo = listdvo.get(i);
          if (i == 0) {
            tempvo = svo;
          }
          else if (sProcesseid.contains(dvo.getProcesseid())) {
            continue;
          }
          else {
            tempvo = (SquareOutViewVO) svo.clone();
          }
          sProcesseid.add(dvo.getProcesseid());
          this.setSquareDataByDetailVO(tempvo, dvo);
          retList.add(tempvo);
        }
      }
      // һ�ν���
      else {
        this.setSquareDataByDetailVO(svo, listdvo.get(0));
        retList.add(svo);
      }
    }

    SquareOutViewVO[] retVOs = retList.toArray(new SquareOutViewVO[0]);

    return retVOs;
  }

  /**
   * ����ȷ��Ӧ�ս��㵥VOת��Ϊ��ȷ��Ӧ�ս�����ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareOutDetailVO[] changeSQVOtoSQDVOForAR(SquareOutVO[] sqvos) {
    // ���ý�������:ȷ��Ӧ��
    SquareOutDetailVO[] dvos =
        this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_AR);
    // �����Ƿ��Զ�����
    this.setBautosquareflagByAR(sqvos, dvos);
    return dvos;
  }

  public SquareOutDetailVO[] changeSQVOtoSQDVOForARRUSH(
      SquareOutViewVO[] blueviews, SquareOutViewVO[] redviews) {
    return this.changeSQVOtoSQDVOForOutRushByFlag(blueviews, redviews,
        SquareType.SQUARETYPE_ARRUSH);
  }

  /**
   * ���س���㵥VOת��Ϊ���س������ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareOutDetailVO[] changeSQVOtoSQDVOForARRUSH(SquareOutVO[] sqvos) {
    // ���ý�������:�ɱ�����
    SquareOutDetailVO[] dvos =
        this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_ARRUSH);
    // �����Ƿ��Զ�����
    this.setBautosquareflagByAR(sqvos, dvos);
    return dvos;
  }

  /**
   * �����ݹ����㵥VOת��Ϊ���ݹ�Ӧ�ս�����ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareOutDetailVO[] changeSQVOtoSQDVOForET(SquareOutVO[] sqvos) {
    // ���ý�������:�ݹ�Ӧ��
    SquareOutDetailVO[] dvos =
        this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_ET);
    // �����Ƿ��Զ�����
    this.setBautosquareflagByAR(sqvos, dvos);
    return dvos;
  }

  /**
   * �����ɱ����㵥VOת��Ϊ���ɱ�Ӧ�ս�����ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareOutDetailVO[] changeSQVOtoSQDVOForIA(SquareOutVO[] sqvos) {
    // ���ý�������:�ɱ�����
    SquareOutDetailVO[] dvos =
        this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_IA);
    // �����Ƿ��Զ�����
    this.setBautosquareflagByIA(sqvos, dvos);
    return dvos;
  }

  /**
   * ������Գ���㵥VOת��Ϊ������Գ������ϸVO ��Ȼ����һ�����ִ��Գ���ⵥ���������ֽ�����ϸ�ͺ��ֽ�����ϸһһ��Ӧ
   * 
   * @param sqvos
   * @return
   */
  public SquareOutDetailVO[] changeSQVOtoSQDVOForOUTRUSH(
      SquareOutViewVO[] blueviews, SquareOutViewVO[] redviews) {
    return this.changeSQVOtoSQDVOForOutRushByFlag(blueviews, redviews,
        SquareType.SQUARETYPE_OUTRUSH);
  }

  /**
   * ����������Ʒ���㵥VOת��Ϊ��������Ʒ������ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareOutDetailVO[] changeSQVOtoSQDVOForREG(SquareOutVO[] sqvos) {
    // ���ý�������:������Ʒ
    SquareOutDetailVO[] dvos =
        this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_REG_DEBIT);
    // �����Ƿ��Զ�����
    this.setBautosquareflagByIA(sqvos, dvos);
    return dvos;
  }

  /**
   * ������Գ崫������Ʒ���㵥VOת��Ϊ��������Ʒ������ϸVO
   * 
   * @param sqvos
   * @return
   */
  public SquareOutDetailVO[] changeSQVOtoSQDVOForREGCredit(SquareOutVO[] sqvos) {
    // ���ý�������:������Ʒ
    SquareOutDetailVO[] dvos =
        this.changeSQVOtoSQDVOByFlag(sqvos, SquareType.SQUARETYPE_REG_CREDIT);
    // �����Ƿ��Զ�����
    this.setBautosquareflagByIA(sqvos, dvos);
    return dvos;
  }

  /**
   * ���ۺ�VO����ת��Ϊ��ͼVO����
   * 
   * @param sqvos
   * @return
   */
  public SquareOutViewVO[] changeToSaleSquareViewVO(SquareOutVO[] sqvos) {
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutVO svo : sqvos) {
      list.addAll(Arrays.asList(svo.changeToSquareOutViewVO()));
    }
    return new ListToArrayTool<SquareOutViewVO>().convertToArray(list);
  }

  public SquareOutVO[] combineBill(SquareOutViewVO[] view) {
    int len = view.length;
    SquareOutVO[] bills = new SquareOutVO[len];
    for (int i = 0; i < len; i++) {
      bills[i] = view[i].changeToSquareOutVO();
    }
    CombineBill<SquareOutVO> cb = new CombineBill<SquareOutVO>();
    cb.appendKey(SquareOutHVO.CSALESQUAREID);
    return cb.combine(bills);
  }

  /**
   * ���ظ������۳��ⵥ����vo�ϲ����ۼӱ��ν�����������˰�ϼ� ���������۳��ⵥ��д�ۼƽ�����Ϣ�ĳ�������Ϊ�ж�ν��㣬ȡ������
   * ��ʱ��ͬһ�����еĽ�����Ϣ����Ϊһ��
   * 
   * @param sqvos
   */
  public SquareOutVO[] combineBVO(SquareOutVO[] sqvos) {
    SquareOutViewVO[] views = this.changeToSaleSquareViewVO(sqvos);
    Map<String, SquareOutViewVO> mview = new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO view : views) {
      String bid = view.getItem().getCsalesquarebid();
      SquareOutViewVO smview = mview.get(bid);
      if (VOChecker.isEmpty(smview)) {
        SquareOutViewVO newview = new SquareOutViewVO();
        // ��¡��Ϊ�˲��ı䴫�����sqvos����Ϊ����sqvos��������
        newview.setHead((SquareOutHVO) view.getHead().clone());
        newview.setItem((SquareOutBVO) view.getItem().clone());
        mview.put(bid, newview);
      }
      else {
        // �����ۼƽ�������
        UFDouble nthisnum =
            MathTool.add(smview.getItem().getNthisnum(), view.getItem()
                .getNthisnum());
        smview.getItem().setNthisnum(nthisnum);

        // �����ۼƽ���ԭ�Ҽ�˰�ϼ�
        UFDouble norigtaxmny =
            MathTool.add(smview.getItem().getNorigtaxmny(), view.getItem()
                .getNorigtaxmny());
        smview.getItem().setNorigtaxmny(norigtaxmny);
      }
    }

    views = mview.values().toArray(new SquareOutViewVO[mview.size()]);
    return this.combineBill(views);

  }

  public SquareOutVO[] composite(SquareOutHVO[] hvos, SquareOutBVO[] bvos) {
    BillComposite<SquareOutVO> bc =
        new BillComposite<SquareOutVO>(SquareOutVO.class);
    SquareOutVO svo = new SquareOutVO();
    bc.append(svo.getMetaData().getParent(), hvos);
    bc.append(svo.getMetaData().getVOMeta(SquareOutBVO.class), bvos);
    return bc.composite();
  }

  public SquareOutViewVO[] deepClone(SquareOutViewVO[] views) {
    DeepCloneTool dct = new DeepCloneTool();
    SquareOutViewVO[] retview = (SquareOutViewVO[]) dct.deepClone(views);
    for (SquareOutViewVO view : retview) {
      view.getHead().setCsalesquareid(view.getItem().getCsalesquareid());
    }
    return retview;
  }

  /**
   * ������Ľ��㵥��id������֯��䵽��ͷ
   * 
   * @param vos
   */
  public void fill4CIDPkOrgToHead(SquareOutViewVO[] vos) {
    for (SquareOutViewVO svo : vos) {
      this.set4CIDPkOrgToHead(svo);
    }
  }

  /**
   * 1.������Ľ��㵥��id������֯��䵽��ͷ 2.���ô�����
   * 
   * @param vos
   */
  public void fillDataForManualSquare(SquareOutViewVO[] vos) {
    DBTool dao = new DBTool();
    String[] processeid = dao.getOIDs(1);
    for (SquareOutViewVO svo : vos) {
      // 1.������Ľ��㵥��id��䵽��ͷ
      this.set4CIDPkOrgToHead(svo);
      // 2.���ô�����
      svo.getItem().setProcesseid(processeid[0]);
    }
  }

  /**
   * ����ϸID����SquareVO
   * 
   * @param sqvos
   * @return
   */
  public void fillDidToSquareVO(SquareOutVO[] sqvos, SquareOutDetailVO[] dvos) {
    Map<String, SquareOutDetailVO> map =
        new HashMap<String, SquareOutDetailVO>();
    for (SquareOutDetailVO dvo : dvos) {
      map.put(dvo.getCsquareoutbvoid(), dvo);
    }
    for (SquareOutVO svo : sqvos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        bvo.setCsalesquaredid(map.get(String.valueOf(bvo.getCsquareoutbvoid()))
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
  public SquareOutVO[] filterCostableVO(SquareOutVO[] vos) {
    SquareOutViewVO[] sviewvos = this.changeToSaleSquareViewVO(vos);
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO view : sviewvos) {
      if (view.getItem().getBcost() == null
          || view.getItem().getBcost().booleanValue()) {
        list.add(view);
      }
    }
    if (list.size() == 0) {
      return null;
    }
    SquareOutViewVO[] retviewVOs = list.toArray(new SquareOutViewVO[0]);
    SquareOutVO[] retvos = this.combineBill(retviewVOs);
    return retvos;
  }

  /**
   * ���˿��Դ�Ӧ�յ�vo
   * 
   * @param vos
   * @return
   */
  public SquareOutVO[] filterIncomeableVO(SquareOutVO[] vos) {
    SquareOutViewVO[] sviewvos = this.changeToSaleSquareViewVO(vos);
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO view : sviewvos) {
      if (view.getItem().getBincome() == null
          || view.getItem().getBincome().booleanValue()) {
        list.add(view);
      }
    }
    if (list.size() == 0) {
      return null;
    }
    SquareOutViewVO[] retviewVOs = list.toArray(new SquareOutViewVO[0]);
    SquareOutVO[] retvos = this.combineBill(retviewVOs);
    return retvos;
  }

  public SquareOutViewVO[] filterSignReturnOut(SquareOutViewVO[] views) {
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    for (SquareOutViewVO view : views) {
      if (view.getHead().getBreturnoutstock().booleanValue()) {
        list.add(view);
      }
    }
    SquareOutViewVO[] retview = null;
    if (list.size() > 0) {
      retview = list.toArray(new SquareOutViewVO[list.size()]);
    }
    return retview;
  }

  public SquareOutBVO[] getSquareOutBVO(SquareOutViewVO[] sqvos) {
    List<SquareOutBVO> blist = new ArrayList<SquareOutBVO>();
    for (SquareOutViewVO vo : sqvos) {
      blist.add(vo.getItem());
    }
    return blist.toArray(new SquareOutBVO[blist.size()]);
  }

  public SquareOutBVO[] getSquareOutBVO(SquareOutVO[] sqvos) {
    List<SquareOutBVO> blist = new ArrayList<SquareOutBVO>();
    for (SquareOutVO vo : sqvos) {
      for (SquareOutBVO bvo : vo.getChildrenVO()) {
        blist.add(bvo);
      }
    }
    return blist.toArray(new SquareOutBVO[0]);
  }

  public SquareOutHVO[] getSquareOutHVO(SquareOutViewVO[] sqvos) {
    Set<SquareOutHVO> hset = new HashSet<SquareOutHVO>();
    SquareOutHVO hvo = null;
    for (SquareOutViewVO vo : sqvos) {
      hvo = vo.getHead();
      if (!hset.contains(hvo)) {
        hset.add(vo.getHead());
      }
    }
    return hset.toArray(new SquareOutHVO[hset.size()]);
  }

  public SquareOutHVO[] getSquareOutHVO(SquareOutVO[] sqvos) {
    SquareOutHVO[] hvos = new SquareOutHVO[sqvos.length];
    int len = sqvos.length;
    for (int i = 0; i < len; i++) {
      hvos[i] = (SquareOutHVO) sqvos[i].getParent();
    }
    return hvos;
  }

  /**
   * ��srcvos�е�TS��ֵ��targetvos
   * 
   * @param srcvos
   * @param targetvos
   */
  public void setNewTS(SquareOutVO[] srcvos, SquareOutVO[] targetvos) {
    SquareOutViewVO[] srcviews = this.changeToSaleSquareViewVO(srcvos);
    SquareOutViewVO[] targetviews = this.changeToSaleSquareViewVO(targetvos);
    this.setNewTS(srcviews, targetviews);
  }

  /**
   * ǩ���˻����۳��ⵥ�ݹ�Ӧ��������ǩ���˻����۳��ⵥ�����������������۳��ⵥ�ݹ�Ӧ����������Сֵ
   * 
   * @param view ���ݹ�Ӧ��ǩ���˻����۳�����㵥
   * @param outSquareNum �������۳��ⵥ�ݹ�Ӧ������
   */
  public void setNthisETRushNumUseMinNum(SquareOutViewVO view, UFDouble netnum) {
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
  public SquareOutVO[] setNthisnumForCancelSquare(SquareOutDetailVO[] sqdvos,
      SquareOutVO[] sqvos) {
    // <bid,SquareOutViewVO>,����ͬ�ı�����vo�ϲ�
    Map<String, SquareOutViewVO> mview = new HashMap<String, SquareOutViewVO>();
    SquareOutViewVO[] views = this.changeToSaleSquareViewVO(sqvos);
    for (SquareOutViewVO view : views) {
      mview.put(view.getItem().getCsalesquarebid(), view);
    }

    // <BID,Map<processid,SquareOutDetailVO>>
    Map<String, Map<String, SquareOutDetailVO>> mbidprovo =
        new HashMap<String, Map<String, SquareOutDetailVO>>();
    for (SquareOutDetailVO dvo : sqdvos) {
      String bid = dvo.getCsalesquarebid();
      String processid = dvo.getProcesseid();
      dvo.setNsquarenum(MathTool.oppose(dvo.getNsquarenum()));
      dvo.setNorigtaxmny(MathTool.oppose(dvo.getNorigtaxmny()));
      Map<String, SquareOutDetailVO> mpdvo = mbidprovo.get(bid);
      if (VOChecker.isEmpty(mpdvo)) {
        mpdvo = new HashMap<String, SquareOutDetailVO>();
      }
      mpdvo.put(processid, dvo);
      mbidprovo.put(bid, mpdvo);
    }

    for (Entry<String, Map<String, SquareOutDetailVO>> entry : mbidprovo
        .entrySet()) {
      String bid = entry.getKey();
      // ����������vo
      SquareOutViewVO view = mview.get(bid);
      view.getItem().setNthisnum(UFDouble.ZERO_DBL);
      view.getItem().setNorigtaxmny(UFDouble.ZERO_DBL);
      Map<String, SquareOutDetailVO> mdvo = entry.getValue();
      for (Entry<String, SquareOutDetailVO> edvo : mdvo.entrySet()) {
        SquareOutDetailVO dvo = edvo.getValue();
        UFDouble oldNthisnum = view.getItem().getNthisnum();
        UFDouble oldNorigtaxmny = view.getItem().getNorigtaxmny();
        UFDouble nthisnum = MathTool.add(oldNthisnum, dvo.getNsquarenum());
        UFDouble norigtaxmny =
            MathTool.add(oldNorigtaxmny, dvo.getNorigtaxmny());
        view.getItem().setNthisnum(nthisnum);
        view.getItem().setNorigtaxmny(norigtaxmny);
      }
    }

    SquareOutViewVO[] rets =
        mview.values().toArray(new SquareOutViewVO[mview.size()]);
    return this.combineBill(rets);

  }

  /**
   * ǩ���˻����۳��ⷢ����Ʒ������ǩ���˻����۳��ⵥ�����������������۳��ⵥ������Ʒ��������Сֵ
   * 
   * @param view ��������Ʒǩ���˻����۳�����㵥
   * @param outSquareNum �������۳��ⵥ������Ʒ����
   */
  public void setNthisREGNumUseMinNum(SquareOutViewVO view, UFDouble nregnum) {
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

  /**
   * ǰ̨�ֹ������ѯ����
   * �����ۼ�Ӧ�ա��ۼƳɱ��������������ۼƽ����������ֹ����������ʾ�ֶΣ�
   * 
   * @param vos
   */
  public void setNtotalsquarenum(SquareOutViewVO[] vos) {
    for (SquareOutViewVO view : vos) {
      UFDouble usquareianum = view.getItem().getNsquareianum();
      UFDouble usquarearnum = view.getItem().getNsquarearnum();
      boolean manualar = !view.getHead().getBautosquareincome().booleanValue();
      boolean manualia = !view.getHead().getBautosquarecost().booleanValue();
      Integer iaKey = view.getItem().getFpreiatype();
      Integer arKey = view.getItem().getFpreartype();

      // ���ۼƳɱ���Ӧ�����������ѽ�������
      manualar =
          manualar
              && SquareType.SQUARETYPE_AR.getIntValue() == arKey.intValue();
      manualia =
          manualia
              && SquareType.SQUARETYPE_IA.getIntValue() == iaKey.intValue();
      if (manualar && manualia) {
        UFDouble maxsquarenum = usquareianum;

        if (MathTool.absCompareTo(maxsquarenum, usquarearnum) < 0) {
          maxsquarenum = usquarearnum;
        }
        view.getItem().setNtotalsquarenum(maxsquarenum);
      }
      else if (manualia
          && SquareType.SQUARETYPE_IA.getIntValue() == iaKey.intValue()) {
        view.getItem().setNtotalsquarenum(usquareianum);
      }
      else if (manualar
          && SquareType.SQUARETYPE_AR.getIntValue() == arKey.intValue()) {
        view.getItem().setNtotalsquarenum(usquarearnum);
      }
    }
  }

  /**
   * ��vo�����ϳ���Գ��־
   * 
   * @param vos
   */
  public void setOutRushFlag(SquareOutViewVO[] vos) {
    DBTool dao = new DBTool();
    String[] processeid = dao.getOIDs(1);
    for (SquareOutViewVO vo : vos) {
      vo.getItem().setBoutrushflag(UFBoolean.TRUE);
      vo.getItem().setProcesseid(processeid[0]);
    }
  }

  public void setProcessid(SquareOutViewVO[] vos) {
    DBTool dao = new DBTool();
    String[] processeid = dao.getOIDs(1);
    for (SquareOutViewVO vo : vos) {
      vo.getItem().setProcesseid(processeid[0]);
    }
  }

  public Map<String, List<SquareOutVO>> splitBillByTransType(SquareOutVO[] svos) {
    if (svos == null) {
      return null;
    }
    Map<String, List<SquareOutVO>> map =
        new HashMap<String, List<SquareOutVO>>();
    String key = null;
    List<SquareOutVO> list = null;
    for (SquareOutVO sdvo : svos) {
      key = sdvo.getParentVO().getVtrantypecode();
      list = map.get(key);
      if (list == null) {
        list = new ArrayList<SquareOutVO>();
        map.put(key, list);
      }
      list.add(sdvo);
    }
    return map;
  }

  /**
   * ���ս������ͷ���SquareOutDetailVO
   * 
   * @param sqdvos
   * @return <����,List<SquareOutDetailVO>> <�ɱ�,List<SquareOutDetailVO>>
   *         <�س�,List<SquareOutDetailVO>> <�ݹ�,List<SquareOutDetailVO>>
   *         <������Ʒ,List<SquareOutDetailVO>>
   */
  public Map<SquareType, List<SquareOutDetailVO>> splitDetailVOBySquareType(
      SquareOutDetailVO[] sqdvos) {
    if (sqdvos == null) {
      return null;
    }
    Map<SquareType, List<SquareOutDetailVO>> map =
        new HashMap<SquareType, List<SquareOutDetailVO>>();
    SquareType key = null;
    List<SquareOutDetailVO> list = null;
    for (SquareOutDetailVO sdvo : sqdvos) {
      key = MDEnum.valueOf(SquareType.class, sdvo.getFsquaretype());
      list = map.get(key);
      if (list == null) {
        list = new ArrayList<SquareOutDetailVO>();
        map.put(key, list);
      }
      list.add(sdvo);
    }
    return map;
  }

  /**
   * �����������������ս������ͷ���SquareOutViewVO
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
  public Map<SquareType, List<SquareOutViewVO>> splitViewVOBySquareTypeForManual(
      SquareOutViewVO[] sqdvos) {
    if (sqdvos == null) {
      return null;
    }
    Map<SquareType, List<SquareOutViewVO>> map =
        new HashMap<SquareType, List<SquareOutViewVO>>();
    List<SquareOutViewVO> list = null;
    for (SquareOutViewVO sdvo : sqdvos) {
      boolean manualar = !sdvo.getHead().getBautosquareincome().booleanValue();
      boolean manualia = !sdvo.getHead().getBautosquarecost().booleanValue();
      boolean btoar = sdvo.getItem().getBincome().booleanValue();
      boolean btoia = sdvo.getItem().getBcost().booleanValue();
      SquareType iaKey =
          MDEnum.valueOf(SquareType.class, sdvo.getItem().getFpreiatype());
      SquareType arKey =
          MDEnum.valueOf(SquareType.class, sdvo.getItem().getFpreartype());
      if (manualia && iaKey != null && btoia) {
        list = map.get(iaKey);
        if (list == null) {
          list = new ArrayList<SquareOutViewVO>();
          map.put(iaKey, list);
        }
        list.add(sdvo);
      }
      if (manualar && arKey != null && btoar) {
        list = map.get(arKey);
        if (list == null) {
          list = new ArrayList<SquareOutViewVO>();
          map.put(arKey, list);
        }
        list.add(sdvo);
      }
    }
    return map;
  }

  public SquareOutViewVO[] subByBID(SquareOutViewVO[] vos1,
      SquareOutViewVO[] vos2) {
    SquareOutViewVO[] leftvos = new SquareOutViewVO[vos1.length - vos2.length];
    Set<String> bids = new HashSet<String>();
    for (SquareOutViewVO view : vos2) {
      bids.add(view.getItem().getCsalesquarebid());
    }
    int i = 0;
    for (SquareOutViewVO view : vos1) {
      if (!bids.contains(view.getItem().getCsalesquarebid())) {
        leftvos[i++] = view;
      }
    }
    return leftvos;
  }

  public SquareOutViewVO[] subByDID(SquareOutViewVO[] vos1,
      SquareOutViewVO[] vos2) {
    SquareOutViewVO[] leftvos = new SquareOutViewVO[vos1.length - vos2.length];
    Set<String> dids = new HashSet<String>();
    for (SquareOutViewVO view : vos2) {
      dids.add(view.getItem().getCsalesquaredid());
    }
    int i = 0;
    for (SquareOutViewVO view : vos1) {
      if (!dids.contains(view.getItem().getCsalesquaredid())) {
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
  private SquareOutDetailVO[] changeSQVOtoSQDVO(SquareOutVO[] sqvos) {
    List<SquareOutDetailVO> list = new ArrayList<SquareOutDetailVO>();
    SquareOutDetailVO tempdvo = null;
    int i = 1;
    for (SquareOutVO svo : sqvos) {
      for (SquareOutBVO sbvo : svo.getChildrenVO()) {
        tempdvo = new SquareOutDetailVO();

        /**
         * ���۳��ⵥ�����㵥voID(��������ʱ�ã�Ԫ������û��)
         * ������vo����ϸvo�Ķ�Ӧ��ϵ����Ϊ���ܴ�����vo���1�в�ɶ���SquareOutDetailVO
         * �����޷�����id��Ӧ
         */
        String id = String.valueOf(i);
        sbvo.setCsquareoutbvoid(id);

        this.setBVOtoDetailVO(tempdvo, sbvo);

        list.add(tempdvo);
        i++;
      }
    }
    return new ListToArrayTool<SquareOutDetailVO>().convertToArray(list);
  }

  private SquareOutDetailVO[] changeSQVOtoSQDVOByFlag(SquareOutVO[] sqvos,
      SquareType type) {
    SquareOutDetailVO[] sqdvos = this.changeSQVOtoSQDVO(sqvos);
    // ���ý�������
    for (SquareOutDetailVO sqdvo : sqdvos) {
      sqdvo.setFsquaretype((Integer) type.value());
    }
    return sqdvos;
  }

  private SquareOutDetailVO[] changeSQVOtoSQDVOForOutRushByFlag(
      SquareOutViewVO[] blueviews, SquareOutViewVO[] redviews, SquareType type) {

    List<SquareOutDetailVO> list = new ArrayList<SquareOutDetailVO>();
    SquareOutDetailVO tempdvo = null;
    /**
     * ���۳��ⵥ�����㵥voID(��������ʱ�ã�Ԫ������û��)
     * ������vo����ϸvo�Ķ�Ӧ��ϵ����Ϊ���ܴ�����vo���1�в�ɶ���SquareOutDetailVO
     * �����޷�����id��Ӧ
     */
    int i = 1;
    String red = "red";
    String blue = "blue";
    // ���ڶ�����ֶԶ�����ֵ�����������Ҫ�ж��Ƿ�������
    int index = 0;
    for (SquareOutViewVO redview : redviews) {
      SquareOutBVO bluebvo = null;
      if (blueviews.length > index) {
        bluebvo = blueviews[index].getItem();
      }
      else {
        bluebvo = blueviews[0].getItem();
      }
      // ������ϸ
      String redid = red + String.valueOf(i);
      redview.getItem().setCsquareoutbvoid(redid);
      tempdvo = new SquareOutDetailVO();
      this.setBVOtoDetailVO(tempdvo, redview.getItem());
      tempdvo.setFsquaretype(type.getIntegerValue());
      tempdvo.setCrushgeneralbid(bluebvo.getCsquarebillbid());
      tempdvo.setVrushbillcode(blueviews[0].getHead().getVbillcode());
      list.add(tempdvo);
      // ������ϸ
      String blueid = blue + String.valueOf(i);
      bluebvo.setCsquareoutbvoid(blueid);
      tempdvo = new SquareOutDetailVO();
      this.setBVOtoDetailVO(tempdvo, bluebvo);
      UFDouble nrednum = UFDouble.ZERO_DBL.sub(redview.getItem().getNthisnum());
      tempdvo.setNsquarenum(nrednum);
      tempdvo.setFsquaretype(type.getIntegerValue());
      tempdvo.setCrushgeneralbid(redview.getItem().getCsquarebillbid());
      tempdvo.setVrushbillcode(redview.getHead().getVbillcode());
      list.add(tempdvo);
      i++;
      index++;
    }
    return list.toArray(new SquareOutDetailVO[0]);
  }

  private void set4CIDPkOrgToHead(SquareOutViewVO svo) {
    svo.getHead().setCsquarebillid(svo.getItem().getCsquarebillid());
    svo.getHead().setPk_org(svo.getItem().getPk_org());
  }

  private void setBautosquareflagByAR(SquareOutVO[] sqvos,
      SquareOutDetailVO[] dvos) {
    Map<String, SquareOutVO> map = new HashMap<String, SquareOutVO>();
    for (SquareOutVO vo : sqvos) {
      map.put(vo.getParentVO().getCsalesquareid(), vo);
    }
    SquareOutHVO hvo = null;
    for (SquareOutDetailVO dvo : dvos) {
      hvo = map.get(dvo.getCsalesquareid()).getParentVO();
      dvo.setBautosquareflag(hvo.getBautosquareincome());
    }
  }

  private void setBautosquareflagByIA(SquareOutVO[] sqvos,
      SquareOutDetailVO[] dvos) {
    Map<String, SquareOutVO> map = new HashMap<String, SquareOutVO>();
    for (SquareOutVO vo : sqvos) {
      map.put(vo.getParentVO().getCsalesquareid(), vo);
    }
    SquareOutHVO hvo = null;
    for (SquareOutDetailVO dvo : dvos) {
      hvo = map.get(dvo.getCsalesquareid()).getParentVO();
      dvo.setBautosquareflag(hvo.getBautosquarecost());
    }
  }

  /**
   * �ý��㵥��ͷ����VO������ϸ��VO
   * 
   * @param dvo
   * @param hvo
   * @param bvo
   */
  private void setBVOtoDetailVO(SquareOutDetailVO dvo, SquareOutBVO bvo) {

    /**
     * ���۳��ⵥ�����㵥voID(��������ʱ�ã�Ԫ������û��)
     * ������vo����ϸvo�Ķ�Ӧ��ϵ����Ϊ���ܴ�����vo���1�в�ɶ���SquareOutDetailVO
     * �����޷�����id��Ӧ
     */
    dvo.setCsquareoutbvoid(bvo.getCsquareoutbvoid());

    dvo.setCsalesquareid(bvo.getCsalesquareid());
    dvo.setCsalesquarebid(bvo.getCsalesquarebid());
    dvo.setCsalesquaredid(bvo.getCsalesquaredid());
    dvo.setCsquarebillid(bvo.getCsquarebillid());
    dvo.setCsquarebillbid(bvo.getCsquarebillbid());
    dvo.setNsquarenum(bvo.getNthisnum());
    dvo.setNnum(bvo.getNnum());

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

    dvo.setNtaxmny(bvo.getNtaxmny());
    // modify by zhangby5 ���ӱ��������Ϣ
    dvo.setNtax(bvo.getNtax());
    dvo.setNtaxnetprice(bvo.getNtaxnetprice());
    dvo.setNtaxprice(bvo.getNtaxprice());

    dvo.setProcesseid(bvo.getProcesseid());
    dvo.setPk_org(bvo.getPk_org());
    dvo.setPk_group(bvo.getPk_group());
    dvo.setDbilldate(bvo.getDbizdate());
    dvo.setBoutrushflag(bvo.getBoutrushflag());
    dvo.setCrushoutbatchid(bvo.getProcesseid());
    // V61�����ֶ�
    dvo.setCtaxcodeid(bvo.getCtaxcodeid());
    dvo.setFtaxtypeflag(bvo.getFtaxtypeflag());
    dvo.setNcaltaxmny(bvo.getNcaltaxmny());
  }

  private void setNewTS(SquareOutViewVO[] srcviews,
      SquareOutViewVO[] targetviews) {
    // <bid,SquareOutViewVO>
    Map<String, SquareOutViewVO> map = new HashMap<String, SquareOutViewVO>();
    for (SquareOutViewVO view : srcviews) {
      map.put(view.getItem().getCsalesquarebid(), view);
    }
    for (SquareOutViewVO view : targetviews) {
      String bid = view.getItem().getCsalesquarebid();
      SquareOutViewVO srcview = map.get(bid);
      view.getHead().setTs(srcview.getHead().getTs());
      view.getItem().setTs(srcview.getItem().getTs());
    }
  }

  /**
   * ���������������������������������κš��Գ����κš�������ϸ��id���Լ����ֽ��
   */
  private void setSquareDataByDetailVO(SquareOutViewVO vo, SquareOutDetailVO dvo) {

    vo.getItem().setCsalesquaredid(dvo.getCsalesquaredid());
    vo.getItem().setProcesseid(dvo.getProcesseid());
    vo.getItem().setCrushoutbatchid(dvo.getCrushoutbatchid());
    // �����������
    vo.getItem().setNthisnum(dvo.getNsquarenum());

    vo.getItem().setNorignetprice(dvo.getNorignetprice());
    vo.getItem().setNorigprice(dvo.getNorigprice());
    vo.getItem().setNorigtaxnetprice(dvo.getNorigtaxnetprice());
    vo.getItem().setNorigtaxprice(dvo.getNorigtaxprice());
    vo.getItem().setNorigdiscount(dvo.getNorigdiscount());
    vo.getItem().setNorigmny(dvo.getNorigmny());
    vo.getItem().setNorigtaxmny(dvo.getNorigtaxmny());

    vo.getItem().setNitemdiscountrate(dvo.getNitemdiscountrate());

    vo.getItem().setNnetprice(dvo.getNnetprice());
    vo.getItem().setNprice(dvo.getNprice());
    vo.getItem().setNtaxprice(dvo.getNtaxprice());
    vo.getItem().setNtaxnetprice(dvo.getNtaxnetprice());
    vo.getItem().setNtaxmny(dvo.getNtaxmny());
    vo.getItem().setNmny(dvo.getNmny());
    vo.getItem().setNtax(dvo.getNtax());
    vo.getItem().setNdiscount(dvo.getNdiscount());

    vo.getItem().setNglobalmny(dvo.getNglobalmny());
    vo.getItem().setNglobaltaxmny(dvo.getNglobaltaxmny());
    vo.getItem().setNgroupmny(dvo.getNgroupmny());
    vo.getItem().setNgrouptaxmny(dvo.getNgrouptaxmny());

    // V61�����ֶ�
    vo.getItem().setCtaxcodeid(dvo.getCtaxcodeid());
    vo.getItem().setFtaxtypeflag(dvo.getFtaxtypeflag());
    vo.getItem().setNcaltaxmny(dvo.getNcaltaxmny());
  }

}
