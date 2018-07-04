package nc.pubimpl.so.m30.pub;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.bill.rewrite.BillRewriteObject;
import nc.impl.pubapp.bill.rewrite.PfRewriteParam;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.util.SaleOrderVOUtil;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.util.ListUtil;

/**
 * ����ƽ̨��д���۶���ʱ�������ݻ�д��ܵĶ���ת����Ҫ��д�����۶�����ͼVO
 * 
 * @author zhangby5
 *
 */
public class SORewriteParaForPFUtil {

  /**
   * ��ʼ����д���۶�������ͼVO
   * 
   * @param rewriteObjs
   * @return
   */
  public static SaleOrderViewVO[] initRewriteViewVOMap(
      BillRewriteObject rewriteObjs, SaleOrderVO[] vos) {
    List<SaleOrderViewVO> viewVOList = new ArrayList<>();
    SaleOrderViewVO[] viewVOs =
        SaleOrderVOUtil.constructVOToViewVO(vos);
    List<String> rewriteSrcBidList = constructRownoList(rewriteObjs);
    for (SaleOrderViewVO viewVO : viewVOs) {
      String crowno = viewVO.getBody().getCsaleorderbid();
      // �����Bill��ȫVO����Ҫ���˵����û�д����ͼVO
      if (!rewriteSrcBidList.contains(crowno)) {
        continue;
      }
      viewVOList.add(viewVO);
    }
    return ListUtil.toArray(viewVOList);
  }
  
  private static List<String> constructRownoList(BillRewriteObject rewriteObjs) {
    List<String> rewriteSrcBidList = new ArrayList<>();
    PfRewriteParam[] rewriteParas = rewriteObjs.getRewriteParas();
    for (PfRewriteParam rewritePara : rewriteParas) {
      rewriteSrcBidList.add(rewritePara.getTargetVO().getPrimaryKey());
    }
    return rewriteSrcBidList;
  }
  
  public static  RewritePara[] builderRewritePara(BillRewriteObject rewriteObjs) {
    PfRewriteParam[] pfParams = rewriteObjs.getRewriteParas();
    if (pfParams == null || pfParams.length == 0) {
      return new RewritePara[0];
    }
    RewritePara[] rewriteParas = new RewritePara[pfParams.length];
    int i = 0;
    for (PfRewriteParam pfParam : pfParams) {
      RewritePara para = constructPara(pfParam.getTargetVO());
      UFDouble nnum = calRewriteNum(pfParam);
      para.setNnum(nnum);
      rewriteParas[i] = para;
      i++;
    }
    return rewriteParas;
  }

  /**
   * �����д����
   * 
   * @param pfParam
   * @return
   */
  private static UFDouble calRewriteNum(PfRewriteParam pfParam) {
    UFDouble nnum = null;
    ISuperVO srcVO = pfParam.getSrcVO();
    ISuperVO originSrcVO = pfParam.getOriginSrcVO();
    // ��ǰ��ť����Ϊ����ʱ
    if (PfRewriteParam.WRTIE_ACTION.equalsIgnoreCase(pfParam.getActionType())) {
      // ��ԭʼVOΪ�գ�������
      if (originSrcVO == null) {
        nnum = (UFDouble) srcVO.getAttributeValue(SOItemKey.NNUM);
      }
      // ����ǰVOΪ�գ�ɾ����
      else if (srcVO == null) {
        nnum = MathTool.oppose((UFDouble) originSrcVO.getAttributeValue(SOItemKey.NNUM));
      }
      // �޸���
      else {
        nnum =
            MathTool.sub((UFDouble) srcVO.getAttributeValue(SOItemKey.NNUM),
                (UFDouble) (originSrcVO.getAttributeValue(SOItemKey.NNUM)));
      }
    }
    // ��ť����Ϊɾ��ʱ
    else {
      nnum =
          MathTool.oppose((UFDouble) originSrcVO
              .getAttributeValue(SOItemKey.NNUM));
    }
    return nnum;
  }

  /**
   * ���ݻ�дĿ��VO��֯��д����
   * 
   * @param vo
   * @return
   */
  private static RewritePara constructPara(ISuperVO vo) {
    RewritePara para = new RewritePara();
    String vsrctype = SOBillType.Order.getCode();
    String csrcid = (String) vo.getAttributeValue(SaleOrderBVO.CSALEORDERID);
    String csrcbid = (String) vo.getPrimaryKey();
    para.setVsrctype(vsrctype);
    para.setCsrcid(csrcid);
    para.setCsrcbid(csrcbid);
    return para;
  }

}
