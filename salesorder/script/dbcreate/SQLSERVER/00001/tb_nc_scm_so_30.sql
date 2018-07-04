/* tablename: ���۶����������� */
create table so_m30trantype (
ctrantypeid nvarchar(20) null 
/*��������*/,
pk_trantype nchar(20) not null 
/*���۶���������������*/,
fdirecttype smallint null 
/*ֱ�����ͱ��*/,
fsalemode smallint null 
/*����ģʽ*/,
bmorerows nchar(1) null 
/*ͬһ����ɷ��ж���*/,
bcanchangeout nchar(1) null 
/*�˻����֮����ܻ�������*/,
faskqtrule smallint null 
/*ѯ�۹���*/,
bmodifyaskedqt nchar(1) null 
/*ѯ���۸��Ƿ�ɸ�*/,
bmodifyunaskedqt nchar(1) null 
/*δѯ���۸��Ƿ�ɸ�*/,
flargessgetqtrule smallint null 
/*��Ʒȡ�۹���*/,
bmodifydiscount nchar(1) null 
/*�����޸��ۿ�*/,
vtrantypecode nvarchar(20) null 
/*�������ͱ���*/,
pk_group nvarchar(20) null 
/*����ID*/,
fmodifymny smallint null 
/*�������Ӱ���ۿۻ��ǵ���*/,
breviseprice nchar(1) null 
/*�޶�ѯ��*/,
bredorderpay nchar(1) null 
/*���ֶ���֧�ֶ����տ�*/,
flargessdistype smallint null 
/*��Ʒ�۸��̯��ʽ*/,
blargesspriceno nchar(1) null 
/*��Ʒ�м۸񱣳ֲ���*/,
barrangeinv nchar(1) null 
/*ֻ�ܰ���һ�η���*/,
barrangeout nchar(1) null 
/*ֻ�ܰ���һ�γ���*/,
bnofindpricehit nchar(1) null 
/*δѯ���۸��Ƿ���ʾ*/,
blossrenew nchar(1) null 
/*;�𲹻�*/,
blrgcashflag nchar(1) null 
/*��Ʒ�Ҹ�*/,
naccpricerule smallint null 
/*�����˵���ȡ�۹���*/,
bcopyiseprice nchar(1) null 
/*�����Ƿ�ѯ��*/,
 constraint pk_so_m30trantype primary key (pk_trantype),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۶������� */
create table so_saleorder (
csaleorderid nchar(20) not null 
/*��������ID*/,
pk_org nvarchar(20) null 
/*������֯*/,
pk_group nvarchar(20) null 
/*����*/,
cbiztypeid nvarchar(20) null 
/*ҵ������*/,
ctrantypeid nvarchar(20) null 
/*��������*/,
vtrantypecode nvarchar(20) null 
/*�������ͱ���*/,
vbillcode nvarchar(40) null 
/*���ݺ�*/,
ccustomerid nvarchar(20) null 
/*�ͻ�*/,
dbilldate nchar(19) null 
/*��������*/,
cdeptid nvarchar(20) null 
/*����*/,
corigcurrencyid nvarchar(20) null 
/*ԭ�ұ���*/,
cemployeeid nvarchar(20) null 
/*ҵ��Ա*/,
cpaytermid nvarchar(20) null 
/*�տ�Э��*/,
cinvoicecustid nvarchar(20) null 
/*��Ʊ�ͻ�*/,
ccustbankid nvarchar(20) null 
/*��������*/,
ccustbankaccid nvarchar(20) null 
/*���������˺�*/,
ctransporttypeid nvarchar(20) null 
/*���䷽ʽ*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
vrevisereason nvarchar(181) null 
/*�޶�����*/,
badvfeeflag nchar(1) null 
/*�����˷�*/,
bfreecustflag nchar(1) null 
/*�Ƿ�ɢ��*/,
vcreditnum nvarchar(20) null 
/*����֤��*/,
cfreecustid nvarchar(20) null 
/*ɢ��*/,
billmaker nvarchar(20) null 
/*�Ƶ���*/,
creationtime nchar(19) null 
/*����ʱ��*/,
approver nvarchar(20) null 
/*������*/,
taudittime nvarchar(19) null 
/*�������*/,
modifiedtime nchar(19) null 
/*�޸�ʱ��*/,
fstatusflag smallint null 
/*����״̬*/,
vnote nvarchar(181) null 
/*��ע*/,
boutendflag nchar(1) null 
/*����رձ��*/,
binvoicendflag nchar(1) null 
/*��Ʊ�رձ��*/,
bcostsettleflag nchar(1) null 
/*�ɱ�����رձ��*/,
bsendendflag nchar(1) null 
/*�����رձ��*/,
npreceiverate decimal(28,8) null 
/*�����տ����*/,
npreceivequota decimal(28,8) null 
/*�����տ��޶�*/,
bpreceiveflag nchar(1) null 
/*�տ��޶����Ԥ��*/,
npreceivemny decimal(28,8) null 
/*ʵ��Ԥ�տ���*/,
nreceivedmny decimal(28,8) null 
/*ʵ���տ���*/,
iprintcount int null 
/*��ӡ����*/,
ntotalorigmny decimal(28,8) null 
/*���ϼ�*/,
ntotalorigsubmny decimal(28,8) null 
/*��ֽ��*/,
boffsetflag nchar(1) null 
/*�Ƿ���*/,
bcooptopoflag nchar(1) null 
/*�Ƿ���Эͬ���ɲɹ�����*/,
bpocooptomeflag nchar(1) null 
/*�Ƿ��ɲɹ�����Эͬ����*/,
vcooppohcode nvarchar(40) null 
/*�Է�������*/,
iversion int null 
/*�޶��汾��*/,
trevisetime nchar(19) null 
/*�޶�ʱ???*/,
creviserid nvarchar(20) null 
/*�޶���*/,
cbalancetypeid nvarchar(20) null 
/*���㷽ʽ*/,
cchanneltypeid nvarchar(20) null 
/*������������*/,
ntotalnum decimal(28,8) null 
/*�ϼ�����*/,
ntotalweight decimal(28,8) null 
/*�ϼ�����*/,
ntotalvolume decimal(28,8) null 
/*�ϼ����*/,
vdef1 nvarchar(101) null 
/*�Զ�����1*/,
vdef2 nvarchar(101) null 
/*�Զ�����2*/,
vdef3 nvarchar(101) null 
/*�Զ�����3*/,
vdef4 nvarchar(101) null 
/*�Զ�����4*/,
vdef5 nvarchar(101) null 
/*�Զ�����5*/,
vdef6 nvarchar(101) null 
/*�Զ�����6*/,
vdef7 nvarchar(101) null 
/*�Զ�����7*/,
vdef8 nvarchar(101) null 
/*�Զ�����8*/,
vdef9 nvarchar(101) null 
/*�Զ�����9*/,
vdef10 nvarchar(101) null 
/*�Զ�����10*/,
vdef11 nvarchar(101) null 
/*�Զ�����11*/,
vdef12 nvarchar(101) null 
/*�Զ�����12*/,
vdef13 nvarchar(101) null 
/*�Զ�����13*/,
vdef14 nvarchar(101) null 
/*�Զ�����14*/,
vdef15 nvarchar(101) null 
/*�Զ�����15*/,
vdef16 nvarchar(101) null 
/*�Զ�����16*/,
vdef17 nvarchar(101) null 
/*�Զ�����17*/,
vdef18 nvarchar(101) null 
/*�Զ�����18*/,
vdef19 nvarchar(101) null 
/*�Զ�����19*/,
vdef20 nvarchar(101) null 
/*�Զ�����20*/,
modifier nvarchar(20) null 
/*�޸���*/,
pk_org_v nvarchar(20) null 
/*������֯���°汾*/,
cdeptvid nvarchar(20) null 
/*�������°汾*/,
barsettleflag nchar(1) null 
/*�������رձ��*/,
creator nchar(20) null 
/*������*/,
ntotalpiece decimal(28,8) null 
/*��???��*/,
dmakedate nchar(19) null 
/*�Ƶ�����*/,
fpfstatusflag smallint null 
/*������״̬*/,
ctradewordid nvarchar(20) null 
/*ó������*/,
vbillsrctype nvarchar(20) null 
/*������Դ��������*/,
cbillsrcid nvarchar(20) null 
/*������Դ����ID*/,
nlrgtotalorigmny decimal(28,8) null 
/*��Ʒ��˰�ϼ�*/,
carsubtypeid nvarchar(20) null 
/*��Ʒ�Ҹ�����*/,
chreceivecustid nvarchar(20) null 
/*�ջ��ͻ�*/,
chreceiveaddid nvarchar(20) null 
/*�ջ���ַ*/,
 constraint pk_so_saleorder primary key (csaleorderid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۶������� */
create table so_saleorder_b (
csaleorderid nvarchar(20) null 
/*���۶�������*/,
csaleorderbid nchar(20) not null 
/*���۶�������*/,
pk_group nvarchar(20) null 
/*����*/,
pk_org nvarchar(20) null 
/*������֯*/,
dbilldate nchar(19) null 
/*��������*/,
crowno nvarchar(20) null 
/*�к�*/,
ccustmaterialid nvarchar(20) null 
/*�ͻ�������*/,
cmaterialvid nvarchar(20) null 
/*����*/,
cproductorid nvarchar(20) null 
/*��������*/,
cmaterialid nvarchar(20) null 
/*��������??��*/,
cvendorid nvarchar(20) null 
/*��Ӧ��*/,
cfactoryid nvarchar(20) null 
/*����*/,
cqualitylevelid nvarchar(20) null 
/*�����ȼ�*/,
cunitid nvarchar(20) null 
/*����λ*/,
cprojectid nvarchar(20) null 
/*��Ŀ*/,
castunitid nvarchar(20) null 
/*��λ*/,
vchangerate nvarchar(60) null 
/*������*/,
nnum decimal(28,8) null 
/*������*/,
nastnum decimal(28,8) null 
/*����*/,
cqtunitid nvarchar(20) null 
/*���۵�λ*/,
vqtunitrate nvarchar(60) null 
/*���۵�λ������*/,
nqtunitnum decimal(28,8) null 
/*���۵�λ����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ�*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
ccurrencyid nvarchar(20) null 
/*��λ��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
nqtorigtaxprice decimal(28,8) null 
/*��˰����*/,
nqtorigprice decimal(28,8) null 
/*��˰����*/,
nqtorigtaxnetprc decimal(28,8) null 
/*��˰����*/,
nqtorignetprice decimal(28,8) null 
/*��˰����*/,
norigprice decimal(28,8) null 
/*����˰����*/,
norigtaxprice decimal(28,8) null 
/*����˰����*/,
norignetprice decimal(28,8) null 
/*����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*����˰����*/,
norigmny decimal(28,8) null 
/*��˰���*/,
norigtaxmny decimal(28,8) null 
/*��˰�ϼ�*/,
norigdiscount decimal(28,8) null 
/*�ۿ۶�*/,
nqttaxnetprice decimal(28,8) null 
/*���Һ�˰����*/,
nqtnetprice decimal(28,8) null 
/*������˰����*/,
nqttaxprice decimal(28,8) null 
/*���Һ�˰����*/,
nqtprice decimal(28,8) null 
/*������˰����*/,
nprice decimal(28,8) null 
/*��������˰����*/,
ntaxprice decimal(28,8) null 
/*�����Һ�˰����*/,
nnetprice decimal(28,8) null 
/*��������˰����*/,
ntaxnetprice decimal(28,8) null 
/*�����Һ�˰����*/,
ntax decimal(28,8) null 
/*����˰��*/,
nmny decimal(28,8) null 
/*������˰���*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount decimal(28,8) null 
/*�����ۿ۶�*/,
nweight decimal(28,8) null 
/*����*/,
nvolume decimal(28,8) null 
/*���*/,
npiece decimal(28,8) null 
/*����*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
ngroupmny decimal(28,8) null 
/*���ű�����˰���*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
nglobalmny decimal(28,8) null 
/*ȫ�ֱ�����˰���*/,
nglobaltaxmny decimal(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
naskqtorigtaxprc decimal(28,8) null 
/*ѯ��ԭ�Һ�˰����*/,
naskqtorigprice decimal(28,8) null 
/*ѯ��ԭ����˰����*/,
naskqtorigtxntprc decimal(28,8) null 
/*ѯ��ԭ�Һ�˰����*/,
naskqtorignetprice decimal(28,8) null 
/*ѯ��ԭ����˰����*/,
cpricepolicyid nvarchar(20) null 
/*�۸�����*/,
cpriceitemid nvarchar(20) null 
/*�۸���Ŀ*/,
cpriceitemtableid nvarchar(20) null 
/*��Ŀ��*/,
cpriceformid nvarchar(20) null 
/*�۸����*/,
blargessflag nchar(1) null 
/*�Ƿ���Ʒ*/,
cprodlineid nvarchar(20) null 
/*��Ʒ��*/,
blaborflag nchar(1) null 
/*����������*/,
bdiscountflag nchar(1) null 
/*�ۿ�������*/,
vbatchcode nvarchar(40) null 
/*����*/,
pk_batchcode nvarchar(20) null 
/*���ε���*/,
dsenddate nchar(19) null 
/*�ƻ���������*/,
dreceivedate nchar(19) null 
/*Ҫ�󵽻�����*/,
creceivecustid nvarchar(20) null 
/*�ջ��ͻ�*/,
creceiveareaid nvarchar(20) null 
/*�ջ�����*/,
creceiveaddrid nvarchar(20) null 
/*�ջ���ַ*/,
creceiveadddocid nvarchar(20) null 
/*�ջ��ص�*/,
csendstockorgvid nvarchar(20) null 
/*���������֯*/,
csendstockorgid nvarchar(20) null 
/*���������֯���°汾*/,
csendstordocid nvarchar(20) null 
/*�����ֿ�*/,
csettleorgvid nvarchar(20) null 
/*���������֯*/,
csettleorgid nvarchar(20) null 
/*���������֯���������֯*/,
carorgvid nvarchar(20) null 
/*Ӧ����֯*/,
carorgid nvarchar(20) null 
/*Ӧ����֯���°汾*/,
ctrafficorgvid nvarchar(20) null 
/*������֯*/,
ctrafficorgid nvarchar(20) null 
/*������֯���°汾*/,
cprofitcentervid nvarchar(20) null 
/*������������*/,
cprofitcenterid nvarchar(20) null 
/*���������������°汾*/,
bbindflag nchar(1) null 
/*�Ƿ�??����*/,
clargesssrcid nvarchar(20) null 
/*��Ʒ�ж�Ӧ��Դ������ID*/,
cbindsrcid nvarchar(20) null 
/*�������Ӧ��Դ������ID*/,
flargesstypeflag int null 
/*��Ʒ�۸��̯��ʽ*/,
nlargessmny decimal(28,8) null 
/*��Ʒ�۸��̯ǰ��˰���*/,
nlargesstaxmny decimal(28,8) null 
/*��Ʒ�۸��̯ǰ��˰�ϼ�*/,
vbrevisereason nvarchar(181) null 
/*�޶�����*/,
cretreasonid nvarchar(20) null 
/*�˻�ԭ��ID*/,
vreturnmode nvarchar(181) null 
/*�˻����δ���ʽ*/,
cretpolicyid nvarchar(20) null 
/*�˻�����ID*/,
barrangedflag nchar(1) null 
/*�Ƿ��Դ�������*/,
carrangepersonid nvarchar(20) null 
/*����Դ������*/,
tlastarrangetime nchar(19) null 
/*����Դ����ʱ��*/,
vclosereason nvarchar(181) null 
/*�ر�ԭ��*/,
cctmanageid nvarchar(20) null 
/*��ͬID*/,
cctmanagebid nvarchar(20) null 
/*��ͬ����ID*/,
vctcode nvarchar(40) null 
/*���ۺ�ͬ��*/,
vfirsttype nvarchar(20) null 
/*Դͷ��������*/,
vfirsttrantype nvarchar(20) null 
/*Դͷ��������*/,
vfirstcode nvarchar(40) null 
/*Դͷ���ݺ�*/,
cfirstid nvarchar(20) null 
/*Դͷ������������*/,
cfirstbid nvarchar(20) null 
/*Դͷ���ݱ�������*/,
vfirstrowno nvarchar(20) null 
/*Դͷ�����к�*/,
vsrctype nvarchar(20) null 
/*��Դ��??����*/,
vsrctrantype nvarchar(20) null 
/*��Դ��������*/,
csrcid nvarchar(20) null 
/*��Դ��������*/,
csrcbid nvarchar(20) null 
/*��Դ���ݸ���*/,
vsrccode nvarchar(40) null 
/*��Դ���ݺ�*/,
vsrcrowno nvarchar(20) null 
/*��Դ�����к�*/,
fretexchange int null 
/*�˻������*/,
cexchangesrcretid nvarchar(20) null 
/*������Դ����*/,
bjczxsflag nchar(1) null 
/*���ת����*/,
vfree1 nvarchar(101) null 
/*���ɸ�������1*/,
vfree2 nvarchar(101) null 
/*���ɸ�������2*/,
vfree3 nvarchar(101) null 
/*���ɸ�������3*/,
vfree4 nvarchar(101) null 
/*���ɸ�������4*/,
vfree5 nvarchar(101) null 
/*���ɸ�������5*/,
vfree6 nvarchar(101) null 
/*���ɸ�������6*/,
vfree7 nvarchar(101) null 
/*���ɸ�������7*/,
vfree8 nvarchar(101) null 
/*���ɸ�������8*/,
vfree9 nvarchar(101) null 
/*���ɸ�������9*/,
vfree10 nvarchar(101) null 
/*���ɸ�������10*/,
vbdef1 nvarchar(101) null 
/*�Զ���???1*/,
vbdef2 nvarchar(101) null 
/*�Զ�����2*/,
vbdef3 nvarchar(101) null 
/*�Զ�����3*/,
vbdef4 nvarchar(101) null 
/*�Զ�����4*/,
vbdef5 nvarchar(101) null 
/*�Զ�����5*/,
vbdef6 nvarchar(101) null 
/*�Զ�����6*/,
vbdef7 nvarchar(101) null 
/*�Զ�����7*/,
vbdef8 nvarchar(101) null 
/*�Զ�����8*/,
vbdef9 nvarchar(101) null 
/*�Զ�����9*/,
vbdef10 nvarchar(101) null 
/*�Զ�����10*/,
vbdef11 nvarchar(101) null 
/*�Զ�����11*/,
vbdef12 nvarchar(101) null 
/*�Զ�����12*/,
vbdef13 nvarchar(101) null 
/*�Զ�����13*/,
vbdef14 nvarchar(101) null 
/*�Զ�??��14*/,
vbdef15 nvarchar(101) null 
/*�Զ�����15*/,
vbdef16 nvarchar(101) null 
/*�Զ�����16*/,
vbdef17 nvarchar(101) null 
/*�Զ�����17*/,
vbdef18 nvarchar(101) null 
/*�Զ�����18*/,
vbdef19 nvarchar(101) null 
/*�Զ�����19*/,
vbdef20 nvarchar(101) null 
/*�Զ�����20*/,
bbsendendflag nchar(1) null 
/*�����ر�*/,
bboutendflag nchar(1) null 
/*����ر�*/,
bbinvoicendflag nchar(1) null 
/*��Ʊ�ر�*/,
bbcostsettleflag nchar(1) null 
/*�ɱ�����ر�*/,
bbarsettleflag nchar(1) null 
/*�������ر�*/,
frowstatus int null 
/*��״̬*/,
vrownote nvarchar(181) null 
/*�б�ע*/,
crececountryid nvarchar(20) null 
/*�ջ�����/����*/,
csendcountryid nvarchar(20) null 
/*��������/����*/,
ctaxcountryid nvarchar(20) null 
/*��˰����/����*/,
fbuysellflag int null 
/*��������*/,
btriatradeflag nchar(1) null 
/*����ó��*/,
ctaxcodeid nvarchar(20) null 
/*˰��*/,
ftaxtypeflag int null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
corigcountryid nvarchar(20) null 
/*ԭ����*/,
corigareaid nvarchar(20) null 
/*ԭ������*/,
cbuypromottypeid nvarchar(20) null 
/*������������*/,
cprcpromottypeid nvarchar(20) null 
/*ѯ�۴�������*/,
vcustombillcode nvarchar(40) null 
/*�ͻ�������*/,
cbuylargessactid nvarchar(20) null 
/*�����*/,
cpricepromtactid nvarchar(20) null 
/*�۸�����*/,
cbuylargessid nvarchar(20) null 
/*��������*/,
csprofitcentervid nvarchar(20) null 
/*������������*/,
csprofitcenterid nvarchar(20) null 
/*���������������°汾*/,
blrgcashflag nchar(1) null 
/*��Ʒ�Ҹ�*/,
naccprice decimal(28,8) null 
/*�����˵���*/,
cpromotpriceid nvarchar(20) null 
/*�����۸����*/,
cmffileid nvarchar(20) null 
/*������*/,
nmffileprice decimal(28,8) null 
/*������*/,
 constraint pk_so_saleorder_b primary key (csaleorderbid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: so_saleorder_exe_��չ�� */
create table so_saleorder_exe (
ntotalsendnum decimal(28,8) null 
/*�ۼƷ�������*/,
ntotalinvoicenum decimal(28,8) null 
/*�ۼƿ�Ʊ����*/,
ntotaloutnum decimal(28,8) null 
/*�ۼƳ�������*/,
ntotalnotoutnum decimal(28,8) null 
/*�ۼ�Ӧ��δ��������*/,
ntotalsignnum decimal(28,8) null 
/*�ۼ�ǩ������*/,
ntranslossnum decimal(28,8) null 
/*�ۼ�;������*/,
ntotalrushnum decimal(28,8) null 
/*�ۼƳ���Գ�����*/,
ntotalestarnum decimal(28,8) null 
/*�ۼ��ݹ�Ӧ������*/,
ntotalarnum decimal(28,8) null 
/*�ۼ�ȷ��Ӧ������*/,
ntotalcostnum decimal(28,8) null 
/*�ۼƳɱ���������*/,
ntotalestarmny decimal(28,8) null 
/*�ۼ��ݹ�Ӧ�ս��*/,
ntotalarmny decimal(28,8) null 
/*�ۼ�ȷ��Ӧ�ս��*/,
ntotalpaymny decimal(28,8) null 
/*�ۼƲ���������*/,
norigsubmny decimal(28,8) null 
/*�ۼƳ�ֽ��*/,
narrangescornum decimal(28,8) null 
/*�ۼư���ί�ⶩ������*/,
narrangepoappnum decimal(28,8) null 
/*�ۼư����빺������*/,
narrangetoornum decimal(28,8) null 
/*�ۼư��ŵ�����������*/,
narrangetoappnum decimal(28,8) null 
/*�ۼư��ŵ�����������*/,
narrangemonum decimal(28,8) null 
/*�ۼư���������������*/,
narrangeponum decimal(28,8) null 
/*�ۼư��Ųɹ���������*/,
ntotalplonum decimal(28,8) null 
/*�ۼ����ɼƻ�����������*/,
ntotalreturnnum decimal(28,8) null 
/*�ۼ��˻�����*/,
ntotaltradenum decimal(28,8) null 
/*�ۼƷ�����Ʒ����*/,
nreqrsnum decimal(28,8) null 
/*Ԥ������*/,
csaleorderbid nchar(20) not null 
/*��չ������*/,
narrangeitcnum decimal(28,8) null 
/*�ۼư��Ž��ں�ͬ������*/,
 constraint pk_o_saleorder_exe primary key (csaleorderbid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �����տ������ʵ�� */
create table so_balance (
csobalanceid nchar(20) not null 
/*�����տ������ʵ��*/,
csaleorderid nvarchar(20) null 
/*���۶�����ʵ��*/,
pk_org nvarchar(20) null 
/*������֯*/,
pk_org_v nvarchar(20) null 
/*������֯���°汾*/,
pk_group nvarchar(20) null 
/*����*/,
ccustomerid nvarchar(20) null 
/*�����ͻ�*/,
cinvoicecustid nvarchar(20) null 
/*��Ʊ�ͻ�*/,
vbillcode nvarchar(40) null 
/*������*/,
corigcurrencyid nvarchar(20) null 
/*����*/,
ntotalorigtaxmny decimal(28,8) null 
/*��˰�ϼ�*/,
ntotalpaymny decimal(28,8) null 
/*�������տ���*/,
ntotalorigbalmny decimal(28,8) null 
/*�����Ѻ������*/,
cpaytermid nvarchar(20) null 
/*�տ�Э��*/,
cemployeeid nvarchar(20) null 
/*ҵ��Ա*/,
cdeptid nvarchar(20) null 
/*����*/,
carorgid nvarchar(20) null 
/*Ӧ����֯*/,
vtrantypecode nvarchar(20) null 
/*���۶�������*/,
cchanneltypeid nvarchar(20) null 
/*������������*/,
dbilldate nvarchar(19) null 
/*��������*/,
 constraint pk_so_balance primary key (csobalanceid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �����տ������ʵ�� */
create table so_balance_b (
csobalancebid nchar(20) not null 
/*�����տ������ʵ��*/,
pk_org nvarchar(20) null 
/*������֯*/,
fibaltype int null 
/*��������*/,
cpaybillid nvarchar(20) null 
/*�տ��ʵ��*/,
cpaybillrowid nvarchar(20) null 
/*�տ��ʵ��*/,
varbillcode nvarchar(40) null 
/*���ݺ�*/,
darbilldate nvarchar(19) null 
/*��������*/,
norigarmny decimal(28,8) null 
/*�����н��*/,
carorigcurrencyid nvarchar(20) null 
/*����*/,
darbalancedate nvarchar(19) null 
/*��������*/,
cprodlineid nvarchar(20) null 
/*��Ʒ��*/,
norigordbalmny decimal(28,8) null 
/*�����������*/,
norigaccbalmny decimal(28,8) null 
/*�տ�Ѳ���������*/,
csobalanceid nvarchar(20) null 
/*�����տ������ʵ��_����*/,
bpreceiveflag nchar(1) null 
/*�տ��޶����Ԥ��*/,
 constraint pk_so_balance_b primary key (csobalancebid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۶����޶����� */
create table so_orderhistory (
corderhistoryid nchar(20) not null 
/*���۶����޶�����ID*/,
csaleorderid nchar(20) not null 
/*��������ID*/,
pk_org nvarchar(20) null 
/*������֯*/,
pk_group nvarchar(20) null 
/*����*/,
cbiztypeid nvarchar(20) null 
/*ҵ������*/,
ctrantypeid nvarchar(20) null 
/*��������*/,
vtrantypecode nvarchar(20) null 
/*�������ͱ���*/,
vbillcode nvarchar(40) null 
/*���ݺ�*/,
ccustomerid nvarchar(20) null 
/*�ͻ�*/,
dbilldate nchar(19) null 
/*��������*/,
cdeptid nvarchar(20) null 
/*����*/,
corigcurrencyid nvarchar(20) null 
/*ԭ�ұ���*/,
cemployeeid nvarchar(20) null 
/*ҵ��Ա*/,
cpaytermid nvarchar(20) null 
/*�տ�Э��*/,
cinvoicecustid nvarchar(20) null 
/*��Ʊ�ͻ�*/,
ccustbankid nvarchar(20) null 
/*��������*/,
ccustbankaccid nvarchar(20) null 
/*���������˺�*/,
ctransporttypeid nvarchar(20) null 
/*���䷽ʽ*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
vrevisereason nvarchar(181) null 
/*�޶�����*/,
badvfeeflag nchar(1) null 
/*�����˷�*/,
bfreecustflag nchar(1) null 
/*�Ƿ�ɢ��*/,
vcreditnum nvarchar(20) null 
/*����֤��*/,
cfreecustid nvarchar(20) null 
/*ɢ��*/,
billmaker nvarchar(20) null 
/*�Ƶ���*/,
creationtime nchar(19) null 
/*����ʱ��*/,
approver nvarchar(20) null 
/*������*/,
taudittime nvarchar(19) null 
/*�������*/,
modifiedtime nchar(19) null 
/*�޸�ʱ��*/,
fstatusflag smallint null 
/*����״̬*/,
vnote nvarchar(181) null 
/*��ע*/,
boutendflag nchar(1) null 
/*����رձ��*/,
binvoicendflag nchar(1) null 
/*��Ʊ�رձ��*/,
bcostsettleflag nchar(1) null 
/*�ɱ�����رձ��*/,
bsendendflag nchar(1) null 
/*�����رձ��*/,
npreceiverate decimal(28,8) null 
/*�����տ����*/,
npreceivequota decimal(28,8) null 
/*�����տ��޶�*/,
bpreceiveflag nchar(1) null 
/*�տ��޶����Ԥ��*/,
npreceivemny decimal(28,8) null 
/*ʵ��Ԥ�տ���*/,
nreceivedmny decimal(28,8) null 
/*ʵ���տ���*/,
iprintcount int null 
/*��ӡ����*/,
ntotalorigmny decimal(28,8) null 
/*���ϼ�*/,
ntotalorigsubmny decimal(28,8) null 
/*��ֽ��*/,
boffsetflag nchar(1) null 
/*�Ƿ���*/,
bcooptopoflag nchar(1) null 
/*�Ƿ���Эͬ���ɲɹ�����*/,
bpocooptomeflag nchar(1) null 
/*�Ƿ��ɲɹ�����Эͬ����*/,
vcooppohcode nvarchar(40) null 
/*�Է�������*/,
iversion int null 
/*�޶��汾��*/,
trevisetime nchar(19) null 
/*�޶�ʱ��*/,
creviserid nvarchar(20) null 
/*�޶���*/,
cbalancetypeid nvarchar(20) null 
/*���㷽ʽ*/,
cchanneltypeid nvarchar(20) null 
/*������������*/,
ntotalnum decimal(28,8) null 
/*�ϼ�����*/,
ntotalweight decimal(28,8) null 
/*�ϼ�����*/,
ntotalvolume decimal(28,8) null 
/*�ϼ����*/,
vdef1 nvarchar(101) null 
/*�Զ�����1*/,
vdef2 nvarchar(101) null 
/*�Զ�����2*/,
vdef3 nvarchar(101) null 
/*�Զ�����3*/,
vdef4 nvarchar(101) null 
/*�Զ�����4*/,
vdef5 nvarchar(101) null 
/*�Զ�����5*/,
vdef6 nvarchar(101) null 
/*�Զ�����6*/,
vdef7 nvarchar(101) null 
/*�Զ�����7*/,
vdef8 nvarchar(101) null 
/*�Զ�����8*/,
vdef9 nvarchar(101) null 
/*�Զ�����9*/,
vdef10 nvarchar(101) null 
/*�Զ�����10*/,
vdef11 nvarchar(101) null 
/*�Զ�����11*/,
vdef12 nvarchar(101) null 
/*�Զ�����12*/,
vdef13 nvarchar(101) null 
/*�Զ�����13*/,
vdef14 nvarchar(101) null 
/*�Զ�����14*/,
vdef15 nvarchar(101) null 
/*�Զ�����15*/,
vdef16 nvarchar(101) null 
/*�Զ�����16*/,
vdef17 nvarchar(101) null 
/*�Զ�����17*/,
vdef18 nvarchar(101) null 
/*�Զ�����18*/,
vdef19 nvarchar(101) null 
/*�Զ�����19*/,
vdef20 nvarchar(101) null 
/*�Զ�����20*/,
modifier nvarchar(20) null 
/*�޸���*/,
pk_org_v nvarchar(20) null 
/*������֯���°汾*/,
cdeptvid nvarchar(20) null 
/*�������°汾*/,
barsettleflag nchar(1) null 
/*�������رձ��*/,
creator nchar(20) null 
/*������*/,
ntotalpiece decimal(28,8) null 
/*�ܼ���*/,
dmakedate nchar(19) null 
/*�Ƶ�����*/,
fpfstatusflag smallint null 
/*������״̬*/,
ctradewordid nvarchar(20) null 
/*ó������*/,
chistrantypeid nvarchar(20) null 
/*�޶���������*/,
vhistrantypecode nvarchar(20) null 
/*�޶��������ͱ���*/,
carsubtypeid nvarchar(20) null 
/*��Ʒ�Ҹ�����*/,
chreceivecustid nvarchar(20) null 
/*�ջ��ͻ�*/,
chreceiveaddid nvarchar(20) null 
/*�ջ���ַ*/,
vbillsrctype nvarchar(20) null 
/*������Դ��������*/,
cbillsrcid nvarchar(20) null 
/*������Դ����ID*/,
nlrgtotalorigmny decimal(28,8) null 
/*��Ʒ��˰�ϼ�*/,
 constraint pk_so_orderhist primary key (corderhistoryid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۶����޶����� */
create table so_orderhistory_b (
corderhistorybid nchar(20) not null 
/*���۶����޶�����ID*/,
corderhistoryid nvarchar(20) not null 
/*���۶����޶�����ID*/,
csaleorderid nvarchar(20) null 
/*���۶�������*/,
csaleorderbid nchar(20) not null 
/*���۶�������*/,
pk_group nvarchar(20) null 
/*����*/,
pk_org nvarchar(20) null 
/*������֯*/,
dbilldate nchar(19) null 
/*��������*/,
crowno nvarchar(20) null 
/*�к�*/,
ccustmaterialid nvarchar(20) null 
/*�ͻ�������*/,
cmaterialvid nvarchar(20) null 
/*����*/,
cproductorid nvarchar(20) null 
/*��������*/,
cmaterialid nvarchar(20) null 
/*�������°汾*/,
cvendorid nvarchar(20) null 
/*��Ӧ��*/,
cfactoryid nvarchar(20) null 
/*����*/,
cqualitylevelid nvarchar(20) null 
/*�����ȼ�*/,
cunitid nvarchar(20) null 
/*����λ*/,
cprojectid nvarchar(20) null 
/*��Ŀ*/,
castunitid nvarchar(20) null 
/*��λ*/,
vchangerate nvarchar(60) null 
/*������*/,
nnum decimal(28,8) null 
/*������*/,
nastnum decimal(28,8) null 
/*����*/,
cqtunitid nvarchar(20) null 
/*���۵�λ*/,
vqtunitrate nvarchar(60) null 
/*���۵�λ������*/,
nqtunitnum decimal(28,8) null 
/*���۵�λ����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ�*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
ccurrencyid nvarchar(20) null 
/*��λ��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
nqtorigtaxprice decimal(28,8) null 
/*��˰����*/,
nqtorigprice decimal(28,8) null 
/*��˰����*/,
nqtorigtaxnetprc decimal(28,8) null 
/*��˰����*/,
nqtorignetprice decimal(28,8) null 
/*��˰����*/,
norigprice decimal(28,8) null 
/*����˰����*/,
norigtaxprice decimal(28,8) null 
/*����˰����*/,
norignetprice decimal(28,8) null 
/*����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*����˰����*/,
norigmny decimal(28,8) null 
/*��˰���*/,
norigtaxmny decimal(28,8) null 
/*��˰�ϼ�*/,
norigdiscount decimal(28,8) null 
/*�ۿ۶�*/,
nqttaxnetprice decimal(28,8) null 
/*���Һ�˰����*/,
nqtnetprice decimal(28,8) null 
/*������˰����*/,
nqttaxprice decimal(28,8) null 
/*���Һ�˰����*/,
nqtprice decimal(28,8) null 
/*������˰����*/,
nprice decimal(28,8) null 
/*������??˰����*/,
ntaxprice decimal(28,8) null 
/*�����Һ�˰����*/,
nnetprice decimal(28,8) null 
/*��������˰����*/,
ntaxnetprice decimal(28,8) null 
/*�����Һ�˰����*/,
ntax decimal(28,8) null 
/*����˰��*/,
nmny decimal(28,8) null 
/*������˰���*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount decimal(28,8) null 
/*�����ۿ۶�*/,
nweight decimal(28,8) null 
/*����*/,
nvolume decimal(28,8) null 
/*���*/,
npiece decimal(28,8) null 
/*����*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
ngroupmny decimal(28,8) null 
/*���ű�����˰���*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
nglobalmny decimal(28,8) null 
/*ȫ�ֱ�����˰���*/,
nglobaltaxmny decimal(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
naskqtorigtaxprc decimal(28,8) null 
/*ѯ��ԭ�Һ�˰����*/,
naskqtorigprice decimal(28,8) null 
/*ѯ��ԭ����˰����*/,
naskqtorigtxntprc decimal(28,8) null 
/*ѯ��ԭ�Һ�˰����*/,
naskqtorignetprice decimal(28,8) null 
/*ѯ��ԭ����˰����*/,
cpricepolicyid nvarchar(20) null 
/*�۸�����*/,
cpriceitemid nvarchar(20) null 
/*�۸���Ŀ*/,
cpriceitemtableid nvarchar(20) null 
/*��Ŀ��*/,
cpriceformid nvarchar(20) null 
/*�۸����*/,
blargessflag nchar(1) null 
/*�Ƿ���Ʒ*/,
cprodlineid nvarchar(20) null 
/*��Ʒ��*/,
blaborflag nchar(1) null 
/*����������*/,
bdiscountflag nchar(1) null 
/*�ۿ�������*/,
vbatchcode nvarchar(40) null 
/*����*/,
pk_batchcode nvarchar(20) null 
/*���ε���*/,
dsenddate nchar(19) null 
/*�ƻ���������*/,
dreceivedate nchar(19) null 
/*Ҫ�󵽻�����*/,
creceivecustid nvarchar(20) null 
/*�ջ��ͻ�*/,
creceiveareaid nvarchar(20) null 
/*�ջ�����*/,
creceiveaddrid nvarchar(20) null 
/*�ջ���ַ*/,
creceiveadddocid nvarchar(20) null 
/*�ջ��ص�*/,
csendstockorgvid nvarchar(20) null 
/*���������֯*/,
csendstockorgid nvarchar(20) null 
/*���������֯���°汾*/,
csendstordocid nvarchar(20) null 
/*�����ֿ�*/,
csettleorgvid nvarchar(20) null 
/*���������֯*/,
csettleorgid nvarchar(20) null 
/*���������֯���������֯*/,
carorgvid nvarchar(20) null 
/*Ӧ����֯*/,
carorgid nvarchar(20) null 
/*Ӧ����֯���°汾*/,
ctrafficorgvid nvarchar(20) null 
/*������֯*/,
ctrafficorgid nvarchar(20) null 
/*������֯���°汾*/,
cprofitcentervid nvarchar(20) null 
/*��������*/,
cprofitcenterid nvarchar(20) null 
/*�����������°汾*/,
bbindflag nchar(1) null 
/*�Ƿ�������*/,
clargesssrcid nvarchar(20) null 
/*��Ʒ�ж�Ӧ��Դ������ID*/,
cbindsrcid nvarchar(20) null 
/*�������Ӧ��Դ������ID*/,
flargesstypeflag int null 
/*��Ʒ�۸��̯��ʽ*/,
nlargessmny decimal(28,8) null 
/*��Ʒ�۸��̯ǰ��˰���*/,
nlargesstaxmny decimal(28,8) null 
/*��Ʒ�۸��̯ǰ��˰�ϼ�*/,
vbrevisereason nvarchar(181) null 
/*�޶�����*/,
cretreasonid nvarchar(20) null 
/*�˻�ԭ��ID*/,
vreturnmode nvarchar(181) null 
/*�˻����δ���ʽ*/,
cretpolicyid nvarchar(20) null 
/*�˻�����ID*/,
barrangedflag nchar(1) null 
/*�Ƿ��Դ�������*/,
carrangepersonid nvarchar(20) null 
/*����Դ������*/,
tlastarrangetime nchar(19) null 
/*����Դ����ʱ��*/,
vclosereason nvarchar(181) null 
/*�ر�ԭ��*/,
cctmanageid nvarchar(20) null 
/*��ͬID*/,
cctmanagebid nvarchar(20) null 
/*��ͬ����ID*/,
vctcode nvarchar(40) null 
/*���ۺ�ͬ��*/,
vfirsttype nvarchar(20) null 
/*Դͷ��������*/,
vfirsttrantype nvarchar(20) null 
/*Դͷ��������*/,
vfirstcode nvarchar(40) null 
/*Դͷ���ݺ�*/,
cfirstid nvarchar(20) null 
/*Դͷ������������*/,
cfirstbid nvarchar(20) null 
/*Դͷ���ݱ�������*/,
vfirstrowno nvarchar(20) null 
/*Դͷ�����к�*/,
vsrctype nvarchar(20) null 
/*��Դ��������*/,
vsrctrantype nvarchar(20) null 
/*��??��������*/,
csrcid nvarchar(20) null 
/*��Դ��������*/,
csrcbid nvarchar(20) null 
/*��Դ���ݸ���*/,
vsrccode nvarchar(40) null 
/*��Դ���ݺ�*/,
vsrcrowno nvarchar(20) null 
/*��Դ�����к�*/,
fretexchange int null 
/*�˻������*/,
cexchangesrcretid nvarchar(20) null 
/*������Դ����*/,
bjczxsflag nchar(1) null 
/*���ת����*/,
vfree1 nvarchar(101) null 
/*���ɸ�������1*/,
vfree2 nvarchar(101) null 
/*���ɸ�������2*/,
vfree3 nvarchar(101) null 
/*���ɸ�������3*/,
vfree4 nvarchar(101) null 
/*���ɸ�������4*/,
vfree5 nvarchar(101) null 
/*���ɸ�������5*/,
vfree6 nvarchar(101) null 
/*���ɸ�������6*/,
vfree7 nvarchar(101) null 
/*���ɸ�������7*/,
vfree8 nvarchar(101) null 
/*����???������8*/,
vfree9 nvarchar(101) null 
/*���ɸ�������9*/,
vfree10 nvarchar(101) null 
/*���ɸ�������10*/,
vbdef1 nvarchar(101) null 
/*�Զ�����1*/,
vbdef2 nvarchar(101) null 
/*�Զ�����2*/,
vbdef3 nvarchar(101) null 
/*�Զ�����3*/,
vbdef4 nvarchar(101) null 
/*�Զ�����4*/,
vbdef5 nvarchar(101) null 
/*�Զ�����5*/,
vbdef6 nvarchar(101) null 
/*�Զ�����6*/,
vbdef7 nvarchar(101) null 
/*�Զ�����7*/,
vbdef8 nvarchar(101) null 
/*�Զ�����8*/,
vbdef9 nvarchar(101) null 
/*�Զ�����9*/,
vbdef10 nvarchar(101) null 
/*�Զ�����10*/,
vbdef11 nvarchar(101) null 
/*�Զ�����11*/,
vbdef12 nvarchar(101) null 
/*�Զ�����12*/,
vbdef13 nvarchar(101) null 
/*�Զ�����13*/,
vbdef14 nvarchar(101) null 
/*�Զ�����14*/,
vbdef15 nvarchar(101) null 
/*�Զ�����15*/,
vbdef16 nvarchar(101) null 
/*�Զ�����16*/,
vbdef17 nvarchar(101) null 
/*�Զ�����17*/,
vbdef18 nvarchar(101) null 
/*�Զ�???��18*/,
vbdef19 nvarchar(101) null 
/*�Զ�����19*/,
vbdef20 nvarchar(101) null 
/*�Զ�����20*/,
bbsendendflag nchar(1) null 
/*�����ر�*/,
bboutendflag nchar(1) null 
/*����ر�*/,
bbinvoicendflag nchar(1) null 
/*��Ʊ�ر�*/,
bbcostsettleflag nchar(1) null 
/*�ɱ�����ر�*/,
bbarsettleflag nchar(1) null 
/*�������ر�*/,
frowstatus int null 
/*��״̬*/,
vrownote nvarchar(181) null 
/*�б�ע*/,
crececountryid nvarchar(20) null 
/*�ջ�����/����*/,
csendcountryid nvarchar(20) null 
/*��������/����*/,
ctaxcountryid nvarchar(20) null 
/*��˰����/����*/,
fbuysellflag int null 
/*��������*/,
btriatradeflag nchar(1) null 
/*����ó��*/,
ctaxcodeid nvarchar(20) null 
/*˰��*/,
ftaxtypeflag int null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
corigcountryid nvarchar(20) null 
/*ԭ����*/,
corigareaid nvarchar(20) null 
/*ԭ������*/,
cbuypromottypeid nvarchar(20) null 
/*������������*/,
cprcpromottypeid nvarchar(20) null 
/*ѯ�۴�������*/,
vcustombillcode nvarchar(40) null 
/*�ͻ�������*/,
cbuylargessactid nvarchar(20) null 
/*�����*/,
cpricepromtactid nvarchar(20) null 
/*�۸�����*/,
cbuylargessid nvarchar(20) null 
/*��������*/,
csprofitcentervid nvarchar(20) null 
/*������������*/,
csprofitcenterid nvarchar(20) null 
/*���������������°汾*/,
cmffileid nvarchar(20) null 
/*������*/,
nmffileprice decimal(28,8) null 
/*������*/,
cpromotpriceid nvarchar(20) null 
/*�����۸����*/,
blrgcashflag nchar(1) null 
/*��Ʒ�Ҹ�*/,
naccprice decimal(28,8) null 
/*�����˵���*/,
 constraint pk_so_orderhist_b primary key (corderhistorybid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۶����ҵĶ���ת���� */
create table so_mb_myorder (
csaleorderid nchar(20) not null 
/*������ʵ��*/,
pk_group nvarchar(20) null 
/*����*/,
dbilldate nchar(19) null 
/*��������*/,
pk_org nvarchar(20) null 
/*������֯*/,
vbillcode nvarchar(40) null 
/*������*/,
ccustomerid nvarchar(20) null 
/*�ͻ�*/,
ntotalorigmny decimal(28,8) null 
/*��˰�ϼ�*/,
cemployeeid nvarchar(20) null 
/*ҵ��Ա*/,
 constraint pk_so_mb_myorder primary key (csaleorderid),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���۶����ƶ������� */
create table so_mb_orderanaly (
pk_group nvarchar(20) null 
/*����*/,
dbilldate nchar(19) null 
/*��������*/,
pk_org nvarchar(20) null 
/*������֯*/,
vbillcode nvarchar(40) null 
/*������*/,
ccustomerid nvarchar(20) null 
/*�ͻ�*/,
cdeptid nvarchar(20) null 
/*����*/,
cemployeeid nvarchar(20) null 
/*ҵ��Ա*/,
cchanneltypeid nvarchar(20) null 
/*��������*/,
cmaterialid nvarchar(20) null 
/*����*/,
cprodlineid nvarchar(20) null 
/*��Ʒ��*/,
cbrandid nvarchar(20) null 
/*Ʒ��*/,
nnum decimal(28,8) null 
/*����������*/,
norigtaxmny decimal(28,8) null 
/*��˰�ϼ�*/,
corigcurrencyid nvarchar(20) null 
/*ԭ��*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
csaleorderid nvarchar(20) null 
/*��������ID*/,
  ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

