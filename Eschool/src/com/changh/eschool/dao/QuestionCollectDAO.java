package com.changh.eschool.dao;

import java.util.List;

import com.changh.eschool.entity.QuestionCollect;

public interface QuestionCollectDAO {
	public List<QuestionCollect> findByStuId(int page,int pagesize,String sortname,String sortorder,int stuId)throws Exception;
	//�ղش浽��
	public void save(QuestionCollect qc)throws Exception;
	//ɾ��
	public void delete(QuestionCollect qc)throws Exception;
	//�Ƿ����ղ�
	public QuestionCollect isCollected(int stuId,int questionId)throws Exception;
	//find total
	public long findTotalByStuId(int stuId)throws Exception;
}
