package nc.pubimpl.so.m32.api;

import nc.pubimpl.so.pub.api.SOTestCase;

import org.junit.Test;

/**
 * @description
 * ���۷�Ʊ��ѯ��������
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-10 ����11:10:10
 * @author ����
 */
public class SaleinvoiceQueryAPITest extends SOTestCase {

  @Test
  public void test() {
    SaleinvoiceQuery query = new SaleinvoiceQuery();
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
