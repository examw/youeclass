package com.changh.eschool.action.mobile;

import java.util.HashMap;
import java.util.Map;

import com.changh.eschool.entity.Student;
import com.changh.eschool.service.StudentService;

public class RegisterAction {
	private String username;
	private String pwd;
	private String email;
	private String phone;
	private String qq;
	private Map<String,Object> result = new HashMap<String,Object>();
	private StudentService studentService;
	public String execute()throws Exception
	{
		Student stu = studentService.findByUsername(username);
		if(stu!=null)
		{
			result.put("OK", 0);
			result.put("msg", "用户名被占用");
			return "success";
		}
		stu = studentService.findByEmail(email);
		if(stu!=null)
		{
			result.put("OK", 0);
			result.put("msg", "邮箱被占用");
			return "success";
		}
		stu = new Student();
		stu.setStuUsername(username);
		stu.setStuEmail(email);
		stu.setStuPassword(pwd);
		stu.setStuPhone(phone);
		stu.setQICQ(qq);
		studentService.addStudent(stu);
		result.put("OK", 1);
		result.put("username", username);
		return "success";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
}
