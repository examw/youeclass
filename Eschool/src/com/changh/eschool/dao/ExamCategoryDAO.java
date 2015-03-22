package com.changh.eschool.dao;

import java.util.List;

import com.changh.eschool.entity.ExamCategory;
import com.changh.eschool.entity.ExamCategory1;


public interface ExamCategoryDAO {
		//����hibernate�ļ���
		public List<ExamCategory1> findAll1()throws Exception;
		public List<ExamCategory1> findByPid1(int pid)throws Exception;
		//////////////////////////
		//�ҳ����еĿ���
		public List<ExamCategory> findAll()throws Exception;
		//��ָ��id�Ŀ���
		public ExamCategory  findById(int id)throws Exception;
		//�޸Ŀ�����Ϣ
		public void update(int id, String examName)throws Exception;
		//ɾ��
		public void delete(int examPid,int id)throws Exception;
		//���
		public void save(ExamCategory exam)throws Exception;
		//���ҵ�ǰ Ϊpid������
		public int fingByPid(int examPid) throws Exception;
		//��id����ExamCategory
		public ExamCategory findByIdExamAdd(int id) throws Exception;
		//��pid����ExamCategory
		public List<ExamCategory> findByExamPid(int examPid);
		//����Ƿ�����ڵ�ǰ����������ӷ���
		public boolean checkAdd(int examId);
		//��ѯĳѧԱ����γ������Ŀγ�С��
		public List<ExamCategory> findByStuId(int stuId) throws Exception;
		
		// 2013.07.18 for mobile
		public List<ExamCategory1> search(String keywords) throws Exception;
		
}
