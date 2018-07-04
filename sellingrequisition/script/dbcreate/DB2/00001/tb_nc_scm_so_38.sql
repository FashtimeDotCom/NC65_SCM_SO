/* tablename: Ԥ�������� */
create table so_preorder (cpreorderid char(20) not null 
/*Ԥ��������*/,
vbillcode varchar(40) null 
/*���ݺ�*/,
pk_org varchar(20) null 
/*������֯*/,
pk_org_v varchar(20) null 
/*������֯�汾*/,
pk_group varchar(20) null 
/*����*/,
ctrantypeid varchar(20) null 
/*Ԥ��������*/,
vtrantypecode varchar(20) null 
/*Ԥ�������ͱ���*/,
cdeptid varchar(20) null 
/*���۲������°汾*/,
cdeptvid varchar(20) null 
/*���۲���*/,
cemployeeid varchar(20) null 
/*ҵ��Ա*/,
ccustomerid varchar(20) null 
/*�ͻ�*/,
cchanneltypeid varchar(20) null 
/*������������*/,
cpaytermid varchar(20) null 
/*�տ�Э��*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
cinvoicecustid varchar(20) null 
/*��Ʊ�ͻ�*/,
ctransporttypeid varchar(20) null 
/*���䷽ʽ*/,
corigcurrencyid varchar(20) null 
/*����*/,
ntotalnum decimal(28,8) null 
/*������*/,
nheadsummny decimal(28,8) null 
/*��˰�ϼ�*/,
dbilldate char(19) null 
/*��������*/,
dabatedate varchar(19) null 
/*ʧЧ����*/,
fstatusflag integer null default 1 
/*����״̬*/,
vsrctype varchar(20) null 
/*��Դ����*/,
cweboperatorid varchar(20) null 
/*���϶����Ƶ���*/,
billmaker varchar(20) null 
/*�Ƶ���*/,
creator varchar(20) null 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
approver varchar(20) null 
/*������*/,
taudittime char(19) null 
/*��������*/,
modifier varchar(20) null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
iprintcount integer null default 0 
/*��ӡ����*/,
vnote varchar(181) null 
/*��ע*/,
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
 constraint pk_so_preorder primary key (cpreorderid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: Ԥ�����ӱ� */
create table so_preorder_b (cpreorderbid char(20) not null 
/*Ԥ�����ӱ�����*/,
pk_group varchar(20) null 
/*����*/,
pk_org varchar(20) null 
/*������֯*/,
ccustmaterialid varchar(20) null 
/*�ͻ�������*/,
cmaterialid varchar(20) null 
/*���ϵ���*/,
cmaterialvid varchar(20) null 
/*���ϱ���*/,
dbilldate char(19) null 
/*��������*/,
cunitid varchar(20) null 
/*����λ*/,
castunitid varchar(20) null 
/*��λ*/,
nnum decimal(28,8) null 
/*������*/,
nastnum decimal(28,8) null 
/*����*/,
vchangerate varchar(60) null 
/*������*/,
cqtunitid varchar(20) null 
/*���۵�λ*/,
nqtunitnum decimal(28,8) null 
/*��������*/,
vqtunitrate varchar(60) null 
/*���ۻ�����*/,
cvendorid varchar(20) null 
/*��Ӧ��*/,
cprojectid varchar(20) null 
/*��Ŀ*/,
cqualitylevelid varchar(20) null 
/*�����ȼ�*/,
cproductorid varchar(20) null 
/*��������*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ�*/,
csendstockorgid varchar(20) null 
/*���������֯���°汾*/,
csendstockorgvid varchar(20) null 
/*���������֯*/,
csendstordocid varchar(20) null 
/*�����ֿ�*/,
ctrafficorgid varchar(20) null 
/*������֯���°汾*/,
ctrafficorgvid varchar(20) null 
/*������֯*/,
csettleorgid varchar(20) null 
/*���������֯���°汾*/,
csettleorgvid varchar(20) null 
/*���������֯*/,
carorgid varchar(20) null 
/*Ӧ����֯���°汾*/,
carorgvid varchar(20) null 
/*Ӧ����֯*/,
cprofitcenterid varchar(20) null 
/*�����������°汾*/,
cprofitcentervid varchar(20) null 
/*��������*/,
creceivecustid varchar(20) null 
/*�ջ��ͻ�*/,
creceiveareaid varchar(20) null 
/*�ջ�����*/,
creceivesiteid varchar(20) null 
/*�ջ��ص�*/,
creceiveaddrid varchar(20) null 
/*�ջ���ַ*/,
cpriceformid varchar(20) null 
/*�۸����*/,
ccurrencyid varchar(20) null 
/*��λ��*/,
corigcurrencyid varchar(20) null 
/*����*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
vfree1 varchar(101) null 
/*���ɸ�������1*/,
vfree2 varchar(101) null 
/*���ɸ�������2*/,
vfree3 varchar(101) null 
/*���ɸ�������3*/,
vfree4 varchar(101) null 
/*���ɸ�������4*/,
vfree5 varchar(101) null 
/*���ɸ�������5*/,
vfree6 varchar(101) null 
/*���ɸ�������6*/,
vfree7 varchar(101) null 
/*���ɸ�������7*/,
vfree8 varchar(101) null 
/*���ɸ�������8*/,
vfree9 varchar(101) null 
/*���ɸ�������9*/,
vfree10 varchar(101) null 
/*���ɸ�������10*/,
vbatchcode varchar(40) null 
/*����*/,
pk_batchcode varchar(20) null 
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
blargessflag char(1) null 
/*��Ʒ*/,
crowno varchar(20) null 
/*�к�*/,
vrownote varchar(181) null 
/*�б�ע*/,
frowstatus integer null 
/*��״̬*/,
dsenddate varchar(19) null 
/*�ƻ���������*/,
dreceivedate varchar(19) null 
/*Ҫ�󵽻�����*/,
cpricepolicyid varchar(20) null 
/*�۸�����*/,
cpriceitemid varchar(20) null 
/*�۸���Ŀ*/,
cpriceitemtableid varchar(50) null 
/*��Ŀ��*/,
carrangeid varchar(20) null 
/*�������*/,
darrdate varchar(19) null 
/*���������*/,
narrnum decimal(28,8) null 
/*�ۼư�������*/,
blineclose char(1) null 
/*�йر�*/,
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
cpreorderid varchar(20) null 
/*Ԥ��������_����*/,
crececountryid varchar(20) null 
/*�ջ�����/����*/,
csendcountryid varchar(20) null 
/*������/����*/,
ctaxcountryid varchar(20) null 
/*��˰��/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
ctaxcodeid varchar(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
 constraint pk_so_preorder_b primary key (cpreorderbid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: Ԥ������������ */
create table so_m38trantype (pk_trantype char(20) not null 
/*Ԥ����������������*/,
bmorerows char(1) null 
/*ͬһ����ɷ��ж���*/,
faskqtrule integer null default 1 
/*ѯ�۹���*/,
bmodifyaskedqt char(1) null 
/*ѯ���۸��Ƿ�ɸ�*/,
bmodifyunaskedqt char(1) null 
/*δѯ���۸��Ƿ�ɸ�*/,
flargessgetqtrule integer null 
/*��Ʒȡ�۹���*/,
bmodifydiscount char(1) null 
/*�����޸��ۿ�*/,
barrange char(1) null 
/*ֻ�ܰ���һ��*/,
pk_group varchar(20) null 
/*����*/,
vtrantypecode varchar(20) null 
/*�������ͱ���*/,
bnofindpricehit char(1) null 
/*δѯ���۸��Ƿ���ʾ*/,
fmodifymny integer null 
/*�������Ӱ���ۿۻ��ǵ���*/,
ctrantypeid varchar(20) null 
/*��������*/,
 constraint pk_so_m38trantype primary key (pk_trantype),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: Ԥ����Ǩ����־�� */
create table so_m38miglog (pk_miglog char(20) not null 
/*����*/,
fmigstatus integer null 
/*Ǩ��״̬*/,
 constraint pk_so_m38miglog primary key (pk_miglog),
 ts char(19) null,
dr smallint null default 0
)
;

