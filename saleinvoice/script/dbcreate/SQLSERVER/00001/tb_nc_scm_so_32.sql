/* tablename: ��Ʊ�������� */
create table so_m32trantype (
pk_trantype nchar(20) not null 
/*��Ʊ������������*/,
ctrantypeid nvarchar(20) null 
/*��������*/,
pk_group nvarchar(20) null 
/*����ID*/,
vtrantypecode nvarchar(20) null 
/*�������ͱ���*/,
fadjuster smallint null 
/*������*/,
 constraint pk_m32trantype primary key (pk_trantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۷�Ʊ�ӱ� */
create table so_saleinvoice_b (
csaleinvoicebid nchar(20) not null 
/*��Ʊ�ӱ�ID*/,
csaleinvoiceid nvarchar(20) null 
/*��Ʊ����ID*/,
pk_group nvarchar(20) null 
/*����*/,
pk_org nvarchar(20) null 
/*��Ʊ��֯*/,
dbilldate nchar(19) null 
/*��Ʊ����*/,
crowno nvarchar(20) null 
/*�к�*/,
ccustmaterialid nvarchar(20) null 
/*�ͻ�������*/,
cmaterialid nvarchar(20) null 
/*����*/,
cmaterialvid nvarchar(20) null 
/*���ϰ汾*/,
cvendorid nvarchar(20) null 
/*��Ӧ��*/,
cprojectid nvarchar(20) null 
/*��Ŀ*/,
cproductorid nvarchar(20) null 
/*��������*/,
cunitid nvarchar(20) null 
/*����λ*/,
castunitid nvarchar(20) null 
/*��λ*/,
vchangerate nvarchar(60) null 
/*������*/,
nnum decimal(28,8) null 
/*������*/,
nastnum decimal(28,8) null 
/*����*/,
cqtunitid nvarchar(20) null 
/*���۵�λ*/,
vqtunitrate nvarchar(60) null 
/*���۵�λ������*/,
nqtunitnum decimal(28,8) null 
/*��������*/,
bdiscountflag nchar(1) null 
/*�ۿ���*/,
blaborflag nchar(1) null 
/*������*/,
blargessflag nchar(1) null 
/*��Ʒ*/,
pk_batchcode nvarchar(20) null 
/*���ε���*/,
vbatchcode nvarchar(40) null 
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
vfirsttype nvarchar(20) null 
/*Դͷ��������*/,
vfirstcode nvarchar(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype nvarchar(20) null 
/*Դͷ��������*/,
vfirstrowno nvarchar(20) null 
/*Դͷ�����к�*/,
cfirstid nvarchar(20) null 
/*Դͷ���ݱ�ͷID*/,
cfirstbid nvarchar(20) null 
/*Դͷ���ݱ���ID*/,
vsrctype nvarchar(20) null 
/*��Դ��������*/,
vsrccode nvarchar(40) null 
/*��Դ���ݺ�*/,
vsrctrantype nvarchar(20) null 
/*��Դ��������*/,
vsrcrowno nvarchar(20) null 
/*��Դ�����к�*/,
csrcid nvarchar(20) null 
/*��Դ���ݱ�ͷID*/,
csrcbid nvarchar(20) null 
/*��Դ���ݱ���ID*/,
copposesrcbid nvarchar(20) null 
/*�Գ���Դ����ID*/,
csaleorgid nvarchar(20) null 
/*������֯*/,
csaleorgvid nvarchar(20) null 
/*������֯�汾*/,
cprofitcenterid nvarchar(20) null 
/*������������*/,
cprofitcentervid nvarchar(20) null 
/*�����������İ汾*/,
carorgid nvarchar(20) null 
/*Ӧ����֯*/,
carorgvid nvarchar(20) null 
/*Ӧ����֯�汾*/,
cordercustid nvarchar(20) null 
/*�����ͻ�*/,
bfreecustflag nchar(1) null 
/*�Ƿ�ɢ��*/,
cfreecustid nvarchar(20) null 
/*ɢ��*/,
cdeptid nvarchar(20) null 
/*���۲���*/,
cdeptvid nvarchar(20) null 
/*���۲��Ű汾*/,
cemployeeid nvarchar(20) null 
/*����ҵ��Ա*/,
creceivecustid nvarchar(20) null 
/*�ջ��ͻ�*/,
creceiveaddrid nvarchar(20) null 
/*�ջ���ַ*/,
ctransporttypeid nvarchar(20) null 
/*���䷽ʽ*/,
csendstockorgid nvarchar(20) null 
/*�����֯*/,
csendstockorgvid nvarchar(20) null 
/*�����֯�汾*/,
csendstordocid nvarchar(20) null 
/*�ֿ�*/,
cprodlineid nvarchar(20) null 
/*��Ʒ��*/,
ccostsubjid nvarchar(20) null 
/*��֧��Ŀ*/,
cctmanageid nvarchar(20) null 
/*��ͬ*/,
vsumcode nvarchar(40) null 
/*���Ļ��ܺ�*/,
csumid nvarchar(20) null 
/*���Ļ�������*/,
cvmivenderid nvarchar(20) null 
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
vrownote nvarchar(181) null 
/*��ע*/,
vfree1 nvarchar(101) null 
/*���ϸ�������1*/,
vfree2 nvarchar(101) null 
/*���ϸ�������2*/,
vfree3 nvarchar(101) null 
/*���ϸ�������3*/,
vfree4 nvarchar(101) null 
/*���ϸ�������4*/,
vfree5 nvarchar(101) null 
/*���ϸ�������5*/,
vfree6 nvarchar(101) null 
/*���ϸ�������6*/,
vfree7 nvarchar(101) null 
/*���ϸ�������7*/,
vfree8 nvarchar(101) null 
/*���ϸ�������8*/,
vfree9 nvarchar(101) null 
/*���ϸ�������9*/,
vfree10 nvarchar(101) null 
/*���ϸ�������10*/,
vbdef1 nvarchar(101) null 
/*�Զ�����1*/,
vbdef2 nvarchar(101) null 
/*�Զ�����2*/,
vbdef3 nvarchar(101) null 
/*�Զ�����3*/,
vbdef4 nvarchar(101) null 
/*�Զ�����4*/,
vbdef5 nvarchar(101) null 
/*�Զ�����5*/,
vbdef6 nvarchar(101) null 
/*�Զ�����6*/,
vbdef7 nvarchar(101) null 
/*�Զ�����7*/,
vbdef8 nvarchar(101) null 
/*�Զ�����8*/,
vbdef9 nvarchar(101) null 
/*�Զ�����9*/,
vbdef10 nvarchar(101) null 
/*�Զ�����10*/,
vbdef11 nvarchar(101) null 
/*�Զ�����11*/,
vbdef12 nvarchar(101) null 
/*�Զ�����12*/,
vbdef13 nvarchar(101) null 
/*�Զ�����13*/,
vbdef14 nvarchar(101) null 
/*�Զ�����14*/,
vbdef15 nvarchar(101) null 
/*�Զ�����15*/,
vbdef16 nvarchar(101) null 
/*�Զ�����16*/,
vbdef17 nvarchar(101) null 
/*�Զ�����17*/,
vbdef18 nvarchar(101) null 
/*�Զ�����18*/,
vbdef19 nvarchar(101) null 
/*�Զ�����19*/,
vbdef20 nvarchar(101) null 
/*�Զ�����20*/,
ctaxcodeid nvarchar(20) null 
/*˰��*/,
ftaxtypeflag int null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
cchanneltypeid nvarchar(20) null 
/*������������*/,
csprofitcentervid nvarchar(20) null 
/*������������*/,
csprofitcenterid nvarchar(20) null 
/*���������������°汾*/,
cmffileid nvarchar(20) null 
/*������*/,
 constraint pk_saleinvoice_b primary key (csaleinvoicebid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۷�Ʊ���� */
create table so_saleinvoice (
csaleinvoiceid nchar(20) not null 
/*��Ʊ����ID*/,
pk_group nvarchar(20) null 
/*����*/,
pk_org nvarchar(20) null 
/*��Ʊ��֯*/,
pk_org_v nvarchar(20) null 
/*��Ʊ��֯�汾*/,
vbillcode nvarchar(40) null 
/*��Ʊ��*/,
cbiztypeid nvarchar(20) null 
/*ҵ������*/,
ctrantypeid nvarchar(20) null 
/*��Ʊ����*/,
vtrantypecode nvarchar(20) null 
/*��Ʊ���ͱ���*/,
cinvoicecustid nvarchar(20) null 
/*��Ʊ�ͻ�*/,
dbilldate nchar(19) null 
/*��Ʊ����*/,
vprintcustname nvarchar(50) null 
/*�ͻ���ӡ����*/,
ccustbankid nvarchar(20) null 
/*�ͻ���������*/,
ccustbankaccid nvarchar(20) null 
/*�ͻ������˺�*/,
cpaytermid nvarchar(20) null 
/*�ո���Э��*/,
vcreditnum nvarchar(20) null 
/*����֤��*/,
vgoldtaxcode nvarchar(100) null 
/*��˰Ʊ��*/,
btogoldtaxflag nchar(1) null 
/*�Ƿ��Ѿ�����˰*/,
tgoldtaxtime nvarchar(19) null 
/*��󴫽�˰ʱ��*/,
corigcurrencyid nvarchar(20) null 
/*ԭ��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ccurrencyid nvarchar(20) null 
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
bsubunitflag nchar(1) null 
/*��ֱ��*/,
fopposeflag int null 
/*�Գ���*/,
vopposesrccode nvarchar(40) null 
/*�Գ���Դ��Ʊ��*/,
copposesrcid nvarchar(20) null 
/*�Գ���Դ��ͷID*/,
vnote nvarchar(181) null 
/*��ע*/,
fstatusflag int null 
/*����״̬*/,
creator nvarchar(20) null 
/*������*/,
billmaker nvarchar(20) null 
/*�Ƶ���*/,
creationtime nchar(19) null 
/*����ʱ��*/,
modifier nvarchar(20) null 
/*����޸���*/,
modifiedtime nchar(19) null 
/*����޸�ʱ��*/,
approver nvarchar(20) null 
/*������*/,
taudittime nvarchar(19) null 
/*�������*/,
iprintcount int null default 0 
/*��ӡ����*/,
vdef1 nvarchar(101) null 
/*�Զ�����1*/,
vdef2 nvarchar(101) null 
/*�Զ�����2*/,
vdef3 nvarchar(101) null 
/*�Զ�����3*/,
vdef4 nvarchar(101) null 
/*�Զ�����4*/,
vdef5 nvarchar(101) null 
/*�Զ�����5*/,
vdef6 nvarchar(101) null 
/*�Զ�����6*/,
vdef7 nvarchar(101) null 
/*�Զ�����7*/,
vdef8 nvarchar(101) null 
/*�Զ�����8*/,
vdef9 nvarchar(101) null 
/*�Զ�����9*/,
vdef10 nvarchar(101) null 
/*�Զ�����10*/,
vdef11 nvarchar(101) null 
/*�Զ�����11*/,
vdef12 nvarchar(101) null 
/*�Զ�����12*/,
vdef13 nvarchar(101) null 
/*�Զ�����13*/,
vdef14 nvarchar(101) null 
/*�Զ�����14*/,
vdef15 nvarchar(101) null 
/*�Զ�����15*/,
vdef16 nvarchar(101) null 
/*�Զ�����16*/,
vdef17 nvarchar(101) null 
/*�Զ�����17*/,
vdef18 nvarchar(101) null 
/*�Զ�����18*/,
vdef19 nvarchar(101) null 
/*�Զ�����19*/,
vdef20 nvarchar(101) null 
/*�Զ�����20*/,
dmakedate nchar(19) null 
/*�Ƶ�����*/,
crececountryid nvarchar(20) null 
/*�ջ�����/����*/,
csendcountryid nvarchar(20) null 
/*��������/����*/,
ctaxcountryid nvarchar(20) null 
/*��˰����/����*/,
fbuysellflag int null 
/*��������*/,
btriatradeflag nchar(1) null 
/*����ó��*/,
ctradewordid nvarchar(20) null 
/*ó������*/,
vvatcode nvarchar(40) null 
/*VATע����*/,
vcustvatcode nvarchar(40) null 
/*�ͻ�VATע����*/,
cbalancetypeid nvarchar(20) null 
/*���㷽ʽ*/,
 constraint pk_saleinvoice primary key (csaleinvoiceid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

