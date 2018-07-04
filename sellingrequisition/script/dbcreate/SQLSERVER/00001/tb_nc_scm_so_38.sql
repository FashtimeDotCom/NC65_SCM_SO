/* tablename: Ԥ�������� */
create table so_preorder (
cpreorderid nchar(20) not null 
/*Ԥ��������*/,
vbillcode nvarchar(40) null 
/*���ݺ�*/,
pk_org nvarchar(20) null 
/*������֯*/,
pk_org_v nvarchar(20) null 
/*������֯�汾*/,
pk_group nvarchar(20) null 
/*����*/,
ctrantypeid nvarchar(20) null 
/*Ԥ��������*/,
vtrantypecode nvarchar(20) null 
/*Ԥ�������ͱ���*/,
cdeptid nvarchar(20) null 
/*���۲������°汾*/,
cdeptvid nvarchar(20) null 
/*���۲���*/,
cemployeeid nvarchar(20) null 
/*ҵ��Ա*/,
ccustomerid nvarchar(20) null 
/*�ͻ�*/,
cchanneltypeid nvarchar(20) null 
/*������������*/,
cpaytermid nvarchar(20) null 
/*�տ�Э��*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
cinvoicecustid nvarchar(20) null 
/*��Ʊ�ͻ�*/,
ctransporttypeid nvarchar(20) null 
/*���䷽ʽ*/,
corigcurrencyid nvarchar(20) null 
/*����*/,
ntotalnum decimal(28,8) null 
/*������*/,
nheadsummny decimal(28,8) null 
/*��˰�ϼ�*/,
dbilldate nchar(19) null 
/*��������*/,
dabatedate nvarchar(19) null 
/*ʧЧ����*/,
fstatusflag int null default 1 
/*����״̬*/,
vsrctype nvarchar(20) null 
/*��Դ����*/,
cweboperatorid nvarchar(20) null 
/*���϶����Ƶ���*/,
billmaker nvarchar(20) null 
/*�Ƶ���*/,
creator nvarchar(20) null 
/*������*/,
creationtime nchar(19) null 
/*����ʱ��*/,
approver nvarchar(20) null 
/*������*/,
taudittime nchar(19) null 
/*��������*/,
modifier nvarchar(20) null 
/*����޸���*/,
modifiedtime nchar(19) null 
/*����޸�ʱ��*/,
iprintcount int null default 0 
/*��ӡ����*/,
vnote nvarchar(181) null 
/*��ע*/,
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
 constraint pk_so_preorder primary key (cpreorderid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: Ԥ�����ӱ� */
create table so_preorder_b (
cpreorderbid nchar(20) not null 
/*Ԥ�����ӱ�����*/,
pk_group nvarchar(20) null 
/*����*/,
pk_org nvarchar(20) null 
/*������֯*/,
ccustmaterialid nvarchar(20) null 
/*�ͻ�������*/,
cmaterialid nvarchar(20) null 
/*���ϵ���*/,
cmaterialvid nvarchar(20) null 
/*���ϱ���*/,
dbilldate nchar(19) null 
/*��������*/,
cunitid nvarchar(20) null 
/*����λ*/,
castunitid nvarchar(20) null 
/*��λ*/,
nnum decimal(28,8) null 
/*������*/,
nastnum decimal(28,8) null 
/*����*/,
vchangerate nvarchar(60) null 
/*������*/,
cqtunitid nvarchar(20) null 
/*���۵�λ*/,
nqtunitnum decimal(28,8) null 
/*��������*/,
vqtunitrate nvarchar(60) null 
/*���ۻ�����*/,
cvendorid nvarchar(20) null 
/*��Ӧ��*/,
cprojectid nvarchar(20) null 
/*��Ŀ*/,
cqualitylevelid nvarchar(20) null 
/*�����ȼ�*/,
cproductorid nvarchar(20) null 
/*��������*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ�*/,
csendstockorgid nvarchar(20) null 
/*���������֯���°汾*/,
csendstockorgvid nvarchar(20) null 
/*���������֯*/,
csendstordocid nvarchar(20) null 
/*�����ֿ�*/,
ctrafficorgid nvarchar(20) null 
/*������֯���°汾*/,
ctrafficorgvid nvarchar(20) null 
/*������֯*/,
csettleorgid nvarchar(20) null 
/*���������֯���°汾*/,
csettleorgvid nvarchar(20) null 
/*���������֯*/,
carorgid nvarchar(20) null 
/*Ӧ����֯���°汾*/,
carorgvid nvarchar(20) null 
/*Ӧ����֯*/,
cprofitcenterid nvarchar(20) null 
/*�����������°汾*/,
cprofitcentervid nvarchar(20) null 
/*��������*/,
creceivecustid nvarchar(20) null 
/*�ջ��ͻ�*/,
creceiveareaid nvarchar(20) null 
/*�ջ�����*/,
creceivesiteid nvarchar(20) null 
/*�ջ��ص�*/,
creceiveaddrid nvarchar(20) null 
/*�ջ���ַ*/,
cpriceformid nvarchar(20) null 
/*�۸����*/,
ccurrencyid nvarchar(20) null 
/*��λ��*/,
corigcurrencyid nvarchar(20) null 
/*����*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
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
vbatchcode nvarchar(40) null 
/*����*/,
pk_batchcode nvarchar(20) null 
/*���ε���*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
norigprice decimal(28,8) null 
/*����˰����*/,
norigtaxprice decimal(28,8) null 
/*����˰����*/,
norignetprice decimal(28,8) null 
/*����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*����˰����*/,
norigmny decimal(28,8) null 
/*��˰���*/,
norigtaxmny decimal(28,8) null 
/*��˰�ϼ�*/,
norigdiscount decimal(28,8) null 
/*�ۿ۶�*/,
nprice decimal(28,8) null 
/*��������˰����*/,
ntaxprice decimal(28,8) null 
/*�����Һ�˰����*/,
nnetprice decimal(28,8) null 
/*��������˰����*/,
ntaxnetprice decimal(28,8) null 
/*�����Һ�˰����*/,
ntax decimal(28,8) null 
/*˰��*/,
nmny decimal(28,8) null 
/*������˰���*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount decimal(28,8) null 
/*�����ۿ۶�*/,
nqtorigtaxprice decimal(28,8) null 
/*��˰����*/,
nqtorigprice decimal(28,8) null 
/*��˰����*/,
nqtorigtaxnetprc decimal(28,8) null 
/*��˰����*/,
nqtorignetprice decimal(28,8) null 
/*��˰����*/,
nqttaxnetprice decimal(28,8) null 
/*���Һ�˰����*/,
nqtnetprice decimal(28,8) null 
/*������˰����*/,
nqttaxprice decimal(28,8) null 
/*���Һ�˰����*/,
nqtprice decimal(28,8) null 
/*������˰����*/,
naskqtorigtaxprc decimal(28,8) null 
/*ѯ������˰����*/,
naskqtorigprice decimal(28,8) null 
/*ѯ������˰����*/,
naskqtorigtxntprc decimal(28,8) null 
/*ѯ������˰����*/,
naskqtorignetprice decimal(28,8) null 
/*ѯ������˰����*/,
ngroupmny decimal(28,8) null 
/*���ű�����˰���*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
nglobalmny decimal(28,8) null 
/*ȫ�ֱ�����˰���*/,
nglobaltaxmny decimal(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
blargessflag nchar(1) null 
/*��Ʒ*/,
crowno nvarchar(20) null 
/*�к�*/,
vrownote nvarchar(181) null 
/*�б�ע*/,
frowstatus int null 
/*��״̬*/,
dsenddate nvarchar(19) null 
/*�ƻ���������*/,
dreceivedate nvarchar(19) null 
/*Ҫ�󵽻�����*/,
cpricepolicyid nvarchar(20) null 
/*�۸�����*/,
cpriceitemid nvarchar(20) null 
/*�۸���Ŀ*/,
cpriceitemtableid nvarchar(50) null 
/*��Ŀ��*/,
carrangeid nvarchar(20) null 
/*�������*/,
darrdate nvarchar(19) null 
/*���������*/,
narrnum decimal(28,8) null 
/*�ۼư�������*/,
blineclose nchar(1) null 
/*�йر�*/,
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
cpreorderid nvarchar(20) null 
/*Ԥ��������_����*/,
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
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
 constraint pk_so_preorder_b primary key (cpreorderbid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: Ԥ������������ */
create table so_m38trantype (
pk_trantype nchar(20) not null 
/*Ԥ����������������*/,
bmorerows nchar(1) null 
/*ͬһ����ɷ��ж���*/,
faskqtrule int null default 1 
/*ѯ�۹���*/,
bmodifyaskedqt nchar(1) null 
/*ѯ���۸��Ƿ�ɸ�*/,
bmodifyunaskedqt nchar(1) null 
/*δѯ���۸��Ƿ�ɸ�*/,
flargessgetqtrule int null 
/*��Ʒȡ�۹���*/,
bmodifydiscount nchar(1) null 
/*�����޸��ۿ�*/,
barrange nchar(1) null 
/*ֻ�ܰ���һ��*/,
pk_group nvarchar(20) null 
/*����*/,
vtrantypecode nvarchar(20) null 
/*�������ͱ���*/,
bnofindpricehit nchar(1) null 
/*δѯ���۸��Ƿ���ʾ*/,
fmodifymny int null 
/*�������Ӱ���ۿۻ��ǵ���*/,
ctrantypeid nvarchar(20) null 
/*��������*/,
 constraint pk_so_m38trantype primary key (pk_trantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: Ԥ����Ǩ����־�� */
create table so_m38miglog (
pk_miglog nchar(20) not null 
/*����*/,
fmigstatus int null 
/*Ǩ��״̬*/,
 constraint pk_so_m38miglog primary key (pk_miglog),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

