package com.changh.eschool.dao;
import java.util.List;

import com.changh.eschool.entity.Cooperate;
import com.changh.eschool.entity.Student;
import com.changh.eschool.entity.Teacher;

//ѧԱ����
public interface StudentDAO {
	//�ҵ�����ѧԱ
	public List<Student> findAll() throws Exception;
	//�ҳ�һ��ʱ����ڵ�����ѧԱ
	public List<Student> select(String date) throws Exception;
	//ɾ��ѧԱ
	public void  delete(int id) throws Exception;
	//���ѧԱ
	public void insert(Student stu) throws Exception;
	//��id����ѧԱ
	public Student findById(int id) throws Exception;
	//�޸�ѧԱ��Ϣ
	public void update(Student stu) throws Exception;
	//���û�������ѧԱ
	public Student findByUsername(String username) throws Exception;
	//��email����ѧԱ
	public Student findByEmail(String email) throws Exception;
	//����username����email���Ƿ����
	public boolean isExist(String username,String email)throws Exception;
	/**
	 * ����������ѧ��
	 * @param stuId
	 * @return
	 */
	public Student findByStuId(int stuId);
	
	/**��������**/
	public Cooperate findCooperate(String url);
	
	/**
	 * ���ֻ��Ų���ѧԱ
	 * 2014.04.11
	 */
	public Student findByMobilePhone(String phone);
	/**
	 * ��token����
	 * @param token
	 * @return
	 */
	public Student findByToken(String token);
}
