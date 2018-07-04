package nc.pubitf.so.tbb.detail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.fetchfunc.IFuncParaValue;
import nc.itf.scmpub.tbb.ITbbDateParaMetaPath;
import nc.vo.pub.ISuperVO;
import nc.vo.scmpub.fetchfunc.FuncParaDescribe;
import nc.vo.scmpub.fetchfunc.split.ParaSplitKey;
import nc.vo.scmpub.tbb.TbbParaValue;
import nc.vo.scmpub.tbb.TbbRegister;
import nc.vo.tb.obj.NtbParamVO;

public class SODetailDataProvider {
  // TODO ��ӱ� 2012.03.14
  /**
   * Ԥ��ȡ������ȡ��ֵ��ȡ
   * 
   * @param tbbregister
   * @param ntbparamvos
   */
  public String getExecDatas(TbbRegister tbbregister, NtbParamVO ntbparamvos) {

    // 3.ת������
    IFuncParaValue para = new TbbParaValue(ntbparamvos);

    // 5.��ÿ�ֵ���������֯����������Ϣ ȡ��ʵ���ࡢȡ������
    return this.getBillTbbExecData(tbbregister, para);
  }

  private String getBillTbbExecData(TbbRegister register, IFuncParaValue para) {
    // ��ѯʵ����
    Class<? extends ISuperVO> entityclass = register.getEntityClass();
    // 1.ʹ�ò���������Ϣ�Դ����������

    FuncParaDescribe desb = this.getParaDescribe(register);
    // 2.��ÿ������ƴ��SQL

    ParaSplitKey splitkey = new ParaSplitKey(desb, para);

    // ����������Ϣ
    this.setParaMetaPath(splitkey, desb, register);
    List<IFuncParaValue> listpara = new ArrayList<IFuncParaValue>();
    listpara.add(para);
    // ���ȡ������SQL������
    TbbSqlBuilder sqlbuilder =
        new TbbSqlBuilder(splitkey, listpara, desb, entityclass);
    return sqlbuilder.getQuerySql();
  }

  private FuncParaDescribe getParaDescribe(TbbRegister register) {
    FuncParaDescribe parades = new FuncParaDescribe();
    parades.setNormalKey(register.getNormalKey());
    parades.setLevelKey(register.getLevelKey());
    parades.setSpelcialKey(register.getSpecialKey());
    parades.setSpecialOperatorMap(register.getParaOperatorMap());
    parades.setParaRedundancyMap(register.getParaReduncyMap());
    return parades;
  }

  private void setParaMetaPath(ParaSplitKey splitkey, FuncParaDescribe desb,
      TbbRegister register) {
    ITbbDateParaMetaPath datemetapath = register.getIDateMetaPath();
    if (null != datemetapath) {
      String datetype = splitkey.getSpecialParaValue(TbbParaValue.DATETYPE);
      register.addParaMetaPathMap(TbbParaValue.BEGINDATE,
          datemetapath.getDateMetaPath(datetype));
      register.addParaMetaPathMap(TbbParaValue.ENDDATE,
          datemetapath.getDateMetaPath(datetype));
    }
    Map<String, String> mapparametapath = register.getParaMetaPathMap();
    desb.setParaMetaPathMap(mapparametapath);
  }

}
