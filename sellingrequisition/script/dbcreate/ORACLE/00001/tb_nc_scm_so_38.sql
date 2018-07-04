/* tablename: Ԥ�������� */
create table so_preorder (cpreorderid char(20) not null 
/*Ԥ��������*/,
vbillcode varchar2(40) null 
/*���ݺ�*/,
pk_org varchar2(20) null 
/*������֯*/,
pk_org_v varchar2(20) null 
/*������֯�汾*/,
pk_group varchar2(20) null 
/*����*/,
ctrantypeid varchar2(20) null 
/*Ԥ��������*/,
vtrantypecode varchar2(20) null 
/*Ԥ�������ͱ���*/,
cdeptid varchar2(20) null 
/*���۲������°汾*/,
cdeptvid varchar2(20) null 
/*���۲���*/,
cemployeeid varchar2(20) null 
/*ҵ��Ա*/,
ccustomerid varchar2(20) null 
/*�ͻ�*/,
cchanneltypeid varchar2(20) null 
/*������������*/,
cpaytermid varchar2(20) null 
/*�տ�Э��*/,
ndiscountrate number(28,8) null 
/*�����ۿ�*/,
cinvoicecustid varchar2(20) null 
/*��Ʊ�ͻ�*/,
ctransporttypeid varchar2(20) null 
/*���䷽ʽ*/,
corigcurrencyid varchar2(20) null 
/*����*/,
ntotalnum number(28,8) null 
/*������*/,
nheadsummny number(28,8) null 
/*��˰�ϼ�*/,
dbilldate char(19) null 
/*��������*/,
dabatedate varchar2(19) null 
/*ʧЧ����*/,
fstatusflag integer default 1 null 
/*����״̬*/,
vsrctype varchar2(20) null 
/*��Դ����*/,
cweboperatorid varchar2(20) null 
/*���϶����Ƶ���*/,
billmaker varchar2(20) null 
/*�Ƶ���*/,
creator varchar2(20) null 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
approver varchar2(20) null 
/*������*/,
taudittime char(19) null 
/*��������*/,
modifier varchar2(20) null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
iprintcount integer default 0 null 
/*��ӡ����*/,
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
dmakedate char(19) null 
/*�Ƶ�����*/,
 constraint pk_so_preorder primary key (cpreorderid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: Ԥ�����ӱ� */
create table so_preorder_b (cpreorderbid char(20) not null 
/*Ԥ�����ӱ�����*/,
pk_group varchar2(20) null 
/*����*/,
pk_org varchar2(20) null 
/*������֯*/,
ccustmaterialid varchar2(20) null 
/*�ͻ�������*/,
cmaterialid varchar2(20) null 
/*���ϵ���*/,
cmaterialvid varchar2(20) null 
/*���ϱ���*/,
dbilldate char(19) null 
/*��������*/,
cunitid varchar2(20) null 
/*����λ*/,
castunitid varchar2(20) null 
/*��λ*/,
nnum number(28,8) null 
/*������*/,
nastnum number(28,8) null 
/*����*/,
vchangerate varchar2(60) null 
/*������*/,
cqtunitid varchar2(20) null 
/*���۵�λ*/,
nqtunitnum number(28,8) null 
/*��������*/,
vqtunitrate varchar2(60) null 
/*���ۻ�����*/,
cvendorid varchar2(20) null 
/*��Ӧ��*/,
cprojectid varchar2(20) null 
/*��Ŀ*/,
cqualitylevelid varchar2(20) null 
/*�����ȼ�*/,
cproductorid varchar2(20) null 
/*��������*/,
ndiscountrate number(28,8) null 
/*�����ۿ�*/,
nitemdiscountrate number(28,8) null 
/*��Ʒ�ۿ�*/,
csendstockorgid varchar2(20) null 
/*���������֯���°汾*/,
csendstockorgvid varchar2(20) null 
/*���������֯*/,
csendstordocid varchar2(20) null 
/*�����ֿ�*/,
ctrafficorgid varchar2(20) null 
/*������֯���°汾*/,
ctrafficorgvid varchar2(20) null 
/*������֯*/,
csettleorgid varchar2(20) null 
/*���������֯���°汾*/,
csettleorgvid varchar2(20) null 
/*���������֯*/,
carorgid varchar2(20) null 
/*Ӧ����֯���°汾*/,
carorgvid varchar2(20) null 
/*Ӧ����֯*/,
cprofitcenterid varchar2(20) null 
/*�����������°汾*/,
cprofitcentervid varchar2(20) null 
/*��������*/,
creceivecustid varchar2(20) null 
/*�ջ��ͻ�*/,
creceiveareaid varchar2(20) null 
/*�ջ�����*/,
creceivesiteid varchar2(20) null 
/*�ջ��ص�*/,
creceiveaddrid varchar2(20) null 
/*�ջ���ַ*/,
cpriceformid varchar2(20) null 
/*�۸����*/,
ccurrencyid varchar2(20) null 
/*��λ��*/,
corigcurrencyid varchar2(20) null 
/*����*/,
nexchangerate number(28,8) null 
/*�۱�����*/,
ngroupexchgrate number(28,8) null 
/*���ű�λ�һ���*/,
nglobalexchgrate number(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
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
vbatchcode varchar2(40) null 
/*����*/,
pk_batchcode varchar2(20) null 
/*���ε���*/,
ntaxrate number(28,8) null 
/*˰��*/,
norigprice number(28,8) null 
/*����˰����*/,
norigtaxprice number(28,8) null 
/*����˰����*/,
norignetprice number(28,8) null 
/*����˰����*/,
norigtaxnetprice number(28,8) null 
/*����˰����*/,
norigmny number(28,8) null 
/*��˰���*/,
norigtaxmny number(28,8) null 
/*��˰�ϼ�*/,
norigdiscount number(28,8) null 
/*�ۿ۶�*/,
nprice number(28,8) null 
/*��������˰����*/,
ntaxprice number(28,8) null 
/*�����Һ�˰����*/,
nnetprice number(28,8) null 
/*��������˰����*/,
ntaxnetprice number(28,8) null 
/*�����Һ�˰����*/,
ntax number(28,8) null 
/*˰��*/,
nmny number(28,8) null 
/*������˰���*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount number(28,8) null 
/*�����ۿ۶�*/,
nqtorigtaxprice number(28,8) null 
/*��˰����*/,
nqtorigprice number(28,8) null 
/*��˰����*/,
nqtorigtaxnetprc number(28,8) null 
/*��˰����*/,
nqtorignetprice number(28,8) null 
/*��˰����*/,
nqttaxnetprice number(28,8) null 
/*���Һ�˰����*/,
nqtnetprice number(28,8) null 
/*������˰����*/,
nqttaxprice number(28,8) null 
/*���Һ�˰����*/,
nqtprice number(28,8) null 
/*������˰����*/,
naskqtorigtaxprc number(28,8) null 
/*ѯ������˰����*/,
naskqtorigprice number(28,8) null 
/*ѯ������˰����*/,
naskqtorigtxntprc number(28,8) null 
/*ѯ������˰����*/,
naskqtorignetprice number(28,8) null 
/*ѯ������˰����*/,
ngroupmny number(28,8) null 
/*���ű�����˰���*/,
ngrouptaxmny number(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
nglobalmny number(28,8) null 
/*ȫ�ֱ�����˰���*/,
nglobaltaxmny number(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
blargessflag char(1) null 
/*��Ʒ*/,
crowno varchar2(20) null 
/*�к�*/,
vrownote varchar2(181) null 
/*�б�ע*/,
frowstatus integer null 
/*��״̬*/,
dsenddate varchar2(19) null 
/*�ƻ���������*/,
dreceivedate varchar2(19) null 
/*Ҫ�󵽻�����*/,
cpricepolicyid varchar2(20) null 
/*�۸�����*/,
cpriceitemid varchar2(20) null 
/*�۸���Ŀ*/,
cpriceitemtableid varchar2(50) null 
/*��Ŀ��*/,
carrangeid varchar2(20) null 
/*�������*/,
darrdate varchar2(19) null 
/*���������*/,
narrnum number(28,8) null 
/*�ۼư�������*/,
blineclose char(1) null 
/*�йر�*/,
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
cpreorderid varchar2(20) null 
/*Ԥ��������_����*/,
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
ncaltaxmny number(28,8) null 
/*��˰���*/,
 constraint pk_so_preorder_b primary key (cpreorderbid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: Ԥ������������ */
create table so_m38trantype (pk_trantype char(20) not null 
/*Ԥ����������������*/,
bmorerows char(1) null 
/*ͬһ����ɷ��ж���*/,
faskqtrule integer default 1 null 
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
pk_group varchar2(20) null 
/*����*/,
vtrantypecode varchar2(20) null 
/*�������ͱ���*/,
bnofindpricehit char(1) null 
/*δѯ���۸��Ƿ���ʾ*/,
fmodifymny integer null 
/*�������Ӱ���ۿۻ��ǵ���*/,
ctrantypeid varchar2(20) null 
/*��������*/,
 constraint pk_so_m38trantype primary key (pk_trantype),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: Ԥ����Ǩ����־�� */
create table so_m38miglog (pk_miglog char(20) not null 
/*����*/,
fmigstatus integer null 
/*Ǩ��״̬*/,
 constraint pk_so_m38miglog primary key (pk_miglog),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

