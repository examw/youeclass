package com.changh.sccms.service;

import java.util.List;

import com.changh.sccms.entity.ExamCategory;
import com.changh.sccms.entity.ExamCategory1;

public interface ExamCategoryService {
	public List<ExamCategory1> findAll() throws Exception;

	public List<ExamCategory1> findByPid(int id) throws Exception;

	// /////////////////////////
	public List<ExamCategory> examList() throws Exception;

	public void deleteExam(int id, int examPid) throws Exception;

	public ExamCategory examLoad(int examId) throws Exception;

	public void modifyStudent(ExamCategory exam) throws Exception;

	/**
	 * @Title: saveExam
	 * @Description: TODO(��ӿ������)
	 * @param id
	 * @param examName
	 * @param examUrl
	 * @param examDescription
	 * @throws Exception
	 *             �趨�ļ�
	 */
	public void saveExam(int id, String examName, String examUrl,
			String examDescription,String title,String examEname) throws Exception;

	/**
	 * @Title: updateExam
	 * @Description: TODO(�޸Ŀ������)
	 * @param id
	 * @param examName
	 * @throws Exception
	 *             �趨�ļ�
	 */
	public void updateExam(int id, String examName,String examUrl,String examDescription) throws Exception;

	public List<ExamCategory> findByExamPid(int examPid);

	// ��鿼���Ƿ��ܹ���������
	public boolean checkExamAdd(int examId);

	/**
	 * ��ÿ������id
	 * 
	 * @return
	 */
	public int getExamId();
	
	public List<ExamCategory> findByPro(String pro,Object value);

}