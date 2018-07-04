/* tablename: �������� */
create table so_buylargess (
pk_buylargess nchar(20) not null 
/*��������id*/,
pk_org nvarchar(20) null 
/*������֯*/,
pk_group nvarchar(20) null 
/*����*/,
cbuymarid nvarchar(20) null 
/*���ϱ���*/,
cbuyunitid nvarchar(20) null 
/*��λ*/,
pk_marbasclass nvarchar(20) null 
/*���ϻ�������*/,
pk_marsaleclass nvarchar(20) null 
/*�������۷���*/,
pk_customer nvarchar(20) null 
/*�ͻ�*/,
pk_custclass nvarchar(20) null 
/*�ͻ���������*/,
pk_custsaleclass nvarchar(20) null 
/*�ͻ����۷���*/,
nbuynum decimal(28,8) null 
/*��������*/,
pk_currinfo nvarchar(20) null 
/*����*/,
islow nchar(1) null 
/*�����¼�*/,
cprioritycode nvarchar(40) null 
/*������*/,
cpromottypeid nvarchar(20) null 
/*��������*/,
cmarketactid nvarchar(20) null 
/*Ӫ���*/,
 constraint pk_so_buylargess primary key (pk_buylargess),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �����ӱ� */
create table so_buylargess_b (
pk_buylargess_b nchar(20) not null 
/*�����ӱ�id*/,
pk_material nvarchar(20) null 
/*���ϱ���*/,
pk_measdoc nvarchar(20) null 
/*��λ*/,
nnum decimal(28,8) null 
/*��������*/,
nprice decimal(28,8) null 
/*����*/,
nmny decimal(28,8) null 
/*���*/,
ftoplimittype int null 
/*��������*/,
ntoplimitvalue decimal(20,8) null 
/*����ֵ*/,
dbegdate nvarchar(19) null 
/*��ʼ����*/,
denddate nvarchar(19) null 
/*��ֹ����*/,
pk_buylargess nvarchar(20) not null 
/*��������id*/,
pk_group nvarchar(20) null 
/*����*/,
 constraint pk_so_buylargess_b primary key (pk_buylargess_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

