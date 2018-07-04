package nc.vo.so.m30.balend.enumeration;


public enum VirtualBalType {

  // ���ⵥ�ѽ���
  BALED(0),

  // ���ⵥ�����(�����۳��ⵥ���η�Ʊ�Ѿ�����������۳��ⵥ��������Գ��Ѿ��޷��ٽ���)
  HALFBAL(1),

  // ���ⵥδ����
  NOTBAL(2);

  private Integer code;

  private VirtualBalType(int code) {
    this.code = Integer.valueOf(code);
  }

  public Integer getCode() {
    return this.code;
  }
}
