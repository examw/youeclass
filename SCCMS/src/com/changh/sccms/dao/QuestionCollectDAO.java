package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.QuestionCollect;

public interface QuestionCollectDAO {
	public List<QuestionCollect> findByStuId(int stuId)throws Exception;
	//�ղش浽��
	public void save(QuestionCollect qc)throws Exception;
	//ɾ��
	public void delete(QuestionCollect qc)throws Exception;
}
