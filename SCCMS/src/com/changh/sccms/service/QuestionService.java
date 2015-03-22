package com.changh.sccms.service;

import java.util.List;

import com.changh.sccms.entity.Question;

public interface QuestionService {
	//find by id
	public Question findById(int questionId)throws Exception;
	//�������⣬����ҳ
	public List<Question> findAll(int page,int pagesize,String sortname,String sortorder)throws Exception;
	//ָ�����͵����⣬����ҳ
	public List<Question> findByType(int page,int pagesize,String sortname,String sortorder,int type)throws Exception;
	//����������أ������������أ������⣬����ҳ
	public List<Question> findByContent(int page,int pagesize,String sortname,String sortorder,String content)throws Exception;
	//ָ��״̬���Ѵ�orδ�𣩵����⣬����ҳ
	public List<Question> findByStatus(int page,int pagesize,String sortname,String sortorder,int status)throws Exception;
}
