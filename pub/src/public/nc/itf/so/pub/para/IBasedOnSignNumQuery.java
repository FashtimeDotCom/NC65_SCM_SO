package nc.itf.so.pub.para;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * @author ף����
 */
public interface IBasedOnSignNumQuery {
  Map<String, String> queryBusitypes() throws BusinessException;
}
