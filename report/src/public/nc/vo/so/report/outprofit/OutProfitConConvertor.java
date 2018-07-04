package nc.vo.so.report.outprofit;


import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseSubscribeCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

import nc.vo.so.report.reportpub.ReportUIAdjust;
import nc.vo.so.report.reportpub.ReportUserObject;

import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.iufo.freereport.extend.ISubscribeConditionConvertor;
import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;



import nc.bs.scmpub.report.ReportQueryCondition;
import nc.bs.scmpub.report.ReportScaleProcess;
import nc.bs.scmpub.report.ReportScaleProcess2;

import nc.ui.querytemplate.querytree.QueryScheme;

/**
 * ���۳���ë�����������Ĵ�����
 * 
 * @since 6.3
 * @version 2012-08-28 10:01:17
 * @author ������
 */
public class OutProfitConConvertor implements ISubscribeConditionConvertor {

  @Override
  public IQueryCondition getQueryCondition(
      ISubscribeQueryCondition subscribCondition, IContext context,
      AbsAnaReportModel reportModel) {
    BaseSubscribeCondition base = (BaseSubscribeCondition) subscribCondition;
    QueryScheme scheme = (QueryScheme) base.getScheme();

    // 1. ���ñ���ɸѡ������Ϊ��
    ReportQueryCondition result = new ReportQueryCondition(true);

    // 2.���ȴ���
    ReportScaleProcess process = this.scaleProcess();
    result.setBusiFormat(process);

    // 3.���ɱ����ʽ����
    ReportUIAdjust adjust = new ReportUIAdjust(OutProfitConConvertor.ALLKEYS);
    adjust.addSpecialHideRela(OutProfitViewVO.CMATERIALVID,
        OutProfitViewVO.CUNITID);
    result.setRoportAdjustor(adjust);

    // 4.�û�ǰ̨������Ϣ
    ReportUserObject userobject = new ReportUserObject(scheme);
    result.setUserObject(userobject);

    // 5.�����ֶ�
//    String[] groupkeys = this.getGroupKeys(userobject);
//    ReportAggDes descriptor =
//        new ReportAggDes(groupkeys, OutProfitConConvertor.AGGKEYS);
//    result.setDescriptors(new Descriptor[] {
//      descriptor
//    });

    return result;
  }

  /**
   * ���л����ֶ�
   */
  public static final String[] AGGKEYS = new String[] {
    OutProfitViewVO.NNUM, OutProfitViewVO.NCOSTNUM, OutProfitViewVO.NMAINNUM,
    OutProfitViewVO.NSHOULDRECEIVNUM, OutProfitViewVO.NTOTALRECEIVMNY,
    OutProfitViewVO.NNOTAXMNY, OutProfitViewVO.NCOSTMNY,
    OutProfitViewVO.NTOTALCOSTMNY, OutProfitViewVO.NPROFITMNY
  };

  /**
   * ���з����ֶ�
   */
  private static final String[] ALLKEYS = new String[] {
    OutProfitViewVO.CSALEORGOID, OutProfitViewVO.CBIZID,
    OutProfitViewVO.CDPTID, OutProfitViewVO.PK_CUSTCLASS,
    OutProfitViewVO.PK_CUSTSALECLASS, OutProfitViewVO.PK_AREACL,
    OutProfitViewVO.CCUSTOMERID, OutProfitViewVO.PK_MARBASCLASS,
    OutProfitViewVO.PK_MARSALECLASS, OutProfitViewVO.CMATERIALOID,
    OutProfitViewVO.VBATCHCODE, OutProfitViewVO.CTRANTYPEID,
    OutProfitViewVO.VBILLCODE, OutProfitViewVO.CCHANNELTYPEID,
    OutProfitViewVO.VDEF1, OutProfitViewVO.VDEF2, OutProfitViewVO.VDEF3,
    OutProfitViewVO.VDEF4, OutProfitViewVO.VDEF5, OutProfitViewVO.VDEF6,
    OutProfitViewVO.VDEF7, OutProfitViewVO.VDEF8, OutProfitViewVO.VDEF9,
    OutProfitViewVO.VDEF10, OutProfitViewVO.VDEF11, OutProfitViewVO.VDEF12,
    OutProfitViewVO.VDEF13, OutProfitViewVO.VDEF14, OutProfitViewVO.VDEF15,
    OutProfitViewVO.VDEF16, OutProfitViewVO.VDEF17, OutProfitViewVO.VDEF18,
    OutProfitViewVO.VDEF19, OutProfitViewVO.VDEF20, OutProfitViewVO.VBDEF1,
    OutProfitViewVO.VBDEF2, OutProfitViewVO.VBDEF3, OutProfitViewVO.VBDEF4,
    OutProfitViewVO.VBDEF5, OutProfitViewVO.VBDEF6, OutProfitViewVO.VBDEF7,
    OutProfitViewVO.VBDEF8, OutProfitViewVO.VBDEF9, OutProfitViewVO.VBDEF10,
    OutProfitViewVO.VBDEF11, OutProfitViewVO.VBDEF12, OutProfitViewVO.VBDEF13,
    OutProfitViewVO.VBDEF14, OutProfitViewVO.VBDEF15, OutProfitViewVO.VBDEF16,
    OutProfitViewVO.VBDEF17, OutProfitViewVO.VBDEF18, OutProfitViewVO.VBDEF19,
    OutProfitViewVO.VBDEF20
  };


  /**
   * ����ֶ�
   */
  private static final String[] MNYKEYS = new String[] {
    OutProfitViewVO.NTOTALRECEIVMNY, OutProfitViewVO.NNOTAXMNY,
    OutProfitViewVO.NCOSTMNY, OutProfitViewVO.NTOTALCOSTMNY,
    OutProfitViewVO.NPROFITMNY,OutProfitViewVO.NPROFITRATE
  };

  /**
   * �����ֶ�
   */
  private static final String[] NUMKEY = new String[] {
    OutProfitViewVO.NNUM, OutProfitViewVO.NCOSTNUM, OutProfitViewVO.NMAINNUM,
    OutProfitViewVO.NSHOULDRECEIVNUM,
  };


  private ReportScaleProcess scaleProcess() {
    ReportScaleProcess2 process = new ReportScaleProcess2();
    // �ɱ����۴���
    process.setCostPriceDigits(new String[] {
      OutProfitViewVO.NCOSTPRICE
    });

    // ��˰����
    process.setPriceDigits(new String[] {
      OutProfitViewVO.NNOTAXPRICE
    }, OutProfitViewVO.CORIGCURRENCYID);

    // ����
    process.setMnyDigits(OutProfitViewVO.CORIGCURRENCYID,
        OutProfitConConvertor.MNYKEYS);
    // �ϼƴ���
    process.setTotalFields(OutProfitConConvertor.MNYKEYS);
    process.setNumTotalFields(OutProfitConConvertor.NUMKEY);

    return process;
  }
}
