package nc.vo.so.m30.entity;

import java.io.Serializable;
import java.util.Map;

import nc.vo.bd.feature.ffile.entity.AggFFileVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m35.entity.ArsubViewVO;

public class SaleOrderUserObject implements Serializable {

  private static final long serialVersionUID = -3771119361183908707L;

  private OffsetTempVO offsetTempVO;

  private SoBalanceVO soBalanceVO;

  private ArsubViewVO[] arsubViews;

  private UFDouble thisGatheringMny;
  
  private Map<String, AggFFileVO> aggffilevomap;
  
  
  /**
   * �����Ƿ��ǰ̨���汣�棬�������ⲿ����ƽ̨����ģ����ǵ�Ч�����⣬ǰ̨���汣��ĵ��ݲ�����Ч�ʼ�飩
   */
  private boolean isclientsave=false;

  
  /**
   * @return ��ȡ�����Ƿ��ǰ̨���汣���
   */
  public boolean isIsclientsave() {
    return isclientsave;
  }

  
  /**
   * ���õ����Ƿ��ǰ̨���汣���
   * @param isclientsave
   */
  public void setIsclientsave(boolean isclientsave) {
    this.isclientsave = isclientsave;
  }
  
  
  

  public OffsetTempVO getOffsetTempVO() {
    return this.offsetTempVO;
  }

  public void setOffsetTempVO(OffsetTempVO offsetTempVO) {
    this.offsetTempVO = offsetTempVO;
  }

  public SoBalanceVO getSoBalanceVO() {
    return this.soBalanceVO;
  }

  public void setSoBalanceVO(SoBalanceVO soBalanceVO) {
    this.soBalanceVO = soBalanceVO;
  }

  public ArsubViewVO[] getArsubViews() {
    return this.arsubViews;
  }

  public void setArsubViews(ArsubViewVO[] arsubViews) {
    this.arsubViews = arsubViews;
  }

  public UFDouble getThisGatheringMny() {
    return this.thisGatheringMny;
  }

  public void setThisGatheringMny(UFDouble thisGatheringMny) {
    this.thisGatheringMny = thisGatheringMny;
  }

  public Map<String, AggFFileVO> getAggffilevomap() {
	  return aggffilevomap;
  }

  public void setAggffilevomap(Map<String, AggFFileVO> aggffilevomap) {
	this.aggffilevomap = aggffilevomap;
  }

}
