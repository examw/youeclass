package com.changh.sccms.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.changh.sccms.entity.ExamPaper;
import com.changh.sccms.entity.ExamQuestion;
import com.changh.sccms.entity.ExamRule;

public interface ExamPaperService {
	//map��װligerGrid������
	public Map<String,Object> findPageByExamId(int examId,int page,int pagesize,String sortname,String sortorder)throws Exception;
	public Map<String,Object> findPageByContent(String content,int page,int pagesize,String sortname,String sortorder)throws Exception;
	//����Ծ�
	public void addPaper(ExamPaper paper,List<ExamRule> rules)throws Exception;
	//�鿴�Ծ����еĿ��� ����ҳ
	public Map<String,Object> findQuestionOfPaper(int paperId,int page,int pagesize,String sortname,String sortorder)throws Exception;
	public Map<String,Object> findQuestionByContent(String content,int page,int pagesize,String sortname,String sortorder)throws Exception;
	//findById
	public ExamPaper findById(int paperId)throws Exception;
	//findPaperByName
	public ExamPaper findPaperByName(String paperName)throws Exception;
	//ɾ���Ծ�
	public boolean deletePaper(int paperId)throws Exception;
	//ɾ������
	public boolean deleteRule(int ruleId)throws Exception;
	//�����Ծ�
	public void updatePaper(ExamPaper paper,List<ExamRule> rules)throws Exception;
	//findRulebyid
	public ExamRule findRuleById(int ruleId)throws Exception;
	//�ҿ�������
	public List<String> findQuestionNo(int ruleId,List<String> list)throws Exception;
	//����һ�������ǲ�������������
	public String findLinkedQid()throws Exception; 
	//���С��Ŀ
	public String addQuestion(ExamQuestion question,String link)throws Exception;
	//����С��Ŀ
	//��id��
	public ExamQuestion findQuestionById(int questionId)throws Exception;
	//�ȶ�md5code����ظ���Ŀ,����ruleId���,��Ӻ��޸�ʱ���в�ͬ�ıȶ�
	public boolean checkRepeat(String content,int ruleId,int model)throws Exception;
	//ɾ������
	public boolean deleteQuestion(int questId)throws Exception;
	//��������
	public boolean updateQuestion(ExamQuestion question)throws Exception;
	//��������
	public boolean updateQuestionsForSetLink(String questionIds)throws Exception;
	//ȡ����������
	public boolean updateQuestionForCancelLink(String questionId)throws Exception;
	//�������
	public boolean saveBatchFromFile(File mf, int ruleId, int paperId,
			int questType,int startNo,int model)throws Exception;
	public boolean saveBatchFromEditor(String questContent, int ruleId,
			int paperId, int questType,int startNo,int model)throws Exception;
	//����ĳ�����µ�����С��
	public List<ExamQuestion> findQuestionOfRule(int ruleId)throws Exception;
	//����ĳ�����µ�����С��
	public List<ExamQuestion> findByCriteria(String criteria)throws Exception;
}
