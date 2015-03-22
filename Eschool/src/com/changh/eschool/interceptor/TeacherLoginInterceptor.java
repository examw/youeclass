package com.changh.eschool.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.changh.eschool.entity.Teacher;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class TeacherLoginInterceptor extends AbstractInterceptor{
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> session = ActionContext.getContext().getSession(); 
		Teacher teacher = (Teacher)session.get("teacher");
		//user��Ϊ��,��ʾ�Ѿ���¼,��������
		if(teacher!=null)
		{
			invocation.invoke();
			return "success";
		}else
		{
			////��������,ת����¼����,�������ַ,���ڵ�¼�ɹ��󷵻���Ӧ�ĵ�ַ/////
			HttpServletRequest httpRequest = ServletActionContext.getRequest();
			//��ȡ����ĵ�ַ ����
			StringBuffer url = httpRequest.getRequestURL().append("?").append(httpRequest.getQueryString());
			//System.out.println(url);
			session.put("lastUrlForTch", url.toString());
			//////////////////////////////////////////
			return "fail";
		}
	}
}
