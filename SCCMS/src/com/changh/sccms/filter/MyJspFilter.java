package com.changh.sccms.filter;

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
		//���ˣ� �˴�Ƿ�׵�������������������������������������������������������
		//�������������󣬵�¼�˶����Է���
		if(url.indexOf("/upload/")==-1&&url.indexOf("/images/")==-1&&url.indexOf("/js/")==-1&&url.indexOf("/css/")==-1&&httpRequest.getSession().getAttribute("admin")==null){//����Աû�е�¼���߱�����ȥ��
				if(url.indexOf("main1.jsp")!=-1)	//��¼ҳ�滹���ڵ�¼ҳ������ҳ����ת����ʾҳ��
				{
					httpResponse.sendRedirect("/SCCMS/system/index");
				}else if(url.indexOf("index")!=-1||url.indexOf("main")!=-1||url.indexOf("error.jsp")!=-1||url.indexOf("login")!=-1)
				{
					chain.doFilter(request, response);
				}else
				{
					//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					httpResponse.sendRedirect("/SCCMS/cms/error.jsp");	
				}
//				httpRequest.getRequestDispatcher("/system/index").forward(httpRequest, response);
			
		}else{
			//���ú����Ĺ�������
			chain.doFilter(request, response);
		}
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
