package nc.bs.so.m30.state.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.ct.entity.CtWriteBackForOrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.util.SOSysParaInitUtil;

import nc.pubitf.ct.saledaily.so.IReWriteZ3For30;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * ���۶����в���(�رա���)��д���ۺ�ͬrule
 * @scene
 * ���۶����в���(�رա���)��
 * @param
 * bIsClose �Ƿ��йر�
 * mParas ����仯������
 * @since 6.0
 * @version 2011-6-20 ����09:49:43
 * @author ô �� ��
 */
public class RowStateReWriteZ3Rule implements IRule<SaleOrderViewVO> {

  private boolean bIsClose;

  private Map<String, Rewrite4CPara> mParas;

  /**
   * @param bIsClose �Ƿ��йر�
   */
  public RowStateReWriteZ3Rule(boolean bIsClose) {
    this.bIsClose = bIsClose;
    Map<String, Rewrite4CPara> mParass =
        (Map<String, Rewrite4CPara>) BSContext.getInstance().getSession(
            Rewrite4CPara.class.getName());
    if (mParass != null) {
      this.mParas = mParass;
    }
  }

  /**
   * ������for�����д���۶���ʱ
   * 
   * @param bIsClose �Ƿ��йر�
   * @param mParas ����仯������
   */
  public RowStateReWriteZ3Rule(boolean bIsClose,
      Map<String, Rewrite4CPara> mParas) {
    this.bIsClose = bIsClose;
    this.mParas = mParas;
  }

  @Override
  public void process(SaleOrderViewVO[] vos) {
    this.fillCtType(vos);
    Map<String, UFBoolean> so10map = new HashMap<String, UFBoolean>();
    List<CtWriteBackForOrderVO> paralist =
        new ArrayList<CtWriteBackForOrderVO>();
    for (SaleOrderViewVO viewvo : vos) {
      SaleOrderBVO bvo = viewvo.getBody();
      SaleOrderHVO hvo = viewvo.getHead();
      if (CTBillType.SaleDaily.getCode().equals(bvo.getVcttype())) {
        String pk_org = bvo.getPk_org();
        UFBoolean so10 = so10map.get(pk_org);
        if (null == so10) {
          so10 = this.getSO10(pk_org);
          so10map.put(pk_org, so10);
        }
        if (so10.booleanValue()) {
          CtWriteBackForOrderVO paras = new CtWriteBackForOrderVO();
          // paras.setPk_ctpu(bvo.getCsrcid());
          // paras.setPk_ctpu_b(bvo.getCsrcbid());
          // 2013-04-26 tianft��������۶���һ����Դ���ۺ�ͬ��һ�й������ۺ�ͬ����srcid�ǲ��Եģ�Ҫ��manageid
          paras.setPk_ctpu(bvo.getCctmanageid());
          paras.setPk_ctpu_b(bvo.getCctmanagebid());
          paras.setcRowNum(bvo.getCrowno());
          if (this.bIsClose) {
            // �йر���Ϊ�����д���۶���ʱ
            if (this.mParas != null) {
              Rewrite4CPara m4CPara = this.mParas.get(bvo.getCsaleorderbid());
              UFDouble changenum = m4CPara.getNchangenum();
              paras.setNum(changenum);
            }
            // �йر�ʱ
            else {
              paras.setNum(MathTool.sub(bvo.getNtotaloutnum(), bvo.getNnum()));
            }
          }
          // �д�ʱ
          else {
            // �д���Ϊ�����д���۶���ʱ
            if (this.mParas != null) {
              Rewrite4CPara m4CPara = this.mParas.get(bvo.getCsaleorderbid());
              UFDouble changenum = m4CPara.getNchangenum();
              paras.setNum(changenum);
            }
            else {
              paras.setNum(MathTool.sub(bvo.getNnum(), bvo.getNtotaloutnum()));
            }
          }
          paras.setPrice(bvo.getNorigtaxprice());

          UFDouble norigtaxmny =
              bvo.getNorigtaxnetprice().multiply(paras.getNum());
          ScaleUtils scale = new ScaleUtils(pk_org);
          UFDouble norigtaxmnyAfterSacle =
              scale.adjustMnyScale(norigtaxmny, hvo.getCorigcurrencyid());
          paras.setPriceNum(norigtaxmnyAfterSacle);
          paralist.add(paras);
        }
      }
    }
    if (paralist.size() > 0) {
      CtWriteBackForOrderVO[] paras =
          paralist.toArray(new CtWriteBackForOrderVO[paralist.size()]);

      IReWriteZ3For30 api =
          NCLocator.getInstance().lookup(IReWriteZ3For30.class);
      try {
        api.rewriteBackZ3For30(paras);
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
    }
  }

  private void fillCtType(SaleOrderViewVO[] vos) {
    for (SaleOrderViewVO vo : vos) {
      SaleOrderBVO body = vo.getBody();
      if (body.getCctmanagebid() != null
          && body.getCctmanagebid().trim().length() > 0) {
        body.setVcttype(CTBillType.SaleDaily.getCode());
      }

    }
  }

  private UFBoolean getSO10(String pk_org) {
    UFBoolean ret = UFBoolean.FALSE;

    ret = SOSysParaInitUtil.getSO10(pk_org);

    return ret;
  }
}
