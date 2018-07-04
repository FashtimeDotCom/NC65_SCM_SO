/* tablename: ���۶����������� */
create table so_m30trantype (ctrantypeid varchar2(20) null 
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
vtrantypecode varchar2(20) null 
/*�������ͱ���*/,
pk_group varchar2(20) null 
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
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۶������� */
create table so_saleorder (csaleorderid char(20) not null 
/*��������ID*/,
pk_org varchar2(20) null 
/*������֯*/,
pk_group varchar2(20) null 
/*����*/,
cbiztypeid varchar2(20) null 
/*ҵ������*/,
ctrantypeid varchar2(20) null 
/*��������*/,
vtrantypecode varchar2(20) null 
/*�������ͱ���*/,
vbillcode varchar2(40) null 
/*���ݺ�*/,
ccustomerid varchar2(20) null 
/*�ͻ�*/,
dbilldate char(19) null 
/*��������*/,
cdeptid varchar2(20) null 
/*����*/,
corigcurrencyid varchar2(20) null 
/*ԭ�ұ���*/,
cemployeeid varchar2(20) null 
/*ҵ��Ա*/,
cpaytermid varchar2(20) null 
/*�տ�Э��*/,
cinvoicecustid varchar2(20) null 
/*��Ʊ�ͻ�*/,
ccustbankid varchar2(20) null 
/*��������*/,
ccustbankaccid varchar2(20) null 
/*���������˺�*/,
ctransporttypeid varchar2(20) null 
/*���䷽ʽ*/,
ndiscountrate number(28,8) null 
/*�����ۿ�*/,
vrevisereason varchar2(181) null 
/*�޶�����*/,
badvfeeflag char(1) null 
/*�����˷�*/,
bfreecustflag char(1) null 
/*�Ƿ�ɢ��*/,
vcreditnum varchar2(20) null 
/*����֤��*/,
cfreecustid varchar2(20) null 
/*ɢ��*/,
billmaker varchar2(20) null 
/*�Ƶ���*/,
creationtime char(19) null 
/*����ʱ��*/,
approver varchar2(20) null 
/*������*/,
taudittime varchar2(19) null 
/*�������*/,
modifiedtime char(19) null 
/*�޸�ʱ��*/,
fstatusflag smallint null 
/*����״̬*/,
vnote varchar2(181) null 
/*��ע*/,
boutendflag char(1) null 
/*����رձ��*/,
binvoicendflag char(1) null 
/*��Ʊ�رձ��*/,
bcostsettleflag char(1) null 
/*�ɱ�����رձ��*/,
bsendendflag char(1) null 
/*�����رձ��*/,
npreceiverate number(28,8) null 
/*�����տ����*/,
npreceivequota number(28,8) null 
/*�����տ��޶�*/,
bpreceiveflag char(1) null 
/*�տ��޶����Ԥ��*/,
npreceivemny number(28,8) null 
/*ʵ��Ԥ�տ���*/,
nreceivedmny number(28,8) null 
/*ʵ���տ���*/,
iprintcount integer null 
/*��ӡ����*/,
ntotalorigmny number(28,8) null 
/*���ϼ�*/,
ntotalorigsubmny number(28,8) null 
/*��ֽ��*/,
boffsetflag char(1) null 
/*�Ƿ���*/,
bcooptopoflag char(1) null 
/*�Ƿ���Эͬ���ɲɹ�����*/,
bpocooptomeflag char(1) null 
/*�Ƿ��ɲɹ�����Эͬ����*/,
vcooppohcode varchar2(40) null 
/*�Է�������*/,
iversion integer null 
/*�޶��汾��*/,
trevisetime char(19) null 
/*�޶�ʱ???*/,
creviserid varchar2(20) null 
/*�޶���*/,
cbalancetypeid varchar2(20) null 
/*���㷽ʽ*/,
cchanneltypeid varchar2(20) null 
/*������������*/,
ntotalnum number(28,8) null 
/*�ϼ�����*/,
ntotalweight number(28,8) null 
/*�ϼ�����*/,
ntotalvolume number(28,8) null 
/*�ϼ����*/,
vdef1 varchar2(101) null 
/*�Զ�����1*/,
vdef2 varchar2(101) null 
/*�Զ�����2*/,
vdef3 varchar2(101) null 
/*�Զ�����3*/,
vdef4 varchar2(101) null 
/*�Զ�����4*/,
vdef5 varchar2(101) null 
/*�Զ�����5*/,
vdef6 varchar2(101) null 
/*�Զ�����6*/,
vdef7 varchar2(101) null 
/*�Զ�����7*/,
vdef8 varchar2(101) null 
/*�Զ�����8*/,
vdef9 varchar2(101) null 
/*�Զ�����9*/,
vdef10 varchar2(101) null 
/*�Զ�����10*/,
vdef11 varchar2(101) null 
/*�Զ�����11*/,
vdef12 varchar2(101) null 
/*�Զ�����12*/,
vdef13 varchar2(101) null 
/*�Զ�����13*/,
vdef14 varchar2(101) null 
/*�Զ�����14*/,
vdef15 varchar2(101) null 
/*�Զ�����15*/,
vdef16 varchar2(101) null 
/*�Զ�����16*/,
vdef17 varchar2(101) null 
/*�Զ�����17*/,
vdef18 varchar2(101) null 
/*�Զ�����18*/,
vdef19 varchar2(101) null 
/*�Զ�����19*/,
vdef20 varchar2(101) null 
/*�Զ�����20*/,
modifier varchar2(20) null 
/*�޸���*/,
pk_org_v varchar2(20) null 
/*������֯���°汾*/,
cdeptvid varchar2(20) null 
/*�������°汾*/,
barsettleflag char(1) null 
/*�������رձ��*/,
creator char(20) null 
/*������*/,
ntotalpiece number(28,8) null 
/*��???��*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
fpfstatusflag smallint null 
/*������״̬*/,
ctradewordid varchar2(20) null 
/*ó������*/,
vbillsrctype varchar2(20) null 
/*������Դ��������*/,
cbillsrcid varchar2(20) null 
/*������Դ����ID*/,
nlrgtotalorigmny number(28,8) null 
/*��Ʒ��˰�ϼ�*/,
carsubtypeid varchar2(20) null 
/*��Ʒ�Ҹ�����*/,
chreceivecustid varchar2(20) null 
/*�ջ��ͻ�*/,
chreceiveaddid varchar2(20) null 
/*�ջ���ַ*/,
 constraint pk_so_saleorder primary key (csaleorderid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۶������� */
create table so_saleorder_b (csaleorderid varchar2(20) null 
/*���۶�������*/,
csaleorderbid char(20) not null 
/*���۶�������*/,
pk_group varchar2(20) null 
/*����*/,
pk_org varchar2(20) null 
/*������֯*/,
dbilldate char(19) null 
/*��������*/,
crowno varchar2(20) null 
/*�к�*/,
ccustmaterialid varchar2(20) null 
/*�ͻ�������*/,
cmaterialvid varchar2(20) null 
/*����*/,
cproductorid varchar2(20) null 
/*��������*/,
cmaterialid varchar2(20) null 
/*��������??��*/,
cvendorid varchar2(20) null 
/*��Ӧ��*/,
cfactoryid varchar2(20) null 
/*����*/,
cqualitylevelid varchar2(20) null 
/*�����ȼ�*/,
cunitid varchar2(20) null 
/*����λ*/,
cprojectid varchar2(20) null 
/*��Ŀ*/,
castunitid varchar2(20) null 
/*��λ*/,
vchangerate varchar2(60) null 
/*������*/,
nnum number(28,8) null 
/*������*/,
nastnum number(28,8) null 
/*����*/,
cqtunitid varchar2(20) null 
/*���۵�λ*/,
vqtunitrate varchar2(60) null 
/*���۵�λ������*/,
nqtunitnum number(28,8) null 
/*���۵�λ����*/,
ntaxrate number(28,8) null 
/*˰��*/,
nitemdiscountrate number(28,8) null 
/*��Ʒ�ۿ�*/,
ndiscountrate number(28,8) null 
/*�����ۿ�*/,
ccurrencyid varchar2(20) null 
/*��λ��*/,
nexchangerate number(28,8) null 
/*�۱�����*/,
nqtorigtaxprice number(28,8) null 
/*��˰����*/,
nqtorigprice number(28,8) null 
/*��˰����*/,
nqtorigtaxnetprc number(28,8) null 
/*��˰����*/,
nqtorignetprice number(28,8) null 
/*��˰����*/,
norigprice number(28,8) null 
/*����˰����*/,
norigtaxprice number(28,8) null 
/*����˰����*/,
norignetprice number(28,8) null 
/*����˰����*/,
norigtaxnetprice number(28,8) null 
/*����˰����*/,
norigmny number(28,8) null 
/*��˰���*/,
norigtaxmny number(28,8) null 
/*��˰�ϼ�*/,
norigdiscount number(28,8) null 
/*�ۿ۶�*/,
nqttaxnetprice number(28,8) null 
/*���Һ�˰����*/,
nqtnetprice number(28,8) null 
/*������˰����*/,
nqttaxprice number(28,8) null 
/*���Һ�˰����*/,
nqtprice number(28,8) null 
/*������˰����*/,
nprice number(28,8) null 
/*��������˰����*/,
ntaxprice number(28,8) null 
/*�����Һ�˰����*/,
nnetprice number(28,8) null 
/*��������˰����*/,
ntaxnetprice number(28,8) null 
/*�����Һ�˰����*/,
ntax number(28,8) null 
/*����˰��*/,
nmny number(28,8) null 
/*������˰���*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount number(28,8) null 
/*�����ۿ۶�*/,
nweight number(28,8) null 
/*����*/,
nvolume number(28,8) null 
/*���*/,
npiece number(28,8) null 
/*����*/,
ngroupexchgrate number(28,8) null 
/*���ű�λ�һ���*/,
ngroupmny number(28,8) null 
/*���ű�����˰���*/,
ngrouptaxmny number(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
nglobalexchgrate number(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
nglobalmny number(28,8) null 
/*ȫ�ֱ�����˰���*/,
nglobaltaxmny number(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
naskqtorigtaxprc number(28,8) null 
/*ѯ��ԭ�Һ�˰����*/,
naskqtorigprice number(28,8) null 
/*ѯ��ԭ����˰����*/,
naskqtorigtxntprc number(28,8) null 
/*ѯ��ԭ�Һ�˰����*/,
naskqtorignetprice number(28,8) null 
/*ѯ��ԭ����˰����*/,
cpricepolicyid varchar2(20) null 
/*�۸�����*/,
cpriceitemid varchar2(20) null 
/*�۸���Ŀ*/,
cpriceitemtableid varchar2(20) null 
/*��Ŀ��*/,
cpriceformid varchar2(20) null 
/*�۸����*/,
blargessflag char(1) null 
/*�Ƿ���Ʒ*/,
cprodlineid varchar2(20) null 
/*��Ʒ��*/,
blaborflag char(1) null 
/*����������*/,
bdiscountflag char(1) null 
/*�ۿ�������*/,
vbatchcode varchar2(40) null 
/*����*/,
pk_batchcode varchar2(20) null 
/*���ε���*/,
dsenddate char(19) null 
/*�ƻ���������*/,
dreceivedate char(19) null 
/*Ҫ�󵽻�����*/,
creceivecustid varchar2(20) null 
/*�ջ��ͻ�*/,
creceiveareaid varchar2(20) null 
/*�ջ�����*/,
creceiveaddrid varchar2(20) null 
/*�ջ���ַ*/,
creceiveadddocid varchar2(20) null 
/*�ջ��ص�*/,
csendstockorgvid varchar2(20) null 
/*���������֯*/,
csendstockorgid varchar2(20) null 
/*���������֯���°汾*/,
csendstordocid varchar2(20) null 
/*�����ֿ�*/,
csettleorgvid varchar2(20) null 
/*���������֯*/,
csettleorgid varchar2(20) null 
/*���������֯���������֯*/,
carorgvid varchar2(20) null 
/*Ӧ����֯*/,
carorgid varchar2(20) null 
/*Ӧ����֯���°汾*/,
ctrafficorgvid varchar2(20) null 
/*������֯*/,
ctrafficorgid varchar2(20) null 
/*������֯���°汾*/,
cprofitcentervid varchar2(20) null 
/*������������*/,
cprofitcenterid varchar2(20) null 
/*���������������°汾*/,
bbindflag char(1) null 
/*�Ƿ�??����*/,
clargesssrcid varchar2(20) null 
/*��Ʒ�ж�Ӧ��Դ������ID*/,
cbindsrcid varchar2(20) null 
/*�������Ӧ��Դ������ID*/,
flargesstypeflag integer null 
/*��Ʒ�۸��̯��ʽ*/,
nlargessmny number(28,8) null 
/*��Ʒ�۸��̯ǰ��˰���*/,
nlargesstaxmny number(28,8) null 
/*��Ʒ�۸��̯ǰ��˰�ϼ�*/,
vbrevisereason varchar2(181) null 
/*�޶�����*/,
cretreasonid varchar2(20) null 
/*�˻�ԭ��ID*/,
vreturnmode varchar2(181) null 
/*�˻����δ���ʽ*/,
cretpolicyid varchar2(20) null 
/*�˻�����ID*/,
barrangedflag char(1) null 
/*�Ƿ��Դ�������*/,
carrangepersonid varchar2(20) null 
/*����Դ������*/,
tlastarrangetime char(19) null 
/*����Դ����ʱ��*/,
vclosereason varchar2(181) null 
/*�ر�ԭ��*/,
cctmanageid varchar2(20) null 
/*��ͬID*/,
cctmanagebid varchar2(20) null 
/*��ͬ����ID*/,
vctcode varchar2(40) null 
/*���ۺ�ͬ��*/,
vfirsttype varchar2(20) null 
/*Դͷ��������*/,
vfirsttrantype varchar2(20) null 
/*Դͷ��������*/,
vfirstcode varchar2(40) null 
/*Դͷ���ݺ�*/,
cfirstid varchar2(20) null 
/*Դͷ������������*/,
cfirstbid varchar2(20) null 
/*Դͷ���ݱ�������*/,
vfirstrowno varchar2(20) null 
/*Դͷ�����к�*/,
vsrctype varchar2(20) null 
/*��Դ��??����*/,
vsrctrantype varchar2(20) null 
/*��Դ��������*/,
csrcid varchar2(20) null 
/*��Դ��������*/,
csrcbid varchar2(20) null 
/*��Դ���ݸ���*/,
vsrccode varchar2(40) null 
/*��Դ���ݺ�*/,
vsrcrowno varchar2(20) null 
/*��Դ�����к�*/,
fretexchange integer null 
/*�˻������*/,
cexchangesrcretid varchar2(20) null 
/*������Դ����*/,
bjczxsflag char(1) null 
/*���ת����*/,
vfree1 varchar2(101) null 
/*���ɸ�������1*/,
vfree2 varchar2(101) null 
/*���ɸ�������2*/,
vfree3 varchar2(101) null 
/*���ɸ�������3*/,
vfree4 varchar2(101) null 
/*���ɸ�������4*/,
vfree5 varchar2(101) null 
/*���ɸ�������5*/,
vfree6 varchar2(101) null 
/*���ɸ�������6*/,
vfree7 varchar2(101) null 
/*���ɸ�������7*/,
vfree8 varchar2(101) null 
/*���ɸ�������8*/,
vfree9 varchar2(101) null 
/*���ɸ�������9*/,
vfree10 varchar2(101) null 
/*���ɸ�������10*/,
vbdef1 varchar2(101) null 
/*�Զ���???1*/,
vbdef2 varchar2(101) null 
/*�Զ�����2*/,
vbdef3 varchar2(101) null 
/*�Զ�����3*/,
vbdef4 varchar2(101) null 
/*�Զ�����4*/,
vbdef5 varchar2(101) null 
/*�Զ�����5*/,
vbdef6 varchar2(101) null 
/*�Զ�����6*/,
vbdef7 varchar2(101) null 
/*�Զ�����7*/,
vbdef8 varchar2(101) null 
/*�Զ�����8*/,
vbdef9 varchar2(101) null 
/*�Զ�����9*/,
vbdef10 varchar2(101) null 
/*�Զ�����10*/,
vbdef11 varchar2(101) null 
/*�Զ�����11*/,
vbdef12 varchar2(101) null 
/*�Զ�����12*/,
vbdef13 varchar2(101) null 
/*�Զ�����13*/,
vbdef14 varchar2(101) null 
/*�Զ�??��14*/,
vbdef15 varchar2(101) null 
/*�Զ�����15*/,
vbdef16 varchar2(101) null 
/*�Զ�����16*/,
vbdef17 varchar2(101) null 
/*�Զ�����17*/,
vbdef18 varchar2(101) null 
/*�Զ�����18*/,
vbdef19 varchar2(101) null 
/*�Զ�����19*/,
vbdef20 varchar2(101) null 
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
vrownote varchar2(181) null 
/*�б�ע*/,
crececountryid varchar2(20) null 
/*�ջ�����/����*/,
csendcountryid varchar2(20) null 
/*��������/����*/,
ctaxcountryid varchar2(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
ctaxcodeid varchar2(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny number(28,8) null 
/*��˰���*/,
corigcountryid varchar2(20) null 
/*ԭ����*/,
corigareaid varchar2(20) null 
/*ԭ������*/,
cbuypromottypeid varchar2(20) null 
/*������������*/,
cprcpromottypeid varchar2(20) null 
/*ѯ�۴�������*/,
vcustombillcode varchar2(40) null 
/*�ͻ�������*/,
cbuylargessactid varchar2(20) null 
/*�����*/,
cpricepromtactid varchar2(20) null 
/*�۸�����*/,
cbuylargessid varchar2(20) null 
/*��������*/,
csprofitcentervid varchar2(20) null 
/*������������*/,
csprofitcenterid varchar2(20) null 
/*���������������°汾*/,
blrgcashflag char(1) null 
/*��Ʒ�Ҹ�*/,
naccprice number(28,8) null 
/*�����˵���*/,
cpromotpriceid varchar2(20) null 
/*�����۸����*/,
cmffileid varchar2(20) null 
/*������*/,
nmffileprice number(28,8) null 
/*������*/,
 constraint pk_so_saleorder_b primary key (csaleorderbid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: so_saleorder_exe_��չ�� */
create table so_saleorder_exe (ntotalsendnum number(28,8) null 
/*�ۼƷ�������*/,
ntotalinvoicenum number(28,8) null 
/*�ۼƿ�Ʊ����*/,
ntotaloutnum number(28,8) null 
/*�ۼƳ�������*/,
ntotalnotoutnum number(28,8) null 
/*�ۼ�Ӧ��δ��������*/,
ntotalsignnum number(28,8) null 
/*�ۼ�ǩ������*/,
ntranslossnum number(28,8) null 
/*�ۼ�;������*/,
ntotalrushnum number(28,8) null 
/*�ۼƳ���Գ�����*/,
ntotalestarnum number(28,8) null 
/*�ۼ��ݹ�Ӧ������*/,
ntotalarnum number(28,8) null 
/*�ۼ�ȷ��Ӧ������*/,
ntotalcostnum number(28,8) null 
/*�ۼƳɱ���������*/,
ntotalestarmny number(28,8) null 
/*�ۼ��ݹ�Ӧ�ս��*/,
ntotalarmny number(28,8) null 
/*�ۼ�ȷ��Ӧ�ս��*/,
ntotalpaymny number(28,8) null 
/*�ۼƲ���������*/,
norigsubmny number(28,8) null 
/*�ۼƳ�ֽ��*/,
narrangescornum number(28,8) null 
/*�ۼư���ί�ⶩ������*/,
narrangepoappnum number(28,8) null 
/*�ۼư����빺������*/,
narrangetoornum number(28,8) null 
/*�ۼư��ŵ�����������*/,
narrangetoappnum number(28,8) null 
/*�ۼư��ŵ�����������*/,
narrangemonum number(28,8) null 
/*�ۼư���������������*/,
narrangeponum number(28,8) null 
/*�ۼư��Ųɹ���������*/,
ntotalplonum number(28,8) null 
/*�ۼ����ɼƻ�����������*/,
ntotalreturnnum number(28,8) null 
/*�ۼ��˻�����*/,
ntotaltradenum number(28,8) null 
/*�ۼƷ�����Ʒ����*/,
nreqrsnum number(28,8) null 
/*Ԥ������*/,
csaleorderbid char(20) not null 
/*��չ������*/,
narrangeitcnum number(28,8) null 
/*�ۼư��Ž��ں�ͬ������*/,
 constraint pk_o_saleorder_exe primary key (csaleorderbid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: �����տ������ʵ�� */
create table so_balance (csobalanceid char(20) not null 
/*�����տ������ʵ��*/,
csaleorderid varchar2(20) null 
/*���۶�����ʵ��*/,
pk_org varchar2(20) null 
/*������֯*/,
pk_org_v varchar2(20) null 
/*������֯���°汾*/,
pk_group varchar2(20) null 
/*����*/,
ccustomerid varchar2(20) null 
/*�����ͻ�*/,
cinvoicecustid varchar2(20) null 
/*��Ʊ�ͻ�*/,
vbillcode varchar2(40) null 
/*������*/,
corigcurrencyid varchar2(20) null 
/*����*/,
ntotalorigtaxmny number(28,8) null 
/*��˰�ϼ�*/,
ntotalpaymny number(28,8) null 
/*�������տ���*/,
ntotalorigbalmny number(28,8) null 
/*�����Ѻ������*/,
cpaytermid varchar2(20) null 
/*�տ�Э��*/,
cemployeeid varchar2(20) null 
/*ҵ��Ա*/,
cdeptid varchar2(20) null 
/*����*/,
carorgid varchar2(20) null 
/*Ӧ����֯*/,
vtrantypecode varchar2(20) null 
/*���۶�������*/,
cchanneltypeid varchar2(20) null 
/*������������*/,
dbilldate varchar2(19) null 
/*��������*/,
 constraint pk_so_balance primary key (csobalanceid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: �����տ������ʵ�� */
create table so_balance_b (csobalancebid char(20) not null 
/*�����տ������ʵ��*/,
pk_org varchar2(20) null 
/*������֯*/,
fibaltype integer null 
/*��������*/,
cpaybillid varchar2(20) null 
/*�տ��ʵ��*/,
cpaybillrowid varchar2(20) null 
/*�տ��ʵ��*/,
varbillcode varchar2(40) null 
/*���ݺ�*/,
darbilldate varchar2(19) null 
/*��������*/,
norigarmny number(28,8) null 
/*�����н��*/,
carorigcurrencyid varchar2(20) null 
/*����*/,
darbalancedate varchar2(19) null 
/*��������*/,
cprodlineid varchar2(20) null 
/*��Ʒ��*/,
norigordbalmny number(28,8) null 
/*�����������*/,
norigaccbalmny number(28,8) null 
/*�տ�Ѳ���������*/,
csobalanceid varchar2(20) null 
/*�����տ������ʵ��_����*/,
bpreceiveflag char(1) null 
/*�տ��޶����Ԥ��*/,
 constraint pk_so_balance_b primary key (csobalancebid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۶����޶����� */
create table so_orderhistory (corderhistoryid char(20) not null 
/*���۶����޶�����ID*/,
csaleorderid char(20) not null 
/*��������ID*/,
pk_org varchar2(20) null 
/*������֯*/,
pk_group varchar2(20) null 
/*����*/,
cbiztypeid varchar2(20) null 
/*ҵ������*/,
ctrantypeid varchar2(20) null 
/*��������*/,
vtrantypecode varchar2(20) null 
/*�������ͱ���*/,
vbillcode varchar2(40) null 
/*���ݺ�*/,
ccustomerid varchar2(20) null 
/*�ͻ�*/,
dbilldate char(19) null 
/*��������*/,
cdeptid varchar2(20) null 
/*����*/,
corigcurrencyid varchar2(20) null 
/*ԭ�ұ���*/,
cemployeeid varchar2(20) null 
/*ҵ��Ա*/,
cpaytermid varchar2(20) null 
/*�տ�Э��*/,
cinvoicecustid varchar2(20) null 
/*��Ʊ�ͻ�*/,
ccustbankid varchar2(20) null 
/*��������*/,
ccustbankaccid varchar2(20) null 
/*���������˺�*/,
ctransporttypeid varchar2(20) null 
/*���䷽ʽ*/,
ndiscountrate number(28,8) null 
/*�����ۿ�*/,
vrevisereason varchar2(181) null 
/*�޶�����*/,
badvfeeflag char(1) null 
/*�����˷�*/,
bfreecustflag char(1) null 
/*�Ƿ�ɢ��*/,
vcreditnum varchar2(20) null 
/*����֤��*/,
cfreecustid varchar2(20) null 
/*ɢ��*/,
billmaker varchar2(20) null 
/*�Ƶ���*/,
creationtime char(19) null 
/*����ʱ��*/,
approver varchar2(20) null 
/*������*/,
taudittime varchar2(19) null 
/*�������*/,
modifiedtime char(19) null 
/*�޸�ʱ��*/,
fstatusflag smallint null 
/*����״̬*/,
vnote varchar2(181) null 
/*��ע*/,
boutendflag char(1) null 
/*����رձ��*/,
binvoicendflag char(1) null 
/*��Ʊ�رձ��*/,
bcostsettleflag char(1) null 
/*�ɱ�����رձ��*/,
bsendendflag char(1) null 
/*�����رձ��*/,
npreceiverate number(28,8) null 
/*�����տ����*/,
npreceivequota number(28,8) null 
/*�����տ��޶�*/,
bpreceiveflag char(1) null 
/*�տ��޶����Ԥ��*/,
npreceivemny number(28,8) null 
/*ʵ��Ԥ�տ���*/,
nreceivedmny number(28,8) null 
/*ʵ���տ���*/,
iprintcount integer null 
/*��ӡ����*/,
ntotalorigmny number(28,8) null 
/*���ϼ�*/,
ntotalorigsubmny number(28,8) null 
/*��ֽ��*/,
boffsetflag char(1) null 
/*�Ƿ���*/,
bcooptopoflag char(1) null 
/*�Ƿ���Эͬ���ɲɹ�����*/,
bpocooptomeflag char(1) null 
/*�Ƿ��ɲɹ�����Эͬ����*/,
vcooppohcode varchar2(40) null 
/*�Է�������*/,
iversion integer null 
/*�޶��汾��*/,
trevisetime char(19) null 
/*�޶�ʱ��*/,
creviserid varchar2(20) null 
/*�޶���*/,
cbalancetypeid varchar2(20) null 
/*���㷽ʽ*/,
cchanneltypeid varchar2(20) null 
/*������������*/,
ntotalnum number(28,8) null 
/*�ϼ�����*/,
ntotalweight number(28,8) null 
/*�ϼ�����*/,
ntotalvolume number(28,8) null 
/*�ϼ����*/,
vdef1 varchar2(101) null 
/*�Զ�����1*/,
vdef2 varchar2(101) null 
/*�Զ�����2*/,
vdef3 varchar2(101) null 
/*�Զ�����3*/,
vdef4 varchar2(101) null 
/*�Զ�����4*/,
vdef5 varchar2(101) null 
/*�Զ�����5*/,
vdef6 varchar2(101) null 
/*�Զ�����6*/,
vdef7 varchar2(101) null 
/*�Զ�����7*/,
vdef8 varchar2(101) null 
/*�Զ�����8*/,
vdef9 varchar2(101) null 
/*�Զ�����9*/,
vdef10 varchar2(101) null 
/*�Զ�����10*/,
vdef11 varchar2(101) null 
/*�Զ�����11*/,
vdef12 varchar2(101) null 
/*�Զ�����12*/,
vdef13 varchar2(101) null 
/*�Զ�����13*/,
vdef14 varchar2(101) null 
/*�Զ�����14*/,
vdef15 varchar2(101) null 
/*�Զ�����15*/,
vdef16 varchar2(101) null 
/*�Զ�����16*/,
vdef17 varchar2(101) null 
/*�Զ�����17*/,
vdef18 varchar2(101) null 
/*�Զ�����18*/,
vdef19 varchar2(101) null 
/*�Զ�����19*/,
vdef20 varchar2(101) null 
/*�Զ�����20*/,
modifier varchar2(20) null 
/*�޸���*/,
pk_org_v varchar2(20) null 
/*������֯���°汾*/,
cdeptvid varchar2(20) null 
/*�������°汾*/,
barsettleflag char(1) null 
/*�������رձ��*/,
creator char(20) null 
/*������*/,
ntotalpiece number(28,8) null 
/*�ܼ���*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
fpfstatusflag smallint null 
/*������״̬*/,
ctradewordid varchar2(20) null 
/*ó������*/,
chistrantypeid varchar2(20) null 
/*�޶���������*/,
vhistrantypecode varchar2(20) null 
/*�޶��������ͱ���*/,
carsubtypeid varchar2(20) null 
/*��Ʒ�Ҹ�����*/,
chreceivecustid varchar2(20) null 
/*�ջ��ͻ�*/,
chreceiveaddid varchar2(20) null 
/*�ջ���ַ*/,
vbillsrctype varchar2(20) null 
/*������Դ��������*/,
cbillsrcid varchar2(20) null 
/*������Դ����ID*/,
nlrgtotalorigmny number(28,8) null 
/*��Ʒ��˰�ϼ�*/,
 constraint pk_so_orderhist primary key (corderhistoryid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۶����޶����� */
create table so_orderhistory_b (corderhistorybid char(20) not null 
/*���۶����޶�����ID*/,
corderhistoryid varchar2(20) not null 
/*���۶����޶�����ID*/,
csaleorderid varchar2(20) null 
/*���۶�������*/,
csaleorderbid char(20) not null 
/*���۶�������*/,
pk_group varchar2(20) null 
/*����*/,
pk_org varchar2(20) null 
/*������֯*/,
dbilldate char(19) null 
/*��������*/,
crowno varchar2(20) null 
/*�к�*/,
ccustmaterialid varchar2(20) null 
/*�ͻ�������*/,
cmaterialvid varchar2(20) null 
/*����*/,
cproductorid varchar2(20) null 
/*��������*/,
cmaterialid varchar2(20) null 
/*�������°汾*/,
cvendorid varchar2(20) null 
/*��Ӧ��*/,
cfactoryid varchar2(20) null 
/*����*/,
cqualitylevelid varchar2(20) null 
/*�����ȼ�*/,
cunitid varchar2(20) null 
/*����λ*/,
cprojectid varchar2(20) null 
/*��Ŀ*/,
castunitid varchar2(20) null 
/*��λ*/,
vchangerate varchar2(60) null 
/*������*/,
nnum number(28,8) null 
/*������*/,
nastnum number(28,8) null 
/*����*/,
cqtunitid varchar2(20) null 
/*���۵�λ*/,
vqtunitrate varchar2(60) null 
/*���۵�λ������*/,
nqtunitnum number(28,8) null 
/*���۵�λ����*/,
ntaxrate number(28,8) null 
/*˰��*/,
nitemdiscountrate number(28,8) null 
/*��Ʒ�ۿ�*/,
ndiscountrate number(28,8) null 
/*�����ۿ�*/,
ccurrencyid varchar2(20) null 
/*��λ��*/,
nexchangerate number(28,8) null 
/*�۱�����*/,
nqtorigtaxprice number(28,8) null 
/*��˰����*/,
nqtorigprice number(28,8) null 
/*��˰����*/,
nqtorigtaxnetprc number(28,8) null 
/*��˰����*/,
nqtorignetprice number(28,8) null 
/*��˰����*/,
norigprice number(28,8) null 
/*����˰����*/,
norigtaxprice number(28,8) null 
/*����˰����*/,
norignetprice number(28,8) null 
/*����˰����*/,
norigtaxnetprice number(28,8) null 
/*����˰����*/,
norigmny number(28,8) null 
/*��˰���*/,
norigtaxmny number(28,8) null 
/*��˰�ϼ�*/,
norigdiscount number(28,8) null 
/*�ۿ۶�*/,
nqttaxnetprice number(28,8) null 
/*���Һ�˰����*/,
nqtnetprice number(28,8) null 
/*������˰����*/,
nqttaxprice number(28,8) null 
/*���Һ�˰����*/,
nqtprice number(28,8) null 
/*������˰����*/,
nprice number(28,8) null 
/*������??˰����*/,
ntaxprice number(28,8) null 
/*�����Һ�˰����*/,
nnetprice number(28,8) null 
/*��������˰����*/,
ntaxnetprice number(28,8) null 
/*�����Һ�˰����*/,
ntax number(28,8) null 
/*����˰��*/,
nmny number(28,8) null 
/*������˰���*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ndiscount number(28,8) null 
/*�����ۿ۶�*/,
nweight number(28,8) null 
/*����*/,
nvolume number(28,8) null 
/*���*/,
npiece number(28,8) null 
/*����*/,
ngroupexchgrate number(28,8) null 
/*���ű�λ�һ���*/,
ngroupmny number(28,8) null 
/*���ű�����˰���*/,
ngrouptaxmny number(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
nglobalexchgrate number(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
nglobalmny number(28,8) null 
/*ȫ�ֱ�����˰���*/,
nglobaltaxmny number(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
naskqtorigtaxprc number(28,8) null 
/*ѯ��ԭ�Һ�˰����*/,
naskqtorigprice number(28,8) null 
/*ѯ��ԭ����˰����*/,
naskqtorigtxntprc number(28,8) null 
/*ѯ��ԭ�Һ�˰����*/,
naskqtorignetprice number(28,8) null 
/*ѯ��ԭ����˰����*/,
cpricepolicyid varchar2(20) null 
/*�۸�����*/,
cpriceitemid varchar2(20) null 
/*�۸���Ŀ*/,
cpriceitemtableid varchar2(20) null 
/*��Ŀ��*/,
cpriceformid varchar2(20) null 
/*�۸����*/,
blargessflag char(1) null 
/*�Ƿ���Ʒ*/,
cprodlineid varchar2(20) null 
/*��Ʒ��*/,
blaborflag char(1) null 
/*����������*/,
bdiscountflag char(1) null 
/*�ۿ�������*/,
vbatchcode varchar2(40) null 
/*����*/,
pk_batchcode varchar2(20) null 
/*���ε���*/,
dsenddate char(19) null 
/*�ƻ���������*/,
dreceivedate char(19) null 
/*Ҫ�󵽻�����*/,
creceivecustid varchar2(20) null 
/*�ջ��ͻ�*/,
creceiveareaid varchar2(20) null 
/*�ջ�����*/,
creceiveaddrid varchar2(20) null 
/*�ջ���ַ*/,
creceiveadddocid varchar2(20) null 
/*�ջ��ص�*/,
csendstockorgvid varchar2(20) null 
/*���������֯*/,
csendstockorgid varchar2(20) null 
/*���������֯���°汾*/,
csendstordocid varchar2(20) null 
/*�����ֿ�*/,
csettleorgvid varchar2(20) null 
/*���������֯*/,
csettleorgid varchar2(20) null 
/*���������֯���������֯*/,
carorgvid varchar2(20) null 
/*Ӧ����֯*/,
carorgid varchar2(20) null 
/*Ӧ����֯���°汾*/,
ctrafficorgvid varchar2(20) null 
/*������֯*/,
ctrafficorgid varchar2(20) null 
/*������֯���°汾*/,
cprofitcentervid varchar2(20) null 
/*��������*/,
cprofitcenterid varchar2(20) null 
/*�����������°汾*/,
bbindflag char(1) null 
/*�Ƿ�������*/,
clargesssrcid varchar2(20) null 
/*��Ʒ�ж�Ӧ��Դ������ID*/,
cbindsrcid varchar2(20) null 
/*�������Ӧ��Դ������ID*/,
flargesstypeflag integer null 
/*��Ʒ�۸��̯��ʽ*/,
nlargessmny number(28,8) null 
/*��Ʒ�۸��̯ǰ��˰���*/,
nlargesstaxmny number(28,8) null 
/*��Ʒ�۸��̯ǰ��˰�ϼ�*/,
vbrevisereason varchar2(181) null 
/*�޶�����*/,
cretreasonid varchar2(20) null 
/*�˻�ԭ��ID*/,
vreturnmode varchar2(181) null 
/*�˻����δ���ʽ*/,
cretpolicyid varchar2(20) null 
/*�˻�����ID*/,
barrangedflag char(1) null 
/*�Ƿ��Դ�������*/,
carrangepersonid varchar2(20) null 
/*����Դ������*/,
tlastarrangetime char(19) null 
/*����Դ����ʱ��*/,
vclosereason varchar2(181) null 
/*�ر�ԭ��*/,
cctmanageid varchar2(20) null 
/*��ͬID*/,
cctmanagebid varchar2(20) null 
/*��ͬ����ID*/,
vctcode varchar2(40) null 
/*���ۺ�ͬ��*/,
vfirsttype varchar2(20) null 
/*Դͷ��������*/,
vfirsttrantype varchar2(20) null 
/*Դͷ��������*/,
vfirstcode varchar2(40) null 
/*Դͷ���ݺ�*/,
cfirstid varchar2(20) null 
/*Դͷ������������*/,
cfirstbid varchar2(20) null 
/*Դͷ���ݱ�������*/,
vfirstrowno varchar2(20) null 
/*Դͷ�����к�*/,
vsrctype varchar2(20) null 
/*��Դ��������*/,
vsrctrantype varchar2(20) null 
/*��??��������*/,
csrcid varchar2(20) null 
/*��Դ��������*/,
csrcbid varchar2(20) null 
/*��Դ���ݸ���*/,
vsrccode varchar2(40) null 
/*��Դ���ݺ�*/,
vsrcrowno varchar2(20) null 
/*��Դ�����к�*/,
fretexchange integer null 
/*�˻������*/,
cexchangesrcretid varchar2(20) null 
/*������Դ����*/,
bjczxsflag char(1) null 
/*���ת����*/,
vfree1 varchar2(101) null 
/*���ɸ�������1*/,
vfree2 varchar2(101) null 
/*���ɸ�������2*/,
vfree3 varchar2(101) null 
/*���ɸ�������3*/,
vfree4 varchar2(101) null 
/*���ɸ�������4*/,
vfree5 varchar2(101) null 
/*���ɸ�������5*/,
vfree6 varchar2(101) null 
/*���ɸ�������6*/,
vfree7 varchar2(101) null 
/*���ɸ�������7*/,
vfree8 varchar2(101) null 
/*����???������8*/,
vfree9 varchar2(101) null 
/*���ɸ�������9*/,
vfree10 varchar2(101) null 
/*���ɸ�������10*/,
vbdef1 varchar2(101) null 
/*�Զ�����1*/,
vbdef2 varchar2(101) null 
/*�Զ�����2*/,
vbdef3 varchar2(101) null 
/*�Զ�����3*/,
vbdef4 varchar2(101) null 
/*�Զ�����4*/,
vbdef5 varchar2(101) null 
/*�Զ�����5*/,
vbdef6 varchar2(101) null 
/*�Զ�����6*/,
vbdef7 varchar2(101) null 
/*�Զ�����7*/,
vbdef8 varchar2(101) null 
/*�Զ�����8*/,
vbdef9 varchar2(101) null 
/*�Զ�����9*/,
vbdef10 varchar2(101) null 
/*�Զ�����10*/,
vbdef11 varchar2(101) null 
/*�Զ�����11*/,
vbdef12 varchar2(101) null 
/*�Զ�����12*/,
vbdef13 varchar2(101) null 
/*�Զ�����13*/,
vbdef14 varchar2(101) null 
/*�Զ�����14*/,
vbdef15 varchar2(101) null 
/*�Զ�����15*/,
vbdef16 varchar2(101) null 
/*�Զ�����16*/,
vbdef17 varchar2(101) null 
/*�Զ�����17*/,
vbdef18 varchar2(101) null 
/*�Զ�???��18*/,
vbdef19 varchar2(101) null 
/*�Զ�����19*/,
vbdef20 varchar2(101) null 
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
vrownote varchar2(181) null 
/*�б�ע*/,
crececountryid varchar2(20) null 
/*�ջ�����/����*/,
csendcountryid varchar2(20) null 
/*��������/����*/,
ctaxcountryid varchar2(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
ctaxcodeid varchar2(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny number(28,8) null 
/*��˰���*/,
corigcountryid varchar2(20) null 
/*ԭ����*/,
corigareaid varchar2(20) null 
/*ԭ������*/,
cbuypromottypeid varchar2(20) null 
/*������������*/,
cprcpromottypeid varchar2(20) null 
/*ѯ�۴�������*/,
vcustombillcode varchar2(40) null 
/*�ͻ�������*/,
cbuylargessactid varchar2(20) null 
/*�����*/,
cpricepromtactid varchar2(20) null 
/*�۸�����*/,
cbuylargessid varchar2(20) null 
/*��������*/,
csprofitcentervid varchar2(20) null 
/*������������*/,
csprofitcenterid varchar2(20) null 
/*���������������°汾*/,
cmffileid varchar2(20) null 
/*������*/,
nmffileprice number(28,8) null 
/*������*/,
cpromotpriceid varchar2(20) null 
/*�����۸����*/,
blrgcashflag char(1) null 
/*��Ʒ�Ҹ�*/,
naccprice number(28,8) null 
/*�����˵���*/,
 constraint pk_so_orderhist_b primary key (corderhistorybid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۶����ҵĶ���ת���� */
create table so_mb_myorder (csaleorderid char(20) not null 
/*������ʵ��*/,
pk_group varchar2(20) null 
/*����*/,
dbilldate char(19) null 
/*��������*/,
pk_org varchar2(20) null 
/*������֯*/,
vbillcode varchar2(40) null 
/*������*/,
ccustomerid varchar2(20) null 
/*�ͻ�*/,
ntotalorigmny number(28,8) null 
/*��˰�ϼ�*/,
cemployeeid varchar2(20) null 
/*ҵ��Ա*/,
 constraint pk_so_mb_myorder primary key (csaleorderid),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���۶����ƶ������� */
create table so_mb_orderanaly (pk_group varchar2(20) null 
/*����*/,
dbilldate char(19) null 
/*��������*/,
pk_org varchar2(20) null 
/*������֯*/,
vbillcode varchar2(40) null 
/*������*/,
ccustomerid varchar2(20) null 
/*�ͻ�*/,
cdeptid varchar2(20) null 
/*����*/,
cemployeeid varchar2(20) null 
/*ҵ��Ա*/,
cchanneltypeid varchar2(20) null 
/*��������*/,
cmaterialid varchar2(20) null 
/*����*/,
cprodlineid varchar2(20) null 
/*��Ʒ��*/,
cbrandid varchar2(20) null 
/*Ʒ��*/,
nnum number(28,8) null 
/*����������*/,
norigtaxmny number(28,8) null 
/*��˰�ϼ�*/,
corigcurrencyid varchar2(20) null 
/*ԭ��*/,
ntaxmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ngrouptaxmny number(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
csaleorderid varchar2(20) null 
/*��������ID*/,
  ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

