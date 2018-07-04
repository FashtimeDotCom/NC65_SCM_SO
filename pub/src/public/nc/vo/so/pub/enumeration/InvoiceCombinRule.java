package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

public enum InvoiceCombinRule {
  CCUSTMATERIALID(SaleInvoiceBVO.CCUSTMATERIALID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0227")/*�ͻ�������*/),
  // 1
  CMARBASCALSSID(SaleInvoiceBVO.CMARBASCALSSID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0093")/*���ϻ�������*/),
  // 2
  CMATERIALID(SaleInvoiceBVO.CMATERIALID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0094")/*�������°汾*/),
  CMATERIALVID(SaleInvoiceBVO.CMATERIALVID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0095")/*���ϱ���*/),
  CASTUNITID(SaleInvoiceBVO.CASTUNITID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0034")/*��λ*/),
  CUNITID(SaleInvoiceBVO.CUNITID, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0033")/*����λ*/),
  CQTUNITID(SaleInvoiceBVO.CQTUNITID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0103")/*���۵�λ*/),
  BDISCOUNTFLAG(SaleInvoiceBVO.BDISCOUNTFLAG, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0080")/*�ۿ���*/),
  BFREECUSTFLAG(SaleInvoiceBVO.BFREECUSTFLAG, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0081")/*�Ƿ�ɢ��*/),
  BLABORFLAG(SaleInvoiceBVO.BLABORFLAG, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0082")/*������*/),
  BLARGESSFLAG(SaleInvoiceBVO.BLARGESSFLAG, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0083")/*��Ʒ*/),
  CPRODLINEID(SaleInvoiceBVO.CPRODLINEID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0098")/*��Ʒ��*/),
  PK_BATCHCODE(SaleInvoiceBVO.PK_BATCHCODE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0150")/*���ε���*/),
  VBATCHCODE(SaleInvoiceBVO.VBATCHCODE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0151")/*���κ�*/),
  CCTMANAGEID(SaleInvoiceBVO.CCTMANAGEID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0086")/*��ͬ��*/),
  CDEPTID(SaleInvoiceBVO.CDEPTID, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0087")/*���۲������°汾*/),
  CDEPTVID(SaleInvoiceBVO.CDEPTVID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0088")/*���۲���*/),
  CEMPLOYEEID(SaleInvoiceBVO.CEMPLOYEEID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0089")/*����ҵ��Ա*/),
  CTRANSPORTTYPEID(SaleInvoiceBVO.CTRANSPORTTYPEID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0113")/*���䷽ʽ*/),
  COPPOSESRCBID(SaleInvoiceBVO.COPPOSESRCBID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0096")/*�Գ���Դ�ӱ�*/),
  CORDERCUSTID(SaleInvoiceBVO.CORDERCUSTID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0097")/*�����ͻ�*/),
  CFREECUSTID(SaleInvoiceBVO.CFREECUSTID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0092")/*ɢ��*/),
  CCOSTSUBJID(SaleInvoiceBVO.CCOSTSUBJID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0085")/*��֧��Ŀ*/),
  CPRODUCTORID(SaleInvoiceBVO.CPRODUCTORID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0099")/*��������*/),
  CVENDORID(SaleInvoiceBVO.CVENDORID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0114")/*��Ӧ��*/),
  CVMIVENDERID(SaleInvoiceBVO.CVMIVENDERID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0115")/*�Ĵ湩Ӧ��*/),
  CPROFITCENTERID(SaleInvoiceBVO.CPROFITCENTERID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0100")/*�����������°汾*/),
  CPROFITCENTERVID(SaleInvoiceBVO.CPROFITCENTERVID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0101")/*��������*/),
  CPROJECTID(SaleInvoiceBVO.CPROJECTID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0102")/*��Ŀ*/),

  CRECEIVEADDRID(SaleInvoiceBVO.CRECEIVEADDRID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0104")/*�ջ���ַ*/),
  CRECEIVECUSTID(SaleInvoiceBVO.CRECEIVECUSTID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0051")/*�ջ��ͻ�*/),
  CSALEORGID(SaleInvoiceBVO.CSALEORGID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0105")/*������֯���°汾*/),
  CSALEORGVID(SaleInvoiceBVO.CSALEORGVID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0106")/*������֯*/),
  CARORGID(SaleInvoiceBVO.CARORGID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0084")/*Ӧ����֯���°汾*/),
  CARORGVID(SaleInvoiceBVO.CARORGVID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0041")/*Ӧ����֯*/),
  CSENDSTOCKORGID(SaleInvoiceBVO.CSENDSTOCKORGID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0107")/*�����֯���°汾*/),
  CSENDSTOCKORGVID(SaleInvoiceBVO.CSENDSTOCKORGVID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0108")/*�����֯*/),
  CSENDSTORDOCID(SaleInvoiceBVO.CSENDSTORDOCID, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0109")/*�ֿ�*/),
  CFIRSTBID(SaleInvoiceBVO.CFIRSTBID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0090")/*Դͷ�����ӱ�*/),
  CFIRSTID(SaleInvoiceBVO.CFIRSTID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0091")/*Դͷ��������*/),
  CSRCBID(SaleInvoiceBVO.CSRCBID, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0110")/*��Դ�����ӱ�*/),
  CSRCID(SaleInvoiceBVO.CSRCID, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0111")/*��Դ��������*/),
  VSRCCODE(SaleInvoiceBVO.VSRCCODE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0169")/*��Դ���ݺ�*/),
  VSRCROWNO(SaleInvoiceBVO.VSRCROWNO, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0170")/*��Դ�����к�*/),
  VSRCTRANTYPE(SaleInvoiceBVO.VSRCTRANTYPE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0171")/*��Դ��������*/),
  VSRCTYPE(SaleInvoiceBVO.VSRCTYPE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0172")/*��Դ��������*/),
  CSUMID(SaleInvoiceBVO.CSUMID, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0112")/*���Ļ�������*/),

  NASTNUM(SaleInvoiceBVO.NASTNUM, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0116")/*����*/),
  NBFORIGSUBMNY("nbforigsubmny", NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0117")/*���ǰ���*/),
  // NCOSTMNY(SaleInvoiceBVO.NCOSTMNY, NCLangRes4VoTransl.getNCLangRes()
  // .getStrByID("4006004_0", "04006004-0118")/*�ɱ����*/),
  NDISCOUNT(SaleInvoiceBVO.NDISCOUNT, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0119")/*�����ۿ۶�*/),
  NDISCOUNTRATE(SaleInvoiceBVO.NDISCOUNTRATE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0036")/*�����ۿ�*/),
  NGLOBALMNY(SaleInvoiceBVO.NGLOBALMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0120")/*ȫ�ֱ�����˰���*/),
  NGLOBALTAXMNY(SaleInvoiceBVO.NGLOBALTAXMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0121")/*ȫ�ֱ��Ҽ�˰�ϼ�*/),
  NGROUPMNY(SaleInvoiceBVO.NGROUPMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0122")/*���ű�����˰���*/),
  NGROUPTAXMNY(SaleInvoiceBVO.NGROUPTAXMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0123")/*���ű��Ҽ�˰�ϼ�*/),
  NINVOICEDISRATE(SaleInvoiceBVO.NINVOICEDISRATE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0037")/*��Ʊ�ۿ�*/),
  NITEMDISCOUNTRATE(SaleInvoiceBVO.NITEMDISCOUNTRATE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0038")/*��Ʒ�ۿ�*/),

  NMNY(SaleInvoiceBVO.NMNY, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0124")/*������˰���*/),
  NNETPRICE(SaleInvoiceBVO.NNETPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0125")/*��������˰����*/),
  NNUM(SaleInvoiceBVO.NNUM, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0126")/*������*/),
  NORIGDISCOUNT(SaleInvoiceBVO.NORIGDISCOUNT, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0127")/*�ۿ۶�*/),
  NORIGMNY(SaleInvoiceBVO.NORIGMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0128")/*��˰���*/),
  NORIGNETPRICE(SaleInvoiceBVO.NORIGNETPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0129")/*����˰����*/),
  NORIGPRICE(SaleInvoiceBVO.NORIGPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0130")/*����˰����*/),
  NORIGSUBMNY(SaleInvoiceBVO.NORIGSUBMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0131")/*���ó�ֽ��*/),
  CTAXCODEID(SaleInvoiceBVO.CTAXCODEID, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0132")/*˰��*/),
  FTAXTYPEFLAG(SaleInvoiceBVO.FTAXTYPEFLAG, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0226")/*��˰���*/),

  NORIGTAXMNY(SaleInvoiceBVO.NORIGTAXMNY, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0133")/*��˰�ϼ�*/),
  NORIGTAXNETPRICE(SaleInvoiceBVO.NORIGTAXNETPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0134")/*����˰����*/),
  NORIGTAXPRICE(SaleInvoiceBVO.NORIGTAXPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0135")/*����˰����*/),
  NPRICE(SaleInvoiceBVO.NPRICE, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0136")/*��������˰����*/),

  NQTNETPRICE(SaleInvoiceBVO.NQTNETPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0137")/*������˰����*/),
  NQTORIGNETPRICE(SaleInvoiceBVO.NQTORIGNETPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0138")/*��˰����*/),
  NQTORIGPRICE(SaleInvoiceBVO.NQTORIGPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0139")/*��˰����*/),
  NQTORIGTAXNETPRC(SaleInvoiceBVO.NQTORIGTAXNETPRC, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0140")/*��˰����*/),

  NQTORIGTAXPRICE(SaleInvoiceBVO.NQTORIGTAXPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0141")/*��˰����*/),
  NQTPRICE(SaleInvoiceBVO.NQTPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0142")/*������˰����*/),
  NQTTAXNETPRICE(SaleInvoiceBVO.NQTTAXNETPRICE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0143")/*���Һ�˰����*/),
  NQTTAXPRICE(SaleInvoiceBVO.NQTTAXPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0144")/*���Һ�˰����*/),

  NQTUNITNUM(SaleInvoiceBVO.NQTUNITNUM, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0145")/*��������*/),
  NTAX(SaleInvoiceBVO.NTAX, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0146")/*����˰��*/),
  NTAXMNY(SaleInvoiceBVO.NTAXMNY, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0147")/*���Ҽ�˰�ϼ�*/),
  NTAXNETPRICE(SaleInvoiceBVO.NTAXNETPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0148")/*�����Һ�˰����*/),

  NTAXPRICE(SaleInvoiceBVO.NTAXPRICE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0149")/*�����Һ�˰����*/),
  NTAXRATE(SaleInvoiceBVO.NTAXRATE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0035")/*˰��*/),

  VBDEF1(SaleInvoiceBVO.VBDEF1, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0061")/*�Զ�����1*/),
  VBDEF2(SaleInvoiceBVO.VBDEF2, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0072")/*�Զ�����2*/),
  VBDEF20(SaleInvoiceBVO.VBDEF20, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0073")/*�Զ�����20*/),
  VBDEF3(SaleInvoiceBVO.VBDEF3, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0074")/*�Զ�����3*/),
  VBDEF4(SaleInvoiceBVO.VBDEF4, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0075")/*�Զ�����4*/),
  VBDEF5(SaleInvoiceBVO.VBDEF5, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0076")/*�Զ�����5*/),
  VBDEF6(SaleInvoiceBVO.VBDEF6, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0077")/*�Զ�����6*/),
  VBDEF7(SaleInvoiceBVO.VBDEF7, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0078")/*�Զ�����7*/),
  VBDEF8(SaleInvoiceBVO.VBDEF8, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0079")/*�Զ�����8*/),
  VBDEF9(SaleInvoiceBVO.VBDEF9, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0225")/*�Զ�����9*/),
  VBDEF10(SaleInvoiceBVO.VBDEF10, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0062")/*�Զ�����10*/),
  VBDEF11(SaleInvoiceBVO.VBDEF11, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0063")/*�Զ�����11*/),
  VBDEF12(SaleInvoiceBVO.VBDEF12, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0064")/*�Զ�����12*/),

  VBDEF13(SaleInvoiceBVO.VBDEF13, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0065")/*�Զ�����13*/),
  VBDEF14(SaleInvoiceBVO.VBDEF14, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0066")/*�Զ�����14*/),
  VBDEF15(SaleInvoiceBVO.VBDEF15, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0067")/*�Զ�����15*/),
  VBDEF16(SaleInvoiceBVO.VBDEF16, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0068")/*�Զ�����16*/),
  VBDEF17(SaleInvoiceBVO.VBDEF17, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0069")/*�Զ�����17*/),
  VBDEF18(SaleInvoiceBVO.VBDEF18, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0070")/*�Զ�����18*/),
  VBDEF19(SaleInvoiceBVO.VBDEF19, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0071")/*�Զ�����19*/),

  VCHANGERATE(SaleInvoiceBVO.VCHANGERATE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0152")/*������*/),
  VFIRSTCODE(SaleInvoiceBVO.VFIRSTCODE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0153")/*Դͷ���ݺ�*/),
  VFIRSTROWNO(SaleInvoiceBVO.VFIRSTROWNO, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0154")/*Դͷ�����к�*/),
  VFIRSTTRANTYPE(SaleInvoiceBVO.VFIRSTTRANTYPE, NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4006004_0", "04006004-0155")/*Դͷ��������*/),
  VFIRSTTYPE(SaleInvoiceBVO.VFIRSTTYPE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0156")/*Դͷ��������*/),
  VFREE1(SaleInvoiceBVO.VFREE1, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0157")/*���ɸ�������1*/),

  VFREE2(SaleInvoiceBVO.VFREE2, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0159")/*���ɸ�������2*/),
  VFREE3(SaleInvoiceBVO.VFREE3, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0160")/*���ɸ�������3*/),
  VFREE4(SaleInvoiceBVO.VFREE4, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0161")/*���ɸ�������4*/),
  VFREE5(SaleInvoiceBVO.VFREE5, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0162")/*���ɸ�������5*/),
  VFREE6(SaleInvoiceBVO.VFREE6, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0163")/*���ɸ�������6*/),
  VFREE7(SaleInvoiceBVO.VFREE7, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0164")/*���ɸ�������7*/),
  VFREE8(SaleInvoiceBVO.VFREE8, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0165")/*���ɸ�������8*/),
  VFREE9(SaleInvoiceBVO.VFREE9, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0166")/*���ɸ�������9*/),
  VFREE10(SaleInvoiceBVO.VFREE10, NCLangRes4VoTransl.getNCLangRes().getStrByID(
      "4006004_0", "04006004-0158")/*���ɸ�������10*/),
  VQTUNITRATE(SaleInvoiceBVO.VQTUNITRATE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0167")/*���۵�λ������*/),
  VROWNOTE(SaleInvoiceBVO.VROWNOTE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0168")/*��ע*/),

  VSUMCODE(SaleInvoiceBVO.VSUMCODE, NCLangRes4VoTransl.getNCLangRes()
      .getStrByID("4006004_0", "04006004-0173")/*���Ļ��ܺ�*/);

  // ��������
  private String key;

  // ��������
  private String name;

  private InvoiceCombinRule(String key, String name) {
    this.key = key;
    this.name = name;
  }

  public String getKey() {
    return this.key;
  }

  public String getName() {
    return this.name;
  }

}
