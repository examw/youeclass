package com.changh.eschool.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.changh.eschool.dao.TeacherDAO;
import com.changh.eschool.entity.Teacher;
import com.changh.eschool.service.TeacherService;
import com.changh.eschool.until.Constant;


public class TeacherServiceImpl implements TeacherService {
	//injection
	private TeacherDAO teacherDao;
	
	//�������н�ʦ
	public List<Teacher>  teacherList() throws Exception{
		List<Teacher> list=null;
	    list = teacherDao.findAll();
		return list;
	}
	
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
		if(teacher.getTchValuation()==null)
		{
			Teacher tch = teacherDao.findById(teacher.getTchId());
			if(tch==null)
			{
				return false;
			}
			teacher.setTchAddTime(tch.getTchAddTime());
			teacher.setTchValuation(tch.getTchValuation());
			teacher.setTchScore(tch.getTchScore());
			teacher.setTchImgUrl(tch.getTchImgUrl());
		}
		teacherDao.update(teacher);
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
	public boolean login(String tchUsername, String password) throws Exception {
		// TODO Auto-generated method stub
		Teacher tch = teacherDao.findByUsername(tchUsername);
		if(tch!=null&&tch.getTchPassword().equals(password)&&tch.getTchStatus()==Constant.ALLOWED)
		{
			//��session
			ServletActionContext.getRequest().getSession().setAttribute("teacher", tch);
			return true;
		}
		return false;
	}

	public Teacher findBytchId(int tchId) {
		return teacherDao.findBytchId(tchId);
	}
}
