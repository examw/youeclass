package com.changh.sccms.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.changh.sccms.dao.QuestionDAO;
import com.changh.sccms.entity.Question;

public class HibernateQuestionDAO extends HibernateDaoSupport implements QuestionDAO {
	//find by id
	public Question findById(int questionId) throws Exception {
		return (Question) this.getHibernateTemplate().get(Question.class, questionId);
	}
	//��ҳ
	public List<Question> findPage(final int page,final  int pagesize, final String sortname,
			final String sortorder) throws Exception {
		// TODO Auto-generated method stub
		return (List)this.getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
					{
						//ʹ��sessionִ�з�ҳ��ѯ����
						String hql="from Question order by "+sortname+" "+sortorder;
						Query query=session.createQuery(hql);
						query.setFirstResult((page-1)*pagesize);
						query.setMaxResults(pagesize);
						return query.list();
					}
				}
			);
	}
	//��������������ҳ
	public List<Question> searchPage(final int page, final int pagesize,final  String sortname,
			final String sortorder,final String content) throws Exception {
		// TODO Auto-generated method stub
		return (List)this.getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
					{
						//ʹ��sessionִ�з�ҳ��ѯ����
						String hql="from Question "+content+" order by "+sortname+" "+sortorder;
						Query query=session.createQuery(hql);
						query.setFirstResult((page-1)*pagesize);
						query.setMaxResults(pagesize);
						return query.list();
					}
				}
			);
		
		
	}
	//����
	public void save(Question question) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(question);
	}
	//find by stuId
	public List<Question> findByStuId(int stuId) throws Exception {
		String hql ="from Question where stuId = "+stuId;
		
		return this.getHibernateTemplate().find(hql);
	}
}
