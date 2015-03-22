package com.changh.sccms.dao;

import java.util.List;

import com.changh.sccms.entity.ExamCategory;
import com.changh.sccms.entity.Grade;
import com.changh.sccms.entity.Grade1;
import com.changh.sccms.entity.GradeCategory;
import com.changh.sccms.entity.Teacher;

public interface GradeDAO {
	//���ҵ�ǰ���Ե����԰༶
	public List<Grade> findbyExamId(int id);
	//���ҵ�ǰ���Ե�������ʦ
	public List<Object[]> findByExamPid(int examId,int examPid);
	//���ҵ�ǰ�༶�����
	public List<GradeCategory> findAll();
	/**
	 * ��ӿ������
	 * @param gc
	 * @return
	 */
	public boolean saveGradeCategory(GradeCategory gc);
	/**
	 * ��ȡgtypeId
	 * @return
	 */
	public int getGCId();
	//�����ϼ�������������п���
	public List<ExamCategory> findByPid(int examPid);
	//��Id����Grade
	public Grade findById(int gradeId);
	//ɾ���༶
	public void delete(int gradeId);
	//��gtypeId���Ұ༶����
	public GradeCategory findByGtypeId(int gtypeId);
	//�޸İ༶
	public void update(Grade grade);
	//��Ӱ༶
	public void save(Grade grade);
	public int getGradeId();
	/**
	 * ������ �Ͱ༶���Ͳ��Ұ༶
	 * @param gtypeName
	 * @param examId
	 * @return
	 */
	public List<Grade1> findGradeByExamPidAndGradeCategory(String gtypeName,int examId);
	/**
	 * ���ҵ�ǰ��ʦ���̵İ༶
	 * @param tchId
	 * @return
	 */
	public List<Grade1> findByTchId(int tchId);
	
	//2014.03.15
	public String findExamName(final int gradeId) throws Exception ;
	//2014.03.17
	public List<Grade> findbyExamPid(int pid);
	//2014.06.09
	public void update(Integer gradeId,String dataFileUrl);
}
