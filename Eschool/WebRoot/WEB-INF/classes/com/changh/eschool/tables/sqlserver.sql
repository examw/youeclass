alter table tbStudyRecord drop column record_endTime;ɾ�� ѧϰ��¼����ʱ��
delete from tbStudyRecord where id=1
alter table tbStudyRecord add record_name nvarchar(100);

//���������ʦ��

create table tbComment(
	id  int identity(1,1),
	comment_id int primary key,
	comment_content nvarchar(500),
	comment_score int,
	stu_id int,
	tch_id int,	
)

alter table Category add comment_addTime dal