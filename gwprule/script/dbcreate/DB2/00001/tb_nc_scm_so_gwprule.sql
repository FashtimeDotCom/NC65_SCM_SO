/* tablename: �������� */
create table so_buylargess (pk_buylargess char(20) not null 
/*��������id*/,
pk_org varchar(20) null 
/*������֯*/,
pk_group varchar(20) null 
/*����*/,
cbuymarid varchar(20) null 
/*���ϱ���*/,
cbuyunitid varchar(20) null 
/*��λ*/,
pk_marbasclass varchar(20) null 
/*���ϻ�������*/,
pk_marsaleclass varchar(20) null 
/*�������۷���*/,
pk_customer varchar(20) null 
/*�ͻ�*/,
pk_custclass varchar(20) null 
/*�ͻ���������*/,
pk_custsaleclass varchar(20) null 
/*�ͻ����۷���*/,
nbuynum decimal(28,8) null 
/*��������*/,
pk_currinfo varchar(20) null 
/*����*/,
islow char(1) null 
/*�����¼�*/,
cprioritycode varchar(40) null 
/*������*/,
cpromottypeid varchar(20) null 
/*��������*/,
cmarketactid varchar(20) null 
/*Ӫ���*/,
 constraint pk_so_buylargess primary key (pk_buylargess),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �����ӱ� */
create table so_buylargess_b (pk_buylargess_b char(20) not null 
/*�����ӱ�id*/,
pk_material varchar(20) null 
/*���ϱ���*/,
pk_measdoc varchar(20) null 
/*��λ*/,
nnum decimal(28,8) null 
/*��������*/,
nprice decimal(28,8) null 
/*����*/,
nmny decimal(28,8) null 
/*���*/,
ftoplimittype integer null 
/*��������*/,
ntoplimitvalue decimal(20,8) null 
/*����ֵ*/,
dbegdate varchar(19) null 
/*��ʼ����*/,
denddate varchar(19) null 
/*��ֹ����*/,
pk_buylargess varchar(20) not null 
/*��������id*/,
pk_group varchar(20) null 
/*����*/,
 constraint pk_so_buylargess_b primary key (pk_buylargess_b),
 ts char(19) null,
dr smallint null default 0
)
;

