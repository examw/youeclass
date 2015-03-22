package com.changh.eschool.service;

import java.util.List;

import com.changh.eschool.action.mobile.GradeGroup;
import com.changh.eschool.entity.Grade;

public interface GradeService {
	public List<List<Grade>> findByExamId(int examId);
	public Grade findById(int gradeId);
	/**
	* @Title: findByStuId
	* @Description: TODO(������һ�仰�����������������)
	* @param stuId
	* @return
	* @throws Exception    �趨�ļ�
	* @return List<Grade>    ��������
	* @throws
	 */
	public List<Grade> findByStuId(int stuId)throws Exception;
	
	//fw findByExamId
	public List<Grade> findByExamId2(int examId)throws Exception;
	/**
	 * ����ʦ���Ұ༶
	 * @param tchId
	 * @return
	 */
	public List<Grade> findByTchId(int tchId);
	/**
	* @Title: findGradeList
	* @Description: TODO(�������������Ұ༶)
	* @param stuId
	* @param examId
	* @return List<Grade>
	* @throws Exception    �趨�ļ�
	 */
	public List<Grade> findGradeList(int stuId,int examId)throws Exception;
	
	public String findExamName(int gradeId)throws Exception;
	
	//fw 2013.06.15 addmethod
	public List<Grade> findByPackageId(int packageId)throws Exception;
	
	public List<GradeGroup> findGroupGradeByExamId(int examId);
	
	
}
