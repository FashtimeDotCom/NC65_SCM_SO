package nc.vo.so.pub.calculator;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.calculator.data.IDataSetForCal;

public interface INumPriceMnyCheckData extends IDataSetForCal {

  /** ����Ƿ��ۿ������� */
  UFBoolean getBdiscountflag();

  /** ����Ƿ���������� */
  UFBoolean getBlaborflag();

  /** ����Ƿ���Ʒ */
  UFBoolean getBlargessflag();

}
