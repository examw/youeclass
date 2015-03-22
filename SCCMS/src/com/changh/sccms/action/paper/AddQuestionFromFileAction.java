package com.changh.sccms.action.paper;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.changh.sccms.service.ExamPaperService;
import com.opensymphony.xwork2.ActionSupport;

public class AddQuestionFromFileAction extends ActionSupport{
	private File mf;//�ͻ����ϴ�����ʱ�ļ�����
	private String mfFileName;//mf+FileName ԭ�ļ���
	private int ruleId;	//����id
	private int paperId;//�Ծ�id
	private int questType;//����
	private int model;	//���ģʽ
	private int startNo;//��ʼλ��
	private ExamPaperService examPaperService;
	
	public void setExamPaperService(ExamPaperService examPaperService) {
		this.examPaperService = examPaperService;
	}
	public String execute(){
		try {
			//�����ļ����������ɿ���
			if(examPaperService.saveBatchFromFile(mf,ruleId,paperId,questType,startNo,model))
			return "success";
			return "fail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String getMfFileName() {
		return mfFileName;
	}

	public void setMfFileName(String mfFileName) {
		this.mfFileName = mfFileName;
	}
	public File getMf() {
		return mf;
	}

	public void setMf(File mf) {
		this.mf = mf;
	}
	
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public int getQuestType() {
		return questType;
	}
	public void setQuestType(int questType) {
		this.questType = questType;
	}
	
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	
	public int getStartNo() {
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public void addActionError(String anErrorMessage) {    
		  
		//����Ҫ���ж�һ�£�������Ҫ�滻�Ĵ��󣬲Ŵ���    
		  
		   if (anErrorMessage.startsWith("the request was rejected because its size")) {    
		  
		     Matcher m = Pattern.compile("//d+").matcher(anErrorMessage);    
		  
		    String s1 = "";    
		  
		    if (m.find())   s1 = m.group();    
		  
		    String s2 = "";    
		  
		    if (m.find())   s2 = m.group();    
		  
		    //͵������������Ϣ�滻��    
		     super.addActionError("���ϴ����ļ���С��" + s1 + "����������Ĵ�С��" + s2 + "��");    
		    //Ҳ���Ը�Ϊ��Field����Ĵ���  
		    // super.addFieldError("file","���ϴ����ļ���С��" + s1 + "����������Ĵ�С��" + s2 + "��");    
		  
		  } else {//����ԭ���ķ�������   
		  
		     super.addActionError(anErrorMessage);    
		}    
		  
		}   
}
