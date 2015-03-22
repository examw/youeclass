package com.changh.eschool.service;

import java.util.List;

import com.changh.eschool.entity.Answer;
import com.changh.eschool.entity.Question;

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
	//find by stuid ����ҳ
	public List<Question> findByStuId(int page,int pagesize,String sortname,String sortorder,int stuId)throws Exception;
	//find total
	public int findTotal(String criteria) throws Exception;
	//ɾ��
	public boolean deleteQuestion(int questionId,int stuId)throws Exception;
	//���
	public void save(Question question)throws Exception;
	//
	public List<Question> findByCriteria(int page,int pagesize,String sortname,String sortorder,String criteria)throws Exception;
	//addAnswer
	public boolean addAnswer(Answer answer)throws Exception;
	/**
	 * ���ҵ�ǰ�ν����������
	 * @param classId
	 * @param type
	 * @return  List<Question>
	 */
	public List<Question> findByClassId( int classId,int type);
}
