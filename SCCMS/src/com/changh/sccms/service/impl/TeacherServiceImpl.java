package com.changh.sccms.service.impl;

import java.util.Date;
import java.util.List;

import com.changh.sccms.dao.TeacherDAO;
import com.changh.sccms.entity.Teacher;
import com.changh.sccms.service.TeacherService;
import com.changh.sccms.until.Escape;


public class TeacherServiceImpl implements TeacherService {
	//injection
	private TeacherDAO teacherDao;
	
	//�������н�ʦ
	public List<Teacher>  teacherList() throws Exception{
		List<Teacher> list=null;
	    list = teacherDao.findAll();
		return list;
	}
	/**
	 * 
	 * @param teacherDao
	 */
	
	public void setTeacherDao(TeacherDAO teacherDao) {
		this.teacherDao = teacherDao;
	}
	//find by id
	public Teacher getTeacher(int id) throws Exception {

		return teacherDao.findById(id);
	}
	//���ӽ�ʦ
	public void addTeacher(Teacher teacher) throws Exception {
		//�������ʱ��
		teacher.setTchAddTime(new Date());
		//����Ĭ��5��
		teacher.setTchScore(5);
		teacherDao.save(teacher);
	}
	//�޸Ľ�ʦ��Ϣ
	public boolean updateTeacher(Teacher teacher) throws Exception {
		//ֻ���²�����Ϣ
		Teacher tch = teacherDao.findById(teacher.getTchId());
		if(tch==null)
		{
			return false;
		}
		//�û��������룬״̬���绰���������Ա����пγ̣����
		tch.setTchUsername(teacher.getTchUsername());
		tch.setTchPassword(teacher.getTchPassword());
		tch.setTchStatus(teacher.getTchStatus());
		tch.setTchDescription(teacher.getTchDescription());
		tch.setTchLessons(teacher.getTchLessons());
		tch.setTchPhone(teacher.getTchPhone());
		tch.setTchSex(teacher.getTchSex());
		tch.setTchName(teacher.getTchName());
		//teacherDao.update(tch);	//�������д
		return true;
	}
	public boolean updateTeacherImgUrl(Teacher teacher) throws Exception {
		// TODO Auto-generated method stub
		Teacher tch = teacherDao.findById(teacher.getTchId());
		if(tch==null)
		{
			return false;
		}
		//�û��������룬״̬���绰���������Ա����пγ̣����
		tch.setTchImgUrl(teacher.getTchImgUrl());
		//teacherDao.update(tch);	//�������д
		return true;
	}
	//ɾ����ʦ
	public void deleteTeacher(int id)throws Exception{
		teacherDao.delete(id);
	}
	//��ָ������������ʦ
	public List<Teacher> searchTeacher(String str, String searchName) throws Exception {
		return teacherDao.search(str, searchName);
	}
	//find by username (���Ľ�ʦ��Ϣʱ��ҪcheckusernameΨһ��)
	public Teacher getTeacher(String username) throws Exception {
		return teacherDao.findByUsername(username);
	}
	public List<Teacher> teacherList(int page, int pagesize, String sortname,
			String sortorder) throws Exception {
		// TODO Auto-generated method stub
		return teacherDao.findAll(page, pagesize, sortname, sortorder);
	}
	public long getTotal(String criteria) throws Exception
	{
		return teacherDao.getTotal(criteria);
	}
	public Teacher findTeacherByTchId(int tchId) {
		return teacherDao.findTeacherByTchId(tchId);
	}
}
