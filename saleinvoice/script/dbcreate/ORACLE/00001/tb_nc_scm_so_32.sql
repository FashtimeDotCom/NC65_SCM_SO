/* tablename: ��Ʊ�������� */
create table so_m32trantype (pk_trantype char(20) not null 
/*��Ʊ������������*/,
ctrantypeid varchar2(20) null 
/*��������*/,
pk_group varchar2(20) null 
/*����ID*/,
vtrantypecode varchar2(20) null 
/*�������ͱ���*/,
fadjuster smallint null 
/*������*/,
 constraint pk_m32trantype primary key (pk_trantype),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۷�Ʊ�ӱ� */
create table so_saleinvoice_b (csaleinvoicebid char(20) not null 
/*��Ʊ�ӱ�ID*/,
csaleinvoiceid varchar2(20) null 
/*��Ʊ����ID*/,
pk_group varchar2(20) null 
/*����*/,
pk_org varchar2(20) null 
/*��Ʊ��֯*/,
dbilldate char(19) null 
/*��Ʊ����*/,
crowno varchar2(20) null 
/*�к�*/,
ccustmaterialid varchar2(20) null 
/*�ͻ�������*/,
cmaterialid varchar2(20) null 
/*����*/,
cmaterialvid varchar2(20) null 
/*���ϰ汾*/,
cvendorid varchar2(20) null 
/*��Ӧ��*/,
cprojectid varchar2(20) null 
/*��Ŀ*/,
cproductorid varchar2(20) null 
/*��������*/,
cunitid varchar2(20) null 
/*����λ*/,
castunitid varchar2(20) null 
/*��λ*/,
vchangerate varchar2(60) null 
/*������*/,
nnum number(28,8) null 
/*������*/,
nastnum number(28,8) null 
/*����*/,
cqtunitid varchar2(20) null 
/*���۵�λ*/,
vqtunitrate varchar2(60) null 
/*���۵�λ������*/,
nqtunitnum number(28,8) null 
/*��������*/,
bdiscountflag char(1) null 
/*�ۿ���*/,
blaborflag char(1) null 
/*������*/,
blargessflag char(1) null 
/*��Ʒ*/,
pk_batchcode varchar2(20) null 
/*���ε���*/,
vbatchcode varchar2(40) null 
/*���κ�*/,
ntaxrate number(28,8) null 
/*˰��*/,
ndiscountrate number(28,8) null 
/*�����ۿ�*/,
nitemdiscountrate number(28,8) null 
/*��Ʒ�ۿ�*/,
ninvoicedisrate number(28,8) null 
/*��Ʊ�ۿ�*/,
norigtaxprice number(20,8) null 
/*����˰����*/,
norigprice number(28,8) null 
/*����˰����*/,
norigtaxnetprice number(28,8) null 
/*����˰����*/,
norignetprice number(28,8) null 
/*����˰����*/,
ntaxprice number(28,8) null 
/*�����Һ�˰����*/,
nprice number(28,8) null 
/*��������˰����*/,
ntaxnetprice number(28,8) null 
/*�����Һ�˰����*/,
nnetprice number(28,8) null 
/*��������˰����*/,
nqtorigtaxprice number(28,8) null 
/*��˰����*/,
nqtorigprice number(28,8) null 
/*��˰����*/,
nqtorigtaxnetprc number(28,8) null 
/*��˰����*/,
nqtorignetprice number(28,8) null 
/*��˰����*/,
nqttaxprice number(28,8) null 
/*���Һ�˰����*/,
nqtprice number(28,8) null 
/*������˰����*/,
nqttaxnetprice number(28,8) null 
/*���Һ�˰����*/,
nqtnetprice number(28,8) null 
/*������˰����*/,
ntax number(28,8) null 
/*����˰��*/,
nmny number(28,8) null 
/*������˰���*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount number(28,8) null 
/*�����ۿ۶�*/,
norigmny number(28,8) null 
/*��˰���*/,
norigtaxmny number(28,8) null 
/*��˰�ϼ�*/,
norigdiscount number(28,8) null 
/*�ۿ۶�*/,
norigsubmny number(28,8) null 
/*��ֽ��*/,
ngroupmny number(28,8) null 
/*���ű�����˰���*/,
ngrouptaxmny number(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
nglobalmny number(28,8) null 
/*ȫ�ֱ�����˰���*/,
nglobaltaxmny number(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
vfirsttype varchar2(20) null 
/*Դͷ��������*/,
vfirstcode varchar2(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype varchar2(20) null 
/*Դͷ��������*/,
vfirstrowno varchar2(20) null 
/*Դͷ�����к�*/,
cfirstid varchar2(20) null 
/*Դͷ���ݱ�ͷID*/,
cfirstbid varchar2(20) null 
/*Դͷ���ݱ���ID*/,
vsrctype varchar2(20) null 
/*��Դ��������*/,
vsrccode varchar2(40) null 
/*��Դ���ݺ�*/,
vsrctrantype varchar2(20) null 
/*��Դ��������*/,
vsrcrowno varchar2(20) null 
/*��Դ�����к�*/,
csrcid varchar2(20) null 
/*��Դ���ݱ�ͷID*/,
csrcbid varchar2(20) null 
/*��Դ���ݱ���ID*/,
copposesrcbid varchar2(20) null 
/*�Գ���Դ����ID*/,
csaleorgid varchar2(20) null 
/*������֯*/,
csaleorgvid varchar2(20) null 
/*������֯�汾*/,
cprofitcenterid varchar2(20) null 
/*������������*/,
cprofitcentervid varchar2(20) null 
/*�����������İ汾*/,
carorgid varchar2(20) null 
/*Ӧ����֯*/,
carorgvid varchar2(20) null 
/*Ӧ����֯�汾*/,
cordercustid varchar2(20) null 
/*�����ͻ�*/,
bfreecustflag char(1) null 
/*�Ƿ�ɢ��*/,
cfreecustid varchar2(20) null 
/*ɢ��*/,
cdeptid varchar2(20) null 
/*���۲���*/,
cdeptvid varchar2(20) null 
/*���۲��Ű汾*/,
cemployeeid varchar2(20) null 
/*����ҵ��Ա*/,
creceivecustid varchar2(20) null 
/*�ջ��ͻ�*/,
creceiveaddrid varchar2(20) null 
/*�ջ���ַ*/,
ctransporttypeid varchar2(20) null 
/*���䷽ʽ*/,
csendstockorgid varchar2(20) null 
/*�����֯*/,
csendstockorgvid varchar2(20) null 
/*�����֯�汾*/,
csendstordocid varchar2(20) null 
/*�ֿ�*/,
cprodlineid varchar2(20) null 
/*��Ʒ��*/,
ccostsubjid varchar2(20) null 
/*��֧��Ŀ*/,
cctmanageid varchar2(20) null 
/*��ͬ*/,
vsumcode varchar2(40) null 
/*���Ļ��ܺ�*/,
csumid varchar2(20) null 
/*���Ļ�������*/,
cvmivenderid varchar2(20) null 
/*�Ĵ湩Ӧ��*/,
nshouldoutnum number(28,8) null 
/*�ۼ�Ӧ��δ��������*/,
ntotaloutnum number(28,8) null 
/*�ۼƳ�������*/,
ntotalincomenum number(28,8) null 
/*�ۼ�ȷ��Ӧ������*/,
ntotalincomemny number(28,8) null 
/*�ۼ�ȷ��Ӧ�ս��*/,
ntotalcostnum number(28,8) null 
/*�ۼƳɱ���������*/,
ntotalpaymny number(28,8) null 
/*�ۼ��տ���*/,
vrownote varchar2(181) null 
/*��ע*/,
vfree1 varchar2(101) null 
/*���ϸ�������1*/,
vfree2 varchar2(101) null 
/*���ϸ�������2*/,
vfree3 varchar2(101) null 
/*���ϸ�������3*/,
vfree4 varchar2(101) null 
/*���ϸ�������4*/,
vfree5 varchar2(101) null 
/*���ϸ�������5*/,
vfree6 varchar2(101) null 
/*���ϸ�������6*/,
vfree7 varchar2(101) null 
/*���ϸ�������7*/,
vfree8 varchar2(101) null 
/*���ϸ�������8*/,
vfree9 varchar2(101) null 
/*���ϸ�������9*/,
vfree10 varchar2(101) null 
/*���ϸ�������10*/,
vbdef1 varchar2(101) null 
/*�Զ�����1*/,
vbdef2 varchar2(101) null 
/*�Զ�����2*/,
vbdef3 varchar2(101) null 
/*�Զ�����3*/,
vbdef4 varchar2(101) null 
/*�Զ�����4*/,
vbdef5 varchar2(101) null 
/*�Զ�����5*/,
vbdef6 varchar2(101) null 
/*�Զ�����6*/,
vbdef7 varchar2(101) null 
/*�Զ�����7*/,
vbdef8 varchar2(101) null 
/*�Զ�����8*/,
vbdef9 varchar2(101) null 
/*�Զ�����9*/,
vbdef10 varchar2(101) null 
/*�Զ�����10*/,
vbdef11 varchar2(101) null 
/*�Զ�����11*/,
vbdef12 varchar2(101) null 
/*�Զ�����12*/,
vbdef13 varchar2(101) null 
/*�Զ�����13*/,
vbdef14 varchar2(101) null 
/*�Զ�����14*/,
vbdef15 varchar2(101) null 
/*�Զ�����15*/,
vbdef16 varchar2(101) null 
/*�Զ�����16*/,
vbdef17 varchar2(101) null 
/*�Զ�����17*/,
vbdef18 varchar2(101) null 
/*�Զ�����18*/,
vbdef19 varchar2(101) null 
/*�Զ�����19*/,
vbdef20 varchar2(101) null 
/*�Զ�����20*/,
ctaxcodeid varchar2(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny number(28,8) null 
/*��˰���*/,
cchanneltypeid varchar2(20) null 
/*������������*/,
csprofitcentervid varchar2(20) null 
/*������������*/,
csprofitcenterid varchar2(20) null 
/*���������������°汾*/,
cmffileid varchar2(20) null 
/*������*/,
 constraint pk_saleinvoice_b primary key (csaleinvoicebid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۷�Ʊ���� */
create table so_saleinvoice (csaleinvoiceid char(20) not null 
/*��Ʊ����ID*/,
pk_group varchar2(20) null 
/*����*/,
pk_org varchar2(20) null 
/*��Ʊ��֯*/,
pk_org_v varchar2(20) null 
/*��Ʊ��֯�汾*/,
vbillcode varchar2(40) null 
/*��Ʊ��*/,
cbiztypeid varchar2(20) null 
/*ҵ������*/,
ctrantypeid varchar2(20) null 
/*��Ʊ����*/,
vtrantypecode varchar2(20) null 
/*��Ʊ���ͱ���*/,
cinvoicecustid varchar2(20) null 
/*��Ʊ�ͻ�*/,
dbilldate char(19) null 
/*��Ʊ����*/,
vprintcustname varchar2(50) null 
/*�ͻ���ӡ����*/,
ccustbankid varchar2(20) null 
/*�ͻ���������*/,
ccustbankaccid varchar2(20) null 
/*�ͻ������˺�*/,
cpaytermid varchar2(20) null 
/*�ո���Э��*/,
vcreditnum varchar2(20) null 
/*����֤��*/,
vgoldtaxcode varchar2(100) null 
/*��˰Ʊ��*/,
btogoldtaxflag char(1) null 
/*�Ƿ��Ѿ�����˰*/,
tgoldtaxtime varchar2(19) null 
/*��󴫽�˰ʱ��*/,
corigcurrencyid varchar2(20) null 
/*ԭ��*/,
nexchangerate number(28,8) null 
/*�۱�����*/,
ccurrencyid varchar2(20) null 
/*���ұ���*/,
ngroupexchgrate number(28,8) null 
/*���ű�λ�һ���*/,
nglobalexchgrate number(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
nhvoicedisrate number(28,8) null 
/*��Ʊ�ۿ�*/,
ntotalastnum number(28,8) null 
/*������*/,
ntotalorigmny number(28,8) null 
/*��˰�ϼ�*/,
ntotalorigsubmny number(28,8) null 
/*��ֽ��*/,
bsubunitflag char(1) null 
/*��ֱ��*/,
fopposeflag integer null 
/*�Գ���*/,
vopposesrccode varchar2(40) null 
/*�Գ���Դ��Ʊ��*/,
copposesrcid varchar2(20) null 
/*�Գ���Դ��ͷID*/,
vnote varchar2(181) null 
/*��ע*/,
fstatusflag integer null 
/*����״̬*/,
creator varchar2(20) null 
/*������*/,
billmaker varchar2(20) null 
/*�Ƶ���*/,
creationtime char(19) null 
/*����ʱ��*/,
modifier varchar2(20) null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
approver varchar2(20) null 
/*������*/,
taudittime varchar2(19) null 
/*�������*/,
iprintcount integer default 0 null 
/*��ӡ����*/,
vdef1 varchar2(101) null 
/*�Զ�����1*/,
vdef2 varchar2(101) null 
/*�Զ�����2*/,
vdef3 varchar2(101) null 
/*�Զ�����3*/,
vdef4 varchar2(101) null 
/*�Զ�����4*/,
vdef5 varchar2(101) null 
/*�Զ�����5*/,
vdef6 varchar2(101) null 
/*�Զ�����6*/,
vdef7 varchar2(101) null 
/*�Զ�����7*/,
vdef8 varchar2(101) null 
/*�Զ�����8*/,
vdef9 varchar2(101) null 
/*�Զ�����9*/,
vdef10 varchar2(101) null 
/*�Զ�����10*/,
vdef11 varchar2(101) null 
/*�Զ�����11*/,
vdef12 varchar2(101) null 
/*�Զ�����12*/,
vdef13 varchar2(101) null 
/*�Զ�����13*/,
vdef14 varchar2(101) null 
/*�Զ�����14*/,
vdef15 varchar2(101) null 
/*�Զ�����15*/,
vdef16 varchar2(101) null 
/*�Զ�����16*/,
vdef17 varchar2(101) null 
/*�Զ�����17*/,
vdef18 varchar2(101) null 
/*�Զ�����18*/,
vdef19 varchar2(101) null 
/*�Զ�����19*/,
vdef20 varchar2(101) null 
/*�Զ�����20*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
crececountryid varchar2(20) null 
/*�ջ�����/����*/,
csendcountryid varchar2(20) null 
/*��������/����*/,
ctaxcountryid varchar2(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
ctradewordid varchar2(20) null 
/*ó������*/,
vvatcode varchar2(40) null 
/*VATע����*/,
vcustvatcode varchar2(40) null 
/*�ͻ�VATע����*/,
cbalancetypeid varchar2(20) null 
/*���㷽ʽ*/,
 constraint pk_saleinvoice primary key (csaleinvoiceid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

