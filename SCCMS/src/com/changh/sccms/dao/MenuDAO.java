package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.Menu;
import com.changh.sccms.entity.Role;

public interface MenuDAO {
	/**
	 * �����Ӳ˵�
	 * @param pid
	 * @return
	 */
	public List<Menu> findByPidMenu(int pid);
	/**
	 * ���û����Ʋ˵�
	 * @param pid
	 * @param list
	 * @return
	 */
	public List<Menu> findByAdmin(int pid,List list);
	/**
	 * ��id���Ҳ˵�
	 * @param id
	 * @return
	 */
	public Menu findMenuById(int id);
	/**
	 * �޸Ļ򱣴�
	 * @param m
	 */
	public void saveOrUpdate(Menu m);
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
