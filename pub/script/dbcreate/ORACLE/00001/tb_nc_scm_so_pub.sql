/* tablename: ҵ���������� */
create table so_busitypeset (pk_busitype char(20) null 
/*ҵ������ID*/,
fgeninvtype char(1) null 
/*��Ʊ����*/,
pk_org varchar2(20) null 
/*��֯*/,
  ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

