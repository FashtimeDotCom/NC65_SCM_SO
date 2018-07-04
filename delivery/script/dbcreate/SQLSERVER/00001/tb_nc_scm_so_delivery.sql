/* tablename: ���������� */
create table so_delivery (
cdeliveryid nchar(20) not null 
/*����������ID*/,
pk_group nvarchar(20) null 
/*����*/,
pk_org nvarchar(20) null 
/*������֯*/,
pk_org_v nvarchar(20) null 
/*������֯�汾*/,
vbillcode nvarchar(40) null 
/*���ݺ�*/,
cbiztypeid nvarchar(20) null 
/*ҵ������*/,
vtrantypecode nvarchar(20) null 
/*�������ͱ���*/,
dbilldate nchar(19) null 
/*��������*/,
csendemployeeid nvarchar(20) null 
/*�����ƻ�Ա*/,
csenddeptid nvarchar(20) null 
/*�����������°汾*/,
csenddeptvid nvarchar(20) null 
/*��������*/,
ctransporttypeid nvarchar(20) null 
/*���䷽ʽ*/,
ctransportrouteid nvarchar(50) null 
/*����·��*/,
ntotalastnum decimal(28,8) null 
/*������*/,
ntotalweight decimal(28,8) null 
/*������*/,
ntotalvolume decimal(28,8) null 
/*�����*/,
ntotalpiece decimal(28,8) null 
/*�ܼ���*/,
fstatusflag smallint null 
/*״̬*/,
vnote nvarchar(181) null 
/*��ע*/,
creator nvarchar(20) null 
/*������*/,
billmaker nvarchar(20) null 
/*�Ƶ���*/,
creationtime nchar(19) null 
/*����ʱ��*/,
approver nvarchar(20) null 
/*������*/,
taudittime nvarchar(19) null 
/*�������*/,
modifier nvarchar(20) null 
/*����޸���*/,
modifiedtime nchar(19) null 
/*����޸�ʱ��*/,
iprintcount int null 
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
ctrantypeid nvarchar(20) null 
/*��������*/,
dmakedate nchar(19) null 
/*�Ƶ�����*/,
ctradewordid nvarchar(20) null 
/*ó������*/,
 constraint pk_so_delivery primary key (cdeliveryid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �������ӱ� */
create table so_delivery_b (
cdeliverybid nchar(20) not null 
/*�������ӱ�ID*/,
cdeliveryid nvarchar(20) null 
/*����������ID*/,
pk_org nvarchar(20) null 
/*������֯*/,
dbilldate nchar(19) null 
/*��������*/,
crowno nvarchar(20) null 
/*�к�*/,
ccustmaterialid nvarchar(20) null 
/*�ͻ�������*/,
cordercustid nvarchar(20) null 
/*�����ͻ�*/,
cfreecustid nvarchar(20) null 
/*ɢ��*/,
cinvoicecustid nvarchar(20) null 
/*��Ʊ�ͻ�*/,
cmaterialid nvarchar(20) null 
/*���ϵ���*/,
cmaterialvid nvarchar(20) null 
/*���ϱ���*/,
cvendorid nvarchar(20) null 
/*��Ӧ��*/,
cprojectid nvarchar(20) null 
/*��Ŀ*/,
cproductorid nvarchar(20) null 
/*��������*/,
castunitid nvarchar(20) null 
/*��λ*/,
nastnum decimal(28,8) null 
/*����*/,
cunitid nvarchar(20) null 
/*����λ*/,
nnum decimal(28,8) null 
/*������*/,
vchangerate nvarchar(60) null 
/*������*/,
cqtunitid nvarchar(20) null 
/*���۵�λ*/,
nqtunitnum decimal(28,8) null 
/*��������*/,
vqtunitrate nvarchar(60) null 
/*���ۻ�����*/,
bcheckflag nchar(1) null 
/*�Ƿ��ѱ���*/,
busecheckflag nchar(1) null 
/*�Ƿ�����ʼ������*/,
ntotalreportnum decimal(28,8) null 
/*�ۼƱ�������*/,
ntotalelignum decimal(28,8) null 
/*�ۼƺϸ�����*/,
ntotalunelignum decimal(28,8) null 
/*�ۼƲ��ϸ�����*/,
nweight decimal(28,8) null 
/*����*/,
nvolume decimal(28,8) null 
/*���*/,
npiece decimal(28,8) null 
/*����*/,
blargessflag nchar(1) null 
/*��Ʒ*/,
pk_batchcode nvarchar(20) null 
/*���ε���*/,
vbatchcode nvarchar(40) null 
/*���κ�*/,
corigcurrencyid nvarchar(20) null 
/*ԭ��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ccurrencyid nvarchar(20) null 
/*��λ��*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ�*/,
norigtaxprice decimal(28,8) null 
/*����˰����*/,
norigprice decimal(28,8) null 
/*����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*����˰����*/,
norignetprice decimal(28,8) null 
/*����˰����*/,
nqtorigtaxprice decimal(28,8) null 
/*��˰����*/,
nqtorigprice decimal(28,8) null 
/*��˰����*/,
nqtorigtaxnetprc decimal(28,8) null 
/*��˰����*/,
nqtorignetprice decimal(28,8) null 
/*��˰����*/,
norigmny decimal(28,8) null 
/*��˰���*/,
norigtaxmny decimal(28,8) null 
/*��˰�ϼ�*/,
norigdiscount decimal(28,8) null 
/*�ۿ۶�*/,
ntaxprice decimal(28,8) null 
/*��������˰����*/,
nprice decimal(28,8) null 
/*��������˰����*/,
ntaxnetprice decimal(28,8) null 
/*��������˰����*/,
nnetprice decimal(28,8) null 
/*��������˰����*/,
nqttaxprice decimal(28,8) null 
/*���Һ�˰����*/,
nqtprice decimal(28,8) null 
/*������˰����*/,
nqttaxnetprice decimal(28,8) null 
/*���Һ�˰����*/,
nqtnetprice decimal(28,8) null 
/*������˰����*/,
ntax decimal(28,8) null 
/*˰��*/,
nmny decimal(28,8) null 
/*������˰���*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount decimal(28,8) null 
/*�����ۿ۶�*/,
vfirsttype nvarchar(20) null 
/*Դͷ��������*/,
vfirstcode nvarchar(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype nvarchar(40) null 
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
csaleorgid nvarchar(20) null 
/*������֯���°汾*/,
csaleorgvid nvarchar(20) null 
/*������֯*/,
csendstockorgid nvarchar(20) null 
/*���������֯���°汾*/,
csendareaid nvarchar(20) null 
/*��������*/,
csendadddocid nvarchar(20) null 
/*�����ص�*/,
csendaddrid nvarchar(20) null 
/*������ַ*/,
csendstordocid nvarchar(20) null 
/*�����ֿ�*/,
csendstockorgvid nvarchar(20) null 
/*���������֯*/,
csendpersonid nvarchar(20) null 
/*������ϵ��*/,
vsendtel nvarchar(30) null 
/*������ϵ�绰*/,
creceivecustid nvarchar(20) null 
/*�ջ��ͻ�*/,
creceiveareaid nvarchar(20) null 
/*�ջ�����*/,
creceiveadddocid nvarchar(20) null 
/*�ջ��ص�*/,
creceiveaddrid nvarchar(20) null 
/*�ջ���ַ*/,
cinstockorgid nvarchar(20) null 
/*�ջ������֯���°�*/,
vreceivetel nvarchar(30) null 
/*�ջ���ϵ�绰*/,
creceivepersonid nvarchar(20) null 
/*�ջ���ϵ��*/,
cinstordocid nvarchar(20) null 
/*�ջ��ֿ�*/,
cinstockorgvid nvarchar(20) null 
/*�ջ������֯*/,
dsenddate nvarchar(19) null 
/*��������*/,
dreceivedate nvarchar(19) null 
/*Ҫ���ջ�����*/,
csupercargoid nvarchar(20) null 
/*Ѻ��Ա*/,
ctranscustid nvarchar(20) null 
/*������*/,
cvehicletypeid nvarchar(20) null 
/*����*/,
cvehicleid nvarchar(20) null 
/*����*/,
cchauffeurid nvarchar(20) null 
/*˾��*/,
cspaceid nvarchar(50) null 
/*��λ*/,
cprodlineid nvarchar(20) null 
/*��Ʒ��*/,
ntotaltransnum decimal(28,8) null 
/*�ۼ���������*/,
btransendflag nchar(1) null 
/*����ر�*/,
ntotaloutnum decimal(28,8) null 
/*�ۼƳ�������*/,
bbarsettleflag nchar(1) null 
/*�������ر�*/,
boutendflag nchar(1) null 
/*����ر�*/,
frownote nvarchar(181) null 
/*��ע*/,
vfree1 nvarchar(101) null 
/*���ɸ�������1*/,
vfree2 nvarchar(101) null 
/*���ɸ�������2*/,
vfree3 nvarchar(101) null 
/*���ɸ�������3*/,
vfree4 nvarchar(101) null 
/*���ɸ�������4*/,
vfree5 nvarchar(101) null 
/*���ɸ�������5*/,
vfree6 nvarchar(101) null 
/*���ɸ�������6*/,
vfree7 nvarchar(101) null 
/*���ɸ�������7*/,
vfree8 nvarchar(101) null 
/*���ɸ�������8*/,
vfree9 nvarchar(101) null 
/*���ɸ�������9*/,
vfree10 nvarchar(101) null 
/*���ɸ�������10*/,
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
cqualitylevelid nvarchar(20) null 
/*�����ȼ�*/,
cprofitcenterid nvarchar(20) null 
/*���������������°汾*/,
cprofitcentervid nvarchar(20) null 
/*������������*/,
carorgid nvarchar(20) null 
/*Ӧ����֯���°汾*/,
carorgvid nvarchar(20) null 
/*Ӧ����֯*/,
csettleorgid nvarchar(20) null 
/*���������֯���°汾*/,
cdeptid nvarchar(20) null 
/*���۲������°汾*/,
cdeptvid nvarchar(20) null 
/*���۲���*/,
cemployeeid nvarchar(20) null 
/*����ҵ��Ա*/,
csettleorgvid nvarchar(20) null 
/*���������֯*/,
cchanneltypeid nvarchar(20) null 
/*��������*/,
vfirstbilldate nvarchar(19) null 
/*Դͷ��������*/,
ntranslossnum decimal(28,8) null 
/*�ۼ�;������*/,
ntotalrushnum decimal(28,8) null 
/*�ۼƳ���Գ�����*/,
ntotalestarnum decimal(28,8) null 
/*�ۼ��ݹ�Ӧ������*/,
ntotalarnum decimal(28,8) null 
/*�ۼ�ȷ��Ӧ������*/,
nreqrsnum decimal(28,8) null 
/*Ԥ������*/,
bqualityflag nchar(1) null 
/*�Ƿ����ʼ�*/,
badvfeeflag nchar(1) null 
/*�����˷�*/,
pk_group nvarchar(20) null 
/*��������*/,
cpriceformid nchar(20) null 
/*�۸����*/,
cretreasonid nchar(20) null 
/*�˻�ԭ��*/,
vreturnmode nchar(20) null 
/*�˻����δ���ʽ*/,
ntotalnotoutnum decimal(28,8) null 
/*�ۼ�Ӧ��δ����������*/,
nglobaltaxmny decimal(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny decimal(28,8) null 
/*ȫ�ֱ�����˰���*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny decimal(28,8) null 
/*���ű�����˰���*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
crececountryid nvarchar(20) null 
/*�ջ�����/����*/,
csendcountryid nvarchar(20) null 
/*������/����*/,
ctaxcountryid nvarchar(20) null 
/*��˰��/����*/,
fbuysellflag int null 
/*��������*/,
btriatradeflag nchar(1) null 
/*����ó��*/,
ctaxcodeid nvarchar(20) null 
/*˰��*/,
ftaxtypeflag int null 
/*��˰���*/,
corigcountryid nvarchar(20) null 
/*ԭ����*/,
corigareaid nvarchar(20) null 
/*ԭ������*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
csprofitcentervid nvarchar(20) null 
/*������������*/,
csprofitcenterid nvarchar(20) null 
/*���������������°汾*/,
crprofitcentervid nvarchar(20) null 
/*�ջ���������*/,
crprofitcenterid nvarchar(20) null 
/*�ջ������������°汾*/,
cmffileid nvarchar(20) null 
/*������*/,
 constraint pk_so_delivery_b primary key (cdeliverybid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �������ʼ�� */
create table so_delivery_check (
cdeliverycid nchar(20) not null 
/*�������ʼ��ID*/,
cdeliverybid nvarchar(20) null 
/*�������ӱ�ID*/,
crowno nvarchar(20) null 
/*�к�*/,
cmaterialid nvarchar(20) null 
/*����*/,
cmaterialvid nvarchar(20) null 
/*���ϰ汾*/,
cvendorid nvarchar(20) null 
/*��Ӧ��*/,
cprojectid nvarchar(20) null 
/*��Ŀ*/,
cqualitylevelid nvarchar(20) null 
/*�����ȼ�*/,
cproductorid nvarchar(20) null 
/*��������*/,
vfree1 nvarchar(101) null 
/*���ɸ�������1*/,
vfree2 nvarchar(101) null 
/*���ɸ�������2*/,
vfree3 nvarchar(101) null 
/*���ɸ�������3*/,
vfree4 nvarchar(101) null 
/*���ɸ�������4*/,
vfree5 nvarchar(101) null 
/*���ɸ�������5*/,
vfree6 nvarchar(101) null 
/*���ɸ�������6*/,
vfree7 nvarchar(101) null 
/*���ɸ�������7*/,
vfree8 nvarchar(101) null 
/*���ɸ�������8*/,
vfree9 nvarchar(101) null 
/*���ɸ�������9*/,
vfree10 nvarchar(101) null 
/*���ɸ�������10*/,
pk_batchcode nvarchar(20) null 
/*���ε���*/,
vbatchcode nvarchar(40) null 
/*���κ�*/,
castunitid nvarchar(20) null 
/*��λ*/,
nastnum decimal(28,8) null 
/*�ʼ�����*/,
cunitid nvarchar(20) null 
/*����λ*/,
nnum decimal(28,8) null 
/*�ʼ�������*/,
vchangerate nvarchar(60) null 
/*������*/,
cqtunitid nvarchar(20) null 
/*���۵�λ*/,
nqtunitnum decimal(28,8) null 
/*�ʼ챨������*/,
vqtunitrate nvarchar(60) null 
/*���ۻ�����*/,
vcheckcode nvarchar(40) null 
/*�ʼ쵥�ݺ�*/,
dcheckdate nvarchar(19) null 
/*�ʼ�����*/,
ccheckstatebid nvarchar(20) null 
/*�ʼ�״̬*/,
cdefectprocessid nvarchar(20) null 
/*���鴦��ʽ*/,
beligflag nchar(1) null 
/*�Ƿ�ϸ�*/,
bcheckinflag nchar(1) null 
/*�ʼ챨���Ƿ�����*/,
blargessflag nchar(1) null 
/*��Ʒ*/,
bpricechgflag nchar(1) null 
/*�Ƿ�������*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ�*/,
norigtaxprice decimal(28,8) null 
/*����˰����*/,
norigprice decimal(28,8) null 
/*����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*����˰����*/,
norignetprice decimal(28,8) null 
/*����˰����*/,
nqtorigtaxprice decimal(28,8) null 
/*��˰����*/,
nqtorigprice decimal(28,8) null 
/*��˰����*/,
nqtorigtaxnetprc decimal(28,8) null 
/*��˰����*/,
nqtorignetprice decimal(28,8) null 
/*��˰����*/,
norigmny decimal(28,8) null 
/*��˰���*/,
norigtaxmny decimal(28,8) null 
/*��˰�ϼ�*/,
norigdiscount decimal(28,8) null 
/*�ۿ۶�*/,
ntaxprice decimal(28,8) null 
/*��������˰����*/,
nprice decimal(28,8) null 
/*��������˰����*/,
ntaxnetprice decimal(28,8) null 
/*��������˰����*/,
nnetprice decimal(28,8) null 
/*��������˰����*/,
nqttaxprice decimal(28,8) null 
/*���Һ�˰����*/,
nqtprice decimal(28,8) null 
/*������˰����*/,
nqttaxnetprice decimal(28,8) null 
/*���Һ�˰����*/,
nqtnetprice decimal(28,8) null 
/*������˰����*/,
ntax decimal(28,8) null 
/*˰��*/,
nmny decimal(28,8) null 
/*������˰���*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount decimal(28,8) null 
/*�����ۿ۶�*/,
corigcurrencyid nvarchar(20) null 
/*ԭ��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ccurrencyid nvarchar(20) null 
/*��λ��*/,
ntotaltransnum decimal(28,8) null 
/*�ۼ���������*/,
btransendflag nchar(1) null 
/*����ر�*/,
ntotaloutnum decimal(28,8) null 
/*�ۼƳ�������*/,
boutendflag nchar(1) null 
/*����ر�*/,
vrownote nvarchar(181) null 
/*��ע*/,
vsrcrowno nvarchar(20) null 
/*���յ��к�*/,
pk_org nvarchar(20) null 
/*������֯*/,
pk_group nvarchar(20) null 
/*��������*/,
ntotalnotoutnum decimal(28,8) null 
/*�ۼ�Ӧ��δ����������*/,
csrcid nchar(20) null 
/*��Դ����id*/,
nglobaltaxmny decimal(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny decimal(28,8) null 
/*ȫ�ֱ�����˰���*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny decimal(28,8) null 
/*���ű�����˰���*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
crececountryid nvarchar(20) null 
/*�ջ�����/����*/,
csendcountryid nvarchar(20) null 
/*������/����*/,
ctaxcountryid nvarchar(20) null 
/*��˰��/����*/,
fbuysellflag int null 
/*��������*/,
btriatradeflag nchar(1) null 
/*����ó��*/,
ctaxcodeid nvarchar(20) null 
/*˰��*/,
ftaxtypeflag int null 
/*��˰���*/,
corigcountryid nvarchar(20) null 
/*ԭ����*/,
corigareaid nvarchar(20) null 
/*ԭ������*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
 constraint pk__delivery_check primary key (cdeliverycid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �������������� */
create table so_m4331trantype (
ctrantypeid nchar(20) null 
/*��������������*/,
pk_group nvarchar(20) null 
/*����*/,
vtrantypecode nvarchar(20) null 
/*��������*/,
bonceoutflag nchar(1) null 
/*ֻһ�γ���*/,
pk_trantype nvarchar(20) not null 
/*������������*/,
 constraint pk_o_m4331trantype primary key (pk_trantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

