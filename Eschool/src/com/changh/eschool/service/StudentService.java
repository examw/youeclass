package com.changh.eschool.service;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.changh.eschool.entity.Address;
import com.changh.eschool.entity.Cooperate;
import com.changh.eschool.entity.Items;
import com.changh.eschool.entity.Order;
import com.changh.eschool.entity.Student;

public interface StudentService {
	public Student studentUpdate(int stuId) throws Exception;
	public void updateStudent(Student stu) throws Exception;
	public List<Student> studentSelect(String date) throws Exception;
	
	//����
	public void addStudent(Student stu)throws Exception;
	//find by username
	public Student findByUsername(String username)throws Exception;
	//find by email
	public Student findByEmail(String email)throws Exception;
	//��¼��ҵ���߼�
	public Student login(String username,String password)throws Exception;
	public Student login(String username,String password,Integer auto)throws Exception;
	//�һ������ҵ���߼�
	public boolean findPwd(String username,String email,JavaMailSender mailSender,SimpleMailMessage mailMessage)throws Exception;
	//��������
	public void resetPwd(String username,String password)throws Exception;
	//�ӹ��ﳵ��ע��
	public boolean registerFromCart(Student stu)throws Exception;
	//��õ�ַ�б�
	public List<Address> findAddrListByStuId(int stuId)throws Exception;
	//������ַ
	public void addAddr(Address addr,int stuId)throws Exception;
	//�޸ĵ�ַ
	public void updateAddr(Address addr)throws Exception;
	//�޸�Ĭ��
	public void updateDefaultAddr(int aid,int stuId)throws Exception;
	/**
	 * find by stuId
	 */
	public Student findByStuId(int stuId);
	public Address findAddr(int addrId)throws Exception;
	public Student findByNameAndPwd(String username,String password)throws Exception;
	
	/**��������**/
	public Cooperate findCooperate(String url);
	/**
	 * ���ֻ��Ų���ѧԱ
	 * 2014.04.11
	 */
	public Student findByMobilePhone(String phone);
	/**
	 * ��Token����ѧԱ
	 * 2014.06.13
	 */
	public Student findByToken(String token);

}
