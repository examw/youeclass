package com.changh.sccms.service;

import java.util.List;

import com.changh.sccms.entity.ClassPackage;
import com.changh.sccms.entity.Deal;
import com.changh.sccms.entity.ExamGrade;
import com.changh.sccms.entity.Grade;
import com.changh.sccms.entity.PackageCategory;

public interface ClassPackageService {
	/**
	 * ���ҿ�������������ײ�
	 * @param examId
	 * @return
	 */
	public  List<List<ClassPackage>> findClassPackageByExamId(int examId);
	public List<PackageCategory> findByExamId(int examId);
	public List<ClassPackage> findByPtypeId(int ptypeId);
	public List<Grade> findByPkgId(int pkgId);
	public List<ExamGrade> gradeTree(int examId);
	//�������ж���
	public List<Deal> findAllDeal();
	//��pkgIncludeCid �����ײͿ�ʱ
	public int findByPkgIncludeCid(String pkgIncludeCid);
	//save classPackage
	public void save(ClassPackage classPackage);
	//�ж��ײ���������Ƿ���ھ����ײ�
	public boolean checkPackage(int ptypeId);
	//����ײ����
	public void savePackageCategory(PackageCategory packageCategory);
	//load�ײ����
	public PackageCategory loadCategory(int ptypeId);
	//modify�ײ����
	public void modifyPackageCategory(PackageCategory packageCategory);
	//ɾ���ײ����
	public boolean deletePackageCategory(int ptypeId);
	//load�����ײ�
	public ClassPackage loadClassPackage(int pkgId);
	//��dealId����Э��
	public Deal findByDealId(int dealId);
	//�޸ľ����ײ�
	public void modifyClassPackage(ClassPackage classPackage);
	//ɾ�������ײ�
	public void deleteClassPackage(int pkgId);
	/**
	* @Title: findPCbyExamId
	* @Description: TODO(�ҵ��ÿ�����������һ���ײ����)
	* @param examId
	* @return    �趨�ļ�
	 */
	public List<PackageCategory> findPCbyExamId(int examId);
	/**
	 * �ײ����id
	 * @return
	 */
	public int getPackageCategoryId();
	/**
	 * ����ײ�id
	 * @return
	 */
	public int getClassPackageId();
	/**
	 * ���Э��
	 * @param deal
	 */
	public void addDeal(Deal deal);
	/**
	 * ���Э��ID
	 * @return
	 */
	public int findDealId();
	/**
	 * �޸�Э��
	 */
	public void update(Deal deal);
	/**
	 * ɾ��Э��
	 */
	public void  delet(Deal deal);
}
