package com.changh.eschool.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.changh.eschool.entity.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserLoginInterceptor extends AbstractInterceptor{
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> session = ActionContext.getContext().getSession(); 
		Student student = (Student)session.get("student");
		//user��Ϊ��,��ʾ�Ѿ���¼,��������
		if(student!=null)
		{
			invocation.invoke();
			return "success";
		}else
		{
			////��������,ת����¼����,�������ַ,���ڵ�¼�ɹ��󷵻���Ӧ�ĵ�ַ/////
			HttpServletRequest httpRequest = ServletActionContext.getRequest();
			 HttpServletResponse response = ServletActionContext.getResponse();
			 String query = httpRequest.getQueryString();
			 if (httpRequest.getHeader("x-requested-with") != null  
                     && httpRequest.getHeader("x-requested-with")  
                             .equalsIgnoreCase("XMLHttpRequest"))//�����ajax������Ӧͷ���У�x-requested-with��  
             {  
                 response.setHeader("sessionstatus", "timeout");//����Ӧͷ����session״̬  
                 session.put("lastUrlForUser",httpRequest.getParameter("url"));
                 return "false";  
             }  
			//��ȡ����ĵ�ַ ����
			StringBuffer url = httpRequest.getRequestURL();
			if(query!=null||"".equals(query))
			{
				url.append("?").append(query);
			}
			//System.out.println(url);
			session.put("lastUrlForUser", url.toString());
			//////////////////////////////////////////
			return "userLoginFail";
		}
	}
}
