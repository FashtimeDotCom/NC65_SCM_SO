package nc.pubimpl.so.deptmatrel.rule;

import java.util.List;

import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.vo.so.pub.util.ObjectUtil;

/**
 * ��չ������Ϣ
 * 
 * @since 6.0
 * @version 2011-7-6 ����04:05:32
 * @author ף����
 */
public class DeptMatRelDeptExtendRule {
  public void extendDept(List<DeptMatRelParaVO> extendpara) {
    DeptMatRelParaVO[] vos = new DeptMatRelParaVO[extendpara.size()];
    extendpara.toArray(vos);
    for (DeptMatRelParaVO vo : vos) {
      String pk_dept = vo.getPk_dept();
      if (null == pk_dept || "".equals(pk_dept)) {
        continue;
      }
      DeptMatRelParaVO newvo =
          (DeptMatRelParaVO) ObjectUtil.serializableClone(vo);
      vo.setPk_dept(null);
      extendpara.add(newvo);
    }
  }
}
