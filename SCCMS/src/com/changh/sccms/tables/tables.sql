CREATE    FUNCTION    admIdSequence()
RETURNS    INT
AS
BEGIN
    DECLARE    @MINNUM    INT
    DECLARE    @MAXNUM    INT
    SET    @MINNUM=1001   
    SET    @MAXNUM=1999999999
    RETURN(    SELECT CASE   
            WHEN    ISNULL(MAX(adm_id),@MINNUM-1)+1 >@MAXNUM THEN    0
            ELSE    ISNULL(MAX(adm_id),@MINNUM-1)+1
               END
        FROM tbAdministor)
END;

if exists(select   *  from   sysobjects   where   name='ConstraintIdForAdmin')
begin
ALTER TABLE tbAdministor DROP CONSTRAINT ConstraintIdForAdmin
end;
alter table tbAdministor add constraint ConstraintIdForAdmin default dbo.admIdSequence() for adm_id;
--------------------------------------------
CREATE    FUNCTION    tradeIdSequence()
RETURNS    INT
AS
BEGIN
    DECLARE    @MINNUM    INT
    DECLARE    @MAXNUM    INT
    SET    @MINNUM=1   
    SET    @MAXNUM=1999999999
    RETURN(    SELECT CASE   
            WHEN    ISNULL(MAX(trade_id),@MINNUM-1)+1 >@MAXNUM THEN    0
            ELSE    ISNULL(MAX(trade_id),@MINNUM-1)+1
               END
        FROM tbTrade)
END;
if exists(select * from sysobjects where name='tbTrade')
begin
drop table tbTrade
end;
--��������ʱ�䡢���׽��������͡�����IP�����׶����š���ֵ����(��ѧϰ������д���� ��--�����)
create table tbTrade(
	id 	int  identity(1,1),
	trade_id int primary key default dbo.tradeIdSequence(),
	trade_time datetime not null,
	trade_money float not null,
	trade_ip varchar(20),
	order_id int ,
	card_id int,
	trade_type int, 
	trade_note varchar(1000) 
);
--------------------------------------------------------------
CREATE    FUNCTION    sendIdSequence()
RETURNS    INT
AS
BEGIN
    DECLARE    @MINNUM    INT
    DECLARE    @MAXNUM    INT
    SET    @MINNUM=1001   
    SET    @MAXNUM=1999999999
    RETURN(    SELECT CASE   
            WHEN    ISNULL(MAX(send_id),@MINNUM-1)+1 >@MAXNUM THEN    0
            ELSE    ISNULL(MAX(send_id),@MINNUM-1)+1
               END
        FROM tbSend)
END;
if exists(select   *  from   sysobjects   where   name='ConstraintIdForSend')
begin
ALTER TABLE tbSend DROP CONSTRAINT ConstraintIdForSend
end;
alter table tbSend add constraint ConstraintIdForSend default dbo.sendIdSequence() for send_id;
------------------------------------------------------------------------------------
CREATE    FUNCTION    epcIdSequence()
RETURNS    INT
AS
BEGIN
    DECLARE    @MINNUM    INT
    DECLARE    @MAXNUM    INT
    SET    @MINNUM=1001   
    SET    @MAXNUM=1999999999
    RETURN(    SELECT CASE   
            WHEN    ISNULL(MAX(epc_id),@MINNUM-1)+1 >@MAXNUM THEN    0
            ELSE    ISNULL(MAX(epc_id),@MINNUM-1)+1
               END
        FROM tbExpressCompany)
END;
if exists(select   *  from   sysobjects   where   name='ConstraintIdForEPC')
begin
ALTER TABLE tbExpressCompany DROP CONSTRAINT ConstraintIdForEPC
end;
alter table tbExpressCompany add constraint ConstraintIdForEPC default dbo.epcIdSequence() for epc_id;

-----------------------------------------------------------------------------------------------------------------
CREATE    FUNCTION    paperIdSequence()
RETURNS    INT
AS
BEGIN
    DECLARE    @MINNUM    INT
    DECLARE    @MAXNUM    INT
    SET    @MINNUM=1001   
    SET    @MAXNUM=1999999999
    RETURN(    SELECT CASE   
            WHEN    ISNULL(MAX(paper_id),@MINNUM-1)+1 >@MAXNUM THEN    0
            ELSE    ISNULL(MAX(paper_id),@MINNUM-1)+1
               END
        FROM tbExamPaper)
END;
if exists(select   *  from   sysobjects   where   name='ConstraintIdForExamPaper')
begin
ALTER TABLE tbExamPaper DROP CONSTRAINT ConstraintIdForExamPaper
end;
alter table tbExamPaper add constraint ConstraintIdForExamPaper default dbo.paperIdSequence() for paper_id;
-------

IF EXISTS(SELECT * FROM sys.objects WHERE name='tbExamPaper') 
DROP TABLE tbExamPaper 
create table tbExamPaper(
	id int identity(1,1),
	paper_id  int default dbo.paperIdSequence() primary key, --����
	paper_examId int,	--�Ծ��������Ե�С��
	paper_e_gradeId int,--�������������µİ༶id
	paper_e_g_cheaterId int,--�ð༶�µ��½ں�
	paper_name varchar(256) not null, --�Ծ�����
	paper_time int not null,--�����Ӵ�����
	paper_score int not null,--�Ծ��ܷ�
	paper_addTime datetime not null,--�Ծ����ʱ��
	paper_type int not null,--�Ծ����� 1����ϰ 2 ���⣬ 3 ģ���⣬4 ���
	paper_clickNum int, --�ο�����
	paper_isChecked int,--�Ƿ���ˣ�0-û����ˣ�1-����ˣ�
	paper_editor varchar(30)--�༭
);
alter table tbExamPaper add paper_linkName varchar(200);
select * from tbExamPaper;
---------------------------------------------------------------------------------------------------
CREATE    FUNCTION    ruleIdSequence()
RETURNS    INT
AS
BEGIN
    DECLARE    @MINNUM    INT
    DECLARE    @MAXNUM    INT
    SET    @MINNUM=1001   
    SET    @MAXNUM=1999999999
    RETURN(    SELECT CASE   
            WHEN    ISNULL(MAX(rule_id),@MINNUM-1)+1 >@MAXNUM THEN    0
            ELSE    ISNULL(MAX(rule_id),@MINNUM-1)+1
               END
        FROM tbExamRule)
END;
if exists(select   *  from   sysobjects   where   name='ConstraintIdForExamRule')
begin
ALTER TABLE tbExamRule DROP CONSTRAINT ConstraintIdForExamRule
end;
alter table tbExamRule add constraint ConstraintIdForExamRule default dbo.ruleIdSequence() for rule_id;
-------------
IF EXISTS(SELECT * FROM sys.objects WHERE name='tbExamRule') 
DROP TABLE tbExamRule 
create table tbExamRule(
	id int identity(1,1),
	rule_id int default dbo.ruleIdSequence() primary key ,	--����
	paper_Id int not null ,	--�Ծ�id�������һ���Ծ���1��������⡿
	rule_title varchar(500),	--�������
	rule_idInPaper int,			--���ڸ��Ծ�ڼ�����
	rule_type int,				--�������� ��ʱ���֣�1����ѡ 2 ��ѡ 3 �ж� 4 �ʴ�
	rule_questionNum int,		--��������
	rule_scoreForEach float,	--ÿ�����
	rule_actualAddNum int,		--ʵ�������
	rule_scoreSet varchar(10),	--��ѡ�۷�
	FOREIGN KEY(paper_id) REFERENCES tbExamPaper(paper_id)
);
select * from tbExamRule;
------------------------------------------------------------------------------------------------
CREATE    FUNCTION    examQuestionIdSequence()
RETURNS    INT
AS
BEGIN
    DECLARE    @MINNUM    INT
    DECLARE    @MAXNUM    INT
    SET    @MINNUM=1001   
    SET    @MAXNUM=1999999999
    RETURN(    SELECT CASE   
            WHEN    ISNULL(MAX(quest_id),@MINNUM-1)+1 >@MAXNUM THEN    0
            ELSE    ISNULL(MAX(quest_id),@MINNUM-1)+1
               END
        FROM tbExamQuestion)
END;
if exists(select   *  from   sysobjects   where   name='ConstraintIdForExamQuestion')
begin
ALTER TABLE tbExamQuestion DROP CONSTRAINT ConstraintIdForExamQuestion
end;
alter table tbExamQuestion add constraint ConstraintIdForExamQuestion default dbo.examQuestionIdSequence() for quest_id;
-------------
IF EXISTS(SELECT * FROM sys.objects WHERE name='tbExamQuestion') 
DROP TABLE tbExamQuestion
create table tbExamQuestion(
	id int identity(1,1),
	quest_id int default dbo.examQuestionIdSequence() primary key,--����
	quest_ruleId int,--��������id
	quest_paperId int,--�����Ծ�id
	quest_examId int,--��������id
	quest_content text,--�������ݣ���ѡ��
	quest_answer text,--�����
	quest_analysis text,--����𰸽���
	quest_type int,--�������͡�1 ��ѡ ��������
	quest_optionNum int,--ѡ���������ѡ��������νѡ��
	quest_orderId int,--������ţ���������������ţ���
	quest_addTime datetime,--�������ʱ��
	quest_linkQuestionId int,--����id
	quest_editor varchar(30),--�༭
	quest_clickNum int ,--�������
	quest_errorNum int,--�������
	quest_md5code varchar(64)--�����md5�����ַ�����ȥ��������š�
);
alter table tbExamQuestion alter column quest_linkQuestionId varchar(30);
--------------------
--���������б� ״̬ ����ʱ�� �÷�/�ܷ� ����ʱ�䣨��ʼʱ�䣬����ʱ�䣩 ��
create table tbExamRecord(
id int identity(1,1) primary key,
paper_id int,
paper_name varchar(300),
stu_id int,
rcd_stauts int,
rcd_score float,
rcd_startTime datetime,
rcd_endTime datetime,
rcd_answers text
);
alter table tbExamRecord add rcd_scoreForEachRule varchar(500);
alter table tbExamRecord alter column rcd_score float;
alter table tbExamRecord drop column paperScore;
alter table tbExamRecord add paper_score float;
alter table tbExamRecord add stu_userName varchar(50);
alter table tbExamRecord add rcd_scoreForEachQuestion text;
alter table tbExamRecord add rcd_tempAnswer text;
alter table tbExamRecord add rcd_testNum int;
alter table tbExamCategory add parentsId varchar(200);
--�����
create table tbQuestionError(
id int identity(1,1) primary key,
paper_id int,
quest_id int not null,
stu_userName varchar(50),--������
error_addTime datetime,
error_type int not null,
error_content varchar(500),--������Ϣ
error_status int not null,--״̬
error_dealTime datetime,--����ʱ��
error_dealPerson varchar(50)--������
)

select * from tbExamCategory where parentsId like '%1001%';