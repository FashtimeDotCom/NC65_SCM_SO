package nc.pubimpl.so.mbuylargess.pub;

import nc.vo.pub.BusinessException;
import nc.vo.so.mbuylargess.match.BuyLargessMatchPara;
import nc.vo.so.mbuylargess.match.BuyLargessMatchResult;

import nc.pubitf.so.mbuylargess.pub.IBuyLargessMatch;
import nc.pubitf.so.mbuylargess.pub.IBuyLargessMatchPara;

/**
 * ��������ƥ��ʵ����
 * 
 * @since 6.1
 * @version 2012-10-30 18:06:19
 * @author ��ӱ�
 */
public class BuyLargessMatchImpl implements IBuyLargessMatch {

  @Override
  public BuyLargessMatchResult[] matchBuyLargessResult(
      IBuyLargessMatchPara[] imatchparas) throws BusinessException {
    // ת��Ϊ����ƥ���ڲ�����
    BuyLargessMatchPara[] matchparas = this.changeToMatchPara(imatchparas);
    // ����ƥ�䴦����
    BuyLargessResultMatch matchprocess = new BuyLargessResultMatch();
    return matchprocess.matchBuyLargessResult(matchparas);
  }

  private BuyLargessMatchPara[] changeToMatchPara(
      IBuyLargessMatchPara[] ordermatchparas) {
    BuyLargessMatchPara[] matchparas =
        new BuyLargessMatchPara[ordermatchparas.length];
    int i = 0;
    for (IBuyLargessMatchPara orderpara : ordermatchparas) {
      matchparas[i] = new BuyLargessMatchPara();
      matchparas[i].setCsaleorgid(orderpara.getCsaleorgid());
      matchparas[i].setCmaterialid(orderpara.getCmarterialid());
      matchparas[i].setCassunitid(orderpara.getCastunitid());
      matchparas[i].setCcustomerid(orderpara.getCcustomerid());
      matchparas[i].setCcurrtypeid(orderpara.getCorigcurrencyid());
      matchparas[i].setDbilldate(orderpara.getDbilldate());
      matchparas[i].setNbillnum(orderpara.getNastnum());
      i++;
    }
    return matchparas;
  }

  @Override
  public BuyLargessMatchResult[] matchBuyLargessView(
      IBuyLargessMatchPara[] imatchparas) throws BusinessException {
    // ת��Ϊ����ƥ���ڲ�����
    BuyLargessMatchPara[] matchparas = this.changeToMatchPara(imatchparas);
    // ����ƥ�䴦����
    BuyLargessViewMatch matchprocess = new BuyLargessViewMatch();
    return matchprocess.matchBuyLargessResult(matchparas);

  }
}
