package com.changh.eschool.dao;

import java.util.List;

import com.changh.eschool.entity.StudyRecord;

/**
 * ���ż�¼DAO
 * @author Administrator
 *
 */
public interface StudyRecordDAO {
	/**
	 * ����ѧϰ��¼
	 * @param studyRecord
	 */
	public void save(StudyRecord studyRecord);
	/**
	 * �������
	 * @return
	 */
	public int findSRecordId( int stuId,int classId);
	/**
	 * ����
	 * @param stuId
	 * @param classId
	 * @return
	 */
	public StudyRecord findSRecord( int stuId,int classId);
	/**
	 * ���ҵ�ǰ��¼��ѧ����ѧϰ��¼
	 * @param stuId
	 * @return
	 */
	public List<StudyRecord> findByStuId(int stuId);
	/**
	 * ������ż�¼
	 * @param stuId
	 */
	public void deleteByStuId(int stuId);
	/**
	 * �ҵ�ÿ���༶�����¼�¼
	 * @param gradeId
	 * @return
	 */
	public StudyRecord findByGradeId(int gradeId,int stuId);
}
