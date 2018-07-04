/* tablename: �ͻ����Ϲ�ϵ��ʵ�� */
create table so_custmatrel (
pk_custmatrel nchar(20) not null 
/*��ʵ������*/,
pk_org nvarchar(20) null 
/*������֯*/,
pk_org_v nvarchar(20) null 
/*������֯�汾*/,
pk_group nvarchar(20) null 
/*����*/,
allowsale int null 
/*��������*/,
 constraint pk_so_custmatrel primary key (pk_custmatrel),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �ͻ����Ϲ�ϵ��ʵ�� */
create table so_custmatrel_b (
pk_custmatrel_b nchar(20) not null 
/*��ʵ������*/,
pk_org nvarchar(20) null 
/*������֯*/,
pk_custbaseclass nvarchar(20) null 
/*�ͻ���������*/,
pk_custsaleclass nvarchar(20) null 
/*�ͻ����۷���*/,
pk_customer nvarchar(20) null 
/*�ͻ�*/,
pk_materialbaseclass nvarchar(20) null 
/*���ϻ�������*/,
pk_materialsaleclass nvarchar(20) null 
/*�������۷���*/,
pk_material nvarchar(20) null 
/*�������°汾*/,
pk_material_v nvarchar(20) null 
/*���ϱ���*/,
exclude nchar(1) null 
/*������*/,
vnote nvarchar(181) null 
/*�б�ע*/,
pk_custmatrel nvarchar(20) null 
/*�ͻ����Ϲ�ϵ��ʵ��_����*/,
pk_group nvarchar(20) null 
/*����*/,
cprioritycode nvarchar(40) null 
/*������*/,
 constraint pk_so_custmatrel_b primary key (pk_custmatrel_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �������Ϲ�ϵ��ʵ�� */
create table so_depmatrel (
pk_depmatrel nchar(20) not null 
/*��ʵ������*/,
pk_org nvarchar(20) null 
/*������֯*/,
pk_org_v nvarchar(20) null 
/*������֯�汾*/,
pk_group nvarchar(20) null 
/*����*/,
allowsale int null 
/*��������*/,
 constraint pk_so_depmatrel primary key (pk_depmatrel),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �������Ϲ�ϵ��ʵ�� */
create table so_depmatrel_b (
pk_org nvarchar(20) null 
/*������֯*/,
pk_depmatrel_b nchar(20) not null 
/*��ʵ������*/,
pk_dept nvarchar(20) null 
/*����*/,
pk_dept_v nvarchar(20) null 
/*���Ű汾*/,
cemployeeid nvarchar(20) null 
/*ҵ��Ա*/,
pk_materialbaseclass nvarchar(20) null 
/*���ϻ�������*/,
pk_materialsaleclass nvarchar(20) null 
/*�������۷���*/,
pk_material nvarchar(20) null 
/*�������°汾*/,
pk_material_v nvarchar(20) null 
/*���ϱ���*/,
exclude nchar(1) null 
/*������*/,
vnote nvarchar(181) null 
/*�б�ע*/,
pk_depmatrel nvarchar(20) null 
/*�������Ϲ�ϵ��ʵ��_����*/,
pk_group nvarchar(20) null 
/*����*/,
cprioritycode nvarchar(40) null 
/*������*/,
 constraint pk_so_depmatrel_b primary key (pk_depmatrel_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �����������Ϲ�ϵ��ʵ�� */
create table so_tranmatrel (
pk_tranmatrel nchar(20) not null 
/*��ʵ������*/,
pk_org nvarchar(20) null 
/*������֯*/,
pk_org_v nvarchar(20) null 
/*������֯�汾*/,
pk_group nvarchar(20) null 
/*����*/,
allowsale int null 
/*��������*/,
applylower nchar(1) null 
/*�����¼�*/,
 constraint pk_so_tranmatrel primary key (pk_tranmatrel),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �����������Ϲ�ϵ��ʵ�� */
create table so_tranmatrel_b (
pk_tranmatrel_b nchar(20) not null 
/*��ʵ������*/,
pk_org nvarchar(20) null 
/*������֯*/,
trantype nvarchar(20) null 
/*��������*/,
pk_materialbaseclass nvarchar(20) null 
/*���ϻ�������*/,
pk_materialsaleclass nvarchar(20) null 
/*�������۷���*/,
pk_material nvarchar(20) null 
/*�������°汾*/,
pk_material_v nvarchar(20) null 
/*���ϱ���*/,
exclude nchar(1) null 
/*������*/,
vnote nvarchar(181) null 
/*�б�ע*/,
pk_tranmatrel nvarchar(20) null 
/*�����������Ϲ�ϵ��ʵ��_����*/,
pk_group nvarchar(20) null 
/*����*/,
cprioritycode nvarchar(40) null 
/*������*/,
 constraint pk_so_tranmatrel_b primary key (pk_tranmatrel_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

