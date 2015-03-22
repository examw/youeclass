package com.changh.eschool.entity;

import java.util.Map;

/**
 * $.ajax����Ҫ���ܵ�JSON
 * 
 * @author
 * 
 */
public class AjaxJson {

	private boolean success = true;// �Ƿ�ɹ�
	private String msg = "�����ɹ�";// ��ʾ��Ϣ
	private String username="";
	private String password="";
	private Object obj = null;// ������Ϣ
	private Map<String, Object> attributes;// ��������
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
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

}
