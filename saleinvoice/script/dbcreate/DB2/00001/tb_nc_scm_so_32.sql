/* tablename: ��Ʊ�������� */
create table so_m32trantype (pk_trantype char(20) not null 
/*��Ʊ������������*/,
ctrantypeid varchar(20) null 
/*��������*/,
pk_group varchar(20) null 
/*����ID*/,
vtrantypecode varchar(20) null 
/*�������ͱ���*/,
fadjuster smallint null 
/*������*/,
 constraint pk_m32trantype primary key (pk_trantype),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۷�Ʊ�ӱ� */
create table so_saleinvoice_b (csaleinvoicebid char(20) not null 
/*��Ʊ�ӱ�ID*/,
csaleinvoiceid varchar(20) null 
/*��Ʊ����ID*/,
pk_group varchar(20) null 
/*����*/,
pk_org varchar(20) null 
/*��Ʊ��֯*/,
dbilldate char(19) null 
/*��Ʊ����*/,
crowno varchar(20) null 
/*�к�*/,
ccustmaterialid varchar(20) null 
/*�ͻ�������*/,
cmaterialid varchar(20) null 
/*����*/,
cmaterialvid varchar(20) null 
/*���ϰ汾*/,
cvendorid varchar(20) null 
/*��Ӧ��*/,
cprojectid varchar(20) null 
/*��Ŀ*/,
cproductorid varchar(20) null 
/*��������*/,
cunitid varchar(20) null 
/*����λ*/,
castunitid varchar(20) null 
/*��λ*/,
vchangerate varchar(60) null 
/*������*/,
nnum decimal(28,8) null 
/*������*/,
nastnum decimal(28,8) null 
/*����*/,
cqtunitid varchar(20) null 
/*���۵�λ*/,
vqtunitrate varchar(60) null 
/*���۵�λ������*/,
nqtunitnum decimal(28,8) null 
/*��������*/,
bdiscountflag char(1) null 
/*�ۿ���*/,
blaborflag char(1) null 
/*������*/,
blargessflag char(1) null 
/*��Ʒ*/,
pk_batchcode varchar(20) null 
/*���ε���*/,
vbatchcode varchar(40) null 
/*���κ�*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ�*/,
ninvoicedisrate decimal(28,8) null 
/*��Ʊ�ۿ�*/,
norigtaxprice decimal(20,8) null 
/*����˰����*/,
norigprice decimal(28,8) null 
/*����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*����˰����*/,
norignetprice decimal(28,8) null 
/*����˰����*/,
ntaxprice decimal(28,8) null 
/*�����Һ�˰����*/,
nprice decimal(28,8) null 
/*��������˰����*/,
ntaxnetprice decimal(28,8) null 
/*�����Һ�˰����*/,
nnetprice decimal(28,8) null 
/*��������˰����*/,
nqtorigtaxprice decimal(28,8) null 
/*��˰����*/,
nqtorigprice decimal(28,8) null 
/*��˰����*/,
nqtorigtaxnetprc decimal(28,8) null 
/*��˰����*/,
nqtorignetprice decimal(28,8) null 
/*��˰����*/,
nqttaxprice decimal(28,8) null 
/*���Һ�˰����*/,
nqtprice decimal(28,8) null 
/*������˰����*/,
nqttaxnetprice decimal(28,8) null 
/*���Һ�˰����*/,
nqtnetprice decimal(28,8) null 
/*������˰����*/,
ntax decimal(28,8) null 
/*����˰��*/,
nmny decimal(28,8) null 
/*������˰���*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount decimal(28,8) null 
/*�����ۿ۶�*/,
norigmny decimal(28,8) null 
/*��˰���*/,
norigtaxmny decimal(28,8) null 
/*��˰�ϼ�*/,
norigdiscount decimal(28,8) null 
/*�ۿ۶�*/,
norigsubmny decimal(28,8) null 
/*��ֽ��*/,
ngroupmny decimal(28,8) null 
/*���ű�����˰���*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
nglobalmny decimal(28,8) null 
/*ȫ�ֱ�����˰���*/,
nglobaltaxmny decimal(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
vfirsttype varchar(20) null 
/*Դͷ��������*/,
vfirstcode varchar(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype varchar(20) null 
/*Դͷ��������*/,
vfirstrowno varchar(20) null 
/*Դͷ�����к�*/,
cfirstid varchar(20) null 
/*Դͷ���ݱ�ͷID*/,
cfirstbid varchar(20) null 
/*Դͷ���ݱ���ID*/,
vsrctype varchar(20) null 
/*��Դ��������*/,
vsrccode varchar(40) null 
/*��Դ���ݺ�*/,
vsrctrantype varchar(20) null 
/*��Դ��������*/,
vsrcrowno varchar(20) null 
/*��Դ�����к�*/,
csrcid varchar(20) null 
/*��Դ���ݱ�ͷID*/,
csrcbid varchar(20) null 
/*��Դ���ݱ���ID*/,
copposesrcbid varchar(20) null 
/*�Գ���Դ����ID*/,
csaleorgid varchar(20) null 
/*������֯*/,
csaleorgvid varchar(20) null 
/*������֯�汾*/,
cprofitcenterid varchar(20) null 
/*������������*/,
cprofitcentervid varchar(20) null 
/*�����������İ汾*/,
carorgid varchar(20) null 
/*Ӧ����֯*/,
carorgvid varchar(20) null 
/*Ӧ����֯�汾*/,
cordercustid varchar(20) null 
/*�����ͻ�*/,
bfreecustflag char(1) null 
/*�Ƿ�ɢ��*/,
cfreecustid varchar(20) null 
/*ɢ��*/,
cdeptid varchar(20) null 
/*���۲���*/,
cdeptvid varchar(20) null 
/*���۲��Ű汾*/,
cemployeeid varchar(20) null 
/*����ҵ��Ա*/,
creceivecustid varchar(20) null 
/*�ջ��ͻ�*/,
creceiveaddrid varchar(20) null 
/*�ջ���ַ*/,
ctransporttypeid varchar(20) null 
/*���䷽ʽ*/,
csendstockorgid varchar(20) null 
/*�����֯*/,
csendstockorgvid varchar(20) null 
/*�����֯�汾*/,
csendstordocid varchar(20) null 
/*�ֿ�*/,
cprodlineid varchar(20) null 
/*��Ʒ��*/,
ccostsubjid varchar(20) null 
/*��֧��Ŀ*/,
cctmanageid varchar(20) null 
/*��ͬ*/,
vsumcode varchar(40) null 
/*���Ļ��ܺ�*/,
csumid varchar(20) null 
/*���Ļ�������*/,
cvmivenderid varchar(20) null 
/*�Ĵ湩Ӧ��*/,
nshouldoutnum decimal(28,8) null 
/*�ۼ�Ӧ��δ��������*/,
ntotaloutnum decimal(28,8) null 
/*�ۼƳ�������*/,
ntotalincomenum decimal(28,8) null 
/*�ۼ�ȷ��Ӧ������*/,
ntotalincomemny decimal(28,8) null 
/*�ۼ�ȷ��Ӧ�ս��*/,
ntotalcostnum decimal(28,8) null 
/*�ۼƳɱ���������*/,
ntotalpaymny decimal(28,8) null 
/*�ۼ��տ���*/,
vrownote varchar(181) null 
/*��ע*/,
vfree1 varchar(101) null 
/*���ϸ�������1*/,
vfree2 varchar(101) null 
/*���ϸ�������2*/,
vfree3 varchar(101) null 
/*���ϸ�������3*/,
vfree4 varchar(101) null 
/*���ϸ�������4*/,
vfree5 varchar(101) null 
/*���ϸ�������5*/,
vfree6 varchar(101) null 
/*���ϸ�������6*/,
vfree7 varchar(101) null 
/*���ϸ�������7*/,
vfree8 varchar(101) null 
/*���ϸ�������8*/,
vfree9 varchar(101) null 
/*���ϸ�������9*/,
vfree10 varchar(101) null 
/*���ϸ�������10*/,
vbdef1 varchar(101) null 
/*�Զ�����1*/,
vbdef2 varchar(101) null 
/*�Զ�����2*/,
vbdef3 varchar(101) null 
/*�Զ�����3*/,
vbdef4 varchar(101) null 
/*�Զ�����4*/,
vbdef5 varchar(101) null 
/*�Զ�����5*/,
vbdef6 varchar(101) null 
/*�Զ�����6*/,
vbdef7 varchar(101) null 
/*�Զ�����7*/,
vbdef8 varchar(101) null 
/*�Զ�����8*/,
vbdef9 varchar(101) null 
/*�Զ�����9*/,
vbdef10 varchar(101) null 
/*�Զ�����10*/,
vbdef11 varchar(101) null 
/*�Զ�����11*/,
vbdef12 varchar(101) null 
/*�Զ�����12*/,
vbdef13 varchar(101) null 
/*�Զ�����13*/,
vbdef14 varchar(101) null 
/*�Զ�����14*/,
vbdef15 varchar(101) null 
/*�Զ�����15*/,
vbdef16 varchar(101) null 
/*�Զ�����16*/,
vbdef17 varchar(101) null 
/*�Զ�����17*/,
vbdef18 varchar(101) null 
/*�Զ�����18*/,
vbdef19 varchar(101) null 
/*�Զ�����19*/,
vbdef20 varchar(101) null 
/*�Զ�����20*/,
ctaxcodeid varchar(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
cchanneltypeid varchar(20) null 
/*������������*/,
csprofitcentervid varchar(20) null 
/*������������*/,
csprofitcenterid varchar(20) null 
/*���������������°汾*/,
cmffileid varchar(20) null 
/*������*/,
 constraint pk_saleinvoice_b primary key (csaleinvoicebid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۷�Ʊ���� */
create table so_saleinvoice (csaleinvoiceid char(20) not null 
/*��Ʊ����ID*/,
pk_group varchar(20) null 
/*����*/,
pk_org varchar(20) null 
/*��Ʊ��֯*/,
pk_org_v varchar(20) null 
/*��Ʊ��֯�汾*/,
vbillcode varchar(40) null 
/*��Ʊ��*/,
cbiztypeid varchar(20) null 
/*ҵ������*/,
ctrantypeid varchar(20) null 
/*��Ʊ����*/,
vtrantypecode varchar(20) null 
/*��Ʊ���ͱ���*/,
cinvoicecustid varchar(20) null 
/*��Ʊ�ͻ�*/,
dbilldate char(19) null 
/*��Ʊ����*/,
vprintcustname varchar(50) null 
/*�ͻ���ӡ����*/,
ccustbankid varchar(20) null 
/*�ͻ���������*/,
ccustbankaccid varchar(20) null 
/*�ͻ������˺�*/,
cpaytermid varchar(20) null 
/*�ո���Э��*/,
vcreditnum varchar(20) null 
/*����֤��*/,
vgoldtaxcode varchar(100) null 
/*��˰Ʊ��*/,
btogoldtaxflag char(1) null 
/*�Ƿ��Ѿ�����˰*/,
tgoldtaxtime varchar(19) null 
/*��󴫽�˰ʱ��*/,
corigcurrencyid varchar(20) null 
/*ԭ��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ccurrencyid varchar(20) null 
/*���ұ���*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
nhvoicedisrate decimal(28,8) null 
/*��Ʊ�ۿ�*/,
ntotalastnum decimal(28,8) null 
/*������*/,
ntotalorigmny decimal(28,8) null 
/*��˰�ϼ�*/,
ntotalorigsubmny decimal(28,8) null 
/*��ֽ��*/,
bsubunitflag char(1) null 
/*��ֱ��*/,
fopposeflag integer null 
/*�Գ���*/,
vopposesrccode varchar(40) null 
/*�Գ���Դ��Ʊ��*/,
copposesrcid varchar(20) null 
/*�Գ���Դ��ͷID*/,
vnote varchar(181) null 
/*��ע*/,
fstatusflag integer null 
/*����״̬*/,
creator varchar(20) null 
/*������*/,
billmaker varchar(20) null 
/*�Ƶ���*/,
creationtime char(19) null 
/*����ʱ��*/,
modifier varchar(20) null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
approver varchar(20) null 
/*������*/,
taudittime varchar(19) null 
/*�������*/,
iprintcount integer null default 0 
/*��ӡ����*/,
vdef1 varchar(101) null 
/*�Զ�����1*/,
vdef2 varchar(101) null 
/*�Զ�����2*/,
vdef3 varchar(101) null 
/*�Զ�����3*/,
vdef4 varchar(101) null 
/*�Զ�����4*/,
vdef5 varchar(101) null 
/*�Զ�����5*/,
vdef6 varchar(101) null 
/*�Զ�����6*/,
vdef7 varchar(101) null 
/*�Զ�����7*/,
vdef8 varchar(101) null 
/*�Զ�����8*/,
vdef9 varchar(101) null 
/*�Զ�����9*/,
vdef10 varchar(101) null 
/*�Զ�����10*/,
vdef11 varchar(101) null 
/*�Զ�����11*/,
vdef12 varchar(101) null 
/*�Զ�����12*/,
vdef13 varchar(101) null 
/*�Զ�����13*/,
vdef14 varchar(101) null 
/*�Զ�����14*/,
vdef15 varchar(101) null 
/*�Զ�����15*/,
vdef16 varchar(101) null 
/*�Զ�����16*/,
vdef17 varchar(101) null 
/*�Զ�����17*/,
vdef18 varchar(101) null 
/*�Զ�����18*/,
vdef19 varchar(101) null 
/*�Զ�����19*/,
vdef20 varchar(101) null 
/*�Զ�����20*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
crececountryid varchar(20) null 
/*�ջ�����/����*/,
csendcountryid varchar(20) null 
/*��������/����*/,
ctaxcountryid varchar(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
ctradewordid varchar(20) null 
/*ó������*/,
vvatcode varchar(40) null 
/*VATע����*/,
vcustvatcode varchar(40) null 
/*�ͻ�VATע����*/,
cbalancetypeid varchar(20) null 
/*���㷽ʽ*/,
 constraint pk_saleinvoice primary key (csaleinvoiceid),
 ts char(19) null,
dr smallint null default 0
)
;

