package nc.vo.so.pub.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.parameter.SCMParameterUtils;
import nc.vo.so.pub.res.ParameterList;

import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;

/**
 * ���۹����Ӳ����Ļ�ȡ
 * 
 * @since 6.0
 * @version 2010-12-2 ����09:14:03
 * @author ף����
 */
public class SOSysParaInitUtil {

  /**
   * ����������֯��ö�������
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static Integer getSO01(String pk_org) {
    return SysParaInitQuery.getParaInt(pk_org, ParameterList.SO01.getCode());
  }

  /**
   * ���������Ƿ�ͳһ����
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO02(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO02.getCode());
  }

  /**
   * ������ͷ��¼Ԥ�տ������Ԥ�տ�������ȹ���
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO03(String pk_org) {
    return SysParaInitQuery.getParaString(pk_org, ParameterList.SO03.getCode());
  }

  /**
   * ����������Ϊ������֯
   * �����۶��������ݲ���Ʒ�ʽ
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO04(String pk_org) {
    return SysParaInitQuery.getParaString(pk_org, ParameterList.SO04.getCode());
  }

  /**
   * ����������Ϊ������֯
   * �����������ݲ���Ʒ�ʽ
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO05(String pk_org) {
    return SysParaInitQuery.getParaString(pk_org, ParameterList.SO05.getCode());
  }

  /**
   * ����������Ϊ������֯
   * �������������ݲ���Ʒ�ʽ
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO06(String pk_org) {
    return SysParaInitQuery.getParaString(pk_org, ParameterList.SO06.getCode());
  }

  /**
   * ����������Ϊ������֯
   * ���۶�����Ʊ���Ʒ�ʽ
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO07(String pk_org) {
    return SysParaInitQuery.getParaString(pk_org, ParameterList.SO07.getCode());
  }

  /**
   * ����������Ϊ������֯
   * ���۳��ⵥ��Ʊ���Ʒ�ʽ
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO08(String pk_org) {
    return SysParaInitQuery.getParaString(pk_org, ParameterList.SO08.getCode());
  }

  /**
   * ����������Ϊ������֯
   * �����ر�ʱ�ö������������޶����ۺ�ִͬ������
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO10(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO10.getCode());
  }

  /**
   * �����տ��������
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String[] getSO11(String pk_org) {
    SysInitVO initvo =
        SysParaInitQuery.queryByParaCode(pk_org, ParameterList.SO11.getCode()
            + ParameterList.SUFFIX);
    if (null != initvo) {
      String value = initvo.getValue();
      if (null == value || "".equals(value)) {
        return null;
      }
      return value.split(ParameterList.SPLITKEY);
    }
    return null;
  }

  /**
   * ������֯
   * ���۳��ⵥӦ�ս��������޸Ŀͻ��͵���
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO12(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO12.getCode());
  }

  /**
   * �����֯
   * ����/ֱ�˰����ݲ����
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFDouble getSO13(String pk_org) {
    return SysParaInitQuery.getParaDbl(pk_org, ParameterList.SO13.getCode());
  }

  /**
   * ������֯
   * ��Ʊ���Ӧ�յ��Զ��ϲ���Ʊ
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO14(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO14.getCode());
  }

  /**
   * ������֯
   * ��Ӧ�յ��ϲ���Ʊʱ���Գ����Ʊ���ı���
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFDouble getSO15(String pk_org) {
    return SysParaInitQuery.getParaDbl(pk_org, ParameterList.SO15.getCode());
  }

  /**
   * ������֯
   * ����ǩ��������Ʊ��ҵ������
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static MapList<String, String> getSO16(String[] pk_orgs) {
    MapList<String, String> sysMap = new MapList<String, String>();
    for (String pk_org : pk_orgs) {
      SysInitVO initvo =
          SysParaInitQuery.queryByParaCode(pk_org, ParameterList.SO16.getCode()
              + ParameterList.SUFFIX);
      if (null != initvo) {
        String value = initvo.getValue();
        if (null == value || "".equals(value)) {
          continue;
        }
        String[] values = value.split(ParameterList.SPLITKEY);
        for (String busi : values) {
          sysMap.put(pk_org, busi);
        }
      }
    }
    return sysMap;
  }

  /**
   * ������֯
   * ��Ʊ����
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static Integer getSO17(String pk_org) {
    return SysParaInitQuery.getParaInt(pk_org, ParameterList.SO17.getCode());
  }

  /**
   * ������֯
   * ���۶����ֵ���ӡ����
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String[] getSO18(String pk_org) {
    SysInitVO initvo =
        SysParaInitQuery.queryByParaCode(pk_org, ParameterList.SO18.getCode()
            + ParameterList.SUFFIX);
    if (null != initvo) {
      String value = initvo.getValue();
      if (null == value || "".equals(value)) {
        return null;
      }
      return value.split(ParameterList.SPLITKEY);
    }
    return null;
  }

  /**
   * ������֯
   * �������ֵ���ӡ����
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String[] getSO19(String pk_org) {
    SysInitVO initvo =
        SysParaInitQuery.queryByParaCode(pk_org, ParameterList.SO19.getCode()
            + ParameterList.SUFFIX);
    if (null != initvo) {
      String value = initvo.getValue();
      if (null == value || "".equals(value)) {
        return null;
      }
      return value.split(ParameterList.SPLITKEY);
    }
    return null;
  }

  /**
   * ������֯
   * ��Ʒ�Ƿ�Ʊ
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO20(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO20.getCode());
  }

  /**
   * ������֯
   * ����ѯ�۴�������
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String[] getSO21(String pk_org) {
    SysInitVO initvo =
        SysParaInitQuery.queryByParaCode(pk_org, ParameterList.SO21.getCode()
            + ParameterList.SUFFIX);
    if (null != initvo) {
      String value = initvo.getValue();
      if (null == value || "".equals(value)) {
        return null;
      }
      return value.split(ParameterList.SPLITKEY);
    }
    return null;
  }

  /**
   * ������֯
   * �洢ѯ�۹�����ϸ
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO22(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO22.getCode());
  }

  /**
   * ����
   * ���ۺ�˰����˰����
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO23(String pk_group) {
    UFBoolean so23 = SCMParameterUtils.getSCM13(pk_group);
    return so23 == null ? UFBoolean.FALSE : so23;
  }

  /**
   * ������֯
   * ��Ԥ�������Ŷ������Ʒ�ʽ
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO26(String pk_org) {
    return SysParaInitQuery.getParaString(pk_org, ParameterList.SO26.getCode());
  }

  /**
   * ����
   * ��Ʊ����Ĭ����ʾ��ʽ
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO27(String pk_group) {
    return SysParaInitQuery.getParaString(pk_group,
        ParameterList.SO27.getCode());
  }

  /**
   * ������֯
   * ���۷�Ʊ���ܹ���
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String[] getSO28(String pk_org) {
    SysInitVO initvo =
        SysParaInitQuery.queryByParaCode(pk_org, ParameterList.SO28.getCode());
    if (null != initvo) {
      String value = initvo.getValue();
      if (null != value && value.length() > 0) {
        return value.split(ParameterList.SPLITKEY);
      }
    }
    return null;
  }

  /**
   * ����������֯��ñ��۵�����
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static Integer getSO29(String pk_org) {
    return SysParaInitQuery.getParaInt(pk_org, ParameterList.SO29.getCode());
  }

  /**
   * ������������ϸ��������
   * 
   * @param pk_org ������֯
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO30(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO30.getCode());
  }

  /**
   * ���۷�Ʊ�кϲ�˰�����
   * 
   * @param pk_org
   * @return so31
   */
  public static UFBoolean getSO31(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO31.getCode());
  }
  
  /**
   * ���۶����Ƿ�֧���������޸�
   * 
   * @param pk_org
   * @return so32
   */
  public static UFBoolean getSO32(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO32.getCode());
  }
  
  /**
   * ���۷�Ʊ�Ƿ�֧���������޸�
   * 
   * @param pk_org
   * @return so33
   */
  public static UFBoolean getSO33(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO33.getCode());
  }


  /**
   * ��ȡ������������ԭʼ�ַ���
   * 
   * @param pk_orgs
   * @param initCode
   * @return
   * @throws BusinessException
   */
  public static Map<String, String> queryBatchParaStringValues(
      String[] pk_orgs, String initCode) {
    if (pk_orgs.length == 0) {
      return null;
    }
    Map<String, String> paraMap = new HashMap<String, String>();
    for (String pk_org : pk_orgs) {
      String code = SysParaInitQuery.getParaString(pk_org, initCode);
      paraMap.put(pk_org, code);
    }
    return paraMap;
  }

  /**
   * �̶�������֯ ������ȡ����������ֵ�Ļ�ȡ
   * 
   * @param pk_org
   * @param initCodes
   * @return
   * @throws BusinessException
   */
  public static Map<String, List<String>> queryBatchParaValues(String pk_org,
      String[] initCodes) {
    if (initCodes.length == 0) {
      return null;
    }
    Map<String, List<String>> paraMap = new HashMap<String, List<String>>();
    Map<String, String> mapCode =
        SysParaInitQuery.queryBatchParaValues(pk_org, initCodes);
    for (Map.Entry<String, String> entry : mapCode.entrySet()) {
      List<String> paraList = new ArrayList<String>();
      String code = entry.getValue();
      if (null != code && !"".equals(code)) {
        String[] paras = code.split(ParameterList.SPLITKEY);
        for (String para : paras) {
          paraList.add(para);
        }
      }
      if (paraList.size() > 0) {
        paraMap.put(entry.getKey(), paraList);
      }
    }
    return paraMap;
  }

  /**
   * ������֯ ��ȡ��ͬ�����ڲ�ͬ��֯�µĲ���ֵ
   * 
   * @param pk_org
   * @param initCode
   * @return
   * @throws BusinessException
   */
  public static Map<String, List<String>> queryBatchParaValues(
      String[] pk_orgs, String initCode) {
    if (pk_orgs.length == 0) {
      return null;
    }
    Map<String, List<String>> paraMap = new HashMap<String, List<String>>();
    for (String pk_org : pk_orgs) {
      List<String> paraList = new ArrayList<String>();
      String code = SysParaInitQuery.getParaString(pk_org, initCode);
      if (null != code && !"".equals(code)) {
        String[] paras = code.split(ParameterList.SPLITKEY);
        for (String para : paras) {
          paraList.add(para);
        }
      }
      if (paraList.size() > 0) {
        paraMap.put(pk_org, paraList);
      }
    }
    return paraMap;
  }

  /**
   * ������֯ �������� ����һһ��Ӧ
   * 
   * @param pk_orgs
   * @param initCodes
   * @return
   * @throws BusinessException
   */
  public static Map<String, List<String>> queryBatchParaValues(
      String[] pk_orgs, String[] initCodes) {
    if (pk_orgs.length == 0 || initCodes.length == 0) {
      return null;
    }
    Map<String, List<String>> paraMap = new HashMap<String, List<String>>();
    for (int i = 0; i < pk_orgs.length; i++) {
      List<String> paraList = new ArrayList<String>();
      String code = SysParaInitQuery.getParaString(pk_orgs[i], initCodes[i]);
      if (null != code && !"".equals(code)) {
        String[] paras = code.split(ParameterList.SPLITKEY);
        for (String para : paras) {
          paraList.add(para);
        }
      }
      if (paraList.size() > 0) {
        paraMap.put(initCodes[i], paraList);
      }
    }
    return paraMap;
  }
}
