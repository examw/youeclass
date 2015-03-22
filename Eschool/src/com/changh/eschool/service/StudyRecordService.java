package com.changh.eschool.service;

import java.util.List;

import com.changh.eschool.entity.StudyRecord;

public interface StudyRecordService {
	/**
	 *	���沥�ż�¼
	 * @param studyRecord
	 */
	public void save(StudyRecord studyRecord);
	/**
	 * �������
	 * @return
	 */
	public int findRecordId(int stuId,int classId);
	/**
	 * ��õ�ǰѧ���Ĳ��ż�¼
	 * @param stuId
	 * @return
	 */
	public List<StudyRecord> findByStuId(int stuId);
	/**
	 * ɾ��ѧ���Ĳ��ż�¼
	 * @param stuId
	 */
	public void deleteByStuId(int stuId);
	/**
	 * �ҵ�ÿ���༶������ѧϰ��¼
	 */
	public StudyRecord findByGreadId(int gradeId,int stuId);
	/**
	 * ����ѧԱ��ѧϰ�ÿνڼ�¼
	 * @param stuId
	 * @param classId
	 * @return
	 */
	public StudyRecord findSRecord(int stuId, int classId);
}
