/* tablename: ���۳�����㵥��ʵ�� */
create table so_squareout_b (csalesquarebid char(20) not null 
/*���۳�����㵥��ʵ��*/,
pk_org varchar(20) null 
/*���������֯*/,
dbilldate char(19) null 
/*���۳��ⵥ��������*/,
vctcode varchar(40) null 
/*��ͬ��*/,
deffectdate char(19) null 
/*Ӧ�յ���Ч����*/,
dbizdate varchar(19) null 
/*���۳��ⵥҵ������*/,
csquarebillid varchar(20) null 
/*���۳��ⵥ��ʵ��*/,
csquarebillbid varchar(20) null 
/*���۳��ⵥ��ʵ��*/,
vfirstcode varchar(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype varchar(20) null 
/*Դͷ���ݽ�������*/,
vfirsttype varchar(20) null 
/*Դͷ��������*/,
cfirstid varchar(20) null 
/*Դͷ��������*/,
cfirstbid varchar(20) null 
/*Դͷ�����ӱ�*/,
vfirstrowno varchar(20) null 
/*Դͷ�����к�*/,
vsrccode varchar(40) null 
/*��Դ���ݺ�*/,
vsrctype varchar(20) null 
/*��Դ��������*/,
vsrctrantype varchar(20) null 
/*��Դ���ݽ�������*/,
csrcid varchar(20) null 
/*��Դ��������*/,
csrcbid varchar(20) null 
/*��Դ�����ӱ�*/,
vsrcrowno varchar(20) null 
/*��Դ�����к�*/,
ccustmaterialid varchar(20) null 
/*�ͻ�������*/,
cmaterialid varchar(20) null 
/*����*/,
cmaterialvid varchar(20) null 
/*���ϰ汾*/,
cvendorid varchar(20) null 
/*��Ӧ��*/,
cproductorid varchar(20) null 
/*��������*/,
cprojectid varchar(20) null 
/*��ĿID*/,
ccustbankaccid varchar(20) null 
/*���������˻�*/,
cordercustid varchar(20) null 
/*�����ͻ�*/,
cinvoicecustid varchar(20) null 
/*������Ʊ�ͻ�*/,
cfreecustid varchar(20) null 
/*ɢ��*/,
cpaytermid varchar(20) null 
/*�����ո���Э��*/,
cchanneltypeid varchar(20) null 
/*������������*/,
blargessflag char(1) null 
/*�Ƿ���Ʒ*/,
carorgid varchar(20) null 
/*Ӧ����֯���°汾*/,
carorgvid varchar(20) null 
/*Ӧ����֯�汾*/,
cprofitcenterid varchar(20) null 
/*���������������°汾*/,
cprofitcentervid varchar(20) null 
/*�����������İ汾*/,
ccostorgid varchar(20) null 
/*�ɱ���*/,
cdeptid varchar(20) null 
/*���۲������°汾*/,
cdeptvid varchar(20) null 
/*���۲��Ű汾*/,
csaleorgid varchar(20) null 
/*������֯*/,
csaleorgvid varchar(20) null 
/*������֯�汾*/,
cemployeeid varchar(20) null 
/*����ҵ��Ա*/,
pk_batchcode varchar(20) null 
/*���κŵ���*/,
vbatchcode varchar(40) null 
/*���κ�*/,
cprolineid varchar(20) null 
/*��Ʒ��*/,
vrownote varchar(181) null 
/*�б�ע*/,
bsquarearfinish char(1) null 
/*�Ƿ�Ӧ�ս������*/,
bsquareiafinish char(1) null 
/*�Ƿ�ɱ��������*/,
bincome char(1) null 
/*�Ƿ�����������*/,
bcost char(1) null 
/*�Ƿ���Գɱ�����*/,
fpreartype integer null 
/*�������������*/,
fpreiatype integer null 
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
corigcurrencyid varchar(20) null 
/*ԭ��*/,
ccurrencyid varchar(20) null 
/*����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
cunitid varchar(20) null 
/*����λ*/,
castunitid varchar(20) null 
/*��λ*/,
vchangerate varchar(60) null 
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
vfree1 varchar(101) null 
/*������1*/,
vfree2 varchar(101) null 
/*������2*/,
vfree3 varchar(101) null 
/*������3*/,
vfree4 varchar(101) null 
/*������4*/,
vfree5 varchar(101) null 
/*������5*/,
vfree6 varchar(101) null 
/*������6*/,
vfree7 varchar(101) null 
/*������7*/,
vfree8 varchar(101) null 
/*������8*/,
vfree9 varchar(101) null 
/*������9*/,
vfree10 varchar(101) null 
/*������10*/,
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
csalesquareid varchar(20) null 
/*���۳�����㵥��ʵ��_����*/,
pk_group varchar(20) null 
/*����*/,
ctaxcodeid varchar(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
csprofitcentervid varchar(20) null 
/*������������*/,
csprofitcenterid varchar(20) null 
/*���������������°汾*/,
cmffileid varchar(20) null 
/*������*/,
 constraint pk_so_squareout_b primary key (csalesquarebid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۳�����㵥��ʵ�� */
create table so_squareout (csalesquareid char(20) not null 
/*���۳�����㵥��ʵ��*/,
pk_org varchar(20) null 
/*���������֯*/,
pk_org_v varchar(20) null 
/*���������֯�汾*/,
pk_group varchar(20) null 
/*����*/,
cbiztypeid varchar(20) null 
/*ҵ������*/,
csquarebillid varchar(20) null 
/*���۳��ⵥ��ʵ��*/,
vbillcode varchar(40) null 
/*���۳��ⵥ���ݺ�*/,
vtrantypecode varchar(20) null 
/*���۳��ⵥ��������*/,
dbilldate char(19) null 
/*���۳��ⵥ��������*/,
dbillsigndate char(19) null 
/*���۳��ⵥǩ������*/,
cwhsmanagerid varchar(20) null 
/*���۳��ⵥ���Ա*/,
csendstockorgid varchar(20) null 
/*�����֯���°汾*/,
csendstockorgvid varchar(20) null 
/*�����֯�汾*/,
csendstordocid varchar(20) null 
/*�ֿ�*/,
bautosquareincome char(1) null 
/*�Ƿ��Զ��������*/,
bautosquarecost char(1) null 
/*�Ƿ��Զ��ɱ�����*/,
breturnoutstock char(1) null 
/*����ǩ�տ�Ʊ���˻س��ⵥ��־*/,
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
ctrantypeid varchar(20) null 
/*���۳��ⵥ������������*/,
csendcountryid varchar(20) null 
/*��������/����*/,
crececountryid varchar(20) null 
/*�ջ�����/����*/,
ctaxcountryid varchar(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
 constraint pk_so_squareout primary key (csalesquareid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۳�����㵥��ϸʵ�� */
create table so_squareout_d (csalesquaredid char(20) not null 
/*���۳�����㵥��ϸʵ��*/,
pk_org varchar(20) null 
/*���������֯*/,
csalesquareid varchar(20) null 
/*���۳�����㵥��ʵ��*/,
csalesquarebid varchar(20) null 
/*���۳�����㵥��ʵ��*/,
csquarebillid varchar(20) null 
/*���۳��ⵥ��ʵ��*/,
csquarebillbid varchar(20) null 
/*���۳��ⵥ��ʵ��*/,
dbilldate char(19) null 
/*���۳��ⵥ��������*/,
processeid varchar(20) null 
/*�������κ�*/,
fsquaretype integer null 
/*��������*/,
bautosquareflag char(1) null 
/*�Ƿ��Զ�����*/,
nsquarenum decimal(28,8) null 
/*���ν�������*/,
boutrushflag char(1) null 
/*�Ƿ����Գ�*/,
vrushbillcode varchar(40) null 
/*�Գ���ⵥ��*/,
crushgeneralbid varchar(20) null 
/*�Գ���ⵥ�ӱ�id*/,
crushoutbatchid varchar(20) null 
/*�Գ����κ�*/,
nastnum decimal(28,8) null 
/*��λ����*/,
nnum decimal(28,8) null 
/*����λ����*/,
vchangerate varchar(60) null 
/*��λ������*/,
castunitid varchar(20) null 
/*��λ*/,
cunitid varchar(20) null 
/*����λ*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
ccurrencyid varchar(20) null 
/*����*/,
corigcurrencyid varchar(20) null 
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
pk_group varchar(20) null 
/*����*/,
ctaxcodeid varchar(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*??˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
ncostprice decimal(28,8) null 
/*�ɱ�����*/,
ncostmny decimal(28,8) null 
/*�ɱ����*/,
 constraint pk_so_squareout_d primary key (csalesquaredid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۷�Ʊ���㵥��ʵ�� */
create table so_squareinv_b (csalesquarebid char(20) not null 
/*���۷�Ʊ���㵥��ʵ��*/,
pk_org varchar(20) null 
/*���������֯*/,
csquarebillid varchar(20) null 
/*���۷�Ʊ��ʵ��*/,
csquarebillbid varchar(20) null 
/*���۷�Ʊ��ʵ��*/,
vctcode varchar(40) null 
/*��ͬ��*/,
deffectdate char(19) null 
/*Ӧ�յ���Ч����*/,
cprojectid varchar(20) null 
/*��Ŀ*/,
dbilldate char(19) null 
/*���۷�Ʊ��������*/,
vfirstcode varchar(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype varchar(20) null 
/*Դͷ���ݽ�������*/,
vfirsttype varchar(20) null 
/*Դͷ��������*/,
cfirstid varchar(20) null 
/*Դͷ��������*/,
cfirstbid varchar(20) null 
/*Դͷ�����ӱ�*/,
vfirstrowno varchar(20) null 
/*Դͷ�����к�*/,
vsrccode varchar(40) null 
/*��Դ���ݺ�*/,
vsrctype varchar(20) null 
/*��Դ��������*/,
vsrctrantype varchar(20) null 
/*��Դ���ݽ�������*/,
csrcid varchar(20) null 
/*��Դ��������*/,
csrcbid varchar(20) null 
/*��Դ�����ӱ�*/,
vsrcrowno varchar(20) null 
/*��Դ�����к�*/,
ccustmaterialid varchar(20) null 
/*�ͻ�������*/,
cmaterialid varchar(20) null 
/*����*/,
cmaterialvid varchar(20) null 
/*���ϰ汾*/,
cvendorid varchar(20) null 
/*��Ӧ��*/,
cproductorid varchar(20) null 
/*��������*/,
cordercustid varchar(20) null 
/*�����ͻ�*/,
cchanneltypeid varchar(20) null 
/*������������*/,
cfreecustid varchar(20) null 
/*ɢ��*/,
blargessflag char(1) null 
/*�Ƿ���Ʒ*/,
blaborflag char(1) null 
/*�Ƿ������*/,
bdiscountflag char(1) null 
/*�Ƿ��ۿ���*/,
carorgid varchar(20) null 
/*Ӧ����֯���°汾*/,
carorgvid varchar(20) null 
/*Ӧ����֯�汾*/,
cprofitcenterid varchar(20) null 
/*�����������°汾*/,
cprofitcentervid varchar(20) null 
/*�������İ汾*/,
ccostorgid varchar(20) null 
/*�ɱ���*/,
csendstockorgid varchar(20) null 
/*�����֯���°汾*/,
csendstockorgvid varchar(20) null 
/*�����֯�汾*/,
csendstordocid varchar(20) null 
/*�ֿ�*/,
cdeptid varchar(20) null 
/*���۲������°汾*/,
cdeptvid varchar(20) null 
/*���۲��Ű汾*/,
csaleorgid varchar(20) null 
/*������֯���°汾*/,
csaleorgvid varchar(20) null 
/*������֯�汾*/,
cemployeeid varchar(20) null 
/*����ҵ��Ա*/,
pk_batchcode varchar(20) null 
/*���κŵ���*/,
vbatchcode varchar(40) null 
/*���κ�*/,
cprolineid varchar(20) null 
/*��Ʒ��*/,
vrownote varchar(181) null 
/*�б�ע*/,
bsquarearfinish char(1) null 
/*�Ƿ�Ӧ�ս������*/,
bsquareiafinish char(1) null 
/*�Ƿ�ɱ��������*/,
bincome char(1) null 
/*�Ƿ�����������*/,
bcost char(1) null 
/*�Ƿ���Գɱ�����*/,
fpreartype integer null 
/*�������������*/,
fpreiatype integer null 
/*���ɱ���������*/,
nsquarearnum decimal(28,8) null 
/*�ۼ�ȷ��Ӧ������*/,
narrushnum decimal(28,8) null 
/*�ۼƻس�Ӧ������*/,
nsquareianum decimal(28,8) null 
/*�ۼƳɱ���������*/,
nsquareregnum decimal(28,8) null 
/*�ۼƷ�����Ʒ����*/,
corigcurrencyid varchar(20) null 
/*ԭ��*/,
ccurrencyid varchar(20) null 
/*����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
cunitid varchar(20) null 
/*����λ*/,
castunitid varchar(20) null 
/*��λ*/,
vchangerate varchar(60) null 
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
vfree1 varchar(101) null 
/*������1*/,
vfree2 varchar(101) null 
/*������2*/,
vfree3 varchar(101) null 
/*������3*/,
vfree4 varchar(101) null 
/*������4*/,
vfree5 varchar(101) null 
/*������5*/,
vfree6 varchar(101) null 
/*������6*/,
vfree7 varchar(101) null 
/*������7*/,
vfree8 varchar(101) null 
/*������8*/,
vfree9 varchar(101) null 
/*������9*/,
vfree10 varchar(101) null 
/*������10*/,
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
csalesquareid varchar(20) null 
/*���۷�Ʊ���㵥��ʵ��_����*/,
pk_group varchar(20) null 
/*����*/,
ctaxcodeid varchar(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
ccostsubjid varchar(20) null 
/*��֧��Ŀ*/,
csprofitcentervid varchar(20) null 
/*������������*/,
csprofitcenterid varchar(20) null 
/*���������������°�*/,
cmffileid varchar(20) null 
/*������*/,
 constraint pk_so_squareinv_b primary key (csalesquarebid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۷�Ʊ���㵥��ʵ�� */
create table so_squareinv (csalesquareid char(20) not null 
/*���۷�Ʊ���㵥��ʵ��*/,
pk_org varchar(20) null 
/*���������֯*/,
pk_org_v varchar(20) null 
/*���������֯�汾*/,
pk_group varchar(20) null 
/*����*/,
cbiztypeid varchar(20) null 
/*ҵ������*/,
ccustbankaccid varchar(20) null 
/*���������˻�*/,
csquarebillid varchar(20) null 
/*���۷�Ʊ��ʵ��*/,
cinvoicecustid varchar(20) null 
/*��Ʊ�ͻ�*/,
vbillcode varchar(40) null 
/*���۷�Ʊ���ݺ�*/,
vtrantypecode varchar(20) null 
/*���۷�Ʊ���ͱ���*/,
dbilldate char(19) null 
/*���۷�Ʊ��������*/,
dbillapprovedate char(19) null 
/*���۷�Ʊ�������*/,
cpaytermid varchar(20) null 
/*��Ʊ�ո���Э��*/,
bautosquareincome char(1) null 
/*�Ƿ��Զ��������*/,
bautosquarecost char(1) null 
/*�Ƿ��Զ��ɱ�����*/,
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
ctrantypeid varchar(20) null 
/*���۷�Ʊ����*/,
csendcountryid varchar(20) null 
/*��������/����*/,
crececountryid varchar(20) null 
/*�ջ�����/����*/,
ctaxcountryid varchar(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
vvatcode varchar(40) null 
/*VATע����*/,
vcustvatcode varchar(40) null 
/*�ͻ�VATע����*/,
cbalancetypeid varchar(20) null 
/*���㷽ʽ*/,
 constraint pk_so_squareinv primary key (csalesquareid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۷�Ʊ���㵥��ϸʵ�� */
create table so_squareinv_d (csalesquaredid char(20) not null 
/*���۷�Ʊ���㵥��ϸʵ��*/,
pk_org varchar(20) null 
/*���������֯*/,
csalesquareid varchar(20) null 
/*���۷�Ʊ���㵥��ʵ��*/,
csalesquarebid varchar(20) null 
/*���۷�Ʊ���㵥��ʵ��*/,
csquarebillid varchar(20) null 
/*���۷�Ʊ��ʵ��*/,
csquarebillbid varchar(20) null 
/*���۷�Ʊ��ʵ��*/,
dbilldate char(19) null 
/*���۷�Ʊ��������*/,
processeid varchar(20) null 
/*�������κ�*/,
fsquaretype integer null 
/*��������*/,
nsquarenum decimal(28,8) null 
/*���ν�������*/,
nastnum decimal(28,8) null 
/*��λ����*/,
nnum decimal(28,8) null 
/*����λ����*/,
vchangerate varchar(60) null 
/*��λ������*/,
castunitid varchar(20) null 
/*��λ*/,
cunitid varchar(20) null 
/*����λ*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
ccurrencyid varchar(20) null 
/*����*/,
corigcurrencyid varchar(20) null 
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
pk_group varchar(20) null 
/*����*/,
ctaxcodeid varchar(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
 constraint pk_so_squareinv_d primary key (csalesquaredid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ;����㵥��ʵ�� */
create table so_squarewas_b (csalesquarebid char(20) not null 
/*;����㵥��ʵ��*/,
pk_org varchar(20) null 
/*���������֯*/,
dbilldate char(19) null 
/*;�𵥵�������*/,
dbizdate char(19) null 
/*���۳��ⵥҵ������*/,
vctcode varchar(40) null 
/*��ͬ��*/,
deffectdate char(19) null 
/*Ӧ�յ���Ч����*/,
csquarebillid varchar(20) null 
/*;����ʵ��*/,
csquarebillbid varchar(20) null 
/*;����ʵ��*/,
vfirstcode varchar(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype varchar(20) null 
/*Դͷ���ݽ�������*/,
vfirsttype varchar(20) null 
/*Դͷ��������*/,
cfirstid varchar(20) null 
/*Դͷ��������*/,
cfirstbid varchar(20) null 
/*Դͷ�����ӱ�*/,
vfirstrowno varchar(20) null 
/*Դͷ�����к�*/,
vsrccode varchar(40) null 
/*��Դ���ݺ�*/,
vsrctype varchar(20) null 
/*��Դ��������*/,
vsrctrantype varchar(20) null 
/*��Դ���ݽ�������*/,
csrcid varchar(20) null 
/*��Դ��������*/,
csrcbid varchar(20) null 
/*��Դ�����ӱ�*/,
vsrcrowno varchar(20) null 
/*��Դ�����к�*/,
ccustmaterialid varchar(20) null 
/*�ͻ�������*/,
cmaterialid varchar(20) null 
/*����*/,
cmaterialvid varchar(20) null 
/*���ϰ汾*/,
cvendorid varchar(20) null 
/*��Ӧ��*/,
cproductorid varchar(20) null 
/*��������*/,
cprojectid varchar(20) null 
/*��ĿID*/,
cordercustid varchar(20) null 
/*�����ͻ�*/,
ccustbankaccid varchar(20) null 
/*���������˻�*/,
cinvoicecustid varchar(20) null 
/*������Ʊ�ͻ�*/,
cfreecustid char(20) null 
/*ɢ��*/,
cpaytermid varchar(20) null 
/*�����ո���Э��*/,
cchanneltypeid varchar(20) null 
/*������������*/,
blargessflag char(1) null 
/*�Ƿ���Ʒ*/,
carorgid varchar(20) null 
/*Ӧ����֯���°汾*/,
carorgvid varchar(20) null 
/*Ӧ����֯�汾*/,
cprofitcenterid varchar(20) null 
/*�����������°汾*/,
cprofitcentervid varchar(20) null 
/*�������İ汾*/,
ccostorgid varchar(20) null 
/*�ɱ���*/,
cdeptid varchar(20) null 
/*���۲������°汾*/,
cdeptvid varchar(20) null 
/*���۲��Ű汾*/,
csaleorgid varchar(20) null 
/*������֯���°汾*/,
csaleorgvid varchar(20) null 
/*������֯�汾*/,
cemployeeid varchar(20) null 
/*����ҵ��Ա*/,
pk_batchcode varchar(20) null 
/*���κŵ���*/,
vbatchcode varchar(40) null 
/*���κ�*/,
cprolineid varchar(20) null 
/*��Ʒ��*/,
vrownote varchar(181) null 
/*�б�ע*/,
bsquarearfinish char(1) null 
/*�Ƿ�Ӧ�ս������*/,
bsquareiafinish char(1) null 
/*�Ƿ�ɱ��������*/,
bincome char(1) null 
/*�Ƿ�����������*/,
bcost char(1) null 
/*�Ƿ���Գɱ�����*/,
fpreartype integer null 
/*�������������*/,
fpreiatype integer null 
/*���ɱ���������*/,
nsquarearnum decimal(28,8) null 
/*�ۼ�ȷ��Ӧ������*/,
narrushnum decimal(28,8) null 
/*�ۼƻس�Ӧ������*/,
nsquareianum decimal(28,8) null 
/*�ۼƳɱ���������*/,
nsquareregnum decimal(28,8) null 
/*�ۼƷ�����Ʒ����*/,
corigcurrencyid varchar(20) null 
/*ԭ��*/,
ccurrencyid varchar(20) null 
/*����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
cunitid varchar(20) null 
/*����λ*/,
castunitid varchar(20) null 
/*��λ*/,
vchangerate varchar(60) null 
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
vfree1 varchar(101) null 
/*������1*/,
vfree2 varchar(101) null 
/*������2*/,
vfree3 varchar(101) null 
/*������3*/,
vfree4 varchar(101) null 
/*������4*/,
vfree5 varchar(101) null 
/*������5*/,
vfree6 varchar(101) null 
/*������6*/,
vfree7 varchar(101) null 
/*������7*/,
vfree8 varchar(101) null 
/*������8*/,
vfree9 varchar(101) null 
/*������9*/,
vfree10 varchar(101) null 
/*������10*/,
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
csalesquareid varchar(20) null 
/*;����㵥��ʵ��_����*/,
pk_group varchar(20) null 
/*����*/,
ctaxcodeid varchar(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
cmffileid varchar(20) null 
/*������*/,
 constraint pk_so_squarewas_b primary key (csalesquarebid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ;����㵥��ʵ�� */
create table so_squarewas (csalesquareid char(20) not null 
/*;����㵥��ʵ��*/,
pk_org varchar(20) null 
/*���������֯*/,
pk_org_v varchar(20) null 
/*���������֯�汾*/,
pk_group varchar(20) null 
/*����*/,
cbiztypeid varchar(20) null 
/*ҵ������*/,
csquarebillid varchar(20) null 
/*;����ʵ��*/,
vbillcode varchar(40) null 
/*;�𵥵��ݺ�*/,
vtrantypecode varchar(20) null 
/*;�𵥽�������*/,
dbilldate char(19) null 
/*;�𵥵�������*/,
cwhsmanagerid varchar(20) null 
/*���۳��ⵥ���Ա*/,
bautosquareincome char(1) null 
/*�Ƿ��Զ��������*/,
bautosquarecost char(1) null 
/*�Ƿ��Զ��ɱ�����*/,
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
csendstockorgid varchar(20) null 
/*�����֯���°汾*/,
csendstockorgvid varchar(20) null 
/*�����֯�汾*/,
csendstordocid varchar(20) null 
/*�ֿ�*/,
btriatradeflag char(1) null 
/*����ó��*/,
crececountryid varchar(20) null 
/*�ջ�����/����*/,
ctaxcountryid varchar(20) null 
/*��˰����/����*/,
ctrantypeid varchar(20) null 
/*;�𵥽�����������*/,
csendcountryid varchar(20) null 
/*��������/����*/,
fbuysellflag integer null 
/*��������*/,
 constraint pk_so_sqwas primary key (csalesquareid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ;����㵥��ϸʵ�� */
create table so_squarewas_d (csalesquaredid char(20) not null 
/*;����㵥��ϸʵ��*/,
pk_org varchar(20) null 
/*���������֯*/,
csalesquareid varchar(20) null 
/*;����㵥��ʵ��*/,
csalesquarebid varchar(20) null 
/*;����㵥��ʵ��*/,
csquarebillid varchar(20) null 
/*;����ʵ��*/,
csquarebillbid varchar(20) null 
/*;����ʵ��*/,
dbilldate char(19) null 
/*;�𵥵�������*/,
processeid varchar(20) null 
/*�������κ�*/,
fsquaretype integer null 
/*��������*/,
nsquarenum decimal(28,8) null 
/*���ν�������*/,
nastnum decimal(28,8) null 
/*��λ����*/,
nnum decimal(28,8) null 
/*����λ����*/,
vchangerate varchar(60) null 
/*��λ������*/,
castunitid varchar(20) null 
/*��λ*/,
cunitid varchar(20) null 
/*����λ*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
ccurrencyid varchar(20) null 
/*����*/,
corigcurrencyid varchar(20) null 
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
pk_group varchar(20) null 
/*����*/,
ctaxcodeid varchar(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
 constraint pk_so_squarewas_d primary key (csalesquaredid),
 ts char(19) null,
dr smallint null default 0
)
;

