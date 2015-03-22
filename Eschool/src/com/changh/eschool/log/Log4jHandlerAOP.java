package com.changh.eschool.log;

import org.aspectj.lang.ProceedingJoinPoint;

import com.changh.eschool.entity.Student;
import com.changh.eschool.until.LogUtil;
import com.changh.eschool.until.PropertiesUtil;
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
			Student student = (Student) ActionContext.getContext().getSession().get("student");
			if(student!=null&&!val.equals(""))LogUtil.logger.warn("\""+student.getStuName()+"\" ִ���� "+val);
			return retVal;
		}
}
