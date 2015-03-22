package com.changh.eschool.action.user;

import com.changh.eschool.action.BaseAction;
import com.changh.eschool.entity.Student;
import com.changh.eschool.service.StudentService;

public class RegisterAction extends BaseAction{
	private Student stu;
	private String addr="/member/center";
	
	public String getAddr() {
		return addr;
	}
	private StudentService studentService;
	
	public Student getStu() {
		return stu;
	}
	public void setStu(Student stu) {
		this.stu = stu;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	public String execute()throws Exception
	{
		//�����
		studentService.addStudent(stu);
		//��session����ʾһ�ε�¼
		//��save��ʱ��û�а취����ȷ������ֵ�� stuId
		stu=studentService.findByUsername(stu.getStuUsername());
		session.put("student", stu);
		return "success";
	}
}
