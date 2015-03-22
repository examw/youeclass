package com.changh.sccms.log;

import org.aspectj.lang.ProceedingJoinPoint;

import com.changh.sccms.entity.Administrator;
import com.changh.sccms.until.LogUtil;
import com.changh.sccms.until.PropertiesUtil;
import com.opensymphony.xwork2.ActionContext;

public class Log4jHandlerAOP {
	//��¼������־
		public Object mylogger(ProceedingJoinPoint pjp) throws Throwable{
			Object retVal = pjp.proceed();//����Ŀ����󷽷�
		
			//��ȡҪ���õ�����
			String className = pjp.getTarget()
								.getClass().getSimpleName();
			//��ȡҪ���õķ�����
			String methodName = pjp.getSignature().getName();
			String key = className+"."+methodName;
			String val = PropertiesUtil.getOptValue(key);
			//���session
			Administrator admin = (Administrator) ActionContext.getContext().getSession().get("admin");
			if(admin!=null&&!val.equals(""))LogUtil.logger.warn("\""+admin.getAdmName()+"\" ִ���� "+val);
			return retVal;
		}
}
