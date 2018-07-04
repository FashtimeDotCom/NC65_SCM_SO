/* tablename: ���۵��������� */
create table so_salequotationtype (pk_trantype char(20) not null 
/*����*/,
vtrantype varchar(20) null default '~' 
/*���۵���������*/,
fsourceflag varchar(50) not null 
/*����������Դ*/,
fhistoryflag varchar(50) not null 
/*��ʷ����Դ*/,
iavgmonth integer null 
/*ƽ����������*/,
vmatchrule varchar(100) null 
/*��ʷ����ƥ�����*/,
bautocloseflag char(1) null 
/*���ɺ�ͬ/�������Զ��رձ��۵�*/,
pk_group varchar(20) null default '~' 
/*����*/,
bsuccqteditable char(1) null 
/*ѯ���۸�ɸ�*/,
bfailqteditable char(1) null 
/*δѯ���۸�ɸ�*/,
bdiscounteditable char(1) null 
/*�����޸��ۿ�*/,
bcustmatrule char(1) null 
/*�ͻ�*/,
bchanmatrule char(1) null 
/*��������*/,
bquotypematrule char(1) null 
/*���۵�����*/,
bdeptmatrule char(1) null 
/*����*/,
bempmatrule char(1) null 
/*ҵ��Ա*/,
bpaytermmatrule char(1) null 
/*�տ�Э��*/,
bbalatypematrule char(1) null 
/*���㷽ʽ*/,
bcurrtypematrule char(1) null 
/*����ƥ��*/,
bunitmatrule char(1) null 
/*��λƥ��*/,
bsendtypematrule char(1) null 
/*���䷽ʽ*/,
bquallevelmatrule char(1) null 
/*�����ȼ�*/,
bareamatrule char(1) null 
/*�ջ�����*/,
fmodifymny smallint null 
/*�������Ӱ�쵥��*/,
ctrantypeid varchar(20) null 
/*��������*/,
bmorerows char(1) null default 'N' 
/*ͬһ����ɷ��ж���*/,
 constraint pk_lequotationtype primary key (pk_trantype),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۵����� */
create table so_salequotation_b (pk_salequotation_b char(20) not null 
/*���۱��۵��ӱ�����*/,
pk_org_v varchar(20) null default '~' 
/*������֯�汾��Ϣ*/,
pk_org varchar(20) null default '~' 
/*������֯*/,
crowno varchar(20) null 
/*�к�*/,
ccustmaterialid varchar(20) null 
/*�ͻ�������*/,
pk_material_v varchar(20) null default '~' 
/*���ϱ���*/,
pk_material varchar(20) null default '~' 
/*�������°汾*/,
pk_qualitylevel varchar(20) null default '~' 
/*�����ȼ�*/,
pk_project varchar(20) null default '~' 
/*��Ŀ*/,
pk_productor varchar(20) null default '~' 
/*��������*/,
pk_supplier varchar(20) null default '~' 
/*��Ӧ��*/,
castunitid varchar(20) null default '~' 
/*��λ*/,
nassistnum decimal(28,8) null 
/*����*/,
nchangerate varchar(60) null 
/*������*/,
pk_unit varchar(20) null default '~' 
/*����λ*/,
nnum decimal(28,8) null 
/*������*/,
pk_areacl varchar(20) null default '~' 
/*�ջ�����*/,
vfree1 varchar(100) null 
/*���ɸ�������1*/,
vfree2 varchar(100) null 
/*���ɸ�������2*/,
vfree3 varchar(100) null 
/*���ɸ�������3*/,
vfree4 varchar(100) null 
/*���ɸ�������4*/,
vfree5 varchar(100) null 
/*���ɸ�������5*/,
vfree6 varchar(100) null 
/*���ɸ�������6*/,
vfree7 varchar(100) null 
/*���ɸ�������7*/,
vfree8 varchar(100) null 
/*���ɸ�������8*/,
vfree9 varchar(100) null 
/*���ɸ�������9*/,
vfree10 varchar(100) null 
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
blargessflag char(1) null 
/*��Ʒ*/,
pk_pricepolicy varchar(20) null default '~' 
/*���۲���*/,
pk_tariffdef varchar(20) null default '~' 
/*��Ŀ��*/,
pk_pricetype varchar(20) null default '~' 
/*�۸���*/,
vpricedetail varchar(20) null default '~' 
/*�۸����*/,
vbnote varchar(181) null 
/*��ע*/,
norigprice decimal(28,8) null 
/*����˰����*/,
norigtaxprice decimal(28,8) null 
/*����˰����*/,
norignetprice decimal(28,8) null 
/*����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*����˰����*/,
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
nordernum decimal(28,8) null 
/*�ۼ����ɶ���������*/,
ncontractnum decimal(28,8) null 
/*�ۼ����ɺ�ͬ������*/,
cqtunitid varchar(20) null default '~' 
/*���۵�λ*/,
nqtnum decimal(28,8) null 
/*��������*/,
nqtchangerate varchar(60) null 
/*���ۻ�����*/,
pk_salequotation varchar(20) null 
/*���۵���ͷ_����*/,
pk_group varchar(20) null 
/*����*/,
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
vsrctype varchar(20) null 
/*��Դ��������*/,
vsrctrantype varchar(20) null 
/*��Դ��������*/,
vsrccode varchar(40) null 
/*��Դ���ݺ�*/,
vsrcrowno varchar(20) null 
/*��Դ�����к�*/,
csrcid varchar(20) null 
/*��Դ��������*/,
csrcbid varchar(20) null 
/*��Դ���ݸ���*/,
vfirsttype varchar(20) null 
/*Դͷ��������*/,
vfirsttrantype varchar(20) null 
/*Դͷ��������*/,
vfirstcode varchar(40) null 
/*Դͷ���ݺ�*/,
cfirstid varchar(20) null 
/*Դͷ��������*/,
cfirstbid varchar(20) null 
/*Դͷ�����ӱ�*/,
vfirstrowno varchar(20) null 
/*Դͷ�����к�*/,
 constraint pk_salequotation_b primary key (pk_salequotation_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۵���ͷ */
create table so_salequotation (pk_salequotation char(20) not null 
/*���۱��۵���������*/,
pk_group varchar(20) null default '~' 
/*����*/,
pk_org_v varchar(20) null default '~' 
/*������֯*/,
pk_org varchar(20) null default '~' 
/*������֯���°汾*/,
vbillcode varchar(40) null 
/*���۵���*/,
vtrantype varchar(20) null default '~' 
/*���۵�����*/,
dquotedate char(19) null 
/*��������*/,
denddate varchar(19) null 
/*ʧЧ����*/,
pk_customer varchar(20) null default '~' 
/*�ͻ�*/,
pk_channeltype varchar(20) null default '~' 
/*��������*/,
cemployeeid varchar(20) null default '~' 
/*ҵ��Ա*/,
pk_dept_v varchar(20) null default '~' 
/*����*/,
pk_dept varchar(20) null default '~' 
/*�������°汾*/,
pk_payterm varchar(20) null default '~' 
/*�տ�Э��*/,
pk_balatype varchar(20) null default '~' 
/*���㷽ʽ*/,
ndiscount decimal(28,8) null 
/*�����ۿ�(%)*/,
pk_currtype varchar(20) null default '~' 
/*����*/,
csendtypeid varchar(20) null default '~' 
/*���䷽ʽ*/,
ntotalnum decimal(28,8) null 
/*������*/,
ntotalmny decimal(28,8) null 
/*��˰�ϼ�*/,
fstatusflag integer null 
/*����״̬*/,
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
billmaker varchar(20) null default '~' 
/*�Ƶ���*/,
dbilldate char(19) null 
/*�Ƶ�ʱ��*/,
approver varchar(20) null default '~' 
/*������*/,
taudittime varchar(19) null 
/*�������*/,
modifier varchar(20) null default '~' 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
ctrantypeid varchar(20) null 
/*��������*/,
creator varchar(20) null 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
vbillsrctype varchar(20) null 
/*������Դ��������*/,
cbillsrcid varchar(20) null 
/*������Դ����ID*/,
 constraint pk_m_salequotation primary key (pk_salequotation),
 ts char(19) null,
dr smallint null default 0
)
;

