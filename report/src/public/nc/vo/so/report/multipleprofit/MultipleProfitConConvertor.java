package nc.vo.so.report.multipleprofit;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseSubscribeCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

import nc.vo.so.report.reportpub.ReportAggDes;
import nc.vo.so.report.reportpub.ReportUIAdjust;
import nc.vo.so.report.reportpub.ReportUserObject;

import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.iufo.freereport.extend.ISubscribeConditionConvertor;
import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;

import nc.pub.smart.model.descriptor.Descriptor;

import nc.bs.scmpub.report.ReportQueryCondition;
import nc.bs.scmpub.report.ReportScaleProcess;
import nc.bs.scmpub.report.ReportScaleProcess2;

import nc.ui.querytemplate.querytree.QueryScheme;

/**
 * �����Ĵ�����
 * 
 * @since 6.3
 * @version 2012-10-22 10:30:37
 * @author zhangkai4
 */
public class MultipleProfitConConvertor implements ISubscribeConditionConvertor {

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
    ReportUIAdjust adjust =
        new ReportUIAdjust(MultipleProfitViewMeta.GROUPKEYS);
    adjust.addSpecialHideRela(MultipleProfitViewVO.CMATERIALID,
        MultipleProfitViewVO.CUNITID);
    result.setRoportAdjustor(adjust);
    // 4.�û�ǰ̨������Ϣ
    ReportUserObject userobject = new ReportUserObject(scheme);
    result.setUserObject(userobject);
    // 5.�����ֶ�
//    String[] groupkeys = this.getGroupKeys(userobject);
//    ReportAggDes descriptor =
//        new ReportAggDes(groupkeys, MultipleProfitViewMeta.AGGKEYS);
//    result.setDescriptors(new Descriptor[] {
//      descriptor
//    });

    return result;
  }

  private ReportScaleProcess scaleProcess() {
    ReportScaleProcess2 process = new ReportScaleProcess2();

    // ����
    process.setMnyDigits(MultipleProfitViewVO.CORIGCURRENCYID,
        MultipleProfitViewMeta.MNYKEYS);
    // �ϼƴ���
    process.setTotalFields(MultipleProfitViewMeta.MNYKEYS);
    process.setNumTotalFields(MultipleProfitViewMeta.NUMKEY);
    // ��˰���۴���
    process.setPriceDigits(new String[] {
      MultipleProfitViewVO.NTOTALRECEIVEPRICE
    }, MultipleProfitViewVO.CORIGCURRENCYID);

    // �ɱ�����
    process.setCostPriceDigits(new String[] {
      MultipleProfitViewVO.NCOSTPRICE
    });
    return process;
  }

  /**
   * ��÷����ֶ�
   * 
   * @param userobject
   * @return
   */
  private String[] getGroupKeys(ReportUserObject userobject) {
    List<String> listgroup = new ArrayList<String>();
    Set<String> selgroups = userobject.getSummaryKeys();
    for (String key : selgroups) {
      listgroup.add(key);
      if (MultipleProfitViewVO.CMATERIALID.equals(key)) {
        listgroup.add(MultipleProfitViewVO.CUNITID);
      }
    }
    listgroup.add(MultipleProfitViewVO.CORIGCURRENCYID);
    String[] groupkeys = new String[listgroup.size()];
    listgroup.toArray(groupkeys);
    return groupkeys;
  }

}
