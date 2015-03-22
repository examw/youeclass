package com.changh.sccms.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.changh.sccms.dao.TipsDAO;
import com.changh.sccms.entity.Tips;

public class HibernateTipsDAO extends HibernateDaoSupport implements TipsDAO {

	@Override
	public void save(Tips ac) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(ac);
	}

	@Override
	public void update(Tips ac) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(ac);
	}

	@Override
	public Tips findById(Integer TipsId) throws Exception {
		// TODO Auto-generated method stub
		String hql ="from Tips ac where ac.id = ?";
		Object[] params={TipsId};
		List<Tips> list= this.getHibernateTemplate().find(hql,params);
		if(list.isEmpty()) return null;
		return list.get(0);
	}

	@Override
	public List<Tips> findPageByCriteria(final int page,final  int pagesize,
			final String sortname, final String sortorder,final  String criteria)
			throws Exception {
		// TODO Auto-generated method stub
		return (List)this.getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
					{
						//使用session执行分页查询代码
						String hql="select ac from Tips ac "+criteria+" order by "+sortname+" "+sortorder;
						Query query=session.createQuery(hql);
						query.setFirstResult((page-1)*pagesize);
						query.setMaxResults(pagesize);
						return query.list();
					}
				}
			);
	}

	@Override
	public long findTotal(String criteria) throws Exception {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Tips ac "+criteria;
		List<Long> list =this.getHibernateTemplate().find(hql);
		return list.get(0);
	}
	@Override
	public void delete(Integer tipsId) throws Exception {
		// TODO Auto-generated method stub
		if(tipsId == null || tipsId == 0)
			return;
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Tips.class, tipsId));
	}
}
