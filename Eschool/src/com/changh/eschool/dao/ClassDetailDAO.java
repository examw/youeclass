package com.changh.eschool.dao;

import java.util.List;

import com.changh.eschool.entity.ClassDetail;
import com.changh.eschool.entity.Grade;
import com.changh.eschool.entity.Lecture;
import com.changh.eschool.entity.Note;



public interface ClassDetailDAO {
	/**
	 * ��ҳ
	 * @param gid
	 * @param page
	 * @param pagesize
	 * @return
	 */
	public List<ClassDetail> findByGid(int gid,int page,int pagesize);
	/**
	* @Title: findClassDetailByGid
	* @Description: TODO(���Ұ༶����Ŀγ�)
	* @param gid
	* @param status(��ѣ��շѣ�ȫ��)
	* @return    �趨�ļ�
	 */
	public List<ClassDetail> findClassDetailByGid(int gid,int status);
	/**
	 * ���ҵ�ǰ�༶�νڵ�����
	 * @param gid
	 * @return
	 */
	public int findTotal(int gid);
	/**
	 * ���Ұ༶
	 * @param gradeId
	 * @return grade
	 */
	public Grade findById(int gradeId);
	/**
	 * ���潲��
	 * @param classDetail
	 */
	public void save(ClassDetail classDetail);
	/**
	 * ɾ������
	 * @param classId
	 */
	public void delete(int classId);
	/**
	 * ���ҽ���
	 * @param classId
	 * @return classDetail
	 */
	public ClassDetail findByClassId(int classId);
	/**
	 * �޸Ľ���
	 * @param classDetail
	 */
	public void update(ClassDetail classDetail);
	/**
	 * ���ҿν���������Խ���
	 * @param classId
	 * @return
	 */
	public List<Lecture> findLectureByClassId(int classId);
	/**
	 * ���ҵ�ǰ�νڵİ༶
	 * @param classId
	 * @return
	 */
	public Grade findGradeByClassId(int classId);
	/**
	 * �����Լ����γ������ǰʮ�ʼ�
	 * @param classId
	 * @param stuId
	 * @param type
	 * @return
	 */
	public List<Note> findMyNote(int classId,int stuId,int type);
	/**
	 * ��ӻ����޸ıʼ�
	 * @param note
	 */
	public void addOrUpdateNote(Note note);
	/**
	 * ����note������
	 * @return
	 */
	public int getNotePK();
	/**
	 * ɾ���ʼ�
	 * @param noteId
	 */
	public void deleteNode(int noteId);
	/**
	* @Title: findGbyclassId
	* @Description: TODO(���νڲ��Ұ༶)
	* @param classId
	* @return    �趨�ļ�
	 */
	public Grade findGbyclassId(int classId);
	
	/////fw 2013.06.15 add method/////
	/**
	 * ��ѯ���п�����������Ŀγ�
	 * @param gid
	 * @return
	 * @throws Exception
	 */
	public List<ClassDetail> findFreeClassByGid(int gid) throws Exception;
}
