package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.Lecture;

public interface LectureDAO {
	//��ӽ���
	public void save(Lecture lecture);
	//���ҵ��ڿν��µ����Խ���
	public List<Lecture> findByClassId(int classId);
	//���ҽ���id
	public Lecture findByLectId(int lectId);
	//�޸�
	public void update(Lecture lecture);
	//ɾ��
	public void delete(int lectId);
	/**
	 * ���ҽ���lectId
	 */
	public int getLectId();
}
