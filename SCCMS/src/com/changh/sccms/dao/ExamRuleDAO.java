package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.ExamRule;

public interface ExamRuleDAO {
		//�������
		public void save(ExamRule rule)throws Exception;
		//findbyId
		public ExamRule findById(int ruleId)throws Exception;
		//�޸Ĵ���
		public void update(ExamRule paper)throws Exception;
		//��ҳ��ѯĳ�Ծ��µ����д���
		public List<ExamRule> findPageByExamId(int paperId,int page,int pagesize,String sortname,String sortorder)throws Exception;
		public long findTotalByExamId(int paperId)throws Exception;
		//ɾ������
		public void delete(ExamRule rule)throws Exception;
		public void deleteAll(List<ExamRule> rules)throws Exception;
		//����or����
		public void saveOrUpdate(List<ExamRule> rules)throws Exception;
}
