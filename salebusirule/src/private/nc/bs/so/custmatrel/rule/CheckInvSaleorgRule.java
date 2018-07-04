package nc.bs.so.custmatrel.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.sale.MaterialSaleVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * @description
 *              ���ۿͻ����Ϲ�ϵ����ǰ��������Ƿ���䵽������֯
 * @scene
 *        ���ۿͻ����Ϲ�ϵ�������޸ı���ǰ
 * @param ��
 * @author gdsjw
 */
public class CheckInvSaleorgRule implements IRule<CustMatRelVO> {

  public CheckInvSaleorgRule() {
    //
  }

  @Override
  public void process(CustMatRelVO[] vos) {
    for (CustMatRelVO vo : vos) {
      // ����ǲ�ȫVO��У��ʱ������Ҫ������״̬
      this.checkInvSaleorg(vo);
    }
  }

  private void checkInvSaleorg(CustMatRelVO bill) {
    CustMatRelHVO header = bill.getParentVO();
    String pk_saleorg = header.getPk_org();
    List<String> materialvids = new ArrayList<String>();
    CustMatRelBVO[] items = bill.getChildrenVO();
    if ((items != null) && (items.length > 0)) {
      for (CustMatRelBVO item : items) {
        int vostatus = item.getStatus();
        if ((vostatus == VOStatus.DELETED) || (vostatus == VOStatus.UNCHANGED)) {
          continue;
        }
        String material_v = item.getPk_material_v();
        if (!(PubAppTool.isNull(material_v))) {
          materialvids.add(material_v);
        }
      }
    }
    if (materialvids.size() == 0) {
      return;
    }
    String[] fields = new String[] {
      "pk_materialsale", "pk_material", "pk_group", "pk_org"
    };
    try {
      List<String> notsaleorgmaterialvids = new ArrayList<String>();
      Map<String, MaterialSaleVO> matsalMap =
          MaterialPubService.queryMaterialSaleInfoByPks(
              materialvids.toArray(new String[0]), pk_saleorg, fields);
      for (String materialvid : materialvids) {
        if ((matsalMap == null) || (!matsalMap.containsKey(materialvid))) {
          notsaleorgmaterialvids.add(materialvid);
        }
      }
      if (notsaleorgmaterialvids.size() > 0) {
        fields = new String[] {
          "code", "name"
        };
        MaterialVO[] materialvos =
            MaterialPubService.queryMaterialBaseInfoByPks(
                notsaleorgmaterialvids.toArray(new String[0]), fields);
        StringBuilder errmessage = new StringBuilder();
        for (MaterialVO materialvo : materialvos) {
          errmessage.append(materialvo.getCode()).append("(")
              .append(materialvo.getName()).append("),");
        }
        if (errmessage.length() > 0) {
          errmessage.deleteCharAt(errmessage.length() - 1);
          throw new BusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4006007_0", "04006007-0014", null, new String[] {
                errmessage.toString()
              })/*��ѡ����[{0}]��δ���䵽������֯����������ͻ����Ϲ�ϵ��*/);

        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
