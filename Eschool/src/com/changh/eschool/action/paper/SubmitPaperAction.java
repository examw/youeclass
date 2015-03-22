package com.changh.eschool.action.paper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.changh.eschool.action.BaseAction;
import com.changh.eschool.entity.ExamPaper;
import com.changh.eschool.entity.ExamQuestion;
import com.changh.eschool.entity.ExamRecord;
import com.changh.eschool.entity.ExamRule;
import com.changh.eschool.entity.Student;
import com.changh.eschool.service.ExamPaperService;

public class SubmitPaperAction extends BaseAction {
	private List<String> textarea;
	private List<String> qids;
	private int paperId;
	private int rcdId;
	private ExamPaper paper;
	private List<ExamRule> ruleList;
	private ExamRecord record;
	private Date startTime;
	private Date endTime;
	private ExamPaperService examPaperService;
	private List<ExamQuestion> list;
	private int examNumbers;
	private int rank;
	private String answers;
	public String execute() throws Exception {
		paper = examPaperService.findById(paperId);
		Student stu = (Student) session.get("student");
		record = examPaperService.updateForAnswer(stu,paperId, answers,textarea,qids,startTime,endTime);
		ruleList = paper.getExamRules();
		if (textarea == null)// û���ʴ���
		{
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
			//�ο�����
			examNumbers = examPaperService.findExamNumbers(paper.getPaperId());
			//�ɼ�����
			rank = examPaperService.findScoreRank(stu.getStuId(),paper.getPaperId());
			return "success"; // ת�����ɼ���ҳ��
		} else {
			// �����ֹ�����
			Map<Integer, String> map = new HashMap<Integer, String>();
			StringBuffer buf = new StringBuffer();
			buf.append("where eq.questId in(");
			for (int i = 0; i < qids.size(); i++) {
				map.put(Integer.parseInt(qids.get(i)), textarea.get(i));
				buf.append(qids.get(i));
				buf.append(",");
			}
			buf.deleteCharAt(buf.length() - 1).append(")");
			list = examPaperService.findByCriteria(buf.toString());
			// ��ֵѧԱ��д�Ĵ�
			for (ExamQuestion q : list) {
				q.setUserAnswer(map.get(q.getQuestId()));
				q.setStandardScore(getScoreEach(ruleList,q.getQuestRuleId()));
			}
		}
		return "success1";
	}
	private double getScoreEach(List<ExamRule> rules,int ruleId)
	{
		for(ExamRule r:rules)
		{
			if(r.getRuleId().equals(ruleId))
			{
				return r.getRuleScoreForEach();
			}
		}
		return 0;
	}
	public List<String> getTextarea() {
		return textarea;
	}

	public void setTextarea(List<String> textarea) {
		this.textarea = textarea;
	}

	public List<String> getQids() {
		return qids;
	}

	public void setQids(List<String> qids) {
		this.qids = qids;
	}

	public int getPaperId() {
		return paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}

	public ExamPaper getPaper() {
		return paper;
	}

	public void setExamPaperService(ExamPaperService examPaperService) {
		this.examPaperService = examPaperService;
	}

	public List<ExamQuestion> getList() {
		return list;
	}

	public ExamRecord getRecord() {
		return record;
	}

	public List<ExamRule> getRuleList() {
		return ruleList;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		try {
			this.startTime = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss").parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		try {
			this.endTime = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss").parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public String getAnswers() {
		return answers;
	}
	public void setAnswers(String answers) {
		this.answers = answers;
	}
	
}
