package com.changh.sccms.action.system;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.changh.sccms.until.PropertiesUtil;

public class ImagesAction {
	private Map<String, Object>  map = new HashMap<String, Object>();
	private String root = "";
	private Boolean isBlAll = false;
	private List<String> data = new ArrayList<String>();
	private String message="";
	
	
	public String execute(){
		String rootPath = PropertiesUtil.getOptValue("rootSCCMS");
		root = rootPath+"/cms/images/32X32";
		System.out.println(root);
		data = recursion(root, isBlAll);
		map.put("Data", data);
		map.put("Message", message);
		if(message==""){
			map.put("IsError", false);
		}else{
			map.put("IsError", true);
		}
		return "success";
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String getRoot() {
		return root;
	}
	public void setRoot(String root) {
		this.root = root;
	}
	public Boolean getIsBlAll() {
		return isBlAll;
	}
	public void setIsBlAll(Boolean isBlAll) {
		this.isBlAll = isBlAll;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> recursion(String root,boolean isBlAll){
		//Java�ж�ȡĳ��Ŀ¼�µ������ļ����ļ���
		  File file=new File(root);
		  if(!file.exists()){
			  message ="ϵͳ����,Ŀ¼������";
			  return null;
		  }
		  File[] tempList = file.listFiles();
		  if(tempList.length==0){
			  message="ϵͳ���󣬸�Ŀ¼��û���ļ�";
			  return null;
		  }
		  System.out.println("��Ŀ¼�¶��������"+tempList.length);
		  for (int i = 0; i < tempList.length; i++) {
			   if (tempList[i].isFile()) {
			    String fileName=tempList[i].getName();//�ļ�����
			    String hzm=fileName.substring(fileName.indexOf(".")+1,fileName.length()); //�ļ���׺��
			    hzm=hzm.toLowerCase();
			    if(hzm.equals("jpg")||hzm.equals("bmp")||hzm.equals("gif")||hzm.equals("png")){
			    	//data.add(tempList[i]);//����Ҫע�⣬ 	
			    	data.add(fileName);
			    }
			   }
			   if (tempList[i].isDirectory()) {	
				 if(isBlAll){
					 recursion(tempList[i].getAbsolutePath(),isBlAll);
				 }
			  
			   }
		  	}
		  return data;
		
		}
}
