select * from tbOrder;
sp_columns tbAdministor;
sp_columns tbCard;
sp_columns tbQuestion;
select * from tbQuestion;
sp_columns tbAskOrComplain;
select * from tbAskOrComplain;
--update tbAskOrComplain set ac_type=0 where ac_id = 2;
select * from tbCard;
select * from tbReply;
select * from tbQuestionError;
--delete from tbReply;
select * from tbRechargeRecord
select * from tbExamCategory where exam_pid=0;
update tbTeacher set tch_lessons = 'һ������ʦ' where tch_status = 1;
sp_columns tbOrder;
sp_columns tbSend;
sp_columns tbItems;
sp_columns tbExpressCompany
--alter table tbExpressCompany add epc_addr varchar(100) 
--insert into tbExpressCompany(epc_id,epc_name,epc_url,epc_phone,epc_orderId,epc_addr)
--values(1001,'˳��','www.xxxx.com','400123456',1,'����ʡ��ɳ��');
--insert into tbExpressCompany(epc_id,epc_name,epc_url,epc_phone,epc_orderId,epc_addr)
--values(1002,'��ͨ','www.abcd.com','400123456',1,'����ʡ��ɳ��');
--insert into tbQuestion(exam_id,grade_id,class_id,question_source,question_title,question_content,question_status,stu_id,question_addTime)
--values(1001,1001,1001,0,'��ʵ�ⲻ��һ������','��ʵ�Ⲣ����һ�����⣡������',0,127,'2013-03-03');
--insert into tbQuestion(exam_id,grade_id,class_id,question_source,question_title,question_content,question_status,stu_id,question_addTime)
--values(1001,1001,1001,0,'��ʵ�ⲻ�Ƕ�������','��ʵ�Ⲣ���Ƕ������⣡������',1,127,'2013-03-05');
--alter table tbOrder drop column order_no;
sp_columns tbExamCategory;
sp_columns tbStudent;
--alter table tbStudent add stu_imgUrl varchar(300) default '../upload/userface/no_face.gif';
--update tbStudent set stu_imgUrl = '../upload/userface/no_face.gif' where stu_id = 127;
sp_columns tbAddress;
--insert into tbAddress(addr_receiveName,addr_fullAddress,addr_mobile,stu_id)
values('������','����һ�Ž�','13001020304',127);
select * from tbAddress;
select * from tbAddress where stu_id =127 and addr_isDefault = 1;
select top 10 i.* from tbOrder o,tbItems i where 1=1 and i.item_name like '%�ײ�%' and o.order_id = i.order_id; 
select * from tbStudent;
--delete from tbAddress;
--����
CREATE    FUNCTION    idSequence()
RETURNS    INT
AS
BEGIN
    DECLARE    @MINNUM    INT
    DECLARE    @MAXNUM    INT
    SET    @MINNUM=1001   
    SET    @MAXNUM=1999999999
    RETURN(    SELECT CASE   
            WHEN    ISNULL(MAX(card_id),@MINNUM-1)+1 >@MAXNUM THEN    0
            ELSE    ISNULL(MAX(card_id),@MINNUM-1)+1
               END
        FROM tbCard)
END
--
if exists(
   select * from sys.objects 
   where name='tbCard'
     
) 
    drop table tbCard
/*==============================================================*/
/* Table: tbCard                                                */
/*==============================================================*/
create table tbCard 
(
	id					int								identity(1,1),
   card_id              int							   default dbo.sqlSequence(),
   card_password        varchar(50)                    not null,
   card_value           int                            not null,
   card_addTime         datetime                       not null,
   card_overTime        datetime                       not null,
   stu_id               bigint                         ,
   card_useTime         datetime                       ,
   card_isPresent       int                            default 0,--�Ƿ����͡�0:���ͣ�1:�͡�
   constraint PK_TBCARD primary key clustered (card_id)
);
--alter table tbCard alter column stu_id bigint;
--insert into tbCard(card_password,card_value,card_addTime,card_overTime)values('sXif73sD',50,'2013-01-01','2014-01-01');
select * from tbCard where card_addTime>'2013-01-03';
--delete from tbCard;
select top 15 * from tbCard where card_useTime is null order by card_addTime desc;

select * from tbCard order by card_id asc
select * from tbGrade
select * from tbStudent e join tbOrder o on e.stu_id = o.stu_id;
select * from tbStudent where stu_email = 123@qq.com
update tbStudent set stu_card = 4500 where stu_id =131;
select * from tbOrder where order_status in (1,2);
--update tbOrder set order_payType = '12.0:' where order_id=1015;
update tbOrder set order_payment =0 where order_id=1050;
update tbOrder set order_status = 1 where order_id = 1050
select * from tbItems;
select * from tbExamCategory;
select * from tbPackageCategory;
select * from tbTeacher where tch_username = '�š�Ƽ';
select * from tbClassPackage;
select * from tbAdministor where adm_id like '%10%';
select * from tbTeacher;
select * from tbAnswer;
select * from tbClassDetail;
select * from tbDeal;
select * from tbTrade;
select * from tbRechargeRecord;
delete from tbTrade where trade_id = 53
select * from tbSend;
select * from tbExamRecord;
update tbExamRecord set rcd_endTime='2013-05-17 08:06:23.000' where id =11;
select * from tbExamRule;
--delete from tbExamRecord;
select * from tbAddress;
--delete from tbAddress where addr_id >1004
select * from log4sccms;
--delete from tbQuestionCollect;
sp_columns tbItems;	alter table tbItems add item_present varchar(1000) default null;
sp_columns tbOrder;
sp_columns tbAdministor
sp_columns tbSend; 
sp_columns tbClassPackage;
sp_columns tbGrade;
sp_columns tbDeal;
sp_columns tbQuestion;
sp_columns tbTeacher
sp_columns tbRechargeRecord
sp_columns tbCard;
sp_columns tbStudent;
sp_columns tbAnswer;
sp_columns tbTeacher;
sp_columns tbTrade; 
sp_columns tbAskOrComplain; 
sp_columns log4sccms; 
--alter table tbTrade  ALTER COLUMN card_id varchar(50);
--alter table tbTrade add stu_id int;

SELECT * FROM sys.Tables




                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
--alter table tbRechargeRecord add rc_isPresent int default 0;

select * from tbOrder o join (select i.*,cp.deal_id from tbItems i join tbClassPackage cp on i.productId= cp.pkg_id where i.item_pType=0 and cp.deal_id is not null) dd on o.order_id = dd.order_id;

--insert into tbOrder(stu_id,order_money,order_invoice,order_status,order_addTime)
values(125,500.0,1,0,'2013-01-05');
--insert into tbOrder(order_id,order_no,stu_id,order_money,order_invoice,order_status,order_addTime)
values(2,'1000002',125,500.0,0,1,'2013-01-05');
--insert into tbOrder(order_id,order_no,stu_id,order_money,order_invoice,order_status,order_addTime)
values(3,'1000003',125,500.0,1,2,'2013-01-06');
--insert into tbOrder(order_id,order_no,stu_id,order_money,order_invoice,order_status,order_addTime)
values(4,'1000004',125,500.0,1,3,'2013-01-06');
--update tbOrder set order_payType ='200|300' where order_id=2;
--insert into tbItems(item_id,order_id,productId,item_oPrice,item_rPrice,item_name,item_pType)
values(1,1,1,500.00,300.00,'һ������ʦ��������ȫ��VIP�ײ� ',0);
--insert into tbItems(order_id,productId,item_oPrice,item_rPrice,item_name,item_pType)
values(20,1,500.00,200.00,'���蹤�̾��þ�����',1);
select * from tbItems;
--update tbOrder set order_payType ='200|300',order_payTime='2013-01-08' where order_id=2;
--update tbOrder set order_payType ='0|'  where order_id=1014;
--update tbOrder set order_status = 2 where order_id=3;
--update tbOrder set order_money = 500.0 where order_id=2;
--update tbOrder set order_payType ='200|300',order_payTime='2013-01-08',order_status=1 where order_id=2;
select * from tbOrder where 1=1 and order_id like '%3\%' or '1\%'='1%';
select * from tbOrder where 1=1and order_id=1;

select * from tbStudent where stu_id = 125;
--update tbStudent set stu_card=0,stu_cash=0 where stu_id =125;
--alter table tbSend add send_detail varchar(500);
--alter table tbSend add send_addTime datetime;
--alter table tbSend add send_cost float;
--alter table tbSend add epc_name varchar(30);
--alter table tbSend add send_content varchar(20);

--alter table tbItems add item_sPrice float;
--update tbItems set item_sPrice = 300 where item_id =1;
--update tbItems set item_sPrice = 200 where item_id =2;


--insert into tbSend(send_id,order_id,send_status,send_detail,send_addTime)
values(1,2,0,'��Ʊ��һ������ʦȫ�ƽ̲�','2012/12/28 12:00:00');
select * from tbSend;
--update tbSend set send_cost =20.0 where id = 7 
--delete from tbSend;

sp_columns tbQuestion;
--alter table tbQuestion alter column question_source int;
--alter table tbQuestion add question_path varchar(200);
--alter table tbQuestion drop column collectTitle;
sp_columns tbQuestionCollect;
--alter table tbQuestionCollect add collect_title varchar(200);
--alter table tbQuestionCollect drop column collect_title;


create table log4sccms
(
	id bigint identity(1,1) primary key,
	message varchar(200),
);
select * from log4sccms;
alter table tbTeacher alter column tch_id int default (dbo.sqlSequence());

if exists(select   *  from   sysobjects   where   name='ConstraintIdForTeacher')
begin
ALTER TABLE tbTeacher DROP CONSTRAINT ConstraintIdForTeacher
end
alter table tbTeacher add constraint ConstraintIdForTeacher default dbo.tchIdSequence() for tch_id;

if exists(select   *  from   sysobjects   where   name='tchIdSequence')
begin
drop function tchIdSequence
end
CREATE    FUNCTION    tchIdSequence()
RETURNS    INT
AS
BEGIN
    DECLARE    @MINNUM    INT
    DECLARE    @MAXNUM    INT
    SET    @MINNUM=1001   
    SET    @MAXNUM=1999999999
    RETURN(    SELECT CASE   
            WHEN    ISNULL(MAX(tch_id),@MINNUM-1)+1 >@MAXNUM THEN    0
            ELSE    ISNULL(MAX(tch_id),@MINNUM-1)+1
               END
        FROM tbTeacher)
END
alter table tbTeacher add constraint ConstraintIdForTeacher default dbo.tchIdSequence() for tch_id;
select * from tbTeacher;
delete from tbTeacher;

select * from tbItems i ,tbClassPackage cp where (i.productId = cp.pkg_id and i.item_pType=0); 
join tbGrade g on (i.item_id = g.grade_id and i.item_pType=1)    
select convert(varchar(10),getdate(),112);

---��ѯѧԱ�ѿ�ͨ�γ�
select cp.* from (select i.* from tbItems i join tbOrder o on i.order_id = o.order_id where o.stu_id =127 and o.order_status in (1,2,3)) temp,tbClassPackage cp where (temp.productId = cp.pkg_id and temp.item_pType=0);
select g.* from (select i.* from tbItems i join tbOrder o on i.order_id = o.order_id where o.stu_id =127 and o.order_status in (1,2,3)) temp,tbGrade g where (temp.productId = g.grade_id and temp.item_pType=1);
---��ѯ�༶���ڿ��Եĵڶ�������
select ec2.exam_name from (select ec.* from (select g.* from tbGrade g where g.grade_id = 1001) temp1 join tbExamCategory ec on temp1.exam_id = ec.exam_id) temp2 join tbExamCategory ec2 on temp2.exam_pid = ec2.exam_id;
----��ѯ�ײ����ڿ��Եĵڶ�������
select ec.exam_name from (select pc.* from tbClassPackage cp join tbPackageCategory pc on cp.pType_id = pc.pType_id where cp.pkg_id=1001)temp join tbExamCategory ec on temp.exam_id = ec.exam_id; 

select pc.* from (select cp.* from (select i.* from tbItems i join tbOrder o on i.order_id = o.order_id where o.stu_id =127 and o.order_status in (1,2,3)) temp,tbClassPackage cp where (temp.productId = cp.pkg_id and temp.item_pType=0)) temp2 join tbPackageCategory pc on pc.pType_id = temp2.pType_id
---��ѯѧԱ�ѿ�ͨ�ײͿγ̵Ŀ���С�� 
select distinct ec.* from tbExamCategory ec join (select pc.* from (select cp.* from (select i.* from tbItems i join tbOrder o on i.order_id = o.order_id where o.stu_id =127 and o.order_status in (1,2,3)) temp,tbClassPackage cp where (temp.productId = cp.pkg_id and temp.item_pType=0)) temp2 join tbPackageCategory pc on pc.pType_id = temp2.pType_id
) pc2 on ec.exam_id = pc2.exam_id ;
---
select distinct ec.* from tbExamCategory ec join (select g.* from (select i.* from tbItems i join tbOrder o on i.order_id = o.order_id where o.stu_id =127 and o.order_status in (1,2,3)) temp,tbGrade g where (temp.productId = g.grade_id and temp.item_pType=1))temp2 on ec.exam_id = temp2.exam_id ;
---��ѯ�������༶

----��ѯĳѧԱ���ղص�����
select * from (select q.* ,row_number()over(order by q.question_addTime desc)as rank from tbQuestion q join tbQuestionCollect qc on q.question_id = qc.question_id where qc.stu_id = 127)as lala where lala.rank between 1 and 10  

------
select i.productId from tbItems i group by i.productId order by count(i.productId) desc




----------------------------------------------------------
if exists(select   *  from   sysobjects   where   name='tbAskOrComplain')
begin
drop table tbAskOrComplain
end
create table tbAskOrComplain(
id int identity(1,1),
ac_id int primary key default dbo.acIdSequence(),
grade_id int,
ac_name varchar(100) not null,--��ѯ����Ͷ�ߵ�����
ac_type int not null,--0��ʾ��ѯ��1��ʾͶ��
ac_status int  default 0 not null,--0��ʾδ�����1��ʾ�ѽ�����ѻظ�
ac_addTime datetime not null,--���ʱ��
ac_content text not null --����
);
if exists(select   *  from   sysobjects   where   name='tbReply')
begin
drop table tbReply
end
create table tbReply(
id int identity(1,1),
reply_id int primary key default dbo.replyIdSequence(),
ac_id int,
reply_content text not null,
reply_addTime datetime not null,
reply_people varchar(20)
);
alter table tbReply
   add constraint FK_TBREPLY_REFERENCE_TBASKORCOMPLAIN foreign key (ac_id)
      references tbAskOrComplain;
alter table tbAskOrComplain add stu_id int not null;

--------------------------------------------------------------------------
if (exists (select * from sys.objects where name = 'proc_get_exam_from_package'))
begin
drop procedure proc_get_exam_from_package
end
create procedure proc_get_exam_from_package
@stuId int
as
    select distinct ec.* from tbExamCategory ec join (select pc.* from (select cp.* from (select i.* from tbItems i join tbOrder o on i.order_id = o.order_id where o.stu_id =@stuId and o.order_status in (1,2,3)) temp,tbClassPackage cp where (temp.productId = cp.pkg_id and temp.item_pType=0)) temp2 join tbPackageCategory pc on pc.pType_id = temp2.pType_id) pc2 on ec.exam_id = pc2.exam_id
execute proc_get_exam_of_student 127;
-----------------------------------------------------
if (exists (select * from sys.objects where name = 'proc_get_exam_from_grade'))
begin
drop procedure proc_get_exam_from_grade
end
create procedure proc_get_exam_from_grade
@stuId int
as
select distinct ec.* from tbExamCategory ec join (select g.* from (select i.* from tbItems i join tbOrder o on i.order_id = o.order_id where o.stu_id =@stuId and o.order_status in (1,2,3)) temp,tbGrade g where (temp.productId = g.grade_id and temp.item_pType=1))temp2 on ec.exam_id = temp2.exam_id 
execute proc_get_exam_from_grade 127;
-----------------------------------------------------------------------
if (exists (select * from sys.objects where name = 'proc_get_package_by_stuId'))
begin
drop procedure proc_get_package_by_stuId
end
create procedure proc_get_package_by_stuId
@stuId int
as
select cp.* from (select i.* from tbItems i join tbOrder o on i.order_id = o.order_id where o.stu_id =@stuId and o.order_status in (1,2,3)) temp,tbClassPackage cp where (temp.productId = cp.pkg_id and temp.item_pType=0);
execute proc_get_package_by_stuId 127;
----------------------------------------------------------------------------------
if (exists (select * from sys.objects where name = 'proc_get_grade_by_stuId'))
begin
drop procedure proc_get_grade_by_stuId
end
create procedure proc_get_grade_by_stuId
@stuId int
as
select g.* from (select i.* from tbItems i join tbOrder o on i.order_id = o.order_id where o.stu_id =@stuId and o.order_status in (1,2,3)) temp,tbGrade g where (temp.productId = g.grade_id and temp.item_pType=1);
----------------------------------------------------------------------------------------
--int page,int pagesize,String sortname,String sortorder,
if (exists (select * from sys.objects where name = 'proc_get_question_from_collect'))
begin
drop procedure proc_get_question_from_collect
end
create procedure proc_get_question_from_collect
@stuId int,
@page int,
@pagesize int,
@sortname varchar(30),
@sortorder varchar(6)
as
select * from (select q.* ,row_number()over(order by (@sortname+' '+@sortorder))as rank from tbQuestion q join tbQuestionCollect qc on q.question_id = qc.question_id where qc.stu_id = @stuId)as lala where lala.rank between (@page-1)*@pagesize and @page*@pagesize
if (exists (select * from sys.objects where name = 'proc_getTotalOfQuestion_from_collect'))
begin
drop procedure proc_getTotalOfQuestion_from_collect
end  
create procedure proc_getTotalOfQuestion_from_collect
@stuId int
as 
select count(*) from tbQuestion q join tbQuestionCollect qc on q.question_id = qc.question_id where qc.stu_id = @stuId

--exec proc_getTotalOfQuestion_from_collect 127;
------------------------------------------------------------------------------------------------------------------
if (exists (select * from sys.objects where name = 'proc_getSecondExamNameOfPkg'))
begin
drop procedure proc_getSecondExamNameOfPkg
end  

create procedure proc_getSecondExamNameOfPkg
@pkgId int
as 
select ec.exam_name from (select pc.* from tbClassPackage cp join tbPackageCategory pc on cp.pType_id = pc.pType_id where cp.pkg_id=@pkgId)temp join tbExamCategory ec on temp.exam_id = ec.exam_id; 

-------------------------------------------------------------------------------------------------------------------
if (exists (select * from sys.objects where name = 'proc_getSecondExamNameOfGrade'))
begin
drop procedure proc_getSecondExamNameOfGrade
end  

create procedure proc_getSecondExamNameOfGrade
@gradeId int
as 
select ec2.exam_name from (select ec.* from (select g.* from tbGrade g where g.grade_id = @gradeId) temp1 join tbExamCategory ec on temp1.exam_id = ec.exam_id) temp2 join tbExamCategory ec2 on temp2.exam_pid = ec2.exam_id;
--------------------------------------------------------------------------------------------------------------------------------------------------------
if (exists (select * from sys.objects where name = 'proc_get_distinct_order_by_stuId'))
begin
drop procedure proc_get_distinct_order_by_stuId
end
create procedure proc_get_distinct_order_by_stuId
@stuId int,
@page int,
@pagesize int,
@keywords varchar(100),
@sortname varchar(30),
@sortorder varchar(6)
as
	select * from (
    select ROW_NUMBER() OVER(
        order by ('o2.'+@sortname +' '+@sortorder)) as pos, 
        o2.*
        from
         (select distinct o1.* from
            tbOrder o1, tbItems i
            where o1.order_id  = i.order_id and and o1.order_status in o1.stu_id =127 and i.item_name like '%'+@keywords+'%'
        ) o2) sp where sp.pos between (@page-1)*@pagesize+1 and @page*@pagesize

exec proc_get_distinct_order_by_stuId 127,2,10,'һ������','order_addTime','desc';

select rank from(select ROW_NUMBER() OVER(order by er.rcd_Score desc) as rank,er.* from tbExamRecord er where er.paper_id = 1003)as er1 where er1.stu_id=127