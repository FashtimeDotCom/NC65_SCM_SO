package nc.vo.so.pub.mmpps.calc;

import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;

import nc.pubitf.mmpub.sdmanage.IDemandResult;

/**
 * ���۲�����������MPS�����ṩ��ѯ�ӿڣ���������������
 * 
 * @since 6.1
 * @version 2011-12-12 13:49
 * @author ������
 */
public abstract class DemandResultForCalcImpl implements IDemandResult {

  /**
   * �������ID
   * 
   * @return ����ID
   */
  public abstract String getBodyDemandbillid();

  /**
   * ��Ҫ���෵���ӱ���
   * 
   * @return �ӱ���
   */
  public abstract String getBodyTable();

  /**
   * ����ӱ��ֶ�
   * 
   * @param bodykey
   * @return �ӱ��ֶ�
   */
  public String getBodyTableCol(String bodykey) {
    return this.getBodyTable() + SOConstant.POINT + bodykey;
  }

  @Override
  public String getCustomerid() {
    return this.getHeadTableCol(SOItemKey.CCUSTOMERID);
  }

  @Override
  public abstract String getDemandbillbid();

  @Override
  public abstract String getDemandbillid();

  @Override
  public String getDemandCode() {
    return this.getHeadTableCol(SOItemKey.VBILLCODE);
  }

  @Override
  public abstract String getDemandNnum();

  @Override
  public String getDemandOrgid() {
    return this.getBodyTableCol(SOItemKey.CSENDSTOCKORGID);
  }

  @Override
  public String getDemandOrgvid() {
    return this.getBodyTableCol(SOItemKey.CSENDSTOCKORGVID);
  }

  @Override
  public String getDemandRowNo() {
    return this.getBodyTableCol(SOItemKey.CROWNO);
  }

  @Override
  public String getDemandTime() {
    return this.getBodyTableCol(SOItemKey.DSENDDATE);
  }

  @Override
  public String getFirstBid() {
    return null;
  }

  /**
   * Դͷ���ݺ�
   */
  @Override
  public abstract String getFirstCode();

  /**
   * Դͷ������������
   */
  @Override
  public String getFirstId() {
    return null;
  }

  /**
   * Դͷ�����ӱ��к�
   */
  @Override
  public abstract String getFirstRowNo();

  /**
   * Դͷ��������
   */
  @Override
  public abstract String getFirstType();

  @Override
  public String getFree1() {
    return this.getBodyTableCol(SOItemKey.VFREE1);
  }

  @Override
  public String getFree10() {
    return this.getBodyTableCol(SOItemKey.VFREE10);
  }

  @Override
  public String getFree2() {
    return this.getBodyTableCol(SOItemKey.VFREE2);
  }

  @Override
  public String getFree3() {
    return this.getBodyTableCol(SOItemKey.VFREE3);
  }

  @Override
  public String getFree4() {
    return this.getBodyTableCol(SOItemKey.VFREE4);
  }

  @Override
  public String getFree5() {
    return this.getBodyTableCol(SOItemKey.VFREE5);
  }

  @Override
  public String getFree6() {
    return this.getBodyTableCol(SOItemKey.VFREE6);
  }

  @Override
  public String getFree7() {
    return this.getBodyTableCol(SOItemKey.VFREE7);
  }

  @Override
  public String getFree8() {
    return this.getBodyTableCol(SOItemKey.VFREE8);
  }

  @Override
  public String getFree9() {
    return this.getBodyTableCol(SOItemKey.VFREE9);
  }

  @Override
  public String getFrom() {
    SqlBuilder fromsql = new SqlBuilder();
    fromsql.append(this.getHeadTable() + " inner join " + this.getBodyTable());
    fromsql.append(" on " + this.getDemandbillid());
    fromsql.append(" = " + this.getBodyDemandbillid());

    return fromsql.toString();
  }

  /**
   * ��Ҫ���෵��������
   * 
   * @return ������
   */
  public abstract String getHeadTable();

  /**
   * �����ֶ�
   * 
   * @param headkey
   * @return �����ֶ�
   */
  public String getHeadTableCol(String headkey) {
    return this.getHeadTable() + SOConstant.POINT + headkey;
  }

  @Override
  public String getMaterialid() {
    return this.getBodyTableCol(SOItemKey.CMATERIALID);
  }

  @Override
  public String getMaterialvid() {
    return this.getBodyTableCol(SOItemKey.CMATERIALVID);
  }

  @Override
  public String getProductorid() {
    return this.getBodyTableCol(SOItemKey.CPRODUCTORID);
  }

  @Override
  public String getPprojectid() {
    return this.getBodyTableCol(SOItemKey.CPROJECTID);
  }

  @Override
  public String getReservatioNnum() {
    return null;
  }

  @Override
  public String getVendorid() {
    return this.getBodyTableCol(SOItemKey.CVENDORID);
  }

  @Override
  public String getWhere() {

    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(this.getHeadTableCol(SOItemKey.DR), 0);
    wheresql.append(" and ");
    wheresql.append(this.getBodyTableCol(SOItemKey.DR), 0);

    return wheresql.toString();
  }

}
