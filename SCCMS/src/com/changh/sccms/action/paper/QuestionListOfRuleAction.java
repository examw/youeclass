package com.changh.sccms.action.paper;

import java.util.List;

import com.changh.sccms.entity.ExamQuestion;
import com.changh.sccms.service.ExamPaperService;

public class QuestionListOfRuleAction {
	private int ruleId;
	private int textareaNum; //ǰ���ʴ���ĸ���
	private int qNum; 		 //ǰ������������
	private List<ExamQuestion> list;
	private ExamPaperService examPaperService;
	public String execute()throws Exception
	{
		list  = examPaperService.findQuestionOfRule(ruleId);
		return "success";
	}
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	public List<ExamQuestion> getList() {
		return list;
	}
	public void setExamPaperService(ExamPaperService examPaperService) {
		this.examPaperService = examPaperService;
	}
	public int getTextareaNum() {
		return textareaNum;
	}
	public void setTextareaNum(int textareaNum) {
		this.textareaNum = textareaNum;
	}
	public int getqNum() {
		return qNum;
	}
	public void setqNum(int qNum) {
		this.qNum = qNum;
	}
	
}
