package nc.pubimpl.so.m30.api;

import nc.pubimpl.so.pub.api.SOTestCase;

import org.junit.Test;

/**
 * @description
 * ���۶�����ѯapi��������
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-10 ����11:05:45
 * @author ����
 */
public class SaleOrderQueryAPITest extends SOTestCase {

  @Test
  public void test() {
    SaleOrderQuery query = new SaleOrderQuery();
    query.queryVOByIDs();
    query.queryViewVOByBIDs();
    query.queryVOByScheme();
    query.queryViewVOByScheme();
    query.queryViewVOBySourceBIDs();
  }

  @Override
  protected String getPwd() {
    return super.getPwd();
  }

  @Override
  protected String getUser() {
    return super.getUser();
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }
}
