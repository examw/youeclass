package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.Administrator;
import com.changh.sccms.entity.Log;
import com.changh.sccms.entity.Student;

public interface AdministratorDAO {
	//�ҳ����еĹ���Ա
	public List<Administrator> findAll()throws Exception;
	//��ָ��id�Ĺ���Ա
	public Administrator findById(int id)throws Exception;
	//��ָ���û����Ĺ���Ա
	public Administrator findByUsername(String username);
	//��ָ�������Ĺ���Ա
	public List<Administrator> findByName(String name)throws Exception;
	//�޸Ĺ���Ա��Ϣ
	public void update(Administrator administrator)throws Exception;
	//ɾ��
	public void delete(int id)throws Exception;
	//���
	public void save(Administrator administrator)throws Exception;
	//����1
	public List<Administrator> search(String str,String searchName)throws Exception;
	//����2
	public List<Administrator> search(String content)throws Exception;
	//�����־
	public void saveLog(Log log) throws Exception; 
	/**
	 * ��ȷ����
	 * @param page
	 * @param pagesize
	 * @param sortname
	 * @param sortorder
	 * @param str
	 * @param searchName
	 * @return
	 * @throws Exception
	 */
	public List<Log> findLog(int page,int pagesize,String sortname,String sortorder,String str,String searchName) throws Exception;
	/**
	 * ʱ��ε���־
	 * @param date
	 * @param page
	 * @param pagesize
	 * @param sortname
	 * @param sortorder
	 * @return
	 * @throws Exception
	 */
	public List<Log> selectLog(String date,int page,int pagesize,String sortname,String sortorder) throws Exception;
	/**
	 * total
	 * @param searchName
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public int getSearchTotal(String searchName,String str) throws Exception;
	/**
	 *	ʱ��total
	 * @param date
	 * @return
	 */
	public int getTotal(String date);
	/**
	 * ɾ��ʮ��ǰ����־
	 */
	public void deleteLog();
	
}
