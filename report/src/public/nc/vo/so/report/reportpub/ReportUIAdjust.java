package nc.vo.so.report.reportpub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.areaset.AreaContentSet;
import com.ufida.report.anareport.areaset.AreaContentSetUtil;
import com.ufida.report.anareport.areaset.AreaFieldSet;
import com.ufida.report.anareport.model.AbsAnaReportModel;
import com.ufida.report.anareport.model.AnaReportModel;

import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.report.ReportQueryConUtil;

import nc.itf.iufo.freereport.extend.IAreaCondition;
import nc.itf.iufo.freereport.extend.IReportAdjustor;

import nc.pub.smart.metadata.Field;
import nc.pub.smart.model.SmartModel;

/**
 * ���ɱ����ʽ����
 * 
 * @since 6.3
 * @version 2012-09-08 14:30:07
 * @author ������
 */
public class ReportUIAdjust implements IReportAdjustor, Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -49845570034919007L;

  private String[] allkeys;

  private MapList<String, String> specialrela = new MapList<String, String>();

  /**
   * ���췽��
   * 
   * @param allkeys
   */

  public ReportUIAdjust(String[] allkeys) {
    this.allkeys = allkeys;
  }

  @Override
  public void doAreaAdjust(IContext context, String areaPK,
      IAreaCondition areaCond, AbsAnaReportModel reportModel) {

    // 1.��������ֶ�
    String[] hidekeys = this.getHideKeys(context);
    if (hidekeys.length == 0) {
      return;
    }

    // 2.����Ҫ���ص���Ϣ���ݵ�����ƽ̨
    this.setDetail(areaPK, reportModel, hidekeys);

  }

  /**
   * ��һ���ֶ����ض���ֶι�ϵ����������Ҫ�������ϡ�����ͺš���λ
   * 
   * @param hidekey
   * @param relkey
   */
  public void addSpecialHideRela(String hidekey, String relkey) {
    this.specialrela.put(hidekey, relkey);
  }

  private void setDetail(String areaPK, AbsAnaReportModel reportModel,
      String[] hidekeys) {
    AreaFieldSet[] detailinfo = new AreaFieldSet[hidekeys.length];
    int i = 0;
    for (String key : hidekeys) {
      Field field = new Field();
      field.setFldname(key);
      detailinfo[i] = new AreaFieldSet(field);
      i++;
    }
    // ��̬�����ֶ�
    AreaContentSet contentSet = new AreaContentSet();
    contentSet.setAreaPk(areaPK);
    // �������õ�����ģ��
    SmartModel smart = reportModel.getAreaData(areaPK).getSmartModel();
    contentSet.setSmartModelDefID(smart.getId());
    contentSet.setDetailFldNames(detailinfo);

    AreaContentSetUtil.resetExCellByHideRelatedFields(contentSet, true,
        reportModel);
  }

  private String[] getHideKeys(IContext context) {
    ReportQueryConUtil qryconutil = new ReportQueryConUtil(context);
    ReportUserObject sumobj = (ReportUserObject) qryconutil.getUserObject();
    Set<String> summkeys = sumobj.getSummaryKeys();
    String[] hidekeys = null;
    if (null == summkeys || summkeys.size() == 0) {
      hidekeys = this.allkeys;
    }
    else {
      List<String> listhide = new ArrayList<String>();
      for (String evekey : this.allkeys) {
        if (summkeys.contains(evekey)) {
          continue;
        }
        listhide.add(evekey);
        if (null != this.specialrela && this.specialrela.containsKey(evekey)) {
          listhide.addAll(this.specialrela.get(evekey));
        }
      }
      hidekeys = new String[listhide.size()];
      listhide.toArray(hidekeys);
    }
    return hidekeys;
  }

  @Override
  public void doReportAdjust(IContext context, AnaReportModel reportModel) {
    // TODO
  }
}
