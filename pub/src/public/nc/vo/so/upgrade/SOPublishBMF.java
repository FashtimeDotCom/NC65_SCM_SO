package nc.vo.so.upgrade;

import java.util.ArrayList;
import java.util.List;

import nc.scmmm.upgrade.scmpub.bmfpublish.AbstractSCMPublishBMF;
import nc.scmmm.upgrade.scmpub.bmfpublish.BmfFilePath;
import nc.scmmm.upgrade.scmpub.bmfpublish.MulitParentDirs;

/**
 * 633soģ�鲹����Ԫ���ݷ�������
 * 
 * @since 6.33
 * @version 2014-6-26 ����10:58:13
 * @author ��¼
 */
public class SOPublishBMF extends AbstractSCMPublishBMF {

  @Override
  protected String getModuleDir() {
    return "so";
  }

  @Override
  protected BmfFilePath[] getBmfFilePath() {
    List<BmfFilePath> paths = new ArrayList<BmfFilePath>();

    paths.add(new BmfFilePath("ardeduction", "ardeduction.bmf")); // �ͻ����õ�Ԫ����
    paths.add(new BmfFilePath("m35trantype", "m35trantype.bmf")); // ���۷��õ�����Ԫ����
    paths.add(new BmfFilePath("SaleQuotation", "salequotation.bmf")); // ���۵�Ԫ����
    paths.add(new BmfFilePath("SO_Buylargess", "buylargess.bmf")); // ��������Ԫ����
    paths.add(new BmfFilePath("SO_SaleOrder", "saleorder.bmf")); // ���۶���Ԫ����
    paths.add(new BmfFilePath("saleinvoice", "saleinvoice.bmf")); // ���۷�ƱԪ����
    paths.add(new BmfFilePath(new MulitParentDirs("SO_SaleSquare", "32"),
        "salesquare32.bmf")); // ���۽���Ԫ����

    return paths.toArray(new BmfFilePath[0]);
  }
}
