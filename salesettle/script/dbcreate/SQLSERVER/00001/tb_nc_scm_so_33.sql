/* tablename: ���۳�����㵥��ʵ�� */
create table so_squareout_b (
csalesquarebid nchar(20) not null 
/*���۳�����㵥��ʵ��*/,
pk_org nvarchar(20) null 
/*���������֯*/,
dbilldate nchar(19) null 
/*���۳��ⵥ��������*/,
vctcode nvarchar(40) null 
/*��ͬ��*/,
deffectdate nchar(19) null 
/*Ӧ�յ���Ч����*/,
dbizdate nvarchar(19) null 
/*���۳��ⵥҵ������*/,
csquarebillid nvarchar(20) null 
/*���۳��ⵥ��ʵ��*/,
csquarebillbid nvarchar(20) null 
/*���۳��ⵥ��ʵ��*/,
vfirstcode nvarchar(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype nvarchar(20) null 
/*Դͷ���ݽ�������*/,
vfirsttype nvarchar(20) null 
/*Դͷ��������*/,
cfirstid nvarchar(20) null 
/*Դͷ��������*/,
cfirstbid nvarchar(20) null 
/*Դͷ�����ӱ�*/,
vfirstrowno nvarchar(20) null 
/*Դͷ�����к�*/,
vsrccode nvarchar(40) null 
/*��Դ���ݺ�*/,
vsrctype nvarchar(20) null 
/*��Դ��������*/,
vsrctrantype nvarchar(20) null 
/*��Դ���ݽ�������*/,
csrcid nvarchar(20) null 
/*��Դ��������*/,
csrcbid nvarchar(20) null 
/*��Դ�����ӱ�*/,
vsrcrowno nvarchar(20) null 
/*��Դ�����к�*/,
ccustmaterialid nvarchar(20) null 
/*�ͻ�������*/,
cmaterialid nvarchar(20) null 
/*����*/,
cmaterialvid nvarchar(20) null 
/*���ϰ汾*/,
cvendorid nvarchar(20) null 
/*��Ӧ��*/,
cproductorid nvarchar(20) null 
/*��������*/,
cprojectid nvarchar(20) null 
/*��ĿID*/,
ccustbankaccid nvarchar(20) null 
/*���������˻�*/,
cordercustid nvarchar(20) null 
/*�����ͻ�*/,
cinvoicecustid nvarchar(20) null 
/*������Ʊ�ͻ�*/,
cfreecustid nvarchar(20) null 
/*ɢ��*/,
cpaytermid nvarchar(20) null 
/*�����ո���Э��*/,
cchanneltypeid nvarchar(20) null 
/*������������*/,
blargessflag nchar(1) null 
/*�Ƿ���Ʒ*/,
carorgid nvarchar(20) null 
/*Ӧ����֯���°汾*/,
carorgvid nvarchar(20) null 
/*Ӧ����֯�汾*/,
cprofitcenterid nvarchar(20) null 
/*���������������°汾*/,
cprofitcentervid nvarchar(20) null 
/*�����������İ汾*/,
ccostorgid nvarchar(20) null 
/*�ɱ���*/,
cdeptid nvarchar(20) null 
/*���۲������°汾*/,
cdeptvid nvarchar(20) null 
/*���۲��Ű汾*/,
csaleorgid nvarchar(20) null 
/*������֯*/,
csaleorgvid nvarchar(20) null 
/*������֯�汾*/,
cemployeeid nvarchar(20) null 
/*����ҵ��Ա*/,
pk_batchcode nvarchar(20) null 
/*���κŵ���*/,
vbatchcode nvarchar(40) null 
/*���κ�*/,
cprolineid nvarchar(20) null 
/*��Ʒ��*/,
vrownote nvarchar(181) null 
/*�б�ע*/,
bsquarearfinish nchar(1) null 
/*�Ƿ�Ӧ�ս������*/,
bsquareiafinish nchar(1) null 
/*�Ƿ�ɱ��������*/,
bincome nchar(1) null 
/*�Ƿ�����������*/,
bcost nchar(1) null 
/*�Ƿ���Գɱ�����*/,
fpreartype int null 
/*�������������*/,
fpreiatype int null 
/*���ɱ���������*/,
nsquarearnum decimal(28,8) null 
/*�ۼ�ȷ��Ӧ������*/,
nsquareestnum decimal(28,8) null 
/*�ۼ��ݹ�Ӧ������*/,
narrushnum decimal(28,8) null 
/*�ۼƻس�Ӧ������*/,
nsquareianum decimal(28,8) null 
/*�ۼƳɱ���������*/,
nsquareregnum decimal(28,8) null 
/*�ۼƷ�����Ʒ����*/,
nrushnum decimal(28,8) null 
/*�ۼƳ���Գ�����*/,
ntotalpaymny decimal(28,8) null 
/*�ۼƳ��⼰���η�Ʊ�տ�������*/,
ndownarmny decimal(28,8) null 
/*�ۼ����η�Ʊȷ��Ӧ�ս��*/,
ndownarnum decimal(28,8) null 
/*�ۼ����η�Ʊȷ��Ӧ������*/,
ndownianum decimal(28,8) null 
/*�ۼ����η�Ʊ�ɱ���������*/,
nsquarearmny decimal(28,8) null 
/*�ۼ�ȷ��Ӧ�ս��*/,
corigcurrencyid nvarchar(20) null 
/*ԭ��*/,
ccurrencyid nvarchar(20) null 
/*����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
cunitid nvarchar(20) null 
/*����λ*/,
castunitid nvarchar(20) null 
/*��λ*/,
vchangerate nvarchar(60) null 
/*��λ������*/,
nnum decimal(28,8) null 
/*����λ����*/,
nastnum decimal(28,8) null 
/*��λ����*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ���*/,
norigtaxprice decimal(28,8) null 
/*ԭ�Һ�˰����*/,
norigprice decimal(28,8) null 
/*ԭ����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*ԭ�Һ�˰����*/,
norignetprice decimal(28,8) null 
/*ԭ����˰����*/,
norigtax decimal(28,8) null 
/*ԭ��˰��*/,
norigmny decimal(28,8) null 
/*ԭ����˰���*/,
norigtaxmny decimal(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
norigdiscount decimal(28,8) null 
/*ԭ���ۿ۶�*/,
ntaxprice decimal(28,8) null 
/*���Һ�˰����*/,
nprice decimal(28,8) null 
/*������˰����*/,
ntaxnetprice decimal(28,8) null 
/*���Һ�˰����*/,
nnetprice decimal(28,8) null 
/*������˰����*/,
ntax decimal(28,8) null 
/*����˰��*/,
nmny decimal(28,8) null 
/*������˰���*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount decimal(28,8) null 
/*�����ۿ۶�*/,
nglobaltaxmny decimal(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny decimal(28,8) null 
/*ȫ�ֱ�����˰���*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny decimal(28,8) null 
/*���ű�����˰���*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
vfree1 nvarchar(101) null 
/*������1*/,
vfree2 nvarchar(101) null 
/*������2*/,
vfree3 nvarchar(101) null 
/*������3*/,
vfree4 nvarchar(101) null 
/*������4*/,
vfree5 nvarchar(101) null 
/*������5*/,
vfree6 nvarchar(101) null 
/*������6*/,
vfree7 nvarchar(101) null 
/*������7*/,
vfree8 nvarchar(101) null 
/*������8*/,
vfree9 nvarchar(101) null 
/*������9*/,
vfree10 nvarchar(101) null 
/*������10*/,
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
csalesquareid nvarchar(20) null 
/*���۳�����㵥��ʵ��_����*/,
pk_group nvarchar(20) null 
/*����*/,
ctaxcodeid nvarchar(20) null 
/*˰��*/,
ftaxtypeflag int null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
csprofitcentervid nvarchar(20) null 
/*������������*/,
csprofitcenterid nvarchar(20) null 
/*���������������°汾*/,
cmffileid nvarchar(20) null 
/*������*/,
 constraint pk_so_squareout_b primary key (csalesquarebid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۳�����㵥��ʵ�� */
create table so_squareout (
csalesquareid nchar(20) not null 
/*���۳�����㵥��ʵ��*/,
pk_org nvarchar(20) null 
/*���������֯*/,
pk_org_v nvarchar(20) null 
/*���������֯�汾*/,
pk_group nvarchar(20) null 
/*����*/,
cbiztypeid nvarchar(20) null 
/*ҵ������*/,
csquarebillid nvarchar(20) null 
/*���۳��ⵥ��ʵ��*/,
vbillcode nvarchar(40) null 
/*���۳��ⵥ���ݺ�*/,
vtrantypecode nvarchar(20) null 
/*���۳��ⵥ��������*/,
dbilldate nchar(19) null 
/*���۳��ⵥ��������*/,
dbillsigndate nchar(19) null 
/*���۳��ⵥǩ������*/,
cwhsmanagerid nvarchar(20) null 
/*���۳��ⵥ���Ա*/,
csendstockorgid nvarchar(20) null 
/*�����֯���°汾*/,
csendstockorgvid nvarchar(20) null 
/*�����֯�汾*/,
csendstordocid nvarchar(20) null 
/*�ֿ�*/,
bautosquareincome nchar(1) null 
/*�Ƿ��Զ��������*/,
bautosquarecost nchar(1) null 
/*�Ƿ��Զ��ɱ�����*/,
breturnoutstock nchar(1) null 
/*����ǩ�տ�Ʊ���˻س��ⵥ��־*/,
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
ctrantypeid nvarchar(20) null 
/*���۳��ⵥ������������*/,
csendcountryid nvarchar(20) null 
/*��������/����*/,
crececountryid nvarchar(20) null 
/*�ջ�����/����*/,
ctaxcountryid nvarchar(20) null 
/*��˰����/����*/,
fbuysellflag int null 
/*��������*/,
btriatradeflag nchar(1) null 
/*����ó��*/,
 constraint pk_so_squareout primary key (csalesquareid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۳�����㵥��ϸʵ�� */
create table so_squareout_d (
csalesquaredid nchar(20) not null 
/*���۳�����㵥��ϸʵ��*/,
pk_org nvarchar(20) null 
/*���������֯*/,
csalesquareid nvarchar(20) null 
/*���۳�����㵥��ʵ��*/,
csalesquarebid nvarchar(20) null 
/*���۳�����㵥��ʵ��*/,
csquarebillid nvarchar(20) null 
/*���۳��ⵥ��ʵ��*/,
csquarebillbid nvarchar(20) null 
/*���۳��ⵥ��ʵ��*/,
dbilldate nchar(19) null 
/*���۳��ⵥ��������*/,
processeid nvarchar(20) null 
/*�������κ�*/,
fsquaretype int null 
/*��������*/,
bautosquareflag nchar(1) null 
/*�Ƿ��Զ�����*/,
nsquarenum decimal(28,8) null 
/*���ν�������*/,
boutrushflag nchar(1) null 
/*�Ƿ����Գ�*/,
vrushbillcode nvarchar(40) null 
/*�Գ���ⵥ��*/,
crushgeneralbid nvarchar(20) null 
/*�Գ���ⵥ�ӱ�id*/,
crushoutbatchid nvarchar(20) null 
/*�Գ����κ�*/,
nastnum decimal(28,8) null 
/*��λ����*/,
nnum decimal(28,8) null 
/*����λ����*/,
vchangerate nvarchar(60) null 
/*��λ������*/,
castunitid nvarchar(20) null 
/*��λ*/,
cunitid nvarchar(20) null 
/*����λ*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
ccurrencyid nvarchar(20) null 
/*����*/,
corigcurrencyid nvarchar(20) null 
/*ԭ��*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ���*/,
norigtaxprice decimal(28,8) null 
/*ԭ�Һ�˰����*/,
norigprice decimal(28,8) null 
/*ԭ����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*ԭ�Һ�˰����*/,
norignetprice decimal(28,8) null 
/*ԭ����˰����*/,
norigtax decimal(28,8) null 
/*ԭ��˰��*/,
norigmny decimal(28,8) null 
/*ԭ����˰���*/,
norigtaxmny decimal(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
norigdiscount decimal(28,8) null 
/*ԭ���ۿ۶�*/,
ntaxprice decimal(28,8) null 
/*���Һ�˰����*/,
nprice decimal(28,8) null 
/*������˰����*/,
ntaxnetprice decimal(28,8) null 
/*���Һ�˰����*/,
nnetprice decimal(28,8) null 
/*������˰����*/,
ntax decimal(28,8) null 
/*����˰��*/,
nmny decimal(28,8) null 
/*������˰���*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount decimal(28,8) null 
/*�����ۿ۶�*/,
nglobaltaxmny decimal(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny decimal(28,8) null 
/*ȫ�ֱ�����˰���*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny decimal(28,8) null 
/*���ű�����˰���*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
pk_group nvarchar(20) null 
/*����*/,
ctaxcodeid nvarchar(20) null 
/*˰��*/,
ftaxtypeflag int null 
/*??˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
ncostprice decimal(28,8) null 
/*�ɱ�����*/,
ncostmny decimal(28,8) null 
/*�ɱ����*/,
 constraint pk_so_squareout_d primary key (csalesquaredid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۷�Ʊ���㵥��ʵ�� */
create table so_squareinv_b (
csalesquarebid nchar(20) not null 
/*���۷�Ʊ���㵥��ʵ��*/,
pk_org nvarchar(20) null 
/*���������֯*/,
csquarebillid nvarchar(20) null 
/*���۷�Ʊ��ʵ��*/,
csquarebillbid nvarchar(20) null 
/*���۷�Ʊ��ʵ��*/,
vctcode nvarchar(40) null 
/*��ͬ��*/,
deffectdate nchar(19) null 
/*Ӧ�յ���Ч����*/,
cprojectid nvarchar(20) null 
/*��Ŀ*/,
dbilldate nchar(19) null 
/*���۷�Ʊ��������*/,
vfirstcode nvarchar(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype nvarchar(20) null 
/*Դͷ���ݽ�������*/,
vfirsttype nvarchar(20) null 
/*Դͷ��������*/,
cfirstid nvarchar(20) null 
/*Դͷ��������*/,
cfirstbid nvarchar(20) null 
/*Դͷ�����ӱ�*/,
vfirstrowno nvarchar(20) null 
/*Դͷ�����к�*/,
vsrccode nvarchar(40) null 
/*��Դ���ݺ�*/,
vsrctype nvarchar(20) null 
/*��Դ��������*/,
vsrctrantype nvarchar(20) null 
/*��Դ���ݽ�������*/,
csrcid nvarchar(20) null 
/*��Դ��������*/,
csrcbid nvarchar(20) null 
/*��Դ�����ӱ�*/,
vsrcrowno nvarchar(20) null 
/*��Դ�����к�*/,
ccustmaterialid nvarchar(20) null 
/*�ͻ�������*/,
cmaterialid nvarchar(20) null 
/*����*/,
cmaterialvid nvarchar(20) null 
/*���ϰ汾*/,
cvendorid nvarchar(20) null 
/*��Ӧ��*/,
cproductorid nvarchar(20) null 
/*��������*/,
cordercustid nvarchar(20) null 
/*�����ͻ�*/,
cchanneltypeid nvarchar(20) null 
/*������������*/,
cfreecustid nvarchar(20) null 
/*ɢ��*/,
blargessflag nchar(1) null 
/*�Ƿ���Ʒ*/,
blaborflag nchar(1) null 
/*�Ƿ������*/,
bdiscountflag nchar(1) null 
/*�Ƿ��ۿ���*/,
carorgid nvarchar(20) null 
/*Ӧ����֯���°汾*/,
carorgvid nvarchar(20) null 
/*Ӧ����֯�汾*/,
cprofitcenterid nvarchar(20) null 
/*�����������°汾*/,
cprofitcentervid nvarchar(20) null 
/*�������İ汾*/,
ccostorgid nvarchar(20) null 
/*�ɱ���*/,
csendstockorgid nvarchar(20) null 
/*�����֯���°汾*/,
csendstockorgvid nvarchar(20) null 
/*�����֯�汾*/,
csendstordocid nvarchar(20) null 
/*�ֿ�*/,
cdeptid nvarchar(20) null 
/*���۲������°汾*/,
cdeptvid nvarchar(20) null 
/*���۲��Ű汾*/,
csaleorgid nvarchar(20) null 
/*������֯���°汾*/,
csaleorgvid nvarchar(20) null 
/*������֯�汾*/,
cemployeeid nvarchar(20) null 
/*����ҵ��Ա*/,
pk_batchcode nvarchar(20) null 
/*���κŵ���*/,
vbatchcode nvarchar(40) null 
/*���κ�*/,
cprolineid nvarchar(20) null 
/*��Ʒ��*/,
vrownote nvarchar(181) null 
/*�б�ע*/,
bsquarearfinish nchar(1) null 
/*�Ƿ�Ӧ�ս������*/,
bsquareiafinish nchar(1) null 
/*�Ƿ�ɱ��������*/,
bincome nchar(1) null 
/*�Ƿ�����������*/,
bcost nchar(1) null 
/*�Ƿ���Գɱ�����*/,
fpreartype int null 
/*�������������*/,
fpreiatype int null 
/*���ɱ���������*/,
nsquarearnum decimal(28,8) null 
/*�ۼ�ȷ��Ӧ������*/,
narrushnum decimal(28,8) null 
/*�ۼƻس�Ӧ������*/,
nsquareianum decimal(28,8) null 
/*�ۼƳɱ���������*/,
nsquareregnum decimal(28,8) null 
/*�ۼƷ�����Ʒ����*/,
corigcurrencyid nvarchar(20) null 
/*ԭ��*/,
ccurrencyid nvarchar(20) null 
/*����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
cunitid nvarchar(20) null 
/*����λ*/,
castunitid nvarchar(20) null 
/*��λ*/,
vchangerate nvarchar(60) null 
/*��λ������*/,
nnum decimal(28,8) null 
/*����λ����*/,
nastnum decimal(28,8) null 
/*��λ����*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ���*/,
norigtaxprice decimal(28,8) null 
/*ԭ�Һ�˰����*/,
norigprice decimal(28,8) null 
/*ԭ����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*ԭ�Һ�˰����*/,
norignetprice decimal(28,8) null 
/*ԭ����˰����*/,
norigtax decimal(28,8) null 
/*ԭ��˰��*/,
norigmny decimal(28,8) null 
/*ԭ����˰���*/,
norigtaxmny decimal(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
norigdiscount decimal(28,8) null 
/*ԭ���ۿ۶�*/,
ntaxprice decimal(28,8) null 
/*���Һ�˰����*/,
nprice decimal(28,8) null 
/*������˰����*/,
ntaxnetprice decimal(28,8) null 
/*���Һ�˰����*/,
nnetprice decimal(28,8) null 
/*������˰����*/,
ntax decimal(28,8) null 
/*����˰��*/,
nmny decimal(28,8) null 
/*������˰���*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount decimal(28,8) null 
/*�����ۿ۶�*/,
nglobaltaxmny decimal(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny decimal(28,8) null 
/*ȫ�ֱ�����˰���*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny decimal(28,8) null 
/*���ű�����˰���*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
vfree1 nvarchar(101) null 
/*������1*/,
vfree2 nvarchar(101) null 
/*������2*/,
vfree3 nvarchar(101) null 
/*������3*/,
vfree4 nvarchar(101) null 
/*������4*/,
vfree5 nvarchar(101) null 
/*������5*/,
vfree6 nvarchar(101) null 
/*������6*/,
vfree7 nvarchar(101) null 
/*������7*/,
vfree8 nvarchar(101) null 
/*������8*/,
vfree9 nvarchar(101) null 
/*������9*/,
vfree10 nvarchar(101) null 
/*������10*/,
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
csalesquareid nvarchar(20) null 
/*���۷�Ʊ���㵥��ʵ��_����*/,
pk_group nvarchar(20) null 
/*����*/,
ctaxcodeid nvarchar(20) null 
/*˰��*/,
ftaxtypeflag int null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
ccostsubjid nvarchar(20) null 
/*��֧��Ŀ*/,
csprofitcentervid nvarchar(20) null 
/*������������*/,
csprofitcenterid nvarchar(20) null 
/*���������������°�*/,
cmffileid nvarchar(20) null 
/*������*/,
 constraint pk_so_squareinv_b primary key (csalesquarebid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۷�Ʊ���㵥��ʵ�� */
create table so_squareinv (
csalesquareid nchar(20) not null 
/*���۷�Ʊ���㵥��ʵ��*/,
pk_org nvarchar(20) null 
/*���������֯*/,
pk_org_v nvarchar(20) null 
/*���������֯�汾*/,
pk_group nvarchar(20) null 
/*����*/,
cbiztypeid nvarchar(20) null 
/*ҵ������*/,
ccustbankaccid nvarchar(20) null 
/*���������˻�*/,
csquarebillid nvarchar(20) null 
/*���۷�Ʊ��ʵ��*/,
cinvoicecustid nvarchar(20) null 
/*��Ʊ�ͻ�*/,
vbillcode nvarchar(40) null 
/*���۷�Ʊ���ݺ�*/,
vtrantypecode nvarchar(20) null 
/*���۷�Ʊ���ͱ���*/,
dbilldate nchar(19) null 
/*���۷�Ʊ��������*/,
dbillapprovedate nchar(19) null 
/*���۷�Ʊ�������*/,
cpaytermid nvarchar(20) null 
/*��Ʊ�ո���Э��*/,
bautosquareincome nchar(1) null 
/*�Ƿ��Զ��������*/,
bautosquarecost nchar(1) null 
/*�Ƿ��Զ��ɱ�����*/,
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
ctrantypeid nvarchar(20) null 
/*���۷�Ʊ����*/,
csendcountryid nvarchar(20) null 
/*��������/����*/,
crececountryid nvarchar(20) null 
/*�ջ�����/����*/,
ctaxcountryid nvarchar(20) null 
/*��˰����/����*/,
fbuysellflag int null 
/*��������*/,
btriatradeflag nchar(1) null 
/*����ó��*/,
vvatcode nvarchar(40) null 
/*VATע����*/,
vcustvatcode nvarchar(40) null 
/*�ͻ�VATע����*/,
cbalancetypeid nvarchar(20) null 
/*���㷽ʽ*/,
 constraint pk_so_squareinv primary key (csalesquareid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۷�Ʊ���㵥��ϸʵ�� */
create table so_squareinv_d (
csalesquaredid nchar(20) not null 
/*���۷�Ʊ���㵥��ϸʵ��*/,
pk_org nvarchar(20) null 
/*���������֯*/,
csalesquareid nvarchar(20) null 
/*���۷�Ʊ���㵥��ʵ��*/,
csalesquarebid nvarchar(20) null 
/*���۷�Ʊ���㵥��ʵ��*/,
csquarebillid nvarchar(20) null 
/*���۷�Ʊ��ʵ��*/,
csquarebillbid nvarchar(20) null 
/*���۷�Ʊ��ʵ��*/,
dbilldate nchar(19) null 
/*���۷�Ʊ��������*/,
processeid nvarchar(20) null 
/*�������κ�*/,
fsquaretype int null 
/*��������*/,
nsquarenum decimal(28,8) null 
/*���ν�������*/,
nastnum decimal(28,8) null 
/*��λ����*/,
nnum decimal(28,8) null 
/*����λ����*/,
vchangerate nvarchar(60) null 
/*��λ������*/,
castunitid nvarchar(20) null 
/*��λ*/,
cunitid nvarchar(20) null 
/*����λ*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
ccurrencyid nvarchar(20) null 
/*����*/,
corigcurrencyid nvarchar(20) null 
/*ԭ��*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ���*/,
norigtaxprice decimal(28,8) null 
/*ԭ�Һ�˰����*/,
norigprice decimal(28,8) null 
/*ԭ����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*ԭ�Һ�˰����*/,
norignetprice decimal(28,8) null 
/*ԭ����˰����*/,
norigtax decimal(28,8) null 
/*ԭ��˰��*/,
norigmny decimal(28,8) null 
/*ԭ����˰���*/,
norigtaxmny decimal(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
norigdiscount decimal(28,8) null 
/*ԭ���ۿ۶�*/,
ntaxprice decimal(28,8) null 
/*���Һ�˰����*/,
nprice decimal(28,8) null 
/*������˰����*/,
ntaxnetprice decimal(28,8) null 
/*���Һ�˰����*/,
nnetprice decimal(28,8) null 
/*������˰����*/,
ntax decimal(28,8) null 
/*����˰��*/,
nmny decimal(28,8) null 
/*������˰���*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount decimal(28,8) null 
/*�����ۿ۶�*/,
nglobaltaxmny decimal(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny decimal(28,8) null 
/*ȫ�ֱ�����˰���*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny decimal(28,8) null 
/*���ű�����˰���*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
pk_group nvarchar(20) null 
/*����*/,
ctaxcodeid nvarchar(20) null 
/*˰��*/,
ftaxtypeflag int null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
 constraint pk_so_squareinv_d primary key (csalesquaredid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ;����㵥��ʵ�� */
create table so_squarewas_b (
csalesquarebid nchar(20) not null 
/*;����㵥��ʵ��*/,
pk_org nvarchar(20) null 
/*���������֯*/,
dbilldate nchar(19) null 
/*;�𵥵�������*/,
dbizdate nchar(19) null 
/*���۳��ⵥҵ������*/,
vctcode nvarchar(40) null 
/*��ͬ��*/,
deffectdate nchar(19) null 
/*Ӧ�յ���Ч����*/,
csquarebillid nvarchar(20) null 
/*;����ʵ��*/,
csquarebillbid nvarchar(20) null 
/*;����ʵ��*/,
vfirstcode nvarchar(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype nvarchar(20) null 
/*Դͷ���ݽ�������*/,
vfirsttype nvarchar(20) null 
/*Դͷ��������*/,
cfirstid nvarchar(20) null 
/*Դͷ��������*/,
cfirstbid nvarchar(20) null 
/*Դͷ�����ӱ�*/,
vfirstrowno nvarchar(20) null 
/*Դͷ�����к�*/,
vsrccode nvarchar(40) null 
/*��Դ���ݺ�*/,
vsrctype nvarchar(20) null 
/*��Դ��������*/,
vsrctrantype nvarchar(20) null 
/*��Դ���ݽ�������*/,
csrcid nvarchar(20) null 
/*��Դ��������*/,
csrcbid nvarchar(20) null 
/*��Դ�����ӱ�*/,
vsrcrowno nvarchar(20) null 
/*��Դ�����к�*/,
ccustmaterialid nvarchar(20) null 
/*�ͻ�������*/,
cmaterialid nvarchar(20) null 
/*����*/,
cmaterialvid nvarchar(20) null 
/*���ϰ汾*/,
cvendorid nvarchar(20) null 
/*��Ӧ��*/,
cproductorid nvarchar(20) null 
/*��������*/,
cprojectid nvarchar(20) null 
/*��ĿID*/,
cordercustid nvarchar(20) null 
/*�����ͻ�*/,
ccustbankaccid nvarchar(20) null 
/*���������˻�*/,
cinvoicecustid nvarchar(20) null 
/*������Ʊ�ͻ�*/,
cfreecustid nchar(20) null 
/*ɢ��*/,
cpaytermid nvarchar(20) null 
/*�����ո���Э��*/,
cchanneltypeid nvarchar(20) null 
/*������������*/,
blargessflag nchar(1) null 
/*�Ƿ���Ʒ*/,
carorgid nvarchar(20) null 
/*Ӧ����֯���°汾*/,
carorgvid nvarchar(20) null 
/*Ӧ����֯�汾*/,
cprofitcenterid nvarchar(20) null 
/*�����������°汾*/,
cprofitcentervid nvarchar(20) null 
/*�������İ汾*/,
ccostorgid nvarchar(20) null 
/*�ɱ���*/,
cdeptid nvarchar(20) null 
/*���۲������°汾*/,
cdeptvid nvarchar(20) null 
/*���۲��Ű汾*/,
csaleorgid nvarchar(20) null 
/*������֯���°汾*/,
csaleorgvid nvarchar(20) null 
/*������֯�汾*/,
cemployeeid nvarchar(20) null 
/*����ҵ��Ա*/,
pk_batchcode nvarchar(20) null 
/*���κŵ���*/,
vbatchcode nvarchar(40) null 
/*���κ�*/,
cprolineid nvarchar(20) null 
/*��Ʒ��*/,
vrownote nvarchar(181) null 
/*�б�ע*/,
bsquarearfinish nchar(1) null 
/*�Ƿ�Ӧ�ս������*/,
bsquareiafinish nchar(1) null 
/*�Ƿ�ɱ��������*/,
bincome nchar(1) null 
/*�Ƿ�����������*/,
bcost nchar(1) null 
/*�Ƿ���Գɱ�����*/,
fpreartype int null 
/*�������������*/,
fpreiatype int null 
/*���ɱ���������*/,
nsquarearnum decimal(28,8) null 
/*�ۼ�ȷ��Ӧ������*/,
narrushnum decimal(28,8) null 
/*�ۼƻس�Ӧ������*/,
nsquareianum decimal(28,8) null 
/*�ۼƳɱ���������*/,
nsquareregnum decimal(28,8) null 
/*�ۼƷ�����Ʒ����*/,
corigcurrencyid nvarchar(20) null 
/*ԭ��*/,
ccurrencyid nvarchar(20) null 
/*����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
cunitid nvarchar(20) null 
/*����λ*/,
castunitid nvarchar(20) null 
/*��λ*/,
vchangerate nvarchar(60) null 
/*��λ������*/,
nnum decimal(28,8) null 
/*����λ����*/,
nastnum decimal(28,8) null 
/*��λ����*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ���*/,
norigtaxprice decimal(28,8) null 
/*ԭ�Һ�˰����*/,
norigprice decimal(28,8) null 
/*ԭ����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*ԭ�Һ�˰����*/,
norignetprice decimal(28,8) null 
/*ԭ����˰����*/,
norigtax decimal(28,8) null 
/*ԭ��˰��*/,
norigmny decimal(28,8) null 
/*ԭ����˰���*/,
norigtaxmny decimal(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
norigdiscount decimal(28,8) null 
/*ԭ���ۿ۶�*/,
ntaxprice decimal(28,8) null 
/*���Һ�˰����*/,
nprice decimal(28,8) null 
/*������˰����*/,
ntaxnetprice decimal(28,8) null 
/*���Һ�˰����*/,
nnetprice decimal(28,8) null 
/*������˰����*/,
ntax decimal(28,8) null 
/*����˰��*/,
nmny decimal(28,8) null 
/*������˰���*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount decimal(28,8) null 
/*�����ۿ۶�*/,
nglobaltaxmny decimal(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny decimal(28,8) null 
/*ȫ�ֱ�����˰���*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny decimal(28,8) null 
/*���ű�����˰���*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
vfree1 nvarchar(101) null 
/*������1*/,
vfree2 nvarchar(101) null 
/*������2*/,
vfree3 nvarchar(101) null 
/*������3*/,
vfree4 nvarchar(101) null 
/*������4*/,
vfree5 nvarchar(101) null 
/*������5*/,
vfree6 nvarchar(101) null 
/*������6*/,
vfree7 nvarchar(101) null 
/*������7*/,
vfree8 nvarchar(101) null 
/*������8*/,
vfree9 nvarchar(101) null 
/*������9*/,
vfree10 nvarchar(101) null 
/*������10*/,
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
csalesquareid nvarchar(20) null 
/*;����㵥��ʵ��_����*/,
pk_group nvarchar(20) null 
/*����*/,
ctaxcodeid nvarchar(20) null 
/*˰��*/,
ftaxtypeflag int null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
cmffileid nvarchar(20) null 
/*������*/,
 constraint pk_so_squarewas_b primary key (csalesquarebid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ;����㵥��ʵ�� */
create table so_squarewas (
csalesquareid nchar(20) not null 
/*;����㵥��ʵ��*/,
pk_org nvarchar(20) null 
/*���������֯*/,
pk_org_v nvarchar(20) null 
/*���������֯�汾*/,
pk_group nvarchar(20) null 
/*����*/,
cbiztypeid nvarchar(20) null 
/*ҵ������*/,
csquarebillid nvarchar(20) null 
/*;����ʵ��*/,
vbillcode nvarchar(40) null 
/*;�𵥵��ݺ�*/,
vtrantypecode nvarchar(20) null 
/*;�𵥽�������*/,
dbilldate nchar(19) null 
/*;�𵥵�������*/,
cwhsmanagerid nvarchar(20) null 
/*���۳��ⵥ���Ա*/,
bautosquareincome nchar(1) null 
/*�Ƿ��Զ��������*/,
bautosquarecost nchar(1) null 
/*�Ƿ��Զ��ɱ�����*/,
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
csendstockorgid nvarchar(20) null 
/*�����֯���°汾*/,
csendstockorgvid nvarchar(20) null 
/*�����֯�汾*/,
csendstordocid nvarchar(20) null 
/*�ֿ�*/,
btriatradeflag nchar(1) null 
/*����ó��*/,
crececountryid nvarchar(20) null 
/*�ջ�����/����*/,
ctaxcountryid nvarchar(20) null 
/*��˰����/����*/,
ctrantypeid nvarchar(20) null 
/*;�𵥽�����������*/,
csendcountryid nvarchar(20) null 
/*��������/����*/,
fbuysellflag int null 
/*��������*/,
 constraint pk_so_sqwas primary key (csalesquareid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ;����㵥��ϸʵ�� */
create table so_squarewas_d (
csalesquaredid nchar(20) not null 
/*;����㵥��ϸʵ��*/,
pk_org nvarchar(20) null 
/*���������֯*/,
csalesquareid nvarchar(20) null 
/*;����㵥��ʵ��*/,
csalesquarebid nvarchar(20) null 
/*;����㵥��ʵ��*/,
csquarebillid nvarchar(20) null 
/*;����ʵ��*/,
csquarebillbid nvarchar(20) null 
/*;����ʵ��*/,
dbilldate nchar(19) null 
/*;�𵥵�������*/,
processeid nvarchar(20) null 
/*�������κ�*/,
fsquaretype int null 
/*��������*/,
nsquarenum decimal(28,8) null 
/*���ν�������*/,
nastnum decimal(28,8) null 
/*��λ����*/,
nnum decimal(28,8) null 
/*����λ����*/,
vchangerate nvarchar(60) null 
/*��λ������*/,
castunitid nvarchar(20) null 
/*��λ*/,
cunitid nvarchar(20) null 
/*����λ*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
ccurrencyid nvarchar(20) null 
/*����*/,
corigcurrencyid nvarchar(20) null 
/*ԭ��*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ���*/,
norigtaxprice decimal(28,8) null 
/*ԭ�Һ�˰����*/,
norigprice decimal(28,8) null 
/*ԭ����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*ԭ�Һ�˰����*/,
norignetprice decimal(28,8) null 
/*ԭ����˰����*/,
norigtax decimal(28,8) null 
/*ԭ��˰��*/,
norigmny decimal(28,8) null 
/*ԭ����˰���*/,
norigtaxmny decimal(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
norigdiscount decimal(28,8) null 
/*ԭ���ۿ۶�*/,
ntaxprice decimal(28,8) null 
/*���Һ�˰����*/,
nprice decimal(28,8) null 
/*������˰����*/,
ntaxnetprice decimal(28,8) null 
/*���Һ�˰����*/,
nnetprice decimal(28,8) null 
/*������˰����*/,
ntax decimal(28,8) null 
/*����˰��*/,
nmny decimal(28,8) null 
/*������˰���*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount decimal(28,8) null 
/*�����ۿ۶�*/,
nglobaltaxmny decimal(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny decimal(28,8) null 
/*ȫ�ֱ�����˰���*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny decimal(28,8) null 
/*���ű�����˰���*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
pk_group nvarchar(20) null 
/*����*/,
ctaxcodeid nvarchar(20) null 
/*˰��*/,
ftaxtypeflag int null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
 constraint pk_so_squarewas_d primary key (csalesquaredid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

