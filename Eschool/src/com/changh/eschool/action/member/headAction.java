package com.changh.eschool.action.member;

import com.changh.eschool.action.BaseAction;
import com.changh.eschool.entity.Student;

//�ж�session���Ƿ���student,���ѧ������
public class headAction extends BaseAction{
	private Student stu;

	public  String execute(){
		stu=(Student)session.get("student");
		return "success";
	}
	public Student getStu() {
		return stu;
	}
	public void setStu(Student stu) {
		this.stu = stu;
	}
}
