package nc.bs.so.tranmatrel.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.tranmatrel.entity.TranMatRelBVO;
import nc.vo.so.tranmatrel.entity.TranMatRelHVO;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * ���۶������Ϲ�ϵ����ǰ��飨�ǿ�������ظ�[��������+���ϻ�������+�������۷���+����]�������ظ���
 * @scene
 * ���۶������Ϲ�ϵ�������޸ı���ǰ
 * @param
 * ��
 * @author gdsjw
 */
public class CheckSaveBillRule implements IRule<TranMatRelVO> {
  public CheckSaveBillRule() {
    //
  }

  @Override
  public void process(TranMatRelVO[] vos) {
    for (TranMatRelVO vo : vos) {
      // ����ǲ�ȫVO��У��ʱ������Ҫ������״̬
      this.checkNotNull(vo);
      this.checkUnique(vo);
    }
  }

  private void checkNotNull(TranMatRelVO bill) {
    TranMatRelHVO header = bill.getParentVO();
    Integer allowsale = header.getAllowsale();
    if (VOChecker.isEmpty(allowsale)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0","04006007-0004")/*@res "��������/��ֹ���۲���Ϊ�ա�"*/);
    }
    UFBoolean applylower = header.getApplylower();
    if (VOChecker.isEmpty(applylower)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0","04006007-0007")/*@res "�����¼�����Ϊ�ա�"*/);
    }
    String pk_org = header.getPk_org();
    if (VOChecker.isEmpty(pk_org)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0","04006007-0005")/*@res "������֯����Ϊ�ա�"*/);
    }
    TranMatRelBVO[] items = bill.getChildrenVO();
    if ((items != null) && (items.length > 0)) {
      for (TranMatRelBVO item : items) {
        int vostatus = item.getStatus();
        if ((vostatus == VOStatus.DELETED) || (vostatus == VOStatus.UNCHANGED)) {
          // �����ɾ����û�仯����
          continue;
        }
        String materialbaseclass = item.getPk_materialbaseclass();
        boolean ismaterialbaseclassnull = false;
        if (PubAppTool.isNull(materialbaseclass)) {
          ismaterialbaseclassnull = true;
        }
        String materialsaleclass = item.getPk_materialsaleclass();
        boolean ismaterialsaleclassnull = false;
        if (PubAppTool.isNull(materialsaleclass)) {
          ismaterialsaleclassnull = true;
        }
        String material_v = item.getPk_material_v();
        boolean ismaterial_vnull = false;
        if (PubAppTool.isNull(material_v)) {
          ismaterial_vnull = true;
        }
        if (ismaterialbaseclassnull && ismaterialsaleclassnull
            && ismaterial_vnull) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006007_0","04006007-0006")/*@res "���Ϸ��������ϲ���ȫ��Ϊ�ա�"*/);
        }
      }
    }
  }

  private void checkUnique(TranMatRelVO bill) {
    Set<String> relCollections = new HashSet<String>();
    TranMatRelBVO[] items = bill.getChildrenVO();
    if ((items != null) && (items.length > 0)) {
      String nullstring = "NULL";
      StringBuilder sbd = new StringBuilder();
      for (TranMatRelBVO item : items) {
        int vostatus = item.getStatus();
        if (vostatus == VOStatus.DELETED) {
          // �����ɾ������
          continue;
        }
        String materialbaseclass = item.getPk_materialbaseclass();
        if (PubAppTool.isNull(materialbaseclass)) {
          materialbaseclass = nullstring;
        }

        String materialsaleclass = item.getPk_materialsaleclass();
        if (PubAppTool.isNull(materialsaleclass)) {
          materialsaleclass = nullstring;
        }

        String material_v = item.getPk_material_v();
        if (PubAppTool.isNull(material_v)) {
          material_v = nullstring;
        }
        String trantype = item.getTrantype();
        if (PubAppTool.isNull(trantype)) {
          trantype = nullstring;
        }

        sbd.delete(0, sbd.length());
        sbd.append(materialbaseclass).append(materialsaleclass)
            .append(material_v).append(trantype);
        if (relCollections.contains(sbd.toString())) {
          ExceptionUtils
              .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006007_0", "04006007-0013")/*[��������+���ϻ�������+�������۷���+����]�������ظ���*/);
        }
        else {
          relCollections.add(sbd.toString());
        }

      }
    }
  }

}