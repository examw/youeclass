package com.changh.sccms.until;

import java.io.IOException;
import java.util.Properties;

/**
 * ����properties�ļ��Ĺ�����
 * @author Administrator
 *
 */
public class PropertiesUtil {
	private static Properties props;
	static {
		props = new Properties();
		try {
			props.load(PropertiesUtil.class
					.getClassLoader()
					.getResourceAsStream("opt.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getOptValue(String key){
		String val = props.getProperty(key);
		if(val == null){
			return "";
		}
		return val;
	}
	
	
}
