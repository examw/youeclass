package com.changh.sccms.action.user.student;

import com.changh.sccms.service.StudentService;

/**
 * ����û����ǲ��Ǳ�ռ��
 * @author Administrator
 *
 */
public class CheckUsernameAction {
	private String username;
	private StudentService studentService;
	private boolean ok=false;
	
	public boolean isOk() {
		return ok;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	public String execute()throws Exception
	{
		if(studentService.findByUsername(username)!=null)
		{
			ok=true; //��ʾ��ռ��
		} 
		return "success";
	}
}
