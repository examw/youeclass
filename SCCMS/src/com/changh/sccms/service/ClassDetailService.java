package com.changh.sccms.service;

import java.util.Date;
import java.util.List;

import com.changh.sccms.entity.ClassDetail;
import com.changh.sccms.entity.Grade;

public interface ClassDetailService {
	public List<ClassDetail> listClassDetail(int gid);

	public Grade findById(int gradeId);

	public void save(ClassDetail classDetail);

	public void delete(String classId);

	public ClassDetail findByClassId(int classId);

	public void update(ClassDetail classDetail);

	/**
	 * ���� ��ȡ����
	 * 
	 * @return
	 */
	public int getClassId();

	public int findNumByGid(int gradeId) throws Exception; // 2014.04.20 fw
															// ��ӷ������ڲ���ĳ�༶���½���

	/**
	 * @Title: findGradeByClassId
	 * @Description: TODO(������һ�仰�����������������)
	 * @param classId
	 * @return Grade
	 */
	public Grade findGradeByClassId(int classId);

	/**
	 * @Title: findClassDetailByGid
	 * @Description: TODO(���Ұ༶����Ŀγ�)
	 * @param gid
	 * @param status
	 *            (��ѣ��շѣ�ȫ��)
	 * @return �趨�ļ�
	 */
	public List<ClassDetail> findClassDetailByGid(int gid, int status);

	// �����Ŀ���ʱ��
	public boolean updateOpenTime(String ids, Date time);
}
