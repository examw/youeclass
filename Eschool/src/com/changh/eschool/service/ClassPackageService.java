package com.changh.eschool.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.changh.eschool.action.mobile.GradeGroup;
import com.changh.eschool.entity.ClassPackage;
import com.changh.eschool.entity.Deal;
import com.changh.eschool.entity.Grade;
import com.changh.eschool.entity.Package;
import com.changh.eschool.entity.PackageCategory;
import com.changh.eschool.entity.SimpleTeacher;
import com.changh.eschool.entity.StuDeal;

/**
 * @Title: ClassPackageService.java
 * @Package com.changh.eschool.service
 * @Description: TODO(�ײ�service)
 * @author Failymiss jiangwei3457@163.com
 * @date 2013-6-7 ����10:00:51
 * @version V1.0
 */
public interface ClassPackageService {
	/**
	 * @Title: findById
	 * @Description: TODO(��ID�����ײ�)
	 * @return �趨�ļ�
	 */
	public ClassPackage findById(int pkgId);

	/**
	 * ���ҿ�������������ײ�
	 * 
	 * @param examId
	 * @return
	 */
	public List<List<ClassPackage>> findByExamId(int examId);

	public List<GradeGroup> findPackageGroupByExamId(int examId) ;
	/**
	 * �����ײ���������԰༶�Ĳ�ͬ��ʦ����
	 */
	public HashSet<SimpleTeacher> findTchName(int pkgId);

	/**
	 * @Title: findPCbyExamId
	 * @Description: TODO(�ҵ��ÿ�����������һ���ײ����)
	 * @param examId
	 * @return �趨�ļ�
	 */
	public List<PackageCategory> findPCbyExamId(int examId);

	/**
	 * @Title: findClassPackage
	 * @Description: TODO(�ҵ����ײ��������������ײ�)
	 * @param ptypeId
	 * @return �趨�ļ�
	 */
	public List<ClassPackage> findClassPackage(int ptypeId);
	/**
	 * @Title: findClassPackageNoTeacher
	 * @Description: TODO(�ҵ����ײ��������������ײ�(��������ʦ))
	 * @param ptypeId
	 * @return �趨�ļ�
	 */
	public List<ClassPackage> findClassPackageNoTeacher(int ptypeId);

	/**
	 * �����ײ���������а༶
	 * 
	 * @param pkgId
	 * @return List<Grade>
	 */
	public List<Grade> findByPkgId(int pkgId);

	/**
	 * s ����Э��by dealId
	 * 
	 * @param dealId
	 * @return
	 */
	public Deal findByDealId(int dealId);

	/**
	 * ����ѧ��Э��
	 * 
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
	 * 
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
	 * 
	 * @param stuId
	 * @return
	 */
	public List<StuDeal> findStuDealByStuId(int stuId, int page, int pagesize);

	/**
	 * ������
	 * 
	 * @param stuId
	 * @return
	 */
	public int findStuDealTotal(int stuId);

}
