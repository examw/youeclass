package com.changh.sccms.service;

import java.util.List;

import com.changh.sccms.entity.ExamRecord;
import com.changh.sccms.entity.Student;
import com.changh.sccms.entity.StudyRecord;

public interface StudentService {
	// findAll
	public List<Student> studentList(int page, int pagesize, String sortName,
			String sortOrder) throws Exception;

	public void deleteStudent(int stuId) throws Exception;

	public Student updateStudent(int stuId) throws Exception;

	public void modifyStudent(Student stu) throws Exception;

	public List<Student> studentSelect(String date, int page, int pagesize,
			String sortname, String sortorder) throws Exception;

	// ����
	public void addStudent(Student stu) throws Exception;

	// find by username
	public Student findByUsername(String username) throws Exception;

	// find by email
	public Student findByEmail(String email) throws Exception;

	// find by id
	public Student findById(int id) throws Exception;

	// ����
	public int getTotal(String date);

	/**
	 * ��ѯ
	 * 
	 * @param page
	 * @param pagesize
	 * @param sortname
	 * @param sortorder
	 * @param str
	 * @param searchName
	 * @return
	 * @throws Exception
	 */
	public List<Student> search(int page, int pagesize, String sortname,
			String sortorder, String str, String searchName) throws Exception;

	// �ֶ����ֽ�ֵ
	public boolean updateAccount(int stuId, double money, String tradePattern,
			String content) throws Exception;

	/**
	 * total
	 * 
	 * @param searchName
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public int getSearchTotal(String searchName, String str) throws Exception;

	/**
	 * ����ѧѡ��ѧϰ��¼
	 * 
	 * @param stuId
	 * @return
	 */
	public List<StudyRecord> findStudyRecord(int stuId, int page, int pagesize,
			String sortname, String sortorder);

	/**
	 * ����ѧԱ�Ŀ��Լ�¼
	 * 
	 * @param stuId
	 * @return
	 */
	public List<ExamRecord> findExamRecord(int stuId, int page, int pagesize,
			String sortname, String sortorder);

	/**
	 * ѧϰ��¼������
	 * 
	 * @param stuId
	 * @return
	 */
	public Integer getStudyRecordTotal(int stuId);

	/**
	 * ���Լ�¼������
	 * 
	 * @param stuId
	 * @return
	 */
	public Integer getExamRecordTotal(int stuId);

	// 2014.06.12
	public void updatePwdReset(int stuId) throws Exception;

	// 2014.06.14
	// �ֶ��۳�ѧϰ��
	public boolean updateAccount(int stuId, double money, String content)
			throws Exception;
}
