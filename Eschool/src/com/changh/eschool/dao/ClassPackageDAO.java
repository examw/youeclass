package com.changh.eschool.dao;

import java.util.List;

import com.changh.eschool.entity.ClassPackage;
import com.changh.eschool.entity.Deal;
import com.changh.eschool.entity.Grade;
import com.changh.eschool.entity.PackageCategory;
import com.changh.eschool.entity.StuDeal;


public interface ClassPackageDAO {
	//������examId�������е��ײ�
	public  List<ClassPackage> findClassPackage(int examId);
	//������Id�Լ��ײ����ֲ����ײ�Id
	public int findPackageCategory(String pkgName,int examId);
	//��pkgId�����������ײ�����
	public List<PackageCategory> findPackageId(int ptypePid);
	//���ײ����Ͳ������Եľ����ײ�
	public List<ClassPackage> findPackage(int ptyeId);
	/**
	 * ���Ҹÿ�����������һ���ײ�����
	 * @param examId
	 * @return
	 */
	public List<PackageCategory> findByExamId(int examId);
	//findbyid
	public ClassPackage findById(int pkgId);
	//�����ײ�����İ༶
	public List<Grade> findByPkgId(int pkgId);
	/**
	 * �����ײ�Id���ҿ���Id
	 * @param pkgId
	 * @return
	 */
	public int findExamId(int ptypeId);
	/**
	 * �����ײ�id��ѯ���ڿ��Եĵڶ�������
	 */
	public String findExamName(int pkgId)throws Exception;
	/**
	 * ����Э��by dealId
	 * @param dealId
	 * @return
	 */
	public Deal findByDealId(int dealId);
	/**
	 * ����ѧ��Э��
	 * @param sd
	 */
	public void saveStuDeal(StuDeal sd);
	/**
	 * �޸�ѧ��Э��
	 */
	public void update(StuDeal sd);
	/**
	 * ɾ��ѧ��Э��
	 */
	public void deleteStuDeal(StuDeal sd);
	/**
	 * ����ѧ��Э��
	 */
	public StuDeal findByStuDealId(int stuDealId);
	/**
	 * ���ײͲ���Э��
	 * @param pkgId
	 * @return
	 */
	public Deal findDealByPkgId(int pkgId);
	/**
	 * ���ѧ��Э��id
	 */
	public int findStuDealId();
	/**
	 * ���ҵ�ǰѧ����Э��
	 * @param stuId
	 * @return
	 */
	public List<StuDeal> findStuDealByStuId(int stuId,int page,int pagesize);
	/**
	 * ����ѧ��Э�������
	 * @param stuId
	 * @return
	 */
	public int findStuDealTotal(int stuId);
	
}
