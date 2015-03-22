package com.changh.sccms.dao;
import java.util.List;

import com.changh.sccms.entity.ExamRecord;
import com.changh.sccms.entity.Student;
import com.changh.sccms.entity.StudyRecord;
import com.changh.sccms.entity.Teacher;

//ѧԱ����
public interface StudentDAO {
	//�ҵ�����ѧԱ
	public List<Student> findAll(int page,int pagesize,String sortName,String sortOrder) throws Exception;
	//�ҳ�һ��ʱ����ڵ�����ѧԱ
	public List<Student> studentSelect(String date,int page,int pagesize,String sortname,String sortorder) throws Exception;
	//ɾ��ѧԱ
	public void  delete(int id) throws Exception;
	//���ѧԱ
	public void insert(Student stu) throws Exception;
	//��id����ѧԱ
	public Student findById(int id) throws Exception;
	//�޸�ѧԱ��Ϣ
	public void modify(Student stu) throws Exception;
	//���û�������ѧԱ
	public Student findByUsername(String username) throws Exception;
	//��email����ѧԱ
	public Student findByEmail(String email) throws Exception;
	//��ʱ���ѯѧԱ
	public int getTotal(String date);
	//�޸�ѧԱ��Ϣ�������߼� fw2013.03.19
	public void update(Student stu)throws Exception;
	/**
	 * ��ѯѧ��
	 * @param page
	 * @param pagesize
	 * @param sortname
	 * @param sortorder
	 * @param str
	 * @param searchName
	 * @return
	 * @throws Exception
	 */
	public List<Student> search(int page,int pagesize,String sortname,String sortorder,String str,String searchName)throws Exception;
	/**
	 * total
	 * @param searchName
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public int getSearchTotal(String searchName,String str) throws Exception;
	/**
	 * ����ѧѡ��ѧϰ��¼
	 * @param stuId
	 * @return
	 */
	public List<StudyRecord>  findStudyRecord(int stuId,int page,int pagesize,String sortname,String sortorder);
	/**
	 * ѧϰ��¼������
	 * @param stuId
	 * @return
	 */
	public Integer getStudyRecordTotal(int stuId);
	/**
	 * ����ѧԱ�Ŀ��Լ�¼
	 * @param stuId
	 * @return
	 */
	public List<ExamRecord> findExamRecord(int stuId,int page,int pagesize,String sortname,String sortorder);
	/**
	 * ���Լ�¼������
	 * @param stuId
	 * @return
	 */
	public Integer getExamRecordTotal(int stuId);
}
