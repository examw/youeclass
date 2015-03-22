package com.changh.eschool.action.user;

import com.changh.eschool.service.StudentService;

public class ResetPwdAction {
	private String username;
	private String password;
	private String addr="/Eschool/user/login.jsp";
	private StudentService studentService;
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddr() {
		return addr;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	public String execute()throws Exception
	{
		studentService.resetPwd(username,password);
		return "success";
	}
}
