package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.ExamQuestion;
import com.changh.sccms.entity.ExamRule;

public interface ExamQuestionDAO {
	//����
	//����ʵ�ʵ�����id
	public int save(ExamQuestion question)throws Exception;
	//������id,ֻ�����
	public void save1(ExamQuestion question)throws Exception;
	//����һ���б��������ʱ
	public void save(List<ExamQuestion> list,int num) throws Exception;
	//findbyId
	public ExamQuestion findById(int questId)throws Exception;
	//findByMD5code
	public List<ExamQuestion> findByMD5Code(String md5code,int ruleId)throws Exception;
	//�޸�
	public void update(ExamRule question)throws Exception;
	//��ҳ��ѯĳ�Ծ��µ�����С��
	public List<ExamQuestion> findPageByCriteria(String criteria,int page,int pagesize,String sortname,String sortorder)throws Exception;
	public long findTotalByCriteria(String criteria)throws Exception;
	//��һ��������ѯ������ҳ
	public List<ExamQuestion> findByCriteria(String criteria)throws Exception;
	//ɾ��
	public void delete(ExamQuestion question)throws Exception;
	public List<ExamQuestion> findAllByPaperId(int paperId)throws Exception;
	public List<ExamQuestion> findAllByRuleId(int ruleId)throws Exception;
	public void deleteAll(List<ExamQuestion> questionList)throws Exception;
	public void deleteAll(String questIds)throws Exception;
	//�����
	public List<String> findQuestionNo(int ruleId)throws Exception;
	//�ҳ������LinkId��ֵ
	public String findLinkedQid()throws Exception;
	
}
