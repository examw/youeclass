package com.changh.sccms.service;

import java.util.List;

import com.changh.sccms.entity.NewClass;

public interface NewClassService {
	//ɾ��
	public boolean delete(int id);
	//�޸�or
	public boolean saveOrupdate(NewClass newclass);
	//��ѯ
	public NewClass findById(int id);
	//getTotal
	public Integer getTotal();
	
	//�����Բ�ѯ
	public List<NewClass> findByPro(String pro,Object obj);
	
	public List<NewClass> getAll();
	
	public List<NewClass> findByIds(String ids);
}
