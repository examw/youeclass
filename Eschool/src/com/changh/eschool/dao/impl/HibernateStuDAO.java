package com.changh.eschool.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.changh.eschool.dao.StudentDAO;
import com.changh.eschool.entity.Cooperate;
import com.changh.eschool.entity.Student;
import com.changh.eschool.entity.Teacher;


public class HibernateStuDAO extends HibernateDaoSupport implements StudentDAO{
	//�ҳ����У�
	public List<Student> findAll()throws Exception
	{
		String hql ="from Student";
		List<Student> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	/*
	 * �ҳ����ʱ����d1����d2֮�������ѧԱ
	 * ������d1��d2 ����ʱ�䣬����d1����d2
	 */
	public List<Student> select(String date) throws Exception {
		//����Date����
		Date endDate = new Date();
		//�������ڵ�ǰʱ�����������
		Calendar cl = Calendar.getInstance();
		cl.setTime(endDate);
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
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd"); 
		//��ʽ����ʼ���ںͽ�������
		String start = dd.format(startDate);
		String end = dd.format(endDate);
		//���(flag��=month) &&  (flag��=week), ���ѯ�ľ��ǵ��������
		String hql = "from Student where stuAddTime > '" + start +"' and stuAddTime <= '"+end+"'";
		//String hql = "from Student where stuAddTime between ? and ?";
		//Object[] params={start,end};
		List<Student> list=this.getHibernateTemplate().find(hql);
		return list;
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
		System.out.println(stu.getStuId());
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
	public void update(Student stu) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(stu);
	}
	//	��ѯָ���û�����ѧԱ
	public Student findByUsername(String username) throws Exception {
		// TODO Aut
		String hql = "from Student where stuUsername=?";
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
		String hql = "from Student where stuEmail=?";
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
	public boolean isExist(String username, String email) throws Exception {
		// TODO Auto-generated method stub

		String hql = "from Student  where stuUsername =? or stuEmail =?";
		Object[] params ={username,email}; 
		List<Student> list = this.getHibernateTemplate().find(hql, params);
		//Ϊ�ձ�ʾ������
		return !list.isEmpty();
	}
	public Student findByStuId(int stuId) {
		// TODO Auto-generated method stub
		String hql ="from Student s where s.stuId="+stuId;
		return (Student) this.getHibernateTemplate().find(hql).get(0);
	}

	
	/**********************************************/
	/*����������ѯ*/
	/**********************************************/
	
	public Cooperate findCooperate(String url){
		
		List<Cooperate> list = this.getHibernateTemplate()
				.find("from Cooperate  c where c.coopUrl=?",url);
		if(!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
		
	}
	/**********************************************/
	/*���ֻ��Ų�ѯ	2014.04.11*/
	/**********************************************/
	@Override
	public Student findByMobilePhone(String phone) {
		// TODO Auto-generated method stub
		String hql = "from Student where stuMobile = ?";
		Object[] params = {phone};
		List<Student> list = this.getHibernateTemplate()
				.find(hql,params);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	/**********************************************/
	/*��TOken��ѯ	2014.04.11*/
	/**********************************************/
	@Override
	public Student findByToken(String token) {
		String hql = "from Student where stuToken = ?";
		Object[] params = {token};
		List<Student> list = this.getHibernateTemplate()
				.find(hql,params);
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
}