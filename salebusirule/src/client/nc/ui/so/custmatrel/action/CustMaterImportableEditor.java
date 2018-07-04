package nc.ui.so.custmatrel.action;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.itf.so.custmatrel.ICustMatRelMaintain;
import nc.ui.so.custmatrel.model.CustMatRelBillManageModel;
import nc.ui.trade.excelimport.Uif2BodyOnlyImportablePanel;
import nc.ui.trade.excelimport.vo.CommonAggVO2;
import nc.ui.trade.excelimport.vo.DataRowVO;
import nc.ui.uif2.UIState;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ExtendedAggregatedValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.base.entity.AllowSale;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * ���봦����
 * 
 * @since 6.3
 * @version 2013-05-17 13:57:37
 * @author liujingn
 */
@SuppressWarnings("restriction")
public class CustMaterImportableEditor extends Uif2BodyOnlyImportablePanel {

  /**
   * jilu for ��Ӥ�� �ݴ�����Ҫ�����VO
   */
  private List<ExtendedAggregatedValueObject> toSaveVOs =
      new ArrayList<ExtendedAggregatedValueObject>();

  @Override
  public void addNew() {
    ((CustMatRelBillManageModel) this.getAppModel()).initModel(null);
    ((CustMatRelBillManageModel) this.getAppModel()).setUiState(UIState.ADD);
  }

  /**
   *
   */
  public CustMaterImportableEditor() {

    super(null, null, null);
  }

  /**
   * 
   * @param title
   * @param funCode
   * @param configFilePath
   */
  public CustMaterImportableEditor(
      String title, String funCode, String configFilePath) {
    super(title, funCode, configFilePath);
  }

  @Override
  protected void setProcessedVO(ExtendedAggregatedValueObject eavo) {
    // // ʹ��ģ����ֵ
    // this.getBillData().setImportBillValueVO(eavo);
    // // ���ñ�����
    // this.setDefaultValue(eavo);
    // // �����޸Ĵ����������֯�´��ڿͻ����Ϲ�ϵ����ô����Ϊ�ǵ����޸Ĳ����������������
    // String pkorg = this.getAppModel().getContext().getPk_org();
    // ICustMatRelMaintain service =
    // NCLocator.getInstance().lookup(ICustMatRelMaintain.class);
    // CustMatRelVO custmtvo = null;
    // try {
    // custmtvo = service.queryByOrg(pkorg);
    // }
    // catch (BusinessException ex) {
    // ExceptionUtils.wrappException(ex);
    // }
    //
    // this.importUpdate(custmtvo);

  }

  @Override
  protected String getAppModelBeanName() {
    return null;
  }

  @Override
  protected String getAddActionBeanName() {
    return null;
  }

  @Override
  protected String getSaveActionBeanName() {
    return null;
  }

  @Override
  protected String getCancelActionBeanName() {
    return null;
  }

  @Override
  protected String getBillCardEditorBeanName() {
    return null;
  }

  /**
   * ������� jilu for ��Ӥ�� ������ǰ̨�ı��棬���ǽ�VOֱ�Ӵ�����̨���б��档
   */
  public void save() throws Exception {
    if (toSaveVOs == null) {
      return;
    }
    for (ExtendedAggregatedValueObject aggVO : toSaveVOs) {
      if (aggVO.getParentVO() == null) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006007_0", "04006007-0065")/* ����ʧ�ܣ�����ı�ͷ���ݲ���Ϊ�ա� */);
      }
      if (aggVO.getTableCodes() == null) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006007_0", "04006007-0066")/* ����ʧ�ܣ�����ı������ݲ���Ϊ�ա� */);
      }
      Object allowsale =
          aggVO.getParentVO().getAttributeValue(CustMatRelHVO.ALLOWSALE);

      if (AllowSale.ALLOW_SALE.getName().equals(allowsale)) {
        aggVO.getParentVO().setAttributeValue(CustMatRelHVO.ALLOWSALE,
            AllowSale.ALLOW_SALE.getIntValue());
      }
      else if (AllowSale.FORBID_SALE.getName().equals(allowsale)) {
        aggVO.getParentVO().setAttributeValue(CustMatRelHVO.ALLOWSALE,
            AllowSale.FORBID_SALE.getIntValue());
      }
      
      if (!AllowSale.ALLOW_SALE.equalsValue(allowsale)
          && !AllowSale.FORBID_SALE.equalsValue(allowsale)
          && !AllowSale.ALLOW_SALE.getName().equals(allowsale)
          && !AllowSale.FORBID_SALE.getName().equals(allowsale)) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006007_0", "04006007-0062")/*
                                                      * ��������/��ֹ�������������¼��1��2��(1,��������
                                                      * ;2,��ֹ����)
                                                      */);
      }

    }
    CustMatRelVO[] aggVOs = this.transExtendedAggVOToAggVO(getToSaveVOs());

    // ��ǰ̨������ļ�����У�飬�ѵ����ļ��ı���תΪpk���浽���ݿ���
    NCLocator.getInstance().lookup(ICustMatRelMaintain.class).importXLS(aggVOs);
  }

  /**
   * ������aggVO����
   * 
   * @param toSaveVOs2
   * @return
   */
  private CustMatRelVO[] transExtendedAggVOToAggVO(
      List<ExtendedAggregatedValueObject> toSaveVOs2) {
    CustMatRelVO[] CustMatRelVOs = new CustMatRelVO[toSaveVOs2.size()];
    // ���ｫ�����ļ�����������֯�Ĳ�ͬ�ϳɶ���ۺ�VO�������ڵ����ļ����޶�ֻ�ܵ���һ��������֯���ļ��������ﷵ�صľۺ�voֻ��һ�ŵ���
    for (int i = 0; i < toSaveVOs2.size(); i++) {
      CommonAggVO2 commonAggVO = (CommonAggVO2) toSaveVOs2.get(i);
      CustMatRelVOs[i] = this.createCustAggVOByCommonAggVO(commonAggVO);
    }
    return CustMatRelVOs;
  }

  /**
   * �ۺϳ�aggVO
   * 
   * @param commonAggVO
   * @return
   */
  private CustMatRelVO createCustAggVOByCommonAggVO(CommonAggVO2 commonAggVO) {
    // �����ͷvo
    CustMatRelHVO relHVO = this.createCustMatRelHVO(commonAggVO);
    // �������vo
    CustMatRelBVO[] relBVO = this.createCustMatRelBVO(commonAggVO);
    CustMatRelVO custMatRelVO = new CustMatRelVO();
    // ����ۺ�vo
    custMatRelVO.setParentVO(relHVO);
    custMatRelVO.setChildrenVO(relBVO);
    return custMatRelVO;
  }

  /**
   * ȡ������б����Ӧ���ݣ�����BVO
   * 
   * @param toSaveVOs2
   * @return
   */
  private CustMatRelBVO[] createCustMatRelBVO(CommonAggVO2 commonAggVO) {
    CircularlyAccessibleValueObject[] dataRowVOs =
        commonAggVO.getTableVO(commonAggVO.getTableCodes()[0]);
    CustMatRelBVO[] custMatRelBVOs = new CustMatRelBVO[dataRowVOs.length];
    // ������ڶ��У�ʹ��˫��forѭ������ÿһ�еı��帳ֵ
    for (int i = 0; i < dataRowVOs.length; i++) {
      // ��ȡdataRowVO�е�fiield���ֶ��������ٸ���field��ȡ���ֶεı���
      DataRowVO dataRowVO = (DataRowVO) dataRowVOs[i];
      for (String field : dataRowVO.getAttributeNames()) {
        String value = dataRowVO.getAttributeValue(field).toString();
        if (value != null) {
          value = value.equals("") ? null : value;
        }
        if (custMatRelBVOs[i] == null) {
          custMatRelBVOs[i] = new CustMatRelBVO();
        }

        if (CustMatRelBVO.EXCLUDE.equals(field)) {
          if (!"Y".equals(value) && !"N".equals(value)) {
            ExceptionUtils.wrappBusinessException(NCLangResOnserver
                .getInstance().getStrByID("4006007_0", "04006007-0064")/*
                                                                        * �������ֶ�Ϊ��ѡ����������N
                                                                        * ��Y��
                                                                        */);
          }
        }
        // ����ȡ�ı���浽bvo�� ������bvo
        custMatRelBVOs[i].setAttributeValue(field, value);
      }
    }
    return custMatRelBVOs;
  }

  /**
   * ȡ������б�ͷ��Ӧ���ݣ�����HVO
   * 
   * @param toSaveVOs2
   * @return
   */
  private CustMatRelHVO createCustMatRelHVO(CommonAggVO2 commonAggVO) {
    CustMatRelHVO custMatRelHVO = new CustMatRelHVO();
    DataRowVO dataRowVO = (DataRowVO) commonAggVO.getParentVO();
    // ��ȡdataRowVO�е�fiield���ֶ��������ٸ���field��ȡ���ֶεı���
    for (String field : dataRowVO.getAttributeNames()) {
      String value = dataRowVO.getAttributeValue(field).toString();
      if (value != null) {
        value = value.equals("") ? null : value;
      }
      // ����ȡ�ı���浽hvo�� ������hvo
      custMatRelHVO.setAttributeValue(field, value);
    }
    return custMatRelHVO;
  }

  public List<ExtendedAggregatedValueObject> getToSaveVOs() {
    return this.toSaveVOs;
  }

  public void setToSaveVOs(List<ExtendedAggregatedValueObject> toSaveVOs) {
    this.toSaveVOs = toSaveVOs;
  }
}
