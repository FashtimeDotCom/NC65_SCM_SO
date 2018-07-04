package nc.vo.so.pub.util;

import nc.ui.format.FormatMetaAccessor;
import nc.ui.format.NCFormatterInstance;
import nc.vo.bd.format.FormatDocVO;
import nc.vo.pub.format.FormatResult;
import nc.vo.pubapp.pattern.log.Log;

/**
 * ���ݸ�ʽ������
 * 
 * @since 6.3
 * @version 2013-07-09 10:48:58
 * @author liujingn
 */
public class SOMilSymbolUtil {

  /**
   * ������ֵ�������ݸ�ʽ
   * 
   * @param str
   * @return ���������ݸ�ʽ
   */
  public static String setNumFormat(String str) {
    FormatDocVO docvo;
    try {
      docvo = FormatMetaAccessor.getFormat();
      if (docvo != null && docvo.getFm() != null
          && docvo.getFm().getDfm() != null) {
        FormatResult format = docvo.getFm().getNfm().format(str);
        return format.getValue();
      }
    }
    catch (Exception e) {
      Log.error(e);
      return str;
    }
    return str;
  }
  
  /**
   * ���������������ݸ�ʽ
   * 
   * @param str
   * @return ���������ݸ�ʽ
   */
  public static String setDateFormat(String str) {
    FormatDocVO docvo;
    try {
      docvo = FormatMetaAccessor.getFormat();
      if (docvo != null && docvo.getFm() != null
          && docvo.getFm().getDfm() != null) {
        FormatResult format = docvo.getFm().getDfm().format(str);
        return format.getValue();
      }
    }
    catch (Exception e) {
      Log.error(e);
      return str;
    }
    return str;
  }
  
  /**
   * ��������ʱ���������ݸ�ʽ
   * 
   * @param str
   * @return ���������ݸ�ʽ
   */
  public static String setDateTimeFormat(String str) {
    FormatDocVO docvo;
    try {
      docvo = FormatMetaAccessor.getFormat();
      
      
      if (docvo != null && docvo.getFm() != null
          && docvo.getFm().getDfm() != null) {
        FormatResult format = docvo.getFm().getTfm().format(str);
        String formatDate = SOMilSymbolUtil.setDateFormat(str);
        StringBuffer result = new StringBuffer().append(formatDate).append(" ").append(format.getValue());
        return result.toString();
      }
    }
    catch (Exception e) {
      Log.error(e);
      return str;
    }
    return str;
  }
  
  /**
   * ��������ʱ���������ݸ�ʽ ƽ̨�ṩ���NCFormater�ظ�SQL����
   * 
   * @param str
   * @param ncformat
   * @return
   */
  public static String setDateTimeFormat(String str, NCFormatterInstance ncformat){
	FormatResult format;
	try {
		format = ncformat.formatDateTime(str);
	} catch (Exception e) {
		Log.error(e);
	    return str;
	}
	return format.getValue();
  }
  
  /**
   * ���������������ݸ�ʽ ƽ̨�ṩ���NCFormater�ظ�SQL����
   * 
   * @param str
   * @param ncformat
   * @return
   */
  public static String setDateFormat(String str, NCFormatterInstance ncformat){
	 FormatResult format;
	 try {
		format = ncformat.formatDate(str);
	 } catch (Exception e) {
		Log.error(e);
		return str;
	 }
	 return format.getValue();
  }
}
