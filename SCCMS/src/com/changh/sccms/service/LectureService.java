package com.changh.sccms.service;

import java.util.List;

import com.changh.sccms.entity.Lecture;

public interface LectureService {
	public void save(Lecture lecture);
	//���ҵ��ڿνڵ����н���
	public List<Lecture> findByClassId(int classId);
	//��lectId���ҽ���
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
