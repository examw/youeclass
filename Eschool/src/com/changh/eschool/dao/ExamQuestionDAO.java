package com.changh.eschool.dao;

import java.util.List;

import com.changh.eschool.entity.ExamQuestion;
import com.changh.eschool.entity.ExamRule;

public interface ExamQuestionDAO {
	//findbyId
	public ExamQuestion findById(int questId)throws Exception;
	//�޸�
	public void update(ExamRule question)throws Exception;
	//��ҳ��ѯĳ�Ծ��µ�����С��
	public List<ExamQuestion> findPageByCriteria(String criteria,int page,int pagesize,String sortname,String sortorder)throws Exception;
	public long findTotalByCriteria(String criteria)throws Exception;
	//��һ��������ѯ������ҳ
	public List<ExamQuestion> findByCriteria(String criteria)throws Exception;
	//ɾ��
	public List<ExamQuestion> findAllByPaperId(int paperId)throws Exception;
	public List<ExamQuestion> findAllByRuleId(int ruleId)throws Exception;
	//�����
	public List<String> findQuestionNo(int ruleId)throws Exception;
	//�ҳ������LinkId��ֵ
	public String findLinkedQid()throws Exception;
	
}
