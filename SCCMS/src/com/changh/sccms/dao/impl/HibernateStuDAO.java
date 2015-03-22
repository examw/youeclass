package com.changh.sccms.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.changh.sccms.dao.StudentDAO;
import com.changh.sccms.entity.ExamRecord;
import com.changh.sccms.entity.Student;
import com.changh.sccms.entity.StudyRecord;


public class HibernateStuDAO extends HibernateDaoSupport implements StudentDAO{
	//�ҳ����У�
	public List<Student> findAll(final int page, final int pagesize, final String sortname,
			final String sortorder) throws Exception {
		return (List)this.getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
					{
						//ʹ��sessionִ�з�ҳ��ѯ����
						String hql="from Student order by "+sortname+" "+sortorder;
						Query query=session.createQuery(hql);
						query.setFirstResult((page-1)*pagesize);
						query.setMaxResults(pagesize);
						return query.list();
					}
				}
			);
	}
	/*
	 * �ҳ����ʱ����d1����d2֮�������ѧԱ
	 * ������d1��d2 ����ʱ�䣬����d1����d2
	 */
	public List<Student> studentSelect(final String date,final int page,final int pagesize,final String sortname,final String sortorder) throws Exception {
		return (List)this.getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
					{
						//ʹ��sessionִ�з�ҳ��ѯ����
						//����Date����
						Date newdate = new Date();
						//�������ڵ�ǰʱ�����������
						Calendar cl1 = Calendar.getInstance();
						cl1.setTime(newdate);
						cl1.add(Calendar.DATE, 1);
						Calendar cl = Calendar.getInstance();
						cl.setTime(newdate);
						
						//���������
						if(date.equals("taday")){
							cl.add(Calendar.DATE, -1);
						 }
						//������죬һ����������
						if(date.equals("month")){
							cl.add(Calendar.MONTH, -1);
						}
						 //������죬һ���ڵ�����
						if(date.equals("week")){
							cl.add(Calendar.DATE, -7);
						 }
						//������죬�����µ�����
						if(date.equals("premonth")){
							cl.add(Calendar.MONTH,-2);
						}
						Date startDate = cl.getTime();
						Date endDate = cl1.getTime();
						SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd"); 
						//��ʽ����ʼ���ںͽ�������
						String start = dd.format(startDate);
						String end = dd.format(endDate);
						String hql = "from Student where stuAddTime > '" + start +"' and stuAddTime <= '"+end+"' order by "+sortname+" "+sortorder;;
						Query query=session.createQuery(hql);
						query.setFirstResult((page-1)*pagesize);
						query.setMaxResults(pagesize);
						return query.list();
					}
				}
			);
		
	}
	/*
	 * ����id����ɾ��
	 */
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!�Ƿ�Ҫ������!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void delete(int id) throws Exception {
		this.getHibernateTemplate().delete(findById(id));
	}
	/*
	 * �����ݿ������һ����¼
	 */
	public void insert(Student stu) throws Exception {
		this.getHibernateTemplate().save(stu);
		
	}
	/*
	 * ����id���в�ѯ
	 */
	public Student findById(int id) throws Exception {
		return (Student) this.getHibernateTemplate().get(Student.class, id);
	}
	/*
	 *�޸�ѧԱ��Ϣ 
	 */
	public void modify(Student stu) throws Exception {
		// TODO Auto-generated method stub
		String hql="from Student where stuId=?";
		Object[] params={stu.getStuId()};
		List<Student> list=this.getHibernateTemplate().find(hql,params);
		Student stu1 =list.get(0);
		stu1.setStuPhone(stu.getStuPhone());
		stu1.setStuMobile(stu.getStuMobile());
		stu1.setStuAddr(stu.getStuAddr());
		stu1.setStuStatus(stu.getStuStatus());
		this.getHibernateTemplate().update(stu1);
	}
	//	��ѯָ���û�����ѧԱ
	public Student findByUsername(String username) throws Exception {
		// TODO Aut
		String hql = "from User where stuUsername=?";
		Object[] params = {username};
		List<Student> list = this.getHibernateTemplate()
				.find(hql,params);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	//	��ѯָ��Email��ѧԱ
	public Student findByEmail(String email) throws SQLException {
		String hql = "from User where stuEmail=?";
		Object[] params = {email};
		List<Student> list = this.getHibernateTemplate()
				.find(hql,params);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	//��ҳ��ѯ
	public List<Student> findPage(final int page,final int size)
	{
		return (List)this.getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
				{
					//ʹ��sessionִ�з�ҳ��ѯ����
					String hql="from Student";
					Query query=session.createQuery(hql);
					query.setFirstResult((page-1)*size);
					query.setMaxResults(size);
					return query.list();
				}
			}
		);
	}
	
	public void update(Student stu) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(stu);
	}
	public int getTotal(String date) {
		//����Date����
		Date newDate = new Date();
		//�������ڵ�ǰʱ�����������
		Calendar cl1 = Calendar.getInstance();
		cl1.setTime(newDate);
		Calendar cl = Calendar.getInstance();
		cl.setTime(newDate);
		//���������
		if(date.equals("taday")){
			cl.add(Calendar.DATE, -1);
		 }
		//������죬һ����������
		if(date.equals("month")){
			cl.add(Calendar.MONTH, -1);
		}
		 //������죬һ���ڵ�����
		if(date.equals("week")){
			cl.add(Calendar.DATE, -7);
		 }
		//������죬�����µ�����
		if(date.equals("premonth")){
			cl.add(Calendar.MONTH,-2);
		}
		Date startDate = cl.getTime();
		Date endDate = cl1.getTime();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd"); 
		//��ʽ����ʼ���ںͽ�������
		String start = dd.format(startDate);
		String end = dd.format(endDate);
		String hql="";
		if(date.equals("all")){
			hql ="select count(*) from Student";
		}else{
			hql="select count(*) from Student where stuAddTime > '" + start +"' and stuAddTime <= '"+end+"'";
		}
		Long total = (Long) this.getHibernateTemplate().find(hql).get(0);
		return total.intValue();
	}
	
	
	public List<Student> search(final int page,final int pagesize,final String sortname,
			final String sortorder,final String str,final String searchName) throws Exception {
		// TODO Auto-generated method stub
		return (List)this.getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
					{
						//ʹ��sessionִ�з�ҳ��ѯ����
						String hql="from Student where "+searchName+" like ? order by "+sortname+" "+sortorder;
						Query query=session.createQuery(hql);
						query.setString(0, ("%"+str+"%"));
						query.setFirstResult((page-1)*pagesize);
						query.setMaxResults(pagesize);
						return query.list();
					}
				}
			);
	}
	
	public int getSearchTotal(String searchName,String str) throws Exception{
		String hql="select count(*) from Student where "+searchName+" like '%"+str+"%'";
		Long total=(Long) this.getHibernateTemplate().find(hql).get(0);
		return total.intValue();	
	}
	public List<StudyRecord> findStudyRecord(final int stuId,final int page,final int pagesize,final String sortname,final String sortorder) {
		return (List)this.getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
					{
						//ʹ��sessionִ�з�ҳ��ѯ����
						String hql="from StudyRecord where stuId =? order by "+sortname+" "+sortorder;
						Query query=session.createQuery(hql);
						query.setInteger(0, stuId);
						query.setFirstResult((page-1)*pagesize);
						query.setMaxResults(pagesize);
						return query.list();
					}
				}
			);
	}
	public List<ExamRecord> findExamRecord(final int stuId,final int page,final int pagesize,final String sortname,final String sortorder) {
		return (List)this.getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
					{
						//ʹ��sessionִ�з�ҳ��ѯ����
						String hql="from ExamRecord where stuId =? order by "+sortname+" "+sortorder;
						Query query=session.createQuery(hql);
						query.setInteger(0, stuId);
						query.setFirstResult((page-1)*pagesize);
						query.setMaxResults(pagesize);
						return query.list();
					}
				}
			);
	}
	public Integer getStudyRecordTotal(int stuId) {
		String hql="select count(*) from StudyRecord where stuId=?";
		Object[] param={stuId};
		Long total=(Long) this.getHibernateTemplate().find(hql,param).get(0);
		return total.intValue();	
	}
	public Integer getExamRecordTotal(int stuId) {
		String hql="select count(*) from ExamRecord where stuId=?";
		Object[] param={stuId};
		Long total=(Long) this.getHibernateTemplate().find(hql,param).get(0);
		return total.intValue();
	}
	
}