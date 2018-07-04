/* tablename: ���������� */
create table so_delivery (cdeliveryid char(20) not null 
/*����������ID*/,
pk_group varchar(20) null 
/*����*/,
pk_org varchar(20) null 
/*������֯*/,
pk_org_v varchar(20) null 
/*������֯�汾*/,
vbillcode varchar(40) null 
/*���ݺ�*/,
cbiztypeid varchar(20) null 
/*ҵ������*/,
vtrantypecode varchar(20) null 
/*�������ͱ���*/,
dbilldate char(19) null 
/*��������*/,
csendemployeeid varchar(20) null 
/*�����ƻ�Ա*/,
csenddeptid varchar(20) null 
/*�����������°汾*/,
csenddeptvid varchar(20) null 
/*��������*/,
ctransporttypeid varchar(20) null 
/*���䷽ʽ*/,
ctransportrouteid varchar(50) null 
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
vnote varchar(181) null 
/*��ע*/,
creator varchar(20) null 
/*������*/,
billmaker varchar(20) null 
/*�Ƶ���*/,
creationtime char(19) null 
/*����ʱ��*/,
approver varchar(20) null 
/*������*/,
taudittime varchar(19) null 
/*�������*/,
modifier varchar(20) null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
iprintcount integer null 
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
ctrantypeid varchar(20) null 
/*��������*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
ctradewordid varchar(20) null 
/*ó������*/,
 constraint pk_so_delivery primary key (cdeliveryid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �������ӱ� */
create table so_delivery_b (cdeliverybid char(20) not null 
/*�������ӱ�ID*/,
cdeliveryid varchar(20) null 
/*����������ID*/,
pk_org varchar(20) null 
/*������֯*/,
dbilldate char(19) null 
/*��������*/,
crowno varchar(20) null 
/*�к�*/,
ccustmaterialid varchar(20) null 
/*�ͻ�������*/,
cordercustid varchar(20) null 
/*�����ͻ�*/,
cfreecustid varchar(20) null 
/*ɢ��*/,
cinvoicecustid varchar(20) null 
/*��Ʊ�ͻ�*/,
cmaterialid varchar(20) null 
/*���ϵ���*/,
cmaterialvid varchar(20) null 
/*���ϱ���*/,
cvendorid varchar(20) null 
/*��Ӧ��*/,
cprojectid varchar(20) null 
/*��Ŀ*/,
cproductorid varchar(20) null 
/*��������*/,
castunitid varchar(20) null 
/*��λ*/,
nastnum decimal(28,8) null 
/*����*/,
cunitid varchar(20) null 
/*����λ*/,
nnum decimal(28,8) null 
/*������*/,
vchangerate varchar(60) null 
/*������*/,
cqtunitid varchar(20) null 
/*���۵�λ*/,
nqtunitnum decimal(28,8) null 
/*��������*/,
vqtunitrate varchar(60) null 
/*���ۻ�����*/,
bcheckflag char(1) null 
/*�Ƿ��ѱ���*/,
busecheckflag char(1) null 
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
blargessflag char(1) null 
/*��Ʒ*/,
pk_batchcode varchar(20) null 
/*���ε���*/,
vbatchcode varchar(40) null 
/*���κ�*/,
corigcurrencyid varchar(20) null 
/*ԭ��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ccurrencyid varchar(20) null 
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
vfirsttype varchar(20) null 
/*Դͷ��������*/,
vfirstcode varchar(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype varchar(40) null 
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
csaleorgid varchar(20) null 
/*������֯���°汾*/,
csaleorgvid varchar(20) null 
/*������֯*/,
csendstockorgid varchar(20) null 
/*���������֯���°汾*/,
csendareaid varchar(20) null 
/*��������*/,
csendadddocid varchar(20) null 
/*�����ص�*/,
csendaddrid varchar(20) null 
/*������ַ*/,
csendstordocid varchar(20) null 
/*�����ֿ�*/,
csendstockorgvid varchar(20) null 
/*���������֯*/,
csendpersonid varchar(20) null 
/*������ϵ��*/,
vsendtel varchar(30) null 
/*������ϵ�绰*/,
creceivecustid varchar(20) null 
/*�ջ��ͻ�*/,
creceiveareaid varchar(20) null 
/*�ջ�����*/,
creceiveadddocid varchar(20) null 
/*�ջ��ص�*/,
creceiveaddrid varchar(20) null 
/*�ջ���ַ*/,
cinstockorgid varchar(20) null 
/*�ջ������֯���°�*/,
vreceivetel varchar(30) null 
/*�ջ���ϵ�绰*/,
creceivepersonid varchar(20) null 
/*�ջ���ϵ��*/,
cinstordocid varchar(20) null 
/*�ջ��ֿ�*/,
cinstockorgvid varchar(20) null 
/*�ջ������֯*/,
dsenddate varchar(19) null 
/*��������*/,
dreceivedate varchar(19) null 
/*Ҫ���ջ�����*/,
csupercargoid varchar(20) null 
/*Ѻ��Ա*/,
ctranscustid varchar(20) null 
/*������*/,
cvehicletypeid varchar(20) null 
/*����*/,
cvehicleid varchar(20) null 
/*����*/,
cchauffeurid varchar(20) null 
/*˾��*/,
cspaceid varchar(50) null 
/*��λ*/,
cprodlineid varchar(20) null 
/*��Ʒ��*/,
ntotaltransnum decimal(28,8) null 
/*�ۼ���������*/,
btransendflag char(1) null 
/*����ر�*/,
ntotaloutnum decimal(28,8) null 
/*�ۼƳ�������*/,
bbarsettleflag char(1) null 
/*�������ر�*/,
boutendflag char(1) null 
/*����ر�*/,
frownote varchar(181) null 
/*��ע*/,
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
cqualitylevelid varchar(20) null 
/*�����ȼ�*/,
cprofitcenterid varchar(20) null 
/*���������������°汾*/,
cprofitcentervid varchar(20) null 
/*������������*/,
carorgid varchar(20) null 
/*Ӧ����֯���°汾*/,
carorgvid varchar(20) null 
/*Ӧ����֯*/,
csettleorgid varchar(20) null 
/*���������֯���°汾*/,
cdeptid varchar(20) null 
/*���۲������°汾*/,
cdeptvid varchar(20) null 
/*���۲���*/,
cemployeeid varchar(20) null 
/*����ҵ��Ա*/,
csettleorgvid varchar(20) null 
/*���������֯*/,
cchanneltypeid varchar(20) null 
/*��������*/,
vfirstbilldate varchar(19) null 
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
bqualityflag char(1) null 
/*�Ƿ����ʼ�*/,
badvfeeflag char(1) null 
/*�����˷�*/,
pk_group varchar(20) null 
/*��������*/,
cpriceformid char(20) null 
/*�۸����*/,
cretreasonid char(20) null 
/*�˻�ԭ��*/,
vreturnmode char(20) null 
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
corigcountryid varchar(20) null 
/*ԭ����*/,
corigareaid varchar(20) null 
/*ԭ������*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
csprofitcentervid varchar(20) null 
/*������������*/,
csprofitcenterid varchar(20) null 
/*���������������°汾*/,
crprofitcentervid varchar(20) null 
/*�ջ���������*/,
crprofitcenterid varchar(20) null 
/*�ջ������������°汾*/,
cmffileid varchar(20) null 
/*������*/,
 constraint pk_so_delivery_b primary key (cdeliverybid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �������ʼ�� */
create table so_delivery_check (cdeliverycid char(20) not null 
/*�������ʼ��ID*/,
cdeliverybid varchar(20) null 
/*�������ӱ�ID*/,
crowno varchar(20) null 
/*�к�*/,
cmaterialid varchar(20) null 
/*����*/,
cmaterialvid varchar(20) null 
/*���ϰ汾*/,
cvendorid varchar(20) null 
/*��Ӧ��*/,
cprojectid varchar(20) null 
/*��Ŀ*/,
cqualitylevelid varchar(20) null 
/*�����ȼ�*/,
cproductorid varchar(20) null 
/*��������*/,
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
pk_batchcode varchar(20) null 
/*���ε���*/,
vbatchcode varchar(40) null 
/*���κ�*/,
castunitid varchar(20) null 
/*��λ*/,
nastnum decimal(28,8) null 
/*�ʼ�����*/,
cunitid varchar(20) null 
/*����λ*/,
nnum decimal(28,8) null 
/*�ʼ�������*/,
vchangerate varchar(60) null 
/*������*/,
cqtunitid varchar(20) null 
/*���۵�λ*/,
nqtunitnum decimal(28,8) null 
/*�ʼ챨������*/,
vqtunitrate varchar(60) null 
/*���ۻ�����*/,
vcheckcode varchar(40) null 
/*�ʼ쵥�ݺ�*/,
dcheckdate varchar(19) null 
/*�ʼ�����*/,
ccheckstatebid varchar(20) null 
/*�ʼ�״̬*/,
cdefectprocessid varchar(20) null 
/*���鴦��ʽ*/,
beligflag char(1) null 
/*�Ƿ�ϸ�*/,
bcheckinflag char(1) null 
/*�ʼ챨���Ƿ�����*/,
blargessflag char(1) null 
/*��Ʒ*/,
bpricechgflag char(1) null 
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
corigcurrencyid varchar(20) null 
/*ԭ��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ccurrencyid varchar(20) null 
/*��λ��*/,
ntotaltransnum decimal(28,8) null 
/*�ۼ���������*/,
btransendflag char(1) null 
/*����ر�*/,
ntotaloutnum decimal(28,8) null 
/*�ۼƳ�������*/,
boutendflag char(1) null 
/*����ر�*/,
vrownote varchar(181) null 
/*��ע*/,
vsrcrowno varchar(20) null 
/*���յ��к�*/,
pk_org varchar(20) null 
/*������֯*/,
pk_group varchar(20) null 
/*��������*/,
ntotalnotoutnum decimal(28,8) null 
/*�ۼ�Ӧ��δ����������*/,
csrcid char(20) null 
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
corigcountryid varchar(20) null 
/*ԭ����*/,
corigareaid varchar(20) null 
/*ԭ������*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
 constraint pk__delivery_check primary key (cdeliverycid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �������������� */
create table so_m4331trantype (ctrantypeid char(20) null 
/*��������������*/,
pk_group varchar(20) null 
/*����*/,
vtrantypecode varchar(20) null 
/*��������*/,
bonceoutflag char(1) null 
/*ֻһ�γ���*/,
pk_trantype varchar(20) not null 
/*������������*/,
 constraint pk_o_m4331trantype primary key (pk_trantype),
 ts char(19) null,
dr smallint null default 0
)
;

