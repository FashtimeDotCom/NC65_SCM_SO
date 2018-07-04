/* tablename: �������� */
create table so_buylargess (pk_buylargess char(20) not null 
/*��������id*/,
pk_org varchar2(20) null 
/*������֯*/,
pk_group varchar2(20) null 
/*����*/,
cbuymarid varchar2(20) null 
/*���ϱ���*/,
cbuyunitid varchar2(20) null 
/*��λ*/,
pk_marbasclass varchar2(20) null 
/*���ϻ�������*/,
pk_marsaleclass varchar2(20) null 
/*�������۷���*/,
pk_customer varchar2(20) null 
/*�ͻ�*/,
pk_custclass varchar2(20) null 
/*�ͻ���������*/,
pk_custsaleclass varchar2(20) null 
/*�ͻ����۷���*/,
nbuynum number(28,8) null 
/*��������*/,
pk_currinfo varchar2(20) null 
/*����*/,
islow char(1) null 
/*�����¼�*/,
cprioritycode varchar2(40) null 
/*������*/,
cpromottypeid varchar2(20) null 
/*��������*/,
cmarketactid varchar2(20) null 
/*Ӫ���*/,
 constraint pk_so_buylargess primary key (pk_buylargess),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: �����ӱ� */
create table so_buylargess_b (pk_buylargess_b char(20) not null 
/*�����ӱ�id*/,
pk_material varchar2(20) null 
/*���ϱ���*/,
pk_measdoc varchar2(20) null 
/*��λ*/,
nnum number(28,8) null 
/*��������*/,
nprice number(28,8) null 
/*����*/,
nmny number(28,8) null 
/*���*/,
ftoplimittype integer null 
/*��������*/,
ntoplimitvalue number(20,8) null 
/*����ֵ*/,
dbegdate varchar2(19) null 
/*��ʼ����*/,
denddate varchar2(19) null 
/*��ֹ����*/,
pk_buylargess varchar2(20) not null 
/*��������id*/,
pk_group varchar2(20) null 
/*����*/,
 constraint pk_so_buylargess_b primary key (pk_buylargess_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

