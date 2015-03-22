package com.changh.eschool.service;

import java.util.List;

import com.changh.eschool.entity.ClassDetail;
import com.changh.eschool.entity.Grade;
import com.changh.eschool.entity.Lecture;
import com.changh.eschool.entity.Note;


public interface ClassDetailService {
	/**
	 * ���ҵ�ǰ�༶��gid�����пν�
	 * @param gid
	 * @return list<ClassDetail>
	 */
	public List<ClassDetail> listClassDetail(int gid,int page,int pagesize);
	/**
	* @Title: findClassDetailByGid
	* @Description: TODO(���Ұ༶����Ŀγ�)
	* @param gid
	* @param status(��ѣ��շѣ�ȫ��)
	* @return    �趨�ļ�
	 */
	public List<ClassDetail> findClassDetailByGid(int gid,int status);
	/**
	* @Title: findGbyclassId
	* @Description: TODO(���Ұ༶)
	* @param classId
	* @return    �趨�ļ�
	 */
	public Grade findGbyclassId(int classId);
	/**
	 * total
	 * @param gradeId
	 * @return
	 */
	public int findTotal(int gradeId);
	/**
	 * findbyId(gradeId)
	 * @param gradeId
	 * @return grade
	 */
	public Grade findById(int gradeId);
	/**
	 * ����ν�
	 * @param classDetail
	 */
	public void save(ClassDetail classDetail);
	/**
	 * ɾ���ν�
	 * @param classId
	 */
	public void delete(int classId);
	/**
	 * findByClassId(classId)
	 * @param classId
	 * @return classDetail
	 */
	public ClassDetail findByClassId(int classId);
	/**
	 * �޸Ŀν�
	 * @param classDetail
	 */
	public void update(ClassDetail classDetail);
	/**
	 * ���ҵ�ǰ�ν���������Խ���
	 * @param classId
	 * @return list<Lecture>
	 */
	public List<Lecture> findLectureByClassId(int classId);
	/**
	 * ���ν�Id���Ұ༶
	 * @param classId
	 * @return grade
	 */
	public Grade findGradeByClassId(int classId);
	/**
	 * ��ӻ����޸ıʼ�
	 * @param note
	 */
	public void addOrUpdateNote(Note note);
	/**
	 * ��ñʼ�����
	 * @return
	 */
	public int getNotePK();
	/**
	 * ��ȡ���µ�ǰ10����¼
	 */
	public List<Note> findMyNote(int classId, int stuId, int type);
	/**
	 * ɾ���ʼ�
	 */
	public void deleteNode(int noteId);
	
	////fw 2013.06.15 add method ///
	public List<ClassDetail> findFreeClassByGid(int gid) throws Exception;
	
}
