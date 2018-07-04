/* tablename: �˻����߷��� */
create table so_returnassign (pk_returnassign char(20) not null 
/*�˻����߷�������*/,
pk_group varchar(20) null 
/*����*/,
pk_saleorg varchar(20) null 
/*������֯*/,
pk_custclass varchar(20) null 
/*�ͻ������������*/,
pk_custsaleclass varchar(20) null 
/*�ͻ����۷������*/,
pk_customer varchar(20) null 
/*�ͻ�����*/,
pk_marbasclass varchar(20) null 
/*���ϻ����������*/,
pk_marsaleclass varchar(20) null 
/*�������۷������*/,
pk_material varchar(20) null 
/*���ϱ���*/,
pk_productline varchar(20) null 
/*��Ʒ�߱���*/,
pk_returnpolicy varchar(20) null 
/*�˻����߱���*/,
cprioritycode varchar(40) null 
/*������*/,
 constraint pk_so_rtnassign primary key (pk_returnassign),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �˻�ԭ�� */
create table so_returnreason (vreasonname2 varchar(300) null 
/*�˻�ԭ������2*/,
vreasonname6 varchar(300) null 
/*�˻�ԭ������6*/,
vreasonname5 varchar(300) null 
/*�˻�ԭ������5*/,
vreasonname4 varchar(300) null 
/*�˻�ԭ������4*/,
vreasonname3 varchar(300) null 
/*�˻�ԭ������3*/,
pk_returnreason char(20) not null 
/*�˻�ԭ������*/,
vreasonname varchar(300) null 
/*�˻�ԭ������*/,
vreasoncode varchar(50) null 
/*�˻�ԭ�����*/,
freasontype smallint null 
/*�˻�ԭ������*/,
vreturnmode varchar(181) null 
/*Ĭ���˻����δ���ʽ*/,
vnote varchar(181) null 
/*��ע*/,
pk_group varchar(20) null default '~' 
/*����*/,
pk_org varchar(20) null default '~' 
/*ҵ��Ԫ*/,
 constraint pk_so_returnreason primary key (pk_returnreason),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �˻��������� */
create table so_returnpolicy (vpolicyname6 varchar(300) null 
/*�˻���������6*/,
vpolicyname5 varchar(300) null 
/*�˻���������5*/,
vpolicyname4 varchar(300) null 
/*�˻���������4*/,
vpolicyname3 varchar(300) null 
/*�˻���������3*/,
vpolicyname varchar(300) null 
/*�˻���������*/,
vpolicyname2 varchar(300) null 
/*�˻���������2*/,
pk_returnpolicy char(20) not null 
/*�˻���������*/,
vpolicycode varchar(40) null 
/*�˻����߱���*/,
vexpressname varchar(50) null 
/*�˻������߼����ʽ*/,
vpolicydetail varchar(181) null 
/*�˻�����˵��*/,
dstartdate char(19) null 
/*ִ�п�ʼ����*/,
denddate char(19) null 
/*ִ�н�������*/,
pk_group varchar(20) null default '~' 
/*����*/,
pk_org varchar(20) null default '~' 
/*ҵ��Ԫ*/,
vexpresscode varchar(50) null 
/*�˻����߱��ʽ����*/,
 constraint pk_so_returnpolicy primary key (pk_returnpolicy),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �˻����� */
create table so_returncndtn (vconditionname4 varchar(300) null 
/*�˻���������4*/,
vconditionname6 varchar(300) null 
/*�˻���������6*/,
vconditionname5 varchar(300) null 
/*�˻���������5*/,
pk_returncndtn char(20) not null 
/*�˻���������*/,
vconditionname3 varchar(300) null 
/*�˻���������3*/,
vconditionname2 varchar(300) null 
/*�˻���������2*/,
vconditionname varchar(300) null 
/*�˻���������*/,
vconditioncode varchar(40) null 
/*�˻���������*/,
vexpressname varchar(50) null 
/*�˻��������ʽ����*/,
vexpressdetail varchar(181) null 
/*�˻�����˵��*/,
pk_group varchar(20) null default '~' 
/*����*/,
pk_org varchar(20) null default '~' 
/*ҵ��Ԫ*/,
vexpresscode varchar(50) null 
/*�˻��������ʽ����*/,
 constraint pk_so_returncndtn primary key (pk_returncndtn),
 ts char(19) null,
dr smallint null default 0
)
;

