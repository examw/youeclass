package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.Question;

public interface QuestionDAO {
	//��ҳ��ѯ����
	public List<Question> findPage(int page,int pagesize,String sortname,String sortorder)throws Exception;
	//findbyid
	public Question findById(int questionId)throws Exception;
	//��ҳ����
	/**
	 * ��ҳ���� 
	 * @param page			�ڼ�ҳ
	 * @param pagesize		ÿҳ��¼��
	 * @param sortname		��������
	 * @param sortorder		����or����
	 * @param content		��������
	 * @return
	 * @throws Exception
	 */
	public List<Question> searchPage(int page,int pagesize,String sortname,String sortorder,String content)throws Exception;
	//����浽��
	public void save(Question question)throws Exception;
	//find by stu_id ĳѧԱ������⼯��
	public List<Question> findByStuId(int stuId)throws Exception;
}
