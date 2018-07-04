/* tablename: �˻����߷��� */
create table so_returnassign (
pk_returnassign nchar(20) not null 
/*�˻����߷�������*/,
pk_group nvarchar(20) null 
/*����*/,
pk_saleorg nvarchar(20) null 
/*������֯*/,
pk_custclass nvarchar(20) null 
/*�ͻ������������*/,
pk_custsaleclass nvarchar(20) null 
/*�ͻ����۷������*/,
pk_customer nvarchar(20) null 
/*�ͻ�����*/,
pk_marbasclass nvarchar(20) null 
/*���ϻ����������*/,
pk_marsaleclass nvarchar(20) null 
/*�������۷������*/,
pk_material nvarchar(20) null 
/*���ϱ���*/,
pk_productline nvarchar(20) null 
/*��Ʒ�߱���*/,
pk_returnpolicy nvarchar(20) null 
/*�˻����߱���*/,
cprioritycode nvarchar(40) null 
/*������*/,
 constraint pk_so_rtnassign primary key (pk_returnassign),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �˻�ԭ�� */
create table so_returnreason (
vreasonname2 nvarchar(300) null 
/*�˻�ԭ������2*/,
vreasonname6 nvarchar(300) null 
/*�˻�ԭ������6*/,
vreasonname5 nvarchar(300) null 
/*�˻�ԭ������5*/,
vreasonname4 nvarchar(300) null 
/*�˻�ԭ������4*/,
vreasonname3 nvarchar(300) null 
/*�˻�ԭ������3*/,
pk_returnreason nchar(20) not null 
/*�˻�ԭ������*/,
vreasonname nvarchar(300) null 
/*�˻�ԭ������*/,
vreasoncode nvarchar(50) null 
/*�˻�ԭ�����*/,
freasontype smallint null 
/*�˻�ԭ������*/,
vreturnmode nvarchar(181) null 
/*Ĭ���˻����δ���ʽ*/,
vnote nvarchar(181) null 
/*��ע*/,
pk_group nvarchar(20) null default '~' 
/*����*/,
pk_org nvarchar(20) null default '~' 
/*ҵ��Ԫ*/,
 constraint pk_so_returnreason primary key (pk_returnreason),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �˻��������� */
create table so_returnpolicy (
vpolicyname6 nvarchar(300) null 
/*�˻���������6*/,
vpolicyname5 nvarchar(300) null 
/*�˻���������5*/,
vpolicyname4 nvarchar(300) null 
/*�˻���������4*/,
vpolicyname3 nvarchar(300) null 
/*�˻���������3*/,
vpolicyname nvarchar(300) null 
/*�˻���������*/,
vpolicyname2 nvarchar(300) null 
/*�˻���������2*/,
pk_returnpolicy nchar(20) not null 
/*�˻���������*/,
vpolicycode nvarchar(40) null 
/*�˻����߱���*/,
vexpressname nvarchar(50) null 
/*�˻������߼����ʽ*/,
vpolicydetail nvarchar(181) null 
/*�˻�����˵��*/,
dstartdate nchar(19) null 
/*ִ�п�ʼ����*/,
denddate nchar(19) null 
/*ִ�н�������*/,
pk_group nvarchar(20) null default '~' 
/*����*/,
pk_org nvarchar(20) null default '~' 
/*ҵ��Ԫ*/,
vexpresscode nvarchar(50) null 
/*�˻����߱��ʽ����*/,
 constraint pk_so_returnpolicy primary key (pk_returnpolicy),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �˻����� */
create table so_returncndtn (
vconditionname4 nvarchar(300) null 
/*�˻���������4*/,
vconditionname6 nvarchar(300) null 
/*�˻���������6*/,
vconditionname5 nvarchar(300) null 
/*�˻���������5*/,
pk_returncndtn nchar(20) not null 
/*�˻���������*/,
vconditionname3 nvarchar(300) null 
/*�˻���������3*/,
vconditionname2 nvarchar(300) null 
/*�˻���������2*/,
vconditionname nvarchar(300) null 
/*�˻���������*/,
vconditioncode nvarchar(40) null 
/*�˻���������*/,
vexpressname nvarchar(50) null 
/*�˻��������ʽ����*/,
vexpressdetail nvarchar(181) null 
/*�˻�����˵��*/,
pk_group nvarchar(20) null default '~' 
/*����*/,
pk_org nvarchar(20) null default '~' 
/*ҵ��Ԫ*/,
vexpresscode nvarchar(50) null 
/*�˻��������ʽ����*/,
 constraint pk_so_returncndtn primary key (pk_returncndtn),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

