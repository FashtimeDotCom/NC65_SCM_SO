/* tablename: �ͻ����Ϲ�ϵ��ʵ�� */
create table so_custmatrel (pk_custmatrel char(20) not null 
/*��ʵ������*/,
pk_org varchar(20) null 
/*������֯*/,
pk_org_v varchar(20) null 
/*������֯�汾*/,
pk_group varchar(20) null 
/*����*/,
allowsale integer null 
/*��������*/,
 constraint pk_so_custmatrel primary key (pk_custmatrel),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �ͻ����Ϲ�ϵ��ʵ�� */
create table so_custmatrel_b (pk_custmatrel_b char(20) not null 
/*��ʵ������*/,
pk_org varchar(20) null 
/*������֯*/,
pk_custbaseclass varchar(20) null 
/*�ͻ���������*/,
pk_custsaleclass varchar(20) null 
/*�ͻ����۷���*/,
pk_customer varchar(20) null 
/*�ͻ�*/,
pk_materialbaseclass varchar(20) null 
/*���ϻ�������*/,
pk_materialsaleclass varchar(20) null 
/*�������۷���*/,
pk_material varchar(20) null 
/*�������°汾*/,
pk_material_v varchar(20) null 
/*���ϱ���*/,
exclude char(1) null 
/*������*/,
vnote varchar(181) null 
/*�б�ע*/,
pk_custmatrel varchar(20) null 
/*�ͻ����Ϲ�ϵ��ʵ��_����*/,
pk_group varchar(20) null 
/*����*/,
cprioritycode varchar(40) null 
/*������*/,
 constraint pk_so_custmatrel_b primary key (pk_custmatrel_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �������Ϲ�ϵ��ʵ�� */
create table so_depmatrel (pk_depmatrel char(20) not null 
/*��ʵ������*/,
pk_org varchar(20) null 
/*������֯*/,
pk_org_v varchar(20) null 
/*������֯�汾*/,
pk_group varchar(20) null 
/*����*/,
allowsale integer null 
/*��������*/,
 constraint pk_so_depmatrel primary key (pk_depmatrel),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �������Ϲ�ϵ��ʵ�� */
create table so_depmatrel_b (pk_org varchar(20) null 
/*������֯*/,
pk_depmatrel_b char(20) not null 
/*��ʵ������*/,
pk_dept varchar(20) null 
/*����*/,
pk_dept_v varchar(20) null 
/*���Ű汾*/,
cemployeeid varchar(20) null 
/*ҵ��Ա*/,
pk_materialbaseclass varchar(20) null 
/*���ϻ�������*/,
pk_materialsaleclass varchar(20) null 
/*�������۷���*/,
pk_material varchar(20) null 
/*�������°汾*/,
pk_material_v varchar(20) null 
/*���ϱ���*/,
exclude char(1) null 
/*������*/,
vnote varchar(181) null 
/*�б�ע*/,
pk_depmatrel varchar(20) null 
/*�������Ϲ�ϵ��ʵ��_����*/,
pk_group varchar(20) null 
/*����*/,
cprioritycode varchar(40) null 
/*������*/,
 constraint pk_so_depmatrel_b primary key (pk_depmatrel_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �����������Ϲ�ϵ��ʵ�� */
create table so_tranmatrel (pk_tranmatrel char(20) not null 
/*��ʵ������*/,
pk_org varchar(20) null 
/*������֯*/,
pk_org_v varchar(20) null 
/*������֯�汾*/,
pk_group varchar(20) null 
/*����*/,
allowsale integer null 
/*��������*/,
applylower char(1) null 
/*�����¼�*/,
 constraint pk_so_tranmatrel primary key (pk_tranmatrel),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �����������Ϲ�ϵ��ʵ�� */
create table so_tranmatrel_b (pk_tranmatrel_b char(20) not null 
/*��ʵ������*/,
pk_org varchar(20) null 
/*������֯*/,
trantype varchar(20) null 
/*��������*/,
pk_materialbaseclass varchar(20) null 
/*���ϻ�������*/,
pk_materialsaleclass varchar(20) null 
/*�������۷���*/,
pk_material varchar(20) null 
/*�������°汾*/,
pk_material_v varchar(20) null 
/*���ϱ���*/,
exclude char(1) null 
/*������*/,
vnote varchar(181) null 
/*�б�ע*/,
pk_tranmatrel varchar(20) null 
/*�����������Ϲ�ϵ��ʵ��_����*/,
pk_group varchar(20) null 
/*����*/,
cprioritycode varchar(40) null 
/*������*/,
 constraint pk_so_tranmatrel_b primary key (pk_tranmatrel_b),
 ts char(19) null,
dr smallint null default 0
)
;

