package com.changh.sccms.dao;

/*
 * ��ʦ���ݽӿ�
 */

import java.util.List;

import com.changh.sccms.entity.Teacher;

public interface TeacherDAO {
	//�ҳ����е���ʦ
	public List<Teacher> findAll()throws Exception;
	public List<Teacher> findAll(int page,int pagesize,String sortname,String sortorder)throws Exception;
	//��ָ��id����ʦ
	public Teacher findById(int id)throws Exception;
	//��ָ���û�������ʦ
	public Teacher findByUsername(String username)throws Exception;
	//��ָ����������ʦ
	public List<Teacher> findByName(String name)throws Exception;
	//�޸���ʦ��Ϣ
	public void update(Teacher teacher)throws Exception;
	//ɾ��
	public void delete(int id)throws Exception;
	//���
	public void save(Teacher teacher)throws Exception;
	//����
	public List<Teacher> search(String str,String searchName)throws Exception;
	public List<Teacher> search(int page,int pagesize,String sortname,String sortorder,String str,String searchName)throws Exception;
	//get total
	public long getTotal(String criteria)throws Exception;
	/**
	 * ��TchId������ʦ
	 * @param tchId
	 * @return
	 */
	public Teacher findTeacherByTchId(int tchId);
}
