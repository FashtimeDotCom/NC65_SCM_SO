/**
 * 
 */
package nc.vo.so.m30.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * @author wangzym
 * @version 2017��3��2�� ����10:02:00
 * Ϊ����ȡ��ȡ���½�ö����
 */
public class Djqz extends MDEnum {

	/**
	 * @param enumValue
	 */
	public Djqz(IEnumValue enumValue) {
		super(enumValue);
		// TODO �Զ����ɵĹ��캯�����
	}

	// ��
	public static final Djqz YES = MDEnum.valueOf(Djqz.class,
			Integer.valueOf(0));

	// ��
	public static final Djqz NO = MDEnum
			.valueOf(Djqz.class, Integer.valueOf(1));

	public Integer getIntegerValue() {
		return Integer.valueOf(this.value().toString());
	}

	public int getIntValue() {
		return Integer.parseInt(this.value().toString());
	}

	public String getStringValue() {
		return this.value().toString();
	}

}
