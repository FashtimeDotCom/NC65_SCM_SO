package nc.vo.so.report.reportpub;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;

/**
 * ���С����ϡ��͡�������֯���ļ��δ���
 * 
 * @since 6.3
 * @version 2012-10-30 13:37:34
 * @author zhangkai4
 */
public class ReportLevelProcesser {

  private static ReportLevelProcesser instance = new ReportLevelProcesser();

  private ReportLevelProcesser() {
    //
  }

  /**
   * ���Ψһʵ��
   * 
   * @return instance
   */
  public static ReportLevelProcesser getInstance() {
    return ReportLevelProcesser.instance;
  }

  /**
   * ���ϼ��δ���
   * 
   * @param views ��Ҫ���������
   * @param maridField ����ID�ֶ���
   * @param marClassField ���ϼ����ֶ���
   * @param level ����
   */
  public void processMarLevel(AbstractDataView[] views, String maridField,
      String marClassField, int level) {
    if (ReportLevelValue.END.equalsValue(Integer.valueOf(level))) {
      return;
    }
    // ��ȡ����ID���������ظ�����
    Set<String> marterids = new HashSet<String>();
    for (AbstractDataView view : views) {
      String marid = (String) view.getAttributeValue(maridField);
      marterids.add(marid);
    }

    String[] cmaterialids = new String[marterids.size()];
    marterids.toArray(cmaterialids);
    // ���δ���
    ReportLevelUtil levelutil = new ReportLevelUtil();
    Map<String, String> materialMap =
        levelutil.queryMarBasClassIDByLevel(cmaterialids, level);
    // ����������VO
    for (AbstractDataView view : views) {
      String materid = (String) view.getAttributeValue(maridField);
      view.setAttributeValue(marClassField, materialMap.get(materid));
    }
  }

  /**
   * ������֯���δ���
   * 
   * @param views
   * @param saleorgClassField ������֯�ֶ���
   * @param level ����
   */
  public void processSaleorgLevel(AbstractDataView[] views,
      String saleorgClassField, int level) {
    if (ReportLevelValue.END.equalsValue(Integer.valueOf(level))) {
      return;
    }

    // ���������֯ID
    Set<String> saleorgids = new HashSet<String>();
    for (AbstractDataView view : views) {
      String sorgid = (String) view.getAttributeValue(saleorgClassField);
      saleorgids.add(sorgid);
    }

    String[] salorgids = new String[saleorgids.size()];
    saleorgids.toArray(salorgids);

    // ���εĴ���
    ReportLevelUtil levelUtil = new ReportLevelUtil();
    Map<String, String> saleorgMap =
        levelUtil.querySaleorgIDByLevel(salorgids, level);
    // ����������VO
    for (AbstractDataView view : views) {
      String saleid = (String) view.getAttributeValue(saleorgClassField);
      view.setAttributeValue(saleorgClassField, saleorgMap.get(saleid));
    }
  }
}
