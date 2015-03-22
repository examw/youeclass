package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.ClassPackage;
import com.changh.sccms.entity.Deal;
import com.changh.sccms.entity.ExamGrade;
import com.changh.sccms.entity.Grade;
import com.changh.sccms.entity.Grade1;
import com.changh.sccms.entity.PackageCategory;

public interface ClassPackageDAO {
	//��ptypeId�����ײ�
	public List<ClassPackage> findByPtypeId(int ptypeId);
	//��examId�����ײͷ���
	public List<PackageCategory> findByExamId(int examId);
	//��pkgId���Ұ༶
	public List<Grade> findByPkgId(int pkgId);
	//��examId�������а༶
	public List<ExamGrade> gradeTree(int examId);
	//��dealId����Э��
	public Deal findByDealId(int dealId);
	//����ȫ��Э��
	public List<Deal> findAllDeal();
	//��pkgIncludeCid�����ײͿ�ʱ
	public int findByPkgIncludeCid(String pkgIncludeCid);
	//����ײ�
	public void save(ClassPackage  classPackage);
	//�����ײ���������Ƿ��о����ײ�
	public boolean checkPackage(int ptypeId);
	//����ײ����
	public void savePackageCategory(PackageCategory packageCategory);
	//��ptypeId�����ײ����
	public PackageCategory loadCategory(int ptypeId);
	//�޸��ײ����
	public void ModifyPackageCategory(PackageCategory packageCategory);
	//ɾ���ײ����
	public boolean deletePackageCategory(int ptypeId);
	//�� pkgId�����ײ�
	public ClassPackage loadClassPackage(int pkgId);
	//�޸ľ����ײ�
	public void ModifyClassPackage(ClassPackage classPackage);
	//ɾ�������ײ�
	public void DeleteClassPackage(int pkgId);
	//��pkgId�����������ײ�����
	public List<PackageCategory> findPackageId(int ptypePid);
	//���ײ����Ͳ������Եľ����ײ�
	public List<ClassPackage> findPackage(int ptyeId);
	/**
	 * ������ʦ�İ༶
	 * @param pkgId
	 * @return
	 */
	public List<Grade1> findGrade1ByPkgId(int pkgId);
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
	public void AddDeal(Deal deal);
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