package com.changh.sccms.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.changh.sccms.dao.LectureDAO;
import com.changh.sccms.entity.Lecture;

public class HibernateLectureDAO extends HibernateDaoSupport implements LectureDAO{

	public void save(Lecture lecture) {
		this.getHibernateTemplate().save(lecture);
	}

	public List<Lecture> findByClassId(int classId) {
		String hql ="select lect from Lecture lect where lect.classId ="+classId;
		return this.getHibernateTemplate().find(hql);
	}

	public Lecture findByLectId(int lectId) {
		return (Lecture) this.getHibernateTemplate().get(Lecture.class, lectId);
	}

	public void update(Lecture lecture) {
		this.getHibernateTemplate().update(lecture);
	}

	public void delete(int lectId) {
		this.getHibernateTemplate().delete(findByLectId(lectId));
	}

	public int getLectId() {
		String hql = "select max(lect.lectId) from Lecture lect";	
		Integer curr_id = (Integer)this.getHibernateTemplate().find(hql).get(0);//��ȡ����idֵ
		if(curr_id == null){//���û�м�¼,����һ��0001���
			return 1001;
		}
		//2.���ݵ�ǰid+1,��ȡ��һ��
		return curr_id+1;
	}

}
