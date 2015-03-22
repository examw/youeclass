package com.changh.eschool.dao;

import java.util.List;

import com.changh.eschool.entity.Grade;
import com.changh.eschool.entity.GradeCategory;



public interface GradeDAO {
	public List<Grade> findGradeByExamPidAndGradeCategory(String gtypeName,int examId);
	public List<GradeCategory> findAll();
	public Grade findById(int gradeId);
	//ѧԱ����༶�ļ���
	public List<Grade> findByStuId(int stuId)throws Exception;
	/**
	* @Title: findGradeList
	* @Description: TODO(�������������Ұ༶)
	* @param stuId
	* @param examId
	* @return List<Grade>
	* @throws Exception    �趨�ļ�
	 */
	public List<Grade> findGradeList(int stuId,int examId)throws Exception;
	public List<Grade> findByCriteria(String criteria)throws Exception;
	public String findExamName(int gradeId)throws Exception;
	/**
	 * ���ҵ�ǰ��ʦ���̵İ༶
	 * @param tchId
	 * @return
	 */
	public List<Grade> findByTchId(int tchId);
	
	//fw 2013.06.15 add method//
	/**
	 * ��ѯ�ײ��°༶�ļ���
	 * @param packageId
	 * @return
	 * @throws Exception
	 */
	public List<Grade> findByIds(String ids)throws Exception;
	//
}
