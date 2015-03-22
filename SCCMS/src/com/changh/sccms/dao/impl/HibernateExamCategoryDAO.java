package com.changh.sccms.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.changh.sccms.dao.ExamCategoryDAO;
import com.changh.sccms.entity.ExamCategory;
import com.changh.sccms.entity.ExamCategory1;

public class HibernateExamCategoryDAO  extends HibernateDaoSupport implements ExamCategoryDAO {
	
	private List<ExamCategory1>  examCategoryList;
	
	@SuppressWarnings("unchecked")
	public List<ExamCategory1> findAll1() throws Exception {	
		if(examCategoryList==null){
			String hql ="from ExamCategory1";
			examCategoryList = this.getHibernateTemplate().find(hql);
		}
		return examCategoryList;
		
	}

	public List<ExamCategory1> findByPid1(int pid) throws Exception {
		String hql="from ExamCategory1 c where c.examPid=?";
		Object[] params ={pid};
		return this.getHibernateTemplate().find(hql,params);
	}
	
	/////////////////////////////////////////////////////////////////
	public List<ExamCategory> findAll() throws Exception {
		String hql="from ExamCategory c where c.examPid=0 order by c.examOrderId";
		List<ExamCategory> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public ExamCategory findById(int examId) throws Exception {
		return (ExamCategory) this.getHibernateTemplate().get(ExamCategory.class, examId);
	}

	public void update(int id,String examName,String examUrl,String examDescription) throws Exception {
		examCategoryList = null;
		String hql ="select ec from ExamCategory ec where ec.id="+id;
		List list =this.getHibernateTemplate().find(hql);
		ExamCategory ec = (ExamCategory) list.get(0);
		ec.setExamName(examName);
		ec.setExamDescription(examDescription);
		ec.setExamUrl(examUrl);
		this.getHibernateTemplate().update(ec);
	}

	/*public void delete(int examId) throws Exception {
		if(null!=(ExamCategory) this.getHibernateTemplate().get(ExamCategory.class, examId)){
		this.getHibernateTemplate().delete(findById(examId));
		}
	}*/

	public void save(ExamCategory exam) throws Exception {
		examCategoryList = null;
		this.getHibernateTemplate().save(exam);
	}

	public int fingByPid(int examPid) throws Exception {
		String hql ="select count(*) from ExamCategory ec where ec.examPid="+examPid;
		List list=this.getHibernateTemplate().find(hql);
		Long count =(Long) list.get(0);
		
		return  count.intValue();
	}

	public void delete(int examPid,int id) throws Exception {
		examCategoryList = null;
		String hql ="select ec from ExamCategory ec where ec.examId="+examPid;
		List list =this.getHibernateTemplate().find(hql);
		//�����ϼ����Եĺ�������1
		if(list!=null&&list.size()!=0){
			ExamCategory ec = (ExamCategory) list.get(0);
			
			ec.setExamChildrenNum(ec.getExamChildrenNum()-1);
			this.getHibernateTemplate().update(ec);
		}
		
		String hql1 ="select ec from ExamCategory ec where ec.id="+id;
		List list1 = this.getHibernateTemplate().find(hql1);
		ExamCategory ec1 = (ExamCategory) list1.get(0);
		//ͬ������ orderId��ǰ��Ķ���һ
		String hql2="select ec from ExamCategory ec where ec.examPid="
					+examPid+" and ec.examOrderId >"+ec1.getExamOrderId();
		List<ExamCategory> list2 =this.getHibernateTemplate().find(hql2);
		for(ExamCategory exam :list2){
			exam.setExamOrderId(exam.getExamOrderId()-1);
			this.getHibernateTemplate().update(exam);
		}
		//ɾ�������ӿ������
		deleteByExamPid(ec1.getExamId());
		this.getHibernateTemplate().delete(ec1);
	}
	
	public void deleteByExamPid(int pid){
		examCategoryList = null;
		try {
			String hql3="select ec from ExamCategory ec where ec.examPid="+pid;
			List<ExamCategory> list = this.getHibernateTemplate().find(hql3);
			if(list!=null&&list.size()!=0){
				//�ݹ�ɾ��
				for(ExamCategory ec:list){
					deleteByExamPid(ec.getExamId());
					this.getHibernateTemplate().delete(ec);
				}
			}
		} catch (DataAccessException e) {
			System.out.println("ɾ��ʧ��");
			e.printStackTrace();
		}
	}

	public ExamCategory findByIdExamAdd(int id) throws Exception {
		String hql ="select ec from ExamCategory ec where ec.id="+id;
		List list =this.getHibernateTemplate().find(hql);
		if(list!=null&&list.size()!=0){
			ExamCategory ec = (ExamCategory) list.get(0);	
			int count =ec.getExamChildrenNum();
			ec.setExamChildrenNum(count+1);
			this.getHibernateTemplate().update(ec);
			String hql1 ="select ec from ExamCategory ec where ec.id="+id;
			List list1 =this.getHibernateTemplate().find(hql);
			ExamCategory ec1 = (ExamCategory) list.get(0);
			return ec1;
		}else{
			return null;
		}
		
	}

	public List<ExamCategory> findByExamPid(int examPid) {
		String hql ="select ec from ExamCategory ec where ec.examPid = "+examPid;
		List<ExamCategory> list =this.getHibernateTemplate().find(hql);
		if(list==null||list.size()==0){
			String hql1 ="select ec from ExamCategory ec where ec.examId = "+examPid;
			return this.getHibernateTemplate().find(hql1);
		}
		return list;
	}

	public boolean checkAdd(int examId) {
		String hql ="select count(*) from Grade g where g.examCategory.examId="+examId;
		Long countG =(Long) this.getHibernateTemplate().find(hql).get(0);
		/*String hql1 ="select count(*) from PackageCategory pc where pc.examId="+examId;
		Long countPC = (Long) this.getHibernateTemplate().find(hql1).get(0);*/
		if(countG.intValue()==0/*&&countPC.intValue()==0*/){
			return true;
		}
		return false;
	}

	public int getExamId() {
		String hql = "select max(ec.examId) from  ExamCategory ec";	
		Integer curr_id = (Integer)this.getHibernateTemplate().find(hql).get(0);//��ȡ����idֵ
		if(curr_id == null){//���û�м�¼,����һ��0001���
			return 1001;
		}
		//2.���ݵ�ǰid+1,��ȡ��һ��
		return curr_id+1;
	}
	
	public List<ExamCategory> findByPro(String pro,Object value){
		return 	this.getHibernateTemplate().find("from ExamCategory ec where  ec."+pro+" = ? and ec.status =0", value);
	}
	

}
