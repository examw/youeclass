package com.changh.sccms.test;

import org.apache.log4j.Logger;

import com.changh.sccms.until.LogUtil;

public class TestLog4j {
	//static Logger logger = Logger.getLogger(TestLog4j.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		logger.debug("������Ϣ");
//		logger.info("��ͨ��Ϣ");
////		System.out.println("lalala");
//		logger.warn("������Ϣ");
//		logger.error("������Ϣ");
//		logger.fatal("������Ϣ");
		for(int i=0;i<10000;i++)
		{
			LogUtil.logger.warn("hahaha_"+i);
		}
	}
	

}
