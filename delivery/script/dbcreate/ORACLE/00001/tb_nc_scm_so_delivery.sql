/* tablename: ���������� */
create table so_delivery (cdeliveryid char(20) not null 
/*����������ID*/,
pk_group varchar2(20) null 
/*����*/,
pk_org varchar2(20) null 
/*������֯*/,
pk_org_v varchar2(20) null 
/*������֯�汾*/,
vbillcode varchar2(40) null 
/*���ݺ�*/,
cbiztypeid varchar2(20) null 
/*ҵ������*/,
vtrantypecode varchar2(20) null 
/*�������ͱ���*/,
dbilldate char(19) null 
/*��������*/,
csendemployeeid varchar2(20) null 
/*�����ƻ�Ա*/,
csenddeptid varchar2(20) null 
/*�����������°汾*/,
csenddeptvid varchar2(20) null 
/*��������*/,
ctransporttypeid varchar2(20) null 
/*���䷽ʽ*/,
ctransportrouteid varchar2(50) null 
/*����·��*/,
ntotalastnum number(28,8) null 
/*������*/,
ntotalweight number(28,8) null 
/*������*/,
ntotalvolume number(28,8) null 
/*�����*/,
ntotalpiece number(28,8) null 
/*�ܼ���*/,
fstatusflag smallint null 
/*״̬*/,
vnote varchar2(181) null 
/*��ע*/,
creator varchar2(20) null 
/*������*/,
billmaker varchar2(20) null 
/*�Ƶ���*/,
creationtime char(19) null 
/*����ʱ��*/,
approver varchar2(20) null 
/*������*/,
taudittime varchar2(19) null 
/*�������*/,
modifier varchar2(20) null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
iprintcount integer null 
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
ctrantypeid varchar2(20) null 
/*��������*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
ctradewordid varchar2(20) null 
/*ó������*/,
 constraint pk_so_delivery primary key (cdeliveryid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: �������ӱ� */
create table so_delivery_b (cdeliverybid char(20) not null 
/*�������ӱ�ID*/,
cdeliveryid varchar2(20) null 
/*����������ID*/,
pk_org varchar2(20) null 
/*������֯*/,
dbilldate char(19) null 
/*��������*/,
crowno varchar2(20) null 
/*�к�*/,
ccustmaterialid varchar2(20) null 
/*�ͻ�������*/,
cordercustid varchar2(20) null 
/*�����ͻ�*/,
cfreecustid varchar2(20) null 
/*ɢ��*/,
cinvoicecustid varchar2(20) null 
/*��Ʊ�ͻ�*/,
cmaterialid varchar2(20) null 
/*���ϵ���*/,
cmaterialvid varchar2(20) null 
/*���ϱ���*/,
cvendorid varchar2(20) null 
/*��Ӧ��*/,
cprojectid varchar2(20) null 
/*��Ŀ*/,
cproductorid varchar2(20) null 
/*��������*/,
castunitid varchar2(20) null 
/*��λ*/,
nastnum number(28,8) null 
/*����*/,
cunitid varchar2(20) null 
/*����λ*/,
nnum number(28,8) null 
/*������*/,
vchangerate varchar2(60) null 
/*������*/,
cqtunitid varchar2(20) null 
/*���۵�λ*/,
nqtunitnum number(28,8) null 
/*��������*/,
vqtunitrate varchar2(60) null 
/*���ۻ�����*/,
bcheckflag char(1) null 
/*�Ƿ��ѱ���*/,
busecheckflag char(1) null 
/*�Ƿ�����ʼ������*/,
ntotalreportnum number(28,8) null 
/*�ۼƱ�������*/,
ntotalelignum number(28,8) null 
/*�ۼƺϸ�����*/,
ntotalunelignum number(28,8) null 
/*�ۼƲ��ϸ�����*/,
nweight number(28,8) null 
/*����*/,
nvolume number(28,8) null 
/*���*/,
npiece number(28,8) null 
/*����*/,
blargessflag char(1) null 
/*��Ʒ*/,
pk_batchcode varchar2(20) null 
/*���ε���*/,
vbatchcode varchar2(40) null 
/*���κ�*/,
corigcurrencyid varchar2(20) null 
/*ԭ��*/,
nexchangerate number(28,8) null 
/*�۱�����*/,
ccurrencyid varchar2(20) null 
/*��λ��*/,
ntaxrate number(28,8) null 
/*˰��*/,
ndiscountrate number(28,8) null 
/*�����ۿ�*/,
nitemdiscountrate number(28,8) null 
/*��Ʒ�ۿ�*/,
norigtaxprice number(28,8) null 
/*����˰����*/,
norigprice number(28,8) null 
/*����˰����*/,
norigtaxnetprice number(28,8) null 
/*����˰����*/,
norignetprice number(28,8) null 
/*����˰����*/,
nqtorigtaxprice number(28,8) null 
/*��˰����*/,
nqtorigprice number(28,8) null 
/*��˰����*/,
nqtorigtaxnetprc number(28,8) null 
/*��˰����*/,
nqtorignetprice number(28,8) null 
/*��˰����*/,
norigmny number(28,8) null 
/*��˰���*/,
norigtaxmny number(28,8) null 
/*��˰�ϼ�*/,
norigdiscount number(28,8) null 
/*�ۿ۶�*/,
ntaxprice number(28,8) null 
/*��������˰����*/,
nprice number(28,8) null 
/*��������˰����*/,
ntaxnetprice number(28,8) null 
/*��������˰����*/,
nnetprice number(28,8) null 
/*��������˰����*/,
nqttaxprice number(28,8) null 
/*���Һ�˰����*/,
nqtprice number(28,8) null 
/*������˰����*/,
nqttaxnetprice number(28,8) null 
/*���Һ�˰����*/,
nqtnetprice number(28,8) null 
/*������˰����*/,
ntax number(28,8) null 
/*˰��*/,
nmny number(28,8) null 
/*������˰���*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount number(28,8) null 
/*�����ۿ۶�*/,
vfirsttype varchar2(20) null 
/*Դͷ��������*/,
vfirstcode varchar2(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype varchar2(40) null 
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
csaleorgid varchar2(20) null 
/*������֯���°汾*/,
csaleorgvid varchar2(20) null 
/*������֯*/,
csendstockorgid varchar2(20) null 
/*���������֯���°汾*/,
csendareaid varchar2(20) null 
/*��������*/,
csendadddocid varchar2(20) null 
/*�����ص�*/,
csendaddrid varchar2(20) null 
/*������ַ*/,
csendstordocid varchar2(20) null 
/*�����ֿ�*/,
csendstockorgvid varchar2(20) null 
/*���������֯*/,
csendpersonid varchar2(20) null 
/*������ϵ��*/,
vsendtel varchar2(30) null 
/*������ϵ�绰*/,
creceivecustid varchar2(20) null 
/*�ջ��ͻ�*/,
creceiveareaid varchar2(20) null 
/*�ջ�����*/,
creceiveadddocid varchar2(20) null 
/*�ջ��ص�*/,
creceiveaddrid varchar2(20) null 
/*�ջ���ַ*/,
cinstockorgid varchar2(20) null 
/*�ջ������֯���°�*/,
vreceivetel varchar2(30) null 
/*�ջ���ϵ�绰*/,
creceivepersonid varchar2(20) null 
/*�ջ���ϵ��*/,
cinstordocid varchar2(20) null 
/*�ջ��ֿ�*/,
cinstockorgvid varchar2(20) null 
/*�ջ������֯*/,
dsenddate varchar2(19) null 
/*��������*/,
dreceivedate varchar2(19) null 
/*Ҫ���ջ�����*/,
csupercargoid varchar2(20) null 
/*Ѻ��Ա*/,
ctranscustid varchar2(20) null 
/*������*/,
cvehicletypeid varchar2(20) null 
/*����*/,
cvehicleid varchar2(20) null 
/*����*/,
cchauffeurid varchar2(20) null 
/*˾��*/,
cspaceid varchar2(50) null 
/*��λ*/,
cprodlineid varchar2(20) null 
/*��Ʒ��*/,
ntotaltransnum number(28,8) null 
/*�ۼ���������*/,
btransendflag char(1) null 
/*����ر�*/,
ntotaloutnum number(28,8) null 
/*�ۼƳ�������*/,
bbarsettleflag char(1) null 
/*�������ر�*/,
boutendflag char(1) null 
/*����ر�*/,
frownote varchar2(181) null 
/*��ע*/,
vfree1 varchar2(101) null 
/*���ɸ�������1*/,
vfree2 varchar2(101) null 
/*���ɸ�������2*/,
vfree3 varchar2(101) null 
/*���ɸ�������3*/,
vfree4 varchar2(101) null 
/*���ɸ�������4*/,
vfree5 varchar2(101) null 
/*���ɸ�������5*/,
vfree6 varchar2(101) null 
/*���ɸ�������6*/,
vfree7 varchar2(101) null 
/*���ɸ�������7*/,
vfree8 varchar2(101) null 
/*���ɸ�������8*/,
vfree9 varchar2(101) null 
/*���ɸ�������9*/,
vfree10 varchar2(101) null 
/*���ɸ�������10*/,
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
cqualitylevelid varchar2(20) null 
/*�����ȼ�*/,
cprofitcenterid varchar2(20) null 
/*���������������°汾*/,
cprofitcentervid varchar2(20) null 
/*������������*/,
carorgid varchar2(20) null 
/*Ӧ����֯���°汾*/,
carorgvid varchar2(20) null 
/*Ӧ����֯*/,
csettleorgid varchar2(20) null 
/*���������֯���°汾*/,
cdeptid varchar2(20) null 
/*���۲������°汾*/,
cdeptvid varchar2(20) null 
/*���۲���*/,
cemployeeid varchar2(20) null 
/*����ҵ��Ա*/,
csettleorgvid varchar2(20) null 
/*���������֯*/,
cchanneltypeid varchar2(20) null 
/*��������*/,
vfirstbilldate varchar2(19) null 
/*Դͷ��������*/,
ntranslossnum number(28,8) null 
/*�ۼ�;������*/,
ntotalrushnum number(28,8) null 
/*�ۼƳ���Գ�����*/,
ntotalestarnum number(28,8) null 
/*�ۼ��ݹ�Ӧ������*/,
ntotalarnum number(28,8) null 
/*�ۼ�ȷ��Ӧ������*/,
nreqrsnum number(28,8) null 
/*Ԥ������*/,
bqualityflag char(1) null 
/*�Ƿ����ʼ�*/,
badvfeeflag char(1) null 
/*�����˷�*/,
pk_group varchar2(20) null 
/*��������*/,
cpriceformid char(20) null 
/*�۸����*/,
cretreasonid char(20) null 
/*�˻�ԭ��*/,
vreturnmode char(20) null 
/*�˻����δ���ʽ*/,
ntotalnotoutnum number(28,8) null 
/*�ۼ�Ӧ��δ����������*/,
nglobaltaxmny number(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny number(28,8) null 
/*ȫ�ֱ�����˰���*/,
nglobalexchgrate number(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngrouptaxmny number(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny number(28,8) null 
/*���ű�����˰���*/,
ngroupexchgrate number(28,8) null 
/*���ű�λ�һ���*/,
crececountryid varchar2(20) null 
/*�ջ�����/����*/,
csendcountryid varchar2(20) null 
/*������/����*/,
ctaxcountryid varchar2(20) null 
/*��˰��/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
ctaxcodeid varchar2(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
corigcountryid varchar2(20) null 
/*ԭ����*/,
corigareaid varchar2(20) null 
/*ԭ������*/,
ncaltaxmny number(28,8) null 
/*��˰���*/,
csprofitcentervid varchar2(20) null 
/*������������*/,
csprofitcenterid varchar2(20) null 
/*���������������°汾*/,
crprofitcentervid varchar2(20) null 
/*�ջ���������*/,
crprofitcenterid varchar2(20) null 
/*�ջ������������°汾*/,
cmffileid varchar2(20) null 
/*������*/,
 constraint pk_so_delivery_b primary key (cdeliverybid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: �������ʼ�� */
create table so_delivery_check (cdeliverycid char(20) not null 
/*�������ʼ��ID*/,
cdeliverybid varchar2(20) null 
/*�������ӱ�ID*/,
crowno varchar2(20) null 
/*�к�*/,
cmaterialid varchar2(20) null 
/*����*/,
cmaterialvid varchar2(20) null 
/*���ϰ汾*/,
cvendorid varchar2(20) null 
/*��Ӧ��*/,
cprojectid varchar2(20) null 
/*��Ŀ*/,
cqualitylevelid varchar2(20) null 
/*�����ȼ�*/,
cproductorid varchar2(20) null 
/*��������*/,
vfree1 varchar2(101) null 
/*���ɸ�������1*/,
vfree2 varchar2(101) null 
/*���ɸ�������2*/,
vfree3 varchar2(101) null 
/*���ɸ�������3*/,
vfree4 varchar2(101) null 
/*���ɸ�������4*/,
vfree5 varchar2(101) null 
/*���ɸ�������5*/,
vfree6 varchar2(101) null 
/*���ɸ�������6*/,
vfree7 varchar2(101) null 
/*���ɸ�������7*/,
vfree8 varchar2(101) null 
/*���ɸ�������8*/,
vfree9 varchar2(101) null 
/*���ɸ�������9*/,
vfree10 varchar2(101) null 
/*���ɸ�������10*/,
pk_batchcode varchar2(20) null 
/*���ε���*/,
vbatchcode varchar2(40) null 
/*���κ�*/,
castunitid varchar2(20) null 
/*��λ*/,
nastnum number(28,8) null 
/*�ʼ�����*/,
cunitid varchar2(20) null 
/*����λ*/,
nnum number(28,8) null 
/*�ʼ�������*/,
vchangerate varchar2(60) null 
/*������*/,
cqtunitid varchar2(20) null 
/*���۵�λ*/,
nqtunitnum number(28,8) null 
/*�ʼ챨������*/,
vqtunitrate varchar2(60) null 
/*���ۻ�����*/,
vcheckcode varchar2(40) null 
/*�ʼ쵥�ݺ�*/,
dcheckdate varchar2(19) null 
/*�ʼ�����*/,
ccheckstatebid varchar2(20) null 
/*�ʼ�״̬*/,
cdefectprocessid varchar2(20) null 
/*���鴦��ʽ*/,
beligflag char(1) null 
/*�Ƿ�ϸ�*/,
bcheckinflag char(1) null 
/*�ʼ챨���Ƿ�����*/,
blargessflag char(1) null 
/*��Ʒ*/,
bpricechgflag char(1) null 
/*�Ƿ�������*/,
ntaxrate number(28,8) null 
/*˰��*/,
ndiscountrate number(28,8) null 
/*�����ۿ�*/,
nitemdiscountrate number(28,8) null 
/*��Ʒ�ۿ�*/,
norigtaxprice number(28,8) null 
/*����˰����*/,
norigprice number(28,8) null 
/*����˰����*/,
norigtaxnetprice number(28,8) null 
/*����˰����*/,
norignetprice number(28,8) null 
/*����˰����*/,
nqtorigtaxprice number(28,8) null 
/*��˰����*/,
nqtorigprice number(28,8) null 
/*��˰����*/,
nqtorigtaxnetprc number(28,8) null 
/*��˰����*/,
nqtorignetprice number(28,8) null 
/*��˰����*/,
norigmny number(28,8) null 
/*��˰���*/,
norigtaxmny number(28,8) null 
/*��˰�ϼ�*/,
norigdiscount number(28,8) null 
/*�ۿ۶�*/,
ntaxprice number(28,8) null 
/*��������˰����*/,
nprice number(28,8) null 
/*��������˰����*/,
ntaxnetprice number(28,8) null 
/*��������˰����*/,
nnetprice number(28,8) null 
/*��������˰����*/,
nqttaxprice number(28,8) null 
/*���Һ�˰����*/,
nqtprice number(28,8) null 
/*������˰����*/,
nqttaxnetprice number(28,8) null 
/*���Һ�˰����*/,
nqtnetprice number(28,8) null 
/*������˰����*/,
ntax number(28,8) null 
/*˰��*/,
nmny number(28,8) null 
/*������˰���*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount number(28,8) null 
/*�����ۿ۶�*/,
corigcurrencyid varchar2(20) null 
/*ԭ��*/,
nexchangerate number(28,8) null 
/*�۱�����*/,
ccurrencyid varchar2(20) null 
/*��λ��*/,
ntotaltransnum number(28,8) null 
/*�ۼ���������*/,
btransendflag char(1) null 
/*����ر�*/,
ntotaloutnum number(28,8) null 
/*�ۼƳ�������*/,
boutendflag char(1) null 
/*����ر�*/,
vrownote varchar2(181) null 
/*��ע*/,
vsrcrowno varchar2(20) null 
/*���յ��к�*/,
pk_org varchar2(20) null 
/*������֯*/,
pk_group varchar2(20) null 
/*��������*/,
ntotalnotoutnum number(28,8) null 
/*�ۼ�Ӧ��δ����������*/,
csrcid char(20) null 
/*��Դ����id*/,
nglobaltaxmny number(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nglobalmny number(28,8) null 
/*ȫ�ֱ�����˰���*/,
nglobalexchgrate number(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
ngrouptaxmny number(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
ngroupmny number(28,8) null 
/*���ű�����˰���*/,
ngroupexchgrate number(28,8) null 
/*���ű�λ�һ���*/,
crececountryid varchar2(20) null 
/*�ջ�����/����*/,
csendcountryid varchar2(20) null 
/*������/����*/,
ctaxcountryid varchar2(20) null 
/*��˰��/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
ctaxcodeid varchar2(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
corigcountryid varchar2(20) null 
/*ԭ����*/,
corigareaid varchar2(20) null 
/*ԭ������*/,
ncaltaxmny number(28,8) null 
/*��˰���*/,
 constraint pk__delivery_check primary key (cdeliverycid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: �������������� */
create table so_m4331trantype (ctrantypeid char(20) null 
/*��������������*/,
pk_group varchar2(20) null 
/*����*/,
vtrantypecode varchar2(20) null 
/*��������*/,
bonceoutflag char(1) null 
/*ֻһ�γ���*/,
pk_trantype varchar2(20) not null 
/*������������*/,
 constraint pk_o_m4331trantype primary key (pk_trantype),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

