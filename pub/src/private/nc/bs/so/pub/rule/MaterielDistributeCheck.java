package nc.bs.so.pub.rule;

import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.pubapp.pattern.pub.MapList;

public class MaterielDistributeCheck {

  /**
   * У�����ϺͿ����֯�ķ����ϵ
   * 
   * @param materIDStoreIDs [����][�����֯]
   *          ����һ�б�������ϡ����������֯��Ӧ������ά�����һ��
   */
  public void check(String[][] materIDStoreIDs) {
    // <�����֯,��������>
    MapList<String, String> mstoreIDmaterIDs = new MapList<String, String>();

    for (String[] storeIDmaterID : materIDStoreIDs) {
      mstoreIDmaterIDs.put(storeIDmaterID[1], storeIDmaterID[0]);
    }

    for (Entry<String, List<String>> entry : mstoreIDmaterIDs.entrySet()) {
      // ����ID
      List<String> aryMarid = entry.getValue();
      Set<String> setid = new HashSet<String>();
      for (String marid : aryMarid) {
        setid.add(marid);
      }
      String[] marids = new String[setid.size()];
      setid.toArray(marids);
      // ���������֯
      String storeID = entry.getKey();
      // У��
      MaterialPubService.checkMaterialVisiabilityInStorckOrg(storeID, marids);
    }

  }

}
