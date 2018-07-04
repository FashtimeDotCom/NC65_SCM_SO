/* tablename: ���۵��������� */
create table so_salequotationtype (pk_trantype char(20) not null 
/*����*/,
vtrantype varchar2(20) default '~' null 
/*���۵���������*/,
fsourceflag varchar2(50) not null 
/*����������Դ*/,
fhistoryflag varchar2(50) not null 
/*��ʷ����Դ*/,
iavgmonth integer null 
/*ƽ����������*/,
vmatchrule varchar2(100) null 
/*��ʷ����ƥ�����*/,
bautocloseflag char(1) null 
/*���ɺ�ͬ/�������Զ��رձ��۵�*/,
pk_group varchar2(20) default '~' null 
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
ctrantypeid varchar2(20) null 
/*��������*/,
bmorerows char(1) default 'N' null 
/*ͬһ����ɷ��ж���*/,
 constraint pk_lequotationtype primary key (pk_trantype),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۵����� */
create table so_salequotation_b (pk_salequotation_b char(20) not null 
/*���۱��۵��ӱ�����*/,
pk_org_v varchar2(20) default '~' null 
/*������֯�汾��Ϣ*/,
pk_org varchar2(20) default '~' null 
/*������֯*/,
crowno varchar2(20) null 
/*�к�*/,
ccustmaterialid varchar2(20) null 
/*�ͻ�������*/,
pk_material_v varchar2(20) default '~' null 
/*���ϱ���*/,
pk_material varchar2(20) default '~' null 
/*�������°汾*/,
pk_qualitylevel varchar2(20) default '~' null 
/*�����ȼ�*/,
pk_project varchar2(20) default '~' null 
/*��Ŀ*/,
pk_productor varchar2(20) default '~' null 
/*��������*/,
pk_supplier varchar2(20) default '~' null 
/*��Ӧ��*/,
castunitid varchar2(20) default '~' null 
/*��λ*/,
nassistnum number(28,8) null 
/*����*/,
nchangerate varchar2(60) null 
/*������*/,
pk_unit varchar2(20) default '~' null 
/*����λ*/,
nnum number(28,8) null 
/*������*/,
pk_areacl varchar2(20) default '~' null 
/*�ջ�����*/,
vfree1 varchar2(100) null 
/*���ɸ�������1*/,
vfree2 varchar2(100) null 
/*���ɸ�������2*/,
vfree3 varchar2(100) null 
/*���ɸ�������3*/,
vfree4 varchar2(100) null 
/*���ɸ�������4*/,
vfree5 varchar2(100) null 
/*���ɸ�������5*/,
vfree6 varchar2(100) null 
/*���ɸ�������6*/,
vfree7 varchar2(100) null 
/*���ɸ�������7*/,
vfree8 varchar2(100) null 
/*���ɸ�������8*/,
vfree9 varchar2(100) null 
/*���ɸ�������9*/,
vfree10 varchar2(100) null 
/*���ɸ�������10*/,
nqtorigprice number(28,8) null 
/*��˰����*/,
ntaxrate number(28,8) null 
/*˰��(%)*/,
nqtorigtaxprice number(28,8) null 
/*��˰����*/,
ndiscountrate number(28,8) null 
/*�����ۿ�(%)*/,
nitemdiscountrate number(28,8) null 
/*��Ʒ�ۿ�(%)*/,
nqtorignetprice number(28,8) null 
/*��˰����*/,
nqtorigtaxnetprc number(28,8) null 
/*��˰����*/,
norigmny number(28,8) null 
/*��˰���*/,
norigtaxmny number(28,8) null 
/*��˰�ϼ�*/,
norigdiscount number(28,8) null 
/*�ۿ۶�*/,
blargessflag char(1) null 
/*��Ʒ*/,
pk_pricepolicy varchar2(20) default '~' null 
/*���۲���*/,
pk_tariffdef varchar2(20) default '~' null 
/*��Ŀ��*/,
pk_pricetype varchar2(20) default '~' null 
/*�۸���*/,
vpricedetail varchar2(20) default '~' null 
/*�۸����*/,
vbnote varchar2(181) null 
/*��ע*/,
norigprice number(28,8) null 
/*����˰����*/,
norigtaxprice number(28,8) null 
/*����˰����*/,
norignetprice number(28,8) null 
/*����˰����*/,
norigtaxnetprice number(28,8) null 
/*����˰����*/,
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
nordernum number(28,8) null 
/*�ۼ����ɶ���������*/,
ncontractnum number(28,8) null 
/*�ۼ����ɺ�ͬ������*/,
cqtunitid varchar2(20) default '~' null 
/*���۵�λ*/,
nqtnum number(28,8) null 
/*��������*/,
nqtchangerate varchar2(60) null 
/*���ۻ�����*/,
pk_salequotation varchar2(20) null 
/*���۵���ͷ_����*/,
pk_group varchar2(20) null 
/*����*/,
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
vsrctype varchar2(20) null 
/*��Դ��������*/,
vsrctrantype varchar2(20) null 
/*��Դ��������*/,
vsrccode varchar2(40) null 
/*��Դ���ݺ�*/,
vsrcrowno varchar2(20) null 
/*��Դ�����к�*/,
csrcid varchar2(20) null 
/*��Դ��������*/,
csrcbid varchar2(20) null 
/*��Դ���ݸ���*/,
vfirsttype varchar2(20) null 
/*Դͷ��������*/,
vfirsttrantype varchar2(20) null 
/*Դͷ��������*/,
vfirstcode varchar2(40) null 
/*Դͷ���ݺ�*/,
cfirstid varchar2(20) null 
/*Դͷ��������*/,
cfirstbid varchar2(20) null 
/*Դͷ�����ӱ�*/,
vfirstrowno varchar2(20) null 
/*Դͷ�����к�*/,
 constraint pk_salequotation_b primary key (pk_salequotation_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۵���ͷ */
create table so_salequotation (pk_salequotation char(20) not null 
/*���۱��۵���������*/,
pk_group varchar2(20) default '~' null 
/*����*/,
pk_org_v varchar2(20) default '~' null 
/*������֯*/,
pk_org varchar2(20) default '~' null 
/*������֯���°汾*/,
vbillcode varchar2(40) null 
/*���۵���*/,
vtrantype varchar2(20) default '~' null 
/*���۵�����*/,
dquotedate char(19) null 
/*��������*/,
denddate varchar2(19) null 
/*ʧЧ����*/,
pk_customer varchar2(20) default '~' null 
/*�ͻ�*/,
pk_channeltype varchar2(20) default '~' null 
/*��������*/,
cemployeeid varchar2(20) default '~' null 
/*ҵ��Ա*/,
pk_dept_v varchar2(20) default '~' null 
/*����*/,
pk_dept varchar2(20) default '~' null 
/*�������°汾*/,
pk_payterm varchar2(20) default '~' null 
/*�տ�Э��*/,
pk_balatype varchar2(20) default '~' null 
/*���㷽ʽ*/,
ndiscount number(28,8) null 
/*�����ۿ�(%)*/,
pk_currtype varchar2(20) default '~' null 
/*����*/,
csendtypeid varchar2(20) default '~' null 
/*���䷽ʽ*/,
ntotalnum number(28,8) null 
/*������*/,
ntotalmny number(28,8) null 
/*��˰�ϼ�*/,
fstatusflag integer null 
/*����״̬*/,
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
billmaker varchar2(20) default '~' null 
/*�Ƶ���*/,
dbilldate char(19) null 
/*�Ƶ�ʱ��*/,
approver varchar2(20) default '~' null 
/*������*/,
taudittime varchar2(19) null 
/*�������*/,
modifier varchar2(20) default '~' null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
ctrantypeid varchar2(20) null 
/*��������*/,
creator varchar2(20) null 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
vbillsrctype varchar2(20) null 
/*������Դ��������*/,
cbillsrcid varchar2(20) null 
/*������Դ����ID*/,
 constraint pk_m_salequotation primary key (pk_salequotation),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

