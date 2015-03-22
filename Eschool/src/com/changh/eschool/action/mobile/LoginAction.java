package com.changh.eschool.action.mobile;

import java.util.HashMap;
import java.util.Map;

import com.changh.eschool.entity.Student;
import com.changh.eschool.service.StudentService;

public class LoginAction {
	private String username;
	private String password;
	private StudentService studentService;
	private Map<String,Object> result = new HashMap<String,Object>();
	public String execute()
	{
		try{
			Student stu = studentService.login(username, password);
			if(stu==null)
			{
				result.put("OK", 0);
				result.put("msg", "用户名或密码错误");
			}else
			{
				result.put("OK", 1);
				result.put("msg", "登录成功");
				result.put("uid", stu.getStuId());
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			result.put("OK", 0);
			result.put("msg", "系统错误");
		}
		return "success";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
}
