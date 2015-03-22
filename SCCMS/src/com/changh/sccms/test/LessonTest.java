package com.changh.sccms.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.changh.sccms.dao.NewsClassDAO;

public class LessonTest {
	@Test
	public void testAdd() throws Exception{
		String[] configs = {"applicationContext-base.xml","applicationContext-dao.xml","applicationContext-service.xml"};
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext(configs);
		NewsClassDAO newClassDao = (NewsClassDAO)ac.getBean("newClassDao");
		
		System.out.println(newClassDao.findById(1001));
		System.out.println(newClassDao.findById(1001));

	}
}
