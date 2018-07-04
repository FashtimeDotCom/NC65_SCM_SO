/* tablename: ���۵��������� */
create table so_salequotationtype (
pk_trantype nchar(20) not null 
/*����*/,
vtrantype nvarchar(20) null default '~' 
/*���۵���������*/,
fsourceflag nvarchar(50) not null 
/*����������Դ*/,
fhistoryflag nvarchar(50) not null 
/*��ʷ����Դ*/,
iavgmonth int null 
/*ƽ����������*/,
vmatchrule nvarchar(100) null 
/*��ʷ����ƥ�����*/,
bautocloseflag nchar(1) null 
/*���ɺ�ͬ/�������Զ��رձ��۵�*/,
pk_group nvarchar(20) null default '~' 
/*����*/,
bsuccqteditable nchar(1) null 
/*ѯ���۸�ɸ�*/,
bfailqteditable nchar(1) null 
/*δѯ���۸�ɸ�*/,
bdiscounteditable nchar(1) null 
/*�����޸��ۿ�*/,
bcustmatrule nchar(1) null 
/*�ͻ�*/,
bchanmatrule nchar(1) null 
/*��������*/,
bquotypematrule nchar(1) null 
/*���۵�����*/,
bdeptmatrule nchar(1) null 
/*����*/,
bempmatrule nchar(1) null 
/*ҵ��Ա*/,
bpaytermmatrule nchar(1) null 
/*�տ�Э��*/,
bbalatypematrule nchar(1) null 
/*���㷽ʽ*/,
bcurrtypematrule nchar(1) null 
/*����ƥ��*/,
bunitmatrule nchar(1) null 
/*��λƥ��*/,
bsendtypematrule nchar(1) null 
/*���䷽ʽ*/,
bquallevelmatrule nchar(1) null 
/*�����ȼ�*/,
bareamatrule nchar(1) null 
/*�ջ�����*/,
fmodifymny smallint null 
/*�������Ӱ�쵥��*/,
ctrantypeid nvarchar(20) null 
/*��������*/,
bmorerows nchar(1) null default 'N' 
/*ͬһ����ɷ��ж���*/,
 constraint pk_lequotationtype primary key (pk_trantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۵����� */
create table so_salequotation_b (
pk_salequotation_b nchar(20) not null 
/*���۱��۵��ӱ�����*/,
pk_org_v nvarchar(20) null default '~' 
/*������֯�汾��Ϣ*/,
pk_org nvarchar(20) null default '~' 
/*������֯*/,
crowno nvarchar(20) null 
/*�к�*/,
ccustmaterialid nvarchar(20) null 
/*�ͻ�������*/,
pk_material_v nvarchar(20) null default '~' 
/*���ϱ���*/,
pk_material nvarchar(20) null default '~' 
/*�������°汾*/,
pk_qualitylevel nvarchar(20) null default '~' 
/*�����ȼ�*/,
pk_project nvarchar(20) null default '~' 
/*��Ŀ*/,
pk_productor nvarchar(20) null default '~' 
/*��������*/,
pk_supplier nvarchar(20) null default '~' 
/*��Ӧ��*/,
castunitid nvarchar(20) null default '~' 
/*��λ*/,
nassistnum decimal(28,8) null 
/*����*/,
nchangerate nvarchar(60) null 
/*������*/,
pk_unit nvarchar(20) null default '~' 
/*����λ*/,
nnum decimal(28,8) null 
/*������*/,
pk_areacl nvarchar(20) null default '~' 
/*�ջ�����*/,
vfree1 nvarchar(100) null 
/*���ɸ�������1*/,
vfree2 nvarchar(100) null 
/*���ɸ�������2*/,
vfree3 nvarchar(100) null 
/*���ɸ�������3*/,
vfree4 nvarchar(100) null 
/*���ɸ�������4*/,
vfree5 nvarchar(100) null 
/*���ɸ�������5*/,
vfree6 nvarchar(100) null 
/*���ɸ�������6*/,
vfree7 nvarchar(100) null 
/*���ɸ�������7*/,
vfree8 nvarchar(100) null 
/*���ɸ�������8*/,
vfree9 nvarchar(100) null 
/*���ɸ�������9*/,
vfree10 nvarchar(100) null 
/*���ɸ�������10*/,
nqtorigprice decimal(28,8) null 
/*��˰����*/,
ntaxrate decimal(28,8) null 
/*˰��(%)*/,
nqtorigtaxprice decimal(28,8) null 
/*��˰����*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�(%)*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ�(%)*/,
nqtorignetprice decimal(28,8) null 
/*��˰����*/,
nqtorigtaxnetprc decimal(28,8) null 
/*��˰����*/,
norigmny decimal(28,8) null 
/*��˰���*/,
norigtaxmny decimal(28,8) null 
/*��˰�ϼ�*/,
norigdiscount decimal(28,8) null 
/*�ۿ۶�*/,
blargessflag nchar(1) null 
/*��Ʒ*/,
pk_pricepolicy nvarchar(20) null default '~' 
/*���۲���*/,
pk_tariffdef nvarchar(20) null default '~' 
/*��Ŀ��*/,
pk_pricetype nvarchar(20) null default '~' 
/*�۸���*/,
vpricedetail nvarchar(20) null default '~' 
/*�۸����*/,
vbnote nvarchar(181) null 
/*��ע*/,
norigprice decimal(28,8) null 
/*����˰����*/,
norigtaxprice decimal(28,8) null 
/*����˰����*/,
norignetprice decimal(28,8) null 
/*����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*����˰����*/,
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
nordernum decimal(28,8) null 
/*�ۼ����ɶ���������*/,
ncontractnum decimal(28,8) null 
/*�ۼ����ɺ�ͬ������*/,
cqtunitid nvarchar(20) null default '~' 
/*���۵�λ*/,
nqtnum decimal(28,8) null 
/*��������*/,
nqtchangerate nvarchar(60) null 
/*���ۻ�����*/,
pk_salequotation nvarchar(20) null 
/*���۵���ͷ_����*/,
pk_group nvarchar(20) null 
/*����*/,
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
vsrctype nvarchar(20) null 
/*��Դ��������*/,
vsrctrantype nvarchar(20) null 
/*��Դ��������*/,
vsrccode nvarchar(40) null 
/*��Դ���ݺ�*/,
vsrcrowno nvarchar(20) null 
/*��Դ�����к�*/,
csrcid nvarchar(20) null 
/*��Դ��������*/,
csrcbid nvarchar(20) null 
/*��Դ���ݸ���*/,
vfirsttype nvarchar(20) null 
/*Դͷ��������*/,
vfirsttrantype nvarchar(20) null 
/*Դͷ��������*/,
vfirstcode nvarchar(40) null 
/*Դͷ���ݺ�*/,
cfirstid nvarchar(20) null 
/*Դͷ��������*/,
cfirstbid nvarchar(20) null 
/*Դͷ�����ӱ�*/,
vfirstrowno nvarchar(20) null 
/*Դͷ�����к�*/,
 constraint pk_salequotation_b primary key (pk_salequotation_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۵���ͷ */
create table so_salequotation (
pk_salequotation nchar(20) not null 
/*���۱��۵���������*/,
pk_group nvarchar(20) null default '~' 
/*����*/,
pk_org_v nvarchar(20) null default '~' 
/*������֯*/,
pk_org nvarchar(20) null default '~' 
/*������֯���°汾*/,
vbillcode nvarchar(40) null 
/*���۵���*/,
vtrantype nvarchar(20) null default '~' 
/*���۵�����*/,
dquotedate nchar(19) null 
/*��������*/,
denddate nvarchar(19) null 
/*ʧЧ����*/,
pk_customer nvarchar(20) null default '~' 
/*�ͻ�*/,
pk_channeltype nvarchar(20) null default '~' 
/*��������*/,
cemployeeid nvarchar(20) null default '~' 
/*ҵ��Ա*/,
pk_dept_v nvarchar(20) null default '~' 
/*����*/,
pk_dept nvarchar(20) null default '~' 
/*�������°汾*/,
pk_payterm nvarchar(20) null default '~' 
/*�տ�Э��*/,
pk_balatype nvarchar(20) null default '~' 
/*���㷽ʽ*/,
ndiscount decimal(28,8) null 
/*�����ۿ�(%)*/,
pk_currtype nvarchar(20) null default '~' 
/*����*/,
csendtypeid nvarchar(20) null default '~' 
/*���䷽ʽ*/,
ntotalnum decimal(28,8) null 
/*������*/,
ntotalmny decimal(28,8) null 
/*��˰�ϼ�*/,
fstatusflag int null 
/*����״̬*/,
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
billmaker nvarchar(20) null default '~' 
/*�Ƶ���*/,
dbilldate nchar(19) null 
/*�Ƶ�ʱ��*/,
approver nvarchar(20) null default '~' 
/*������*/,
taudittime nvarchar(19) null 
/*�������*/,
modifier nvarchar(20) null default '~' 
/*����޸���*/,
modifiedtime nchar(19) null 
/*����޸�ʱ��*/,
ctrantypeid nvarchar(20) null 
/*��������*/,
creator nvarchar(20) null 
/*������*/,
creationtime nchar(19) null 
/*����ʱ��*/,
dmakedate nchar(19) null 
/*�Ƶ�����*/,
vbillsrctype nvarchar(20) null 
/*������Դ��������*/,
cbillsrcid nvarchar(20) null 
/*������Դ����ID*/,
 constraint pk_m_salequotation primary key (pk_salequotation),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

