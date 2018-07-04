package nc.vo.so.pub;

/**
 * ���۹�����ö����
 * 
 * @since 6.1
 * @version 2012-11-29 09:29:07
 * @author ��ӱ�
 */
public enum SOTable {

  /**
   * ��������
   */
  BUYLARGESS("so_buylargess"),
  /**
   * �����ӱ�
   */
  BUYLARGESS_B("so_buylargess_b"),
  /**
   * ���۵���������
   */
  SALEQUOTATIONTYPE("so_salequotationtype"),

  /**
   * ���۵�����
   */
  SALEQUOTATION("so_salequotation"),

  /**
   * ���۵��ӱ�
   */
  SALEQUOTATION_B("so_salequotation_b"),

  /**
   * Ԥ������������
   */
  M38TRANTYPE("so_m38trantype"),

  /**
   * Ԥ��������
   */
  PREORDER("so_preorder"),

  /**
   * Ԥ�����ӱ�
   */
  PREORDER_B("so_preorder_b"),

  /**
   * ���۶�������
   */
  SALEORDER("so_saleorder"),

  /**
   * ���۶����ӱ�
   */
  SALEORDER_B("so_saleorder_b"),

  /**
   * ���۶���ִ�б�
   */
  SALEORDER_EXE("so_saleorder_exe"),

  /**
   * ���۶����޶�����
   */
  ORDERHISTORY("so_orderhistory"),

  /**
   * ���۶����޶��ӱ�
   */
  ORDERHISTORY_B("so_orderhistory_b"),

  /**
   * ����������
   */
  DELIVERY("so_delivery"),

  /**
   * �������ӱ�
   */
  DELIVERY_B("so_delivery_b"),

  /**
   * �������ʼ��
   */
  DELIVERY_CHECK("so_delivery_check"),

  /**
   * ���۷�Ʊ����
   */
  SALEINVOICE("so_saleinvoice"),

  /**
   * ���۷�Ʊ�ӱ�
   */
  SALEINVOICE_B("so_saleinvoice_b"),

  /**
   * ���۷�Ʊ���㵥����
   */
  SQUAREINV("so_squareinv"),

  /**
   * ���۷�Ʊ���㵥�ӱ�
   */
  SQUAREINV_B("so_squareinv_b"),

  /**
   * ���۷�Ʊ���㵥��ϸ��
   */
  SQUAREINV_D("so_squareinv_d"),

  /**
   * ���۳�����㵥����
   */
  SQUAREOUT("so_squareout"),

  /**
   * ���۳�����㵥�ӱ�
   */
  SQUAREOUT_B("so_squareout_b"),

  /**
   * ���۳�����㵥��ϸ��
   */
  SQUAREOUT_D("so_squareout_d"),

  /**
   * ;����㵥����
   */
  SQUAREWAS("so_squarewas"),

  /**
   * ;����㵥�ӱ�
   */
  SQUAREWAS_B("so_squarewas_b"),

  /**
   * ;����㵥��ϸ��
   */
  SQUAREWAS_D("so_squarewas_d"),

  /**
   * ���۹���ID��ʱ��1
   * ʹ�ó����������ֶγ���200ʱ������ʱ����
   */
  TMP_SO_ID1("tmp_so_id1"),
  /**
   * ���۹���ID��ʱ��2
   * ʹ�ó�����ͬʱ�����������ֶγ���200ʱ��2��������ʱ����
   */
  TMP_SO_ID2("tmp_so_id2"),

  /**
   * �˻�����ƥ�������ʱ��
   * ʹ�ó������˻�����ƥ��ʱ�����չ����
   */
  TMP_SO_RETURNASSIGN("tmp_so_retasgn61"),

  /**
   * ������֯ID��ʱ��
   * ʹ�ó��������۶�������Ʊ�������տ����Ϊ�����ṩȡ������ʱ�����������֯����
   */
  TMP_SO_SALEORG("tmp_so_saleorg"),

  /**
   * ��������ID��ʱ��
   * ʹ�ó��������۶�������Ʊ�������տ����Ϊ�����ṩȡ������ʱ����Ŷ������Ͳ���
   */
  TMP_SO_ORDERTYPE("tmp_so_ordertype"),

  /**
   * �����ͻ�ID��ʱ��
   * ʹ�ó��������۶�������Ʊ�������տ����Ϊ�����ṩȡ������ʱ����Ŷ����ͻ�����
   */
  TMP_SO_ORDERCUST("tmp_so_ordercust"),

  /**
   * ��Ʊ�ͻ�ID��ʱ��
   * ʹ�ó��������۶�������Ʊ�������տ����Ϊ�����ṩȡ������ʱ����ſ�Ʊ�ͻ�����
   */
  TMP_SO_INVCUST("tmp_so_invcust"),

  /**
   * ���㷽ʽID��ʱ��
   * ʹ�ó��������۶�������Ʊ�������տ����Ϊ�����ṩȡ������ʱ����Ž��㷽ʽ����
   */
  TMP_SO_BALTYPE("tmp_so_baltype"),
  /**
   * �۸���ID��ʱ��
   * ʹ�ó��������۶�������Ʊ�������տ����Ϊ�����ṩȡ������ʱ����ż۸������
   */
  TMP_SO_PRCITEM("tmp_so_prcitem"),
  /**
   * �ƻ���������ʹ����ʱ��
   * ʹ�ó������ƻ����������������������
   */
//  TMP_SO_PID("tmp_so_pid"), for V636 ������ʱ��������һ��������ά�ȣ�������Ҫ�޸ı��� jilu
  TMP_SO_PID("tmp_so_pid2"),
  /**
   * ����ë��������ҳ��ʱ��
   * ʹ�ó���������ë�����������ŷ�ҳ������ϸ����
   */
  TMP_SO_ORDERPFPAGE("tmp_so_orderpfpage"),
  /**
   * ����ë���������ݼӹ������ʱ��
   * ʹ�ó���������ë������������ë������������
   */
  TMP_SO_ORDERPROFIT("tmp_so_orderprofit"),
  /**
   * ����ë��������ҳ��ʱ��
   * ʹ�ó���������ë�����������ŷ�ҳ���ⵥ��ϸ����
   */
  TMP_SO_OUTPFPAGE("tmp_so_outpfpage"),
  /**
   * ����ë���������ݼӹ������ʱ��
   * ʹ�ó���������ë������������ë������������
   */
  TMP_SO_OUTPROFIT("tmp_so_outprofit"),

  /**
   * ����ִ�л��ܷ�ҳ��ʱ��
   * ʹ�ó���������ִ�л��ܱ����ŷ�ҳ������ϸ����
   */
  TMP_SO_ORDERSUMPAGE("tmp_so_ordersumpage"),
  /**
   * ����ִ�л������ݼӹ������ʱ��
   * ʹ�ó���������ִ�л��ܱ������ݼӹ�ִ�н������
   */
  TMP_SO_ORDERSUMMARY("tmp_so_ordersummary"),
  /**
   * ��Ʊִ�л��ܷ�ҳ��ʱ��
   * ʹ�ó�������Ʊִ�л��ܱ����ŷ�ҳ��Ʊ��ϸ����
   */
  TMP_SO_INVSUMPAGE("tmp_so_invsumpage"),
  /**
   * ��Ʊִ�л������ݼӹ������ʱ��
   * ʹ�ó�������Ʊִ�л��ܱ������ݼӹ�ִ�н������
   */
  TMP_SO_INVSUMMARY("tmp_so_invsummary"),
  /**
   * ����ִ�л��ܷ�ҳ��ʱ��
   * ʹ�ó���������ִ�л��ܱ����ŷ�ҳ������ϸ����
   */
  TMP_SO_OUTSUMPAGE("tmp_so_outsumpage"),
  /**
   * ����ִ�л������ݼӹ������ʱ��
   * ʹ�ó���������ִ�л��ܱ������ݼӹ�ִ�н������
   */
  TMP_SO_OUTSUMMARY("tmp_so_outsummary"),
  /**
   * �ۺ�ë���������ݼӹ������ʱ��
   * ʹ�ó������ۺ�ë���������������ݼӹ�ִ�н������
   */
  TMP_SO_MULTIPLEPROFIT("tmp_so_multprofit"),
  /**
   * ����ƥ��ʹ����ʱ��
   * ʹ�ó���������ƥ����ƥ�����
   */
  TMP_SO_LARMATCH("tmp_so_larmatch"),

  TMP_SO_PRCPROMTYPE("tmp_so_prcpromtype");

  private String name;

  private SOTable(String name) {
    this.name = name;
  }

  /**
   * ��ñ���
   * 
   * @return ����
   */
  public String getName() {
    return this.name;
  }
}
