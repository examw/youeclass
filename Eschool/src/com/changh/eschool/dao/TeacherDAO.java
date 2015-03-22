package com.changh.eschool.dao;

/*
 * ��ʦ���ݽӿ�
 */

import java.util.List;

import com.changh.eschool.entity.Teacher;

public interface TeacherDAO {
	//�ҳ����е���ʦ
	public List<Teacher> findAll()throws Exception;
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
	/**
	 * ��tchId������ʦ
	 * @param tchId
	 * @return
	 * @throws Exception
	 */
	public Teacher findBytchId(int tchId);
}
