package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.ExamPaper;

public interface ExamPaperDAO {
	//�����Ծ����ظþ������ֵ
	public int save(ExamPaper paper)throws Exception;
	//findbyId
	public ExamPaper findById(int paperId)throws Exception;
	//�޸��Ծ�
	public void update(ExamPaper paper)throws Exception;
	public void updatePaperScore(int paperId,double score)throws Exception;
	//��ҳ��ѯĳ�����µ������Ծ�
	public List<ExamPaper> findPageByExamId(int examId,int page,int pagesize,String sortname,String sortorder)throws Exception;
	public long findTotalByExamId(int examId)throws Exception;
	public List<ExamPaper> findPageByContent(String content,int page,int pagesize,String sortname,String sortorder)throws Exception;
	public long findTotalByContent(String content)throws Exception;
	//ɾ���Ծ�
	public void delete(ExamPaper paper)throws Exception;
	//findByName
	public ExamPaper findByName(String paperName)throws Exception;
}
