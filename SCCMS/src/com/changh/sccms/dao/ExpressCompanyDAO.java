package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.ExpressCompany;

public interface ExpressCompanyDAO {
	//�ҳ����еĿ�ݹ�˾
	public List<ExpressCompany> findAll()throws Exception;
	//��ָ��id�Ŀ�ݹ�˾
	public ExpressCompany findById(int id)throws Exception;
	//��ָ�����ƵĿ�ݹ�˾
	public List<ExpressCompany> findByName(String name)throws Exception;
	//�޸Ŀ�ݹ�˾��Ϣ
	public void update(ExpressCompany expressCompany)throws Exception;
	//ɾ��
	public void delete(int id)throws Exception;
	//���
	public void save(ExpressCompany expressCompany)throws Exception;
	//����
	public List<ExpressCompany> search(String str,String searchName)throws Exception;
}
