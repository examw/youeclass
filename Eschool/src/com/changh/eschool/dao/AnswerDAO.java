package com.changh.eschool.dao;

import java.util.List;

import com.changh.eschool.entity.Answer;

public interface AnswerDAO {
	//find by ����id
	public List<Answer> findByQuestionId(int questionId)throws Exception;
	//�����
	public void save(Answer answer)throws Exception;
}
