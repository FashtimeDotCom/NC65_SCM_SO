package nc.vo.so.pub.transtype;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.pub.SOItemKey;
import nc.vo.trade.checkrule.VOChecker;

/**
 * ���۹���������У�鹤����
 * E �����������ڵĵ��ݱ��Ӧvo,�������۶�������vo:SaleOrderHVO
 * E ������������Ӧ����չ��vo,�������۶�������������չ��vo:M30TranTypeVO
 * 
 * @since 6.0
 * @version 2011-6-14 ����07:35:33
 * @author zhangcheng
 */
public class CheckTransTypeRef<E extends ISuperVO, T extends ISuperVO> {

  /**
   * Ҫ��ѯ�ĵ���ʵ��Class
   */
  private Class<E> voClass;

  /**
   * Ҫ��ѯ�Ľ���������չʵ��Class
   */
  private Class<T> voTransTypeClass;

  public CheckTransTypeRef(Class<E> voClass, Class<T> voTransTypeClass) {
    this.voClass = voClass;
    this.voTransTypeClass = voTransTypeClass;
  }

  /**
   * ���������Ѿ����������ã���ֹ�޸ĺ�ɾ��
   * 
   * @param transTypeIDKey -- ���ݽ�������ID�ֶ���
   * @param transTypeIDValues -- ���ݽ�������IDֵ
   */
  public void checkRef(String transTypeIDKey, String transTypeIDValues) {
    SqlBuilder condition = new SqlBuilder();
    condition.append(" and ");
    condition.append(transTypeIDKey, transTypeIDValues);
    E[] vos = new VOQuery<E>(this.voClass).query(condition.toString(), null);
    if (!VOChecker.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006004_0", "04006004-0010")
      /*@res "���������Ѿ����������ã���ֹ�޸ĺ�ɾ����"*/);
    }
  }

  /**
   * ���������Ѿ����������ã���ֹ�޸�keys��ָ�����ֶ�
   * 
   * @param transTypeIDKey -- ���ݽ�������ID�ֶ���
   * @param transTypeIDValues -- ���ݽ�������IDֵ
   * @param transTypeIDValues -- ���Ƚϵ��ֶ�key
   */
  public void checkRefByFields(String transTypeIDKey, String transTypeIDValues,
      T updateTransTypeVO, String[] keys) {
    SqlBuilder condition = new SqlBuilder();
    condition.append(" and ");
    condition.append(transTypeIDKey, transTypeIDValues);
    E[] vos = new VOQuery<E>(this.voClass,new String[]{
    		SOItemKey.VBILLCODE
    }).query(condition.toString(), null);
    // �Ѿ��е�������
    if (!VOChecker.isEmpty(vos)) {
      // ��ѯԭʼ������������
      T[] transvos =
          new VOQuery<T>(this.voTransTypeClass).query(condition.toString(),
              null);
      T origTransTypeVO = transvos[0];
      for (String key : keys) {
        Object origValue = origTransTypeVO.getAttributeValue(key);
        Object updateValue = updateTransTypeVO.getAttributeValue(key);
        // ���Բ�Ϊ��
        if (!VOChecker.isEmpty(origValue) && !VOChecker.isEmpty(updateValue)) {
          if (!origValue.equals(updateValue)) {
            ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006004_0", "04006004-0011")
            /*@res "���������Ѿ����������ã���ֹ�޸ģ�"*/);
          }
        }
        // ���Զ�Ϊ��
        else if (VOChecker.isEmpty(origValue) && VOChecker.isEmpty(updateValue)) {
          continue;
        }
        else {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4006004_0", "04006004-0011")
          /*@res "���������Ѿ����������ã���ֹ�޸ģ�"*/);
        }
      }
    }
  }

}
