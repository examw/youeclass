package com.changh.sccms.dao;

import java.util.Date;
import java.util.List;

import com.changh.sccms.entity.ClassDetail;
import com.changh.sccms.entity.Grade;
/**
 * @Title: ClassDetailDAO.java
 * @Package com.changh.sccms.dao
 * @Description: TODO(��һ�仰�������ļ���ʲô)
 * @author Failymiss jiangwei3457@163.com
 * @date 2013-6-3 ����4:57:45
 * @version V1.0
 */
public interface ClassDetailDAO {
	public List<ClassDetail> findByGid(int gid);

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
	/**
	* @Title: findClassDetailByGid
	* @Description: TODO(���Ұ༶����Ŀγ�)
	* @param gid
	* @param status(��ѣ��շѣ�ȫ��)
	* @return    �趨�ļ�
	 */
	public List<ClassDetail> findClassDetailByGid(int gid,int status);

	public long findNumByGid(int gradeId) throws Exception; // 2014.04.20 fw
															// ��ӷ������ڲ���ĳ�༶���½���

	/**
	 * @Title: findGradeByClassId
	 * @Description: TODO(���ν��ҳ��༶)
	 * @param classId
	 * @return Grade
	 */
	public Grade findGradeByClassId(int classId);
	
	public void updateOpenTime(String ids,Date time);
}
