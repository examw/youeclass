package com.changh.eschool.action.mobile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ���apk�İ汾��Ϣ
 * @author Administrator
 *
 */
public class CheckVersionUpdateAction {
	private String apkVersion;
	private String apkSize;
	private String apkPath;
	private String content;   
	private int appType;	//app����
	private Map<String,Object> result = new HashMap<String,Object>();	//���صĽ��
	private String oldVersion;//�ɵİ汾��
	public String execute() throws UnsupportedEncodingException
	{
		Properties ppt = new Properties();
		try {
		    ppt.load(CheckVersionUpdateAction.class .getResourceAsStream("/mobileAppVersion.properties")); 
		    if(appType==1)	//androidƽ̨��
		    {
		    	apkVersion = ppt.getProperty("apkVersion");       
		    	apkSize = ppt.getProperty("apkSize");       
		    	apkPath = ppt.getProperty("apkPath"); 
		    	content = ppt.getProperty("content");
		    	System.out.println(apkPath);
		    }
		    else
		    {
		    	result.put("S", 0);
		    	return "success";
		    }
		}catch (Exception e) {       
		    e.printStackTrace();  
		}  
		if(oldVersion==null||oldVersion.equals(""))
		{
			result.put("S", 0);//û�и���
		}
		else if(oldVersion.equals(apkVersion))
		{
			result.put("S", 0);//û�и���
		}else
		{
			result.put("S", 1);
			result.put("version", apkVersion);
			result.put("apkSize", apkSize);
			result.put("url", URLEncoder.encode(apkPath,"UTF-8"));
			result.put("Content", content);
		}
		return "success";
	}
	public int getAppType() {
		return appType;
	}
	public void setAppType(int appType) {
		this.appType = appType;
	}
	public String getOldVersion() {
		return oldVersion;
	}
	public void setOldVersion(String oldVersion) {
		this.oldVersion = oldVersion;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	
}
