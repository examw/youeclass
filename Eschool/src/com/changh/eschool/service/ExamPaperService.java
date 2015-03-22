package com.changh.eschool.service;

import java.util.Date;
import java.util.List;

import com.changh.eschool.entity.ExamPaper;
import com.changh.eschool.entity.ExamQuestion;
import com.changh.eschool.entity.ExamRecord;
import com.changh.eschool.entity.ExamRule;
import com.changh.eschool.entity.Student;

public interface ExamPaperService {
	//����ĳ�༶�����п���
	public List<ExamPaper> findPageByGradeId(int gradeId,int page,int pagesize,String sortname,String sortorder)throws Exception;
	public long findTotalByGradeId(int gradeId)throws Exception;
	//findById
	public ExamPaper findById(int paperId)throws Exception;
	public ExamRule findRuleById(int ruleId)throws Exception;
	//findPaperByName
	public ExamPaper findPaperByName(String paperName)throws Exception;
	//����С��Ŀ
	//��id��
	public ExamQuestion findQuestionById(int questionId)throws Exception;
	//����ĳ�����µ�����С��
	public List<ExamQuestion> findQuestionOfRule(int ruleId)throws Exception;
	//����ĳ�����µ�����С��
	public List<ExamQuestion> findByCriteria(String criteria)throws Exception;
	public ExamRecord updateForAnswer(Student stu,int paperId, String answers,List<String> textarea,List<String> qids,Date startTime,Date endTime)throws Exception;
	public ExamRecord updateForManualMark(int paperId, int recordId,
			List<String> qids, List<String> rids, List<String> scores)throws Exception;
	public ExamRecord findRecordById(int rcdId)throws Exception;
	public int findRecordTotalByCriteria(String criteria)throws Exception;
	public List<ExamRecord> findRecordByCriteria(String criteria, int page,
			int pagesize, String sortname, String sortorder)throws Exception;
	public ExamRecord findRecordBy2Id(int stuId, int paperId)throws Exception;
	public int findExamNumbers(int paperId)throws Exception;
	public int findScoreRank(int stuId, int paperId)throws Exception;
	public void saveAnswer(int paperId, int stuId, String optionAnswer,
			String contentAnswer)throws Exception;
}
