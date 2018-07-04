package nc.bs.so.m30.rule.approve;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30.ref.po.m20.POm20ServicesUtil;
import nc.itf.so.m30.ref.to.m5a.TOm5AServicesUtil;
import nc.itf.uap.pf.IPFMetaModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pfflow04.MessagedriveVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SaleOrderTranTypeUtil;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.votools.SoVoTools;

/**
 *
 * @description
 * ȡ��������������Ƿ�������Ϣ����(PUSH5AOR20)����(before):
 * <ul>
 * <li>û�����ã���������Ѱ������ɵ�������/�빺��������ȡ������
 * <li>��������������ֱ������Ϊֱ�˵�����Ҫɾ����������̬��������
 * <li>��������������ֱ������Ϊֱ�˲ɹ���Ҫɾ����������̬�빺��
 * @scene 
 * ���۶���ȡ������ǰ
 * @param 
 * pfMetaModelService ������Ϣ��������ӿ�
 * alDelete5AVOs ������Ϣ����PUSH5AOR20��Ҫɾ����������̬��������VOs
 * alDelete20VOs ������Ϣ����PUSH5AOR20��Ҫɾ����������̬�빺��VOs
 * @since 6.0
 * @version 2010-12-21 ����10:03:45
 * @author ��־ΰ
 */
public class CheckPush5Aor20Rule implements IRule<SaleOrderVO> {

  private IPFMetaModel pfMetaModelService;

  // ������Ϣ����PUSH5AOR20��Ҫɾ����������̬��������VOs
  private List<SaleOrderVO> alDelete5AVOs;

  // ������Ϣ����PUSH5AOR20��Ҫɾ����������̬�빺��VOs
  private List<SaleOrderVO> alDelete20VOs;

  @Override
  public void process(SaleOrderVO[] vos) {
    // 1.����VOs����
    this.filterVOs(vos);

    // 2.����������ȡ������ɾ��������Ϣ�����Ƶ����ɵĵ�������
    if (null != this.alDelete5AVOs && this.alDelete5AVOs.size() > 0) {
      SaleOrderVO[] delete5Avos = new SaleOrderVO[this.alDelete5AVOs.size()];
      this.alDelete5AVOs.toArray(delete5Avos);
      this.delete5A(delete5Avos);
    }

    // 3.����������ȡ������ɾ��������Ϣ�����Ƶ����ɵ��빺��
    if (null != this.alDelete20VOs && this.alDelete20VOs.size() > 0) {
      SaleOrderVO[] delete20vos = new SaleOrderVO[this.alDelete20VOs.size()];
      this.alDelete20VOs.toArray(delete20vos);
      this.delete20(delete20vos);
    }
  }

  private void delete20(SaleOrderVO[] vos) {
    String[] m30IDs = SoVoTools.getVOPKValues(vos);
    try {
      POm20ServicesUtil.delete20ByPo(m30IDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void delete5A(SaleOrderVO[] vos) {
    String[] m30IDs = SoVoTools.getVOPKValues(vos);
    try {
      TOm5AServicesUtil.delete5AByTo(m30IDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void filterVOs(SaleOrderVO[] vos) {
    for (SaleOrderVO vo : vos) {
      // �����Ƿ�������Ϣ����PUSH5AOR20����VOs
      if (this.isConfigDrive(vo)) {
        this.filterVOsByDirectType(vo);
      }
    }
  }

  private void filterVOsByDirectType(SaleOrderVO vo) {
    int directType =
        new SaleOrderTranTypeUtil().getDirectType(vo.getParentVO()
            .getCtrantypeid());
    this.alDelete5AVOs = new ArrayList<SaleOrderVO>();
    this.alDelete20VOs = new ArrayList<SaleOrderVO>();
    // ���ݽ�������ֱ�����ͱ�Ƿ���VOs
    if (DirectType.DIRECTTRAN_TO.getIntValue() == directType) {
      this.alDelete5AVOs.add(vo);
    }
    else if (DirectType.DIRECTTRAN_PO.getIntValue() == directType) {
      this.alDelete20VOs.add(vo);
    }
    // ����ʱ��Ҫȫ��ɾ��
    else {
      this.alDelete5AVOs.add(vo);
      this.alDelete20VOs.add(vo);
    }
    // ��Ҫ���ѰԴ����,����ͨ��ѰԴ���ɵ����β����Զ�ɾ��
  }

  private boolean isConfigDrive(SaleOrderVO vo) {
    IPFMetaModel service = this.getPFMetaModel();
    try {
      MessagedriveVO[] mdVOs =
          service.queryAllMsgdrvVOs(vo.getParentVO().getVtrantypecode(), vo
              .getParentVO().getCbiztypeid(), "APPROVE");
      for (MessagedriveVO mdVO : mdVOs) {
        if ("PUSH5AOR20".equals(mdVO.getActiontype())) {
          return true;
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  private IPFMetaModel getPFMetaModel() {
    if (this.pfMetaModelService == null) {
      this.pfMetaModelService =
          NCLocator.getInstance().lookup(IPFMetaModel.class);
    }
    return this.pfMetaModelService;
  }
}
