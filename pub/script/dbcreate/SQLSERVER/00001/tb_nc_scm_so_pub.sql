/* tablename: ҵ���������� */
create table so_busitypeset (
pk_busitype nchar(20) null 
/*ҵ������ID*/,
fgeninvtype nchar(1) null 
/*��Ʊ����*/,
pk_org nvarchar(20) null 
/*��֯*/,
  ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

