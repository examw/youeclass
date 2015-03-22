package com.changh.sccms.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.changh.sccms.dao.AdministratorDAO;
import com.changh.sccms.entity.Administrator;
import com.changh.sccms.entity.Log;
import com.changh.sccms.entity.Student;

public class HibernateAdministratorDAO extends HibernateDaoSupport implements AdministratorDAO {
	//ɾ��ָ�������Ĺ���Ա
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(findById(id));
		
	}
	//��ѯ���еĹ���Ա
	public List<Administrator> findAll() throws Exception {
		List<Administrator> list = null;
		String hql ="from Administrator";
		list=this.getHibernateTemplate().find(hql);
		return list;
	}
	//����������ѯ����Ա
	public Administrator findById(int id) throws Exception {
		Administrator adm = null;
		adm = (Administrator)this.getHibernateTemplate().get(Administrator.class, id);
		return adm;
	}
	//�����û�����ѯ
	public Administrator findByUsername(String username) {
		// TODO Auto-generated method stub
		String hql = "from Administrator where admUsername=?";
		Object[] params={username};
		List list=this.getHibernateTemplate().find(hql, params);
		if(list.isEmpty())
		{
			return null;
		}
		return (Administrator)list.get(0);
	}
	//����������ѯ
	public List<Administrator> findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		String hql = "from Administrator where admName=?";
		Object[] params={name};
		List list=this.getHibernateTemplate().find(hql, params);
		if(list.isEmpty())
		{
			return null;
		}
		return list;
	}
	//����
	public void update(Administrator administrator) throws Exception {
		this.getHibernateTemplate().update(administrator);
	}
	//���
	public void save(Administrator administrator) throws Exception {
		// TODO Auto-generated method stub
		/*
		  ��ŵ����ɹ��򣿣���
		 */
		this.getHibernateTemplate().save(administrator);
	}
	//����
	public List<Administrator> search(String str, String searchName) throws Exception {
		String hql="from Administrator where "+searchName+"=?";
		Object[] params=new Object[1];
		try
		{
			int id = Integer.parseInt(str);
			params[0]=id;
		}catch(Exception e)
		{
			params[0]=str;
		}
		
		List list=this.getHibernateTemplate().find(hql, params);
		return list;
	}
	public List<Administrator> search(String content) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer buf =new StringBuffer("from Administrator ");
		try
		{
			int id = Integer.parseInt(content);
			buf.append(" where admId like '%");
			buf.append(content);
			buf.append("%'");
		}catch(Exception e)
		{
			buf.append(" where admUsername like '%");
			buf.append(content);
			buf.append("%'");
			buf.append(" or admName like '%");
			buf.append(content);
			buf.append("%' ");
		}
		return this.getHibernateTemplate().find(buf.toString());
	}
	//��ҳ��ѯ
	public List<Student> findPage(final int page,final int pagesize,final String sortName)
	{
		return (List)this.getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
				{
					//ʹ��sessionִ�з�ҳ��ѯ����
					String hql="from Adminstrator order by "+sortName;
					Query query=session.createQuery(hql);
					query.setFirstResult((page-1)*pagesize);
					query.setMaxResults(pagesize);
					return query.list();
				}
			}
		);
	}
	public void saveLog(Log log) throws Exception {
		this.getHibernateTemplate().save(log);
		
	}
	
	public List<Log> selectLog(final String date,final int page,final int pagesize,final String sortname,final String sortorder) throws Exception {
		return (List)this.getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
					{
						if(date.equals("1")||date.equals("0")){
							String hql ="from Log where operatetype='"+date+"' order by "+sortname+" "+sortorder;
							Query query=session.createQuery(hql);
							query.setFirstResult((page-1)*pagesize);
							query.setMaxResults(pagesize);
							return query.list();
						}
						if(date.equals("all")){
							String hql ="from Log  order by "+sortname+" "+sortorder;
							Query query=session.createQuery(hql);
							query.setFirstResult((page-1)*pagesize);
							query.setMaxResults(pagesize);
							return query.list();
						}else{
							//ʹ��sessionִ�з�ҳ��ѯ����
							//����Date����
							Date newdate = new Date();
							Calendar cl1 = Calendar.getInstance();
							cl1.setTime(newdate);
							cl1.add(Calendar.DATE, 1);
							//�������ڵ�ǰʱ�����������
							Calendar cl = Calendar.getInstance();
							cl.setTime(newdate);
							//���������
							if(date.equals("taday")){
								cl.add(Calendar.DATE, -1);
							 }
							 //������죬һ���ڵ�����
							if(date.equals("week")){
								cl.add(Calendar.DATE, -7);
							 }

							
							Date startDate = cl.getTime();
							Date endDate = cl1.getTime();
							SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd"); 
							//��ʽ����ʼ���ںͽ�������
							String start = dd.format(startDate);
							String end = dd.format(endDate);
							String hql = "from Log where operatetime >'" + start +"' and operatetime <= '"+end+"' order by "+sortname+" "+sortorder;
							Query query=session.createQuery(hql);
							query.setFirstResult((page-1)*pagesize);
							query.setMaxResults(pagesize);
							return query.list();
						}
						
					}
				}
			);
		
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
			hql ="select count(*) from Log";
		}else if(date.equals("1")||date.equals("0")){
			hql="select count(*) from Log where operatetype="+date;
		}else{
			hql="select count(*) from Log where operatetime > '" + start +"' and operatetime <= '"+end+"'";
		}
		Long total = (Long) this.getHibernateTemplate().find(hql).get(0);
		return total.intValue();
	}
	
	public List<Log> findLog(final int page,final int pagesize,final String sortname,
			final String sortorder,final String str,final String searchName) throws Exception {
		// TODO Auto-generated method stub
		return (List)this.getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
					{
						//ʹ��sessionִ�з�ҳ��ѯ����
						String hql="from Log where "+searchName+" like ? order by "+sortname+" "+sortorder;
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
		String hql="select count(*) from Log where "+searchName+" like '%"+str+"%'";
		Long total=(Long) this.getHibernateTemplate().find(hql).get(0);
		return total.intValue();	
	}
	public void deleteLog() {
		Date newDate = new Date();
		//�������ڵ�ǰʱ�����������
		Calendar cl = Calendar.getInstance();
		cl.setTime(newDate);
		cl.add(Calendar.DATE, -10);
		Date startDate = cl.getTime();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd"); 
		//��ʽ����ʼ���ںͽ�������
		String start = dd.format(startDate);
		String hql = "from Log where  operatetime <='"+start+"'";
		this.getHibernateTemplate().deleteAll(this.getHibernateTemplate().find(hql));
	}
}
