/* tablename: ���۳�����㵥��ʵ�� */
create table so_squareout_b (csalesquarebid char(20) not null 
/*���۳�����㵥��ʵ��*/,
pk_org varchar2(20) null 
/*���������֯*/,
dbilldate char(19) null 
/*���۳��ⵥ��������*/,
vctcode varchar2(40) null 
/*��ͬ��*/,
deffectdate char(19) null 
/*Ӧ�յ���Ч����*/,
dbizdate varchar2(19) null 
/*���۳��ⵥҵ������*/,
csquarebillid varchar2(20) null 
/*���۳��ⵥ��ʵ��*/,
csquarebillbid varchar2(20) null 
/*���۳��ⵥ��ʵ��*/,
vfirstcode varchar2(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype varchar2(20) null 
/*Դͷ���ݽ�������*/,
vfirsttype varchar2(20) null 
/*Դͷ��������*/,
cfirstid varchar2(20) null 
/*Դͷ��������*/,
cfirstbid varchar2(20) null 
/*Դͷ�����ӱ�*/,
vfirstrowno varchar2(20) null 
/*Դͷ�����к�*/,
vsrccode varchar2(40) null 
/*��Դ���ݺ�*/,
vsrctype varchar2(20) null 
/*��Դ��������*/,
vsrctrantype varchar2(20) null 
/*��Դ���ݽ�������*/,
csrcid varchar2(20) null 
/*��Դ��������*/,
csrcbid varchar2(20) null 
/*��Դ�����ӱ�*/,
vsrcrowno varchar2(20) null 
/*��Դ�����к�*/,
ccustmaterialid varchar2(20) null 
/*�ͻ�������*/,
cmaterialid varchar2(20) null 
/*����*/,
cmaterialvid varchar2(20) null 
/*���ϰ汾*/,
cvendorid varchar2(20) null 
/*��Ӧ��*/,
cproductorid varchar2(20) null 
/*��������*/,
cprojectid varchar2(20) null 
/*��ĿID*/,
ccustbankaccid varchar2(20) null 
/*���������˻�*/,
cordercustid varchar2(20) null 
/*�����ͻ�*/,
cinvoicecustid varchar2(20) null 
/*������Ʊ�ͻ�*/,
cfreecustid varchar2(20) null 
/*ɢ��*/,
cpaytermid varchar2(20) null 
/*�����ո���Э��*/,
cchanneltypeid varchar2(20) null 
/*������������*/,
blargessflag char(1) null 
/*�Ƿ���Ʒ*/,
carorgid varchar2(20) null 
/*Ӧ����֯���°汾*/,
carorgvid varchar2(20) null 
/*Ӧ����֯�汾*/,
cprofitcenterid varchar2(20) null 
/*���������������°汾*/,
cprofitcentervid varchar2(20) null 
/*�����������İ汾*/,
ccostorgid varchar2(20) null 
/*�ɱ���*/,
cdeptid varchar2(20) null 
/*���۲������°汾*/,
cdeptvid varchar2(20) null 
/*���۲��Ű汾*/,
csaleorgid varchar2(20) null 
/*������֯*/,
csaleorgvid varchar2(20) null 
/*������֯�汾*/,
cemployeeid varchar2(20) null 
/*����ҵ��Ա*/,
pk_batchcode varchar2(20) null 
/*���κŵ���*/,
vbatchcode varchar2(40) null 
/*���κ�*/,
cprolineid varchar2(20) null 
/*��Ʒ��*/,
vrownote varchar2(181) null 
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
nsquarearnum number(28,8) null 
/*�ۼ�ȷ��Ӧ������*/,
nsquareestnum number(28,8) null 
/*�ۼ��ݹ�Ӧ������*/,
narrushnum number(28,8) null 
/*�ۼƻس�Ӧ������*/,
nsquareianum number(28,8) null 
/*�ۼƳɱ���������*/,
nsquareregnum number(28,8) null 
/*�ۼƷ�����Ʒ����*/,
nrushnum number(28,8) null 
/*�ۼƳ���Գ�����*/,
ntotalpaymny number(28,8) null 
/*�ۼƳ��⼰���η�Ʊ�տ�������*/,
ndownarmny number(28,8) null 
/*�ۼ����η�Ʊȷ��Ӧ�ս��*/,
ndownarnum number(28,8) null 
/*�ۼ����η�Ʊȷ��Ӧ������*/,
ndownianum number(28,8) null 
/*�ۼ����η�Ʊ�ɱ���������*/,
nsquarearmny number(28,8) null 
/*�ۼ�ȷ��Ӧ�ս��*/,
corigcurrencyid varchar2(20) null 
/*ԭ��*/,
ccurrencyid varchar2(20) null 
/*����*/,
ntaxrate number(28,8) null 
/*˰��*/,
nexchangerate number(28,8) null 
/*�۱�����*/,
cunitid varchar2(20) null 
/*����λ*/,
castunitid varchar2(20) null 
/*��λ*/,
vchangerate varchar2(60) null 
/*��λ������*/,
nnum number(28,8) null 
/*����λ����*/,
nastnum number(28,8) null 
/*��λ����*/,
nitemdiscountrate number(28,8) null 
/*��Ʒ�ۿ���*/,
norigtaxprice number(28,8) null 
/*ԭ�Һ�˰����*/,
norigprice number(28,8) null 
/*ԭ����˰����*/,
norigtaxnetprice number(28,8) null 
/*ԭ�Һ�˰����*/,
norignetprice number(28,8) null 
/*ԭ����˰����*/,
norigtax number(28,8) null 
/*ԭ��˰��*/,
norigmny number(28,8) null 
/*ԭ����˰���*/,
norigtaxmny number(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
norigdiscount number(28,8) null 
/*ԭ���ۿ۶�*/,
ntaxprice number(28,8) null 
/*���Һ�˰����*/,
nprice number(28,8) null 
/*������˰����*/,
ntaxnetprice number(28,8) null 
/*���Һ�˰����*/,
nnetprice number(28,8) null 
/*������˰����*/,
ntax number(28,8) null 
/*����˰��*/,
nmny number(28,8) null 
/*������˰���*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount number(28,8) null 
/*�����ۿ۶�*/,
nglobaltaxmny number(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny number(28,8) null 
/*ȫ�ֱ�����˰���*/,
ngrouptaxmny number(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny number(28,8) null 
/*���ű�����˰���*/,
nglobalexchgrate number(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngroupexchgrate number(28,8) null 
/*���ű�λ�һ���*/,
vfree1 varchar2(101) null 
/*������1*/,
vfree2 varchar2(101) null 
/*������2*/,
vfree3 varchar2(101) null 
/*������3*/,
vfree4 varchar2(101) null 
/*������4*/,
vfree5 varchar2(101) null 
/*������5*/,
vfree6 varchar2(101) null 
/*������6*/,
vfree7 varchar2(101) null 
/*������7*/,
vfree8 varchar2(101) null 
/*������8*/,
vfree9 varchar2(101) null 
/*������9*/,
vfree10 varchar2(101) null 
/*������10*/,
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
csalesquareid varchar2(20) null 
/*���۳�����㵥��ʵ��_����*/,
pk_group varchar2(20) null 
/*����*/,
ctaxcodeid varchar2(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny number(28,8) null 
/*��˰���*/,
csprofitcentervid varchar2(20) null 
/*������������*/,
csprofitcenterid varchar2(20) null 
/*���������������°汾*/,
cmffileid varchar2(20) null 
/*������*/,
 constraint pk_so_squareout_b primary key (csalesquarebid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۳�����㵥��ʵ�� */
create table so_squareout (csalesquareid char(20) not null 
/*���۳�����㵥��ʵ��*/,
pk_org varchar2(20) null 
/*���������֯*/,
pk_org_v varchar2(20) null 
/*���������֯�汾*/,
pk_group varchar2(20) null 
/*����*/,
cbiztypeid varchar2(20) null 
/*ҵ������*/,
csquarebillid varchar2(20) null 
/*���۳��ⵥ��ʵ��*/,
vbillcode varchar2(40) null 
/*���۳��ⵥ���ݺ�*/,
vtrantypecode varchar2(20) null 
/*���۳��ⵥ��������*/,
dbilldate char(19) null 
/*���۳��ⵥ��������*/,
dbillsigndate char(19) null 
/*���۳��ⵥǩ������*/,
cwhsmanagerid varchar2(20) null 
/*���۳��ⵥ���Ա*/,
csendstockorgid varchar2(20) null 
/*�����֯���°汾*/,
csendstockorgvid varchar2(20) null 
/*�����֯�汾*/,
csendstordocid varchar2(20) null 
/*�ֿ�*/,
bautosquareincome char(1) null 
/*�Ƿ��Զ��������*/,
bautosquarecost char(1) null 
/*�Ƿ��Զ��ɱ�����*/,
breturnoutstock char(1) null 
/*����ǩ�տ�Ʊ���˻س��ⵥ��־*/,
vnote varchar2(181) null 
/*��ע*/,
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
ctrantypeid varchar2(20) null 
/*���۳��ⵥ������������*/,
csendcountryid varchar2(20) null 
/*��������/����*/,
crececountryid varchar2(20) null 
/*�ջ�����/����*/,
ctaxcountryid varchar2(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
 constraint pk_so_squareout primary key (csalesquareid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۳�����㵥��ϸʵ�� */
create table so_squareout_d (csalesquaredid char(20) not null 
/*���۳�����㵥��ϸʵ��*/,
pk_org varchar2(20) null 
/*���������֯*/,
csalesquareid varchar2(20) null 
/*���۳�����㵥��ʵ��*/,
csalesquarebid varchar2(20) null 
/*���۳�����㵥��ʵ��*/,
csquarebillid varchar2(20) null 
/*���۳��ⵥ��ʵ��*/,
csquarebillbid varchar2(20) null 
/*���۳��ⵥ��ʵ��*/,
dbilldate char(19) null 
/*���۳��ⵥ��������*/,
processeid varchar2(20) null 
/*�������κ�*/,
fsquaretype integer null 
/*��������*/,
bautosquareflag char(1) null 
/*�Ƿ��Զ�����*/,
nsquarenum number(28,8) null 
/*���ν�������*/,
boutrushflag char(1) null 
/*�Ƿ����Գ�*/,
vrushbillcode varchar2(40) null 
/*�Գ���ⵥ��*/,
crushgeneralbid varchar2(20) null 
/*�Գ���ⵥ�ӱ�id*/,
crushoutbatchid varchar2(20) null 
/*�Գ����κ�*/,
nastnum number(28,8) null 
/*��λ����*/,
nnum number(28,8) null 
/*����λ����*/,
vchangerate varchar2(60) null 
/*��λ������*/,
castunitid varchar2(20) null 
/*��λ*/,
cunitid varchar2(20) null 
/*����λ*/,
nexchangerate number(28,8) null 
/*�۱�����*/,
ntaxrate number(28,8) null 
/*˰��*/,
ccurrencyid varchar2(20) null 
/*����*/,
corigcurrencyid varchar2(20) null 
/*ԭ��*/,
nitemdiscountrate number(28,8) null 
/*��Ʒ�ۿ���*/,
norigtaxprice number(28,8) null 
/*ԭ�Һ�˰����*/,
norigprice number(28,8) null 
/*ԭ����˰����*/,
norigtaxnetprice number(28,8) null 
/*ԭ�Һ�˰����*/,
norignetprice number(28,8) null 
/*ԭ����˰����*/,
norigtax number(28,8) null 
/*ԭ��˰��*/,
norigmny number(28,8) null 
/*ԭ����˰���*/,
norigtaxmny number(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
norigdiscount number(28,8) null 
/*ԭ���ۿ۶�*/,
ntaxprice number(28,8) null 
/*���Һ�˰����*/,
nprice number(28,8) null 
/*������˰����*/,
ntaxnetprice number(28,8) null 
/*���Һ�˰����*/,
nnetprice number(28,8) null 
/*������˰����*/,
ntax number(28,8) null 
/*����˰��*/,
nmny number(28,8) null 
/*������˰���*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount number(28,8) null 
/*�����ۿ۶�*/,
nglobaltaxmny number(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny number(28,8) null 
/*ȫ�ֱ�����˰���*/,
ngrouptaxmny number(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny number(28,8) null 
/*���ű�����˰���*/,
nglobalexchgrate number(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngroupexchgrate number(28,8) null 
/*���ű�λ�һ���*/,
pk_group varchar2(20) null 
/*����*/,
ctaxcodeid varchar2(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*??˰���*/,
ncaltaxmny number(28,8) null 
/*��˰���*/,
ncostprice number(28,8) null 
/*�ɱ�����*/,
ncostmny number(28,8) null 
/*�ɱ����*/,
 constraint pk_so_squareout_d primary key (csalesquaredid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۷�Ʊ���㵥��ʵ�� */
create table so_squareinv_b (csalesquarebid char(20) not null 
/*���۷�Ʊ���㵥��ʵ��*/,
pk_org varchar2(20) null 
/*���������֯*/,
csquarebillid varchar2(20) null 
/*���۷�Ʊ��ʵ��*/,
csquarebillbid varchar2(20) null 
/*���۷�Ʊ��ʵ��*/,
vctcode varchar2(40) null 
/*��ͬ��*/,
deffectdate char(19) null 
/*Ӧ�յ���Ч����*/,
cprojectid varchar2(20) null 
/*��Ŀ*/,
dbilldate char(19) null 
/*���۷�Ʊ��������*/,
vfirstcode varchar2(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype varchar2(20) null 
/*Դͷ���ݽ�������*/,
vfirsttype varchar2(20) null 
/*Դͷ��������*/,
cfirstid varchar2(20) null 
/*Դͷ��������*/,
cfirstbid varchar2(20) null 
/*Դͷ�����ӱ�*/,
vfirstrowno varchar2(20) null 
/*Դͷ�����к�*/,
vsrccode varchar2(40) null 
/*��Դ���ݺ�*/,
vsrctype varchar2(20) null 
/*��Դ��������*/,
vsrctrantype varchar2(20) null 
/*��Դ���ݽ�������*/,
csrcid varchar2(20) null 
/*��Դ��������*/,
csrcbid varchar2(20) null 
/*��Դ�����ӱ�*/,
vsrcrowno varchar2(20) null 
/*��Դ�����к�*/,
ccustmaterialid varchar2(20) null 
/*�ͻ�������*/,
cmaterialid varchar2(20) null 
/*����*/,
cmaterialvid varchar2(20) null 
/*���ϰ汾*/,
cvendorid varchar2(20) null 
/*��Ӧ��*/,
cproductorid varchar2(20) null 
/*��������*/,
cordercustid varchar2(20) null 
/*�����ͻ�*/,
cchanneltypeid varchar2(20) null 
/*������������*/,
cfreecustid varchar2(20) null 
/*ɢ��*/,
blargessflag char(1) null 
/*�Ƿ���Ʒ*/,
blaborflag char(1) null 
/*�Ƿ������*/,
bdiscountflag char(1) null 
/*�Ƿ��ۿ���*/,
carorgid varchar2(20) null 
/*Ӧ����֯���°汾*/,
carorgvid varchar2(20) null 
/*Ӧ����֯�汾*/,
cprofitcenterid varchar2(20) null 
/*�����������°汾*/,
cprofitcentervid varchar2(20) null 
/*�������İ汾*/,
ccostorgid varchar2(20) null 
/*�ɱ���*/,
csendstockorgid varchar2(20) null 
/*�����֯���°汾*/,
csendstockorgvid varchar2(20) null 
/*�����֯�汾*/,
csendstordocid varchar2(20) null 
/*�ֿ�*/,
cdeptid varchar2(20) null 
/*���۲������°汾*/,
cdeptvid varchar2(20) null 
/*���۲��Ű汾*/,
csaleorgid varchar2(20) null 
/*������֯���°汾*/,
csaleorgvid varchar2(20) null 
/*������֯�汾*/,
cemployeeid varchar2(20) null 
/*����ҵ��Ա*/,
pk_batchcode varchar2(20) null 
/*���κŵ���*/,
vbatchcode varchar2(40) null 
/*���κ�*/,
cprolineid varchar2(20) null 
/*��Ʒ��*/,
vrownote varchar2(181) null 
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
nsquarearnum number(28,8) null 
/*�ۼ�ȷ��Ӧ������*/,
narrushnum number(28,8) null 
/*�ۼƻس�Ӧ������*/,
nsquareianum number(28,8) null 
/*�ۼƳɱ���������*/,
nsquareregnum number(28,8) null 
/*�ۼƷ�����Ʒ����*/,
corigcurrencyid varchar2(20) null 
/*ԭ��*/,
ccurrencyid varchar2(20) null 
/*����*/,
ntaxrate number(28,8) null 
/*˰��*/,
nexchangerate number(28,8) null 
/*�۱�����*/,
cunitid varchar2(20) null 
/*����λ*/,
castunitid varchar2(20) null 
/*��λ*/,
vchangerate varchar2(60) null 
/*��λ������*/,
nnum number(28,8) null 
/*����λ����*/,
nastnum number(28,8) null 
/*��λ����*/,
nitemdiscountrate number(28,8) null 
/*��Ʒ�ۿ���*/,
norigtaxprice number(28,8) null 
/*ԭ�Һ�˰����*/,
norigprice number(28,8) null 
/*ԭ����˰����*/,
norigtaxnetprice number(28,8) null 
/*ԭ�Һ�˰����*/,
norignetprice number(28,8) null 
/*ԭ����˰����*/,
norigtax number(28,8) null 
/*ԭ��˰��*/,
norigmny number(28,8) null 
/*ԭ����˰���*/,
norigtaxmny number(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
norigdiscount number(28,8) null 
/*ԭ���ۿ۶�*/,
ntaxprice number(28,8) null 
/*���Һ�˰����*/,
nprice number(28,8) null 
/*������˰����*/,
ntaxnetprice number(28,8) null 
/*���Һ�˰����*/,
nnetprice number(28,8) null 
/*������˰����*/,
ntax number(28,8) null 
/*����˰��*/,
nmny number(28,8) null 
/*������˰���*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount number(28,8) null 
/*�����ۿ۶�*/,
nglobaltaxmny number(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny number(28,8) null 
/*ȫ�ֱ�����˰���*/,
ngrouptaxmny number(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny number(28,8) null 
/*���ű�����˰���*/,
nglobalexchgrate number(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngroupexchgrate number(28,8) null 
/*���ű�λ�һ���*/,
vfree1 varchar2(101) null 
/*������1*/,
vfree2 varchar2(101) null 
/*������2*/,
vfree3 varchar2(101) null 
/*������3*/,
vfree4 varchar2(101) null 
/*������4*/,
vfree5 varchar2(101) null 
/*������5*/,
vfree6 varchar2(101) null 
/*������6*/,
vfree7 varchar2(101) null 
/*������7*/,
vfree8 varchar2(101) null 
/*������8*/,
vfree9 varchar2(101) null 
/*������9*/,
vfree10 varchar2(101) null 
/*������10*/,
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
csalesquareid varchar2(20) null 
/*���۷�Ʊ���㵥��ʵ��_����*/,
pk_group varchar2(20) null 
/*����*/,
ctaxcodeid varchar2(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny number(28,8) null 
/*��˰���*/,
ccostsubjid varchar2(20) null 
/*��֧��Ŀ*/,
csprofitcentervid varchar2(20) null 
/*������������*/,
csprofitcenterid varchar2(20) null 
/*���������������°�*/,
cmffileid varchar2(20) null 
/*������*/,
 constraint pk_so_squareinv_b primary key (csalesquarebid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۷�Ʊ���㵥��ʵ�� */
create table so_squareinv (csalesquareid char(20) not null 
/*���۷�Ʊ���㵥��ʵ��*/,
pk_org varchar2(20) null 
/*���������֯*/,
pk_org_v varchar2(20) null 
/*���������֯�汾*/,
pk_group varchar2(20) null 
/*����*/,
cbiztypeid varchar2(20) null 
/*ҵ������*/,
ccustbankaccid varchar2(20) null 
/*���������˻�*/,
csquarebillid varchar2(20) null 
/*���۷�Ʊ��ʵ��*/,
cinvoicecustid varchar2(20) null 
/*��Ʊ�ͻ�*/,
vbillcode varchar2(40) null 
/*���۷�Ʊ���ݺ�*/,
vtrantypecode varchar2(20) null 
/*���۷�Ʊ���ͱ���*/,
dbilldate char(19) null 
/*���۷�Ʊ��������*/,
dbillapprovedate char(19) null 
/*���۷�Ʊ�������*/,
cpaytermid varchar2(20) null 
/*��Ʊ�ո���Э��*/,
bautosquareincome char(1) null 
/*�Ƿ��Զ��������*/,
bautosquarecost char(1) null 
/*�Ƿ��Զ��ɱ�����*/,
vnote varchar2(181) null 
/*��ע*/,
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
ctrantypeid varchar2(20) null 
/*���۷�Ʊ����*/,
csendcountryid varchar2(20) null 
/*��������/����*/,
crececountryid varchar2(20) null 
/*�ջ�����/����*/,
ctaxcountryid varchar2(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
vvatcode varchar2(40) null 
/*VATע����*/,
vcustvatcode varchar2(40) null 
/*�ͻ�VATע����*/,
cbalancetypeid varchar2(20) null 
/*���㷽ʽ*/,
 constraint pk_so_squareinv primary key (csalesquareid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۷�Ʊ���㵥��ϸʵ�� */
create table so_squareinv_d (csalesquaredid char(20) not null 
/*���۷�Ʊ���㵥��ϸʵ��*/,
pk_org varchar2(20) null 
/*���������֯*/,
csalesquareid varchar2(20) null 
/*���۷�Ʊ���㵥��ʵ��*/,
csalesquarebid varchar2(20) null 
/*���۷�Ʊ���㵥��ʵ��*/,
csquarebillid varchar2(20) null 
/*���۷�Ʊ��ʵ��*/,
csquarebillbid varchar2(20) null 
/*���۷�Ʊ��ʵ��*/,
dbilldate char(19) null 
/*���۷�Ʊ��������*/,
processeid varchar2(20) null 
/*�������κ�*/,
fsquaretype integer null 
/*��������*/,
nsquarenum number(28,8) null 
/*���ν�������*/,
nastnum number(28,8) null 
/*��λ����*/,
nnum number(28,8) null 
/*����λ����*/,
vchangerate varchar2(60) null 
/*��λ������*/,
castunitid varchar2(20) null 
/*��λ*/,
cunitid varchar2(20) null 
/*����λ*/,
nexchangerate number(28,8) null 
/*�۱�����*/,
ntaxrate number(28,8) null 
/*˰��*/,
ccurrencyid varchar2(20) null 
/*����*/,
corigcurrencyid varchar2(20) null 
/*ԭ��*/,
nitemdiscountrate number(28,8) null 
/*��Ʒ�ۿ���*/,
norigtaxprice number(28,8) null 
/*ԭ�Һ�˰����*/,
norigprice number(28,8) null 
/*ԭ����˰����*/,
norigtaxnetprice number(28,8) null 
/*ԭ�Һ�˰����*/,
norignetprice number(28,8) null 
/*ԭ����˰����*/,
norigtax number(28,8) null 
/*ԭ��˰��*/,
norigmny number(28,8) null 
/*ԭ����˰���*/,
norigtaxmny number(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
norigdiscount number(28,8) null 
/*ԭ���ۿ۶�*/,
ntaxprice number(28,8) null 
/*���Һ�˰����*/,
nprice number(28,8) null 
/*������˰����*/,
ntaxnetprice number(28,8) null 
/*���Һ�˰����*/,
nnetprice number(28,8) null 
/*������˰����*/,
ntax number(28,8) null 
/*����˰��*/,
nmny number(28,8) null 
/*������˰���*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount number(28,8) null 
/*�����ۿ۶�*/,
nglobaltaxmny number(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny number(28,8) null 
/*ȫ�ֱ�����˰���*/,
ngrouptaxmny number(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny number(28,8) null 
/*���ű�����˰���*/,
nglobalexchgrate number(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngroupexchgrate number(28,8) null 
/*���ű�λ�һ���*/,
pk_group varchar2(20) null 
/*����*/,
ctaxcodeid varchar2(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny number(28,8) null 
/*��˰���*/,
 constraint pk_so_squareinv_d primary key (csalesquaredid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ;����㵥��ʵ�� */
create table so_squarewas_b (csalesquarebid char(20) not null 
/*;����㵥��ʵ��*/,
pk_org varchar2(20) null 
/*���������֯*/,
dbilldate char(19) null 
/*;�𵥵�������*/,
dbizdate char(19) null 
/*���۳��ⵥҵ������*/,
vctcode varchar2(40) null 
/*��ͬ��*/,
deffectdate char(19) null 
/*Ӧ�յ���Ч����*/,
csquarebillid varchar2(20) null 
/*;����ʵ��*/,
csquarebillbid varchar2(20) null 
/*;����ʵ��*/,
vfirstcode varchar2(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype varchar2(20) null 
/*Դͷ���ݽ�������*/,
vfirsttype varchar2(20) null 
/*Դͷ��������*/,
cfirstid varchar2(20) null 
/*Դͷ��������*/,
cfirstbid varchar2(20) null 
/*Դͷ�����ӱ�*/,
vfirstrowno varchar2(20) null 
/*Դͷ�����к�*/,
vsrccode varchar2(40) null 
/*��Դ���ݺ�*/,
vsrctype varchar2(20) null 
/*��Դ��������*/,
vsrctrantype varchar2(20) null 
/*��Դ���ݽ�������*/,
csrcid varchar2(20) null 
/*��Դ��������*/,
csrcbid varchar2(20) null 
/*��Դ�����ӱ�*/,
vsrcrowno varchar2(20) null 
/*��Դ�����к�*/,
ccustmaterialid varchar2(20) null 
/*�ͻ�������*/,
cmaterialid varchar2(20) null 
/*����*/,
cmaterialvid varchar2(20) null 
/*���ϰ汾*/,
cvendorid varchar2(20) null 
/*��Ӧ��*/,
cproductorid varchar2(20) null 
/*��������*/,
cprojectid varchar2(20) null 
/*��ĿID*/,
cordercustid varchar2(20) null 
/*�����ͻ�*/,
ccustbankaccid varchar2(20) null 
/*���������˻�*/,
cinvoicecustid varchar2(20) null 
/*������Ʊ�ͻ�*/,
cfreecustid char(20) null 
/*ɢ��*/,
cpaytermid varchar2(20) null 
/*�����ո���Э��*/,
cchanneltypeid varchar2(20) null 
/*������������*/,
blargessflag char(1) null 
/*�Ƿ���Ʒ*/,
carorgid varchar2(20) null 
/*Ӧ����֯���°汾*/,
carorgvid varchar2(20) null 
/*Ӧ����֯�汾*/,
cprofitcenterid varchar2(20) null 
/*�����������°汾*/,
cprofitcentervid varchar2(20) null 
/*�������İ汾*/,
ccostorgid varchar2(20) null 
/*�ɱ���*/,
cdeptid varchar2(20) null 
/*���۲������°汾*/,
cdeptvid varchar2(20) null 
/*���۲��Ű汾*/,
csaleorgid varchar2(20) null 
/*������֯���°汾*/,
csaleorgvid varchar2(20) null 
/*������֯�汾*/,
cemployeeid varchar2(20) null 
/*����ҵ��Ա*/,
pk_batchcode varchar2(20) null 
/*���κŵ���*/,
vbatchcode varchar2(40) null 
/*���κ�*/,
cprolineid varchar2(20) null 
/*��Ʒ��*/,
vrownote varchar2(181) null 
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
nsquarearnum number(28,8) null 
/*�ۼ�ȷ��Ӧ������*/,
narrushnum number(28,8) null 
/*�ۼƻس�Ӧ������*/,
nsquareianum number(28,8) null 
/*�ۼƳɱ���������*/,
nsquareregnum number(28,8) null 
/*�ۼƷ�����Ʒ����*/,
corigcurrencyid varchar2(20) null 
/*ԭ��*/,
ccurrencyid varchar2(20) null 
/*����*/,
ntaxrate number(28,8) null 
/*˰��*/,
nexchangerate number(28,8) null 
/*�۱�����*/,
cunitid varchar2(20) null 
/*����λ*/,
castunitid varchar2(20) null 
/*��λ*/,
vchangerate varchar2(60) null 
/*��λ������*/,
nnum number(28,8) null 
/*����λ����*/,
nastnum number(28,8) null 
/*��λ����*/,
nitemdiscountrate number(28,8) null 
/*��Ʒ�ۿ���*/,
norigtaxprice number(28,8) null 
/*ԭ�Һ�˰����*/,
norigprice number(28,8) null 
/*ԭ����˰����*/,
norigtaxnetprice number(28,8) null 
/*ԭ�Һ�˰����*/,
norignetprice number(28,8) null 
/*ԭ����˰����*/,
norigtax number(28,8) null 
/*ԭ��˰��*/,
norigmny number(28,8) null 
/*ԭ����˰���*/,
norigtaxmny number(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
norigdiscount number(28,8) null 
/*ԭ���ۿ۶�*/,
ntaxprice number(28,8) null 
/*���Һ�˰����*/,
nprice number(28,8) null 
/*������˰����*/,
ntaxnetprice number(28,8) null 
/*���Һ�˰����*/,
nnetprice number(28,8) null 
/*������˰����*/,
ntax number(28,8) null 
/*����˰��*/,
nmny number(28,8) null 
/*������˰���*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount number(28,8) null 
/*�����ۿ۶�*/,
nglobaltaxmny number(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny number(28,8) null 
/*ȫ�ֱ�����˰���*/,
ngrouptaxmny number(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny number(28,8) null 
/*���ű�����˰���*/,
nglobalexchgrate number(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngroupexchgrate number(28,8) null 
/*���ű�λ�һ���*/,
vfree1 varchar2(101) null 
/*������1*/,
vfree2 varchar2(101) null 
/*������2*/,
vfree3 varchar2(101) null 
/*������3*/,
vfree4 varchar2(101) null 
/*������4*/,
vfree5 varchar2(101) null 
/*������5*/,
vfree6 varchar2(101) null 
/*������6*/,
vfree7 varchar2(101) null 
/*������7*/,
vfree8 varchar2(101) null 
/*������8*/,
vfree9 varchar2(101) null 
/*������9*/,
vfree10 varchar2(101) null 
/*������10*/,
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
csalesquareid varchar2(20) null 
/*;����㵥��ʵ��_����*/,
pk_group varchar2(20) null 
/*����*/,
ctaxcodeid varchar2(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny number(28,8) null 
/*��˰���*/,
cmffileid varchar2(20) null 
/*������*/,
 constraint pk_so_squarewas_b primary key (csalesquarebid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ;����㵥��ʵ�� */
create table so_squarewas (csalesquareid char(20) not null 
/*;����㵥��ʵ��*/,
pk_org varchar2(20) null 
/*���������֯*/,
pk_org_v varchar2(20) null 
/*���������֯�汾*/,
pk_group varchar2(20) null 
/*����*/,
cbiztypeid varchar2(20) null 
/*ҵ������*/,
csquarebillid varchar2(20) null 
/*;����ʵ��*/,
vbillcode varchar2(40) null 
/*;�𵥵��ݺ�*/,
vtrantypecode varchar2(20) null 
/*;�𵥽�������*/,
dbilldate char(19) null 
/*;�𵥵�������*/,
cwhsmanagerid varchar2(20) null 
/*���۳��ⵥ���Ա*/,
bautosquareincome char(1) null 
/*�Ƿ��Զ��������*/,
bautosquarecost char(1) null 
/*�Ƿ��Զ��ɱ�����*/,
vnote varchar2(181) null 
/*��ע*/,
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
csendstockorgid varchar2(20) null 
/*�����֯���°汾*/,
csendstockorgvid varchar2(20) null 
/*�����֯�汾*/,
csendstordocid varchar2(20) null 
/*�ֿ�*/,
btriatradeflag char(1) null 
/*����ó��*/,
crececountryid varchar2(20) null 
/*�ջ�����/����*/,
ctaxcountryid varchar2(20) null 
/*��˰����/����*/,
ctrantypeid varchar2(20) null 
/*;�𵥽�����������*/,
csendcountryid varchar2(20) null 
/*��������/����*/,
fbuysellflag integer null 
/*��������*/,
 constraint pk_so_sqwas primary key (csalesquareid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ;����㵥��ϸʵ�� */
create table so_squarewas_d (csalesquaredid char(20) not null 
/*;����㵥��ϸʵ��*/,
pk_org varchar2(20) null 
/*���������֯*/,
csalesquareid varchar2(20) null 
/*;����㵥��ʵ��*/,
csalesquarebid varchar2(20) null 
/*;����㵥��ʵ��*/,
csquarebillid varchar2(20) null 
/*;����ʵ��*/,
csquarebillbid varchar2(20) null 
/*;����ʵ��*/,
dbilldate char(19) null 
/*;�𵥵�������*/,
processeid varchar2(20) null 
/*�������κ�*/,
fsquaretype integer null 
/*��������*/,
nsquarenum number(28,8) null 
/*���ν�������*/,
nastnum number(28,8) null 
/*��λ����*/,
nnum number(28,8) null 
/*����λ����*/,
vchangerate varchar2(60) null 
/*��λ������*/,
castunitid varchar2(20) null 
/*��λ*/,
cunitid varchar2(20) null 
/*����λ*/,
nexchangerate number(28,8) null 
/*�۱�����*/,
ntaxrate number(28,8) null 
/*˰��*/,
ccurrencyid varchar2(20) null 
/*����*/,
corigcurrencyid varchar2(20) null 
/*ԭ��*/,
nitemdiscountrate number(28,8) null 
/*��Ʒ�ۿ���*/,
norigtaxprice number(28,8) null 
/*ԭ�Һ�˰����*/,
norigprice number(28,8) null 
/*ԭ����˰����*/,
norigtaxnetprice number(28,8) null 
/*ԭ�Һ�˰����*/,
norignetprice number(28,8) null 
/*ԭ����˰����*/,
norigtax number(28,8) null 
/*ԭ��˰��*/,
norigmny number(28,8) null 
/*ԭ����˰���*/,
norigtaxmny number(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
norigdiscount number(28,8) null 
/*ԭ���ۿ۶�*/,
ntaxprice number(28,8) null 
/*���Һ�˰����*/,
nprice number(28,8) null 
/*������˰����*/,
ntaxnetprice number(28,8) null 
/*���Һ�˰����*/,
nnetprice number(28,8) null 
/*������˰����*/,
ntax number(28,8) null 
/*����˰��*/,
nmny number(28,8) null 
/*������˰���*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount number(28,8) null 
/*�����ۿ۶�*/,
nglobaltaxmny number(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny number(28,8) null 
/*ȫ�ֱ�����˰���*/,
ngrouptaxmny number(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny number(28,8) null 
/*���ű�����˰���*/,
nglobalexchgrate number(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngroupexchgrate number(28,8) null 
/*���ű�λ�һ���*/,
pk_group varchar2(20) null 
/*����*/,
ctaxcodeid varchar2(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny number(28,8) null 
/*��˰���*/,
 constraint pk_so_squarewas_d primary key (csalesquaredid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

