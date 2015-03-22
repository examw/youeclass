package com.changh.eschool.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �����ⲿֱ�ӷ���jspҳ��
 * @author Administrator
 *
 */
public class MyJspFilter implements Filter{
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String url = httpRequest.getRequestURI();
		if(url.indexOf(".jsp")!=-1){
			if(httpRequest.getSession().getAttribute("student")==null){
//				httpRequest.getRequestDispatcher("/system/index").forward(httpRequest, response);
			httpResponse.sendRedirect("/user/login.jsp");
		}else{
			//���ú����Ĺ�������
			chain.doFilter(request, response);
		}
		}else{
			chain.doFilter(request, response);
		}	
		//���ˣ� �˴�Ƿ�׵�������������������������������������������������������
		
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
