package nc.pubimpl.so.mreturnreason.opc.mecc1;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.mreturnreason.entity.ReturnReasonVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.mreturnreason.opc.mecc1.IQueryReturnReasonInfo;
import nc.pubitf.so.mreturnreason.opc.mecc1.ReturnReasonInfoVO;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * �˻�ԭ������
 * 
 * @since 6.0
 * @version 2011-12-28 ����04:20:05
 * @author ����
 * 
 */
public class QueryReturnReasonInfoImpl implements IQueryReturnReasonInfo {

  @Override
  public ReturnReasonInfoVO[] queryGroupReturnReason(String[] pk_group)
      throws BusinessException {
    ReturnReasonInfoVO[] rrivo = null;
    if (null == pk_group || pk_group.length == 0) {
      ExceptionUtils.wrappBusinessException("����PKΪ��");/*-=notranslate=-*/
    }
    try {
      // Ҫ��ѯ��ʵ��������(���š��˻�ԭ��id���˻�ԭ����롢�˻�ԭ������)
      String[] entityNames =
          new String[] {
            ReturnReasonVO.PK_GROUP, ReturnReasonVO.PK_RETURNREASON,
            ReturnReasonVO.VREASONCODE, ReturnReasonVO.VREASONNAME
          };
      VOQuery<ReturnReasonVO> query =
          new VOQuery<ReturnReasonVO>(ReturnReasonVO.class, entityNames);
      // �����ѯ����
      SqlBuilder sWheres = new SqlBuilder();
      sWheres.append("and ");
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      sWheres.append(iq.buildSQL(ReturnReasonVO.PK_GROUP, pk_group));
      sWheres.append(" and ");
      // ���鼯�ż��ڵ������
      sWheres.append(iq.buildSQL(ReturnReasonVO.PK_ORG, pk_group));
      ReturnReasonVO[] rrvo = query.query(sWheres.toString(), null);
      if (null == rrvo || rrvo.length == 0) {
        return new ReturnReasonInfoVO[0];
      }
      int len = rrvo.length;
      rrivo = new ReturnReasonInfoVO[len];
      // ��ԭ�������鸳ֵ�¶�������
      for (int i = 0; i < len; i++) {
        rrivo[i] = new ReturnReasonInfoVO();
        // �˻�ԭ��id
        rrivo[i].setPk_returnreason(rrvo[i].getPk_returnreason());
        // �˻�ԭ�����
        rrivo[i].setVreasoncode(rrvo[i].getVreasoncode());
        // �˻�ԭ������
        rrivo[i].setVreasonname(rrvo[i].getVreasonname());
        // ����
        rrivo[i].setPk_group(rrvo[i].getPk_group());
      }

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return rrivo;
  }

  /**
   * ���ݵ������������������֯�����۹���ģ���ѯ��������֯�ɼ����˻�ԭ�򵵰�
   * 
   * @return �˻�ԭ����ϢReturnReasonVO
   * @throws BusinessException
   * @author ������
   */
  @Override
  public ReturnReasonVO[] queryReturnReasonByPk_orgs(String[] pk_orgs)
      throws BusinessException {
    if (null == pk_orgs || pk_orgs.length == 0) {
      return null;
    }
    ReturnReasonVO[] returnReasonVOs = null;
    try {
      // �����ѯ����
      SqlBuilder conditions = new SqlBuilder();
      String pk_group = AppContext.getInstance().getPkGroup();
      conditions.append(" and ");
      conditions.append(ReturnReasonVO.PK_GROUP, pk_group);
      conditions.append(" and ");
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      conditions.append(iq.buildSQL(ReturnReasonVO.PK_ORG, pk_orgs));
      // ����˻�ԭ����ϢVO
      VOQuery<ReturnReasonVO> voQuery =
          new VOQuery<ReturnReasonVO>(ReturnReasonVO.class);
      returnReasonVOs = voQuery.query(conditions.toString(), null);

      if (null == returnReasonVOs || returnReasonVOs.length == 0) {
        return new ReturnReasonVO[0];
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return returnReasonVOs;
  }
}
