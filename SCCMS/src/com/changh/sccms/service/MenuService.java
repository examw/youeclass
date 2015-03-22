package com.changh.sccms.service;

import java.util.List;

import com.changh.sccms.entity.Menu;
import com.changh.sccms.entity.Role;

public interface MenuService {
	public List<Menu> findByPidMenu(int pid);
	/**
	 * �޸Ļ��߱���
	 * @param m
	 */
	public void saveOrUpdate(Menu m);
	/**
	 * 
	 * @param menuId
	 * @param pid
	 * @return
	 */
	public List<Menu> findMenuById(List list,int pid);
	/**
	 * ɾ��
	 */
	public boolean delete(int id);
	/**
	 * ��ɫ����
	 */
	public List<Role> findAll();
	/**
	 * �޸Ļ�����ӽ�ɫ
	 */
	public void saveOrUpdateRole(Role role);
	/**
	 * ɾ����ɫ
	 */
	public void deleteRole(int id);
	/**
	 * �鿴
	 */
	public Role findById(int id);
}
