package com.changh.eschool.action.paper;

import java.util.List;

import javax.servlet.http.Cookie;

import com.changh.eschool.action.BaseAction;
import com.changh.eschool.entity.ExamPaper;
import com.changh.eschool.entity.ExamRecord;
import com.changh.eschool.entity.ExamRule;
import com.changh.eschool.entity.Student;
import com.changh.eschool.service.ExamPaperService;
import com.changh.eschool.until.Escape;

public class LoadPaperAction extends BaseAction{
	private int paperId;
	private ExamPaper paper;
	private ExamRecord record;
	private List<ExamRule> ruleList;
	private ExamPaperService examPaperService;
	private String actionFlag="";
	private int rcdId;
	private int examNumbers;
	private int rank;
	private int ruleSize;
	private int totalNum;
	private String tempAnswer;
	public String execute()throws Exception
	{
		Student stu = (Student) session.get("student");
		paper = examPaperService.findById(paperId);
		if(paper==null){
			return "showPaper";
		}
		record = examPaperService.findRecordBy2Id(stu.getStuId(),paperId);
		ruleSize = paper.getExamRules().size();
		ruleList = paper.getExamRules();
		totalNum = getTotalQuestionNum(ruleList);
		if(record==null)	//��һ�ο������Ծ�
		{
			return "showPaper";
		}else
		{	
			tempAnswer=record.getRcdTempAnswer();
			if(actionFlag.equals("retry"))	//���¿���
			{
				return "showPaper";
			}
			if(actionFlag.equals("withAnswer"))	//�鿴���Դ�
			{
				rcdId = record.getId();
				//ÿ�������ʵ���������
				//to do something
				String[] str = record.getRcdScoreForEachRule().split(";");
				for(ExamRule r:ruleList)
				{
					for(int i=0;i<str.length;i++)
					{
						if(r.getRuleId().equals(Integer.parseInt(str[i].split("=")[0])))
						{
							r.setUserScore(str[i].split("=")[1]);
						}
					}
				}
				return "showPaper";
			}
			rcdId = record.getId();
			//�ο�����
			examNumbers = examPaperService.findExamNumbers(paper.getPaperId());
			//�ɼ�����
			rank = examPaperService.findScoreRank(stu.getStuId(),paper.getPaperId());
			String[] str = record.getRcdScoreForEachRule().split(";");
			for(ExamRule r:ruleList)
			{
				for(int i=0;i<str.length;i++)
				{
					if(r.getRuleId().equals(Integer.parseInt(str[i].split("=")[0])))
					{
						r.setUserScore(str[i].split("=")[1]);
					}
				}
			}
			return "showResult";
		}
	}
	//������
	private int getTotalQuestionNum(List<ExamRule> ruleList2) {	
		// TODO Auto-generated method stub
		int i = 0;
		for(ExamRule r:ruleList2)
		{
			i+=r.getRuleActualAddNum();
		}
		return i;
	}
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = Escape.unescapeId(paperId);
	}
	public ExamPaper getPaper() {
		return paper;
	}
	public void setExamPaperService(ExamPaperService examPaperService) {
		this.examPaperService = examPaperService;
	}
	public int getRuleSize() {
		return ruleSize;
	}
	public List<ExamRule> getRuleList() {
		return ruleList;
	}
	public String getActionFlag() {
		return actionFlag;
	}
	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}
	public int getRcdId() {
		return rcdId;
	}
	public void setRcdId(int rcdId) {
		this.rcdId = rcdId;
	}
	public int getExamNumbers() {
		return examNumbers;
	}
	public int getRank() {
		return rank;
	}
	public ExamRecord getRecord() {
		return record;
	}
	public void setRecord(ExamRecord record) {
		this.record = record;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public String getTempAnswer() {
		return tempAnswer;
	}
	public void setTempAnswer(String tempAnswer) {
		this.tempAnswer = tempAnswer;
	}
	
}
