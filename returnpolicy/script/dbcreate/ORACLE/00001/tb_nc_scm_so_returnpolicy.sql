/* tablename: �˻����߷��� */
create table so_returnassign (pk_returnassign char(20) not null 
/*�˻����߷�������*/,
pk_group varchar2(20) null 
/*����*/,
pk_saleorg varchar2(20) null 
/*������֯*/,
pk_custclass varchar2(20) null 
/*�ͻ������������*/,
pk_custsaleclass varchar2(20) null 
/*�ͻ����۷������*/,
pk_customer varchar2(20) null 
/*�ͻ�����*/,
pk_marbasclass varchar2(20) null 
/*���ϻ����������*/,
pk_marsaleclass varchar2(20) null 
/*�������۷������*/,
pk_material varchar2(20) null 
/*���ϱ���*/,
pk_productline varchar2(20) null 
/*��Ʒ�߱���*/,
pk_returnpolicy varchar2(20) null 
/*�˻����߱���*/,
cprioritycode varchar2(40) null 
/*������*/,
 constraint pk_so_rtnassign primary key (pk_returnassign),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: �˻�ԭ�� */
create table so_returnreason (vreasonname2 varchar2(300) null 
/*�˻�ԭ������2*/,
vreasonname6 varchar2(300) null 
/*�˻�ԭ������6*/,
vreasonname5 varchar2(300) null 
/*�˻�ԭ������5*/,
vreasonname4 varchar2(300) null 
/*�˻�ԭ������4*/,
vreasonname3 varchar2(300) null 
/*�˻�ԭ������3*/,
pk_returnreason char(20) not null 
/*�˻�ԭ������*/,
vreasonname varchar2(300) null 
/*�˻�ԭ������*/,
vreasoncode varchar2(50) null 
/*�˻�ԭ�����*/,
freasontype smallint null 
/*�˻�ԭ������*/,
vreturnmode varchar2(181) null 
/*Ĭ���˻����δ���ʽ*/,
vnote varchar2(181) null 
/*��ע*/,
pk_group varchar2(20) default '~' null 
/*����*/,
pk_org varchar2(20) default '~' null 
/*ҵ��Ԫ*/,
 constraint pk_so_returnreason primary key (pk_returnreason),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: �˻��������� */
create table so_returnpolicy (vpolicyname6 varchar2(300) null 
/*�˻���������6*/,
vpolicyname5 varchar2(300) null 
/*�˻���������5*/,
vpolicyname4 varchar2(300) null 
/*�˻���������4*/,
vpolicyname3 varchar2(300) null 
/*�˻���������3*/,
vpolicyname varchar2(300) null 
/*�˻���������*/,
vpolicyname2 varchar2(300) null 
/*�˻���������2*/,
pk_returnpolicy char(20) not null 
/*�˻���������*/,
vpolicycode varchar2(40) null 
/*�˻����߱���*/,
vexpressname varchar2(50) null 
/*�˻������߼����ʽ*/,
vpolicydetail varchar2(181) null 
/*�˻�����˵��*/,
dstartdate char(19) null 
/*ִ�п�ʼ����*/,
denddate char(19) null 
/*ִ�н�������*/,
pk_group varchar2(20) default '~' null 
/*����*/,
pk_org varchar2(20) default '~' null 
/*ҵ��Ԫ*/,
vexpresscode varchar2(50) null 
/*�˻����߱��ʽ����*/,
 constraint pk_so_returnpolicy primary key (pk_returnpolicy),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: �˻����� */
create table so_returncndtn (vconditionname4 varchar2(300) null 
/*�˻���������4*/,
vconditionname6 varchar2(300) null 
/*�˻���������6*/,
vconditionname5 varchar2(300) null 
/*�˻���������5*/,
pk_returncndtn char(20) not null 
/*�˻���������*/,
vconditionname3 varchar2(300) null 
/*�˻���������3*/,
vconditionname2 varchar2(300) null 
/*�˻���������2*/,
vconditionname varchar2(300) null 
/*�˻���������*/,
vconditioncode varchar2(40) null 
/*�˻���������*/,
vexpressname varchar2(50) null 
/*�˻��������ʽ����*/,
vexpressdetail varchar2(181) null 
/*�˻�����˵��*/,
pk_group varchar2(20) default '~' null 
/*����*/,
pk_org varchar2(20) default '~' null 
/*ҵ��Ԫ*/,
vexpresscode varchar2(50) null 
/*�˻��������ʽ����*/,
 constraint pk_so_returncndtn primary key (pk_returncndtn),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

