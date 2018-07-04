/* tablename: ���۶����������� */
create table so_m30trantype (ctrantypeid varchar(20) null 
/*��������*/,
pk_trantype char(20) not null 
/*���۶���������������*/,
fdirecttype smallint null 
/*ֱ�����ͱ��*/,
fsalemode smallint null 
/*����ģʽ*/,
bmorerows char(1) null 
/*ͬһ����ɷ��ж���*/,
bcanchangeout char(1) null 
/*�˻����֮����ܻ�������*/,
faskqtrule smallint null 
/*ѯ�۹���*/,
bmodifyaskedqt char(1) null 
/*ѯ���۸��Ƿ�ɸ�*/,
bmodifyunaskedqt char(1) null 
/*δѯ���۸��Ƿ�ɸ�*/,
flargessgetqtrule smallint null 
/*��Ʒȡ�۹���*/,
bmodifydiscount char(1) null 
/*�����޸��ۿ�*/,
vtrantypecode varchar(20) null 
/*�������ͱ���*/,
pk_group varchar(20) null 
/*����ID*/,
fmodifymny smallint null 
/*�������Ӱ���ۿۻ��ǵ���*/,
breviseprice char(1) null 
/*�޶�ѯ��*/,
bredorderpay char(1) null 
/*���ֶ���֧�ֶ����տ�*/,
flargessdistype smallint null 
/*��Ʒ�۸��̯��ʽ*/,
blargesspriceno char(1) null 
/*��Ʒ�м۸񱣳ֲ���*/,
barrangeinv char(1) null 
/*ֻ�ܰ���һ�η���*/,
barrangeout char(1) null 
/*ֻ�ܰ���һ�γ���*/,
bnofindpricehit char(1) null 
/*δѯ���۸��Ƿ���ʾ*/,
blossrenew char(1) null 
/*;�𲹻�*/,
blrgcashflag char(1) null 
/*��Ʒ�Ҹ�*/,
naccpricerule smallint null 
/*�����˵���ȡ�۹���*/,
bcopyiseprice char(1) null 
/*�����Ƿ�ѯ��*/,
 constraint pk_so_m30trantype primary key (pk_trantype),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۶������� */
create table so_saleorder (csaleorderid char(20) not null 
/*��������ID*/,
pk_org varchar(20) null 
/*������֯*/,
pk_group varchar(20) null 
/*����*/,
cbiztypeid varchar(20) null 
/*ҵ������*/,
ctrantypeid varchar(20) null 
/*��������*/,
vtrantypecode varchar(20) null 
/*�������ͱ���*/,
vbillcode varchar(40) null 
/*���ݺ�*/,
ccustomerid varchar(20) null 
/*�ͻ�*/,
dbilldate char(19) null 
/*��������*/,
cdeptid varchar(20) null 
/*����*/,
corigcurrencyid varchar(20) null 
/*ԭ�ұ���*/,
cemployeeid varchar(20) null 
/*ҵ��Ա*/,
cpaytermid varchar(20) null 
/*�տ�Э��*/,
cinvoicecustid varchar(20) null 
/*��Ʊ�ͻ�*/,
ccustbankid varchar(20) null 
/*��������*/,
ccustbankaccid varchar(20) null 
/*���������˺�*/,
ctransporttypeid varchar(20) null 
/*���䷽ʽ*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
vrevisereason varchar(181) null 
/*�޶�����*/,
badvfeeflag char(1) null 
/*�����˷�*/,
bfreecustflag char(1) null 
/*�Ƿ�ɢ��*/,
vcreditnum varchar(20) null 
/*����֤��*/,
cfreecustid varchar(20) null 
/*ɢ��*/,
billmaker varchar(20) null 
/*�Ƶ���*/,
creationtime char(19) null 
/*����ʱ��*/,
approver varchar(20) null 
/*������*/,
taudittime varchar(19) null 
/*�������*/,
modifiedtime char(19) null 
/*�޸�ʱ��*/,
fstatusflag smallint null 
/*����״̬*/,
vnote varchar(181) null 
/*��ע*/,
boutendflag char(1) null 
/*����رձ��*/,
binvoicendflag char(1) null 
/*��Ʊ�رձ��*/,
bcostsettleflag char(1) null 
/*�ɱ�����رձ��*/,
bsendendflag char(1) null 
/*�����رձ��*/,
npreceiverate decimal(28,8) null 
/*�����տ����*/,
npreceivequota decimal(28,8) null 
/*�����տ��޶�*/,
bpreceiveflag char(1) null 
/*�տ��޶����Ԥ��*/,
npreceivemny decimal(28,8) null 
/*ʵ��Ԥ�տ���*/,
nreceivedmny decimal(28,8) null 
/*ʵ���տ���*/,
iprintcount integer null 
/*��ӡ����*/,
ntotalorigmny decimal(28,8) null 
/*���ϼ�*/,
ntotalorigsubmny decimal(28,8) null 
/*��ֽ��*/,
boffsetflag char(1) null 
/*�Ƿ���*/,
bcooptopoflag char(1) null 
/*�Ƿ���Эͬ���ɲɹ�����*/,
bpocooptomeflag char(1) null 
/*�Ƿ��ɲɹ�����Эͬ����*/,
vcooppohcode varchar(40) null 
/*�Է�������*/,
iversion integer null 
/*�޶��汾��*/,
trevisetime char(19) null 
/*�޶�ʱ???*/,
creviserid varchar(20) null 
/*�޶���*/,
cbalancetypeid varchar(20) null 
/*���㷽ʽ*/,
cchanneltypeid varchar(20) null 
/*������������*/,
ntotalnum decimal(28,8) null 
/*�ϼ�����*/,
ntotalweight decimal(28,8) null 
/*�ϼ�����*/,
ntotalvolume decimal(28,8) null 
/*�ϼ����*/,
vdef1 varchar(101) null 
/*�Զ�����1*/,
vdef2 varchar(101) null 
/*�Զ�����2*/,
vdef3 varchar(101) null 
/*�Զ�����3*/,
vdef4 varchar(101) null 
/*�Զ�����4*/,
vdef5 varchar(101) null 
/*�Զ�����5*/,
vdef6 varchar(101) null 
/*�Զ�����6*/,
vdef7 varchar(101) null 
/*�Զ�����7*/,
vdef8 varchar(101) null 
/*�Զ�����8*/,
vdef9 varchar(101) null 
/*�Զ�����9*/,
vdef10 varchar(101) null 
/*�Զ�����10*/,
vdef11 varchar(101) null 
/*�Զ�����11*/,
vdef12 varchar(101) null 
/*�Զ�����12*/,
vdef13 varchar(101) null 
/*�Զ�����13*/,
vdef14 varchar(101) null 
/*�Զ�����14*/,
vdef15 varchar(101) null 
/*�Զ�����15*/,
vdef16 varchar(101) null 
/*�Զ�����16*/,
vdef17 varchar(101) null 
/*�Զ�����17*/,
vdef18 varchar(101) null 
/*�Զ�����18*/,
vdef19 varchar(101) null 
/*�Զ�����19*/,
vdef20 varchar(101) null 
/*�Զ�����20*/,
modifier varchar(20) null 
/*�޸���*/,
pk_org_v varchar(20) null 
/*������֯���°汾*/,
cdeptvid varchar(20) null 
/*�������°汾*/,
barsettleflag char(1) null 
/*�������رձ��*/,
creator char(20) null 
/*������*/,
ntotalpiece decimal(28,8) null 
/*��???��*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
fpfstatusflag smallint null 
/*������״̬*/,
ctradewordid varchar(20) null 
/*ó������*/,
vbillsrctype varchar(20) null 
/*������Դ��������*/,
cbillsrcid varchar(20) null 
/*������Դ����ID*/,
nlrgtotalorigmny decimal(28,8) null 
/*��Ʒ��˰�ϼ�*/,
carsubtypeid varchar(20) null 
/*��Ʒ�Ҹ�����*/,
chreceivecustid varchar(20) null 
/*�ջ��ͻ�*/,
chreceiveaddid varchar(20) null 
/*�ջ���ַ*/,
 constraint pk_so_saleorder primary key (csaleorderid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۶������� */
create table so_saleorder_b (csaleorderid varchar(20) null 
/*���۶�������*/,
csaleorderbid char(20) not null 
/*���۶�������*/,
pk_group varchar(20) null 
/*����*/,
pk_org varchar(20) null 
/*������֯*/,
dbilldate char(19) null 
/*��������*/,
crowno varchar(20) null 
/*�к�*/,
ccustmaterialid varchar(20) null 
/*�ͻ�������*/,
cmaterialvid varchar(20) null 
/*����*/,
cproductorid varchar(20) null 
/*��������*/,
cmaterialid varchar(20) null 
/*��������??��*/,
cvendorid varchar(20) null 
/*��Ӧ��*/,
cfactoryid varchar(20) null 
/*����*/,
cqualitylevelid varchar(20) null 
/*�����ȼ�*/,
cunitid varchar(20) null 
/*����λ*/,
cprojectid varchar(20) null 
/*��Ŀ*/,
castunitid varchar(20) null 
/*��λ*/,
vchangerate varchar(60) null 
/*������*/,
nnum decimal(28,8) null 
/*������*/,
nastnum decimal(28,8) null 
/*����*/,
cqtunitid varchar(20) null 
/*���۵�λ*/,
vqtunitrate varchar(60) null 
/*���۵�λ������*/,
nqtunitnum decimal(28,8) null 
/*���۵�λ����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ�*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
ccurrencyid varchar(20) null 
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
cpricepolicyid varchar(20) null 
/*�۸�����*/,
cpriceitemid varchar(20) null 
/*�۸���Ŀ*/,
cpriceitemtableid varchar(20) null 
/*��Ŀ��*/,
cpriceformid varchar(20) null 
/*�۸����*/,
blargessflag char(1) null 
/*�Ƿ���Ʒ*/,
cprodlineid varchar(20) null 
/*��Ʒ��*/,
blaborflag char(1) null 
/*����������*/,
bdiscountflag char(1) null 
/*�ۿ�������*/,
vbatchcode varchar(40) null 
/*����*/,
pk_batchcode varchar(20) null 
/*���ε���*/,
dsenddate char(19) null 
/*�ƻ���������*/,
dreceivedate char(19) null 
/*Ҫ�󵽻�����*/,
creceivecustid varchar(20) null 
/*�ջ��ͻ�*/,
creceiveareaid varchar(20) null 
/*�ջ�����*/,
creceiveaddrid varchar(20) null 
/*�ջ���ַ*/,
creceiveadddocid varchar(20) null 
/*�ջ��ص�*/,
csendstockorgvid varchar(20) null 
/*���������֯*/,
csendstockorgid varchar(20) null 
/*���������֯���°汾*/,
csendstordocid varchar(20) null 
/*�����ֿ�*/,
csettleorgvid varchar(20) null 
/*���������֯*/,
csettleorgid varchar(20) null 
/*���������֯���������֯*/,
carorgvid varchar(20) null 
/*Ӧ����֯*/,
carorgid varchar(20) null 
/*Ӧ����֯���°汾*/,
ctrafficorgvid varchar(20) null 
/*������֯*/,
ctrafficorgid varchar(20) null 
/*������֯���°汾*/,
cprofitcentervid varchar(20) null 
/*������������*/,
cprofitcenterid varchar(20) null 
/*���������������°汾*/,
bbindflag char(1) null 
/*�Ƿ�??����*/,
clargesssrcid varchar(20) null 
/*��Ʒ�ж�Ӧ��Դ������ID*/,
cbindsrcid varchar(20) null 
/*�������Ӧ��Դ������ID*/,
flargesstypeflag integer null 
/*��Ʒ�۸��̯��ʽ*/,
nlargessmny decimal(28,8) null 
/*��Ʒ�۸��̯ǰ��˰���*/,
nlargesstaxmny decimal(28,8) null 
/*��Ʒ�۸��̯ǰ��˰�ϼ�*/,
vbrevisereason varchar(181) null 
/*�޶�����*/,
cretreasonid varchar(20) null 
/*�˻�ԭ��ID*/,
vreturnmode varchar(181) null 
/*�˻����δ���ʽ*/,
cretpolicyid varchar(20) null 
/*�˻�����ID*/,
barrangedflag char(1) null 
/*�Ƿ��Դ�������*/,
carrangepersonid varchar(20) null 
/*����Դ������*/,
tlastarrangetime char(19) null 
/*����Դ����ʱ��*/,
vclosereason varchar(181) null 
/*�ر�ԭ��*/,
cctmanageid varchar(20) null 
/*��ͬID*/,
cctmanagebid varchar(20) null 
/*��ͬ����ID*/,
vctcode varchar(40) null 
/*���ۺ�ͬ��*/,
vfirsttype varchar(20) null 
/*Դͷ��������*/,
vfirsttrantype varchar(20) null 
/*Դͷ��������*/,
vfirstcode varchar(40) null 
/*Դͷ���ݺ�*/,
cfirstid varchar(20) null 
/*Դͷ������������*/,
cfirstbid varchar(20) null 
/*Դͷ���ݱ�������*/,
vfirstrowno varchar(20) null 
/*Դͷ�����к�*/,
vsrctype varchar(20) null 
/*��Դ��??����*/,
vsrctrantype varchar(20) null 
/*��Դ��������*/,
csrcid varchar(20) null 
/*��Դ��������*/,
csrcbid varchar(20) null 
/*��Դ���ݸ���*/,
vsrccode varchar(40) null 
/*��Դ���ݺ�*/,
vsrcrowno varchar(20) null 
/*��Դ�����к�*/,
fretexchange integer null 
/*�˻������*/,
cexchangesrcretid varchar(20) null 
/*������Դ����*/,
bjczxsflag char(1) null 
/*���ת����*/,
vfree1 varchar(101) null 
/*���ɸ�������1*/,
vfree2 varchar(101) null 
/*���ɸ�������2*/,
vfree3 varchar(101) null 
/*���ɸ�������3*/,
vfree4 varchar(101) null 
/*���ɸ�������4*/,
vfree5 varchar(101) null 
/*���ɸ�������5*/,
vfree6 varchar(101) null 
/*���ɸ�������6*/,
vfree7 varchar(101) null 
/*���ɸ�������7*/,
vfree8 varchar(101) null 
/*���ɸ�������8*/,
vfree9 varchar(101) null 
/*���ɸ�������9*/,
vfree10 varchar(101) null 
/*���ɸ�������10*/,
vbdef1 varchar(101) null 
/*�Զ���???1*/,
vbdef2 varchar(101) null 
/*�Զ�����2*/,
vbdef3 varchar(101) null 
/*�Զ�����3*/,
vbdef4 varchar(101) null 
/*�Զ�����4*/,
vbdef5 varchar(101) null 
/*�Զ�����5*/,
vbdef6 varchar(101) null 
/*�Զ�����6*/,
vbdef7 varchar(101) null 
/*�Զ�����7*/,
vbdef8 varchar(101) null 
/*�Զ�����8*/,
vbdef9 varchar(101) null 
/*�Զ�����9*/,
vbdef10 varchar(101) null 
/*�Զ�����10*/,
vbdef11 varchar(101) null 
/*�Զ�����11*/,
vbdef12 varchar(101) null 
/*�Զ�����12*/,
vbdef13 varchar(101) null 
/*�Զ�����13*/,
vbdef14 varchar(101) null 
/*�Զ�??��14*/,
vbdef15 varchar(101) null 
/*�Զ�����15*/,
vbdef16 varchar(101) null 
/*�Զ�����16*/,
vbdef17 varchar(101) null 
/*�Զ�����17*/,
vbdef18 varchar(101) null 
/*�Զ�����18*/,
vbdef19 varchar(101) null 
/*�Զ�����19*/,
vbdef20 varchar(101) null 
/*�Զ�����20*/,
bbsendendflag char(1) null 
/*�����ر�*/,
bboutendflag char(1) null 
/*����ر�*/,
bbinvoicendflag char(1) null 
/*��Ʊ�ر�*/,
bbcostsettleflag char(1) null 
/*�ɱ�����ر�*/,
bbarsettleflag char(1) null 
/*�������ر�*/,
frowstatus integer null 
/*��״̬*/,
vrownote varchar(181) null 
/*�б�ע*/,
crececountryid varchar(20) null 
/*�ջ�����/����*/,
csendcountryid varchar(20) null 
/*��������/����*/,
ctaxcountryid varchar(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
ctaxcodeid varchar(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
corigcountryid varchar(20) null 
/*ԭ����*/,
corigareaid varchar(20) null 
/*ԭ������*/,
cbuypromottypeid varchar(20) null 
/*������������*/,
cprcpromottypeid varchar(20) null 
/*ѯ�۴�������*/,
vcustombillcode varchar(40) null 
/*�ͻ�������*/,
cbuylargessactid varchar(20) null 
/*�����*/,
cpricepromtactid varchar(20) null 
/*�۸�����*/,
cbuylargessid varchar(20) null 
/*��������*/,
csprofitcentervid varchar(20) null 
/*������������*/,
csprofitcenterid varchar(20) null 
/*���������������°汾*/,
blrgcashflag char(1) null 
/*��Ʒ�Ҹ�*/,
naccprice decimal(28,8) null 
/*�����˵���*/,
cpromotpriceid varchar(20) null 
/*�����۸����*/,
cmffileid varchar(20) null 
/*������*/,
nmffileprice decimal(28,8) null 
/*������*/,
 constraint pk_so_saleorder_b primary key (csaleorderbid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: so_saleorder_exe_��չ�� */
create table so_saleorder_exe (ntotalsendnum decimal(28,8) null 
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
csaleorderbid char(20) not null 
/*��չ������*/,
narrangeitcnum decimal(28,8) null 
/*�ۼư��Ž��ں�ͬ������*/,
 constraint pk_o_saleorder_exe primary key (csaleorderbid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �����տ������ʵ�� */
create table so_balance (csobalanceid char(20) not null 
/*�����տ������ʵ��*/,
csaleorderid varchar(20) null 
/*���۶�����ʵ��*/,
pk_org varchar(20) null 
/*������֯*/,
pk_org_v varchar(20) null 
/*������֯���°汾*/,
pk_group varchar(20) null 
/*����*/,
ccustomerid varchar(20) null 
/*�����ͻ�*/,
cinvoicecustid varchar(20) null 
/*��Ʊ�ͻ�*/,
vbillcode varchar(40) null 
/*������*/,
corigcurrencyid varchar(20) null 
/*����*/,
ntotalorigtaxmny decimal(28,8) null 
/*��˰�ϼ�*/,
ntotalpaymny decimal(28,8) null 
/*�������տ���*/,
ntotalorigbalmny decimal(28,8) null 
/*�����Ѻ������*/,
cpaytermid varchar(20) null 
/*�տ�Э��*/,
cemployeeid varchar(20) null 
/*ҵ��Ա*/,
cdeptid varchar(20) null 
/*����*/,
carorgid varchar(20) null 
/*Ӧ����֯*/,
vtrantypecode varchar(20) null 
/*���۶�������*/,
cchanneltypeid varchar(20) null 
/*������������*/,
dbilldate varchar(19) null 
/*��������*/,
 constraint pk_so_balance primary key (csobalanceid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �����տ������ʵ�� */
create table so_balance_b (csobalancebid char(20) not null 
/*�����տ������ʵ��*/,
pk_org varchar(20) null 
/*������֯*/,
fibaltype integer null 
/*��������*/,
cpaybillid varchar(20) null 
/*�տ��ʵ��*/,
cpaybillrowid varchar(20) null 
/*�տ��ʵ��*/,
varbillcode varchar(40) null 
/*���ݺ�*/,
darbilldate varchar(19) null 
/*��������*/,
norigarmny decimal(28,8) null 
/*�����н��*/,
carorigcurrencyid varchar(20) null 
/*����*/,
darbalancedate varchar(19) null 
/*��������*/,
cprodlineid varchar(20) null 
/*��Ʒ��*/,
norigordbalmny decimal(28,8) null 
/*�����������*/,
norigaccbalmny decimal(28,8) null 
/*�տ�Ѳ���������*/,
csobalanceid varchar(20) null 
/*�����տ������ʵ��_����*/,
bpreceiveflag char(1) null 
/*�տ��޶����Ԥ��*/,
 constraint pk_so_balance_b primary key (csobalancebid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۶����޶����� */
create table so_orderhistory (corderhistoryid char(20) not null 
/*���۶����޶�����ID*/,
csaleorderid char(20) not null 
/*��������ID*/,
pk_org varchar(20) null 
/*������֯*/,
pk_group varchar(20) null 
/*����*/,
cbiztypeid varchar(20) null 
/*ҵ������*/,
ctrantypeid varchar(20) null 
/*��������*/,
vtrantypecode varchar(20) null 
/*�������ͱ���*/,
vbillcode varchar(40) null 
/*���ݺ�*/,
ccustomerid varchar(20) null 
/*�ͻ�*/,
dbilldate char(19) null 
/*��������*/,
cdeptid varchar(20) null 
/*����*/,
corigcurrencyid varchar(20) null 
/*ԭ�ұ���*/,
cemployeeid varchar(20) null 
/*ҵ��Ա*/,
cpaytermid varchar(20) null 
/*�տ�Э��*/,
cinvoicecustid varchar(20) null 
/*��Ʊ�ͻ�*/,
ccustbankid varchar(20) null 
/*��������*/,
ccustbankaccid varchar(20) null 
/*���������˺�*/,
ctransporttypeid varchar(20) null 
/*���䷽ʽ*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
vrevisereason varchar(181) null 
/*�޶�����*/,
badvfeeflag char(1) null 
/*�����˷�*/,
bfreecustflag char(1) null 
/*�Ƿ�ɢ��*/,
vcreditnum varchar(20) null 
/*����֤��*/,
cfreecustid varchar(20) null 
/*ɢ��*/,
billmaker varchar(20) null 
/*�Ƶ���*/,
creationtime char(19) null 
/*����ʱ��*/,
approver varchar(20) null 
/*������*/,
taudittime varchar(19) null 
/*�������*/,
modifiedtime char(19) null 
/*�޸�ʱ��*/,
fstatusflag smallint null 
/*����״̬*/,
vnote varchar(181) null 
/*��ע*/,
boutendflag char(1) null 
/*����رձ��*/,
binvoicendflag char(1) null 
/*��Ʊ�رձ��*/,
bcostsettleflag char(1) null 
/*�ɱ�����رձ��*/,
bsendendflag char(1) null 
/*�����رձ��*/,
npreceiverate decimal(28,8) null 
/*�����տ����*/,
npreceivequota decimal(28,8) null 
/*�����տ��޶�*/,
bpreceiveflag char(1) null 
/*�տ��޶����Ԥ��*/,
npreceivemny decimal(28,8) null 
/*ʵ��Ԥ�տ���*/,
nreceivedmny decimal(28,8) null 
/*ʵ���տ���*/,
iprintcount integer null 
/*��ӡ����*/,
ntotalorigmny decimal(28,8) null 
/*���ϼ�*/,
ntotalorigsubmny decimal(28,8) null 
/*��ֽ��*/,
boffsetflag char(1) null 
/*�Ƿ���*/,
bcooptopoflag char(1) null 
/*�Ƿ���Эͬ���ɲɹ�����*/,
bpocooptomeflag char(1) null 
/*�Ƿ��ɲɹ�����Эͬ����*/,
vcooppohcode varchar(40) null 
/*�Է�������*/,
iversion integer null 
/*�޶��汾��*/,
trevisetime char(19) null 
/*�޶�ʱ��*/,
creviserid varchar(20) null 
/*�޶���*/,
cbalancetypeid varchar(20) null 
/*���㷽ʽ*/,
cchanneltypeid varchar(20) null 
/*������������*/,
ntotalnum decimal(28,8) null 
/*�ϼ�����*/,
ntotalweight decimal(28,8) null 
/*�ϼ�����*/,
ntotalvolume decimal(28,8) null 
/*�ϼ����*/,
vdef1 varchar(101) null 
/*�Զ�����1*/,
vdef2 varchar(101) null 
/*�Զ�����2*/,
vdef3 varchar(101) null 
/*�Զ�����3*/,
vdef4 varchar(101) null 
/*�Զ�����4*/,
vdef5 varchar(101) null 
/*�Զ�����5*/,
vdef6 varchar(101) null 
/*�Զ�����6*/,
vdef7 varchar(101) null 
/*�Զ�����7*/,
vdef8 varchar(101) null 
/*�Զ�����8*/,
vdef9 varchar(101) null 
/*�Զ�����9*/,
vdef10 varchar(101) null 
/*�Զ�����10*/,
vdef11 varchar(101) null 
/*�Զ�����11*/,
vdef12 varchar(101) null 
/*�Զ�����12*/,
vdef13 varchar(101) null 
/*�Զ�����13*/,
vdef14 varchar(101) null 
/*�Զ�����14*/,
vdef15 varchar(101) null 
/*�Զ�����15*/,
vdef16 varchar(101) null 
/*�Զ�����16*/,
vdef17 varchar(101) null 
/*�Զ�����17*/,
vdef18 varchar(101) null 
/*�Զ�����18*/,
vdef19 varchar(101) null 
/*�Զ�����19*/,
vdef20 varchar(101) null 
/*�Զ�����20*/,
modifier varchar(20) null 
/*�޸���*/,
pk_org_v varchar(20) null 
/*������֯���°汾*/,
cdeptvid varchar(20) null 
/*�������°汾*/,
barsettleflag char(1) null 
/*�������رձ��*/,
creator char(20) null 
/*������*/,
ntotalpiece decimal(28,8) null 
/*�ܼ���*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
fpfstatusflag smallint null 
/*������״̬*/,
ctradewordid varchar(20) null 
/*ó������*/,
chistrantypeid varchar(20) null 
/*�޶���������*/,
vhistrantypecode varchar(20) null 
/*�޶��������ͱ���*/,
carsubtypeid varchar(20) null 
/*��Ʒ�Ҹ�����*/,
chreceivecustid varchar(20) null 
/*�ջ��ͻ�*/,
chreceiveaddid varchar(20) null 
/*�ջ���ַ*/,
vbillsrctype varchar(20) null 
/*������Դ��������*/,
cbillsrcid varchar(20) null 
/*������Դ����ID*/,
nlrgtotalorigmny decimal(28,8) null 
/*��Ʒ��˰�ϼ�*/,
 constraint pk_so_orderhist primary key (corderhistoryid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۶����޶����� */
create table so_orderhistory_b (corderhistorybid char(20) not null 
/*���۶����޶�����ID*/,
corderhistoryid varchar(20) not null 
/*���۶����޶�����ID*/,
csaleorderid varchar(20) null 
/*���۶�������*/,
csaleorderbid char(20) not null 
/*���۶�������*/,
pk_group varchar(20) null 
/*����*/,
pk_org varchar(20) null 
/*������֯*/,
dbilldate char(19) null 
/*��������*/,
crowno varchar(20) null 
/*�к�*/,
ccustmaterialid varchar(20) null 
/*�ͻ�������*/,
cmaterialvid varchar(20) null 
/*����*/,
cproductorid varchar(20) null 
/*��������*/,
cmaterialid varchar(20) null 
/*�������°汾*/,
cvendorid varchar(20) null 
/*��Ӧ��*/,
cfactoryid varchar(20) null 
/*����*/,
cqualitylevelid varchar(20) null 
/*�����ȼ�*/,
cunitid varchar(20) null 
/*����λ*/,
cprojectid varchar(20) null 
/*��Ŀ*/,
castunitid varchar(20) null 
/*��λ*/,
vchangerate varchar(60) null 
/*������*/,
nnum decimal(28,8) null 
/*������*/,
nastnum decimal(28,8) null 
/*����*/,
cqtunitid varchar(20) null 
/*���۵�λ*/,
vqtunitrate varchar(60) null 
/*���۵�λ������*/,
nqtunitnum decimal(28,8) null 
/*���۵�λ����*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
nitemdiscountrate decimal(28,8) null 
/*��Ʒ�ۿ�*/,
ndiscountrate decimal(28,8) null 
/*�����ۿ�*/,
ccurrencyid varchar(20) null 
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
cpricepolicyid varchar(20) null 
/*�۸�����*/,
cpriceitemid varchar(20) null 
/*�۸���Ŀ*/,
cpriceitemtableid varchar(20) null 
/*��Ŀ��*/,
cpriceformid varchar(20) null 
/*�۸����*/,
blargessflag char(1) null 
/*�Ƿ���Ʒ*/,
cprodlineid varchar(20) null 
/*��Ʒ��*/,
blaborflag char(1) null 
/*����������*/,
bdiscountflag char(1) null 
/*�ۿ�������*/,
vbatchcode varchar(40) null 
/*����*/,
pk_batchcode varchar(20) null 
/*���ε���*/,
dsenddate char(19) null 
/*�ƻ���������*/,
dreceivedate char(19) null 
/*Ҫ�󵽻�����*/,
creceivecustid varchar(20) null 
/*�ջ��ͻ�*/,
creceiveareaid varchar(20) null 
/*�ջ�����*/,
creceiveaddrid varchar(20) null 
/*�ջ���ַ*/,
creceiveadddocid varchar(20) null 
/*�ջ��ص�*/,
csendstockorgvid varchar(20) null 
/*���������֯*/,
csendstockorgid varchar(20) null 
/*���������֯���°汾*/,
csendstordocid varchar(20) null 
/*�����ֿ�*/,
csettleorgvid varchar(20) null 
/*���������֯*/,
csettleorgid varchar(20) null 
/*���������֯���������֯*/,
carorgvid varchar(20) null 
/*Ӧ����֯*/,
carorgid varchar(20) null 
/*Ӧ����֯���°汾*/,
ctrafficorgvid varchar(20) null 
/*������֯*/,
ctrafficorgid varchar(20) null 
/*������֯���°汾*/,
cprofitcentervid varchar(20) null 
/*��������*/,
cprofitcenterid varchar(20) null 
/*�����������°汾*/,
bbindflag char(1) null 
/*�Ƿ�������*/,
clargesssrcid varchar(20) null 
/*��Ʒ�ж�Ӧ��Դ������ID*/,
cbindsrcid varchar(20) null 
/*�������Ӧ��Դ������ID*/,
flargesstypeflag integer null 
/*��Ʒ�۸��̯��ʽ*/,
nlargessmny decimal(28,8) null 
/*��Ʒ�۸��̯ǰ��˰���*/,
nlargesstaxmny decimal(28,8) null 
/*��Ʒ�۸��̯ǰ��˰�ϼ�*/,
vbrevisereason varchar(181) null 
/*�޶�����*/,
cretreasonid varchar(20) null 
/*�˻�ԭ��ID*/,
vreturnmode varchar(181) null 
/*�˻����δ���ʽ*/,
cretpolicyid varchar(20) null 
/*�˻�����ID*/,
barrangedflag char(1) null 
/*�Ƿ��Դ�������*/,
carrangepersonid varchar(20) null 
/*����Դ������*/,
tlastarrangetime char(19) null 
/*����Դ����ʱ��*/,
vclosereason varchar(181) null 
/*�ر�ԭ��*/,
cctmanageid varchar(20) null 
/*��ͬID*/,
cctmanagebid varchar(20) null 
/*��ͬ����ID*/,
vctcode varchar(40) null 
/*���ۺ�ͬ��*/,
vfirsttype varchar(20) null 
/*Դͷ��������*/,
vfirsttrantype varchar(20) null 
/*Դͷ��������*/,
vfirstcode varchar(40) null 
/*Դͷ���ݺ�*/,
cfirstid varchar(20) null 
/*Դͷ������������*/,
cfirstbid varchar(20) null 
/*Դͷ���ݱ�������*/,
vfirstrowno varchar(20) null 
/*Դͷ�����к�*/,
vsrctype varchar(20) null 
/*��Դ��������*/,
vsrctrantype varchar(20) null 
/*��??��������*/,
csrcid varchar(20) null 
/*��Դ��������*/,
csrcbid varchar(20) null 
/*��Դ���ݸ���*/,
vsrccode varchar(40) null 
/*��Դ���ݺ�*/,
vsrcrowno varchar(20) null 
/*��Դ�����к�*/,
fretexchange integer null 
/*�˻������*/,
cexchangesrcretid varchar(20) null 
/*������Դ����*/,
bjczxsflag char(1) null 
/*���ת����*/,
vfree1 varchar(101) null 
/*���ɸ�������1*/,
vfree2 varchar(101) null 
/*���ɸ�������2*/,
vfree3 varchar(101) null 
/*���ɸ�������3*/,
vfree4 varchar(101) null 
/*���ɸ�������4*/,
vfree5 varchar(101) null 
/*���ɸ�������5*/,
vfree6 varchar(101) null 
/*���ɸ�������6*/,
vfree7 varchar(101) null 
/*���ɸ�������7*/,
vfree8 varchar(101) null 
/*����???������8*/,
vfree9 varchar(101) null 
/*���ɸ�������9*/,
vfree10 varchar(101) null 
/*���ɸ�������10*/,
vbdef1 varchar(101) null 
/*�Զ�����1*/,
vbdef2 varchar(101) null 
/*�Զ�����2*/,
vbdef3 varchar(101) null 
/*�Զ�����3*/,
vbdef4 varchar(101) null 
/*�Զ�����4*/,
vbdef5 varchar(101) null 
/*�Զ�����5*/,
vbdef6 varchar(101) null 
/*�Զ�����6*/,
vbdef7 varchar(101) null 
/*�Զ�����7*/,
vbdef8 varchar(101) null 
/*�Զ�����8*/,
vbdef9 varchar(101) null 
/*�Զ�����9*/,
vbdef10 varchar(101) null 
/*�Զ�����10*/,
vbdef11 varchar(101) null 
/*�Զ�����11*/,
vbdef12 varchar(101) null 
/*�Զ�����12*/,
vbdef13 varchar(101) null 
/*�Զ�����13*/,
vbdef14 varchar(101) null 
/*�Զ�����14*/,
vbdef15 varchar(101) null 
/*�Զ�����15*/,
vbdef16 varchar(101) null 
/*�Զ�����16*/,
vbdef17 varchar(101) null 
/*�Զ�����17*/,
vbdef18 varchar(101) null 
/*�Զ�???��18*/,
vbdef19 varchar(101) null 
/*�Զ�����19*/,
vbdef20 varchar(101) null 
/*�Զ�����20*/,
bbsendendflag char(1) null 
/*�����ر�*/,
bboutendflag char(1) null 
/*����ر�*/,
bbinvoicendflag char(1) null 
/*��Ʊ�ر�*/,
bbcostsettleflag char(1) null 
/*�ɱ�����ر�*/,
bbarsettleflag char(1) null 
/*�������ر�*/,
frowstatus integer null 
/*��״̬*/,
vrownote varchar(181) null 
/*�б�ע*/,
crececountryid varchar(20) null 
/*�ջ�����/����*/,
csendcountryid varchar(20) null 
/*��������/����*/,
ctaxcountryid varchar(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
ctaxcodeid varchar(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
corigcountryid varchar(20) null 
/*ԭ����*/,
corigareaid varchar(20) null 
/*ԭ������*/,
cbuypromottypeid varchar(20) null 
/*������������*/,
cprcpromottypeid varchar(20) null 
/*ѯ�۴�������*/,
vcustombillcode varchar(40) null 
/*�ͻ�������*/,
cbuylargessactid varchar(20) null 
/*�����*/,
cpricepromtactid varchar(20) null 
/*�۸�����*/,
cbuylargessid varchar(20) null 
/*��������*/,
csprofitcentervid varchar(20) null 
/*������������*/,
csprofitcenterid varchar(20) null 
/*���������������°汾*/,
cmffileid varchar(20) null 
/*������*/,
nmffileprice decimal(28,8) null 
/*������*/,
cpromotpriceid varchar(20) null 
/*�����۸����*/,
blrgcashflag char(1) null 
/*��Ʒ�Ҹ�*/,
naccprice decimal(28,8) null 
/*�����˵���*/,
 constraint pk_so_orderhist_b primary key (corderhistorybid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۶����ҵĶ���ת���� */
create table so_mb_myorder (csaleorderid char(20) not null 
/*������ʵ��*/,
pk_group varchar(20) null 
/*����*/,
dbilldate char(19) null 
/*��������*/,
pk_org varchar(20) null 
/*������֯*/,
vbillcode varchar(40) null 
/*������*/,
ccustomerid varchar(20) null 
/*�ͻ�*/,
ntotalorigmny decimal(28,8) null 
/*��˰�ϼ�*/,
cemployeeid varchar(20) null 
/*ҵ��Ա*/,
 constraint pk_so_mb_myorder primary key (csaleorderid),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���۶����ƶ������� */
create table so_mb_orderanaly (pk_group varchar(20) null 
/*����*/,
dbilldate char(19) null 
/*��������*/,
pk_org varchar(20) null 
/*������֯*/,
vbillcode varchar(40) null 
/*������*/,
ccustomerid varchar(20) null 
/*�ͻ�*/,
cdeptid varchar(20) null 
/*����*/,
cemployeeid varchar(20) null 
/*ҵ��Ա*/,
cchanneltypeid varchar(20) null 
/*��������*/,
cmaterialid varchar(20) null 
/*����*/,
cprodlineid varchar(20) null 
/*��Ʒ��*/,
cbrandid varchar(20) null 
/*Ʒ��*/,
nnum decimal(28,8) null 
/*����������*/,
norigtaxmny decimal(28,8) null 
/*��˰�ϼ�*/,
corigcurrencyid varchar(20) null 
/*ԭ��*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
csaleorderid varchar(20) null 
/*��������ID*/,
  ts char(19) null,
dr smallint null default 0
)
;

